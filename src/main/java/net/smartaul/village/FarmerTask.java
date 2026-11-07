package net.smartaul.village;

import net.smartaul.config.SmartAulConfig;

import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;
import net.minecraft.class_1263;
import net.minecraft.class_1268;
import net.minecraft.class_1309;
import net.minecraft.class_1646;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2302;
import net.minecraft.class_2338;
import net.minecraft.class_2344;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3449;
import net.minecraft.class_3489;
import net.minecraft.class_4097;
import net.minecraft.class_4140;
import net.minecraft.class_4141;
import net.minecraft.class_7045;
import net.minecraft.class_9334;
import net.minecraft.class_2350.class_2353;
import net.minecraft.class_2902.class_2903;

public final class FarmerTask extends class_4097<class_1646> {
   private static final List<class_2338> x8hdlkby0zqy7 = x28pmcpngjzkye();
   private Colony x2t4nzijeau7w8;
   private FarmerPhase x35hbh6x2b34v;
   private class_2338 x186dphgnqvqj8;
   private class_2338 x2zlk0t7dy583;
   private int x3ge7r6nupqfxf;
   private int x2t1b8rmtaayej;
   private int x3hb4q8slofu00;
   private int x248yv75v4ezcm;
   private int x22gyzpic72nbl;
   private class_2338 x3573wm1i9fumn;
   private class_2338 xubfe0kx0uwh0;
   private class_2338 x3abk5220xo1ii;
   private class_2338 x302ihddc8urvz;
   private class_2338 x170sea3kgcr1k;
   private class_2338 x383bqm5z33o4t;
   private class_2338 xai7ni1lsqyge;
   private class_2338 x2iq1y0s1fcym6;
   private final Set<class_2338> x35axhpl2cj5i9 = new HashSet<>();
   private long x108884j082kq2;
   private double x2k8pxoh02imeh;
   private final Map<class_2338, Long> x1joansufiq3vh = new HashMap<>();
   private long x1hnez0xuhdh5r;
   private long x3kpmopdhvi7xy;
   private final BlockWorkProgress x39cq9cwr96dck = new BlockWorkProgress();
   private boolean x3uazqikqycd5p;

   public FarmerTask() {
      super(ImmutableMap.of(class_4140.field_18445, class_4141.field_18458), 12000);
   }

   protected boolean shouldRun(class_3218 var1, class_1646 var2) {
      if (!var2.method_6109()
         && !var2.method_6113()
         && !var2.method_18009()
         && var1.method_75260() >= this.x3kpmopdhvi7xy
         && WorldEditPolicy.x35ev8l9vc9uk7(var1)) {
         this.x2t4nzijeau7w8 = ColonyManager.x3psqezd5jem54(var1, var2.method_24515())
            .filter(var1x -> var1x.roleOf(var2.method_5667()) == ColonyRole.FARMER && !var1x.x2ad3s8aentmo4().isEmpty())
            .orElse(null);
         return this.x2t4nzijeau7w8 != null;
      } else {
         return false;
      }
   }

   protected void run(class_3218 var1, class_1646 var2, long var3) {
      ActionTrace.worker(var2, "task_start", "FarmerTask", false);
      this.x35hbh6x2b34v = FarmerPhase.SUPPLY;
      this.x3uazqikqycd5p = false;
      this.x248yv75v4ezcm = 0;
      this.x22gyzpic72nbl = 0;
      this.x1hnez0xuhdh5r = var3;
      this.x186dphgnqvqj8 = null;
      this.x302ihddc8urvz = null;
      this.x1k14u7t078rxc();
      this.x3ge7r6nupqfxf = 0;
      this.x1joansufiq3vh.entrySet().removeIf(var2x -> var2x.getValue() <= var3);
   }

   protected boolean shouldKeepRunning(class_3218 var1, class_1646 var2, long var3) {
      return !var2.method_6113()
         && !var2.method_18009()
         && this.x2t4nzijeau7w8 != null
         && this.x2t4nzijeau7w8.roleOf(var2.method_5667()) == ColonyRole.FARMER
         && !this.x3uazqikqycd5p;
   }

