package net.smartaul.village;

import java.util.*;
import java.util.function.ToIntFunction;
import net.minecraft.*;

final class NavigationSafetyRegression {
   private static void require(boolean value, String message) {
      if (!value) throw new AssertionError(message);
   }
   static void run() {
      require(!VillageSafety.deepDrop(depth -> 1), "solid ground is not a drop");
      require(!VillageSafety.deepDrop(depth -> depth == 2 ? 1 : 0), "one-block pit has a safe landing");
      require(!VillageSafety.deepDrop(depth -> depth == 3 ? 1 : 0), "two-block pit does not trigger a deep cover");
      require(VillageSafety.deepDrop(depth -> 0), "three-block opening needs a cover");
      require(VillageSafety.deepDrop(depth -> depth == 2 ? 2 : 0), "unknown or fluid below is unsafe");
      var start = new class_2338(0, 64, 0);
      var goal = start.method_10069(12, 0, 0);
      ToIntFunction<class_2338> flat = pos -> pos.method_10264() == 64 && Math.abs(pos.method_10263()) <= 20
         && Math.abs(pos.method_10260()) <= 20 ? 0 : -1;
      var direct = NavigationSearch.find(start, goal, flat, (a, b) -> true, 6000, 0);
      require(direct.reached() && direct.points().size() == 11, "flat shortest route reaches target tolerance");
      require(VillagerNavigation.straightWaypoint(direct.points(), 0) == 7, "straight segments retain bounded lookahead");
      require(VillagerNavigation.straightWaypoint(List.of(start, start.method_10069(1, 0, 0), start.method_10069(1, 0, 1), start.method_10069(2, 0, 1)), 0) == 1,
         "waypoint execution preserves detour corners");
      var diagonal = NavigationSearch.find(start, start.method_10069(8, 0, 8), flat, (a, b) -> true, 6000, 0);
      require(diagonal.reached() && diagonal.points().size() == 7, "diagonal route avoids cardinal zigzags");
      ToIntFunction<class_2338> cliff = pos -> flat.applyAsInt(pos) < 0 ? -1
         : pos.method_10263() >= 3 && pos.method_10263() <= 8 && Math.abs(pos.method_10260()) <= 2 ? -1 : 0;
      var around = NavigationSearch.find(start, goal, cliff, (a, b) -> true, 6000, 0);
      require(around.reached() && around.points().stream().allMatch(p -> cliff.applyAsInt(p) >= 0), "route goes around cave opening");
      for (var point : around.points()) require(point.method_10264() == 64, "no imaginary descent through void");
      ToIntFunction<class_2338> corner = pos -> pos.equals(start) || pos.equals(start.method_10069(1, 0, 1)) ? 0 : -1;
      var blocked = NavigationSearch.find(start, start.method_10069(5, 0, 5), corner, (a, b) -> true, 6000, 0);
      require(blocked.points().isEmpty(), "diagonal cannot pass between blocked corners");
      ToIntFunction<class_2338> ledge = pos -> flat.applyAsInt(pos) < 0 ? -1
         : pos.method_10263() >= 2 && pos.method_10263() <= 9 && pos.method_10260() == 0 ? 100 : 0;
      var safer = NavigationSearch.find(start, goal, ledge, (a, b) -> true, 6000, 0);
      require(safer.reached() && safer.points().stream().noneMatch(p -> ledge.applyAsInt(p) == 100), "prefer safe detour over exposed ledge");
      var partial = NavigationSearch.find(start, goal, flat, (a, b) -> true, 3, 0);
      require(!partial.reached() && partial.expanded() == 3 && !partial.points().isEmpty(), "node budget returns real partial route");
      var timeout = NavigationSearch.find(start, goal, flat, (a, b) -> true, 6000, 1);
      require(timeout.expanded() == 1 && !timeout.reached(), "wall clock budget stops search");
      require(NavigationSearch.estimate(start, start.method_10069(10, 10, 0)) <= 140, "heuristic does not double count ascent");
      var stair = NavigationSearch.find(start, start.method_10069(8, 8, 0),
         p -> p.method_10260() == 0 && p.method_10264() - 64 == p.method_10263() && p.method_10263() >= 0 && p.method_10263() <= 8 ? 0 : -1,
         (a, b) -> true, 6000, 0);
      require(stair.reached(), "single-block staircase is reachable");

      // Compare A* against an independent Dijkstra oracle on deterministic weighted maps.
      for (int seed = 0; seed < 30; seed++) {
         var random = new Random(seed);
         int[][] weights = new int[14][9];
         for (int x = 0; x < 14; x++) for (int z = 0; z < 9; z++) weights[x][z] = random.nextInt(5) == 0 ? -1 : random.nextInt(4) * 7;
         weights[0][4] = 0;
         var from = new class_2338(0, 64, 4); var to = new class_2338(13, 64, 4);
         ToIntFunction<class_2338> terrain = p -> p.method_10264() == 64 && p.method_10263() >= 0 && p.method_10263() < 14
            && p.method_10260() >= 0 && p.method_10260() < 9 ? weights[p.method_10263()][p.method_10260()] : -1;
         var found = NavigationSearch.find(from, to, terrain, (a, b) -> true, 6000, 0);
         int expected = oracle(from, to, terrain);
         require(found.reached() == (expected >= 0), "A* reachability matches Dijkstra seed " + seed);
         if (found.reached()) {
            int cost = 0; var prev = from;
            for (var point : found.points()) {
               cost += (point.method_10263() != prev.method_10263() && point.method_10260() != prev.method_10260() ? 14 : 10) + terrain.applyAsInt(point);
               prev = point;
            }
            require(cost == expected, "A* weighted route matches Dijkstra seed " + seed);
         }
      }
      persistenceAndTemplate();
      System.out.println("Navigation/safety/town hall regressions: PASS");
   }

