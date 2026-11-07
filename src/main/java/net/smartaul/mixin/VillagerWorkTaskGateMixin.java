package net.smartaul.mixin;

import net.minecraft.class_1646;
import net.minecraft.class_4114;
import net.minecraft.class_4129;
import net.minecraft.class_4295;
import net.minecraft.class_5326;
import net.minecraft.class_5327;
import net.minecraft.class_7893;
import net.smartaul.village.ColonyTaskGate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_4129.class)
public abstract class VillagerWorkTaskGateMixin {
   @Redirect(method = "method_19020", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_4295;method_47037()Lnet/minecraft/class_7893;"))
   private static class_7893<class_1646> smartAul$redirectCreateCoreTasksInvoke() {
      return new ColonyTaskGate(class_4295.method_47037());
   }

   @Redirect(method = "method_19020", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_4114;method_46887()Lnet/minecraft/class_7893;"))
   private static class_7893<class_1646> smartAul$redirectCreateCoreTasksInvoke2() {
      return new ColonyTaskGate(class_4114.method_46887());
   }

   @Redirect(method = "method_19020", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_5327;method_47207(F)Lnet/minecraft/class_7893;"))
   private static class_7893<class_1646> smartAul$redirectCreateCoreTasksInvoke3(float var0) {
      return new ColonyTaskGate(class_5327.method_47207(var0));
   }

   @Redirect(method = "method_19020", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_5326;method_47006()Lnet/minecraft/class_7893;"))
   private static class_7893<class_1646> smartAul$redirectCreateCoreTasksInvoke4() {
      return new ColonyTaskGate(class_5326.method_47006());
   }
}
