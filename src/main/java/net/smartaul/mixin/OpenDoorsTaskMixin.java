package net.smartaul.mixin;

import net.minecraft.class_1309;
import net.minecraft.class_4107;
import net.minecraft.class_7893;
import net.smartaul.village.ColonyTaskGate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_4107.class)
public abstract class OpenDoorsTaskMixin {
   @Inject(method = "method_46964", at = @At("RETURN"), cancellable = true)
   private static void smartAul$onCreateReturn(CallbackInfoReturnable<class_7893<class_1309>> var0) {
      var0.setReturnValue(new ColonyTaskGate((class_7893)var0.getReturnValue(), true));
   }
}
