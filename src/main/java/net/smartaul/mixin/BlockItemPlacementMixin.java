package net.smartaul.mixin;

import java.util.UUID;
import net.minecraft.class_1747;
import net.minecraft.class_1750;
import net.minecraft.class_2244;
import net.minecraft.class_2323;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.smartaul.village.ProtectedLand;
import net.smartaul.village.ColonyWorldData;
import net.smartaul.village.LandClaimManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1747.class)
public abstract class BlockItemPlacementMixin {
   @Inject(method = "method_7708", at = @At("RETURN"))
   private void smartAul$onPlaceReturn(class_1750 var1, class_2680 var2, CallbackInfoReturnable<Boolean> var3) {
      if ((Boolean)var3.getReturnValue() && var1.method_8036() != null && var1.method_8045() instanceof class_3218 var4) {
         ColonyWorldData var8 = ColonyWorldData.get(var4);
         class_2338 var6 = var1.method_8037();
         UUID var7 = var1.method_8036().method_5667();
         LandClaimManager.x273xo57gqmrz9(var4, var1.method_8036(), var6, var2);
         var8.recordPlacement(var6, var7, var4.method_75260());
         if (var2.method_26204() instanceof class_2323) {
            var8.recordPlacement(var6.method_10084(), var7, var4.method_75260());
         }

         if (var2.method_26204() instanceof class_2244) {
            var8.recordPlacement(var6.method_10093((class_2350)var2.method_11654(class_2244.field_11177)), var7, var4.method_75260());
         }

         ProtectedLand.x1se6fg76pktcw(var4, var6);
         ProtectedLand.x34rm129tews28(var4);
      }
   }
}
