package net.smartaul.village;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import net.minecraft.class_1646;
import net.minecraft.class_1799;
import net.minecraft.class_1935;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3486;
import net.minecraft.class_3489;
import net.minecraft.class_7923;

final class TreePlanting {
   private static final Map<Colony, TreePlantingPlan> x7hy25hsbab3i = new WeakHashMap<>();

   TreePlanting() {
   }

   static boolean x2gox5tp6i8qkj(class_3218 var0, Colony var1, class_1646 var2, class_2338 var3) {
      TreePlantingPlan var4 = x7hy25hsbab3i.get(var1);
      if (var4 == null) {
         int var5 = var3.method_10263() - var2.method_31477();
         int var6 = var3.method_10260() - var2.method_31479();
         class_2350 var7 = Math.abs(var5) >= Math.abs(var6)
            ? (var5 >= 0 ? class_2350.field_11034 : class_2350.field_11039)
            : (var6 >= 0 ? class_2350.field_11035 : class_2350.field_11043);

         for (int var11 : new int[]{0, -1}) {
            class_2338 var12 = var2.method_24515().method_10086(var11);

            for (int var13 = 1; var13 <= 10 && var4 == null; var13++) {
               class_2338 var14 = var12.method_10079(var7, var13);
               if (!xi4xj9pzi82m3(var0, var14)) {
                  break;
               }

               if (var0.method_8316(var14).method_15767(class_3486.field_15517)) {
                  var var15 = new ArrayList<class_2338>();

                  class_2338 var16;
                  for (var16 = var14;
                     var15.size() < 24
                        && xi4xj9pzi82m3(var0, var16)
                        && var0.method_8316(var16).method_15767(class_3486.field_15517)
                        && var0.method_8320(var16).method_27852(class_2246.field_10382)
                        && WorldEditPolicy.x13f7be5vlapn7(var0, var16);
                     var16 = var16.method_10093(var7)
                  ) {
                     var15.add(var16);
                  }

                  class_2338 var17 = var14.method_10079(var7.method_10153(), 2);
                  if (!VillagerShelter.x9bnd6e2jczhd(var0, var17)) {
                     var17 = var17.method_10084();
                  }

                  class_2338 var18 = VillagerShelter.x9bnd6e2jczhd(var0, var16) ? var16 : var16.method_10084();
                  if (!var15.isEmpty()
                     && xi4xj9pzi82m3(var0, var16)
                     && !var0.method_8316(var16).method_15767(class_3486.field_15517)
                     && VillagerShelter.x9bnd6e2jczhd(var0, var18)
                     && VillagerShelter.x9bnd6e2jczhd(var0, var17)) {
                     var4 = new TreePlantingPlan(List.copyOf(var15), var17);
                  }
                  break;
               }
            }

            if (var4 != null) {
               break;
            }
         }

         if (var4 == null) {
            return false;
         }

         x7hy25hsbab3i.put(var1, var4);
      }

      class_2338 var19 = var4.x1f1dkpug78222().stream().filter(var1x -> var0.method_8316(var1x).method_15767(class_3486.field_15517)).findFirst().orElse(null);
      if (var19 == null) {
         x7hy25hsbab3i.remove(var1);
         VillagerShelter.x2fxoqmbs0henb(var2);
         VillagerNavigation.x3oupbjeq14mj8(var2);
         return false;
      } else if (WorldEditPolicy.x13f7be5vlapn7(var0, var19) && var0.method_8320(var19).method_27852(class_2246.field_10382)) {
         VillagerShelter.xf8lnb134kd0j(var0, var2, var4.x1ekcqx613mv1d(), 0.9);
         if (var2.method_73189().method_1025(class_243.method_24955(var4.x1ekcqx613mv1d())) > 1.0) {
            return true;
         } else {
            class_2680 var20 = var0.method_8320(var19);
            boolean var21 = var1.x3171qim2pcjd7()
               .entrySet()
               .stream()
               .anyMatch(var0x -> new class_1799((class_1935)class_7923.field_41178.method_63535(var0x.getKey())).method_31573(class_3489.field_15539));
            boolean var22;
            if (var21) {
               var22 = WorldEditPolicy.x1bq0f8n53gtob(var0, var19, class_2246.field_10161.method_9564(), 3);
               if (var22) {
                  SettlementManager.xpl4idj7kx63g(var1);
                  ColonyWorldData.get(var0).method_80();
               }
            } else {
               var22 = ColonyManager.x38ipozok9mc0x(
                  var0,
                  var1,
                  () -> ColonyManager.x22hjebj4vmztt(var0, var1, var0xx -> var0xx.method_31573(class_3489.field_15539), 1)
                     && WorldEditPolicy.x1bq0f8n53gtob(var0, var19, class_2246.field_10161.method_9564(), 3),
                  List.of()
               );
            }

            if (!var22 && var0.method_8320(var19) != var20) {
               WorldEditScope.x2aebumvi646n9(() -> var0.method_8652(var19, var20, 3));
            }

            if (!var22 && !var21 && !ColonyManager.x1tl8iu49onump(var0, var1, var0x -> var0x.method_31573(class_3489.field_15539))) {
               x7hy25hsbab3i.remove(var1);
               return false;
            } else {
               return true;
            }
         }
      } else {
         x7hy25hsbab3i.remove(var1);
         return false;
      }
   }

   private static boolean xi4xj9pzi82m3(class_3218 var0, class_2338 var1) {
      return var0.method_8621().method_11952(var1) && var0.method_14178().method_12123(var1.method_10263() >> 4, var1.method_10260() >> 4);
   }
}
