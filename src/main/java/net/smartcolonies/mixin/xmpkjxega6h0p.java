package net.smartcolonies.mixin;

import java.util.Optional;
import net.minecraft.class_1297;
import net.minecraft.class_1439;
import net.minecraft.class_1646;
import net.minecraft.class_1676;
import net.minecraft.class_3218;
import net.minecraft.class_3966;
import net.smartcolonies.r.x15kgoaq6i3m6i;
import net.smartcolonies.r.x1rxiy9mtu5b3h;
import net.smartcolonies.r.x2n55y2tom37h2;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1676.class)
public abstract class xmpkjxega6h0p {
   @Inject(method = "method_7454", at = @At("HEAD"), cancellable = true)
   private void x7nte3afhd17b(class_3966 var1, CallbackInfo var2) {
      class_1676 var3 = (class_1676)(Object)this;
      if (var3.method_73183() instanceof class_3218 var4 && var3.method_24921() instanceof class_1646 var5) {
         class_1297 var9 = var1.method_17782();
         if (var9 instanceof class_1646 || var9 instanceof class_1439) {
            var var7 = x2n55y2tom37h2.x3psqezd5jem54(var4, var5.method_24515());
            if (!var7.isEmpty() && ((x15kgoaq6i3m6i)var7.get()).roleOf(var5.method_5667()) == x1rxiy9mtu5b3h.GUARD) {
               if (x2n55y2tom37h2.x3psqezd5jem54(var4, var9.method_24515()).map(var1x -> var1x.id().equals(((x15kgoaq6i3m6i)var7.get()).id())).orElse(false)) {
                  var3.method_31472();
                  var2.cancel();
               }
            }
         }
      }
   }
}
