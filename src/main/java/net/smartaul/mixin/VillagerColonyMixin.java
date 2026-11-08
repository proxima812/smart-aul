package net.smartaul.mixin;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1646;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1914;
import net.minecraft.class_2244;
import net.minecraft.class_2338;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3481;
import net.minecraft.class_3852;
import net.minecraft.class_4095;
import net.minecraft.class_4140;
import net.minecraft.class_4148;
import net.minecraft.class_4149;
import net.minecraft.class_4168;
import net.minecraft.class_4174;
import net.minecraft.class_4208;
import net.minecraft.class_4217;
import net.minecraft.class_7477;
import net.minecraft.class_7893;
import net.minecraft.class_9334;
import net.minecraft.class_4095.class_5303;
import net.minecraft.class_4153.class_4155;
import net.smartaul.village.Colony;
import net.smartaul.village.VillagerMaintenance;
import net.smartaul.village.VillagerShelter;
import net.smartaul.village.ColonyTrading;
import net.smartaul.village.ConstructionManager;
import net.smartaul.village.ColonySensors;
import net.smartaul.village.RancherTask;
import net.smartaul.village.ColonyRole;
import net.smartaul.village.WallBuilderTask;
import net.smartaul.village.ConstructionJob;
import net.smartaul.village.HouseBuilderTask;
import net.smartaul.village.GuardTask;
import net.smartaul.village.ColonyManager;
import net.smartaul.village.WorkDiagnostics;
import net.smartaul.village.ColonyTravelTask;
import net.smartaul.village.VillagerStuckRecovery;
import net.smartaul.village.RaidShelterTask;
import net.smartaul.village.VillageThreats;
import net.smartaul.village.MinerTask;
import net.smartaul.village.RoadBuilderTask;
import net.smartaul.village.LumberjackTask;
import net.smartaul.village.VillageSleep;
import net.smartaul.village.LandClaimManager;
import net.smartaul.village.VillagerTaskReset;
import net.smartaul.village.BlacksmithTask;
import net.smartaul.village.FishermanTask;
import net.smartaul.village.VillagerEquipment;
import net.smartaul.village.RepairTask;
import net.smartaul.village.ColonyActivities;
import net.smartaul.village.FarmerTask;
import net.smartaul.village.PropertyDeeds;
import net.smartaul.village.ColonyMemories;
import net.smartaul.village.ProjectileDefense;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1646.class)
public abstract class VillagerColonyMixin {
   @Shadow
   @Final
   private static ImmutableList<class_4140<?>> field_18538;
   @Shadow
   @Final
   private static ImmutableList<class_4149<? extends class_4148<? super class_1646>>> field_18539;

   @Inject(method = "method_5992", at = @At("HEAD"), cancellable = true)
   private void smartAul$onInteractMobHead(class_1657 var1, class_1268 var2, CallbackInfoReturnable<class_1269> var3) {
      class_1646 var4 = (class_1646)(Object)this;
      if (var2 == class_1268.field_5808 && !var4.method_6109() && var4.method_73183() instanceof class_3218 var5) {
         ColonyManager.x3psqezd5jem54(var5, var4.method_24515())
            .ifPresent(
               var5x -> {
                  ColonyRole var6 = var5x.roleOf(var4.method_5667());
                  int var7 = PropertyDeeds.xau920lx57wg3(var1.method_5998(var2));
                  if (var6 == ColonyRole.BUILDER && var7 > 0) {
                     if (PropertyDeeds.x11oga8h5115u7(var1.method_5998(var2))) {
                        PropertyDeeds.x3000fumbeekrm(var5.method_8503(), var1.method_5998(var2));
                        var1.method_7353(class_2561.method_43470("Этот листок уже использован. Повторный заказ невозможен."), true);
                     } else {
                        var var8 = PropertyDeeds.x2l483kg9fflzs(var5, var5x, var1, var2);
                        var1.method_7353(
                           class_2561.method_43470(
                              var8.isPresent()
                                 ? "Строитель принял заказ. Дом: " + ((ConstructionJob)var8.get()).x3ij6gescvakv2().method_23854()
                                 : "Заказ пока не принят: нужно завершить дорогу/предыдущий заказ или найти свободный участок. Листок не использован."
                           ),
                           true
                        );
                     }

                     var3.setReturnValue(class_1269.field_5812);
                  } else if (var6 != ColonyRole.CITIZEN && !var4.method_6113() && !var4.method_18009()) {
                     ColonyManager.x21g1rujs9r1h6(var5, var4, var6);
                     ColonyTrading.x88t1bn7r4bt7(var5, var5x, var4, var6);
                     VillagerTaskReset.x3qgnd0wqcbp6n(var4);
                     ((VillagerTradingInvoker)var4).smartAul$beginTradeWith(var1);
                     var3.setReturnValue(class_1269.field_5812);
                  }
               }
            );
      }
   }

