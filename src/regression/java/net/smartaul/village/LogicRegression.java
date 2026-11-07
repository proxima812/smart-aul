package net.smartaul.village;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;
import net.minecraft.*;

/** World-free behavior checks. Bootstrapped by DevelopmentRegression before run(). */
final class LogicRegression {
   private static int checks;
   private static final UUID OWNER = new UUID(0, 101), OTHER = new UUID(0, 102);

   private static void check(boolean value, String label) {
      checks++;
      if (!value) throw new AssertionError(label);
   }

   static void run() {
      checks = 0;
      landAndReservations();
      inventoryAndConversion();
      economyAndRecipes();
      deeds();
      wallPersistence();
      System.out.println("Logic regressions: PASS (" + checks + " checks)");
   }

   private static Colony colony() {
      return new Colony(OTHER, new class_2338(0, 64, 0), 96, 0L);
   }

   private static void landAndReservations() {
      var center = new class_2338(-16, 64, -16);
      var claim = new LandClaim(OWNER, OTHER, center, 8);
      for (int x = -9; x <= 9; x++) for (int z = -9; z <= 9; z++) {
         boolean inside = Math.abs(x) <= 8 && Math.abs(z) <= 8;
         var p = center.method_10069(x, 200, z);
         check(claim.contains(p) == inside, "square claim boundary ignores height " + x + "," + z);
         check(claim.intersects(new class_3341(p)) == inside, "point box agrees with claim containment");
      }
      check(claim.intersects(new class_3341(-8, -64, -8, 10, -32, 10)), "corner-touch intersects independent of height");
      check(!claim.intersects(new class_3341(-7, 0, -8, 10, 100, 10)), "one-block gap does not intersect");
      check(claim.intersects(new class_3341(-40, 0, -40, 40, 1, 40)), "box enclosing claim intersects");
      check(new LandClaim(OWNER, OTHER, center, Integer.MIN_VALUE).x3rgd0i6cz19eu() == 1, "negative radius clamps to one");
      check(new LandClaim(OWNER, OTHER, center, Integer.MAX_VALUE).x3rgd0i6cz19eu() == 32, "oversized radius clamps to 32");
      check(LandClaim.fromNbt(claim.toNbt()).equals(claim), "claim NBT preserves owner, colony, center and radius");
      var legacy = claim.toNbt();
      legacy.method_10551("radius");
      check(LandClaim.fromNbt(legacy).x3rgd0i6cz19eu() == 8, "missing radius uses legacy default");
      legacy.method_10582("owner", "invalid-uuid");
      boolean rejected = false;
      try { LandClaim.fromNbt(legacy); } catch (IllegalArgumentException expected) { rejected = true; }
      check(rejected, "invalid claim UUID is rejected");

      var data = new ColonyWorldData();
      data.addLand(claim);
      var p = center.method_10069(8, -20, 8);
      data.recordPlacement(p, OWNER, 500);
      check(data.isPlayerBlock(p) && data.blockOwner(p).orElseThrow().equals(OWNER), "placement retains ownership");
      check(!data.foreignBlocks(claim), "own blocks permit claim");
      data.recordPlacement(p, OTHER, 500);
      check(data.foreignBlocks(claim), "foreign block on boundary prevents claim");
      check(!data.recentAttackerBlock(p, OTHER, 499), "future placement is not a recent attack");
      check(data.recentAttackerBlock(p, OTHER, 500), "reservation starts at placement tick");
      check(data.recentAttackerBlock(p, OTHER, 1700), "reservation includes tick 1200");
      check(!data.recentAttackerBlock(p, OTHER, 1701), "reservation expires at tick 1201");
      check(!data.recentAttackerBlock(p, OWNER, 500), "reservation is owner-specific");
      check(data.playerBuildNear(center, 12), "near query crosses negative chunk boundary");
      check(!data.playerBuildNear(center, 11), "near query uses circular distance");
      var loaded = worldRoundTrip(data);
      check(loaded.land().equals(data.land()), "world NBT preserves land claims");
      check(loaded.blockOwner(p).orElseThrow().equals(OTHER) && loaded.isPlayerBlock(p), "world NBT preserves tracked blocks and owners");
      check(loaded.playerBuildNear(center, 12), "world NBT rebuilds chunk index");
      check(!loaded.recentAttackerBlock(p, OTHER, 500), "transient reservations do not survive reload");
      data.recordPlacement(center, OWNER, 1701);
      check(!data.recentAttackerBlock(p, OTHER, 1701), "another placement prunes expired reservations");
      data.forgetPlacement(p);
      data.forgetPlacement(p);
      check(!data.isPlayerBlock(p) && data.blockOwner(p).isEmpty() && !data.foreignBlocks(claim), "forget placement is idempotent");
      data.forgetPlacement(center);
      check(!data.playerBuildNear(center, 12), "forget removes the final chunk index entry");

      // Exercise public warning persistence with a legacy-shaped input, without server entities.
      var warnings = new class_2487();
      var entries = new class_2499();
      var warning = new class_2487();
      warning.method_10582("player", OWNER.toString());
      warning.method_10582("colony", OTHER.toString());
      warning.method_10544("pos", center.method_10063());
      warning.method_10569("continued", 100);
      warning.method_10544("delivered", (long)Integer.MAX_VALUE + 99);
      entries.add(warning);
      entries.add(legacy);
      warnings.method_10566("land_warnings", entries);
      var target = new ColonyWorldData();
      LandClaimManager.x1vdpt6b5sksjj(warnings, target);
      var saved = new class_2487();
      LandClaimManager.x2xqw0zvyjpw3b(saved, target);
      var list = saved.method_68569("land_warnings");
      check(list.size() == 1, "malformed warning skipped while valid warning loads");
      check(list.method_68582(0).method_68083("continued", -1) == 3, "warning continuation count bounded");
      check(list.method_68582(0).method_68080("delivered", -1) == (long)Integer.MAX_VALUE + 99, "warning tick preserves long precision");
      var savedAgain = new class_2487();
      var reloaded = new ColonyWorldData();
      LandClaimManager.x1vdpt6b5sksjj(saved, reloaded);
      LandClaimManager.x2xqw0zvyjpw3b(savedAgain, reloaded);
      check(saved.equals(savedAgain), "warning NBT is stable across reload");
   }

