package net.smartaul.village;

import java.util.HashSet;
import java.util.UUID;
import net.minecraft.class_2246;
import net.minecraft.class_2281;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_2745;
import net.minecraft.class_2818;
import net.minecraft.class_3218;

public final class ProtectedLand {
   private ProtectedLand() {
   }

   public static boolean x3gqyq7yie9jxr(class_3218 var0, class_2338 var1) {
      ColonyWorldData var2 = ColonyWorldData.get(var0);
      return var2.land().stream().anyMatch(var1x -> var1x.contains(var1))
         || var2.colonies()
            .stream()
            .anyMatch(
               var1x -> var1x.x1ja61emeln2hc().stream().anyMatch(var1xx -> var1xx.x2nfrt8qxdscso().isPresent() && var1xx.x24kbqv6g4kkrg().method_14662(var1))
            );
   }

   public static boolean xlzfxuhnckux8(class_3218 var0, UUID var1, class_2338 var2) {
      ColonyWorldData var3 = ColonyWorldData.get(var0);
      return var3.land().stream().anyMatch(var2x -> var2x.xetxwd9l2gcx6().equals(var1) && var2x.contains(var2))
         || var3.colonies()
            .stream()
            .anyMatch(
               var2x -> var2x.x1ja61emeln2hc()
                  .stream()
                  .anyMatch(
                     var2xx -> var2xx.x2s8emavervqrz()
                        && var2xx.x2nfrt8qxdscso().filter(var1::equals).isPresent()
                        && var2xx.x24kbqv6g4kkrg().method_14662(var2)
                  )
            );
   }

   private static boolean xwhfuj6t8luq3(class_3218 var0, class_2338 var1) {
      return ColonyWorldData.get(var0).isPlayerBlock(var1) || x3gqyq7yie9jxr(var0, var1);
   }

   public static boolean xic1z39wiy3pn(class_3218 var0, class_2338 var1) {
      if (xwhfuj6t8luq3(var0, var1)) {
         return true;
      } else if (ColonyWorldData.get(var0).colonies().stream().anyMatch(var1x -> var1x.x29j0o9zdxri2().containsKey(var1))) {
         return false;
      } else if (!var0.method_14178().method_12123(var1.method_10263() >> 4, var1.method_10260() >> 4)) {
         return false;
      } else {
         class_2818 var2 = var0.method_14178().method_21730(var1.method_10263() >> 4, var1.method_10260() >> 4);
         if (var2 == null) {
            return false;
         } else {
            class_2680 var3 = var2.method_8320(var1);
            if (var3.method_27852(class_2246.field_10034) && var3.method_11654(class_2281.field_10770) != class_2745.field_12569) {
               class_2338 var4 = var1.method_10093(class_2281.method_9758(var3));
               if (!var0.method_14178().method_12123(var4.method_10263() >> 4, var4.method_10260() >> 4)) {
                  return false;
               } else {
                  class_2818 var5 = var0.method_14178().method_21730(var4.method_10263() >> 4, var4.method_10260() >> 4);
                  return var5 != null && var5.method_8320(var4).method_27852(class_2246.field_10034) && xwhfuj6t8luq3(var0, var4);
               }
            } else {
               return false;
            }
         }
      }
   }

   public static void x1se6fg76pktcw(class_3218 var0, class_2338 var1) {
      class_2818 var2 = var0.method_14178().method_21730(var1.method_10263() >> 4, var1.method_10260() >> 4);
      if (var2 != null) {
         class_2680 var3 = var2.method_8320(var1);
         if (var3.method_27852(class_2246.field_10034) && var3.method_11654(class_2281.field_10770) != class_2745.field_12569) {
            class_2338 var4 = var1.method_10093(class_2281.method_9758(var3));
            class_2818 var5 = var0.method_14178().method_21730(var4.method_10263() >> 4, var4.method_10260() >> 4);
            if (var5 != null && var5.method_8320(var4).method_27852(class_2246.field_10034) && !xwhfuj6t8luq3(var0, var4)) {
               boolean var6 = ColonyWorldData.get(var0)
                  .colonies()
                  .stream()
                  .anyMatch(var1x -> var1x.x29j0o9zdxri2().containsKey(var4) || var1x.x2ad3s8aentmo4().contains(var4));
               if (xwhfuj6t8luq3(var0, var1) && var6) {
                  byte var7 = 18;
                  var0.method_8652(var1, (class_2680)var3.method_11657(class_2281.field_10770, class_2745.field_12569), var7);
                  var0.method_8652(var4, (class_2680)var0.method_8320(var4).method_11657(class_2281.field_10770, class_2745.field_12569), var7);
               }
            }
         }
      }
   }

   public static void x34rm129tews28(class_3218 var0) {
      ColonyWorldData var1 = ColonyWorldData.get(var0);
      boolean var2 = false;

      for (Colony var4 : var1.colonies()) {
         boolean var5 = false;
         var2 |= var4.x2xqc3q79vfkw3().keySet().removeIf(var1x -> var1.land().stream().anyMatch(var1xx -> var1xx.contains(var1x)));
         var var6 = new HashSet<>(var4.x2ad3s8aentmo4());
         var6.addAll(var4.x29j0o9zdxri2().keySet());

         for (class_2338 var8 : var6) {
            if (var0.method_14178().method_12123(var8.method_10263() >> 4, var8.method_10260() >> 4) && xic1z39wiy3pn(var0, var8)) {
               var4.x3qhye0v48751k(var8);
               var2 = true;
               var5 = true;
            }
         }

         for (class_2338 var12 : var6) {
            if (var0.method_14178().method_12123(var12.method_10263() >> 4, var12.method_10260() >> 4)) {
               class_2818 var9 = var0.method_14178().method_21730(var12.method_10263() >> 4, var12.method_10260() >> 4);
               if (var9 != null) {
                  class_2680 var10 = var9.method_8320(var12);
                  if (var10.method_27852(class_2246.field_10034) && var10.method_11654(class_2281.field_10770) != class_2745.field_12569) {
                     x1se6fg76pktcw(var0, var12.method_10093(class_2281.method_9758(var10)));
                  }
               }
            }
         }

         if (var5) {
            ColonyManager.x2u0vsam1tcmhq(var0, var4);
         }
      }

      if (var2) {
         var1.method_80();
      }
   }
}
