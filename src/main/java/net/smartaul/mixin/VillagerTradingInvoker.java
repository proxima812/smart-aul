package net.smartaul.mixin;

import net.minecraft.class_1646;
import net.minecraft.class_1657;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(class_1646.class)
public interface VillagerTradingInvoker {
   @Invoker("method_19191")
   void smartAul$beginTradeWith(class_1657 var1);
}
