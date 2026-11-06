package net.smartcolonies.r;

import com.google.common.collect.ImmutableMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.class_11;
import net.minecraft.class_1268;
import net.minecraft.class_1277;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1646;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3341;
import net.minecraft.class_3481;
import net.minecraft.class_3489;
import net.minecraft.class_4097;
import net.minecraft.class_4140;
import net.minecraft.class_4141;
import net.minecraft.class_7923;
import net.minecraft.class_2350.class_2353;
import net.minecraft.class_2902.class_2903;

public final class x3gesg5rjwqz5j extends class_4097<class_1646> {
   public static final String x3l030m30karg3 = "smart_colonies_emergency_return";
   private static final int x23l4wrkdbbupb = 512;
   private static final class_2350[] x2n94koxvmk13t = new class_2350[]{
      class_2350.field_11043, class_2350.field_11034, class_2350.field_11035, class_2350.field_11039
   };
   private static final List<class_2338> x1fgwiff13cmf2 = x3cil34rc17d8m();
   private x15kgoaq6i3m6i xy1q9c188jgfl;
   private xmq1yravtjp1k x1ajk4ev1s3vmk;
   private class_2338 x39r1px550mewh;
   private class_2338 x1co9a4owy6j0p;
   private class_2338 x3ima85yg8u7pt;
   private class_2338 x1pyrwt4gvtvim;
   private final ArrayDeque<class_2338> x26z6s4l7xpegw = new ArrayDeque<>();
   private final ArrayDeque<class_2338> x1wi4zdglz9eff = new ArrayDeque<>();
   private final List<class_2338> x1m6docvvu7vog = new ArrayList<>();
   private long x1xqtm36d9leb0;
   private long x1lbhfpli59w62;
   private long x191fs1fsn83bz;
   private long xhvgstwe661u5;
   private final x20lc7jle5mw9c x1lb0jykmu89gj = new x20lc7jle5mw9c();
   private double x1uepbjw0wv8fc;
   private class_243 x1vlro08p2yyfr;
   private int x35hjgfi0ny5oi;
   private int xtas7exwnkweh;
   private int xc9ckqw7lv8b1;
   private int x340ppe230jyu;
   private int x3alovr2tshkm1;
   private int x2bkom4nbrpupj;
   private int x342hqz5dxukyd;
   private boolean x1ow0ndkhuxfv7;
   private final Map<class_2338, Long> x1mcvl1k31xh98 = new HashMap<>();
   private class_2338 x5s8pk56x49d;
   private double x1vajhas144pja;
   private long x2c83xgjkd1pgw;
   private int xdyvztdlmgr2v;
   private int x3034xvdt9o8yg;
   private long xvo4i1er6ijvn;
   private boolean xgxy3geynmx1k;
   private class_2338 x2qljtijql8vba;
   private long x1bhavtwhoa4l5;
   private long x7c7ayx9b17cq;
   private boolean x120csoysnauu8;
   private boolean x1yhzt10hzydbt;
   private long x2ayx8ea5u28av;
   private int x1ngl9rxhvf6cn;
   private double x2wpsg3os7b6uj;
   private long x1cibfctnwc0om;

   public x3gesg5rjwqz5j() {
      super(ImmutableMap.of(class_4140.field_18445, class_4141.field_18458), 24000);
   }

   protected boolean shouldRun(class_3218 var1, class_1646 var2) {
      if (!var2.method_6109()
         && !var2.method_6113()
         && !var2.method_18009()
         && var1.method_8503().method_3780() >= this.x191fs1fsn83bz
         && Math.floorMod(var1.method_8532(), 24000L) < 7800L
         && x1dxdlnevtfbhm.x35ev8l9vc9uk7(var1)) {
         this.xy1q9c188jgfl = x2n55y2tom37h2.x3psqezd5jem54(var1, var2.method_24515())
            .filter(var1x -> var1x.roleOf(var2.method_5667()) == x1rxiy9mtu5b3h.MINER && !var1x.x2ad3s8aentmo4().isEmpty())
            .orElse(null);
         return this.xy1q9c188jgfl != null;
      } else {
         return false;
      }
   }

   protected void run(class_3218 var1, class_1646 var2, long var3) {
      this.x1ajk4ev1s3vmk = xmq1yravtjp1k.SUPPLY;
      this.x1ow0ndkhuxfv7 = false;
      this.x39r1px550mewh = null;
      this.x1pyrwt4gvtvim = null;
      this.x35hjgfi0ny5oi = 0;
      this.x26z6s4l7xpegw.clear();
      this.x1wi4zdglz9eff.clear();
      this.x1m6docvvu7vog.clear();
      this.x3alovr2tshkm1 = -1;
      this.x2bkom4nbrpupj = -1;
      this.x342hqz5dxukyd = 0;
      this.xgxy3geynmx1k = false;
      this.x1xqtm36d9leb0 = var3;
      this.x1lbhfpli59w62 = var1.method_8503().method_3780();
      int var5 = this.x28tnsle11zyqe(var2);
      this.x340ppe230jyu = !this.xy1q9c188jgfl.x31ow2tlg0dm5w().isEmpty()
            && this.xy1q9c188jgfl.x31ow2tlg0dm5w().get(var5).method_10262(var2.method_24515()) <= 9.0
            && var2.method_31478() < this.xy1q9c188jgfl.x31ow2tlg0dm5w().getFirst().method_10264() - 2
         ? var5
         : 0;
      long var6 = var1.method_8503().method_3780();
      this.xdyvztdlmgr2v = 0;
      this.x3034xvdt9o8yg = -1;
      this.x5s8pk56x49d = null;
      this.x2c83xgjkd1pgw = var6;
      this.xvo4i1er6ijvn = var6;
      this.x1mcvl1k31xh98.entrySet().removeIf(var2x -> var2x.getValue() <= var6);
      int var8 = this.xy1q9c188jgfl.x3eeagbf8g6pbh();
      this.x120csoysnauu8 = this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() >= 32 && var8 >= 2 || var8 >= 6;
      this.x1yhzt10hzydbt = this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() < 32 && var8 >= 6 && var8 < 12;
      this.x2ayx8ea5u28av = var6;
      this.x1ngl9rxhvf6cn = this.xy1q9c188jgfl.x31ow2tlg0dm5w().size();
      this.xhvgstwe661u5 = var6;
      this.x1uepbjw0wv8fc = Double.MAX_VALUE;
      this.x1vlro08p2yyfr = var2.method_73189();
      this.x2wpsg3os7b6uj = Double.MAX_VALUE;
      this.x1cibfctnwc0om = var6;
      this.x2idgvhct1beze(var2, this.xy1q9c188jgfl.x31ow2tlg0dm5w().isEmpty() ? this.xy1q9c188jgfl.core() : this.xy1q9c188jgfl.x31ow2tlg0dm5w().getLast());
   }

   protected boolean shouldKeepRunning(class_3218 var1, class_1646 var2, long var3) {
      return !var2.method_6113()
         && !var2.method_18009()
         && this.xy1q9c188jgfl != null
         && this.xy1q9c188jgfl.roleOf(var2.method_5667()) == x1rxiy9mtu5b3h.MINER
         && !this.x1ow0ndkhuxfv7;
   }

