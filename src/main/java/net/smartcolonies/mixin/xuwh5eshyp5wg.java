package net.smartcolonies.mixin;

import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1536;
import net.minecraft.class_1646;
import net.minecraft.class_1676;
import net.minecraft.class_1802;
import net.minecraft.class_1937;
import net.minecraft.class_2604;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1536.class)
public abstract class xuwh5eshyp5wg extends class_1676 {
   protected xuwh5eshyp5wg(class_1299<? extends class_1676> var1, class_1937 var2) {
      super(var1, var2);
   }

   @Inject(method = "method_5773", at = @At("HEAD"), cancellable = true)
   private void x3aj70jh2zxi80(CallbackInfo var1) {
      if (this.method_24921() instanceof class_1646 var2) {
         super.method_5773();
         if (!var2.method_5805()
            || var2.method_6113()
            || var2.method_18009()
            || !var2.method_6047().method_31574(class_1802.field_8378)
            || this.method_5858(var2) > 64.0) {
            this.method_31472();
         }

         this.method_18800(0.0, 0.0, 0.0);
         var1.cancel();
      }
   }

   @Inject(method = "method_31471", at = @At("HEAD"), cancellable = true)
   private void x3ot4yxde0cp7y(class_2604 var1, CallbackInfo var2) {
      class_1297 var3 = this.method_73183().method_8469(var1.method_11166());
      if (var3 instanceof class_1646) {
         super.method_31471(var1);
         this.method_7432(var3);
         var2.cancel();
      }
   }
}
