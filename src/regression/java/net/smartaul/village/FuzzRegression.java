package net.smartaul.village;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.ToIntFunction;
import net.minecraft.*;
import net.smartaul.config.*;

/** Fixed-seed properties over the production search and persistence entry points. */
final class FuzzRegression {
   private static final long BASE = 0x534D41525441554CL;
   private static final int MAPS = 3000, NBT_CASES = 1000, JSON_CASES = 64;
   private static long seed;
   private static String phase;
   private static int reached;

   static void run() {
      long began = System.nanoTime();
      reached = 0;
      try {
         for (int i = 0; i < MAPS; i++) {
            seed = BASE + i; phase = "navigation";
            navigation(new Random(seed), i);
         }
         for (int i = 0; i < NBT_CASES; i++) {
            seed = BASE ^ (0x10000L + i); phase = "NBT";
            persistence(new Random(seed));
         }
         phase = "JSON/migration";
         json();
      } catch (Exception | AssertionError error) {
         throw new AssertionError("Fuzz failure phase=" + phase + " seed=" + seed, error);
      }
      require(reached >= 500, "coverage: at least 500 reachable maps");
      double seconds = (System.nanoTime() - began) / 1e9;
      require(seconds < 30, "fuzz addition exceeds 30s: " + seconds);
      System.out.printf(Locale.ROOT, "Fuzz regression: PASS (%d maps / %d searches / %d reachable, %d NBT cases, %d JSON cases; %.3fs; base seed=%d)%n",
         MAPS, MAPS * 3 + MAPS / 4, reached, NBT_CASES, JSON_CASES, seconds, BASE);
   }

   private static void require(boolean ok, String label) {
      if (!ok) throw new AssertionError(label + " [" + phase + " seed=" + seed + "]");
   }

   private record MapData(int[][] heights, int[][] weights, boolean[][] water) {
      int terrain(class_2338 p) {
         int x = p.method_10263(), z = p.method_10260();
         if (x < 0 || z < 0 || x >= heights.length || z >= heights[0].length
            || heights[x][z] != p.method_10264()) return -1;
         return weights[x][z];
      }
      boolean transition(class_2338 a, class_2338 b) {
         // Model a worker unable to climb out of water onto a higher bank.
         return !water[a.method_10263()][a.method_10260()] || b.method_10264() <= a.method_10264();
      }
   }

   private static void navigation(Random r, int index) {
      int w = 7 + r.nextInt(10), h = 7 + r.nextInt(8);
      var map = new MapData(new int[w][h], new int[w][h], new boolean[w][h]);
      for (int x = 0; x < w; x++) for (int z = 0; z < h; z++) {
         // Mix flat mazes, terraces, steep cliffs, isolated pits and weighted water.
         int mode = index % 4;
         map.heights[x][z] = 64 + (mode == 0 ? 0 : mode == 1 ? x / 3 : r.nextInt(5) - 2);
         map.water[x][z] = r.nextInt(7) == 0;
         map.weights[x][z] = r.nextInt(5) == 0 ? -1 : map.water[x][z] ? 25 : r.nextInt(4) * 3;
      }
      map.weights[0][h / 2] = 0;
      var start = new class_2338(0, map.heights[0][h / 2], h / 2);
      var goal = new class_2338(w - 1, map.heights[w - 1][h / 2], h / 2);
      int optimum = dijkstra(map, start, goal);
      long began = System.nanoTime();
      var full = NavigationSearch.find(start, goal, map::terrain, map::transition, 6000, 0);
      require(full.reached() == (optimum >= 0), "Dijkstra reachability");
      int cost = validatePath(map, start, goal, full, 6000);
      if (full.reached()) {
         reached++;
         require(cost == optimum, "Dijkstra cost expected=" + optimum + " actual=" + cost);
      }
      int limit = 1 + r.nextInt(8);
      var partial = NavigationSearch.find(start, goal, map::terrain, map::transition, limit, 0);
      validatePath(map, start, goal, partial, limit);
      // 1ns deterministically expires after the mandatory first expansion. Avoid millisecond races.
      var timed = NavigationSearch.find(start, goal, map::terrain, map::transition, 6000, 1);
      validatePath(map, start, goal, timed, 1);
      require(timed.expanded() == 1 && !timed.reached(), "time budget");
      require(System.nanoTime() - began < 1_000_000_000L, "search watchdog (1s scheduler allowance)");
      if (index % 4 == 0) {
         ToIntFunction<class_2338> corridor = p -> p.method_10264() == 64 && p.method_10260() == 0
            && p.method_10263() >= 0 && p.method_10263() <= 100 ? 0 : -1;
         var progress = NavigationSearch.find(new class_2338(0,64,0), new class_2338(100,64,0), corridor, (a,b) -> true, 4, 0);
         require(progress.expanded() == 4 && !progress.reached() && progress.points().size() == 3,
            "exhausted node budget retains actual progress");
      }
   }

