package net.smartaul.village;

import net.smartaul.config.SmartAulConfig;

import net.minecraft.class_1439;
import net.minecraft.class_1657;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3341;
import net.minecraft.class_3481;
import net.minecraft.class_6862;
import net.minecraft.class_7924;
import net.minecraft.class_2350.class_2353;

public final class GolemPillarBreaking {
   private GolemPillarBreaking() {
   }

   public static boolean x1qwgvfo0xc7pp(class_3218 var0, class_1439 var1, Colony var2) {
      if (SmartAulConfig.x2by5f9ehxvwk9().roles.golems_break_attacker_pillars && WorldEditPolicy.x35ev8l9vc9uk7(var0)) {
         if (var1.method_5968() instanceof class_1657 var3 && !var3.method_68878() && !var3.method_7325()) {
            double var13 = var3.method_23317() - var1.method_23317();
            double var6 = var3.method_23321() - var1.method_23321();
            double var8 = var3.method_23318() - var1.method_23318();
            if (!(var8 < 2.0) && !(var8 > 9.0) && !(var13 * var13 + var6 * var6 > 20.0) && Math.floorMod(var0.method_75260() + var1.method_5628(), 12) == 0) {
               class_2338 var10 = null;

               for (int var11 = 1; var11 <= 8; var11++) {
                  class_2338 var12 = var3.method_24515().method_10087(var11);
                  if (!var0.method_8320(var12).method_26215()) {
                     var10 = var12;
                     break;
                  }
               }

               if (var10 == null
                  || !xxdhr31as89gp(var0, var2, var10)
                  || !ColonyWorldData.get(var0).isPlayerBlock(var10)
                  || WorldEditPolicy.xz4flfirg81x1(var0, var10)) {
                  return false;
               } else if (!var0.method_8651(var10, false, var1)) {
                  return false;
               } else {
                  ColonyWorldData.get(var0).forgetPlacement(var10);
                  VillagerNavigation.x1wi30zgg7nf81(var0, var1, var3.method_24515(), 1.9);
                  return true;
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private static boolean xxdhr31as89gp(class_3218 var0, Colony var1, class_2338 var2) {
      class_2680 var3 = var0.method_8320(var2);
      if (var3.method_26164(class_6862.method_40092(class_7924.field_41254, class_2960.method_60655("smart_colonies", "builder_protected")))) {
         return false;
      } else if (!var3.method_26215() && var0.method_8321(var2) == null && !(var3.method_26214(var0, var2) < 0.0F)) {
         boolean var4 = var3.method_26164(class_3481.field_29822)
            || var3.method_26164(class_3481.field_25806)
            || var3.method_27852(class_2246.field_10445)
            || var3.method_27852(class_2246.field_29031)
            || var3.method_27852(class_2246.field_10515);
         if (!var4) {
            return false;
         } else {
            int var5 = 0;

            for (class_2350 var7 : class_2353.field_11062) {
               if (var0.method_8320(var2.method_10093(var7)).method_26215()) {
                  var5++;
               }
            }

            if (var5 < 2) {
               return false;
            } else {
               for (ConstructionJob var11 : var1.x1ja61emeln2hc()) {
                  class_3341 var8 = var11.x24kbqv6g4kkrg();
                  if (var2.method_10263() >= var8.method_35415() - 2
                     && var2.method_10263() <= var8.method_35418() + 2
                     && var2.method_10264() >= var8.method_35416() - 1
                     && var2.method_10264() <= var8.method_35419() + 2
                     && var2.method_10260() >= var8.method_35417() - 2
                     && var2.method_10260() <= var8.method_35420() + 2) {
                     return false;
                  }
               }

               for (class_2338 var12 : class_2338.method_10097(var2.method_10069(-3, -3, -3), var2.method_10069(3, 3, 3))) {
                  class_2680 var13 = var0.method_8320(var12);
                  if (var13.method_26164(class_3481.field_16443)
                     || var13.method_26164(class_3481.field_15495)
                     || var13.method_27852(class_2246.field_16332)
                     || var13.method_27852(class_2246.field_10034)
                     || var13.method_27852(class_2246.field_16328)) {
                     return false;
                  }
               }

               return true;
            }
         }
      } else {
         return false;
      }
   }
}
