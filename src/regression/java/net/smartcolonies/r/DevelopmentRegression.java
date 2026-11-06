package net.smartcolonies.r;

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
      var c = new Gson().fromJson("{\"build_delay_ticks\":10}", x3o0tuxsltwqoc.class);
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
      c = new x3o0tuxsltwqoc();
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
      persistence();
      var dispatcher = new com.mojang.brigadier.CommandDispatcher<net.minecraft.class_2168>();
      DevelopmentCommands.register(dispatcher);
      DevelopmentCommands.registerLegacyAlias(dispatcher);
      check(dispatcher.getRoot().getChild("smart_aul").getChild("settle") != null, "new public command namespace");
      check(dispatcher.getRoot().getChild("smartcolonies").getRedirect() == dispatcher.getRoot().getChild("smart_aul"), "old commands redirect to new root");
      configMigration();
      roleNames();
      var roles = java.util.List.of(x1rxiy9mtu5b3h.BUILDER, x1rxiy9mtu5b3h.FARMER, x1rxiy9mtu5b3h.LUMBERJACK,
         x1rxiy9mtu5b3h.MINER, x1rxiy9mtu5b3h.FISHERMAN, x1rxiy9mtu5b3h.RANCHER, x1rxiy9mtu5b3h.GUARD);
      var minimum = DevelopmentPolicy.minimumRoles(4, roles);
      check(minimum.size() == 4 && minimum.containsAll(roles.subList(0, 4)), "four settlers preserve food, housing, wood and stone roles");
      check(DevelopmentPolicy.minimumRoles(1, roles).equals(java.util.List.of(x1rxiy9mtu5b3h.FARMER)), "food survives a reduced workforce");
      check(DevelopmentPolicy.minimumRoles(0, roles).isEmpty(), "no phantom workers");
      check(DevelopmentPolicy.minimumRoles(8, roles).equals(roles), "large villages keep all active roles");
      System.out.println("Development regression: PASS (" + checks + " checks)");
   }

   private static void roleNames() {
      try {
         var method = x2n55y2tom37h2.class.getDeclaredMethod("x2qi06jvtolj3u", net.minecraft.class_2561.class);
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
      var colony = new x15kgoaq6i3m6i(UUID.randomUUID(), new class_2338(400, 64, 0), 96, Long.MIN_VALUE);
      colony.parentColonyId(parent);
      colony.xvuz8u6ue7m7c();
      colony.xveqe1h09qne3(leader, new class_2338(800, 64, 0), 1000L);
      colony.x2zftltl6vhwwx().add(companion);
      colony.x1i1560srleos1(Map.of(class_2960.method_60654("minecraft:oak_log"), 32));
      colony.xy40t7pepehnr(8);
      var job = new x2arpn38dclo1g(UUID.randomUUID(), class_2960.method_60654("minecraft:village/plains/houses/plains_small_house_1"), new class_2338(410, 64, 10), 7, 6, 7);
      job.x248ps383cwu1s(100, 1100L, 8);
      job.xkvyd96wj0qui(UUID.randomUUID(), 1100L);
      job.xdhrxq42ir75w(1120L);
      job.xdhrxq42ir75w(1140L);
      colony.x31ve0dsfeaxo4(job);
      var restored = x15kgoaq6i3m6i.x3bvvkrzdde479(colony.xufzblerzrsnk());
      check(restored.id().equals(colony.id()) && restored.parentColonyId().orElseThrow().equals(parent), "parent and settlement identity roundtrip");
      check(restored.xxqujir3s0ild(), "settlement bootstrap does not spawn extra residents after reload");
      check(restored.x2a380ray4md5t().orElseThrow().equals(leader) && restored.x2zftltl6vhwwx().contains(companion), "expedition people roundtrip");
      check(restored.x1x0agg3sdxjk8().orElseThrow().equals(new class_2338(800, 64, 0)) && restored.x1spmho2xy9muc() == 1000L, "expedition target and time roundtrip");
      check(restored.x3171qim2pcjd7().equals(colony.x3171qim2pcjd7()) && restored.x13ua0ftegttnr() == 8, "cargo and capital roundtrip");
      var savedJob = restored.x1ja61emeln2hc().getFirst();
      check(savedJob.xmu75rcopbjnh() == 2 && savedJob.xxjwqetbgzzle() == 100 && savedJob.x32oz43hmeynhc() == x20r5v7z43p3d5.BUILDING, "construction progress roundtrip");
      savedJob.x248ps383cwu1s(100, 1200L, 8);
      check(savedJob.xmu75rcopbjnh() == 2, "reacquiring the same plan preserves progress");
      restored.x2qmn6nt922pp6();
      restored = x15kgoaq6i3m6i.x3bvvkrzdde479(restored.xufzblerzrsnk());
      check(restored.xfx95ux1xn73w() && restored.x1x0agg3sdxjk8().orElseThrow().equals(restored.core()), "recall persists return to origin");
      check(restored.x3171qim2pcjd7().equals(colony.x3171qim2pcjd7()), "recall preserves cargo");
      class_2487 oldSave = colony.xufzblerzrsnk();
      oldSave.method_10551("parent_colony");
      check(x15kgoaq6i3m6i.x3bvvkrzdde479(oldSave).parentColonyId().isEmpty(), "old saves need no parent field");
      oldSave.method_10582("parent_colony", "invalid");
      check(x15kgoaq6i3m6i.x3bvvkrzdde479(oldSave).parentColonyId().isEmpty(), "malformed optional parent is tolerated");
   }
}
