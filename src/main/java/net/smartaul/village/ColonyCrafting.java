package net.smartaul.village;

import java.util.List;
import java.util.function.Predicate;
import net.minecraft.class_1299;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_3218;
import net.minecraft.class_3489;

public final class ColonyCrafting {
   public static final List<CraftingRecipe> x3ck5rmycrqg51 = List.of(
      new CraftingRecipe(class_1802.field_8091, 2, null, 0, 1),
      new CraftingRecipe(class_1802.field_8528, 4, class_1802.field_20412, 2, 1),
      new CraftingRecipe(class_1802.field_8371, 8, class_1802.field_8620, 2, 1),
      new CraftingRecipe(class_1802.field_8802, 18, class_1802.field_8477, 2, 1),
      new CraftingRecipe(class_1802.field_8102, 6, class_1802.field_8276, 3, 1),
      new CraftingRecipe(class_1802.field_8255, 6, class_1802.field_8620, 1, 2),
      new CraftingRecipe(class_1802.field_8743, 10, class_1802.field_8620, 5, 0),
      new CraftingRecipe(class_1802.field_8523, 16, class_1802.field_8620, 8, 0),
      new CraftingRecipe(class_1802.field_8396, 14, class_1802.field_8620, 7, 0),
      new CraftingRecipe(class_1802.field_8660, 8, class_1802.field_8620, 4, 0),
      new CraftingRecipe(class_1802.field_8805, 24, class_1802.field_8477, 5, 0),
      new CraftingRecipe(class_1802.field_8058, 40, class_1802.field_8477, 8, 0),
      new CraftingRecipe(class_1802.field_8348, 34, class_1802.field_8477, 7, 0),
      new CraftingRecipe(class_1802.field_8285, 20, class_1802.field_8477, 4, 0)
   );

   private ColonyCrafting() {
   }

   public static Predicate<class_1799> xeccokn9mws72(class_1792 var0) {
      class_1799 var1 = new class_1799(var0);
      return var1x -> !var1x.method_7960() && class_1799.method_31577(var1x, var1);
   }

   public static void x1p0bvs1guedf7(class_3218 var0, Colony var1) {
      int var2 = var0.method_18198(
            class_1299.field_6147, var1x -> var1x.method_5805() && var1x.method_24515().method_10262(var1.core()) <= var1.radius() * var1.radius()
         )
         .size();
      int var3 = Math.max(36, (4 - var2) * 36);

      for (CraftingRecipe var5 : x3ck5rmycrqg51) {
         int var6 = 2 - ColonyManager.x1mlne8tl8k5ag(var0, var1, xeccokn9mws72(var5.x3dtjj1ybya6jb()));
         if (var6 > 0) {
            Predicate<class_1799> var7 = var1x -> var1x.method_31574(var5.x38p5wnjfccixq());
            int var8 = var5.x38p5wnjfccixq() == class_1802.field_8620
               ? var3
               : (var5.x38p5wnjfccixq() == class_1802.field_8477 ? 4 : (var5.x38p5wnjfccixq() == class_1802.field_20412 ? 64 : 2));
            if ((var5.x2wg9tuwow5zee() <= 0 || ColonyManager.x1mlne8tl8k5ag(var0, var1, var7) >= var8 + var5.x2wg9tuwow5zee() * var6)
               && (
                  var5.x3nya5dnq7wnel() <= 0
                     || ColonyManager.x1mlne8tl8k5ag(var0, var1, var0x -> var0x.method_31573(class_3489.field_15539)) >= 48 + var5.x3nya5dnq7wnel() * var6
               )) {
               ColonyManager.x38ipozok9mc0x(
                  var0,
                  var1,
                  () -> (var5.x2wg9tuwow5zee() == 0 || ColonyManager.x22hjebj4vmztt(var0, var1, var7, var5.x2wg9tuwow5zee() * var6))
                     && (
                        var5.x3nya5dnq7wnel() == 0
                           || ColonyManager.x22hjebj4vmztt(var0, var1, var0xx -> var0xx.method_31573(class_3489.field_15539), var5.x3nya5dnq7wnel() * var6)
                     ),
                  List.of(new class_1799(var5.x3dtjj1ybya6jb(), var6))
               );
            }
         }
      }
   }
}
