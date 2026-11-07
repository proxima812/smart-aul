package net.smartaul.village;

import net.smartaul.config.SmartAulConfig;

import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.class_1268;
import net.minecraft.class_1309;
import net.minecraft.class_1646;
import net.minecraft.class_1799;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3481;
import net.minecraft.class_4097;
import net.minecraft.class_2350.class_2353;
import net.minecraft.class_2902.class_2903;

public final class WallBuilderTask extends class_4097<class_1646> {
   private Colony x2u8nlt588jgdf;
   private WallBlockPlan x14qty2zxfnsdq;
   private final BlockWorkProgress x29jk2160vr00r = new BlockWorkProgress();
   private long xt2mnxmxrcwkx;
   private long x48z7fhj2ntkf;

   public WallBuilderTask() {
      super(ImmutableMap.of(), 12000);
   }

   protected boolean shouldRun(class_3218 var1, class_1646 var2) {
      if (!var2.method_6109() && !var2.method_6113() && !var2.method_18009()) {
         this.x2u8nlt588jgdf = ColonyManager.x3psqezd5jem54(var1, var2.method_24515())
            .filter(var2x -> var2x.roleOf(var2.method_5667()) == ColonyRole.BUILDER && VillageDefense.xuj80syz8c60l(var1, var2x, var2))
            .orElse(null);
         return this.x2u8nlt588jgdf != null;
      } else {
         return false;
      }
   }

   protected boolean shouldKeepRunning(class_3218 var1, class_1646 var2, long var3) {
      return this.x2u8nlt588jgdf != null
         && this.x2u8nlt588jgdf.roleOf(var2.method_5667()) == ColonyRole.BUILDER
         && !var2.method_6113()
         && !var2.method_18009()
         && VillageDefense.xuj80syz8c60l(var1, this.x2u8nlt588jgdf, var2);
   }

   protected void run(class_3218 var1, class_1646 var2, long var3) {
      ActionTrace.worker(var2, "task_start", "WallBuilderTask", false);
      this.x14qty2zxfnsdq = null;
      this.xt2mnxmxrcwkx = var3;
      this.x48z7fhj2ntkf = var3;
   }

