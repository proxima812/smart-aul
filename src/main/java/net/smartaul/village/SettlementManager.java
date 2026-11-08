package net.smartaul.village;

import net.smartaul.SmartAulMod;
import net.smartaul.config.SmartAulConfig;
import net.smartaul.config.VillageConfig;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.function.Predicate;
import net.minecraft.class_1263;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1542;
import net.minecraft.class_1646;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1935;
import net.minecraft.class_2244;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2561;
import net.minecraft.class_2818;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3489;
import net.minecraft.class_3852;
import net.minecraft.class_4140;
import net.minecraft.class_7923;
import net.minecraft.class_2902.class_2903;

public final class SettlementManager {


   private SettlementManager() {
   }

   public static void x28wuwjwutxh4c(class_3218 var0, ColonyWorldData var1, long var2) {
      if (Math.floorMod(var2, 20) == 0 && WorldEditPolicy.x35ev8l9vc9uk7(var0) && SmartAulConfig.x2by5f9ehxvwk9().roles.enable_auto_expansion) {
         for (Colony var5 : new ArrayList<>(var1.colonies())) {
            if (var0.method_37118(var5.core()) || !var5.x2a380ray4md5t().isEmpty()) {
               if (var5.x2a380ray4md5t().isEmpty() && !DeveloperMode.x2d8qtehqomuj6(var0)) {
                  xutdbpd6qmqlv(var0, var1, var5, var2);
               } else {
                  x38mjeaqbg6qzt(var0, var1, var5, var2);
               }
            }
         }
      }
   }

   private static void xutdbpd6qmqlv(class_3218 var0, ColonyWorldData var1, Colony var2, long var3) {
      if ((!SmartAulConfig.x2by5f9ehxvwk9().village.automatic_settlement_requires_city || SettlementSupport.isCity(var2))
         && var3 >= var2.x15y708t7gvjof()
         && var2.population() >= SmartAulConfig.x2by5f9ehxvwk9().village.settlement_min_population
         && !RaidShelterTask.active(var0, var2)
         && var2.cachedFood() >= Math.max(128, var2.population() * 6 + 32)
         && var2.cachedLogs() >= 64
         && var2.cachedStone() >= 128) {
         var2.x3ki4hc4ldp1us(var3 + SmartAulConfig.x2by5f9ehxvwk9().village.settlement_retry_ticks);
         var1.method_80();
         var var5 = x6whfxtraodl3(var0, var2);
         if (var5.size() >= 5) {
            var var6 = xrxwk7xpq9bee(var0, var1, var2);
            if (var6.isEmpty()) {
               var2.x3ki4hc4ldp1us(var3 + SmartAulConfig.x2by5f9ehxvwk9().village.settlement_retry_ticks);
               var1.method_80();
            } else {
               x8e2cs7lq2652(var0, var1, var2, var5, (class_2338)var6.get(), var3);
            }
         }
      }
   }

   private static List<class_1646> x6whfxtraodl3(class_3218 var0, Colony var1) {
      var var2 = var0.method_18023(
         class_1299.field_6077,
         ColonyManager.x150ccxomrlozr(var0, var1),
         var2x -> var2x.method_5805()
            && !var2x.method_6109()
            && ColonyManager.x3psqezd5jem54(var0, var2x.method_24515()).map(var1xx -> var1xx.id().equals(var1.id())).orElse(false)
      );
      var var3 = new EnumMap<ColonyRole, Integer>(ColonyRole.class);

      for (class_1646 var5 : var2) {
         var3.merge(var1.roleOf(var5.method_5667()), 1, Integer::sum);
      }

      var var8 = new ArrayList<class_1646>();

      for (class_1646 var6 : var2.stream()
         .sorted(Comparator.comparingInt(var1x -> var1.roleOf(var1x.method_5667()) == ColonyRole.CITIZEN ? 0 : 1))
         .toList()) {
         ColonyRole var7 = var1.roleOf(var6.method_5667());
         if (!var6.method_18009()
            && !var6.method_6113()
            && var7 != ColonyRole.ELDER
            && var7 != ColonyRole.FOUNDER
            && (var7 == ColonyRole.CITIZEN || (Integer)var3.get(var7) > 1)) {
            var8.add(var6);
            var3.merge(var7, -1, Integer::sum);
            if (var8.size() == 5) {
               break;
            }
         }
      }

      return var8;
   }

