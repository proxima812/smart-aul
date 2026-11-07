package net.smartaul.mixin;

import java.util.List;
import net.minecraft.class_10442;
import net.minecraft.class_3887;
import net.minecraft.class_922;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_922.class)
public interface LivingEntityRendererAccessor {
   @Accessor("field_4738")
   List<class_3887<?, ?>> smartAul$getFeatures();

   @Accessor("field_55298")
   class_10442 smartAul$getItemModelResolver();
}
