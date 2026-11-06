package net.smartcolonies.r;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import net.minecraft.class_11;
import net.minecraft.class_1299;
import net.minecraft.class_1646;
import net.minecraft.class_2246;
import net.minecraft.class_2302;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_2818;
import net.minecraft.class_3218;
import net.minecraft.class_3341;
import net.minecraft.class_3481;
import net.minecraft.class_2902.class_2903;

public final class x32w10jmrowl3p {
   public static final int x3fqjwal9ef9rt = 48;
   public static final int x3p6brpzuhzsao = 16;

   private x32w10jmrowl3p() {
   }

   public static Optional<class_2338> x2o5z7e7ygl136(class_3218 var0, x15kgoaq6i3m6i var1, int var2, int var3, int var4) {
      return xzvjreewiw7bn(var0, var1, var2, var3, var4, false);
   }

   public static Optional<class_2338> xzvjreewiw7bn(class_3218 var0, x15kgoaq6i3m6i var1, int var2, int var3, int var4, boolean var5) {
      int var6 = Math.min(var1.radius(), x3sqkg3n6v972r.x2by5f9ehxvwk9().village.expansion_radius_max);
      int var7 = (var6 * 2 + 1) * (var6 * 2 + 1);
      class_2338 var8 = var1.core();

      for (int var9 = 0; var9 < 48 && var1.x1eughlg5bm9gx() < var7; var9++) {
         int var10 = var1.x1eughlg5bm9gx();
         var1.x2xopfa6a46mo9();
         x4t5d0ytwq4ac var11 = xuu0zlh8kzgkl(var10);
         if (Math.max(Math.abs(var11.x3bqzqsu81ak1i()), Math.abs(var11.x17l8srdozs7l())) >= 8
            && Math.max(Math.abs(var11.x3bqzqsu81ak1i()), Math.abs(var11.x17l8srdozs7l())) <= var6 - Math.max(var2, var3)) {
            int var12 = var6 - Math.max(var2, var3) - 4;
            if (var11.x3bqzqsu81ak1i() * var11.x3bqzqsu81ak1i() + var11.x17l8srdozs7l() * var11.x17l8srdozs7l() <= var12 * var12) {
               int var13 = var8.method_10263() + var11.x3bqzqsu81ak1i();
               int var14 = var8.method_10260() + var11.x17l8srdozs7l();
               if (x1710fugau4ahf(var0, var13, var14, var2, var3)) {
                  var var15 = x2lxftuktkskuu(var0, var1, var13, var14, var2, var3, var4);
                  if (var15.isPresent()) {
                     var var16 = xamz6pfj7qa87.x33m0vw9cimu8k(var0, var1, (class_2338)var15.get(), var2, var3);
                     if (!var16.isEmpty() && (var5 || x1e0m4zk94351d(var0, var1, (class_2338)var15.get(), var2, var3))) {
                        x27aao31y6r2ta.x15itjvs0tajaz(var0, var1, (class_2338)var15.get(), var2, var3);
                        if (var5) {
                           for (class_2338 var18 : var16.get()) {
                              if (!var1.x1n1a2vp01kxzf().contains(var18)) {
                                 var1.x1n1a2vp01kxzf().add(var18);
                              }
                           }
                        } else {
                           var1.x15dc4qxz6dpo3((List<class_2338>)var16.get());
                        }

                        var1.x2lr91dptxy1ww();
                        return var15;
                     }
                  }
               }
            }
         }
      }

      if (var1.x1eughlg5bm9gx() >= var7) {
         var1.x2lr91dptxy1ww();
      }

      return Optional.empty();
   }

