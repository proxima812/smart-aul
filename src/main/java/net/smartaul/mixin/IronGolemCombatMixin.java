package net.smartaul.mixin;

import java.util.Optional;
import net.minecraft.class_1282;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1439;
import net.minecraft.class_1646;
import net.minecraft.class_1657;
import net.minecraft.class_3218;
import net.smartaul.village.Colony;
import net.smartaul.village.ColonyManager;
import net.smartaul.village.GolemDamageHooks;
import net.smartaul.village.VillageThreats;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1439.class)
public abstract class IronGolemCombatMixin {
   @Unique
   private class_1297 x2vmufm3ulqp0w;

   @ModifyArg(
      method = "method_6121",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1297;method_64397(Lnet/minecraft/class_3218;Lnet/minecraft/class_1282;F)Z"),
      index = 1
   )
   private class_1282 xjl73cpm54dpl(class_1282 var1) {
      class_1439 var2 = (class_1439)(Object)this;
      return GolemDamageHooks.x3fmeiovmxy173((class_3218)var2.method_73183(), var2, this.x2vmufm3ulqp0w, var1);
   }

   @Inject(method = "method_64397", at = @At("HEAD"), cancellable = true)
   private void smartAul$onDamageHead(class_3218 var1, class_1282 var2, float var3, CallbackInfoReturnable<Boolean> var4) {
      if (var2.method_5529() instanceof class_1646 var5 && this.sameColony(var1, var5)) {
         var4.setReturnValue(false);
      }
   }

   @Inject(method = "method_64397", at = @At("RETURN"))
   private void smartAul$onDamageReturn(class_3218 var1, class_1282 var2, float var3, CallbackInfoReturnable<Boolean> var4) {
      class_1439 var5 = (class_1439)(Object)this;
      if ((Boolean)var4.getReturnValue() && var2.method_5529() instanceof class_1309 var6 && VillageThreats.x1bj0phudo3a5m(var6)) {
         ColonyManager.x3psqezd5jem54(var1, var5.method_24515()).ifPresent(var2x -> VillageThreats.x9xz66k4m5ee9(var1, var2x, var6));
      }
   }

   @Inject(method = "method_6121", at = @At("HEAD"), cancellable = true)
   private void smartAul$onTryAttackHead(class_3218 var1, class_1297 var2, CallbackInfoReturnable<Boolean> var3) {
      this.x2vmufm3ulqp0w = var2;
      if (var2 instanceof class_1646 var4 && this.sameColony(var1, var4)) {
         ((class_1439)(Object)this).method_5980(null);
         var3.setReturnValue(false);
      }
   }

   @Inject(method = "method_6121", at = @At("RETURN"))
   private void smartAul$onTryAttackReturn(class_3218 var1, class_1297 var2, CallbackInfoReturnable<Boolean> var3) {
      class_1439 var4 = (class_1439)(Object)this;
      if ((Boolean)var3.getReturnValue() && var2 instanceof class_1657 var5 && ColonyManager.x3psqezd5jem54(var1, var4.method_24515()).isPresent()) {
         var5.method_37222(new class_1293(class_1294.field_5909, 60, 0), var4);
      }

      this.x2vmufm3ulqp0w = null;
   }

   private boolean sameColony(class_3218 var1, class_1646 var2) {
      class_1439 var3 = (class_1439)(Object)this;
      var var4 = ColonyManager.x3psqezd5jem54(var1, var3.method_24515());
      var var5 = ColonyManager.x3psqezd5jem54(var1, var2.method_24515());
      return var4.isPresent() && var5.isPresent() && ((Colony)var4.get()).id().equals(((Colony)var5.get()).id());
   }
}