   private static int validatePath(MapData map, class_2338 start, class_2338 goal, NavigationSearch.Result result, int limit) {
      require(result.expanded() <= limit, "expansion limit");
      var previous = start;
      var seen = new HashSet<class_2338>(); seen.add(start);
      int cost = 0;
      for (var p : result.points()) {
         int dx = Math.abs(p.method_10263() - previous.method_10263());
         int dz = Math.abs(p.method_10260() - previous.method_10260());
         int dy = p.method_10264() - previous.method_10264();
         require(dx <= 1 && dz <= 1 && dx + dz > 0 && Math.abs(dy) <= 1, "adjacent safe step/drop");
         require(map.terrain(p) >= 0 && map.transition(previous, p) && seen.add(p), "supported traversable acyclic path");
         if (dx == 1 && dz == 1) {
            require(dy == 0 && map.terrain(new class_2338(p.method_10263(), previous.method_10264(), previous.method_10260())) >= 0
               && map.terrain(new class_2338(previous.method_10263(), previous.method_10264(), p.method_10260())) >= 0, "no corner cutting");
         }
         cost += (dx + dz == 2 ? 14 : 10) + Math.abs(dy) * 4 + map.terrain(p);
         previous = p;
      }
      if (result.reached()) require(previous.method_10262(goal) <= 2.25, "real goal endpoint");
      return cost;
   }

   // Independent graph oracle: enumerate pairs of supported cells, not A* neighbours/heuristics.
   private static int dijkstra(MapData map, class_2338 start, class_2338 goal) {
      record Visit(class_2338 pos, int cost) {}
      var vertices = new ArrayList<class_2338>();
      for (int x = 0; x < map.heights.length; x++) for (int z = 0; z < map.heights[0].length; z++)
         if (map.weights[x][z] >= 0) vertices.add(new class_2338(x, map.heights[x][z], z));
      var queue = new PriorityQueue<Visit>(Comparator.comparingInt(Visit::cost));
      var distance = new HashMap<class_2338, Integer>();
      distance.put(start, 0); queue.add(new Visit(start, 0));
      while (!queue.isEmpty()) {
         var v = queue.remove();
         if (distance.get(v.pos) != v.cost) continue;
         if (v.pos.method_10262(goal) <= 2.25) return v.cost;
         for (var b : vertices) {
            int x = Math.abs(b.method_10263() - v.pos.method_10263());
            int z = Math.abs(b.method_10260() - v.pos.method_10260());
            int y = Math.abs(b.method_10264() - v.pos.method_10264());
            if (x > 1 || z > 1 || y > 1 || x + z == 0 || !map.transition(v.pos, b)) continue;
            if (x + z == 2 && (y != 0
               || map.terrain(new class_2338(b.method_10263(), v.pos.method_10264(), v.pos.method_10260())) < 0
               || map.terrain(new class_2338(v.pos.method_10263(), v.pos.method_10264(), b.method_10260())) < 0)) continue;
            int candidate = v.cost + (x + z == 2 ? 14 : 10) + y * 4 + map.terrain(b);
            if (candidate < distance.getOrDefault(b, Integer.MAX_VALUE)) {
               distance.put(b, candidate); queue.add(new Visit(b, candidate));
            }
         }
      }
      return -1;
   }

