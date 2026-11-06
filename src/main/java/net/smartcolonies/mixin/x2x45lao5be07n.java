package net.smartcolonies.mixin;

import java.util.function.Supplier;
import net.minecraft.class_4148;
import net.minecraft.class_4149;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(class_4149.class)
public interface x2x45lao5be07n {
   @Invoker("<init>")
   static <U extends class_4148<?>> class_4149<U> x3nufuz5z25vn0(Supplier<U> var0) {
      throw new AssertionError("Mixin invoker was not applied");
   }
}