   private static ColonyWorldData worldRoundTrip(ColonyWorldData data) {
      // The production codec uses these private NBT adapters; no duplicate serializer in tests.
      try {
         Method write = ColonyWorldData.class.getDeclaredMethod("x17s9f8fvs5qrt");
         Method read = ColonyWorldData.class.getDeclaredMethod("x1vfd7ma58aaai", class_2487.class);
         write.setAccessible(true);
         read.setAccessible(true);
         return (ColonyWorldData)read.invoke(null, write.invoke(data));
      } catch (ReflectiveOperationException error) { throw new AssertionError("world NBT adapters", error); }
   }

   private static int count(class_1263 inventory, class_1792 item) {
      int result = 0;
      for (int i = 0; i < inventory.method_5439(); i++)
         if (inventory.method_5438(i).method_31574(item)) result += inventory.method_5438(i).method_7947();
      return result;
   }

   private static void inventoryAndConversion() {
      var inventory = new class_1277(4);
      inventory.method_5447(0, new class_1799(class_1802.field_8229, 20));
      inventory.method_5447(1, new class_1799(class_1802.field_8229, 12));
      inventory.method_5447(2, new class_1799(class_1802.field_8550, 7));
      var before = InventoryMaterials.x2l7fon36lgwu2(inventory);
      check(before.size() == 2 && before.get(new ItemStackKey(new class_1799(class_1802.field_8229))) == 32,
         "inventory aggregates equivalent stacks and ignores empty slots");
      check(InventoryMaterials.x2qxayij05nw0s(before, before) == 0, "unchanged inventory has zero loss");
      check(InventoryMaterials.x2qxayij05nw0s(before, Map.of()) == 39, "empty inventory loses all material");
      var id = class_2960.method_60654("test:material");
      check(InventoryMaterials.xhz2g56hwio6r(Map.of(id, 9), Map.of(id, 3)) == 6, "resource delta counts removed units");
      check(InventoryMaterials.xhz2g56hwio6r(Map.of(id, 3), Map.of(id, 9)) == 0, "gains do not create negative losses");
      check(InventoryMaterials.xhz2g56hwio6r(Map.of(), Map.of(id, 9)) == 0, "new resource type is not a loss");
      for (int reserve : new int[]{-1, 0, 1, 2, 4, 64}) {
         for (int slots : new int[]{1, 2, 4}) {
            var inv = new class_1277(slots);
            for (int i = 0; i < slots; i++) inv.method_5447(i, new class_1799(class_1802.field_8103));
            int converted = OfflineDevelopment.x3tihjtlnbfyrs(inv, reserve);
            int source = count(inv, class_1802.field_8103), output = count(inv, class_1802.field_8550);
            int expected = Math.max(0, slots - Math.max(0, reserve));
            check(converted == expected, "conversion respects reserve and capacity: " + reserve + "/" + slots);
            check(source + output == slots && output == converted, "conversion conserves material");
            check(OfflineDevelopment.x3tihjtlnbfyrs(inv, reserve) == 0, "conversion is stable on repeat");
         }
      }
      var full = new class_1277(3);
      full.method_5447(0, new class_1799(class_1802.field_8103));
      full.method_5447(1, new class_1799(class_1802.field_8103));
      full.method_5447(2, new class_1799(class_1802.field_8550, 15));
      check(OfflineDevelopment.x3tihjtlnbfyrs(full, 1) == 1, "conversion fills only available output space");
      check(count(full, class_1802.field_8103) == 1 && count(full, class_1802.field_8550) == 16, "full inventory conversion loses no items");
      check(OfflineDevelopment.x3tihjtlnbfyrs(new class_1277(0), 0) == 0, "empty inventory creates no resources");
   }

