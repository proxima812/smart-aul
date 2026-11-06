package net.smartcolonies.r;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import net.minecraft.class_1268;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1439;
import net.minecraft.class_1646;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2398;
import net.minecraft.class_3218;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3730;
import net.minecraft.class_2350.class_2353;
import net.minecraft.class_2902.class_2903;

public final class xt78n188f3w97 {
   public static final int x17pu6o7z48nge = 4;
   private class_1439 x2opxfmx0yhl47;
   private class_2338 x2cyyiai9sia93;
   private class_2338 xyqlhatpmatxy;
   private long x26e0wozd7vxgm;

   private static List<class_1439> x37pu9d7f43xig(class_3218 var0, x15kgoaq6i3m6i var1) {
      return var0.method_8390(class_1439.class, new class_238(var1.core()).method_1014(var1.radius()), class_1309::method_5805);
   }

   public static int x1gfvg3f4hcsus(class_3218 var0, x15kgoaq6i3m6i var1) {
      return x37pu9d7f43xig(var0, var1).size();
   }

   public static int x20ln0j5i9gefl(x15kgoaq6i3m6i var0) {
      return Math.max(4, (var0.population() + 5) / 6);
   }

   private static long x2lwwcte00kkej(class_3218 var0) {
      return var0.method_8503().method_3780();
   }

   public static boolean x3pk6ywniogk9m(class_3218 var0, x15kgoaq6i3m6i var1) {
      return x37pu9d7f43xig(var0, var1).stream().anyMatch(var0x -> var0x.method_6032() < var0x.method_6063())
         || x2lwwcte00kkej(var0) >= var1.x1ljiw4h4dmxfs() && x37pu9d7f43xig(var0, var1).size() < x20ln0j5i9gefl(var1);
   }

