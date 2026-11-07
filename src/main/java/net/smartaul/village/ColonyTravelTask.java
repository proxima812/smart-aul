package net.smartaul.village;

import com.google.common.collect.ImmutableMap;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.class_11;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1308;
import net.minecraft.class_1309;
import net.minecraft.class_1646;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2244;
import net.minecraft.class_2323;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_2680;
import net.minecraft.class_2742;
import net.minecraft.class_2756;
import net.minecraft.class_3218;
import net.minecraft.class_4097;
import net.minecraft.class_4140;
import net.minecraft.class_4141;

public final class ColonyTravelTask extends class_4097<class_1646> {
   private Colony x3h8nrw0jzbxhj;
   private long x1txgr0c09368a;
   private long xnm14zy5rwkmf;
   private class_2338 xh8i537idrcwu;
   private class_2338 x174ibrm7q6xkj;

   public ColonyTravelTask() {
      super(ImmutableMap.of(class_4140.field_18445, class_4141.field_18458), 600);
   }

   protected boolean shouldRun(class_3218 var1, class_1646 var2) {
      if (!var2.method_6109() && !var2.method_6113() && !var2.method_18009() && WorldEditPolicy.x35ev8l9vc9uk7(var1)) {
         this.x3h8nrw0jzbxhj = ColonyManager.x3psqezd5jem54(var1, var2.method_24515())
            .filter(var1x -> var1x.roleOf(var2.method_5667()) == ColonyRole.BUILDER && !var1x.x2s37os1kht7vr())
            .orElse(null);
         return this.x3h8nrw0jzbxhj != null && SettlementSupport.shouldRepair(var1, this.x3h8nrw0jzbxhj, var2);
      } else {
         return false;
      }
   }

   protected boolean shouldKeepRunning(class_3218 var1, class_1646 var2, long var3) {
      return this.x3h8nrw0jzbxhj != null
         && this.x3h8nrw0jzbxhj.roleOf(var2.method_5667()) == ColonyRole.BUILDER
         && SettlementSupport.shouldRepair(var1, this.x3h8nrw0jzbxhj, var2)
         && !var2.method_6113()
         && !var2.method_18009()
         && WorldEditPolicy.x35ev8l9vc9uk7(var1);
   }

   protected void run(class_3218 var1, class_1646 var2, long var3) {
      ActionTrace.worker(var2, "task_start", "ColonyTravelTask", false);
      this.x1txgr0c09368a = this.xnm14zy5rwkmf = var3;
      this.xh8i537idrcwu = null;
      this.x174ibrm7q6xkj = null;
   }

