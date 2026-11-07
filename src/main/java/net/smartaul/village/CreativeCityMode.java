package net.smartaul.village;

import net.smartaul.config.SmartAulConfig;

import java.util.List;
import java.util.UUID;
import net.minecraft.class_1923;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_2902.class_2903;

/** Creative-only city director; survival configuration is never rewritten. */
public final class CreativeCityMode {
   private static final int BUILD_BUDGET = 256;
   private static final int FARM_SIZE = 58;
   private CreativeCityMode() {}

   public static boolean contains(class_3218 world, Colony colony) {
      var p = ColonyWorldData.get(world).creativeCity;
      return p != null && p.active && p.colonies.contains(colony.id());
   }

   public static CommandResult start(class_3218 world, Colony root) {
      var data = ColonyWorldData.get(world);
      if (data.creativeCity != null && data.creativeCity.active) return new CommandResult(0, status(world));
      if (!WorldEditPolicy.x35ev8l9vc9uk7(world))
         return new CommandResult(0, "Разрешите изменения ландшафта и mobGriefing.");
      if (!SmartAulConfig.x2by5f9ehxvwk9().village.offline_development)
         return new CommandResult(0, "Для ускоренной стройки нужен village.offline_development=true.");
      if (root.x2a380ray4md5t().isPresent() || DeveloperMode.x2r8c2c8wlnbsc(world, root))
         return new CommandResult(0, "Сначала завершите экспедицию или dev max.");
      data.creativeCity = new CreativeCityPlan(root.id(), root.core());
      data.colonies().stream().filter(c -> c.parentColonyId().filter(root.id()::equals).isPresent())
         .forEach(c -> data.creativeCity.colonies.add(c.id()));
      DeveloperMode.xevco0r0wp03v(world);
      for (var id : data.creativeCity.colonies) data.byId(id).ifPresent(Colony::x2lr91dptxy1ww);
      data.method_80();
      ActionTrace.event(world, root, null, root.core(), "creative_start", "villages=" + data.creativeCity.colonies.size());
      return new CommandResult(1, "Креативная деревня: бесконечные ресурсы, непрерывная стройка и освоение карты. /smart_aul dev creative status | stop");
   }

   public static CommandResult stop(class_3218 world) {
      var data = ColonyWorldData.get(world);
      var p = data.creativeCity;
      if (p == null || !p.active) return new CommandResult(0, "Творческий город не строится.");
      finish(world, p, "Остановлено командой; постройки сохранены");
      return new CommandResult(1, status(world));
   }

   private static void finish(class_3218 world, CreativeCityPlan p, String message) {
      for (var id : p.colonies) ColonyWorldData.get(world).byId(id).ifPresent(c -> {
         c.x23n2tdykuxd7a(0);
         c.x20lu0dxozk5g(-c.x11xljsmcmocmw());
         c.xemejsnsuhts2(world.method_75260());
         for (var job : c.x1ja61emeln2hc()) job.xkv5b17xl7vd6(false);
      });
      ActionTrace.event(world, null, null, p.origin, "creative_stop", message + "; owned_chunks=" + p.ownedChunks.size());
      p.active = false;
      p.message = message;
      for (long key : p.ownedChunks) world.method_17988((int)key, (int)(key >> 32), false);
      p.ownedChunks.clear();
      ColonyWorldData.get(world).method_80();
   }

