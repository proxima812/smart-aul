package net.smartcolonies.mixin;

import net.minecraft.class_1646;
import net.minecraft.class_3218;
import net.minecraft.class_4111;
import net.smartcolonies.r.x2n55y2tom37h2;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_4111.class)
public abstract class x42bj8l8yz8hz {
   @Inject(method = "method_19571", at = @At("HEAD"), cancellable = true)
   private void xoe4r49nvirkm(class_3218 var1, class_1646 var2, CallbackInfoReturnable<Boolean> var3) {
      if (!x2n55y2tom37h2.xis8cbwd2sinx(var1, var2)) {
         var3.setReturnValue(false);
      }
   }
}
