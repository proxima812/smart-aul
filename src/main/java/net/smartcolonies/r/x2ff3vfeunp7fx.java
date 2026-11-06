package net.smartcolonies.r;

import com.google.common.collect.ImmutableMap;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.class_11;
import net.minecraft.class_1268;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1439;
import net.minecraft.class_1646;
import net.minecraft.class_1657;
import net.minecraft.class_1667;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1890;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_3218;
import net.minecraft.class_3489;
import net.minecraft.class_4097;
import net.minecraft.class_4140;
import net.minecraft.class_4141;
import net.minecraft.class_9334;
import net.minecraft.class_2902.class_2903;

public final class x2ff3vfeunp7fx extends class_4097<class_1646> {
   private x15kgoaq6i3m6i x9c3bb07a70ik;
   private class_1309 x2iis7neec0fi2;
   private long x20qrt67vkcoed;
   private long x26ep0h4mwdyaa;
   private long x21bf69nq5jtr5;
   private long x17fuqwxy7vxi0;

   public x2ff3vfeunp7fx() {
      super(ImmutableMap.of(class_4140.field_18445, class_4141.field_18458), 1200);
   }

   protected boolean shouldRun(class_3218 var1, class_1646 var2) {
      if (!var2.method_6109() && !var2.method_6113() && !var2.method_18009()) {
         var var3 = x2n55y2tom37h2.x3psqezd5jem54(var1, var2.method_24515())
            .filter(var1x -> var1x.roleOf(var2.method_5667()) == x1rxiy9mtu5b3h.GUARD);
         if (var3.isEmpty()) {
            return false;
         } else {
            this.x9c3bb07a70ik = (x15kgoaq6i3m6i)var3.get();
            this.x2iis7neec0fi2 = this.x2h60gnu0hedrs(var2)
               .filter(var1x -> x37idyr79ed75m.x1bj0phudo3a5m(var1x) && x37idyr79ed75m.xs8awvrrlhug9(this.x9c3bb07a70ik, var1x))
               .orElseGet(() -> x37idyr79ed75m.x3kaxmxx3tadjj(var1, this.x9c3bb07a70ik).orElse(null));
            boolean var4 = this.x9c3bb07a70ik.x3klfyffbbfab2() != Long.MIN_VALUE && var1.method_75260() - this.x9c3bb07a70ik.x3klfyffbbfab2() <= 100L;
            return this.x2iis7neec0fi2 != null || var4 || var2.method_6032() < var2.method_6063() * 0.3F;
         }
      } else {
         return false;
      }
   }

   protected boolean shouldKeepRunning(class_3218 var1, class_1646 var2, long var3) {
      return !var2.method_6113()
         && !var2.method_18009()
         && this.x9c3bb07a70ik != null
         && this.x9c3bb07a70ik.roleOf(var2.method_5667()) == x1rxiy9mtu5b3h.GUARD
         && (
            this.x2iis7neec0fi2 != null && this.x2iis7neec0fi2.method_5805() && x37idyr79ed75m.xs8awvrrlhug9(this.x9c3bb07a70ik, this.x2iis7neec0fi2)
               || this.x9c3bb07a70ik.x3klfyffbbfab2() != Long.MIN_VALUE && var3 - this.x9c3bb07a70ik.x3klfyffbbfab2() <= 100L
               || var2.method_6032() < var2.method_6063() * 0.6F
         );
   }

   protected void run(class_3218 var1, class_1646 var2, long var3) {
      this.x20qrt67vkcoed = var3;
      this.x26ep0h4mwdyaa = var3;
      this.x21bf69nq5jtr5 = var3;
      this.x17fuqwxy7vxi0 = var3;
      if (var2.method_6047().method_7960()) {
         x2n55y2tom37h2.x3lxwdirnn77qc(
               var1,
               this.x9c3bb07a70ik,
               var0 -> var0.method_31573(class_3489.field_42611) || var0.method_31573(class_3489.field_42612) || var0.method_31573(class_3489.field_48311)
            )
            .ifPresent(var1x -> var2.method_5673(class_1304.field_6173, var1x));
      }
   }

