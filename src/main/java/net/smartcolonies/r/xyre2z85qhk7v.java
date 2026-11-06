package net.smartcolonies.r;

import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1320;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1890;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_5134;
import net.minecraft.class_5321;
import net.minecraft.class_6880;
import net.minecraft.class_7924;
import net.minecraft.class_8110;
import net.minecraft.class_9285;
import net.minecraft.class_9334;
import net.minecraft.class_6880.class_6883;

public final class xyre2z85qhk7v {
   private static final class_5321<class_8110> xraqws2b0ihjx = class_5321.method_29179(
      class_7924.field_42534, class_2960.method_60655("smart_colonies", "golem_attack")
   );

   private xyre2z85qhk7v() {
   }

   public static double x2rmpu6d2tdks1(class_1799 var0, class_6880<class_1320> var1, double var2) {
      return ((class_9285)var0.method_58695(class_9334.field_49636, class_9285.field_49326)).method_57481(var1, var2, class_1304.field_6173);
   }

   public static float x3c7ytg0gca1m6(class_3218 var0, class_1309 var1, class_1309 var2, class_1282 var3) {
      class_1799 var4 = var1.method_6047();
      float var5 = (float)Math.max(1.0, x2rmpu6d2tdks1(var4, class_5134.field_23721, 1.0));
      return class_1890.method_60120(var0, var4, var2, var3, var5);
   }

   public static int x3jcrekq98bvm8(class_1799 var0) {
      double var1 = Math.max(0.1, x2rmpu6d2tdks1(var0, class_5134.field_23723, 4.0));
      return Math.clamp((long)((int)Math.ceil(20.0 / var1)), 5, 200);
   }

   public static class_1282 xqzpg1xselqv5(class_3218 var0, class_1309 var1, class_1297 var2, class_1282 var3) {
      if (var2 instanceof class_1657 && !x2n55y2tom37h2.x3psqezd5jem54(var0, var1.method_24515()).isEmpty()) {
         class_6883 var4 = var0.method_30349().method_30530(class_7924.field_42534).method_46747(xraqws2b0ihjx);
         return new class_1282(var4, var1);
      } else {
         return var3;
      }
   }
}
