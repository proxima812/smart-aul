package net.smartcolonies.r;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.WeakHashMap;
import net.minecraft.class_11;
import net.minecraft.class_1308;
import net.minecraft.class_1408;
import net.minecraft.class_1646;
import net.minecraft.class_2246;
import net.minecraft.class_2323;
import net.minecraft.class_2338;
import net.minecraft.class_2349;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_2482;
import net.minecraft.class_2488;
import net.minecraft.class_2510;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3486;
import net.minecraft.class_4140;
import net.minecraft.class_2350.class_2353;

public final class x18ucnhocy9qn5 {
   private static final Map<class_1308, x2gtpqlzi1e0te> xy2bukilziav0 = new WeakHashMap<>();
   private static final Map<class_3218, Long> x1tiv6ssain9ri = new WeakHashMap<>();
   private static final int x32hrhla2zhanh = 6000;

   private x18ucnhocy9qn5() {
   }

   public static void x26rn6haaqjid6() {
      xy2bukilziav0.clear();
      x1tiv6ssain9ri.clear();
   }

   public static void x3oupbjeq14mj8(class_1308 var0) {
      xy2bukilziav0.remove(var0);
      var0.method_5942().method_6340();
   }

   public static String xs0vwkswo7jny(class_1308 var0) {
      x2gtpqlzi1e0te var1 = xy2bukilziav0.get(var0);
      return var1 == null
         ? "none"
         : "goal="
            + var1.xdxnox9g75zv6
            + " points="
            + var1.x35iwfetci3lq3
            + "/"
            + var1.x3djwzkmkmiiku.size()
            + " next="
            + (var1.x35iwfetci3lq3 < var1.x3djwzkmkmiiku.size() ? var1.x3djwzkmkmiiku.get(var1.x35iwfetci3lq3) : null)
            + " failures="
            + var1.x2n9nq6uu09ugo
            + " navEnd="
            + (var0.method_5942().method_6345() == null ? null : var0.method_5942().method_6345().method_45())
            + " following="
            + var0.method_5942().method_23966()
            + " index="
            + (var1.x1h7unpbxmmlly == null ? "-" : var1.x1h7unpbxmmlly.method_39() + "/" + var1.x1h7unpbxmmlly.method_38())
            + " velocity="
            + var0.method_18798();
   }

