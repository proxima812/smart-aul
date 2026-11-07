package net.smartaul.village;

import net.smartaul.config.SmartAulConfig;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import net.minecraft.class_11;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1308;
import net.minecraft.class_1439;
import net.minecraft.class_1646;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2349;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3341;
import net.minecraft.class_3443;
import net.minecraft.class_3449;
import net.minecraft.class_3481;
import net.minecraft.class_3489;
import net.minecraft.class_3854;
import net.minecraft.class_7045;
import net.minecraft.class_2902.class_2903;

public final class VillageDefense {
   private VillageDefense() {
   }

   public static void xyvb622rqbii1(class_3218 var0, Colony var1, long var2) {
      VillageDefenseState var4 = var1.x2f7z1gnnht5ab();
      if (var2 % 5L == 0L) {
         x2wa9qjwcgiun6(var0, var1);
      }

      if (SmartAulConfig.x2by5f9ehxvwk9().roles.enable_village_walls) {
         if (var2 >= var4.x37dzr2zekycme && !var1.x2s37os1kht7vr() && !var1.x2ad3s8aentmo4().isEmpty() && !RaidShelterTask.active(var0, var1)) {
            var4.x37dzr2zekycme = var2 + 1200L;
            int var5 = x2p7mtd5kxiire(var0, var1);
            if (var4.x14zjd0to6v6yv != 0 && var5 <= var4.x14zjd0to6v6yv) {
               x1afpp1y1qlxxi(var0, var1, var4.x14zjd0to6v6yv);
               x2r4heaqeaed6q(var0, var1);
            } else {
               if (var5 > var1.radius() - 4) {
                  if (var5 + 8 > SmartAulConfig.x2by5f9ehxvwk9().village.expansion_radius_max) {
                     return;
                  }

                  var1.x1ne2dy4ss3d7w(var5 + 8);
               }

               x13dxs2zmn9lxz(var0, var1, var5);
               ColonyManager.xchl1k1tnh3w8(var0).method_80();
            }
         }
      }
   }

   public static int x2p7mtd5kxiire(class_3218 var0, Colony var1) {
      double var2 = 24.0;
      class_3449 var4 = var0.method_27056().method_57560(var1.core(), class_7045.field_37045);
      if (var4 != class_3449.field_16713) {
         for (class_3443 var6 : var4.method_14963()) {
            class_3341 var7 = var6.method_14935();

            for (int var11 : new int[]{var7.method_35415(), var7.method_35418()}) {
               for (int var15 : new int[]{var7.method_35417(), var7.method_35420()}) {
                  var2 = Math.max(var2, Math.sqrt(x1qj4tky9v09mn(var1.core(), var11, var15)));
               }
            }
         }
      }

      for (ConstructionJob var20 : var1.x1ja61emeln2hc()) {
         if (var20.x32oz43hmeynhc() != ConstructionStatus.BLOCKED || var20.x2zq2smhbu3lzo() != 0L) {
            class_3341 var24 = var20.x24kbqv6g4kkrg();

            for (int var32 : new int[]{var24.method_35415(), var24.method_35418()}) {
               for (int var40 : new int[]{var24.method_35417(), var24.method_35420()}) {
                  var2 = Math.max(var2, Math.sqrt(x1qj4tky9v09mn(var1.core(), var32, var40)));
               }
            }
         }
      }

      for (class_2338 var21 : var1.x1hdthtpd2qb8g()) {
         var2 = Math.max(var2, Math.sqrt(x1qj4tky9v09mn(var1.core(), var21.method_10263(), var21.method_10260())));
      }

      for (class_2338 var22 : var1.x29j0o9zdxri2().keySet()) {
         var2 = Math.max(var2, Math.sqrt(x1qj4tky9v09mn(var1.core(), var22.method_10263(), var22.method_10260())));
      }

      if (var1.x329rgb9lpsari().isPresent()) {
         class_2338 var19 = var1.x329rgb9lpsari().get();
         byte var23 = 5;

         for (int var31 : new int[]{-var23, var23}) {
            for (int var39 : new int[]{-var23, var23}) {
               var2 = Math.max(var2, Math.sqrt(x1qj4tky9v09mn(var1.core(), var19.method_10263() + var31, var19.method_10260() + var39)));
            }
         }
      }

      return (int)Math.ceil((var2 + 8.0) / 8.0) * 8;
   }

