package net.smartaul.village;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.WeakHashMap;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1439;
import net.minecraft.class_1548;
import net.minecraft.class_1588;
import net.minecraft.class_1646;
import net.minecraft.class_1657;
import net.minecraft.class_238;
import net.minecraft.class_3218;
import net.minecraft.class_4168;

public final class VillageThreats {
   public static final int xwho32kpnb881 = 32;
   public static final int x2vgg2qsnxoqv0 = 12;
   private static final double xqhki5nz20q5b = 144.0;
   private static final Map<Colony, ThreatCache> x2smtlzj5x8if5 = new WeakHashMap<>();
   private static long x1h1idtou36wre;

   public static long x1xumgud6xlrp4() {
      return x1h1idtou36wre;
   }

   public static void x3didvqu3hbx58() {
      x2smtlzj5x8if5.clear();
      x1h1idtou36wre = 0L;
      VillagerNavigation.x26rn6haaqjid6();
   }

   private VillageThreats() {
   }

   public static Optional<class_1309> x3ekrcznmkym0j(class_3218 var0, Colony var1) {
      var var2 = var1.x10fb3wo7mboo(var0.method_75260())
         .<class_1297>map(var0::method_66347)
         .filter(var0x -> var0x instanceof class_1309)
         .map(var0x -> (class_1309)var0x)
         .filter(VillageThreats::x1bj0phudo3a5m)
         .filter(var1x -> xs8awvrrlhug9(var1, var1x));
      if (var2.isPresent()) {
         return var2;
      } else {
         var var3 = var1.x2npriys7z2eli(var0.method_75260())
            .<class_1657>map(var0::method_18470)
            .filter(VillageThreats::x1bj0phudo3a5m)
            .filter(var1x -> xs8awvrrlhug9(var1, var1x))
            .map(var0x -> (class_1309)var0x);
         return var3.isPresent()
            ? var3
            : var0.method_8390(class_1646.class, xx2tq85gza9uw(var1), class_1309::method_5805)
               .stream()
               .filter(var2x -> ColonyManager.x3psqezd5jem54(var0, var2x.method_24515()).map(var1xx -> var1xx.id().equals(var1.id())).orElse(false))
               .filter(var0x -> var0x.field_6012 - var0x.method_6117() <= 100)
               .<class_1309>map(class_1309::method_6065)
               .filter(VillageThreats::x1bj0phudo3a5m)
               .filter(var1x -> xs8awvrrlhug9(var1, var1x))
               .findFirst();
      }
   }

   public static boolean x1bj0phudo3a5m(class_1309 var0) {
      return var0 != null
         && var0.method_5805()
         && !(var0 instanceof class_1548)
         && !(var0 instanceof class_1646)
         && !(var0 instanceof class_1439)
         && !(var0 instanceof class_1657 var1 && (var1.method_68878() || var1.method_7325()));
   }

   public static boolean xs8awvrrlhug9(Colony var0, class_1309 var1) {
      int var2 = var0.radius();
      double var3 = var1.method_23317() - (var0.core().method_10263() + 0.5);
      double var5 = var1.method_23321() - (var0.core().method_10260() + 0.5);
      return var3 * var3 + var5 * var5 <= var2 * var2 && Math.abs(var1.method_23318() - var0.core().method_10264()) <= 12.0;
   }

   public static class_238 xx2tq85gza9uw(Colony var0) {
      int var1 = var0.radius();
      return new class_238(var0.core()).method_1009(var1, 12.0, var1);
   }

   public static Optional<class_1309> x3kaxmxx3tadjj(class_3218 var0, Colony var1) {
      ThreatCache var2 = x2smtlzj5x8if5.get(var1);
      long var3 = var0.method_75260();
      if (var2 != null
         && var2.x137xpxqz8kwz() == var0
         && var3 >= var2.xfdtg8nu348d5()
         && var3 - var2.xfdtg8nu348d5() < 5L
         && var2.x3d4l1o8shgghr().filter(var1x -> !x1bj0phudo3a5m(var1x) || !xs8awvrrlhug9(var1, var1x)).isEmpty()) {
         return var2.x3d4l1o8shgghr();
      } else {
         x1h1idtou36wre++;
         var var5 = x2d6o0suqhd9mu(var0, var1);
         x2smtlzj5x8if5.put(var1, new ThreatCache(var0, var3, var5));
         return var5;
      }
   }