   protected void keepRunning(class_3218 var1, class_1646 var2, long var3) {
      if (!xzszqoikocxnx.x12ghbsk7tys8r(var1, var2, this.x9c3bb07a70ik)) {
         if (var2.method_6032() < var2.method_6063() * 0.3F) {
            this.x2orverzrrwr2f(var1, var2, var3);
         } else {
            if (var3 >= this.x26ep0h4mwdyaa) {
               this.x2iis7neec0fi2 = this.x2h60gnu0hedrs(var2)
                  .filter(var1x -> x37idyr79ed75m.x1bj0phudo3a5m(var1x) && x37idyr79ed75m.xs8awvrrlhug9(this.x9c3bb07a70ik, var1x))
                  .orElseGet(() -> x37idyr79ed75m.x3kaxmxx3tadjj(var1, this.x9c3bb07a70ik).orElse(null));
               this.x26ep0h4mwdyaa = var3 + 10L;
            }

            if (this.x2iis7neec0fi2 == null) {
               var2.method_18868().method_18875(xxao083dbo7ag.x14b5znl8ndblc);
               class_2338 var12 = this.xel4gvy3cgwwh(var1, this.x9c3bb07a70ik, var2);
               if (var3 >= this.x20qrt67vkcoed && var2.method_24515().method_10262(var12) > 36.0 && x2n55y2tom37h2.x5ll9elqpk1a4(var1)) {
                  var2.method_5942().method_6337(var12.method_10263() + 0.5, var12.method_10264(), var12.method_10260() + 0.5, 0.65);
                  this.x20qrt67vkcoed = var3 + 20L;
               }
            } else {
               var2.method_18868().method_18878(xxao083dbo7ag.x14b5znl8ndblc, this.x2iis7neec0fi2);
               double var5 = var2.method_5858(this.x2iis7neec0fi2);
               boolean var7 = var2.method_6065() != null && var2.field_6012 - var2.method_6117() <= 30;
               boolean var8 = var5 >= 25.0 && !var7 && var2.method_6057(this.x2iis7neec0fi2);
               if (var3 >= this.x17fuqwxy7vxi0 && this.x1t6stxd93drq7(var2, var8)) {
                  this.x17fuqwxy7vxi0 = var3 + 20L;
               }

               class_1799 var9 = var2.method_6047();
               var2.method_5988().method_6226(this.x2iis7neec0fi2, 30.0F, 30.0F);
               boolean var10 = var2.method_6079().method_31574(class_1802.field_8255) && (var7 || var5 <= 16.0) && var3 < this.x20qrt67vkcoed;
               if (var10) {
                  float var13 = (float)(
                     Math.toDegrees(
                           Math.atan2(this.x2iis7neec0fi2.method_23321() - var2.method_23321(), this.x2iis7neec0fi2.method_23317() - var2.method_23317())
                        )
                        - 90.0
                  );
                  var2.method_36456(var13);
                  var2.method_5636(var13);
                  var2.method_5847(var13);
                  if (!var2.method_6115() || var2.method_6058() != class_1268.field_5810) {
                     var2.method_6019(class_1268.field_5810);
                  }

                  if (var5 > 4.0 && var3 >= this.x21bf69nq5jtr5) {
                     x18ucnhocy9qn5.x1wi30zgg7nf81(var1, var2, this.x2iis7neec0fi2.method_24515(), 0.72);
                     this.x21bf69nq5jtr5 = var3 + 12L;
                  }
               } else {
                  if (var2.method_6115() && var2.method_6058() == class_1268.field_5810) {
                     var2.method_6075();
                  }

                  if (var9.method_31574(class_1802.field_8102) && var5 <= 225.0 && var2.method_6057(this.x2iis7neec0fi2)) {
                     if (!(var5 < 49.0) && this.x2mc1rt61nbu7q(var1, var2, this.x2iis7neec0fi2)) {
                        var2.method_5942().method_6340();
                     } else if (var3 >= this.x21bf69nq5jtr5) {
                        this.x15umqhn1l74rw(var1, var2, this.x2iis7neec0fi2);
                        this.x21bf69nq5jtr5 = var3 + 12L;
                     }

                     if (var3 >= this.x20qrt67vkcoed && this.x2mc1rt61nbu7q(var1, var2, this.x2iis7neec0fi2)) {
                        this.x1hhdroc5eq3e0(var1, var2, this.x2iis7neec0fi2, var9);
                        this.x20qrt67vkcoed = var3 + 30L;
                     }
                  } else if (var5 > 4.0) {
                     if (var3 >= this.x21bf69nq5jtr5) {
                        x18ucnhocy9qn5.x1wi30zgg7nf81(var1, var2, this.x2iis7neec0fi2.method_24515(), 0.85);
                        this.x21bf69nq5jtr5 = var3 + 12L;
                     }
                  } else if (var3 >= this.x20qrt67vkcoed && var2.method_6057(this.x2iis7neec0fi2)) {
                     class_1282 var11 = xyre2z85qhk7v.xqzpg1xselqv5(var1, var2, this.x2iis7neec0fi2, var2.method_48923().method_48812(var2));
                     if (this.x2iis7neec0fi2.method_64397(var1, var11, xyre2z85qhk7v.x3c7ytg0gca1m6(var1, var2, this.x2iis7neec0fi2, var11))) {
                        class_1890.method_60619(var1, this.x2iis7neec0fi2, var11, var9);
                        var9.method_59979(this.x2iis7neec0fi2, var2);
                     }

                     var2.method_6104(class_1268.field_5808);
                     this.x20qrt67vkcoed = var3 + xyre2z85qhk7v.x3jcrekq98bvm8(var9);
                  }
               }
            }
         }
      }
   }