   public static boolean x1wi30zgg7nf81(class_3218 var0, class_1308 var1, class_2338 var2, double var3) {
      if (var1 instanceof class_1646 var5) {
         var5.method_18868().method_18875(class_4140.field_18445);
         var5.method_18868().method_18875(class_4140.field_18449);
      }

      class_1408 var25 = var1.method_5942();
      var25.method_70687(true);
      var25.method_6354(true);
      x2gtpqlzi1e0te var6 = xy2bukilziav0.computeIfAbsent(var1, var0x -> new x2gtpqlzi1e0te());
      long var7 = var0.method_75260();
      if (var6.xdxnox9g75zv6 != null && !(var6.xdxnox9g75zv6.method_10262(var2) > 16.0)) {
         var6.xdxnox9g75zv6 = var2.method_10062();
      } else {
         var6.xdxnox9g75zv6 = var2.method_10062();
         var6.x3djwzkmkmiiku = List.of();
         var6.x35iwfetci3lq3 = 0;
         var6.x1h7unpbxmmlly = null;
         var6.xbrpkefumlqsh = 0L;
      }

      if (var6.x1qt4uh5iogtme == null || var6.x1qt4uh5iogtme.method_10262(var1.method_24515()) >= 4.0) {
         var6.x1qt4uh5iogtme = var1.method_24515();
         var6.x1etn08okxo4qh = var7;
         var6.x2n9nq6uu09ugo = 0;
      }

      if (var1.method_5649(var2.method_10263() + 0.5, var2.method_10264(), var2.method_10260() + 0.5) <= 2.25) {
         x3oupbjeq14mj8(var1);
         return true;
      } else {
         if (var6.x3djwzkmkmiiku.isEmpty()
            && var1.method_24515().method_10262(var2) < 2304.0
            && var7 >= var6.x220dzuvs9upmh
            && x2n55y2tom37h2.x5ll9elqpk1a4(var0)) {
            var6.x220dzuvs9upmh = var7 + 60L;
            var25.method_23964(4.0F);

            class_11 var9;
            try {
               var9 = var25.method_6348(var2, 1);
            } finally {
               var25.method_23965();
            }

            if (var9 != null && var9.method_21655()) {
               var6.x1h7unpbxmmlly = var9;
               var6.x3djwzkmkmiiku = List.of(var2.method_10062());
               var6.x35iwfetci3lq3 = 0;
               return var25.method_6334(var9, var3);
            }
         }

         if (var7 - var6.x1etn08okxo4qh > 120L && var7 - var6.x2fcxxyu45q1du >= 120L) {
            var6.x3djwzkmkmiiku = List.of();
            var6.x1h7unpbxmmlly = null;
            var6.x2fcxxyu45q1du = var7;
         }

         while (
            var6.x35iwfetci3lq3 < var6.x3djwzkmkmiiku.size()
               && var1.method_5707(class_243.method_24955((class_2382)var6.x3djwzkmkmiiku.get(var6.x35iwfetci3lq3))) < 2.25
         ) {
            var6.x35iwfetci3lq3++;
         }

         if (var6.x35iwfetci3lq3 >= var6.x3djwzkmkmiiku.size() && var7 >= var6.xbrpkefumlqsh) {
            if (var7 - x1tiv6ssain9ri.getOrDefault(var0, -4611686018427387904L) < 10L || !x2n55y2tom37h2.x5ll9elqpk1a4(var0)) {
               return false;
            }

            x1tiv6ssain9ri.put(var0, var7);
            var6.xbrpkefumlqsh = var7 + 100L;
            var6.x3djwzkmkmiiku = x3iiafc1l36ps5(var0, var1, var6.xdxnox9g75zv6);
            var6.x35iwfetci3lq3 = 0;
            var6.x1h7unpbxmmlly = null;
            if (var6.x3djwzkmkmiiku.isEmpty()) {
               var6.x2n9nq6uu09ugo++;
            }
         }

         if (var6.x35iwfetci3lq3 >= var6.x3djwzkmkmiiku.size()) {
            return false;
         } else if (var6.x1h7unpbxmmlly != null && !var6.x1h7unpbxmmlly.method_46()) {
            if (var25.method_6345() != var6.x1h7unpbxmmlly || !var25.method_23966()) {
               var25.method_6334(var6.x1h7unpbxmmlly, var3);
            }

            return true;
         } else if (var7 >= var6.x26j1gavkh2jv2 && x2n55y2tom37h2.x5ll9elqpk1a4(var0)) {
            var6.x26j1gavkh2jv2 = var7 + 10L;
            int var26 = Math.min(var6.x3djwzkmkmiiku.size() - 1, var6.x35iwfetci3lq3 + 7);
            class_2338 var10 = var6.x3djwzkmkmiiku.get(var26);
            var25.method_23964(4.0F);

            try {
               var6.x1h7unpbxmmlly = var25.method_6348(var10, 0);
            } finally {
               var25.method_23965();
            }

            if (var6.x1h7unpbxmmlly != null && var6.x1h7unpbxmmlly.method_21655()) {
               var6.x35iwfetci3lq3 = var26;
            } else {
               var6.x1h7unpbxmmlly = null;
               var25.method_23964(4.0F);

               try {
                  var6.x1h7unpbxmmlly = var25.method_6348(var6.x3djwzkmkmiiku.get(var6.x35iwfetci3lq3), 0);
               } finally {
                  var25.method_23965();
               }

               if (var6.x1h7unpbxmmlly == null || !var6.x1h7unpbxmmlly.method_21655()) {
                  var6.x2n9nq6uu09ugo++;
                  var6.x1h7unpbxmmlly = null;
                  var6.x3djwzkmkmiiku = List.of();
                  var6.xbrpkefumlqsh = var7 + 40L;
                  return false;
               }
            }

            return var25.method_6334(var6.x1h7unpbxmmlly, var3);
         } else {
            return false;
         }
      }
   }

