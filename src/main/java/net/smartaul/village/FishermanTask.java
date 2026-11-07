package net.smartaul.village;

import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import net.minecraft.class_11;
import net.minecraft.class_1268;
import net.minecraft.class_1299;
import net.minecraft.class_1304;
import net.minecraft.class_1536;
import net.minecraft.class_1646;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2382;
import net.minecraft.class_3218;
import net.minecraft.class_3489;
import net.minecraft.class_4097;
import net.minecraft.class_4140;
import net.minecraft.class_4141;
import net.minecraft.class_2350.class_2353;
import net.minecraft.class_2902.class_2903;

public final class FishermanTask extends class_4097<class_1646> {
   private static final List<class_2338> x2kdfv2w398skl = x3j2bsjy7dlm8n();
   private Colony x2d46f6032ktvg;
   private FishermanPhase x13ycknwfia743;
   private class_2338 x252scecjwbsjc;
   private class_2338 x2n8fs353bluyf;
   private int x3kmuxybabtcha;
   private int x2cuv9elefqg8b;
   private long x1ivrvuxnf7a8r;
   private long x1rc9hdzmwvbv4;
   private boolean x21q585dpo3fow;
   private class_1536 xm389zhbgl3fn;
   private long xapcnyboftre6;

   public FishermanTask() {
      super(ImmutableMap.of(class_4140.field_18445, class_4141.field_18458), 12000);
   }

   protected boolean shouldRun(class_3218 var1, class_1646 var2) {
      if (!var2.method_6109() && !var2.method_6113() && !var2.method_18009() && var1.method_75260() >= this.x1rc9hdzmwvbv4) {
         this.x2d46f6032ktvg = ColonyManager.x3psqezd5jem54(var1, var2.method_24515())
            .filter(var1x -> var1x.roleOf(var2.method_5667()) == ColonyRole.FISHERMAN && !var1x.x2ad3s8aentmo4().isEmpty())
            .orElse(null);
         return this.x2d46f6032ktvg != null;
      } else {
         return false;
      }
   }

   protected void run(class_3218 var1, class_1646 var2, long var3) {
      ActionTrace.worker(var2, "task_start", "FishermanTask", false);
      this.x13ycknwfia743 = FishermanPhase.SUPPLY;
      this.x252scecjwbsjc = this.x2n8fs353bluyf = null;
      this.x3kmuxybabtcha = 0;
      this.x2cuv9elefqg8b = 0;
      this.x1ivrvuxnf7a8r = var3;
      this.x21q585dpo3fow = false;
      this.x32ermkhbb9bv2();
      this.xapcnyboftre6 = var3 + 100L;
   }

   protected boolean shouldKeepRunning(class_3218 var1, class_1646 var2, long var3) {
      return !var2.method_6113()
         && !var2.method_18009()
         && this.x2d46f6032ktvg != null
         && this.x2d46f6032ktvg.roleOf(var2.method_5667()) == ColonyRole.FISHERMAN
         && !this.x21q585dpo3fow;
   }

