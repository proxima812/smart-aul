package net.smartcolonies.r;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.Map.Entry;
import java.util.function.Predicate;
import net.minecraft.class_1263;
import net.minecraft.class_1299;
import net.minecraft.class_1646;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_2818;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3489;
import net.minecraft.class_3730;
import net.minecraft.class_3852;
import net.minecraft.class_7477;
import net.minecraft.class_7893;
import net.minecraft.class_7923;
import net.minecraft.class_4153.class_4155;

public final class x1intnp6ax9q2k {
   private static final Map<x2arpn38dclo1g, List<x2xwxksqmx9pch>> x1m6javv76y909 = new WeakHashMap<>();

   private x1intnp6ax9q2k() {
   }

   public static void x2rwu2l29t4lpj() {
      x1m6javv76y909.clear();
   }

   public static void x3ij59k18pnfmd(class_3218 var0, x15kgoaq6i3m6i var1) {
      long var2 = var0.method_75260();
      long var4 = var1.x1t2gtpnwtd60z();
      if (var4 != var2) {
         if (var1.population() >= 2 || var1.x216l9aitzit41()) {
            boolean var6 = var0.method_37118(var1.core()) || var1.x3tnjjviw7kr77() >= var2 - 1L;
            if (var6 || var1.x216l9aitzit41() || var4 == Long.MIN_VALUE || var2 <= var4 || var2 - var4 >= 1200L || var1.x3p06s7riestqm() != 0L) {
               var1.x14t0iw6xpk0xx(var2);
               x3o0tuxsltwqoc var7 = x3sqkg3n6v972r.x2by5f9ehxvwk9().village;
               if (var4 == Long.MIN_VALUE) {
                  x2n55y2tom37h2.xchl1k1tnh3w8(var0).method_80();
               }

               if (var4 != Long.MIN_VALUE
                  && var2 > var4
                  && (var2 - var4 >= 1200L || var1.x216l9aitzit41() || var1.x3p06s7riestqm() != 0L)
                  && var7.offline_development
                  && x1dxdlnevtfbhm.x35ev8l9vc9uk7(var0)) {
                  long var8 = var2 - var4 < 1200L && !var1.x216l9aitzit41() && var6 ? 0L : var2 - var4;
                  long var10 = Math.min(var8 + var1.x3p06s7riestqm(), var7.offline_max_days * 24000L);
                  if (var10 > 0L) {
                     if (!var1.x216l9aitzit41() && xk3x156edihju(var0, var1, var10 / 24000.0)) {
                        var1.xtcqji4527aor(var10);
                        x2n55y2tom37h2.xchl1k1tnh3w8(var0).method_80();
                     } else {
                        var1.xtcqji4527aor(0L);
                        var1.x3vh0r7o2vtxxq(var10);
                        double var12 = var10 / 24000.0;
                        boolean var14 = var1.x216l9aitzit41();
                        if (var14) {
                           var1.x1nmmpj859zi63();
                        }

                        var1.xqdbjfmq8abgh(false);
                        int var15 = Math.max(var14 ? 8 : 2, var1.population());
                        int var16 = var1.x18two0xs8s6p9(x1rxiy9mtu5b3h.FARMER);
                        int var17 = var1.x18two0xs8s6p9(x1rxiy9mtu5b3h.FISHERMAN);
                        int var18 = var1.x18two0xs8s6p9(x1rxiy9mtu5b3h.RANCHER);
                        int var19 = var1.x18two0xs8s6p9(x1rxiy9mtu5b3h.LUMBERJACK);
                        int var20 = var1.x18two0xs8s6p9(x1rxiy9mtu5b3h.MINER);
                        int var21 = var1.x18two0xs8s6p9(x1rxiy9mtu5b3h.BUILDER);
                        if (var14) {
                           var16 = Math.max(1, var16);
                           var17 = Math.max(1, var17);
                           var18 = Math.max(1, var18);
                           var19 = Math.max(1, var19);
                           var20 = Math.max(1, var20);
                           var21 = Math.max(1, var21);
                        }

                        x2n55y2tom37h2.x2u0vsam1tcmhq(var0, var1);
                        double var22 = var12 * var7.development_speed * (var16 * 24 * 5 + var17 * 18 * 5 + var18 * 12 * 8);
                        double var24 = var12 * var15 * 10.0;
                        double var26 = Math.clamp((var1.cachedFood() + var22) / Math.max(1.0, var24), 0.0, 1.0);
                        int var28 = (int)Math.min((double)var1.cachedFood(), Math.ceil(Math.max(0.0, var24 - var22)));
                        if (var28 > 0) {
                           x2n55y2tom37h2.x2o11j2ko0ev05(var0, var1, var28);
                        }

                        double var29 = Math.max(0.0, var22 - var24) / Math.max(1.0, var22);
                        x1snasam03o544(var1, class_1802.field_8229, var12 * var7.development_speed * var16 * 24.0 * var29);
                        x1snasam03o544(var1, class_1802.field_8373, var12 * var7.development_speed * var17 * 18.0 * var29);
                        x1snasam03o544(var1, class_1802.field_8176, var12 * var7.development_speed * var18 * 12.0 * var29);
                        x1snasam03o544(var1, xzhi8fvcu7goa(var0, var1), var12 * var7.development_speed * var26 * var19 * 256.0);
                        x1snasam03o544(var1, class_1802.field_20412, var12 * var7.development_speed * var26 * var20 * 384.0);
                        x1snasam03o544(var1, class_1802.field_8620, var12 * var7.development_speed * var26 * var20 * 12.0);
                        x1snasam03o544(var1, class_1802.field_8713, var12 * var7.development_speed * var26 * var20 * 16.0);
                        x1snasam03o544(var1, class_1802.field_8477, var12 * var7.development_speed * var26 * var20 * 0.5);
                        boolean var31 = var26 >= 0.75;
                        if (var31 && var21 > 0 && var7.offline_max_days > 0) {
                           var1.x20lu0dxozk5g(var1.x275t1qstogox8(var12 * var7.development_speed * var26 * var21 * 1000.0));
                           var1.x23n2tdykuxd7a(var1.x2p9t1nvxo82zp() + var1.x3eazb9acbxmlj(var12 * var7.development_speed * var26 * var21));
                           x1yz1dkagfdrrb(var0, var1);
                        }

                        if (var31) {
                           var1.x1hehk3f3lrpr(var1.x3h8wqo6wk0f7p() + var1.x3eljz0p0x6yek(var12 * var7.development_speed * var26 * Math.max(1, var15 / 8)));
                        }

                        var1.xemejsnsuhts2(Long.MAX_VALUE);
                        x2n55y2tom37h2.xchl1k1tnh3w8(var0).method_80();
                     }
                  }
               }
            }
         }
      }
   }

