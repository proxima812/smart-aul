package net.smartaul.mixin;

import net.minecraft.class_10034;
import net.minecraft.class_10080;
import net.minecraft.class_1268;
import net.minecraft.class_1306;
import net.minecraft.class_1646;
import net.minecraft.class_1802;
import net.minecraft.class_3883;
import net.minecraft.class_620;
import net.minecraft.class_909;
import net.minecraft.class_963;
import net.minecraft.class_5617.class_5618;
import net.minecraft.class_572.class_573;
import net.smartaul.client.render.VillagerArmorFeature;
import net.smartaul.client.render.ArmoredVillagerRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_963.class)
public abstract class VillagerRendererMixin {
   @Inject(method = "<init>", at = @At("RETURN"))
   private void smartAul$onInitializeReturn(class_5618 var1, CallbackInfo var2) {
      ((LivingEntityRendererAccessor)this).smartAul$getFeatures().add(new VillagerArmorFeature((class_3883<class_10080, class_620>)this, var1));
   }

   @Inject(method = "method_62568", at = @At("HEAD"), cancellable = true)
   private void smartAul$onCreateRenderStateHead(CallbackInfoReturnable<class_10080> var1) {
      var1.setReturnValue(new ArmoredVillagerRenderState());
   }

   @Inject(method = "method_62567", at = @At("RETURN"))
   private void smartAul$onUpdateRenderStateReturn(class_1646 var1, class_10080 var2, float var3, CallbackInfo var4) {
      if (var2 instanceof ArmoredVillagerRenderState var5) {
         class_10034 var6 = var5.x2wtazye3qgwz3;
         class_909.method_62461(var1, var6, var3, ((LivingEntityRendererAccessor)this).smartAul$getItemModelResolver());
         var6.field_53328 = var2.field_53328;
         var6.field_53457 = var2.field_53457;
         var6.field_53465 = var2.field_53465;
         var6.field_53447 = var2.field_53447;
         var6.field_53448 = var2.field_53448;
         var6.field_53446 = var2.field_53446;
         var6.field_53450 = var2.field_53450;
         var6.field_53451 = var2.field_53451;
         var6.field_53453 = var2.field_53453;
         var6.field_53454 = var2.field_53454;
         var6.field_53460 = var2.field_53460;
         var6.field_53333 = var2.field_53333;
         var6.field_61821 = var2.field_61821;
         var6.field_55304 = var6.field_63601.method_7960() ? class_573.field_3409 : class_573.field_3410;
         var6.field_55306 = var6.field_63602.method_7960() ? class_573.field_3409 : class_573.field_3410;
         if (var1.method_6115() && var1.method_6030().method_31574(class_1802.field_8255)) {
            class_1306 var7 = var1.method_6058() == class_1268.field_5808 ? var1.method_6068() : var1.method_6068().method_5928();
            if (var7 == class_1306.field_6182) {
               var6.field_55306 = class_573.field_3406;
            } else {
               var6.field_55304 = class_573.field_3406;
            }
         }

         if (var1.method_6115() && var1.method_6030().method_31574(class_1802.field_8102)) {
            if (var1.method_6068() == class_1306.field_6183) {
               var6.field_55304 = class_573.field_3403;
            } else {
               var6.field_55306 = class_573.field_3403;
            }
         }
      }
   }
}