   private static void x8e2cs7lq2652(class_3218 var0, ColonyWorldData var1, Colony var2, List<class_1646> var3, class_2338 var4, long var5) {
      var var7 = new LinkedHashMap<class_2960,Integer>();

      for (MaterialRequirement var9 : List.of(
         new MaterialRequirement(var0x -> var0x.method_31573(class_3489.field_15539), 32),
         new MaterialRequirement(var0x -> var0x.method_31573(class_3489.field_25808), 112),
         new MaterialRequirement(var0x -> ColonyManager.xhahxgpa08yc9(var0x) > 0, 32)
      )) {
         for (int var10 = 0; var10 < var9.x1w6emwv8ukb2(); var10++) {
            ColonyManager.x3lxwdirnn77qc(var0, var2, var9.x3r2nd9ja5plho())
               .ifPresent(var1x -> var7.merge(class_7923.field_41178.method_10221(var1x.method_7909()), var1x.method_7947(), Integer::sum));
         }
      }

      for (class_1646 var13 : var3) {
         VillagerShelter.x2fxoqmbs0henb(var13);
         VillagerNavigation.x3oupbjeq14mj8(var13);
         var13.method_18868().method_18875(class_4140.field_18445);
         var13.method_18868().method_18875(class_4140.field_18449);
      }

      class_1646 var12 = (class_1646)var3.getFirst();
      var12.method_5971();
      var2.x36xpmo21hmp9h(var12.method_5667(), ColonyRole.FOUNDER);
      var2.xveqe1h09qne3(var12.method_5667(), var4, var5);
      var2.x1i1560srleos1(var7);

      for (class_1646 var15 : var3.subList(1, 5)) {
         var15.method_5971();
         var2.x2zftltl6vhwwx().add(var15.method_5667());
         var2.x36xpmo21hmp9h(var15.method_5667(), ColonyRole.FOUNDER);
         ColonyManager.x21g1rujs9r1h6(var0, var15, ColonyRole.FOUNDER);
      }

      var12.method_7195(var12.method_7231().method_66688(var0.method_30349(), class_3852.field_17054));
      var12.method_5665(class_2561.method_43471("entity.smart_colonies.role.founder"));
      var12.method_5880(true);
      var12.method_5780("smart_colonies_role_name");
      var1.method_80();
      net.smartaul.SmartAulMod.LOGGER.info("Settlement expedition from {} to {}: {} settlers, {} cargo items", var2.id(), var4, var3.size(), var7.values().stream().mapToInt(Integer::intValue).sum());
   }

   static boolean xpl4idj7kx63g(Colony var0) {
      var var1 = var0.x3171qim2pcjd7().entrySet().iterator();

      while (var1.hasNext()) {
         Entry var2 = (Entry)var1.next();
         if (new class_1799((class_1935)class_7923.field_41178.method_63535((class_2960)var2.getKey())).method_31573(class_3489.field_15539)) {
            if ((Integer)var2.getValue() == 1) {
               var1.remove();
            } else {
               var2.setValue((Integer)var2.getValue() - 1);
            }

            return true;
         }
      }

      return false;
   }

