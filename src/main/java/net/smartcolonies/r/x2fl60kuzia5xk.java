package net.smartcolonies.r;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.class_1304;
import net.minecraft.class_1646;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_3218;
import net.minecraft.class_3489;
import net.minecraft.class_6862;

public final class x2fl60kuzia5xk {
   private x2fl60kuzia5xk() {
   }

   public static void xqe8twcjwxn55(class_3218 var0, x15kgoaq6i3m6i var1, class_1646 var2, class_1799 var3) {
      if (!var3.method_7960() && !x2n55y2tom37h2.x2cl1crde2rnxy(var0, var1, var3)) {
         class_1799 var4 = var2.method_35199().method_5491(var3.method_7972());
         var3.method_7939(0);
         if (!var4.method_7960()) {
            var2.method_5775(var0, var4);
         }
      }
   }

   public static void x31tan2efb24l6(class_3218 var0, x15kgoaq6i3m6i var1, class_1646 var2, class_6862<class_1792> var3, class_1792 var4, class_1792 var5) {
      class_1792 var6 = x27jm3a98myhk6(var5);
      class_1792 var7 = x64wuh9keue2y(var5);
      int var8 = x3ip8oxl48f0s7(var2.method_6047(), var4, var5, var6, var7);
      if (var8 < 4) {
         var var9 = x2n55y2tom37h2.x3lxwdirnn77qc(var0, var1, var6x -> var6x.method_31573(var3) && x3ip8oxl48f0s7(var6x, var4, var5, var6, var7) > var8);
         class_1799 var10 = var9.orElse(class_1799.field_8037);
         if (var10.method_7960()) {
            int var11 = x2n55y2tom37h2.x1mlne8tl8k5ag(var0, var1, var0x -> var0x.method_31573(class_3489.field_15539));
            int var12 = var5 == class_1802.field_8431 ? 2 : 3;
            if (var7 != null && var11 >= 1 && x2n55y2tom37h2.x1mlne8tl8k5ag(var0, var1, var0x -> var0x.method_31574(class_1802.field_8477)) >= var12) {
               x2n55y2tom37h2.x22hjebj4vmztt(var0, var1, var0x -> var0x.method_31574(class_1802.field_8477), var12);
               x2n55y2tom37h2.x22hjebj4vmztt(var0, var1, var0x -> var0x.method_31573(class_3489.field_15539), 1);
               var10 = new class_1799(var7);
            } else {
               if (var8 < 3) {
                  x7uj9zeib8utf(var0, var1, var12);
               }

               if (var8 < 3
                  && var6 != null
                  && var11 >= 1
                  && x2n55y2tom37h2.x1mlne8tl8k5ag(var0, var1, var0x -> var0x.method_31574(class_1802.field_8620)) >= var12) {
                  x2n55y2tom37h2.x22hjebj4vmztt(var0, var1, var0x -> var0x.method_31574(class_1802.field_8620), var12);
                  x2n55y2tom37h2.x22hjebj4vmztt(var0, var1, var0x -> var0x.method_31573(class_3489.field_15539), 1);
                  var10 = new class_1799(var6);
               }
            }

            if (var10.method_7960()
               && var8 < 2
               && var11 >= 1
               && x2n55y2tom37h2.x1mlne8tl8k5ag(var0, var1, var0x -> var0x.method_31574(class_1802.field_20412)) >= 3) {
               x2n55y2tom37h2.x22hjebj4vmztt(var0, var1, var0x -> var0x.method_31573(class_3489.field_15539), 1);
               x2n55y2tom37h2.x22hjebj4vmztt(var0, var1, var0x -> var0x.method_31574(class_1802.field_20412), 3);
               var10 = new class_1799(var5);
            } else if (var10.method_7960() && var8 < 1 && var11 >= 2) {
               x2n55y2tom37h2.x22hjebj4vmztt(var0, var1, var0x -> var0x.method_31573(class_3489.field_15539), 2);
               var10 = new class_1799(var4);
            }
         }

         if (!var10.method_7960()) {
            class_1799 var13 = var2.method_6047();
            if (!var13.method_7960() && !x2n55y2tom37h2.x2cl1crde2rnxy(var0, var1, var13)) {
               class_1799 var14 = var2.method_35199().method_5491(var13.method_7972());
               if (!var14.method_7960()) {
                  var2.method_5775(var0, var14);
               }
            }

            var2.method_5673(class_1304.field_6173, var10);
         }
      }
   }

