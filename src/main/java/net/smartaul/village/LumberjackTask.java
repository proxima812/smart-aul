package net.smartaul.village;

import com.google.common.collect.ImmutableMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import net.minecraft.class_1268;
import net.minecraft.class_1646;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2382;
import net.minecraft.class_2397;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3341;
import net.minecraft.class_3481;
import net.minecraft.class_3489;
import net.minecraft.class_4097;
import net.minecraft.class_4140;
import net.minecraft.class_4141;
import net.minecraft.class_7923;
import net.minecraft.class_2902.class_2903;

public final class LumberjackTask extends class_4097<class_1646> {
   private Colony x3vq8ve3p9yo05;
   private class_2338 x6y72uqrb3ast;
   private class_2338 x20nwp91vek9gs;
   private final ArrayDeque<class_2338> x3lwz7qzwqov02 = new ArrayDeque<>();
   private int x1fcyn4788lnom;
   private long xlugh0yctpavf;
   private final BlockWorkProgress x2sdf6du2dt880 = new BlockWorkProgress();
   private int x1fyvpgtwlnea1;
   private class_2248 x2ec4pv7n77crf;
   private boolean x36rtxyjcbqu0h;
   private class_2338 x3q8evkcp5s5ah;
   private boolean x1wdryuolkw2ax;
   private class_2338 x1lk82divdauiy;
   private static final List<class_2338> x1mx6rmh8pcz11 = x1m23fzqx9vjd6();

   public LumberjackTask() {
      super(ImmutableMap.of(class_4140.field_18445, class_4141.field_18458), 6000);
   }

   protected boolean shouldRun(class_3218 var1, class_1646 var2) {
      if (!var2.method_6109() && !var2.method_6113() && !var2.method_18009() && WorldEditPolicy.x35ev8l9vc9uk7(var1)) {
         var var3 = ColonyManager.x3psqezd5jem54(var1, var2.method_24515())
            .filter(
               var1x -> var1x.roleOf(var2.method_5667()) == ColonyRole.LUMBERJACK
                  || var1x.roleOf(var2.method_5667()) == ColonyRole.BUILDER && !var1x.x2s37os1kht7vr() && var1x.x2ad3s8aentmo4().isEmpty()
            );
         if (var3.isEmpty()) {
            return false;
         } else if (this.xzivu84hciub6(var2) >= 4) {
            this.x3vq8ve3p9yo05 = (Colony)var3.get();
            this.x6y72uqrb3ast = null;
            this.x20nwp91vek9gs = null;
            this.x3lwz7qzwqov02.clear();
            this.x36rtxyjcbqu0h = true;
            this.x3q8evkcp5s5ah = null;
            return true;
         } else {
            var var4 = this.x1w0zwh8g6ezr3(var1, (Colony)var3.get(), var2);
            if (var4.isEmpty()) {
               return false;
            } else {
               this.x3vq8ve3p9yo05 = (Colony)var3.get();
               this.x6y72uqrb3ast = (class_2338)var4.get();
               this.x3lwz7qzwqov02.clear();
               this.x3lwz7qzwqov02.addAll(xacevez0qzktk(var1, this.x6y72uqrb3ast));
               this.x20nwp91vek9gs = this.x3lwz7qzwqov02.pollFirst();
               this.x1fyvpgtwlnea1 = 0;
               this.x36rtxyjcbqu0h = false;
               this.x3q8evkcp5s5ah = null;
               this.x2ec4pv7n77crf = this.x4sv44f0zvtt5(var1.method_8320(this.x6y72uqrb3ast));
               var2.method_18868().method_18878(ColonyMemories.x1h4kjv4hra8oe, this.x6y72uqrb3ast);
               return true;
            }
         }
      } else {
         return false;
      }
   }

   protected boolean shouldKeepRunning(class_3218 var1, class_1646 var2, long var3) {
      return !var2.method_6113()
         && !var2.method_18009()
         && this.x3vq8ve3p9yo05 != null
         && (
            this.x3vq8ve3p9yo05.roleOf(var2.method_5667()) == ColonyRole.LUMBERJACK
               || this.x3vq8ve3p9yo05.roleOf(var2.method_5667()) == ColonyRole.BUILDER
                  && !this.x3vq8ve3p9yo05.x2s37os1kht7vr()
                  && this.x3vq8ve3p9yo05.x2ad3s8aentmo4().isEmpty()
         )
         && (this.x36rtxyjcbqu0h || this.x20nwp91vek9gs != null);
   }