   private static UUID uuid(Random r) { return new UUID(r.nextLong(), r.nextLong()); }
   private static class_2338 position(Random r) { return new class_2338(r.nextInt(1000), 64 + r.nextInt(30), r.nextInt(1000)); }

   /** Mutate every serialized key, including nested list/compound payloads. */
   private static void corrupt(class_2487 n, Random r, int depth) {
      for (String key : new TreeSet<>(n.method_10541())) {
         switch (r.nextInt(8)) {
            case 0 -> n.method_10551(key);
            case 1 -> n.method_10582(key, "invalid UUID / identifier !");
            case 2 -> n.method_10544(key, r.nextBoolean() ? Long.MAX_VALUE : Long.MIN_VALUE);
            case 3 -> n.method_10569(key, r.nextBoolean() ? Integer.MAX_VALUE : Integer.MIN_VALUE);
            case 4 -> n.method_10566(key, new class_2487());
            case 5 -> n.method_10564(key, new long[]{Long.MIN_VALUE, Long.MAX_VALUE, 0, 0});
            default -> {
               if (depth < 3) {
                  n.method_10562(key).ifPresent(c -> corrupt(c, r, depth + 1));
                  for (var entry : n.method_68569(key)) if (entry instanceof class_2487 c) corrupt(c, r, depth + 1);
               }
            }
         }
      }
      n.method_10582("unknown_future_field", "ignored");
   }