   private Optional<class_1309> x2h60gnu0hedrs(class_1646 var1) {
      return var1.method_18868().method_18904(xxao083dbo7ag.x14b5znl8ndblc);
   }

   private boolean x1t6stxd93drq7(class_1646 var1, boolean var2) {
      Predicate<class_1799> var3 = var2
         ? var0 -> var0.method_31574(class_1802.field_8102)
         : var0 -> var0.method_31573(class_3489.field_42611) || var0.method_31573(class_3489.field_42612);
      if (var3.test(var1.method_6047())) {
         return false;
      } else {
         for (int var4 = 0; var4 < var1.method_35199().method_5439(); var4++) {
            class_1799 var5 = var1.method_35199().method_5438(var4);
            if (var3.test(var5)) {
               var1.method_6075();
               class_1799 var6 = var5.method_7972();
               var1.method_35199().method_5447(var4, class_1799.field_8037);
               class_1799 var7 = var1.method_6047().method_7972();
               var1.method_5673(class_1304.field_6173, var6);
               if (!var7.method_7960()) {
                  class_1799 var8 = var1.method_35199().method_5491(var7);
                  if (!var8.method_7960()) {
                     var1.method_5775((class_3218)var1.method_73183(), var8);
                  }
               }

               return true;
            }
         }

         return false;
      }
   }

   private void x1hhdroc5eq3e0(class_3218 var1, class_1646 var2, class_1309 var3, class_1799 var4) {
      class_1667 var5 = new class_1667(var1, var2, new class_1799(class_1802.field_8107), var4);
      double var6 = var3.method_23317() - var2.method_23317();
      double var8 = var3.method_23321() - var2.method_23321();
      double var10 = Math.sqrt(var6 * var6 + var8 * var8);
      double var12 = var3.method_23318() + var3.method_17682() * 0.5 - var5.method_23318() + var10 * 0.18;
      var5.method_7485(var6, var12, var8, 1.6F, 5.0F);
      var5.method_7438(4.0);
      var1.method_8649(var5);
      var2.method_6104(class_1268.field_5808);
   }

   protected void finishRunning(class_3218 var1, class_1646 var2, long var3) {
      if (var2.method_6115() && var2.method_6058() == class_1268.field_5810) {
         var2.method_6075();
      }

      x18ucnhocy9qn5.x3oupbjeq14mj8(var2);
      var2.method_18868().method_18875(xxao083dbo7ag.x14b5znl8ndblc);
      this.x9c3bb07a70ik = null;
      this.x2iis7neec0fi2 = null;
   }

   private class_2338 xel4gvy3cgwwh(class_3218 var1, x15kgoaq6i3m6i var2, class_1646 var3) {
      class_238 var4 = new class_238(var2.core()).method_1014(Math.min(var2.radius(), 48));
      return var1.method_8390(
            class_1646.class,
            var4,
            var1x -> var1x.method_5805()
               && var2.roleOf(var1x.method_5667()) == x1rxiy9mtu5b3h.GUARD
               && !var1x.method_6047().method_31574(class_1802.field_8102)
         )
         .stream()
         .min(Comparator.comparing(class_1297::method_5667))
         .filter(var1x -> var1x != var3)
         .<class_2338>map(class_1297::method_24515)
         .orElse(var2.core());
   }

