package net.smartaul.village;

import net.smartaul.command.DevelopmentCommands;
import net.smartaul.config.ConfigMigration;
import net.smartaul.config.DevelopmentPolicy;
import net.smartaul.config.VillageConfig;

import com.google.gson.Gson;
import java.util.Map;
import java.util.UUID;
import net.minecraft.class_2338;
import net.minecraft.class_2487;
import net.minecraft.class_2960;

/** Executable regression checks, no external test framework required. */
public final class DevelopmentRegression {
   private static int checks;
   private static void check(boolean value, String label) {
      checks++;
      if (!value) throw new AssertionError(label);
   }

   public static void main(String[] args) {
      var c = new Gson().fromJson("{\"build_delay_ticks\":10}", VillageConfig.class);
      check(c.development_speed == 3 && c.builder_blocks_per_action == 3, "old configs inherit new defaults");
      check(c.automatic_settlement_requires_city, "automatic expansion waits for a city");
      c.development_speed = Integer.MAX_VALUE;
      c.builder_blocks_per_action = -4;
      c.settlement_min_distance = 2000;
      c.settlement_max_distance = 1;
      c.settlement_retry_ticks = -1;
      DevelopmentPolicy.validate(c);
      check(c.development_speed == 8 && c.builder_blocks_per_action == 1, "work budgets bounded");
      check(c.settlement_min_distance == 1024 && c.settlement_max_distance == 1024, "distance range remains ordered");
      check(c.settlement_retry_ticks == 200, "search backoff has a floor");
      c.max_population_per_bell = 2;
      DevelopmentPolicy.validate(c);
      check(c.city_population == 2 && c.settlement_min_population == 2, "small population cap is valid");
      check(DevelopmentPolicy.delay(10, 3) == 4 && DevelopmentPolicy.delay(1200, 3) == 400, "timing acceleration");
      check(DevelopmentPolicy.delay(1, 8) == 1 && DevelopmentPolicy.delay(10, 0) == 10, "timing never zero");
      check(!DevelopmentPolicy.separated(383 * 383, 256, 96, 32), "reject overlapping territories");
      check(DevelopmentPolicy.separated(384 * 384, 256, 96, 32), "accept exact border gap");
      c = new VillageConfig();
      check(DevelopmentPolicy.stage(32, 11, c).equals("посёлок"), "population alone is not a city");
      check(DevelopmentPolicy.stage(31, 12, c).equals("посёлок"), "houses alone are not a city");
      check(DevelopmentPolicy.stage(32, 12, c).equals("город"), "city requires population and houses");
      check(DevelopmentPolicy.stage(8, 0, c).equals("деревня"), "new settlement starts as a village");
      UUID one = new UUID(0, 1), two = new UUID(0, 2);
      check(DevelopmentPolicy.repairWorker(java.util.List.of(one), true).isEmpty(), "only builder keeps building housing");
      check(DevelopmentPolicy.repairWorker(java.util.List.of(two, one), true).orElseThrow().equals(one), "one deterministic repair worker");
      check(DevelopmentPolicy.repairWorker(java.util.List.of(one, two), true).orElseThrow().equals(one), "repair assignment independent of entity order");
      check(DevelopmentPolicy.repairWorker(java.util.List.of(one), false).orElseThrow().equals(one), "repair remains available without a house job");
      net.minecraft.class_155.method_36208();
      net.minecraft.class_2966.method_12851();
      NavigationSafetyRegression.run();
      LeisureRegression.run();
      persistence();
      expeditionLoss();
      var dispatcher = new com.mojang.brigadier.CommandDispatcher<net.minecraft.class_2168>();
      DevelopmentCommands.register(dispatcher);
      DevelopmentCommands.registerLegacyAlias(dispatcher);
      check(dispatcher.getRoot().getChild("smart_aul").getChild("settle") != null, "new public command namespace");
      check(dispatcher.getRoot().getChild("smartcolonies").getRedirect() == dispatcher.getRoot().getChild("smart_aul"), "old commands redirect to new root");
      var dev = dispatcher.getRoot().getChild("smart_aul").getChild("dev");
      check(dev != null && dev.getCommand() != null, "developer mode has help");
      for (String command : java.util.List.of("on", "off", "status", "supply", "population", "max", "stop",
            "rebalance", "develop", "expedition", "build", "walls", "shortage", "role", "change"))
         check(dev.getChild(command) != null, "developer scenario: " + command);
      configMigration();
      creativeCity(dispatcher);
      creativeRestocking();
      aiDiagnostics();
      roleNames();
      var roles = java.util.List.of(ColonyRole.BUILDER, ColonyRole.FARMER, ColonyRole.LUMBERJACK,
         ColonyRole.MINER, ColonyRole.FISHERMAN, ColonyRole.RANCHER, ColonyRole.GUARD);
      var minimum = DevelopmentPolicy.minimumRoles(4, roles);
      check(minimum.size() == 4 && minimum.containsAll(roles.subList(0, 4)), "four settlers preserve food, housing, wood and stone roles");
      check(DevelopmentPolicy.minimumRoles(1, roles).equals(java.util.List.of(ColonyRole.FARMER)), "food survives a reduced workforce");
      check(DevelopmentPolicy.minimumRoles(0, roles).isEmpty(), "no phantom workers");
      check(DevelopmentPolicy.minimumRoles(8, roles).equals(roles), "large villages keep all active roles");
      System.out.println("Development regression: PASS (" + checks + " checks)");
   }

