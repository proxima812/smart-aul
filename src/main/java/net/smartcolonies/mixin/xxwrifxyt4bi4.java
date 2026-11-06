package net.smartcolonies.mixin;

import net.minecraft.class_1646;
import net.minecraft.class_1725;
import net.minecraft.class_1727;
import net.minecraft.class_1799;
import net.minecraft.class_1914;
import net.minecraft.class_1915;
import net.minecraft.class_3218;
import net.smartcolonies.r.x1faxfib09uh4;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1727.class)
public abstract class xxwrifxyt4bi4 {
   @Shadow
   @Final
   private class_1915 field_7858;
   @Shadow
   @Final
   private class_1725 field_7860;

   @Inject(method = "method_7671", at = @At("HEAD"), cancellable = true)
   private void x2g0wc5ylgvp3y(int var1, CallbackInfoReturnable<class_1799> var2) {
      if (this.field_7858 instanceof class_1646 var3 && var3.method_73183() instanceof class_3218 var4) {
         class_1914 var7 = this.field_7860.method_7642();
         if (var7 != null && x1faxfib09uh4.xcynwq3uap918(var3, var7) && !x1faxfib09uh4.x1ujd85h9771yr(var4, var3, var7)) {
            var7.method_8254();
            this.field_7860.method_7645();
            var2.setReturnValue(class_1799.field_8037);
         }
      }
   }
}