   @Inject(method = "method_18008", at = @At("TAIL"))
   private void smartAul$onAfterUsingTail(class_1914 var1, CallbackInfo var2) {
      class_1646 var3 = (class_1646)(Object)this;
      if (var3.method_73183() instanceof class_3218 var4) {
         ColonyTrading.x1qctxo20x2yyf(var4, var3, var1);
      }
   }

   @Inject(method = "method_28306", at = @At("HEAD"), cancellable = true)
   private void smartAul$onCreateBrainProfileHead(CallbackInfoReturnable<class_5303<class_1646>> var1) {
      ImmutableList var2 = ImmutableList.builder().addAll(field_18538).addAll(ColonyMemories.x1eiihmuh1mj7p()).build();
      ImmutableList var3 = ImmutableList.builder().addAll(field_18539).add(ColonySensors.x38iq4tihdy4v8).build();
      var1.setReturnValue(class_4095.method_28311(var2, var3));
   }

   @Inject(method = "method_5958", at = @At("HEAD"))
   private void smartAul$onMobTickHead(class_3218 var1, CallbackInfo var2) {
      class_1646 var3 = (class_1646)(Object)this;
      if (!var3.method_6109() && var3.method_7231().comp_3521().method_40225(class_3852.field_17051)) {
         ColonyManager.x3psqezd5jem54(var1, var3.method_24515()).ifPresent(var2x -> {
            ColonyRole var3x = var2x.roleOf(var3.method_5667());
            if (var3x != ColonyRole.CITIZEN) {
               ColonyManager.x21g1rujs9r1h6(var1, var3, var3x);
            }
         });
      }
   }

