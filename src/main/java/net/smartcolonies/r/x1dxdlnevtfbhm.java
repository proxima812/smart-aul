package net.smartcolonies.r;

import java.util.Map.Entry;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1646;
import net.minecraft.class_1928;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2346;
import net.minecraft.class_238;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2818;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3481;
import net.minecraft.class_4050;
import net.minecraft.class_6862;
import net.minecraft.class_7924;

public final class x1dxdlnevtfbhm {
   private static final class_6862<class_2248> x2a42nw811caa4 = class_6862.method_40092(
      class_7924.field_41254, class_2960.method_60655("smart_colonies", "builder_protected")
   );

   private x1dxdlnevtfbhm() {
   }

   public static boolean xroinv4hk6l7h(class_2680 var0) {
      return var0.method_26164(class_3481.field_29822)
         || var0.method_26164(class_3481.field_15466)
         || var0.method_26164(class_3481.field_25806)
         || var0.method_26164(class_3481.field_36265)
         || var0.method_27852(class_2246.field_9979)
         || var0.method_27852(class_2246.field_10344)
         || var0.method_27852(class_2246.field_10255)
         || var0.method_27852(class_2246.field_10491)
         || var0.method_27852(class_2246.field_27165);
   }

   public static boolean x35ev8l9vc9uk7(class_3218 var0) {
      return x3sqkg3n6v972r.x2by5f9ehxvwk9().roles.allow_terrain_changes && (Boolean)var0.method_64395().method_76185(class_1928.field_19388);
   }

   public static boolean x3o1lagqjim5tz(class_3218 var0, class_2338 var1) {
      return x27aao31y6r2ta.x3hz8l3ppqisjl(var0, var1) || xjs779qdest9c(var0, var1);
   }

   public static boolean xjs779qdest9c(class_3218 var0, class_2338 var1) {
      if (var0.method_8320(var1).method_26215()) {
         x3d5vk1zj3fvkj.get(var0).forgetPlacement(var1);
      }

      return x3d5vk1zj3fvkj.get(var0).land().stream().anyMatch(var1x -> var1x.contains(var1))
         || x3d5vk1zj3fvkj.get(var0).isPlayerBlock(var1)
         || var0.method_8321(var1) != null
         || var0.method_8320(var1).method_26164(x2a42nw811caa4)
         || xz4flfirg81x1(var0, var1);
   }

   public static boolean xz4flfirg81x1(class_3218 var0, class_2338 var1) {
      for (int var2 = var1.method_10263() - 32 >> 4; var2 <= var1.method_10263() + 32 >> 4; var2++) {
         for (int var3 = var1.method_10260() - 32 >> 4; var3 <= var1.method_10260() + 32 >> 4; var3++) {
            if (var0.method_14178().method_12123(var2, var3)) {
               class_2818 var4 = var0.method_14178().method_21730(var2, var3);
               if (var4 != null) {
                  for (Entry var6 : var4.method_12214().entrySet()) {
                     if (Math.abs(((class_2338)var6.getKey()).method_10263() - var1.method_10263()) <= 32
                        && Math.abs(((class_2338)var6.getKey()).method_10260() - var1.method_10260()) <= 32
                        && var0.method_8320((class_2338)var6.getKey()).method_27852(class_2246.field_10327)) {
                        return true;
                     }
                  }
               }
            }
         }
      }

      return false;
   }

   public static boolean x13f7be5vlapn7(class_3218 var0, class_2338 var1) {
      return x35ev8l9vc9uk7(var0) && var0.method_14178().method_12123(var1.method_10263() >> 4, var1.method_10260() >> 4) && !x3o1lagqjim5tz(var0, var1);
   }

   public static boolean x1bq0f8n53gtob(class_3218 var0, class_2338 var1, class_2680 var2, int var3) {
      class_265 var4 = var2.method_26220(var0, var1);
      if (!var4.method_1110()) {
         for (class_1297 var7 : var0.method_8333(
            null, new class_238(var1).method_1009(0.0, 2.0, 0.0), var0x -> var0x.method_5805() && !var0x.method_7325() && var0x instanceof class_1309
         )) {
            class_238 var8 = var7.method_5829();
            if (var7 instanceof class_1309 var9 && (var9.method_6113() || var9.method_18376() == class_4050.field_18078)) {
               var8 = new class_238(
                  var7.method_23317() - 0.35,
                  var7.method_23318(),
                  var7.method_23321() - 0.35,
                  var7.method_23317() + 0.35,
                  var7.method_23318() + 2.0,
                  var7.method_23321() + 0.35
               );
            }

            for (class_238 var10 : var4.method_1090()) {
               if (var10.method_989(var1.method_10263(), var1.method_10264(), var1.method_10260()).method_994(var8)) {
                  return false;
               }
            }
         }
      }

      return x13f7be5vlapn7(var0, var1) && xyghu7rojfjwk.x2aebumvi646n9(() -> var0.method_8652(var1, var2, var3));
   }

   public static boolean x231if4rvsvptj(class_3218 var0, class_2338 var1, boolean var2, class_1297 var3) {
      if (var3 != null && var3.method_24515().method_10074().equals(var1)) {
         return false;
      } else if (var0.method_8320(var1.method_10084()).method_26204() instanceof class_2346) {
         return false;
      } else if (!var0.method_8333(
            null,
            new class_238(var1.method_10084()).method_1014(0.02),
            var1x -> var1x.method_5805() && var1x instanceof class_1309 && var1x.method_24515().method_10074().equals(var1)
         )
         .isEmpty()) {
         return false;
      } else {
         boolean var4 = x13f7be5vlapn7(var0, var1) && xyghu7rojfjwk.x2aebumvi646n9(() -> var0.method_8651(var1, var2, var3));
         if (var4 && var3 instanceof class_1646 var5) {
            x2vaj7rsm57i4n.x2t98zlq4lu49t(var5, "добыча/расчистка", 1);
         }

         return var4;
      }
   }
}
