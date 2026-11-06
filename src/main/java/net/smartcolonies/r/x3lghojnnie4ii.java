package net.smartcolonies.r;

import java.util.List;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2382;
import net.minecraft.class_2470;
import net.minecraft.class_2680;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3341;
import net.minecraft.class_3443;
import net.minecraft.class_3449;
import net.minecraft.class_3492;
import net.minecraft.class_3499;
import net.minecraft.class_3781;
import net.minecraft.class_3790;
import net.minecraft.class_7045;
import net.minecraft.class_3499.class_3501;
import net.minecraft.class_3499.class_5162;
import net.smartcolonies.mixin.x2gac63vevkbv3;

public final class x3lghojnnie4ii {
   private x3lghojnnie4ii() {
   }

   public static int xc4tegq500os8(class_3218 var0, x15kgoaq6i3m6i var1) {
      int var2 = var1.x2xqc3q79vfkw3().size();
      class_3449 var3 = var0.method_27056().method_57560(var1.core(), class_7045.field_37045);
      if (var3 != class_3449.field_16713) {
         for (class_3443 var5 : var3.method_14963()) {
            if (var5 instanceof class_3790 var6 && var6.method_16644() instanceof class_3781 var7) {
               class_2960 var15;
               try {
                  var15 = var7.method_67689();
               } catch (IllegalStateException var10) {
                  continue;
               }

               String var9 = var15.method_12832();
               if (var9.contains("/houses/") && !var9.contains("farm") && !var9.contains("pen") && !var9.contains("animal")) {
                  x25dbr9jxsajgt(var0, var1, var15, var6.method_16648(), var6.method_16888(), null);
               }
            }
         }
      }

      for (x2arpn38dclo1g var12 : var1.x1ja61emeln2hc()) {
         if (var12.x2s8emavervqrz() && !var12.x2nfrt8qxdscso().isPresent()) {
            class_3499 var13 = (class_3499)var0.method_14183().method_15094(var12.x3kqe1qjsw9oty()).orElse(null);
            if (var13 != null) {
               class_2382 var14 = var13.method_15160();
               class_3341 var16 = var12.x24kbqv6g4kkrg();
               if (var14.method_10263() == var16.method_35414() && var14.method_10264() == var16.method_14660() && var14.method_10260() == var16.method_14663()
                  )
                {
                  x25dbr9jxsajgt(var0, var1, var12.x3kqe1qjsw9oty(), var12.x3ij6gescvakv2(), class_2470.field_11467, var16);
               }
            }
         }
      }

      return var1.x2xqc3q79vfkw3().size() - var2;
   }

   private static void x25dbr9jxsajgt(class_3218 var0, x15kgoaq6i3m6i var1, class_2960 var2, class_2338 var3, class_2470 var4, class_3341 var5) {
      class_3499 var6 = (class_3499)var0.method_14183().method_15094(var2).orElse(null);
      if (var6 != null) {
         class_3492 var7 = new class_3492().method_15123(var4);
         var var8 = ((x2gac63vevkbv3)var6).xbqthaw3aplko();
         if (!var8.isEmpty()) {
            for (class_3501 var10 : ((class_5162)var8.getFirst()).method_27125()) {
               class_2680 var11 = var10.comp_1342().method_26186(var4);
               class_2338 var12 = class_3499.method_15171(var7, var10.comp_1341()).method_10081(var3);
               if ((var5 == null || var5.method_14662(var12))
                  && var0.method_14178().method_12123(var12.method_10263() >> 4, var12.method_10260() >> 4)
                  && !var11.method_26215()
                  && !var11.method_45474()
                  && !var11.method_27852(class_2246.field_16540)
                  && !var11.method_27852(class_2246.field_10465)
                  && !var11.method_27852(class_2246.field_10194)
                  && !var11.method_27852(class_2246.field_10362)
                  && !x2uh56vnzc4u51.x3gqyq7yie9jxr(var0, var12)
                  && !x3d5vk1zj3fvkj.get(var0).isPlayerBlock(var12)) {
                  class_2680 var13 = var0.method_8320(var12);
                  if (var13.method_26204() != var11.method_26204()
                     && (var13.method_26215() || var13.method_45474())
                     && !var1.x1ja61emeln2hc().stream().anyMatch(var1x -> var1x.x24kbqv6g4kkrg().method_14662(var12) && !var1x.x2s8emavervqrz())) {
                     x20rghnsl2yowl.x17m23t077i8f8(var0, var1, x20rghnsl2yowl.x335dkgs8aizip(var0, var12, var11));
                  }
               }
            }
         }
      }
   }
}