   public static void patrol(class_3218 var0, x15kgoaq6i3m6i var1, class_1646 var2) {
      class_1646 var3 = var0.method_8390(
            class_1646.class,
            x37idyr79ed75m.xx2tq85gza9uw(var1),
            var1x -> var1x.method_5805() && var1.roleOf(var1x.method_5667()) == x1rxiy9mtu5b3h.GUARD
         )
         .stream()
         .min(Comparator.comparing(class_1297::method_5667))
         .orElse(var2);
      class_2338 var4;
      if (var3 != var2) {
         if (var2.method_5858(var3) < 16.0) {
            return;
         }

         var4 = var3.method_24515();
      } else {
         int var5 = (int)(var0.method_75260() / 600L % 4L);
         int var6 = var1.core().method_10263() + (var5 == 0 ? 10 : (var5 == 2 ? -10 : 0));
         int var7 = var1.core().method_10260() + (var5 == 1 ? 10 : (var5 == 3 ? -10 : 0));
         if (!var0.method_14178().method_12123(var6 >> 4, var7 >> 4)) {
            return;
         }

         var4 = new class_2338(var6, var0.method_8624(class_2903.field_13203, var6, var7), var7);
      }

      if (x2n55y2tom37h2.x5ll9elqpk1a4(var0)) {
         var2.method_5942().method_6337(var4.method_10263() + 0.5, var4.method_10264(), var4.method_10260() + 0.5, 0.6);
      }
   }

   private boolean x2mc1rt61nbu7q(class_3218 var1, class_1646 var2, class_1309 var3) {
      class_243 var4 = var2.method_33571();
      class_243 var5 = var3.method_33571();
      return var1.method_8333(
            var2, new class_238(var4, var5).method_1014(1.0), var0 -> var0 instanceof class_1646 || var0 instanceof class_1439 || var0 instanceof class_1657
         )
         .stream()
         .filter(var1x -> var1x != var3)
         .noneMatch(var2x -> var2x.method_5829().method_1014(0.4).method_992(var4, var5).isPresent());
   }

   private void x15umqhn1l74rw(class_3218 var1, class_1646 var2, class_1309 var3) {
      double var4 = var2.method_23317() - var3.method_23317();
      double var6 = var2.method_23321() - var3.method_23321();
      double var8 = Math.max(0.1, Math.sqrt(var4 * var4 + var6 * var6));
      double var10 = (var2.method_5628() & 1) == 0 ? 1.0 : -1.0;

      for (int var12 = 0; var12 < 3; var12++) {
         double var13 = var12 == 0 ? var10 * 2.0 : (var12 == 1 ? -var10 * 4.0 : 0.0);
         int var15 = (int)Math.floor(var2.method_23317() + var4 / var8 * 5.0 - var6 / var8 * var13);
         int var16 = (int)Math.floor(var2.method_23321() + var6 / var8 * 5.0 + var4 / var8 * var13);
         if (var1.method_14178().method_12123(var15 >> 4, var16 >> 4)) {
            class_2338 var17 = new class_2338(var15, var1.method_8624(class_2903.field_13203, var15, var16), var16);
            if (!(Math.abs(var17.method_10264() - var2.method_23318()) > 2.0)
               && var1.method_8316(var17.method_10074()).method_15769()
               && !(var17.method_10262(this.x9c3bb07a70ik.core()) > (double)this.x9c3bb07a70ik.radius() * this.x9c3bb07a70ik.radius())) {
               if (!x2n55y2tom37h2.x5ll9elqpk1a4(var1)) {
                  return;
               }

               class_11 var18 = var2.method_5942().method_6348(var17, 0);
               if (var18 != null && var18.method_21655()) {
                  var2.method_5942().method_6334(var18, 0.95);
                  return;
               }
            }
         }
      }
   }

   private void x2orverzrrwr2f(class_3218 var1, class_1646 var2, long var3) {
      if (!this.x9c3bb07a70ik.x2ad3s8aentmo4().isEmpty()) {
         class_2338 var5 = this.x9c3bb07a70ik.x2ad3s8aentmo4().getFirst();
         x1c1r5c6a13gtl.x2wzylgizxltef(var1, var2, var5, 0.75);
         if (var2.method_5649(var5.method_10263() + 0.5, var5.method_10264(), var5.method_10260() + 0.5) <= 9.0 && var3 >= this.x20qrt67vkcoed) {
            x2n55y2tom37h2.x3lxwdirnn77qc(var1, this.x9c3bb07a70ik, var0 -> var0.method_58694(class_9334.field_50075) != null)
               .ifPresent(var1x -> var2.method_6025(4.0F));
            this.x20qrt67vkcoed = var3 + 40L;
         }
      } else if (x2n55y2tom37h2.x5ll9elqpk1a4(var1)) {
         var2.method_5942()
            .method_6337(this.x9c3bb07a70ik.core().method_10263(), this.x9c3bb07a70ik.core().method_10264(), this.x9c3bb07a70ik.core().method_10260(), 0.75);
      }
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
