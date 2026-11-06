package net.smartcolonies.r;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import net.minecraft.class_1263;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1646;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2281;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_2398;
import net.minecraft.class_2561;
import net.minecraft.class_2586;
import net.minecraft.class_2595;
import net.minecraft.class_2680;
import net.minecraft.class_2745;
import net.minecraft.class_2818;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3341;
import net.minecraft.class_3481;
import net.minecraft.class_3489;
import net.minecraft.class_3499;
import net.minecraft.class_3719;
import net.minecraft.class_3730;
import net.minecraft.class_3852;
import net.minecraft.class_3854;
import net.minecraft.class_4174;
import net.minecraft.class_5321;
import net.minecraft.class_7477;
import net.minecraft.class_7893;
import net.minecraft.class_7923;
import net.minecraft.class_9334;
import net.minecraft.class_2338.class_2339;
import net.minecraft.class_2350.class_2353;
import net.minecraft.class_2902.class_2903;
import net.minecraft.class_4153.class_4155;
import net.smartcolonies.SmartColoniesMod;

public final class x2n55y2tom37h2 {
   private static final int x2dii1ed72e0qa = 16;
   private static final int x3hsc98x8oxfm9 = 100;
   private static final int x3iy3x09j2n16t = 768;
   private static final int x3jhact2bob0zj = 33;
   private static final int x2qbzy5v090jxv = 35937;
   private static final int x39ilanc4hhepr = 100;
   private static final int x1zv0o4iephg77 = 10;
   private static final int x20glesds2tqss = 8;
   private static final int xjngvrp8ssq6v = 24;
   private static final String x1ao7ast073lam = "smart_colonies_role_name";
   private static final Map<class_3218, x2df6ogxvxe0yk> x3vsxsdrywaymi = new IdentityHashMap<>();

   private x2n55y2tom37h2() {
   }

   public static void x35h05qb6ebno5() {
      x3vsxsdrywaymi.clear();
      x8veg662b2f5p.x1161stz4yb35x();
      x37idyr79ed75m.x3didvqu3hbx58();
      xzszqoikocxnx.x30zm3b0hljw9q();
      x1intnp6ax9q2k.x2rwu2l29t4lpj();
      x3m66wv1c6161h.x2ik4hs9xwj2v7();
      x36u1fhs2z6uoi.clearRuntimeCaches();
   }

   public static x3d5vk1zj3fvkj xchl1k1tnh3w8(class_3218 var0) {
      return x3d5vk1zj3fvkj.get(var0);
   }

   public static void xhvlm4bwxgmtf(class_3218 var0) {
      x3d5vk1zj3fvkj var1 = xchl1k1tnh3w8(var0);
      if (var0.method_75260() % 20L == 0L) {
         x2uh56vnzc4u51.x34rm129tews28(var0);
      }

      x6amcj7vm0jm5.x1w4hwyk1suofz(var0);
      long var2 = var0.method_75260();
      int var4 = 768;
      int var5 = var0.method_18456().isEmpty()
         ? x3sqkg3n6v972r.x2by5f9ehxvwk9().village.offline_blocks_per_tick
         : x3sqkg3n6v972r.x2by5f9ehxvwk9().village.offline_arrival_blocks_per_tick;
      var var6 = new ArrayList<>(var1.colonies());
      if (!var6.isEmpty()) {
         Collections.rotate(var6, -Math.floorMod(var2, var6.size()));
      }

      for (x15kgoaq6i3m6i var8 : var6) {
         if (x22gnz9u3m8aag(var0, var8.core())) {
            class_2818 var9 = var0.method_14178().method_21730(var8.core().method_10263() >> 4, var8.core().method_10260() >> 4);
            if (var9 != null && var9.method_8320(var8.core()).method_27852(class_2246.field_16332)) {
               boolean var10 = var0.method_37118(var8.core()) || var8.x3tnjjviw7kr77() >= var2 - 1L;
               boolean var11 = var0.method_18456()
                  .stream()
                  .anyMatch(
                     var2x -> x2od8cp87hfx5s(var8.core(), var2x.method_24515()) <= Math.pow((var0.method_8503().method_3760().method_14568() + 1) * 16.0, 2.0)
                  );
               if (var10 || var11) {
                  if (var8.population() == 0 && var10) {
                     x2o9v4z8h5h1n1(var0, var8, var2);
                  }

                  x1intnp6ax9q2k.x3ij59k18pnfmd(var0, var8);
                  if (var8.x3p06s7riestqm() <= 0L || !x3sqkg3n6v972r.x2by5f9ehxvwk9().village.offline_development || !x1dxdlnevtfbhm.x35ev8l9vc9uk7(var0)) {
                     var5 -= x1intnp6ax9q2k.x12vivbc3rlygx(var0, var8, var5);
                     if (var10) {
                        if (!var8.x28w4myn4c9xwu() && (var8.x3k3m01633dfw1() == Long.MIN_VALUE || var2 - var8.x3k3m01633dfw1() >= 100L)) {
                           var8.x3db8zzffozhu6();
                        }

                        if (var8.x28w4myn4c9xwu() && var4 > 0) {
                           var4 -= xllp0ajwexj5r(var0, var8, Math.min(256, var4));
                        }

                        if (Math.floorMod(var2 + var8.core().method_10063(), 10) == 0) {
                           x1t38fv75txqm7(var0, var8, var2);
                        }

                        if (var8.x202n2ys5dq0ho() == Long.MIN_VALUE || var2 - var8.x202n2ys5dq0ho() >= 100L) {
                           x2o9v4z8h5h1n1(var0, var8, var2);
                           x1asz375yez9sw(var0, var8, var0x -> var0x.method_31574(class_1802.field_8803), 32);
                           xw0xosmde9jmp.x2m8lyhoez9v7c(var0, var8);
                           x16cwhuov65ela(var0, var8);
                           x3cjs4pzi3umdr(var0, var8);
                        }

                        if (var2 % 200L == 0L) {
                           var1.method_80();
                        }

                        if (Math.floorMod(var2 + var8.core().method_10063(), 4) == 0) {
                           x39ey6ni61f3zn(var0, var8);
                        }

                        x27aao31y6r2ta.xyvb622rqbii1(var0, var8, var2);
                        if (x3sqkg3n6v972r.x2by5f9ehxvwk9().debug.render_build_boxes && var2 % 20L == 0L) {
                           x3tgmhaj405foe(var0, var8);
                        }
                     }
                  }
               }
            }
         }
      }

      x313o9woxa22fw.x28wuwjwutxh4c(var0, var1, var2);
      x8veg662b2f5p.x2twncqk5nkey3(var0);
   }

   public static Optional<x15kgoaq6i3m6i> x3psqezd5jem54(class_3218 var0, class_2338 var1) {
      return xchl1k1tnh3w8(var0)
         .colonies()
         .stream()
         .filter(var1x -> x2od8cp87hfx5s(var1x.core(), var1) <= (double)var1x.radius() * var1x.radius())
         .min(Comparator.comparingDouble(var1x -> x2od8cp87hfx5s(var1x.core(), var1)));
   }

   public static Optional<x15kgoaq6i3m6i> x2yj2v4iudqlvb(class_3218 var0, UUID var1) {
      return xchl1k1tnh3w8(var0).colonies().stream().filter(var1x -> var1x.x3hurh64xwtw51(var1)).findFirst();
   }

   private static double x2od8cp87hfx5s(class_2338 var0, class_2338 var1) {
      double var2 = (double)var0.method_10263() - var1.method_10263();
      double var4 = (double)var0.method_10260() - var1.method_10260();
      return var2 * var2 + var4 * var4;
   }

   public static class_238 x150ccxomrlozr(class_3218 var0, x15kgoaq6i3m6i var1) {
      int var2 = var1.radius();
      return new class_238(
         var1.core().method_10263() - var2,
         var0.method_31607(),
         var1.core().method_10260() - var2,
         var1.core().method_10263() + var2 + 1,
         var0.method_31600() + 1,
         var1.core().method_10260() + var2 + 1
      );
   }

   public static boolean x5ll9elqpk1a4(class_3218 var0) {
      x2df6ogxvxe0yk var1 = x3vsxsdrywaymi.computeIfAbsent(var0, var0x -> new x2df6ogxvxe0yk());
      if (var1.x15wujcws0eexn != var0.method_75260()) {
         var1.x15wujcws0eexn = var0.method_75260();
         var1.x1kbn5io9xhyfp = 0;
      }

      if (var1.x1kbn5io9xhyfp >= 6) {
         return false;
      } else {
         var1.x1kbn5io9xhyfp++;
         return true;
      }
   }

   public static void x30py6w81fega5(class_3218 var0, class_2338 var1) {
      x3psqezd5jem54(var0, var1).filter(var1x -> var1x.core().method_10262(var1) <= 1024.0).ifPresent(var1x -> {
         var1x.xz6ij33c0780z(var0.method_75260());
         xchl1k1tnh3w8(var0).method_80();
      });
   }

   static x15kgoaq6i3m6i x3ermfoq0kwcv0(class_3218 var0, class_2338 var1, class_3341 var2) {
      x3d5vk1zj3fvkj var3 = xchl1k1tnh3w8(var0);
      var var4 = var3.colonies().stream().filter(var1x -> var1x.core().equals(var1)).findFirst();
      if (var4.isPresent()) {
         x1l3hk8rr8x5jb((x15kgoaq6i3m6i)var4.get(), var2);
         var3.method_80();
         return (x15kgoaq6i3m6i)var4.get();
      } else {
         var var5 = var3.colonies().stream().filter(var2x -> var2.method_14662(var2x.core()) || var2x.core().method_10262(var1) < 16384.0).findFirst();
         if (var5.isPresent()) {
            x1l3hk8rr8x5jb((x15kgoaq6i3m6i)var5.get(), var2);
            var3.method_80();
            return (x15kgoaq6i3m6i)var5.get();
         } else {
            x15kgoaq6i3m6i var6 = new x15kgoaq6i3m6i(UUID.randomUUID(), var1, 96, Long.MIN_VALUE);
            var6.x14t0iw6xpk0xx(0L);
            var6.xqdbjfmq8abgh(true);
            x1l3hk8rr8x5jb(var6, var2);
            var3.add(var6);
            SmartColoniesMod.LOGGER.info("Registered generated vanilla village at {}", var1);
            return var6;
         }
      }
   }

   static void x1l3hk8rr8x5jb(x15kgoaq6i3m6i var0, class_3341 var1) {
      int var2 = Math.max(Math.abs(var1.method_35415() - var0.core().method_10263()), Math.abs(var1.method_35418() - var0.core().method_10263()));
      int var3 = Math.max(Math.abs(var1.method_35417() - var0.core().method_10260()), Math.abs(var1.method_35420() - var0.core().method_10260()));
      int var4 = Math.max(96, (int)Math.ceil(Math.hypot(var2, var3)) + 16);
      var0.x1ne2dy4ss3d7w(Math.max(var0.radius(), Math.min(512, var4)));
   }