   protected void keepRunning(class_3218 var1, class_1646 var2, long var3) {
      if (var3 >= this.x1hnez0xuhdh5r) {
         if (WorldEditPolicy.x35ev8l9vc9uk7(var1)) {
            this.x1hnez0xuhdh5r = var3 + 2L;
            if (this.x35hbh6x2b34v == FarmerPhase.SUPPLY || this.x35hbh6x2b34v == FarmerPhase.RETURN) {
               this.x16irl4fyul8tl(var1, var2);
            } else if (this.x248yv75v4ezcm >= 64) {
               this.x35hbh6x2b34v = FarmerPhase.RETURN;
            } else if (this.x35hbh6x2b34v == FarmerPhase.SEARCH) {
               if (!this.x2osz16rzlj1uy(var1, var2)) {
                  if (this.x22gyzpic72nbl >= 1) {
                     if (x3ek8tyax561ln(var2) > 0) {
                        this.x2zlk0t7dy583 = this.x2zlm4l0rs2vkb(var1, var2).orElse(null);
                        if (this.x2zlk0t7dy583 != null) {
                           this.x35hbh6x2b34v = FarmerPhase.EXPAND;
                           this.x3hb4q8slofu00 = 0;
                           this.x186dphgnqvqj8 = this.x2zlk0t7dy583;
                           return;
                        }
                     }

                     this.x35hbh6x2b34v = FarmerPhase.RETURN;
                  }
               }
            } else if (this.x35hbh6x2b34v == FarmerPhase.EXPAND) {
               this.x3cfcl93yta6x3(var1, var2);
            } else if (this.x186dphgnqvqj8 == null) {
               this.x35hbh6x2b34v = FarmerPhase.SEARCH;
            } else if (this.x2t4nzijeau7w8.x2a24huns14rw4(this.x186dphgnqvqj8, 3)) {
               this.x1joansufiq3vh.put(this.x186dphgnqvqj8.method_10062(), var3 + 1200L);
               this.x186dphgnqvqj8 = null;
               this.x302ihddc8urvz = null;
               this.x35hbh6x2b34v = FarmerPhase.SEARCH;
               this.x3ge7r6nupqfxf = 0;
               this.x22gyzpic72nbl = 0;
               WorkDiagnostics.x1qd0mutwsr3sc(var2, "цель фермера освобождена для стройплощадки");
            } else {
               var2.method_18868().method_18878(ColonyMemories.x2bwom1pasl6g5, this.x186dphgnqvqj8);
               if (this.x3ihyxa2ovvxin(var1, var2, this.x186dphgnqvqj8)) {
                  class_2680 var5 = var1.method_8320(this.x186dphgnqvqj8);
                  if (this.x39cq9cwr96dck.xpohy1q30uxlk(var1, var2, this.x186dphgnqvqj8, SmartAulConfig.x2by5f9ehxvwk9().work.farming_ticks, false)) {
                     if (this.x35hbh6x2b34v == FarmerPhase.HARVEST) {
                        if (var5.method_26204() instanceof class_2302 var6 && var6.method_9825(var5)) {
                           class_1792 var9 = var5.method_27852(class_2246.field_10609)
                              ? class_1802.field_8179
                              : (
                                 var5.method_27852(class_2246.field_10247)
                                    ? class_1802.field_8567
                                    : (var5.method_27852(class_2246.field_10341) ? class_1802.field_8186 : class_1802.field_8861)
                              );
                           class_1792 var8 = var5.method_27852(class_2246.field_10609)
                              ? class_1802.field_8179
                              : (
                                 var5.method_27852(class_2246.field_10247)
                                    ? class_1802.field_8567
                                    : (var5.method_27852(class_2246.field_10341) ? class_1802.field_8309 : class_1802.field_8317)
                              );
                           if (this.x14ghv3cndp6s0(var1, this.x186dphgnqvqj8.method_10074())
                              && var6.method_9564().method_26184(var1, this.x186dphgnqvqj8)
                              && WorldEditPolicy.x1bq0f8n53gtob(var1, this.x186dphgnqvqj8, var6.method_9564(), 3)) {
                              this.x3bk6yjss3lhsi(var1, this.x186dphgnqvqj8.method_10074());
                              this.xfiiclr55ays8(var1, var2, new class_1799(var9, var8 == var9 ? 2 : 1));
                              if (var8 != var9) {
                                 this.xfiiclr55ays8(var1, var2, new class_1799(var8, 2));
                              }

                              WorkDiagnostics.x2t98zlq4lu49t(var2, "сбор и пересев", 1);
                              this.x248yv75v4ezcm++;
                           }
                        } else if ((var5.method_27852(class_2246.field_46282) || var5.method_27852(class_2246.field_46283))
                           && this.x14ghv3cndp6s0(var1, this.x186dphgnqvqj8.method_10074())
                           && WorldEditPolicy.x231if4rvsvptj(var1, this.x186dphgnqvqj8, false, var2)) {
                           this.xfiiclr55ays8(
                              var1, var2, new class_1799(var5.method_27852(class_2246.field_46282) ? class_1802.field_17518 : class_1802.field_8497, 1)
                           );
                           this.x248yv75v4ezcm++;
                        }
                     } else if (this.x35hbh6x2b34v == FarmerPhase.HAY) {
                        if (var5.method_27852(class_2246.field_10359)
                           && this.x3fr9c9b48dbto(var1, this.x186dphgnqvqj8)
                           && WorldEditPolicy.x231if4rvsvptj(var1, this.x186dphgnqvqj8, false, var2)) {
                           this.xfiiclr55ays8(var1, var2, new class_1799(class_1802.field_8861, 9));
                           this.x248yv75v4ezcm++;
                           var2.method_6104(class_1268.field_5808);
                        }
                     } else if (this.x35hbh6x2b34v == FarmerPhase.PLANT) {
                        this.x10t5vgir9ic2y(var1, var2, this.x186dphgnqvqj8.method_10074());
                     } else if (this.x35hbh6x2b34v == FarmerPhase.SEEDS) {
                        if ((var5.method_27852(class_2246.field_10479) || var5.method_27852(class_2246.field_10214))
                           && WorldEditPolicy.x231if4rvsvptj(var1, this.x186dphgnqvqj8, false, var2)
                           && var1.field_9229.method_43056()) {
                           this.xfiiclr55ays8(var1, var2, new class_1799(class_1802.field_8317));
                        }
                     } else if (this.x35hbh6x2b34v == FarmerPhase.WATER
                        && x3g51t72kitfhf(var1, this.x186dphgnqvqj8)
                        && x17w4mwk2x8sut(var2, class_1802.field_8550)) {
                        this.xfiiclr55ays8(var1, var2, new class_1799(class_1802.field_8705));
                     }

                     var2.method_6104(class_1268.field_5808);
                     this.x186dphgnqvqj8 = null;
                     this.x302ihddc8urvz = null;
                     this.x22gyzpic72nbl = 0;
                     if (this.x35hbh6x2b34v != FarmerPhase.RETURN) {
                        this.x35hbh6x2b34v = FarmerPhase.SEARCH;
                     }
                  }
               }
            }
         }
      }
   }