   private static double x1qj4tky9v09mn(class_2338 var0, int var1, int var2) {
      double var3 = var1 - var0.method_10263();
      double var5 = var2 - var0.method_10260();
      return var3 * var3 + var5 * var5;
   }

   public static void x13dxs2zmn9lxz(class_3218 var0, Colony var1, int var2) {
      VillageDefenseState var3 = var1.x2f7z1gnnht5ab();
      var3.x1q0gb1h69ywu7.clear();
      var3.x1b5j5bmruqafh.clear();
      var3.xb3n8htn3gf9q.clear();
      var3.x2fh8ft5lrq7el.clear();
      var3.x3pic36dm8dawn.clear();
      var3.x16wpjsjmva3od.forEach((var1x, var2x) -> var3.x1q0gb1h69ywu7.add(new WallBlockPlan(var1x, var2x, true)));
      var3.x14zjd0to6v6yv = var2;
      x1afpp1y1qlxxi(var0, var1, var2);
   }

   private static void x1afpp1y1qlxxi(class_3218 var0, Colony var1, int var2) {
      VillageDefenseState var3 = var1.x2f7z1gnnht5ab();
      var var4 = x5vq586vowowx(var0, var1, var2);
      var var5 = new HashSet<>(var3.x3pic36dm8dawn);
      var4.forEach((var2x, var3x) -> {
         long var4x = new class_2338(var2x.method_10263(), 0, var2x.method_10260()).method_10063();
         if (!var5.contains(var4x) && !var3.x2fh8ft5lrq7el.contains(var2x)) {
            var3.x1q0gb1h69ywu7.add(new WallBlockPlan(var2x, var3x, false));
         }

         var3.x3pic36dm8dawn.add(var4x);
      });
      ColonyManager.xchl1k1tnh3w8(var0).method_80();
   }

   private static void x2r4heaqeaed6q(class_3218 var0, Colony var1) {
      VillageDefenseState var2 = var1.x2f7z1gnnht5ab();
      var var3 = new HashSet<class_2338>();
      var2.x1q0gb1h69ywu7.forEach(var1x -> var3.add(var1x.x1oppt2nr5wv2g()));
      var2.x16wpjsjmva3od
         .forEach(
            (var3x, var4) -> {
               if (!var3.contains(var3x)
                  && !var2.x2fh8ft5lrq7el.contains(var3x)
                  && var0.method_14178().method_12123(var3x.method_10263() >> 4, var3x.method_10260() >> 4)
                  && var0.method_8320(var3x).method_26215()) {
                  var2.x1q0gb1h69ywu7.add(new WallBlockPlan(var3x, var4, false));
               }
            }
         );
   }