   public static void x3drrj6t9qvanr(class_3218 var0, class_1646 var1, x1rxiy9mtu5b3h var2) {
      x3psqezd5jem54(var0, var1.method_24515()).ifPresent(var3 -> {
         var3.x36xpmo21hmp9h(var1.method_5667(), var2);
         xf6ny39u2klct.x3cumye59iar40(var0, var3, var1);
         var1.method_18868().method_18878(xxao083dbo7ag.x3ecoc0soedv0m, var2);
         xchl1k1tnh3w8(var0).method_80();
      });
   }

   public static Optional<x2arpn38dclo1g> x3ajt835fnqglk(class_3218 var0, UUID var1, class_2960 var2, class_2338 var3) {
      return xchl1k1tnh3w8(var0).byId(var1).map(var3x -> {
         x2arpn38dclo1g var4 = new x2arpn38dclo1g(UUID.randomUUID(), var2, var3);
         var3x.x31ve0dsfeaxo4(var4);
         xchl1k1tnh3w8(var0).method_80();
         return var4;
      });
   }

   public static boolean x3dsqtct85933r(class_3218 var0, x15kgoaq6i3m6i var1, class_1792 var2) {
      return x3lxwdirnn77qc(var0, var1, var1x -> var1x.method_31574(var2)).isPresent();
   }

   public static Optional<class_1799> x3lxwdirnn77qc(class_3218 var0, x15kgoaq6i3m6i var1, Predicate<class_1799> var2) {
      for (class_2338 var4 : var1.x2ad3s8aentmo4()) {
         if (x22gnz9u3m8aag(var0, var4) && var0.method_8321(var4) instanceof class_1263 var6) {
            for (int var7 = 0; var7 < var6.method_5439(); var7++) {
               class_1799 var8 = var6.method_5438(var7);
               if (!var8.method_7960() && var2.test(var8)) {
                  class_1799 var9 = var8.method_46651(1);
                  var8.method_7934(1);
                  var6.method_5431();
                  var1.x2sh8yupnxmo71(var9.method_7909(), 1);
                  return Optional.of(var9);
               }
            }
         }
      }

      return Optional.empty();
   }

   public static Optional<class_1799> x5jd7014csotp(class_3218 var0, class_2338 var1, Predicate<class_1799> var2, int var3) {
      if (var3 > 0 && x22gnz9u3m8aag(var0, var1)) {
         if (var0.method_8321(var1) instanceof class_1263 var5) {
            for (int var6 = 0; var6 < var5.method_5439(); var6++) {
               class_1799 var7 = var5.method_5438(var6);
               if (!var7.method_7960() && var2.test(var7)) {
                  int var8 = Math.min(var3, var7.method_7947());
                  class_1799 var9 = var7.method_46651(var8);
                  var7.method_7934(var8);
                  var5.method_5431();
                  x3psqezd5jem54(var0, var1).ifPresent(var2x -> var2x.x2sh8yupnxmo71(var9.method_7909(), var8));
                  return Optional.of(var9);
               }
            }

            return Optional.empty();
         } else {
            return Optional.empty();
         }
      } else {
         return Optional.empty();
      }
   }

   public static Optional<class_2338> x1falh21nhy4yk(class_3218 var0, x15kgoaq6i3m6i var1, class_2338 var2) {
      return var1.x2ad3s8aentmo4()
         .stream()
         .filter(var1x -> x22gnz9u3m8aag(var0, var1x))
         .filter(var1x -> var0.method_8321(var1x) instanceof class_1263)
         .min(Comparator.comparingDouble(var1x -> var1x.method_10262(var2)));
   }

   public static Optional<class_2338> xjas28crgjspn(class_3218 var0, x15kgoaq6i3m6i var1, class_2338 var2, x2jj5edkiu5orf var3) {
      return var1.x2ad3s8aentmo4()
         .stream()
         .filter(var1x -> x22gnz9u3m8aag(var0, var1x))
         .filter(var1x -> var0.method_8321(var1x) instanceof class_1263)
         .min(
            Comparator.<class_2338>comparingInt(var2x -> x3nsk4svfallqp(var1.x2h8eqlzb5mm0w(var2x), var3))
               .thenComparingDouble(var1x -> var1x.method_10262(var2))
         );
   }

   public static Optional<class_2338> x3jrfw2d3yykeu(class_3218 var0, x15kgoaq6i3m6i var1, class_1646 var2, x2jj5edkiu5orf var3) {
      return var1.x2ad3s8aentmo4()
         .stream()
         .filter(var1x -> x22gnz9u3m8aag(var0, var1x))
         .filter(var1x -> var0.method_8321(var1x) instanceof class_1263)
         .filter(var2x -> !x1c1r5c6a13gtl.xps35ehel6nkk(var2, var2x, var0.method_75260()))
         .min(
            Comparator.<class_2338>comparingInt(var2x -> x3nsk4svfallqp(var1.x2h8eqlzb5mm0w(var2x), var3))
               .thenComparingDouble(var1x -> var1x.method_10262(var2.method_24515()))
         );
   }

   public static Optional<class_2338> x1dhbpietunk9i(class_3218 var0, x15kgoaq6i3m6i var1, class_1646 var2, Predicate<class_1799> var3) {
      return var1.x2ad3s8aentmo4()
         .stream()
         .filter(var1x -> x22gnz9u3m8aag(var0, var1x))
         .filter(var2x -> var0.method_8321(var2x) instanceof class_1263 var3x && x3edwxvtjh8348(var3x, var3))
         .filter(var2x -> !x1c1r5c6a13gtl.xps35ehel6nkk(var2, var2x, var0.method_75260()))
         .min(Comparator.comparingDouble(var1x -> var1x.method_10262(var2.method_24515())));
   }

   public static Optional<class_2338> x2ytjyhq411pf7(class_3218 var0, x15kgoaq6i3m6i var1, class_2338 var2, Predicate<class_1799> var3) {
      return var1.x2ad3s8aentmo4()
         .stream()
         .filter(var1x -> x22gnz9u3m8aag(var0, var1x))
         .filter(var2x -> var0.method_8321(var2x) instanceof class_1263 var3x && x3edwxvtjh8348(var3x, var3))
         .min(Comparator.comparingDouble(var1x -> var1x.method_10262(var2)));
   }

   private static boolean x3edwxvtjh8348(class_1263 var0, Predicate<class_1799> var1) {
      for (int var2 = 0; var2 < var0.method_5439(); var2++) {
         if (!var0.method_5438(var2).method_7960() && var1.test(var0.method_5438(var2))) {
            return true;
         }
      }

      return false;
   }