   private void x16irl4fyul8tl(class_3218 var1, class_1646 var2) {
      var var3 = ColonyManager.x3jrfw2d3yykeu(var1, this.x2t4nzijeau7w8, var2, HousePurpose.FOOD);
      if (this.x35hbh6x2b34v == FarmerPhase.SUPPLY && x3ek8tyax561ln(var2) < 8) {
         var var4 = this.x2t4nzijeau7w8
            .x2ad3s8aentmo4()
            .stream()
            .filter(var1x -> var1.method_14178().method_12123(var1x.method_10263() >> 4, var1x.method_10260() >> 4))
            .filter(
               var1x -> var1.method_8321(var1x) instanceof class_1263 var2x
                  && IntStream.range(0, var2x.method_5439()).anyMatch(var1xx -> x1lanxufahckh0(var2x.method_5438(var1xx)))
            )
            .min(
               Comparator.<class_2338>comparingInt(
                     var1x -> {
                        class_1263 var2x = (class_1263)var1.method_8321(var1x);
                        return IntStream.range(0, var2x.method_5439())
                              .anyMatch(var1xx -> x1lanxufahckh0(var2x.method_5438(var1xx)) && !var2x.method_5438(var1xx).method_31574(class_1802.field_46249))
                           ? 0
                           : 1;
                     }
                  )
                  .thenComparingDouble(var1x -> var1x.method_10262(var2.method_24515()))
            );
         if (var4.isPresent()) {
            var3 = var4;
         }
      }

      if (var3.isEmpty()) {
         this.x3uazqikqycd5p = true;
      } else if (this.x3ihyxa2ovvxin(var1, var2, (class_2338)var3.get())) {
         for (int var8 = 0; var8 < var2.method_35199().method_5439(); var8++) {
            class_1799 var5 = var2.method_35199().method_5438(var8);
            if (x1lanxufahckh0(var5)) {
               int var6 = Math.max(0, var5.method_7947() - 8);
               if (var6 > 0) {
                  class_1799 var7 = var5.method_46651(var6);
                  WorkDiagnostics.x25cotfj45r525(var1, this.x2t4nzijeau7w8, var2, var7);
                  var5.method_7934(var6 - var7.method_7947());
               }
            } else if (var5.method_31574(class_1802.field_8229)) {
               int var12 = Math.max(0, var5.method_7947() - 1);
               if (var12 > 0) {
                  class_1799 var13 = var5.method_46651(var12);
                  WorkDiagnostics.x25cotfj45r525(var1, this.x2t4nzijeau7w8, var2, var13);
                  var5.method_7934(var12 - var13.method_7947());
               }
            } else if (x1lanxufahckh0(var5)
               || var5.method_31574(class_1802.field_8861)
               || var5.method_31574(class_1802.field_8229)
               || var5.method_31574(class_1802.field_8186)
               || var5.method_31574(class_1802.field_17518)
               || var5.method_31574(class_1802.field_8497)) {
               WorkDiagnostics.x25cotfj45r525(var1, this.x2t4nzijeau7w8, var2, var5);
            }
         }

         int var9 = 0;

         while (
            var9 < 32
               && ColonyManager.x1mlne8tl8k5ag(var1, this.x2t4nzijeau7w8, var0 -> var0.method_31574(class_1802.field_8861)) >= 19
               && ColonyManager.x38ipozok9mc0x(
                  var1,
                  this.x2t4nzijeau7w8,
                  () -> ColonyManager.x22hjebj4vmztt(var1, this.x2t4nzijeau7w8, var0 -> var0.method_31574(class_1802.field_8861), 3),
                  List.of(new class_1799(class_1802.field_8229))
               )
         ) {
            var9++;
         }

         if (!x2tznyqefzmfni(var2)) {
            ColonyManager.x3lxwdirnn77qc(var1, this.x2t4nzijeau7w8, var0 -> var0.method_58694(class_9334.field_50075) != null)
               .ifPresent(var3x -> this.xfiiclr55ays8(var1, var2, var3x));
         }

         if (this.x35hbh6x2b34v == FarmerPhase.RETURN) {
            this.x3uazqikqycd5p = true;
            this.x3kpmopdhvi7xy = var1.method_75260() + 20L;
         } else {
            VillagerInventory.x31tan2efb24l6(var1, this.x2t4nzijeau7w8, var2, class_3489.field_42613, class_1802.field_8167, class_1802.field_8431);
            VillagerInventory.x3bl8qz81ha13t(var1, this.x2t4nzijeau7w8, var2);
            if (!ColonyManager.x10q255l9ii1rw(var1, this.x2t4nzijeau7w8, class_1802.field_46249)
               && ColonyManager.x1mlne8tl8k5ag(var1, this.x2t4nzijeau7w8, var0 -> var0.method_31574(class_1802.field_17518)) >= 2) {
               ColonyManager.x38ipozok9mc0x(
                  var1,
                  this.x2t4nzijeau7w8,
                  () -> ColonyManager.x22hjebj4vmztt(var1, this.x2t4nzijeau7w8, var0 -> var0.method_31574(class_1802.field_17518), 1),
                  List.of(new class_1799(class_1802.field_46249, 4))
               );
            }

            if (x3ek8tyax561ln(var2) < 32) {
               var9 = 32 - x3ek8tyax561ln(var2);
               var var11 = ColonyManager.x5jd7014csotp(
                  var1, (class_2338)var3.get(), var0 -> x1lanxufahckh0(var0) && !var0.method_31574(class_1802.field_46249), var9
               );
               if (var11.isEmpty()) {
                  var11 = ColonyManager.x5jd7014csotp(var1, (class_2338)var3.get(), FarmerTask::x1lanxufahckh0, Math.min(4, var9));
               }

               var11.ifPresent(var3x -> this.xfiiclr55ays8(var1, var2, var3x));
            }

            this.x35hbh6x2b34v = FarmerPhase.SEARCH;
            this.x3ge7r6nupqfxf = 0;
            this.x22gyzpic72nbl = 0;
            this.x1k14u7t078rxc();
         }
      }
   }