   public static Map<class_2338, class_2680> x5vq586vowowx(class_3218 var0, Colony var1, int var2) {
      var var3 = new LinkedHashMap<class_2338,class_2680>();
      String var4 = class_3854.method_16930(var0.method_23753(var1.core())).method_29177().method_12832();

      class_2248 var5 = switch (var4) {
         case "desert" -> class_2246.field_9979;
         case "snow" -> class_2246.field_10056;
         default -> class_2246.field_10445;
      };

      class_2248 var6 = switch (var4) {
         case "desert" -> class_2246.field_10361;
         case "savanna" -> class_2246.field_10184;
         default -> class_2246.field_10445;
      };

      class_2248 var21 = switch (var4) {
         case "desert" -> class_2246.field_10292;
         case "savanna" -> class_2246.field_10533;
         case "taiga", "snow" -> class_2246.field_10037;
         default -> class_2246.field_10431;
      };

      class_2248 var22 = switch (var4) {
         case "savanna" -> class_2246.field_10457;
         case "taiga", "snow" -> class_2246.field_10291;
         default -> class_2246.field_10188;
      };
      var var23 = new LinkedHashSet<class_2338>();

      for (int var24 = -var2; var24 <= var2; var24++) {
         int var11 = (int)Math.round(Math.sqrt(var2 * var2 - var24 * var24));

         for (int var15 : new int[]{-1, 1}) {
            var23.add(new class_2338(var1.core().method_10263() + var24, 0, var1.core().method_10260() + var15 * var11));
            var23.add(new class_2338(var1.core().method_10263() + var15 * var11, 0, var1.core().method_10260() + var24));
         }
      }

      for (class_2338 var26 : var23) {
         int var27 = var26.method_10263();
         int var28 = var26.method_10260();
         if (var0.method_14178().method_12123(var27 >> 4, var28 >> 4)) {
            int var29 = var0.method_8624(class_2903.field_13203, var27, var28);

            for (int var30 = 0; var30 < 20 && var29 > var0.method_31607(); var30++) {
               class_2680 var16 = var0.method_8320(new class_2338(var27, var29 - 1, var28));
               if (!var16.method_45474() && !var16.method_26164(class_3481.field_15503) && !var16.method_26164(class_3481.field_15475)) {
                  break;
               }

               var29--;
            }

            class_2338 var31 = new class_2338(var27, var29, var28);
            if (WorldEditPolicy.xroinv4hk6l7h(var0.method_8320(var31.method_10074())) || TerrainSupport.x3hqfrterusj2u(var0, var31.method_10074())) {
               boolean var32 = Math.abs(var27 - var1.core().method_10263()) <= 2;
               boolean var17 = Math.abs(var28 - var1.core().method_10260()) <= 1;
               boolean var18 = var0.method_8320(var31.method_10074()).method_27852(class_2246.field_10194);
               boolean var19 = var32 || var17 || var18;
               if (var19) {
                  var3.put(var31, (class_2680)var22.method_9564().method_11657(class_2349.field_11177, var32 ? class_2350.field_11043 : class_2350.field_11034));
                  if (var32 && var28 > var1.core().method_10260()) {
                     var3.put(var31.method_10086(4), var5.method_9564());
                  }
               } else {
                  boolean var20 = Math.floorMod(var27 + var28, 6) == 0;
                  var3.put(var31, var5.method_9564());
                  var3.put(var31.method_10084(), (var20 ? var21 : var6).method_9564());
                  var3.put(var31.method_10086(2), (var20 ? var21 : var6).method_9564());
                  if (var20) {
                     var3.put(var31.method_10086(3), var5.method_9564());
                  }
               }
            }
         }
      }

      return var3;
   }

   public static boolean x3hz8l3ppqisjl(class_3218 var0, class_2338 var1) {
      return ColonyManager.xchl1k1tnh3w8(var0).colonies().stream().anyMatch(var1x -> var1x.x2f7z1gnnht5ab().x16wpjsjmva3od.containsKey(var1));
   }

   public static boolean x3di73z4zkdknf(class_3218 var0, class_2338 var1) {
      return var0.method_8320(var1).method_26204() instanceof class_2349 && x3hz8l3ppqisjl(var0, var1);
   }

   public static boolean x1hmaqzrqcstj(Colony var0, class_2338 var1, int var2) {
      VillageDefenseState var3 = var0.x2f7z1gnnht5ab();
      if (var3.x14zjd0to6v6yv == 0) {
         return false;
      } else {
         double var4 = Math.sqrt(x1qj4tky9v09mn(var0.core(), var1.method_10263(), var1.method_10260()));
         return Math.abs(var4 - var3.x14zjd0to6v6yv) <= var2 + 2;
      }
   }

   public static boolean xuj80syz8c60l(class_3218 var0, Colony var1, class_1646 var2) {
      boolean var3 = var1.x2f7z1gnnht5ab().x1q0gb1h69ywu7.stream().anyMatch(WallBlockPlan::xttubpoe0ch3r);
      if (!var1.x2f7z1gnnht5ab().x1q0gb1h69ywu7.isEmpty()
         && SmartAulConfig.x2by5f9ehxvwk9().roles.enable_village_walls
         && (var3 || var1.x1n1a2vp01kxzf().isEmpty())
         && !VillageRepair.x81tynoeus5lh(var0, var1)
         && !var1.x2s37os1kht7vr()
         && !RaidShelterTask.active(var0, var1)) {
         var var4 = var0.method_18023(
               class_1299.field_6077,
               ColonyManager.x150ccxomrlozr(var0, var1),
               var1x -> var1x.method_5805() && !var1x.method_6109() && var1.roleOf(var1x.method_5667()) == ColonyRole.BUILDER
            )
            .stream()
            .sorted(Comparator.comparing(class_1297::method_5667))
            .toList();
         if (var4.isEmpty()) {
            return false;
         } else if (var4.size() == 1 && var1.x3e4ycl8cmmmgc() && !var3) {
            return false;
         } else if (!((class_1646)var4.getLast()).method_5667().equals(var2.method_5667())) {
            return false;
         } else {
            WallBlockPlan var5 = x2h9dz6t7qspz2(var0, var1, var2);
            if (var5 == null) {
               return false;
            } else if (var4.size() == 1 && var1.x3e4ycl8cmmmgc() && !xccmx864swr9g(var1, var5.x1oppt2nr5wv2g())) {
               return false;
            } else {
               return var5.xttubpoe0ch3r() ? true : ColonyManager.x1mlne8tl8k5ag(var0, var1, xdo05oqfqaqzq(var5.x1bbgyigo2fq58())) > 32;
            }
         }
      } else {
         return false;
      }
   }