   @Inject(method = "method_5958", at = @At("TAIL"))
   private void smartAul$onMobTickTail(class_3218 var1, CallbackInfo var2) {
      class_1646 var3 = (class_1646)(Object)this;
      var var4 = ColonyManager.x2yj2v4iudqlvb(var1, var3.method_5667()).or(() -> ColonyManager.x3psqezd5jem54(var1, var3.method_24515()));
      VillageSleep.x2zyakuv0vnixz(var1, var3);
      if (var4.isEmpty()) {
         var3.method_18868().method_18875(ColonyMemories.x3ecoc0soedv0m);
         if (var3.method_18868()
            .method_24538()
            .filter(
               var0 -> var0.equals(ColonyActivities.x12qpuae7zsr5m)
                  || var0.equals(ColonyActivities.x2t145qayl7tz3)
                  || var0.equals(ColonyActivities.LEISURE)
                  || var0.equals(ColonyActivities.x38u24k78sk73y)
                  || var0.equals(class_4168.field_22396)
            )
            .isPresent()) {
            var3.method_18868().method_18871(var1.method_75728(), var1.method_75260(), var3.method_73189());
         }
      } else {
         Colony var5 = (Colony)var4.get();
         if (ColonyManager.x3psqezd5jem54(var1, var3.method_24515()).map(var1x -> var1x.id().equals(var5.id())).orElse(false)) {
            var5.x1j7a15w06f1fz(var1.method_75260());
            ConstructionManager.x3ij59k18pnfmd(var1, var5);
         }

         VillagerEquipment.x3nc609h8fgb5y(var1, var5, var3);
         ColonyRole var6 = var5.roleOf(var3.method_5667());
         if (RaidShelterTask.active(var1, var5) && RaidShelterTask.civilian(var5, var3)) {
            if (var3.method_18009()) {
               var3.method_8259(null);
            }

            if (var3.method_6113()) {
               var3.method_18400();
            }

            if (var3.method_18868().method_24538().filter(ColonyActivities.x38u24k78sk73y::equals).isEmpty()) {
               for (class_7893 var19 : List.copyOf(var3.method_18868().method_27074())) {
                  if (!(var19 instanceof RaidShelterTask)) {
                     var19.method_18925(var1, var3, var1.method_75260());
                  }
               }

               VillagerShelter.x2fxoqmbs0henb(var3);
            }

            var3.method_18868().method_18875(class_4140.field_18445);
            var3.method_18868().method_24526(ColonyActivities.x38u24k78sk73y);
         } else {
            if (var3.method_18868().method_24538().filter(ColonyActivities.x38u24k78sk73y::equals).isPresent()) {
               var3.method_18868().method_18871(var1.method_75728(), var1.method_75260(), var3.method_73189());
            }

            if (!ProjectileDefense.x12ghbsk7tys8r(var1, var3, var5)) {
               if (var6 == ColonyRole.FOUNDER) {
                  var3.method_18868().method_18878(ColonyMemories.x3ecoc0soedv0m, var6);
                  if (var3.method_18868().method_24538().filter(ColonyActivities.x2t145qayl7tz3::equals).isEmpty()) {
                     for (class_7893 var18 : List.copyOf(var3.method_18868().method_27074())) {
                        var18.method_18925(var1, var3, var1.method_75260());
                     }

                     VillagerShelter.x2fxoqmbs0henb(var3);
                  }

                  var3.method_18868().method_18875(class_4140.field_18445);
                  var3.method_18868().method_24526(ColonyActivities.x2t145qayl7tz3);
               } else if (var3.method_18009()) {
                  VillagerTaskReset.x3qgnd0wqcbp6n(var3);
               } else {
                  if ((var1.method_75260() + var3.method_5628()) % 100L == 0L) {
                     var3.method_18868().method_18878(ColonyMemories.x3ecoc0soedv0m, var6);
                  }

                  boolean var7 = var5.x3klfyffbbfab2() != Long.MIN_VALUE && var1.method_75260() - var5.x3klfyffbbfab2() <= 200L
                     || RaidShelterTask.active(var1, var5)
                     || var6 == ColonyRole.GUARD && VillageThreats.x3kaxmxx3tadjj(var1, var5).isPresent();
                  boolean var8 = Math.floorMod(var1.method_8532(), 24000L) >= 1000L && Math.floorMod(var1.method_8532(), 24000L) < 9000L;
                  boolean var9 = Math.floorMod(var1.method_8532(), 24000L) >= 12000L;
                  boolean var10 = Math.floorMod(var1.method_8532(), 24000L) >= 9000L && Math.floorMod(var1.method_8532(), 24000L) < 12000L;
                  this.xwfvqwvn9pi6v(var1, var3, var5, var9, var7);
                  if (var3.method_6113()) {
                     var3.method_5942().method_6340();
                     var3.method_18868().method_18875(class_4140.field_18445);
                     var3.method_18799(class_243.field_1353);
                     var3.method_18868().method_24526(class_4168.field_18597);
                  } else {
                     boolean var11 = this.x19aa321fd7oor(var1, var3, var5, var10);
                     if (!LandClaimManager.x20vp2osbvc2gk(var1, var3, var5)) {
                        boolean var12;
                        boolean var13;
                        boolean var10000;
                        label280: {
                           VillagerMaintenance.xdfuvky2q520(var1, var5, var3);
                           WorkDiagnostics.x1m5qncn5p4tgc(var1, var5, var3);
                           VillagerStuckRecovery.x8pl4kh8avnpr(var1, var5, var3);
                           var12 = VillagerStuckRecovery.x3u53699ao0yas(var3, var1.method_75260());
                           var13 = var6 == ColonyRole.GUARD
                              && (
                                 var3.method_18868()
                                       .method_18904(ColonyMemories.x14b5znl8ndblc)
                                       .filter(var1x -> VillageThreats.x1bj0phudo3a5m(var1x) && VillageThreats.xs8awvrrlhug9(var5, var1x))
                                       .isPresent()
                                    || VillageThreats.x3kaxmxx3tadjj(var1, var5).isPresent()
                              );
                           label205:
                           if (var6 != ColonyRole.GUARD || !var7 && !var13) {
                              if (var8) {
                                 switch (var6) {
                                    case BUILDER:
                                       if (var5.x2s37os1kht7vr()
                                          || var5.x2ad3s8aentmo4().isEmpty()
                                          || var5.x3e4ycl8cmmmgc()
                                          || !var5.x1n1a2vp01kxzf().isEmpty()
                                          || !var5.x2xqc3q79vfkw3().isEmpty()
                                          || !var5.x2f7z1gnnht5ab().x1q0gb1h69ywu7.isEmpty()) {
                                          break label205;
                                       }
                                       break;
                                    case FARMER:
                                       if (!var5.x2ad3s8aentmo4().isEmpty()) {
                                          break label205;
                                       }
                                       break;
                                    case LUMBERJACK:
                                       break label205;
                                    case MINER:
                                       if (!var5.x2ad3s8aentmo4().isEmpty()) {
                                          break label205;
                                       }
                                       break;
                                    case FISHERMAN:
                                       if (!var5.x2ad3s8aentmo4().isEmpty()) {
                                          break label205;
                                       }
                                       break;
                                    case RANCHER:
                                       if (!var5.x2ad3s8aentmo4().isEmpty()) {
                                          break label205;
                                       }
                                       break;
                                    case GUARD:
                                       if (!var5.x2ad3s8aentmo4().isEmpty()) {
                                          break label205;
                                       }
                                 }
                              }

                              var10000 = false;
                              break label280;
                           }

                           var10000 = true;
                        }

                        boolean var14 = var10000;
                        class_4168 var15 = var12 || var11
                           ? class_4168.field_18595
                           : (
                              var9 && !var7
                                 ? class_4168.field_18597
                                 : (
                                    var6 != ColonyRole.GUARD || !var13 && !var7 && !(var3.method_6032() < var3.method_6063() * 0.3F)
                                       ? (var14 ? ColonyActivities.x12qpuae7zsr5m
                                          : !var7 && net.smartaul.village.VillageLeisure.visit(var1, var5, var3) ? ColonyActivities.LEISURE : null)
                                       : class_4168.field_22396
                                 )
                           );
                        if (var15 != null) {
                           var3.method_18868().method_24526(var15);
                        } else if (var3.method_18868()
                           .method_24538()
                           .filter(
                              var0 -> var0.equals(class_4168.field_22396)
                                 || var0.equals(ColonyActivities.x12qpuae7zsr5m)
                                 || var0.equals(ColonyActivities.x2t145qayl7tz3)
                                 || var0.equals(ColonyActivities.LEISURE)
                           )
                           .isPresent()) {
                           var3.method_18868().method_18871(var1.method_75728(), var1.method_75260(), var3.method_73189());
                        }

                        if (var6 == ColonyRole.GUARD
                           && var8
                           && !var7
                           && (var1.method_75260() + var3.method_5628()) % 40L == 0L
                           && var3.method_18868().method_27074().stream().noneMatch(var0 -> var0 instanceof BlacksmithTask)) {
                           GuardTask.patrol(var1, var5, var3);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private boolean x19aa321fd7oor(class_3218 var1, class_1646 var2, Colony var3, boolean var4) {
      int var5 = x1ukapw8ymk7ox(var2);
      boolean var6 = !var2.method_6109() && var2.method_5618() == 0;
      boolean var7 = x1pbipaexrd92u(var2);
      boolean var8 = var4 && (!var7 || var6 && var5 < 12);
      if ((var1.method_75260() + var2.method_5628()) % 20L != 0L) {
         return var8;
      } else {
         if (var2.method_6032() < var2.method_6063() && (var1.method_75260() + var2.method_5628()) % 40L == 0L) {
            for (int var9 = 0; var9 < var2.method_35199().method_5439(); var9++) {
               class_1799 var10 = var2.method_35199().method_5438(var9);
               class_4174 var11 = (class_4174)var10.method_58694(class_9334.field_50075);
               if (var11 != null) {
                  var10.method_7934(1);
                  var2.method_6025(Math.max(2.0F, var11.comp_2491() * 1.5F));
                  var2.method_6104(class_1268.field_5808);
                  return var8;
               }
            }

            ColonyManager.x1falh21nhy4yk(var1, var3, var2.method_24515()).ifPresent(var3x -> {
               if (var2.method_24515().method_10262(var3x) <= 9.0 && var3.cachedFood() > var3.population() * 12) {
                  ColonyManager.x3lxwdirnn77qc(var1, var3, var0x -> var0x.method_58694(class_9334.field_50075) != null).ifPresent(var1xx -> {
                     class_4174 var2xx = (class_4174)var1xx.method_58694(class_9334.field_50075);
                     var2.method_6025(var2xx == null ? 2.0F : Math.max(2.0F, var2xx.comp_2491() * 1.5F));
                     var2.method_6104(class_1268.field_5808);
                  });
               }
            });
         }

         if (!var8) {
            return false;
         } else {
            var var14 = ColonyManager.x1falh21nhy4yk(var1, var3, var2.method_24515());
            if (var14.isEmpty()) {
               return true;
            } else if (var2.method_24515().method_10262((class_2382)var14.get()) > 9.0) {
               var2.method_18868().method_18878(ColonyMemories.x3o547clcv9kaw, (class_2338)var14.get());
               VillagerShelter.x2wzylgizxltef(var1, var2, (class_2338)var14.get(), 0.85);
               return true;
            } else {
               int var15 = var6 ? 12 : (var7 ? 0 : 1);

               for (int var16 = 0; var16 < 12 && var5 < var15; var16++) {
                  var var12 = ColonyManager.x3lxwdirnn77qc(var1, var3, var0 -> var0.method_58694(class_9334.field_50075) != null);
                  if (var12.isEmpty()) {
                     break;
                  }

                  var5 += ColonyManager.xhahxgpa08yc9((class_1799)var12.get());
                  class_1799 var13 = var2.method_35199().method_5491((class_1799)var12.get());
                  if (!var13.method_7960()) {
                     ColonyManager.x2cl1crde2rnxy(var1, var3, var13);
                  }
               }

               if (!x1pbipaexrd92u(var2)) {
                  ColonyManager.x3lxwdirnn77qc(var1, var3, var0 -> var0.method_58694(class_9334.field_50075) != null).ifPresent(var3x -> {
                     class_1799 var4x = var2.method_35199().method_5491(var3x);
                     if (!var4x.method_7960()) {
                        ColonyManager.x2cl1crde2rnxy(var1, var3, var4x);
                     }
                  });
               }

               var2.method_18868().method_18875(ColonyMemories.x3o547clcv9kaw);
               return !x1pbipaexrd92u(var2) || var6 && x1ukapw8ymk7ox(var2) < 12;
            }
         }
      }
   }

   private static boolean x1pbipaexrd92u(class_1646 var0) {
      for (int var1 = 0; var1 < var0.method_35199().method_5439(); var1++) {
         if (var0.method_35199().method_5438(var1).method_58694(class_9334.field_50075) != null) {
            return true;
         }
      }

      return false;
   }

   private static int x1ukapw8ymk7ox(class_1646 var0) {
      int var1 = 0;

      for (int var2 = 0; var2 < var0.method_35199().method_5439(); var2++) {
         class_1799 var3 = var0.method_35199().method_5438(var2);
         var1 += ColonyManager.xhahxgpa08yc9(var3) * var3.method_7947();
      }

      return var1;
   }

   @Inject(method = "method_19174", at = @At("TAIL"))
   private void smartAul$onInitBrainTail(class_4095<class_1646> var1, CallbackInfo var2) {
      var1.method_18881(ColonyActivities.x2t145qayl7tz3, ImmutableList.of());
      var1.method_18881(ColonyActivities.x38u24k78sk73y, ImmutableList.of(Pair.of(0, new RaidShelterTask())));
      var1.method_18881(ColonyActivities.LEISURE, ImmutableList.of(Pair.of(0, new net.smartaul.village.LeisureTask())));
      var1.method_18881(class_4168.field_22396, ImmutableList.of(Pair.of(0, new GuardTask())));
      var1.method_18881(
         ColonyActivities.x12qpuae7zsr5m,
         ImmutableList.of(
            Pair.of(0, new RepairTask()),
            Pair.of(0, new net.smartaul.village.CourierTask()),
            Pair.of(0, new ColonyTravelTask()),
            Pair.of(0, new RoadBuilderTask()),
            Pair.of(1, new HouseBuilderTask()),
            Pair.of(1, new WallBuilderTask()),
            Pair.of(1, new BlacksmithTask()),
            Pair.of(2, new FarmerTask()),
            Pair.of(3, new LumberjackTask()),
            Pair.of(4, new MinerTask()),
            Pair.of(5, new FishermanTask()),
            Pair.of(6, new RancherTask())
         )
      );
   }

   private void xwfvqwvn9pi6v(class_3218 var1, class_1646 var2, Colony var3, boolean var4, boolean var5) {
      if (var4 && !var5) {
         for (class_7893 var7 : List.copyOf(var2.method_18868().method_27074())) {
            if (var7.getClass().getName().startsWith("net.smartaul.") || var7 instanceof class_4217) {
               var7.method_18925(var1, var2, var1.method_75260());
            }
         }

         if (var2.method_6113()) {
            var2.method_5942().method_6340();
            var2.method_18868().method_18875(class_4140.field_18445);
         } else if ((var1.method_75260() + var2.method_5628()) % 20L == 0L) {
            var var9 = var2.method_18868()
               .method_18904(class_4140.field_18438)
               .filter(var1x -> var1x.comp_2207().equals(var1.method_27983()))
               .<class_2338>map(class_4208::comp_2208)
               .filter(var2x -> xi6qq1j9vjyfk(var1, var2x, var2));
            var var10 = var9.isPresent()
               ? var9
               : var1.method_19494()
                  .method_21647(
                     var0 -> var0.method_40225(class_7477.field_39291),
                     var2x -> xi6qq1j9vjyfk(var1, var2x, var2),
                     var3.core(),
                     var3.radius(),
                     class_4155.field_18489
                  )
                  .min(Comparator.comparingDouble(var1x -> var1x.method_10262(var2.method_24515())));
            if (!var10.isEmpty()) {
               class_2338 var8 = (class_2338)var10.get();
               var2.method_18868().method_18878(class_4140.field_18438, class_4208.method_19443(var1.method_27983(), var8));
               if (var2.method_24515().method_10262(var8) <= 4.0) {
                  var2.method_5942().method_6340();
                  var2.method_18403(var8);
               } else {
                  VillagerShelter.x1lrdp6728c3ml(var1, var2, var8, 0.85);
               }
            }
         }
      } else {
         if (var2.method_6113()) {
            var2.method_18400();
         }
      }
   }

   private static boolean xi6qq1j9vjyfk(class_3218 var0, class_2338 var1, class_1646 var2) {
      if (!var0.method_14178().method_12123(var1.method_10263() >> 4, var1.method_10260() >> 4)) {
         return false;
      } else {
         class_2680 var3 = var0.method_8320(var1);
         if (!var3.method_26164(class_3481.field_16443)) {
            return false;
         } else {
            return var3.method_28498(class_2244.field_9968) && var3.method_11654(class_2244.field_9968)
               ? var2.method_18398().<Boolean>map(var1::equals).orElse(false)
               : true;
         }
      }
   }
}