   private static void aiDiagnostics() {
      check(MinerTask.retryAfter(100, 800) == 800, "miner waits for the actual expedition deadline");
      check(MinerTask.retryAfter(100, 80) == 120, "expired miner deadline cannot restart every tick");
      Object subject = new Object();
      check(ActionTrace.changed(subject, "wait", "blocked", 10), "first wait is recorded");
      check(!ActionTrace.changed(subject, "wait", "blocked", 11), "identical waits do not flood disk");
      check(ActionTrace.changed(subject, "wait", "blocked", 110), "persistent waits retain heartbeat evidence");
      check(ActionTrace.changed(subject, "wait", "new target", 111), "changed reason is recorded immediately");
      check(new Gson().fromJson("{}", net.smartaul.config.DebugConfig.class).log_ai_actions, "old configs enable action tracing by default");
      var cells = new java.util.HashSet<ResourceCounts>();
      for (int i = 0; i < 193 * 193; i++) {
         var cell = BuildingSiteSearch.creativeCandidate(i);
         check(cells.add(cell), "creative candidate permutation never repeats a site");
         check(Math.abs(cell.x3bqzqsu81ak1i()) <= 96 && Math.abs(cell.x17l8srdozs7l()) <= 96, "creative candidate stays in the loaded area");
         if (i < 49 * 49) check(cell.x3bqzqsu81ak1i() % 4 == 0 && cell.x17l8srdozs7l() % 4 == 0, "coarse pass covers distant land before dense rescans");
      }
      var old = new CreativeCityPlan(UUID.randomUUID(), new class_2338(0, 64, 0)).toNbt();
      old.method_10551("search_order_version");
      check(CreativeCityPlan.fromNbt(old).searchOrderVersion == 0, "old search order is marked for migration");
      try {
         var directory = java.nio.file.Files.createTempDirectory("smart-aul-trace-test-");
         var path = directory.resolve("actions.jsonl");
         try (var log = new ActionTrace.Journal(path, 1_000_000, 2, 1024)) {
            for (int i = 0; i < 256; i++) log.offer(new Gson().toJson(Map.of("event", "action", "i", i, "detail", "quote\"\nline")));
         }
         var lines = java.nio.file.Files.readAllLines(path);
         check(lines.size() == 256, "closing the log drains queued actions");
         check(com.google.gson.JsonParser.parseString(lines.getFirst()).getAsJsonObject().get("detail").getAsString().equals("quote\"\nline"), "JSON escaping preserves action details");
         try (var log = new ActionTrace.Journal(path, 500, 2, 1024)) {
            for (int i = 0; i < 100; i++) log.offer(new Gson().toJson(Map.of("event", "rotation", "i", i)));
         }
         check(java.nio.file.Files.exists(directory.resolve("actions.jsonl.2")), "action logs rotate instead of growing forever");
         try (var files = java.nio.file.Files.list(directory)) {
            var all = files.toList();
            check(all.size() == 3, "rotation keeps exactly the configured file count");
            for (var file : all) java.nio.file.Files.delete(file);
         }
         java.nio.file.Files.delete(directory);
      } catch (java.io.IOException error) { throw new AssertionError("action journal", error); }
   }

