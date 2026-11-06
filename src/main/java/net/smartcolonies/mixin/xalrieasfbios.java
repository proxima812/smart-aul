package net.smartcolonies.mixin;

import net.minecraft.class_10026;
import net.minecraft.class_1536;
import net.minecraft.class_1646;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_4604;
import net.minecraft.class_906;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_906.class)
public abstract class xalrieasfbios {
   @Inject(method = "method_62442", at = @At("RETURN"), cancellable = true)
   private void x2lnx8tf9xctws(class_1536 var1, class_4604 var2, double var3, double var5, double var7, CallbackInfoReturnable<Boolean> var9) {
      if (var1.method_24921() instanceof class_1646) {
         var9.setReturnValue(var1.method_5640(var1.method_5649(var3, var5, var7)) && var2.method_23093(var1.method_5829().method_1014(1.0)));
      }
   }

   @Inject(method = "method_62443", at = @At("RETURN"))
   private void x19hsepigvngv1(class_1536 var1, class_10026 var2, float var3, CallbackInfo var4) {
      if (var1.method_24921() instanceof class_1646 var5) {
         float var8 = class_3532.method_17821(var3, var5.field_6220, var5.field_6283) * (float) (Math.PI / 180.0);
         class_243 var7 = var5.method_30950(var3).method_1019(new class_243(-0.25, 1.15, 0.45).method_1024(-var8));
         var2.field_53370 = var7.method_1020(var1.method_30950(var3).method_1031(0.0, 0.25, 0.0));
      }
   }
}
