package net.smartaul.village;

import java.util.Comparator;
import java.util.Map;
import java.util.WeakHashMap;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1547;
import net.minecraft.class_1646;
import net.minecraft.class_1665;
import net.minecraft.class_1802;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_3218;

public final class ProjectileDefense {
   private static final Map<class_1646, Long> x36r0gqxc0c4p4 = new WeakHashMap<>();
   private static final Map<class_1646, ThreatVisibilityCache> x35xo3a8r0bq7r = new WeakHashMap<>();
   private static final Map<Colony, ProjectileThreatCache> x8qmioahtujgp = new WeakHashMap<>();
   private static long x2m5hd6j88izsr;

   public static long x6x64hgghjnq6() {
      return x2m5hd6j88izsr;
   }

   public static void x30zm3b0hljw9q() {
      x36r0gqxc0c4p4.clear();
      x35xo3a8r0bq7r.clear();
      x8qmioahtujgp.clear();
      x2m5hd6j88izsr = 0L;
   }

   private ProjectileDefense() {
   }

   public static boolean x1dlmo6wmzb2ly(class_1646 var0, class_1665 var1) {
      return var1.method_24921() != var0 && !var1.method_31481() ? xxrhfgph156lx(var0, var1) < Double.POSITIVE_INFINITY && var0.method_6057(var1) : false;
   }

   private static double xxrhfgph156lx(class_1646 var0, class_1665 var1) {
      class_243 var2 = var1.method_18798();
      if (var2.method_1027() < 0.04) {
         return Double.POSITIVE_INFINITY;
      } else {
         class_243 var3 = var1.method_73189();
         class_238 var4 = var0.method_5829().method_1014(0.2);
         double var5 = var1.method_5799() ? 0.6 : 0.99;

         for (int var7 = 0; var7 < 24; var7++) {
            class_243 var8 = var3.method_1019(var2);
            if (var4.method_1006(var3) || var4.method_992(var3, var8).isPresent()) {
               return var7;
            }

            var3 = var8;
            var2 = var2.method_1021(var5).method_1031(0.0, -var1.method_56989(), 0.0);
         }

         return Double.POSITIVE_INFINITY;
      }
   }

   public static boolean x12ghbsk7tys8r(class_3218 var0, class_1646 var1, Colony var2) {
      if (var2.roleOf(var1.method_5667()) == ColonyRole.GUARD
         && !var1.method_6113()
         && !var1.method_18009()
         && var1.method_6079().method_31574(class_1802.field_8255)) {
         long var3 = var0.method_75260();
         ThreatVisibilityCache var5 = x35xo3a8r0bq7r.get(var1);
         if (var5 != null && var5.x2uroynf8b4qnl() == var3) {
            return var5.xxr5jl7q1ghs0();
         } else {
            ProjectileThreatCache var6 = x8qmioahtujgp.get(var2);
            if (var6 == null || var6.x3pwphu5fgb0hr() != var0 || var6.x3tdytvdbh7xq2() != var3) {
               x2m5hd6j88izsr++;
               var6 = new ProjectileThreatCache(
                  var0,
                  var3,
                  var0.method_8390(
                     class_1665.class,
                     VillageThreats.xx2tq85gza9uw(var2).method_1014(32.0),
                     var0x -> !var0x.method_31481() && var0x.method_18798().method_1027() >= 0.04
                  )
               );
               x8qmioahtujgp.put(var2, var6);
            }

            class_1665 var7 = var6.x1j7ge6q8c5e6g()
               .stream()
               .filter(
                  var2x -> x1dlmo6wmzb2ly(var1, var2x)
                     && !(var2x.method_24921() instanceof class_1646 var3x && var2.roleOf(var3x.method_5667()) == ColonyRole.GUARD)
               )
               .min(Comparator.comparingDouble(var1x -> x1inv2pzkj8ftu(var1, var1x)))
               .orElse(null);
            class_1309 var8 = null;
            if (var7 == null) {
               class_1309 var9 = (class_1309)var1.method_18868().method_18904(ColonyMemories.x14b5znl8ndblc).orElse(null);
               if (var9 != null
                  && var9.method_5805()
                  && var1.method_5858(var9) <= 576.0
                  && var1.method_6057(var9)
                  && (
                     var9.method_6115() && (var9.method_6030().method_31574(class_1802.field_8102) || var9.method_6030().method_31574(class_1802.field_8399))
                        || var9 instanceof class_1547 var10
                           && var10.method_5968() == var1
                           && var10.method_6047().method_31574(class_1802.field_8102)
                           && Math.floorMod(var3, 40) < 20
                  )) {
                  var8 = var9;
               }
            }

            if (var7 != null || var8 != null) {
               class_1297 var15 = var7 != null ? var7 : var8;
               double var16 = var15.method_23317() - var1.method_23317();
               double var12 = var15.method_23321() - var1.method_23321();
               float var14 = (float)(Math.toDegrees(Math.atan2(var12, var16)) - 90.0);
               var1.method_36456(var14);
               var1.method_5636(var14);
               var1.method_5847(var14);
               var1.method_5988().method_6226((class_1297)var15, 90.0F, 90.0F);
               x36r0gqxc0c4p4.put(var1, var3 + (var7 == null && !var8.method_6115() ? 6 : 12));
            }

            if (var3 >= x36r0gqxc0c4p4.getOrDefault(var1, Long.MIN_VALUE)) {
               if (x36r0gqxc0c4p4.remove(var1) != null && var1.method_6115() && var1.method_6058() == class_1268.field_5810) {
                  var1.method_6075();
               }

               x35xo3a8r0bq7r.put(var1, new ThreatVisibilityCache(var3, false));
               return false;
            } else {
               if (!var1.method_6115() || var1.method_6058() != class_1268.field_5810) {
                  var1.method_6075();
                  var1.method_6019(class_1268.field_5810);
               }

               var1.method_5942().method_6340();
               WorkDiagnostics.x1qd0mutwsr3sc(var1, "прикрывается щитом от стрелы");
               x35xo3a8r0bq7r.put(var1, new ThreatVisibilityCache(var3, true));
               return true;
            }
         }
      } else {
         return false;
      }
   }

   private static double x1inv2pzkj8ftu(class_1646 var0, class_1665 var1) {
      return xxrhfgph156lx(var0, var1);
   }
}