   private static boolean xk3x156edihju(class_3218 var0, x15kgoaq6i3m6i var1, double var2) {
      double var4 = var2 * Math.max(2, var1.population()) * 10.0;
      double var6 = var2 * x3sqkg3n6v972r.x2by5f9ehxvwk9().village.development_speed
         * (
            var1.x18two0xs8s6p9(x1rxiy9mtu5b3h.FARMER) * 120
               + var1.x18two0xs8s6p9(x1rxiy9mtu5b3h.FISHERMAN) * 90
               + var1.x18two0xs8s6p9(x1rxiy9mtu5b3h.RANCHER) * 96
         );
      if (var6 >= var4) {
         return false;
      } else {
         boolean var8 = false;
         int var9 = 0;

         for (class_2338 var11 : var1.x2ad3s8aentmo4()) {
            class_2818 var12 = var0.method_14178().method_21730(var11.method_10263() >> 4, var11.method_10260() >> 4);
            if (var12 == null) {
               var8 = true;
            } else if (var12.method_8321(var11) instanceof class_1263 var13) {
               for (int var15 = 0; var15 < var13.method_5439(); var15++) {
                  var9 += x2n55y2tom37h2.xhahxgpa08yc9(var13.method_5438(var15)) * var13.method_5438(var15).method_7947();
               }
            }
         }

         return var8 && var9 + var6 < var4;
      }
   }

   public static void x1yz1dkagfdrrb(class_3218 var0, x15kgoaq6i3m6i var1) {
      if (var1.x2p9t1nvxo82zp() > 0 && var1.x11xljsmcmocmw() > 0) {
         int var2 = var1.x2p9t1nvxo82zp();

         for (x2arpn38dclo1g var4 : var1.x1ja61emeln2hc()) {
            if (var2 <= 0) {
               break;
            }

            if (!var4.x2nfrt8qxdscso().isPresent()
               && !var4.x2s8emavervqrz()
               && var4.x32oz43hmeynhc() != x20r5v7z43p3d5.COMPLETE
               && (var4.x32oz43hmeynhc() != x20r5v7z43p3d5.BLOCKED || var4.x2zq2smhbu3lzo() != 0L)) {
               var4.xkv5b17xl7vd6(true);
               var4.x3vu8mnvg0blk4().ifPresent(var1x -> {
                  if (var0.method_66347(var1x) instanceof class_1646 var2x) {
                     for (class_7893 var4x : List.copyOf(var2x.method_18868().method_27074())) {
                        if (var4x instanceof x2cvbyh5y5day0) {
                           var4x.method_18925(var0, var2x, var0.method_75260());
                        }
                     }
                  }
               });
               var4.x23bey8uos6gyt();
               var2--;
            }
         }
      }
   }

