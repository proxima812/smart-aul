package net.smartaul.mixin;

import net.minecraft.class_4168;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(class_4168.class)
public interface ActivityInvoker {
   @Invoker("method_19210")
   static class_4168 smartAul$register(String var0) {
      throw new AssertionError("Activity invoker was not transformed");
   }
}
