package net.smartaul.village;

import com.google.common.collect.ImmutableMap;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import net.minecraft.class_11;
import net.minecraft.class_1646;
import net.minecraft.class_2246;
import net.minecraft.class_2323;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2488;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3341;
import net.minecraft.class_3765;
import net.minecraft.class_4097;
import net.minecraft.class_4140;
import net.minecraft.class_7477;
import net.minecraft.class_2350.class_2353;
import net.minecraft.class_4153.class_4155;

public final class RaidShelterTask extends class_4097<class_1646> {
   private static final Map<class_1646, class_2338> x2hbwcpenut843 = new WeakHashMap<>();
   private Colony x2d7pnemo6v9my;
   private class_2338 x2rn5xik6bqfpl;
   private class_2338 xq0qi837nwm2w;
   private long x28ccgmu677r01;
   private boolean x19rxy5xx7call;
   private long x2if2n1g199b49;
   private long x13n4ea8a3sluj;

   public static void clearRuntimeCaches() {
      x2hbwcpenut843.clear();
   }

   public RaidShelterTask() {
      super(ImmutableMap.of(), 12000);
   }

   public static boolean active(class_3218 var0, Colony var1) {
      int var2 = Math.min(1024, var1.radius() + 32);
      class_3765 var3 = var0.method_19495().method_19209(var1.core(), var2 * var2);
      return var3 != null && !var3.method_16832() && !var3.method_20022() && var3.method_16504();
   }

   public static boolean civilian(Colony var0, class_1646 var1) {
      return var0.roleOf(var1.method_5667()) != ColonyRole.GUARD;
   }

   protected boolean shouldRun(class_3218 var1, class_1646 var2) {
      this.x2d7pnemo6v9my = ColonyManager.x3psqezd5jem54(var1, var2.method_24515()).filter(var2x -> active(var1, var2x) && civilian(var2x, var2)).orElse(null);
      return this.x2d7pnemo6v9my != null;
   }

   protected void run(class_3218 var1, class_1646 var2, long var3) {
      ActionTrace.worker(var2, "task_start", "RaidShelterTask", false);
      x2hbwcpenut843.remove(var2);
      this.x2rn5xik6bqfpl = this.xq0qi837nwm2w = null;
      this.x2if2n1g199b49 = this.x13n4ea8a3sluj = var3;
   }

   protected boolean shouldKeepRunning(class_3218 var1, class_1646 var2, long var3) {
      return this.x2d7pnemo6v9my != null && active(var1, this.x2d7pnemo6v9my) && civilian(this.x2d7pnemo6v9my, var2);
   }

   protected void keepRunning(class_3218 var1, class_1646 var2, long var3) {
      if (var2.method_6113()) {
         var2.method_18400();
      }

      if (this.x2rn5xik6bqfpl != null
         && (
            !VillagerShelter.x9bnd6e2jczhd(var1, this.x2rn5xik6bqfpl)
               || var1.method_8311(this.x2rn5xik6bqfpl)
               || var3 % 100L == 0L && !x15at4spp0fe1m(var1, this.x2rn5xik6bqfpl, new HashMap<>())
         )) {
         this.x2rn5xik6bqfpl = null;
         x2hbwcpenut843.remove(var2);
      }

      if (this.x2rn5xik6bqfpl == null && var3 >= this.x2if2n1g199b49) {
         this.x2rn5xik6bqfpl = findShelter(var1, this.x2d7pnemo6v9my, var2);
         this.x2if2n1g199b49 = var3 + 100L;
      }

      if (this.x2rn5xik6bqfpl == null) {
         WorkDiagnostics.x1qd0mutwsr3sc(var2, "рейд: нет доступного укрытия");
      } else {
         WorkDiagnostics.x1qd0mutwsr3sc(var2, "прячется от рейда: " + this.x2rn5xik6bqfpl.method_23854());
         var2.method_18868().method_18875(class_4140.field_18445);
         if (var2.method_5649(this.x2rn5xik6bqfpl.method_10263() + 0.5, this.x2rn5xik6bqfpl.method_10264(), this.x2rn5xik6bqfpl.method_10260() + 0.5) <= 1.0
            && this.x1l8tf1y94ka6n(var1, var2, var3)) {
            var2.method_5942().method_6340();
         } else {
            if (var3 >= this.x13n4ea8a3sluj) {
               VillagerShelter.xf8lnb134kd0j(var1, var2, this.x2rn5xik6bqfpl, 1.05);
               if (VillagerShelter.xps35ehel6nkk(var2, this.x2rn5xik6bqfpl, var3)) {
                  this.x2rn5xik6bqfpl = null;
                  x2hbwcpenut843.remove(var2);
               }

               this.x13n4ea8a3sluj = var3 + 20L;
            }
         }
      }
   }

