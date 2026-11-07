package net.smartaul.mixin;

import net.minecraft.server.MinecraftServer;
import net.smartaul.village.ColonyManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerTickMixin {
   @Inject(method = "method_3782", at = @At("TAIL"))
   private void smartAul$onShutdownTail(CallbackInfo var1) {
      ColonyManager.x35h05qb6ebno5();
   }
}