   private static class_1792 xzhi8fvcu7goa(class_3218 var0, x15kgoaq6i3m6i var1) {
      String var2 = var0.method_23753(var1.core()).method_40230().map(var0x -> var0x.method_29177().method_12832()).orElse("plains");
      return var2.contains("savanna")
         ? class_1802.field_8820
         : (!var2.contains("taiga") && !var2.contains("snow") ? class_1802.field_8583 : class_1802.field_8684);
   }

   private static void x1snasam03o544(x15kgoaq6i3m6i var0, class_1792 var1, double var2) {
      int var4 = var0.x1e0cfaky0ruvh(class_7923.field_41178.method_10221(var1), var2);
      if (var4 > 0) {
         var0.x1i0d1gs2hqpsr().merge(class_7923.field_41178.method_10221(var1), Math.min(4096, var4), (var0x, var1x) -> Math.min(4096, var0x + var1x));
      }
   }

   public static boolean xezn2zouajb9i(class_3218 var0, x15kgoaq6i3m6i var1, Predicate<class_1799> var2) {
      return var1.x1i0d1gs2hqpsr()
            .entrySet()
            .stream()
            .anyMatch(var1x -> var1x.getValue() > 0 && var2.test(new class_1799((class_1935)class_7923.field_41178.method_63535(var1x.getKey()))))
         || x2n55y2tom37h2.x1tl8iu49onump(var0, var1, var2);
   }

   public static boolean x31mf0b9fst14k(class_3218 var0, x15kgoaq6i3m6i var1, Predicate<class_1799> var2) {
      var var3 = var1.x1i0d1gs2hqpsr().entrySet().iterator();

      while (var3.hasNext()) {
         Entry var4 = (Entry)var3.next();
         if ((Integer)var4.getValue() > 0 && var2.test(new class_1799((class_1935)class_7923.field_41178.method_63535((class_2960)var4.getKey())))) {
            if ((Integer)var4.getValue() == 1) {
               var3.remove();
            } else {
               var4.setValue((Integer)var4.getValue() - 1);
            }

            return true;
         }
      }

      return x2n55y2tom37h2.x22hjebj4vmztt(var0, var1, var2, 1);
   }

   private static int x1fctwp4pjtanw(class_3218 var0, x15kgoaq6i3m6i var1, int var2) {
      int var3 = 0;
      int var4 = 0;
      var var5 = var1.x1n1a2vp01kxzf().iterator();

      while (var5.hasNext() && var3 < Math.min(32, var2) && var4++ < 64 && var1.x11xljsmcmocmw() > 0) {
         class_2338 var6 = (class_2338)var5.next();
         if (var0.method_14178().method_12123(var6.method_10263() >> 4, var6.method_10260() >> 4)) {
            if (!xamz6pfj7qa87.xxon284tpck85(var0, var1, var6)) {
               var5.remove();
            } else if (xamz6pfj7qa87.x3hqfrterusj2u(var0, var6)) {
               var5.remove();
            } else {
               Predicate<class_1799> var7 = var0x -> var0x.method_31573(class_3489.field_15539) || var0x.method_31573(class_3489.field_25808);
               if (!xezn2zouajb9i(var0, var1, var7)) {
                  break;
               }

               class_2680 var8 = var0.method_8320(var6);
               if (x1dxdlnevtfbhm.x1bq0f8n53gtob(var0, var6, class_2246.field_10194.method_9564(), 3)) {
                  if (!x31mf0b9fst14k(var0, var1, var7)) {
                     xyghu7rojfjwk.x2aebumvi646n9(() -> var0.method_8652(var6, var8, 3));
                     break;
                  }

                  var1.x3g65nuli3tuzl();
                  var5.remove();
                  var3++;
               }
            }
         }
      }

      if (var4 > 0) {
         x2n55y2tom37h2.xchl1k1tnh3w8(var0).method_80();
      }

      return var3;
   }