   public static boolean x3bl8qz81ha13t(class_3218 var0, x15kgoaq6i3m6i var1, class_1646 var2) {
      for (int var3 = 0; var3 < var2.method_35199().method_5439(); var3++) {
         if (var2.method_35199().method_5438(var3).method_31574(class_1802.field_8550)
            || var2.method_35199().method_5438(var3).method_31574(class_1802.field_8705)) {
            return true;
         }
      }

      boolean var5 = false;

      for (int var4 = 0; var4 < var2.method_35199().method_5439(); var4++) {
         var5 |= var2.method_35199().method_5438(var4).method_7960();
      }

      if (!var5) {
         return false;
      } else {
         var var6 = x2n55y2tom37h2.x3lxwdirnn77qc(
            var0, var1, var0x -> var0x.method_31574(class_1802.field_8705) || var0x.method_31574(class_1802.field_8550)
         );
         if (var6.isPresent()) {
            var2.method_35199().method_5491((class_1799)var6.get());
            return true;
         } else {
            x7uj9zeib8utf(var0, var1, 3);
            if (x2n55y2tom37h2.x22hjebj4vmztt(var0, var1, var0x -> var0x.method_31574(class_1802.field_8620), 3)) {
               var2.method_35199().method_5491(new class_1799(class_1802.field_8550));
               return true;
            } else {
               return false;
            }
         }
      }
   }

   public static int x7uj9zeib8utf(class_3218 var0, x15kgoaq6i3m6i var1, int var2) {
      return x2ia8mwg6bu4uh(var0, var1, class_1802.field_33400, class_1802.field_8620, var2);
   }

   public static void x3bzx7lf7atdqb(class_3218 var0, x15kgoaq6i3m6i var1) {
      x2ia8mwg6bu4uh(var0, var1, class_1802.field_33400, class_1802.field_8620, Integer.MAX_VALUE);
      x2ia8mwg6bu4uh(var0, var1, class_1802.field_33401, class_1802.field_27022, Integer.MAX_VALUE);
      x2ia8mwg6bu4uh(var0, var1, class_1802.field_33402, class_1802.field_8695, Integer.MAX_VALUE);
   }

   private static int x2ia8mwg6bu4uh(class_3218 var0, x15kgoaq6i3m6i var1, class_1792 var2, class_1792 var3, int var4) {
      int var5 = x2n55y2tom37h2.x1mlne8tl8k5ag(var0, var1, var1x -> var1x.method_31574(var3));
      int var6 = Math.max(0, var4 - var5);
      int var7 = x2n55y2tom37h2.x1mlne8tl8k5ag(var0, var1, var1x -> var1x.method_31574(var2));
      Predicate<class_1799> var8 = var0x -> var0x.method_31574(class_1802.field_8713)
         || var0x.method_31574(class_1802.field_8665)
         || var0x.method_31573(class_3489.field_15539);
      int var9 = x2n55y2tom37h2.x1mlne8tl8k5ag(var0, var1, var8);
      int var10 = (int)Math.min((long)Math.min(var6, var7), var9 * 8L);
      if (var10 <= 0) {
         return var5;
      } else {
         int var11 = (var10 + 7) / 8;
         boolean var12 = x2n55y2tom37h2.x38ipozok9mc0x(
            var0,
            var1,
            () -> x2n55y2tom37h2.x22hjebj4vmztt(var0, var1, var1xx -> var1xx.method_31574(var2), var10)
               && x2n55y2tom37h2.x22hjebj4vmztt(var0, var1, var8, var11),
            List.of(new class_1799(var3, var10))
         );
         return var12 ? var5 + var10 : var5;
      }
   }

   private static class_1792 x27jm3a98myhk6(class_1792 var0) {
      return var0 == class_1802.field_8387
         ? class_1802.field_8403
         : (var0 == class_1802.field_8062 ? class_1802.field_8475 : (var0 == class_1802.field_8431 ? class_1802.field_8609 : null));
   }

   private static class_1792 x64wuh9keue2y(class_1792 var0) {
      return var0 == class_1802.field_8387
         ? class_1802.field_8377
         : (var0 == class_1802.field_8062 ? class_1802.field_8556 : (var0 == class_1802.field_8431 ? class_1802.field_8527 : null));
   }

   private static int x3ip8oxl48f0s7(class_1799 var0, class_1792 var1, class_1792 var2, class_1792 var3, class_1792 var4) {
      if ((var4 == null || !var0.method_31574(var4))
         && !var0.method_31574(class_1802.field_22024)
         && !var0.method_31574(class_1802.field_22025)
         && !var0.method_31574(class_1802.field_22026)) {
         if (var3 != null && var0.method_31574(var3)) {
            return 3;
         } else if (var0.method_31574(var2)) {
            return 2;
         } else {
            return var0.method_31574(var1) ? 1 : 0;
         }
      } else {
         return 4;
      }
   }
}
