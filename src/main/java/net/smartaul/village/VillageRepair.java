package net.smartaul.village;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Predicate;
import net.minecraft.class_1299;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_2244;
import net.minecraft.class_2246;
import net.minecraft.class_2302;
import net.minecraft.class_2323;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_2742;
import net.minecraft.class_2756;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3443;
import net.minecraft.class_3449;
import net.minecraft.class_3492;
import net.minecraft.class_3499;
import net.minecraft.class_3781;
import net.minecraft.class_3790;
import net.minecraft.class_7045;
import net.smartaul.mixin.StructureTemplateAccessor;

public final class VillageRepair {
   private static final Map<Colony, Map<class_2338, Long>> xm2t2pguz7drc = new WeakHashMap<>();

   private VillageRepair() {
   }

   public static boolean x2b8s98wj2zcbw(class_3218 var0, Colony var1, class_2338 var2) {
      if (ColonyWorldData.get(var0).land().stream().anyMatch(var1x -> var1x.contains(var2))) {
         return false;
      } else if (!var0.method_14178().method_12123(var2.method_10263() >> 4, var2.method_10260() >> 4)) {
         return false;
      } else {
         if (var1.safetyCovers.contains(var2) && !VillageSafety.solid(var0, var2) && !VillageSafety.canCover(var0, var1, var2)) return false;
         class_2680 var3 = var1.x2xqc3q79vfkw3().get(var2);
         class_2680 var4 = var0.method_8320(var2);
         if (var3 == null) {
            return false;
         } else if (var4.method_26204() == var3.method_26204()) {
            return true;
         } else if (var0.method_75260() < xm2t2pguz7drc.getOrDefault(var1, Map.of()).getOrDefault(var2, Long.MIN_VALUE)) {
            return false;
         } else if ((var4.method_26215() || var4.method_45474()) && WorldEditPolicy.x13f7be5vlapn7(var0, var2)) {
            class_1792 var5 = HouseBuilderTask.materialItem(var3);
            if (var5 == class_1802.field_8162) {
               return false;
            } else {
               Predicate<net.minecraft.class_1799> var6 = HouseBuilderTask.materialPredicate(var3, var5);
               return ColonyManager.x1mlne8tl8k5ag(var0, var1, var6) > 0
                  ? true
                  : var0.method_18023(
                        class_1299.field_6077,
                        ColonyManager.x150ccxomrlozr(var0, var1),
                        var1x -> var1x.method_5805() && var1.roleOf(var1x.method_5667()) == ColonyRole.BUILDER
                     )
                     .stream()
                     .anyMatch(var1x -> var6.test(var1x.method_6047()) || var1x.method_35199().method_43256(var6));
            }
         } else {
            return false;
         }
      }
   }

   public static void xqxgtse6quf4h(class_3218 var0, Colony var1, class_2338 var2) {
      xm2t2pguz7drc.computeIfAbsent(var1, var0x -> new HashMap<>()).put(var2.method_10062(), var0.method_75260() + 600L);
   }

   public static boolean x81tynoeus5lh(class_3218 var0, Colony var1) {
      return var1.x2xqc3q79vfkw3().keySet().stream().anyMatch(var2 -> x2b8s98wj2zcbw(var0, var1, var2));
   }

   public static boolean x5epxvcc1p5ir(class_3218 var0, Colony var1, class_2338 var2) {
      if (ColonyWorldData.get(var0).land().stream().anyMatch(var1x -> var1x.contains(var2))) {
         return false;
      } else if (var1.x29j0o9zdxri2().containsKey(var2) || var1.core().equals(var2)) {
         return true;
      } else if (var1.x1ja61emeln2hc()
         .stream()
         .anyMatch(var1x -> var1x.x2nfrt8qxdscso().isPresent() && var1x.x2s8emavervqrz() && var1x.x24kbqv6g4kkrg().method_14662(var2))) {
         return true;
      } else if (ColonyWorldData.get(var0).isPlayerBlock(var2)) {
         return false;
      } else if (var1.x2f7z1gnnht5ab().x16wpjsjmva3od.containsKey(var2)) {
         return true;
      } else {
         return var1.x1ja61emeln2hc().stream().anyMatch(var1x -> var1x.x24kbqv6g4kkrg().method_14662(var2)) ? true : x239t1m3hfi74d(var0, var2);
      }
   }