   public static String status(class_3218 world) {
      var p = ColonyWorldData.get(world).creativeCity;
      if (p == null) return "Творческий город ещё не запускался.";
      long houses = p.colonies.stream().map(ColonyWorldData.get(world)::byId)
         .flatMap(java.util.Optional::stream).mapToLong(SettlementSupport::completedHouses).sum();
      String phase = p.message;
      if (p.active && !p.mapComplete) {
         var current = p.activeColony < p.colonies.size()
            ? ColonyWorldData.get(world).byId(p.colonies.get(p.activeColony)).orElse(null) : null;
         if (current == null) phase = "Поиск новой деревни: участок " + p.frontier + ", попытка " + (p.siteAttempt + 1) + "/26";
         else {
            int side = chunkRadius(current) * 2 + 1;
            var job = current.x1ja61emeln2hc().stream()
               .filter(j -> j.x32oz43hmeynhc() != ConstructionStatus.COMPLETE && j.x32oz43hmeynhc() != ConstructionStatus.BLOCKED)
               .findFirst().orElse(null);
            phase = "Деревня @ " + current.core().method_23854() + "; "
               + (p.chunkCursor < side * side ? "загрузка чанков " + p.chunkCursor + "/" + side * side
                  : job != null ? "строительство " + job.xmu75rcopbjnh() + "/" + job.xxjwqetbgzzle()
                  : "поиск площадки: " + Math.max(0, current.x1eughlg5bm9gx()) + " кандидатов; проверено шаблонов " + p.failedTemplates);
         }
      }
      return "Креативная деревня: " + (p.active ? "строится" : "остановлена")
         + "; время " + p.elapsed / 1200 + ":" + String.format(java.util.Locale.ROOT, "%02d", p.elapsed / 20 % 60)
         + "; деревень " + p.colonies.size()
         + "; зданий " + houses + "; освоено деревень " + p.activeColony
         + (p.active ? "; ресурсы бесконечные; " : "; обычное снабжение; ") + phase;
   }

   private static int chunkRadius(Colony c) {
      int radius = Math.min(96, Math.min(c.radius(), SmartAulConfig.x2by5f9ehxvwk9().village.expansion_radius_max));
      return (radius + 15) / 16 + 1;
   }

   // One new chunk per tick. Existing forced chunks belong to their original owner.
   private static boolean load(class_3218 world, CreativeCityPlan p, int x, int z) {
      long key = class_1923.method_8331(x, z);
      if (!world.method_17984().contains(key)) {
         if (p.ownedChunks.size() >= CreativeCityPlan.CHUNK_LIMIT) {
            p.message = "Достигнут лимит загруженных чанков";
            return false;
         }
         p.ownedChunks.add(key);
         ColonyWorldData.get(world).method_80();
         world.method_17988(x, z, true);
         ActionTrace.event(world, null, null, null, "chunk_load", "x=" + x + "; z=" + z);
      }
      world.method_8497(x, z);
      return true;
   }

   public static void tick(class_3218 world) {
      var data = ColonyWorldData.get(world);
      var p = data.creativeCity;
      if (p == null || !p.active) return;
      if (!world.method_54719().method_54751()) return;
      if (!WorldEditPolicy.x35ev8l9vc9uk7(world) || !SmartAulConfig.x2by5f9ehxvwk9().village.offline_development) {
         p.message = "Пауза: изменения мира или offline_development выключены";
         return;
      }
      DeveloperMode.xevco0r0wp03v(world);
      if (p.searchOrderVersion != 1) {
         for (var id : p.colonies) data.byId(id).ifPresent(Colony::x2lr91dptxy1ww);
         p.failedTemplates = 0;
         p.searchOrderVersion = 1;
         data.method_80();
      }
      if (p.rebuildWindow) { release(world, p); p.farmCursor = 0; p.rebuildWindow = false; }
      if (p.elapsed < Long.MAX_VALUE) p.elapsed++;
      if (world.method_75260() % 100 == 0) ActionTrace.event(world, null, null, p.origin, "creative_heartbeat", status(world));
      if (world.method_75260() % 20 == 0 && !p.colonies.isEmpty()) {
         int supplyIndex = (int)Math.floorMod(world.method_75260() / 20, (long)p.colonies.size());
         data.byId(p.colonies.get(supplyIndex)).filter(v -> supplyIndex != p.activeColony && world.method_37118(v.core()))
            .ifPresent(v -> DeveloperMode.x2b94htgx8j8wb(world, v));
      }
      if (p.activeColony >= p.colonies.size()) {
         if (!p.mapComplete && world.method_75260() % 20 == 0) foundDistrict(world, p);
         return;
      }
      var c = data.byId(p.colonies.get(p.activeColony)).orElse(null);
      if (c == null) { advance(world, p); return; }
      // Keep one settlement loaded, including its full building search radius.
      int chunks = chunkRadius(c), side = chunks * 2 + 1;
      if (p.chunkCursor < side * side) {
         for (int i = 0; i < 16 && p.chunkCursor < side * side; i++) {
            int local = p.chunkCursor;
            int x = (c.core().method_10263() >> 4) + local % side - chunks;
            int z = (c.core().method_10260() >> 4) + local / side - chunks;
            boolean ready = world.method_14178().method_12123(x, z);
            if (!load(world, p, x, z)) break;
            p.chunkCursor++;
            if (!ready) break;
         }
         return;
      }
      prepare(world, p, c);
      if (world.method_75260() % 20 == 0) data.method_80();
      if (p.activeColony >= p.colonies.size() || !p.colonies.get(p.activeColony).equals(c.id())) return;
      ConstructionManager.x12vivbc3rlygx(world, c, BUILD_BUDGET);
      if (p.farmCursor < FARM_SIZE) farmStep(world, p);
   }

