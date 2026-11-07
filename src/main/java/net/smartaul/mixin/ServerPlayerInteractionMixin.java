package net.smartaul.mixin;

import java.util.Map;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3225;
import net.smartaul.village.Colony;
import net.smartaul.village.ConstructionStatus;
import net.smartaul.village.VillageRepair;
import net.smartaul.village.ConstructionJob;
import net.smartaul.village.ColonyManager;
import net.smartaul.village.VillageThreats;
import net.smartaul.village.ColonyWorldData;
import net.smartaul.village.WorldEditScope;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_3225.class)
public abstract class ServerPlayerInteractionMixin {
   @Shadow
   protected class_3218 field_14007;
   @Shadow
   protected class_3222 field_14008;
   @Unique
   private Colony x1kxtmy73igiaq;
   @Unique
   private ConstructionJob x2z5231e1txc97;
   @Unique
   private boolean xq204v34j0ov;
   @Unique
   private Map<class_2338, class_2680> x1qw76x55rvpdd = Map.of();

   @Inject(method = "method_14266", at = @At("HEAD"))
   private void smartAul$onTryBreakBlockHead(class_2338 var1, CallbackInfoReturnable<Boolean> var2) {
      this.x1kxtmy73igiaq = null;
      this.x2z5231e1txc97 = null;
      this.xq204v34j0ov = false;
      this.x1qw76x55rvpdd = Map.of();
      ColonyManager.x3psqezd5jem54(this.field_14007, var1)
         .ifPresent(
            var2x -> {
               if (VillageRepair.x5epxvcc1p5ir(this.field_14007, var2x, var1)) {
                  this.x2z5231e1txc97 = var2x.x1ja61emeln2hc().stream().filter(var1xx -> var1xx.x24kbqv6g4kkrg().method_14662(var1)).findFirst().orElse(null);
                  this.xq204v34j0ov = this.x2z5231e1txc97 != null
                     && this.x2z5231e1txc97.x2s8emavervqrz()
                     && this.x2z5231e1txc97.x2nfrt8qxdscso().filter(this.field_14008.method_5667()::equals).isPresent();
                  this.x1qw76x55rvpdd = VillageRepair.xg03tmq830uoq(this.field_14007, var1);
                  if (!this.x1qw76x55rvpdd.isEmpty() || this.xq204v34j0ov) {
                     this.x1kxtmy73igiaq = var2x;
                  }
               }
            }
         );
   }

   @Inject(method = "method_14266", at = @At("RETURN"))
   private void smartAul$onTryBreakBlockReturn(class_2338 var1, CallbackInfoReturnable<Boolean> var2) {
      if ((Boolean)var2.getReturnValue()) {
         ColonyWorldData.get(this.field_14007).forgetPlacement(var1);
         if (this.x1kxtmy73igiaq != null) {
            if (this.xq204v34j0ov) {
               this.x1kxtmy73igiaq.x2xqc3q79vfkw3().remove(var1);
               this.x1qw76x55rvpdd.keySet().forEach(this.x1kxtmy73igiaq.x2xqc3q79vfkw3()::remove);
               this.x2z5231e1txc97.x3lcv8vebl7uhh();
            } else {
               VillageRepair.x17m23t077i8f8(this.field_14007, this.x1kxtmy73igiaq, this.x1qw76x55rvpdd);
               if (this.x2z5231e1txc97 != null && this.x2z5231e1txc97.x32oz43hmeynhc() == ConstructionStatus.COMPLETE) {
                  this.x2z5231e1txc97.xy78n60m5p9uj(this.field_14007.method_75260());
               }

               VillageThreats.xgnwo32dqwtgi(this.field_14007, this.x1kxtmy73igiaq, this.field_14008, 4);
            }

            ColonyWorldData.get(this.field_14007).method_80();
         }
      }

      this.x1kxtmy73igiaq = null;
      this.x2z5231e1txc97 = null;
      this.xq204v34j0ov = false;
      this.x1qw76x55rvpdd = Map.of();
   }

   @Redirect(method = "method_14266", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_3218;method_8650(Lnet/minecraft/class_2338;Z)Z"))
   private boolean smartAul$redirectTryBreakBlockInvoke(class_3218 var1, class_2338 var2, boolean var3) {
      return WorldEditScope.x2aebumvi646n9(() -> var1.method_8650(var2, var3));
   }
}
