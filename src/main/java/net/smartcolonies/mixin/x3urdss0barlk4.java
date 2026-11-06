package net.smartcolonies.mixin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2513;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_5819;
import net.minecraft.class_2350.class_2353;
import net.smartcolonies.r.x15kgoaq6i3m6i;
import net.smartcolonies.r.x2fvuqnp9zks2x;
import net.smartcolonies.r.x2n55y2tom37h2;
import net.smartcolonies.r.x3d5vk1zj3fvkj;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_2513.class)
public abstract class x3urdss0barlk4 {
   @Inject(method = "method_9514", at = @At("HEAD"), cancellable = true)
   private void x2lk3osvzflfm7(class_2680 var1, class_3218 var2, class_2338 var3, class_5819 var4, CallbackInfo var5) {
      if (!x3d5vk1zj3fvkj.get(var2).isPlayerBlock(var3)) {
         x2n55y2tom37h2.x3psqezd5jem54(var2, var3).ifPresent(var3x -> {
            if (!x2fvuqnp9zks2x.x14gj5yhqspcfw(var2, var3x, var3.method_10074()) || xtwcee6bxkzkm(var2, var3).isEmpty()) {
               var5.cancel();
            }
         });
      }
   }

   @Redirect(
      method = "method_9514",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2350$class_2353;method_10183(Lnet/minecraft/class_5819;)Lnet/minecraft/class_2350;")
   )
   private class_2350 x3fxui0ux025wm(class_2353 var1, class_5819 var2, class_2680 var3, class_3218 var4, class_2338 var5, class_5819 var6) {
      if (!x3d5vk1zj3fvkj.get(var4).isPlayerBlock(var5) && !x2n55y2tom37h2.x3psqezd5jem54(var4, var5).isEmpty()) {
         var var7 = xtwcee6bxkzkm(var4, var5);
         return var7.isEmpty() ? var1.method_10183(var2) : (class_2350)var7.get(var2.method_43048(var7.size()));
      } else {
         return var1.method_10183(var2);
      }
   }

   @Unique
   private static List<class_2350> xtwcee6bxkzkm(class_3218 var0, class_2338 var1) {
      var var2 = x2n55y2tom37h2.x3psqezd5jem54(var0, var1);
      var var3 = new ArrayList<class_2350>();

      for (class_2350 var5 : class_2353.field_11062) {
         if (var2.isEmpty() || x2fvuqnp9zks2x.x14gj5yhqspcfw(var0, (x15kgoaq6i3m6i)var2.get(), var1.method_10074().method_10093(var5))) {
            var3.add(var5);
         }
      }

      return var3;
   }
}