   private boolean x2osz16rzlj1uy(class_3218 var1, class_1646 var2) {
      int var3 = Math.min(this.x2t4nzijeau7w8.radius() - 4, 128);
      int var4 = x3ek8tyax561ln(var2);

      for (int var5 = 0; var5 < 2048; var5++) {
         if (this.x3ge7r6nupqfxf >= x8hdlkby0zqy7.size() || x8hdlkby0zqy7.get(this.x3ge7r6nupqfxf).method_10262(class_2338.field_10980) > var3 * var3) {
            this.x3ge7r6nupqfxf = 0;
            this.x22gyzpic72nbl = 1;
            if (var4 > 0 && this.x170sea3kgcr1k != null) {
               this.x186dphgnqvqj8 = this.x170sea3kgcr1k;
               this.x35hbh6x2b34v = FarmerPhase.PLANT;
            } else if (var4 > 0 && this.x3573wm1i9fumn != null) {
               this.x186dphgnqvqj8 = this.x3573wm1i9fumn;
               this.x35hbh6x2b34v = FarmerPhase.PLANT;
            } else if (this.xai7ni1lsqyge != null) {
               this.x186dphgnqvqj8 = this.xai7ni1lsqyge;
               this.x35hbh6x2b34v = FarmerPhase.HARVEST;
            } else if (this.x2iq1y0s1fcym6 != null) {
               this.x186dphgnqvqj8 = this.x2iq1y0s1fcym6;
               this.x35hbh6x2b34v = FarmerPhase.HAY;
            } else if (var4 > 0 && this.x383bqm5z33o4t != null) {
               this.x186dphgnqvqj8 = this.x383bqm5z33o4t;
               this.x35hbh6x2b34v = FarmerPhase.PLANT;
            } else if (var4 > 0 && this.x3abk5220xo1ii != null && !x23na3hzbpcw6h(var2, class_1802.field_8705)) {
               this.x186dphgnqvqj8 = this.x3abk5220xo1ii;
               this.x35hbh6x2b34v = FarmerPhase.WATER;
            } else if (var4 == 0 && this.xubfe0kx0uwh0 != null) {
               this.x186dphgnqvqj8 = this.xubfe0kx0uwh0;
               this.x35hbh6x2b34v = FarmerPhase.SEEDS;
            }

            this.x1k14u7t078rxc();
            return this.x186dphgnqvqj8 != null;
         }

         class_2338 var6 = x8hdlkby0zqy7.get(this.x3ge7r6nupqfxf++);
         int var7 = this.x2t4nzijeau7w8.core().method_10263() + var6.method_10263();
         int var8 = this.x2t4nzijeau7w8.core().method_10260() + var6.method_10260();
         if (var1.method_14178().method_12123(var7 >> 4, var8 >> 4)) {
            int var9 = var1.method_8624(class_2903.field_13203, var7, var8) - 1;

            for (int var10 = Math.max(var1.method_31607(), var9 - 3); var10 <= var9 + 2; var10++) {
               class_2338 var11 = new class_2338(var7, var10, var8);
               class_2680 var12 = var1.method_8320(var11);
               if ((
                     var12.method_26204() instanceof class_2302
                        || var12.method_27852(class_2246.field_10362)
                        || var12.method_27852(class_2246.field_10359)
                        || var12.method_27852(class_2246.field_46283)
                        || var12.method_27852(class_2246.field_46282)
                        || var12.method_27852(class_2246.field_10382)
                        || var12.method_27852(class_2246.field_10479)
                        || var12.method_27852(class_2246.field_10214)
                        || x2c1mm5rnqniq6(var12) && x2csm4l72r60h(var1, var11)
                  )
                  && !this.x2t4nzijeau7w8.x2a24huns14rw4(var11, 3)
                  && BuildingSiteValidator.x14gj5yhqspcfw(var1, this.x2t4nzijeau7w8, var11)
                  && !ColonyWorldData.get(var1).isPlayerBlock(var11)
                  && var1.method_8321(var11) == null) {
                  if (var12.method_27852(class_2246.field_10362)) {
                     this.x3bk6yjss3lhsi(var1, var11);
                  }

                  if (this.x1joansufiq3vh.getOrDefault(var11, 0L) <= var1.method_75260()
                     && this.x1joansufiq3vh.getOrDefault(var11.method_10084(), 0L) <= var1.method_75260()) {
                     if (var12.method_27852(class_2246.field_10359) && this.x3fr9c9b48dbto(var1, var11)) {
                        this.x2iq1y0s1fcym6 = x2xg33b8fprhix(var2, this.x2iq1y0s1fcym6, var11);
                     }

                     if (var12.method_26204() instanceof class_2302 var13 && var13.method_9825(var12) && this.x14ghv3cndp6s0(var1, var11.method_10074())
                        || var12.method_27852(class_2246.field_46283)
                        || var12.method_27852(class_2246.field_46282) && (x1zz5gn7bdzakj(var1, var11) || this.x14ghv3cndp6s0(var1, var11.method_10074()))) {
                        this.xai7ni1lsqyge = x2xg33b8fprhix(var2, this.xai7ni1lsqyge, var11);
                     }

                     if (var4 > 0
                        && x2csm4l72r60h(var1, var11)
                        && this.x7lsf0es95maw(var1, var2, var11.method_10084()) >= 0
                        && this.x14ghv3cndp6s0(var1, var11)) {
                        if (var12.method_27852(class_2246.field_10362)) {
                           this.x3573wm1i9fumn = x2xg33b8fprhix(var2, this.x3573wm1i9fumn, var11.method_10084());
                        } else if (x2c1mm5rnqniq6(var12) && x3unncyqyb7c08(var1, var11) && xmxmjfdwaicjw(var1, var11)) {
                           this.x383bqm5z33o4t = x2xg33b8fprhix(var2, this.x383bqm5z33o4t, var11.method_10084());
                        }
                     }

                     if (var4 > 0
                        && x2c1mm5rnqniq6(var12)
                        && x2csm4l72r60h(var1, var11)
                        && this.x7lsf0es95maw(var1, var2, var11.method_10084()) >= 0
                        && this.x14ghv3cndp6s0(var1, var11)
                        && xmxmjfdwaicjw(var1, var11)
                        && (this.x2t4nzijeau7w8.x1hdthtpd2qb8g().contains(var11) || VillageRepair.x1e11i1kd8301v(var1, var11))) {
                        this.x3bk6yjss3lhsi(var1, var11);
                        this.x170sea3kgcr1k = x2xg33b8fprhix(var2, this.x170sea3kgcr1k, var11.method_10084());
                     }

                     if (var4 == 0 && this.xubfe0kx0uwh0 == null && (var12.method_27852(class_2246.field_10479) || var12.method_27852(class_2246.field_10214))) {
                        this.xubfe0kx0uwh0 = var11;
                     }

                     if (this.x3abk5220xo1ii == null && x23na3hzbpcw6h(var2, class_1802.field_8550) && x3g51t72kitfhf(var1, var11)) {
                        this.x3abk5220xo1ii = var11;
                     }
                  }
               }
            }
         }
      }

      return true;
   }

