package net.smartcolonies.mixin;

import java.util.Map;
import java.util.UUID;
import net.minecraft.class_1263;
import net.minecraft.class_1707;
import net.minecraft.class_2813;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3244;
import net.smartcolonies.r.x15kgoaq6i3m6i;
import net.smartcolonies.r.x1pk6h11cwmsba;
import net.smartcolonies.r.x2n55y2tom37h2;
import net.smartcolonies.r.x2x8dxxurg9b7p;
import net.smartcolonies.r.x37idyr79ed75m;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_3244.class)
public abstract class x2vgik63bzhq9q {
   @Shadow
   public class_3222 field_14140;
   @Unique
   private UUID x3a415rzf5zerx;
   @Unique
   private Map<x2x8dxxurg9b7p, Integer> xw2sp1abe7j8z;
   @Unique
   private class_1263 x1q6yox0rpcgbf;

   @Inject(method = "method_12076", at = @At("HEAD"))
   private void x1hy1igt7xg7w1(class_2813 var1, CallbackInfo var2) {
      class_3218 var4 = this.field_14140.method_51469();
      if (var4 instanceof class_3218 && var4.method_8503().method_18854()) {
         this.x3a415rzf5zerx = null;
         this.xw2sp1abe7j8z = null;
         this.x1q6yox0rpcgbf = null;
         if (this.field_14140.field_7512 instanceof class_1707 var6) {
            x2n55y2tom37h2.xchl1k1tnh3w8(var4)
               .colonies()
               .stream()
               .filter(var2x -> x1pk6h11cwmsba.xu27zvtjfruut(var4, var2x, var6.method_7629()))
               .findFirst()
               .ifPresent(var2x -> {
                  this.x3a415rzf5zerx = var2x.id();
                  this.x1q6yox0rpcgbf = var6.method_7629();
                  this.xw2sp1abe7j8z = x1pk6h11cwmsba.x2l7fon36lgwu2(this.x1q6yox0rpcgbf);
               });
         }
      }
   }

   @Inject(method = "method_12076", at = @At("RETURN"))
   private void x1wv6ie5tgrumw(class_2813 var1, CallbackInfo var2) {
      if (this.field_14140.method_51469().method_8503().method_18854()) {
         if (this.x3a415rzf5zerx != null && this.xw2sp1abe7j8z != null) {
            class_3218 var4 = this.field_14140.method_51469();
            if (var4 instanceof class_3218) {
               x15kgoaq6i3m6i var6 = x2n55y2tom37h2.xchl1k1tnh3w8(var4).byId(this.x3a415rzf5zerx).orElse(null);
               if (var6 != null) {
                  int var5 = x1pk6h11cwmsba.x2qxayij05nw0s(this.xw2sp1abe7j8z, x1pk6h11cwmsba.x2l7fon36lgwu2(this.x1q6yox0rpcgbf));
                  if (var5 > 0) {
                     x37idyr79ed75m.xgnwo32dqwtgi(var4, var6, this.field_14140, var5);
                  }
               }

               this.x3a415rzf5zerx = null;
               this.xw2sp1abe7j8z = null;
               this.x1q6yox0rpcgbf = null;
               return;
            }
         }
      }
   }
}
