package net.smartcolonies.r;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import net.minecraft.class_2246;
import net.minecraft.class_2302;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3341;
import net.minecraft.class_2350.class_2353;
import net.minecraft.class_2902.class_2903;

public final class xamz6pfj7qa87 {
   private xamz6pfj7qa87() {
   }

   public static boolean x3hqfrterusj2u(class_3218 var0, class_2338 var1) {
      return var0.method_8320(var1).method_27852(class_2246.field_10194) || var0.method_8320(var1).method_27852(class_2246.field_10467);
   }

   public static class_2338 x3qthrkxcelgb7(class_3218 var0, int var1, int var2) {
      class_2338 var3 = new class_2338(var1, var0.method_8624(class_2903.field_13203, var1, var2) - 1, var2);

      for (int var4 = 0; var4 < 6 && x27aao31y6r2ta.x3hz8l3ppqisjl(var0, var3); var4++) {
         var3 = var3.method_10074();
      }

      return var3;
   }

   public static boolean xxon284tpck85(class_3218 var0, x15kgoaq6i3m6i var1, class_2338 var2) {
      if (!x1dxdlnevtfbhm.x35ev8l9vc9uk7(var0)) {
         return false;
      } else if (!var0.method_14178().method_12123(var2.method_10263() >> 4, var2.method_10260() >> 4)) {
         return false;
      } else if (x3d5vk1zj3fvkj.get(var0).land().stream().anyMatch(var1x -> var1x.contains(var2))) {
         return false;
      } else if (x20rghnsl2yowl.x239t1m3hfi74d(var0, var2)) {
         return false;
      } else if (x3hqfrterusj2u(var0, var2) || !x3d5vk1zj3fvkj.get(var0).isPlayerBlock(var2) && !x3d5vk1zj3fvkj.get(var0).isPlayerBlock(var2.method_10084())) {
         if (!var0.method_14178().method_12123(var2.method_10263() >> 4, var2.method_10260() >> 4)) {
            return false;
         } else {
            class_2680 var3 = var0.method_8320(var2);
            if (var3.method_27852(class_2246.field_10362) || var0.method_8320(var2.method_10084()).method_26204() instanceof class_2302) {
               return false;
            } else if ((x1dxdlnevtfbhm.xroinv4hk6l7h(var3) || x3hqfrterusj2u(var0, var2)) && var0.method_8321(var2) == null) {
               if (var0.method_8316(var2).method_15769() && var0.method_8316(var2.method_10084()).method_15769()) {
                  if (!var0.method_8320(var2.method_10084()).method_26215()
                     && !var0.method_8320(var2.method_10084()).method_45474()
                     && !x27aao31y6r2ta.x3hz8l3ppqisjl(var0, var2.method_10084())) {
                     return false;
                  } else if (!var0.method_8320(var2.method_10086(2)).method_26215() && !x27aao31y6r2ta.x3hz8l3ppqisjl(var0, var2.method_10086(2))) {
                     return false;
                  } else {
                     for (x2arpn38dclo1g var5 : var1.x1ja61emeln2hc()) {
                        class_3341 var6 = var5.x24kbqv6g4kkrg();
                        if (var2.method_10263() >= var6.method_35415() - 2
                           && var2.method_10263() <= var6.method_35418() + 2
                           && var2.method_10260() >= var6.method_35417() - 2
                           && var2.method_10260() <= var6.method_35420() + 2) {
                           return false;
                        }
                     }

                     return true;
                  }
               } else {
                  return false;
               }
            } else {
               return false;
            }
         }
      } else {
         return false;
      }
   }

   public static Optional<List<class_2338>> x33m0vw9cimu8k(class_3218 var0, x15kgoaq6i3m6i var1, class_2338 var2, int var3, int var4) {
      var var5 = new ArrayDeque<>();
      var var6 = new HashMap<>();

      for (int[] var10 : new int[][]{{var3 / 2, -3}, {var3 / 2, var4 + 2}, {-3, var4 / 2}, {var3 + 2, var4 / 2}}) {
         int var11 = var2.method_10263() + var10[0];
         int var12 = var2.method_10260() + var10[1];
         if (var0.method_14178().method_12123(var11 >> 4, var12 >> 4)) {
            class_2338 var13 = x3qthrkxcelgb7(var0, var11, var12);
            if (xxon284tpck85(var0, var1, var13)) {
               var5.add(var13);
               var6.put(var13, var13);
            }
         }
      }

      int var15 = 0;

      while (!var5.isEmpty() && var15++ < 1800) {
         class_2338 var16 = (class_2338)var5.removeFirst();
         boolean var17 = var1.x1ja61emeln2hc().isEmpty()
            && Math.abs(var16.method_10263() - var1.core().method_10263()) <= 2
            && Math.abs(var16.method_10260() - var1.core().method_10260()) <= 2
            && Math.abs(var16.method_10264() + 1 - var1.core().method_10264()) <= 1;
         if (x3hqfrterusj2u(var0, var16) || var17) {
            ArrayList var18;
            for (var18 = new ArrayList<>(); !((class_2338)var6.get(var16)).equals(var16); var16 = (class_2338)var6.get(var16)) {
               if (!x3hqfrterusj2u(var0, var16)) {
                  var18.add(var16);
               }
            }

            if (!x3hqfrterusj2u(var0, var16)) {
               var18.add(var16);
            }

            return Optional.of(var18);
         }

         for (class_2350 var20 : class_2353.field_11062) {
            int var21 = var16.method_10263() + var20.method_10148();
            int var22 = var16.method_10260() + var20.method_10165();
            if ((
                  var21 < var2.method_10263() - 2
                     || var21 >= var2.method_10263() + var3 + 2
                     || var22 < var2.method_10260() - 2
                     || var22 >= var2.method_10260() + var4 + 2
               )
               && Math.abs(var21 - var2.method_10263()) <= 32
               && Math.abs(var22 - var2.method_10260()) <= 32
               && var0.method_14178().method_12123(var21 >> 4, var22 >> 4)) {
               class_2338 var14 = x3qthrkxcelgb7(var0, var21, var22);
               if (Math.abs(var14.method_10264() - var16.method_10264()) <= 1 && !var6.containsKey(var14) && xxon284tpck85(var0, var1, var14)) {
                  var6.put(var14, var16);
                  var5.addLast(var14);
               }
            }
         }
      }

      return Optional.empty();
   }
}