   private static void persistence(Random r) {
      var emptyPlan = CreativeCityPlan.fromNbt(new class_2487());
      require(!emptyPlan.active, "missing creative identity must not resume work");
      ConstructionJob.x20eftzvkuvpro(new class_2487());
      Colony.x3bvvkrzdde479(new class_2487());
      var colony = new Colony(uuid(r), position(r), 96 + r.nextInt(400), 0L);
      var job = new ConstructionJob(uuid(r), TownHall.TEMPLATE, position(r), 1+r.nextInt(20), 1+r.nextInt(20), 1+r.nextInt(20));
      job.x29hoj3w31nt46(100 + r.nextInt(1000), r.nextInt(10000));
      if (r.nextBoolean()) job.x208u8540wth7a(uuid(r));
      for (int i = 0; i < r.nextInt(20); i++) job.x26iyzb2d5j0bx();
      colony.x31ve0dsfeaxo4(job);
      for (int i = 0; i < 40; i++) {
         var cover = position(r);
         colony.x2xqc3q79vfkw3().put(cover, class_2246.field_10445.method_9564());
         if (i < 32) colony.safetyCovers.add(cover);
      }
      colony.completedSafetyCovers = r.nextLong() & Long.MAX_VALUE;
      var cn = colony.xufzblerzrsnk();
      var restored = Colony.x3bvvkrzdde479(cn).xufzblerzrsnk();
      require(cn.equals(restored), "valid colony/safety/job roundtrip");
      var legacySuspect = colony.xufzblerzrsnk();
      legacySuspect.method_10566("reported_thief", new class_2495(new int[]{1, 2, 3, 4}));
      require(Colony.x3bvvkrzdde479(legacySuspect).xufzblerzrsnk().method_68564("reported_thief", "")
         .equals(class_4844.method_26276(new int[]{1, 2, 3, 4}).toString()), "legacy optional UUID retained");
      var jn = job.xklnhlce5e8jn();
      require(jn.equals(ConstructionJob.x20eftzvkuvpro(jn).xklnhlce5e8jn()), "valid job roundtrip");
      cn.method_10564("safety_covers", colony.x2xqc3q79vfkw3().keySet().stream().mapToLong(class_2338::method_10063).toArray());
      require(Colony.x3bvvkrzdde479(cn).safetyCovers.size() == 32, "oversized safety queue capped");
      corrupt(cn, r, 0); corrupt(jn, r, 0);
      var loaded = Colony.x3bvvkrzdde479(cn);
      var normalized = loaded.xufzblerzrsnk();
      require(normalized.method_68083("radius", 0) >= 96
         && normalized.method_68083("last_population", -1) >= 0 && normalized.method_68083("last_population", 201) <= 200
         && normalized.method_68083("last_beds", -1) >= 0 && normalized.method_68083("last_beds", 1001) <= 1000
         && normalized.method_68083("catchup_labor", -1) >= 0 && normalized.method_68083("catchup_labor", 12001) <= 12000, "colony bounds");
      require(loaded.safetyCovers.size() <= 32 && loaded.completedSafetyCovers >= 0
         && loaded.safetyCovers.stream().allMatch(loaded.x2xqc3q79vfkw3()::containsKey), "bounded supported safety queue");
      var lj = ConstructionJob.x20eftzvkuvpro(jn);
      require(lj.xmu75rcopbjnh() >= 0 && lj.xxjwqetbgzzle() >= 0 && lj.x37hzrzyr2xivs() >= 0
         && lj.x37hzrzyr2xivs() <= 3 && lj.xbnccd29nwem4() >= 0 && lj.xbnccd29nwem4() <= 1200, "job bounds");
      var plan = new CreativeCityPlan(uuid(r), position(r));
      plan.colonies.add(uuid(r)); plan.elapsed = r.nextLong() & Long.MAX_VALUE;
      plan.frontier = 1 + r.nextInt(100000); plan.activeColony = r.nextInt(2);
      plan.chunkCursor = r.nextInt(CreativeCityPlan.CHUNK_LIMIT + 1);
      plan.ownedChunks.add(r.nextLong()); plan.farms.put(plan.root, position(r));
      var pn = plan.toNbt();
      require(pn.equals(CreativeCityPlan.fromNbt(pn).toNbt()), "valid creative plan roundtrip");
      var oversized = new long[CreativeCityPlan.CHUNK_LIMIT + 100];
      for (int i = 0; i < oversized.length; i++) oversized[i] = i;
      pn.method_10564("owned_chunks", oversized);
      require(CreativeCityPlan.fromNbt(pn).ownedChunks.size() == CreativeCityPlan.CHUNK_LIMIT, "oversized chunk set capped");
      corrupt(pn, r, 0);
      var lp = CreativeCityPlan.fromNbt(pn);
      require(lp.elapsed >= 0 && lp.frontier >= 1 && lp.chunkCursor >= 0 && lp.chunkCursor <= CreativeCityPlan.CHUNK_LIMIT
         && lp.roadCursor >= 0 && lp.roadCursor <= 7695 && lp.farmCursor >= 0 && lp.farmCursor <= 348
         && lp.siteAttempt >= 0 && lp.siteAttempt <= 289 && lp.activeColony >= 0 && lp.activeColony <= lp.colonies.size()
         && lp.ownedChunks.size() <= CreativeCityPlan.CHUNK_LIMIT && lp.colonies.getFirst().equals(lp.root), "creative bounds/root");
   }