   private static void release(class_3218 world, CreativeCityPlan p) {
      for (long key : p.ownedChunks) world.method_17988((int)key, (int)(key >> 32), false);
      p.ownedChunks.clear();
      p.chunkCursor = 0;
   }

   private static void advance(class_3218 world, CreativeCityPlan p) {
      release(world, p);
      ActionTrace.event(world, null, null, null, "creative_advance", "finished_index=" + p.activeColony);
      p.activeColony++;
      p.failedTemplates = 0;
      p.farmCursor = 0;
      ColonyWorldData.get(world).method_80();
   }

   private static void foundDistrict(class_3218 world, CreativeCityPlan p) {
      // Skip unsuitable sites and keep moving outwards instead of stalling a whole world.
      int spacing = Math.max(256, 192 + SmartAulConfig.x2by5f9ehxvwk9().village.settlement_border_gap + 32);
      var cell = CreativeCityPlan.cell(p.frontier);
      var border = world.method_8621();
      if (CreativeCityPlan.exhausted(p.frontier, spacing, p.origin.method_10263(), p.origin.method_10260(),
         border.method_11976(), border.method_11963(), border.method_11958(), border.method_11977())) {
         p.mapComplete = true;
         p.message = "Все доступные участки карты проверены; ресурсы остаются бесконечными";
         ColonyWorldData.get(world).method_80(); return;
      }
      long x = p.origin.method_10263() + cell[0] * spacing;
      long z = p.origin.method_10260() + cell[1] * spacing;
      if (p.siteAttempt > 0) {
         x += ((p.siteAttempt - 1) % 5 - 2) * 8;
         z += ((p.siteAttempt - 1) / 5 - 2) * 8;
      }
      // Do not generate chunks outside the current world border.
      if (Math.abs(x) > 29999900 || Math.abs(z) > 29999900
         || !border.method_11952(new class_2338((int)x, p.origin.method_10264(), (int)z))) {
         skipSite(world, p); p.message = "Поиск участка внутри границы мира";
         ColonyWorldData.get(world).method_80(); return;
      }
      if (!load(world, p, (int)x >> 4, (int)z >> 4)) return;
      var pos = surface(world, (int)x, (int)z);
      if (!SettlementSupport.available(world, pos, p.root) || !VillagerShelter.x9bnd6e2jczhd(world, pos)
         || !WorldEditPolicy.x13f7be5vlapn7(world, pos)
         || WorldEditPolicy.x3o1lagqjim5tz(world, pos.method_10074())) {
         skipSite(world, p);
         p.message = "Пропущен непригодный участок @ " + pos.method_23854();
         ColonyWorldData.get(world).method_80(); return;
      }
      var colony = new Colony(UUID.randomUUID(), pos, 96, Long.MIN_VALUE);
      var storage = ColonyManager.x2l4eb2ibsf118(world, colony);
      if (storage.isPresent() && WorldEditPolicy.x1bq0f8n53gtob(world, pos, class_2246.field_16332.method_9564(), 3)) {
         if (ColonyManager.xt55z8grtuvpq(world, colony, storage.get(), HousePurpose.GENERAL)) {
            colony.parentColonyId(p.root);
            colony.xvuz8u6ue7m7c();
            ColonyWorldData.get(world).add(colony);
            p.colonies.add(colony.id());
            ActionTrace.event(world, colony, null, pos, "village_founded", "frontier=" + p.frontier);
            p.frontier++;
            p.siteAttempt = 0;
            p.message = "Основана деревня " + p.colonies.size() + " @ " + pos.method_23854();
         } else WorldEditScope.x2aebumvi646n9(() -> world.method_8652(pos, class_2246.field_10124.method_9564(), 3));
      }
      if (p.activeColony >= p.colonies.size()) skipSite(world, p);
      else release(world, p);
      ColonyWorldData.get(world).method_80();
   }

