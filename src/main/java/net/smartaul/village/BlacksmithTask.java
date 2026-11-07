package net.smartaul.village;

import com.google.common.collect.ImmutableMap;
import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.class_1268;
import net.minecraft.class_1304;
import net.minecraft.class_1646;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_3218;
import net.minecraft.class_3489;
import net.minecraft.class_4097;
import net.minecraft.class_4140;
import net.minecraft.class_4141;

public final class BlacksmithTask extends class_4097<class_1646> {
   private static final Predicate<class_1799> xom517cw0um4v = var0 -> var0.method_31574(class_1802.field_8620);
   private static final Predicate<class_1799> x34u6jjh5a935g = var0 -> var0.method_31574(class_1802.field_8477);
   private static final Predicate<class_1799> x194844gl7dagx = var0 -> var0.method_31573(class_3489.field_15539);
   private Colony x3hy0zpbzjwfng;
   private class_2338 xkzbncf5sbizi;
   private long xoq2gkiz7k5l2;
   private boolean x1lxqbh3uu8b29;
   private final VillageGolems x1nptxhjn9u8rb = new VillageGolems();

   public BlacksmithTask() {
      super(ImmutableMap.of(class_4140.field_18445, class_4141.field_18458), 600);
   }

   public static boolean needsEquipment(class_3218 var0, Colony var1, class_1646 var2) {
      if (x1oufdnvti1fyk(var2, var0x -> var0x.method_31574(class_1802.field_8102))
         && x1oufdnvti1fyk(var2, var0x -> var0x.method_31573(class_3489.field_42611) || var0x.method_31573(class_3489.field_42612))
         && var2.method_6079().method_31574(class_1802.field_8255)) {
         for (class_1304 var6 : new class_1304[]{class_1304.field_6169, class_1304.field_6174, class_1304.field_6172, class_1304.field_6166}) {
            class_1799 var7 = var2.method_6118(var6);
            if (var7.method_7960() || !x313d952sywy3b(var7) && var1.x3peiu091351qg() >= x25z7tdvietcut(var6)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   protected boolean shouldRun(class_3218 var1, class_1646 var2) {
      if (!var2.method_6109() && !var2.method_6113() && !var2.method_18009() && var1.method_75260() >= this.xoq2gkiz7k5l2) {
         var var3 = ColonyManager.x3psqezd5jem54(var1, var2.method_24515()).filter(var0 -> !var0.x2ad3s8aentmo4().isEmpty()).filter(var2x -> {
            ColonyRole var3x = var2x.roleOf(var2.method_5667());
            return var3x == ColonyRole.GUARD && (needsEquipment(var1, var2x, var2) || VillageGolems.x3pk6ywniogk9m(var1, var2x));
         });
         if (var3.isEmpty()) {
            return false;
         } else {
            this.x3hy0zpbzjwfng = (Colony)var3.get();
            this.xkzbncf5sbizi = ColonyManager.x1falh21nhy4yk(var1, this.x3hy0zpbzjwfng, var2.method_24515()).orElse(null);
            this.x1lxqbh3uu8b29 = false;
            this.x1nptxhjn9u8rb.x3ku3wcoma0qbb();
            return this.xkzbncf5sbizi != null;
         }
      } else {
         return false;
      }
   }

   protected boolean shouldKeepRunning(class_3218 var1, class_1646 var2, long var3) {
      return !var2.method_6113()
         && !var2.method_18009()
         && this.x3hy0zpbzjwfng != null
         && this.x3hy0zpbzjwfng.roleOf(var2.method_5667()) == ColonyRole.GUARD
         && this.xkzbncf5sbizi != null
         && !this.x1lxqbh3uu8b29;
   }

   protected void run(class_3218 var1, class_1646 var2, long var3) {
      ActionTrace.worker(var2, "task_start", "BlacksmithTask", false);
      var2.method_18868().method_18878(ColonyMemories.x3o547clcv9kaw, this.xkzbncf5sbizi);
   }

   protected void keepRunning(class_3218 var1, class_1646 var2, long var3) {
      if (this.x1nptxhjn9u8rb.xhboo89lslsdy()) {
         this.x1lxqbh3uu8b29 = this.x1nptxhjn9u8rb.x21yp5lsgxd9co(var1, this.x3hy0zpbzjwfng, var2, var3);
         if (this.x1lxqbh3uu8b29) {
            this.xoq2gkiz7k5l2 = var3 + 40L;
         }
      } else if (var2.method_5649(this.xkzbncf5sbizi.method_10263() + 0.5, this.xkzbncf5sbizi.method_10264() + 0.5, this.xkzbncf5sbizi.method_10260() + 0.5)
         > 9.0) {
         VillagerShelter.x2wzylgizxltef(
            var1,
            var2,
            class_2338.method_49637(this.xkzbncf5sbizi.method_10263() + 0.5, this.xkzbncf5sbizi.method_10264(), this.xkzbncf5sbizi.method_10260() + 0.5),
            0.65
         );
      } else if (!this.x1nptxhjn9u8rb.xl4620yflyi80(var1, this.x3hy0zpbzjwfng, var2)) {
         boolean var5 = this.x3hy0zpbzjwfng.roleOf(var2.method_5667()) == ColonyRole.GUARD
            && (this.xarpn7hhhzvxe(var1, var2) || this.xlzguy5xco81e(var1, var2));
         if (var5) {
            var2.method_6104(class_1268.field_5808);
         }

         this.x1lxqbh3uu8b29 = true;
         this.xoq2gkiz7k5l2 = var3 + (var5 ? 40 : 200);
      }
   }

   protected void finishRunning(class_3218 var1, class_1646 var2, long var3) {
      ActionTrace.worker(var2, "task_stop", "BlacksmithTask", false);
      var2.method_5942().method_6340();
      this.x3hy0zpbzjwfng = null;
      this.xkzbncf5sbizi = null;
      this.x1nptxhjn9u8rb.x3ku3wcoma0qbb();
   }

   private boolean xarpn7hhhzvxe(class_3218 var1, class_1646 var2) {
      if (!x1oufdnvti1fyk(var2, var0 -> var0.method_31574(class_1802.field_8102))) {
         var var3 = ColonyManager.x3lxwdirnn77qc(var1, this.x3hy0zpbzjwfng, var0 -> var0.method_31574(class_1802.field_8102));
         if (var3.isPresent()) {
            return this.x3pwsh2mdq6w1j(var1, var2, (class_1799)var3.get());
         }

         if (this.x2a5t34vw8m1l3(var1, var0 -> var0.method_31574(class_1802.field_8276), 3, x194844gl7dagx, 1)
            || ColonyManager.x22hjebj4vmztt(var1, this.x3hy0zpbzjwfng, x194844gl7dagx, 4)) {
            return this.x3pwsh2mdq6w1j(var1, var2, new class_1799(class_1802.field_8102));
         }
      }

      if (!x1oufdnvti1fyk(var2, var0 -> var0.method_31573(class_3489.field_42611) || var0.method_31573(class_3489.field_42612))) {
         var var4 = ColonyManager.x3lxwdirnn77qc(
            var1,
            this.x3hy0zpbzjwfng,
            var0 -> var0.method_31574(class_1802.field_8802) || var0.method_31574(class_1802.field_8371) || var0.method_31573(class_3489.field_42612)
         );
         if (var4.isPresent()) {
            return this.x3pwsh2mdq6w1j(var1, var2, (class_1799)var4.get());
         }

         if (this.x2a5t34vw8m1l3(var1, x34u6jjh5a935g, 2, x194844gl7dagx, 1)) {
            return this.x3pwsh2mdq6w1j(var1, var2, new class_1799(class_1802.field_8802));
         }

         VillagerInventory.x7uj9zeib8utf(var1, this.x3hy0zpbzjwfng, 2);
         if (this.x2a5t34vw8m1l3(var1, xom517cw0um4v, 2, x194844gl7dagx, 1)) {
            return this.x3pwsh2mdq6w1j(var1, var2, new class_1799(class_1802.field_8371));
         }
      }

      if (!var2.method_6079().method_31574(class_1802.field_8255)) {
         var var5 = ColonyManager.x3lxwdirnn77qc(var1, this.x3hy0zpbzjwfng, var0 -> var0.method_31574(class_1802.field_8255));
         if (var5.isPresent()) {
            this.x1d7s430m0tpuj(var1, var2, class_1304.field_6171);
            var2.method_5673(class_1304.field_6171, (class_1799)var5.get());
            return true;
         }

         VillagerInventory.x7uj9zeib8utf(var1, this.x3hy0zpbzjwfng, 1);
         if (this.x2a5t34vw8m1l3(var1, xom517cw0um4v, 1, x194844gl7dagx, 6)) {
            this.x1d7s430m0tpuj(var1, var2, class_1304.field_6171);
            var2.method_5673(class_1304.field_6171, new class_1799(class_1802.field_8255));
            return true;
         }
      }

      return false;
   }

   private boolean x3pwsh2mdq6w1j(class_3218 var1, class_1646 var2, class_1799 var3) {
      if (!x3ur57yzm9k1a(var2.method_6047())) {
         this.x1d7s430m0tpuj(var1, var2, class_1304.field_6173);
         var2.method_5673(class_1304.field_6173, var3);
         return true;
      } else {
         class_1799 var4 = var2.method_35199().method_5491(var3);
         if (!var4.method_7960()) {
            VillagerInventory.xqe8twcjwxn55(var1, this.x3hy0zpbzjwfng, var2, var4);
         }

         return true;
      }
   }

   private boolean xlzguy5xco81e(class_3218 var1, class_1646 var2) {
      EquipmentRecipe[] var3 = new EquipmentRecipe[]{
         new EquipmentRecipe(class_1304.field_6174, class_1802.field_8058, class_1802.field_8523, 8),
         new EquipmentRecipe(class_1304.field_6172, class_1802.field_8348, class_1802.field_8396, 7),
         new EquipmentRecipe(class_1304.field_6169, class_1802.field_8805, class_1802.field_8743, 5),
         new EquipmentRecipe(class_1304.field_6166, class_1802.field_8285, class_1802.field_8660, 4)
      };

      for (EquipmentRecipe var7 : var3) {
         class_1799 var8 = var2.method_6118(var7.x3v9a3rm2wy1oi());
         if (!x313d952sywy3b(var8)) {
            var var9 = ColonyManager.x3lxwdirnn77qc(
               var1, this.x3hy0zpbzjwfng, var2x -> var2x.method_31574(var7.x2b30qxyd0cjni()) || var8.method_7960() && var2x.method_31574(var7.x6ma4582xqrzt())
            );
            if (var9.isPresent()) {
               this.x1d7s430m0tpuj(var1, var2, var7.x3v9a3rm2wy1oi());
               var2.method_5673(var7.x3v9a3rm2wy1oi(), (class_1799)var9.get());
               return true;
            }

            if (ColonyManager.x22hjebj4vmztt(var1, this.x3hy0zpbzjwfng, x34u6jjh5a935g, var7.xl7i1w1rdwzb1())) {
               this.x1d7s430m0tpuj(var1, var2, var7.x3v9a3rm2wy1oi());
               var2.method_5673(var7.x3v9a3rm2wy1oi(), new class_1799(var7.x2b30qxyd0cjni()));
               return true;
            }

            if (var8.method_7960()) {
               VillagerInventory.x7uj9zeib8utf(var1, this.x3hy0zpbzjwfng, var7.xl7i1w1rdwzb1());
               if (ColonyManager.x22hjebj4vmztt(var1, this.x3hy0zpbzjwfng, xom517cw0um4v, var7.xl7i1w1rdwzb1())) {
                  var2.method_5673(var7.x3v9a3rm2wy1oi(), new class_1799(var7.x6ma4582xqrzt()));
                  return true;
               }
            }
         }
      }

      return false;
   }

   private boolean x2a5t34vw8m1l3(class_3218 var1, Predicate<class_1799> var2, int var3, Predicate<class_1799> var4, int var5) {
      return ColonyManager.x1mlne8tl8k5ag(var1, this.x3hy0zpbzjwfng, var2) >= var3 && ColonyManager.x1mlne8tl8k5ag(var1, this.x3hy0zpbzjwfng, var4) >= var5
         ? ColonyManager.x22hjebj4vmztt(var1, this.x3hy0zpbzjwfng, var2, var3) && ColonyManager.x22hjebj4vmztt(var1, this.x3hy0zpbzjwfng, var4, var5)
         : false;
   }

   private static boolean x3ur57yzm9k1a(class_1799 var0) {
      return !var0.method_7960()
         && (var0.method_31574(class_1802.field_8102) || var0.method_31573(class_3489.field_42611) || var0.method_31573(class_3489.field_42612));
   }

   private static boolean x1oufdnvti1fyk(class_1646 var0, Predicate<class_1799> var1) {
      if (!var1.test(var0.method_6047()) && !var1.test(var0.method_6079())) {
         for (int var2 = 0; var2 < var0.method_35199().method_5439(); var2++) {
            if (var1.test(var0.method_35199().method_5438(var2))) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   private void x1d7s430m0tpuj(class_3218 var1, class_1646 var2, class_1304 var3) {
      VillagerInventory.xqe8twcjwxn55(var1, this.x3hy0zpbzjwfng, var2, var2.method_6118(var3));
   }

   private static int x1svbmkuu5e77z(class_1799 var0) {
      if (var0.method_31574(class_1802.field_8802)
         || var0.method_31574(class_1802.field_8556)
         || var0.method_31574(class_1802.field_22022)
         || var0.method_31574(class_1802.field_22025)) {
         return 4;
      } else if (var0.method_31574(class_1802.field_8102)) {
         return 4;
      } else if (var0.method_31574(class_1802.field_8371) || var0.method_31574(class_1802.field_8475)) {
         return 3;
      } else {
         return !var0.method_31573(class_3489.field_42611) && !var0.method_31573(class_3489.field_42612) ? 0 : 1;
      }
   }

   private static boolean x313d952sywy3b(class_1799 var0) {
      return var0.method_31574(class_1802.field_8805)
         || var0.method_31574(class_1802.field_8058)
         || var0.method_31574(class_1802.field_8348)
         || var0.method_31574(class_1802.field_8285)
         || var0.method_31574(class_1802.field_22027)
         || var0.method_31574(class_1802.field_22028)
         || var0.method_31574(class_1802.field_22029)
         || var0.method_31574(class_1802.field_22030);
   }

   private static int x25z7tdvietcut(class_1304 var0) {
      return var0 == class_1304.field_6174 ? 8 : (var0 == class_1304.field_6172 ? 7 : (var0 == class_1304.field_6169 ? 5 : 4));
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