   private static void economyAndRecipes() {
      check(VillageEconomy.x742kp3et4jfu(20, 0, 0, 0) == 32, "scarcity raises price with no divide by zero");
      check(VillageEconomy.x742kp3et4jfu(20, 80, 0, 10) == 16, "surplus reduces price");
      check(VillageEconomy.x8v45wzyc7cx3(20, 0, 100) == 30, "missing stock increases purchase price");
      check(VillageEconomy.x8v45wzyc7cx3(20, 100, 100) == 15, "sufficient stock lowers purchase price");
      check(VillageEconomy.xpl8mvusb83z9(64, 100, 20) == 20, "trade batch uses a quarter of surplus");
      check(VillageEconomy.xpl8mvusb83z9(64, 0, 20) == 1, "trade batch has positive minimum");
      for (int base : new int[]{-1, 0, 1, 20, 64, 1000}) {
         for (int stock : new int[]{0, 1, 20, 1000}) {
            int sell = VillageEconomy.x742kp3et4jfu(base, stock, 20, 10);
            int buy = VillageEconomy.x8v45wzyc7cx3(base, stock, 20);
            int batch = VillageEconomy.xpl8mvusb83z9(base, stock, 20);
            check(sell >= 1 && sell <= 64 && buy >= 1 && buy <= 64 && batch >= 1 && batch <= 64, "trade values stay in stack limits");
         }
      }
      var c = colony();
      check(VillageEconomy.x2vr85xzgtiacc(c, 24, true) == 19, "empty village land price");
      check(VillageEconomy.x286mwub0gxrf2(c) == 1, "empty village has minimum trade multiplier");
      c.x2rezm3a3rz7iu(1000, 10000, 10000, 0, 0, 0);
      check(VillageEconomy.x286mwub0gxrf2(c) == 4, "wealth multiplier is capped");
      var outputs = new HashSet<class_1792>();
      for (var recipe : ColonyCrafting.x3ck5rmycrqg51) {
         check(outputs.add(recipe.x3dtjj1ybya6jb()), "crafting outputs are unique");
         var matches = ColonyCrafting.xeccokn9mws72(recipe.x3dtjj1ybya6jb());
         check(matches.test(new class_1799(recipe.x3dtjj1ybya6jb(), 2)), "recipe predicate accepts output independent of count");
         check(!matches.test(class_1799.field_8037), "recipe predicate rejects empty stack");
         check(!matches.test(new class_1799(class_1802.field_8407)), "recipe predicate rejects unrelated item");
      }
      var named = new class_1799(ColonyCrafting.x3ck5rmycrqg51.getFirst().x3dtjj1ybya6jb());
      named.method_57379(class_9334.field_49631, class_2561.method_43470("custom"));
      check(!ColonyCrafting.xeccokn9mws72(named.method_7909()).test(named), "crafting stock predicate distinguishes custom components");
   }

