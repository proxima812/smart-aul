package net.smartcolonies.mixin;

import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2614;
import net.minecraft.class_2615;
import net.minecraft.class_3218;
import net.minecraft.class_3532;
import net.smartcolonies.r.x1pk6h11cwmsba;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_2614.class)
public abstract class x3q50tg4x7kpfk {
   @Inject(method = "method_11241", at = @At("HEAD"), cancellable = true)
   private static void xkwkf0w6v8o70(class_1937 var0, class_2615 var1, CallbackInfoReturnable<Boolean> var2) {
      if (var0 instanceof class_3218 var3) {
         class_2338 var4 = new class_2338(
            class_3532.method_15357(var1.method_11266()), class_3532.method_15357(var1.method_11264() + 1.0), class_3532.method_15357(var1.method_11265())
         );
         if (x1pk6h11cwmsba.x33zofxkqevoo8(var3, var4)) {
            var2.setReturnValue(false);
         }
      }
   }
}