   public static void x15itjvs0tajaz(class_3218 var0, Colony var1, class_2338 var2, int var3, int var4) {
      if (var1.x2f7z1gnnht5ab().x14zjd0to6v6yv != 0) {
         double var5 = 0.0;

         for (int var10 : new int[]{var2.method_10263() - 4, var2.method_10263() + var3 + 4}) {
            for (int var14 : new int[]{var2.method_10260() - 4, var2.method_10260() + var4 + 4}) {
               var5 = Math.max(var5, Math.sqrt(x1qj4tky9v09mn(var1.core(), var10, var14)));
            }
         }

         int var15 = (int)Math.ceil((var5 + 8.0) / 8.0) * 8;
         if (var15 > var1.x2f7z1gnnht5ab().x14zjd0to6v6yv && var15 <= var1.radius() - 4) {
            x13dxs2zmn9lxz(var0, var1, var15);
            ColonyManager.xchl1k1tnh3w8(var0).method_80();
         }
      }
   }

   public static Predicate<class_1799> xdo05oqfqaqzq(class_2680 var0) {
      return !var0.method_26164(class_3481.field_15475) && !(var0.method_26204() instanceof class_2349)
         ? var1 -> var1.method_31573(class_3489.field_25808)
            || var1.method_31574(class_1802.field_20384)
            || var1.method_31574(class_1802.field_20408)
            || var1.method_31574(var0.method_26204().method_8389())
         : var1 -> var1.method_31573(class_3489.field_15539) || var1.method_31574(var0.method_26204().method_8389());
   }

   public static WallBlockPlan x2h9dz6t7qspz2(class_3218 var0, Colony var1, class_1646 var2) {
      var1.x2f7z1gnnht5ab().x1b5j5bmruqafh.entrySet().removeIf(var1x -> var1x.getValue() <= var0.method_75260());
      boolean var3 = var1.x2f7z1gnnht5ab()
         .x1q0gb1h69ywu7
         .stream()
         .anyMatch(
            var2x -> var2x.xttubpoe0ch3r()
               && !var1.x2f7z1gnnht5ab().x1b5j5bmruqafh.containsKey(var2x.x1oppt2nr5wv2g())
               && var0.method_14178().method_12123(var2x.x1oppt2nr5wv2g().method_10263() >> 4, var2x.x1oppt2nr5wv2g().method_10260() >> 4)
         );
      var var4 = new HashMap<class_2680, Boolean>();
      return var1.x2f7z1gnnht5ab()
         .x1q0gb1h69ywu7
         .stream()
         .filter(
            var4x -> var4x.xttubpoe0ch3r() == var3
               && !var1.x2f7z1gnnht5ab().x1b5j5bmruqafh.containsKey(var4x.x1oppt2nr5wv2g())
               && (
                  var4x.xttubpoe0ch3r()
                     || var4.computeIfAbsent(var4x.x1bbgyigo2fq58(), var2xx -> ColonyManager.x1mlne8tl8k5ag(var0, var1, xdo05oqfqaqzq(var2xx)) > 32)
               )
               && var0.method_14178().method_12123(var4x.x1oppt2nr5wv2g().method_10263() >> 4, var4x.x1oppt2nr5wv2g().method_10260() >> 4)
         )
         .min(
            Comparator.<WallBlockPlan>comparingInt(var1x -> var1x.xttubpoe0ch3r() && xccmx864swr9g(var1, var1x.x1oppt2nr5wv2g()) ? 0 : 1)
               .thenComparingDouble(var1x -> var1x.x1oppt2nr5wv2g().method_10262(var2.method_24515()))
         )
         .orElse(null);
   }

