package net.smartaul.village;

import java.util.*;
import net.minecraft.*;
import net.smartaul.config.*;

final class LeisureRegression {
   private static void require(boolean value, String message) {
      if (!value) throw new AssertionError(message);
   }
   static void run() {
      var config = new com.google.gson.Gson().fromJson("{}", VillageConfig.class);
      require(config.tavern_min_population == 10, "old JSON defaults to ten residents");
      config.tavern_min_population = -20; DevelopmentPolicy.validate(config);
      require(config.tavern_min_population == 2, "tavern threshold lower bound");
      config.max_population_per_bell = 6; config.tavern_min_population = Integer.MAX_VALUE;
      DevelopmentPolicy.validate(config);
      require(config.tavern_min_population == 6, "threshold respects small population cap");
      config = new VillageConfig();
      var c = new Colony(UUID.randomUUID(), new class_2338(0,64,0), 96, 0);
      for (int population : new int[]{0, 9, 10, 20}) {
         c.x1c0m5jj5x6wpm(population, 24, 0);
         require(VillageLeisure.needsTavern(c, config) == (population >= 10), "population threshold " + population);
      }
      var legacy = Colony.x3bvvkrzdde479(c.xufzblerzrsnk());
      require(VillageLeisure.needsTavern(legacy, config), "old NBT needs no migration or new leisure field");
      var job = new ConstructionJob(UUID.randomUUID(), VillageLeisure.TEMPLATE, new class_2338(10,63,10),9,6,13);
      c.x31ve0dsfeaxo4(job);
      require(!VillageLeisure.needsTavern(c, config) && VillageLeisure.completed(c).isEmpty(), "queued tavern blocks duplicates but not usable yet");
      require(!VillageLeisure.needsTavern(Colony.x3bvvkrzdde479(c.xufzblerzrsnk()), config), "queued tavern identity survives reload");
      job.x2hk920e3qof5e(500);
      var reloaded = Colony.x3bvvkrzdde479(c.xufzblerzrsnk());
      require(!VillageLeisure.needsTavern(reloaded, config), "blocked tavern survives reload without duplication");
      require(reloaded.x1ja61emeln2hc().getFirst().x32oz43hmeynhc() == ConstructionStatus.BLOCKED, "blocked status round trip");
      job.x3lcv8vebl7uhh();
      reloaded = Colony.x3bvvkrzdde479(c.xufzblerzrsnk());
      require(VillageLeisure.completed(reloaded).isPresent(), "completed tavern survives reload");
      require(VillageLeisure.summary(c).contains("Таверна: готова"), "progress includes completed tavern");
      for (long time : new long[]{-1,0,1000,8999,12000,13000,23999,24000})
         require(!VillageLeisure.restTime(time), "no leisure during work or sleep " + time);
      for (long time : new long[]{9000,10000,11999,33000})
         require(VillageLeisure.restTime(time), "meeting schedule " + time);
      require(HousePurpose.values()[4] == HousePurpose.ANIMAL, "existing purpose ordinals unchanged");
      var cycle = new HousePurpose[]{HousePurpose.FOOD,HousePurpose.WOOD,HousePurpose.MINING,HousePurpose.ANIMAL,HousePurpose.GENERAL};
      for (int i = 0; i < 20; i++) require(HousePurpose.forHouse(i) == cycle[i % 5], "house distribution unchanged");
      template(job);
      // These are the actual intermediary task hooks; descriptive helper names alone do not override Minecraft.
      try {
         LeisureTask.class.getDeclaredMethod("method_18919", class_3218.class, class_1646.class);
         for (String hook : List.of("method_18920", "method_18924", "method_18926", "method_18927"))
            LeisureTask.class.getDeclaredMethod(hook, class_3218.class, class_1646.class, long.class);
      } catch (ReflectiveOperationException ex) { throw new AssertionError(ex); }
      System.out.println("Tavern/leisure regressions: PASS");
   }

   private static void template(ConstructionJob job) {
      try (var stream = LeisureRegression.class.getResourceAsStream("/data/smart_colonies/structure/tavern.nbt")) {
         require(stream != null, "tavern resource packaged");
         var nbt = class_2507.method_10629(stream, class_2505.method_53899(2_000_000));
         var structure = new class_3499(); structure.method_15183(class_7923.field_41175, nbt);
         require(structure.method_15160().equals(new class_2338(9,6,13)), "Minecraft reads tavern dimensions");
         var palette = nbt.method_68569("palette"); var blocks = nbt.method_68569("blocks");
         var cells = new HashMap<class_2338, String>();
         for (int i=0; i<blocks.size(); i++) {
            var b = blocks.method_68582(i); var p = b.method_68569("pos");
            cells.put(new class_2338(p.method_10600(0).orElseThrow(),p.method_10600(1).orElseThrow(),p.method_10600(2).orElseThrow()),
               palette.method_68582(b.method_68083("state",-1)).method_68564("Name",""));
         }
         require("minecraft:oak_planks".equals(cells.get(new class_2338(4,1,2))), "bar counter exists");
         require("minecraft:oak_pressure_plate".equals(cells.get(new class_2338(2,2,5))), "table exists");
         require("minecraft:oak_stairs".equals(cells.get(new class_2338(1,1,10))), "courtyard benches exist");
         require("minecraft:cobblestone".equals(cells.get(new class_2338(4,1,10))), "decorative well exists");
         require("minecraft:wall_torch".equals(cells.get(new class_2338(2,3,0))), "lighting exists");
         for (int x : new int[]{4,5}) for (int y=1;y<=3;y++)
            require("minecraft:air".equals(cells.get(new class_2338(x,y,8))), "two-wide accessible entrance");
         var points = new HashSet<class_2338>();
         for (int i=0;i<4;i++) {
            var point = VillageLeisure.meetingPoint(job,i);
            require(points.add(point), "distinct visitor positions");
            var origin = job.x3ij6gescvakv2();
            var local = new class_2338(point.method_10263()-origin.method_10263(),1,point.method_10260()-origin.method_10260());
            require("minecraft:air".equals(cells.get(local)) && "minecraft:air".equals(cells.get(local.method_10084())), "visitor has headroom");
            require("minecraft:cobblestone".equals(cells.get(local.method_10074())), "visitor has solid floor");
         }
      } catch (java.io.IOException ex) { throw new AssertionError(ex); }
   }
}
