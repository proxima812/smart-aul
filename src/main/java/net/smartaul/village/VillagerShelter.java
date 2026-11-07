package net.smartaul.village;

import java.util.HashSet;
import java.util.Map;
import java.util.WeakHashMap;
import net.minecraft.class_11;
import net.minecraft.class_1646;
import net.minecraft.class_2246;
import net.minecraft.class_2281;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2488;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_4140;
import net.minecraft.class_2350.class_2353;

public final class VillagerShelter {
   private static final Map<class_1646, ShelterState> x53dla2m2sjpv = new WeakHashMap<>();

   private VillagerShelter() {
   }

   public static class_2338 x37u4pqy3fbps(class_1646 var0) {
      ShelterState var1 = x53dla2m2sjpv.get(var0);
      return var1 != null && var0.method_73183().method_75260() - var1.x2yeh8j3blv419 < 60L ? var1.x1zh0xo7suwqwi : null;
   }

   public static boolean xps35ehel6nkk(class_1646 var0, class_2338 var1, long var2) {
      ShelterState var4 = x53dla2m2sjpv.get(var0);
      return var4 != null && var4.x3l3snwia7ft28.getOrDefault(var1, 0L) > var2;
   }

   public static boolean x1f6ghn62xaxti(class_1646 var0, long var1) {
      ShelterState var3 = x53dla2m2sjpv.get(var0);
      return var3 != null
         && var1 - var3.x2yeh8j3blv419 < 60L
         && var3.x14c5jvrao50ds >= 12
         && var1 - var3.x1l7j1x9mqjydy >= 600L
         && var3.x27ooc1aah0m7v != null
         && var3.x27ooc1aah0m7v.method_1025(var0.method_73189()) < 16.0
         && var0.method_5942().method_6357()
         && var3.x1zh0xo7suwqwi != null
         && var0.method_24515().method_10262(var3.x1zh0xo7suwqwi) > 9.0;
   }

   public static void xi17ogl3h66v0(class_1646 var0, long var1) {
      ShelterState var3 = x53dla2m2sjpv.get(var0);
      if (var3 != null && var3.x1zh0xo7suwqwi != null) {
         var3.x3l3snwia7ft28.put(var3.x1zh0xo7suwqwi, var1 + 600L);
      }

      var0.method_5942().method_6340();
   }

   public static void x1uiixbj22gkfz(class_1646 var0, class_2338 var1, long var2) {
      ShelterState var4 = x53dla2m2sjpv.computeIfAbsent(var0, var0x -> new ShelterState());
      var4.x3l3snwia7ft28.put(var1.method_10062(), var2 + 200L);
      if (var1.equals(var4.x1zh0xo7suwqwi)) {
         var0.method_5942().method_6340();
      }
   }

   public static void x2fxoqmbs0henb(class_1646 var0) {
      x53dla2m2sjpv.remove(var0);
      var0.method_5942().method_6340();
      var0.method_18868().method_18875(class_4140.field_18445);
      var0.method_18868().method_18875(class_4140.field_18449);
   }

   public static boolean x2wzylgizxltef(class_3218 var0, class_1646 var1, class_2338 var2, double var3) {
      return x38akzuwnnzvsh(var0, var1, var2, var3, 6.0);
   }

   public static boolean x1lrdp6728c3ml(class_3218 var0, class_1646 var1, class_2338 var2, double var3) {
      return x38akzuwnnzvsh(var0, var1, var2, var3, 2.0);
   }

   public static boolean xf8lnb134kd0j(class_3218 var0, class_1646 var1, class_2338 var2, double var3) {
      return x38akzuwnnzvsh(var0, var1, var2, var3, 0.25);
   }

   public static boolean x1cssc4al5kdgu(class_3218 var0, class_1646 var1, class_2338 var2, double var3) {
      return x38akzuwnnzvsh(var0, var1, var2, var3, 1.0);
   }