   public static class_2338 findShelter(class_3218 var0, Colony var1, class_1646 var2) {
      var var3 = new HashSet<class_2338>();
      var var4 = new HashMap<class_2338,Boolean>();
      var0.method_19494()
         .method_21647(var0x -> var0x.method_40225(class_7477.field_39291), var0x -> true, var1.core(), var1.radius(), class_4155.field_18489)
         .filter(var1x -> var0.method_14178().method_12123(var1x.method_10263() >> 4, var1x.method_10260() >> 4))
         .sorted(Comparator.comparingDouble(var1x -> var1x.method_10262(var2.method_24515())))
         .limit(32L)
         .forEach(
            var4x -> {
               for (int var5 = -2; var5 <= 2; var5++) {
                  for (int var6x = -2; var6x <= 2; var6x++) {
                     class_2338 var7x = var4x.method_10069(var5, 0, var6x);
                     if (var0.method_14178().method_12123(var7x.method_10263() >> 4, var7x.method_10260() >> 4)
                        && VillagerShelter.x9bnd6e2jczhd(var0, var7x)
                        && x15at4spp0fe1m(var0, var7x, var4)
                        && !ProtectedLand.x3gqyq7yie9jxr(var0, var7x)
                        && (
                           VillageRepair.x239t1m3hfi74d(var0, var7x)
                              || var1.x1ja61emeln2hc()
                                 .stream()
                                 .anyMatch(var1xx -> var1xx.x2nfrt8qxdscso().isEmpty() && var1xx.x24kbqv6g4kkrg().method_14662(var7x))
                        )) {
                        var3.add(var7x);
                     }
                  }
               }
            }
         );

      for (ConstructionJob var6 : var1.x1ja61emeln2hc()) {
         if (!var6.x2nfrt8qxdscso().isPresent() && var6.x2s8emavervqrz()) {
            class_3341 var7 = var6.x24kbqv6g4kkrg();
            int var8 = (var7.method_35415() + var7.method_35418()) / 2;
            int var9 = (var7.method_35417() + var7.method_35420()) / 2;

            for (byte var10 = -4; var10 <= 4; var10 += 2) {
               for (byte var11 = -4; var11 <= 4; var11 += 2) {
                  for (int var12 = 1; var12 <= 4; var12++) {
                     class_2338 var13 = new class_2338(var8 + var10, var7.method_35416() + var12, var9 + var11);
                     if (var7.method_14662(var13)
                        && var0.method_14178().method_12123(var13.method_10263() >> 4, var13.method_10260() >> 4)
                        && VillagerShelter.x9bnd6e2jczhd(var0, var13)
                        && x15at4spp0fe1m(var0, var13, var4)
                        && !ProtectedLand.x3gqyq7yie9jxr(var0, var13)) {
                        var3.add(var13);
                     }
                  }
               }
            }
         }
      }

      var3.removeIf(var2x -> VillagerShelter.xps35ehel6nkk(var2, var2x, var0.method_75260()));
      var var17 = new HashSet<>(var3);
      var17.removeIf(
         var2x -> x2hbwcpenut843.entrySet()
            .stream()
            .anyMatch(
               var3x -> var3x.getKey() != var2
                  && var3x.getKey().method_5805()
                  && var3x.getKey().method_73183() == var0
                  && var3x.getValue().method_10262(var2x) < 2.0
            )
      );
      if (!var17.isEmpty()) {
         var3.retainAll(var17);
      }

      if (!var3.isEmpty() && ColonyManager.x5ll9elqpk1a4(var0)) {
         var2.method_5942().method_70687(true);
         var2.method_5942().method_61439(128.0F);
         var2.method_5942().method_23964(8.0F);

         class_11 var18;
         try {
            var18 = var2.method_5942().method_29934(var3, 0);
         } finally {
            var2.method_5942().method_23965();
         }

         if (var18 != null && var18.method_21655()) {
            x2hbwcpenut843.put(var2, var18.method_48());
            return var18.method_48();
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   protected void finishRunning(class_3218 var1, class_1646 var2, long var3) {
      ActionTrace.worker(var2, "task_stop", "RaidShelterTask", false);
      x2hbwcpenut843.remove(var2);
      var2.method_5942().method_6340();
      this.x2rn5xik6bqfpl = null;
      this.x2d7pnemo6v9my = null;
   }

   private boolean x1l8tf1y94ka6n(class_3218 var1, class_1646 var2, long var3) {
      class_2338 var5 = var2.method_24515();
      if (!var5.equals(this.xq0qi837nwm2w) || var3 - this.x28ccgmu677r01 >= 100L) {
         this.xq0qi837nwm2w = var5;
         this.x28ccgmu677r01 = var3;
         this.x19rxy5xx7call = x15at4spp0fe1m(var1, var5, new HashMap<>());
      }

      return this.x19rxy5xx7call;
   }

   private static boolean x15at4spp0fe1m(class_3218 var0, class_2338 var1, Map<class_2338, Boolean> var2) {
      if (var2.containsKey(var1)) {
         return (Boolean)var2.get(var1);
      } else if (VillagerShelter.x9bnd6e2jczhd(var0, var1) && !var0.method_8311(var1)) {
         var var3 = new HashSet<class_2338>();
         var var4 = new ArrayDeque<>();
         var3.add(var1);
         var4.add(var1);
         boolean var5 = true;

         label76:
         while (!var4.isEmpty()) {
            class_2338 var6 = (class_2338)var4.removeFirst();
            if (var3.size() <= 192
               && Math.abs(var6.method_10263() - var1.method_10263()) <= 16
               && Math.abs(var6.method_10260() - var1.method_10260()) <= 16
               && !var0.method_8311(var6)) {
               var var7 = class_2353.field_11062.iterator();

               while (true) {
                  if (var7.hasNext()) {
                     class_2350 var8 = (class_2350)var7.next();
                     class_2338 var9 = var6.method_10093(var8);
                     if (var0.method_14178().method_12123(var9.method_10263() >> 4, var9.method_10260() >> 4)) {
                        class_2680 var10 = var0.method_8320(var9);
                        if (var10.method_26204() instanceof class_2323) {
                           continue;
                        }

                        boolean var11 = (
                              var10.method_26220(var0, var9).method_1110()
                                 || var10.method_27852(class_2246.field_10477) && (Integer)var10.method_11654(class_2488.field_11518) < 8
                           )
                           && var0.method_8320(var9.method_10084()).method_26220(var0, var9.method_10084()).method_1110();
                        if (var11 && var3.add(var9)) {
                           var4.addLast(var9);
                        }
                        continue;
                     }

                     var5 = false;
                  }

                  if (!var5) {
                     break label76;
                  }
                  continue label76;
               }
            }

            var5 = false;
            break;
         }

         for (class_2338 var13 : var3) {
            var2.put(var13, var5);
         }

         return var5;
      } else {
         return false;
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
