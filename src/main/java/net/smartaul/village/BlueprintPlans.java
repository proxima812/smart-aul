package net.smartaul.village;

import java.util.*;
import java.util.function.*;
import net.minecraft.*;
import net.smartaul.blueprint.BlueprintPayload;

public final class BlueprintPlans {
   public static final int RADIUS = 80;
   private BlueprintPlans() {}
   /** Pure selection seam; permissions are evaluated before reading world state. */
   public static List<BlueprintPayload.Block> remaining(List<ConstructionBlockPlan> plan,
         Predicate<class_2338> visible, Function<class_2338, class_2680> actual, int limit) {
      var result = new ArrayList<BlueprintPayload.Block>();
      var seen = new HashSet<class_2338>();
      for (var b : plan) {
         if (result.size() >= Math.min(Math.max(0, limit), BlueprintPayload.MAX_BLOCKS)) break;
         var pos = b.x31k0w8t7ul189();
         if (b.x3ln30gt147gjn() || b.x2hmkda3gwcacx().method_26215() || !visible.test(pos)
               || HouseBuilderTask.x1zol2fshkzbul(actual.apply(pos), b.x2hmkda3gwcacx()) || !seen.add(pos)) continue;
         result.add(new BlueprintPayload.Block(pos.method_10063(), class_2248.method_9507(b.x2hmkda3gwcacx())));
      }
      return result;
   }
   public static BlueprintPayload snapshot(class_3218 world, class_2338 player) {
      var blocks = new ArrayList<BlueprintPayload.Block>();
      var bounds = new ArrayList<BlueprintPayload.Bounds>();
      if (!WorldEditPolicy.x35ev8l9vc9uk7(world)) return new BlueprintPayload(blocks, bounds);
      var jobs = ColonyWorldData.get(world).colonies().stream().flatMap(c -> c.x1ja61emeln2hc().stream())
         .filter(j -> j.x32oz43hmeynhc() != ConstructionStatus.COMPLETE)
         .filter(j -> near(player, j.x3ij6gescvakv2(), RADIUS))
         .sorted(Comparator.comparingDouble(j -> distance(player, j.x3ij6gescvakv2())))
         .limit(BlueprintPayload.MAX_BUILDINGS).toList();
      long inspected = 0;
      for (var job : jobs) {
         if (blocks.size() >= BlueprintPayload.MAX_BLOCKS) break;
         var box = job.x24kbqv6g4kkrg();
         long volume = ((long)box.method_35418() - box.method_35415() + 1)
            * ((long)box.method_35419() - box.method_35416() + 1)
            * ((long)box.method_35420() - box.method_35417() + 1);
         if (volume <= 0 || volume > 32768 || inspected + volume > 32768) continue;
         inspected += volume;
         // Do not reveal even a building boundary when its footprint overlaps protected land.
         boolean allowed = true;
         for (int x = box.method_35415(); allowed && x <= box.method_35418(); x++)
            for (int z = box.method_35417(); allowed && z <= box.method_35420(); z++)
               for (int y = box.method_35416(); allowed && y <= box.method_35419(); y++) {
                  var pos = new class_2338(x, y, z);
                  allowed = VillageSafety.loaded(world, pos) && !ColonyWorldData.get(world).isPlayerBlock(pos)
                     && ColonyWorldData.get(world).land().stream().noneMatch(land -> land.contains(pos));
               }
         if (!allowed) continue;
         var plan = HouseBuilderTask.loadPlan(world, job).orElse(List.of());
         var selected = remaining(plan, pos -> near(player, pos, RADIUS) && VillageSafety.loaded(world, pos)
            && !WorldEditPolicy.x3o1lagqjim5tz(world, pos), world::method_8320, BlueprintPayload.MAX_BLOCKS - blocks.size());
         if (selected.isEmpty()) continue;
         blocks.addAll(selected);
         bounds.add(new BlueprintPayload.Bounds(new class_2338(box.method_35415(), box.method_35416(), box.method_35417()).method_10063(),
            new class_2338(box.method_35418() + 1, box.method_35419() + 1, box.method_35420() + 1).method_10063()));
      }
      return new BlueprintPayload(blocks, bounds);
   }
   private static double distance(class_2338 a, class_2338 b) {
      double x = (double)a.method_10263() - b.method_10263(), y = (double)a.method_10264() - b.method_10264(), z = (double)a.method_10260() - b.method_10260();
      return x*x + y*y + z*z;
   }
   public static boolean near(class_2338 a, class_2338 b, int radius) { return distance(a, b) <= (double)radius * radius; }
}
