package net.smartaul.village;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.class_1263;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_5250;
import net.minecraft.class_5321;
import net.minecraft.class_7924;
import net.minecraft.class_9279;
import net.minecraft.class_9290;
import net.minecraft.class_9334;
import net.minecraft.server.MinecraftServer;

public final class PropertyDeeds {
   private static final String x1g39vuwea1vgp = "smart_colonies_property_level";
   private static final String x1n3en8rys61rg = "smart_colonies_property_used";
   private static final String x2jw57s5wlcclv = "smart_colonies_property_job";
   private static final String x3vj699b9gonpt = "smart_colonies_property_colony";
   private static final String x3o9nnjfl2vrxc = "smart_colonies_property_dimension";
   private static final String x1dcmqcpdfz12n = "smart_colonies_property_origin";
   private static final String x2n5oznh3c02l0 = "smart_colonies_property_complete";

   private PropertyDeeds() {
   }

   public static class_1799 xhgw7talm1sp8(int var0) {
      int var1 = Math.clamp((long)var0, 1, 3);
      class_1799 var2 = new class_1799(class_1802.field_8407);
      var2.method_57379(class_9334.field_49631, class_2561.method_43471("item.smart_colonies.property_deed"));
      var2.method_57379(
         class_9334.field_49632,
         new class_9290(List.of(class_2561.method_43471(var1 == 1 ? "item.smart_colonies.property_deed.small" : "item.smart_colonies.property_deed.medium")))
      );
      class_2487 var3 = new class_2487();
      var3.method_10569("smart_colonies_property_level", var1);
      var2.method_57379(class_9334.field_49628, class_9279.method_57456(var3));
      return var2;
   }

   public static class_1799 x2qg2bi4tzny42(class_3218 var0, Colony var1, int var2) {
      class_1799 var3 = xhgw7talm1sp8(var2);
      class_2487 var4 = ((class_9279)var3.method_58694(class_9334.field_49628)).method_57461();
      var4.method_10582("smart_colonies_property_colony", var1.id().toString());
      var4.method_10582("smart_colonies_property_dimension", var0.method_27983().method_29177().toString());
      var3.method_57379(class_9334.field_49628, class_9279.method_57456(var4));
      return var3;
   }

   public static int x3kj2ljyx6tv0(class_1799 var0) {
      return x11oga8h5115u7(var0) ? 0 : xau920lx57wg3(var0);
   }

   public static int xau920lx57wg3(class_1799 var0) {
      if (!var0.method_31574(class_1802.field_8407)) {
         return 0;
      } else {
         class_9279 var1 = (class_9279)var0.method_58694(class_9334.field_49628);
         return var1 == null ? 0 : Math.clamp((long)var1.method_57461().method_68083("smart_colonies_property_level", 0), 0, 3);
      }
   }

   public static boolean x11oga8h5115u7(class_1799 var0) {
      if (!var0.method_31574(class_1802.field_8407)) {
         return false;
      } else {
         class_9279 var1 = (class_9279)var0.method_58694(class_9334.field_49628);
         if (var1 == null) {
            return false;
         } else {
            class_2487 var2 = var1.method_57461();
            return var2.method_68566("smart_colonies_property_used", false) || !var2.method_68564("smart_colonies_property_job", "").isEmpty();
         }
      }
   }

   public static Optional<ConstructionJob> x2l483kg9fflzs(class_3218 var0, Colony var1, class_1657 var2, class_1268 var3) {
      class_1799 var4 = var2.method_5998(var3);
      int var5 = x3kj2ljyx6tv0(var4);
      if (var5 == 0) {
         return Optional.empty();
      } else {
         class_2487 var6 = ((class_9279)var4.method_58694(class_9334.field_49628)).method_57461();
         if (var6.method_68564("smart_colonies_property_colony", "").isEmpty()
            || var6.method_68564("smart_colonies_property_colony", "").equals(var1.id().toString())
               && var6.method_68564("smart_colonies_property_dimension", "").equals(var0.method_27983().method_29177().toString())) {
            var var7 = ColonyManager.x2fxdvzecolqll(var0, var1, var2.method_5667(), var5);
            if (var7.isEmpty()) {
               return var7;
            } else {
               class_1799 var8 = var4.method_46651(1);
               class_2487 var9 = ((class_9279)var8.method_58694(class_9334.field_49628)).method_57461();
               var9.method_10556("smart_colonies_property_used", true);
               var9.method_10582("smart_colonies_property_job", ((ConstructionJob)var7.get()).id().toString());
               var9.method_10582("smart_colonies_property_colony", var1.id().toString());
               var9.method_10582("smart_colonies_property_dimension", var0.method_27983().method_29177().toString());
               var9.method_10544("smart_colonies_property_origin", ((ConstructionJob)var7.get()).x3ij6gescvakv2().method_10063());
               var9.method_10556("smart_colonies_property_complete", false);
               var8.method_57379(class_9334.field_49628, class_9279.method_57456(var9));
               x1wadvar5cjjlm(var8, var9);
               var4.method_7934(1);
               var2.method_6122(var3, var8);
               if (!var4.method_7960() && !var2.method_31548().method_7394(var4)) {
                  var2.method_7328(var4, false);
               }

               return var7;
            }
         } else {
            return Optional.empty();
         }
      }
   }

