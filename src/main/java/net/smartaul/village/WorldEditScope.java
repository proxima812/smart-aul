package net.smartaul.village;

import java.util.Map;
import java.util.function.BooleanSupplier;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_3218;

public final class WorldEditScope {
   private static final ThreadLocal<Integer> x148uq649zls15 = ThreadLocal.withInitial(() -> 0);

   private WorldEditScope() {
   }

   public static void x3mcqehepulqri() {
      x148uq649zls15.set(x148uq649zls15.get() + 1);
   }

   public static void xutm7g9geb0c0() {
      int var0 = x148uq649zls15.get() - 1;
      if (var0 <= 0) {
         x148uq649zls15.remove();
      } else {
         x148uq649zls15.set(var0);
      }
   }

   public static boolean x2aebumvi646n9(BooleanSupplier var0) {
      x3mcqehepulqri();

      boolean var1;
      try {
         var1 = var0.getAsBoolean();
      } finally {
         xutm7g9geb0c0();
      }

      return var1;
   }

   public static void x2d89mem7j8r8n(class_3218 var0, class_2338 var1, class_2680 var2, class_2680 var3) {
      if (x148uq649zls15.get() <= 0
         && var2 != null
         && var2.method_26204() != var3.method_26204()
         && !var2.method_26215()
         && !var2.method_45474()
         && (var3.method_26215() || var3.method_45474())
         && var0.method_8503().method_18854()) {
         ColonyManager.x3psqezd5jem54(var0, var1).ifPresent(var3x -> {
            if (!var3x.x1ja61emeln2hc().stream().anyMatch(var1xx -> var1xx.x24kbqv6g4kkrg().method_14662(var1) && !var1xx.x2s8emavervqrz())) {
               if (VillageRepair.x5epxvcc1p5ir(var0, var3x, var1)) {
                  var var4 = VillageRepair.x335dkgs8aizip(var0, var1, var2);
                  if (!var4.isEmpty()) {
                     VillageRepair.x17m23t077i8f8(var0, var3x, var4);
                  }
               }
            }
         });
      }
   }
}