   protected void run(class_3218 var1, class_1646 var2, long var3) {
      ActionTrace.worker(var2, "task_start", "LumberjackTask", false);
      this.xlugh0yctpavf = var3;
      this.x1wdryuolkw2ax = !var2.method_6047().method_31573(class_3489.field_42612) && !this.x3vq8ve3p9yo05.x2ad3s8aentmo4().isEmpty();
   }

   protected void keepRunning(class_3218 var1, class_1646 var2, long var3) {
      if (var3 >= this.xlugh0yctpavf) {
         if (WorldEditPolicy.x35ev8l9vc9uk7(var1)) {
            if (this.x1wdryuolkw2ax) {
               var var5 = ColonyManager.x3jrfw2d3yykeu(var1, this.x3vq8ve3p9yo05, var2, HousePurpose.WOOD);
               if (var5.isPresent() && var2.method_24515().method_10262((class_2382)var5.get()) > 9.0) {
                  VillagerShelter.x2wzylgizxltef(
                     var1,
                     var2,
                     class_2338.method_49637(
                        ((class_2338)var5.get()).method_10263() + 0.5, ((class_2338)var5.get()).method_10264(), ((class_2338)var5.get()).method_10260() + 0.5
                     ),
                     0.8
                  );
                  this.xlugh0yctpavf = var3 + 10L;
                  return;
               }

               VillagerInventory.x31tan2efb24l6(var1, this.x3vq8ve3p9yo05, var2, class_3489.field_42612, class_1802.field_8406, class_1802.field_8062);
               this.x1wdryuolkw2ax = false;
            }

            if (this.x36rtxyjcbqu0h) {
               this.x2jcnu6th3z2yp(var1, var2, var3);
            } else {
               while (this.x20nwp91vek9gs != null && !var1.method_8320(this.x20nwp91vek9gs).method_26164(class_3481.field_15475)) {
                  this.x20nwp91vek9gs = this.x3lwz7qzwqov02.pollFirst();
               }

               if (this.x20nwp91vek9gs == null) {
                  this.x1n88l7ttiksvi(var1, var2);
                  this.x36rtxyjcbqu0h = true;
               } else {
                  class_2680 var10 = var1.method_8320(this.x20nwp91vek9gs);
                  if (!WorldEditPolicy.x13f7be5vlapn7(var1, this.x20nwp91vek9gs)) {
                     this.x20nwp91vek9gs = this.x3lwz7qzwqov02.pollFirst();
                     this.xlugh0yctpavf = var3 + 2L;
                  } else {
                     double var6 = var2.method_5649(this.x6y72uqrb3ast.method_10263() + 0.5, var2.method_23318(), this.x6y72uqrb3ast.method_10260() + 0.5);
                     if (var6 > 25.0) {
                        VillagerShelter.x2wzylgizxltef(
                           var1,
                           var2,
                           class_2338.method_49637(
                              this.x6y72uqrb3ast.method_10263() + 0.5, this.x6y72uqrb3ast.method_10264(), this.x6y72uqrb3ast.method_10260() + 0.5
                           ),
                           0.6
                        );
                     } else {
                        class_1799 var8 = new class_1799(var10.method_26204().method_8389());
                        if (this.x2sdf6du2dt880
                           .xpohy1q30uxlk(var1, var2, this.x20nwp91vek9gs, BlockWorkProgress.x20ocn113pbk5f(var1, var2, this.x20nwp91vek9gs), true)) {
                           if (WorldEditPolicy.x231if4rvsvptj(var1, this.x20nwp91vek9gs, false, var2)) {
                              class_1799 var9 = var2.method_35199().method_5491(var8);
                              if (!var9.method_7960()) {
                                 var2.method_5775(var1, var9);
                              }

                              this.x1fyvpgtwlnea1++;
                              var2.method_6104(class_1268.field_5808);
                              this.x20nwp91vek9gs = this.x3lwz7qzwqov02.pollFirst();
                              if (this.x20nwp91vek9gs == null) {
                                 this.x1n88l7ttiksvi(var1, var2);
                                 this.x36rtxyjcbqu0h = true;
                              }
                           }

                           this.xlugh0yctpavf = var3 + 1L;
                        }
                     }
                  }
               }
            }
         }
      }
   }