   protected void keepRunning(class_3218 var1, class_1646 var2, long var3) {
      if (var3 >= this.x1ivrvuxnf7a8r) {
         if (this.x13ycknwfia743 == FishermanPhase.SUPPLY || this.x13ycknwfia743 == FishermanPhase.RETURN) {
            this.xbt4deyrou7q8(var1, var2, var3);
         } else if (this.x13ycknwfia743 == FishermanPhase.SEARCH) {
            if (!this.x38ykb3ggi4dan(var1, var2)) {
               if (this.x3kmuxybabtcha >= x2kdfv2w398skl.size()) {
                  this.x13ycknwfia743 = FishermanPhase.RETURN;
               }
            }
         } else if (this.x252scecjwbsjc != null && var1.method_8320(this.x252scecjwbsjc).method_27852(class_2246.field_10382)) {
            var2.method_18868().method_18878(ColonyMemories.x263ucjykllb1b, this.x252scecjwbsjc);
            if (var2.method_24515().method_10262(this.x2n8fs353bluyf) > 6.0) {
               this.x80alxwfk9adt(var1, var2, this.x2n8fs353bluyf, 0.75);
            } else {
               var2.method_5942().method_6340();
               var2.method_5988()
                  .method_20248(this.x252scecjwbsjc.method_10263() + 0.5, this.x252scecjwbsjc.method_10264() + 0.2, this.x252scecjwbsjc.method_10260() + 0.5);
               if (this.xm389zhbgl3fn == null || this.xm389zhbgl3fn.method_31481()) {
                  this.xm389zhbgl3fn = new class_1536(class_1299.field_6103, var1);
                  this.xm389zhbgl3fn.method_7432(var2);
                  this.xm389zhbgl3fn.method_5875(true);
                  this.xm389zhbgl3fn
                     .method_5808(
                        this.x252scecjwbsjc.method_10263() + 0.5,
                        this.x252scecjwbsjc.method_10264() + 0.85,
                        this.x252scecjwbsjc.method_10260() + 0.5,
                        0.0F,
                        0.0F
                     );
                  if (!var1.method_8649(this.xm389zhbgl3fn)) {
                     this.xm389zhbgl3fn = null;
                     return;
                  }

                  var2.method_6104(class_1268.field_5808);
                  this.xapcnyboftre6 = var3 + 100L + var1.field_9229.method_43048(61);
               }

               if (var3 >= this.xapcnyboftre6) {
                  this.xapcnyboftre6 = var3 + 100L + var1.field_9229.method_43048(61);
                  class_1799 var5 = new class_1799(var1.field_9229.method_43048(4) == 0 ? class_1802.field_8209 : class_1802.field_8429);
                  class_1799 var6 = var2.method_35199().method_5491(var5);
                  var2.method_6104(class_1268.field_5808);
                  this.x2cuv9elefqg8b++;
                  if (var6.method_7960()) {
                     WorkDiagnostics.x2t98zlq4lu49t(var2, "рыбалка", 1);
                  } else {
                     var2.method_5775(var1, var6);
                  }

                  if (!var6.method_7960() || this.x2cuv9elefqg8b >= 12) {
                     this.x32ermkhbb9bv2();
                     this.x13ycknwfia743 = FishermanPhase.RETURN;
                     this.x252scecjwbsjc = this.x2n8fs353bluyf = null;
                  }
               }
            }
         } else {
            this.x13ycknwfia743 = FishermanPhase.SEARCH;
            this.x252scecjwbsjc = this.x2n8fs353bluyf = null;
         }
      }
   }

   private void xbt4deyrou7q8(class_3218 var1, class_1646 var2, long var3) {
      var var5 = ColonyManager.x3jrfw2d3yykeu(var1, this.x2d46f6032ktvg, var2, HousePurpose.FOOD);
      if (var5.isEmpty()) {
         this.x21q585dpo3fow = true;
      } else {
         var2.method_18868().method_18878(ColonyMemories.x3o547clcv9kaw, (class_2338)var5.get());
         if (var2.method_24515().method_10262((class_2382)var5.get()) > 9.0) {
            this.x80alxwfk9adt(var1, var2, (class_2338)var5.get(), 0.75);
         } else {
            for (int var6 = 0; var6 < var2.method_35199().method_5439(); var6++) {
               class_1799 var7 = var2.method_35199().method_5438(var6);
               if (var7.method_31574(class_1802.field_8429)
                  || var7.method_31574(class_1802.field_8209)
                  || var7.method_31574(class_1802.field_8373)
                  || var7.method_31574(class_1802.field_8509)) {
                  WorkDiagnostics.x25cotfj45r525(var1, this.x2d46f6032ktvg, var2, var7);
               }
            }

            this.x34vw5qt9vs5wg(var1);
            if (this.x13ycknwfia743 == FishermanPhase.RETURN) {
               this.x21q585dpo3fow = true;
               this.x1rc9hdzmwvbv4 = var3 + 40L;
            } else {
               if (!var2.method_6047().method_31574(class_1802.field_8378)) {
                  var var8 = ColonyManager.x3lxwdirnn77qc(var1, this.x2d46f6032ktvg, var0 -> var0.method_31574(class_1802.field_8378));
                  if (var8.isPresent()) {
                     this.x1cfeftsaw9e5z(var1, var2);
                     var2.method_5673(class_1304.field_6173, (class_1799)var8.get());
                  } else if (this.xzqd1l0vwcny0(var1)) {
                     this.x1cfeftsaw9e5z(var1, var2);
                     var2.method_5673(class_1304.field_6173, new class_1799(class_1802.field_8378));
                  }
               }

               if (!var2.method_6047().method_31574(class_1802.field_8378)) {
                  WorkDiagnostics.x1qd0mutwsr3sc(var2, "для удочки нужны 5 брёвен или 2 бревна и 2 нити");
                  this.x21q585dpo3fow = true;
                  this.x1rc9hdzmwvbv4 = var3 + 200L;
               } else {
                  this.x13ycknwfia743 = FishermanPhase.SEARCH;
                  this.x3kmuxybabtcha = 0;
               }
            }
         }
      }
   }

