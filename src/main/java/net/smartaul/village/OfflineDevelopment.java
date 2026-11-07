package net.smartaul.village;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import net.minecraft.class_1263;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1646;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_3218;

public final class OfflineDevelopment {
   public static final int x2289wrswms5uz = 4;

   private OfflineDevelopment() {
   }

   private static boolean x1o3p2gzsi5ixs(class_3218 var0, class_2338 var1) {
      return var0.method_14178().method_12123(var1.method_10263() >> 4, var1.method_10260() >> 4) && !ProtectedLand.xic1z39wiy3pn(var0, var1);
   }

   public static int x31lz6msk67uxc(class_3218 var0, Colony var1) {
      int var2 = 0;

      for (class_2338 var4 : var1.x2ad3s8aentmo4()) {
         if (x1o3p2gzsi5ixs(var0, var4) && var0.method_8321(var4) instanceof class_1263 var5) {
            for (int var7 = 0; var7 < var5.method_5439(); var7++) {
               if (var5.method_5438(var7).method_31574(class_1802.field_8103)) {
                  var2 += var5.method_5438(var7).method_7947();
               }
            }
         }
      }

      return var2;
   }

   public static int x2m95xmyunf3vb(class_3218 var0, Colony var1) {
      int var2 = x31lz6msk67uxc(var0, var1);
      if (var2 >= 4) {
         return var2;
      } else {
         for (class_1646 var4 : var0.method_18023(class_1299.field_6077, ColonyManager.x150ccxomrlozr(var0, var1), class_1297::method_5805)) {
            if (ColonyManager.x3psqezd5jem54(var0, var4.method_24515()).orElse(null) == var1) {
               for (int var5 = 0; var5 < var4.method_35199().method_5439(); var5++) {
                  class_1799 var6 = var4.method_35199().method_5438(var5);
                  if (var6.method_31574(class_1802.field_8103)) {
                     var2 += var6.method_7947();
                  }
               }

               if (var2 >= 4) {
                  return var2;
               }
            }
         }

         return var2;
      }
   }

   public static int x2m8lyhoez9v7c(class_3218 var0, Colony var1) {
      int var2 = 4;
      int var3 = 0;
      var var4 = Collections.newSetFromMap(new IdentityHashMap<>());

      for (class_2338 var6 : var1.x2ad3s8aentmo4()) {
         if (x1o3p2gzsi5ixs(var0, var6) && var0.method_8321(var6) instanceof class_1263 var7 && var4.add(var7)) {
            var3 += x3tihjtlnbfyrs(var7, var2);

            for (int var9 = 0; var9 < var7.method_5439(); var9++) {
               if (var7.method_5438(var9).method_31574(class_1802.field_8103)) {
                  var2 = Math.max(0, var2 - var7.method_5438(var9).method_7947());
               }
            }
         }
      }

      return var3;
   }

   public static int x3tihjtlnbfyrs(class_1263 var0, int var1) {
      int var2 = Math.max(0, var1);
      int var3 = 0;
      class_1799 var4 = new class_1799(class_1802.field_8550);

      for (int var5 = 0; var5 < var0.method_5439(); var5++) {
         class_1799 var6 = var0.method_5438(var5);
         if (var6.method_31574(class_1802.field_8103)) {
            int var7 = Math.min(var2, var6.method_7947());
            var2 -= var7;
            int var8 = var6.method_7947() - var7;
            if (var8 != 0) {
               int var9 = 0;

               for (int var10 = 0; var10 < var0.method_5439(); var10++) {
                  class_1799 var11 = var0.method_5438(var10);
                  if (var11.method_7960()) {
                     var9 += var0.method_58350(var4);
                  } else if (class_1799.method_31577(var11, var4)) {
                     var9 += Math.max(0, var0.method_58350(var4) - var11.method_7947());
                  }
               }

               if (var7 == 0 && var9 + var0.method_58350(var4) >= var8) {
                  var9 += var0.method_58350(var4);
               }

               int var15 = Math.min(var8, var9);
               if (var15 != 0) {
                  var6.method_7934(var15);
                  if (var6.method_7960()) {
                     var0.method_5447(var5, class_1799.field_8037);
                  }

                  class_1799 var16 = new class_1799(class_1802.field_8550, var15);

                  for (int var12 = 0; var12 < var0.method_5439() && !var16.method_7960(); var12++) {
                     class_1799 var13 = var0.method_5438(var12);
                     if (class_1799.method_31577(var13, var16)) {
                        int var14 = Math.min(var16.method_7947(), Math.max(0, var0.method_58350(var16) - var13.method_7947()));
                        var13.method_7933(var14);
                        var16.method_7934(var14);
                     }
                  }

                  for (int var17 = 0; var17 < var0.method_5439() && !var16.method_7960(); var17++) {
                     if (var0.method_5438(var17).method_7960()) {
                        int var18 = Math.min(var16.method_7947(), var0.method_58350(var16));
                        var0.method_5447(var17, var16.method_46651(var18));
                        var16.method_7934(var18);
                     }
                  }

                  var3 += var15;
                  var0.method_5431();
               }
            }
         }
      }

      return var3;
   }
}