   public static int x12vivbc3rlygx(class_3218 var0, x15kgoaq6i3m6i var1, int var2) {
      if (!x1dxdlnevtfbhm.x35ev8l9vc9uk7(var0)) {
         return 0;
      } else if (!x3sqkg3n6v972r.x2by5f9ehxvwk9().village.offline_development) {
         return 0;
      } else {
         if (!var1.x1i0d1gs2hqpsr().isEmpty()
            && var1.x2ad3s8aentmo4()
               .stream()
               .noneMatch(
                  var1x -> var0.method_14178().method_12123(var1x.method_10263() >> 4, var1x.method_10260() >> 4)
                     && var0.method_8321(var1x) instanceof class_1263
               )) {
            x2n55y2tom37h2.x2l4eb2ibsf118(var0, var1).ifPresent(var2x -> {
               if (x2n55y2tom37h2.xt55z8grtuvpq(var0, var1, var2x, x2jj5edkiu5orf.GENERAL)) {
                  var1.x1z3izo4s1trbu();
                  x2n55y2tom37h2.xchl1k1tnh3w8(var0).method_80();
               }
            });
         }

         if (var1.x2p9t1nvxo82zp() > 0 && var1.x11xljsmcmocmw() > 0 && var2 > 0) {
            x1yz1dkagfdrrb(var0, var1);
            x2n55y2tom37h2.x32npq1dyt2s4h(var0, var1);
         }

         boolean var3 = var1.x1ja61emeln2hc().stream().anyMatch(var0x -> var0x.x3fgv5ft5sp7zt() && var0x.x32oz43hmeynhc() != x20r5v7z43p3d5.COMPLETE);
         if (var0.method_75260() % 5L == 0L && !var1.x1i0d1gs2hqpsr().isEmpty()) {
            int var4 = 0;
            var var5 = var1.x1i0d1gs2hqpsr().entrySet().iterator();

            while (var5.hasNext() && var4++ < 16) {
               Entry var6 = (Entry)var5.next();
               class_1792 var7 = (class_1792)class_7923.field_41178.method_63535((class_2960)var6.getKey());
               if (!var3 || !new class_1799(var7).method_31573(class_3489.field_15539) && !new class_1799(var7).method_31573(class_3489.field_25808)) {
                  int var8 = Math.min((Integer)var6.getValue(), var7.method_7882());
                  class_1799 var9 = new class_1799(var7, var8);
                  x2n55y2tom37h2.x2cl1crde2rnxy(var0, var1, var9);
                  int var10 = var8 - var9.method_7947();
                  if (var10 > 0) {
                     var6.setValue((Integer)var6.getValue() - var10);
                     x2n55y2tom37h2.xchl1k1tnh3w8(var0).method_80();
                  }

                  if ((Integer)var6.getValue() <= 0) {
                     var5.remove();
                  }
               }
            }
         }

         if (var1.x3h8wqo6wk0f7p() > 0 && var0.method_75260() % 10L == 0L && var0.method_37118(var1.core())) {
            x25bgjce4mxb68(var0, var1);
         }

         x2arpn38dclo1g var12 = var1.x1ja61emeln2hc()
            .stream()
            .filter(var0x -> var0x.x3fgv5ft5sp7zt() && var0x.x32oz43hmeynhc() != x20r5v7z43p3d5.COMPLETE)
            .findFirst()
            .orElse(null);
         int var13 = var1.x2p9t1nvxo82zp() > 0 ? x1fctwp4pjtanw(var0, var1, var2) : 0;
         var2 -= var13;
         if (var12 == null || var2 <= 0) {
            return var13;
         } else if (var1.x11xljsmcmocmw() <= 0) {
            var12.xkv5b17xl7vd6(false);
            return var13;
         } else {
            var var14 = x1m6javv76y909.computeIfAbsent(var12, var1x -> x2cvbyh5y5day0.loadPlan(var0, var1x).orElse(List.of()));
            if (var14.isEmpty()) {
               var12.xkv5b17xl7vd6(false);
               x1m6javv76y909.remove(var12);
               return var13;
            } else {
               int var15 = 0;

               for (int var16 = 0; var16 < 16 && var15 < var2 && var12.x32oz43hmeynhc() != x20r5v7z43p3d5.COMPLETE; var16++) {
                  int var17 = var12.xmu75rcopbjnh();
                  var15 += x2cvbyh5y5day0.catchupStep(var0, var1, var12, var14, var2 - var15);
                  if (var12.xmu75rcopbjnh() == var17) {
                     break;
                  }
               }

               if (var12.x32oz43hmeynhc() == x20r5v7z43p3d5.COMPLETE) {
                  var12.xkv5b17xl7vd6(false);
                  x1m6javv76y909.remove(var12);
                  var1.x23n2tdykuxd7a(var1.x2p9t1nvxo82zp() - 1);
                  x2n55y2tom37h2.xchl1k1tnh3w8(var0).method_80();
               }

               return var15 + var13;
            }
         }
      }
   }