   protected void keepRunning(class_3218 var1, class_1646 var2, long var3) {
      if (var3 >= this.x1txgr0c09368a) {
         this.x1txgr0c09368a = var3 + 10L;
         class_2338 var5 = this.x3h8nrw0jzbxhj
            .x2xqc3q79vfkw3()
            .keySet()
            .stream()
            .filter(var2x -> VillageRepair.x2b8s98wj2zcbw(var1, this.x3h8nrw0jzbxhj, var2x))
            .min(Comparator.<class_2338>comparingInt(pos -> this.x3h8nrw0jzbxhj.safetyCovers.contains(pos) ? 0 : 1)
               .thenComparingDouble(var2.method_24515()::method_10262))
            .orElse(null);
         if (var5 != null) {
            this.xh8i537idrcwu = var5;
            class_2680 var6 = this.x3h8nrw0jzbxhj.x2xqc3q79vfkw3().get(var5);
            class_2680 var7 = var1.method_8320(var5);
            if (var7.method_26204() == var6.method_26204()) {
               this.x22mz196jv2hz8(var1, var5);
            } else if (!var7.method_26215() && !var7.method_45474()) {
               WorkDiagnostics.x1qd0mutwsr3sc(var2, "ремонт: место занято");
            } else {
               class_2338 var8 = null;
               if (var6.method_26204() instanceof class_2323) {
                  var8 = var6.method_11654(class_2323.field_10946) == class_2756.field_12607 ? var5.method_10084() : var5.method_10074();
               } else if (var6.method_26204() instanceof class_2244) {
                  var8 = var5.method_10079(
                     (class_2350)var6.method_11654(class_2244.field_11177), var6.method_11654(class_2244.field_9967) == class_2742.field_12557 ? 1 : -1
                  );
               }

               class_2680 var9 = var8 == null ? null : this.x3h8nrw0jzbxhj.x2xqc3q79vfkw3().get(var8);
               if (var9 != null && var1.method_8320(var8).method_26204() == var9.method_26204()) {
                  this.x22mz196jv2hz8(var1, var8);
                  var9 = null;
               }

               if (var9 == null
                  || (var1.method_8320(var8).method_26215() || var1.method_8320(var8).method_45474()) && WorldEditPolicy.x13f7be5vlapn7(var1, var8)) {
                  class_1792 var10 = HouseBuilderTask.materialItem(var6);
                  Predicate<class_1799> var11 = HouseBuilderTask.materialPredicate(var6, var10);
                  if (var10 != class_1802.field_8162) {
                     class_1799 var12 = class_1799.field_8037;
                     if (var11.test(var2.method_6047())) {
                        var12 = var2.method_6047();
                     } else {
                        for (int var13 = 0; var13 < var2.method_35199().method_5439(); var13++) {
                           if (var11.test(var2.method_35199().method_5438(var13))) {
                              var12 = var2.method_35199().method_5438(var13);
                              break;
                           }
                        }
                     }

                     if (var12.method_7960()) {
                        var var19 = ColonyManager.x2ytjyhq411pf7(var1, this.x3h8nrw0jzbxhj, var2.method_24515(), var11);
                        if (var19.isEmpty()) {
                           WorkDiagnostics.x1qd0mutwsr3sc(var2, "ремонт: нужны материалы");
                        } else if (var2.method_24515().method_10262((class_2382)var19.get()) > 9.0) {
                           VillagerShelter.x2wzylgizxltef(var1, var2, (class_2338)var19.get(), 0.8);
                        } else {
                           int var20 = Math.clamp(
                              this.x3h8nrw0jzbxhj.x2xqc3q79vfkw3().values().stream().filter(var1x -> HouseBuilderTask.materialItem(var1x) == var10).count(),
                              1,
                              16
                           );
                           ColonyManager.x5jd7014csotp(var1, (class_2338)var19.get(), var11, var20).ifPresent(var3x -> {
                              class_1799 var4 = var2.method_35199().method_5491(var3x);
                              if (!var4.method_7960() && !ColonyManager.x2cl1crde2rnxy(var1, this.x3h8nrw0jzbxhj, var4)) {
                                 var2.method_5775(var1, var4);
                              }
                           });
                        }
                     } else if (!HouseBuilderTask.withinBuildReach(var2, var5)) {
                        this.x2le3dr4kogzvn(var1, var2, var5);
                     } else {
                        if (!var6.method_26220(var1, var5).method_1110()) {
                           var var18 = var1.method_8333(null, new class_238(var5), var0 -> var0 instanceof class_1309 && var0.method_5805());
                           if (!var18.isEmpty()) {
                              for (class_1297 var15 : var18) {
                                 if (var15 instanceof class_1308 var16) {
                                    class_2338 var17 = class_2338.method_25997(
                                          var5, 4, 2, var2x -> VillagerShelter.x9bnd6e2jczhd(var1, var2x) && var2x.method_10262(var5) >= 4.0
                                       )
                                       .orElse(this.x3h8nrw0jzbxhj.core());
                                    if (ColonyManager.x5ll9elqpk1a4(var1)) {
                                       var16.method_5942().method_6337(var17.method_10263() + 0.5, var17.method_10264(), var17.method_10260() + 0.5, 0.8);
                                    }
                                 }
                              }

                              return;
                           }
                        }

                        if (this.x3h8nrw0jzbxhj.safetyCovers.contains(var5)
                           && (!VillageSafety.canCover(var1, this.x3h8nrw0jzbxhj, var5)
                              || !VillageSafety.solid(var1, var2.method_24515().method_10074()))) return;
                        if (WorldEditPolicy.x1bq0f8n53gtob(var1, var5, var6, 2)) {
                           if (var9 != null && !WorldEditPolicy.x1bq0f8n53gtob(var1, var8, var9, 2)) {
                              var1.method_8652(var5, var7, 2);
                           } else {
                              var12.method_7934(1);
                              var1.method_8408(var5, var6.method_26204());
                              if (var9 != null) {
                                 var1.method_8408(var8, var9.method_26204());
                                 this.x22mz196jv2hz8(var1, var8);
                              }

                              var2.method_6104(class_1268.field_5808);
                              WorkDiagnostics.x2t98zlq4lu49t(var2, this.x3h8nrw0jzbxhj.safetyCovers.contains(var5) ? "перекрытие провала" : "восстановление дома", 1);
                              this.x22mz196jv2hz8(var1, var5);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private void x22mz196jv2hz8(class_3218 var1, class_2338 var2) {
      if (this.x3h8nrw0jzbxhj.safetyCovers.remove(var2) && this.x3h8nrw0jzbxhj.completedSafetyCovers < Long.MAX_VALUE) this.x3h8nrw0jzbxhj.completedSafetyCovers++;
      this.x3h8nrw0jzbxhj.x2xqc3q79vfkw3().remove(var2);
      ColonyManager.xchl1k1tnh3w8(var1).method_80();
   }

   private void x2le3dr4kogzvn(class_3218 var1, class_1646 var2, class_2338 var3) {
      if (this.x174ibrm7q6xkj != null
         && VillagerShelter.x9bnd6e2jczhd(var1, this.x174ibrm7q6xkj)
         && x2n3rio18qdgz4(this.x174ibrm7q6xkj, var3) <= 16.0
         && Math.abs(this.x174ibrm7q6xkj.method_10264() - var3.method_10264()) <= 13
         && !VillagerShelter.xps35ehel6nkk(var2, this.x174ibrm7q6xkj, var1.method_75260())) {
         VillagerShelter.x2wzylgizxltef(var1, var2, this.x174ibrm7q6xkj, 0.85);
      } else {
         var var4 = new HashSet<class_2338>();

         for (int var5 = -4; var5 <= 4; var5++) {
            for (int var6 = -4; var6 <= 4; var6++) {
               if (var5 * var5 + var6 * var6 <= 16) {
                  for (int var7 = var3.method_10264() - 13; var7 <= var3.method_10264() + 3; var7++) {
                     class_2338 var8 = new class_2338(var3.method_10263() + var5, var7, var3.method_10260() + var6);
                     if (!var8.equals(var3)
                        && !var8.method_10074().equals(var3)
                        && VillagerShelter.x9bnd6e2jczhd(var1, var8)
                        && !VillagerShelter.xps35ehel6nkk(var2, var8, var1.method_75260())) {
                        var4.add(var8);
                     }
                  }
               }
            }
         }

         if (!var4.isEmpty() && ColonyManager.x5ll9elqpk1a4(var1)) {
            class_11 var9 = var2.method_5942().method_29934(var4, 0);
            if (var9 != null && var9.method_21655()) {
               this.x174ibrm7q6xkj = var9.method_48();
               VillagerShelter.x2wzylgizxltef(var1, var2, this.x174ibrm7q6xkj, 0.85);
            } else {
               WorkDiagnostics.x1qd0mutwsr3sc(var2, "ремонт: поиск безопасного подхода");
            }
         }
      }
   }

   private static double x2n3rio18qdgz4(class_2338 var0, class_2338 var1) {
      double var2 = var0.method_10263() - var1.method_10263();
      double var4 = var0.method_10260() - var1.method_10260();
      return var2 * var2 + var4 * var4;
   }

   protected void finishRunning(class_3218 var1, class_1646 var2, long var3) {
      ActionTrace.worker(var2, "task_stop", "ColonyTravelTask", false);
      if (this.x3h8nrw0jzbxhj != null
         && this.xh8i537idrcwu != null
         && this.x3h8nrw0jzbxhj.x2xqc3q79vfkw3().containsKey(this.xh8i537idrcwu)
         && var3 - this.xnm14zy5rwkmf >= 500L) {
         VillageRepair.xqxgtse6quf4h(var1, this.x3h8nrw0jzbxhj, this.xh8i537idrcwu);
      }

      var2.method_5942().method_6340();
      this.x3h8nrw0jzbxhj = null;
      this.xh8i537idrcwu = null;
      this.x174ibrm7q6xkj = null;
   }

   @Override
   protected boolean method_18919(class_3218 var1, class_1646 var2) {
      return shouldRun(var1, var2);
   }

   @Override
   protected boolean method_18927(class_3218 var1, class_1646 var2, long var3) {
      return shouldKeepRunning(var1, var2, var3);
   }

   @Override
   protected void method_18926(class_3218 var1, class_1646 var2, long var3) {
      finishRunning(var1, var2, var3);
   }

   @Override
   protected void method_18924(class_3218 var1, class_1646 var2, long var3) {
      keepRunning(var1, var2, var3);
   }

   @Override
   protected void method_18920(class_3218 var1, class_1646 var2, long var3) {
      run(var1, var2, var3);
   }
}
