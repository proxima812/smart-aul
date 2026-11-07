package net.smartaul.mixin;

import java.util.function.Supplier;
import net.minecraft.class_4148;
import net.minecraft.class_4149;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(class_4149.class)
public interface SensorTypeInvoker {
   @Invoker("<init>")
   static <U extends class_4148<?>> class_4149<U> smartAul$createSensorType(Supplier<U> var0) {
      throw new AssertionError("Mixin invoker was not applied");
   }
}