   public static boolean x2pesumrvfap2d(class_3218 var0, x15kgoaq6i3m6i var1, List<class_1646> var2) {
      if (x2lwwcte00kkej(var0) >= var1.x1ljiw4h4dmxfs() && x1gfvg3f4hcsus(var0, var1) < x20ln0j5i9gefl(var1)) {
         class_1646 var3 = var2.stream()
            .filter(var1x -> var1x.method_5805() && !var1x.method_6109() && var1.roleOf(var1x.method_5667()) == x1rxiy9mtu5b3h.GUARD)
            .min(Comparator.comparingDouble(var1x -> var1x.method_24515().method_10262(var1.core())))
            .orElse(null);
         if (var3 != null && x2n55y2tom37h2.x1tl8iu49onump(var0, var1, xt78n188f3w97::x323q5u6pfg7wx)) {
            x2aesxzdpwjel5(var0, var1, 36);
            if (x2n55y2tom37h2.x1mlne8tl8k5ag(var0, var1, var0x -> var0x.method_31574(class_1802.field_8620)) < 36) {
               return false;
            } else {
               class_2338 var4 = null;

               for (int var5 = 3; var5 <= 12 && var4 == null; var5++) {
                  for (class_2350 var7 : class_2353.field_11062) {
                     int var8 = var3.method_31477() + var7.method_10148() * var5;
                     int var9 = var3.method_31479() + var7.method_10165() * var5;
                     if (var0.method_14178().method_12123(var8 >> 4, var9 >> 4)) {
                        class_2338 var10 = new class_2338(var8, var0.method_8624(class_2903.field_13203, var8, var9), var9);
                        if (x2tryg7j1x110u(var0, var10) && !var1.xhu9xvlie05r6(var10, 3)) {
                           var4 = var10;
                        }
                     }
                  }
               }

               if (var4 == null) {
                  return false;
               } else {
                  class_1439 var11 = (class_1439)class_1299.field_6147.method_5883(var0, class_3730.field_16471);
                  if (var11 == null) {
                     return false;
                  } else {
                     var11.method_5814(var4.method_10263() + 0.5, var4.method_10264(), var4.method_10260() + 0.5);
                     var11.method_6499(false);
                     var11.method_5971();
                     if (!var0.method_8649(var11)) {
                        return false;
                     } else {
                        x2n55y2tom37h2.x22hjebj4vmztt(var0, var1, var0x -> var0x.method_31574(class_1802.field_8620), 36);
                        x2n55y2tom37h2.x22hjebj4vmztt(var0, var1, xt78n188f3w97::x323q5u6pfg7wx, 1);
                        var3.method_6104(class_1268.field_5808);
                        var0.method_65096(
                           class_2398.field_11203, var4.method_10263() + 0.5, var4.method_10264() + 1, var4.method_10260() + 0.5, 12, 0.8, 1.0, 0.8, 0.02
                        );
                        var1.x163kb3edimpm3(x2lwwcte00kkej(var0) + 300L);
                        x2n55y2tom37h2.xchl1k1tnh3w8(var0).method_80();
                        return true;
                     }
                  }
               }
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public void x3ku3wcoma0qbb() {
      this.x2opxfmx0yhl47 = null;
      this.x2cyyiai9sia93 = null;
      this.xyqlhatpmatxy = null;
   }

   public boolean xhboo89lslsdy() {
      return this.x2opxfmx0yhl47 != null || this.x2cyyiai9sia93 != null;
   }

   public boolean xl4620yflyi80(class_3218 var1, x15kgoaq6i3m6i var2, class_1646 var3) {
      this.x2opxfmx0yhl47 = x37pu9d7f43xig(var1, var2)
         .stream()
         .filter(var0 -> var0.method_6032() < var0.method_6063())
         .min(Comparator.comparingDouble(var1x -> var1x.method_5858(var3)))
         .orElse(null);
      if (this.x2opxfmx0yhl47 != null) {
         if (x1kf3zgkco82h3(var3) == null) {
            if (!x96n4hutou332(var3)) {
               this.x2opxfmx0yhl47 = null;
               return false;
            }

            x2aesxzdpwjel5(var1, var2, 1);
            var var15 = x2n55y2tom37h2.x3lxwdirnn77qc(var1, var2, var0 -> var0.method_31574(class_1802.field_8620));
            if (var15.isEmpty()) {
               this.x2opxfmx0yhl47 = null;
               return false;
            }

            var3.method_35199().method_5491((class_1799)var15.get());
         }

         return true;
      } else if (x2lwwcte00kkej(var1) >= var2.x1ljiw4h4dmxfs() && x37pu9d7f43xig(var1, var2).size() < x20ln0j5i9gefl(var2)) {
         if (!x2n55y2tom37h2.x1tl8iu49onump(var1, var2, xt78n188f3w97::x323q5u6pfg7wx)) {
            return false;
         } else {
            x2aesxzdpwjel5(var1, var2, 36);
            if (x2n55y2tom37h2.x1mlne8tl8k5ag(var1, var2, var0 -> var0.method_31574(class_1802.field_8620)) < 36) {
               return false;
            } else {
               class_2338 var4 = null;
               double var5 = Double.MAX_VALUE;

               for (int var7 = -32; var7 <= 32; var7++) {
                  for (int var8 = -32; var8 <= 32; var8++) {
                     if (var7 * var7 + var8 * var8 >= 16 && var7 * var7 + var8 * var8 <= 1024) {
                        int var9 = var2.core().method_10263() + var7;
                        int var10 = var2.core().method_10260() + var8;
                        if (var1.method_14178().method_12123(var9 >> 4, var10 >> 4)) {
                           class_2338 var11 = new class_2338(var9, var1.method_8624(class_2903.field_13203, var9, var10), var10);
                           class_2338 var12 = x3mty4h6w5e7b3(var1, var11);
                           if (x2tryg7j1x110u(var1, var11) && var12 != null && !var2.xhu9xvlie05r6(var11, 3)) {
                              double var13 = var11.method_10262(var3.method_24515()) + 0.2 * var11.method_10262(var2.core());
                              if (var13 < var5) {
                                 var4 = var11.method_10062();
                                 this.xyqlhatpmatxy = var12;
                                 var5 = var13;
                              }
                           }
                        }
                     }
                  }
               }

               if (var4 != null) {
                  this.x2cyyiai9sia93 = var4;
                  this.x26e0wozd7vxgm = var1.method_75260() + 60L;
                  return true;
               } else {
                  return false;
               }
            }
         }
      } else {
         return false;
      }
   }

   public boolean x21yp5lsgxd9co(class_3218 var1, x15kgoaq6i3m6i var2, class_1646 var3, long var4) {
      if (this.x2opxfmx0yhl47 != null) {
         if (!this.x2opxfmx0yhl47.method_5805() || this.x2opxfmx0yhl47.method_6032() >= this.x2opxfmx0yhl47.method_6063()) {
            this.x3ku3wcoma0qbb();
            return true;
         } else if (var3.method_5858(this.x2opxfmx0yhl47) > 9.0) {
            if (var4 % 10L == 0L && x2n55y2tom37h2.x5ll9elqpk1a4(var1)) {
               var3.method_5942().method_6335(this.x2opxfmx0yhl47, 0.8);
            }

            return false;
         } else {
            class_1799 var7 = x1kf3zgkco82h3(var3);
            if (var7 != null) {
               this.x2opxfmx0yhl47.method_6025(25.0F);
               var7.method_7934(1);
               var3.method_6104(class_1268.field_5808);
               var1.method_8396(null, this.x2opxfmx0yhl47.method_24515(), class_3417.field_21077, class_3419.field_15254, 1.0F, 1.0F);
            }

            this.x3ku3wcoma0qbb();
            return true;
         }
      } else if (this.x2cyyiai9sia93 != null) {
         if (!x2tryg7j1x110u(var1, this.x2cyyiai9sia93)) {
            this.x3ku3wcoma0qbb();
            return true;
         } else if (var3.method_24515().method_10262(this.xyqlhatpmatxy) > 4.0) {
            this.x26e0wozd7vxgm = var4 + 60L;
            if (var4 % 10L == 0L && x2n55y2tom37h2.x5ll9elqpk1a4(var1)) {
               x1c1r5c6a13gtl.x2wzylgizxltef(var1, var3, this.xyqlhatpmatxy, 0.8);
            }

            return false;
         } else {
            var3.method_5942().method_6340();
            if (var4 % 15L == 0L) {
               var3.method_6104(class_1268.field_5808);
            }

            if (var4 < this.x26e0wozd7vxgm) {
               return false;
            } else if (x2lwwcte00kkej(var1) >= var2.x1ljiw4h4dmxfs()
               && x37pu9d7f43xig(var1, var2).size() < x20ln0j5i9gefl(var2)
               && x2n55y2tom37h2.x1mlne8tl8k5ag(var1, var2, var0 -> var0.method_31574(class_1802.field_8620)) >= 36
               && x2n55y2tom37h2.x1tl8iu49onump(var1, var2, xt78n188f3w97::x323q5u6pfg7wx)) {
               class_1439 var6 = (class_1439)class_1299.field_6147.method_5883(var1, class_3730.field_16471);
               if (var6 != null) {
                  var6.method_5814(this.x2cyyiai9sia93.method_10263() + 0.5, this.x2cyyiai9sia93.method_10264(), this.x2cyyiai9sia93.method_10260() + 0.5);
                  var6.method_6499(false);
                  var6.method_5971();
                  if (var1.method_8649(var6)) {
                     x2n55y2tom37h2.x22hjebj4vmztt(var1, var2, var0 -> var0.method_31574(class_1802.field_8620), 36);
                     x2n55y2tom37h2.x22hjebj4vmztt(var1, var2, xt78n188f3w97::x323q5u6pfg7wx, 1);
                     var2.x163kb3edimpm3(x2lwwcte00kkej(var1) + 200L);
                     x2n55y2tom37h2.xchl1k1tnh3w8(var1).method_80();
                  }
               }

               this.x3ku3wcoma0qbb();
               return true;
            } else {
               this.x3ku3wcoma0qbb();
               return true;
            }
         }
      } else {
         return true;
      }
   }

   private static boolean x2tryg7j1x110u(class_3218 var0, class_2338 var1) {
      for (int var2 = 0; var2 <= 1; var2++) {
         for (int var3 = 0; var3 <= 1; var3++) {
            class_2338 var4 = var1.method_10069(var2, -1, var3);
            if (!var0.method_14178().method_12123(var4.method_10263() >> 4, var4.method_10260() >> 4)) {
               return false;
            }

            if (!var0.method_8320(var4).method_26206(var0, var4, class_2350.field_11036) || !var0.method_8316(var4).method_15769()) {
               return false;
            }

            for (int var5 = 0; var5 < 3; var5++) {
               if (!var0.method_8320(var1.method_10069(var2, var5, var3)).method_26215()) {
                  return false;
               }
            }
         }
      }

      return var0.method_8335(null, new class_238(var1).method_1009(0.6, 0.0, 0.6).method_1012(0.0, 2.0, 0.0)).isEmpty();
   }

   private static class_2338 x3mty4h6w5e7b3(class_3218 var0, class_2338 var1) {
      for (int var2 = 2; var2 <= 4; var2++) {
         for (class_2350 var4 : class_2353.field_11062) {
            class_2338 var5 = var1.method_10079(var4, var2);
            if (var0.method_14178().method_12123(var5.method_10263() >> 4, var5.method_10260() >> 4)
               && var0.method_8316(var5).method_15769()
               && var0.method_8316(var5.method_10074()).method_15769()
               && var0.method_8320(var5).method_26220(var0, var5).method_1110()
               && var0.method_8320(var5.method_10084()).method_26220(var0, var5.method_10084()).method_1110()
               && var0.method_8320(var5.method_10074()).method_26206(var0, var5.method_10074(), class_2350.field_11036)) {
               return var5.method_10062();
            }
         }
      }

      return null;
   }

   private static boolean x323q5u6pfg7wx(class_1799 var0) {
      return var0.method_31574(class_1802.field_17518) || var0.method_31574(class_1802.field_17519);
   }

   private static class_1799 x1kf3zgkco82h3(class_1646 var0) {
      for (int var1 = 0; var1 < var0.method_35199().method_5439(); var1++) {
         if (var0.method_35199().method_5438(var1).method_31574(class_1802.field_8620)) {
            return var0.method_35199().method_5438(var1);
         }
      }

      return null;
   }

   private static boolean x96n4hutou332(class_1646 var0) {
      for (int var1 = 0; var1 < var0.method_35199().method_5439(); var1++) {
         if (var0.method_35199().method_5438(var1).method_7960()) {
            return true;
         }
      }

      return false;
   }

   private static void x2aesxzdpwjel5(class_3218 var0, x15kgoaq6i3m6i var1, int var2) {
      x2fl60kuzia5xk.x7uj9zeib8utf(var0, var1, var2);
   }
}
