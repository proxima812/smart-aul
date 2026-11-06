package net.smartcolonies.r;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.WeakHashMap;
import net.minecraft.class_11;
import net.minecraft.class_1268;
import net.minecraft.class_1309;
import net.minecraft.class_1646;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2398;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3341;
import net.minecraft.class_3481;
import net.minecraft.class_4140;
import net.minecraft.class_7923;
import net.minecraft.class_2350.class_2353;
import net.minecraft.class_2902.class_2903;

public final class x35kkrl6uz1e8h {
   private static final Map<class_1646, x2cu91tum5yd6h> x25u0cyk1y45um = Collections.synchronizedMap(new WeakHashMap<>());
   private static final Map<class_1646, Long> x237zvjlldjb5z = Collections.synchronizedMap(new WeakHashMap<>());
   private static final Map<class_1646, x102ju4l0cty5k> xeksvy84118bm = Collections.synchronizedMap(new WeakHashMap<>());
   private static final int x2zxyof023qhq1 = 100;
   private static final int x1kadx2a48avi2 = 60;
   private static final int x1h0gaextn519b = 360;

   private x35kkrl6uz1e8h() {
   }

   public static void x1feket2ghytw5(class_1646 var0, long var1) {
      x237zvjlldjb5z.put(var0, var1);
   }

