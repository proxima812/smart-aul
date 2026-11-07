package net.smartaul.village;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import io.netty.buffer.Unpooled;
import net.minecraft.*;
import net.smartaul.blueprint.BlueprintPayload;

final class BlueprintRegression {
   private static void require(boolean value, String label) { if (!value) throw new AssertionError(label); }
   private static void reject(Runnable action) {
      try { action.run(); } catch (IllegalArgumentException expected) { return; }
      throw new AssertionError("Oversized snapshot accepted");
   }
   static void run() {
      var origin = new class_2338(-12, 64, 30);
      var state = class_2246.field_10445.method_9564();
      var plan = List.of(new ConstructionBlockPlan(origin, state, false, false),
         new ConstructionBlockPlan(origin.method_10084(), state, true, false),
         new ConstructionBlockPlan(origin.method_10069(1,0,0), state, false, false),
         new ConstructionBlockPlan(origin, state, false, false));
      require(BlueprintPlans.remaining(plan, p -> true, p -> state, 20).isEmpty(), "installed blocks disappear");
      var reads = new AtomicInteger();
      require(BlueprintPlans.remaining(plan, p -> false, p -> { reads.incrementAndGet(); return state; }, 20).isEmpty()
         && reads.get() == 0, "hidden/unloaded/protected positions never read");
      var missing = BlueprintPlans.remaining(plan, p -> true, p -> class_2246.field_10124.method_9564(), 20);
      require(missing.size() == 2, "clearance and duplicate positions excluded");
      require(BlueprintPlans.remaining(plan, p -> true, p -> class_2246.field_10124.method_9564(), 1).size() == 1, "per-player remaining budget");
      require(BlueprintPlans.remaining(plan, p -> true, p -> state, -1).isEmpty(), "negative budget");
      var many = new ArrayList<ConstructionBlockPlan>();
      for (int i=0; i<5000; i++) many.add(new ConstructionBlockPlan(origin.method_10069(i,0,0), state, false, false));
      require(BlueprintPlans.remaining(many, p -> true, p -> class_2246.field_10124.method_9564(), 99999).size() == BlueprintPayload.MAX_BLOCKS, "hard block limit");
      require(BlueprintPlans.near(origin, origin.method_10069(80,0,0), 80)
         && !BlueprintPlans.near(origin, origin.method_10069(80,1,0), 80), "radius is three dimensional and inclusive");
      var payload = new BlueprintPayload(missing, List.of(new BlueprintPayload.Bounds(origin.method_10063(), origin.method_10069(8,6,8).method_10063())));
      var buf = Unpooled.buffer();
      try {
         BlueprintPayload.CODEC.encode(buf, payload);
         require(payload.equals(BlueprintPayload.CODEC.decode(buf)) && buf.readableBytes() == 0, "real codec round trip");
         BlueprintPayload.CODEC.encode(buf, new BlueprintPayload(List.of(), List.of()));
         require(BlueprintPayload.CODEC.decode(buf).blocks().isEmpty(), "empty replacement clears snapshot");
         buf.clear(); buf.writeInt(BlueprintPayload.MAX_BLOCKS + 1);
         reject(() -> BlueprintPayload.CODEC.decode(buf));
         buf.clear(); buf.writeInt(-1); reject(() -> BlueprintPayload.CODEC.decode(buf));
         buf.clear(); buf.writeInt(0); buf.writeInt(BlueprintPayload.MAX_BUILDINGS + 1);
         reject(() -> BlueprintPayload.CODEC.decode(buf));
         reject(() -> new BlueprintPayload(Collections.nCopies(BlueprintPayload.MAX_BLOCKS + 1, missing.getFirst()), List.of()));
      } finally { buf.release(); }
      System.out.println("Blueprint regressions: PASS");
   }
}
