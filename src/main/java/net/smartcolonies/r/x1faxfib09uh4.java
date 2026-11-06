package net.smartcolonies.r;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Predicate;
import net.minecraft.class_1263;
import net.minecraft.class_1646;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1914;
import net.minecraft.class_1916;
import net.minecraft.class_2338;
import net.minecraft.class_3218;
import net.minecraft.class_3489;
import net.minecraft.class_7923;
import net.minecraft.class_9306;

public final class x1faxfib09uh4 {
   private static final Map<class_1646, Map<class_1914, xbwzbqntj80jx>> x2od5627wbgq9s = Collections.synchronizedMap(new WeakHashMap<>());

   private x1faxfib09uh4() {
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public static class_1916 x88t1bn7r4bt7(class_3218 var0, x15kgoaq6i3m6i var1, class_1646 var2, x1rxiy9mtu5b3h var3) {
      class_1916 var4 = new class_1916();
      var var5 = new IdentityHashMap<class_1914,xbwzbqntj80jx>();
      switch (var3) {
         case ELDER:
            for (int var9 = 1; var9 <= 2; var9++) {
               int var12 = x1a5rxjbs73iva.x2vr85xzgtiacc(var1, var9 == 1 ? 32 : 64, false);
               class_1914 var8 = new class_1914(
                  new class_9306(class_1802.field_8687, var12),
                  xquptt42fexz2.x2qg2bi4tzny42(var0, var1, var9),
                  x1a5rxjbs73iva.x286mwub0gxrf2(var1),
                  10 * var9,
                  0.0F
               );
               var4.add(var8);
               var5.put(var8, new xbwzbqntj80jx(var1, var3, class_1802.field_8407, 1, var12, true, 0, var0x -> false, true));
            }

            int var10 = x1a5rxjbs73iva.x2vr85xzgtiacc(var1, 24, true);
            class_1914 var13 = new class_1914(
               new class_9306(class_1802.field_8687, var10), x6amcj7vm0jm5.x31zhh2yvui44c(var0, var1), x1a5rxjbs73iva.x286mwub0gxrf2(var1), 10, 0.0F
            );
            var4.add(var13);
            var5.put(var13, new xbwzbqntj80jx(var1, var3, class_1802.field_8407, 1, var10, true, 0, var0x -> false, true));
            break;
         case MINER:
            x2r5pchrzult8h(
               var0, var1, var3, var4, var5, var0x -> var0x.method_31574(class_1802.field_20412) || var0x.method_31574(class_1802.field_29025), 16, 64, 1
            );
            x2r5pchrzult8h(var0, var1, var3, var4, var5, var0x -> var0x.method_31574(class_1802.field_27022), 8, 8, 1);
            x2r5pchrzult8h(var0, var1, var3, var4, var5, var0x -> var0x.method_31574(class_1802.field_8620), 4, 36, 2);
            x2r5pchrzult8h(var0, var1, var3, var4, var5, var0x -> var0x.method_31574(class_1802.field_8477), 1, 4, 4);
            x1hglz9jh0d1so(var0, var1, var3, var4, var5, class_1802.field_8583, 8, 16, 1);
            x1hglz9jh0d1so(var0, var1, var3, var4, var5, class_1802.field_8713, 8, 16, 1);
            break;
         case LUMBERJACK:
            x2r5pchrzult8h(var0, var1, var3, var4, var5, var0x -> var0x.method_31573(class_3489.field_15539), 8, 48, 1);
            x2r5pchrzult8h(var0, var1, var3, var4, var5, var0x -> var0x.method_31573(class_3489.field_15528), 4, 8, 1);
            x1hglz9jh0d1so(var0, var1, var3, var4, var5, class_1802.field_17535, 8, 8, 1);
            break;
         case FARMER:
            x2r5pchrzult8h(var0, var1, var3, var4, var5, var0x -> var0x.method_31574(class_1802.field_8861), 16, 32, 1);
            x2r5pchrzult8h(var0, var1, var3, var4, var5, var0x -> var0x.method_31574(class_1802.field_8229), 6, 16, 1);
            x2r5pchrzult8h(
               var0,
               var1,
               var3,
               var4,
               var5,
               var0x -> var0x.method_31574(class_1802.field_8179) || var0x.method_31574(class_1802.field_8567) || var0x.method_31574(class_1802.field_8186),
               12,
               24,
               1
            );
            x1hglz9jh0d1so(var0, var1, var3, var4, var5, class_1802.field_8317, 16, 16, 1);
            x1hglz9jh0d1so(var0, var1, var3, var4, var5, class_1802.field_46249, 4, 8, 1);
            x1hglz9jh0d1so(var0, var1, var3, var4, var5, class_1802.field_8229, 8, 64, 2);
            break;
         case FISHERMAN:
            x2r5pchrzult8h(
               var0, var1, var3, var4, var5, var0x -> var0x.method_31574(class_1802.field_8429) || var0x.method_31574(class_1802.field_8373), 8, 16, 1
            );
            x2r5pchrzult8h(
               var0, var1, var3, var4, var5, var0x -> var0x.method_31574(class_1802.field_8209) || var0x.method_31574(class_1802.field_8509), 6, 12, 1
            );
            x1hglz9jh0d1so(var0, var1, var3, var4, var5, class_1802.field_8276, 3, 3, 1);
            break;
         case RANCHER:
            x2r5pchrzult8h(var0, var1, var3, var4, var5, var0x -> var0x.method_31574(class_1802.field_8803), 16, 16, 1);
            x2r5pchrzult8h(var0, var1, var3, var4, var5, var0x -> var0x.method_31574(class_1802.field_8745), 4, 8, 1);
            x2r5pchrzult8h(var0, var1, var3, var4, var5, var0x -> var0x.method_31574(class_1802.field_8153), 8, 12, 1);
            x2r5pchrzult8h(
               var0, var1, var3, var4, var5, var0x -> class_7923.field_41178.method_10221(var0x.method_7909()).method_12832().endsWith("_wool"), 6, 12, 1
            );
            x1hglz9jh0d1so(var0, var1, var3, var4, var5, class_1802.field_8861, 16, 16, 2);
            break;
         case BUILDER:
            x2r5pchrzult8h(var0, var1, var3, var4, var5, var0x -> var0x.method_31573(class_3489.field_15537), 16, 64, 1);
            x2r5pchrzult8h(var0, var1, var3, var4, var5, var0x -> var0x.method_31573(class_3489.field_25808), 16, 128, 1);
            x1hglz9jh0d1so(var0, var1, var3, var4, var5, class_1802.field_8583, 16, 48, 2);
            x1hglz9jh0d1so(var0, var1, var3, var4, var5, class_1802.field_20412, 32, 96, 2);
            break;
         case GUARD:
            x22fpul10e08s.x1p0bvs1guedf7(var0, var1);

            for (xzld74pdvhfbu var7 : x22fpul10e08s.x3ck5rmycrqg51) {
               x2r5pchrzult8h(var0, var1, var3, var4, var5, x22fpul10e08s.xeccokn9mws72(var7.x3dtjj1ybya6jb()), 1, 1, var7.x1e0osd3mmpogq());
            }

            x2r5pchrzult8h(var0, var1, var3, var4, var5, var0x -> var0x.method_31574(class_1802.field_8107), 16, 32, 1);
            x1hglz9jh0d1so(var0, var1, var3, var4, var5, class_1802.field_8107, 32, 32, 2);
            x1hglz9jh0d1so(var0, var1, var3, var4, var5, class_1802.field_8620, 8, 24, 3);
            break;
         default:
            return var2.method_8264();
      }

      if (var4.isEmpty()) {
         class_1792 var11 = switch (var3) {
            case ELDER -> class_1802.field_8407;
            case MINER, BUILDER -> class_1802.field_20412;
            case LUMBERJACK -> class_1802.field_8583;
            case FARMER, RANCHER -> class_1802.field_8861;
            case FISHERMAN -> class_1802.field_8429;
            case GUARD -> class_1802.field_8107;
            default -> class_1802.field_8229;
         };
         class_1914 var14 = new class_1914(new class_9306(class_1802.field_8687), new class_1799(var11), 1, 0, 0.0F);
         var14.method_8254();
         var4.add(var14);
      }

      x2od5627wbgq9s.put(var2, var5);
      var2.method_16917(var4);
      return var4;
   }

   public static boolean xcynwq3uap918(class_1646 var0, class_1914 var1) {
      var var2 = x2od5627wbgq9s.get(var0);
      return var2 != null && var2.containsKey(var1);
   }

   public static boolean x1ujd85h9771yr(class_3218 var0, class_1646 var1, class_1914 var2) {
      xbwzbqntj80jx var3 = x19rv12tlpuxm5(var1, var2);
      if (var3 == null) {
         return true;
      } else {
         x15kgoaq6i3m6i var4 = var3.x2dllwbgbx21jg();
         int var5 = var3.x1cdr8im5qpob() ? var2.method_19272().method_7947() : var3.xq8izrlsuolkq();
         int var6 = var3.x1cdr8im5qpob() ? var3.xinr6pgmsbyag() : var2.method_19272().method_7947();
         if (var3.x3bz3dxgnj5nsn()) {
            return x3qagqs1j5wazk(var0, var4, class_1802.field_8687) >= var5;
         } else {
            return var3.x1cdr8im5qpob()
               ? xv9ih2vzuzpss(var0, var4, var3.x3quiphk00acds(), var3.x14h2cquxnospk()) >= var3.x2ybkscunemmqc() + var3.xinr6pgmsbyag()
                  && xv9ih2vzuzpss(var0, var4, var3.x3quiphk00acds(), var1x -> var1x.method_31574(var3.x2l8db5concsuf()) && var3.x14h2cquxnospk().test(var1x))
                     >= var3.xinr6pgmsbyag()
                  && x3qagqs1j5wazk(var0, var4, class_1802.field_8687) >= var5
               : x2n55y2tom37h2.x1mlne8tl8k5ag(var0, var4, var0x -> var0x.method_31574(class_1802.field_8687)) >= var5
                  && x3qagqs1j5wazk(var0, var4, var3.x2l8db5concsuf()) >= var6;
         }
      }
   }

   public static void x1qctxo20x2yyf(class_3218 var0, class_1646 var1, class_1914 var2) {
      xbwzbqntj80jx var3 = x19rv12tlpuxm5(var1, var2);
      if (var3 != null) {
         x15kgoaq6i3m6i var4 = var3.x2dllwbgbx21jg();
         if (x1ujd85h9771yr(var0, var1, var2)) {
            int var5 = var3.x1cdr8im5qpob() ? var2.method_19272().method_7947() : var3.xq8izrlsuolkq();
            int var6 = var3.x1cdr8im5qpob() ? var3.xinr6pgmsbyag() : var2.method_19272().method_7947();
            if (var3.x3bz3dxgnj5nsn()) {
               if (x2n55y2tom37h2.x2cl1crde2rnxy(var0, var4, new class_1799(class_1802.field_8687, var5))) {
                  var4.x3ktd3i0hq4h53();
                  x2vaj7rsm57i4n.x2t98zlq4lu49t(var1, "продажа разрешения", 1);
                  x2n55y2tom37h2.xchl1k1tnh3w8(var0).method_80();
               }
            } else {
               if (var3.x1cdr8im5qpob()) {
                  if (!x3gx6johh202p8(
                     var0,
                     var4,
                     var3.x3quiphk00acds(),
                     var1x -> var1x.method_31574(var3.x2l8db5concsuf()) && var3.x14h2cquxnospk().test(var1x),
                     var3.xinr6pgmsbyag()
                  )) {
                     return;
                  }

                  if (!x2n55y2tom37h2.x2cl1crde2rnxy(var0, var4, new class_1799(class_1802.field_8687, var5))) {
                     return;
                  }

                  var4.x3ktd3i0hq4h53();
                  x2vaj7rsm57i4n.x2t98zlq4lu49t(var1, "торговля с игроком: продажа", var3.xinr6pgmsbyag());
               } else {
                  if (!x2n55y2tom37h2.x22hjebj4vmztt(var0, var4, var0x -> var0x.method_31574(class_1802.field_8687), var5)) {
                     return;
                  }

                  if (!x2n55y2tom37h2.x2cl1crde2rnxy(var0, var4, new class_1799(var3.x2l8db5concsuf(), var6))) {
                     return;
                  }

                  var4.xqcex85brzoq2();
                  x2vaj7rsm57i4n.x2t98zlq4lu49t(var1, "торговля с игроком: покупка", var6);
               }

               x2n55y2tom37h2.xchl1k1tnh3w8(var0).method_80();
            }
         }
      }
   }

   private static void x2r5pchrzult8h(
      class_3218 var0,
      x15kgoaq6i3m6i var1,
      x1rxiy9mtu5b3h var2,
      class_1916 var3,
      Map<class_1914, xbwzbqntj80jx> var4,
      Predicate<class_1799> var5,
      int var6,
      int var7,
      int var8
   ) {
      int var9 = xv9ih2vzuzpss(var0, var1, var2, var5);
      var7 = Math.max(var7, var2 != x1rxiy9mtu5b3h.FARMER && var2 != x1rxiy9mtu5b3h.FISHERMAN ? 0 : var1.population() * 2);
      var6 = x1a5rxjbs73iva.xpl8mvusb83z9(var6, var9, var7);
      var8 = x1a5rxjbs73iva.x742kp3et4jfu(var8, var9, var7, var6);
      class_1792 var10 = x1n2hhi2syzjbb(var0, var1, var2, var5, var6);
      if (var10 != null) {
         int var11 = Math.min(
            12,
            Math.min(
               (xv9ih2vzuzpss(var0, var1, var2, var5) - var7) / var6,
               xv9ih2vzuzpss(var0, var1, var2, var2x -> var2x.method_31574(var10) && var5.test(var2x)) / var6
            )
         );
         if (var11 > 0) {
            class_1914 var12 = new class_1914(new class_9306(class_1802.field_8687, var8), new class_1799(var10, var6), var11, 1, 0.0F);
            var3.add(var12);
            var4.put(var12, new xbwzbqntj80jx(var1, var2, var10, var6, var8, true, var7, var5, false));
         }
      }
   }

   private static void x1hglz9jh0d1so(
      class_3218 var0,
      x15kgoaq6i3m6i var1,
      x1rxiy9mtu5b3h var2,
      class_1916 var3,
      Map<class_1914, xbwzbqntj80jx> var4,
      class_1792 var5,
      int var6,
      int var7,
      int var8
   ) {
      int var9 = x2n55y2tom37h2.x1mlne8tl8k5ag(var0, var1, var1x -> var1x.method_31574(var5));
      var7 = Math.max(var7, var2 != x1rxiy9mtu5b3h.FARMER && var2 != x1rxiy9mtu5b3h.RANCHER ? var7 : var1.population() * 2);
      var8 = x1a5rxjbs73iva.x8v45wzyc7cx3(var8, var9, var7);
      var6 = Math.max(1, Math.min(var6, Math.max(1, var7 - var9)));
      int var10 = x2n55y2tom37h2.x1mlne8tl8k5ag(var0, var1, var0x -> var0x.method_31574(class_1802.field_8687)) / var8;
      int var11 = Math.min(12, Math.min(var10, Math.max(0, (var7 - var9 + var6 - 1) / var6)));
      if (var11 > 0 && x3qagqs1j5wazk(var0, var1, var5) >= var6) {
         class_1914 var12 = new class_1914(new class_9306(var5, var6), new class_1799(class_1802.field_8687, var8), var11, 1, 0.0F);
         var3.add(var12);
         var4.put(var12, new xbwzbqntj80jx(var1, var2, var5, var6, var8, false, 0, var1x -> var1x.method_31574(var5), false));
      }
   }

   private static xbwzbqntj80jx x19rv12tlpuxm5(class_1646 var0, class_1914 var1) {
      var var2 = x2od5627wbgq9s.get(var0);
      return var2 == null ? null : (xbwzbqntj80jx)var2.get(var1);
   }

   private static class_1792 x1n2hhi2syzjbb(class_3218 var0, x15kgoaq6i3m6i var1, x1rxiy9mtu5b3h var2, Predicate<class_1799> var3, int var4) {
      for (class_2338 var6 : var1.x2ad3s8aentmo4()) {
         if (var0.method_14178().method_12123(var6.method_10263() >> 4, var6.method_10260() >> 4)
            && !x2uh56vnzc4u51.xic1z39wiy3pn(var0, var6)
            && (
               var2 == x1rxiy9mtu5b3h.GUARD
                  || var1.x2h8eqlzb5mm0w(var6) == x2dtqvbx3ov8jv(var2)
                  || var1.x2h8eqlzb5mm0w(var6) == x2jj5edkiu5orf.GENERAL
            )
            && var0.method_8321(var6) instanceof class_1263 var7) {
            for (int var10 = 0; var10 < var7.method_5439(); var10++) {
               class_1799 var9 = var7.method_5438(var10);
               if (!var9.method_7960()
                  && var3.test(var9)
                  && xv9ih2vzuzpss(var0, var1, var2, var2x -> var2x.method_31574(var9.method_7909()) && var3.test(var2x)) >= var4) {
                  return var9.method_7909();
               }
            }
         }
      }

      return null;
   }

   private static int xv9ih2vzuzpss(class_3218 var0, x15kgoaq6i3m6i var1, x1rxiy9mtu5b3h var2, Predicate<class_1799> var3) {
      int var4 = 0;
      x2jj5edkiu5orf var5 = x2dtqvbx3ov8jv(var2);

      for (class_2338 var7 : var1.x2ad3s8aentmo4()) {
         if (var0.method_14178().method_12123(var7.method_10263() >> 4, var7.method_10260() >> 4)
            && !x2uh56vnzc4u51.xic1z39wiy3pn(var0, var7)
            && (var2 == x1rxiy9mtu5b3h.GUARD || var1.x2h8eqlzb5mm0w(var7) == var5 || var1.x2h8eqlzb5mm0w(var7) == x2jj5edkiu5orf.GENERAL)
            && var0.method_8321(var7) instanceof class_1263 var8) {
            for (int var11 = 0; var11 < var8.method_5439(); var11++) {
               class_1799 var10 = var8.method_5438(var11);
               if (!var10.method_7960() && var3.test(var10)) {
                  var4 += var10.method_7947();
               }
            }
         }
      }

      return var4;
   }

   private static boolean x3gx6johh202p8(class_3218 var0, x15kgoaq6i3m6i var1, x1rxiy9mtu5b3h var2, Predicate<class_1799> var3, int var4) {
      if (xv9ih2vzuzpss(var0, var1, var2, var3) < var4) {
         return false;
      } else {
         int var5 = var4;
         x2jj5edkiu5orf var6 = x2dtqvbx3ov8jv(var2);

         for (class_2338 var8 : var1.x2ad3s8aentmo4()) {
            if (var0.method_14178().method_12123(var8.method_10263() >> 4, var8.method_10260() >> 4)
               && !x2uh56vnzc4u51.xic1z39wiy3pn(var0, var8)
               && (var2 == x1rxiy9mtu5b3h.GUARD || var1.x2h8eqlzb5mm0w(var8) == var6 || var1.x2h8eqlzb5mm0w(var8) == x2jj5edkiu5orf.GENERAL)
               && var0.method_8321(var8) instanceof class_1263 var10) {
               for (int var11 = 0; var11 < var10.method_5439() && var5 > 0; var11++) {
                  class_1799 var12 = var10.method_5438(var11);
                  if (!var12.method_7960() && var3.test(var12)) {
                     int var13 = Math.min(var5, var12.method_7947());
                     class_1792 var14 = var12.method_7909();
                     var12.method_7934(var13);
                     var1.x2sh8yupnxmo71(var14, var13);
                     var5 -= var13;
                     var10.method_5431();
                  }
               }

               if (var5 == 0) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   private static int x3qagqs1j5wazk(class_3218 var0, x15kgoaq6i3m6i var1, class_1792 var2) {
      return x2n55y2tom37h2.x1amge7aoh04ca(var0, var1, new class_1799(var2));
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private static x2jj5edkiu5orf x2dtqvbx3ov8jv(x1rxiy9mtu5b3h var0) {
      return switch (var0) {
         case MINER -> x2jj5edkiu5orf.MINING;
         case LUMBERJACK, BUILDER -> x2jj5edkiu5orf.WOOD;
         case FARMER, FISHERMAN -> x2jj5edkiu5orf.FOOD;
         case RANCHER -> x2jj5edkiu5orf.ANIMAL;
         default -> x2jj5edkiu5orf.GENERAL;
      };
   }
}
