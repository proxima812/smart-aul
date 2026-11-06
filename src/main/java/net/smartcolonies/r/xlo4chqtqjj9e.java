package net.smartcolonies.r;

import com.google.common.collect.ImmutableMap;
import net.minecraft.class_1268;
import net.minecraft.class_1646;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_3218;
import net.minecraft.class_4097;
import net.minecraft.class_4140;
import net.minecraft.class_4141;

public final class xlo4chqtqjj9e extends class_4097<class_1646> {
   private x15kgoaq6i3m6i xnwtgacms2tpk;
   private class_2338 x307t38ql367ok;
   private long x1hqs3il4h0q4l;
   private final x20lc7jle5mw9c x2ws1oqe8gabg8 = new x20lc7jle5mw9c();
   private class_1799 x1zzy1362cikeg = class_1799.field_8037;

   public xlo4chqtqjj9e() {
      super(ImmutableMap.of(class_4140.field_18445, class_4141.field_18458), 2400);
   }

   protected boolean shouldRun(class_3218 var1, class_1646 var2) {
      if (!var2.method_6109() && !var2.method_6113() && !var2.method_18009() && x1dxdlnevtfbhm.x35ev8l9vc9uk7(var1)) {
         this.xnwtgacms2tpk = x2n55y2tom37h2.x3psqezd5jem54(var1, var2.method_24515())
            .filter(var1x -> var1x.roleOf(var2.method_5667()) == x1rxiy9mtu5b3h.BUILDER && var1x.x2s37os1kht7vr())
            .orElse(null);
         return this.xnwtgacms2tpk != null;
      } else {
         return false;
      }
   }

   protected boolean shouldKeepRunning(class_3218 var1, class_1646 var2, long var3) {
      return this.xnwtgacms2tpk != null
         && this.xnwtgacms2tpk.roleOf(var2.method_5667()) == x1rxiy9mtu5b3h.BUILDER
         && this.xnwtgacms2tpk.x2s37os1kht7vr()
         && !var2.method_6113()
         && !var2.method_18009();
   }

   protected void run(class_3218 var1, class_1646 var2, long var3) {
      this.x307t38ql367ok = null;
      this.x1hqs3il4h0q4l = var3;
      this.x1zzy1362cikeg = var2.method_6047();
   }

   protected void keepRunning(class_3218 var1, class_1646 var2, long var3) {
      if (this.x307t38ql367ok == null) {
         if (var3 < this.x1hqs3il4h0q4l) {
            return;
         }

         this.x307t38ql367ok = x2n55y2tom37h2.x2l4eb2ibsf118(var1, this.xnwtgacms2tpk).orElse(null);
         this.x1hqs3il4h0q4l = var3 + 100L;
         if (this.x307t38ql367ok == null) {
            x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "нет безопасного места для начального склада");
            return;
         }
      }

      if (x1c1r5c6a13gtl.xps35ehel6nkk(var2, this.x307t38ql367ok, var3)) {
         this.x307t38ql367ok = null;
      } else {
         var2.method_6122(class_1268.field_5808, new class_1799(class_1802.field_8106));
         if (var2.method_24515().method_10262(this.x307t38ql367ok) > 9.0) {
            x1c1r5c6a13gtl.x2wzylgizxltef(var1, var2, this.x307t38ql367ok, 0.65);
            x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "установка начального склада: " + this.x307t38ql367ok.method_23854());
         } else if (this.x2ws1oqe8gabg8.xpohy1q30uxlk(var1, var2, this.x307t38ql367ok, 20, false)) {
            if (!x2n55y2tom37h2.xt55z8grtuvpq(var1, this.xnwtgacms2tpk, this.x307t38ql367ok, x2jj5edkiu5orf.GENERAL)) {
               this.x307t38ql367ok = null;
            } else {
               this.xnwtgacms2tpk.x1z3izo4s1trbu();
               x2n55y2tom37h2.xchl1k1tnh3w8(var1).method_80();
               x2n55y2tom37h2.x2cl1crde2rnxy(var1, this.xnwtgacms2tpk, new class_1799(class_1802.field_8583, 16));
               x2n55y2tom37h2.x2cl1crde2rnxy(var1, this.xnwtgacms2tpk, new class_1799(class_1802.field_8317, 16));
               x2vaj7rsm57i4n.x2t98zlq4lu49t(var2, "поставлен начальный двойной склад", 2);
               var2.method_6104(class_1268.field_5808);
               var2.method_6122(class_1268.field_5808, this.x1zzy1362cikeg);
               this.x1zzy1362cikeg = class_1799.field_8037;
            }
         }
      }
   }

   protected void finishRunning(class_3218 var1, class_1646 var2, long var3) {
      this.x2ws1oqe8gabg8.xupxzzyodxtwj(var1, var2);
      x1c1r5c6a13gtl.x2fxoqmbs0henb(var2);
      this.xnwtgacms2tpk = null;
      this.x307t38ql367ok = null;
      if (var2.method_6047().method_31574(class_1802.field_8106)) {
         var2.method_6122(class_1268.field_5808, this.x1zzy1362cikeg);
      }

      this.x1zzy1362cikeg = class_1799.field_8037;
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