   public static void x8pl4kh8avnpr(class_3218 var0, x15kgoaq6i3m6i var1, class_1646 var2) {
      if ((var0.method_75260() + var2.method_5628()) % 20L == 0L) {
         if (x3u53699ao0yas(var2, var0.method_75260())) {
            x25u0cyk1y45um.remove(var2);
         } else if (var1.roleOf(var2.method_5667()) == x1rxiy9mtu5b3h.GUARD
            && var2.method_18868().method_18904(xxao083dbo7ag.x14b5znl8ndblc).isPresent()) {
            x25u0cyk1y45um.remove(var2);
         } else {
            long var3 = Math.floorMod(var0.method_8532(), 24000L);
            if (var2.method_6113() || var2.method_18009() || var3 >= 9000L) {
               x25u0cyk1y45um.remove(var2);
               xeksvy84118bm.remove(var2);
            } else if (x20lc7jle5mw9c.x1fhuggzt16ek2(var2, var0.method_75260())) {
               x25u0cyk1y45um.remove(var2);
               xeksvy84118bm.remove(var2);
            } else if (x102ui8nytk53n(var0, var2)) {
               x25u0cyk1y45um.remove(var2);
            } else if (x1c1r5c6a13gtl.x1f6ghn62xaxti(var2, var0.method_75260())) {
               if (x1sixoyijd0z9u(var0, var1, var2)) {
                  x25u0cyk1y45um.remove(var2);
               }
            } else {
               class_2338 var5 = x1om0jz0ba9i8l(var2);
               if (var5 != null && !(var2.method_24515().method_10262(var5) <= 9.0)) {
                  double var6 = var2.method_24515().method_10262(var5);
                  class_243 var8 = var2.method_73189();
                  x2cu91tum5yd6h var9 = x25u0cyk1y45um.get(var2);
                  if (var9 != null && !(var9.x3hblxrvsbx0pw().method_10262(var5) > 4.0)) {
                     boolean var10 = var9.x1zfnlcsuk3t1i().method_1025(var8) >= 0.16;
                     boolean var11 = var6 + 1.0 < var9.xwwyae897rr8r();
                     if (!var10 && !var11) {
                        long var12 = var0.method_75260() - var9.x38qwv32hf8w5w();
                        boolean var14 = !var2.method_24828()
                           && var0.method_75260() - var9.x2sysqaosnmrak() >= 100L
                           && !x3smw0c29vok53(var0, var2.method_24515(), 12);
                        if (!var14 && (var12 < 360L || !var2.method_5942().method_6357() && var9.x3d25jiqyb27mb() < 3)) {
                           if (var12 >= 100L && var0.method_75260() - var9.x16yow35qysf17() >= 60L) {
                              class_2338 var15 = var2.method_24515();
                              int var16 = var5.method_10263() - var15.method_10263();
                              int var17 = var5.method_10260() - var15.method_10260();
                              class_2350 var18 = var16 == 0 && var17 == 0
                                 ? class_2350.field_11043
                                 : (
                                    Math.abs(var16) >= Math.abs(var17)
                                       ? (var16 > 0 ? class_2350.field_11034 : class_2350.field_11039)
                                       : (var17 > 0 ? class_2350.field_11035 : class_2350.field_11043)
                                 );
                              int var19 = 0;
                              if (var16 == 0 && var17 == 0) {
                                 for (class_2350 var21 : class_2353.field_11062) {
                                    var19 = x2g2khbsuvhzto(var0, var1, var2, var15, var5, var21, var9.x26xkfgjum6mf6());
                                    if (var19 != 0) {
                                       var18 = var21;
                                       break;
                                    }
                                 }
                              } else if (var9.x3d25jiqyb27mb() % 2 == 0) {
                                 var19 = x2g2khbsuvhzto(var0, var1, var2, var15, var5, var18, var9.x26xkfgjum6mf6());
                              }

                              if (var19 == 0 && xahx1liq9jvh7(var0, var1, var2, var15, var5, var18)) {
                                 var19 = 1;
                              }

                              if (var19 == 0) {
                                 var19 = x2g2khbsuvhzto(var0, var1, var2, var15, var5, var18, var9.x26xkfgjum6mf6());
                              }

                              if (var19 == 2) {
                                 var2.method_6104(class_1268.field_5808);
                              }

                              x25u0cyk1y45um.put(
                                 var2,
                                 new x2cu91tum5yd6h(
                                    var5.method_10062(),
                                    var9.x1zfnlcsuk3t1i(),
                                    var9.x2sysqaosnmrak(),
                                    var9.x38qwv32hf8w5w(),
                                    var9.x1urgnoe13hqro(),
                                    var0.method_75260(),
                                    var9.x3d25jiqyb27mb() + 1,
                                    var9.x26xkfgjum6mf6() + (var19 == 2 ? 1 : 0),
                                    Math.min(var6, var9.xwwyae897rr8r())
                                 )
                              );
                           }
                        } else {
                           if (x1sixoyijd0z9u(var0, var1, var2)) {
                              x25u0cyk1y45um.remove(var2);
                           }
                        }
                     } else {
                        x25u0cyk1y45um.put(
                           var2,
                           new x2cu91tum5yd6h(
                              var5.method_10062(),
                              var8,
                              var9.x2sysqaosnmrak(),
                              var10 ? var0.method_75260() : var9.x38qwv32hf8w5w(),
                              var11 ? var0.method_75260() : var9.x1urgnoe13hqro(),
                              var9.x16yow35qysf17(),
                              0,
                              0,
                              Math.min(var6, var9.xwwyae897rr8r())
                           )
                        );
                     }
                  } else {
                     x25u0cyk1y45um.put(
                        var2,
                        new x2cu91tum5yd6h(
                           var5.method_10062(), var8, var0.method_75260(), var0.method_75260(), var0.method_75260(), var0.method_75260(), 0, 0, var6
                        )
                     );
                  }
               } else {
                  x25u0cyk1y45um.remove(var2);
               }
            }
         }
      }
   }

   public static boolean x3u53699ao0yas(class_1646 var0, long var1) {
      Long var3 = x237zvjlldjb5z.get(var0);
      if (var3 == null) {
         return false;
      } else if (var1 < var3) {
         return true;
      } else {
         x237zvjlldjb5z.remove(var0);
         return false;
      }
   }

