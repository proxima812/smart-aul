package net.smartaul.mixin;

import net.minecraft.class_2533;
import net.minecraft.class_8177;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_2533.class)
public interface TrapdoorBlockAccessor {
   @Accessor("field_42779")
   class_8177 smartAul$getBlockSetType();
}
