package net.smartaul.command;

import net.smartaul.config.SmartAulConfig;
import net.smartaul.village.Colony;
import net.smartaul.village.ColonyManager;
import net.smartaul.village.ColonyMemories;
import net.smartaul.village.ColonyRole;
import net.smartaul.village.ColonyWorldData;
import net.smartaul.village.ConstructionJob;
import net.smartaul.village.ConstructionManager;
import net.smartaul.village.ConstructionStatus;
import net.smartaul.village.FarmerTask;
import net.smartaul.village.FishermanTask;
import net.smartaul.village.HouseBuilderTask;
import net.smartaul.village.LivestockManager;
import net.smartaul.village.MinerTask;
import net.smartaul.village.OfflineDevelopment;
import net.smartaul.village.RancherTask;
import net.smartaul.village.VillageDefenseState;
import net.smartaul.village.VillageEconomy;
import net.smartaul.village.VillageGolems;
import net.smartaul.village.VillageHouseDiscovery;
import net.smartaul.village.VillagerStuckRecovery;
import net.smartaul.village.WorkDiagnostics;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.class_1299;
import net.minecraft.class_1304;
import net.minecraft.class_1439;
import net.minecraft.class_1646;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2168;
import net.minecraft.class_2170;
import net.minecraft.class_2338;
import net.minecraft.class_2382;
import net.minecraft.class_2561;
import net.minecraft.class_4140;
import net.minecraft.class_7923;

public final class ColonyCommands {
   private ColonyCommands() {
   }

   public static void x32f59jmix8kb9() {
      CommandRegistrationCallback.EVENT.register((CommandRegistrationCallback)(var0, var1, var2) -> {
         x20dekagiglx0e(var0);
         DevelopmentCommands.registerLegacyAlias(var0);
      });
   }