   private static void creativeRestocking() {
      var calls = new java.util.concurrent.atomic.AtomicInteger();
      VillagerInventory.restock(() -> 0, 16, () -> { calls.incrementAndGet(); return true; });
      check(calls.get() == 1, "infinite source stops when full recipient makes no progress");
      calls.set(0);
      VillagerInventory.restock(() -> 0, 16, () -> { calls.incrementAndGet(); return false; });
      check(calls.get() == 1, "empty source exits immediately");
      calls.set(0);
      var inventory = new net.minecraft.class_1277(1);
      var item = net.minecraft.class_1802.field_8229;
      inventory.method_5447(0, new net.minecraft.class_1799(item, 63));
      VillagerInventory.restock(() -> inventory.method_5438(0).method_7947(), 80, () -> {
         if (!VillagerInventory.canAccept(inventory, item)) return false;
         calls.incrementAndGet();
         inventory.method_5491(new net.minecraft.class_1799(item));
         return true;
      });
      check(calls.get() == 1 && inventory.method_5438(0).method_7947() == 64, "partial stack fills once without overflowing or dropping items");
      check(!VillagerInventory.canAccept(inventory, net.minecraft.class_1802.field_8550), "full inventory rejects a different supply");
      var count = new java.util.concurrent.atomic.AtomicInteger();
      VillagerInventory.restock(count::get, 16, () -> { count.incrementAndGet(); return true; });
      check(count.get() == 16, "infinite source fills a reachable target exactly");
      count.set(0);
      VillagerInventory.restock(count::get, Integer.MAX_VALUE, () -> { count.incrementAndGet(); return true; });
      check(count.get() == 64, "one restock call has a hard work budget");
   }

   private static void creativeCity(com.mojang.brigadier.CommandDispatcher<net.minecraft.class_2168> dispatcher) {
      var command = dispatcher.getRoot().getChild("smart_aul").getChild("dev").getChild("creative");
      check(command != null && command.getCommand() != null, "creative city starts directly");
      check(command.getChild("status") != null && command.getChild("stop") != null, "creative city can be inspected and stopped");
      check(CreativeCityPlan.populationTarget(0) == 6 && CreativeCityPlan.populationTarget(1000) == 32, "population scales with buildings");
      var cells = new java.util.HashSet<String>();
      for (int i = 0; i < 10000; i++) {
         var cell = CreativeCityPlan.cell(i);
         check(cells.add(cell[0] + ":" + cell[1]), "frontier never repeats a grid cell");
         if (i > 0) {
            var previous = CreativeCityPlan.cell(i - 1);
            check(Math.abs(cell[0] - previous[0]) + Math.abs(cell[1] - previous[1]) == 1, "frontier visits adjacent cells");
         }
      }
      check(!CreativeCityPlan.exhausted(24, 256, 0, 0, -300, 300, -300, 300), "border traversal does not stop at first outside cell");
      check(CreativeCityPlan.exhausted(25, 256, 0, 0, -300, 300, -300, 300), "border traversal ends after final ring");
      check(!CreativeCityPlan.exhausted(9, 256, 1000, -1000, -300, 300, -300, 300), "offset origin covers farthest border");
      var plan = new CreativeCityPlan(UUID.randomUUID(), new class_2338(-27, 64, 91));
      plan.elapsed = 9876543210L;
      plan.frontier = 12345;
      plan.activeColony = 7;
      plan.failedTemplates = 2;
      for (int i = 0; i < 20; i++) plan.colonies.add(UUID.randomUUID());
      plan.colonies.add(UUID.randomUUID());
      plan.ownedChunks.add(net.minecraft.class_1923.method_8331(-12, 15));
      plan.roadCursor = 45;
      plan.farmCursor = 305;
      plan.chunkCursor = 200;
      plan.siteAttempt = 9;
      plan.farms.put(plan.root, new class_2338(-10, 64, 108));
      var restored = CreativeCityPlan.fromNbt(plan.toNbt());
      check(restored.root.equals(plan.root) && restored.origin.equals(plan.origin), "creative root persists");
      check(restored.colonies.equals(plan.colonies) && restored.elapsed == 9876543210L, "creative membership and timer persist");
      check(restored.ownedChunks.equals(plan.ownedChunks), "creative chunk ownership persists for cleanup");
      check(restored.farms.equals(plan.farms), "creative reserved farms persist");
      check(restored.roadCursor == 45 && restored.farmCursor == 305 && restored.chunkCursor == 200 && restored.siteAttempt == 9, "creative infrastructure resumes");
      plan.mapComplete = true;
      check(CreativeCityPlan.fromNbt(plan.toNbt()).mapComplete, "completed map keeps its state");
      plan.active = false;
      check(!CreativeCityPlan.fromNbt(plan.toNbt()).active, "stopped creative city stays stopped");
      check(restored.frontier == 12345 && restored.activeColony == 7 && restored.failedTemplates == 2, "continuous frontier resumes past six villages");
      check(restored.rebuildWindow, "saved chunk window is revalidated after restart");
      var legacy = plan.toNbt();
      legacy.method_10551("elapsed_ticks");
      legacy.method_10569("elapsed", 48000);
      check(CreativeCityPlan.fromNbt(legacy).elapsed == 48000, "legacy forty-minute plan migrates without losing time");

   }

