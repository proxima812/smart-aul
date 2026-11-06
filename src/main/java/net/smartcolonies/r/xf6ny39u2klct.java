package net.smartcolonies.r;

import java.util.Comparator;
import java.util.List;
import net.minecraft.class_1304;
import net.minecraft.class_1542;
import net.minecraft.class_1646;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_3218;
import net.minecraft.class_3489;
import net.minecraft.class_4168;
import net.minecraft.class_7923;
import net.minecraft.class_9334;
import net.smartcolonies.mixin.x3u93owcuyb5d4;

public final class xf6ny39u2klct {
   private xf6ny39u2klct() {
   }

   public static void x3cumye59iar40(class_3218 var0, x15kgoaq6i3m6i var1, class_1646 var2) {
      x1rxiy9mtu5b3h var3 = var1.roleOf(var2.method_5667());
      if (var3 != x1rxiy9mtu5b3h.GUARD) {
         for (class_1304 var7 : new class_1304[]{
            class_1304.field_6169, class_1304.field_6174, class_1304.field_6172, class_1304.field_6166, class_1304.field_6171, class_1304.field_6173
         }) {
            class_1799 var8 = var2.method_6118(var7);
            if (!var8.method_7960()
               && (var7 != class_1304.field_6173 || x26qf74mhgffbi(var8) || var8.method_31573(class_3489.field_42612) && var3 != x1rxiy9mtu5b3h.LUMBERJACK)
               )
             {
               class_1799 var9 = var8.method_7972();
               var2.method_6075();
               var2.method_5673(var7, class_1799.field_8037);
               x27tk113eckvag(var0, var1, var2, var9);
            }
         }

         class_2338 var10 = x2n55y2tom37h2.x1falh21nhy4yk(var0, var1, var2.method_24515()).orElse(null);
         if (var10 != null && var2.method_24515().method_10262(var10) <= 9.0) {
            for (int var11 = 0; var11 < var2.method_35199().method_5439(); var11++) {
               class_1799 var12 = var2.method_35199().method_5438(var11);
               if (x26qf74mhgffbi(var12) || var12.method_31573(class_3489.field_42612) && var3 != x1rxiy9mtu5b3h.LUMBERJACK) {
                  x2n55y2tom37h2.x2cl1crde2rnxy(var0, var1, var12);
               }
            }
         }
      }
   }

   private static void x27tk113eckvag(class_3218 var0, x15kgoaq6i3m6i var1, class_1646 var2, class_1799 var3) {
      class_2338 var4 = x2n55y2tom37h2.x1falh21nhy4yk(var0, var1, var2.method_24515()).orElse(null);
      if (var4 != null && var2.method_24515().method_10262(var4) <= 9.0) {
         x2n55y2tom37h2.x2cl1crde2rnxy(var0, var1, var3);
      }

      if (!var3.method_7960()) {
         class_1799 var5 = var2.method_35199().method_5491(var3);
         if (!var5.method_7960()) {
            class_1542 var6 = var2.method_5775(var0, var5);
            if (var6 != null) {
               var6.method_6988();
            }
         }
      }
   }

   public static boolean x26qf74mhgffbi(class_1799 var0) {
      return var0.method_31573(class_3489.field_42611)
         || var0.method_31574(class_1802.field_8102)
         || var0.method_31574(class_1802.field_8399)
         || var0.method_31574(class_1802.field_8255)
         || var0.method_31574(class_1802.field_8107)
         || var0.method_57826(class_9334.field_54196);
   }

