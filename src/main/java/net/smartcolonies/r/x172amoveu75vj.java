package net.smartcolonies.r;

import net.minecraft.class_1309;
import net.minecraft.class_1646;
import net.minecraft.class_3218;
import net.minecraft.class_7893;
import net.minecraft.class_4097.class_4098;

public final class x172amoveu75vj<E extends class_1309> implements class_7893<E> {
   private final class_7893<E> x1h6xn35v2y5uc;
   private final boolean x1rdpmsfrf8owh;

   public x172amoveu75vj(class_7893<E> var1) {
      this(var1, false);
   }

   public x172amoveu75vj(class_7893<E> var1, boolean var2) {
      this.x1h6xn35v2y5uc = var1;
      this.x1rdpmsfrf8owh = var2;
   }

   private boolean x2wppgvkmmnftn(class_3218 var1, E var2) {
      return var2 instanceof class_1646
         && (
            x2n55y2tom37h2.x2yj2v4iudqlvb(var1, var2.method_5667()).isPresent()
               || x2n55y2tom37h2.x3psqezd5jem54(var1, var2.method_24515())
                  .filter(var2x -> this.x1rdpmsfrf8owh || var2x.roleOf(var2.method_5667()) != x1rxiy9mtu5b3h.CITIZEN)
                  .isPresent()
         );
   }

   public class_4098 method_18921() {
      return this.x1h6xn35v2y5uc.method_18921();
   }

   public String method_46910() {
      return this.x1h6xn35v2y5uc.method_46910();
   }

   public boolean method_18922(class_3218 var1, E var2, long var3) {
      return !this.x2wppgvkmmnftn(var1, (E)var2) && this.x1h6xn35v2y5uc.method_18922(var1, var2, var3);
   }

   public void method_18923(class_3218 var1, E var2, long var3) {
      if (this.x2wppgvkmmnftn(var1, (E)var2)) {
         this.x1h6xn35v2y5uc.method_18925(var1, var2, var3);
      } else {
         this.x1h6xn35v2y5uc.method_18923(var1, var2, var3);
      }
   }

   public void method_18925(class_3218 var1, E var2, long var3) {
      this.x1h6xn35v2y5uc.method_18925(var1, var2, var3);
   }
}