   protected void keepRunning(class_3218 var1, class_1646 var2, long var3) {
      long var5 = var1.method_8503().method_3780();
      if (this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() > this.x1ngl9rxhvf6cn) {
         this.x1ngl9rxhvf6cn = this.xy1q9c188jgfl.x31ow2tlg0dm5w().size();
         this.x2ayx8ea5u28av = var5;
      } else if (this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() >= 32 && var5 - this.x2ayx8ea5u28av >= 400L
         || !this.xy1q9c188jgfl.x31ow2tlg0dm5w().isEmpty() && var5 - this.x2ayx8ea5u28av >= 2400L) {
         this.x120csoysnauu8 = true;
      }

      if (var2.method_5752().contains("smart_colonies_emergency_return")) {
         var2.method_5738("smart_colonies_emergency_return");
         this.x1ajk4ev1s3vmk = xmq1yravtjp1k.RETURN;
         this.x39r1px550mewh = null;
         this.x1pyrwt4gvtvim = null;
         this.x340ppe230jyu = 0;
         this.x1m6docvvu7vog.clear();
         this.x342hqz5dxukyd = 0;
         this.x2bkom4nbrpupj = -1;
         this.x5s8pk56x49d = null;
         this.xgxy3geynmx1k = false;
         this.xhvgstwe661u5 = var5;
         this.x1uepbjw0wv8fc = Double.MAX_VALUE;
         this.x1vlro08p2yyfr = var2.method_73189();
      }

      if (this.x1ajk4ev1s3vmk == xmq1yravtjp1k.DEEP_EXPEDITION) {
         this.x1lam802btimdu(var1, var2, var3);
      } else if (this.x120csoysnauu8 && this.x1yhzt10hzydbt && this.x1ajk4ev1s3vmk == xmq1yravtjp1k.SUPPLY && this.x1xbvmwg584cth(var1, var2)) {
         this.x120csoysnauu8 = false;
         this.x1yhzt10hzydbt = false;
         this.x1ajk4ev1s3vmk = xmq1yravtjp1k.WORK;
         this.x2ayx8ea5u28av = this.x1lbhfpli59w62 = var5;
         this.x1ngl9rxhvf6cn = this.xy1q9c188jgfl.x31ow2tlg0dm5w().size();
      } else if (this.x120csoysnauu8 && !this.xy1q9c188jgfl.x31ow2tlg0dm5w().isEmpty() && var5 >= this.xy1q9c188jgfl.x1wlbtxwjz4vz1()) {
         this.x294dcmermbsdf(var1, var2, var3);
      } else if (var3 >= this.x1xqtm36d9leb0) {
         this.x1xqtm36d9leb0 = var3 + 1L;
         long var7 = Math.floorMod(var1.method_8532(), 24000L);
         if (this.x1ajk4ev1s3vmk == xmq1yravtjp1k.WORK && var7 >= 8000L && var7 < 12000L) {
            this.xhzkfb8tfrmu6(var2);
         } else if (this.x1ajk4ev1s3vmk != xmq1yravtjp1k.WORK) {
            this.x3vdgwaoodch5a(var1, var2, var3);
         } else {
            if (this.x1pyrwt4gvtvim != null && var5 - this.x1lbhfpli59w62 > 200L) {
               this.x1mcvl1k31xh98.put(this.x1pyrwt4gvtvim, var5 + 6000L);
               this.x1pyrwt4gvtvim = null;
               this.x39r1px550mewh = null;
               this.x26z6s4l7xpegw.clear();
               if (!this.x1m6docvvu7vog.isEmpty()) {
                  this.xhzkfb8tfrmu6(var2);
                  this.xgxy3geynmx1k = true;
                  return;
               }
            }

            if (var5 - this.x1lbhfpli59w62 <= 1600L
               && this.x35hjgfi0ny5oi < 192
               && xocihbcrvdo4w(var2) < 256
               && x215m1zmqihw78(var2) < 24
               && !(var2.method_6032() < var2.method_6063() * 0.6F)) {
               if (this.x39r1px550mewh != null) {
                  class_2680 var22 = var1.method_8320(this.x39r1px550mewh);
                  if (xe9s6y96sy1nw(var1, this.x39r1px550mewh)) {
                     this.x39r1px550mewh = null;
                     this.x1lbhfpli59w62 = var5;
                  } else if (!this.x2koaf0y8uk4zf(var1, this.x39r1px550mewh)) {
                     this.x39r1px550mewh = null;
                     this.x1pyrwt4gvtvim = null;
                  } else if (!x2pv6ejqyq6ou5(var2, var22)) {
                     this.x39r1px550mewh = null;
                     this.xhzkfb8tfrmu6(var2);
                  } else {
                     this.x2idgvhct1beze(var2, this.x39r1px550mewh);
                     if (var2.method_24515().method_10262(this.x1co9a4owy6j0p) > 2.5) {
                        if (x1c1r5c6a13gtl.xps35ehel6nkk(var2, this.x1co9a4owy6j0p, var5)) {
                           this.x1mcvl1k31xh98.put(this.x39r1px550mewh, var5 + 1200L);
                           this.x39r1px550mewh = null;
                           this.x1pyrwt4gvtvim = null;
                        } else {
                           this.x2qi4jek408dcr(var1, var2, this.x1co9a4owy6j0p);
                        }
                     } else if (!this.x3kfcd4ju4t319(var1, var2, var2.method_24515())) {
                        this.xhzkfb8tfrmu6(var2);
                     } else if (!(this.x39r1px550mewh.method_10262(var2.method_24515()) > 12.0)
                        && !this.x39r1px550mewh.equals(var2.method_24515().method_10074())) {
                        class_2680 var24 = var1.method_8320(this.x39r1px550mewh);
                        var var26 = class_2248.method_9609(var24, var1, this.x39r1px550mewh, null, var2, var2.method_6047());
                        if (!x3b13cjb6ycs5u(var2, var26)) {
                           this.xhzkfb8tfrmu6(var2);
                        } else if (this.x1lb0jykmu89gj
                           .xpohy1q30uxlk(var1, var2, this.x39r1px550mewh, x20lc7jle5mw9c.x20ocn113pbk5f(var1, var2, this.x39r1px550mewh), true)) {
                           if (x1dxdlnevtfbhm.x231if4rvsvptj(var1, this.x39r1px550mewh, false, var2)) {
                              var26.forEach(var1x -> var2.method_35199().method_5491(var1x));
                              var2.method_6104(class_1268.field_5808);
                              this.x35hjgfi0ny5oi++;
                              this.x1lbhfpli59w62 = var5;
                              if (x1pn4sqnjt1xiv(var24)) {
                                 this.x9380ui9wkv3h(var1, this.x39r1px550mewh);
                              }
                           }

                           this.x39r1px550mewh = null;
                        }
                     } else {
                        this.x39r1px550mewh = null;
                     }
                  }
               } else if (!this.x2xwjgtx7cwwvg(var1, var2, var5)) {
                  if (this.x1pyrwt4gvtvim != null
                     && (
                        !x1pn4sqnjt1xiv(var1.method_8320(this.x1pyrwt4gvtvim))
                           || !x2pv6ejqyq6ou5(var2, var1.method_8320(this.x1pyrwt4gvtvim))
                           || this.xy1q9c188jgfl.x31ow2tlg0dm5w().contains(this.x1pyrwt4gvtvim.method_10084())
                     )) {
                     this.x1pyrwt4gvtvim = null;
                  }

                  if (!this.x27hg6cma7yaw5(var1, var2)) {
                     if (this.x1pyrwt4gvtvim == null && this.x2bkom4nbrpupj >= 0 && this.x26z6s4l7xpegw.isEmpty()) {
                        this.xhzkfb8tfrmu6(var2);
                        this.xgxy3geynmx1k = true;
                     } else {
                        if (this.x1pyrwt4gvtvim == null && var5 >= this.xvo4i1er6ijvn) {
                           this.xvo4i1er6ijvn = var5 + 10L;
                           if (this.x2clgblb4ftzb2(var1, var2)) {
                              return;
                           }
                        }

                        if (this.xy1q9c188jgfl.x31ow2tlg0dm5w().isEmpty()) {
                           this.x2ibslq4rhdko0(var1, var2);
                        } else {
                           class_2338 var9 = this.x1m6docvvu7vog.isEmpty() ? this.xy1q9c188jgfl.x31ow2tlg0dm5w().getLast() : this.x1m6docvvu7vog.getLast();
                           this.x2idgvhct1beze(var2, var9);
                           if (var2.method_24515().method_10262(var9) > 2.5) {
                              if (!this.x1m6docvvu7vog.isEmpty()) {
                                 this.x2qi4jek408dcr(var1, var2, var9);
                              } else {
                                 this.xdyvztdlmgr2v = Math.min(this.xdyvztdlmgr2v, this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() - 1);
                                 if (x1r7nkw0jt7ym0(var2, this.xy1q9c188jgfl.x31ow2tlg0dm5w().get(this.xdyvztdlmgr2v))
                                    && this.xdyvztdlmgr2v < this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() - 1) {
                                    this.xdyvztdlmgr2v++;
                                 }

                                 if (this.xdyvztdlmgr2v > this.x3034xvdt9o8yg) {
                                    this.x3034xvdt9o8yg = this.xdyvztdlmgr2v;
                                    this.x1lbhfpli59w62 = var5;
                                 }

                                 class_2338 var23 = this.xy1q9c188jgfl.x31ow2tlg0dm5w().get(this.xdyvztdlmgr2v);
                                 this.x2qi4jek408dcr(var1, var2, var23);
                              }
                           } else if (!this.x3kfcd4ju4t319(var1, var2, var9)) {
                              this.xhzkfb8tfrmu6(var2);
                           } else if (this.x1pyrwt4gvtvim != null
                              || !this.x1m6docvvu7vog.isEmpty()
                              || this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() > 8 && this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() % 8 != 0
                              || !this.x3kbhcj0gn3eds(var1, var9)) {
                              if (this.x1pyrwt4gvtvim != null && this.x1m6docvvu7vog.isEmpty()) {
                                 this.x2bkom4nbrpupj = this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() - 1;
                                 this.x1m6docvvu7vog.add(var9.method_10062());
                              }

                              if (this.x1pyrwt4gvtvim == null && this.x1m6docvvu7vog.isEmpty() && this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() >= 512) {
                                 this.x294dcmermbsdf(var1, var2, var3);
                              } else {
                                 class_2338 var10 = this.x1pyrwt4gvtvim == null
                                    ? nextStep(this.xy1q9c188jgfl, var1.method_31607())
                                    : this.x7zvchtwcbs2l(var9, this.x1pyrwt4gvtvim);
                                 if (var10 == null) {
                                    if (this.x1pyrwt4gvtvim != null) {
                                       this.x1mcvl1k31xh98.put(this.x1pyrwt4gvtvim, var5 + 6000L);
                                    }

                                    this.x1pyrwt4gvtvim = null;
                                 } else if (x19i92dflbkly4(var1, var10.method_10074()) || !this.x1npl0tf7hnjwg(var1, var2, var10)) {
                                    if (!this.xesfei3h73qdo(var1, var10)
                                       || this.x2rtvy1700fh69(var10)
                                       || this.xy1q9c188jgfl.x31ow2tlg0dm5w().contains(var10)
                                       || this.x1m6docvvu7vog.contains(var10)) {
                                       if (this.x1pyrwt4gvtvim != null) {
                                          this.x1mcvl1k31xh98.put(this.x1pyrwt4gvtvim, var5 + 6000L);
                                          this.x1pyrwt4gvtvim = null;
                                          return;
                                       }

                                       boolean var11 = false;
                                       int var12 = this.xy1q9c188jgfl.xdd8htksk84yo();

                                       for (int var13 = 1; var13 < 4; var13++) {
                                          this.xy1q9c188jgfl.xmskodayog7rh(var12 + var13);
                                          class_2338 var14 = nextStep(this.xy1q9c188jgfl, var1.method_31607());
                                          if (this.xesfei3h73qdo(var1, var14)
                                             && !this.x2rtvy1700fh69(var14)
                                             && !this.xy1q9c188jgfl.x31ow2tlg0dm5w().contains(var14)) {
                                             var10 = var14;
                                             var11 = true;
                                             x2n55y2tom37h2.xchl1k1tnh3w8(var1).method_80();
                                             break;
                                          }
                                       }

                                       if (!var11) {
                                          label248:
                                          for (int var16 : new int[]{0, 1, -1}) {
                                             for (class_2350 var20 : x2n94koxvmk13t) {
                                                class_2338 var21 = var9.method_10093(var20).method_10069(0, var16, 0);
                                                if (!this.x2rtvy1700fh69(var21)
                                                   && !this.xy1q9c188jgfl.x31ow2tlg0dm5w().contains(var21)
                                                   && this.xesfei3h73qdo(var1, var21)) {
                                                   var10 = var21;
                                                   var11 = true;
                                                   break label248;
                                                }
                                             }
                                          }

                                          this.xy1q9c188jgfl.xmskodayog7rh(var12);
                                          if (!var11) {
                                             this.x294dcmermbsdf(var1, var2, var3);
                                             return;
                                          }
                                       }
                                    }

                                    for (int var25 = 2; var25 >= 0; var25--) {
                                       class_2338 var27 = var10.method_10086(var25);
                                       if (!xe9s6y96sy1nw(var1, var27)) {
                                          if (!this.x2koaf0y8uk4zf(var1, var27)) {
                                             this.xhzkfb8tfrmu6(var2);
                                             return;
                                          }

                                          this.x39r1px550mewh = var27;
                                          this.x1co9a4owy6j0p = var9;
                                          this.x2idgvhct1beze(var2, this.x39r1px550mewh);
                                          return;
                                       }
                                    }

                                    if (this.x1pyrwt4gvtvim != null) {
                                       this.x1m6docvvu7vog.add(var10.method_10062());
                                    } else {
                                       this.xy1q9c188jgfl.x31ow2tlg0dm5w().add(var10.method_10062());
                                       this.xdyvztdlmgr2v = this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() - 1;
                                       x2n55y2tom37h2.xchl1k1tnh3w8(var1).method_80();
                                    }

                                    this.x1lbhfpli59w62 = var5;
                                    this.x2qi4jek408dcr(var1, var2, var10);
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            } else {
               this.xhzkfb8tfrmu6(var2);
            }
         }
      }
   }

   private void x294dcmermbsdf(class_3218 var1, class_1646 var2, long var3) {
      this.x1lb0jykmu89gj.xupxzzyodxtwj(var1, var2);
      this.x1ajk4ev1s3vmk = xmq1yravtjp1k.DEEP_EXPEDITION;
      this.x39r1px550mewh = null;
      this.x1pyrwt4gvtvim = null;
      this.x7c7ayx9b17cq = var1.method_8503().method_3780() + DevelopmentPolicy.delay(x3sqkg3n6v972r.x2by5f9ehxvwk9().work.deep_mining_ticks, x3sqkg3n6v972r.x2by5f9ehxvwk9().village.development_speed);
      x1c1r5c6a13gtl.x2fxoqmbs0henb(var2);
      this.x2idgvhct1beze(var2, var2.method_24515());
      x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "глубинная экспедиция: работа в забое");
      this.x1lam802btimdu(var1, var2, var3);
   }

   private void x1lam802btimdu(class_3218 var1, class_1646 var2, long var3) {
      x1c1r5c6a13gtl.x2fxoqmbs0henb(var2);
      this.x2idgvhct1beze(var2, var2.method_24515());
      if (var3 % 40L == 0L) {
         var2.method_6104(class_1268.field_5808);
      }

      if (var1.method_8503().method_3780() >= this.x7c7ayx9b17cq) {
         int var5 = 48 + var1.field_9229.method_43048(49);
         int var6 = 8 + var1.field_9229.method_43048(13);
         int var7 = 4 + var1.field_9229.method_43048(9);
         int var8 = 4 + var1.field_9229.method_43048(13);
         int var9 = this.x3ldu7xuw4ylde(var1, var2, new class_1799(class_1802.field_20412, var5))
            + this.x3ldu7xuw4ylde(var1, var2, new class_1799(class_1802.field_8713, var6))
            + this.x3ldu7xuw4ylde(var1, var2, new class_1799(class_1802.field_33400, var7))
            + this.x3ldu7xuw4ylde(var1, var2, new class_1799(class_1802.field_33401, var8));
         if (var2.method_6047().method_31574(class_1802.field_8403)
            || var2.method_6047().method_31574(class_1802.field_8377)
            || var2.method_6047().method_31574(class_1802.field_22024)) {
            if (var1.field_9229.method_43048(3) == 0) {
               var9 += this.x3ldu7xuw4ylde(var1, var2, new class_1799(class_1802.field_8477, 1 + var1.field_9229.method_43048(2)));
            }

            var9 += this.x3ldu7xuw4ylde(var1, var2, new class_1799(class_1802.field_8725, 4 + var1.field_9229.method_43048(9)));
         }

         this.xy1q9c188jgfl.x2itw5qfmlx92d(var9);
         this.xy1q9c188jgfl.xq8drze9hw6pb(var1.method_8503().method_3780() + 600L);
         x2n55y2tom37h2.xchl1k1tnh3w8(var1).method_80();
         x2vaj7rsm57i4n.x2t98zlq4lu49t(var2, "глубинная добыча", var9);
         this.x35hjgfi0ny5oi += var9;
         this.x1xqtm36d9leb0 = var3;
         this.xhzkfb8tfrmu6(var2);
      }
   }

   private int x3ldu7xuw4ylde(class_3218 var1, class_1646 var2, class_1799 var3) {
      int var4 = var3.method_7947();
      x2vaj7rsm57i4n.x25cotfj45r525(var1, this.xy1q9c188jgfl, var2, var3);
      return var4 - var3.method_7947();
   }

   public static class_2338 nextStep(x15kgoaq6i3m6i var0, int var1) {
      class_2338 var2 = var0.x31ow2tlg0dm5w().getLast();
      int var3 = var0.x31ow2tlg0dm5w().size() - 1;
      int var4 = Math.max(var1 + 10, -52);
      int var5 = 0;

      for (int var6 = var0.x31ow2tlg0dm5w().size() - 2; var6 >= 0 && var0.x31ow2tlg0dm5w().get(var6).method_10264() == var2.method_10264(); var6--) {
         var5++;
      }

      byte var11 = 64;
      if (var2.method_10264() <= var4 || var2.method_10264() == 16 && var5 < 2 * var11 + 3) {
         int var12 = var5 / (var11 + 3);
         int var8 = var5 % (var11 + 3);
         class_2350 var9 = x2n94koxvmk13t[var0.xdd8htksk84yo() % 4];
         class_2350 var10 = var8 < var11 ? (var12 % 2 == 0 ? var9 : var9.method_10153()) : var9.method_10170();
         return var2.method_10093(var10);
      } else {
         class_2350 var7 = x2n94koxvmk13t[(var0.xdd8htksk84yo() + var3 / 24) % 4];
         return var2.method_10093(var7).method_10087(var2.method_10264() > var4 ? 1 : 0);
      }
   }

   private void x3vdgwaoodch5a(class_3218 var1, class_1646 var2, long var3) {
      long var5 = var1.method_8503().method_3780();
      if (this.x342hqz5dxukyd > 0 && !this.x1m6docvvu7vog.isEmpty()) {
         this.x342hqz5dxukyd = Math.min(this.x342hqz5dxukyd, this.x1m6docvvu7vog.size() - 1);
         class_2338 var7 = this.x1m6docvvu7vog.get(this.x342hqz5dxukyd - 1);
         this.x2idgvhct1beze(var2, var7);
         double var8 = var2.method_24515().method_10262(var7);
         if (!x1r7nkw0jt7ym0(var2, var7)) {
            if (var8 + 1.0 < this.x1uepbjw0wv8fc) {
               this.x1uepbjw0wv8fc = var8;
               this.xhvgstwe661u5 = var5;
            }

            this.xwknhpp7tkplu(var2, var5);
            this.x2qi4jek408dcr(var1, var2, var7);
            if (var5 - this.xhvgstwe661u5 > 360L) {
               x35kkrl6uz1e8h.xdo71dbv16zut(var1, this.xy1q9c188jgfl, var2);
               this.x342hqz5dxukyd = 0;
            }

            return;
         }

         this.x342hqz5dxukyd--;
         this.xhvgstwe661u5 = var5;
         this.x1uepbjw0wv8fc = Double.MAX_VALUE;
         this.x1vlro08p2yyfr = var2.method_73189();
      }

      if (!this.x1m6docvvu7vog.isEmpty()) {
         this.x1m6docvvu7vog.clear();
         this.x340ppe230jyu = this.x2bkom4nbrpupj >= 0
            ? Math.min(this.x2bkom4nbrpupj, this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() - 1)
            : this.x28tnsle11zyqe(var2);
         this.x2bkom4nbrpupj = -1;
         this.xhvgstwe661u5 = var5;
         this.x1uepbjw0wv8fc = Double.MAX_VALUE;
         this.x1vlro08p2yyfr = var2.method_73189();
      }

      if (this.xgxy3geynmx1k) {
         this.xgxy3geynmx1k = false;
         this.x1ajk4ev1s3vmk = xmq1yravtjp1k.WORK;
         this.x39r1px550mewh = null;
         this.x1pyrwt4gvtvim = null;
         this.xdyvztdlmgr2v = this.x340ppe230jyu;
         this.x3034xvdt9o8yg = this.xdyvztdlmgr2v;
         this.x5s8pk56x49d = null;
         this.x1lbhfpli59w62 = var5;
      } else {
         if (this.x340ppe230jyu > 0) {
            this.x340ppe230jyu = Math.min(this.x340ppe230jyu, this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() - 1);
            class_2338 var12 = this.xy1q9c188jgfl.x31ow2tlg0dm5w().get(this.x340ppe230jyu - 1);
            this.x2idgvhct1beze(var2, var12);
            double var14 = var2.method_24515().method_10262(var12);
            if (!x1r7nkw0jt7ym0(var2, var12)) {
               if (var14 + 1.0 < this.x1uepbjw0wv8fc) {
                  this.x1uepbjw0wv8fc = var14;
                  this.xhvgstwe661u5 = var5;
               }

               this.xwknhpp7tkplu(var2, var5);
               this.x2qi4jek408dcr(var1, var2, var12);
               if (var5 - this.xhvgstwe661u5 > 360L) {
                  x35kkrl6uz1e8h.xdo71dbv16zut(var1, this.xy1q9c188jgfl, var2);
                  this.xhvgstwe661u5 = var5;
                  this.x1uepbjw0wv8fc = Double.MAX_VALUE;
                  this.x1vlro08p2yyfr = var2.method_73189();
               }

               return;
            }

            this.x340ppe230jyu--;
            this.xhvgstwe661u5 = var5;
            this.x1uepbjw0wv8fc = Double.MAX_VALUE;
            this.x1vlro08p2yyfr = var2.method_73189();
         }

         var var13 = x2n55y2tom37h2.x3jrfw2d3yykeu(var1, this.xy1q9c188jgfl, var2, x2jj5edkiu5orf.MINING);
         if (var13.isEmpty()) {
            this.x1ow0ndkhuxfv7 = true;
         } else {
            double var15 = var2.method_24515().method_10262((class_2382)var13.get());
            if (var15 > 9.0) {
               if (var15 + 1.0 < this.x2wpsg3os7b6uj) {
                  this.x2wpsg3os7b6uj = var15;
                  this.x1cibfctnwc0om = var5;
               }

               this.x2qi4jek408dcr(var1, var2, (class_2338)var13.get());
               if ((var5 - this.x1cibfctnwc0om > 200L || x1c1r5c6a13gtl.x1f6ghn62xaxti(var2, var5)) && this.x2nr3c5c4mpjij(var1, var2, (class_2338)var13.get())
                  )
                {
                  this.x2wpsg3os7b6uj = Double.MAX_VALUE;
                  this.x1cibfctnwc0om = var5;
               }
            } else {
               this.x2wpsg3os7b6uj = Double.MAX_VALUE;
               this.x1cibfctnwc0om = var5;

               for (int var10 = 0; var10 < var2.method_35199().method_5439(); var10++) {
                  class_1799 var11 = var2.method_35199().method_5438(var10);
                  if (x3suldnm75afk3(var11)) {
                     x2vaj7rsm57i4n.x25cotfj45r525(var1, this.xy1q9c188jgfl, var2, var11);
                  }
               }

               x2fl60kuzia5xk.x3bzx7lf7atdqb(var1, this.xy1q9c188jgfl);
               if (this.x120csoysnauu8
                  && !this.xy1q9c188jgfl.x31ow2tlg0dm5w().isEmpty()
                  && var1.method_8503().method_3780() >= this.xy1q9c188jgfl.x1wlbtxwjz4vz1()) {
                  this.x294dcmermbsdf(var1, var2, var3);
               } else if (this.x120csoysnauu8 && !this.xy1q9c188jgfl.x31ow2tlg0dm5w().isEmpty()) {
                  this.x1ow0ndkhuxfv7 = true;
               } else if (this.x1ajk4ev1s3vmk == xmq1yravtjp1k.RETURN) {
                  this.x1ow0ndkhuxfv7 = true;
                  this.x191fs1fsn83bz = var5 + 40L;
               } else {
                  this.x1moizhe667oge(var1, var2);
                  this.x3mjvdhkyzto32(var1, var2);
                  this.x1medszyn1khgr(var1, var2);
                  if (var2.method_6047().method_31573(class_3489.field_42614)
                     && xncu5r00mkmgq(var2, class_1802.field_8810) != 0
                     && x2n55y2tom37h2.x57l6vd1sx1rt(var1, this.xy1q9c188jgfl, x3gesg5rjwqz5j::x3suldnm75afk3)) {
                     this.x1ajk4ev1s3vmk = xmq1yravtjp1k.WORK;
                     this.x1lbhfpli59w62 = var5;
                     this.xdyvztdlmgr2v = 0;
                     this.x3034xvdt9o8yg = -1;
                     this.x5s8pk56x49d = null;
                  } else {
                     this.x1ow0ndkhuxfv7 = true;
                     this.x191fs1fsn83bz = var5 + 100L;
                  }
               }
            }
         }
      }
   }

   private boolean x2nr3c5c4mpjij(class_3218 var1, class_1646 var2, class_2338 var3) {
      for (int var4 = 1; var4 <= 2; var4++) {
         for (int var5 = -var4; var5 <= var4; var5++) {
            for (int var6 = -var4; var6 <= var4; var6++) {
               if (Math.abs(var5) == var4 || Math.abs(var6) == var4) {
                  class_2338 var7 = var3.method_10069(var5, 0, var6);
                  if (x1c1r5c6a13gtl.x9bnd6e2jczhd(var1, var7)
                     && var1.method_8333(var2, new class_238(var7).method_1014(0.35), var0 -> var0.method_5805() && var0 instanceof class_1309).isEmpty()) {
                     var2.method_5942().method_6340();
                     var2.method_5808(var7.method_10263() + 0.5, var7.method_10264(), var7.method_10260() + 0.5, var2.method_36454(), var2.method_36455());
                     var2.method_18799(class_243.field_1353);
                     var2.method_38785();
                     this.x5s8pk56x49d = null;
                     this.x2c83xgjkd1pgw = var1.method_8503().method_3780();
                     x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "восстановление доступа к складу шахтёра");
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   private void x1moizhe667oge(class_3218 var1, class_1646 var2) {
      x2fl60kuzia5xk.x31tan2efb24l6(var1, this.xy1q9c188jgfl, var2, class_3489.field_42614, class_1802.field_8647, class_1802.field_8387);
   }

   private void x17lv4ejkx2w4z(class_3218 var1, class_1646 var2, class_1799 var3) {
      class_1799 var4 = var2.method_6047();
      x2fl60kuzia5xk.xqe8twcjwxn55(var1, this.xy1q9c188jgfl, var2, var4);
      var2.method_5673(class_1304.field_6173, var3);
   }

   private void x3mjvdhkyzto32(class_3218 var1, class_1646 var2) {
      for (int var3 = 0; var3 < 32 && xncu5r00mkmgq(var2, class_1802.field_8810) < 32 && xaavk81ye4wra(var2, class_1802.field_8810); var3++) {
         var var4 = x2n55y2tom37h2.x3lxwdirnn77qc(var1, this.xy1q9c188jgfl, var0 -> var0.method_31574(class_1802.field_8810));
         if (var4.isPresent()) {
            var2.method_35199().method_5491((class_1799)var4.get());
         } else {
            Predicate<class_1799> var5 = var0 -> var0.method_31573(class_3489.field_15539);
            Predicate<class_1799> var6 = var0 -> var0.method_31574(class_1802.field_8713) || var0.method_31574(class_1802.field_8665);
            if (x2n55y2tom37h2.x1mlne8tl8k5ag(var1, this.xy1q9c188jgfl, var5) >= 1 && x2n55y2tom37h2.x1mlne8tl8k5ag(var1, this.xy1q9c188jgfl, var6) >= 1) {
               x2n55y2tom37h2.x22hjebj4vmztt(var1, this.xy1q9c188jgfl, var5, 1);
               x2n55y2tom37h2.x22hjebj4vmztt(var1, this.xy1q9c188jgfl, var6, 1);
            } else if (!x2n55y2tom37h2.x22hjebj4vmztt(var1, this.xy1q9c188jgfl, var5, 2)) {
               break;
            }

            class_1799 var7 = var2.method_35199().method_5491(new class_1799(class_1802.field_8810, 4));
            if (!var7.method_7960()) {
               x2fl60kuzia5xk.xqe8twcjwxn55(var1, this.xy1q9c188jgfl, var2, var7);
            }
         }
      }
   }

   private void x1medszyn1khgr(class_3218 var1, class_1646 var2) {
      int var3 = xncu5r00mkmgq(var2, class_1802.field_20412);
      if (var3 < 16) {
         x2n55y2tom37h2.x1dhbpietunk9i(var1, this.xy1q9c188jgfl, var2, var0 -> var0.method_31574(class_1802.field_20412))
            .flatMap(var2x -> x2n55y2tom37h2.x5jd7014csotp(var1, var2x, var0x -> var0x.method_31574(class_1802.field_20412), 16 - var3))
            .ifPresent(var1x -> var2.method_35199().method_5491(var1x));
      }
   }

   private boolean x1npl0tf7hnjwg(class_3218 var1, class_1646 var2, class_2338 var3) {
      class_2338 var4 = var3.method_10074();
      if (!this.x2rtvy1700fh69(var3)
         && var1.method_8316(var3).method_15769()
         && var1.method_8316(var3.method_10084()).method_15769()
         && var1.method_8316(var4).method_15769()
         && xe9s6y96sy1nw(var1, var4)) {
         boolean var5 = false;

         for (class_2350 var7 : class_2353.field_11062) {
            if (var1.method_8320(var4.method_10093(var7)).method_26206(var1, var4.method_10093(var7), var7.method_10153())) {
               var5 = true;
               break;
            }
         }

         if (!var5) {
            return false;
         } else {
            for (int var8 = 0; var8 < var2.method_35199().method_5439(); var8++) {
               class_1799 var9 = var2.method_35199().method_5438(var8);
               if (var9.method_31574(class_1802.field_20412) && x1dxdlnevtfbhm.x1bq0f8n53gtob(var1, var4, class_2246.field_10445.method_9564(), 3)) {
                  var9.method_7934(1);
                  var2.method_6104(class_1268.field_5808);
                  this.x1lbhfpli59w62 = var1.method_8503().method_3780();
                  return true;
               }
            }

            return false;
         }
      } else {
         return false;
      }
   }

   private boolean x3kfcd4ju4t319(class_3218 var1, class_1646 var2, class_2338 var3) {
      for (class_2338 var5 : class_2338.method_10097(var3.method_10069(-4, -2, -4), var3.method_10069(4, 3, 4))) {
         if (var1.method_8320(var5).method_27852(class_2246.field_10336) || var1.method_8320(var5).method_27852(class_2246.field_10099)) {
            return true;
         }
      }

      if (var1.method_8320(var3).method_26215() && class_2246.field_10336.method_9564().method_26184(var1, var3)) {
         for (int var6 = 0; var6 < var2.method_35199().method_5439(); var6++) {
            class_1799 var7 = var2.method_35199().method_5438(var6);
            if (var7.method_31574(class_1802.field_8810) && x1dxdlnevtfbhm.x1bq0f8n53gtob(var1, var3, class_2246.field_10336.method_9564(), 3)) {
               var7.method_7934(1);
               var2.method_6104(class_1268.field_5808);
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   private void xwknhpp7tkplu(class_1646 var1, long var2) {
      class_243 var4 = var1.method_73189();
      if (this.x1vlro08p2yyfr == null || this.x1vlro08p2yyfr.method_1025(var4) >= 16.0) {
         this.x1vlro08p2yyfr = var4;
         this.xhvgstwe661u5 = var2;
      }
   }

   private boolean x2xwjgtx7cwwvg(class_3218 var1, class_1646 var2, long var3) {
      for (class_2350 var8 : class_2350.values()) {
         class_2338 var9 = var2.method_24515().method_10093(var8);
         if (var8 != class_2350.field_11033) {
            class_2680 var10 = var1.method_8320(var9);
            if (x1pn4sqnjt1xiv(var10) && this.x2koaf0y8uk4zf(var1, var9) && x2pv6ejqyq6ou5(var2, var10)) {
               var var11 = class_2248.method_9609(var10, var1, var9, null, var2, var2.method_6047());
               if (!x3b13cjb6ycs5u(var2, var11)) {
                  this.xhzkfb8tfrmu6(var2);
                  return true;
               }

               this.x2idgvhct1beze(var2, var9);
               if (!this.x1lb0jykmu89gj.xpohy1q30uxlk(var1, var2, var9, x20lc7jle5mw9c.x20ocn113pbk5f(var1, var2, var9), true)) {
                  return true;
               }

               if (x1dxdlnevtfbhm.x231if4rvsvptj(var1, var9, false, var2)) {
                  var11.forEach(var1x -> var2.method_35199().method_5491(var1x));
                  var2.method_6104(class_1268.field_5808);
                  this.x35hjgfi0ny5oi++;
                  this.x1lbhfpli59w62 = var3;
                  this.x9380ui9wkv3h(var1, var9);
               }

               return true;
            }
         }
      }

      return false;
   }

   private void x2ibslq4rhdko0(class_3218 var1, class_1646 var2) {
      for (int var3 = 0; var3 < 8; var3++) {
         int var4 = Math.floorMod(this.xc9ckqw7lv8b1++, 2401);
         int var5 = var4 % 49 - 24;
         int var6 = var4 / 49 - 24;
         if (Math.max(Math.abs(var5), Math.abs(var6)) >= 14) {
            int var7 = this.xy1q9c188jgfl.core().method_10263() + var5;
            int var8 = this.xy1q9c188jgfl.core().method_10260() + var6;
            if (var1.method_14178().method_12123(var7 >> 4, var8 >> 4)) {
               class_2338 var9 = new class_2338(var7, var1.method_8624(class_2903.field_13203, var7, var8), var8);
               if (xe9s6y96sy1nw(var1, var9) && xe9s6y96sy1nw(var1, var9.method_10084()) && this.x2koaf0y8uk4zf(var1, var9.method_10074())) {
                  boolean var10 = true;

                  for (class_2338 var12 : class_2338.method_10097(var9.method_10069(-3, -1, -3), var9.method_10069(3, 2, 3))) {
                     class_2680 var13 = var1.method_8320(var12);
                     if (var13.method_27852(class_2246.field_10194)
                        || var1.method_8321(var12) != null
                        || !var13.method_26215() && !var13.method_45474() && !x2ql14ex7ui8sk(var13) && !var13.method_26164(class_3481.field_15503)) {
                        var10 = false;
                        break;
                     }
                  }

                  if (var10 && this.x378g3z86pefiu(var1, var2, var9)) {
                     this.xy1q9c188jgfl.x31ow2tlg0dm5w().add(var9);
                     this.xy1q9c188jgfl.xmskodayog7rh(Math.abs(var5) > Math.abs(var6) ? (var5 > 0 ? 1 : 3) : (var6 > 0 ? 2 : 0));
                     x2n55y2tom37h2.xchl1k1tnh3w8(var1).method_80();
                     this.x1lbhfpli59w62 = var1.method_8503().method_3780();
                     return;
                  }
               }
            }
         }
      }
   }

   private boolean x2clgblb4ftzb2(class_3218 var1, class_1646 var2) {
      for (int var3 = 0; var3 < 512; var3++) {
         if (this.x3hlg4mlj93nm0(var1, var2, var2.method_24515().method_10081((class_2382)x1fgwiff13cmf2.get(var3)))) {
            return true;
         }
      }

      if (this.x3ima85yg8u7pt == null || this.xtas7exwnkweh >= x1fgwiff13cmf2.size() || this.x3ima85yg8u7pt.method_10262(var2.method_24515()) > 1024.0) {
         this.x3ima85yg8u7pt = var2.method_24515();
         this.xtas7exwnkweh = 0;
      }

      for (int var5 = 0; var5 < 1024 && this.xtas7exwnkweh < x1fgwiff13cmf2.size(); var5++) {
         class_2338 var4 = this.x3ima85yg8u7pt.method_10081((class_2382)x1fgwiff13cmf2.get(this.xtas7exwnkweh++));
         if (this.x3hlg4mlj93nm0(var1, var2, var4)) {
            return true;
         }
      }

      return false;
   }

   private boolean x3hlg4mlj93nm0(class_3218 var1, class_1646 var2, class_2338 var3) {
      if (this.x1mcvl1k31xh98.getOrDefault(var3, 0L) > var1.method_8503().method_3780()) {
         return false;
      } else if (!this.xy1q9c188jgfl.x31ow2tlg0dm5w().contains(var3.method_10084())
         && !this.x1m6docvvu7vog.contains(var3.method_10084())
         && !this.x2rtvy1700fh69(var3)) {
         if (!var1.method_14178().method_12123(var3.method_10263() >> 4, var3.method_10260() >> 4)) {
            return false;
         } else {
            class_2680 var4 = var1.method_8320(var3);
            if (!x1pn4sqnjt1xiv(var4) || !this.x2koaf0y8uk4zf(var1, var3) || !x2pv6ejqyq6ou5(var2, var4)) {
               return false;
            } else if (!this.xy1q9c188jgfl.x31ow2tlg0dm5w().isEmpty() && this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() < 20) {
               return false;
            } else {
               if (!this.xy1q9c188jgfl.x31ow2tlg0dm5w().isEmpty() && this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() < 32) {
                  class_2338 var5 = nextStep(this.xy1q9c188jgfl, var1.method_31607());
                  if (var3.equals(var5) || var3.equals(var5.method_10084()) || var3.equals(var5.method_10086(2))) {
                     return false;
                  }
               }

               for (class_2350 var6 : class_2353.field_11062) {
                  class_2338 var7 = var3.method_10093(var6);
                  if (xe9s6y96sy1nw(var1, var7)
                     && xe9s6y96sy1nw(var1, var7.method_10084())
                     && x19i92dflbkly4(var1, var7.method_10074())
                     && (var7.method_10262(var2.method_24515()) <= 4.0 || this.x378g3z86pefiu(var1, var2, var7))) {
                     this.x39r1px550mewh = var3;
                     this.x1co9a4owy6j0p = var7;
                     return true;
                  }
               }

               boolean var9 = this.xy1q9c188jgfl.x31ow2tlg0dm5w().isEmpty()
                  || var2.method_24515().method_10262((class_2382)this.xy1q9c188jgfl.x31ow2tlg0dm5w().getLast()) <= 9.0
                  || !this.x1m6docvvu7vog.isEmpty() && var2.method_24515().method_10262((class_2382)this.x1m6docvvu7vog.getLast()) <= 9.0;
               boolean var10 = var4.method_27852(class_2246.field_10212)
                  || var4.method_27852(class_2246.field_27120)
                  || var4.method_27852(class_2246.field_10418)
                     && this.x35hjgfi0ny5oi < 16
                     && var3.method_10262(var2.method_24515()) <= 64.0
                     && x2n55y2tom37h2.x1mlne8tl8k5ag(var1, this.xy1q9c188jgfl, var0 -> var0.method_31574(class_1802.field_8713)) < 8;
               if (var9
                  && (var2.method_31478() <= 48 || var10)
                  && var3.method_10264() <= var2.method_31478() + 4
                  && (this.x1pyrwt4gvtvim == null || var3.method_10262(var2.method_24515()) < this.x1pyrwt4gvtvim.method_10262(var2.method_24515()))) {
                  this.x1pyrwt4gvtvim = var3.method_10062();
               }

               return false;
            }
         }
      } else {
         return false;
      }
   }

   private static boolean x1pn4sqnjt1xiv(class_2680 var0) {
      return class_7923.field_41175.method_10221(var0.method_26204()).method_12832().endsWith("_ore");
   }

   private class_2338 x7zvchtwcbs2l(class_2338 var1, class_2338 var2) {
      int var3 = var2.method_10263() - var1.method_10263();
      int var4 = var2.method_10260() - var1.method_10260();
      if (var3 == 0 && var4 == 0) {
         if (var2.method_10264() == var1.method_10264()) {
            return null;
         } else {
            int var9 = var2.method_10264() > var1.method_10264() ? 1 : -1;

            for (class_2350 var7 : class_2353.field_11062) {
               class_2338 var8 = var1.method_10093(var7).method_10069(0, var9, 0);
               if (!this.xy1q9c188jgfl.x31ow2tlg0dm5w().contains(var8) && !this.x1m6docvvu7vog.contains(var8)) {
                  return var8;
               }
            }

            return null;
         }
      } else {
         class_2350 var5 = Math.abs(var3) >= Math.abs(var4)
            ? (var3 > 0 ? class_2350.field_11034 : class_2350.field_11039)
            : (var4 > 0 ? class_2350.field_11035 : class_2350.field_11043);
         return var1.method_10093(var5)
            .method_10069(0, var2.method_10264() < var1.method_10264() - 1 ? -1 : (var2.method_10264() > var1.method_10264() + 1 ? 1 : 0), 0);
      }
   }

   private static List<class_2338> x3cil34rc17d8m() {
      var var0 = new ArrayList<class_2338>();

      for (int var1 = -32; var1 <= 32; var1++) {
         for (int var2 = -12; var2 <= 12; var2++) {
            for (int var3 = -32; var3 <= 32; var3++) {
               var0.add(new class_2338(var1, var2, var3));
            }
         }
      }

      var0.sort(Comparator.comparingDouble(var0x -> var0x.method_10262(class_2338.field_10980)));
      return List.copyOf(var0);
   }

   private boolean x27hg6cma7yaw5(class_3218 var1, class_1646 var2) {
      while (!this.x26z6s4l7xpegw.isEmpty()) {
         class_2338 var3 = this.x26z6s4l7xpegw.removeFirst();
         if (x1pn4sqnjt1xiv(var1.method_8320(var3)) && !this.x2rtvy1700fh69(var3)) {
            if (this.x3hlg4mlj93nm0(var1, var2, var3)) {
               return true;
            }

            if (this.x1pyrwt4gvtvim != null) {
               return false;
            }
         }
      }

      return false;
   }

   private void x9380ui9wkv3h(class_3218 var1, class_2338 var2) {
      for (int var3 = -1; var3 <= 1; var3++) {
         for (int var4 = -1; var4 <= 1; var4++) {
            for (int var5 = -1; var5 <= 1; var5++) {
               if (var3 != 0 || var4 != 0 || var5 != 0) {
                  class_2338 var6 = var2.method_10069(var3, var4, var5).method_10062();
                  if (x1pn4sqnjt1xiv(var1.method_8320(var6)) && !this.x26z6s4l7xpegw.contains(var6)) {
                     this.x26z6s4l7xpegw.addLast(var6);
                  }
               }
            }
         }
      }
   }

   private boolean x3kbhcj0gn3eds(class_3218 var1, class_2338 var2) {
      while (!this.x1wi4zdglz9eff.isEmpty()) {
         class_2338 var14 = this.x1wi4zdglz9eff.removeFirst();
         if (!xe9s6y96sy1nw(var1, var14) && this.x2koaf0y8uk4zf(var1, var14)) {
            this.x39r1px550mewh = var14;
            this.x1co9a4owy6j0p = var2;
            this.x2b8i0pzdq33m8(var14);
            return true;
         }
      }

      int var3 = this.xy1q9c188jgfl.x31ow2tlg0dm5w().size();
      if (var3 >= 2 && this.x3alovr2tshkm1 != var3) {
         this.x3alovr2tshkm1 = var3;
         class_2338 var4 = this.xy1q9c188jgfl.x31ow2tlg0dm5w().get(var3 - 2);
         int var5 = Integer.compare(var2.method_10263(), var4.method_10263());
         int var6 = Integer.compare(var2.method_10260(), var4.method_10260());
         if (var5 == 0 && var6 == 0) {
            return false;
         } else {
            int var7 = -var6;
            int var8 = var5;

            for (int var12 : new int[]{-1, 1}) {
               for (int var13 = 0; var13 <= 1; var13++) {
                  this.x1wi4zdglz9eff.addLast(var2.method_10069(var7 * var12, var13, var8 * var12).method_10062());
               }
            }

            return this.x3kbhcj0gn3eds(var1, var2);
         }
      } else {
         return false;
      }
   }

   private void x2b8i0pzdq33m8(class_2338 var1) {
      this.x39r1px550mewh = var1;
   }

   private boolean xesfei3h73qdo(class_3218 var1, class_2338 var2) {
      if (var1.method_14178().method_12123(var2.method_10263() >> 4, var2.method_10260() >> 4) && x19i92dflbkly4(var1, var2.method_10074())) {
         for (int var3 = 0; var3 <= 2; var3++) {
            if (!xe9s6y96sy1nw(var1, var2.method_10086(var3)) && !this.x2koaf0y8uk4zf(var1, var2.method_10086(var3))) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   private boolean x2koaf0y8uk4zf(class_3218 var1, class_2338 var2) {
      if (this.xy1q9c188jgfl.x31ow2tlg0dm5w().contains(var2.method_10084()) || this.x1m6docvvu7vog.contains(var2.method_10084())) {
         return false;
      } else if (!x1dxdlnevtfbhm.x13f7be5vlapn7(var1, var2)) {
         return false;
      } else if (x20rghnsl2yowl.x239t1m3hfi74d(var1, var2)) {
         return false;
      } else if (x2ql14ex7ui8sk(var1.method_8320(var2)) && var1.method_8321(var2) == null) {
         for (class_2350 var6 : class_2350.values()) {
            class_2338 var7 = var2.method_10093(var6);
            if (!var1.method_14178().method_12123(var7.method_10263() >> 4, var7.method_10260() >> 4)
               || !var1.method_8316(var7).method_15769()
               || var6 == class_2350.field_11036
                  && (var1.method_8320(var7).method_27852(class_2246.field_10255) || var1.method_8320(var7).method_26164(class_3481.field_15466))) {
               return false;
            }
         }

         for (x2arpn38dclo1g var9 : this.xy1q9c188jgfl.x1ja61emeln2hc()) {
            class_3341 var10 = var9.x24kbqv6g4kkrg();
            if (var2.method_10264() >= var10.method_35416() - 5
               && var2.method_10263() >= var10.method_35415() - 3
               && var2.method_10263() <= var10.method_35418() + 3
               && var2.method_10260() >= var10.method_35417() - 3
               && var2.method_10260() <= var10.method_35420() + 3) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   private static boolean x2ql14ex7ui8sk(class_2680 var0) {
      return x1dxdlnevtfbhm.xroinv4hk6l7h(var0) || x1pn4sqnjt1xiv(var0);
   }

   private static boolean xe9s6y96sy1nw(class_3218 var0, class_2338 var1) {
      return var0.method_8316(var1).method_15769() && var0.method_8320(var1).method_26220(var0, var1).method_1110();
   }

   private static boolean x19i92dflbkly4(class_3218 var0, class_2338 var1) {
      return var0.method_8316(var1).method_15769() && var0.method_8320(var1).method_26206(var0, var1, class_2350.field_11036);
   }

   private boolean x2rtvy1700fh69(class_2338 var1) {
      double var2 = (double)var1.method_10263() - this.xy1q9c188jgfl.core().method_10263();
      double var4 = (double)var1.method_10260() - this.xy1q9c188jgfl.core().method_10260();
      return var2 * var2 + var4 * var4 > (double)(this.xy1q9c188jgfl.radius() - 4) * (this.xy1q9c188jgfl.radius() - 4);
   }

   private static boolean x2pv6ejqyq6ou5(class_1646 var0, class_2680 var1) {
      class_1799 var2 = var0.method_6047();
      boolean var3 = var2.method_31574(class_1802.field_8403) || var2.method_31574(class_1802.field_8377) || var2.method_31574(class_1802.field_22024);
      boolean var4 = var3 || var2.method_31574(class_1802.field_8387);
      String var5 = class_7923.field_41175.method_10221(var1.method_26204()).method_12832();
      if (var5.contains("diamond") || var5.contains("gold") || var5.contains("redstone") || var5.contains("emerald")) {
         return var3;
      } else {
         return !var5.contains("iron") && !var5.contains("copper") ? true : var4;
      }
   }

   private static class_1792 x142qgo06bbcrm(class_2680 var0) {
      String var1 = class_7923.field_41175.method_10221(var0.method_26204()).method_12832();
      if (var1.contains("diamond")) {
         return class_1802.field_8477;
      } else if (var1.contains("emerald")) {
         return class_1802.field_8687;
      } else if (var1.contains("redstone")) {
         return class_1802.field_8725;
      } else if (var1.contains("lapis")) {
         return class_1802.field_8759;
      } else if (var1.contains("copper")) {
         return class_1802.field_33401;
      } else if (var1.contains("gold")) {
         return class_1802.field_33402;
      } else if (var1.contains("iron")) {
         return class_1802.field_33400;
      } else if (var1.contains("coal")) {
         return class_1802.field_8713;
      } else if (var0.method_26164(class_3481.field_15466)
         || var0.method_26164(class_3481.field_36265)
         || var0.method_27852(class_2246.field_10255)
         || var0.method_27852(class_2246.field_9979)
         || var0.method_27852(class_2246.field_10344)
         || var0.method_27852(class_2246.field_10491)) {
         return var0.method_26204().method_8389();
      } else if (var0.method_27852(class_2246.field_28888)) {
         return class_1802.field_29025;
      } else {
         return var0.method_26164(class_3481.field_29822) ? class_1802.field_8831 : class_1802.field_20412;
      }
   }

   private static boolean x3suldnm75afk3(class_1799 var0) {
      return !var0.method_7960()
         && !var0.method_31573(class_3489.field_42614)
         && !var0.method_31574(class_1802.field_8810)
         && x2n55y2tom37h2.xhahxgpa08yc9(var0) == 0;
   }

   private static int xncu5r00mkmgq(class_1646 var0, class_1792 var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var0.method_35199().method_5439(); var3++) {
         if (var0.method_35199().method_5438(var3).method_31574(var1)) {
            var2 += var0.method_35199().method_5438(var3).method_7947();
         }
      }

      return var2;
   }

   private static int x215m1zmqihw78(class_1646 var0) {
      return xncu5r00mkmgq(var0, class_1802.field_33400)
         + xncu5r00mkmgq(var0, class_1802.field_33401)
         + xncu5r00mkmgq(var0, class_1802.field_33402)
         + xncu5r00mkmgq(var0, class_1802.field_8477)
         + xncu5r00mkmgq(var0, class_1802.field_8687)
         + xncu5r00mkmgq(var0, class_1802.field_8725)
         + xncu5r00mkmgq(var0, class_1802.field_8759)
         + xncu5r00mkmgq(var0, class_1802.field_8713);
   }

   private static boolean xaavk81ye4wra(class_1646 var0, class_1792 var1) {
      for (int var2 = 0; var2 < var0.method_35199().method_5439(); var2++) {
         class_1799 var3 = var0.method_35199().method_5438(var2);
         if (var3.method_7960() || var3.method_31574(var1) && var3.method_7947() < var3.method_7914()) {
            return true;
         }
      }

      return false;
   }

   private boolean x378g3z86pefiu(class_3218 var1, class_1646 var2, class_2338 var3) {
      if (!x2n55y2tom37h2.x5ll9elqpk1a4(var1)) {
         return false;
      } else {
         class_11 var4 = var2.method_5942().method_6348(var3, 0);
         return var4 != null && var4.method_21655();
      }
   }

   private void x2qi4jek408dcr(class_3218 var1, class_1646 var2, class_2338 var3) {
      long var4 = var1.method_8503().method_3780();
      if (this.x2qljtijql8vba != null && var4 < this.x1bhavtwhoa4l5) {
         x1c1r5c6a13gtl.x2wzylgizxltef(var1, var2, this.x2qljtijql8vba, 0.85);
      } else {
         if (!var3.equals(this.x5s8pk56x49d)) {
            this.x5s8pk56x49d = var3.method_10062();
            this.x1vajhas144pja = Double.MAX_VALUE;
            this.x2c83xgjkd1pgw = var4;
         }

         double var6 = var2.method_5649(var3.method_10263() + 0.5, var3.method_10264(), var3.method_10260() + 0.5);
         if (var6 + 0.5 < this.x1vajhas144pja) {
            this.x1vajhas144pja = var6;
            this.x2c83xgjkd1pgw = var4;
         }

         if (var4 - this.x2c83xgjkd1pgw > 60L
            && var2.method_24515().method_10262(var3) <= 16.0
            && !var1.method_8333(var2, var2.method_5829().method_1014(1.2), var1x -> var1x instanceof class_1646 && var1x.method_5628() < var2.method_5628())
               .isEmpty()) {
            for (class_2350 var9 : class_2353.field_11062) {
               class_2338 var10 = var2.method_24515().method_10093(var9);
               if (!var10.equals(var3)
                  && x1c1r5c6a13gtl.x9bnd6e2jczhd(var1, var10)
                  && var1.method_8333(var2, new class_238(var10).method_1014(0.1), var0 -> var0 instanceof class_1309).isEmpty()) {
                  this.x2qljtijql8vba = var10;
                  this.x1bhavtwhoa4l5 = var4 + 40L;
                  x1c1r5c6a13gtl.x2wzylgizxltef(var1, var2, var10, 0.85);
                  return;
               }
            }
         }

         x1c1r5c6a13gtl.x2wzylgizxltef(var1, var2, var3, 1.05);
         if (this.x1ajk4ev1s3vmk == xmq1yravtjp1k.WORK && var4 - this.x2c83xgjkd1pgw > 400L) {
            if (this.xdyvztdlmgr2v == 0 && this.x39r1px550mewh == null && xocihbcrvdo4w(var2) == 0 && this.x1xbvmwg584cth(var1, var2)) {
               return;
            }

            if (this.x39r1px550mewh != null) {
               this.x1mcvl1k31xh98.put(this.x39r1px550mewh, var4 + 1200L);
            }

            if (this.x1pyrwt4gvtvim != null) {
               this.x1mcvl1k31xh98.put(this.x1pyrwt4gvtvim, var4 + 6000L);
               this.x1pyrwt4gvtvim = null;
               this.x26z6s4l7xpegw.clear();
               this.xgxy3geynmx1k = false;
            }

            x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "недоступный забой: возврат с грузом");
            this.xhzkfb8tfrmu6(var2);
         }
      }
   }

   private boolean x1xbvmwg584cth(class_3218 var1, class_1646 var2) {
      int var3 = Math.max(0, this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() - 8);

      for (int var4 = this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() - 1; var4 >= var3; var4--) {
         class_2338 var5 = this.xy1q9c188jgfl.x31ow2tlg0dm5w().get(var4);
         if (x1c1r5c6a13gtl.x9bnd6e2jczhd(var1, var5)
            && var1.method_8333(var2, new class_238(var5).method_1014(0.35), var0 -> var0.method_5805() && var0 instanceof class_1309).isEmpty()) {
            var2.method_5942().method_6340();
            var2.method_5808(var5.method_10263() + 0.5, var5.method_10264(), var5.method_10260() + 0.5, var2.method_36454(), var2.method_36455());
            var2.method_18799(class_243.field_1353);
            var2.method_38785();
            this.xdyvztdlmgr2v = var4;
            this.x3034xvdt9o8yg = var4;
            this.x5s8pk56x49d = null;
            this.x2c83xgjkd1pgw = this.x1lbhfpli59w62 = var1.method_8503().method_3780();
            x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "восстановление доступа к шахтному маршруту");
            return true;
         }
      }

      return false;
   }

   private static boolean x1r7nkw0jt7ym0(class_1646 var0, class_2338 var1) {
      return Math.abs(var0.method_23318() - var1.method_10264()) < 0.6
         && var0.method_5649(var1.method_10263() + 0.5, var1.method_10264(), var1.method_10260() + 0.5) < 1.3;
   }

   private static boolean x3b13cjb6ycs5u(class_1646 var0, List<class_1799> var1) {
      class_1277 var2 = new class_1277(var0.method_35199().method_5439());

      for (int var3 = 0; var3 < var2.method_5439(); var3++) {
         var2.method_5447(var3, var0.method_35199().method_5438(var3).method_7972());
      }

      for (class_1799 var4 : var1) {
         if (!var2.method_5491(var4.method_7972()).method_7960()) {
            return false;
         }
      }

      return true;
   }

   private int x28tnsle11zyqe(class_1646 var1) {
      int var2 = 0;
      double var3 = Double.MAX_VALUE;

      for (int var5 = 0; var5 < this.xy1q9c188jgfl.x31ow2tlg0dm5w().size(); var5++) {
         double var6 = this.xy1q9c188jgfl.x31ow2tlg0dm5w().get(var5).method_10262(var1.method_24515());
         if (var6 < var3) {
            var3 = var6;
            var2 = var5;
         }
      }

      return var2;
   }

   private void xhzkfb8tfrmu6(class_1646 var1) {
      this.xgxy3geynmx1k = false;
      this.x342hqz5dxukyd = Math.max(0, this.x1m6docvvu7vog.size() - 1);
      this.x1ajk4ev1s3vmk = xmq1yravtjp1k.RETURN;
      this.x39r1px550mewh = null;
      this.x5s8pk56x49d = null;
      this.x340ppe230jyu = this.x1m6docvvu7vog.isEmpty() ? this.x28tnsle11zyqe(var1) : Math.max(0, this.x2bkom4nbrpupj);
      if (this.x1m6docvvu7vog.isEmpty()
         && !this.xy1q9c188jgfl.x31ow2tlg0dm5w().isEmpty()
         && this.xy1q9c188jgfl.x31ow2tlg0dm5w().get(this.x340ppe230jyu).method_10262(var1.method_24515()) > 9.0) {
         this.x340ppe230jyu = 0;
      }

      this.xhvgstwe661u5 = ((class_3218)var1.method_73183()).method_8503().method_3780();
      this.x1uepbjw0wv8fc = Double.MAX_VALUE;
      this.x1vlro08p2yyfr = var1.method_73189();
   }

   private void x2idgvhct1beze(class_1646 var1, class_2338 var2) {
      var1.method_18868().method_18878(xxao083dbo7ag.x15kki2mjvh7tw, var2);
   }

   private static int xocihbcrvdo4w(class_1646 var0) {
      int var1 = 0;

      for (int var2 = 0; var2 < var0.method_35199().method_5439(); var2++) {
         class_1799 var3 = var0.method_35199().method_5438(var2);
         if (x3suldnm75afk3(var3)) {
            var1 += var3.method_7947();
         }
      }

      return var1;
   }

   public String debugState(class_1646 var1) {
      return (this.x1ajk4ev1s3vmk == null ? "ожидание" : this.x1ajk4ev1s3vmk.name().toLowerCase(Locale.ROOT))
         + ", выкопано="
         + this.x35hjgfi0ny5oi
         + ", груз="
         + xocihbcrvdo4w(var1)
         + ", руда="
         + x215m1zmqihw78(var1)
         + ", маршрут="
         + (this.xy1q9c188jgfl == null ? 0 : this.xy1q9c188jgfl.x31ow2tlg0dm5w().size())
         + ", точка="
         + this.xdyvztdlmgr2v
         + ", пропущено="
         + this.x1mcvl1k31xh98.size()
         + ", target="
         + this.x39r1px550mewh
         + ", stand="
         + this.x1co9a4owy6j0p
         + ", ore="
         + this.x1pyrwt4gvtvim
         + ", detour="
         + this.x1m6docvvu7vog.size()
         + ", return="
         + this.x340ppe230jyu;
   }

   protected void finishRunning(class_3218 var1, class_1646 var2, long var3) {
      this.x1lb0jykmu89gj.xupxzzyodxtwj(var1, var2);
      var2.method_5942().method_6340();
      var2.method_18868().method_18875(xxao083dbo7ag.x15kki2mjvh7tw);
      this.xy1q9c188jgfl = null;
      this.x39r1px550mewh = null;
   }

   @Override
   protected boolean method_18919(class_3218 var1, class_1646 var2) {
      return shouldRun(var1, var2);
   }

   @Override
   protected boolean method_18927(class_3218 var1, class_1646 var2, long var3) {
      return shouldKeepRunning(var1, var2, var3);
   }

   @Override
   protected void method_18926(class_3218 var1, class_1646 var2, long var3) {
      finishRunning(var1, var2, var3);
   }

   @Override
   protected void method_18924(class_3218 var1, class_1646 var2, long var3) {
      keepRunning(var1, var2, var3);
   }

   @Override
   protected void method_18920(class_3218 var1, class_1646 var2, long var3) {
      run(var1, var2, var3);
   }
}
