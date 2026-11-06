package net.smartcolonies.r;

import net.minecraft.class_10034;
import net.minecraft.class_572;
import net.minecraft.class_630;

final class x3eqv1sxys1fr3 extends class_572<class_10034> {
   x3eqv1sxys1fr3(class_630 var1) {
      super(var1);
   }

   public void method_17087(class_10034 var1) {
      super.method_17087(var1);
      this.field_3398.field_3656 = this.field_3394.field_3656 = -2.0F;
      this.field_3391.field_37940 = 1.55F;
      this.field_3392.field_3657 = -2.0F;
      this.field_3397.field_3657 = 2.0F;
      this.field_3392.field_3654 = (float)Math.cos(var1.field_53450 * 0.6662F) * 0.7F * var1.field_53451;
      this.field_3397.field_3654 = (float)Math.cos(var1.field_53450 * 0.6662F + Math.PI) * 0.7F * var1.field_53451;

      for (class_630 var5 : new class_630[]{this.field_3401, this.field_27433}) {
         var5.field_3657 = 0.0F;
         var5.field_3656 = 3.0F;
         var5.field_3655 = -1.0F;
         var5.field_3654 = -0.75F;
         var5.field_3675 = var5.field_3674 = 0.0F;
      }
   }

   @Override
   public void method_2819(class_10034 var1) {
      method_17087(var1);
   }
}
