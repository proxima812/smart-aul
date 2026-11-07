package net.smartaul.mixin;

import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import net.minecraft.class_1314;
import net.minecraft.class_2338;
import net.minecraft.class_3218;
import net.minecraft.class_4096;
import net.minecraft.class_4140;
import net.minecraft.class_4158;
import net.minecraft.class_4208;
import net.minecraft.class_6880;
import net.minecraft.class_7893;
import net.smartaul.village.ColonyTaskGate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_4096.class)
public abstract class FindPointOfInterestTaskMixin {
   @Inject(method = "method_46883", at = @At("RETURN"), cancellable = true)
   private static void smartAul$onCreateReturn(
      Predicate<class_6880<class_4158>> var0,
      class_4140<class_4208> var1,
      class_4140<class_4208> var2,
      boolean var3,
      Optional<Byte> var4,
      BiPredicate<class_3218, class_2338> var5,
      CallbackInfoReturnable<class_7893<class_1314>> var6
   ) {
      if (var1 == class_4140.field_18439 || var1 == class_4140.field_25160 || var2 == class_4140.field_18439 || var2 == class_4140.field_25160) {
         var6.setReturnValue(new ColonyTaskGate((class_7893)var6.getReturnValue()));
      }
   }
}