   private static void json() throws Exception {
      Path dir = Files.createTempDirectory("smart-aul-fuzz-");
      var gson = new Gson();
      String previousSettings = gson.toJson(SmartAulConfig.x2by5f9ehxvwk9());
      try {
         Path legacy = dir.resolve("smart_colonies.json"), current = dir.resolve("smart_aul.json");
         for (int i = 0; i < JSON_CASES; i++) {
            seed = BASE ^ (0x20000L + i); var r = new Random(seed);
            JsonObject village = gson.toJsonTree(new VillageConfig()).getAsJsonObject();
            for (String key : new TreeSet<>(village.keySet())) {
               boolean bool = village.getAsJsonPrimitive(key).isBoolean();
               switch (r.nextInt(7)) {
                  case 0 -> village.remove(key);
                  case 1 -> { if (bool) village.addProperty(key, false); else village.addProperty(key, Integer.MIN_VALUE); }
                  case 2 -> { if (bool) village.addProperty(key, true); else village.addProperty(key, Integer.MAX_VALUE); }
                  case 3 -> village.add(key, com.google.gson.JsonNull.INSTANCE);
                  case 4 -> { if (i % 8 != 6) village.addProperty(key, "wrong type"); }
                  default -> { }
               }
            }
            var root = new JsonObject(); root.add("village", village); root.addProperty("future", 42);
            String document = switch (i % 8) {
               case 0 -> "{"; case 1 -> "null"; case 2 -> "[]";
               case 3 -> "{\"village\":null,\"work\":null,\"roles\":null,\"debug\":null}";
               case 4 -> "{\"village\":{\"development_speed\":1e100}}";
               default -> gson.toJson(root);
            };
            Files.deleteIfExists(current); Files.writeString(legacy, document);
            ConfigMigration.copyLegacy(current, legacy);
            require(Files.readString(current).equals(document) && Files.readString(legacy).equals(document), "migration copies bytes/preserves source");
            SmartAulConfig.x2ce1ihtn5bm4e(current);
            var c = SmartAulConfig.x2by5f9ehxvwk9().village;
            String validated = gson.toJson(c);
            require(c.max_population_per_bell >= 2 && c.max_population_per_bell <= 200 && c.max_constructed_houses >= 4
               && c.max_constructed_houses <= 200 && c.development_speed >= 1 && c.development_speed <= 8
               && c.builder_blocks_per_action >= 1 && c.builder_blocks_per_action <= 8
               && c.city_population >= Math.min(8, c.max_population_per_bell) && c.city_population <= c.max_population_per_bell
               && c.city_houses >= 1 && c.city_houses <= c.max_constructed_houses
               && c.settlement_min_population >= Math.min(12,c.max_population_per_bell) && c.settlement_min_population <= c.max_population_per_bell
               && c.settlement_min_distance >= 192 && c.settlement_min_distance <= 1024
               && c.settlement_max_distance >= c.settlement_min_distance && c.settlement_max_distance <= 1536
               && c.settlement_border_gap >= 16 && c.settlement_border_gap <= 128
               && c.settlement_retry_ticks >= 200 && c.settlement_retry_ticks <= 12000
               && c.settlement_cooldown_ticks >= 1200 && c.settlement_cooldown_ticks <= 72000
               && c.settlement_capital_interval_ticks >= 100 && c.settlement_capital_interval_ticks <= 2400
               && c.settlement_timeout_ticks >= 2400 && c.settlement_timeout_ticks <= 72000, "validated config bounds");
            DevelopmentPolicy.validate(c);
            require(validated.equals(gson.toJson(c)), "validation idempotent");
            String saved = Files.readString(current);
            ConfigMigration.copyLegacy(current, legacy);
            require(saved.equals(Files.readString(current)), "existing new config wins");
            SmartAulConfig.x2ce1ihtn5bm4e(current);
            require(saved.equals(Files.readString(current)), "valid JSON roundtrip identical");
         }
      } finally {
         try (var entries = Files.list(dir)) { for (Path p : entries.toList()) Files.delete(p); }
         Files.delete(dir);
         // Restore the settings that were active before the fuzz run.
         Path reset = Files.createTempFile("smart-aul-fuzz-reset-", ".json");
         try { Files.writeString(reset, previousSettings); SmartAulConfig.x2ce1ihtn5bm4e(reset); }
         finally { Files.deleteIfExists(reset); }
      }
   }
}
