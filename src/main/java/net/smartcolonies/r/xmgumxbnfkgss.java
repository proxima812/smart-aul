package net.smartcolonies.r;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import net.minecraft.class_1429;
import net.minecraft.class_1646;
import net.minecraft.class_1799;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3481;
import net.minecraft.class_3489;
import net.minecraft.class_7923;
import net.minecraft.class_2902.class_2903;

public final class xmgumxbnfkgss {
   private static final Map<x15kgoaq6i3m6i, x3u9kd91x2xq1p> x1bniuvikkeslx = new WeakHashMap<>();

   private xmgumxbnfkgss() {
   }

   public static void x1cgirut0dfx4w(class_3218 var0, x15kgoaq6i3m6i var1) {
      x3u9kd91x2xq1p var2 = x1bniuvikkeslx.computeIfAbsent(var1, var0x -> new x3u9kd91x2xq1p());
      if (var2.x1o4o8xboni3e8 == Long.MIN_VALUE) {
         var2.x1o4o8xboni3e8 = var0.method_75260();
         var2.xlbk4c97ghjy = var1.cachedFood();
         var2.xrosgbbvdp82b = var1.cachedLogs();
         var2.x1yo2g8rhr59ei = var1.cachedStone();
      }

      if (var0.method_75260() - var2.x1o4o8xboni3e8 >= 600L) {
         var2.x3nmfccx25rnli = var1.cachedFood() - var2.xlbk4c97ghjy;
         var2.x13n7yetjru0p4 = var1.cachedLogs() - var2.xrosgbbvdp82b;
         var2.xi51lceq0w0wj = var1.cachedStone() - var2.x1yo2g8rhr59ei;
         var2.xlbk4c97ghjy = var1.cachedFood();
         var2.xrosgbbvdp82b = var1.cachedLogs();
         var2.x1yo2g8rhr59ei = var1.cachedStone();
         var2.x1o4o8xboni3e8 = var0.method_75260();
      }

      int var3 = Math.min(96, var1.radius() - 8);
      int var4 = var3 / 4 + 1;
      int var5 = var4 * var4;
      if (var2.x18cylnq6b3mht >= var5) {
         var2.x18cylnq6b3mht = 0;
         var2.xpfb01pmvz35k = 0;
         var2.x3flapherkr2s2 = var2.x15em0agwht2le = var2.x219nexa31oys0 = 0;
      }

      for (int var6 = 0; var6 < 64 && var2.x18cylnq6b3mht < var5; var2.x18cylnq6b3mht++) {
         int var7 = var1.core().method_10263() - var3 + var2.x18cylnq6b3mht % var4 * 8;
         int var8 = var1.core().method_10260() - var3 + var2.x18cylnq6b3mht / var4 * 8;
         if (var0.method_14178().method_12123(var7 >> 4, var8 >> 4)) {
            var2.xpfb01pmvz35k++;
            class_2338 var9 = new class_2338(var7, var0.method_8624(class_2903.field_13197, var7, var8) - 1, var8);
            class_2680 var10 = var0.method_8320(var9);
            if (var10.method_26164(class_3481.field_15503) || var10.method_26164(class_3481.field_15475)) {
               var2.x3flapherkr2s2++;
            }

            if (var10.method_27852(class_2246.field_10382) && var9.method_10262(var1.core().method_33096(var9.method_10264())) <= 2304.0) {
               var2.x15em0agwht2le++;
            }

            if (var10.method_26164(class_3481.field_29822) || var10.method_27852(class_2246.field_10362)) {
               var2.x219nexa31oys0++;
            }
         }

         var6++;
      }

      if (var2.x18cylnq6b3mht >= var5) {
         var2.xk34jfx0ry30w = var2.xpfb01pmvz35k == var5;
         var2.xn8fkkfabhplq = var2.x3flapherkr2s2;
         var2.xn6juy1i2cuj8 = var2.x15em0agwht2le;
         var2.x207nyec47ataq = var2.x219nexa31oys0;
      }

      if (var2.x2cfskbx6c4wj9 == Long.MIN_VALUE || var0.method_75260() - var2.x2cfskbx6c4wj9 >= 600L) {
         var2.x2uy4yads13li4 = var0.method_8390(class_1429.class, x2n55y2tom37h2.x150ccxomrlozr(var0, var1), var0x -> var0x.method_5805()).size();
         var2.x2cfskbx6c4wj9 = var0.method_75260();
      }
   }

