package net.smartaul.village;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.Map.Entry;
import net.minecraft.class_11;
import net.minecraft.class_1439;
import net.minecraft.class_1646;
import net.minecraft.class_1657;
import net.minecraft.class_2323;
import net.minecraft.class_2338;
import net.minecraft.class_2349;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2533;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_2756;
import net.minecraft.class_3218;
import net.minecraft.class_3419;
import net.minecraft.class_4140;
import net.minecraft.class_5712;
import net.minecraft.class_8177;
import net.smartaul.mixin.TrapdoorBlockAccessor;

public final class VillageSleep {
   private static final Map<class_3218, Map<class_2338, Long>> x125nzphw6b46r = new WeakHashMap<>();

   private VillageSleep() {
   }

   public static void x2ik4hs9xwj2v7() {
      x125nzphw6b46r.clear();
   }

   public static void x2zyakuv0vnixz(class_3218 var0, class_1646 var1) {
      x2pmhgbzwd63c4(var0, var1, var0.method_75260());
   }

   public static void x2pmhgbzwd63c4(class_3218 var0, class_1646 var1, long var2) {
      if (!ColonyManager.x3psqezd5jem54(var0, var1.method_24515()).isEmpty() || !ColonyManager.x2yj2v4iudqlvb(var0, var1.method_5667()).isEmpty()) {
         var1.method_5942().method_70687(!var1.method_6113());
         if (Math.floorMod(var2 + var1.method_5628(), 3) == 0) {
            var var4 = x125nzphw6b46r.computeIfAbsent(var0, var0x -> new HashMap<>());
            class_2338 var5 = var1.method_24515();

            for (class_2338 var7 : class_2338.method_10097(var5.method_10069(-4, -1, -4), var5.method_10069(4, 2, 4))) {
               if (var0.method_14178().method_12123(var7.method_10263() >> 4, var7.method_10260() >> 4)) {
                  class_2680 var8 = var0.method_8320(var7);
                  boolean var9 = var8.method_26204() instanceof class_2349;
                  boolean var10 = var8.method_26204() instanceof class_2323 && class_2323.method_24796(var8);
                  boolean var11 = var8.method_26204() instanceof class_2533 var12 && ((TrapdoorBlockAccessor)var12).smartAul$getBlockSetType().comp_1471();
                  if ((var9 || var10 || var11)
                     && (!var9 || !VillageDefense.x3di73z4zkdknf(var0, var7))
                     && (!var10 || var8.method_11654(class_2323.field_10946) != class_2756.field_12609)
                     && (!var9 || !(Boolean)var8.method_11654(class_2349.field_11021))
                     && (!var10 || !(Boolean)var8.method_11654(class_2323.field_10940))
                     && (!var11 || !(Boolean)var8.method_11654(class_2533.field_11629))
                     && (
                        !var9
                           || !ColonyManager.x3psqezd5jem54(var0, var7)
                              .flatMap(Colony::x329rgb9lpsari)
                              .map(var1x -> var1x.method_10076(5).equals(var7))
                              .orElse(false)
                     )) {
                     double var21 = var7.method_10262(var5);
                     if (!(var21 > 16.25)) {
                        class_2338 var14 = var7.method_10062();
                        if (x2s7jlhnk5vi31(var1, var14) || var11 && xuh5rud0uhpzw(var1, var14)) {
                           if (!var11 || !var14.equals(var5.method_10074())) {
                              if (var9 && !(Boolean)var8.method_11654(class_2349.field_11026)) {
                                 var0.method_8652(var14, (class_2680)var8.method_11657(class_2349.field_11026, true), 3);
                              } else if (var10 && !(Boolean)var8.method_11654(class_2323.field_10945)) {
                                 ((class_2323)var8.method_26204()).method_10033(var1, var0, var8, var14, true);
                              } else if (var11 && !(Boolean)var8.method_11654(class_2533.field_11631)) {
                                 x16779z1in3x0y(var0, var14, var8, true);
                              }

                              var4.put(var14, var2);
                           }
                        } else if (Math.floorMod(var0.method_8532(), 24000) >= 12000 && (Boolean)var8.method_11654(class_2741.field_12537)) {
                           var4.putIfAbsent(var14, var2 - 12L);
                        }
                     }
                  }
               }
            }

            if (Math.floorMod(var2, 5) == 0) {
               var var15 = var4.entrySet().iterator();

               while (var15.hasNext()) {
                  Entry var16 = (Entry)var15.next();
                  class_2338 var17 = (class_2338)var16.getKey();
                  if (var0.method_14178().method_12123(var17.method_10263() >> 4, var17.method_10260() >> 4)) {
                     class_2680 var18 = var0.method_8320(var17);
                     if (VillageDefense.x3di73z4zkdknf(var0, var17)) {
                        var15.remove();
                     } else if (!(var18.method_26204() instanceof class_2349)
                        && !(var18.method_26204() instanceof class_2323)
                        && !(var18.method_26204() instanceof class_2533)) {
                        var15.remove();
                     } else if ((!(var18.method_26204() instanceof class_2323) || !(Boolean)var18.method_11654(class_2323.field_10940))
                        && (!(var18.method_26204() instanceof class_2349) || !(Boolean)var18.method_11654(class_2349.field_11021))
                        && (!(var18.method_26204() instanceof class_2533) || !(Boolean)var18.method_11654(class_2533.field_11629))) {
                        if (var2 - (Long)var16.getValue() >= 12L) {
                           boolean var19 = !var0.method_8333(
                                 null,
                                 new class_238(var17).method_1009(0.15, 0.2, 0.15),
                                 var0x -> var0x.method_5805()
                                    && !var0x.method_7325()
                                    && (var0x instanceof class_1646 || var0x instanceof class_1439 || var0x instanceof class_1657)
                              )
                              .isEmpty();
                           if (var19) {
                              var16.setValue(var2);
                           } else {
                              if (var18.method_26204() instanceof class_2349 && (Boolean)var18.method_11654(class_2349.field_11026)) {
                                 var0.method_8652(var17, (class_2680)var18.method_11657(class_2349.field_11026, false), 3);
                              } else if (var18.method_26204() instanceof class_2323 var20 && (Boolean)var18.method_11654(class_2323.field_10945)) {
                                 var20.method_10033(var1, var0, var18, var17, false);
                              } else if (var18.method_26204() instanceof class_2533 && (Boolean)var18.method_11654(class_2533.field_11631)) {
                                 x16779z1in3x0y(var0, var17, var18, false);
                              }

                              var15.remove();
                           }
                        }
                     } else {
                        var15.remove();
                     }
                  }
               }

               if (var4.isEmpty()) {
                  x125nzphw6b46r.remove(var0);
               }
            }
         }
      }
   }

