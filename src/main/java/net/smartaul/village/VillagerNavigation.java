package net.smartaul.village;

import java.util.List;
import java.util.Map;
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

public final class VillagerNavigation {
   private static final Map<class_1308, NavigationState> xy2bukilziav0 = new WeakHashMap<>();
   private static final Map<class_3218, Long> x1tiv6ssain9ri = new WeakHashMap<>();

   private VillagerNavigation() {
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
      NavigationState var1 = xy2bukilziav0.get(var0);
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
      NavigationState var6 = xy2bukilziav0.computeIfAbsent(var1, var0x -> new NavigationState());
      long var7 = var0.method_75260();
      if (var6.xdxnox9g75zv6 != null && !(var6.xdxnox9g75zv6.method_10262(var2) > 16.0)) {
         var6.xdxnox9g75zv6 = var2.method_10062();
      } else {
         if (var1 instanceof class_1646 worker) ActionTrace.worker(worker, "navigation_goal", var2.method_23854(), true);
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
            && ColonyManager.x5ll9elqpk1a4(var0)) {
            var6.x220dzuvs9upmh = var7 + 60L;
            var25.method_23964(4.0F);

            class_11 var9;
            try {
               var9 = var25.method_6348(var2, 1);
            } finally {
               var25.method_23965();
            }

            if (var9 != null && var9.method_21655() && safePath(var0, var1, var9) && !exposedPath(var0, var9)) {
               var6.x1h7unpbxmmlly = var9;
               var6.x3djwzkmkmiiku = List.of(var2.method_10062());
               var6.x35iwfetci3lq3 = 0;
               return var25.method_6334(var9, var3);
            }
         }

         if (var6.x1h7unpbxmmlly != null && !safePath(var0, var1, var6.x1h7unpbxmmlly)) {
            var25.method_6340();
            var6.x1h7unpbxmmlly = null;
            var6.x3djwzkmkmiiku = List.of();
            var6.xbrpkefumlqsh = var7;
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
            if (var7 - x1tiv6ssain9ri.getOrDefault(var0, -4611686018427387904L) < 10L || !ColonyManager.x5ll9elqpk1a4(var0)) {
               return false;
            }

            x1tiv6ssain9ri.put(var0, var7);
            var6.xbrpkefumlqsh = var7 + 100L;
            long pathStarted = System.nanoTime();
            var6.x3djwzkmkmiiku = x3iiafc1l36ps5(var0, var1, var6.xdxnox9g75zv6);
            ActionTrace.event(var0, null, var1 instanceof class_1646 worker ? worker : null, var2, "navigation_search", "points=" + var6.x3djwzkmkmiiku.size() + "; us=" + (System.nanoTime() - pathStarted) / 1000);
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
         } else if (var7 >= var6.x26j1gavkh2jv2 && ColonyManager.x5ll9elqpk1a4(var0)) {
            var6.x26j1gavkh2jv2 = var7 + 10L;
            int var26 = straightWaypoint(var6.x3djwzkmkmiiku, var6.x35iwfetci3lq3);
            class_2338 var10 = var6.x3djwzkmkmiiku.get(var26);
            var25.method_23964(4.0F);

            try {
               var6.x1h7unpbxmmlly = var25.method_6348(var10, 0);
            } finally {
               var25.method_23965();
            }

            if (var6.x1h7unpbxmmlly != null && var6.x1h7unpbxmmlly.method_21655() && safePath(var0, var1, var6.x1h7unpbxmmlly)) {
               var6.x35iwfetci3lq3 = var26;
            } else {
               var6.x1h7unpbxmmlly = null;
               var25.method_23964(4.0F);

               try {
                  var6.x1h7unpbxmmlly = var25.method_6348(var6.x3djwzkmkmiiku.get(var6.x35iwfetci3lq3), 0);
               } finally {
                  var25.method_23965();
               }

               if (var6.x1h7unpbxmmlly == null || !var6.x1h7unpbxmmlly.method_21655() || !safePath(var0, var1, var6.x1h7unpbxmmlly)) {
                  if (var1 instanceof class_1646 worker) ActionTrace.worker(worker, "navigation_wait", "no_reachable_waypoint; goal=" + var2.method_23854(), true);
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
      NavigationState var2 = xy2bukilziav0.get(var1);
      long var3 = var0.method_75260();
      if (ColonyManager.x2yj2v4iudqlvb(var0, var1.method_5667()).isPresent()) return false;
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
                           && !ProtectedLand.x3gqyq7yie9jxr(var0, var13)
                           && !WorldEditPolicy.x3o1lagqjim5tz(var0, var13.method_10074())
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
      return NavigationSearch.find(var1.method_24515(), var2,
         pos -> x21dbjs9wlccy4(var0, var1, pos) ? VillageSafety.edgePenalty(var0, pos) : -1,
         (from, to) -> to.method_10264() <= from.method_10264() || x1sodm3kk4gpjc(var0, var1, from.method_10084()),
         6000, 3_000_000L).points();
   }

   static int straightWaypoint(List<class_2338> points, int index) {
      int end = Math.min(points.size() - 1, index + 7);
      if (index >= end) return index;
      var first = points.get(index); var next = points.get(index + 1);
      int dx = next.method_10263() - first.method_10263();
      int dy = next.method_10264() - first.method_10264();
      int dz = next.method_10260() - first.method_10260();
      for (int i = index + 1; i < end; i++) {
         var a = points.get(i); var b = points.get(i + 1);
         if (b.method_10263() - a.method_10263() != dx || b.method_10264() - a.method_10264() != dy
            || b.method_10260() - a.method_10260() != dz) return i;
      }
      return end;
   }

   public static boolean safePath(class_3218 world, class_1308 mob, class_11 path) {
      if (path == null) return false;
      var previous = mob.method_24515();
      // Validate the next local segment on every use, without rescanning a distant route each tick.
      for (int i = path.method_39(); i < Math.min(path.method_38(), path.method_39() + 8); i++) {
         var pos = path.method_31031(i);
         if (previous.method_10264() - pos.method_10264() > 1 || !x21dbjs9wlccy4(world, mob, pos)) return false;
         previous = pos;
      }
      return true;
   }

   public static boolean exposedPath(class_3218 world, class_11 path) {
      if (path == null) return false;
      for (int i = path.method_39(); i < Math.min(path.method_38(), path.method_39() + 24); i++)
         if (VillageSafety.edgePenalty(world, path.method_31031(i)) > 0) return true;
      return false;
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
                  || var8.method_26204() instanceof class_2349 && (var1 instanceof class_1646 || VillageDefense.x3di73z4zkdknf(var0, var7))
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