   private static boolean xccmx864swr9g(Colony var0, class_2338 var1) {
      return var0.xhu9xvlie05r6(var1, 2) || var0.x1n1a2vp01kxzf().stream().anyMatch(var1x -> var1x.method_10084().method_10262(var1) <= 4.0);
   }

   public static void xj9o3at3dqqse(class_3218 var0, Colony var1, WallBlockPlan var2) {
      if (var1.x2f7z1gnnht5ab().xb3n8htn3gf9q.merge(var2.x1oppt2nr5wv2g(), 1, Integer::sum) >= 3) {
         x33x279v3webhm(var0, var1, var2);
      } else {
         var1.x2f7z1gnnht5ab().x1b5j5bmruqafh.put(var2.x1oppt2nr5wv2g(), var0.method_75260() + 1200L);
      }
   }

   public static void x33x279v3webhm(class_3218 var0, Colony var1, WallBlockPlan var2) {
      VillageDefenseState var3 = var1.x2f7z1gnnht5ab();
      var3.x1q0gb1h69ywu7.remove(var2);
      var3.x1b5j5bmruqafh.remove(var2.x1oppt2nr5wv2g());
      var3.xb3n8htn3gf9q.remove(var2.x1oppt2nr5wv2g());
      var3.x2fh8ft5lrq7el.add(var2.x1oppt2nr5wv2g());
      var3.x1fvrorwquv0wt++;
      ColonyManager.xchl1k1tnh3w8(var0).method_80();
   }

   public static void x2kvmlso28quo8(class_3218 var0, Colony var1, WallBlockPlan var2, boolean var3) {
      VillageDefenseState var4 = var1.x2f7z1gnnht5ab();
      var4.x1q0gb1h69ywu7.remove(var2);
      var4.x1b5j5bmruqafh.remove(var2.x1oppt2nr5wv2g());
      var4.xb3n8htn3gf9q.remove(var2.x1oppt2nr5wv2g());
      if (var2.xttubpoe0ch3r()) {
         var4.x16wpjsjmva3od.remove(var2.x1oppt2nr5wv2g());
      } else if (var3) {
         var4.x16wpjsjmva3od.put(var2.x1oppt2nr5wv2g(), var2.x1bbgyigo2fq58());
      } else {
         var4.x1fvrorwquv0wt++;
      }

      var1.x2xqc3q79vfkw3().remove(var2.x1oppt2nr5wv2g());
      ColonyManager.xchl1k1tnh3w8(var0).method_80();
   }

   private static void x2wa9qjwcgiun6(class_3218 var0, Colony var1) {
      for (Entry var3 : var1.x2f7z1gnnht5ab().x16wpjsjmva3od.entrySet()) {
         class_2338 var4 = (class_2338)var3.getKey();
         if (((class_2680)var3.getValue()).method_26204() instanceof class_2349
            && var0.method_14178().method_12123(var4.method_10263() >> 4, var4.method_10260() >> 4)) {
            class_2680 var5 = var0.method_8320(var4);
            if (var5.method_26204() instanceof class_2349 && !(Boolean)var5.method_11654(class_2349.field_11021)) {
               boolean var6 = var0.method_8333(
                     null,
                     new class_238(var4).method_1009(6.0, 2.0, 6.0),
                     var0x -> var0x.method_5805() && (var0x instanceof class_1646 || var0x instanceof class_1439)
                  )
                  .stream()
                  .anyMatch(var1x -> {
                     if (!(var1x instanceof class_1308 var2 && !(var1x instanceof class_1646 var3x && (var3x.method_6113() || var3x.method_18009())))) {
                        return false;
                     } else if (var1x.method_5829().method_994(new class_238(var4).method_1009(1.0, 1.0, 1.0))) {
                        return true;
                     } else {
                        class_11 var5x = var2.method_5942().method_6345();
                        if (var5x != null && !var5x.method_46()) {
                           for (int var4x = var5x.method_39(); var4x < Math.min(var5x.method_38(), var5x.method_39() + 8); var4x++) {
                              if (var5x.method_31031(var4x).method_10262(var4) <= 9.0) {
                                 return true;
                              }
                           }

                           return false;
                        } else {
                           return false;
                        }
                     }
                  });
               if ((Boolean)var5.method_11654(class_2349.field_11026) != var6) {
                  var0.method_8652(var4, (class_2680)var5.method_11657(class_2349.field_11026, var6), 3);
               }
            }
         }
      }
   }
}
