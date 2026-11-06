package net.smartcolonies.r;

import net.minecraft.class_1268;
import net.minecraft.class_1646;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_3218;
import net.minecraft.class_3489;

public final class x19n3hxzuoqopl {
   private static final int x1rh65nzmvvs8x = 6;
   private static final int x1qn1a8g5bgne1 = 5;

   private x19n3hxzuoqopl() {
   }

   public static void xdfuvky2q520(class_3218 var0, x15kgoaq6i3m6i var1, class_1646 var2) {
      if (var2.method_5805() && !var2.method_6113() && Math.floorMod(var0.method_75260() + var2.method_5628() * 17L, 80L) == 0L) {
         class_2338 var3 = var2.method_24515();
         if (var0.method_22339(var3) <= 6 && !x18ap5wluhtzk2(var0, var3)) {
            class_2338 var4 = x8fofjzx50yhu(var0, var1, var3);
            if (var4 != null) {
               class_1799 var5 = x1tm2jo7usf6ui(var2);
               class_1799 var6 = class_1799.field_8037;
               if (var5.method_7960()) {
                  var6 = x2n55y2tom37h2.x3lxwdirnn77qc(var0, var1, var0x -> var0x.method_31574(class_1802.field_8810)).orElse(class_1799.field_8037);
                  if (var6.method_7960() && x2uh9hhyc0v6wn(var0, var1)) {
                     var6 = new class_1799(class_1802.field_8810, 4);
                  }

                  if (var6.method_7960()) {
                     return;
                  }
               }

               if (x1dxdlnevtfbhm.x1bq0f8n53gtob(var0, var4, class_2246.field_10336.method_9564(), 3)) {
                  if (!var5.method_7960()) {
                     var5.method_7934(1);
                  } else {
                     var6.method_7934(1);
                     if (!var6.method_7960()) {
                        class_1799 var7 = var2.method_35199().method_5491(var6);
                        if (!var7.method_7960()) {
                           x2fl60kuzia5xk.xqe8twcjwxn55(var0, var1, var2, var7);
                        }
                     }
                  }

                  var2.method_6104(class_1268.field_5808);
               } else if (!var6.method_7960()) {
                  x2fl60kuzia5xk.xqe8twcjwxn55(var0, var1, var2, var6);
               }
            }
         }
      }
   }

   private static class_2338 x8fofjzx50yhu(class_3218 var0, x15kgoaq6i3m6i var1, class_2338 var2) {
      for (int var3 = 1; var3 <= 3; var3++) {
         for (int var4 = -var3; var4 <= var3; var4++) {
            for (int var5 = -var3; var5 <= var3; var5++) {
               if (Math.max(Math.abs(var4), Math.abs(var5)) == var3) {
                  for (int var6 = -1; var6 <= 1; var6++) {
                     class_2338 var7 = var2.method_10069(var4, var6, var5);
                     if (var0.method_14178().method_12123(var7.method_10263() >> 4, var7.method_10260() >> 4)) {
                        double var8 = var7.method_10263() - var1.core().method_10263();
                        double var10 = var7.method_10260() - var1.core().method_10260();
                        if (!(var8 * var8 + var10 * var10 > (double)var1.radius() * var1.radius())
                           && var0.method_22339(var7) <= 6
                           && var0.method_8320(var7).method_26215()
                           && class_2246.field_10336.method_9564().method_26184(var0, var7)) {
                           return var7.method_10062();
                        }
                     }
                  }
               }
            }
         }
      }

      return null;
   }

   private static boolean x18ap5wluhtzk2(class_3218 var0, class_2338 var1) {
      for (class_2338 var3 : class_2338.method_10097(var1.method_10069(-5, -3, -5), var1.method_10069(5, 3, 5))) {
         if (var0.method_14178().method_12123(var3.method_10263() >> 4, var3.method_10260() >> 4)
            && (var0.method_8320(var3).method_27852(class_2246.field_10336) || var0.method_8320(var3).method_27852(class_2246.field_10099))) {
            return true;
         }
      }

      return false;
   }

   private static class_1799 x1tm2jo7usf6ui(class_1646 var0) {
      for (int var1 = 0; var1 < var0.method_35199().method_5439(); var1++) {
         class_1799 var2 = var0.method_35199().method_5438(var1);
         if (var2.method_31574(class_1802.field_8810)) {
            return var2;
         }
      }

      return class_1799.field_8037;
   }

   private static boolean x2uh9hhyc0v6wn(class_3218 var0, x15kgoaq6i3m6i var1) {
      boolean var2 = x2n55y2tom37h2.x1mlne8tl8k5ag(var0, var1, var0x -> var0x.method_31574(class_1802.field_8713) || var0x.method_31574(class_1802.field_8665))
         > 0;
      boolean var3 = x2n55y2tom37h2.x1mlne8tl8k5ag(var0, var1, var0x -> var0x.method_31574(class_1802.field_8600) || var0x.method_31573(class_3489.field_15539))
         > 0;
      if (var2 && var3) {
         x2n55y2tom37h2.x22hjebj4vmztt(var0, var1, var0x -> var0x.method_31574(class_1802.field_8713) || var0x.method_31574(class_1802.field_8665), 1);
         x2n55y2tom37h2.x22hjebj4vmztt(var0, var1, var0x -> var0x.method_31574(class_1802.field_8600) || var0x.method_31573(class_3489.field_15539), 1);
         return true;
      } else {
         return false;
      }
   }
}
