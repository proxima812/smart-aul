package net.smartaul.client.render;

import net.minecraft.class_10034;
import net.minecraft.class_11659;
import net.minecraft.class_1306;
import net.minecraft.class_3883;
import net.minecraft.class_4587;
import net.minecraft.class_572;
import net.minecraft.class_989;

final class VillagerHeldItemFeature extends class_989<class_10034, class_572<class_10034>> {
   VillagerHeldItemFeature(class_3883<class_10034, class_572<class_10034>> var1) {
      super(var1);
   }

   public void render(class_4587 var1, class_11659 var2, int var3, class_10034 var4, float var5, float var6) {
      class_1306 var7 = var4.field_55303 == class_1306.field_6183 ? class_1306.field_6182 : class_1306.field_6183;
      this.method_4192(var4, var7 == class_1306.field_6182 ? var4.field_55307 : var4.field_55305, var4.method_75466(var7), var7, var1, var2, var3);
   }

   @Override
   public void method_17162(class_4587 var1, class_11659 var2, int var3, class_10034 var4, float var5, float var6) {
      render(var1, var2, var3, var4, var5, var6);
   }

   @Override
   public void method_4199(class_4587 var1, class_11659 var2, int var3, class_10034 var4, float var5, float var6) {
      render(var1, var2, var3, var4, var5, var6);
   }
}