   private static boolean x102ui8nytk53n(class_3218 var0, class_1646 var1) {
      if (!var1.method_24828()) {
         xeksvy84118bm.remove(var1);
         return false;
      } else {
         class_2338 var2 = var1.method_24515();

         for (class_2350 var4 : class_2353.field_11062) {
            if (x147zp9ypin2wg(var0, var2.method_10093(var4))) {
               xeksvy84118bm.remove(var1);
               return false;
            }
         }

         class_2338 var9 = null;

         label74:
         for (int var10 = 1; var10 <= 3; var10++) {
            for (int var5 = 1; var5 <= 2; var5++) {
               for (int var6 = -var10; var6 <= var10; var6++) {
                  for (int var7 = -var10; var7 <= var10; var7++) {
                     if (Math.max(Math.abs(var6), Math.abs(var7)) == var10) {
                        class_2338 var8 = var2.method_10069(var6, var5, var7);
                        if (x147zp9ypin2wg(var0, var8)) {
                           var9 = var8.method_10062();
                           break label74;
                        }
                     }
                  }
               }
            }
         }

         if (var9 == null) {
            xeksvy84118bm.remove(var1);
            return false;
         } else {
            long var11 = var0.method_75260();
            x102ju4l0cty5k var12 = xeksvy84118bm.get(var1);
            if (var12 == null || var12.xjmya1m07w50b().method_10262(var2) > 4.0 || !var12.x2gv3orj2jn99t().equals(var9)) {
               var12 = new x102ju4l0cty5k(var2.method_10062(), var9, var11);
               xeksvy84118bm.put(var1, var12);
            }

            if (var11 - var12.x2cpmgiveflnwy() >= 80L) {
               var1.method_5942().method_6340();
               var1.method_5808(var9.method_10263() + 0.5, var9.method_10264(), var9.method_10260() + 0.5, var1.method_36454(), var1.method_36455());
               var1.method_18799(class_243.field_1353);
               var1.method_38785();
               xeksvy84118bm.remove(var1);
               x2vaj7rsm57i4n.x1qd0mutwsr3sc(var1, "выход из небольшой ямы");
            } else if (x2n55y2tom37h2.x5ll9elqpk1a4(var0)) {
               var1.method_5942().method_6337(var9.method_10263() + 0.5, var9.method_10264(), var9.method_10260() + 0.5, 0.9);
            }

            return true;
         }
      }
   }

   private static int x2g2khbsuvhzto(class_3218 var0, x15kgoaq6i3m6i var1, class_1646 var2, class_2338 var3, class_2338 var4, class_2350 var5, int var6) {
      class_2338 var7 = var3.method_10093(var5);
      boolean var8 = var1.roleOf(var2.method_5667()) == x1rxiy9mtu5b3h.MINER;
      if (var4.method_10264() > var3.method_10264()) {
         if (x147zp9ypin2wg(var0, var7.method_10084()) && x3s6b8w8xand71(var0, var2, var7.method_10084())) {
            return 1;
         }

         if (!var8
            && var6 < 3
            && !var0.method_8320(var7.method_10084()).method_26220(var0, var7.method_10084()).method_1110()
            && var0.method_8320(var7.method_10086(2)).method_26220(var0, var7.method_10086(2)).method_1110()
            && x13f18m8o5x2va(var0, var1, var2, var7.method_10084())
            && x1dxdlnevtfbhm.x231if4rvsvptj(var0, var7.method_10084(), false, var2)) {
            return 2;
         }

         if (var6 < 3 && var0.method_8320(var7).method_26215() && var0.method_8320(var7.method_10084()).method_26215() && xjbg5jauzmkc2(var0, var1, var2, var7)
            )
          {
            return 2;
         }
      }

      if (var4.method_10264() < var3.method_10264()) {
         if (x147zp9ypin2wg(var0, var7.method_10074()) && x3s6b8w8xand71(var0, var2, var7.method_10074())) {
            return 1;
         }

         if (x147zp9ypin2wg(var0, var7.method_10087(2)) && x3s6b8w8xand71(var0, var2, var7.method_10087(2))) {
            return 1;
         }
      }

      if (var6 < 3
         && var0.method_8320(var7).method_26220(var0, var7).method_1110()
         && !var0.method_8320(var7.method_10074()).method_26206(var0, var7.method_10074(), class_2350.field_11036)) {
         for (int var9 = 2; var9 <= 3; var9++) {
            if (x147zp9ypin2wg(var0, var3.method_10079(var5, var9)) && xjbg5jauzmkc2(var0, var1, var2, var7.method_10074())) {
               return 2;
            }
         }
      }

      if (!var8 && var6 < 2 && var4.method_10264() <= var3.method_10264()) {
         for (class_2338 var10 : List.of(var7.method_10084(), var7)) {
            if (!var0.method_8320(var10).method_26220(var0, var10).method_1110()
               && x13f18m8o5x2va(var0, var1, var2, var10)
               && x1dxdlnevtfbhm.x231if4rvsvptj(var0, var10, false, var2)) {
               return 2;
            }
         }
      }

      return 0;
   }

