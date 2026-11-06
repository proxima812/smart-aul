package net.smartcolonies.mixin;

import net.minecraft.class_1282;
import net.minecraft.class_1309;
import net.minecraft.class_1646;
import net.minecraft.class_3218;
import net.minecraft.class_8103;
import net.minecraft.class_8111;
import net.smartcolonies.r.x2n55y2tom37h2;
import net.smartcolonies.r.x313o9woxa22fw;
import net.smartcolonies.r.x35kkrl6uz1e8h;
import net.smartcolonies.r.x37idyr79ed75m;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1309.class)
public abstract class x3gehxcu4100o9 {
   @Inject(method = "method_6078", at = @At("HEAD"))
   private void x1gg6fo9ceuak1(class_1282 var1, CallbackInfo var2) {
      if ((Object)this instanceof class_1646 var3 && var3.method_73183() instanceof class_3218 var4) {
         x313o9woxa22fw.xw6o9aon4s40m(var4, var3);
      }
   }

   @Inject(method = "method_64397", at = @At("RETURN"))
   private void xhs2l1aufu5ek(class_3218 var1, class_1282 var2, float var3, CallbackInfoReturnable<Boolean> var4) {
      if ((Boolean)var4.getReturnValue() && var3 > 0.0F && (Object)this instanceof class_1646 var5 && var2.method_5529() instanceof class_1309 var6) {
         x2n55y2tom37h2.x3psqezd5jem54(var1, var5.method_24515()).ifPresent(var2x -> x37idyr79ed75m.x9xz66k4m5ee9(var1, var2x, var6));
      }
   }

   @Inject(method = "method_64397", at = @At("HEAD"), cancellable = true)
   private void x1i7i9y2zs5yd0(class_3218 var1, class_1282 var2, float var3, CallbackInfoReturnable<Boolean> var4) {
      if ((Object)this instanceof class_1646 var5) {
         boolean var6 = var2.method_49708(class_8111.field_42340);
         boolean var7 = var2.method_48789(class_8103.field_42250) && var3 >= var5.method_6032();
         if (var6 || var7) {
            x2n55y2tom37h2.x3psqezd5jem54(var1, var5.method_24515()).ifPresent(var3x -> {
               if (x35kkrl6uz1e8h.xdo71dbv16zut(var1, var3x, var5)) {
                  var4.setReturnValue(false);
               }
            });
         }
      }
   }
}