   private static void deeds() {
      for (int level : new int[]{Integer.MIN_VALUE, 0, 1, 2, 3, 4, Integer.MAX_VALUE}) {
         var deed = PropertyDeeds.xhgw7talm1sp8(level);
         int expected = Math.clamp(level, 1, 3);
         check(PropertyDeeds.x3kj2ljyx6tv0(deed) == expected, "deed creation clamps level");
         check(!PropertyDeeds.x11oga8h5115u7(deed), "new deed is unused");
         var nbt = deed.method_58694(class_9334.field_49628).method_57461();
         nbt.method_10556("smart_colonies_property_used", true);
         deed.method_57379(class_9334.field_49628, class_9279.method_57456(nbt));
         check(PropertyDeeds.x3kj2ljyx6tv0(deed) == 0 && PropertyDeeds.xau920lx57wg3(deed) == expected, "used deed keeps level but cannot be reused");
      }
      var deed = PropertyDeeds.xhgw7talm1sp8(2);
      var nbt = deed.method_58694(class_9334.field_49628).method_57461();
      nbt.method_10582("smart_colonies_property_job", OWNER.toString());
      deed.method_57379(class_9334.field_49628, class_9279.method_57456(nbt));
      check(PropertyDeeds.x3kj2ljyx6tv0(deed) == 0, "job-bound legacy deed cannot be reused without used flag");
      check(PropertyDeeds.x3kj2ljyx6tv0(new class_1799(class_1802.field_8407)) == 0, "plain paper is not a property deed");
      check(!LandClaimManager.x3ew3lrc2q3oxu(deed), "property deed is not a land deed");
      nbt.method_10556("smart_colonies_land", true);
      deed.method_57379(class_9334.field_49628, class_9279.method_57456(nbt));
      check(LandClaimManager.x3ew3lrc2q3oxu(deed), "land marker identifies a paper deed");
      var wrongItem = new class_1799(class_1802.field_8550);
      wrongItem.method_57379(class_9334.field_49628, class_9279.method_57456(nbt));
      check(!LandClaimManager.x3ew3lrc2q3oxu(wrongItem) && PropertyDeeds.xau920lx57wg3(wrongItem) == 0, "deed metadata on wrong item is rejected");
   }

   private static void wallPersistence() {
      var c = colony();
      var state = c.x2f7z1gnnht5ab();
      var p = new class_2338(-17, 65, 31);
      var block = class_2246.field_10445.method_9564();
      state.x1q0gb1h69ywu7.add(new WallBlockPlan(p, block, false));
      state.x1q0gb1h69ywu7.add(new WallBlockPlan(p.method_10084(), block, true));
      state.x16wpjsjmva3od.put(p, block);
      state.x37dzr2zekycme = (long)Integer.MAX_VALUE + 1;
      var loaded = Colony.x3bvvkrzdde479(c.xufzblerzrsnk()).x2f7z1gnnht5ab();
      check(loaded.x1q0gb1h69ywu7.equals(state.x1q0gb1h69ywu7), "wall plans preserve order, position, state and removal flag through colony NBT");
      check(loaded.x16wpjsjmva3od.equals(state.x16wpjsjmva3od), "managed wall blocks survive NBT");
      check(loaded.x37dzr2zekycme == state.x37dzr2zekycme, "wall plan tick retains long precision");
      var invalid = new class_2487();
      invalid.method_10569("radius", Integer.MAX_VALUE);
      invalid.method_10569("skipped", -1);
      loaded = new VillageDefenseState();
      loaded.x3e1m4qdtyt44s(invalid);
      check(loaded.x14zjd0to6v6yv == 512 && loaded.x1fvrorwquv0wt == 0, "wall NBT clamps invalid counters");
      check(loaded.x1q0gb1h69ywu7.isEmpty(), "legacy wall NBT defaults to empty queue");
   }
}
