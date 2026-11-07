package net.smartaul.village;

import net.smartaul.config.SmartAulConfig;
import net.smartaul.config.VillageConfig;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.Map.Entry;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1646;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_3218;
import net.minecraft.class_3489;
import net.minecraft.class_3730;
import net.minecraft.class_3854;
import net.minecraft.class_7893;
import net.minecraft.class_2902.class_2903;

public final class DeveloperMode {
   private static final Map<class_3218, Map<UUID, DevelopmentSession>> x2jxf155fb1smi = new IdentityHashMap<>();
   private static final int x16fg2ntpvc4ue = 12000;

   private DeveloperMode() {
   }

   public static boolean x2d8qtehqomuj6(class_3218 var0) {
      return x2jxf155fb1smi.containsKey(var0);
   }

   public static void xevco0r0wp03v(class_3218 var0) {
      x2jxf155fb1smi.computeIfAbsent(var0, var0x -> new HashMap<>());
   }

   public static void x3evocl9yxshic(class_3218 var0) {
      var var1 = x2jxf155fb1smi.remove(var0);
      if (var1 != null) {
         var1.forEach((var1x, var2) -> ColonyWorldData.get(var0).byId(var1x).ifPresent(var2x -> xilv8zdrjafo6(var0, var2x, var2)));
      }
   }

   public static void x1161stz4yb35x() {
      for (class_3218 var1 : new ArrayList<>(x2jxf155fb1smi.keySet())) {
         x3evocl9yxshic(var1);
      }
   }

   public static boolean x2r8c2c8wlnbsc(class_3218 var0, Colony var1) {
      return CreativeCityMode.contains(var0, var1) || x2jxf155fb1smi.containsKey(var0) && x2jxf155fb1smi.get(var0).containsKey(var1.id());
   }

   public static List<class_1646> x2ymjp22rympo2(class_3218 var0, Colony var1) {
      return var0.method_18023(class_1299.field_6077, ColonyManager.x150ccxomrlozr(var0, var1), class_1297::method_5805)
         .stream()
         .filter(var2 -> ColonyManager.x3psqezd5jem54(var0, var2.method_24515()).map(var1xx -> var1xx.id().equals(var1.id())).orElse(false))
         .sorted(Comparator.comparing(class_1297::method_5667))
         .toList();
   }

