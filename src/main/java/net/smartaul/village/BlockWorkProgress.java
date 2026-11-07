package net.smartaul.village;

import net.smartaul.config.DevelopmentPolicy;
import net.smartaul.config.SmartAulConfig;

import java.util.Map;
import java.util.WeakHashMap;
import net.minecraft.class_1268;
import net.minecraft.class_1646;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_3218;

public final class BlockWorkProgress {
   private static final Map<class_1646, Long> x2pz5vwhc9swi4 = new WeakHashMap<>();
   private class_2338 xh3z7y4r6eog0;
   private class_2680 x3gbvo2clj2uxy;
   private long x5u2w3drumyur;
   private int xw22danc9z9hx;

   public static boolean x1fhuggzt16ek2(class_1646 var0, long var1) {
      return x2pz5vwhc9swi4.getOrDefault(var0, Long.MIN_VALUE) >= var1;
   }

   public static int x20ocn113pbk5f(class_3218 var0, class_1646 var1, class_2338 var2) {
      class_2680 var3 = var0.method_8320(var2);
      float var4 = var3.method_26214(var0, var2);
      double var5 = Math.max(1.0F, var1.method_6047().method_7924(var3));
      return Math.clamp((long)((int)Math.ceil(Math.max(0.0F, var4) * 30.0F / var5)), SmartAulConfig.x2by5f9ehxvwk9().work.minimum_break_ticks, 200);
   }

   public boolean xpohy1q30uxlk(class_3218 var1, class_1646 var2, class_2338 var3, int var4, boolean var5) {
      class_2680 var6 = var1.method_8320(var3);
      if (!var3.equals(this.xh3z7y4r6eog0) || !var6.equals(this.x3gbvo2clj2uxy)) {
         this.xupxzzyodxtwj(var1, var2);
         this.xh3z7y4r6eog0 = var3.method_10062();
         this.x3gbvo2clj2uxy = var6;
         this.x5u2w3drumyur = var1.method_75260();
         this.xw22danc9z9hx = DevelopmentPolicy.delay(var4, SmartAulConfig.x2by5f9ehxvwk9().village.development_speed);
      }

      long var7 = var1.method_75260() - this.x5u2w3drumyur;
      x2pz5vwhc9swi4.put(var2, var1.method_75260() + 20L);
      if (var7 % 6L == 0L) {
         var2.method_6104(class_1268.field_5808);
      }

      if (var5) {
         var1.method_8517(var2.method_5628(), this.xh3z7y4r6eog0, (int)Math.min(9L, var7 * 10L / this.xw22danc9z9hx));
      }

      if (var7 < this.xw22danc9z9hx) {
         return false;
      } else {
         this.xupxzzyodxtwj(var1, var2);
         x2pz5vwhc9swi4.put(var2, var1.method_75260() + 20L);
         return true;
      }
   }

   public void xupxzzyodxtwj(class_3218 var1, class_1646 var2) {
      x2pz5vwhc9swi4.remove(var2);
      if (this.xh3z7y4r6eog0 != null) {
         var1.method_8517(var2.method_5628(), this.xh3z7y4r6eog0, -1);
      }

      this.xh3z7y4r6eog0 = null;
      this.x3gbvo2clj2uxy = null;
   }
}