   public static void x2hptx3oklbfn2(x15kgoaq6i3m6i var0, EnumMap<x1rxiy9mtu5b3h, Integer> var1) {
      x3u9kd91x2xq1p var2 = x1bniuvikkeslx.get(var0);
      if (var2 != null) {
         boolean var3 = var0.cachedFood() < Math.max(48, var0.population() * 8);
         if (var2.xk34jfx0ry30w) {
            if (var2.xn8fkkfabhplq == 0) {
               var1.put(x1rxiy9mtu5b3h.LUMBERJACK, Math.min(15, (Integer)var1.get(x1rxiy9mtu5b3h.LUMBERJACK)));
            }

            if (var2.xn6juy1i2cuj8 == 0) {
               var1.put(x1rxiy9mtu5b3h.FISHERMAN, 10);
            }

            if (var2.x207nyec47ataq == 0 && var0.x1hdthtpd2qb8g().isEmpty()) {
               var1.put(x1rxiy9mtu5b3h.FARMER, 15);
            }
         }

         if (var2.x2uy4yads13li4 < 2 && var0.x329rgb9lpsari().isEmpty()) {
            var1.put(x1rxiy9mtu5b3h.RANCHER, 10);
         }

         if (var3 && var2.x3nmfccx25rnli < 0) {
            for (x1rxiy9mtu5b3h var5 : List.of(x1rxiy9mtu5b3h.FARMER, x1rxiy9mtu5b3h.FISHERMAN, x1rxiy9mtu5b3h.RANCHER)) {
               if ((Integer)var1.get(var5) > 15) {
                  var1.put(var5, Math.min(100, (Integer)var1.get(var5) + 15));
               }
            }
         }

         if (var0.cachedLogs() < 32 && var2.x13n7yetjru0p4 < 0 && (Integer)var1.get(x1rxiy9mtu5b3h.LUMBERJACK) > 15) {
            var1.put(x1rxiy9mtu5b3h.LUMBERJACK, 100);
         }

         if (var0.cachedStone() < 96 && var2.xi51lceq0w0wj < 0) {
            var1.put(x1rxiy9mtu5b3h.MINER, 100);
         }

         if (x3sqkg3n6v972r.x2by5f9ehxvwk9().roles.enable_village_walls && !var0.x2f7z1gnnht5ab().x1q0gb1h69ywu7.isEmpty()) {
            var1.put(x1rxiy9mtu5b3h.BUILDER, Math.max(60, (Integer)var1.get(x1rxiy9mtu5b3h.BUILDER)));
            if (var0.cachedStone() < 256) {
               var1.put(x1rxiy9mtu5b3h.MINER, Math.max(85, (Integer)var1.get(x1rxiy9mtu5b3h.MINER)));
            }
         }
      }
   }

   public static boolean x2hrr4jjriuh85(class_3218 var0, x15kgoaq6i3m6i var1, class_1646 var2, long var3) {
      if (!var2.method_6113() && !var2.method_18009() && !x36u1fhs2z6uoi.active(var0, var1) && !var1.x3hurh64xwtw51(var2.method_5667())) {
         if (var3 - var1.x2bp8u9p8dnt9e(var2.method_5667()) < 2400L) {
            return false;
         } else if (var1.x1ja61emeln2hc()
            .stream()
            .anyMatch(
               var1x -> (var1x.x32oz43hmeynhc() == x20r5v7z43p3d5.QUEUED || var1x.x32oz43hmeynhc() == x20r5v7z43p3d5.BUILDING)
                  && var1x.x3vu8mnvg0blk4().filter(var2.method_5667()::equals).isPresent()
            )) {
            return false;
         } else {
            boolean var5 = var2.method_18868().method_27074().stream().noneMatch(var0x -> var0x.getClass().getName().startsWith("net.smartcolonies."))
               || x2vaj7rsm57i4n.x1z41ec22rzeo2(var2, 600L) && var2.method_5942().method_6357();
            int var6 = 0;

            for (int var7 = 0; var7 < var2.method_35199().method_5439(); var7++) {
               class_1799 var8 = var2.method_35199().method_5438(var7);
               if (var8.method_31573(class_3489.field_15539)
                  || var8.method_31573(class_3489.field_25808)
                  || class_7923.field_41178.method_10221(var8.method_7909()).method_12832().startsWith("raw_")) {
                  var6 += var8.method_7947();
               }
            }

            return var5 && var6 <= (var1.roleOf(var2.method_5667()) == x1rxiy9mtu5b3h.MINER ? 16 : 0);
         }
      } else {
         return false;
      }
   }

   public static void xw6l4sc9khw02(x15kgoaq6i3m6i var0, class_1646 var1, long var2) {
      var0.xvamv2rzn6h0g(var1.method_5667(), var2);
   }

   public static String x32tk2361rdt82(x15kgoaq6i3m6i var0) {
      x3u9kd91x2xq1p var1 = x1bniuvikkeslx.get(var0);
      return var1 == null
         ? "разведка не начата"
         : "разведка="
            + (var1.xk34jfx0ry30w ? "готова" : "идёт")
            + ", лес="
            + var1.xn8fkkfabhplq
            + ", вода="
            + var1.xn6juy1i2cuj8
            + ", почва="
            + var1.x207nyec47ataq
            + ", животных="
            + var1.x2uy4yads13li4
            + ", изменение запасов еды/дерева/камня за 30с="
            + var1.x3nmfccx25rnli
            + "/"
            + var1.x13n7yetjru0p4
            + "/"
            + var1.xi51lceq0w0wj;
   }
}