   public static boolean x3hi5h4uz5pg79(class_3218 var0, class_1646 var1) {
      x2gtpqlzi1e0te var2 = xy2bukilziav0.get(var1);
      long var3 = var0.method_75260();
      if (var2 != null
         && var2.x2n9nq6uu09ugo >= 8
         && var3 - var2.x1etn08okxo4qh >= 1200L
         && !var1.method_6113()
         && !var1.method_18009()
         && var1.method_24828()
         && var2.x3djwzkmkmiiku.isEmpty()
         && !(var1.method_18798().method_1027() > 0.01)) {
         class_2338 var5 = var1.method_24515();

         for (int var6 = 1; var6 <= 6; var6++) {
            for (int var10 : new int[]{1, 2, 0, -1}) {
               for (int var11 = -var6; var11 <= var6; var11++) {
                  for (int var12 = -var6; var12 <= var6; var12++) {
                     if (Math.abs(var11) == var6 || Math.abs(var12) == var6) {
                        class_2338 var13 = var5.method_10069(var11, var10, var12);
                        if (x21dbjs9wlccy4(var0, var1, var13)
                           && !x2uh56vnzc4u51.x3gqyq7yie9jxr(var0, var13)
                           && !x1dxdlnevtfbhm.x3o1lagqjim5tz(var0, var13.method_10074())
                           && var0.method_8333(var1, new class_238(var13).method_1012(0.0, 1.0, 0.0), var0x -> var0x.method_5805()).isEmpty()) {
                           var1.method_5808(
                              var13.method_10263() + 0.5, var13.method_10264(), var13.method_10260() + 0.5, var1.method_36454(), var1.method_36455()
                           );
                           x3oupbjeq14mj8(var1);
                           return true;
                        }
                     }
                  }
               }
            }
         }

         return false;
      } else {
         return false;
      }
   }

   private static List<class_2338> x3iiafc1l36ps5(class_3218 var0, class_1308 var1, class_2338 var2) {
      class_2338 var3 = var1.method_24515();
      var var4 = new PriorityQueue<>(Comparator.comparingDouble(x23h5ttlfi4p2e::x3bn75ty1xrpx3));
      var var5 = new HashMap<class_2338, Integer>();
      var var6 = new HashMap<class_2338, class_2338>();
      var var7 = new HashSet<>();
      var var8 = new HashMap<class_2338, Boolean>();
      var5.put(var3, 0);
      var4.add(new x23h5ttlfi4p2e(var3, 0, xp31m43a2ifr6(var3, var2)));
      class_2338 var9 = var3;
      double var10 = xp31m43a2ifr6(var3, var2);
      int var12 = 0;
      int var13 = Math.min(var3.method_10263(), var2.method_10263()) - 48;
      int var14 = Math.max(var3.method_10263(), var2.method_10263()) + 48;
      int var15 = Math.min(var3.method_10260(), var2.method_10260()) - 48;
      int var16 = Math.max(var3.method_10260(), var2.method_10260()) + 48;

      while (!var4.isEmpty() && var12++ < 6000) {
         x23h5ttlfi4p2e var17 = (x23h5ttlfi4p2e)var4.poll();
         class_2338 var18 = var17.x191jg6ukc9dt8();
         if (var7.add(var18)) {
            double var19 = xp31m43a2ifr6(var18, var2);
            if (var19 < var10) {
               var9 = var18;
               var10 = var19;
            }

            if (var18.method_10262(var2) <= 2.25) {
               var9 = var18;
               break;
            }

            for (class_2350 var22 : class_2353.field_11062) {
               class_2338 var23 = var18.method_10093(var22);
               if (var23.method_10263() >= var13 && var23.method_10263() <= var14 && var23.method_10260() >= var15 && var23.method_10260() <= var16) {
                  for (int var27 : new int[]{0, 1, -1}) {
                     class_2338 var28 = var23.method_10086(var27);
                     if (var8.computeIfAbsent(var28, var2x -> x21dbjs9wlccy4(var0, var1, var2x))
                        && (var27 <= 0 || x1sodm3kk4gpjc(var0, var1, var18.method_10084()))) {
                        int var29 = var17.x2xm2fy4xaxww7() + 10 + Math.abs(var27) * 4;
                        if (var29 < var5.getOrDefault(var28, Integer.MAX_VALUE)) {
                           var5.put(var28, var29);
                           var6.put(var28, var18);
                           var4.add(new x23h5ttlfi4p2e(var28, var29, var29 + xp31m43a2ifr6(var28, var2) * 10.0));
                        }
                        break;
                     }
                  }
               }
            }
         }
      }

      if (var9.equals(var3)) {
         return List.of();
      } else {
         var var30 = new ArrayList<class_2338>();

         for (class_2338 var31 = var9; !var31.equals(var3); var31 = (class_2338)var6.get(var31)) {
            if (var31 == null) {
               return List.of();
            }

            var30.add(var31);
         }

         Collections.reverse(var30);
         return var30;
      }
   }