   private static void skipSite(class_3218 world, CreativeCityPlan p) {
      release(world, p);
      if (++p.siteAttempt >= 26) { p.siteAttempt = 0; p.frontier++; }
   }

   private static void prepare(class_3218 world, CreativeCityPlan p, Colony c) {
      if (world.method_75260() % 100 == 0 && !p.farms.containsKey(c.id())) chooseFarm(world, p, c);
      if (world.method_75260() % 20 == 0) {
         DeveloperMode.x2b94htgx8j8wb(world, c);
         DeveloperMode.xcqb3adsjgu8q(world, c, CreativeCityPlan.populationTarget(SettlementSupport.completedHouses(c)));
      }
      c.x23n2tdykuxd7a(4);
      c.x20lu0dxozk5g(12000);
      c.xemejsnsuhts2(world.method_75260() + 12000L);
      ConstructionManager.x1yz1dkagfdrrb(world, c);
      if (c.x3e4ycl8cmmmgc()) return;
      int completed = (int)SettlementSupport.completedHouses(c);
      var homes = HouseTemplateRegistry.xrbmqlm78i747("plains");
      var services = HouseTemplateRegistry.x3tmz8uu7gcem2("plains").stream().filter(n -> !homes.contains(n)).toList();
      var names = new java.util.ArrayList<String>(homes);
      names.addAll(services);
      if (names.isEmpty()) { p.message = "Нет шаблонов зданий"; return; }
      int preferred = completed % 3 == 2 && !services.isEmpty() ? homes.size() + (completed / 3) % services.size() : completed % Math.max(1, homes.size());
      String name = names.get((preferred + p.failedTemplates) % names.size());
      var template = TownHall.exists(c) ? HouseTemplateRegistry.x4jnab85o8lop("plains", name) : TownHall.TEMPLATE;
      var structure = world.method_14183().method_15094(template);
      if (structure.isEmpty()) { p.message = "Не найден шаблон " + name; return; }
      var size = structure.get().method_15160();
      if (!c.x2zi111bgbtgzq()) c.x31uoom8ikakob();
      var pos = BuildingSiteSearch.xzvjreewiw7bn(world, c, size.method_10263(), size.method_10260(), size.method_10264(), true);
      if (pos.isPresent()) {
         var job = new ConstructionJob(UUID.randomUUID(), template, pos.get(), size.method_10263(), size.method_10264(), size.method_10260());
         job.xkv5b17xl7vd6(true);
         c.x31ve0dsfeaxo4(job);
         ActionTrace.event(world, c, null, pos.get(), "construction_queued", "template=" + template);
         ColonyWorldData.get(world).method_80();
         p.failedTemplates = 0;
      } else if (!c.x2zi111bgbtgzq() && ++p.failedTemplates >= names.size()) {
         p.message = "Деревня застроена; расширяемся дальше";
         advance(world, p);
      }
   }

   private static class_2338 surface(class_3218 w, int x, int z) {
      return new class_2338(x, w.method_8624(class_2903.field_13203, x, z), z);
   }

   private static boolean natural(class_3218 w, class_2338 ground) {
      var s = w.method_8320(ground);
      return WorldEditPolicy.xroinv4hk6l7h(s) || s.method_27852(class_2246.field_10194)
         || s.method_27852(class_2246.field_10362);
   }

