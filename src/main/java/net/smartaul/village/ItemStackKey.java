package net.smartaul.village;

import net.minecraft.class_1799;

public final class ItemStackKey {
   private final class_1799 x24856ou7tviys;

   public ItemStackKey(class_1799 var1) {
      this.x24856ou7tviys = var1.method_46651(1);
   }

   @Override
   public int hashCode() {
      return class_1799.method_57355(this.x24856ou7tviys);
   }

   @Override
   public boolean equals(Object var1) {
      return var1 instanceof ItemStackKey var2 && class_1799.method_31577(this.x24856ou7tviys, var2.x24856ou7tviys);
   }
}
