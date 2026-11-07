package net.smartaul.village;

import net.minecraft.class_2338;
import net.minecraft.class_3218;

public final class BuildingSiteValidator {
   private BuildingSiteValidator() {
   }

   public static boolean x14gj5yhqspcfw(class_3218 var0, Colony var1, class_2338 var2) {
      if (var1.x2o79xnwz4g9nh(var2, 1)) {
         return false;
      } else if (ProtectedLand.x3gqyq7yie9jxr(var0, var2)) {
         return false;
      } else if (VillageRepair.x239t1m3hfi74d(var0, var2) || VillageRepair.x239t1m3hfi74d(var0, var2.method_10084())) {
         return false;
      } else if (!var1.x2a24huns14rw4(var2, 3) && !x10bn1mdrsybs8(var1, var2)) {
         for (Colony var4 : ColonyManager.xchl1k1tnh3w8(var0).colonies()) {
            if (!var4.id().equals(var1.id()) && (var4.x2a24huns14rw4(var2, 3) || x10bn1mdrsybs8(var4, var2))) {
               return false;
            }
         }

         for (class_2338 var6 : class_2338.method_10097(var2.method_10069(-1, -2, -1), var2.method_10069(1, 2, 1))) {
            if (!var0.method_14178().method_12123(var6.method_10263() >> 4, var6.method_10260() >> 4)) {
               return false;
            }

            if (TerrainSupport.x3hqfrterusj2u(var0, var6)) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   private static boolean x10bn1mdrsybs8(Colony var0, class_2338 var1) {
      return var0.x1n1a2vp01kxzf()
         .stream()
         .anyMatch(var1x -> Math.abs(var1x.method_10263() - var1.method_10263()) <= 1 && Math.abs(var1x.method_10260() - var1.method_10260()) <= 1);
   }
}