   private boolean xzqd1l0vwcny0(class_3218 var1) {
      int var2 = ColonyManager.x1mlne8tl8k5ag(var1, this.x2d46f6032ktvg, var0 -> var0.method_31573(class_3489.field_15539));
      int var3 = ColonyManager.x1mlne8tl8k5ag(var1, this.x2d46f6032ktvg, var0 -> var0.method_31574(class_1802.field_8276));
      return var2 >= 2 && var3 >= 2
         ? ColonyManager.x22hjebj4vmztt(var1, this.x2d46f6032ktvg, var0 -> var0.method_31573(class_3489.field_15539), 2)
            && ColonyManager.x22hjebj4vmztt(var1, this.x2d46f6032ktvg, var0 -> var0.method_31574(class_1802.field_8276), 2)
         : var2 >= 5 && ColonyManager.x22hjebj4vmztt(var1, this.x2d46f6032ktvg, var0 -> var0.method_31573(class_3489.field_15539), 5);
   }

   private void x34vw5qt9vs5wg(class_3218 var1) {
      int var2 = ColonyManager.x1mlne8tl8k5ag(var1, this.x2d46f6032ktvg, var0 -> var0.method_31574(class_1802.field_8429));
      int var3 = ColonyManager.x1mlne8tl8k5ag(var1, this.x2d46f6032ktvg, var0 -> var0.method_31574(class_1802.field_8209));
      int var4 = Math.min(8, var2 + var3);
      if (var4 != 0) {
         int var5 = Math.min(var2, var4);
         int var6 = var4 - var5;
         var var7 = new ArrayList<class_1799>();
         if (var5 > 0) {
            var7.add(new class_1799(class_1802.field_8373, var5));
         }

         if (var6 > 0) {
            var7.add(new class_1799(class_1802.field_8509, var6));
         }

         ColonyManager.x38ipozok9mc0x(
            var1,
            this.x2d46f6032ktvg,
            () -> ColonyManager.x22hjebj4vmztt(
                  var1,
                  this.x2d46f6032ktvg,
                  var0 -> var0.method_31574(class_1802.field_8713) || var0.method_31574(class_1802.field_8665) || var0.method_31573(class_3489.field_15539),
                  1
               )
               && ColonyManager.x22hjebj4vmztt(var1, this.x2d46f6032ktvg, var0 -> var0.method_31574(class_1802.field_8429), var5)
               && ColonyManager.x22hjebj4vmztt(var1, this.x2d46f6032ktvg, var0 -> var0.method_31574(class_1802.field_8209), var6),
            var7
         );
      }
   }

   private void x1cfeftsaw9e5z(class_3218 var1, class_1646 var2) {
      class_1799 var3 = var2.method_6047();
      if (!var3.method_7960() && !ColonyManager.x2cl1crde2rnxy(var1, this.x2d46f6032ktvg, var3)) {
         class_1799 var4 = var2.method_35199().method_5491(var3.method_7972());
         if (!var4.method_7960()) {
            var2.method_5775(var1, var4);
         }
      }
   }

