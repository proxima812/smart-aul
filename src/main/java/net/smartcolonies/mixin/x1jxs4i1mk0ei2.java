package net.smartcolonies.mixin;

import net.minecraft.class_4168;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(class_4168.class)
public interface x1jxs4i1mk0ei2 {
   @Invoker("method_19210")
   static class_4168 x3arpyl63fzrty(String var0) {
      throw new AssertionError("Activity invoker was not transformed");
   }
}
