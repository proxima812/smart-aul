package net.smartcolonies.r;

import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2818;
import net.minecraft.class_2826;
import net.minecraft.class_3218;
import net.minecraft.class_3449;
import net.minecraft.class_7045;
import net.minecraft.class_2338.class_2339;

public final class x2s667aisw18ww {
   private x2s667aisw18ww() {
   }

   public static void xuo3itsmzrpvl(class_3218 var0, class_2818 var1) {
      class_2826[] var2 = var1.method_12006();
      class_2339 var3 = new class_2339();
      int var4 = var1.method_12004().method_8326();
      int var5 = var1.method_12004().method_8328();
      int var6 = var1.method_31607();

      for (int var7 = 0; var7 < var2.length; var7++) {
         class_2826 var8 = var2[var7];
         if (!var8.method_38292() && var8.method_19523(var0x -> var0x.method_27852(class_2246.field_16332))) {
            int var9 = var6 + var7 * 16;

            for (int var10 = 0; var10 < 16; var10++) {
               for (int var11 = 0; var11 < 16; var11++) {
                  for (int var12 = 0; var12 < 16; var12++) {
                     if (var8.method_12254(var12, var10, var11).method_27852(class_2246.field_16332)) {
                        var3.method_10103(var4 + var12, var9 + var10, var5 + var11);
                        x18a1web1ghx4e(var0, var3.method_10062());
                     }
                  }
               }
            }
         }
      }
   }

   private static void x18a1web1ghx4e(class_3218 var0, class_2338 var1) {
      class_3449 var2 = var0.method_27056().method_57560(var1, class_7045.field_37045);
      if (var2 != class_3449.field_16713 && var2.method_16657()) {
         if (var0.method_27056().method_41033(var1, var2)) {
            x2n55y2tom37h2.x3ermfoq0kwcv0(var0, var1, var2.method_14969());
         }
      }
   }
}
