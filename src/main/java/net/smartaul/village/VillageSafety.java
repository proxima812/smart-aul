package net.smartaul.village;

import net.minecraft.*;

/** Detect exposed drops near village workers; build only from an existing safe rim. */
public final class VillageSafety {
   private VillageSafety() {}
   static boolean loaded(class_3218 world, class_2338 pos) {
      return !world.method_31606(pos) && world.method_8621().method_11952(pos)
         && world.method_14178().method_12123(pos.method_10263() >> 4, pos.method_10260() >> 4);
   }

   static boolean solid(class_3218 world, class_2338 ground) {
      if (!loaded(world, ground) || !world.method_8316(ground).method_15769()) return false;
      var state = world.method_8320(ground);
      return state.method_26206(world, ground, class_2350.field_11036)
         && !state.method_27852(class_2246.field_10092) && !state.method_27852(class_2246.field_17350);
   }

   static boolean deepDrop(class_3218 world, class_2338 feet) {
      return deepDrop(depth -> {
         var pos = feet.method_10087(depth);
         if (!loaded(world, pos) || !world.method_8316(pos).method_15769()) return 2;
         return world.method_8320(pos).method_26215() ? 0 : 1;
      });
   }

   /** 0 = air, 1 = obstacle/landing, 2 = fluid or unknown terrain. */
   static boolean deepDrop(java.util.function.IntUnaryOperator column) {
      for (int depth = 1; depth <= 3; depth++) {
         int cell = column.applyAsInt(depth);
         if (cell == 2) return true;
         if (cell == 1) return false;
      }
      return true;
   }

   static int edgePenalty(class_3218 world, class_2338 feet) {
      int penalty = 0;
      for (var dir : class_2350.class_2353.field_11062)
         if (deepDrop(world, feet.method_10093(dir))) penalty += 12;
      return penalty + (world.method_8316(feet).method_15769() ? 0 : 30);
   }

   static boolean canCover(class_3218 world, Colony colony, class_2338 ground) {
      var feet = ground.method_10084();
      if (!loaded(world, ground) || !loaded(world, feet.method_10084())
         || SettlementSupport.distanceSquared(ground, colony.core()) > (double)colony.radius() * colony.radius()
         || Math.abs(feet.method_10264() - colony.core().method_10264()) > 8
         || !world.method_8320(ground).method_26215() || !world.method_8320(feet).method_26215()
         || !world.method_8320(feet.method_10084()).method_26215() || !deepDrop(world, feet)
         || colony.x1ja61emeln2hc().stream().anyMatch(j -> j.x24kbqv6g4kkrg().method_14662(ground))
         || !WorldEditPolicy.x13f7be5vlapn7(world, ground)) return false;
      for (var dir : class_2350.class_2353.field_11062) {
         var rim = ground.method_10093(dir);
         if (solid(world, rim) && world.method_8320(rim.method_10084()).method_26215()
            && world.method_8320(rim.method_10086(2)).method_26215()) return true;
      }
      return false;
   }

   static void observe(class_3218 world, Colony colony) {
      if (!WorldEditPolicy.x35ev8l9vc9uk7(world)) return;
      // Cancel obsolete requests without touching unloaded chunks or charging materials.
      var pending = java.util.List.copyOf(colony.safetyCovers);
      for (int i = 0; i < Math.min(4, pending.size()); i++) {
         var pos = pending.get((int)Math.floorMod(world.method_75260() / 20 + i, pending.size()));
         if (solid(world, pos)) {
            if (colony.safetyCovers.remove(pos) && colony.completedSafetyCovers < Long.MAX_VALUE) colony.completedSafetyCovers++;
            colony.x2xqc3q79vfkw3().remove(pos);
            ColonyWorldData.get(world).method_80();
         } else if (loaded(world, pos) && !canCover(world, colony, pos)) {
            colony.safetyCovers.remove(pos);
            colony.x2xqc3q79vfkw3().remove(pos);
            ColonyWorldData.get(world).method_80();
         }
      }
      if (colony.safetyCovers.size() >= 32) return;
      var workers = world.method_18023(class_1299.field_6077, ColonyManager.x150ccxomrlozr(world, colony),
         v -> v.method_5805() && !v.method_6109());
      if (workers.isEmpty()) return;
      int cursor = (int)Math.floorMod(world.method_75260() / 20, workers.size());
      // One worker and four neighbouring cells per colony per second, never a world scan.
      var feet = workers.get(cursor).method_24515();
      if (!solid(world, feet.method_10074())) return;
      for (var dir : class_2350.class_2353.field_11062) {
         var ground = feet.method_10093(dir).method_10074();
         if (colony.safetyCovers.size() < 32 && !colony.x2xqc3q79vfkw3().containsKey(ground) && canCover(world, colony, ground)) {
            colony.safetyCovers.add(ground.method_10062());
            colony.x2xqc3q79vfkw3().put(ground.method_10062(), class_2246.field_10445.method_9564());
            ColonyWorldData.get(world).method_80();
            ActionTrace.event(world, colony, null, ground, "safety_cover_queued", "exposed_drop");
         }
      }
   }
}