   public static CommandResult xcqb3adsjgu8q(class_3218 var0, Colony var1, int var2) {
      if (!x2d8qtehqomuj6(var0)) {
         return xfqwmmra1djk();
      } else if (var2 >= 2 && var2 <= (CreativeCityMode.contains(var0, var1) ? 64 : Math.min(64, SmartAulConfig.x2by5f9ehxvwk9().village.max_population_per_bell))) {
         var var3 = new ArrayList<>(x2ymjp22rympo2(var0, var1));
         int var4 = Math.max(var1.population(), var3.size());
         int var5 = 0;

         for (int var6 = 2; var6 <= 16 && var4 + var5 < var2; var6++) {
            for (int var7 = -var6; var7 <= var6 && var4 + var5 < var2; var7++) {
               for (int var8 = -var6; var8 <= var6 && var4 + var5 < var2; var8++) {
                  if (Math.abs(var7) == var6 || Math.abs(var8) == var6) {
                     int var9 = var1.core().method_10263() + var7;
                     int var10 = var1.core().method_10260() + var8;
                     if (var0.method_14178().method_12123(var9 >> 4, var10 >> 4)) {
                        class_2338 var11 = new class_2338(var9, var0.method_8624(class_2903.field_13203, var9, var10), var10);
                        if (Math.abs(var11.method_10264() - var1.core().method_10264()) <= 4
                           && var0.method_8621().method_11952(var11)
                           && var0.method_8621().method_11952(var11.method_10084())
                           && VillagerShelter.x9bnd6e2jczhd(var0, var11)
                           && !ProtectedLand.x3gqyq7yie9jxr(var0, var11)
                           && !WorldEditPolicy.x3o1lagqjim5tz(var0, var11.method_10074())
                           && !var1.x1ja61emeln2hc().stream().anyMatch(var1x -> var1x.x24kbqv6g4kkrg().method_14662(var11))
                           && var0.method_8333(
                                 null, new class_238(var11).method_1012(0.0, 1.0, 0.0).method_1014(0.05), var0x -> var0x.method_5805() && !var0x.method_7325()
                              )
                              .isEmpty()) {
                           class_1646 var12 = (class_1646)class_1299.field_6077.method_5883(var0, class_3730.field_16462);
                           if (var12 != null) {
                              var12.method_5814(var11.method_10263() + 0.5, var11.method_10264(), var11.method_10260() + 0.5);
                              var12.method_7195(var12.method_7231().method_66687(var0.method_30349(), class_3854.method_16930(var0.method_23753(var1.core()))));
                              var12.method_5971();
                              if (var0.method_8649(var12)) {
                                 var3.add(var12);
                                 var5++;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         var1.x1c0m5jj5x6wpm(var4 + var5, var1.beds(), var0.method_75260());
         ColonyWorldData.get(var0).method_80();
         return new CommandResult(
            var5,
            "Добавлено жителей: "
               + var5
               + "; население по учёту="
               + (var4 + var5)
               + "/"
               + var2
               + "; загружено="
               + var3.size()
               + ". Профессии распределит обычная экономика; существующие жители сохранены."
         );
      } else {
         return new CommandResult(
            0, "Допустимое население: 2.." + Math.min(64, SmartAulConfig.x2by5f9ehxvwk9().village.max_population_per_bell) + ". Жители не удаляются."
         );
      }
   }

   public static CommandResult x2b94htgx8j8wb(class_3218 var0, Colony var1) {
      if (!x2d8qtehqomuj6(var0)) {
         return xfqwmmra1djk();
      } else {
         if (ColonyManager.x11dwzrdveisv7(var0, var1) == 0 && var1.x2ad3s8aentmo4().isEmpty()) {
            var var2 = ColonyManager.x2l4eb2ibsf118(var0, var1);
            if (var2.isEmpty() || !ColonyManager.xt55z8grtuvpq(var0, var1, (class_2338)var2.get(), HousePurpose.GENERAL)) {
               return new CommandResult(0, "Нет безопасного места для общественного склада. Личные сундуки не используются.");
            }

            var1.x1z3izo4s1trbu();
         }

         String var14 = class_3854.method_16930(var0.method_23753(var1.core())).method_29177().method_12832();
         class_1792 var3 = var14.equals("savanna")
            ? class_1802.field_8820
            : (!var14.equals("taiga") && !var14.equals("snow") ? class_1802.field_8583 : class_1802.field_8684);
         var var4 = new LinkedHashMap<>();
         var4.put(class_1802.field_8229, 256);
         var4.put(var3, 512);
         var4.put(class_1802.field_20412, 768);
         var4.put(class_1802.field_8620, 192);
         var4.put(class_1802.field_8713, 128);
         var4.put(class_1802.field_8477, 16);
         var4.put(class_1802.field_8317, 64);
         var4.put(class_1802.field_8861, 64);
         var4.put(class_1802.field_8179, 32);
         var4.put(class_1802.field_8567, 32);
         var4.put(class_1802.field_17518, 32);
         var4.put(class_1802.field_46249, 32);
         var4.put(class_1802.field_8687, 128);
         var4.put(class_1802.field_19044, 64);
         var4.put(class_1802.field_8280, 128);
         var4.put(class_1802.field_8858, 256);
         var4.put(class_1802.field_8696, 128);
         var4.put(class_1802.field_8810, 64);
         var4.put(class_1802.field_8600, 64);
         var4.put(class_1802.field_8276, 32);
         var4.put(class_1802.field_8107, 64);
         var4.put(class_1802.field_8550, 4);
         var4.put(class_1802.field_8868, 2);
         var4.put(
            var14.equals("savanna")
               ? class_1802.field_17539
               : (!var14.equals("taiga") && !var14.equals("snow") ? class_1802.field_17535 : class_1802.field_17536),
            32
         );
         int var5 = 0;
         int var6 = 0;

         for (Entry var8 : var4.entrySet()) {
            class_1792 var9 = (class_1792)var8.getKey();
            int var10 = Math.max(0, (Integer)var8.getValue() - ColonyManager.x1mlne8tl8k5ag(var0, var1, var1x -> var1x.method_31574(var9)));

            while (var10 > 0) {
               int var11 = Math.min(var10, var9.method_7882());
               class_1799 var12 = new class_1799(var9, var11);
               ColonyManager.x2cl1crde2rnxy(var0, var1, var12);
               int var13 = var11 - var12.method_7947();
               var5 += var13;
               var10 -= var13;
               if (var13 < var11) {
                  var6 += var10;
                  break;
               }
            }
         }

         ColonyManager.x2u0vsam1tcmhq(var0, var1);
         ColonyWorldData.get(var0).method_80();
         return new CommandResult(
            var5, "Съёмочные ресурсы добавлены в общие склады: " + var5 + "; не поместилось: " + var6 + ". Это выдача командой, не добыча жителей."
         );
      }
   }

   public static CommandResult x2zuchidlrmdh(class_3218 var0, Colony var1, ColonyRole var2, int var3, class_2338 var4) {
      if (!x2d8qtehqomuj6(var0)) {
         return xfqwmmra1djk();
      } else if (var2 != ColonyRole.ELDER && var2 != ColonyRole.FOUNDER && var3 >= 1 && var3 <= 16) {
         int var5 = 0;

         for (class_1646 var7 : x2ymjp22rympo2(var0, var1)
            .stream()
            .sorted(
               Comparator.<class_1646>comparingInt(var1x -> var1.roleOf(var1x.method_5667()) == ColonyRole.CITIZEN ? 0 : 1)
                  .thenComparingDouble(var1x -> var1x.method_24515().method_10262(var4))
            )
            .toList()) {
            if (var5 >= var3) {
               break;
            }

            ColonyRole var8 = var1.roleOf(var7.method_5667());
            if (!var7.method_6109()
               && !var7.method_18009()
               && !var1.x3hurh64xwtw51(var7.method_5667())
               && var8 != ColonyRole.ELDER
               && var8 != var2) {
               ColonyManager.x3drrj6t9qvanr(var0, var7, var2);
               var1.xvamv2rzn6h0g(var7.method_5667(), var0.method_75260());
               ColonyManager.x21g1rujs9r1h6(var0, var7, var2);
               var5++;
            }
         }

         var1.xhwx3flu2btqb(x2ymjp22rympo2(var0, var1));
         ColonyWorldData.get(var0).method_80();
         return new CommandResult(var5, "Постановочно назначено " + var2.serializedName() + ": " + var5 + ". Далее действует обычный кулдаун и спрос.");
      } else {
         return new CommandResult(0, "Специальные роли не назначаются этой командой; количество 1..16.");
      }
   }

   public static CommandResult xl7doh5h33e42(class_3218 var0, Colony var1, boolean var2) {
      if (!x2d8qtehqomuj6(var0)) {
         return xfqwmmra1djk();
      } else if (x2r8c2c8wlnbsc(var0, var1)) {
         return new CommandResult(0, "Сначала завершите или остановите max: он пополняет запасы.");
      } else {
         int var3 = ColonyManager.x1asz375yez9sw(
            var0, var1, var1x -> var2 ? ColonyManager.xhahxgpa08yc9(var1x) > 0 : var1x.method_31573(class_3489.field_15539), 0
         );
         ColonyManager.x2u0vsam1tcmhq(var0, var1);
         ColonyWorldData.get(var0).method_80();
         return new CommandResult(
            var3,
            "Из ОБЩИХ складов убрано "
               + (var2 ? "еды" : "брёвен")
               + ": "
               + var3
               + ". Личные вещи не затронуты. Профессии не подменены: ждите обычную переоценку спроса и кулдаун."
         );
      }
   }

   public static CommandResult x2sc3yk5od1kev(class_3218 var0, Colony var1, ColonyRole var2, class_2338 var3) {
      if (!x2d8qtehqomuj6(var0)) {
         return xfqwmmra1djk();
      } else {
         class_1646 var4 = x2ymjp22rympo2(var0, var1)
            .stream()
            .filter(
               var2x -> !var2x.method_6109()
                  && !var2x.method_18009()
                  && !var2x.method_6113()
                  && !var1.x3hurh64xwtw51(var2x.method_5667())
                  && var1.roleOf(var2x.method_5667()) != ColonyRole.ELDER
                  && var2x.method_24515().method_10262(var3) <= 144.0
            )
            .min(Comparator.comparingDouble(var1x -> var1x.method_24515().method_10262(var3)))
            .orElse(null);
         if (var4 == null) {
            return new CommandResult(0, "Подойдите ближе: нужен свободный взрослый житель в пределах 12 блоков, не старейшина и не переселенец.");
         } else {
            ColonyRole var5 = var1.roleOf(var4.method_5667());
            if (var5 == var2) {
               return new CommandResult(0, "У ближайшего жителя уже профессия " + var2.serializedName() + ". Подойдите к другому или выберите другую роль.");
            } else {
               for (class_7893 var7 : List.copyOf(var4.method_18868().method_27074())) {
                  var7.method_18925(var0, var4, var0.method_75260());
               }

               VillagerShelter.x2fxoqmbs0henb(var4);
               VillagerNavigation.x3oupbjeq14mj8(var4);
               ColonyManager.x3drrj6t9qvanr(var0, var4, var2);
               var1.xvamv2rzn6h0g(var4.method_5667(), var0.method_75260());
               ColonyManager.x21g1rujs9r1h6(var0, var4, var2);
               var1.xhwx3flu2btqb(x2ymjp22rympo2(var0, var1));
               ColonyWorldData.get(var0).method_80();
               return new CommandResult(
                  1,
                  "Постановочная смена: "
                     + var5.serializedName()
                     + " → "
                     + var2.serializedName()
                     + "; житель "
                     + var4.method_5667()
                     + "; координаты "
                     + var4.method_24515().method_23854()
                     + ". Скин и инструменты меняются обычной логикой."
               );
            }
         }
      }
   }

   public static CommandResult x32g8vo9hf8rm3(class_3218 var0, Colony var1) {
      if (!x2d8qtehqomuj6(var0)) {
         return xfqwmmra1djk();
      } else {
         var var2 = x2ymjp22rympo2(var0, var1);
         var var3 = new HashMap<>();
         var2.forEach(var2x -> var3.put(var2x.method_5667(), var1.roleOf(var2x.method_5667())));
         ColonyManager.xsd7almzt4rct(var0, var1, var2);
         var var4 = new ArrayList<String>();

         for (class_1646 var6 : var2) {
            if (var3.get(var6.method_5667()) != var1.roleOf(var6.method_5667())) {
               var4.add(
                  ((ColonyRole)var3.get(var6.method_5667())).serializedName()
                     + " → "
                     + var1.roleOf(var6.method_5667()).serializedName()
                     + " @ "
                     + var6.method_24515().method_23854()
               );
            }
         }

         return new CommandResult(
            var4.size(),
            "Переоценка реального спроса: "
               + (var4.isEmpty() ? "изменений нет (проверьте избыток профессий, кулдаун 120 с, груз, торговлю и активную стройку)" : String.join("; ", var4))
               + ". "
               + LivestockManager.x32tk2361rdt82(var1)
         );
      }
   }

   public static CommandResult x241eq3b06s3l0(class_3218 var0, Colony var1, class_2338 var2) {
      if (!x2d8qtehqomuj6(var0)) {
         return xfqwmmra1djk();
      } else if (!x2r8c2c8wlnbsc(var0, var1) && !var1.x2a380ray4md5t().isPresent() && !RaidShelterTask.active(var0, var1)) {
         CommandResult var3 = x2b94htgx8j8wb(var0, var1);
         if (var1.x2ad3s8aentmo4().isEmpty()) {
            return var3;
         } else {
            if (ColonyManager.x11dwzrdveisv7(var0, var1) < 27) {
               var var4 = ColonyManager.x2l4eb2ibsf118(var0, var1);
               if (var4.isPresent()
                  && ColonyManager.x1mlne8tl8k5ag(var0, var1, var0x -> var0x.method_31573(class_3489.field_15539)) >= 4
                  && ColonyManager.xt55z8grtuvpq(var0, var1, (class_2338)var4.get(), HousePurpose.GENERAL)) {
                  ColonyManager.x22hjebj4vmztt(var0, var1, var0x -> var0x.method_31573(class_3489.field_15539), 4);
               }
            }

            xcqb3adsjgu8q(var0, var1, 24);
            x2zuchidlrmdh(var0, var1, ColonyRole.BUILDER, 2, var2);
            x2zuchidlrmdh(var0, var1, ColonyRole.FARMER, 4, var2);
            x2zuchidlrmdh(var0, var1, ColonyRole.LUMBERJACK, 2, var2);
            x2zuchidlrmdh(var0, var1, ColonyRole.MINER, 3, var2);
            return new CommandResult(
               1,
               "Постановочно подготовлены склад, население и работники; расчёт ещё НЕ запускался. Снимите деревню ДО, затем film develop 3, затем film develop status. "
                  + var3.x2fa4oag1sp0w9()
            );
         }
      } else {
         return new CommandResult(0, "Завершите max, экспедицию или нападение перед подготовкой сцены.");
      }
   }

   public static CommandResult x1uwgtgswk0hva(class_3218 var0, Colony var1, int var2) {
      if (!x2d8qtehqomuj6(var0)) {
         return xfqwmmra1djk();
      } else if (x2r8c2c8wlnbsc(var0, var1)) {
         return new CommandResult(0, "Развитие max уже выполняется.");
      } else {
         VillageConfig var3 = SmartAulConfig.x2by5f9ehxvwk9().village;
         if (WorldEditPolicy.x35ev8l9vc9uk7(var0) && var3.offline_development && var3.offline_max_days != 0) {
            if (x2ymjp22rympo2(var0, var1).size() < 2) {
               return new CommandResult(0, "Нужно хотя бы два жителя.");
            } else {
               int var4 = Math.min(var2, var3.offline_max_days);
               if (var4 < 1) {
                  return new CommandResult(0, "Допустимые дни: 1..30.");
               } else {
                  var1.xhwx3flu2btqb(x2ymjp22rympo2(var0, var1));
                  long var5 = var1.x1ja61emeln2hc()
                     .stream()
                     .filter(var0x -> var0x.x32oz43hmeynhc() == ConstructionStatus.COMPLETE && var0x.x2nfrt8qxdscso().isEmpty())
                     .count();
                  var1.x14t0iw6xpk0xx(var0.method_75260() - var4 * 24000L);
                  ConstructionManager.x3ij59k18pnfmd(var0, var1);
                  var1.xs28n9ortj0j6(0L);
                  ColonyWorldData.get(var0).method_80();
                  return new CommandResult(
                     var4,
                     "Смоделировано отсутствие "
                        + var4
                        + " игровых дней без перевода часов мира. До расчёта готовых новых общественных домов="
                        + var5
                        + ". "
                        + ConstructionManager.x1rq82fkez3pr7(var0, var1)
                        + ". Заработанные дома применяются ускоренно на безопасных загруженных участках; если труд=0, проверьте еду и строителей. Очередь сохраняется до выполнения. Это демонстрация обычной формулы, не добыча в реальном времени."
                  );
               }
            }
         } else {
            return new CommandResult(0, "Нужны разрешённые изменения ландшафта и offline_development с ненулевым лимитом дней.");
         }
      }
   }

   public static CommandResult x3hszkva73cyco(class_3218 var0, Colony var1) {
      if (!x2d8qtehqomuj6(var0)) {
         return xfqwmmra1djk();
      } else if (x2r8c2c8wlnbsc(var0, var1)) {
         return new CommandResult(0, "max уже выполняется; /smart_aul film status покажет состояние.");
      } else {
         SmartAulConfig var2 = SmartAulConfig.x2by5f9ehxvwk9();
         if (!WorldEditPolicy.x35ev8l9vc9uk7(var0) || !var2.roles.enable_auto_expansion || !var2.village.offline_development) {
            return new CommandResult(0, "Для max нужны изменения ландшафта, авторасширение и offline_development. Конфиг команда не меняет.");
         } else if (RaidShelterTask.active(var0, var1)) {
            return new CommandResult(0, "Во время нападения подготовка max не запускается.");
         } else if (!var1.x2a380ray4md5t().isPresent() && var1.x11xljsmcmocmw() <= 0 && var1.x2p9t1nvxo82zp() <= 0 && var1.x1i0d1gs2hqpsr().isEmpty()) {
            int var3 = Math.min(12, var2.village.max_constructed_houses);
            DevelopmentSession var4 = new DevelopmentSession(var0.method_75260() + 12000L, var3);
            x2jxf155fb1smi.get(var0).put(var1.id(), var4);
            var1.x38l97g8nvc9ui(true);
            x2b94htgx8j8wb(var0, var1);
            xcqb3adsjgu8q(var0, var1, Math.min(48, var2.village.max_population_per_bell));
            var1.xs28n9ortj0j6(0L);
            return new CommandResult(
               1,
               "Подготовка развитой деревни запущена: цель "
                  + var3
                  + " новых общественных домов и до 48 жителей. Это ускоренная постановка, не мгновенная замена деревни. Лимит 10 минут; нужны загруженные участки. Остановить: film stop."
            );
         } else {
            return new CommandResult(0, "Сначала завершите активную экспедицию или расчёт офлайн-развития.");
         }
      }
   }

   public static CommandResult x2dwx84ddtzhb0(class_3218 var0, Colony var1) {
      var var2 = x2jxf155fb1smi.get(var0);
      DevelopmentSession var3 = var2 == null ? null : (DevelopmentSession)var2.remove(var1.id());
      if (var3 == null) {
         return new CommandResult(0, "Ускоренная подготовка этой деревни не запущена.");
      } else {
         xilv8zdrjafo6(var0, var1, var3);
         return new CommandResult(1, "Ускорение остановлено. Построенные блоки, выданные ресурсы и новые жители остаются; это не откат мира.");
      }
   }

   private static void xilv8zdrjafo6(class_3218 var0, Colony var1, DevelopmentSession var2) {
      var2.x1bjciaf2920n9.forEach(var0x -> var0x.xkv5b17xl7vd6(false));
      var1.x38l97g8nvc9ui(false);
      var1.x23n2tdykuxd7a(0);
      var1.x20lu0dxozk5g(-var1.x11xljsmcmocmw());
      var1.xemejsnsuhts2(var0.method_75260());
      ColonyWorldData.get(var0).method_80();
   }

   public static void x2twncqk5nkey3(class_3218 var0) {
      var var1 = x2jxf155fb1smi.get(var0);
      if (var1 != null && !var1.isEmpty() && var0.method_75260() % 20L == 0L) {
         var var2 = var1.entrySet().iterator();

         while (var2.hasNext()) {
            Entry var3 = (Entry)var2.next();
            Colony var4 = ColonyWorldData.get(var0).byId((UUID)var3.getKey()).orElse(null);
            if (var4 == null) {
               var2.remove();
            } else {
               DevelopmentSession var5 = (DevelopmentSession)var3.getValue();
               long var6 = var4.x1ja61emeln2hc()
                  .stream()
                  .filter(var0x -> var0x.x32oz43hmeynhc() == ConstructionStatus.COMPLETE && var0x.x2nfrt8qxdscso().isEmpty())
                  .count();
               if (var6 < var5.xzklk0cnnmaka
                  && var0.method_75260() < var5.xvy74q5kam3tt
                  && WorldEditPolicy.x35ev8l9vc9uk7(var0)
                  && SmartAulConfig.x2by5f9ehxvwk9().village.offline_development
                  && SmartAulConfig.x2by5f9ehxvwk9().roles.enable_auto_expansion) {
                  if (var0.method_37118(var4.core()) && var0.method_14178().method_12123(var4.core().method_10263() >> 4, var4.core().method_10260() >> 4)) {
                     if (var0.method_75260() % 200L == 0L) {
                        x2b94htgx8j8wb(var0, var4);
                     }

                     var4.x23n2tdykuxd7a((int)Math.min(4L, var5.xzklk0cnnmaka - var6));
                     var4.x20lu0dxozk5g(12000 - var4.x11xljsmcmocmw());
                     var4.xemejsnsuhts2(var0.method_75260() + 12000L);

                     for (ConstructionJob var9 : var4.x1ja61emeln2hc()) {
                        if (var9.x2nfrt8qxdscso().isEmpty() && var9.x32oz43hmeynhc() != ConstructionStatus.COMPLETE) {
                           var9.xkv5b17xl7vd6(true);
                           var5.x1bjciaf2920n9.add(var9);
                        }
                     }

                     ConstructionManager.x1yz1dkagfdrrb(var0, var4);
                     ColonyWorldData.get(var0).method_80();
                  }
               } else {
                  xilv8zdrjafo6(var0, var4, var5);
                  var2.remove();
               }
            }
         }
      }
   }

   public static String x24jh62s2oq06y(class_3218 var0, Colony var1) {
      DevelopmentSession var2 = x2jxf155fb1smi.getOrDefault(var0, Map.of()).get(var1.id());
      long var3 = var1.x1ja61emeln2hc()
         .stream()
         .filter(var0x -> var0x.x32oz43hmeynhc() == ConstructionStatus.COMPLETE && var0x.x2nfrt8qxdscso().isEmpty())
         .count();
      return "Съёмочный режим="
         + x2d8qtehqomuj6(var0)
         + "; max="
         + (var2 == null ? "выключен" : var3 + "/" + var2.xzklk0cnnmaka + ", осталось " + Math.max(0L, var2.xvy74q5kam3tt - var0.method_75260()) / 20L + " с")
         + "; жителей="
         + x2ymjp22rympo2(var0, var1).size()
         + "; готовых новых общественных домов="
         + var3
         + "; "
         + ConstructionManager.x1rq82fkez3pr7(var0, var1)
         + "; экспедиция="
         + var1.x2a380ray4md5t().map(UUID::toString).orElse("нет")
         + "; сопровождающих="
         + var1.x2zftltl6vhwwx().size()
         + "; цель="
         + var1.x1x0agg3sdxjk8().<String>map(class_2382::method_23854).orElse("-");
   }

   private static CommandResult xfqwmmra1djk() {
      return new CommandResult(0, "Сначала /smart_aul film on. Только на копии мира: команды меняют население, ресурсы и постройки.");
   }
}
