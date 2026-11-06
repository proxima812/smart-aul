package net.smartcolonies.r;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.WeakHashMap;
import net.minecraft.class_1268;
import net.minecraft.class_1299;
import net.minecraft.class_1646;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1923;
import net.minecraft.class_2244;
import net.minecraft.class_2246;
import net.minecraft.class_2281;
import net.minecraft.class_2323;
import net.minecraft.class_2338;
import net.minecraft.class_2482;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2510;
import net.minecraft.class_2561;
import net.minecraft.class_2680;
import net.minecraft.class_2682;
import net.minecraft.class_3218;
import net.minecraft.class_3341;
import net.minecraft.class_3708;
import net.minecraft.class_3781;
import net.minecraft.class_3790;
import net.minecraft.class_9279;
import net.minecraft.class_9290;
import net.minecraft.class_9334;
import net.minecraft.class_2902.class_2903;

public final class x6amcj7vm0jm5 {
   public static final int x3hucludc7erve = 8;
   private static final String x2w7cyo0bvcpzm = "smart_colonies_land";
   private static final Map<x3d5vk1zj3fvkj, Map<UUID, x3rldzj1ugn0ma>> x1qp0aad0xefh = new WeakHashMap<>();

   private x6amcj7vm0jm5() {
   }

   private static Map<UUID, x3rldzj1ugn0ma> xt33a2rbozpnr(x3d5vk1zj3fvkj var0) {
      return x1qp0aad0xefh.computeIfAbsent(var0, var0x -> new HashMap<>());
   }

   public static class_1799 x31zhh2yvui44c(class_3218 var0, x15kgoaq6i3m6i var1) {
      class_1799 var2 = new class_1799(class_1802.field_8407);
      class_2487 var3 = new class_2487();
      var3.method_10556("smart_colonies_land", true);
      var3.method_10582("colony", var1.id().toString());
      var3.method_10582("dimension", var0.method_27983().method_29177().toString());
      var2.method_57379(class_9334.field_49628, class_9279.method_57456(var3));
      var2.method_57379(class_9334.field_49631, class_2561.method_43471("item.smart_colonies.land_deed"));
      var2.method_57379(class_9334.field_49632, new class_9290(List.of(class_2561.method_43469("item.smart_colonies.land_deed.use", new Object[]{17, 17}))));
      return var2;
   }

   public static boolean x3ew3lrc2q3oxu(class_1799 var0) {
      class_9279 var1 = (class_9279)var0.method_58694(class_9334.field_49628);
      return var0.method_31574(class_1802.field_8407) && var1 != null && var1.method_57461().method_68566("smart_colonies_land", false);
   }

   public static boolean x1mzhkk1en9rl(class_3218 var0, class_1657 var1, class_1268 var2, class_2338 var3) {
      class_1799 var4 = var1.method_5998(var2);
      if (!x3ew3lrc2q3oxu(var4)) {
         return false;
      } else {
         class_2487 var5 = ((class_9279)var4.method_58694(class_9334.field_49628)).method_57461();
         if (var5.method_68566("used", false)) {
            var1.method_7353(class_2561.method_43471("item.smart_colonies.land_deed.used"), false);
            return false;
         } else {
            x3d5vk1zj3fvkj var6 = x3d5vk1zj3fvkj.get(var0);

            x15kgoaq6i3m6i var7;
            try {
               var7 = var6.byId(UUID.fromString(var5.method_68564("colony", ""))).orElse(null);
            } catch (IllegalArgumentException var9) {
               return false;
            }

            if (var7 != null
               && var0.method_27983().method_29177().toString().equals(var5.method_68564("dimension", ""))
               && xboirziij2fbq(var0, var7, var1.method_5667(), var3)) {
               var6.addLand(new x3o14q3v4oy99m(var1.method_5667(), var7.id(), var3, 8));
               xt33a2rbozpnr(var6).remove(var1.method_5667());
               class_1799 var8 = var4.method_46651(1);
               var5.method_10556("used", true);
               var5.method_10544("center", var3.method_10063());
               var8.method_57379(class_9334.field_49628, class_9279.method_57456(var5));
               var8.method_57379(class_9334.field_49631, class_2561.method_43469("item.smart_colonies.land_deed.complete", new Object[]{var3.method_23854()}));
               var4.method_7934(1);
               var1.method_6122(var2, var8);
               if (!var4.method_7960() && !var1.method_31548().method_7394(var4)) {
                  var1.method_7328(var4, false);
               }

               x2uh56vnzc4u51.x34rm129tews28(var0);
               return true;
            } else {
               var1.method_7353(class_2561.method_43471("message.smart_colonies.land_unavailable"), false);
               return false;
            }
         }
      }
   }