   protected void finishRunning(class_3218 var1, class_1646 var2, long var3) {
      ActionTrace.worker(var2, "task_stop", "LumberjackTask", false);
      this.x2sdf6du2dt880.xupxzzyodxtwj(var1, var2);
      var2.method_5942().method_6340();
      this.x3vq8ve3p9yo05 = null;
      this.x6y72uqrb3ast = null;
      this.x20nwp91vek9gs = null;
      this.x3lwz7qzwqov02.clear();
      this.x3q8evkcp5s5ah = null;
      var2.method_18868().method_18875(ColonyMemories.x1h4kjv4hra8oe);
   }

   private Optional<class_2338> x1w0zwh8g6ezr3(class_3218 var1, Colony var2, class_1646 var3) {
      class_2338 var4 = var3.method_24515();
      int var5 = Math.min(var2.radius() - 4, 96);
      if (this.x1lk82divdauiy == null || this.x1lk82divdauiy.method_10262(var4) > 256.0 || this.x1fcyn4788lnom >= x1mx6rmh8pcz11.size()) {
         this.x1lk82divdauiy = var4.method_10062();
         this.x1fcyn4788lnom = 0;
      }

      for (int var6 = 0; var6 < 1024 && this.x1fcyn4788lnom < x1mx6rmh8pcz11.size(); var6++) {
         class_2338 var7 = x1mx6rmh8pcz11.get(this.x1fcyn4788lnom++);
         int var8 = this.x1lk82divdauiy.method_10263() + var7.method_10263();
         int var9 = this.x1lk82divdauiy.method_10260() + var7.method_10260();
         double var10 = (double)var8 - var2.core().method_10263();
         double var12 = (double)var9 - var2.core().method_10260();
         if (!(var10 * var10 + var12 * var12 > (double)var5 * var5) && var1.method_14178().method_12123(var8 >> 4, var9 >> 4)) {
            int var14 = var1.method_8624(class_2903.field_13197, var8, var9) - 1;

            for (int var15 = var14; var15 >= var14 - 40; var15--) {
               class_2338 var16 = new class_2338(var8, var15, var9);
               if (var1.method_8320(var16).method_26164(class_3481.field_15475)) {
                  var var17 = x1cm237nywz8lg(var1, var16);
                  if (var17.isPresent()
                     && !VillagerShelter.xps35ehel6nkk(var3, (class_2338)var17.get(), var1.method_75260())
                     && !ColonyWorldData.get(var1).isPlayerBlock((class_2338)var17.get())
                     && isNaturalTree(var1, var2, (class_2338)var17.get())) {
                     return var17;
                  }
               }
            }
         }
      }

      return Optional.empty();
   }

   private static List<class_2338> x1m23fzqx9vjd6() {
      var var0 = new ArrayList<class_2338>();

      for (int var1 = -96; var1 <= 96; var1++) {
         for (int var2 = -96; var2 <= 96; var2++) {
            if (var1 * var1 + var2 * var2 <= 9216) {
               var0.add(new class_2338(var1, 0, var2));
            }
         }
      }

      var0.sort(Comparator.comparingInt(var0x -> var0x.method_10263() * var0x.method_10263() + var0x.method_10260() * var0x.method_10260()));
      return List.copyOf(var0);
   }

   private static Optional<class_2338> x1cm237nywz8lg(class_3218 var0, class_2338 var1) {
      return x3plp61tcex66x(var0, var1, true)
         .stream()
         .filter(var1x -> x25k1zpzqhtmjn(var0, var1x.method_10074()))
         .min(Comparator.comparingInt(class_2382::method_10264).thenComparingDouble(var1x -> var1x.method_10262(var1)));
   }

