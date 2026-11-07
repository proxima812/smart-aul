package net.smartaul.mixin;

import net.minecraft.class_1308;
import net.minecraft.class_14;
import net.minecraft.class_1439;
import net.minecraft.class_1646;
import net.minecraft.class_2338;
import net.minecraft.class_2349;
import net.minecraft.class_3218;
import net.minecraft.class_7;
import net.minecraft.class_8;
import net.minecraft.class_9316;
import net.smartaul.village.Colony;
import net.smartaul.village.VillageDefense;
import net.smartaul.village.ColonyManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_14.class)
public abstract class LandPathNodeMakerMixin extends class_8 {
   @Inject(method = "method_17", at = @At("HEAD"), cancellable = true)
   private void smartAul$onGetDefaultNodeTypeHead(class_9316 var1, int var2, int var3, int var4, CallbackInfoReturnable<class_7> var5) {
      class_1308 var6 = this.field_33;
      if (var6 != null && var6.method_73183() instanceof class_3218 var7) {
         class_2338 var12 = new class_2338(var2, var3, var4);
         boolean var9 = VillageDefense.x3di73z4zkdknf(var7, var12);
         boolean var10 = var6 instanceof class_1646 var11
            && var7.method_8320(var12).method_26204() instanceof class_2349
            && (ColonyManager.x3psqezd5jem54(var7, var11.method_24515()).isPresent() || ColonyManager.x2yj2v4iudqlvb(var7, var11.method_5667()).isPresent())
            && ColonyManager.x3psqezd5jem54(var7, var12)
               .flatMap(Colony::x329rgb9lpsari)
               .map(var1x -> !var1x.method_10076(5).equals(var12))
               .orElse(true);
         if ((var6 instanceof class_1646 || var6 instanceof class_1439) && var9 || var10) {
            var5.setReturnValue(class_7.field_12);
         }
      }
   }
}