   private static Optional<class_2338> x2lxftuktkskuu(class_3218 var0, x15kgoaq6i3m6i var1, int var2, int var3, int var4, int var5, int var6) {
      if (var1.x2vcibw4inamjw(new class_3341(var2, 0, var3, var2 + var4 - 1, 0, var3 + var5 - 1), 3)) {
         return Optional.empty();
      } else {
         for (int var7 = -2; var7 < var4 + 2; var7++) {
            for (int var8 = -2; var8 < var5 + 2; var8++) {
               class_2338 var9 = xamz6pfj7qa87.x3qthrkxcelgb7(var0, var2 + var7, var3 + var8);
               if (xamz6pfj7qa87.x3hqfrterusj2u(var0, var9)) {
                  return Optional.empty();
               }
            }
         }

         int var17 = Integer.MAX_VALUE;
         int var18 = Integer.MIN_VALUE;

         for (int var19 = 0; var19 < var4; var19++) {
            for (int var10 = 0; var10 < var5; var10++) {
               int var11 = x1zwog5kwmtqw8(var0, var2 + var19, var3 + var10);
               class_2338 var12 = new class_2338(var2 + var19, var11, var3 + var10);
               if (x20rghnsl2yowl.x239t1m3hfi74d(var0, var12)) {
                  return Optional.empty();
               }

               if (!xrjsytz39cbck(var0.method_8320(var12)) || var0.method_8320(var12.method_10084()).method_26204() instanceof class_2302) {
                  return Optional.empty();
               }

               var17 = Math.min(var17, var11);
               var18 = Math.max(var18, var11);
            }
         }

         if (var18 - var17 > 4) {
            return Optional.empty();
         } else {
            int var20 = (var17 + var18 + 1) / 2;
            if (var20 > var1.core().method_10264() + 16) {
               return Optional.empty();
            } else {
               class_2338 var21 = new class_2338(var2, var20, var3);
               class_3341 var22 = new class_3341(var2 - 4, var20 - 4, var3 - 4, var2 + var4 + 3, var20 + var6 + 2, var3 + var5 + 3);
               if (x3d5vk1zj3fvkj.get(var0).land().stream().anyMatch(var1x -> var1x.intersects(var22))) {
                  return Optional.empty();
               } else {
                  for (x2arpn38dclo1g var13 : var1.x1ja61emeln2hc()) {
                     if (var13.x32oz43hmeynhc() != x20r5v7z43p3d5.COMPLETE
                        && (var13.x32oz43hmeynhc() != x20r5v7z43p3d5.BLOCKED || var13.x2zq2smhbu3lzo() != 0L)
                        && var13.x24kbqv6g4kkrg().method_14657(var22)) {
                        return Optional.empty();
                     }
                  }

                  for (class_2338 var26 : var1.x31ow2tlg0dm5w()) {
                     if (var26.method_10264() >= var20 - 5
                        && var26.method_10264() <= var20 + var6 + 2
                        && var26.method_10263() >= var22.method_35415()
                        && var26.method_10263() <= var22.method_35418()
                        && var26.method_10260() >= var22.method_35417()
                        && var26.method_10260() <= var22.method_35420()) {
                        return Optional.empty();
                     }
                  }

                  for (int var25 = -1; var25 <= var4; var25++) {
                     for (int var27 = -1; var27 <= var5; var27++) {
                        int var14 = -4;

                        while (var14 <= var6 + 2) {
                           class_2338 var15 = var21.method_10069(var25, var14, var27);
                           class_2680 var16 = var0.method_8320(var15);
                           if (x3d5vk1zj3fvkj.get(var0).isPlayerBlock(var15)) {
                              return Optional.empty();
                           }

                           if (var0.method_8321(var15) == null && var0.method_8316(var15).method_15769()) {
                              if (x27aao31y6r2ta.x3hz8l3ppqisjl(var0, var15)
                                 || var16.method_26215()
                                 || var16.method_45474()
                                 || xrjsytz39cbck(var16)
                                 || var16.method_26164(class_3481.field_15503)
                                 || var16.method_26164(class_3481.field_15475) && x33tqnmmkvlc81(var0, var15)) {
                                 var14++;
                                 continue;
                              }

                              return Optional.empty();
                           }

                           return Optional.empty();
                        }
                     }
                  }

                  return x3sqkg3n6v972r.x2by5f9ehxvwk9().roles.builder_respects_player_builds && xwcjcz0wnbzkv(var0, var22)
                     ? Optional.empty()
                     : Optional.of(var21);
               }
            }
         }
      }
   }

   private static boolean xrjsytz39cbck(class_2680 var0) {
      return !var0.method_27852(class_2246.field_10194) && !var0.method_27852(class_2246.field_10362) && x1dxdlnevtfbhm.xroinv4hk6l7h(var0);
   }

   private static boolean x1e0m4zk94351d(class_3218 var0, x15kgoaq6i3m6i var1, class_2338 var2, int var3, int var4) {
      var var5 = var0.method_18023(
         class_1299.field_6077,
         x2n55y2tom37h2.x150ccxomrlozr(var0, var1),
         var1x -> var1x.method_5805() && !var1x.method_6109() && var1.roleOf(var1x.method_5667()) == x1rxiy9mtu5b3h.BUILDER
      );
      if (var5.isEmpty()) {
         return false;
      } else {
         var var6 = new HashSet<class_2338>();

         for (int[] var10 : new int[][]{{var3 / 2, -3}, {var3 / 2, var4 + 2}, {-3, var4 / 2}, {var3 + 2, var4 / 2}}) {
            int var11 = var2.method_10263() + var10[0];
            int var12 = var2.method_10260() + var10[1];
            class_2338 var13 = xamz6pfj7qa87.x3qthrkxcelgb7(var0, var11, var12).method_10084();
            if (x1c1r5c6a13gtl.x9bnd6e2jczhd(var0, var13)) {
               var6.add(var13);
            }
         }

         if (!var6.isEmpty() && x2n55y2tom37h2.x5ll9elqpk1a4(var0)) {
            class_1646 var17 = var5.stream().min(Comparator.comparingDouble(var1x -> var1x.method_24515().method_10262(var1.core()))).orElseThrow();
            var17.method_5942().method_70687(true);
            var17.method_5942().method_6354(true);
            var17.method_5942().method_61439(128.0F);
            var17.method_5942().method_23964(8.0F);

            class_11 var18;
            try {
               var18 = var17.method_5942().method_29934(var6, 0);
            } finally {
               var17.method_5942().method_23965();
            }

            return var18 != null && var18.method_21655();
         } else {
            return false;
         }
      }
   }