   public static boolean xboirziij2fbq(class_3218 var0, x15kgoaq6i3m6i var1, UUID var2, class_2338 var3) {
      if (Math.hypot(var3.method_10263() - var1.core().method_10263(), var3.method_10260() - var1.core().method_10260()) + 11.36 > var1.radius()) {
         return false;
      } else if (Math.max(Math.abs(var3.method_10263() - var1.core().method_10263()), Math.abs(var3.method_10260() - var1.core().method_10260())) < 14) {
         return false;
      } else {
         x3o14q3v4oy99m var4 = new x3o14q3v4oy99m(var2, var1.id(), var3, 8);
         if (x3d5vk1zj3fvkj.get(var0).foreignBlocks(var4)) {
            return false;
         } else if (x3d5vk1zj3fvkj.get(var0)
            .land()
            .stream()
            .anyMatch(
               var1x -> Math.abs(var1x.x2kystgxl68xrn().method_10263() - var3.method_10263()) <= var1x.x3rgd0i6cz19eu() + 8
                  && Math.abs(var1x.x2kystgxl68xrn().method_10260() - var3.method_10260()) <= var1x.x3rgd0i6cz19eu() + 8
            )) {
            return false;
         } else {
            for (x15kgoaq6i3m6i var6 : x3d5vk1zj3fvkj.get(var0).colonies()) {
               if (var6.x1ja61emeln2hc().stream().anyMatch(var1x -> var4.intersects(var1x.x24kbqv6g4kkrg()))) {
                  return false;
               }
            }

            for (int var11 = -8; var11 <= 8; var11++) {
               for (int var12 = -8; var12 <= 8; var12++) {
                  class_2338 var7 = var3.method_10069(var11, 0, var12);
                  if (!var0.method_8621().method_11952(var7)) {
                     return false;
                  }

                  if (!var0.method_14178().method_12123(var7.method_10263() >> 4, var7.method_10260() >> 4)) {
                     return false;
                  }

                  int var8 = var0.method_8624(class_2903.field_13203, var7.method_10263(), var7.method_10260()) - 1;
                  var7 = new class_2338(var7.method_10263(), var8, var7.method_10260());
                  if (x1ajta1wleukjn(var0, var7)
                     || var1.x29j0o9zdxri2().containsKey(var7)
                     || var0.method_8320(var7).method_27852(class_2246.field_10362)
                     || var0.method_8320(var7).method_27852(class_2246.field_16332)
                     || xamz6pfj7qa87.x3hqfrterusj2u(var0, var7)) {
                     return false;
                  }

                  for (class_2338 var10 : class_2338.method_10097(var7.method_10087(8), var7.method_10086(8))) {
                     if (x3d5vk1zj3fvkj.get(var0).blockOwner(var10).filter(var1x -> !var1x.equals(var2)).isPresent() || var1.x29j0o9zdxri2().containsKey(var10)
                        )
                      {
                        return false;
                     }
                  }
               }
            }

            return true;
         }
      }
   }

   private static boolean x1ajta1wleukjn(class_3218 var0, class_2338 var1) {
      return var0.method_27056()
         .method_41035(new class_1923(var1), var0x -> true)
         .stream()
         .flatMap(var0x -> var0x.method_14963().stream())
         .anyMatch(
            var1x -> {
               class_3341 var2 = var1x.method_14935();
               if (var1x instanceof class_3790 var3
                  && var1.method_10263() >= var2.method_35415()
                  && var1.method_10263() <= var2.method_35418()
                  && var1.method_10260() >= var2.method_35417()
                  && var1.method_10260() <= var2.method_35420()
                  && var3.method_16644() instanceof class_3781 var4) {
                  try {
                     String var7 = var4.method_67689().method_12832();
                     return var7.startsWith("village/")
                        && (var7.contains("/houses/") || var7.contains("/town_centers/") || var7.contains("/streets/") || var7.contains("meeting_point"));
                  } catch (IllegalStateException var6) {
                     return false;
                  }
               } else {
                  return false;
               }
            }
         );
   }