   protected void keepRunning(class_3218 var1, class_1646 var2, long var3) {
      if (WorldEditPolicy.x35ev8l9vc9uk7(var1)) {
         if (this.x14qty2zxfnsdq == null || !this.x2u8nlt588jgdf.x2f7z1gnnht5ab().x1q0gb1h69ywu7.contains(this.x14qty2zxfnsdq)) {
            this.x14qty2zxfnsdq = VillageDefense.x2h9dz6t7qspz2(var1, this.x2u8nlt588jgdf, var2);
            this.xt2mnxmxrcwkx = var3;
            this.x29jk2160vr00r.xupxzzyodxtwj(var1, var2);
         }

         if (this.x14qty2zxfnsdq != null) {
            class_2338 var5 = this.x14qty2zxfnsdq.x1oppt2nr5wv2g();
            class_2680 var6 = var1.method_8320(var5);
            if (this.x14qty2zxfnsdq.xttubpoe0ch3r() || !x29zfr9q2yep60(var1, var5) && !this.x2u8nlt588jgdf.x2o79xnwz4g9nh(var5, 1)) {
               if (!this.x14qty2zxfnsdq.xttubpoe0ch3r()
                  || var6.method_26204() == this.x14qty2zxfnsdq.x1bbgyigo2fq58().method_26204()
                     && this.x2u8nlt588jgdf.x2f7z1gnnht5ab().x16wpjsjmva3od.containsKey(var5)) {
                  if (!WorldEditPolicy.xjs779qdest9c(var1, var5)
                     && !VillageRepair.x239t1m3hfi74d(var1, var5)
                     && (!this.x2u8nlt588jgdf.xhu9xvlie05r6(var5, 1) || this.x14qty2zxfnsdq.xttubpoe0ch3r())) {
                     if (!this.x14qty2zxfnsdq.xttubpoe0ch3r() && var6.method_26204() == this.x14qty2zxfnsdq.x1bbgyigo2fq58().method_26204()) {
                        if (this.x2u8nlt588jgdf.x2f7z1gnnht5ab().x16wpjsjmva3od.containsKey(var5)) {
                           VillageDefense.x2kvmlso28quo8(var1, this.x2u8nlt588jgdf, this.x14qty2zxfnsdq, true);
                        } else {
                           VillageDefense.x2kvmlso28quo8(var1, this.x2u8nlt588jgdf, this.x14qty2zxfnsdq, false);
                        }

                        this.x14qty2zxfnsdq = null;
                     } else if (var3 - this.xt2mnxmxrcwkx > 600L) {
                        VillageDefense.xj9o3at3dqqse(var1, this.x2u8nlt588jgdf, this.x14qty2zxfnsdq);
                        WorkDiagnostics.x1qd0mutwsr3sc(var2, "стена: ищет другой подход");
                        this.x14qty2zxfnsdq = null;
                     } else {
                        boolean var15 = var6.method_26164(class_3481.field_15503) || var6.method_26164(class_3481.field_15475);
                        if (!this.x14qty2zxfnsdq.xttubpoe0ch3r() && !var6.method_26215() && !var6.method_45474() && !var15) {
                           VillageDefense.x2kvmlso28quo8(var1, this.x2u8nlt588jgdf, this.x14qty2zxfnsdq, false);
                           this.x14qty2zxfnsdq = null;
                        } else {
                           double var16 = var2.method_23317() - (var5.method_10263() + 0.5);
                           double var10 = var2.method_23321() - (var5.method_10260() + 0.5);
                           if (var2.method_5829().method_994(new class_238(var5)) || var2.method_24515().method_10074().equals(var5)) {
                              for (class_2350 var20 : class_2353.field_11062) {
                                 class_2338 var21 = var5.method_10079(var20, 2);
                                 var21 = new class_2338(
                                    var21.method_10263(),
                                    var1.method_8624(class_2903.field_13203, var21.method_10263(), var21.method_10260()),
                                    var21.method_10260()
                                 );
                                 if (VillagerShelter.x9bnd6e2jczhd(var1, var21)) {
                                    VillagerShelter.x2wzylgizxltef(var1, var2, var21, 0.8);
                                    break;
                                 }
                              }
                           } else if (!(var16 * var16 + var10 * var10 > 9.0) && !(Math.abs(var2.method_23318() - var5.method_10264()) > 5.0)) {
                              var2.method_5942().method_6340();
                              if (!this.x14qty2zxfnsdq.xttubpoe0ch3r() && var15) {
                                 if (this.x29jk2160vr00r.xpohy1q30uxlk(var1, var2, var5, BlockWorkProgress.x20ocn113pbk5f(var1, var2, var5), true)
                                    && WorldEditPolicy.x231if4rvsvptj(var1, var5, true, var2)) {
                                    this.x29jk2160vr00r.xupxzzyodxtwj(var1, var2);
                                 }
                              } else if (this.x29jk2160vr00r
                                 .xpohy1q30uxlk(
                                    var1,
                                    var2,
                                    var5,
                                    this.x14qty2zxfnsdq.xttubpoe0ch3r()
                                       ? BlockWorkProgress.x20ocn113pbk5f(var1, var2, var5)
                                       : SmartAulConfig.x2by5f9ehxvwk9().work.placement_ticks,
                                    this.x14qty2zxfnsdq.xttubpoe0ch3r()
                                 )) {
                                 if (this.x14qty2zxfnsdq.xttubpoe0ch3r()) {
                                    if (var2.method_5829().method_994(new class_238(var5)) || var2.method_24515().method_10074().equals(var5)) {
                                       return;
                                    }

                                    var var12 = class_2248.method_9609(var6, var1, var5, var1.method_8321(var5), var2, var2.method_6047());
                                    if (var1.method_8651(var5, false, var2)) {
                                       for (class_1799 var14 : var12) {
                                          if (!ColonyManager.x2cl1crde2rnxy(var1, this.x2u8nlt588jgdf, var14)) {
                                             var2.method_5775(var1, var14);
                                          }
                                       }

                                       VillageDefense.x2kvmlso28quo8(var1, this.x2u8nlt588jgdf, this.x14qty2zxfnsdq, true);
                                       WorkDiagnostics.x2t98zlq4lu49t(var2, "перенос старой стены", 1);
                                       this.x14qty2zxfnsdq = null;
                                    }
                                 } else {
                                    Predicate<class_1799> var17 = VillageDefense.xdo05oqfqaqzq(this.x14qty2zxfnsdq.x1bbgyigo2fq58());
                                    if (ColonyManager.x1mlne8tl8k5ag(var1, this.x2u8nlt588jgdf, var17) <= 32) {
                                       return;
                                    }

                                    if (!var1.method_8333(null, new class_238(var5), var0 -> var0.method_5805() && var0 instanceof class_1309).isEmpty()) {
                                       return;
                                    }

                                    boolean var19 = ColonyManager.x38ipozok9mc0x(
                                       var1,
                                       this.x2u8nlt588jgdf,
                                       () -> !ColonyManager.x22hjebj4vmztt(var1, this.x2u8nlt588jgdf, var17, 1)
                                          ? false
                                          : var1.method_8652(var5, this.x14qty2zxfnsdq.x1bbgyigo2fq58(), 3),
                                       List.of()
                                    );
                                    if (var19) {
                                       VillageDefense.x2kvmlso28quo8(var1, this.x2u8nlt588jgdf, this.x14qty2zxfnsdq, true);
                                       WorkDiagnostics.x2t98zlq4lu49t(var2, "стена деревни", 1);
                                       this.x14qty2zxfnsdq = null;
                                    }
                                 }

                                 var2.method_6104(class_1268.field_5808);
                              }
                           } else {
                              if (var3 >= this.x48z7fhj2ntkf) {
                                 VillagerShelter.x2wzylgizxltef(var1, var2, var5, 0.8);
                                 this.x48z7fhj2ntkf = var3 + 20L;
                              }
                           }
                        }
                     }
                  } else {
                     VillageDefense.x2kvmlso28quo8(var1, this.x2u8nlt588jgdf, this.x14qty2zxfnsdq, false);
                     this.x14qty2zxfnsdq = null;
                  }
               } else {
                  VillageDefense.x2kvmlso28quo8(var1, this.x2u8nlt588jgdf, this.x14qty2zxfnsdq, false);
                  this.x14qty2zxfnsdq = null;
               }
            } else {
               for (WallBlockPlan var8 : List.copyOf(this.x2u8nlt588jgdf.x2f7z1gnnht5ab().x1q0gb1h69ywu7)) {
                  if (!var8.xttubpoe0ch3r()
                     && var8.x1oppt2nr5wv2g().method_10263() == var5.method_10263()
                     && var8.x1oppt2nr5wv2g().method_10260() == var5.method_10260()) {
                     VillageDefense.x33x279v3webhm(var1, this.x2u8nlt588jgdf, var8);
                  }
               }

               this.x14qty2zxfnsdq = null;
            }
         }
      }
   }

   protected void finishRunning(class_3218 var1, class_1646 var2, long var3) {
      ActionTrace.worker(var2, "task_stop", "WallBuilderTask", false);
      this.x29jk2160vr00r.xupxzzyodxtwj(var1, var2);
      var2.method_5942().method_6340();
      this.x2u8nlt588jgdf = null;
      this.x14qty2zxfnsdq = null;
   }

   private static boolean x29zfr9q2yep60(class_3218 var0, class_2338 var1) {
      for (int var2 = 0; var2 <= 4; var2++) {
         class_2338 var3 = var1.method_10087(var2);
         if (!var0.method_8316(var3).method_15769()) {
            return true;
         }

         class_2680 var4 = var0.method_8320(var3);
         if (!var4.method_26215() && !var4.method_45474()) {
            return false;
         }
      }

      return false;
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