   private static int x1zwog5kwmtqw8(class_3218 var0, int var1, int var2) {
      int var3 = var0.method_8624(class_2903.field_13203, var1, var2) - 1;

      for (int var4 = 0; var4 < 16 && var3 > var0.method_31607(); var4++) {
         class_2338 var5 = new class_2338(var1, var3, var2);
         class_2680 var6 = var0.method_8320(var5);
         if (!x27aao31y6r2ta.x3hz8l3ppqisjl(var0, var5)
            && !var6.method_45474()
            && !var6.method_26164(class_3481.field_15503)
            && (!var6.method_26164(class_3481.field_15475) || !x33tqnmmkvlc81(var0, var5))) {
            break;
         }

         var3--;
      }

      return var3;
   }

   private static boolean x33tqnmmkvlc81(class_3218 var0, class_2338 var1) {
      if (x3d5vk1zj3fvkj.get(var0).isPlayerBlock(var1)) {
         return false;
      } else {
         for (class_2338 var3 : class_2338.method_10097(var1.method_10069(-3, -1, -3), var1.method_10069(3, 8, 3))) {
            if (var0.method_8320(var3).method_26164(class_3481.field_15503)) {
               return true;
            }
         }

         return false;
      }
   }

   private static boolean xwcjcz0wnbzkv(class_3218 var0, class_3341 var1) {
      int var2 = var1.method_35415() - 32;
      int var3 = var1.method_35418() + 32;
      int var4 = var1.method_35417() - 32;
      int var5 = var1.method_35420() + 32;

      for (int var6 = var2 >> 4; var6 <= var3 >> 4; var6++) {
         for (int var7 = var4 >> 4; var7 <= var5 >> 4; var7++) {
            if (var0.method_14178().method_12123(var6, var7)) {
               class_2818 var8 = var0.method_14178().method_21730(var6, var7);
               if (var8 != null) {
                  boolean var9 = var8.method_12214()
                     .entrySet()
                     .stream()
                     .anyMatch(
                        var5x -> {
                           class_2338 var6x = (class_2338)var5x.getKey();
                           return var6x.method_10263() >= var2
                              && var6x.method_10263() <= var3
                              && var6x.method_10260() >= var4
                              && var6x.method_10260() <= var5
                              && var0.method_8320(var6x).method_27852(class_2246.field_10327);
                        }
                     );
                  if (var9) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   private static boolean x1710fugau4ahf(class_3218 var0, int var1, int var2, int var3, int var4) {
      int var5 = var1 - 4 >> 4;
      int var6 = var1 + var3 + 3 >> 4;
      int var7 = var2 - 4 >> 4;
      int var8 = var2 + var4 + 3 >> 4;

      for (int var9 = var5; var9 <= var6; var9++) {
         for (int var10 = var7; var10 <= var8; var10++) {
            if (!var0.method_14178().method_12123(var9, var10)) {
               return false;
            }
         }
      }

      return true;
   }

   private static x4t5d0ytwq4ac xuu0zlh8kzgkl(int var0) {
      if (var0 == 0) {
         return new x4t5d0ytwq4ac(0, 0);
      } else {
         int var1 = (int)Math.ceil((Math.sqrt(var0 + 1) - 1.0) / 2.0);
         int var2 = var1 * 2;
         int var3 = (var1 * 2 + 1) * (var1 * 2 + 1) - 1;
         int var4 = var3 - var0;
         if (var4 < var2) {
            return new x4t5d0ytwq4ac(var1 - var4, -var1);
         } else if (var4 < var2 * 2) {
            return new x4t5d0ytwq4ac(-var1, -var1 + (var4 - var2));
         } else {
            return var4 < var2 * 3 ? new x4t5d0ytwq4ac(-var1 + (var4 - var2 * 2), var1) : new x4t5d0ytwq4ac(var1, var1 - (var4 - var2 * 3));
         }
      }
   }
}