   public static boolean x34sw32fdrc9v3(class_2680 var0) {
      return var0.method_26234(class_2682.field_12294, class_2338.field_10980)
         || var0.method_26204() instanceof class_2323
         || var0.method_26204() instanceof class_2244
         || var0.method_26204() instanceof class_2281
         || var0.method_26204() instanceof class_3708
         || var0.method_26204() instanceof class_2510
         || var0.method_26204() instanceof class_2482;
   }

   public static void x273xo57gqmrz9(class_3218 var0, class_1657 var1, class_2338 var2, class_2680 var3) {
      if (!var1.method_68878() && !var1.method_7325() && x34sw32fdrc9v3(var3) && !x2uh56vnzc4u51.xlzfxuhnckux8(var0, var1.method_5667(), var2)) {
         x15kgoaq6i3m6i var4 = x2n55y2tom37h2.x3psqezd5jem54(var0, var2).orElse(null);
         if (var4 != null) {
            if (!(var2.method_10262(var4.core()) > (double)Math.min(96, var4.radius()) * Math.min(96, var4.radius()))) {
               x3d5vk1zj3fvkj var5 = x3d5vk1zj3fvkj.get(var0);
               x3rldzj1ugn0ma var6 = xt33a2rbozpnr(var5).get(var1.method_5667());
               if (var6 != null && var6.x3r0wyaaw6oa4f.equals(var4.id()) && (var6.xba2llgzzadr8 < 0L || var0.method_75260() - var6.xba2llgzzadr8 <= 24000L)) {
                  var6.x19bsssr8009ew = var2.method_10062();
                  if (var6.xba2llgzzadr8 >= 0L && var0.method_75260() - var6.xba2llgzzadr8 >= 600L) {
                     var6.x3o0ypcqec9o4n++;
                     if (var6.x3o0ypcqec9o4n >= 3) {
                        var6.x2z4teazzptlwj = true;
                        x37idyr79ed75m.xgnwo32dqwtgi(var0, var4, var1, 4);
                     }
                  }

                  var5.method_80();
               } else {
                  xt33a2rbozpnr(var5).put(var1.method_5667(), new x3rldzj1ugn0ma(var1.method_5667(), var4.id(), var2));
                  var5.method_80();
               }
            }
         }
      }
   }