   private static boolean x38akzuwnnzvsh(class_3218 var0, class_1646 var1, class_2338 var2, double var3, double var5) {
      var1.method_18868().method_18875(class_4140.field_18445);
      var1.method_18868().method_18875(class_4140.field_18449);
      ShelterState var7 = x53dla2m2sjpv.computeIfAbsent(var1, var0x -> new ShelterState());
      if (var7.x1wuut87u4s3dl != null && !VillagerNavigation.safePath(var0, var1, var7.x1wuut87u4s3dl)) {
         var1.method_5942().method_6340();
         var7.x1wuut87u4s3dl = null;
      }
      long var8 = var0.method_75260();
      if (var7.x27ooc1aah0m7v != null && var7.x27ooc1aah0m7v.method_1025(var1.method_73189()) >= 16.0) {
         var7.x27ooc1aah0m7v = null;
         var7.x14c5jvrao50ds = 0;
      }

      var7.x3l3snwia7ft28.entrySet().removeIf(var2x -> var2x.getValue() <= var8);
      boolean var10 = var2.equals(var7.x1zh0xo7suwqwi);
      boolean var11 = var8 - var7.x2yeh8j3blv419 < 60L;
      var7.x2yeh8j3blv419 = var8;
      if (var10 && var11 && var7.x9kftcrtna1an != null && !(var7.x9kftcrtna1an.method_1025(var1.method_73189()) >= 0.0625)) {
         if (var8 - var7.x27415kcgpc72a >= 100L && var1.method_5942().method_23966() && var2.method_10262(var1.method_24515()) > 2.25) {
            var7.x3l3snwia7ft28.put(var2.method_10062(), var8 + 200L);
            var1.method_5942().method_6340();
            var7.x1wuut87u4s3dl = null;
            var7.xz4s2nt8f9lq5 = var8 + 20L;
            var7.x27415kcgpc72a = var8;
            WorkDiagnostics.x1qd0mutwsr3sc(var1, "подход перекрыт: выбирается другая точка");
            return false;
         }
      } else {
         var7.x9kftcrtna1an = var1.method_73189();
         var7.x27415kcgpc72a = var8;
      }

      if (!var10) {
         boolean var12 = var7.x1zh0xo7suwqwi != null && var7.x1zh0xo7suwqwi.method_10262(var2) <= 9.0;
         var7.x1zh0xo7suwqwi = var2.method_10062();
         if (!var12) {
            var7.xe4qlumt6ojc9 = 0;
            var7.xz4s2nt8f9lq5 = var8;
         }
      }

      if (xps35ehel6nkk(var1, var2, var8)) {
         return false;
      } else if (var10 && var7.x1wuut87u4s3dl != null && var7.x1wuut87u4s3dl == var1.method_5942().method_6345() && var1.method_5942().method_23966() && VillagerNavigation.safePath(var0, var1, var7.x1wuut87u4s3dl)) {
         return true;
      } else if (var8 < var7.xz4s2nt8f9lq5) {
         return false;
      } else {
         var7.xz4s2nt8f9lq5 = var8 + 10L + Math.floorMod(var1.method_5628(), 10);
         if (!ColonyManager.x5ll9elqpk1a4(var0)) {
            return false;
         } else {
            var1.method_5942().method_70687(true);
            var1.method_5942().method_6354(true);
            var1.method_5942().method_61439(64.0F);
            var var20 = new HashSet<class_2338>();
            if (x9bnd6e2jczhd(var0, var2)) {
               var20.add(var2);
            } else {
               for (int var13 = -2; var13 <= 2; var13++) {
                  for (int var14 = -2; var14 <= 2; var14++) {
                     for (int var15 = -1; var15 <= 1; var15++) {
                        class_2338 var16 = var2.method_10069(var13, var15, var14);
                        if (var16.method_10262(var2) <= var5 && x9bnd6e2jczhd(var0, var16)) {
                           var20.add(var16);
                        }
                     }
                  }
               }
            }

            if (var20.isEmpty()) {
               var20.add(var2);
            }

            var1.method_5942().method_23964(var1.method_24515().method_10262(var2) > 576.0 ? 8.0F : 4.0F);

            class_11 var21;
            try {
               var21 = var1.method_5942().method_29934(var20, 0);
            } finally {
               var1.method_5942().method_23965();
            }

            if (var21 != null && !VillagerNavigation.safePath(var0, var1, var21)) {
               var1.method_5942().method_6340();
               var21 = null;
            }
            if (var21 == null || VillagerNavigation.exposedPath(var0, var21)) {
               var safeGoal = var20.stream().min(java.util.Comparator.comparingDouble(var1.method_24515()::method_10262)).orElse(var2);
               if (VillagerNavigation.x1wi30zgg7nf81(var0, var1, safeGoal, var3)) return true;
            }
            boolean var22 = var21 != null
               && var21.method_45() != null
               && var21.method_38() > 1
               && var21.method_45().method_22879().method_10262(var2) + 4.0 < var1.method_24515().method_10262(var2);
            if (var21 != null && (var21.method_21655() || var22) && var1.method_5942().method_6334(var21, var3)) {
               var7.x1wuut87u4s3dl = var21;
               var7.xe4qlumt6ojc9 = 0;
               if (var21.method_21655()) {
                  var7.x14c5jvrao50ds = 0;
                  var7.x27ooc1aah0m7v = null;
               }

               return true;
            } else {
               if (var7.x27ooc1aah0m7v == null || var7.x27ooc1aah0m7v.method_1025(var1.method_73189()) >= 16.0) {
                  var7.x27ooc1aah0m7v = var1.method_73189();
                  var7.x1l7j1x9mqjydy = var8;
                  var7.x14c5jvrao50ds = 0;
               }

               var7.x14c5jvrao50ds++;
               if (++var7.xe4qlumt6ojc9 >= 4) {
                  var7.x3l3snwia7ft28.put(var2.method_10062(), var8 + 200L);
                  var7.xe4qlumt6ojc9 = 0;
                  if (var0.method_8320(var2).method_26204() instanceof class_2281) {
                     for (class_2350 var24 : class_2353.field_11062) {
                        class_2338 var17 = var2.method_10093(var24);
                        if (var0.method_8320(var17).method_26204() instanceof class_2281) {
                           var7.x3l3snwia7ft28.put(var17, var8 + 200L);
                        }
                     }
                  }
               }

               WorkDiagnostics.x1qd0mutwsr3sc(var1, "нет доступного пути к " + var2.method_23854());
               return false;
            }
         }
      }
   }

   public static boolean x9bnd6e2jczhd(class_3218 var0, class_2338 var1) {
      if (!var0.method_14178().method_12123(var1.method_10263() >> 4, var1.method_10260() >> 4)) {
         return false;
      } else {
         class_2680 var2 = var0.method_8320(var1);
         boolean var3 = var2.method_27852(class_2246.field_10477) && (Integer)var2.method_11654(class_2488.field_11518) < 8;
         return var0.method_14178().method_12123(var1.method_10263() >> 4, var1.method_10260() >> 4)
            && var0.method_8316(var1).method_15769()
            && var0.method_8316(var1.method_10074()).method_15769()
            && (var2.method_26220(var0, var1).method_1110() || var3)
            && var0.method_8320(var1.method_10084()).method_26220(var0, var1.method_10084()).method_1110()
            && var0.method_8320(var1.method_10074()).method_26206(var0, var1.method_10074(), class_2350.field_11036);
      }
   }
}