   public static boolean x10q255l9ii1rw(class_3218 var0, x15kgoaq6i3m6i var1, class_1792 var2) {
      for (class_2338 var4 : var1.x2ad3s8aentmo4()) {
         if (x22gnz9u3m8aag(var0, var4) && var0.method_8321(var4) instanceof class_1263 var6) {
            for (int var7 = 0; var7 < var6.method_5439(); var7++) {
               if (var6.method_5438(var7).method_31574(var2) && !var6.method_5438(var7).method_7960()) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public static boolean x1tl8iu49onump(class_3218 var0, x15kgoaq6i3m6i var1, Predicate<class_1799> var2) {
      for (class_2338 var4 : var1.x2ad3s8aentmo4()) {
         if (x22gnz9u3m8aag(var0, var4) && var0.method_8321(var4) instanceof class_1263 var6) {
            for (int var7 = 0; var7 < var6.method_5439(); var7++) {
               class_1799 var8 = var6.method_5438(var7);
               if (!var8.method_7960() && var2.test(var8)) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public static int x1mlne8tl8k5ag(class_3218 var0, x15kgoaq6i3m6i var1, Predicate<class_1799> var2) {
      int var3 = 0;

      for (class_2338 var5 : var1.x2ad3s8aentmo4()) {
         if (x22gnz9u3m8aag(var0, var5) && var0.method_8321(var5) instanceof class_1263 var7) {
            for (int var8 = 0; var8 < var7.method_5439(); var8++) {
               class_1799 var9 = var7.method_5438(var8);
               if (!var9.method_7960() && var2.test(var9)) {
                  var3 += var9.method_7947();
               }
            }
         }
      }

      return var3;
   }

   public static boolean x22hjebj4vmztt(class_3218 var0, x15kgoaq6i3m6i var1, Predicate<class_1799> var2, int var3) {
      if (var3 <= 0) {
         return true;
      } else if (x1mlne8tl8k5ag(var0, var1, var2) < var3) {
         return false;
      } else {
         int var4 = var3;

         for (class_2338 var6 : var1.x2ad3s8aentmo4()) {
            if (x22gnz9u3m8aag(var0, var6) && var0.method_8321(var6) instanceof class_1263 var8) {
               for (int var9 = 0; var9 < var8.method_5439() && var4 > 0; var9++) {
                  class_1799 var10 = var8.method_5438(var9);
                  if (!var10.method_7960() && var2.test(var10)) {
                     int var11 = Math.min(var4, var10.method_7947());
                     class_1792 var12 = var10.method_7909();
                     var10.method_7934(var11);
                     var1.x2sh8yupnxmo71(var12, var11);
                     var4 -= var11;
                     var8.method_5431();
                  }
               }

               if (var4 == 0) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public static int x1asz375yez9sw(class_3218 var0, x15kgoaq6i3m6i var1, Predicate<class_1799> var2, int var3) {
      int var4 = Math.max(0, var3);
      int var5 = 0;

      for (class_2338 var7 : var1.x2ad3s8aentmo4()) {
         if (x22gnz9u3m8aag(var0, var7) && var0.method_8321(var7) instanceof class_1263 var8) {
            for (int var13 = 0; var13 < var8.method_5439(); var13++) {
               class_1799 var10 = var8.method_5438(var13);
               if (!var10.method_7960() && var2.test(var10)) {
                  int var11 = Math.min(var4, var10.method_7947());
                  int var12 = var10.method_7947() - var11;
                  var4 -= var11;
                  if (var12 > 0) {
                     var10.method_7934(var12);
                     var5 += var12;
                     var8.method_5431();
                  }
               }
            }
         }
      }

      return var5;
   }

   public static boolean x2cl1crde2rnxy(class_3218 var0, x15kgoaq6i3m6i var1, class_1799 var2) {
      if (var2.method_7960()) {
         return true;
      } else {
         x2jj5edkiu5orf var3 = x2ic8ypdr45hiz(var2);
         var var4 = new ArrayList<>(var1.x2ad3s8aentmo4());
         var4.sort(Comparator.comparingInt(var2x -> x3nsk4svfallqp(var1.x2h8eqlzb5mm0w(var2x), var3)));

         for (class_2338 var6 : var4) {
            if (x22gnz9u3m8aag(var0, var6) && var0.method_8321(var6) instanceof class_1263 var8) {
               for (int var9 = 0; var9 < var8.method_5439(); var9++) {
                  class_1799 var10 = var8.method_5438(var9);
                  int var11 = var8.method_58350(var2);
                  if (var10.method_7960()) {
                     int var12 = Math.min(var2.method_7947(), var11);
                     var8.method_5447(var9, var2.method_46651(var12));
                     var2.method_7934(var12);
                     var8.method_5431();
                     if (var2.method_7960()) {
                        return true;
                     }
                  } else if (class_1799.method_31577(var10, var2) && var10.method_7947() < var11) {
                     int var13 = Math.min(var2.method_7947(), var11 - var10.method_7947());
                     var10.method_7933(var13);
                     var2.method_7934(var13);
                     var8.method_5431();
                     if (var2.method_7960()) {
                        return true;
                     }
                  }
               }
            }
         }

         return var2.method_7960();
      }
   }

   public static int x1amge7aoh04ca(class_3218 var0, x15kgoaq6i3m6i var1, class_1799 var2) {
      if (var2.method_7960()) {
         return 0;
      } else {
         long var3 = 0L;

         for (class_2338 var6 : var1.x2ad3s8aentmo4()) {
            if (x22gnz9u3m8aag(var0, var6) && var0.method_8321(var6) instanceof class_1263 var7) {
               for (int var10 = 0; var10 < var7.method_5439(); var10++) {
                  class_1799 var9 = var7.method_5438(var10);
                  if (var9.method_7960()) {
                     var3 += var7.method_58350(var2);
                  } else if (class_1799.method_31577(var9, var2)) {
                     var3 += Math.max(0, var7.method_58350(var2) - var9.method_7947());
                  }
               }
            }
         }

         return (int)Math.min(2147483647L, var3);
      }
   }

   public static boolean x38ipozok9mc0x(class_3218 var0, x15kgoaq6i3m6i var1, BooleanSupplier var2, List<class_1799> var3) {
      var var4 = new IdentityHashMap<class_1263, List<class_1799>>();

      for (class_2338 var6 : var1.x2ad3s8aentmo4()) {
         if (x22gnz9u3m8aag(var0, var6) && var0.method_8321(var6) instanceof class_1263 var7) {
            var4.computeIfAbsent(var7, var0x -> IntStream.range(0, var0x.method_5439()).mapToObj(var1x -> var0x.method_5438(var1x).method_7972()).toList());
         }
      }

      boolean var12 = false;

      boolean var17;
      try {
         if (!var2.getAsBoolean()) {
            return false;
         }

         var var13 = var3.iterator();

         class_1799 var16;
         do {
            if (!var13.hasNext()) {
               var12 = true;
               return true;
            }

            var16 = (class_1799)var13.next();
         } while (x2cl1crde2rnxy(var0, var1, var16.method_7972()));

         var17 = false;
      } finally {
         if (!var12) {
            var4.forEach((var0x, var1x) -> {
               for (int var2x = 0; var2x < var1x.size(); var2x++) {
                  var0x.method_5447(var2x, (class_1799)var1x.get(var2x));
               }

               var0x.method_5431();
            });
         }

         x2u0vsam1tcmhq(var0, var1);
      }

      return var17;
   }

   private static int x3nsk4svfallqp(x2jj5edkiu5orf var0, x2jj5edkiu5orf var1) {
      if (var0 == var1) {
         return 0;
      } else {
         return var0 == x2jj5edkiu5orf.GENERAL ? 1 : 2;
      }
   }

   public static x2jj5edkiu5orf x2ic8ypdr45hiz(class_1799 var0) {
      if (var0.method_7960()) {
         return x2jj5edkiu5orf.GENERAL;
      } else {
         String var1 = class_7923.field_41178.method_10221(var0.method_7909()).method_12832();
         if (var0.method_31574(class_1802.field_8803)
            || var0.method_31574(class_1802.field_8103)
            || var0.method_31574(class_1802.field_8745)
            || var0.method_31574(class_1802.field_8153)
            || var0.method_31574(class_1802.field_8245)
            || var1.endsWith("_wool")) {
            return x2jj5edkiu5orf.ANIMAL;
         } else if (xhahxgpa08yc9(var0) > 0 || var0.method_31574(class_1802.field_8861) || var0.method_31574(class_1802.field_17528) || var1.endsWith("_seeds")
            )
          {
            return x2jj5edkiu5orf.FOOD;
         } else if (var0.method_31573(class_3489.field_15539)
            || var0.method_31573(class_3489.field_15537)
            || var0.method_31573(class_3489.field_15528)
            || var0.method_31574(class_1802.field_8600)
            || var0.method_31574(class_1802.field_8665)) {
            return x2jj5edkiu5orf.WOOD;
         } else {
            return !var0.method_31573(class_3489.field_25808)
                  && !var1.contains("_ore")
                  && !var1.startsWith("raw_")
                  && !var1.endsWith("_ingot")
                  && !var1.endsWith("_nugget")
                  && !var0.method_31574(class_1802.field_8713)
                  && !var0.method_31574(class_1802.field_8477)
                  && !var0.method_31574(class_1802.field_8687)
                  && !var0.method_31574(class_1802.field_8725)
                  && !var0.method_31574(class_1802.field_8759)
                  && !var0.method_31574(class_1802.field_8145)
               ? x2jj5edkiu5orf.GENERAL
               : x2jj5edkiu5orf.MINING;
         }
      }
   }

   private static void x3cjs4pzi3umdr(class_3218 var0, x15kgoaq6i3m6i var1) {
      if (x1dxdlnevtfbhm.x35ev8l9vc9uk7(var0)) {
         var var2 = var1.x1ja61emeln2hc()
            .stream()
            .filter(var0x -> var0x.x32oz43hmeynhc() == x20r5v7z43p3d5.COMPLETE && var0x.x2nfrt8qxdscso().isEmpty())
            .toList();

         for (int var3 = 0; var3 < var2.size(); var3++) {
            x2arpn38dclo1g var4 = (x2arpn38dclo1g)var2.get(var3);
            if (!x2fs1y9t8j4hqu(var1, var4)) {
               var var5 = x6a0sltqj8ii7(var0, var4);
               if (!var5.isEmpty()) {
                  if (!x22hjebj4vmztt(var0, var1, var0x -> var0x.method_31573(class_3489.field_15539), 4)) {
                     return;
                  }

                  x2jj5edkiu5orf var6 = x2jj5edkiu5orf.forHouse(var3);
                  xt55z8grtuvpq(var0, var1, (class_2338)var5.get(), var6);
                  xchl1k1tnh3w8(var0).method_80();
                  return;
               }
            }
         }
      }
   }

   private static boolean x2fs1y9t8j4hqu(x15kgoaq6i3m6i var0, x2arpn38dclo1g var1) {
      class_3341 var2 = var1.x24kbqv6g4kkrg();
      return var0.x29j0o9zdxri2()
         .keySet()
         .stream()
         .anyMatch(
            var1x -> var1x.method_10263() >= var2.method_35415() - 2
               && var1x.method_10263() <= var2.method_35418() + 2
               && var1x.method_10264() >= var2.method_35416() - 1
               && var1x.method_10264() <= var2.method_35419() + 2
               && var1x.method_10260() >= var2.method_35417() - 2
               && var1x.method_10260() <= var2.method_35420() + 2
         );
   }

   private static Optional<class_2338> x6a0sltqj8ii7(class_3218 var0, x2arpn38dclo1g var1) {
      class_3341 var2 = var1.x24kbqv6g4kkrg();

      for (int var3 = var2.method_35416() + 1; var3 <= var2.method_35419() - 1; var3++) {
         for (int var4 = var2.method_35415(); var4 < var2.method_35418(); var4++) {
            for (int var5 = var2.method_35417(); var5 <= var2.method_35420(); var5++) {
               class_2338 var6 = new class_2338(var4, var3, var5);
               class_2338 var7 = var6.method_10078();
               if (x1svuyoyvp8ad5(var0, var6, var7) && (x1rn82cvt1s306(var0, var6) || x1rn82cvt1s306(var0, var7))) {
                  return Optional.of(var6);
               }
            }
         }
      }

      for (int var11 = 1; var11 <= 2; var11++) {
         for (int var12 = var2.method_35415() - var11; var12 < var2.method_35418() + var11; var12++) {
            for (int var8 : new int[]{var2.method_35417() - var11, var2.method_35420() + var11}) {
               int var9 = var0.method_8624(class_2903.field_13203, var12, var8);
               class_2338 var10 = new class_2338(var12, var9, var8);
               if (x1svuyoyvp8ad5(var0, var10, var10.method_10078())) {
                  return Optional.of(var10);
               }
            }
         }
      }

      return Optional.empty();
   }

   public static Optional<class_2338> x2l4eb2ibsf118(class_3218 var0, x15kgoaq6i3m6i var1) {
      for (int var2 = 3; var2 <= 20; var2++) {
         for (int var3 = -var2; var3 <= var2; var3++) {
            for (int var4 = -var2; var4 <= var2; var4++) {
               if (Math.abs(var3) == var2 || Math.abs(var4) == var2) {
                  int var5 = var1.core().method_10263() + var3;
                  int var6 = var1.core().method_10260() + var4;
                  if (var0.method_14178().method_12123(var5 >> 4, var6 >> 4) && var0.method_14178().method_12123(var5 + 1 >> 4, var6 >> 4)) {
                     int var7 = var0.method_8624(class_2903.field_13203, var5, var6);
                     if (Math.abs(var7 - var1.core().method_10264()) <= 3) {
                        class_2338 var8 = new class_2338(var5, var7, var6);
                        class_2338 var9 = var8.method_10078();
                        if (x1svuyoyvp8ad5(var0, var8, var9)
                           && !var1.x1ja61emeln2hc()
                              .stream()
                              .anyMatch(var2x -> var2x.x24kbqv6g4kkrg().method_14662(var8) || var2x.x24kbqv6g4kkrg().method_14662(var9))
                           && !var0.method_8320(var8.method_10074()).method_27852(class_2246.field_10034)
                           && !var0.method_8320(var9.method_10074()).method_27852(class_2246.field_10034)) {
                           return Optional.of(var8);
                        }
                     }
                  }
               }
            }
         }
      }

      return Optional.empty();
   }

   private static boolean x1svuyoyvp8ad5(class_3218 var0, class_2338 var1, class_2338 var2) {
      if (x1t48c42x6zfb3(var0, var1) || x1t48c42x6zfb3(var0, var2) || x1t48c42x6zfb3(var0, var1.method_10084()) || x1t48c42x6zfb3(var0, var2.method_10084())) {
         return false;
      } else if (x2uh56vnzc4u51.x3gqyq7yie9jxr(var0, var1) || x2uh56vnzc4u51.x3gqyq7yie9jxr(var0, var2)) {
         return false;
      } else if (!x1dxdlnevtfbhm.x13f7be5vlapn7(var0, var1) || !x1dxdlnevtfbhm.x13f7be5vlapn7(var0, var2)) {
         return false;
      } else if (var0.method_8320(var1).method_26215()
         && var0.method_8320(var2).method_26215()
         && var0.method_8320(var1.method_10084()).method_26215()
         && var0.method_8320(var2.method_10084()).method_26215()) {
         if (!var0.method_8320(var1.method_10074()).method_26206(var0, var1.method_10074(), class_2350.field_11036)
            || !var0.method_8320(var2.method_10074()).method_26206(var0, var2.method_10074(), class_2350.field_11036)) {
            return false;
         } else if (var0.method_8320(var1.method_10074()).method_27852(class_2246.field_10194)
            || var0.method_8320(var2.method_10074()).method_27852(class_2246.field_10194)
            || var0.method_8320(var1.method_10074()).method_27852(class_2246.field_10362)
            || var0.method_8320(var2.method_10074()).method_27852(class_2246.field_10362)) {
            return false;
         } else if (!var0.method_8333(null, new class_238(var1).method_991(new class_238(var2)).method_1014(0.02), var0x -> var0x.method_5805()).isEmpty()) {
            return false;
         } else {
            for (class_2338 var4 : class_2338.method_10097(var1.method_10069(-2, -1, -2), var2.method_10069(2, 2, 2))) {
               class_2680 var5 = var0.method_8320(var4);
               if (var5.method_26164(class_3481.field_15495) || var5.method_26164(class_3481.field_16443) || var5.method_27852(class_2246.field_16332)) {
                  return false;
               }
            }

            return x3dmceb8h6ov6d(var0, var1, var2, class_2350.field_11043) || x3dmceb8h6ov6d(var0, var1, var2, class_2350.field_11035);
         }
      } else {
         return false;
      }
   }

   private static boolean x1rn82cvt1s306(class_3218 var0, class_2338 var1) {
      for (class_2350 var3 : class_2353.field_11062) {
         class_2338 var4 = var1.method_10093(var3);
         if (!var0.method_8320(var4).method_26220(var0, var4).method_1110()) {
            return true;
         }
      }

      return false;
   }

   public static boolean xt55z8grtuvpq(class_3218 var0, x15kgoaq6i3m6i var1, class_2338 var2, x2jj5edkiu5orf var3) {
      class_2338 var4 = var2.method_10078();
      if (x1t48c42x6zfb3(var0, var2) || x1t48c42x6zfb3(var0, var4) || x1t48c42x6zfb3(var0, var2.method_10084()) || x1t48c42x6zfb3(var0, var4.method_10084())) {
         return false;
      } else if (x2uh56vnzc4u51.x3gqyq7yie9jxr(var0, var2) || x2uh56vnzc4u51.x3gqyq7yie9jxr(var0, var4)) {
         return false;
      } else if (!x1dxdlnevtfbhm.x13f7be5vlapn7(var0, var2) || !x1dxdlnevtfbhm.x13f7be5vlapn7(var0, var4)) {
         return false;
      } else if (!var0.method_8320(var2).method_26215() || !var0.method_8320(var4).method_26215()) {
         return false;
      } else if (!x3dmceb8h6ov6d(var0, var2, var4, class_2350.field_11043) && !x3dmceb8h6ov6d(var0, var2, var4, class_2350.field_11035)) {
         return false;
      } else {
         xg5u0itczfrpg(var0, var2, var4, xdzsxthi9esqy(var0, var1, var2, var4));
         var1.x1v7s85y1rhqmd(var2, var3);
         var1.x1v7s85y1rhqmd(var4, var3);
         var1.x35zk9b4abyi6a(var2, var4);
         return true;
      }
   }

   private static boolean x1t48c42x6zfb3(class_3218 var0, class_2338 var1) {
      return xchl1k1tnh3w8(var0).colonies().stream().anyMatch(var1x -> var1x.x2xqc3q79vfkw3().containsKey(var1));
   }

   public static void x16cwhuov65ela(class_3218 var0, x15kgoaq6i3m6i var1) {
      if (x1dxdlnevtfbhm.x35ev8l9vc9uk7(var0)) {
         for (Entry var3 : var1.x2ivzhhwd37mdn().entrySet()) {
            class_2338 var4 = (class_2338)var3.getKey();
            class_2338 var5 = (class_2338)var3.getValue();
            if (x22gnz9u3m8aag(var0, var4)
               && x22gnz9u3m8aag(var0, var5)
               && !x3d5vk1zj3fvkj.get(var0).isPlayerBlock(var4)
               && !x3d5vk1zj3fvkj.get(var0).isPlayerBlock(var5)) {
               class_2680 var6 = var0.method_8320(var4);
               class_2680 var7 = var0.method_8320(var5);
               if (var6.method_27852(class_2246.field_10034) && var7.method_27852(class_2246.field_10034)) {
                  class_2350 var8 = (class_2350)var6.method_11654(class_2281.field_10768);
                  if (var8 != class_2350.field_11043 && var8 != class_2350.field_11035 || !x3dmceb8h6ov6d(var0, var4, var5, var8)) {
                     var8 = xdzsxthi9esqy(var0, var1, var4, var5);
                  }

                  class_2745 var9 = var8 == class_2350.field_11043 ? class_2745.field_12574 : class_2745.field_12571;
                  class_2745 var10 = var8 == class_2350.field_11043 ? class_2745.field_12571 : class_2745.field_12574;
                  if (var6.method_11654(class_2281.field_10768) != var8
                     || var6.method_11654(class_2281.field_10770) != var9
                     || var7.method_11654(class_2281.field_10768) != var8
                     || var7.method_11654(class_2281.field_10770) != var10) {
                     xg5u0itczfrpg(var0, var4, var5, var8);
                  }
               }
            }
         }
      }
   }

   private static boolean x3dmceb8h6ov6d(class_3218 var0, class_2338 var1, class_2338 var2, class_2350 var3) {
      for (class_2338 var5 : List.of(var1.method_10093(var3), var2.method_10093(var3))) {
         if (!x22gnz9u3m8aag(var0, var5)
            || !var0.method_8320(var5).method_26220(var0, var5).method_1110()
            || !var0.method_8320(var5.method_10084()).method_26220(var0, var5.method_10084()).method_1110()) {
            return false;
         }
      }

      return true;
   }

   private static class_2350 xdzsxthi9esqy(class_3218 var0, x15kgoaq6i3m6i var1, class_2338 var2, class_2338 var3) {
      boolean var4 = x3dmceb8h6ov6d(var0, var2, var3, class_2350.field_11043);
      boolean var5 = x3dmceb8h6ov6d(var0, var2, var3, class_2350.field_11035);
      if (var4 != var5) {
         return var4 ? class_2350.field_11043 : class_2350.field_11035;
      } else {
         return var1.core().method_10260() > var2.method_10260() ? class_2350.field_11035 : class_2350.field_11043;
      }
   }

   private static void xg5u0itczfrpg(class_3218 var0, class_2338 var1, class_2338 var2, class_2350 var3) {
      class_2680 var4 = var0.method_8320(var1);
      class_2680 var5 = var0.method_8320(var2);
      class_2680 var6 = (class_2680)((class_2680)(var4.method_27852(class_2246.field_10034) ? var4 : class_2246.field_10034.method_9564())
            .method_11657(class_2281.field_10768, var3))
         .method_11657(class_2281.field_10770, var3 == class_2350.field_11043 ? class_2745.field_12574 : class_2745.field_12571);
      class_2680 var7 = (class_2680)((class_2680)(var5.method_27852(class_2246.field_10034) ? var5 : class_2246.field_10034.method_9564())
            .method_11657(class_2281.field_10768, var3))
         .method_11657(class_2281.field_10770, var3 == class_2350.field_11043 ? class_2745.field_12571 : class_2745.field_12574);
      byte var8 = 18;
      var0.method_8652(var1, var6, var8);
      var0.method_8652(var2, var7, var8);
   }

   public static boolean x57l6vd1sx1rt(class_3218 var0, x15kgoaq6i3m6i var1, Predicate<class_1799> var2) {
      for (class_2338 var4 : var1.x2ad3s8aentmo4()) {
         if (x22gnz9u3m8aag(var0, var4) && var0.method_8321(var4) instanceof class_1263 var6) {
            for (int var7 = 0; var7 < var6.method_5439(); var7++) {
               class_1799 var8 = var6.method_5438(var7);
               if (var8.method_7960() || var2.test(var8) && var8.method_7947() < var8.method_7914()) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public static int x11dwzrdveisv7(class_3218 var0, x15kgoaq6i3m6i var1) {
      int var2 = 0;

      for (class_2338 var4 : var1.x2ad3s8aentmo4()) {
         if (x22gnz9u3m8aag(var0, var4) && var0.method_8321(var4) instanceof class_1263 var5) {
            for (int var7 = 0; var7 < var5.method_5439(); var7++) {
               if (var5.method_5438(var7).method_7960()) {
                  var2++;
               }
            }
         }
      }

      return var2;
   }

   public static boolean xis8cbwd2sinx(class_3218 var0, class_1646 var1) {
      var var2 = x3psqezd5jem54(var0, var1.method_24515());
      return var2.isEmpty() ? true : x9jjpc1qt9hfg(var0, (x15kgoaq6i3m6i)var2.get()).isEmpty();
   }

   public static List<String> x9jjpc1qt9hfg(class_3218 var0, x15kgoaq6i3m6i var1) {
      var var2 = new ArrayList<String>();
      int var3 = x3sqkg3n6v972r.x2by5f9ehxvwk9().village.max_population_per_bell;
      if (var1.population() >= var3) {
         var2.add("лимит населения " + var3);
      }

      if (var1.beds() <= var1.population()) {
         var2.add("нет лишней кровати");
      }

      int var4 = Math.max(24, var1.population() * 6);
      int var5 = x1397xrm4pk893(var0, var1);
      int var6 = var1.cachedFood() + var5;
      if (var6 < var4) {
         var2.add("еда всего " + var6 + "/" + var4);
      }

      if (var1.x3klfyffbbfab2() != Long.MIN_VALUE && var0.method_75260() - var1.x3klfyffbbfab2() < 1200L) {
         var2.add("после угрозы осталось " + (1200L - (var0.method_75260() - var1.x3klfyffbbfab2()) + 19L) / 20L + " с");
      }

      return var2;
   }

   public static int xhahxgpa08yc9(class_1799 var0) {
      class_4174 var1 = (class_4174)var0.method_58694(class_9334.field_50075);
      return var1 == null ? 0 : Math.max(1, var1.comp_2491());
   }

   public static int x1397xrm4pk893(class_3218 var0, x15kgoaq6i3m6i var1) {
      int var2 = 0;

      for (class_1646 var4 : var0.method_18023(class_1299.field_6077, x150ccxomrlozr(var0, var1), var0x -> var0x.method_5805())) {
         if (x3psqezd5jem54(var0, var4.method_24515()).map(var1x -> var1x.id().equals(var1.id())).orElse(false)) {
            var2 += x24dik3nra38j8(var4);
         }
      }

      return var2;
   }

   private static int x24dik3nra38j8(class_1646 var0) {
      int var1 = 0;

      for (int var2 = 0; var2 < var0.method_35199().method_5439(); var2++) {
         class_1799 var3 = var0.method_35199().method_5438(var2);
         var1 += xhahxgpa08yc9(var3) * var3.method_7947();
      }

      return var1;
   }

   private static boolean x3adevuvxjhqbp(class_1646 var0) {
      for (int var1 = 0; var1 < var0.method_35199().method_5439(); var1++) {
         if (xhahxgpa08yc9(var0.method_35199().method_5438(var1)) > 0) {
            return true;
         }
      }

      return false;
   }

   private static void x2qawesj33vqrj(class_3218 var0, x15kgoaq6i3m6i var1, List<class_1646> var2) {
      long var3 = Math.floorMod(var0.method_8532(), 24000L);
      if (var3 >= 9000L && var3 < 12000L) {
         for (class_1646 var6 : var2) {
            int var7 = x24dik3nra38j8(var6);
            int var8 = !var6.method_6109() && var6.method_5618() == 0 ? 12 : (x3adevuvxjhqbp(var6) ? 0 : 1);

            for (int var9 = 0; var9 < 12 && var7 < var8; var9++) {
               var var10 = x3lxwdirnn77qc(var0, var1, var0x -> xhahxgpa08yc9(var0x) > 0);
               if (var10.isEmpty()) {
                  break;
               }

               var7 += xhahxgpa08yc9((class_1799)var10.get());
               class_1799 var11 = var6.method_35199().method_5491((class_1799)var10.get());
               if (!var11.method_7960()) {
                  x2cl1crde2rnxy(var0, var1, var11);
               }
            }

            if (!x3adevuvxjhqbp(var6)) {
               x3lxwdirnn77qc(var0, var1, var0x -> xhahxgpa08yc9(var0x) > 0).ifPresent(var3x -> {
                  class_1799 var4 = var6.method_35199().method_5491(var3x);
                  if (!var4.method_7960()) {
                     x2cl1crde2rnxy(var0, var1, var4);
                  }
               });
            }
         }
      }
   }

   private static int xllp0ajwexj5r(class_3218 var0, x15kgoaq6i3m6i var1, int var2) {
      class_2338 var3 = var1.core();
      class_2339 var4 = new class_2339();

      int var5;
      for (var5 = 0; var1.x3kz9uqjj0g2fr() < 35937 && var5 < var2; var5++) {
         int var6 = var1.x3kz9uqjj0g2fr();
         int var7 = var6 % 33 - 16;
         int var8 = var6 / 33;
         int var9 = var8 % 33 - 16;
         int var10 = var8 / 33 - 16;
         var4.method_10103(var3.method_10263() + var7, var3.method_10264() + var10, var3.method_10260() + var9);
         if (x22gnz9u3m8aag(var0, var4)) {
            class_2586 var11 = var0.method_8321(var4);
            if (var11 instanceof class_2595 || var11 instanceof class_3719) {
               var1.x36r3qqcopafen().add(var4.method_10062());
            }
         }

         var1.x11k3dhx0f8bj3();
      }

      if (var1.x3kz9uqjj0g2fr() >= 35937) {
         var1.xtffnc1wwh86p(var0.method_75260());
         x2u0vsam1tcmhq(var0, var1);
      }

      return var5;
   }

   static void x2u0vsam1tcmhq(class_3218 var0, x15kgoaq6i3m6i var1) {
      int var2 = 0;
      int var3 = 0;
      int var4 = 0;
      int var5 = 0;
      int var6 = 0;
      int var7 = 0;

      for (class_2338 var9 : var1.x2ad3s8aentmo4()) {
         if (x22gnz9u3m8aag(var0, var9) && var0.method_8321(var9) instanceof class_1263 var11) {
            for (int var12 = 0; var12 < var11.method_5439(); var12++) {
               class_1799 var13 = var11.method_5438(var12);
               if (!var13.method_7960()) {
                  if (var13.method_58694(class_9334.field_50075) != null) {
                     var2 += xhahxgpa08yc9(var13) * var13.method_7947();
                  }

                  if (var13.method_31573(class_3489.field_15539)) {
                     var3 += var13.method_7947();
                  }

                  if (var13.method_31573(class_3489.field_25808)) {
                     var4 += var13.method_7947();
                  }

                  if (var13.method_31573(class_3489.field_15528)) {
                     var5 += var13.method_7947();
                  }

                  if (var13.method_31574(class_1802.field_8620) || var13.method_31574(class_1802.field_33400)) {
                     var6 += var13.method_7947();
                  }

                  if (var13.method_31574(class_1802.field_8477)) {
                     var7 += var13.method_7947();
                  }
               }
            }
         }
      }

      var1.x2rezm3a3rz7iu(var2, var3, var4, var5, var6, var7);
   }

   private static void x2o9v4z8h5h1n1(class_3218 var0, x15kgoaq6i3m6i var1, long var2) {
      class_238 var4 = x150ccxomrlozr(var0, var1);
      var var5 = new ArrayList<>(
         var0.method_18023(class_1299.field_6077, var4, var0x -> var0x.method_5805())
            .stream()
            .filter(var2x -> x3psqezd5jem54(var0, var2x.method_24515()).map(var1xx -> var1xx.id().equals(var1.id())).orElse(false))
            .toList()
      );
      int var6 = (int)var5.stream().filter(var0x -> !var0x.method_6109()).count();
      if (!var1.xxqujir3s0ild()) {
         if (var6 >= 8) {
            var1.xvuz8u6ue7m7c();
            xchl1k1tnh3w8(var0).method_80();
         } else if (var1.x3e7xus757aqpt() >= 2) {
            x1yev2n44jz8yr(var0, var1, var5, 8 - var6);
            var6 = (int)var5.stream().filter(var0x -> !var0x.method_6109()).count();
            if (var6 >= 8) {
               var1.xvuz8u6ue7m7c();
            }

            xchl1k1tnh3w8(var0).method_80();
         }
      }

      int var7 = (int)var0.method_19494().method_20252(var0x -> var0x.method_40225(class_7477.field_39291), var1.core(), var1.radius(), class_4155.field_18489);
      var1.x1c0m5jj5x6wpm(var5.size(), var7, var2);
      x2qawesj33vqrj(var0, var1, var5);
      x1wvfvkvaehe0z(var0, var1, var5);
      x2u0vsam1tcmhq(var0, var1);
      xmgumxbnfkgss.x1cgirut0dfx4w(var0, var1);
      x1o5o1i05gjwxl(var0, var1, var5);
      var1.xhwx3flu2btqb(var5);
      x36n0pgu9qp6rk.xkfga2dpst7kt(var0, var1, var5, var2);
      xt78n188f3w97.x2pesumrvfap2d(var0, var1, var5);
      var var8 = var5.stream()
         .filter(var1x -> !var1x.method_6109() && var1.roleOf(var1x.method_5667()) == x1rxiy9mtu5b3h.BUILDER)
         .map(class_1297::method_5667)
         .collect(Collectors.toSet());
      boolean var9 = false;
      long var10 = Math.floorMod(var0.method_8532(), 24000L);
      boolean var12 = var10 >= 1000L && var10 < 9000L;

      for (x2arpn38dclo1g var14 : var1.x1ja61emeln2hc()) {
         boolean var15 = var14.x3vu8mnvg0blk4()
            .flatMap(var1x -> var5.stream().filter(var1xx -> var1xx.method_5667().equals(var1x)).findFirst())
            .map(var0x -> var0x.method_18868().method_27074().stream().anyMatch(var0xx -> var0xx instanceof x2cvbyh5y5day0))
            .orElse(false);
         if (var14.x32oz43hmeynhc() == x20r5v7z43p3d5.BLOCKED && var14.x2zq2smhbu3lzo() > 0L && var2 >= var14.x2zq2smhbu3lzo()) {
            var14.x1ogar57a8j8ls();
            var9 = true;
         }

         boolean var16 = var12
            && var14.x32oz43hmeynhc() == x20r5v7z43p3d5.BUILDING
            && var14.x16o43azbz1opa() != Long.MIN_VALUE
            && var2 - var14.x16o43azbz1opa() > (var15 ? 1800 : 200);
         if (var14.x3vu8mnvg0blk4().isPresent() && (!var8.contains(var14.x3vu8mnvg0blk4().get()) || var16)) {
            var14.x23bey8uos6gyt();
            var9 = true;
         }
      }

      if (var9) {
         xchl1k1tnh3w8(var0).method_80();
      }

      x3gadv4asdqmks(var0, var1, var5, var2);
   }

   private static void x1yev2n44jz8yr(class_3218 var0, x15kgoaq6i3m6i var1, List<class_1646> var2, int var3) {
      for (int var4 = 0; var4 < var3; var4++) {
         var var5 = xcmgrg6f6maj1(var0, var1, var4);
         if (var5.isEmpty()) {
            break;
         }

         class_1646 var6 = (class_1646)class_1299.field_6077.method_5883(var0, class_3730.field_16471);
         if (var6 == null) {
            break;
         }

         class_2338 var7 = (class_2338)var5.get();
         var6.method_5814(var7.method_10263() + 0.5, var7.method_10264(), var7.method_10260() + 0.5);
         var6.method_5971();
         if (var0.method_8649(var6)) {
            var2.add(var6);
         }
      }
   }

   static Optional<class_2338> xcmgrg6f6maj1(class_3218 var0, x15kgoaq6i3m6i var1, int var2) {
      for (int var3 = 2; var3 <= 12; var3++) {
         for (int var4 = 0; var4 < var3 * 8; var4++) {
            int var5 = Math.floorMod(var4 + var2 * 7, var3 * 8);
            int var6;
            int var7;
            if (var5 < var3 * 2) {
               var6 = -var3 + var5;
               var7 = -var3;
            } else if (var5 < var3 * 4) {
               var6 = var3;
               var7 = -var3 + (var5 - var3 * 2);
            } else if (var5 < var3 * 6) {
               var6 = var3 - (var5 - var3 * 4);
               var7 = var3;
            } else {
               var6 = -var3;
               var7 = var3 - (var5 - var3 * 6);
            }

            int var8 = var1.core().method_10263() + var6;
            int var9 = var1.core().method_10260() + var7;
            if (var0.method_14178().method_12123(var8 >> 4, var9 >> 4)) {
               class_2338 var10 = new class_2338(var8, var0.method_8624(class_2903.field_13203, var8, var9), var9);
               if (var0.method_8316(var10).method_15769()
                  && var0.method_8316(var10.method_10074()).method_15769()
                  && var0.method_8320(var10).method_26220(var0, var10).method_1110()
                  && var0.method_8320(var10.method_10084()).method_26220(var0, var10.method_10084()).method_1110()
                  && var0.method_8320(var10.method_10074()).method_26206(var0, var10.method_10074(), class_2350.field_11036)) {
                  class_238 var11 = new class_238(var10).method_1014(0.2);
                  if (!x2uh56vnzc4u51.x3gqyq7yie9jxr(var0, var10)
                     && var0.method_8621().method_11952(var10)
                     && var0.method_8333(null, var11, var0x -> var0x.method_5805()).isEmpty()) {
                     return Optional.of(var10);
                  }
               }
            }
         }
      }

      return Optional.empty();
   }

   private static void x3gadv4asdqmks(class_3218 var0, x15kgoaq6i3m6i var1, List<class_1646> var2, long var3) {
      if (var3 >= var1.x180oul2yysi55() && x9jjpc1qt9hfg(var0, var1).isEmpty() && var1.cachedFood() >= 24) {
         var var5 = var2.stream()
            .filter(var0x -> !var0x.method_6109() && var0x.method_5618() == 0)
            .sorted(Comparator.comparingDouble(var1x -> var1x.method_24515().method_10262(var1.core())))
            .limit(2L)
            .toList();
         if (var5.size() >= 2) {
            var var6 = xcmgrg6f6maj1(var0, var1, (int)(var3 / 100L));
            if (!var6.isEmpty()) {
               class_1646 var7 = (class_1646)class_1299.field_6077.method_5883(var0, class_3730.field_16466);
               if (var7 != null) {
                  class_2338 var8 = (class_2338)var6.get();
                  var7.method_5814(var8.method_10263() + 0.5, var8.method_10264(), var8.method_10260() + 0.5);
                  var7.method_5614(-DevelopmentPolicy.delay(1800, x3sqkg3n6v972r.x2by5f9ehxvwk9().village.development_speed));
                  var7.method_5971();
                  if (x38ipozok9mc0x(var0, var1, () -> x2o11j2ko0ev05(var0, var1, 24) && var0.method_8649(var7), List.of())) {
                     var5.forEach(var0x -> var0x.method_5614(DevelopmentPolicy.delay(1200, x3sqkg3n6v972r.x2by5f9ehxvwk9().village.development_speed)));
                     var1.x336kh3r7239fs(var3 + DevelopmentPolicy.delay(300, x3sqkg3n6v972r.x2by5f9ehxvwk9().village.development_speed));
                     var0.method_65096(
                        class_2398.field_11201, var8.method_10263() + 0.5, var8.method_10264() + 1, var8.method_10260() + 0.5, 8, 0.8, 0.5, 0.8, 0.0
                     );
                     xchl1k1tnh3w8(var0).method_80();
                  }
               }
            }
         }
      }
   }

   public static boolean x2o11j2ko0ev05(class_3218 var0, x15kgoaq6i3m6i var1, int var2) {
      if (var2 <= 0) {
         return true;
      } else {
         long var3 = 0L;

         for (class_2338 var6 : var1.x2ad3s8aentmo4()) {
            if (x22gnz9u3m8aag(var0, var6) && var0.method_8321(var6) instanceof class_1263 var7) {
               for (int var17 = 0; var17 < var7.method_5439(); var17++) {
                  class_1799 var9 = var7.method_5438(var17);
                  var3 += (long)xhahxgpa08yc9(var9) * var9.method_7947();
               }
            }
         }

         if (var3 < var2) {
            return false;
         } else {
            int var14 = var2;

            for (class_2338 var16 : var1.x2ad3s8aentmo4()) {
               if (x22gnz9u3m8aag(var0, var16) && var0.method_8321(var16) instanceof class_1263 var18) {
                  for (int var20 = 0; var20 < var18.method_5439() && var14 > 0; var20++) {
                     class_1799 var10 = var18.method_5438(var20);
                     int var11 = xhahxgpa08yc9(var10);
                     if (var11 > 0) {
                        int var12 = Math.min(var10.method_7947(), (var14 + var11 - 1) / var11);
                        class_1792 var13 = var10.method_7909();
                        var10.method_7934(var12);
                        var1.x2sh8yupnxmo71(var13, var12);
                        var14 -= var12 * var11;
                        var18.method_5431();
                     }
                  }

                  if (var14 <= 0) {
                     return true;
                  }
               }
            }

            return false;
         }
      }
   }

   private static void x1o5o1i05gjwxl(class_3218 var0, x15kgoaq6i3m6i var1, List<class_1646> var2) {
      x2ycvfpsvizlf9(var0, var1, var2, false);
   }

   public static void xsd7almzt4rct(class_3218 var0, x15kgoaq6i3m6i var1, List<class_1646> var2) {
      x2u0vsam1tcmhq(var0, var1);
      xmgumxbnfkgss.x1cgirut0dfx4w(var0, var1);
      x2ycvfpsvizlf9(var0, var1, var2, true);
      var1.xhwx3flu2btqb(var2);
   }

   private static void x2ycvfpsvizlf9(class_3218 var0, x15kgoaq6i3m6i var1, List<class_1646> var2, boolean var3) {
      var var4 = var2.stream().filter(var0x -> !var0x.method_6109()).sorted(Comparator.comparing(class_1297::method_5667)).toList();
      var var5 = var4.stream().filter(var1x -> {
         x1rxiy9mtu5b3h var2x = var1.roleOf(var1x.method_5667());
         return var2x != x1rxiy9mtu5b3h.ELDER && var2x != x1rxiy9mtu5b3h.FOUNDER;
      }).toList();
      var var6 = x17qz5r6rg31u8(var1, var0.method_75260(), var5.size());
      var var7 = new EnumMap<x1rxiy9mtu5b3h, Integer>(x1rxiy9mtu5b3h.class);

      for (x1rxiy9mtu5b3h var11 : x1rxiy9mtu5b3h.values()) {
         var7.put(var11, 0);
      }

      for (class_1646 var23 : var4) {
         var7.compute(var1.roleOf(var23.method_5667()), (var0x, var1x) -> var1x + 1);
      }

      boolean var22 = false;
      int var24 = (Integer)var7.get(x1rxiy9mtu5b3h.CITIZEN);
      boolean var25 = var24 > 0;
      boolean var26 = var3
         || var1.x2c2srygrjrm6g() != Long.MIN_VALUE
            && var0.method_75260() - var1.x2c2srygrjrm6g()
               >= (var1.cachedFood() >= Math.max(48, var1.population() * 8) && var1.x182p7mjnr75sg() != 0 ? 2400 : 1200);
      int var12 = var25 ? var24 : (var26 ? 1 : 0);
      var var13 = new ArrayList<>(
         List.of(
            x1rxiy9mtu5b3h.BUILDER,
            x1rxiy9mtu5b3h.FARMER,
            x1rxiy9mtu5b3h.LUMBERJACK,
            x1rxiy9mtu5b3h.MINER,
            x1rxiy9mtu5b3h.FISHERMAN,
            x1rxiy9mtu5b3h.RANCHER,
            x1rxiy9mtu5b3h.GUARD
         )
      );
      var var14 = x8x0922dn63y6(var1, var0.method_75260());
      var13.sort(
         Comparator.<x1rxiy9mtu5b3h>comparingInt(var2x -> (Integer)var6.get(var2x) - (Integer)var7.get(var2x))
            .reversed()
            .thenComparing(Comparator.comparingInt(var14::get).reversed())
      );

      label97:
      for (x1rxiy9mtu5b3h var16 : var13) {
         while (var7.get(var16) < var6.get(var16) && var12 > 0) {
            var var17 = var5.stream()
               .filter(var2x -> var1.roleOf(var2x.method_5667()) != var16)
               .filter(var3x -> var25 || xmgumxbnfkgss.x2hrr4jjriuh85(var0, var1, var3x, var0.method_75260()))
               .filter(var4x -> {
                  x1rxiy9mtu5b3h var5x = var1.roleOf(var4x.method_5667());
                  return var25 ? var5x == x1rxiy9mtu5b3h.CITIZEN : (Integer)var7.get(var5x) > (Integer)var6.get(var5x);
               })
               .min(Comparator.comparingInt(var1x -> var1.roleOf(var1x.method_5667()) == x1rxiy9mtu5b3h.CITIZEN ? 0 : 1));
            if (var17.isEmpty()) {
               break;
            }

            x1rxiy9mtu5b3h var18 = var1.roleOf(((class_1646)var17.get()).method_5667());

            for (class_7893 var20 : List.copyOf(((class_1646)var17.get()).method_18868().method_27074())) {
               if (var20.getClass().getName().startsWith("net.smartcolonies.")) {
                  var20.method_18925(var0, (class_1309)var17.get(), var0.method_75260());
               }
            }

            x1c1r5c6a13gtl.x2fxoqmbs0henb((class_1646)var17.get());
            var22 |= var1.x36xpmo21hmp9h(((class_1646)var17.get()).method_5667(), var16);
            xmgumxbnfkgss.xw6l4sc9khw02(var1, (class_1646)var17.get(), var0.method_75260());
            var7.put(var18, (Integer)var7.get(var18) - 1);
            var7.put(var16, (Integer)var7.get(var16) + 1);
            if (--var12 <= 0) {
               break label97;
            }
         }
      }

      if (var22 || var1.x2c2srygrjrm6g() == Long.MIN_VALUE) {
         var1.xjq48e1i3w4gj(var0.method_75260());
         var22 = true;
      }

      for (class_1646 var29 : var4) {
         x21g1rujs9r1h6(var0, var29, var1.roleOf(var29.method_5667()));
      }

      x3d5vk1zj3fvkj var28 = xchl1k1tnh3w8(var0);
      if (var22) {
         var28.method_80();
      }

      for (class_1646 var31 : var4) {
         var31.method_18868().method_18878(xxao083dbo7ag.x3ecoc0soedv0m, var1.roleOf(var31.method_5667()));
      }
   }

   private static void x1wvfvkvaehe0z(class_3218 var0, x15kgoaq6i3m6i var1, List<class_1646> var2) {
      var var3 = var2.stream().filter(var0x -> !var0x.method_6109()).toList();
      var var4 = var3.stream()
         .filter(var1x -> var1.roleOf(var1x.method_5667()) == x1rxiy9mtu5b3h.ELDER)
         .min(
            Comparator.<class_1646>comparingInt(var1x -> var1.x3ctixeo4to9a4().filter(var1x.method_5667()::equals).isPresent() ? 0 : 1)
               .thenComparingDouble(var1x -> var1x.method_24515().method_10262(var1.core()))
         );
      if (var4.isPresent()) {
         for (class_1646 var11 : var3) {
            if (var11 != var4.get() && var1.roleOf(var11.method_5667()) == x1rxiy9mtu5b3h.ELDER) {
               var1.x36xpmo21hmp9h(var11.method_5667(), x1rxiy9mtu5b3h.CITIZEN);
               xchl1k1tnh3w8(var0).method_80();
            }
         }

         if (var1.x3ctixeo4to9a4().filter(((class_1646)var4.get()).method_5667()::equals).isEmpty()) {
            var1.x3c59xc9hrfvhz(((class_1646)var4.get()).method_5667());
            xchl1k1tnh3w8(var0).method_80();
         }
      } else if (!var3.isEmpty()) {
         var var5 = new EnumMap<x1rxiy9mtu5b3h,Long>(x1rxiy9mtu5b3h.class);

         for (class_1646 var7 : var3) {
            var5.merge(var1.roleOf(var7.method_5667()), 1L, Long::sum);
         }

         var var10 = var3.stream()
            .filter(var1x -> var1.roleOf(var1x.method_5667()) == x1rxiy9mtu5b3h.CITIZEN)
            .min(Comparator.comparingDouble(var1x -> var1x.method_24515().method_10262(var1.core())))
            .or(
               () -> var3.stream()
                  .filter(var1xx -> var1.roleOf(var1xx.method_5667()) != x1rxiy9mtu5b3h.FOUNDER)
                  .filter(var2xx -> var5.getOrDefault(var1.roleOf(var2xx.method_5667()), 0L) > 1L)
                  .min(Comparator.comparingDouble(var1xx -> var1xx.method_24515().method_10262(var1.core())))
            );
         if (var10.isEmpty()) {
            var var12 = xcmgrg6f6maj1(var0, var1, var3.size());
            if (var12.isEmpty()) {
               return;
            }

            class_1646 var8 = (class_1646)class_1299.field_6077.method_5883(var0, class_3730.field_16471);
            if (var8 == null) {
               return;
            }

            var8.method_5814(
               ((class_2338)var12.get()).method_10263() + 0.5, ((class_2338)var12.get()).method_10264(), ((class_2338)var12.get()).method_10260() + 0.5
            );
            var8.method_5971();
            if (!var0.method_8649(var8)) {
               return;
            }

            var2.add(var8);
            var10 = Optional.of(var8);
         }

         class_1646 var13 = (class_1646)var10.get();
         var1.x36xpmo21hmp9h(var13.method_5667(), x1rxiy9mtu5b3h.ELDER);
         var1.x3c59xc9hrfvhz(var13.method_5667());
         x21g1rujs9r1h6(var0, var13, x1rxiy9mtu5b3h.ELDER);
         xchl1k1tnh3w8(var0).method_80();
      }
   }

   public static EnumMap<x1rxiy9mtu5b3h, Integer> x8x0922dn63y6(x15kgoaq6i3m6i var0, long var1) {
      var var3 = new EnumMap<x1rxiy9mtu5b3h,Integer>(x1rxiy9mtu5b3h.class);

      for (x1rxiy9mtu5b3h var7 : x1rxiy9mtu5b3h.values()) {
         var3.put(var7, 0);
      }

      int var17 = Math.max(1, var0.population());
      int var18 = var0.cachedFood();
      int var19 = var0.cachedLogs();
      int var20 = var0.cachedStone();
      int var8 = var0.x12nt3bv2et7b7();
      boolean var9 = var0.x182p7mjnr75sg() < x1ext6bk0354f9(var0.population());
      var3.put(x1rxiy9mtu5b3h.BUILDER, var9 ? 100 : (var0.x3e4ycl8cmmmgc() ? 90 : 25));
      int var10 = Math.max(48, var17 * 8);
      int var11 = Math.max(96, var17 * 20);
      if (var18 < var10) {
         var3.put(x1rxiy9mtu5b3h.FARMER, 75);
         var3.put(x1rxiy9mtu5b3h.FISHERMAN, 55);
         var3.put(x1rxiy9mtu5b3h.RANCHER, 40);
      } else if (var18 < var11) {
         var3.put(x1rxiy9mtu5b3h.FARMER, 60);
         var3.put(x1rxiy9mtu5b3h.FISHERMAN, 40);
         var3.put(x1rxiy9mtu5b3h.RANCHER, 30);
      } else {
         var3.put(x1rxiy9mtu5b3h.FARMER, 25);
         var3.put(x1rxiy9mtu5b3h.FISHERMAN, 15);
         var3.put(x1rxiy9mtu5b3h.RANCHER, 15);
      }

      int var12 = Math.max(64, var17 * 8);
      int var13 = var19 >= Math.max(16, var12 / 4) && var19 * 4L >= var20 ? (var19 < var12 ? 70 : 20) : 100;
      var3.put(x1rxiy9mtu5b3h.LUMBERJACK, var13);
      int var14 = Math.max(128, var17 * 16);
      int var15 = var20 < var14 / 4 ? 100 : (var20 < var14 ? 65 : 15);
      if (var8 < Math.max(8, var17)) {
         var15 = Math.max(var15, 60);
      }

      var3.put(x1rxiy9mtu5b3h.MINER, var15);
      boolean var16 = var0.x3klfyffbbfab2() != Long.MIN_VALUE && var1 >= var0.x3klfyffbbfab2() && var1 - var0.x3klfyffbbfab2() < 2400L;
      var3.put(x1rxiy9mtu5b3h.GUARD, x3sqkg3n6v972r.x2by5f9ehxvwk9().roles.enable_guards ? (var16 ? 100 : (var17 >= 12 ? 35 : 20)) : 0);
      xmgumxbnfkgss.x2hptx3oklbfn2(var0, var3);
      return var3;
   }

   public static EnumMap<x1rxiy9mtu5b3h, Integer> x17qz5r6rg31u8(x15kgoaq6i3m6i var0, long var1, int var3) {
      var var4 = x8x0922dn63y6(var0, var1);
      var var5 = new EnumMap<x1rxiy9mtu5b3h,Integer>(x1rxiy9mtu5b3h.class);
      var var6 = new EnumMap<x1rxiy9mtu5b3h, Double>(x1rxiy9mtu5b3h.class);

      for (x1rxiy9mtu5b3h var10 : x1rxiy9mtu5b3h.values()) {
         var5.put(var10, 0);
      }

      if (var3 <= 0) {
         return var5;
      } else {
         var var17 = List.of(
               x1rxiy9mtu5b3h.BUILDER,
               x1rxiy9mtu5b3h.FARMER,
               x1rxiy9mtu5b3h.LUMBERJACK,
               x1rxiy9mtu5b3h.MINER,
               x1rxiy9mtu5b3h.FISHERMAN,
               x1rxiy9mtu5b3h.RANCHER,
               x1rxiy9mtu5b3h.GUARD
            )
            .stream()
            .filter(var1x -> (Integer)var4.get(var1x) > 0)
            .toList();
         var minimumRoles = DevelopmentPolicy.minimumRoles(var3, var17);
         for (x1rxiy9mtu5b3h var21 : minimumRoles) {
            var5.put(var21, 1);
         }

         int var18 = minimumRoles.size();
         int var20 = var3 - var18;
         int var22 = var17.stream().mapToInt(var4::get).sum();
         if (var22 <= 0) {
            var5.put(x1rxiy9mtu5b3h.CITIZEN, var3);
            return var5;
         } else {
            int var11 = var18;

            for (x1rxiy9mtu5b3h var13 : var17) {
               double var14 = (double)((Integer)var4.get(var13)).intValue() * var20 / var22;
               int var16 = (int)Math.floor(var14);
               var5.put(var13, (Integer)var5.get(var13) + var16);
               var6.put(var13, var14 - var16);
               var11 += var16;
            }

            while (var11 < var3) {
               x1rxiy9mtu5b3h var23 = var6.entrySet()
                  .stream()
                  .max(Comparator.<Entry<x1rxiy9mtu5b3h, Double>>comparingDouble(Entry::getValue).thenComparingInt(var1x -> (Integer)var4.get(var1x.getKey())))
                  .map(Entry::getKey)
                  .orElse(x1rxiy9mtu5b3h.FARMER);
               var5.put(var23, (Integer)var5.get(var23) + 1);
               var6.put(var23, -1.0);
               var11++;
            }

            return var5;
         }
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public static void x21g1rujs9r1h6(class_3218 var0, class_1646 var1, x1rxiy9mtu5b3h var2) {
      x3psqezd5jem54(var0, var1.method_24515()).ifPresent(var2x -> xf6ny39u2klct.x3cumye59iar40(var0, var2x, var1));
      class_2338 var3 = x3psqezd5jem54(var0, var1.method_24515()).map(x15kgoaq6i3m6i::core).orElse(var1.method_24515());
      class_5321 var4 = class_3854.method_16930(var0.method_23753(var3));
      if (!var1.method_7231().comp_3520().method_40225(var4)) {
         var1.method_7195(var1.method_7231().method_66687(var0.method_30349(), var4));
      }
      class_5321 var5 = switch (var2) {
         case BUILDER -> class_3852.field_17061;
         case GUARD -> class_3852.field_17052;
         case LUMBERJACK, MINER -> class_3852.field_17064;
         case FARMER -> class_3852.field_17056;
         case FISHERMAN -> class_3852.field_17057;
         case RANCHER -> class_3852.field_17063;
         case ELDER, FOUNDER -> class_3852.field_17054;
         default -> class_3852.field_17051;
      };
      if (!var1.method_7231().comp_3521().method_40225(var5)) {
         var1.method_7195(var1.method_7231().method_66688(var0.method_30349(), var5));
      }

      boolean var6 = var1.method_5752().contains("smart_colonies_role_name");
      if (!var1.method_16914() || var6 && x2qi06jvtolj3u(var1.method_5797())) {
         if (var2 == x1rxiy9mtu5b3h.CITIZEN) {
            if (var6) {
               var1.method_5665(null);
               var1.method_5880(false);
               var1.method_5738("smart_colonies_role_name");
            }
         } else {
            var1.method_5665(class_2561.method_43471("entity.smart_colonies.role." + var2.serializedName()));
            var1.method_5880(true);
            var1.method_5780("smart_colonies_role_name");
         }
      }
   }

   private static boolean x2qi06jvtolj3u(class_2561 var0) {
      String var1 = var0.getString();
      return Set.of(
               "Builder",
               "Elder",
               "Founder",
               "Guard",
               "Militia",
               "Lumberjack",
               "Miner",
               "Farmer",
               "Fisherman",
               "Rancher",
               "Строитель",
               "Ополченец",
               "Лесоруб",
               "Шахтёр",
               "Фермер",
               "Рыбак",
               "Животновод",
               "Старейшина",
               "Основатель"
            )
            .contains(var1)
         || var1.startsWith("entity.smart_colonies.role.");
   }

   private static void x1t38fv75txqm7(class_3218 var0, x15kgoaq6i3m6i var1, long var2) {
      if (var0.method_19503(var1.core()) || x37idyr79ed75m.x3kaxmxx3tadjj(var0, var1).isPresent()) {
         var1.xz6ij33c0780z(var2);
         xchl1k1tnh3w8(var0).method_80();
      }
   }

   private static void x39ey6ni61f3zn(class_3218 var0, x15kgoaq6i3m6i var1) {
      if (var1.x2p9t1nvxo82zp() <= 0 || var1.x11xljsmcmocmw() <= 0) {
         if (var0.method_75260() >= var1.x147mtuve9wuay() && var1.x1n1a2vp01kxzf().isEmpty()) {
            if (x3sqkg3n6v972r.x2by5f9ehxvwk9().roles.enable_auto_expansion) {
               if (var1.population() != 0) {
                  long var2 = var1.x1ja61emeln2hc().stream().filter(var0x -> var0x.x32oz43hmeynhc() == x20r5v7z43p3d5.COMPLETE).count();
                  boolean var4 = var1.x3t9uchcxi5zlm().isPresent();
                  boolean var5 = var1.x182p7mjnr75sg() < x1ext6bk0354f9(var1.population());
                  boolean var6 = var2 < x3sqkg3n6v972r.x2by5f9ehxvwk9().village.max_constructed_houses;
                  if (var5 || var6 || var4) {
                     if (!var1.x3e4ycl8cmmmgc()) {
                        class_2960 var7 = var4 ? x24xpzm43k9cqb(var0, var1, var1.x1beis4altbswn()) : xvg326225w07a(var0, var1);
                        var var8 = var0.method_14183().method_15094(var7);
                        if (!var8.isEmpty()) {
                           class_2382 var9 = ((class_3499)var8.get()).method_15160();
                           if (!var1.x2zi111bgbtgzq()) {
                              var1.x31uoom8ikakob();
                           }

                           x32w10jmrowl3p.x2o5z7e7ygl136(var0, var1, var9.method_10263(), var9.method_10260(), var9.method_10264())
                              .ifPresent(
                                 var4x -> {
                                    UUID var5x = var1.x3t9uchcxi5zlm().orElse(null);
                                    int var6x = var1.x1beis4altbswn();
                                    x2arpn38dclo1g var7x = new x2arpn38dclo1g(
                                       UUID.randomUUID(), var7, var4x, var9.method_10263(), var9.method_10264(), var9.method_10260(), var5x, var6x
                                    );
                                    if (var5x == null && var1.x2p9t1nvxo82zp() > 0 && var1.x11xljsmcmocmw() > 0 && var0.method_75260() < var1.x3tkladv9pjeao()) {
                                       var7x.xkv5b17xl7vd6(true);
                                    }

                                    var1.x31ve0dsfeaxo4(var7x);
                                    if (var5x != null) {
                                       var1.xqsw8yhmtxlpl();
                                    }

                                    int var8x = (int)Math.ceil(Math.sqrt(var1.core().method_10262(var4x)))
                                       + Math.max(var9.method_10263(), var9.method_10260())
                                       + 4;
                                    SettlementSupport.expandRadius(var0, var1, Math.max(var1.radius(), Math.min(x3sqkg3n6v972r.x2by5f9ehxvwk9().village.expansion_radius_max, var8x)));
                                    xchl1k1tnh3w8(var0).method_80();
                                 }
                              );
                           if (!var1.x2zi111bgbtgzq() && !var1.x3e4ycl8cmmmgc()) {
                              SettlementSupport.expandRadius(var0, var1,
                                 Math.max(var1.radius(), Math.min(x3sqkg3n6v972r.x2by5f9ehxvwk9().village.expansion_radius_max, var1.radius() + 16))
                              );
                              var1.xs28n9ortj0j6(var0.method_75260() + 200L);
                              xchl1k1tnh3w8(var0).method_80();
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   static void x32npq1dyt2s4h(class_3218 var0, x15kgoaq6i3m6i var1) {
      if (x3sqkg3n6v972r.x2by5f9ehxvwk9().roles.enable_auto_expansion && !var1.x3e4ycl8cmmmgc() && var0.method_75260() >= var1.x147mtuve9wuay()) {
         long var2 = var1.x1ja61emeln2hc().stream().filter(var0x -> var0x.x2nfrt8qxdscso().isEmpty() && var0x.x2s8emavervqrz()).count();
         if (var2 >= x3sqkg3n6v972r.x2by5f9ehxvwk9().village.max_constructed_houses) {
            var1.x23n2tdykuxd7a(0);
         } else {
            class_2960 var4 = xvg326225w07a(var0, var1);
            var var5 = var0.method_14183().method_15094(var4);
            if (!var5.isEmpty()) {
               class_2382 var6 = ((class_3499)var5.get()).method_15160();
               if (!var1.x2zi111bgbtgzq()) {
                  var1.x31uoom8ikakob();
               }

               x32w10jmrowl3p.xzvjreewiw7bn(var0, var1, var6.method_10263(), var6.method_10260(), var6.method_10264(), true).ifPresent(var4x -> {
                  x2arpn38dclo1g var5x = new x2arpn38dclo1g(UUID.randomUUID(), var4, var4x, var6.method_10263(), var6.method_10264(), var6.method_10260());
                  var5x.xkv5b17xl7vd6(true);
                  var1.x31ve0dsfeaxo4(var5x);
                  xchl1k1tnh3w8(var0).method_80();
               });
               if (!var1.x2zi111bgbtgzq() && !var1.x3e4ycl8cmmmgc()) {
                  SettlementSupport.expandRadius(var0, var1, Math.min(x3sqkg3n6v972r.x2by5f9ehxvwk9().village.expansion_radius_max, var1.radius() + 16));
                  var1.xs28n9ortj0j6(var0.method_75260() + 200L);
                  xchl1k1tnh3w8(var0).method_80();
               }
            }
         }
      }
   }

   public static int x1ext6bk0354f9(int var0) {
      return Math.clamp((long)((var0 + 3) / 4), 4, 16);
   }

   public static String x2y69zyuty3rxs(class_3218 var0, x15kgoaq6i3m6i var1) {
      if (!var1.x1n1a2vp01kxzf().isEmpty()) {
         return "участок выбран, строится дорога (осталось " + var1.x1n1a2vp01kxzf().size() + ")";
      } else if (var1.x3e4ycl8cmmmgc()) {
         return "участок выбран, активно задание дома";
      } else if (var1.x2zi111bgbtgzq()) {
         return "поиск, проверен кандидат " + var1.x1eughlg5bm9gx();
      } else if (!x3sqkg3n6v972r.x2by5f9ehxvwk9().roles.enable_auto_expansion) {
         return "авторасширение выключено в конфиге";
      } else if (var1.population() == 0) {
         return "нет жителей";
      } else {
         long var2 = var1.x1ja61emeln2hc().stream().filter(var0x -> var0x.x32oz43hmeynhc() == x20r5v7z43p3d5.COMPLETE).count();
         int var4 = x3sqkg3n6v972r.x2by5f9ehxvwk9().village.max_constructed_houses;
         int var5 = x1ext6bk0354f9(var1.population());
         if (var1.x182p7mjnr75sg() >= var5 && var2 >= var4) {
            return "городская цель достигнута: домов " + var2 + "/" + var4;
         } else {
            long var6 = Math.max(0L, var1.x147mtuve9wuay() - var0.method_75260());
            if (var6 > 0L) {
               return "повтор поиска через " + (var6 + 19L) / 20L + " с";
            } else {
               return var1.x182p7mjnr75sg() < var5
                  ? "спрос на жильё есть, поиск запускается"
                  : "город растёт: домов " + var2 + "/" + var4 + ", поиск запускается";
            }
         }
      }
   }

   private static class_2960 xvg326225w07a(class_3218 var0, x15kgoaq6i3m6i var1) {
      String var2 = var0.method_23753(var1.core()).method_40230().map(var0x -> var0x.method_29177().method_12832()).orElse("plains");
      String var3 = var2.contains("desert")
         ? "desert"
         : (
            var2.contains("savanna")
               ? "savanna"
               : (!var2.contains("snow") && !var2.contains("frozen") ? (var2.contains("taiga") ? "taiga" : "plains") : "snowy")
         );
      long var4 = var1.x1ja61emeln2hc().stream().filter(var0x -> var0x.x32oz43hmeynhc() == x20r5v7z43p3d5.COMPLETE).count();
      var var6 = x53nro1q5nc9v.xrbmqlm78i747(var3);
      return x53nro1q5nc9v.x4jnab85o8lop(var3, (String)var6.get(Math.floorMod((int)var4, var6.size())));
   }

   private static class_2960 x24xpzm43k9cqb(class_3218 var0, x15kgoaq6i3m6i var1, int var2) {
      String var3 = var0.method_23753(var1.core()).method_40230().map(var0x -> var0x.method_29177().method_12832()).orElse("plains");
      String var4 = var3.contains("desert")
         ? "desert"
         : (
            var3.contains("savanna")
               ? "savanna"
               : (!var3.contains("snow") && !var3.contains("frozen") ? (var3.contains("taiga") ? "taiga" : "plains") : "snowy")
         );
      return x53nro1q5nc9v.x2xvromqtmmr47(var4, var2);
   }

   public static boolean x3pcmrumjil4mo(class_3218 var0, x15kgoaq6i3m6i var1, UUID var2, int var3) {
      return x2fxdvzecolqll(var0, var1, var2, var3).isPresent();
   }

   public static Optional<x2arpn38dclo1g> x2fxdvzecolqll(class_3218 var0, x15kgoaq6i3m6i var1, UUID var2, int var3) {
      if (var3 >= 1 && var3 <= 3 && !var1.x3t9uchcxi5zlm().isPresent() && var1.x1n1a2vp01kxzf().isEmpty() && x1dxdlnevtfbhm.x35ev8l9vc9uk7(var0)) {
         boolean var4 = var1.x1ja61emeln2hc()
            .stream()
            .anyMatch(var1x -> var1x.x2nfrt8qxdscso().filter(var2::equals).isPresent() && var1x.x32oz43hmeynhc() != x20r5v7z43p3d5.COMPLETE);
         if (var4) {
            return Optional.empty();
         } else {
            class_2960 var5 = x24xpzm43k9cqb(var0, var1, var3);
            var var6 = var0.method_14183().method_15094(var5);
            if (var6.isEmpty()) {
               return Optional.empty();
            } else {
               class_2382 var7 = ((class_3499)var6.get()).method_15160();
               if (!var1.x2zi111bgbtgzq()) {
                  var1.x31uoom8ikakob();
               }

               Optional<class_2338> var8 = Optional.empty();

               for (int var9 = 0; var9 < 32 && var1.x2zi111bgbtgzq() && var8.isEmpty(); var9++) {
                  var8 = x32w10jmrowl3p.x2o5z7e7ygl136(var0, var1, var7.method_10263(), var7.method_10260(), var7.method_10264());
               }

               xchl1k1tnh3w8(var0).method_80();
               if (var8.isEmpty()) {
                  return Optional.empty();
               } else {
                  x2arpn38dclo1g var10 = new x2arpn38dclo1g(
                     UUID.randomUUID(), var5, (class_2338)var8.get(), var7.method_10263(), var7.method_10264(), var7.method_10260(), var2, var3
                  );
                  var1.x31ve0dsfeaxo4(var10);
                  return Optional.of(var10);
               }
            }
         }
      } else {
         return Optional.empty();
      }
   }

   private static void x3tgmhaj405foe(class_3218 var0, x15kgoaq6i3m6i var1) {
      var1.x1ja61emeln2hc()
         .stream()
         .filter(var0x -> var0x.x32oz43hmeynhc() == x20r5v7z43p3d5.QUEUED || var0x.x32oz43hmeynhc() == x20r5v7z43p3d5.BUILDING)
         .forEach(var1x -> {
            class_3341 var2 = var1x.x24kbqv6g4kkrg();

            for (int var6 : new int[]{var2.method_35415(), var2.method_35418()}) {
               for (int var10 : new int[]{var2.method_35416(), var2.method_35419()}) {
                  for (int var14 : new int[]{var2.method_35417(), var2.method_35420()}) {
                     var0.method_65096(class_2398.field_11207, var6 + 0.5, var10 + 0.5, var14 + 0.5, 1, 0.0, 0.0, 0.0, 0.0);
                  }
               }
            }
         });
   }

   private static boolean x22gnz9u3m8aag(class_3218 var0, class_2338 var1) {
      return var0.method_14178().method_12123(var1.method_10263() >> 4, var1.method_10260() >> 4)
         && (!(var0.method_8321(var1) instanceof class_1263) || !x2uh56vnzc4u51.xic1z39wiy3pn(var0, var1));
   }
}