   public static void x1w4hwyk1suofz(class_3218 var0) {
      if (var0.method_75260() % 20L == 0L) {
         x3d5vk1zj3fvkj var1 = x3d5vk1zj3fvkj.get(var0);
         if (Math.floorMod(var0.method_8532(), 24000L) < 12000L) {
            for (x3rldzj1ugn0ma var3 : new ArrayList<>(xt33a2rbozpnr(var1).values())) {
               if (var3.xba2llgzzadr8 < 0L) {
                  class_1657 var4 = var0.method_18470(var3.x118py1lrhxh0v);
                  x15kgoaq6i3m6i var5 = var1.byId(var3.x3r0wyaaw6oa4f).orElse(null);
                  if (var4 != null && var5 != null && !var4.method_7325()) {
                     class_1646 var6 = (class_1646)var0.method_18023(
                           class_1299.field_6077,
                           x2n55y2tom37h2.x150ccxomrlozr(var0, var5),
                           var1x -> var1x.method_5805()
                              && !var1x.method_6109()
                              && !var1x.method_6113()
                              && !var1x.method_18009()
                              && var5.roleOf(var1x.method_5667()) == x1rxiy9mtu5b3h.ELDER
                        )
                        .stream()
                        .findFirst()
                        .orElse(null);
                     if (var6 != null) {
                        if (var6.method_5858(var4) > 16.0) {
                           x1c1r5c6a13gtl.x2wzylgizxltef(var0, var6, var4.method_24515(), 0.9);
                        } else {
                           class_1799 var7 = new class_1799(class_1802.field_8407);
                           var7.method_57379(class_9334.field_49631, class_2561.method_43471("item.smart_colonies.land_notice"));
                           var7.method_57379(
                              class_9334.field_49632,
                              new class_9290(
                                 List.of(
                                    class_2561.method_43469(
                                       "item.smart_colonies.land_notice.price", new Object[]{x1a5rxjbs73iva.x2vr85xzgtiacc(var5, 24, true)}
                                    ),
                                    class_2561.method_43471("item.smart_colonies.land_notice.warning")
                                 )
                              )
                           );
                           if (!var4.method_31548().method_7394(var7)) {
                              var4.method_7328(var7, false);
                           }

                           var4.method_7353(class_2561.method_43471("item.smart_colonies.land_notice.warning"), false);
                           var3.xba2llgzzadr8 = var0.method_75260();
                           var1.method_80();
                           x1c1r5c6a13gtl.x2fxoqmbs0henb(var6);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public static boolean x20vp2osbvc2gk(class_3218 var0, class_1646 var1, x15kgoaq6i3m6i var2) {
      if (var2.roleOf(var1.method_5667()) == x1rxiy9mtu5b3h.ELDER
         && !var1.method_6113()
         && !var1.method_18009()
         && Math.floorMod(var0.method_8532(), 24000L) < 12000L
         && (var2.x3klfyffbbfab2() == Long.MIN_VALUE || var0.method_75260() - var2.x3klfyffbbfab2() >= 200L)) {
         class_1657 var3 = xt33a2rbozpnr(x3d5vk1zj3fvkj.get(var0))
            .values()
            .stream()
            .filter(var1x -> var1x.x3r0wyaaw6oa4f.equals(var2.id()) && var1x.xba2llgzzadr8 < 0L)
            .map(var1x -> var0.method_18470(var1x.x118py1lrhxh0v))
            .filter(Objects::nonNull)
            .filter(var1x -> !var1x.method_7325() && var1x.method_24515().method_10262(var2.core()) <= (double)var2.radius() * var2.radius())
            .min(Comparator.comparingDouble(var1::method_5858))
            .orElse(null);
         if (var3 == null) {
            return false;
         } else {
            var1.method_18868().method_24526(xnssk2qgda4r6.x12qpuae7zsr5m);
            if (var1.method_5858(var3) > 16.0) {
               x1c1r5c6a13gtl.x2wzylgizxltef(var0, var1, var3.method_24515(), 0.9);
            } else {
               x1c1r5c6a13gtl.x2fxoqmbs0henb(var1);
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public static void x2xqw0zvyjpw3b(class_2487 var0, x3d5vk1zj3fvkj var1) {
      class_2499 var2 = new class_2499();
      xt33a2rbozpnr(var1).values().forEach(var1x -> {
         class_2487 var2x = new class_2487();
         var2x.method_10582("player", var1x.x118py1lrhxh0v.toString());
         var2x.method_10582("colony", var1x.x3r0wyaaw6oa4f.toString());
         var2x.method_10544("pos", var1x.x19bsssr8009ew.method_10063());
         var2x.method_10544("delivered", var1x.xba2llgzzadr8);
         var2x.method_10569("continued", var1x.x3o0ypcqec9o4n);
         var2x.method_10556("punished", var1x.x2z4teazzptlwj);
         var2.add(var2x);
      });
      var0.method_10566("land_warnings", var2);
   }

   public static void x1vdpt6b5sksjj(class_2487 var0, x3d5vk1zj3fvkj var1) {
      class_2499 var2 = var0.method_68569("land_warnings");

      for (int var3 = 0; var3 < var2.size(); var3++) {
         try {
            class_2487 var4 = var2.method_68582(var3);
            x3rldzj1ugn0ma var5 = new x3rldzj1ugn0ma(
               UUID.fromString(var4.method_68564("player", "")),
               UUID.fromString(var4.method_68564("colony", "")),
               class_2338.method_10092(var4.method_68080("pos", 0L))
            );
            var5.xba2llgzzadr8 = var4.method_68080("delivered", -1L);
            var5.x3o0ypcqec9o4n = Math.clamp((long)var4.method_68083("continued", 0), 0, 3);
            var5.x2z4teazzptlwj = var4.method_68566("punished", false);
            xt33a2rbozpnr(var1).put(var5.x118py1lrhxh0v, var5);
         } catch (IllegalArgumentException var6) {
         }
      }
   }
}
