package net.smartaul.village;

import net.minecraft.class_1309;
import net.minecraft.class_1646;
import net.minecraft.class_3218;
import net.minecraft.class_4140;
import net.minecraft.class_7893;
import net.minecraft.class_4097.class_4098;

public final class ColonyIdleTaskGate<E extends class_1309> implements class_7893<E> {
   private final class_7893<E> x2sm9yj46yo0rq;

   public ColonyIdleTaskGate(class_7893<E> var1) {
      this.x2sm9yj46yo0rq = var1;
   }

   private boolean x2mdxtohyy24nk(E var1) {
      return var1 instanceof class_1646 var2
         && (
            VillagerShelter.x37u4pqy3fbps(var2) != null
               || var2.method_18868()
                  .method_24538()
                  .filter(
                     var0 -> var0.equals(ColonyActivities.x12qpuae7zsr5m)
                        || var0.equals(ColonyActivities.x38u24k78sk73y)
                        || var0.equals(ColonyActivities.x2t145qayl7tz3)
                        || var0.equals(ColonyActivities.LEISURE)
                  )
                  .isPresent()
         );
   }

   public class_4098 method_18921() {
      return this.x2sm9yj46yo0rq.method_18921();
   }

   public String method_46910() {
      return this.x2sm9yj46yo0rq.method_46910();
   }

   public boolean method_18922(class_3218 var1, E var2, long var3) {
      if (this.x2mdxtohyy24nk((E)var2)) {
         var2.method_18868().method_18875(class_4140.field_18445);
         return false;
      } else {
         return this.x2sm9yj46yo0rq.method_18922(var1, var2, var3);
      }
   }

   public void method_18923(class_3218 var1, E var2, long var3) {
      if (this.x2mdxtohyy24nk((E)var2)) {
         this.x2sm9yj46yo0rq.method_18925(var1, var2, var3);
      } else {
         this.x2sm9yj46yo0rq.method_18923(var1, var2, var3);
      }
   }

   public void method_18925(class_3218 var1, E var2, long var3) {
      this.x2sm9yj46yo0rq.method_18925(var1, var2, var3);
   }
}