   private static int oracle(class_2338 start, class_2338 goal, ToIntFunction<class_2338> terrain) {
      record Step(class_2338 pos, int cost) {}
      var open = new PriorityQueue<Step>(Comparator.comparingInt(Step::cost));
      var distances = new HashMap<class_2338, Integer>();
      open.add(new Step(start, 0)); distances.put(start, 0);
      while (!open.isEmpty()) {
         var step = open.poll();
         if (distances.get(step.pos) != step.cost) continue;
         if (step.pos.method_10262(goal) <= 2.25) return step.cost;
         for (int x = -1; x <= 1; x++) for (int z = -1; z <= 1; z++) {
            if (x == 0 && z == 0) continue;
            var next = step.pos.method_10069(x, 0, z);
            int extra = terrain.applyAsInt(next);
            if (extra < 0 || x != 0 && z != 0 && (terrain.applyAsInt(step.pos.method_10069(x, 0, 0)) < 0 || terrain.applyAsInt(step.pos.method_10069(0, 0, z)) < 0)) continue;
            int cost = step.cost + (x != 0 && z != 0 ? 14 : 10) + extra;
            if (cost < distances.getOrDefault(next, Integer.MAX_VALUE)) {
               distances.put(next, cost); open.add(new Step(next, cost));
            }
         }
      }
      return -1;
   }

   private static void persistenceAndTemplate() {
      var colony = new Colony(UUID.randomUUID(), new class_2338(0, 64, 0), 96, 0L);
      require(!TownHall.exists(colony), "old colony can schedule its first town hall");
      var cover = new class_2338(3, 63, 4);
      colony.safetyCovers.add(cover); colony.x2xqc3q79vfkw3().put(cover, class_2246.field_10445.method_9564());
      colony.completedSafetyCovers = (long)Integer.MAX_VALUE + 123;
      var hall = new ConstructionJob(UUID.randomUUID(), TownHall.TEMPLATE, new class_2338(12, 63, 12), 9, 6, 9);
      colony.x31ve0dsfeaxo4(hall);
      var loaded = Colony.x3bvvkrzdde479(colony.xufzblerzrsnk());
      require(TownHall.exists(loaded) && loaded.x1ja61emeln2hc().size() == 1, "queued hall survives reload without duplicate");
      require(loaded.safetyCovers.contains(cover) && loaded.x2xqc3q79vfkw3().containsKey(cover), "safety queue and block state roundtrip together");
      require(loaded.completedSafetyCovers == colony.completedSafetyCovers, "completed safety counter is a long");
      var old = colony.xufzblerzrsnk(); old.method_10551("safety_covers"); old.method_10551("completed_safety_covers");
      var legacy = Colony.x3bvvkrzdde479(old);
      require(legacy.safetyCovers.isEmpty() && legacy.completedSafetyCovers == 0, "missing new NBT fields are compatible");
      var dangling = colony.xufzblerzrsnk();
      dangling.method_10564("safety_covers", new long[]{cover.method_10063(), new class_2338(99, 63, 99).method_10063()});
      require(Colony.x3bvvkrzdde479(dangling).safetyCovers.size() == 1, "orphan safety requests are discarded on load");
      hall.x3lcv8vebl7uhh();
      require(Colony.x3bvvkrzdde479(colony.xufzblerzrsnk()).x1ja61emeln2hc().getFirst().x32oz43hmeynhc() == ConstructionStatus.COMPLETE, "completed town hall survives reload");
      require(TownHall.summaries(colony).get(0).get(2).equals("Готово: 1"), "board reflects actual completed construction");
      require(TownHall.summaries(colony).get(1).get(3).equals("Провалов: 1"), "board reflects pending safety work");
      try (var stream = NavigationSafetyRegression.class.getResourceAsStream("/data/smart_colonies/structure/town_hall.nbt")) {
         require(stream != null, "town hall resource packaged");
         var nbt = class_2507.method_10629(stream, class_2505.method_53899(2_000_000));
         var structure = new class_3499(); structure.method_15183(class_7923.field_41175, nbt);
         require(structure.method_15160().equals(new class_2338(9, 6, 9)), "Minecraft decodes town hall dimensions");
         var states = nbt.method_68569("palette"); var blocks = nbt.method_68569("blocks");
         var map = new HashMap<class_2338, String>();
         for (int i = 0; i < blocks.size(); i++) {
            var block = blocks.method_68582(i); var coords = block.method_68569("pos");
            var pos = new class_2338(coords.method_10600(0).orElseThrow(), coords.method_10600(1).orElseThrow(), coords.method_10600(2).orElseThrow());
            map.put(pos, states.method_68582(block.method_68083("state", -1)).method_68564("Name", ""));
         }
         for (int i = 0; i < 3; i++) {
            var absolute = TownHall.board(hall, i); var origin = hall.x3ij6gescvakv2();
            var pos = new class_2338(absolute.method_10263() - origin.method_10263(), absolute.method_10264() - origin.method_10264(), absolute.method_10260() - origin.method_10260());
            require("minecraft:oak_wall_sign".equals(map.get(pos)), "physical board matches interaction coordinates");
            require("minecraft:oak_planks".equals(map.get(pos.method_10069(0, 0, -1))), "board has supporting wall");
         }
         require(!map.containsKey(new class_2338(4, 1, 8)) && !map.containsKey(new class_2338(4, 2, 8)), "town hall entrance remains open");
      } catch (java.io.IOException ex) { throw new AssertionError(ex); }
   }
}
