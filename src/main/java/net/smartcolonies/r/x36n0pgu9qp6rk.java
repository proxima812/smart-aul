package net.smartcolonies.r;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.class_1263;
import net.minecraft.class_1297;
import net.minecraft.class_1646;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_3218;
import net.minecraft.class_3489;
import net.minecraft.class_7923;

public final class x36n0pgu9qp6rk {
   private static final long x14f8xquqjscjr = 200L;

   private x36n0pgu9qp6rk() {
   }

   public static void xkfga2dpst7kt(class_3218 var0, x15kgoaq6i3m6i var1, List<class_1646> var2, long var3) {
      if (var3 >= var1.x3b9zegn105yce() && !var1.x2ad3s8aentmo4().isEmpty()) {
         var1.x3kvixtipq5eqg(var3 + 200L);
         var var5 = var2.stream()
            .filter(var1x -> !var1x.method_6109() && var1.roleOf(var1x.method_5667()) != x1rxiy9mtu5b3h.CITIZEN)
            .sorted(Comparator.comparing(class_1297::method_5667))
            .toList();
         if (!var5.isEmpty()) {
            x2n55y2tom37h2.x2u0vsam1tcmhq(var0, var1);
            boolean var6 = var1.cachedFood() < 32;
            boolean var7 = var1.cachedLogs() < 8;
            if (!var6 && !var7) {
               class_1646 var13 = var5.stream().filter(var1x -> {
                  x1rxiy9mtu5b3h var2x = var1.roleOf(var1x.method_5667());
                  return var2x == x1rxiy9mtu5b3h.GUARD || var2x == x1rxiy9mtu5b3h.BUILDER;
               }).findFirst().orElse((class_1646)var5.getFirst());
               if (xt78n188f3w97.x1gfvg3f4hcsus(var0, var1) < xt78n188f3w97.x20ln0j5i9gefl(var1)) {
                  if (x2egalzebb4k1a(var0, var1, var0x -> var0x.method_31574(class_1802.field_46249)) == 0
                     && x33p9oz0tzh54f(var0, var1, var13, new class_1799(class_1802.field_46249, 4), 1, "семена тыквы для фермы")) {
                     return;
                  }

                  int var14 = xt78n188f3w97.x20ln0j5i9gefl(var1) - xt78n188f3w97.x1gfvg3f4hcsus(var0, var1);
                  int var16 = x2egalzebb4k1a(var0, var1, x36n0pgu9qp6rk::x2l8k383vfoz57);
                  if (var16 < var14
                     && x33p9oz0tzh54f(var0, var1, var13, new class_1799(class_1802.field_17518, Math.min(2, var14 - var16)), 1, "тыква для голема")) {
                     return;
                  }

                  int var18 = x2egalzebb4k1a(var0, var1, var0x -> var0x.method_31574(class_1802.field_8620));
                  if (var18 < 36 && x33p9oz0tzh54f(var0, var1, var13, new class_1799(class_1802.field_8620, Math.min(8, 36 - var18)), 2, "железо для голема")) {
                     return;
                  }
               }

               int var15 = Math.floorMod(var1.x3dkkolbe7ust6(), var5.size());
               var1.x18w8ltfnp5moy();

               for (int var17 = 0; var17 < var5.size(); var17++) {
                  class_1646 var19 = (class_1646)var5.get((var15 + var17) % var5.size());
                  x1rxiy9mtu5b3h var12 = var1.roleOf(var19.method_5667());
                  if (x1llll0zw8rv2n(var0, var1, var19, var12) || x3ce7hz5kx2dh(var0, var1, var19, var12)) {
                     return;
                  }
               }
            } else {
               class_1646 var8 = (class_1646)var5.getFirst();
               if (!var6 || !x33p9oz0tzh54f(var0, var1, var8, new class_1799(class_1802.field_8229, 8), 2, "аварийный запас еды")) {
                  if (!var7
                     || var6 && var1.cachedLogs() >= 5
                     || !x33p9oz0tzh54f(var0, var1, var8, new class_1799(class_1802.field_8583, 8), 1, "древесина для запуска хозяйства")) {
                     for (class_1646 var10 : var5) {
                        x1rxiy9mtu5b3h var11 = var1.roleOf(var10.method_5667());
                        if ((var11 == x1rxiy9mtu5b3h.MINER || var11 == x1rxiy9mtu5b3h.BUILDER)
                           && xyt7zxwpica1j(
                              var0,
                              var1,
                              var10,
                              var11,
                              var0x -> var0x.method_31574(class_1802.field_20412) || var0x.method_31574(class_1802.field_29025),
                              32,
                              64,
                              1,
                              "камень на снабжение"
                           )) {
                           return;
                        }

                        if (x3ce7hz5kx2dh(var0, var1, var10, var11)) {
                           return;
                        }
                     }

                     x2vaj7rsm57i4n.x1qd0mutwsr3sc(var8, "не хватает еды/древесины: копим изумруды на снабжение");
                  }
               }
            }
         }
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private static boolean x1llll0zw8rv2n(class_3218 var0, x15kgoaq6i3m6i var1, class_1646 var2, x1rxiy9mtu5b3h var3) {
      return switch (var3) {
         case BUILDER -> var1.cachedLogs() < 48 && x33p9oz0tzh54f(var0, var1, var2, new class_1799(class_1802.field_8583, 16), 2, "брёвна")
            || var1.cachedStone() < 96 && x33p9oz0tzh54f(var0, var1, var2, new class_1799(class_1802.field_20412, 32), 2, "камень");
         case MINER -> var1.cachedLogs() < 16 && x33p9oz0tzh54f(var0, var1, var2, new class_1799(class_1802.field_8583, 8), 1, "крепи шахты")
            || x2egalzebb4k1a(var0, var1, var0x -> var0x.method_31574(class_1802.field_8713) || var0x.method_31574(class_1802.field_8665)) < 16
               && x33p9oz0tzh54f(var0, var1, var2, new class_1799(class_1802.field_8713, 8), 1, "уголь");
         case FARMER -> x2egalzebb4k1a(var0, var1, var0x -> var0x.method_31574(class_1802.field_46249)) < 4
               && x33p9oz0tzh54f(var0, var1, var2, new class_1799(class_1802.field_46249, 4), 1, "семена тыквы")
            || x2egalzebb4k1a(var0, var1, x36n0pgu9qp6rk::x161x1w456knty) < 16
               && x33p9oz0tzh54f(var0, var1, var2, new class_1799(class_1802.field_8317, 16), 1, "семена")
            || var1.cachedFood() < Math.max(64, var1.population() * 8) && x33p9oz0tzh54f(var0, var1, var2, new class_1799(class_1802.field_8229, 8), 2, "еда");
         case LUMBERJACK -> var1.x2zm76h0pkiu6o() < 8 && x33p9oz0tzh54f(var0, var1, var2, new class_1799(class_1802.field_17535, 8), 1, "саженцы");
         case FISHERMAN -> x2egalzebb4k1a(var0, var1, var0x -> var0x.method_31574(class_1802.field_8276)) < 3
            && x33p9oz0tzh54f(var0, var1, var2, new class_1799(class_1802.field_8276, 3), 1, "леска");
         case RANCHER -> x2egalzebb4k1a(var0, var1, var0x -> var0x.method_31574(class_1802.field_8861)) < 16
            && x33p9oz0tzh54f(var0, var1, var2, new class_1799(class_1802.field_8861, 16), 2, "корм");
         case GUARD -> x2egalzebb4k1a(var0, var1, var0x -> var0x.method_31574(class_1802.field_8107)) < 32
               && x33p9oz0tzh54f(var0, var1, var2, new class_1799(class_1802.field_8107, 32), 2, "стрелы")
            || var1.x12nt3bv2et7b7() < 24 && x33p9oz0tzh54f(var0, var1, var2, new class_1799(class_1802.field_8620, 8), 3, "железо для снаряжения");
         default -> false;
      };
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private static boolean x3ce7hz5kx2dh(class_3218 var0, x15kgoaq6i3m6i var1, class_1646 var2, x1rxiy9mtu5b3h var3) {
      return switch (var3) {
         case BUILDER -> xyt7zxwpica1j(var0, var1, var2, var3, var0x -> var0x.method_31573(class_3489.field_15537), 32, 128, 1, "доски")
            || xyt7zxwpica1j(var0, var1, var2, var3, var0x -> var0x.method_31573(class_3489.field_25808), 32, 256, 1, "строительный камень");
         case MINER -> xyt7zxwpica1j(
               var0, var1, var2, var3, var0x -> var0x.method_31574(class_1802.field_20412) || var0x.method_31574(class_1802.field_29025), 32, 256, 1, "камень"
            )
            || xyt7zxwpica1j(var0, var1, var2, var3, var0x -> var0x.method_31574(class_1802.field_27022), 16, 32, 1, "медь")
            || xyt7zxwpica1j(var0, var1, var2, var3, var0x -> var0x.method_31574(class_1802.field_8620), 8, 48, 2, "железо")
            || xyt7zxwpica1j(var0, var1, var2, var3, var0x -> var0x.method_31574(class_1802.field_8477), 2, 8, 3, "алмазы");
         case FARMER -> xyt7zxwpica1j(var0, var1, var2, var3, var0x -> var0x.method_31574(class_1802.field_8861), 20, 64, 1, "пшеница")
            || xyt7zxwpica1j(var0, var1, var2, var3, var0x -> var0x.method_31574(class_1802.field_8229), 12, 32, 1, "хлеб")
            || xyt7zxwpica1j(
               var0,
               var1,
               var2,
               var3,
               var0x -> var0x.method_31574(class_1802.field_8179) || var0x.method_31574(class_1802.field_8567) || var0x.method_31574(class_1802.field_8186),
               24,
               48,
               1,
               "урожай"
            );
         case LUMBERJACK -> xyt7zxwpica1j(var0, var1, var2, var3, var0x -> var0x.method_31573(class_3489.field_15539), 16, 128, 1, "брёвна")
            || xyt7zxwpica1j(var0, var1, var2, var3, var0x -> var0x.method_31573(class_3489.field_15528), 16, 32, 1, "саженцы");
         case FISHERMAN -> xyt7zxwpica1j(var0, var1, var2, var3, var0x -> var0x.method_31574(class_1802.field_8429), 15, 32, 1, "треска")
            || xyt7zxwpica1j(var0, var1, var2, var3, var0x -> var0x.method_31574(class_1802.field_8209), 12, 24, 1, "лосось");
         case RANCHER -> xyt7zxwpica1j(var0, var1, var2, var3, var0x -> var0x.method_31574(class_1802.field_8803), 16, 16, 1, "яйца")
            || xyt7zxwpica1j(var0, var1, var2, var3, var0x -> var0x.method_31574(class_1802.field_8745), 8, 16, 1, "кожа")
            || xyt7zxwpica1j(var0, var1, var2, var3, var0x -> var0x.method_31574(class_1802.field_8153), 16, 24, 1, "перья")
            || xyt7zxwpica1j(
               var0, var1, var2, var3, var0x -> class_7923.field_41178.method_10221(var0x.method_7909()).method_12832().endsWith("_wool"), 12, 24, 1, "шерсть"
            );
         default -> false;
      };
   }

   private static boolean xyt7zxwpica1j(
      class_3218 var0, x15kgoaq6i3m6i var1, class_1646 var2, x1rxiy9mtu5b3h var3, Predicate<class_1799> var4, int var5, int var6, int var7, String var8
   ) {
      if (xbctmjmoh96up(var0, var1, var3, var4) < var6 + var5) {
         return false;
      } else if (!x2n55y2tom37h2.x38ipozok9mc0x(
         var0, var1, () -> x2vihal082zx8k(var0, var1, var3, var4, var5), List.of(new class_1799(class_1802.field_8687, var7))
      )) {
         return false;
      } else {
         var1.x3ktd3i0hq4h53();
         x2n55y2tom37h2.xchl1k1tnh3w8(var0).method_80();
         x2vaj7rsm57i4n.x2t98zlq4lu49t(var2, "продажа: " + var8, var5);
         return true;
      }
   }

   private static boolean x33p9oz0tzh54f(class_3218 var0, x15kgoaq6i3m6i var1, class_1646 var2, class_1799 var3, int var4, String var5) {
      if (!x2n55y2tom37h2.x38ipozok9mc0x(
         var0, var1, () -> x2n55y2tom37h2.x22hjebj4vmztt(var0, var1, var0xx -> var0xx.method_31574(class_1802.field_8687), var4), List.of(var3)
      )) {
         return false;
      } else {
         var1.xqcex85brzoq2();
         x2n55y2tom37h2.xchl1k1tnh3w8(var0).method_80();
         x2vaj7rsm57i4n.x2t98zlq4lu49t(var2, "покупка: " + var5, var3.method_7947());
         return true;
      }
   }

   private static int xbctmjmoh96up(class_3218 var0, x15kgoaq6i3m6i var1, x1rxiy9mtu5b3h var2, Predicate<class_1799> var3) {
      x2jj5edkiu5orf var4 = x3rd7rxzqtevtb(var2);
      int var5 = 0;

      for (class_2338 var7 : var1.x2ad3s8aentmo4()) {
         if ((var1.x2h8eqlzb5mm0w(var7) == var4 || var1.x2h8eqlzb5mm0w(var7) == x2jj5edkiu5orf.GENERAL)
            && var0.method_8321(var7) instanceof class_1263 var8) {
            for (int var11 = 0; var11 < var8.method_5439(); var11++) {
               class_1799 var10 = var8.method_5438(var11);
               if (!var10.method_7960() && var3.test(var10)) {
                  var5 += var10.method_7947();
               }
            }
         }
      }

      return var5;
   }

   private static int x2egalzebb4k1a(class_3218 var0, x15kgoaq6i3m6i var1, Predicate<class_1799> var2) {
      return x2n55y2tom37h2.x1mlne8tl8k5ag(var0, var1, var2);
   }

   private static boolean x2vihal082zx8k(class_3218 var0, x15kgoaq6i3m6i var1, x1rxiy9mtu5b3h var2, Predicate<class_1799> var3, int var4) {
      if (xbctmjmoh96up(var0, var1, var2, var3) < var4) {
         return false;
      } else {
         int var5 = var4;
         x2jj5edkiu5orf var6 = x3rd7rxzqtevtb(var2);

         for (class_2338 var8 : var1.x2ad3s8aentmo4()) {
            if ((var1.x2h8eqlzb5mm0w(var8) == var6 || var1.x2h8eqlzb5mm0w(var8) == x2jj5edkiu5orf.GENERAL)
               && var0.method_8321(var8) instanceof class_1263 var10) {
               for (int var11 = 0; var11 < var10.method_5439() && var5 > 0; var11++) {
                  class_1799 var12 = var10.method_5438(var11);
                  if (!var12.method_7960() && var3.test(var12)) {
                     int var13 = Math.min(var5, var12.method_7947());
                     class_1792 var14 = var12.method_7909();
                     var12.method_7934(var13);
                     var1.x2sh8yupnxmo71(var14, var13);
                     var5 -= var13;
                     var10.method_5431();
                  }
               }

               if (var5 == 0) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private static x2jj5edkiu5orf x3rd7rxzqtevtb(x1rxiy9mtu5b3h var0) {
      return switch (var0) {
         case BUILDER, LUMBERJACK -> x2jj5edkiu5orf.WOOD;
         case MINER -> x2jj5edkiu5orf.MINING;
         case FARMER, FISHERMAN -> x2jj5edkiu5orf.FOOD;
         case RANCHER -> x2jj5edkiu5orf.ANIMAL;
         default -> x2jj5edkiu5orf.GENERAL;
      };
   }

   private static boolean x2l8k383vfoz57(class_1799 var0) {
      return var0.method_31574(class_1802.field_17518) || var0.method_31574(class_1802.field_17519);
   }

   private static boolean x161x1w456knty(class_1799 var0) {
      return var0.method_31574(class_1802.field_8317)
         || var0.method_31574(class_1802.field_8309)
         || var0.method_31574(class_1802.field_46249)
         || var0.method_31574(class_1802.field_8179)
         || var0.method_31574(class_1802.field_8567);
   }
}