   private static List<class_2338> xacevez0qzktk(class_3218 var0, class_2338 var1) {
      var var2 = x3plp61tcex66x(var0, var1, false);
      var2.sort(Comparator.comparingInt(class_2382::method_10264).thenComparingDouble(var1x -> var1x.method_10262(var1)));
      return var2;
   }

   private static List<class_2338> x3plp61tcex66x(class_3218 var0, class_2338 var1, boolean var2) {
      var var3 = new ArrayDeque<>();
      var var4 = new HashSet<>();
      var var5 = new ArrayList<class_2338>();
      var3.add(var1.method_10062());
      var4.add(var1.method_10062());

      while (!var3.isEmpty() && var5.size() < 256) {
         class_2338 var6 = (class_2338)var3.removeFirst();
         if (var0.method_8320(var6).method_26164(class_3481.field_15475)
            && Math.abs(var6.method_10263() - var1.method_10263()) <= 10
            && Math.abs(var6.method_10260() - var1.method_10260()) <= 10
            && var6.method_10264() >= (var2 ? var1.method_10264() - 40 : var1.method_10264())
            && var6.method_10264() <= var1.method_10264() + 40) {
            var5.add(var6);

            for (int var7 = -1; var7 <= 1; var7++) {
               for (int var8 = -1; var8 <= 1; var8++) {
                  for (int var9 = -1; var9 <= 1; var9++) {
                     if (var7 != 0 || var8 != 0 || var9 != 0) {
                        class_2338 var10 = var6.method_10069(var7, var8, var9).method_10062();
                        if (var4.add(var10)) {
                           var3.addLast(var10);
                        }
                     }
                  }
               }
            }
         }
      }

      return var5;
   }

   public static boolean isNaturalTree(class_3218 var0, Colony var1, class_2338 var2) {
      if (!x25k1zpzqhtmjn(var0, var2.method_10074())) {
         return false;
      } else {
         class_2680 var3 = var0.method_8320(var2);
         String var4 = class_7923.field_41175.method_10221(var3.method_26204()).method_12832();
         if (var4.endsWith("_log") && !var4.startsWith("stripped_")) {
            var var5 = xacevez0qzktk(var0, var2);
            if (var5.size() < 3) {
               return false;
            } else {
               int var6 = var5.stream().mapToInt(class_2382::method_10264).max().orElse(var2.method_10264()) - var2.method_10264() + 1;
               if (var6 >= 3 && var6 <= 40) {
                  for (ConstructionJob var8 : var1.x1ja61emeln2hc()) {
                     class_3341 var9 = var8.x24kbqv6g4kkrg();
                     if (var2.method_10263() >= var9.method_35415() - 5
                        && var2.method_10263() <= var9.method_35418() + 5
                        && var2.method_10260() >= var9.method_35417() - 5
                        && var2.method_10260() <= var9.method_35420() + 5) {
                        return false;
                     }
                  }

                  int var13 = 0;

                  for (class_2338 var16 : class_2338.method_10097(var2.method_10069(-4, 0, -4), var2.method_10069(4, var6 + 3, 4))) {
                     if (!var0.method_14178().method_12123(var16.method_10263() >> 4, var16.method_10260() >> 4)) {
                        return false;
                     }

                     class_2680 var10 = var0.method_8320(var16);
                     if (var10.method_26164(class_3481.field_15503)
                        && (!var10.method_28498(class_2397.field_11200) || !(Boolean)var10.method_11654(class_2397.field_11200))) {
                        var13++;
                     }
                  }

                  for (class_2338 var17 : var5) {
                     if (var17.method_10264() <= var2.method_10264() + 3) {
                        for (class_2338 var11 : class_2338.method_10097(var17.method_10069(-1, -1, -1), var17.method_10069(1, 1, 1))) {
                           if (!var11.equals(var17)) {
                              class_2680 var12 = var0.method_8320(var11);
                              if (var12.method_26164(class_3481.field_15471)
                                 || var12.method_26164(class_3481.field_16443)
                                 || var12.method_26164(class_3481.field_15495)
                                 || var12.method_27852(class_2246.field_10445)
                                 || var12.method_27852(class_2246.field_10056)
                                 || var0.method_8321(var11) != null) {
                                 return false;
                              }
                           }
                        }
                     }
                  }

                  return var13 >= 8;
               } else {
                  return false;
               }
            }
         } else {
            return false;
         }
      }
   }