   private static boolean xahx1liq9jvh7(class_3218 var0, x15kgoaq6i3m6i var1, class_1646 var2, class_2338 var3, class_2338 var4, class_2350 var5) {
      var var6 = new ArrayList<class_2338>();

      for (int var7 = 1; var7 <= 4; var7++) {
         for (int var8 = -var7; var8 <= var7; var8++) {
            for (int var9 = -var7; var9 <= var7; var9++) {
               if (Math.max(Math.abs(var8), Math.abs(var9)) == var7) {
                  for (int var13 : new int[]{0, 1, -1, 2, -2}) {
                     class_2338 var14 = var3.method_10069(var8, var13, var9);
                     double var15 = (double)var14.method_10263() - var1.core().method_10263();
                     double var17 = (double)var14.method_10260() - var1.core().method_10260();
                     if (!(var15 * var15 + var17 * var17 > (double)(var1.radius() - 2) * (var1.radius() - 2))
                        && x147zp9ypin2wg(var0, var14)
                        && !var14.equals(var3.method_10093(var5))
                        && !(var14.method_10262(var4) > var3.method_10262(var4) + 16.0)
                        && var0.method_8333(var2, new class_238(var14).method_1014(0.35), var0x -> var0x.method_5805() && var0x instanceof class_1309)
                           .isEmpty()) {
                        var6.add(var14.method_10062());
                        break;
                     }
                  }
               }
            }
         }
      }

      var6.sort(Comparator.comparingDouble(var2x -> var2x.method_10262(var4) + var2x.method_10262(var3) * 0.25));

      for (int var19 = 0; var19 < Math.min(6, var6.size()); var19++) {
         if (!x2n55y2tom37h2.x5ll9elqpk1a4(var0)) {
            return false;
         }

         class_2338 var20 = (class_2338)var6.get(var19);
         class_11 var21 = var2.method_5942().method_6348(var20, 0);
         if (var21 != null && var21.method_21655()) {
            return var2.method_5942().method_6334(var21, 0.85);
         }
      }

      return false;
   }

   private static boolean x3s6b8w8xand71(class_3218 var0, class_1646 var1, class_2338 var2) {
      return x2n55y2tom37h2.x5ll9elqpk1a4(var0)
         && var1.method_5942().method_6337(var2.method_10263() + 0.5, var2.method_10264(), var2.method_10260() + 0.5, 0.85);
   }

   private static boolean x147zp9ypin2wg(class_3218 var0, class_2338 var1) {
      return var0.method_14178().method_12123(var1.method_10263() >> 4, var1.method_10260() >> 4)
         && var0.method_8316(var1).method_15769()
         && var0.method_8316(var1.method_10074()).method_15769()
         && var0.method_8320(var1).method_26220(var0, var1).method_1110()
         && var0.method_8320(var1.method_10084()).method_26220(var0, var1.method_10084()).method_1110()
         && var0.method_8320(var1.method_10074()).method_26206(var0, var1.method_10074(), class_2350.field_11036);
   }

   private static boolean x3smw0c29vok53(class_3218 var0, class_2338 var1, int var2) {
      for (int var3 = 1; var3 <= var2; var3++) {
         if (var0.method_8320(var1.method_10087(var3)).method_26206(var0, var1.method_10087(var3), class_2350.field_11036)
            && var0.method_8316(var1.method_10087(var3)).method_15769()) {
            return true;
         }
      }

      return false;
   }

   private static class_2338 x1om0jz0ba9i8l(class_1646 var0) {
      class_2338 var1 = x1c1r5c6a13gtl.x37u4pqy3fbps(var0);
      if (var1 != null) {
         return var1;
      } else {
         for (class_4140 var3 : List.of(
            xxao083dbo7ag.x15kki2mjvh7tw,
            xxao083dbo7ag.x1r15huley8x9h,
            xxao083dbo7ag.x1h4kjv4hra8oe,
            xxao083dbo7ag.x2bwom1pasl6g5,
            xxao083dbo7ag.x263ucjykllb1b,
            xxao083dbo7ag.x2ppem7wipzctm,
            xxao083dbo7ag.x3o547clcv9kaw
         )) {
            var var4 = var0.method_18868().method_18904(var3);
            if (var4.isPresent()) {
               return (class_2338)var4.get();
            }
         }

         return var0.method_5942().method_6355();
      }
   }