   public static boolean x23ik6olfsfgwk(class_1799 var0, x1rxiy9mtu5b3h var1) {
      if (var0.method_7960()) {
         return false;
      } else {
         String var2 = class_7923.field_41178.method_10221(var0.method_7909()).method_12832();
         return var0.method_57826(class_9334.field_50075)
            || var0.method_31573(class_3489.field_15539)
            || var0.method_31573(class_3489.field_15537)
            || var0.method_31573(class_3489.field_25808)
            || var0.method_31573(class_3489.field_15528)
            || var0.method_31574(class_1802.field_8687)
            || var0.method_31574(class_1802.field_8477)
            || var0.method_31574(class_1802.field_8713)
            || var0.method_31574(class_1802.field_8665)
            || var2.startsWith("raw_")
            || var2.endsWith("_ingot")
            || var2.endsWith("_nugget")
            || var0.method_31574(class_1802.field_8725)
            || var0.method_31574(class_1802.field_8759)
            || var0.method_31574(class_1802.field_8155)
            || var0.method_31574(class_1802.field_8276)
            || var0.method_31574(class_1802.field_8745)
            || var0.method_31574(class_1802.field_8861)
            || var0.method_31574(class_1802.field_8324)
            || var0.method_31574(class_1802.field_17518)
            || var0.method_31574(class_1802.field_17519)
            || var0.method_31574(class_1802.field_17528)
            || var0.method_31574(class_1802.field_8773)
            || var0.method_31574(class_1802.field_8494)
            || var0.method_31574(class_1802.field_8603)
            || var1 == x1rxiy9mtu5b3h.BUILDER
               && (var0.method_31574(class_1802.field_20391) || var0.method_31574(class_1802.field_20395) || var0.method_31574(class_1802.field_8280))
            || var0.method_31574(class_1802.field_8810)
            || var0.method_31573(class_3489.field_15544)
            || x26qf74mhgffbi(var0)
            || var1 == x1rxiy9mtu5b3h.FARMER && var2.endsWith("_seeds")
            || var1 == x1rxiy9mtu5b3h.RANCHER
               && (
                  var0.method_31574(class_1802.field_8803)
                     || var0.method_31574(class_1802.field_8103)
                     || var0.method_31574(class_1802.field_8550)
                     || var0.method_31574(class_1802.field_8868)
               )
            || var1 == x1rxiy9mtu5b3h.MINER && var0.method_31573(class_3489.field_42614)
            || var1 == x1rxiy9mtu5b3h.LUMBERJACK && var0.method_31573(class_3489.field_42612)
            || var1 == x1rxiy9mtu5b3h.FARMER && var0.method_31573(class_3489.field_42613)
            || var1 == x1rxiy9mtu5b3h.FISHERMAN && var0.method_31574(class_1802.field_8378);
      }
   }

   public static void x3nc609h8fgb5y(class_3218 var0, x15kgoaq6i3m6i var1, class_1646 var2) {
      if (Math.floorMod(var2.field_6012 + var2.method_5628(), 5) == 0) {
         boolean var3 = Math.floorMod(var2.field_6012 + var2.method_5628(), 20) == 0;
         if (var3) {
            x3cumye59iar40(var0, var1, var2);
         }

         if (!var2.method_6113() && !var2.method_18009() && !var2.method_6109() && !x36u1fhs2z6uoi.active(var0, var1)) {
            x1rxiy9mtu5b3h var4 = var1.roleOf(var2.method_5667());
            if (var4 != x1rxiy9mtu5b3h.GUARD || !var2.method_18868().method_18896(xxao083dbo7ag.x14b5znl8ndblc)) {
               class_2338 var5 = var3 ? x2n55y2tom37h2.x1falh21nhy4yk(var0, var1, var2.method_24515()).orElse(null) : null;
               boolean var6 = var3
                  && var2.method_18868().method_27074().stream().anyMatch(var0x -> var0x.getClass().getName().startsWith("net.smartcolonies."));
               if (var3 && !var6 && var5 != null && var2.method_24515().method_10262(var5) <= 9.0) {
                  for (int var7 = 0; var7 < var2.method_35199().method_5439(); var7++) {
                     class_1799 var8 = var2.method_35199().method_5438(var7);
                     if (!var8.method_7960()
                        && !var8.method_57826(class_9334.field_50075)
                        && !var8.method_31574(class_1802.field_8810)
                        && !var8.method_31573(class_3489.field_42614)
                        && !var8.method_31573(class_3489.field_42612)
                        && !var8.method_31573(class_3489.field_42613)
                        && !var8.method_31574(class_1802.field_8378)
                        && (var4 != x1rxiy9mtu5b3h.GUARD || !x26qf74mhgffbi(var8))
                        && (
                           var4 != x1rxiy9mtu5b3h.RANCHER
                              || !var8.method_31574(class_1802.field_8868)
                                 && !var8.method_31574(class_1802.field_8550)
                                 && !var8.method_31574(class_1802.field_8861)
                                 && !var8.method_31574(class_1802.field_8317)
                                 && !var8.method_31574(class_1802.field_8179)
                        )
                        && (
                           var4 != x1rxiy9mtu5b3h.FARMER
                              || !var8.method_31574(class_1802.field_8861)
                                 && !class_7923.field_41178.method_10221(var8.method_7909()).method_12832().endsWith("_seeds")
                        )) {
                        x2vaj7rsm57i4n.x25cotfj45r525(var0, var1, var2, var8);
                     }
                  }
               }

               for (class_1542 var11 : var0.method_8390(
                  class_1542.class, var2.method_5829().method_1009(1.8, 0.6, 1.8), var0x -> !var0x.method_31481() && !var0x.method_6977()
               )) {
                  x105bgml33wsy(var0, var1, var2, var11, var4, Integer.MAX_VALUE);
               }

               boolean var10 = Math.floorMod(var0.method_8532(), 24000) >= 12000
                  || var2.method_18868().method_24538().filter(class_4168.field_18597::equals).isPresent();
               if (var3 && !var6 && !var10 && var4 != x1rxiy9mtu5b3h.FOUNDER) {
                  var var12 = var0.method_8390(
                     class_1542.class,
                     var2.method_5829().method_1009(8.0, 2.0, 8.0),
                     var4x -> x2t0sm1vfzey0p(var0, var1, var2, var4x, var4)
                        && var4x.method_5858(var2) <= 64.0
                        && x17qx2f4ijtfsn(var2, var4x.method_6983())
                        && !x1c1r5c6a13gtl.xps35ehel6nkk(var2, var4x.method_24515(), var0.method_75260())
                  );
                  var12.stream()
                     .min(Comparator.comparingDouble(var2::method_5858))
                     .ifPresent(
                        var2x -> {
                           x1c1r5c6a13gtl.x1cssc4al5kdgu(var0, var2, var2x.method_24515(), 0.8);
                           x2vaj7rsm57i4n.x1qd0mutwsr3sc(
                              var2, "идёт подобрать: " + class_7923.field_41178.method_10221(var2x.method_6983().method_7909()).method_12832()
                           );
                        }
                     );
               }
            }
         }
      }
   }

