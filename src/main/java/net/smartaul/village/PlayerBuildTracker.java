package net.smartaul.village;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import net.minecraft.class_11352;
import net.minecraft.class_1923;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.minecraft.class_2818;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3341;
import net.minecraft.class_3443;
import net.minecraft.class_3449;
import net.minecraft.class_3492;
import net.minecraft.class_3499;
import net.minecraft.class_3781;
import net.minecraft.class_3790;
import net.minecraft.class_8942;
import net.minecraft.class_3499.class_3501;
import net.smartaul.mixin.StructureTemplateAccessor;

public final class PlayerBuildTracker {
   private static final Map<class_3218, Set<class_1923>> xp4ojrpa6zqhd = new WeakHashMap<>();

   private PlayerBuildTracker() {
   }

   public static void x2yhjdk5vgl5bq(class_3218 var0, class_2818 var1) {
      if (!var1.method_12179().isEmpty()) {
         xp4ojrpa6zqhd.computeIfAbsent(var0, var0x -> new LinkedHashSet<>()).add(var1.method_12004());
      }
   }

   public static void x1f4i4km49zkmu(class_3218 var0) {
      var var1 = xp4ojrpa6zqhd.remove(var0);
      if (var1 != null) {
         for (class_1923 var3 : var1) {
            if (var0.method_14178().method_12123(var3.field_9181, var3.field_9180)) {
               class_3341 var4 = new class_3341(
                  var3.method_8326(), var0.method_31607(), var3.method_8328(), var3.method_8327(), var0.method_31600(), var3.method_8329()
               );

               label105:
               for (class_3449 var6 : var0.method_27056().method_41035(var3, var0x -> true)) {
                  var var7 = var6.method_14963().iterator();

                  while (true) {
                     class_2960 var21;
                     class_3790 var9;
                     while (true) {
                        if (!var7.hasNext()) {
                           continue label105;
                        }

                        class_3443 var8 = (class_3443)var7.next();
                        if (var8 instanceof class_3790 && (var9 = (class_3790)var8).method_14935().method_14657(var4) && var9.method_16644() instanceof class_3781 var10) {
                           try {
                              var21 = var10.method_67689();
                              break;
                           } catch (IllegalStateException var20) {
                           }
                        }
                     }

                     if (var21.method_12836().equals("smart_colonies")
                        && var21.method_12832().startsWith("village/")
                        && var21.method_12832().contains("/houses/")
                        && !var21.method_12832().contains("/infrastructure/")) {
                        class_3499 var12 = (class_3499)var0.method_14183().method_15094(var21).orElseThrow();
                        class_3492 var13 = new class_3492().method_15123(var9.method_16888());

                        for (class_3501 var16 : ((StructureTemplateAccessor)var12).smartAul$getBlockInfoLists().getFirst().method_27125()) {
                           if (!var16.comp_1342().method_27852(class_2246.field_16540) && !var16.comp_1342().method_27852(class_2246.field_10465)) {
                              class_2338 var17 = class_3499.method_15171(var13, var16.comp_1341()).method_10081(var9.method_16648());
                              if (var4.method_14662(var17)) {
                                 class_2680 var18 = var16.comp_1342().method_26186(var9.method_16888());
                                 if (var0.method_8320(var17).method_26204() != var18.method_26204()) {
                                    WorldEditScope.x2aebumvi646n9(() -> var0.method_8652(var17, var18, 18));
                                    class_2586 var19 = var0.method_8321(var17);
                                    if (var19 != null && var16.comp_1343() != null) {
                                       var19.method_58691(class_11352.method_71417(class_8942.field_60348, var0.method_30349(), var16.comp_1343()));
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }
}
