package net.smartcolonies.r;

import java.lang.ref.WeakReference;
import java.util.EnumSet;
import java.util.Map;
import java.util.WeakHashMap;
import net.minecraft.class_1352;
import net.minecraft.class_1429;
import net.minecraft.class_1646;
import net.minecraft.class_1792;
import net.minecraft.class_1352.class_4134;
import net.smartcolonies.mixin.xcvv14vzbvjae;

public final class xul6rx4t0aij9 extends class_1352 {
   private static final Map<class_1429, xul6rx4t0aij9> x2jqgbs3h8y4a3 = new WeakHashMap<>();
   private final WeakReference<class_1429> x14kgczmxh7igi;
   private class_1646 x3iilaqce7f5kt;
   private class_1792 x2ez1qd2601876;
   private int xze5msx2a8rgp;

   private xul6rx4t0aij9(class_1429 var1) {
      this.x14kgczmxh7igi = new WeakReference<>(var1);
      this.method_6265(EnumSet.of(class_4134.field_18405, class_4134.field_18406));
   }

   public static void follow(class_1429 var0, class_1646 var1, class_1792 var2) {
      xul6rx4t0aij9 var3 = x2jqgbs3h8y4a3.computeIfAbsent(var0, var0x -> {
         xul6rx4t0aij9 var1x = new xul6rx4t0aij9(var0x);
         ((xcvv14vzbvjae)var0x).x26nz0ko7si5a7().method_6277(1, var1x);
         return var1x;
      });
      var3.x3iilaqce7f5kt = var1;
      var3.x2ez1qd2601876 = var2;
   }

   public static void release(class_1429 var0) {
      xul6rx4t0aij9 var1 = x2jqgbs3h8y4a3.get(var0);
      if (var1 != null) {
         var1.x3iilaqce7f5kt = null;
         var1.x2ez1qd2601876 = null;
      }
   }

   public boolean method_6264() {
      class_1429 var1 = this.x14kgczmxh7igi.get();
      return var1 != null
         && var1.method_5805()
         && this.x3iilaqce7f5kt != null
         && this.x3iilaqce7f5kt.method_5805()
         && !this.x3iilaqce7f5kt.method_6113()
         && !this.x3iilaqce7f5kt.method_18009()
         && this.x2ez1qd2601876 != null
         && this.x3iilaqce7f5kt.method_6047().method_31574(this.x2ez1qd2601876)
         && var1.method_5858(this.x3iilaqce7f5kt) <= 144.0;
   }

   public boolean method_6266() {
      return this.method_6264();
   }

   public void method_6269() {
      this.xze5msx2a8rgp = 0;
   }

   public void method_6268() {
      class_1429 var1 = this.x14kgczmxh7igi.get();
      if (var1 != null && this.x3iilaqce7f5kt != null) {
         var1.method_5988().method_6226(this.x3iilaqce7f5kt, 30.0F, 30.0F);
         if (var1.method_5858(this.x3iilaqce7f5kt) < 2.25) {
            var1.method_5942().method_6340();
         } else {
            if (this.xze5msx2a8rgp-- <= 0) {
               this.xze5msx2a8rgp = 5;
               var1.method_5942().method_6335(this.x3iilaqce7f5kt, 1.05);
            }
         }
      }
   }

   public void method_6270() {
      class_1429 var1 = this.x14kgczmxh7igi.get();
      if (var1 != null) {
         var1.method_5942().method_6340();
      }
   }
}
