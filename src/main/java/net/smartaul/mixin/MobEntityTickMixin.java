package net.smartaul.mixin;

import java.util.Optional;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1308;
import net.minecraft.class_1439;
import net.minecraft.class_1646;
import net.minecraft.class_243;
import net.minecraft.class_3218;
import net.smartaul.village.VillagerNavigation;
import net.smartaul.village.ColonyManager;
import net.smartaul.village.VillageThreats;
import net.smartaul.village.GolemPillarBreaking;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1308.class)
public abstract class MobEntityTickMixin {
   @Inject(method = "method_5958", at = @At("TAIL"))
   private void smartAul$onMobTickTail(class_3218 var1, CallbackInfo var2) {
      if ((Object)this instanceof class_1439 var3) {
         var var4 = ColonyManager.x3psqezd5jem54(var1, var3.method_24515());
         if (var4.isPresent() && Math.floorMod(var1.method_75260() + var3.method_5628(), 20) == 0) {
            if (var3.method_6032() < var3.method_6063()) {
               var3.method_6025(2.0F);
            }

            var3.method_37222(new class_1293(class_1294.field_5898, 240, 0, false, false), var3);
            var3.method_6073(Math.min(var3.method_52541(), var3.method_6067() + 1.0F));
         }

         if (var3.method_5799()) {
            class_243 var5 = var3.method_18798();
            class_243 var6 = var3.method_5968() != null ? var3.method_5968().method_73189() : null;
            if (var6 == null) {
               var6 = ColonyManager.x3psqezd5jem54(var1, var3.method_24515()).map(var0 -> class_243.method_24953(var0.core())).orElse(var3.method_73189());
            }

            class_243 var7 = var6.method_1020(var3.method_73189());
            double var8 = Math.sqrt(var7.field_1352 * var7.field_1352 + var7.field_1350 * var7.field_1350);
            double var10 = var8 > 0.01 ? 0.075 : 0.0;
            var3.method_5796(true);
            var3.method_18800(
               var8 > 0.01 ? var7.field_1352 / var8 * var10 : var5.field_1352 * 0.5,
               Math.max(var5.field_1351, 0.11),
               var8 > 0.01 ? var7.field_1350 / var8 * var10 : var5.field_1350 * 0.5
            );
         } else if (var3.method_5681()) {
            var3.method_5796(false);
         }

         if ((var1.method_75260() + var3.method_5628()) % 4L == 0L) {
            ColonyManager.x3psqezd5jem54(var1, var3.method_24515())
               .ifPresent(
                  var2x -> {
                     if (var3.method_5968() != null && !VillageThreats.xs8awvrrlhug9(var2x, var3.method_5968())) {
                        var3.method_5980(null);
                        var3.method_5942().method_6340();
                     }

                     if (var3.method_5968() instanceof class_1646 var3x
                        && ColonyManager.x3psqezd5jem54(var1, var3x.method_24515()).map(var1xx -> var1xx.id().equals(var2x.id())).orElse(false)) {
                        var3.method_5980(null);
                     }

                     VillageThreats.x3kaxmxx3tadjj(var1, var2x).ifPresent(var2xx -> {
                        var3.method_5980(var2xx);
                        VillagerNavigation.x1wi30zgg7nf81(var1, var3, var2xx.method_24515(), 1.8);
                     });
                     GolemPillarBreaking.x1qwgvfo0xc7pp(var1, var3, var2x);
                  }
               );
         }
      }
   }
}
