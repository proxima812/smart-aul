package net.smartaul.mixin;

import net.minecraft.class_10080;
import net.minecraft.class_620;
import net.minecraft.class_630;
import net.smartaul.client.render.ArmoredVillagerRenderState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_620.class)
public abstract class VillagerModelMixin {
   @Shadow
   @Final
   private class_630 field_55117;
   @Shadow
   @Final
   private class_630 field_27527;
   @Unique
   private class_630 x2kuxqci27a0xm;

   @Inject(method = "<init>", at = @At("RETURN"))
   private void smartAul$onInitializeReturn(class_630 var1, CallbackInfo var2) {
      this.x2kuxqci27a0xm = var1.method_32086("body");
   }

   @Inject(method = "method_62123", at = @At("RETURN"))
   private void smartAul$onSetAnglesReturn(class_10080 var1, CallbackInfo var2) {
      if (var1 instanceof ArmoredVillagerRenderState var3) {
         this.field_55117.field_3665 = true;
         if (this.field_27527.method_41919("hat")) {
            this.field_27527.method_32086("hat").field_3665 = var3.x2wtazye3qgwz3.field_55309.method_7960();
         }

         this.x2kuxqci27a0xm.method_32086("jacket").field_3665 = var3.x2wtazye3qgwz3.field_53419.method_7960();
      }
   }
}
