package net.smartaul.village;

import java.util.Comparator;
import java.util.Set;
import net.minecraft.class_1646;
import net.minecraft.class_3218;
import net.minecraft.class_4140;
import net.minecraft.class_4148;

public final class VillageStorageSensor extends class_4148<class_1646> {
   public VillageStorageSensor() {
      super(100);
   }

   protected void sense(class_3218 var1, class_1646 var2) {
      ColonyManager.x3psqezd5jem54(var1, var2.method_24515())
         .ifPresentOrElse(
            var2x -> var2x.x2ad3s8aentmo4()
               .stream()
               .filter(var1xx -> var1.method_14178().method_12123(var1xx.method_10263() >> 4, var1xx.method_10260() >> 4))
               .min(Comparator.comparingDouble(var1xx -> var1xx.method_10262(var2.method_24515())))
               .ifPresentOrElse(
                  var1xx -> var2.method_18868().method_18878(ColonyMemories.x3o547clcv9kaw, var1xx),
                  () -> var2.method_18868().method_18875(ColonyMemories.x3o547clcv9kaw)
               ),
            () -> var2.method_18868().method_18875(ColonyMemories.x3o547clcv9kaw)
         );
   }

   public Set<class_4140<?>> method_19099() {
      return Set.of(ColonyMemories.x3o547clcv9kaw);
   }

   @Override
   protected void method_19101(class_3218 var1, class_1646 var2) {
      sense(var1, var2);
   }
}