   private static boolean x25k1zpzqhtmjn(class_3218 var0, class_2338 var1) {
      class_2680 var2 = var0.method_8320(var1);
      return var2.method_26164(class_3481.field_29822) || var2.method_27852(class_2246.field_37546) || var2.method_27852(class_2246.field_37547);
   }

   private void x1n88l7ttiksvi(class_3218 var1, class_1646 var2) {
      if (this.x1fyvpgtwlnea1 > 0) {
         class_1799 var3 = new class_1799(class_1802.field_8600, Math.max(1, this.x1fyvpgtwlnea1 / 2));
         var2.method_35199().method_5491(var3);
      }

      if (this.x1fyvpgtwlnea1 > 0 && this.x2ec4pv7n77crf != null) {
         boolean var13 = this.x2ec4pv7n77crf == class_2246.field_10160 || this.x2ec4pv7n77crf == class_2246.field_54712;
         int var4 = this.x3ja1z9v4hxh83(var1, this.x6y72uqrb3ast, 12);
         double var5 = this.x6y72uqrb3ast.method_10262(this.x3vq8ve3p9yo05.core());
         int var7 = !(var5 < 1024.0) && var4 < 3 ? (var4 >= 1 ? 2 : 3) : 1;
         if (var5 < 3136.0) {
            int var8 = this.xtgy4dce41scv(var1, 10);
            if (var8 >= 10) {
               return;
            }

            var7 = Math.min(var7, 10 - var8);
         }

         if (var13) {
            if (var5 < 3136.0 && this.xtgy4dce41scv(var1, 7) > 6) {
               return;
            }

            var7 = 4;
         }

         var var14 = var13
            ? List.of(
               this.x6y72uqrb3ast, this.x6y72uqrb3ast.method_10078(), this.x6y72uqrb3ast.method_10072(), this.x6y72uqrb3ast.method_10072().method_10078()
            )
            : List.of(
               this.x6y72uqrb3ast,
               this.x6y72uqrb3ast.method_10089(4),
               this.x6y72uqrb3ast.method_10088(4),
               this.x6y72uqrb3ast.method_10077(4),
               this.x6y72uqrb3ast.method_10076(4),
               this.x6y72uqrb3ast.method_10069(3, 0, 3),
               this.x6y72uqrb3ast.method_10069(-3, 0, 3),
               this.x6y72uqrb3ast.method_10069(3, 0, -3),
               this.x6y72uqrb3ast.method_10069(-3, 0, -3)
            );
         int var9 = 0;
         if (!var13 || !var14.stream().anyMatch(var2x -> !this.x3q7mzgphtw30p(var1, var2x) || !this.x2ec4pv7n77crf.method_9564().method_26184(var1, var2x))) {
            for (class_2338 var11 : var14) {
               if (var9 >= var7) {
                  break;
               }

               if (this.x3q7mzgphtw30p(var1, var11)) {
                  class_2680 var12 = this.x2ec4pv7n77crf.method_9564();
                  if (var12.method_26184(var1, var11) && WorldEditPolicy.x1bq0f8n53gtob(var1, var11, var12, 3)) {
                     var9++;
                  }
               }
            }

            if (var9 > 0) {
               var2.method_6104(class_1268.field_5808);
            }
         }
      }
   }

   private int xtgy4dce41scv(class_3218 var1, int var2) {
      int var3 = 0;

      for (int var4 = -48; var4 <= 48; var4++) {
         for (int var5 = -48; var5 <= 48; var5++) {
            if (var4 * var4 + var5 * var5 <= 2304) {
               int var6 = this.x3vq8ve3p9yo05.core().method_10263() + var4;
               int var7 = this.x3vq8ve3p9yo05.core().method_10260() + var5;
               if (var1.method_14178().method_12123(var6 >> 4, var7 >> 4)) {
                  int var8 = var1.method_8624(class_2903.field_13203, var6, var7);

                  for (int var9 = var8; var9 >= Math.max(var1.method_31607(), var8 - 32); var9--) {
                     if (var1.method_8320(new class_2338(var6, var9, var7)).method_26164(class_3481.field_15462)) {
                        if (++var3 >= var2) {
                           return var3;
                        }
                     }
                  }
               }
            }
         }
      }

      return var3;
   }