   private static class_2338 x2xg33b8fprhix(class_1646 var0, class_2338 var1, class_2338 var2) {
      return var1 != null && !(var2.method_10262(var0.method_24515()) < var1.method_10262(var0.method_24515())) ? var1 : var2;
   }

   private void x1k14u7t078rxc() {
      this.x3573wm1i9fumn = this.xubfe0kx0uwh0 = this.x3abk5220xo1ii = this.x170sea3kgcr1k = this.xai7ni1lsqyge = this.x2iq1y0s1fcym6 = this.x383bqm5z33o4t = null;
   }

   private void x3bk6yjss3lhsi(class_3218 var1, class_2338 var2) {
      if (this.x2t4nzijeau7w8.x1tdgzp0ab8tn6(var2)) {
         ColonyManager.xchl1k1tnh3w8(var1).method_80();
      }
   }

   private Optional<class_2338> x2zlm4l0rs2vkb(class_3218 var1, class_1646 var2) {
      for (int var3 = 0; var3 < 1024; var3++) {
         class_2338 var4 = x8hdlkby0zqy7.get(Math.floorMod(this.x2t1b8rmtaayej++, x8hdlkby0zqy7.size()));
         int var5 = Math.min(this.x2t4nzijeau7w8.radius() - 4, 128);
         if (var4.method_10262(class_2338.field_10980) > var5 * var5) {
            this.x2t1b8rmtaayej = 0;
         } else {
            int var6 = this.x2t4nzijeau7w8.core().method_10263() + var4.method_10263();
            int var7 = this.x2t4nzijeau7w8.core().method_10260() + var4.method_10260();
            if (var1.method_14178().method_12123(var6 >> 4, var7 >> 4)) {
               class_2338 var8 = new class_2338(var6, var1.method_8624(class_2903.field_13203, var6, var7) - 1, var7);
               if (this.x1joansufiq3vh.getOrDefault(var8, 0L) <= var1.method_75260()
                  && this.x14ghv3cndp6s0(var1, var8)
                  && x2csm4l72r60h(var1, var8)
                  && (x2c1mm5rnqniq6(var1.method_8320(var8)) || var1.method_8320(var8).method_27852(class_2246.field_10382))
                  && (x23na3hzbpcw6h(var2, class_1802.field_8705) || xmxmjfdwaicjw(var1, var8))) {
                  int var9 = 0;

                  for (class_2338 var11 : class_2338.method_10097(var8.method_10069(-2, 0, -2), var8.method_10069(2, 0, 2))) {
                     if (!var11.equals(var8)
                        && x2c1mm5rnqniq6(var1.method_8320(var11))
                        && x2csm4l72r60h(var1, var11)
                        && this.x14ghv3cndp6s0(var1, var11)
                        && (x23na3hzbpcw6h(var2, class_1802.field_8705) || xmxmjfdwaicjw(var1, var11))) {
                        var9++;
                     }
                  }

                  if (var9 >= 4) {
                     return Optional.of(var8);
                  }
               }
            }
         }
      }

      return Optional.empty();
   }

   private static boolean x2c1mm5rnqniq6(class_2680 var0) {
      return var0.method_27852(class_2246.field_10566) || var0.method_27852(class_2246.field_10219);
   }

   private static boolean x2csm4l72r60h(class_3218 var0, class_2338 var1) {
      class_2680 var2 = var0.method_8320(var1.method_10084());
      return var2.method_26215() || var2.method_27852(class_2246.field_10479) || var2.method_27852(class_2246.field_10214);
   }