   public static void x3000fumbeekrm(MinecraftServer var0, class_1799 var1) {
      if (x11oga8h5115u7(var1)) {
         class_2487 var2 = ((class_9279)var1.method_58694(class_9334.field_49628)).method_57461();
         class_2960 var3 = class_2960.method_12829(var2.method_68564("smart_colonies_property_dimension", ""));
         if (var3 != null) {
            class_3218 var4 = var0.method_3847(class_5321.method_29179(class_7924.field_41223, var3));
            if (var4 != null) {
               try {
                  ColonyManager.xchl1k1tnh3w8(var4)
                     .byId(UUID.fromString(var2.method_68564("smart_colonies_property_colony", "")))
                     .ifPresent(
                        var2x -> var2x.x1ja61emeln2hc()
                           .stream()
                           .filter(var1xx -> var1xx.id().toString().equals(var2.method_68564("smart_colonies_property_job", "")))
                           .findFirst()
                           .ifPresent(var2xx -> {
                              if (var2xx.x2s8emavervqrz() && !var2.method_68566("smart_colonies_property_complete", false)) {
                                 var2.method_10556("smart_colonies_property_complete", true);
                                 var1.method_57379(class_9334.field_49628, class_9279.method_57456(var2));
                              }

                              x1wadvar5cjjlm(var1, var2);
                           })
                     );
               } catch (IllegalArgumentException var6) {
               }
            }
         }
      }
   }

   private static void x1wadvar5cjjlm(class_1799 var0, class_2487 var1) {
      class_2338 var2 = class_2338.method_10092(var1.method_68080("smart_colonies_property_origin", 0L));
      class_5250 var3 = class_2561.method_43469(
         var1.method_68566("smart_colonies_property_complete", false)
            ? "item.smart_colonies.property_deed.complete"
            : "item.smart_colonies.property_deed.building",
         new Object[]{var2.method_23854()}
      );
      if (!var3.equals(var0.method_58694(class_9334.field_49631))) {
         var0.method_57379(class_9334.field_49631, var3);
      }

      class_9290 var4 = new class_9290(
         List.of(
            class_2561.method_43471(xau920lx57wg3(var0) == 1 ? "item.smart_colonies.property_deed.small" : "item.smart_colonies.property_deed.medium"),
            class_2561.method_43471("item.smart_colonies.property_deed.used")
         )
      );
      if (!var4.equals(var0.method_58694(class_9334.field_49632))) {
         var0.method_57379(class_9334.field_49632, var4);
      }
   }

   public static void x2cidrexyzeol0(MinecraftServer var0, class_1263 var1) {
      for (int var2 = 0; var2 < var1.method_5439(); var2++) {
         x3000fumbeekrm(var0, var1.method_5438(var2));
      }
   }

   public static void x2pghlycghjdni(MinecraftServer var0) {
      if (var0.method_3780() % 20 == 0) {
         for (class_3222 var2 : var0.method_3760().method_14571()) {
            x2cidrexyzeol0(var0, var2.method_31548());
            x2cidrexyzeol0(var0, var2.method_7274());

            for (class_1735 var4 : var2.field_7512.field_7761) {
               x3000fumbeekrm(var0, var4.method_7677());
            }

            x3000fumbeekrm(var0, var2.field_7512.method_34255());
         }
      }
   }
}