   private static void x20dekagiglx0e(CommandDispatcher<class_2168> var0) {
      DevelopmentCommands.register(var0);
      var0.register(
         (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)class_2170.method_9247("smart_aul")
                     .then(StructurePreviewCommands.x2aso8h2ks6ywe()))
                  .then(DeveloperCommands.x2bhz7qcxu7mfc()))
               .then(
                  ((LiteralArgumentBuilder)class_2170.method_9247("repair").requires(class_2170.method_71774(class_2170.field_31839)))
                     .executes(
                        var0x -> {
                           class_2168 var1 = (class_2168)var0x.getSource();
                           var var2 = ColonyManager.x3psqezd5jem54(var1.method_9225(), class_2338.method_49638(var1.method_9222()));
                           if (var2.isEmpty()) {
                              var1.method_9226(() -> class_2561.method_43470("Рядом нет колонии."), false);
                              return 0;
                           } else {
                              int var3 = VillageHouseDiscovery.xc4tegq500os8(var1.method_9225(), (Colony)var2.get());
                              var1.method_9226(
                                 () -> class_2561.method_43470(
                                    "Проверены загруженные общественные дома. В очередь ремонта добавлено блоков: " + var3 + ". Нужны строители и материалы."
                                 ),
                                 false
                              );
                              return var3;
                           }
                        }
                     )
               ))
            .then(
               class_2170.method_9247("status")
                  .executes(
                     var0x -> {
                        class_2168 var1 = (class_2168)var0x.getSource();
                        class_2338 var2 = class_2338.method_49638(var1.method_9222());
                        var var3 = ColonyManager.x3psqezd5jem54(var1.method_9225(), var2);
                        if (var3.isEmpty()) {
                           var1.method_9226(() -> class_2561.method_43470("Smart Aul: рядом нет сгенерированной ванильной деревни."), false);
                           return 0;
                        } else {
                           Colony var4 = (Colony)var3.get();
                           long var5 = var4.x3klfyffbbfab2() == Long.MIN_VALUE ? -1L : var1.method_9225().method_75260() - var4.x3klfyffbbfab2();
                           var var7 = var1.method_9225()
                              .method_18023(class_1299.field_6077, ColonyManager.x150ccxomrlozr(var1.method_9225(), var4), var0xx -> var0xx.method_5805())
                              .stream()
                              .filter(
                                 var2x -> ColonyManager.x3psqezd5jem54(var1.method_9225(), var2x.method_24515())
                                    .map(var1xx -> var1xx.id().equals(var4.id()))
                                    .orElse(false)
                              )
                              .toList();
                           String var8 = Arrays.stream(ColonyRole.values())
                              .map(var2x -> var2x.serializedName() + ":" + var7.stream().filter(var2xx -> var4.roleOf(var2xx.method_5667()) == var2x).count())
                              .collect(Collectors.joining("/"));
                           String var9 = Arrays.stream(ConstructionStatus.values())
                              .map(
                                 var1x -> var1x.name().toLowerCase()
                                    + ":"
                                    + var4.x1ja61emeln2hc().stream().filter(var1xx -> var1xx.x32oz43hmeynhc() == var1x).count()
                              )
                              .collect(Collectors.joining("/"));
                           long var10 = var7.stream()
                              .filter(
                                 var0xx -> var0xx.method_18868()
                                    .method_27074()
                                    .stream()
                                    .anyMatch(var0xxx -> var0xxx.getClass().getName().startsWith("net.smartaul."))
                              )
                              .count();
                           String var12 = "Smart Aul: core="
                              + var4.core().method_23854()
                              + ", radius="
                              + var4.radius()
                              + ", population="
                              + var4.population()
                              + "/"
                              + var4.beds()
                              + " beds, food="
                              + var4.cachedFood()
                              + ", carriedFood="
                              + ColonyManager.x1397xrm4pk893(var1.method_9225(), var4)
                              + ", logs="
                              + var4.cachedLogs()
                              + ", stone="
                              + var4.cachedStone()
                              + ", iron="
                              + var4.x12nt3bv2et7b7()
                              + ", diamonds="
                              + var4.x3peiu091351qg()
                              + ", storages="
                              + var4.x2ad3s8aentmo4().size()
                              + ", roles="
                              + var8
                              + ", jobs="
                              + var9
                              + ", activeWorkers="
                              + var10
                              + ", threatAge="
                              + var5;
                           var1.method_9226(() -> class_2561.method_43470(var12), false);
                           var1.method_9226(() -> class_2561.method_43470("Развитие: " + ConstructionManager.x2ypihddkgbmhu(var4)), false);
                           var var13 = ColonyWorldData.get(var1.method_9225())
                              .land()
                              .stream()
                              .filter(var1x -> var1x.x3mnabq6qj85f8().equals(var4.id()))
                              .map(var0xx -> var0xx.x2kystgxl68xrn().method_23854() + " радиус=" + var0xx.x3rgd0i6cz19eu())
                              .toList();
                           var1.method_9226(
                              () -> class_2561.method_43470(
                                 "Недвижимость: участки="
                                    + var13
                                    + "; цены сейчас: маленький дом="
                                    + VillageEconomy.x2vr85xzgtiacc(var4, 32, false)
                                    + ", средний дом="
                                    + VillageEconomy.x2vr85xzgtiacc(var4, 64, false)
                                    + ", участок="
                                    + VillageEconomy.x2vr85xzgtiacc(var4, 24, true)
                                    + " изумрудов."
                              ),
                              false
                           );
                           String var14 = ColonyManager.x8x0922dn63y6(var4, var1.method_9225().method_75260())
                              .entrySet()
                              .stream()
                              .filter(var0xx -> var0xx.getKey() != ColonyRole.CITIZEN)
                              .map(var0xx -> var0xx.getKey().serializedName() + ":" + var0xx.getValue() + "%")
                              .collect(Collectors.joining("/"));
                           var1.method_9226(() -> class_2561.method_43470("Спрос на профессии: " + var14), false);
                           var1.method_9226(() -> class_2561.method_43470(LivestockManager.x32tk2361rdt82(var4)), false);
                           VillageDefenseState var15 = var4.x2f7z1gnnht5ab();
                           var1.method_9226(
                              () -> class_2561.method_43470(
                                 "Стены: радиус="
                                    + var15.x14zjd0to6v6yv
                                    + ", блоков="
                                    + var15.x16wpjsjmva3od.size()
                                    + ", осталось="
                                    + var15.x1q0gb1h69ywu7.size()
                                    + ", пропущено="
                                    + var15.x1fvrorwquv0wt
                                    + ", цель="
                                    + (var15.x1q0gb1h69ywu7.isEmpty() ? "-" : var15.x1q0gb1h69ywu7.getFirst().x1oppt2nr5wv2g().method_23854())
                              ),
                              false
                           );
                           var var16 = ColonyManager.x9jjpc1qt9hfg(var1.method_9225(), var4);
                           long var17 = var7.stream().filter(var0xx -> !var0xx.method_6109() && var0xx.method_5618() == 0).count();
                           var1.method_9226(
                              () -> class_2561.method_43470(
                                 "Размножение: "
                                    + (var16.isEmpty() ? "колония разрешает" : String.join("; ", var16))
                                    + "; готовых родителей="
                                    + var17
                                    + "/2 минимум. Колония тратит 24 единицы питательности любой еды из общих складов; носить именно хлеб не нужно. Нужна свободная кровать; следующая попытка через "
                                    + Math.max(0L, var4.x180oul2yysi55() - var1.method_9225().method_75260()) / 20L
                                    + " с."
                              ),
                              false
                           );
                           String var19 = var4.x1ja61emeln2hc()
                              .stream()
                              .filter(
                                 var0xx -> var0xx.x32oz43hmeynhc() == ConstructionStatus.QUEUED || var0xx.x32oz43hmeynhc() == ConstructionStatus.BUILDING
                              )
                              .map(var2x -> x224hvqfelnea1(var2x, var7, var1.method_9225().method_75260()))
                              .collect(Collectors.joining("; "));
                           String var20 = x3u8cqfu11cpc6(var7, ColonyMemories.x1h4kjv4hra8oe);
                           String var21 = x3u8cqfu11cpc6(var7, ColonyMemories.x2bwom1pasl6g5);
                           String var22 = x3u8cqfu11cpc6(var7, ColonyMemories.x15kki2mjvh7tw);
                           String var23 = x3u8cqfu11cpc6(var7, ColonyMemories.x263ucjykllb1b);
                           String var24 = x3u8cqfu11cpc6(var7, ColonyMemories.x2ppem7wipzctm);
                           String var25 = var4.x2ad3s8aentmo4()
                              .stream()
                              .map(var1x -> var4.x2h8eqlzb5mm0w(var1x).displayName() + "@" + var1x.method_23854())
                              .collect(Collectors.joining("; "));
                           String var26 = var7.stream()
                              .filter(var1x -> var4.roleOf(var1x.method_5667()) == ColonyRole.GUARD)
                              .map(
                                 var0xx -> var0xx.method_5845().substring(0, 8)
                                    + "@"
                                    + var0xx.method_24515().method_23854()
                                    + " weapon="
                                    + x2hdxge3ubqygw(var0xx.method_6047())
                                    + " armor="
                                    + x2hdxge3ubqygw(var0xx.method_6118(class_1304.field_6169))
                                    + "/"
                                    + x2hdxge3ubqygw(var0xx.method_6118(class_1304.field_6174))
                                    + "/"
                                    + x2hdxge3ubqygw(var0xx.method_6118(class_1304.field_6172))
                                    + "/"
                                    + x2hdxge3ubqygw(var0xx.method_6118(class_1304.field_6166))
                              )
                              .collect(Collectors.joining("; "));
                           var1.method_9226(() -> class_2561.method_43470("Targets: houses=[" + var19 + "]"), false);
                           var1.method_9226(
                              () -> class_2561.method_43470(
                                 "Ремонт: блоков="
                                    + var4.x2xqc3q79vfkw3().size()
                                    + ", координаты=["
                                    + var4.x2xqc3q79vfkw3()
                                       .keySet()
                                       .stream()
                                       .limit(12L)
                                       .<CharSequence>map(class_2382::method_23854)
                                       .collect(Collectors.joining("; "))
                                    + "]"
                              ),
                              false
                           );
                           var1.method_9226(
                              () -> class_2561.method_43470(
                                 "Строительство: свободных кроватей="
                                    + var4.x182p7mjnr75sg()
                                    + "/"
                                    + ColonyManager.x1ext6bk0354f9(var4.population())
                                    + " целевой запас, построено домов="
                                    + var4.x1ja61emeln2hc().stream().filter(var0xxx -> var0xxx.x32oz43hmeynhc() == ConstructionStatus.COMPLETE).count()
                                    + "/"
                                    + SmartAulConfig.x2by5f9ehxvwk9().village.max_constructed_houses
                                    + ", состояние="
                                    + ColonyManager.x2y69zyuty3rxs(var1.method_9225(), var4)
                                    + "."
                              ),
                              false
                           );
                           var1.method_9226(
                              () -> class_2561.method_43470(
                                 "Targets: trees=["
                                    + var20
                                    + "], farms=["
                                    + var21
                                    + "], mines=["
                                    + var22
                                    + "], fishing=["
                                    + var23
                                    + "], animals=["
                                    + var24
                                    + "]"
                              ),
                              false
                           );
                           var1.method_9226(
                              () -> class_2561.method_43470(
                                 "Storages: ["
                                    + var25
                                    + "] freeSlots="
                                    + ColonyManager.x11dwzrdveisv7(var1.method_9225(), var4)
                                    + "; eggs="
                                    + ColonyManager.x1mlne8tl8k5ag(var1.method_9225(), var4, var0xxx -> var0xxx.method_31574(class_1802.field_8803))
                                    + "/32; milk="
                                    + OfflineDevelopment.x31lz6msk67uxc(var1.method_9225(), var4)
                                    + "/4"
                              ),
                              false
                           );
                           var1.method_9226(() -> class_2561.method_43470("Militia: [" + var26 + "]"), false);
                           String var27 = var4.x1n1a2vp01kxzf().isEmpty() ? "-" : var4.x1n1a2vp01kxzf().getFirst().method_23854();
                           String var28 = var4.x31ow2tlg0dm5w().isEmpty()
                              ? "-"
                              : "entrance="
                                 + var4.x31ow2tlg0dm5w().getFirst().method_23854()
                                 + " frontier="
                                 + var4.x31ow2tlg0dm5w().getLast().method_23854()
                                 + " steps="
                                 + (var4.x31ow2tlg0dm5w().size() - 1);
                           var1.method_9226(
                              () -> class_2561.method_43470(
                                 "Road: next="
                                    + var27
                                    + " remaining="
                                    + var4.x1n1a2vp01kxzf().size()
                                    + " stalled="
                                    + var4.x3nmwt0p1j1m6g()
                                    + " skipped="
                                    + var4.x2frtu6yleucxg()
                                    + "; Mine: "
                                    + var28
                                    + ", deepTrips="
                                    + var4.x2qncyh2nmpkyq()
                                    + ", deepYield="
                                    + var4.x324ff4i2oimhg()
                                    + "; Pen: "
                                    + var4.x329rgb9lpsari().<String>map(class_2382::method_23854).orElse("-")
                              ),
                              false
                           );
                           String var29 = var1.method_9225()
                              .method_8390(class_1439.class, ColonyManager.x150ccxomrlozr(var1.method_9225(), var4), var0xx -> var0xx.method_5805())
                              .stream()
                              .map(var0xx -> var0xx.method_24515().method_23854() + " hp=" + (int)var0xx.method_6032() + "/" + (int)var0xx.method_6063())
                              .collect(Collectors.joining("; "));
                           long var30 = var1.method_9225()
                              .method_8390(class_1439.class, ColonyManager.x150ccxomrlozr(var1.method_9225(), var4), var0xx -> var0xx.method_5805())
                              .size();
                           var1.method_9226(
                              () -> class_2561.method_43470("Golems: " + var30 + "/" + VillageGolems.x20ln0j5i9gefl(var4) + " [" + var29 + "]"), false
                           );
                           boolean var32 = ColonyManager.x1tl8iu49onump(
                              var1.method_9225(), var4, var0xx -> var0xx.method_31574(class_1802.field_17518) || var0xx.method_31574(class_1802.field_17519)
                           );
                           var1.method_9226(
                              () -> class_2561.method_43470(
                                 "Golem materials: iron="
                                    + var4.x12nt3bv2et7b7()
                                    + "/36, pumpkin="
                                    + var32
                                    + ", nextBuild="
                                    + Math.max(0L, var4.x1ljiw4h4dmxfs() - var1.method_9211().method_3780())
                                    + " ticks"
                              ),
                              false
                           );
                           int var33 = ColonyManager.x1mlne8tl8k5ag(var1.method_9225(), var4, var0xx -> var0xx.method_31574(class_1802.field_8687));
                           var1.method_9226(
                              () -> class_2561.method_43470(
                                 "Economy: emeralds="
                                    + var33
                                    + ", sold="
                                    + var4.x4zx3pxdqkrq()
                                    + ", bought="
                                    + var4.x1v1doml6bflbz()
                                    + ", next="
                                    + Math.max(0L, var4.x3b9zegn105yce() - var1.method_9225().method_75260())
                                    + " ticks"
                              ),
                              false
                           );
                           var1.method_9226(
                              () -> class_2561.method_43470(
                                 "Развитие: elder="
                                    + var4.x3ctixeo4to9a4().map(var0xxx -> var0xxx.toString().substring(0, 8)).orElse("-")
                                    + ", founder="
                                    + var4.x2a380ray4md5t().map(var0xxx -> var0xxx.toString().substring(0, 8)).orElse("-")
                                    + ", founderTarget="
                                    + var4.x1x0agg3sdxjk8().<String>map(class_2382::method_23854).orElse("-")
                                    + ", visits="
                                    + var4.x37br4tln8q08z()
                                    + ", capital="
                                    + var4.x13ua0ftegttnr()
                                    + ", propertyOrder="
                                    + var4.x3t9uchcxi5zlm().map(var1x -> var1x.toString().substring(0, 8) + " L" + var4.x1beis4altbswn()).orElse("-")
                              ),
                              false
                           );
                           var4.x2npriys7z2eli(var1.method_9225().method_75260())
                              .ifPresent(
                                 var2x -> var1.method_9226(
                                    () -> class_2561.method_43470(
                                       "Theft alarm: player="
                                          + var2x.toString().substring(0, 8)
                                          + ", stolen="
                                          + var4.x6at97kfxajem()
                                          + ", remaining="
                                          + Math.max(0L, var4.x3ifovfwl4jqpk() - var1.method_9225().method_75260()) / 20L
                                          + "s"
                                    ),
                                    false
                                 )
                              );
                           String var34 = var7.stream()
                              .filter(var1x -> var4.roleOf(var1x.method_5667()) != ColonyRole.CITIZEN)
                              .map(
                                 var2x -> var4.roleOf(var2x.method_5667()).serializedName()
                                    + "@"
                                    + var2x.method_24515().method_23854()
                                    + " held="
                                    + x2hdxge3ubqygw(var2x.method_6047())
                                    + " recovery="
                                    + VillagerStuckRecovery.x3u53699ao0yas(var2x, var1.method_9225().method_75260())
                                    + " work=["
                                    + WorkDiagnostics.xnmb1jjez0ytg(var2x)
                                    + "] tasks="
                                    + var2x.method_18868()
                                       .method_27074()
                                       .stream()
                                       .filter(var0xxx -> var0xxx.getClass().getName().startsWith("net.smartaul."))
                                       .map(
                                          var1xx -> var1xx instanceof FarmerTask var6
                                             ? "Farm[" + var6.debugState() + "]"
                                             : (
                                                var1xx instanceof MinerTask var5x
                                                   ? "Mine[" + var5x.debugState(var2x) + "]"
                                                   : (
                                                      var1xx instanceof FishermanTask var4x
                                                         ? "Fish[" + var4x.debugState() + "]"
                                                         : (
                                                            var1xx instanceof RancherTask var3x
                                                               ? "Livestock[" + var3x.debugState() + "]"
                                                               : (
                                                                  var1xx instanceof HouseBuilderTask var2xx
                                                                     ? "Build[" + var2xx.debugState() + "]"
                                                                     : var1xx.getClass().getSimpleName()
                                                               )
                                                         )
                                                   )
                                             )
                                       )
                                       .collect(Collectors.joining("/"))
                              )
                              .collect(Collectors.joining("; "));
                           var1.method_9226(() -> class_2561.method_43470("Workers: [" + var34 + "]"), false);
                           return 1;
                        }
                     }
                  )
            )
      );
   }

   private static String x3u8cqfu11cpc6(List<class_1646> var0, class_4140<class_2338> var1) {
      return var0.stream()
         .flatMap(var1x -> var1x.method_18868().method_18904(var1).stream().map(var1xx -> var1x.method_5845().substring(0, 8) + "@" + var1xx.method_23854()))
         .collect(Collectors.joining("; "));
   }

   private static String x2hdxge3ubqygw(class_1799 var0) {
      return var0.method_7960() ? "-" : class_7923.field_41178.method_10221(var0.method_7909()).method_12832();
   }

   private static String x224hvqfelnea1(ConstructionJob var0, List<class_1646> var1, long var2) {
      String var4 = var0.x3vu8mnvg0blk4()
         .flatMap(var1x -> var1.stream().filter(var1xx -> var1xx.method_5667().equals(var1x)).findFirst())
         .flatMap(var0x -> var0x.method_18868().method_18904(ColonyMemories.x1r15huley8x9h))
         .<String>map(class_2382::method_23854)
         .orElse("-");
      String var5 = var0.x16o43azbz1opa() == Long.MIN_VALUE ? "-" : Long.toString(Math.max(0L, var2 - var0.x16o43azbz1opa()) / 20L);
      return var0.x32oz43hmeynhc().name().toLowerCase()
         + "@"
         + var0.x3ij6gescvakv2().method_23854()
         + " template="
         + var0.x3kqe1qjsw9oty().method_12832().substring(var0.x3kqe1qjsw9oty().method_12832().lastIndexOf(47) + 1)
         + " blocks="
         + var0.xmu75rcopbjnh()
         + "/"
         + var0.xxjwqetbgzzle()
         + " retry="
         + Math.max(0L, var0.x2zq2smhbu3lzo() - var2) / 20L
         + "s owner="
         + var0.x2nfrt8qxdscso().map(var1x -> var1x.toString().substring(0, 8) + " L" + var0.x37hzrzyr2xivs()).orElse("-")
         + " idle="
         + var5
         + "s supplyStall="
         + var0.xbnccd29nwem4() / 20
         + "s target="
         + var4;
   }
}