   private static boolean x3unncyqyb7c08(class_3218 var0, class_2338 var1) {
      for (class_2338 var3 : class_2338.method_10097(var1.method_10069(-1, 0, -1), var1.method_10069(1, 0, 1))) {
         if (var0.method_8320(var3).method_27852(class_2246.field_10362)) {
            return true;
         }
      }

      return false;
   }

   private static boolean x1zz5gn7bdzakj(class_3218 var0, class_2338 var1) {
      for (class_2350 var3 : class_2353.field_11062) {
         if (var0.method_8320(var1.method_10093(var3)).method_27852(class_2246.field_46284)) {
            return true;
         }
      }

      return false;
   }

   private boolean x3fr9c9b48dbto(class_3218 var1, class_2338 var2) {
      class_3449 var3 = var1.method_27056().method_57560(var2, class_7045.field_37045);
      if (var3 != class_3449.field_16713 && var3.method_16657() && var1.method_27056().method_41033(var2, var3)) {
         return true;
      } else if (var2.method_10262(this.x2t4nzijeau7w8.core()) > 2304.0) {
         return false;
      } else {
         for (class_2338 var5 : class_2338.method_10097(var2.method_10069(-4, -5, -4), var2.method_10069(4, 1, 4))) {
            if (var1.method_8320(var5).method_27852(class_2246.field_10194)) {
               return true;
            }
         }

         return false;
      }
   }

   private boolean x14ghv3cndp6s0(class_3218 var1, class_2338 var2) {
      if (!var1.method_14178().method_12123(var2.method_10263() >> 4, var2.method_10260() >> 4)) {
         return false;
      } else if (!BuildingSiteValidator.x14gj5yhqspcfw(var1, this.x2t4nzijeau7w8, var2)) {
         return false;
      } else {
         for (class_2338 var4 : class_2338.method_10097(var2.method_10069(-1, 0, -1), var2.method_10069(1, 1, 1))) {
            class_2680 var5 = var1.method_8320(var4);
            if (var5.method_27852(class_2246.field_10194) || var5.method_27852(class_2246.field_10467) || var1.method_8321(var4) != null) {
               return false;
            }
         }

         return true;
      }
   }

   private static List<class_2338> x28pmcpngjzkye() {
      var var0 = new ArrayList<class_2338>();

      for (int var1 = -128; var1 <= 128; var1++) {
         for (int var2 = -128; var2 <= 128; var2++) {
            var0.add(new class_2338(var1, 0, var2));
         }
      }

      var0.sort(Comparator.comparingDouble(var0x -> var0x.method_10262(class_2338.field_10980)));
      return List.copyOf(var0);
   }

   private void x3cfcl93yta6x3(class_3218 var1, class_1646 var2) {
      if (this.x2zlk0t7dy583 != null && !this.x14ghv3cndp6s0(var1, this.x2zlk0t7dy583)) {
         this.x1joansufiq3vh.put(this.x2zlk0t7dy583.method_10062(), var1.method_75260() + 1200L);
         this.x35hbh6x2b34v = FarmerPhase.SEARCH;
         this.x186dphgnqvqj8 = null;
         this.x2zlk0t7dy583 = null;
         this.x302ihddc8urvz = null;
         this.x3ge7r6nupqfxf = 0;
         this.x22gyzpic72nbl = 0;
      } else if (this.x3hb4q8slofu00 < 25 && x3ek8tyax561ln(var2) != 0) {
         var2.method_18868().method_18878(ColonyMemories.x2bwom1pasl6g5, this.x2zlk0t7dy583);
         if (this.x3hb4q8slofu00 == 0 && !xmxmjfdwaicjw(var1, this.x2zlk0t7dy583)) {
            if (!x23na3hzbpcw6h(var2, class_1802.field_8705)
               || !x2c1mm5rnqniq6(var1.method_8320(this.x2zlk0t7dy583))
               || !x2csm4l72r60h(var1, this.x2zlk0t7dy583)) {
               this.x35hbh6x2b34v = FarmerPhase.RETURN;
               return;
            }

            if (!this.x3ihyxa2ovvxin(var1, var2, this.x2zlk0t7dy583.method_10084())) {
               return;
            }

            if (!var1.method_8320(this.x2zlk0t7dy583.method_10084()).method_26215()) {
               WorldEditPolicy.x231if4rvsvptj(var1, this.x2zlk0t7dy583.method_10084(), false, var2);
            }

            if (WorldEditPolicy.x1bq0f8n53gtob(var1, this.x2zlk0t7dy583, class_2246.field_10382.method_9564(), 3) && x17w4mwk2x8sut(var2, class_1802.field_8705)
               )
             {
               this.xfiiclr55ays8(var1, var2, new class_1799(class_1802.field_8550));
            }
         }

         class_2338 var3 = this.x2zlk0t7dy583.method_10069(this.x3hb4q8slofu00 % 5 - 2, 0, this.x3hb4q8slofu00 / 5 - 2);
         if (this.x35axhpl2cj5i9.contains(var3)
            || !this.x14ghv3cndp6s0(var1, var3)
            || !x2csm4l72r60h(var1, var3)
            || !x2c1mm5rnqniq6(var1.method_8320(var3))
            || !xmxmjfdwaicjw(var1, var3)) {
            this.x3hb4q8slofu00++;
         } else if (this.x3ihyxa2ovvxin(var1, var2, var3.method_10084())) {
            if (this.x39cq9cwr96dck.xpohy1q30uxlk(var1, var2, var3.method_10084(), SmartAulConfig.x2by5f9ehxvwk9().work.farming_ticks, false)) {
               this.x10t5vgir9ic2y(var1, var2, var3);
               this.x3hb4q8slofu00++;
               this.x302ihddc8urvz = null;
            }
         }
      } else {
         this.x35hbh6x2b34v = FarmerPhase.SEARCH;
         this.x186dphgnqvqj8 = null;
         this.x302ihddc8urvz = null;
         this.x3ge7r6nupqfxf = 0;
         this.x22gyzpic72nbl = 0;
         this.x1k14u7t078rxc();
      }
   }

