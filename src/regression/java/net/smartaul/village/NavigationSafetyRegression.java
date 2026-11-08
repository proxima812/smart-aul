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
         // Resumable search: any slicing gives exactly the one-shot result for the same node limit.
         for (int limit : new int[]{3, 17, 6000}) {
            var session = new NavigationSearch.Session(from, to, terrain, (a, b) -> true);
            int slice = 1 + seed % 7;
            while (!session.finished(limit)) session.advance(limit, slice, 0);
            var sliced = session.result(limit);
            var once = NavigationSearch.find(from, to, terrain, (a, b) -> true, limit, 0);
            require(sliced.equals(once), "sliced search equals one-shot seed " + seed + " limit " + limit);
         }
      }
      longRouteTrap();
      persistenceAndTemplate();
      System.out.println("Navigation/safety/town hall regressions: PASS");
   }

   /**
    * B-002: a concave wall facing the start. A repeated short one-shot search (the old
    * behaviour under a 3 ms slice) walks into the pocket and stops; the resumable search
    * with the long-route node limit plans the detour.
    */
   private static void longRouteTrap() {
      var start = new class_2338(0, 64, 0);
      var goal = new class_2338(170, 64, 0);
      ToIntFunction<class_2338> trap = p -> {
         int x = p.method_10263(), z = p.method_10260();
         if (p.method_10264() != 64 || x < -20 || x > 190 || Math.abs(z) > 120) return -1;
         boolean wall = x == 40 && Math.abs(z) <= 40 || Math.abs(z) == 40 && x >= 20 && x <= 40;
         return wall ? -1 : 0;
      };
      var position = start;
      for (int step = 0; step < 200 && position.method_10262(goal) > 2.25; step++) {
         var partial = NavigationSearch.find(position, goal, trap, (a, b) -> true, 100, 0);
         if (partial.points().isEmpty()) break;
         position = partial.points().getLast();
      }
      require(position.method_10263() < 40 && Math.abs(position.method_10260()) < 40,
         "trap fixture: repeated short searches stall inside the pocket, at " + position);
      int limit = VillagerNavigation.LONG_ROUTE_NODES, weight = VillagerNavigation.LONG_ROUTE_WEIGHT;
      var session = new NavigationSearch.Session(start, goal, trap, (a, b) -> true, weight);
      int slices = 0;
      while (!session.finished(limit)) { session.advance(limit, 100, 0); slices++; }
      var route = session.result(limit);
      require(route.reached(), "resumable search plans the detour (expanded " + route.expanded() + ")");
      require(route.points().stream().allMatch(p -> trap.applyAsInt(p) >= 0), "detour stays on walkable cells");
      var once = new NavigationSearch.Session(start, goal, trap, (a, b) -> true, weight);
      once.advance(limit, Integer.MAX_VALUE, 0);
      require(route.equals(once.result(limit)), "slicing does not change the detour");
      int optimal = pathCost(start, NavigationSearch.find(start, goal, trap, (a, b) -> true, 60_000, 0).points());
      require(pathCost(start, route.points()) * 100 <= optimal * weight, "weighted detour stays within the weight bound");

      // Expedition targets lie beyond the ±192 search window, so the route is planned in legs.
      // With a pocket larger than one leg, the closest-by-estimate node is the wall itself;
      // a leg must follow the lowest-f frontier node, which already includes the detour.
      var far = new class_2338(300, 64, 0);
      ToIntFunction<class_2338> deep = p -> {
         int x = p.method_10263(), z = p.method_10260();
         if (p.method_10264() != 64 || x < -20 || x > 320 || Math.abs(z) > 150) return -1;
         return x == 120 && Math.abs(z) <= 110 || Math.abs(z) == 110 && x >= 20 && x <= 120 ? -1 : 0;
      };
      var greedy = legs(start, far, deep, false);
      require(greedy.method_10263() < 120, "deep trap fixture: closest-node legs stall at the wall, at " + greedy);
      var legEnd = legs(start, far, deep, true);
      require(legEnd.method_10262(far) <= 2.25, "frontier legs reach a target beyond the window, at " + legEnd);
      System.out.println("Long-route trap: PASS (expanded " + route.expanded() + ", slices " + slices + ")");
   }

   /** Plans successive long-route legs, walking to the end of each, like a travelling leader. */
   private static class_2338 legs(class_2338 start, class_2338 goal, ToIntFunction<class_2338> terrain, boolean frontier) {
      int limit = VillagerNavigation.LONG_ROUTE_NODES;
      var position = start;
      for (int leg = 0; leg < 10 && position.method_10262(goal) > 2.25; leg++) {
         var session = new NavigationSearch.Session(position, goal, terrain, (a, b) -> true, VillagerNavigation.LONG_ROUTE_WEIGHT);
         while (!session.finished(limit)) session.advance(limit, 500, 0);
         var path = frontier ? session.frontierResult(limit) : session.result(limit);
         if (path.points().isEmpty()) break;
         require(path.points().stream().allMatch(p -> terrain.applyAsInt(p) >= 0), "leg stays on walkable cells");
         position = path.points().getLast();
      }
      return position;
   }

   private static int pathCost(class_2338 start, List<class_2338> points) {
      int cost = 0; var prev = start;
      for (var point : points) {
         cost += point.method_10263() != prev.method_10263() && point.method_10260() != prev.method_10260() ? 14 : 10;
         prev = point;
      }
      return cost;
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