   private int x3ja1z9v4hxh83(class_3218 var1, class_2338 var2, int var3) {
      int var4 = 0;

      for (int var5 = -20; var5 <= 20; var5++) {
         for (int var6 = -20; var6 <= 20; var6++) {
            if (var5 * var5 + var6 * var6 <= 400) {
               int var7 = var2.method_10263() + var5;
               int var8 = var2.method_10260() + var6;
               if (var1.method_14178().method_12123(var7 >> 4, var8 >> 4)) {
                  int var9 = var1.method_8624(class_2903.field_13197, var7, var8);

                  for (int var10 = var9; var10 >= Math.max(var1.method_31607(), var9 - 24); var10--) {
                     class_2338 var11 = new class_2338(var7, var10, var8);
                     class_2680 var12 = var1.method_8320(var11);
                     if (var12.method_26164(class_3481.field_15462) || var12.method_26164(class_3481.field_15475) && x25k1zpzqhtmjn(var1, var11.method_10074())
                        )
                      {
                        var4++;
                        break;
                     }
                  }

                  if (var4 >= var3) {
                     return var4;
                  }
               }
            }
         }
      }

      return var4;
   }

   private boolean x3q7mzgphtw30p(class_3218 var1, class_2338 var2) {
      if (!BuildingSiteValidator.x14gj5yhqspcfw(var1, this.x3vq8ve3p9yo05, var2.method_10074())) {
         return false;
      } else if (WorldEditPolicy.x13f7be5vlapn7(var1, var2) && !ColonyWorldData.get(var1).isPlayerBlock(var2.method_10074())) {
         class_2680 var3 = var1.method_8320(var2);
         if (!var3.method_26215() && !var3.method_45474()) {
            return false;
         } else if (var1.method_8320(var2.method_10084()).method_26215() && var1.method_8320(var2.method_10086(2)).method_26215()) {
            class_2680 var4 = var1.method_8320(var2.method_10074());
            if (var4.method_26164(class_3481.field_29822) && !var4.method_27852(class_2246.field_10194) && !var4.method_27852(class_2246.field_10362)) {
               for (ConstructionJob var6 : this.x3vq8ve3p9yo05.x1ja61emeln2hc()) {
                  class_3341 var7 = var6.x24kbqv6g4kkrg();
                  if (var2.method_10263() >= var7.method_35415() - 3
                     && var2.method_10263() <= var7.method_35418() + 3
                     && var2.method_10260() >= var7.method_35417() - 3
                     && var2.method_10260() <= var7.method_35420() + 3) {
                     return false;
                  }
               }

               return true;
            } else {
               return false;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private void x2jcnu6th3z2yp(class_3218 var1, class_1646 var2, long var3) {
      boolean var5 = this.x3vq8ve3p9yo05.x2ad3s8aentmo4().isEmpty() || ColonyManager.x11dwzrdveisv7(var1, this.x3vq8ve3p9yo05) <= 4;
      var var6 = !var5
            && ColonyManager.x57l6vd1sx1rt(
               var1, this.x3vq8ve3p9yo05, var0 -> var0.method_31573(class_3489.field_15539) || var0.method_31574(class_1802.field_8600)
            )
         ? ColonyManager.x3jrfw2d3yykeu(var1, this.x3vq8ve3p9yo05, var2, HousePurpose.WOOD)
         : Optional.empty();
      if (var6.isEmpty()) {
         if (this.xzivu84hciub6(var2) < 4) {
            this.x36rtxyjcbqu0h = false;
            return;
         }

         if (this.x3q8evkcp5s5ah == null) {
            this.x3q8evkcp5s5ah = ColonyManager.x2l4eb2ibsf118(var1, this.x3vq8ve3p9yo05).orElse(null);
         }

         if (this.x3q8evkcp5s5ah == null) {
            this.x36rtxyjcbqu0h = false;
            return;
         }

         var2.method_18868().method_18878(ColonyMemories.x3o547clcv9kaw, this.x3q8evkcp5s5ah);
         if (var2.method_5649(this.x3q8evkcp5s5ah.method_10263() + 0.5, this.x3q8evkcp5s5ah.method_10264() + 0.5, this.x3q8evkcp5s5ah.method_10260() + 0.5)
            > 9.0) {
            VillagerShelter.x2wzylgizxltef(
               var1,
               var2,
               class_2338.method_49637(this.x3q8evkcp5s5ah.method_10263() + 0.5, this.x3q8evkcp5s5ah.method_10264(), this.x3q8evkcp5s5ah.method_10260() + 0.5),
               0.6
            );
            return;
         }

         class_2338 var7 = this.x3q8evkcp5s5ah.method_10078();
         if (!var1.method_8320(this.x3q8evkcp5s5ah).method_26215() || !var1.method_8320(var7).method_26215() || this.xzivu84hciub6(var2) < 4) {
            this.x3q8evkcp5s5ah = null;
            return;
         }

         if (!ColonyManager.xt55z8grtuvpq(var1, this.x3vq8ve3p9yo05, this.x3q8evkcp5s5ah, HousePurpose.WOOD)) {
            this.x3q8evkcp5s5ah = null;
            return;
         }

         this.xkldxdn7jvvus(var2, 4);
         ColonyManager.xchl1k1tnh3w8(var1).method_80();
         var2.method_6104(class_1268.field_5808);
         var6 = Optional.of(this.x3q8evkcp5s5ah);
      }

      class_2338 var10 = (class_2338)var6.get();
      if (var2.method_5649(var10.method_10263() + 0.5, var10.method_10264() + 0.5, var10.method_10260() + 0.5) > 9.0) {
         VillagerShelter.x2wzylgizxltef(var1, var2, class_2338.method_49637(var10.method_10263() + 0.5, var10.method_10264(), var10.method_10260() + 0.5), 0.6);
      } else {
         for (int var8 = 0; var8 < var2.method_35199().method_5439(); var8++) {
            class_1799 var9 = var2.method_35199().method_5438(var8);
            if (var9.method_31573(class_3489.field_15539) || var9.method_31574(class_1802.field_8600)) {
               WorkDiagnostics.x25cotfj45r525(var1, this.x3vq8ve3p9yo05, var2, var9);
            }
         }

         var2.method_6104(class_1268.field_5808);
         this.x36rtxyjcbqu0h = false;
         this.xlugh0yctpavf = var3 + 20L;
      }
   }

   private int xzivu84hciub6(class_1646 var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.method_35199().method_5439(); var3++) {
         class_1799 var4 = var1.method_35199().method_5438(var3);
         if (var4.method_31573(class_3489.field_15539)) {
            var2 += var4.method_7947();
         }
      }

      return var2;
   }

   private boolean xkldxdn7jvvus(class_1646 var1, int var2) {
      if (this.xzivu84hciub6(var1) < var2) {
         return false;
      } else {
         int var3 = var2;

         for (int var4 = 0; var4 < var1.method_35199().method_5439() && var3 > 0; var4++) {
            class_1799 var5 = var1.method_35199().method_5438(var4);
            if (var5.method_31573(class_3489.field_15539)) {
               int var6 = Math.min(var3, var5.method_7947());
               var5.method_7934(var6);
               var3 -= var6;
            }
         }

         return true;
      }
   }

   private class_2248 x4sv44f0zvtt5(class_2680 var1) {
      String var2 = class_7923.field_41175.method_10221(var1.method_26204()).method_12832();
      if (var2.contains("dark_oak")) {
         return class_2246.field_10160;
      } else if (var2.contains("pale_oak")) {
         return class_2246.field_54712;
      } else if (var2.contains("spruce")) {
         return class_2246.field_10217;
      } else if (var2.contains("birch")) {
         return class_2246.field_10575;
      } else if (var2.contains("acacia")) {
         return class_2246.field_10385;
      } else if (var2.contains("jungle")) {
         return class_2246.field_10276;
      } else if (var2.contains("mangrove")) {
         return class_2246.field_37544;
      } else {
         return var2.contains("cherry") ? class_2246.field_42727 : class_2246.field_10394;
      }
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