   private void x10t5vgir9ic2y(class_3218 var1, class_1646 var2, class_2338 var3) {
      if (x3ek8tyax561ln(var2) != 0 && x2csm4l72r60h(var1, var3) && this.x14ghv3cndp6s0(var1, var3)) {
         if (this.x7lsf0es95maw(var1, var2, var3.method_10084()) >= 0) {
            class_2680 var4 = var1.method_8320(var3);
            if (var4.method_27852(class_2246.field_10362) || x2c1mm5rnqniq6(var4) && xmxmjfdwaicjw(var1, var3)) {
               if (!var1.method_8320(var3.method_10084()).method_26215()) {
                  WorldEditPolicy.x231if4rvsvptj(var1, var3.method_10084(), false, var2);
               }

               if (!var4.method_27852(class_2246.field_10362)) {
                  class_238 var5 = new class_238(
                     var3.method_10263(),
                     var3.method_10264(),
                     var3.method_10260(),
                     var3.method_10263() + 1,
                     var3.method_10264() + 0.9375,
                     var3.method_10260() + 1
                  );
                  if (!WorldEditPolicy.x13f7be5vlapn7(var1, var3)
                     || !var1.method_8333(null, var5, var0 -> var0.method_5805() && var0 instanceof class_1309).isEmpty()) {
                     return;
                  }

                  if (!var1.method_8652(var3, (class_2680)class_2246.field_10362.method_9564().method_11657(class_2344.field_11009, 7), 3)) {
                     return;
                  }
               }

               this.x3bk6yjss3lhsi(var1, var3);
               this.x2o0pdq0sbpo5(var1, var2, var3.method_10084());
            }
         }
      }
   }

   private void x2o0pdq0sbpo5(class_3218 var1, class_1646 var2, class_2338 var3) {
      if (this.x14ghv3cndp6s0(var1, var3.method_10074())
         && var1.method_8320(var3.method_10074()).method_27852(class_2246.field_10362)
         && var1.method_8320(var3).method_26215()
         && x1z1pu5d45jp7l(var1, var3)) {
         int var4 = -1;
         class_2338 var5 = null;
         if (this.x35axhpl2cj5i9.size() < 2) {
            for (int var6 = 0; var6 < var2.method_35199().method_5439(); var6++) {
               if (var2.method_35199().method_5438(var6).method_31574(class_1802.field_46249)) {
                  var5 = this.xkkvqxmgd5bq8(var1, var3);
                  if (var5 != null) {
                     var4 = var6;
                     break;
                  }
               }
            }
         }

         if (var4 < 0) {
            var4 = this.x7lsf0es95maw(var1, var2, var3);
         }

         if (var4 >= 0) {
            class_1799 var8 = var2.method_35199().method_5438(var4);
            class_2248 var7 = var8.method_31574(class_1802.field_8179)
               ? class_2246.field_10609
               : (
                  var8.method_31574(class_1802.field_8567)
                     ? class_2246.field_10247
                     : (
                        var8.method_31574(class_1802.field_8309)
                           ? class_2246.field_10341
                           : (var8.method_31574(class_1802.field_46249) ? class_2246.field_46286 : class_2246.field_10293)
                     )
               );
            if (var8.method_31574(class_1802.field_46249)) {
               if (var5 == null) {
                  var5 = this.xkkvqxmgd5bq8(var1, var3);
               }

               if (var5 == null) {
                  return;
               }
            }

            if (var7.method_9564().method_26184(var1, var3) && WorldEditPolicy.x1bq0f8n53gtob(var1, var3, var7.method_9564(), 3)) {
               if (var5 != null) {
                  this.x35axhpl2cj5i9.add(var5.method_10062());
               }

               var8.method_7934(1);
               var2.method_6104(class_1268.field_5808);
               WorkDiagnostics.x2t98zlq4lu49t(var2, "посадка", 1);
            }
         }
      }
   }

   private int x7lsf0es95maw(class_3218 var1, class_1646 var2, class_2338 var3) {
      if (!x1z1pu5d45jp7l(var1, var3)) {
         return -1;
      } else {
         for (int var4 = 0; var4 < var2.method_35199().method_5439(); var4++) {
            class_1799 var5 = var2.method_35199().method_5438(var4);
            if (!var5.method_7960()
               && x1lanxufahckh0(var5)
               && (!var5.method_31574(class_1802.field_46249) || this.x35axhpl2cj5i9.size() < 2 && this.xkkvqxmgd5bq8(var1, var3) != null)) {
               return var4;
            }
         }

         return -1;
      }
   }

   private static boolean x1z1pu5d45jp7l(class_3218 var0, class_2338 var1) {
      return var0.method_22339(var1) >= 8 || var0.method_8311(var1);
   }

   private class_2338 xkkvqxmgd5bq8(class_3218 var1, class_2338 var2) {
      for (class_2350 var4 : class_2353.field_11062) {
         class_2338 var5 = var2.method_10074().method_10093(var4);
         if (!this.x35axhpl2cj5i9.contains(var5) && x2c1mm5rnqniq6(var1.method_8320(var5)) && x2csm4l72r60h(var1, var5) && this.x14ghv3cndp6s0(var1, var5)) {
            return var5.method_10062();
         }
      }

      return null;
   }

