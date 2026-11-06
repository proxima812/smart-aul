package net.smartcolonies.mixin;

import net.minecraft.class_1309;
import net.minecraft.class_4107;
import net.minecraft.class_7893;
import net.smartcolonies.r.x172amoveu75vj;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_4107.class)
public abstract class xbfkfe2ag7erz {
   @Inject(method = "method_46964", at = @At("RETURN"), cancellable = true)
   private static void x3iliq3cc086c3(CallbackInfoReturnable<class_7893<class_1309>> var0) {
      var0.setReturnValue(new x172amoveu75vj((class_7893)var0.getReturnValue(), true));
   }
}