   private static boolean xe2rg0vv8nbqj(Colony var0, int var1) {
      var var2 = var0.x3171qim2pcjd7();
      if (var2.entrySet().stream().filter(var0x -> x2b07n23fgkh41((class_2960)var0x.getKey())).mapToInt(Entry::getValue).sum() < var1) {
         return false;
      } else {
         var var3 = new ArrayList<>(var2.keySet());
         var3.sort(
            Comparator.comparingInt(
               var0x -> new class_1799((class_1935)class_7923.field_41178.method_63535(var0x)).method_31573(class_3489.field_25808) ? 0 : 1
            )
         );

         for (class_2960 var5 : var3) {
            if (x2b07n23fgkh41(var5)) {
               int var6 = Math.min(var1, (Integer)var2.get(var5));
               int var7 = (Integer)var2.get(var5) - var6;
               if (var7 == 0) {
                  var2.remove(var5);
               } else {
                  var2.put(var5, var7);
               }

               var1 -= var6;
               if (var1 == 0) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   private static boolean x2b07n23fgkh41(class_2960 var0) {
      class_1799 var1 = new class_1799((class_1935)class_7923.field_41178.method_63535(var0));
      return var1.method_31573(class_3489.field_15539) || var1.method_31573(class_3489.field_25808) || ColonyManager.xhahxgpa08yc9(var1) > 0;
   }

   private static void x1c3y5ewaifjlb(class_3218 var0, Colony var1, Colony var2, boolean var3) {
      var var4 = var1.x3171qim2pcjd7().entrySet().iterator();

      while (var4.hasNext()) {
         Entry var5 = (Entry)var4.next();
         class_1792 var6 = (class_1792)class_7923.field_41178.method_63535((class_2960)var5.getKey());
         int var7 = (Integer)var5.getValue();

         while (var7 > 0) {
            int var8 = Math.min(var6.method_7882(), var7);
            class_1799 var9 = new class_1799(var6, var8);
            ColonyManager.x2cl1crde2rnxy(var0, var2, var9);
            int var10 = var8 - var9.method_7947();
            var7 -= var10;
            if (!var9.method_7960()) {
               if (!var3) {
                  break;
               }

               var0.method_8649(new class_1542(var0, var2.core().method_10263() + 0.5, var2.core().method_10264() + 1, var2.core().method_10260() + 0.5, var9));
               var7 -= var9.method_7947();
            }
         }

         if (var7 == 0) {
            var4.remove();
         } else {
            var5.setValue(var7);
         }
      }

      ColonyWorldData.get(var0).method_80();
   }

   public static CommandResult requestSettlement(class_3218 world, Colony colony, class_2338 requested) {
      String reason = SettlementSupport.readiness(world, colony);
      if (!reason.isEmpty()) return new CommandResult(0, reason);
      class_2338 target = requested == null ? xrxwk7xpq9bee(world, ColonyWorldData.get(world), colony).orElse(null) : requested;
      VillageConfig config = SmartAulConfig.x2by5f9ehxvwk9().village;
      if (target == null) return new CommandResult(0, "Не найден загруженный участок. Загрузите окрестности или укажите settle X Y Z.");
      double distance = x1g8mc3ys7ith3(colony.core(), target);
      if (distance < (double)config.settlement_min_distance * config.settlement_min_distance
         || distance > (double)config.settlement_max_distance * config.settlement_max_distance
         || !SettlementSupport.available(world, target, null))
         return new CommandResult(0, "Нужен отдельный участок в " + config.settlement_min_distance + ".." + config.settlement_max_distance + " блоках, вне территорий и целей других поселений.");
      if (!world.method_8621().method_11952(target) || !world.method_37118(target)
         || !world.method_8320(target).method_26215() || !VillagerShelter.x9bnd6e2jczhd(world, target)
         || !x2d69km2ccnj1p(world, target)
         || ColonyManager.x2l4eb2ibsf118(world, new Colony(UUID.randomUUID(), target, 96, Long.MIN_VALUE)).isEmpty())
         return new CommandResult(0, "Нужна загруженная сухая площадка 17x17 с местом для склада, вдали от построек и участков игрока.");
      var settlers = x6whfxtraodl3(world, colony);
      if (settlers.size() < 5) return new CommandResult(0, "Нужны пять свободных взрослых сверх обязательных профессий; старейшина остаётся дома.");
      x8e2cs7lq2652(world, ColonyWorldData.get(world), colony, settlers, target.method_10062(), world.method_75260() + 200L);
      return new CommandResult(1, "Экспедиция из пяти жителей отправится через 10 секунд в " + target.method_23854() + ". Ресурсы взяты со склада; загружайте маршрут, следуя за группой.");
   }

   public static CommandResult x3seqvpn908svy(class_3218 var0, Colony var1, class_2338 var2) {
      if (!DeveloperMode.x2d8qtehqomuj6(var0)) {
         return new CommandResult(0, "Сначала /smart_aul film on.");
      } else if (!WorldEditPolicy.x35ev8l9vc9uk7(var0) || !SmartAulConfig.x2by5f9ehxvwk9().roles.enable_auto_expansion) {
         return new CommandResult(0, "Переселение или изменения ландшафта выключены в конфиге.");
      } else if (var1.x2a380ray4md5t().isPresent() || DeveloperMode.x2r8c2c8wlnbsc(var0, var1)) {
         return new CommandResult(0, "Экспедиция или подготовка max уже выполняется.");
      } else if (RaidShelterTask.active(var0, var1)) {
         return new CommandResult(0, "Сначала завершите нападение: переселенцы не отправляются из атакованной деревни.");
      } else {
         ColonyWorldData var3 = ColonyWorldData.get(var0);
         class_2338 var4 = var2 == null ? xrxwk7xpq9bee(var0, var3, var1).orElse(null) : var2.method_10062();
         if (var4 == null) {
            return new CommandResult(0, "Не найден загруженный участок в 192..320 блоках. Можно указать координаты: film expedition X Y Z.");
         } else if (!x2d69km2ccnj1p(var0, var4)) {
            return new CommandResult(0, "Участок слишком близко к постройкам/земле игрока либо окружающий ландшафт непригоден для поселения.");
         } else if (var0.method_31606(var4)
            || var0.method_31606(var4.method_10084())
            || !var0.method_8621().method_11952(var4)
            || !var0.method_14178().method_12123(var4.method_10263() >> 4, var4.method_10260() >> 4)) {
            return new CommandResult(0, "Цель должна быть внутри мира и в загруженном чанке.");
         } else if (!SettlementSupport.available(var0, var4, null)
            || x1g8mc3ys7ith3(var1.core(), var4) > Math.pow(SmartAulConfig.x2by5f9ehxvwk9().village.settlement_max_distance, 2)
            || !VillagerShelter.x9bnd6e2jczhd(var0, var4)
            || !var0.method_8320(var4).method_26215()
            || !WorldEditPolicy.x13f7be5vlapn7(var0, var4)
            || WorldEditPolicy.x3o1lagqjim5tz(var0, var4.method_10074())) {
            return new CommandResult(0, "Нужна свободная сухая площадка на земле, не ближе 160 блоков к колоколам и не дальше 640 блоков от родной деревни.");
         } else if (ColonyManager.x2l4eb2ibsf118(var0, new Colony(UUID.randomUUID(), var4, 96, Long.MIN_VALUE)).isEmpty()) {
            return new CommandResult(0, "У цели нет безопасного места для двойного склада. Осмотрите площадку и загрузите соседние чанки.");
         } else {
            var var5 = x6whfxtraodl3(var0, var1);
            if (var5.size() < 5) {
               return new CommandResult(
                  0, "Нужно пять свободных взрослых жителей сверх обязательных профессий; старейшина не уходит. Для подготовки: film population 24."
               );
            } else if (!ColonyManager.x22hjebj4vmztt(
               var0,
               var1,
               var0x -> var0x.method_31573(class_3489.field_15539) || var0x.method_31573(class_3489.field_25808) || ColonyManager.xhahxgpa08yc9(var0x) > 0,
               48
            )) {
               return new CommandResult(0, "Нужно 48 единиц еды, брёвен или камня в общественном складе для стартового капитала.");
            } else {
               x8e2cs7lq2652(var0, var3, var1, var5, var4, var0.method_75260() + 200L);

               for (int var6 = 0; var6 < 3; var6++) {
                  var1.xy40t7pepehnr(8);
               }

               var3.method_80();
               return new CommandResult(
                  1,
                  "Основатель и четверо сопровождающих собираются 10 секунд, затем идут в "
                     + var4.method_23854()
                     + ". Капитал подготовлен командой за 48 ресурсов; поход, основание и смена профессий — обычная логика. Следуйте за группой для загрузки маршрута."
               );
            }
         }
      }
   }

   private static double x1g8mc3ys7ith3(class_2338 var0, class_2338 var1) {
      double var2 = (double)var0.method_10263() - var1.method_10263();
      double var4 = (double)var0.method_10260() - var1.method_10260();
      return var2 * var2 + var4 * var4;
   }

   private static Optional<class_2338> xrxwk7xpq9bee(class_3218 var0, ColonyWorldData var1, Colony var2) {
      for (int var3 = 0; var3 < 16; var3++) {
         double var4 = var0.method_8409().method_43058() * Math.PI * 2.0;
         VillageConfig settings = SmartAulConfig.x2by5f9ehxvwk9().village;
         int var6 = settings.settlement_min_distance + var0.method_8409().method_43048(settings.settlement_max_distance - settings.settlement_min_distance + 1);
         int var7 = var2.core().method_10263() + (int)Math.round(Math.cos(var4) * var6);
         int var8 = var2.core().method_10260() + (int)Math.round(Math.sin(var4) * var6);
         if (var0.method_14178().method_12123(var7 >> 4, var8 >> 4)) {
            class_2338 var9 = new class_2338(var7, var0.method_8624(class_2903.field_13203, var7, var8), var8);
            if (SettlementSupport.available(var0, var9, null) && var0.method_8621().method_11952(var9)
               && var0.method_8316(var9).method_15769()
               && var0.method_8320(var9).method_26220(var0, var9).method_1110()
               && var0.method_8320(var9.method_10084()).method_26220(var0, var9.method_10084()).method_1110()
               && var0.method_8320(var9.method_10074()).method_26206(var0, var9.method_10074(), class_2350.field_11036)
               && WorldEditPolicy.x13f7be5vlapn7(var0, var9)
               && !WorldEditPolicy.x3o1lagqjim5tz(var0, var9.method_10074())) {
               boolean var10 = !SettlementSupport.available(var0, var9, null);
               if (!var10 && x2d69km2ccnj1p(var0, var9)) {
                  return Optional.of(var9.method_10062());
               }
            }
         }
      }

      return Optional.empty();
   }

   public static boolean x2d69km2ccnj1p(class_3218 var0, class_2338 var1) {
      ColonyWorldData var2 = ColonyWorldData.get(var0);
      if (var2.playerBuildNear(var1, 104)
         || var2.land()
            .stream()
            .anyMatch(
               var1x -> Math.abs((long)var1x.x2kystgxl68xrn().method_10263() - var1.method_10263()) <= 104 + var1x.x3rgd0i6cz19eu()
                  && Math.abs((long)var1x.x2kystgxl68xrn().method_10260() - var1.method_10260()) <= 104 + var1x.x3rgd0i6cz19eu()
            )) {
         return false;
      } else if (WorldEditPolicy.xz4flfirg81x1(var0, var1)) {
         return false;
      } else {
         for (int var3 = var1.method_10263() - 104 >> 4; var3 <= var1.method_10263() + 104 >> 4; var3++) {
            for (int var4 = var1.method_10260() - 104 >> 4; var4 <= var1.method_10260() + 104 >> 4; var4++) {
               if (var0.method_14178().method_12123(var3, var4)) {
                  class_2818 var5 = var0.method_14178().method_21730(var3, var4);
                  if (var5 != null) {
                     for (Entry var7 : var5.method_12214().entrySet()) {
                        class_2338 var8 = (class_2338)var7.getKey();
                        if (Math.abs(var8.method_10264() - var1.method_10264()) <= 32
                           && x1g8mc3ys7ith3(var8, var1) <= 10816.0
                           && (var7.getValue() instanceof class_1263 || var0.method_8320(var8).method_26204() instanceof class_2244)) {
                           return false;
                        }
                     }
                  }
               }
            }
         }

         int var10 = Integer.MAX_VALUE;
         int var11 = Integer.MIN_VALUE;

         for (int var12 = -8; var12 <= 8; var12++) {
            int var13 = -8;

            while (var13 <= 8) {
               class_2338 var14 = var1.method_10069(var12, 0, var13);
               if (var0.method_8621().method_11952(var14) && var0.method_14178().method_12123(var14.method_10263() >> 4, var14.method_10260() >> 4)) {
                  int var15 = var0.method_8624(class_2903.field_13203, var14.method_10263(), var14.method_10260()) - 1;
                  class_2338 var9 = new class_2338(var14.method_10263(), var15, var14.method_10260());
                  if (WorldEditPolicy.xroinv4hk6l7h(var0.method_8320(var9))
                     && var0.method_8316(var9.method_10084()).method_15769()
                     && var0.method_8320(var9.method_10084()).method_26220(var0, var9.method_10084()).method_1110()) {
                     if (WorldEditPolicy.xroinv4hk6l7h(var0.method_8320(var9.method_10074()))
                        && WorldEditPolicy.xroinv4hk6l7h(var0.method_8320(var9.method_10087(2)))) {
                        var10 = Math.min(var10, var15);
                        var11 = Math.max(var11, var15);
                        if (var11 - var10 > 3) {
                           return false;
                        }

                        var13++;
                        continue;
                     }

                     return false;
                  }

                  return false;
               }

               return false;
            }
         }

         return var11 - var10 <= 3 && Math.abs(var1.method_10264() - 1 - (var10 + var11) / 2) <= 3;
      }
   }

   public static void xw6o9aon4s40m(class_3218 var0, class_1646 var1) {
      for (Colony var3 : ColonyWorldData.get(var0).colonies()) {
         if (var3.x3hurh64xwtw51(var1.method_5667())) {
            if (var3.x2a380ray4md5t().filter(var1.method_5667()::equals).isPresent()) {
               var3.x1nxldt1cw1h5h();
            } else {
               var3.x2zftltl6vhwwx().remove(var1.method_5667());
            }

            var3.x36xpmo21hmp9h(var1.method_5667(), ColonyRole.CITIZEN);
            var3.x2qmn6nt922pp6();
            if (var3.x1x26mhvd93s02() && var3.x2zftltl6vhwwx().isEmpty()) {
               for (Entry var5 : var3.x3171qim2pcjd7().entrySet()) {
                  class_1792 var6 = (class_1792)class_7923.field_41178.method_63535((class_2960)var5.getKey());
                  int var7 = (Integer)var5.getValue();

                  while (var7 > 0) {
                     int var8 = Math.min(var7, var6.method_7882());
                     var0.method_8649(new class_1542(var0, var1.method_23317(), var1.method_23318(), var1.method_23321(), new class_1799(var6, var8)));
                     var7 -= var8;
                  }
               }

               var3.x3171qim2pcjd7().clear();
            }

            ColonyWorldData.get(var0).method_80();
         }
      }
   }

   private static void x38mjeaqbg6qzt(class_3218 var0, ColonyWorldData var1, Colony var2, long var3) {
      UUID var5 = var2.x2a380ray4md5t().orElse(null);
      class_2338 var6 = var2.x1x0agg3sdxjk8().orElse(null);
      if (var5 != null && var6 != null) {
         if (!var2.xfx95ux1xn73w() && var3 - var2.x1spmho2xy9muc() >= SmartAulConfig.x2by5f9ehxvwk9().village.settlement_timeout_ticks) {
            var2.x2qmn6nt922pp6();
            var1.method_80();
         }
         if (var2.x1x26mhvd93s02()) {
            UUID var7 = var2.x2zftltl6vhwwx()
               .stream()
               .filter(var1x -> var0.method_66347(var1x) instanceof class_1646 var2x && var2x.method_5805())
               .findFirst()
               .orElse(null);
            if (var7 == null) {
               if (var2.x2zftltl6vhwwx().isEmpty()) {
                  var2.x3ch5vvzyhhzwj(var3 + SmartAulConfig.x2by5f9ehxvwk9().village.settlement_cooldown_ticks);
                  var1.method_80();
               }

               return;
            }

            var2.x35wpd0lcwvgn6(var7);
            var5 = var7;
            var1.method_80();
         }

         class_1297 var23 = var0.method_66347(var5);
         if (var23 != null) {
            if (var23 instanceof class_1646 var8 && var8.method_5805()) {
               if (var8.field_6012 - var8.method_6117() <= 100 && VillageThreats.x1bj0phudo3a5m(var8.method_6065())
                  || var2.x2zftltl6vhwwx()
                     .stream()
                     .anyMatch(
                        var1x -> var0.method_66347(var1x) instanceof class_1646 var2x
                           && var2x.field_6012 - var2x.method_6117() <= 100
                           && VillageThreats.x1bj0phudo3a5m(var2x.method_6065())
                     )) {
                  var2.x2qmn6nt922pp6();
               }

               if (var2.x2zftltl6vhwwx().isEmpty()) {
                  var2.x2qmn6nt922pp6();
               }

               if (var2.xfx95ux1xn73w()) {
                  var var25 = new ArrayList<>(var2.x2zftltl6vhwwx());
                  var25.add(var5);
                  boolean var10 = true;

                  for (UUID var27 : var25) {
                     if (!(var0.method_66347(var27) instanceof class_1646 var28 && var28.method_5805())) {
                        var10 = false;
                     } else if (var28.method_24515().method_10262(var2.core()) > 144.0) {
                        var10 = false;
                        VillagerNavigation.x1wi30zgg7nf81(var0, var28, var2.core(), 0.9);
                        VillagerNavigation.x3hi5h4uz5pg79(var0, var28);
                     }
                  }

                  if (var10) {
                     x1c3y5ewaifjlb(var0, var2, var2, true);
                     var2.x3ch5vvzyhhzwj(var3 + SmartAulConfig.x2by5f9ehxvwk9().village.settlement_cooldown_ticks);
                     var1.method_80();
                  }
               } else if (var3 % 200L != 0L
                  || !var0.method_14178().method_12123(var6.method_10263() >> 4, var6.method_10260() >> 4)
                  || x2d69km2ccnj1p(var0, var6)
                     && SettlementSupport.available(var0, var6, var2.id())) {
                  long var24 = var3 - var2.x1spmho2xy9muc();
                  boolean var11 = true;
                  boolean var12 = false;
                  boolean var13 = var8.method_24515().method_10262(var6) <= 36.0;
                  int var14 = 0;

                  for (UUID var16 : new ArrayList<>(var2.x2zftltl6vhwwx())) {
                     int var17 = var14++ % 4;
                     class_1297 var18 = var0.method_66347(var16);
                     if (var18 == null) {
                        var11 = false;
                        var12 = true;
                     } else if (!(var18 instanceof class_1646 var19 && var19.method_5805())) {
                        var2.expeditionMemberLost(var16);
                        var11 = false;
                        var12 = true;
                        var1.method_80();
                     } else if (!var19.method_18009() && !var19.method_6113()) {
                        if (var19.method_5858(var8) > 16.0 || var13 && var19.method_24515().method_10262(var6) > 25.0) {
                           class_2338 var20 = var8.method_24515();
                           if (var13) {
                              int[][] var21 = new int[][]{{2, 0}, {0, 2}, {-2, 0}, {0, -2}};
                              class_2338 var22 = var6.method_10069(var21[var17][0], 0, var21[var17][1]);
                              var20 = VillagerShelter.x9bnd6e2jczhd(var0, var22) ? var22 : var6;
                           }

                           VillagerNavigation.x1wi30zgg7nf81(var0, var19, var20, 0.95);
                           VillagerNavigation.x3hi5h4uz5pg79(var0, var19);
                        }

                        if (var19.method_5858(var8) > 144.0) {
                           var12 = true;
                        }

                        if (var19.method_24515().method_10262(var6) > 36.0) {
                           var11 = false;
                        } else if (var2.x13ua0ftegttnr() >= 24 && var8.method_24515().method_10262(var6) <= 9.0 && !x14xuvjghdgxyq(var0, var19, var6)) {
                           var11 = false;
                        }
                     } else {
                        var11 = false;
                        var12 = true;
                     }
                  }

                  if (var24 < 0L) {
                     if (var8.method_24515().method_10262(var2.core()) > 9.0) {
                        VillagerNavigation.x1wi30zgg7nf81(var0, var8, var2.core(), 0.9);
                     }
                  } else {
                     if (var2.x37br4tln8q08z() < 3 && var24 >= (var2.x37br4tln8q08z() + 1L) * SmartAulConfig.x2by5f9ehxvwk9().village.settlement_capital_interval_ticks) {
                        boolean var30 = var2.xlklmlbkuwa0t()
                           ? xe2rg0vv8nbqj(var2, 16)
                           : ColonyManager.x22hjebj4vmztt(
                              var0,
                              var2,
                              var0x -> var0x.method_31573(class_3489.field_15539)
                                 || var0x.method_31573(class_3489.field_25808)
                                 || ColonyManager.xhahxgpa08yc9(var0x) > 0,
                              16
                           );
                        if (var30) {
                           var2.xy40t7pepehnr(8);
                           var1.method_80();
                        }
                     }

                     if (!var2.xfx95ux1xn73w() && !var8.method_6113() && !var8.method_18009()) {
                        if (var12) {
                           // Wait for stragglers without discarding the planned long route (B-002).
                           VillagerNavigation.pause(var0, var8);
                        } else if (var8.method_24515().method_10262(var6) <= 9.0) {
                           if (var2.x13ua0ftegttnr() >= 24 && var11 && x14xuvjghdgxyq(var0, var8, var6)) {
                              var8.method_5942().method_6340();
                              xn4bhm2rctqli(var0, var1, var2, var8, var6, var3);
                           }
                        } else {
                           if (!TreePlanting.x2gox5tp6i8qkj(var0, var2, var8, var6)) {
                              VillagerNavigation.x1wi30zgg7nf81(var0, var8, var6, 0.9);
                           }

                           VillagerNavigation.x3hi5h4uz5pg79(var0, var8);
                        }
                     }
                  }
               } else {
                  var var9 = xrxwk7xpq9bee(var0, var1, var2);
                  if (var9.isPresent()) {
                     var2.x324hyyfyx41wk((class_2338)var9.get());
                  } else {
                     var2.x2qmn6nt922pp6();
                  }

                  var1.method_80();
               }
            } else {
               var2.x1nxldt1cw1h5h();
               var1.method_80();
            }
         }
      }
   }

   private static boolean x14xuvjghdgxyq(class_3218 var0, class_1646 var1, class_2338 var2) {
      if (!var1.method_5829().method_994(new class_238(var2))) {
         return true;
      } else {
         class_2338 var3 = (class_2338)class_2338.method_25997(
               var2,
               3,
               1,
               var3x -> Math.max(Math.abs(var3x.method_10263() - var2.method_10263()), Math.abs(var3x.method_10260() - var2.method_10260())) >= 2
                  && VillagerShelter.x9bnd6e2jczhd(var0, var3x)
                  && WorldEditPolicy.x13f7be5vlapn7(var0, var3x)
                  && !WorldEditPolicy.x3o1lagqjim5tz(var0, var3x.method_10074())
                  && var0.method_8333(var1, new class_238(var3x).method_1012(0.0, 1.0, 0.0).method_1014(0.1), class_1297::method_5805).isEmpty()
            )
            .orElse(null);
         if (var3 != null) {
            VillagerShelter.xf8lnb134kd0j(var0, var1, var3, 0.9);
         }

         return false;
      }
   }

   private static void xn4bhm2rctqli(class_3218 var0, ColonyWorldData var1, Colony var2, class_1646 var3, class_2338 var4, long var5) {
      if (SettlementSupport.available(var0, var4, var2.id()) && x2d69km2ccnj1p(var0, var4) && !var2.x2zftltl6vhwwx().isEmpty()) {
         if (WorldEditPolicy.x13f7be5vlapn7(var0, var4)
            && !WorldEditPolicy.x3o1lagqjim5tz(var0, var4.method_10074())
            && var0.method_8621().method_11952(var4)
            && var0.method_8320(var4).method_26215()
            && var0.method_8320(var4.method_10074()).method_26206(var0, var4.method_10074(), class_2350.field_11036)) {
            Colony var7 = new Colony(UUID.randomUUID(), var4, 96, Long.MIN_VALUE);
            var var8 = ColonyManager.x2l4eb2ibsf118(var0, var7);
            if (!var8.isEmpty() && var0.method_8333(null, new class_238(var4), var0x -> var0x.method_5805() && var0x instanceof class_1309).isEmpty()) {
               if (WorldEditPolicy.x1bq0f8n53gtob(var0, var4, class_2246.field_16332.method_9564(), 3)) {
                  if (!ColonyManager.xt55z8grtuvpq(var0, var7, (class_2338)var8.get(), HousePurpose.GENERAL)) {
                     var0.method_8652(var4, class_2246.field_10124.method_9564(), 3);
                  } else {
                     var7.parentColonyId(var2.id());
                     var7.xvuz8u6ue7m7c(); // Five actual settlers; do not spawn the vanilla-village bootstrap residents.
                     var1.add(var7);
                     net.smartaul.SmartAulMod.LOGGER.info("Founded independent settlement {} at {} from {}", var7.id(), var4, var2.id());
                     if (var2.xlklmlbkuwa0t()) {
                        x1c3y5ewaifjlb(var0, var2, var7, true);
                     } else {
                        xqy84pu018mqc(var0, var2, var7, var0x -> var0x.method_31573(class_3489.field_15539), 32);
                        xqy84pu018mqc(var0, var2, var7, var0x -> var0x.method_31573(class_3489.field_25808), 64);
                        xqy84pu018mqc(var0, var2, var7, var0x -> ColonyManager.xhahxgpa08yc9(var0x) > 0, 32);
                     }

                     var7.x36xpmo21hmp9h(var3.method_5667(), ColonyRole.ELDER);
                     var7.x3c59xc9hrfvhz(var3.method_5667());
                     ColonyRole[] var9 = new ColonyRole[]{
                        ColonyRole.BUILDER, ColonyRole.FARMER, ColonyRole.LUMBERJACK, ColonyRole.MINER
                     };
                     int var10 = 0;

                     for (UUID var12 : var2.x2zftltl6vhwwx()) {
                        if (var0.method_66347(var12) instanceof class_1646 var13) {
                           ColonyRole var15 = var9[var10++ % var9.length];
                           var7.x36xpmo21hmp9h(var12, var15);
                           ColonyManager.x21g1rujs9r1h6(var0, var13, var15);
                        }
                     }

                     var3.method_7195(var3.method_7231().method_66688(var0.method_30349(), class_3852.field_17054));
                     var3.method_5665(class_2561.method_43471("entity.smart_colonies.role.elder"));
                     var3.method_5880(true);
                     var3.method_5780("smart_colonies_role_name");
                     var2.x36xpmo21hmp9h(var3.method_5667(), ColonyRole.CITIZEN);
                     var2.x3ch5vvzyhhzwj(var5 + SmartAulConfig.x2by5f9ehxvwk9().village.settlement_cooldown_ticks);
                     var1.method_80();
                  }
               }
            }
         }
      }
   }

   private static void xqy84pu018mqc(class_3218 var0, Colony var1, Colony var2, Predicate<class_1799> var3, int var4) {
      for (int var5 = 0; var5 < var4; var5++) {
         var var6 = ColonyManager.x3lxwdirnn77qc(var0, var1, var3);
         if (var6.isEmpty()) {
            return;
         }

         if (!ColonyManager.x2cl1crde2rnxy(var0, var2, (class_1799)var6.get())) {
            if (!ColonyManager.x2cl1crde2rnxy(var0, var1, (class_1799)var6.get())) {
               var0.method_8649(
                  new class_1542(
                     var0, var1.core().method_10263() + 0.5, var1.core().method_10264() + 1, var1.core().method_10260() + 0.5, (class_1799)var6.get()
                  )
               );
            }

            return;
         }
      }
   }
}