   private boolean x3ihyxa2ovvxin(class_3218 var1, class_1646 var2, class_2338 var3) {
      double var4 = var2.method_24515().method_10262(var3);
      if (var4 <= 9.0) {
         this.x302ihddc8urvz = null;
         return true;
      } else {
         if (!var3.equals(this.x302ihddc8urvz)) {
            this.x302ihddc8urvz = var3;
            this.x2k8pxoh02imeh = var4;
            this.x108884j082kq2 = var1.method_75260();
         }

         if (var4 < this.x2k8pxoh02imeh - 1.0) {
            this.x2k8pxoh02imeh = var4;
            this.x108884j082kq2 = var1.method_75260();
         }

         if (var1.method_75260() - this.x108884j082kq2 <= 200L) {
            VillagerShelter.x2wzylgizxltef(var1, var2, var3, 0.9);
            return false;
         } else {
            this.x1joansufiq3vh.put(var3, var1.method_75260() + 1200L);
            if (this.x35hbh6x2b34v != FarmerPhase.SUPPLY && this.x35hbh6x2b34v != FarmerPhase.RETURN) {
               if (this.x35hbh6x2b34v == FarmerPhase.EXPAND) {
                  this.x1joansufiq3vh.put(this.x2zlk0t7dy583, var1.method_75260() + 1200L);
               }

               this.x35hbh6x2b34v = FarmerPhase.SEARCH;
               this.x186dphgnqvqj8 = null;
               this.x3ge7r6nupqfxf = 0;
               this.x22gyzpic72nbl = 0;
               this.x1k14u7t078rxc();
            } else {
               this.x3uazqikqycd5p = true;
               this.x3kpmopdhvi7xy = var1.method_75260() + 100L;
            }

            this.x302ihddc8urvz = null;
            return false;
         }
      }
   }

   public String debugState() {
      return this.x35hbh6x2b34v == null ? "ожидание" : this.x35hbh6x2b34v.name().toLowerCase(Locale.ROOT);
   }

   private static boolean x1lanxufahckh0(class_1799 var0) {
      return var0.method_31574(class_1802.field_8317)
         || var0.method_31574(class_1802.field_8309)
         || var0.method_31574(class_1802.field_8179)
         || var0.method_31574(class_1802.field_8567)
         || var0.method_31574(class_1802.field_46249);
   }

   private static int x3ek8tyax561ln(class_1646 var0) {
      int var1 = 0;

      for (int var2 = 0; var2 < var0.method_35199().method_5439(); var2++) {
         if (x1lanxufahckh0(var0.method_35199().method_5438(var2))) {
            var1 += var0.method_35199().method_5438(var2).method_7947();
         }
      }

      return var1;
   }

   private static boolean x23na3hzbpcw6h(class_1646 var0, class_1792 var1) {
      for (int var2 = 0; var2 < var0.method_35199().method_5439(); var2++) {
         if (var0.method_35199().method_5438(var2).method_31574(var1)) {
            return true;
         }
      }

      return false;
   }

   private static boolean x2tznyqefzmfni(class_1646 var0) {
      for (int var1 = 0; var1 < var0.method_35199().method_5439(); var1++) {
         if (var0.method_35199().method_5438(var1).method_58694(class_9334.field_50075) != null) {
            return true;
         }
      }

      return false;
   }

   private static boolean x17w4mwk2x8sut(class_1646 var0, class_1792 var1) {
      for (int var2 = 0; var2 < var0.method_35199().method_5439(); var2++) {
         if (var0.method_35199().method_5438(var2).method_31574(var1)) {
            var0.method_35199().method_5438(var2).method_7934(1);
            return true;
         }
      }

      return false;
   }

   private void xfiiclr55ays8(class_3218 var1, class_1646 var2, class_1799 var3) {
      class_1799 var4 = var2.method_35199().method_5491(var3);
      if (!var4.method_7960()) {
         var2.method_5775(var1, var4);
         this.x35hbh6x2b34v = FarmerPhase.RETURN;
      }
   }

   private static boolean xmxmjfdwaicjw(class_3218 var0, class_2338 var1) {
      for (class_2338 var3 : class_2338.method_10097(var1.method_10069(-4, 0, -4), var1.method_10069(4, 1, 4))) {
         if (var0.method_8320(var3).method_27852(class_2246.field_10382)) {
            return true;
         }
      }

      return false;
   }

   private static boolean x3g51t72kitfhf(class_3218 var0, class_2338 var1) {
      if (var0.method_8320(var1).method_27852(class_2246.field_10382) && var0.method_8316(var1).method_15771()) {
         int var2 = 0;

         for (class_2350 var4 : class_2353.field_11062) {
            if (var0.method_8320(var1.method_10093(var4)).method_27852(class_2246.field_10382)) {
               var2++;
            }
         }

         return var2 >= 2;
      } else {
         return false;
      }
   }

   protected void finishRunning(class_3218 var1, class_1646 var2, long var3) {
      ActionTrace.worker(var2, "task_stop", "FarmerTask", false);
      this.x39cq9cwr96dck.xupxzzyodxtwj(var1, var2);
      var2.method_5942().method_6340();
      var2.method_18868().method_18875(ColonyMemories.x2bwom1pasl6g5);
      this.x2t4nzijeau7w8 = null;
      this.x186dphgnqvqj8 = null;
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
