package net.smartaul.mixin;

import net.minecraft.class_1646;
import net.minecraft.class_3218;
import net.minecraft.class_4111;
import net.smartaul.village.ColonyManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_4111.class)
public abstract class VillagerBreedTaskMixin {
   @Inject(method = "method_19571", at = @At("HEAD"), cancellable = true)
   private void smartAul$onShouldRunHead(class_3218 var1, class_1646 var2, CallbackInfoReturnable<Boolean> var3) {
      if (!ColonyManager.xis8cbwd2sinx(var1, var2)) {
         var3.setReturnValue(false);
      }
   }
}