   private static void expeditionLoss() {
      UUID leader = UUID.randomUUID(), companion = UUID.randomUUID();
      var colony = new Colony(UUID.randomUUID(), new class_2338(0, 64, 0), 96, 0L);
      colony.xveqe1h09qne3(leader, new class_2338(400, 64, 0), 100L);
      colony.x2zftltl6vhwwx().add(companion);
      colony.x1i1560srleos1(Map.of(class_2960.method_60654("minecraft:oak_log"), 32));
      colony.x2zftltl6vhwwx().add(companion);
      colony.x2zftltl6vhwwx().add(leader);
      var restored = Colony.x3bvvkrzdde479(colony.xufzblerzrsnk());
      check(restored.x2zftltl6vhwwx().equals(java.util.List.of(companion)), "loaded party contains unique companions excluding leader");
      restored.expeditionMemberLost(companion);
      check(restored.xfx95ux1xn73w() && restored.x2zftltl6vhwwx().isEmpty(), "confirmed companion loss recalls survivors");
      var cargo = Map.copyOf(restored.x3171qim2pcjd7());
      restored.expeditionMemberLost(companion);
      restored = Colony.x3bvvkrzdde479(restored.xufzblerzrsnk());
      check(restored.xfx95ux1xn73w() && restored.x3171qim2pcjd7().equals(cargo), "repeated loss and reload preserve return cargo");
      restored.expeditionMemberLost(leader);
      check(restored.x1x26mhvd93s02(), "confirmed leader loss persists for survivor promotion");
   }

   private static void roleNames() {
      try {
         var method = ColonyManager.class.getDeclaredMethod("x2qi06jvtolj3u", net.minecraft.class_2561.class);
         method.setAccessible(true);
         check((boolean)method.invoke(null, net.minecraft.class_2561.method_43470("Elder")), "English elder label can be reassigned");
         check((boolean)method.invoke(null, net.minecraft.class_2561.method_43470("Founder")), "English founder label can be reassigned");
         check(!(boolean)method.invoke(null, net.minecraft.class_2561.method_43470("Player named villager")), "custom villager names stay protected");
      } catch (ReflectiveOperationException error) { throw new AssertionError(error); }
   }

   private static void configMigration() {
      java.nio.file.Path directory = null;
      try {
         directory = java.nio.file.Files.createTempDirectory("smart-aul-config-test-");
         var old = directory.resolve("smart_colonies.json");
         var current = directory.resolve("smart_aul.json");
         java.nio.file.Files.writeString(old, "{old-config}");
         ConfigMigration.copyLegacy(current, old);
         check(java.nio.file.Files.readString(current).equals("{old-config}") && java.nio.file.Files.exists(old), "config migration preserves legacy source");
         java.nio.file.Files.writeString(current, "{new-config}");
         ConfigMigration.copyLegacy(current, old);
         check(java.nio.file.Files.readString(current).equals("{new-config}"), "existing new config is never overwritten");
      } catch (java.io.IOException error) { throw new AssertionError(error); }
      finally {
         if (directory != null) try {
            java.nio.file.Files.deleteIfExists(directory.resolve("smart_colonies.json"));
            java.nio.file.Files.deleteIfExists(directory.resolve("smart_aul.json"));
            java.nio.file.Files.deleteIfExists(directory);
         } catch (java.io.IOException error) { throw new AssertionError(error); }
      }
   }