   private static Optional<class_1309> x2d6o0suqhd9mu(class_3218 var0, Colony var1) {
      var var2 = x3ekrcznmkym0j(var0, var1);
      if (var2.isPresent()) {
         return var2;
      } else {
         var var3 = var0.method_8390(
            class_1646.class,
            xx2tq85gza9uw(var1),
            var2x -> var2x.method_5805()
               && ColonyManager.x3psqezd5jem54(var0, var2x.method_24515()).map(var1xx -> var1xx.id().equals(var1.id())).orElse(false)
         );
         var var4 = var0.method_8390(class_1439.class, xx2tq85gza9uw(var1), class_1309::method_5805);
         return var0.method_8390(class_1588.class, xx2tq85gza9uw(var1), var0x -> var0x.method_5805() && !(var0x instanceof class_1548))
            .stream()
            .filter(var3x -> xs8awvrrlhug9(var1, var3x) && x2dm767cry9ux8(var3x, var3, var4))
            .min(
               Comparator.<class_1588>comparingInt(
                     var1x -> var1x.method_5968() instanceof class_1646 var2x && var1.roleOf(var2x.method_5667()) == ColonyRole.GUARD
                        ? 0
                        : (var1x.method_5968() instanceof class_1646 ? 1 : 2)
                  )
                  .thenComparingDouble(var1x -> var1x.method_24515().method_10262(var1.core()))
            )
            .map(var0x -> (class_1309)var0x);
      }
   }

   private static boolean x2dm767cry9ux8(class_1588 var0, List<class_1646> var1, List<class_1439> var2) {
      class_1309 var3 = var0.method_5968();
      return (!(var3 instanceof class_1646) || !var1.contains(var3)) && (!(var3 instanceof class_1439) || !var2.contains(var3))
         ? var1.stream()
               .anyMatch(var1x -> var1x.method_5858(var0) <= 144.0 && Math.abs(var1x.method_23318() - var0.method_23318()) <= 6.0 && var1x.method_6057(var0))
            || var2.stream()
               .anyMatch(var1x -> var1x.method_5858(var0) <= 144.0 && Math.abs(var1x.method_23318() - var0.method_23318()) <= 6.0 && var1x.method_6057(var0))
         : true;
   }

   public static void xgnwo32dqwtgi(class_3218 var0, Colony var1, class_1657 var2, int var3) {
      if (var3 > 0 && !var2.method_68878() && !var2.method_7325()) {
         x2smtlzj5x8if5.remove(var1);
         var1.x2jh70rewptpw1(var2.method_5667(), var0.method_75260() + 1200L, var3);
         var1.xz6ij33c0780z(var0.method_75260());
         xhannuplm2isb(var0, var1, var2);
         ColonyManager.xchl1k1tnh3w8(var0).method_80();
      }
   }

   public static void x9xz66k4m5ee9(class_3218 var0, Colony var1, class_1309 var2) {
      if (x1bj0phudo3a5m(var2) && xs8awvrrlhug9(var1, var2)) {
         x2smtlzj5x8if5.remove(var1);
         var1.x8tht6cvqikr(var2.method_5667(), var0.method_75260() + 1200L);
         var1.xz6ij33c0780z(var0.method_75260());
         xhannuplm2isb(var0, var1, var2);
         ColonyManager.xchl1k1tnh3w8(var0).method_80();
      }
   }

   private static void xhannuplm2isb(class_3218 var0, Colony var1, class_1309 var2) {
      var0.method_8390(class_1439.class, xx2tq85gza9uw(var1), class_1309::method_5805)
         .stream()
         .filter(var2x -> ColonyManager.x3psqezd5jem54(var0, var2x.method_24515()).map(var1xx -> var1xx.id().equals(var1.id())).orElse(false))
         .forEach(var2x -> {
            var2x.method_5980(var2);
            VillagerNavigation.x1wi30zgg7nf81(var0, var2x, var2.method_24515(), 1.9);
         });
      var0.method_8390(class_1646.class, xx2tq85gza9uw(var1), class_1309::method_5805)
         .stream()
         .filter(var1x -> var1.roleOf(var1x.method_5667()) == ColonyRole.GUARD)
         .forEach(var2x -> {
            if (var2x.method_6113()) {
               var2x.method_18400();
            }

            if (var2x.method_18009()) {
               var2x.method_8259(null);
            }

            var2x.method_18868().method_24525(ColonyMemories.x14b5znl8ndblc, var2, 1200L);
            var2x.method_18868().method_24526(class_4168.field_22396);
            VillagerNavigation.x1wi30zgg7nf81(var0, var2x, var2.method_24515(), 1.15);
         });
   }
}