   private static void x25bgjce4mxb68(class_3218 var0, x15kgoaq6i3m6i var1) {
      var var2 = var0.method_18023(class_1299.field_6077, x2n55y2tom37h2.x150ccxomrlozr(var0, var1), var0x -> var0x.method_5805());
      int var3 = x3sqkg3n6v972r.x2by5f9ehxvwk9().village.max_population_per_bell;
      int var4 = (int)var0.method_19494().method_20252(var0x -> var0x.method_40225(class_7477.field_39291), var1.core(), var1.radius(), class_4155.field_18489);
      if (var2.size() < Math.min(var3, var4) && var2.size() >= 2) {
         class_2338 var5 = x2n55y2tom37h2.xcmgrg6f6maj1(var0, var1, var2.size()).filter(var1x -> !x2uh56vnzc4u51.x3gqyq7yie9jxr(var0, var1x)).orElse(null);
         if (var5 != null) {
            class_1646 var6 = (class_1646)class_1299.field_6077.method_5883(var0, class_3730.field_16466);
            if (var6 != null) {
               var6.method_5814(var5.method_10263() + 0.5, var5.method_10264(), var5.method_10260() + 0.5);
               var6.method_5614(-DevelopmentPolicy.delay(1800, x3sqkg3n6v972r.x2by5f9ehxvwk9().village.development_speed));
               var6.method_5971();
               var6.method_7195(((class_1646)var2.getFirst()).method_7231().method_66688(var0.method_30349(), class_3852.field_17051));
               if (x2n55y2tom37h2.x38ipozok9mc0x(var0, var1, () -> x2n55y2tom37h2.x2o11j2ko0ev05(var0, var1, 24) && var0.method_8649(var6), List.of())) {
                  var1.x36xpmo21hmp9h(var6.method_5667(), x1rxiy9mtu5b3h.CITIZEN);
                  var1.x1hehk3f3lrpr(var1.x3h8wqo6wk0f7p() - 1);
                  x2n55y2tom37h2.xchl1k1tnh3w8(var0).method_80();
               }
            }
         }
      }
   }

   public static String x2ypihddkgbmhu(x15kgoaq6i3m6i var0) {
      return "уровень="
         + var0.x3sjn9mskj5v9f()
         + ", учтено отсутствия="
         + var0.x2ya2lfuvm01ea() / 20L
         + " с, запас на выдачу="
         + var0.x1i0d1gs2hqpsr().values().stream().mapToInt(Integer::intValue).sum()
         + ", труд="
         + var0.x11xljsmcmocmw()
         + ", домов к достройке="
         + var0.x2p9t1nvxo82zp()
         + ", прирост="
         + var0.x3h8wqo6wk0f7p();
   }

   public static String x1rq82fkez3pr7(class_3218 var0, x15kgoaq6i3m6i var1) {
      x2arpn38dclo1g var2 = var1.x1ja61emeln2hc()
         .stream()
         .filter(var0x -> var0x.x3fgv5ft5sp7zt() && var0x.x32oz43hmeynhc() != x20r5v7z43p3d5.COMPLETE)
         .findFirst()
         .orElse(null);
      String var3 = var2 == null
         ? x2n55y2tom37h2.x2y69zyuty3rxs(var0, var1)
         : var2.x3ij6gescvakv2().method_23854() + " " + var2.xmu75rcopbjnh() + "/" + var2.xxjwqetbgzzle();
      return x2ypihddkgbmhu(var1)
         + "; склады="
         + var1.x2ad3s8aentmo4().size()
         + ", свободных слотов="
         + x2n55y2tom37h2.x11dwzrdveisv7(var0, var1)
         + "; еда/брёвна/камень="
         + var1.cachedFood()
         + "/"
         + var1.cachedLogs()
         + "/"
         + var1.cachedStone()
         + "; стройка="
         + var3
         + "; учтено до тика="
         + var1.x1t2gtpnwtd60z()
         + "; ожидает данных склада="
         + var1.x3p06s7riestqm() / 20L
         + " с";
   }
}