   private static void chooseFarm(class_3218 w, CreativeCityPlan p, Colony c) {
      for (int attempt = 0; attempt < 16; attempt++) {
         int seed = Math.floorMod(p.elapsed / 20 + attempt * 17, 64);
         int x = c.core().method_10263() + (seed % 8 - 4) * 10;
         int z = c.core().method_10260() + (seed / 8 - 4) * 10;
         if (Math.abs(x - c.core().method_10263()) < 10 && Math.abs(z - c.core().method_10260()) < 10) continue;
         var origin = surface(w, x, z);
         if (reserved(w, c, x - 3, z - 3, 7, 7)) continue;
         boolean safe = true;
         for (int dx = -3; dx <= 3 && safe; dx++) for (int dz = -3; dz <= 3 && safe; dz++) {
            var ground = new class_2338(x + dx, origin.method_10264() - 1, z + dz);
            safe = natural(w, ground) && w.method_8320(ground.method_10084()).method_26215()
               && WorldEditPolicy.x13f7be5vlapn7(w, ground)
               && c.x1ja61emeln2hc().stream().noneMatch(j -> j.x24kbqv6g4kkrg().method_14662(ground));
         }
         if (safe) { p.farms.put(c.id(), origin); return; }
      }
   }

   public static boolean reserved(class_3218 w, Colony c, int x, int z, int width, int depth) {
      if (!contains(w, c)) return false;
      var p = ColonyWorldData.get(w).creativeCity;
      var farm = p.farms.get(c.id());
      if (farm != null && x <= farm.method_10263() + 5 && x + width >= farm.method_10263() - 5
         && z <= farm.method_10260() + 5 && z + depth >= farm.method_10260() - 5) return true;
      // Reserve a local approach to the bell, without long roads crossing future settlements.
      int cx = c.core().method_10263(), cz = c.core().method_10260();
      return x <= cx + 2 && x + width >= cx - 2 && z <= cz + 8 && z + depth >= cz - 8;
   }

   private static void farmStep(class_3218 w, CreativeCityPlan p) {
      int district = p.activeColony, cell = p.farmCursor % FARM_SIZE;
      var c = ColonyWorldData.get(w).byId(p.colonies.get(district)).orElseThrow();
      var farm = p.farms.get(c.id());
      if (farm == null) { p.message = "Поиск ровной площадки фермы " + district; return; }
      int x = farm.method_10263(), z = farm.method_10260();
      class_2338 pos;
      class_2680 state;
      boolean safe;
      if (cell < 50) {
         int square = cell % 25;
         var top = new class_2338(x + square % 5 - 2, farm.method_10264(), z + square / 5 - 2);
         pos = cell < 25 ? top.method_10074() : top;
         state = square == 12 ? (cell < 25 ? class_2246.field_10382 : class_2246.field_10124).method_9564()
            : (cell < 25 ? class_2246.field_10362 : class_2246.field_10293).method_9564();
         safe = cell < 25 ? natural(w, pos) && w.method_8320(top).method_26215() : w.method_8320(pos).method_26215();
      } else {
         int corner = (cell - 50) / 2;
         pos = new class_2338(x + (corner % 2 == 0 ? -3 : 3), farm.method_10264() + (cell % 2), z + (corner < 2 ? -3 : 3));
         state = (cell % 2 == 0 ? class_2246.field_10620 : class_2246.field_16541).method_9564();
         safe = w.method_8320(pos).method_26215() && (cell % 2 != 0 || natural(w, pos.method_10074()));
      }
      if (w.method_8320(pos).equals(state)) { p.farmCursor++; return; }
      if (!safe || c.x1ja61emeln2hc().stream().anyMatch(j -> j.x24kbqv6g4kkrg().method_14662(pos))
         || !WorldEditPolicy.x13f7be5vlapn7(w, pos) || !WorldEditPolicy.x1bq0f8n53gtob(w, pos, state, 3)) p.blockedFarms++;
      p.farmCursor++;
   }
}