   private static double xp31m43a2ifr6(class_2338 var0, class_2338 var1) {
      return Math.abs(var0.method_10263() - var1.method_10263())
         + Math.abs(var0.method_10260() - var1.method_10260())
         + Math.abs(var0.method_10264() - var1.method_10264());
   }

   private static boolean x21dbjs9wlccy4(class_3218 var0, class_1308 var1, class_2338 var2) {
      if (!var0.method_31606(var2)
         && var0.method_8621().method_11952(var2)
         && var0.method_14178().method_12123(var2.method_10263() >> 4, var2.method_10260() >> 4)) {
         class_2680 var3 = var0.method_8320(var2.method_10074());
         boolean var4 = var3.method_26206(var0, var2.method_10074(), class_2350.field_11036)
            || var3.method_26204() instanceof class_2510
            || var3.method_26204() instanceof class_2482;
         boolean var5 = var0.method_8316(var2).method_15767(class_3486.field_15517) && var0.method_8316(var2.method_10084()).method_15769();
         return (var0.method_8316(var2.method_10074()).method_15769() && var4 || var5)
            && !var3.method_27852(class_2246.field_10092)
            && !var3.method_27852(class_2246.field_17350)
            && x1sodm3kk4gpjc(var0, var1, var2);
      } else {
         return false;
      }
   }

   private static boolean x1sodm3kk4gpjc(class_3218 var0, class_1308 var1, class_2338 var2) {
      double var3 = var1.method_17681() / 2.0;
      class_238 var5 = new class_238(
         var2.method_10263() + 0.5 - var3 + 0.01,
         var2.method_10264() + 0.01,
         var2.method_10260() + 0.5 - var3 + 0.01,
         var2.method_10263() + 0.5 + var3 - 0.01,
         var2.method_10264() + var1.method_17682() - 0.01,
         var2.method_10260() + 0.5 + var3 - 0.01
      );

      label72:
      for (class_2338 var7 : class_2338.method_10097(
         class_2338.method_49637(var5.field_1323, var5.field_1322, var5.field_1321), class_2338.method_49637(var5.field_1320, var5.field_1325, var5.field_1324)
      )) {
         if (!var0.method_14178().method_12123(var7.method_10263() >> 4, var7.method_10260() >> 4)) {
            return false;
         }

         if (!var0.method_8316(var7).method_15769() && !var0.method_8316(var7).method_15767(class_3486.field_15517)) {
            return false;
         }

         class_2680 var8 = var0.method_8320(var7);
         if (!var0.method_8316(var7).method_15767(class_3486.field_15517)) {
            if (!var8.method_27852(class_2246.field_10036)
               && !var8.method_27852(class_2246.field_10164)
               && !var8.method_27852(class_2246.field_10029)
               && !var8.method_27852(class_2246.field_16999)
               && !var8.method_27852(class_2246.field_27879)) {
               if (var8.method_26204() instanceof class_2488
                     && (Integer)var8.method_11654(class_2488.field_11518) < 8
                     && var7.method_10264() == var2.method_10264()
                  || var8.method_26204() instanceof class_2349 && (var1 instanceof class_1646 || x27aao31y6r2ta.x3di73z4zkdknf(var0, var7))
                  || var1 instanceof class_1646 && var8.method_26204() instanceof class_2323 && class_2323.method_24796(var8)) {
                  continue;
               }

               var var9 = var8.method_26220(var0, var7).method_1090().iterator();

               class_238 var10;
               do {
                  if (!var9.hasNext()) {
                     continue label72;
                  }

                  var10 = (class_238)var9.next();
               } while (!var10.method_989(var7.method_10263(), var7.method_10264(), var7.method_10260()).method_994(var5));

               return false;
            }

            return false;
         }
      }

      return true;
   }
}