   private static boolean x13f18m8o5x2va(class_3218 var0, x15kgoaq6i3m6i var1, class_1646 var2, class_2338 var3) {
      if (!x1dxdlnevtfbhm.x13f7be5vlapn7(var0, var3)) {
         return false;
      } else if (var0.method_14178().method_12123(var3.method_10263() >> 4, var3.method_10260() >> 4) && var0.method_8321(var3) == null) {
         if (var3.method_10264() >= var2.method_31478() && !var3.equals(var2.method_24515().method_10074())) {
            if (!var0.method_8333(var2, new class_238(var3.method_10084()).method_1014(0.05), var0x -> var0x.method_5805() && var0x instanceof class_1309)
               .isEmpty()) {
               return false;
            } else {
               class_2680 var4 = var0.method_8320(var3);
               if (!var4.method_26164(class_3481.field_25806)
                  && !var4.method_26164(class_3481.field_15466)
                  && !var4.method_27852(class_2246.field_10255)
                  && !var4.method_27852(class_2246.field_10566)
                  && !var4.method_27852(class_2246.field_10219)
                  && !var4.method_27852(class_2246.field_10253)
                  && !var4.method_27852(class_2246.field_10520)
                  && !var4.method_27852(class_2246.field_27165)
                  && !var4.method_27852(class_2246.field_10491)) {
                  return false;
               } else {
                  class_2350[] var5 = class_2350.values();
                  int var6 = var5.length;
                  int var7 = 0;

                  while (var7 < var6) {
                     class_2350 var8 = var5[var7];
                     class_2338 var9 = var3.method_10093(var8);
                     class_2680 var10 = var0.method_8320(var9);
                     if (var0.method_8316(var9).method_15769()
                        && !var10.method_27852(class_2246.field_10194)
                        && !var10.method_27852(class_2246.field_10362)
                        && !var10.method_26164(class_3481.field_16443)
                        && !var10.method_26164(class_3481.field_15495)) {
                        String var11 = class_7923.field_41175.method_10221(var10.method_26204()).method_12832();
                        if (!var11.contains("planks") && !var11.contains("fence") && !var11.contains("brick")) {
                           var7++;
                           continue;
                        }

                        return false;
                     }

                     return false;
                  }

                  for (x2arpn38dclo1g var13 : var1.x1ja61emeln2hc()) {
                     class_3341 var14 = var13.x24kbqv6g4kkrg();
                     if (var3.method_10263() >= var14.method_35415() - 3
                        && var3.method_10263() <= var14.method_35418() + 3
                        && var3.method_10264() >= var14.method_35416() - 3
                        && var3.method_10264() <= var14.method_35419() + 3
                        && var3.method_10260() >= var14.method_35417() - 3
                        && var3.method_10260() <= var14.method_35420() + 3) {
                        return false;
                     }
                  }

                  return true;
               }
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private static boolean xjbg5jauzmkc2(class_3218 var0, x15kgoaq6i3m6i var1, class_1646 var2, class_2338 var3) {
      if (var0.method_8320(var3).method_26215() && !x1556papwgwmb0(var0, var1, var3)) {
         for (int var4 = 0; var4 < var2.method_35199().method_5439(); var4++) {
            class_1799 var5 = var2.method_35199().method_5438(var4);
            class_2680 var6 = var5.method_31574(class_1802.field_20412)
               ? class_2246.field_10445.method_9564()
               : (var5.method_31574(class_1802.field_8831) ? class_2246.field_10566.method_9564() : null);
            if (var6 != null && x1dxdlnevtfbhm.x1bq0f8n53gtob(var0, var3, var6, 3)) {
               var5.method_7934(1);
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   private static boolean x1556papwgwmb0(class_3218 var0, x15kgoaq6i3m6i var1, class_2338 var2) {
      if (var0.method_8321(var2) != null) {
         return true;
      } else {
         for (class_2350 var6 : class_2350.values()) {
            class_2680 var7 = var0.method_8320(var2.method_10093(var6));
            if (var7.method_27852(class_2246.field_10194)
               || var7.method_27852(class_2246.field_10362)
               || var7.method_26164(class_3481.field_16443)
               || var7.method_26164(class_3481.field_15495)) {
               return true;
            }
         }

         for (x2arpn38dclo1g var9 : var1.x1ja61emeln2hc()) {
            class_3341 var10 = var9.x24kbqv6g4kkrg();
            if (var2.method_10263() >= var10.method_35415() - 2
               && var2.method_10263() <= var10.method_35418() + 2
               && var2.method_10264() >= var10.method_35416() - 2
               && var2.method_10264() <= var10.method_35419() + 2
               && var2.method_10260() >= var10.method_35417() - 2
               && var2.method_10260() <= var10.method_35420() + 2) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean xdo71dbv16zut(class_3218 var0, x15kgoaq6i3m6i var1, class_1646 var2) {
      return x1sixoyijd0z9u(var0, var1, var2);
   }

   private static boolean x1sixoyijd0z9u(class_3218 var0, x15kgoaq6i3m6i var1, class_1646 var2) {
      for (int var3 = 1; var3 <= 12; var3++) {
         for (int var4 = -var3; var4 <= var3; var4++) {
            for (int var5 = -var3; var5 <= var3; var5++) {
               if (Math.abs(var4) == var3 || Math.abs(var5) == var3) {
                  int var6 = var1.core().method_10263() + var4;
                  int var7 = var1.core().method_10260() + var5;
                  if (var0.method_14178().method_12123(var6 >> 4, var7 >> 4)) {
                     int var8 = var0.method_8624(class_2903.field_13203, var6, var7);
                     class_2338 var9 = new class_2338(var6, var8, var7);
                     if (var0.method_8316(var9).method_15769()
                        && var0.method_8316(var9.method_10074()).method_15769()
                        && var0.method_8320(var9).method_26220(var0, var9).method_1110()
                        && var0.method_8320(var9.method_10084()).method_26220(var0, var9.method_10084()).method_1110()
                        && var0.method_8320(var9.method_10074()).method_26206(var0, var9.method_10074(), class_2350.field_11036)
                        && var0.method_8333(var2, new class_238(var9).method_1014(0.35), var0x -> var0x.method_5805() && var0x instanceof class_1309).isEmpty()
                        )
                      {
                        var0.method_65096(class_2398.field_11214, var2.method_23317(), var2.method_23323(0.5), var2.method_23321(), 16, 0.3, 0.6, 0.3, 0.1);
                        var2.method_5942().method_6340();
                        if (var1.roleOf(var2.method_5667()) == x1rxiy9mtu5b3h.MINER) {
                           var2.method_5780("smart_colonies_emergency_return");
                        } else {
                           x3omirynxb9l0d(var2);
                           x237zvjlldjb5z.put(var2, var0.method_75260() + 200L);
                        }

                        var2.method_5808(var6 + 0.5, var8, var7 + 0.5, var2.method_36454(), var2.method_36455());
                        var2.method_18799(class_243.field_1353);
                        var2.method_38785();
                        var0.method_65096(class_2398.field_11214, var6 + 0.5, var8 + 1, var7 + 0.5, 16, 0.3, 0.6, 0.3, 0.1);
                        return true;
                     }
                  }
               }
            }
         }
      }

      return false;
   }

   private static void x3omirynxb9l0d(class_1646 var0) {
      for (class_4140 var2 : List.of(
         xxao083dbo7ag.x1r15huley8x9h,
         xxao083dbo7ag.x1h4kjv4hra8oe,
         xxao083dbo7ag.x2bwom1pasl6g5,
         xxao083dbo7ag.x263ucjykllb1b,
         xxao083dbo7ag.x2ppem7wipzctm,
         xxao083dbo7ag.x3o547clcv9kaw
      )) {
         var0.method_18868().method_18875(var2);
      }
   }
}