   private boolean x38ykb3ggi4dan(class_3218 var1, class_1646 var2) {
      int var3 = Math.min(48, this.x2d46f6032ktvg.radius() - 4);

      for (int var4 = 0; var4 < 256 && this.x3kmuxybabtcha < x2kdfv2w398skl.size(); var4++) {
         class_2338 var5 = x2kdfv2w398skl.get(this.x3kmuxybabtcha++);
         if (var5.method_10262(class_2338.field_10980) > var3 * var3) {
            this.x3kmuxybabtcha = x2kdfv2w398skl.size();
            break;
         }

         int var6 = this.x2d46f6032ktvg.core().method_10263() + var5.method_10263();
         int var7 = this.x2d46f6032ktvg.core().method_10260() + var5.method_10260();
         if (var1.method_14178().method_12123(var6 >> 4, var7 >> 4)) {
            int var8 = var1.method_8624(class_2903.field_13197, var6, var7) - 1;
            class_2338 var9 = new class_2338(var6, var8, var7);
            if (var1.method_8320(var9).method_27852(class_2246.field_10382)
               && var1.method_8316(var9).method_15771()
               && var1.method_8320(var9.method_10084()).method_26215()) {
               for (class_2350 var11 : class_2353.field_11062) {
                  for (int var12 = 1; var12 <= 4; var12++) {
                     class_2338 var13 = var9.method_10079(var11, var12).method_10084();
                     if (!VillagerShelter.xps35ehel6nkk(var2, var13, var1.method_75260())
                        && var1.method_8320(var13).method_26215()
                        && var1.method_8320(var13.method_10084()).method_26215()
                        && var1.method_8320(var13.method_10074()).method_26206(var1, var13.method_10074(), class_2350.field_11036)) {
                        if (!ColonyManager.x5ll9elqpk1a4(var1)) {
                           return true;
                        }

                        class_11 var14 = var2.method_5942().method_6348(var13, 1);
                        if (var14 != null && var14.method_21655()) {
                           this.x252scecjwbsjc = var9;
                           this.x2n8fs353bluyf = var13;
                           this.x13ycknwfia743 = FishermanPhase.FISH;
                           this.x1ivrvuxnf7a8r = var1.method_75260() + 40L;
                           return true;
                        }
                     }
                  }
               }
            }
         }
      }

      return false;
   }

   private void x80alxwfk9adt(class_3218 var1, class_1646 var2, class_2338 var3, double var4) {
      VillagerShelter.x2wzylgizxltef(var1, var2, class_2338.method_49637(var3.method_10263() + 0.5, var3.method_10264(), var3.method_10260() + 0.5), var4);
   }

   private void x32ermkhbb9bv2() {
      if (this.xm389zhbgl3fn != null) {
         this.xm389zhbgl3fn.method_31472();
      }

      this.xm389zhbgl3fn = null;
   }

   public String debugState() {
      return (this.x13ycknwfia743 == null ? "ожидание" : this.x13ycknwfia743.name().toLowerCase(Locale.ROOT)) + ", улов=" + this.x2cuv9elefqg8b;
   }

   protected void finishRunning(class_3218 var1, class_1646 var2, long var3) {
      ActionTrace.worker(var2, "task_stop", "FishermanTask", false);
      this.x32ermkhbb9bv2();
      var2.method_5942().method_6340();
      var2.method_18868().method_18875(ColonyMemories.x263ucjykllb1b);
      var2.method_18868().method_18875(ColonyMemories.x3o547clcv9kaw);
      this.x2d46f6032ktvg = null;
      this.x252scecjwbsjc = this.x2n8fs353bluyf = null;
   }

   private static List<class_2338> x3j2bsjy7dlm8n() {
      var var0 = new ArrayList<class_2338>();

      for (int var1 = -48; var1 <= 48; var1++) {
         for (int var2 = -48; var2 <= 48; var2++) {
            var0.add(new class_2338(var1, 0, var2));
         }
      }

      var0.sort(Comparator.comparingDouble(var0x -> var0x.method_10262(class_2338.field_10980)));
      return List.copyOf(var0);
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
