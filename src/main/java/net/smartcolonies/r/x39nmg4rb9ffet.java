package net.smartcolonies.r;

import net.minecraft.class_10034;
import net.minecraft.class_10080;
import net.minecraft.class_11659;
import net.minecraft.class_11677;
import net.minecraft.class_3883;
import net.minecraft.class_3887;
import net.minecraft.class_4587;
import net.minecraft.class_5603;
import net.minecraft.class_5605;
import net.minecraft.class_5606;
import net.minecraft.class_5607;
import net.minecraft.class_5610;
import net.minecraft.class_572;
import net.minecraft.class_620;
import net.minecraft.class_970;
import net.minecraft.class_5617.class_5618;

public final class x39nmg4rb9ffet extends class_3887<class_10080, class_620> {
   private final class_572<class_10034> xgh13qspwnc6y = new class_572(
      class_5607.method_32110(class_572.method_32011(class_5605.field_27715, 0.0F), 64, 32).method_32109()
   );
   private final class_970<class_10034, class_572<class_10034>, x3eqv1sxys1fr3> x1aondm6sulwto;
   private final x21shfgvf0y2mn xbte8c97h0kev;

   public x39nmg4rb9ffet(class_3883<class_10080, class_620> var1, class_5618 var2) {
      super(var1);
      class_3883 var3 = () -> this.xgh13qspwnc6y;
      class_11677 var4 = class_572.method_72854(new class_5605(1.0F), new class_5605(0.5F))
         .method_72962(
            var0 -> {
               class_5610 var1x = var0.method_32111();
               class_5603 var2x = class_5603.method_32091(0.0F, 3.0F, -1.0F, -0.75F, 0.0F, 0.0F);
               class_5605 var3x = new class_5605(0.35F);
               var1x.method_32117("right_arm", class_5606.method_32108().method_32101(40, 16).method_32098(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, var3x), var2x);
               var1x.method_32117(
                  "left_arm", class_5606.method_32108().method_32101(40, 16).method_32096().method_32098(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, var3x), var2x
               );
               return new x3eqv1sxys1fr3(class_5607.method_32110(var0, 64, 32).method_32109());
            }
         );
      this.x1aondm6sulwto = new class_970(var3, var4, var2.method_64072());
      this.xbte8c97h0kev = new x21shfgvf0y2mn(var3);
   }

   public void render(class_4587 var1, class_11659 var2, int var3, class_10080 var4, float var5, float var6) {
      if (var4 instanceof xp08rwn075i54 var7 && !var4.field_53333) {
         class_10034 var8 = var7.x2wtazye3qgwz3;
         this.xgh13qspwnc6y.method_17087(var8);
         this.x1aondm6sulwto.method_17157(var1, var2, var3, var8, var5, var6);
         this.xbte8c97h0kev.render(var1, var2, var3, var8, var5, var6);
      }
   }

   @Override
   public void method_4199(class_4587 var1, class_11659 var2, int var3, class_10080 var4, float var5, float var6) {
      render(var1, var2, var3, var4, var5, var6);
   }
}
