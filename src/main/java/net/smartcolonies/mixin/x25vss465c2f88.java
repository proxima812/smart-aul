package net.smartcolonies.mixin;

import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_2818;
import net.minecraft.class_3218;
import net.smartcolonies.r.xyghu7rojfjwk;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_2818.class)
public abstract class x25vss465c2f88 {
   @Shadow
   @Final
   private class_1937 field_12858;

   @Inject(method = "method_12010", at = @At("RETURN"))
   private void x1f6pfyu5te63k(class_2338 var1, class_2680 var2, int var3, CallbackInfoReturnable<class_2680> var4) {
      if (this.field_12858 instanceof class_3218 var5) {
         xyghu7rojfjwk.x2d89mem7j8r8n(var5, var1, (class_2680)var4.getReturnValue(), var2);
      }
   }
}