   private static void persistence() {
      UUID parent = UUID.randomUUID(), leader = UUID.randomUUID(), companion = UUID.randomUUID();
      var colony = new Colony(UUID.randomUUID(), new class_2338(400, 64, 0), 96, Long.MIN_VALUE);
      colony.parentColonyId(parent);
      colony.xvuz8u6ue7m7c();
      colony.xveqe1h09qne3(leader, new class_2338(800, 64, 0), 1000L);
      colony.x2zftltl6vhwwx().add(companion);
      colony.x1i1560srleos1(Map.of(class_2960.method_60654("minecraft:oak_log"), 32));
      colony.xy40t7pepehnr(8);
      var job = new ConstructionJob(UUID.randomUUID(), class_2960.method_60654("minecraft:village/plains/houses/plains_small_house_1"), new class_2338(410, 64, 10), 7, 6, 7);
      job.x248ps383cwu1s(100, 1100L, 8);
      job.xkvyd96wj0qui(UUID.randomUUID(), 1100L);
      job.xdhrxq42ir75w(1120L);
      job.xdhrxq42ir75w(1140L);
      colony.x31ve0dsfeaxo4(job);
      colony.x31uoom8ikakob();
      for (int i = 0; i < 120; i++) colony.x2xopfa6a46mo9();
      var restored = Colony.x3bvvkrzdde479(colony.xufzblerzrsnk());
      check(restored.x1eughlg5bm9gx() == 120, "building site search resumes instead of repeating after restart");
      var oldSearch = colony.xufzblerzrsnk();
      oldSearch.method_10551("building_search_cursor");
      check(!Colony.x3bvvkrzdde479(oldSearch).x2zi111bgbtgzq(), "legacy saves start with idle building search");
      check(restored.id().equals(colony.id()) && restored.parentColonyId().orElseThrow().equals(parent), "parent and settlement identity roundtrip");
      check(restored.xxqujir3s0ild(), "settlement bootstrap does not spawn extra residents after reload");
      check(restored.x2a380ray4md5t().orElseThrow().equals(leader) && restored.x2zftltl6vhwwx().contains(companion), "expedition people roundtrip");
      check(restored.x1x0agg3sdxjk8().orElseThrow().equals(new class_2338(800, 64, 0)) && restored.x1spmho2xy9muc() == 1000L, "expedition target and time roundtrip");
      check(restored.x3171qim2pcjd7().equals(colony.x3171qim2pcjd7()) && restored.x13ua0ftegttnr() == 8, "cargo and capital roundtrip");
      var savedJob = restored.x1ja61emeln2hc().getFirst();
      check(savedJob.xmu75rcopbjnh() == 2 && savedJob.xxjwqetbgzzle() == 100 && savedJob.x32oz43hmeynhc() == ConstructionStatus.BUILDING, "construction progress roundtrip");
      savedJob.x248ps383cwu1s(100, 1200L, 8);
      check(savedJob.xmu75rcopbjnh() == 2, "reacquiring the same plan preserves progress");
      restored.x2qmn6nt922pp6();
      restored = Colony.x3bvvkrzdde479(restored.xufzblerzrsnk());
      check(restored.xfx95ux1xn73w() && restored.x1x0agg3sdxjk8().orElseThrow().equals(restored.core()), "recall persists return to origin");
      check(restored.x3171qim2pcjd7().equals(colony.x3171qim2pcjd7()), "recall preserves cargo");
      class_2487 oldSave = colony.xufzblerzrsnk();
      oldSave.method_10551("parent_colony");
      check(Colony.x3bvvkrzdde479(oldSave).parentColonyId().isEmpty(), "old saves need no parent field");
      oldSave.method_10582("parent_colony", "invalid");
      check(Colony.x3bvvkrzdde479(oldSave).parentColonyId().isEmpty(), "malformed optional parent is tolerated");
   }
}
