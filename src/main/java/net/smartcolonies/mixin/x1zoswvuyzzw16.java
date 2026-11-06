package net.smartcolonies.mixin;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.collect.ImmutableList.Builder;
import com.mojang.datafixers.util.Pair;
import net.minecraft.class_1646;
import net.minecraft.class_4112;
import net.minecraft.class_4129;
import net.minecraft.class_5325;
import net.minecraft.class_7893;
import net.smartcolonies.r.x172amoveu75vj;
import net.smartcolonies.r.x3nymzubg04xss;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_4129.class)
public abstract class x1zoswvuyzzw16 {
   @Inject(method = "method_19020", at = @At("RETURN"), cancellable = true)
   private static void x17i9vuo4uz26m(CallbackInfoReturnable<ImmutableList<Pair<Integer, ? extends class_7893<? super class_1646>>>> var0) {
      Builder var1 = ImmutableList.builder();
      UnmodifiableIterator var2 = ((ImmutableList)var0.getReturnValue()).iterator();

      while (var2.hasNext()) {
         Pair var3 = (Pair)var2.next();
         var1.add(
            var3.getSecond() instanceof class_4112
               ? Pair.of((Integer)var3.getFirst(), new x3nymzubg04xss((class_7893)var3.getSecond()))
               : (var3.getSecond() instanceof class_5325 ? Pair.of((Integer)var3.getFirst(), new x172amoveu75vj((class_7893)var3.getSecond())) : var3)
         );
      }

      var0.setReturnValue(var1.build());
   }

   @Inject(method = "method_19021", at = @At("RETURN"), cancellable = true)
   private static void x2bb0wcllhl9oo(CallbackInfoReturnable<ImmutableList<Pair<Integer, ? extends class_7893<? super class_1646>>>> var0) {
      Builder var1 = ImmutableList.builder();
      UnmodifiableIterator var2 = ((ImmutableList)var0.getReturnValue()).iterator();

      while (var2.hasNext()) {
         Pair var3 = (Pair)var2.next();
         var1.add(Pair.of((Integer)var3.getFirst(), new x172amoveu75vj((class_7893)var3.getSecond())));
      }

      var0.setReturnValue(var1.build());
   }
}