   public static boolean x239t1m3hfi74d(class_3218 var0, class_2338 var1) {
      if (!var0.method_14178().method_12123(var1.method_10263() >> 4, var1.method_10260() >> 4)) {
         return false;
      } else {
         class_3449 var2 = var0.method_27056().method_57560(var1, class_7045.field_37045);
         return var2 == class_3449.field_16713 ? false : var2.method_14963().stream().anyMatch(var1x -> {
            if (var1x instanceof class_3790 var2x && var1x.method_14935().method_14662(var1) && var2x.method_16644() instanceof class_3781 var3) {
               try {
                  String var6 = var3.method_67689().method_12832();
                  return var6.contains("/houses/") && !var6.contains("farm") && !var6.contains("pen") && !var6.contains("animal");
               } catch (IllegalStateException var5) {
                  return false;
               }
            } else {
               return false;
            }
         });
      }
   }

   public static boolean x1e11i1kd8301v(class_3218 var0, class_2338 var1) {
      if (!var0.method_14178().method_12123(var1.method_10263() >> 4, var1.method_10260() >> 4)) {
         return false;
      } else {
         class_3449 var2 = var0.method_27056().method_57560(var1, class_7045.field_37045);
         if (var2 == class_3449.field_16713) {
            return false;
         } else {
            for (class_3443 var4 : var2.method_14963()) {
               if (var4 instanceof class_3790 var5 && var5.method_14935().method_14662(var1) && var5.method_16644() instanceof class_3781 var6) {
                  class_2960 var12;
                  try {
                     var12 = var6.method_67689();
                  } catch (IllegalStateException var11) {
                     continue;
                  }

                  if (var12.method_12832().contains("farm")) {
                     class_3499 var8 = (class_3499)var0.method_14183().method_15094(var12).orElse(null);
                     if (var8 != null) {
                        class_3492 var9 = new class_3492().method_15123(var5.method_16888());
                        var var10 = ((StructureTemplateAccessor)var8).smartAul$getBlockInfoLists().getFirst().method_27125();
                        if (var10.stream()
                           .anyMatch(
                              var3 -> var3.comp_1342().method_27852(class_2246.field_10362)
                                 && class_3499.method_15171(var9, var3.comp_1341()).method_10081(var5.method_16648()).equals(var1)
                           )) {
                           return true;
                        }
                     }
                  }
               }
            }

            return false;
         }
      }
   }

   public static Map<class_2338, class_2680> xg03tmq830uoq(class_3218 var0, class_2338 var1) {
      return x335dkgs8aizip(var0, var1, var0.method_8320(var1));
   }

   public static Map<class_2338, class_2680> x335dkgs8aizip(class_3218 var0, class_2338 var1, class_2680 var2) {
      var var3 = new LinkedHashMap<class_2338,class_2680>();
      if (!var2.method_26215()
         && !var2.method_45474()
         && !(var2.method_26204() instanceof class_2302)
         && !var2.method_27852(class_2246.field_10194)
         && !var2.method_27852(class_2246.field_10362)) {
         if (var2.method_26204() instanceof class_2323) {
            class_2338 var4 = var2.method_11654(class_2323.field_10946) == class_2756.field_12607 ? var1 : var1.method_10074();

            for (class_2338 var8 : new class_2338[]{var4, var4.method_10084()}) {
               class_2680 var9 = var8.equals(var1) ? var2 : var0.method_8320(var8);
               if (var9.method_26204() instanceof class_2323) {
                  var3.put(
                     var8.method_10062(),
                     (class_2680)((class_2680)var9.method_11657(class_2323.field_10945, false)).method_11657(class_2323.field_10940, false)
                  );
               }
            }
         } else if (var2.method_26204() instanceof class_2244) {
            class_2338 var10 = var2.method_11654(class_2244.field_9967) == class_2742.field_12557
               ? var1
               : var1.method_10093(((class_2350)var2.method_11654(class_2244.field_11177)).method_10153());

            for (class_2338 var14 : new class_2338[]{var10, var10.method_10093((class_2350)var2.method_11654(class_2244.field_11177))}) {
               class_2680 var15 = var14.equals(var1) ? var2 : var0.method_8320(var14);
               if (var15.method_26204() instanceof class_2244) {
                  var3.put(var14.method_10062(), (class_2680)var15.method_11657(class_2244.field_9968, false));
               }
            }
         } else {
            var3.put(var1.method_10062(), var2);
         }

         return var3;
      } else {
         return var3;
      }
   }

   public static void x17m23t077i8f8(class_3218 var0, Colony var1, Map<class_2338, class_2680> var2) {
      var2.forEach((var2x, var3) -> {
         if (!var0.method_8320(var2x).equals(var3)) {
            var1.x2xqc3q79vfkw3().putIfAbsent(var2x, var3);
         }
      });
      ColonyManager.xchl1k1tnh3w8(var0).method_80();
   }
}