   private static void x16779z1in3x0y(class_3218 var0, class_2338 var1, class_2680 var2, boolean var3) {
      if (var0.method_8652(var1, (class_2680)var2.method_11657(class_2533.field_11631, var3), 3)) {
         class_8177 var4 = ((TrapdoorBlockAccessor)var2.method_26204()).smartAul$getBlockSetType();
         var0.method_8396(null, var1, var3 ? var4.comp_1294() : var4.comp_1293(), class_3419.field_15245, 1.0F, 1.0F);
         var0.method_33596(null, var3 ? class_5712.field_28168 : class_5712.field_28169, var1);
      }
   }

   private static boolean x2s7jlhnk5vi31(class_1646 var0, class_2338 var1) {
      if (!var0.method_6113() && !var0.method_18009()) {
         class_11 var2 = var0.method_5942().method_6345();
         if (var2 != null && !var2.method_46() && !(var0.method_24515().method_10262(var1) > 12.0)) {
            for (int var3 = Math.max(0, var2.method_39() - 1); var3 < Math.min(var2.method_38(), var2.method_39() + 3); var3++) {
               class_2338 var4 = var2.method_31031(var3);
               if (Math.abs(var4.method_10264() - var1.method_10264()) <= 1
                  && Math.abs(var4.method_10263() - var1.method_10263()) <= 1
                  && Math.abs(var4.method_10260() - var1.method_10260()) <= 1) {
                  return true;
               }
            }

            return false;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private static boolean xuh5rud0uhpzw(class_1646 var0, class_2338 var1) {
      if (!var0.method_6113() && !var0.method_18009() && !(var0.method_24515().method_10262(var1) > 9.0)) {
         class_2338 var2 = VillagerShelter.x37u4pqy3fbps(var0);
         if (var2 == null) {
            var2 = var0.method_18868().method_18904(class_4140.field_18445).map(var0x -> var0x.method_19094().method_18989()).orElse(null);
         }

         if (var2 != null && !var2.equals(var0.method_24515())) {
            class_243 var3 = var0.method_73189().method_1031(0.0, 0.5, 0.0);
            class_243 var4 = class_243.method_24953(var2).method_1031(0.0, 0.5, 0.0);
            class_243 var5 = var4.method_1020(var3);
            double var6 = var5.method_1027();
            if (var6 < 0.01) {
               return false;
            } else {
               class_243 var8 = class_243.method_24953(var1);
               double var9 = Math.clamp(var8.method_1020(var3).method_1026(var5) / var6, 0.0, 1.0);
               return var3.method_1019(var5.method_1021(var9)).method_1025(var8) <= 1.5;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }
}