   static int x105bgml33wsy(class_3218 var0, x15kgoaq6i3m6i var1, class_1646 var2, class_1542 var3, x1rxiy9mtu5b3h var4, int var5) {
      if (!x2t0sm1vfzey0p(var0, var1, var2, var3, var4)) {
         return 0;
      } else {
         class_1799 var6 = var3.method_6983();
         int var7 = Math.min(var5, var6.method_7947());
         if (var6.method_31574(class_1802.field_8803)) {
            var7 = Math.min(var7, Math.max(0, 16 - x3nf466jvpoooi(var2, class_1802.field_8803)));
         }

         if (var6.method_31574(class_1802.field_8103)) {
            var7 = Math.min(var7, Math.max(0, 4 - xw0xosmde9jmp.x2m95xmyunf3vb(var0, var1)));
         }

         if (var7 <= 0) {
            return 0;
         } else {
            class_1799 var8 = var6.method_7972();
            var8.method_7939(var7);
            class_1799 var9 = var2.method_35199().method_5491(var8);
            int var10 = var7 - var9.method_7947();
            if (var10 <= 0) {
               return 0;
            } else {
               var6.method_7934(var10);
               var2.method_6103(var3, var10);
               if (var6.method_7960()) {
                  var3.method_31472();
               } else {
                  var3.method_6979(var6);
               }

               x2vaj7rsm57i4n.x2t98zlq4lu49t(var2, "подбор полезных предметов", var10);
               return var10;
            }
         }
      }
   }

   static boolean x2t0sm1vfzey0p(class_3218 var0, x15kgoaq6i3m6i var1, class_1646 var2, class_1542 var3, x1rxiy9mtu5b3h var4) {
      if (var3.method_31481()
         || var3.method_6977()
         || ((x3u93owcuyb5d4)var3).x6t8mlwqv273n() != null
         || x2uh56vnzc4u51.x3gqyq7yie9jxr(var0, var3.method_24515())
         || !var2.method_6057(var3)
         || !x23ik6olfsfgwk(var3.method_6983(), var4)) {
         return false;
      } else {
         return var3.method_6983().method_31574(class_1802.field_8803) && x3nf466jvpoooi(var2, class_1802.field_8803) >= 16
            ? false
            : !var3.method_6983().method_31574(class_1802.field_8103) || xw0xosmde9jmp.x2m95xmyunf3vb(var0, var1) < 4;
      }
   }

   private static boolean x17qx2f4ijtfsn(class_1646 var0, class_1799 var1) {
      for (int var2 = 0; var2 < var0.method_35199().method_5439(); var2++) {
         class_1799 var3 = var0.method_35199().method_5438(var2);
         if (var3.method_7960() || class_1799.method_31577(var3, var1) && var3.method_7947() < var0.method_35199().method_58350(var1)) {
            return true;
         }
      }

      return false;
   }

   private static int x3nf466jvpoooi(class_1646 var0, class_1792 var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var0.method_35199().method_5439(); var3++) {
         class_1799 var4 = var0.method_35199().method_5438(var3);
         if (var4.method_31574(var1)) {
            var2 += var4.method_7947();
         }
      }

      return var2;
   }
}
