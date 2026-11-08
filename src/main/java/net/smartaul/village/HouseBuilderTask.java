package net.smartaul.village;

import net.smartaul.config.SmartAulConfig;

import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.class_11;
import net.minecraft.class_1263;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1308;
import net.minecraft.class_1309;
import net.minecraft.class_1439;
import net.minecraft.class_1646;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2195;
import net.minecraft.class_2244;
import net.minecraft.class_2246;
import net.minecraft.class_2261;
import net.minecraft.class_2281;
import net.minecraft.class_2302;
import net.minecraft.class_2323;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2415;
import net.minecraft.class_2470;
import net.minecraft.class_2488;
import net.minecraft.class_2498;
import net.minecraft.class_2513;
import net.minecraft.class_2680;
import net.minecraft.class_2769;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3341;
import net.minecraft.class_3481;
import net.minecraft.class_3489;
import net.minecraft.class_3492;
import net.minecraft.class_3499;
import net.minecraft.class_4097;
import net.minecraft.class_4140;
import net.minecraft.class_4141;
import net.minecraft.class_6862;
import net.minecraft.class_7893;
import net.minecraft.class_7923;
import net.minecraft.class_7924;
import net.minecraft.class_2902.class_2903;
import net.minecraft.class_3499.class_3501;
import net.minecraft.class_3499.class_5162;
import net.smartaul.mixin.StructureTemplateAccessor;

public final class HouseBuilderTask extends class_4097<class_1646> {
   private static final int x2j5qpw4473kyo = 12000;
   private static final double xqz1t0tgdckuk = 36.0;
   private static final double x1r0ny3vabstq4 = 9.0;
   private static final int x1l7wzrnir2mle = 20;
   private static final int x2b0guwc8pt2x0 = 8;
   private Colony x80pqmx58uqey;
   private ConstructionJob x6xecrdg78nv4;
   private List<ConstructionBlockPlan> x2npvmjy6y5jzn = List.of();
   private long xb9ohdv6bza8d;
   private long x35o9smdlqo00n;
   private long x24dc5178j3w8p;
   private int x1dhh6f58ylaqh;
   private long x1f9jpbscw9lb9;
   private class_2338 x2nyqpt8pxcmd0;
   private class_2338 x1cmffrn8pk1ze;
   private long xmf2hvjz8duvg;
   private double xase2imhyie81;
   private boolean x83c5i71zw6iy;
   private final BlockWorkProgress x28a0hyz23oia6 = new BlockWorkProgress();

   public HouseBuilderTask() {
      super(ImmutableMap.of(class_4140.field_18445, class_4141.field_18458), 12000);
   }

   protected boolean shouldRun(class_3218 var1, class_1646 var2) {
      if (!var2.method_6109()
         && !var2.method_6113()
         && !var2.method_18009()
         && var1.method_75260() >= this.x1f9jpbscw9lb9
         && WorldEditPolicy.x35ev8l9vc9uk7(var1)) {
         var var3 = ColonyManager.x3psqezd5jem54(var1, var2.method_24515())
            .filter(var1x -> var1x.roleOf(var2.method_5667()) == ColonyRole.BUILDER);
         if (!var3.isEmpty()
            && !((Colony)var3.get()).x2s37os1kht7vr()
            && !((Colony)var3.get()).x2ad3s8aentmo4().isEmpty()
            && ((Colony)var3.get()).x1n1a2vp01kxzf().isEmpty()
            && !SettlementSupport.shouldRepair(var1, (Colony)var3.get(), var2)
            && !VillageDefense.xuj80syz8c60l(var1, (Colony)var3.get(), var2)) {
            var var4 = ((Colony)var3.get())
               .x39hvql45i0wl6(var2.method_5667())
               .filter(var0 -> !var0.x3fgv5ft5sp7zt())
               .filter(var2x -> var2x.xxo2yeczi7qrw(var2.method_5667(), var1.method_75260()));
            if (var4.isEmpty()) {
               return false;
            } else if (!protectPen(var1, (Colony)var3.get(), (ConstructionJob)var4.get())) {
               return false;
            } else {
               var var5 = loadPlan(var1, (ConstructionJob)var4.get());
               if (var5.isEmpty()) {
                  ((ConstructionJob)var4.get()).x2hk920e3qof5e(var1.method_75260() + 1200L);
                  ColonyManager.xchl1k1tnh3w8(var1).method_80();
                  return false;
               } else {
                  this.x80pqmx58uqey = (Colony)var3.get();
                  this.x6xecrdg78nv4 = (ConstructionJob)var4.get();
                  this.x2npvmjy6y5jzn = (List<ConstructionBlockPlan>)var5.get();
                  this.x6xecrdg78nv4.x248ps383cwu1s(this.x2npvmjy6y5jzn.size(), var1.method_75260(), 8);
                  this.x6xecrdg78nv4.xkvyd96wj0qui(var2.method_5667(), var1.method_75260());
                  this.x2nm02vmn59j42(var2);
                  ColonyManager.xchl1k1tnh3w8(var1).method_80();
                  return this.x6xecrdg78nv4.xmu75rcopbjnh() < this.x2npvmjy6y5jzn.size();
               }
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   protected boolean shouldKeepRunning(class_3218 var1, class_1646 var2, long var3) {
      return !var2.method_6113()
         && !var2.method_18009()
         && this.x80pqmx58uqey != null
         && this.x80pqmx58uqey.roleOf(var2.method_5667()) == ColonyRole.BUILDER
         && !VillageDefense.xuj80syz8c60l(var1, this.x80pqmx58uqey, var2)
         && !SettlementSupport.shouldRepair(var1, this.x80pqmx58uqey, var2)
         && this.x6xecrdg78nv4 != null
         && !this.x6xecrdg78nv4.x3fgv5ft5sp7zt()
         && this.x6xecrdg78nv4.x32oz43hmeynhc() == ConstructionStatus.BUILDING
         && this.x6xecrdg78nv4.x3vu8mnvg0blk4().filter(var2.method_5667()::equals).isPresent()
         && this.x6xecrdg78nv4.xmu75rcopbjnh() < this.x2npvmjy6y5jzn.size();
   }

   protected void run(class_3218 var1, class_1646 var2, long var3) {
      ActionTrace.worker(var2, "task_start", "HouseBuilderTask", false);
      this.xb9ohdv6bza8d = var3;
      this.x35o9smdlqo00n = var3;
      this.x24dc5178j3w8p = var3;
      this.x1dhh6f58ylaqh = 0;
      this.x2nyqpt8pxcmd0 = null;
      this.x1cmffrn8pk1ze = null;
      this.x83c5i71zw6iy = this.x6xecrdg78nv4.x2s8emavervqrz()
         || this.x2npvmjy6y5jzn
            .stream()
            .anyMatch(
               var1x -> !var1x.x3ln30gt147gjn()
                  && !var1x.x1jjn414zs82u2()
                  && (
                     var1x.x2hmkda3gwcacx().method_26164(class_3481.field_15471)
                        || var1x.x2hmkda3gwcacx().method_26164(class_3481.field_15495)
                        || var1x.x2hmkda3gwcacx().method_26164(class_3481.field_16443)
                  )
                  && var1.method_8320(var1x.x31k0w8t7ul189()).equals(var1x.x2hmkda3gwcacx())
            );
   }

   protected void keepRunning(class_3218 var1, class_1646 var2, long var3) {
      if (!protectPen(var1, this.x80pqmx58uqey, this.x6xecrdg78nv4)) {
         WorkDiagnostics.x1qd0mutwsr3sc(var2, "стройка пересекает загон: поиск другого участка");
      } else if (var3 >= this.xb9ohdv6bza8d && this.x6xecrdg78nv4.xmu75rcopbjnh() < this.x2npvmjy6y5jzn.size()) {
         if (!WorldEditPolicy.x35ev8l9vc9uk7(var1)) {
            WorkDiagnostics.x1qd0mutwsr3sc(var2, "изменение мира выключено");
         } else if (!this.x83c5i71zw6iy
            && this.x6xecrdg78nv4.x2nfrt8qxdscso().isEmpty()
            && this.x6xecrdg78nv4.x3kqe1qjsw9oty().method_12836().equals("smart_colonies")
            && !this.x6xecrdg78nv4.x3kqe1qjsw9oty().method_12832().startsWith("village/legacy_beta24/")
            && this.x6xecrdg78nv4.x3ij6gescvakv2().method_10264() > this.x80pqmx58uqey.core().method_10264() + 16
            && this.x2npvmjy6y5jzn.get(this.x6xecrdg78nv4.xmu75rcopbjnh()).x3ln30gt147gjn()) {
            this.x6xecrdg78nv4.x2hk920e3qof5e(0L);
            this.x80pqmx58uqey.x2lr91dptxy1ww();
            this.x80pqmx58uqey.xs28n9ortj0j6(var3);
            WorkDiagnostics.x1qd0mutwsr3sc(var2, "не начатая стройка на высоком склоне: поиск другого участка");
            ColonyManager.xchl1k1tnh3w8(var1).method_80();
         } else if (var3 - this.x24dc5178j3w8p > 1200L) {
            WorkDiagnostics.x1qd0mutwsr3sc(var2, "стройка без прогресса: отложена для повторной проверки");
            this.x6xecrdg78nv4.x1pry0l0yrsmkc(var2.method_5667(), var3 + 2400L);
            this.x6xecrdg78nv4.x2hk920e3qof5e(var3 + 600L);
            this.x1f9jpbscw9lb9 = var3 + 2400L;
            ColonyManager.xchl1k1tnh3w8(var1).method_80();
         } else {
            int var5;
            for (var5 = 0;
               this.x6xecrdg78nv4.xmu75rcopbjnh() < this.x2npvmjy6y5jzn.size()
                  && var5 < 64
                  && this.x2byjgmxzxpv4f(var1, this.x2npvmjy6y5jzn.get(this.x6xecrdg78nv4.xmu75rcopbjnh()));
               var5++
            ) {
               this.x6xecrdg78nv4.xdhrxq42ir75w(var3);
            }

            if (var5 > 0) {
               this.x24dc5178j3w8p = var3;
               ColonyManager.xchl1k1tnh3w8(var1).method_80();
            }

            if (this.x6xecrdg78nv4.xmu75rcopbjnh() >= this.x2npvmjy6y5jzn.size()) {
               this.x6xecrdg78nv4.x3lcv8vebl7uhh();
               ColonyManager.xchl1k1tnh3w8(var1).method_80();
            } else if (var5 != 64 || !this.x2byjgmxzxpv4f(var1, this.x2npvmjy6y5jzn.get(this.x6xecrdg78nv4.xmu75rcopbjnh()))) {
               ConstructionBlockPlan var6 = this.x2npvmjy6y5jzn.get(this.x6xecrdg78nv4.xmu75rcopbjnh());
               class_2338 var7 = var6.x31k0w8t7ul189();
               var2.method_18868().method_18878(ColonyMemories.x1r15huley8x9h, var7);
               if (!var6.x3ln30gt147gjn()
                  && !var1.method_8320(var7).equals(var6.x2hmkda3gwcacx())
                  && !this.x2pq34yhi7gjxs(var2, var6.x2hmkda3gwcacx(), materialItem(var6.x2hmkda3gwcacx()))) {
                  this.x14d1iudi3z1jl(var1, var2, var6, var3);
               } else {
                  boolean var8 = var6.x3ln30gt147gjn()
                     ? var2.method_24515().method_10074().equals(var7)
                     : !var6.x2hmkda3gwcacx().method_26220(var1, var7).method_1110() && var2.method_5829().method_994(new class_238(var7));
                  if (!var8 && withinBuildReach(var2, var7)) {
                     var2.method_5942().method_6340();
                     int var9 = var6.x3ln30gt147gjn()
                        ? BlockWorkProgress.x20ocn113pbk5f(var1, var2, var7)
                        : Math.max(SmartAulConfig.x2by5f9ehxvwk9().village.build_delay_ticks, SmartAulConfig.x2by5f9ehxvwk9().work.placement_ticks);
                     if (this.x28a0hyz23oia6.xpohy1q30uxlk(var1, var2, var7, var9, var6.x3ln30gt147gjn())) {
                        int var10 = 0;

                        while (var10 < SmartAulConfig.x2by5f9ehxvwk9().village.builder_blocks_per_action && this.x6xecrdg78nv4.xmu75rcopbjnh() < this.x2npvmjy6y5jzn.size()) {
                           ConstructionBlockPlan var11 = this.x2npvmjy6y5jzn.get(this.x6xecrdg78nv4.xmu75rcopbjnh());
                           if (!withinBuildReach(var2, var11.x31k0w8t7ul189())
                              || !var11.x3ln30gt147gjn()
                                 && !var1.method_8320(var11.x31k0w8t7ul189()).equals(var11.x2hmkda3gwcacx())
                                 && !this.x2pq34yhi7gjxs(var2, var11.x2hmkda3gwcacx(), materialItem(var11.x2hmkda3gwcacx()))
                              || !this.x1dcdu1mepirat(var1, var2, var11)) {
                              break;
                           }

                           this.x24dc5178j3w8p = var3;
                           this.x1dhh6f58ylaqh = 0;
                           var10++;
                           WorkDiagnostics.x2t98zlq4lu49t(
                              var2, var11.x3ln30gt147gjn() ? "подготовка участка" : (var11.x1jjn414zs82u2() ? "фундамент" : "строительство"), 1
                           );
                           this.x6xecrdg78nv4.xdhrxq42ir75w(var1.method_75260());
                        }

                        if (var10 > 0) {
                           ColonyManager.xchl1k1tnh3w8(var1).method_80();
                        }

                        this.xb9ohdv6bza8d = var3 + 1L;
                        if (this.x6xecrdg78nv4.xmu75rcopbjnh() >= this.x2npvmjy6y5jzn.size()) {
                           this.x6xecrdg78nv4.x3lcv8vebl7uhh();
                           ColonyManager.xchl1k1tnh3w8(var1).method_80();
                        }
                     }
                  } else {
                     if (var8) {
                        WorkDiagnostics.x1qd0mutwsr3sc(var2, "освобождение места для строящегося блока");
                     }

                     if (this.x2nyqpt8pxcmd0 != null && var2.method_5942().method_6357() && this.x2nyqpt8pxcmd0.method_10262(var2.method_24515()) <= 9.0) {
                        VillagerShelter.x1uiixbj22gkfz(var2, this.x2nyqpt8pxcmd0, var3);
                        this.x2nyqpt8pxcmd0 = null;
                     }

                     if (var3 >= this.x35o9smdlqo00n) {
                        this.x15kyz5qjj4odo(var1, var2, var7);
                        this.x35o9smdlqo00n = var3 + 20L;
                     }
                  }
               }
            }
         }
      }
   }

   protected void finishRunning(class_3218 var1, class_1646 var2, long var3) {
      if(this.x80pqmx58uqey!=null) this.x80pqmx58uqey.materialRequests.cancel(var2.method_5667());
      ActionTrace.worker(var2, "task_stop", "HouseBuilderTask", false);
      this.x28a0hyz23oia6.xupxzzyodxtwj(var1, var2);
      var2.method_5942().method_6340();
      var2.method_18868().method_18875(ColonyMemories.x1r15huley8x9h);
      if (this.x6xecrdg78nv4 != null && this.x6xecrdg78nv4.xmu75rcopbjnh() >= this.x2npvmjy6y5jzn.size()) {
         this.x6xecrdg78nv4.x3lcv8vebl7uhh();
         ColonyManager.xchl1k1tnh3w8(var1).method_80();
      } else if (this.x6xecrdg78nv4 != null && this.x6xecrdg78nv4.x3vu8mnvg0blk4().filter(var2.method_5667()::equals).isPresent()) {
         this.x6xecrdg78nv4.x23bey8uos6gyt();
         ColonyManager.xchl1k1tnh3w8(var1).method_80();
      }

      this.x80pqmx58uqey = null;
      this.x6xecrdg78nv4 = null;
      this.x2npvmjy6y5jzn = List.of();
   }

   private boolean x1dcdu1mepirat(class_3218 var1, class_1646 var2, ConstructionBlockPlan var3) {
      class_2680 var4 = var3.x2hmkda3gwcacx();
      class_2680 var5 = var1.method_8320(var3.x31k0w8t7ul189());
      if (this.x2byjgmxzxpv4f(var1, var3)) {
         return true;
      } else if (var3.x3ln30gt147gjn()) {
         if (x10z0hm6bbvhlr(var1, var3.x31k0w8t7ul189(), var5)) {
            this.x6xecrdg78nv4.x2hk920e3qof5e(var1.method_75260() + 2400L);
            WorkDiagnostics.x1qd0mutwsr3sc(var2, "расчистка остановлена защитой участка");
            ColonyManager.xchl1k1tnh3w8(var1).method_80();
            return false;
         } else if (!var5.method_26215() && x3b6f14zbbbwjh(var5)) {
            if (var2.method_24515().method_10074().equals(var3.x31k0w8t7ul189())) {
               this.x15kyz5qjj4odo(var1, var2, var3.x31k0w8t7ul189());
               return false;
            } else {
               return WorldEditPolicy.x231if4rvsvptj(var1, var3.x31k0w8t7ul189(), false, var2);
            }
         } else {
            return true;
         }
      } else if (WorldEditPolicy.x3o1lagqjim5tz(var1, var3.x31k0w8t7ul189())) {
         WorkDiagnostics.x1qd0mutwsr3sc(var2, "участок защищён: " + var3.x31k0w8t7ul189().method_23854());
         this.x6xecrdg78nv4.x2hk920e3qof5e(var1.method_75260() + 2400L);
         ColonyManager.xchl1k1tnh3w8(var1).method_80();
         return false;
      } else {
         if (SmartAulConfig.x2by5f9ehxvwk9().roles.builder_respects_player_builds) {
            class_6862 var6 = class_6862.method_40092(class_7924.field_41254, class_2960.method_60655("smart_colonies", "builder_protected"));
            if (var5.method_26164(var6) || var1.method_8321(var3.x31k0w8t7ul189()) != null) {
               this.x6xecrdg78nv4.x2hk920e3qof5e(var1.method_75260() + 2400L);
               WorkDiagnostics.x1qd0mutwsr3sc(var2, "помеха на участке: дом будет продолжен, а не брошен");
               ColonyManager.xchl1k1tnh3w8(var1).method_80();
               return false;
            }
         }

         class_1792 var12 = materialItem(var4);
         if (var12 != class_1802.field_8162 && this.x2pq34yhi7gjxs(var2, var4, var12)) {
            if (!var4.method_26220(var1, var3.x31k0w8t7ul189()).method_1110()) {
               var var7 = var1.method_8333(
                  null,
                  new class_238(var3.x31k0w8t7ul189()),
                  var3x -> var3x instanceof class_1309
                     && var3x.method_5805()
                     && !var3x.method_7325()
                     && var4.method_26220(var1, var3.x31k0w8t7ul189())
                        .method_1090()
                        .stream()
                        .anyMatch(
                           var2xx -> var2xx.method_989(
                                 var3.x31k0w8t7ul189().method_10263(), var3.x31k0w8t7ul189().method_10264(), var3.x31k0w8t7ul189().method_10260()
                              )
                              .method_994(var3x.method_5829())
                        )
               );
               if (!var7.isEmpty()) {
                  WorkDiagnostics.x1qd0mutwsr3sc(var2, "строящийся блок занят: " + var3.x31k0w8t7ul189().method_23854());

                  for (class_1297 var9 : var7) {
                     if ((var9 instanceof class_1646 || var9 instanceof class_1439) && var9 instanceof class_1308 var10 && ColonyManager.x5ll9elqpk1a4(var1)) {
                        class_2338 var11 = this.x9bqehx0b70gk(var1, var9.method_24515());
                        var10.method_5942().method_6337(var11.method_10263() + 0.5, var11.method_10264(), var11.method_10260() + 0.5, 0.65);
                     }
                  }

                  return false;
               }
            }

            if (!WorldEditPolicy.x1bq0f8n53gtob(var1, var3.x31k0w8t7ul189(), var4, 3)) {
               x1sqdj274gd8s2(var1, this.x6xecrdg78nv4, var3);
               return false;
            } else if (!this.x2g9vwrz4x5lcw(var2, var4, var12)) {
               WorldEditPolicy.x1bq0f8n53gtob(var1, var3.x31k0w8t7ul189(), var5, 3);
               return false;
            } else {
               var2.method_6104(class_1268.field_5808);
               class_2498 var13 = var4.method_26231();
               var1.method_8396(
                  null, var3.x31k0w8t7ul189(), var13.method_10598(), var2.method_5634(), (var13.method_10597() + 1.0F) / 2.0F, var13.method_10599() * 0.8F
               );
               return true;
            }
         } else {
            return false;
         }
      }
   }

   private boolean x2byjgmxzxpv4f(class_3218 var1, ConstructionBlockPlan var2) {
      class_2680 var3 = var1.method_8320(var2.x31k0w8t7ul189());
      if (x1zol2fshkzbul(var3, var2.x2hmkda3gwcacx())) {
         return true;
      } else {
         return var2.x3ln30gt147gjn()
            ? var3.method_26215() || !x3b6f14zbbbwjh(var3)
            : var2.x1jjn414zs82u2()
               && var3.method_26206(var1, var2.x31k0w8t7ul189(), class_2350.field_11036)
               && var1.method_8316(var2.x31k0w8t7ul189()).method_15769();
      }
   }

   public static Optional<List<ConstructionBlockPlan>> loadPlan(class_3218 var0, ConstructionJob var1) {
      return var0.method_8503()
         .method_27727()
         .method_15094(var1.x3kqe1qjsw9oty())
         .map(
            var1x -> {
               class_3492 var2 = new class_3492().method_15125(class_2415.field_11302).method_15123(class_2470.field_11467).method_15133(true);
               var var3 = ((StructureTemplateAccessor)var1x).smartAul$getBlockInfoLists();
               if (var3.isEmpty()) {
                  return List.of();
               } else {
                  var var4 = new ArrayList<ConstructionBlockPlan>();
                  var var5 = new HashMap<class_2338, class_2680>();

                  for (class_3501 var7 : ((class_5162)var3.getFirst()).method_27125()) {
                     class_2680 var8 = var7.comp_1342().method_26185(var2.method_15114()).method_26186(var2.method_15113());
                     if (!var8.method_26215() && !var8.method_27852(class_2246.field_10369) && !var8.method_27852(class_2246.field_16540)) {
                        var5.put(var1.x3ij6gescvakv2().method_10081(class_3499.method_15171(var2, var7.comp_1341())), var8);
                     }
                  }

                  class_3341 var11 = var1.x24kbqv6g4kkrg();

                  for (int var12 = var11.method_35415() - 1; var12 <= var11.method_35418() + 1; var12++) {
                     for (int var14 = var11.method_35417() - 1; var14 <= var11.method_35420() + 1; var14++) {
                        for (int var9 = var11.method_35416() + 1; var9 <= var11.method_35419() + 2; var9++) {
                           class_2338 var10 = new class_2338(var12, var9, var14);
                           var4.add(new ConstructionBlockPlan(var10, var5.getOrDefault(var10, class_2246.field_10124.method_9564()), true, false));
                        }

                        if (var12 >= var11.method_35415() && var12 <= var11.method_35418() && var14 >= var11.method_35417() && var14 <= var11.method_35420()) {
                           for (int var16 = var11.method_35416(); var16 >= var11.method_35416() - 4; var16--) {
                              class_2338 var18 = new class_2338(var12, var16, var14);
                              var4.add(new ConstructionBlockPlan(var18, class_2246.field_10445.method_9564(), false, true));
                           }
                        }
                     }
                  }

                  for (class_3501 var15 : ((class_5162)var3.getFirst()).method_27125()) {
                     class_2680 var17 = var15.comp_1342().method_26185(var2.method_15114()).method_26186(var2.method_15113());
                     if (!var17.method_26215() && !var17.method_27852(class_2246.field_10369) && !var17.method_27852(class_2246.field_16540)) {
                        class_2338 var19 = class_3499.method_15171(var2, var15.comp_1341());
                        var4.add(new ConstructionBlockPlan(var1.x3ij6gescvakv2().method_10081(var19), var17, false, false));
                     }
                  }

                  var4.sort(
                     Comparator.comparingInt(HouseBuilderTask::xpqrxw62ch5me)
                        .thenComparingInt(var0xx -> var0xx.x3ln30gt147gjn() ? -var0xx.x31k0w8t7ul189().method_10264() : var0xx.x31k0w8t7ul189().method_10264())
                        .thenComparingInt(var0xx -> -var0xx.x31k0w8t7ul189().method_10260())
                        .thenComparingInt(var0xx -> -var0xx.x31k0w8t7ul189().method_10263())
                  );
                  return List.copyOf(var4);
               }
            }
         );
   }

   private static int xpqrxw62ch5me(ConstructionBlockPlan var0) {
      if (var0.x3ln30gt147gjn()) {
         return 0;
      } else if (var0.x1jjn414zs82u2()) {
         return 1;
      } else {
         String var1 = class_7923.field_41175.method_10221(var0.x2hmkda3gwcacx().method_26204()).method_12832();
         if (var1.contains("chain")) {
            return 3;
         } else {
            return !(var0.x2hmkda3gwcacx().method_26204() instanceof class_2261)
                  && !(var0.x2hmkda3gwcacx().method_26204() instanceof class_2323)
                  && !(var0.x2hmkda3gwcacx().method_26204() instanceof class_2244)
                  && !List.of("lantern", "torch", "ladder", "carpet", "sign", "button", "lever", "rail", "banner", "candle", "flower_pot", "pressure_plate")
                     .stream()
                     .anyMatch(var1::contains)
               ? 2
               : 4;
         }
      }
   }

   private void x2nm02vmn59j42(class_1646 var1) {
      var1.method_18868().method_18878(ColonyMemories.x3ecoc0soedv0m, ColonyRole.BUILDER);
      var1.method_18868().method_18878(ColonyMemories.x2ooa9y9romfh, this.x6xecrdg78nv4.x24kbqv6g4kkrg());
      if (!this.x80pqmx58uqey.x2ad3s8aentmo4().isEmpty()) {
         var1.method_18868().method_18878(ColonyMemories.x3o547clcv9kaw, this.x80pqmx58uqey.x2ad3s8aentmo4().getFirst());
      }
   }

   private void x14d1iudi3z1jl(class_3218 var1, class_1646 var2, ConstructionBlockPlan var3, long var4) {
      class_1792 var6 = materialItem(var3.x2hmkda3gwcacx());
      int var7 = ColonyManager.x1mlne8tl8k5ag(var1, this.x80pqmx58uqey, var0 -> var0.method_31573(class_3489.field_15539));
      int var8 = ColonyManager.x1mlne8tl8k5ag(var1, this.x80pqmx58uqey, var0 -> var0.method_31573(class_3489.field_25808));
      Predicate<class_1799> var9 = materialPredicate(var3.x2hmkda3gwcacx(), var6)
         .and(var2x -> var2x.method_31573(class_3489.field_15539) ? var7 > 8 : (var2x.method_31573(class_3489.field_25808) ? var8 > 8 : true));
      if (CourierSupply.request(var1, this.x80pqmx58uqey, var2, var2.method_24515(), var6, var9, 32, 50)) {
         this.xb9ohdv6bza8d = var4 + 40L;
         return;
      }
      var var10 = ColonyManager.x1dhbpietunk9i(var1, this.x80pqmx58uqey, var2, var9);
      boolean var11 = false;
      if (var10.isEmpty()) {
         var var12 = ColonyManager.x2ytjyhq411pf7(var1, this.x80pqmx58uqey, var2.method_24515(), var9);
         if (var12.isPresent()) {
            this.x6xecrdg78nv4.x3feu6p476x7c(var4);
            ColonyManager.xchl1k1tnh3w8(var1).method_80();
            if (this.x6xecrdg78nv4.xbnccd29nwem4() < 600) {
               WorkDiagnostics.x1qd0mutwsr3sc(var2, "путь к складу стройматериалов временно недоступен");
               this.xb9ohdv6bza8d = var4 + 20L;
               return;
            }

            var10 = var12;
            var11 = true;
         }
      }

      if (var10.isEmpty()) {
         WorkDiagnostics.x1qd0mutwsr3sc(var2, "ожидание стройматериалов");
         this.xb9ohdv6bza8d = var4 + 40L;
      } else {
         class_2338 var24 = (class_2338)var10.get();
         var2.method_18868().method_18878(ColonyMemories.x3o547clcv9kaw, var24);
         double var13 = var2.method_5649(var24.method_10263() + 0.5, var24.method_10264() + 0.5, var24.method_10260() + 0.5);
         if (this.x1cmffrn8pk1ze == null || this.x1cmffrn8pk1ze.method_10262(var24) > 2.0) {
            this.x1cmffrn8pk1ze = var24;
            this.xmf2hvjz8duvg = var4;
            this.xase2imhyie81 = var13;
         } else if (var13 + 16.0 < this.xase2imhyie81) {
            this.xase2imhyie81 = var13;
            this.xmf2hvjz8duvg = var4;
         }

         if (var13 > 9.0 && var4 - this.xmf2hvjz8duvg >= 600L) {
            var11 = true;
         }

         if (!var11 && var2.method_5649(var24.method_10263() + 0.5, var24.method_10264() + 0.5, var24.method_10260() + 0.5) > 9.0) {
            if (var4 >= this.x35o9smdlqo00n) {
               boolean var25 = VillagerShelter.x2wzylgizxltef(
                  var1, var2, class_2338.method_49637(var24.method_10263() + 0.5, var24.method_10264(), var24.method_10260() + 0.5), 0.6
               );
               if (!var25) {
                  this.x6xecrdg78nv4.x3feu6p476x7c(var4);
                  ColonyManager.xchl1k1tnh3w8(var1).method_80();
               }

               this.x35o9smdlqo00n = var4 + 20L;
            }
         } else {
            boolean var15 = false;

            for (int var16 = 0; var16 < var2.method_35199().method_5439(); var16++) {
               class_1799 var17 = var2.method_35199().method_5438(var16);
               if (!var17.method_7960() && !materialPredicate(var3.x2hmkda3gwcacx(), var6).test(var17)) {
                  if (!var15 && ColonyManager.xhahxgpa08yc9(var17) > 0) {
                     var15 = true;
                  } else {
                     WorkDiagnostics.x25cotfj45r525(var1, this.x80pqmx58uqey, var2, var17);
                  }
               }
            }

            if (var1.method_8321(var24) instanceof class_1263 var26) {
               var var28 = new ArrayList<Integer>();

               for (int var18 = 0; var18 < var26.method_5439(); var18++) {
                  if (var9.test(var26.method_5438(var18))) {
                     var28.add(var18);
                  }
               }

               String var29 = class_7923.field_41175.method_10221(var3.x2hmkda3gwcacx().method_26204()).method_12832();
               var28.sort(Comparator.comparingInt(var4x -> {
                  class_1799 var5 = var26.method_5438(var4x);
                  if (var5.method_31574(var6)) {
                     return 0;
                  } else if (var5.method_31573(class_3489.field_25808)) {
                     return x3eloj7pplrkw8(var29) ? 1 : 2;
                  } else if (!var5.method_31573(class_3489.field_15539)) {
                     return 1;
                  } else {
                     return x2wwfwdfwbj3ts(var29) && var7 >= 40 ? 1 : 3;
                  }
               }));

               for (int var20 : var28) {
                  class_1799 var21 = var26.method_5438(var20);
                  if (!var21.method_7960() && var9.test(var21)) {
                     int var22 = var21.method_31573(class_3489.field_15539) ? var7 - 8 : (var21.method_31573(class_3489.field_25808) ? var8 - 8 : 32);
                     if (var22 > 0) {
                        class_1792 var23 = var21.method_7909();
                        ColonyManager.x5jd7014csotp(var1, var24, var1x -> var1x.method_31574(var23), Math.min(64, var22)).ifPresent(var3x -> {
                           this.x3gd6g2uloauvn(var1, var2, var3x);
                           var2.method_6104(class_1268.field_5808);
                        });
                        this.x6xecrdg78nv4.x1rzj1yojiksff();
                        this.x1cmffrn8pk1ze = null;
                        this.x24dc5178j3w8p = var4;
                        if (var11) {
                           WorkDiagnostics.x2t98zlq4lu49t(var2, "снабжение стройки", 1);
                        }

                        ColonyManager.xchl1k1tnh3w8(var1).method_80();
                        this.xb9ohdv6bza8d = var4 + 10L;
                        return;
                     }
                  }
               }
            }

            WorkDiagnostics.x1qd0mutwsr3sc(var2, "сохраняется запас для инструментов и факелов");
            this.xb9ohdv6bza8d = var4 + 10L;
         }
      }
   }

   private boolean x2pq34yhi7gjxs(class_1646 var1, class_2680 var2, class_1792 var3) {
      Predicate<class_1799> var4 = materialPredicate(var2, var3);
      if (var4.test(var1.method_6047())) {
         return true;
      } else {
         for (int var5 = 0; var5 < var1.method_35199().method_5439(); var5++) {
            if (var4.test(var1.method_35199().method_5438(var5))) {
               return true;
            }
         }

         return false;
      }
   }

   private boolean x2g9vwrz4x5lcw(class_1646 var1, class_2680 var2, class_1792 var3) {
      Predicate<class_1799> var4 = materialPredicate(var2, var3);
      if (var4.test(var1.method_6047())) {
         var1.method_6047().method_7934(1);
         return true;
      } else {
         for (int var5 = 0; var5 < var1.method_35199().method_5439(); var5++) {
            class_1799 var6 = var1.method_35199().method_5438(var5);
            if (!var6.method_7960() && var4.test(var6)) {
               var6.method_7934(1);
               return true;
            }
         }

         return false;
      }
   }

   private void x3gd6g2uloauvn(class_3218 var1, class_1646 var2, class_1799 var3) {
      class_1799 var4 = var2.method_6047().method_7972();
      var2.method_5673(class_1304.field_6173, class_1799.field_8037);
      if (!var4.method_7960()) {
         class_1799 var5 = var2.method_35199().method_5491(var4);
         if (!var5.method_7960() && !ColonyManager.x2cl1crde2rnxy(var1, this.x80pqmx58uqey, var5)) {
            var2.method_5775(var1, var5);
         }
      }

      var2.method_5673(class_1304.field_6173, var3);
   }

   public static Predicate<class_1799> materialPredicate(class_2680 var0, class_1792 var1) {
      if (var1 == class_1802.field_8162) {
         var1 = materialItem(var0);
      }

      class_1792 var2 = var1;
      Predicate<class_1799> var3 = var1x -> !var1x.method_7960() && var1x.method_31574(var2);
      String var4 = class_7923.field_41175.method_10221(var0.method_26204()).method_12832();
      if (x2wwfwdfwbj3ts(var4)) {
         var3 = var3.or(var0x -> var0x.method_31573(class_3489.field_15539));
      }

      if (x3eloj7pplrkw8(var4)) {
         var3 = var3.or(var0x -> var0x.method_31573(class_3489.field_25808));
      }

      if (var4.contains("sandstone")) {
         var3 = var3.or(var0x -> var0x.method_31574(class_1802.field_8858) || var0x.method_31574(class_1802.field_8200));
      }

      return var3.or(var0x -> var0x.method_31573(class_3489.field_15539) || var0x.method_31573(class_3489.field_25808));
   }

   public static class_1792 materialItem(class_2680 var0) {
      class_1792 var1 = var0.method_26204().method_8389();
      if (var1 != class_1802.field_8162) {
         return var1;
      } else if (var0.method_27852(class_2246.field_10362) || var0.method_27852(class_2246.field_10194)) {
         return class_1802.field_8831;
      } else if (var0.method_27852(class_2246.field_10382)) {
         return class_1802.field_8705;
      } else if (var0.method_27852(class_2246.field_10164)) {
         return class_1802.field_8187;
      } else if (var0.method_27852(class_2246.field_46286) || var0.method_27852(class_2246.field_46284)) {
         return class_1802.field_46249;
      } else if (var0.method_27852(class_2246.field_46287) || var0.method_27852(class_2246.field_46285)) {
         return class_1802.field_46250;
      } else if (var0.method_27852(class_2246.field_10091)) {
         return class_1802.field_8725;
      } else {
         return var0.method_27852(class_2246.field_10589) ? class_1802.field_8276 : var1;
      }
   }

   private class_2338 x9bqehx0b70gk(class_3218 var1, class_2338 var2) {
      class_3341 var3 = this.x6xecrdg78nv4.x24kbqv6g4kkrg();
      var var4 = new ArrayList<class_2338>();

      for (int var5 = 2; var5 <= 5; var5++) {
         for (int var6 = var3.method_35415() - var5; var6 <= var3.method_35418() + var5; var6++) {
            for (int var10 : new int[]{var3.method_35417() - var5, var3.method_35420() + var5}) {
               this.x3ug5hpy6o0yih(var1, var2, var4, var6, var10);
            }
         }

         for (int var11 = var3.method_35417() - var5 + 1; var11 < var3.method_35420() + var5; var11++) {
            for (int var15 : new int[]{var3.method_35415() - var5, var3.method_35418() + var5}) {
               this.x3ug5hpy6o0yih(var1, var2, var4, var15, var11);
            }
         }

         if (!var4.isEmpty()) {
            break;
         }
      }

      return var4.stream().min(Comparator.comparingDouble(var2::method_10262)).orElse(this.x6xecrdg78nv4.x3ij6gescvakv2());
   }

   private void x15kyz5qjj4odo(class_3218 var1, class_1646 var2, class_2338 var3) {
      if (this.x2nyqpt8pxcmd0 != null
         && VillagerShelter.x9bnd6e2jczhd(var1, this.x2nyqpt8pxcmd0)
         && x1tj11tseztzrt(this.x2nyqpt8pxcmd0, var3) <= 9.0
         && Math.abs(this.x2nyqpt8pxcmd0.method_10264() - var3.method_10264()) <= 13
         && !this.x2nyqpt8pxcmd0.method_10074().equals(var3)
         && !this.x2nyqpt8pxcmd0.equals(var3)
         && !x3o70vbguw4qn7(var2, this.x2nyqpt8pxcmd0, var3)
         && !VillagerShelter.xps35ehel6nkk(var2, this.x2nyqpt8pxcmd0, var1.method_75260())) {
         VillagerShelter.x2wzylgizxltef(var1, var2, this.x2nyqpt8pxcmd0, 0.8);
      } else {
         class_3341 var4 = this.x6xecrdg78nv4.x24kbqv6g4kkrg();
         var var5 = new ArrayList<class_2338>();

         for (int var6 = 2; var6 <= 4; var6++) {
            for (int var7 = var4.method_35415() - var6; var7 <= var4.method_35418() + var6; var7++) {
               for (int var11 : new int[]{var4.method_35417() - var6, var4.method_35420() + var6}) {
                  this.x3ug5hpy6o0yih(var1, var3, var5, var7, var11);
               }
            }

            for (int var17 = var4.method_35417() - var6 + 1; var17 < var4.method_35420() + var6; var17++) {
               for (int var24 : new int[]{var4.method_35415() - var6, var4.method_35418() + var6}) {
                  this.x3ug5hpy6o0yih(var1, var3, var5, var24, var17);
               }
            }
         }

         for (int var15 = -5; var15 <= 5; var15++) {
            for (int var18 = -5; var18 <= 5; var18++) {
               if (!(var15 * var15 + var18 * var18 > 9.0)) {
                  for (int var20 = var3.method_10264() - 13; var20 <= var3.method_10264() + 13; var20++) {
                     class_2338 var22 = new class_2338(var3.method_10263() + var15, var20, var3.method_10260() + var18);
                     if (VillagerShelter.x9bnd6e2jczhd(var1, var22)) {
                        var5.add(var22);
                     }
                  }
               }
            }
         }

         var5.removeIf(
            var3x -> x1tj11tseztzrt(var3x, var3) > 9.0
               || Math.abs(var3x.method_10264() - var3.method_10264()) > 13
               || var3x.method_10074().equals(var3)
               || var3x.equals(var3)
               || x3o70vbguw4qn7(var2, var3x, var3)
               || VillagerShelter.xps35ehel6nkk(var2, var3x, var1.method_75260())
         );
         var5.sort(Comparator.comparingDouble(var2x -> 0.35 * x1tj11tseztzrt(var2x, var3) + var2x.method_10262(var2.method_24515())));
         if (var5.isEmpty()) {
            WorkDiagnostics.x1qd0mutwsr3sc(var2, "нет безопасного подхода к стройке");
         } else if (ColonyManager.x5ll9elqpk1a4(var1)) {
            var2.method_5942().method_61439(128.0F);
            var2.method_5942().method_23964(8.0F);

            class_11 var16;
            try {
               var16 = var2.method_5942().method_29934(new HashSet<>(var5), 0);
            } finally {
               var2.method_5942().method_23965();
            }

            if (var16 != null && var16.method_21655()) {
               this.x2nyqpt8pxcmd0 = var16.method_48();
            } else {
               this.x2nyqpt8pxcmd0 = (class_2338)var5.getFirst();
               WorkDiagnostics.x1qd0mutwsr3sc(var2, "поиск доступного подхода к стройке");
            }

            VillagerShelter.x2wzylgizxltef(var1, var2, this.x2nyqpt8pxcmd0, 0.8);
         }
      }
   }

   private void x3ug5hpy6o0yih(class_3218 var1, class_2338 var2, List<class_2338> var3, int var4, int var5) {
      class_2338 var6 = this.x2m6fmt7uq9mrl(var1, var4, var5);
      if (Math.abs(var6.method_10264() - var2.method_10264()) <= 13) {
         if (VillagerShelter.x9bnd6e2jczhd(var1, var6)) {
            var3.add(var6);
         }
      }
   }

   public static boolean withinBuildReach(class_1646 var0, class_2338 var1) {
      double var2 = var0.method_23317() - (var1.method_10263() + 0.5);
      double var4 = var0.method_23321() - (var1.method_10260() + 0.5);
      return var2 * var2 + var4 * var4 <= 36.0 && Math.abs(var0.method_23318() - var1.method_10264()) <= 14.0;
   }

   public static boolean protectPen(class_3218 var0, Colony var1, ConstructionJob var2) {
      if (!var1.x2vcibw4inamjw(var2.x24kbqv6g4kkrg(), 1) || var2.x2s8emavervqrz()) {
         return true;
      } else if (var2.x2nfrt8qxdscso().isPresent()) {
         var2.x2hk920e3qof5e(var0.method_75260() + 1200L);
         ColonyManager.xchl1k1tnh3w8(var0).method_80();
         return false;
      } else {
         var2.x2hk920e3qof5e(0L);
         var1.x2lr91dptxy1ww();
         var1.xs28n9ortj0j6(var0.method_75260());
         ColonyManager.xchl1k1tnh3w8(var0).method_80();
         return false;
      }
   }

   private static boolean x3o70vbguw4qn7(class_1646 var0, class_2338 var1, class_2338 var2) {
      double var3 = var0.method_17681() / 2.0;
      return new class_238(
            var1.method_10263() + 0.5 - var3,
            var1.method_10264(),
            var1.method_10260() + 0.5 - var3,
            var1.method_10263() + 0.5 + var3,
            var1.method_10264() + var0.method_17682(),
            var1.method_10260() + 0.5 + var3
         )
         .method_994(new class_238(var2));
   }

   private static double x1tj11tseztzrt(class_2338 var0, class_2338 var1) {
      double var2 = var0.method_10263() - var1.method_10263();
      double var4 = var0.method_10260() - var1.method_10260();
      return var2 * var2 + var4 * var4;
   }

   private static boolean x3b6f14zbbbwjh(class_2680 var0) {
      return var0.method_45474()
         || var0.method_26204() instanceof class_2302
         || var0.method_26204() instanceof class_2513
         || var0.method_26204() instanceof class_2195
         || var0.method_27852(class_2246.field_46282)
         || var0.method_27852(class_2246.field_46283)
         || var0.method_27852(class_2246.field_10362)
         || var0.method_26164(class_3481.field_15503)
         || var0.method_26164(class_3481.field_15475)
         || WorldEditPolicy.xroinv4hk6l7h(var0);
   }

   private static boolean x10z0hm6bbvhlr(class_3218 var0, class_2338 var1, class_2680 var2) {
      if (WorldEditPolicy.x3o1lagqjim5tz(var0, var1)) {
         return true;
      } else if (var0.method_8321(var1) != null) {
         return true;
      } else {
         return !SmartAulConfig.x2by5f9ehxvwk9().roles.builder_respects_player_builds
            ? false
            : var2.method_26164(class_6862.method_40092(class_7924.field_41254, class_2960.method_60655("smart_colonies", "builder_protected")));
      }
   }

   private class_2338 x2m6fmt7uq9mrl(class_3218 var1, int var2, int var3) {
      int var4 = var1.method_8624(class_2903.field_13203, var2, var3);
      class_2338 var5 = new class_2338(var2, var4, var3);
      class_2680 var6 = var1.method_8320(var5.method_10074());
      return var6.method_27852(class_2246.field_10477) && var6.method_11654(class_2488.field_11518) < 8 ? var5.method_10074() : var5;
   }

   private static boolean x2wwfwdfwbj3ts(String var0) {
      return var0.contains("planks")
         || var0.contains("wood")
         || var0.contains("log")
         || var0.contains("fence")
         || var0.contains("door")
         || var0.contains("trapdoor")
         || var0.contains("wooden_")
         || var0.contains("_stairs")
         || var0.contains("_slab");
   }

   public String debugState() {
      if (this.x6xecrdg78nv4 != null && this.x6xecrdg78nv4.xmu75rcopbjnh() < this.x2npvmjy6y5jzn.size()) {
         ConstructionBlockPlan var1 = this.x2npvmjy6y5jzn.get(this.x6xecrdg78nv4.xmu75rcopbjnh());
         return (var1.x3ln30gt147gjn() ? "расчистка" : (var1.x1jjn414zs82u2() ? "фундамент" : "дом"))
            + " "
            + this.x6xecrdg78nv4.xmu75rcopbjnh()
            + "/"
            + this.x2npvmjy6y5jzn.size()
            + " target="
            + var1.x31k0w8t7ul189().method_23854()
            + " block="
            + class_7923.field_41175.method_10221(var1.x2hmkda3gwcacx().method_26204())
            + (this.x2nyqpt8pxcmd0 == null ? "" : " stand=" + this.x2nyqpt8pxcmd0.method_23854());
      } else {
         return "ожидание";
      }
   }

   private static boolean x3eloj7pplrkw8(String var0) {
      return var0.contains("cobblestone") || var0.contains("stone") || var0.contains("andesite") || var0.contains("diorite") || var0.contains("granite");
   }

   private static void x1sqdj274gd8s2(class_3218 var0, ConstructionJob var1, ConstructionBlockPlan var2) {
      if (var0.method_75260() % 20L == 0L) {
         for (class_1297 var4 : var0.method_8333(
            null, new class_238(var2.x31k0w8t7ul189()).method_1009(0.0, 2.0, 0.0),
            var0x -> var0x.method_5805() && var0x instanceof class_1308
         )) {
            if (var4 instanceof class_1308 var5 && ColonyManager.x5ll9elqpk1a4(var0)) {
               class_2338 var6 = null;
               double var7 = Double.MAX_VALUE;
               int var9 = var1.x24kbqv6g4kkrg().method_35418() - var1.x24kbqv6g4kkrg().method_35415() + 1;
               int var10 = var1.x24kbqv6g4kkrg().method_35420() - var1.x24kbqv6g4kkrg().method_35417() + 1;

               for (int[] var14 : new int[][]{{-3, var10 / 2}, {var9 + 2, var10 / 2}, {var9 / 2, -3}, {var9 / 2, var10 + 2}}) {
                  int var15 = var1.x3ij6gescvakv2().method_10263() + var14[0];
                  int var16 = var1.x3ij6gescvakv2().method_10260() + var14[1];
                  if (var0.method_14178().method_12123(var15 >> 4, var16 >> 4)) {
                     class_2338 var17 = new class_2338(var15, var0.method_8624(class_2903.field_13203, var15, var16), var16);
                     if (VillagerShelter.x9bnd6e2jczhd(var0, var17)
                        && !ProtectedLand.x3gqyq7yie9jxr(var0, var17)
                        && var17.method_10262(var5.method_24515()) < var7) {
                        var7 = var17.method_10262(var5.method_24515());
                        var6 = var17;
                     }
                  }
               }

               if (var6 != null) {
                  if (var5 instanceof class_1646 var18) {
                     for (class_7893 var20 : List.copyOf(var18.method_18868().method_27074())) {
                        if (!(var20 instanceof HouseBuilderTask) && var20.getClass().getName().startsWith("net.smartaul.")) {
                           var20.method_18925(var0, var18, var0.method_75260());
                        }
                     }

                     VillagerShelter.xf8lnb134kd0j(var0, var18, var6, 0.9);
                  } else {
                     boolean moving = var5.method_5942().method_6337(var6.method_10263() + 0.5, var6.method_10264(), var6.method_10260() + 0.5, 0.9);
                     ActionTrace.event(var0, null, null, var6, "obstruction_clear", "mob=" + var5.method_5667()
                        + "; type=" + var5.method_5864() + "; from=" + var5.method_24515().method_23854() + "; path_started=" + moving);
                  }
               }
            }
         }
      }
   }

   static boolean x1zol2fshkzbul(class_2680 var0, class_2680 var1) {
      if (var0.method_26204() != var1.method_26204()) {
         return false;
      } else {
         for (class_2769 var3 : var1.method_28501()) {
            if (!Set.of("shape", "north", "south", "east", "west", "up", "down", "open", "occupied", "powered", "lit", "age", "distance", "persistent")
                  .contains(var3.method_11899())
               && (!(var0.method_26204() instanceof class_2281) || !var3.method_11899().equals("type"))
               && !var0.method_11654(var3).equals(var1.method_11654(var3))) {
               return false;
            }
         }

         return true;
      }
   }

   public static int catchupStep(class_3218 var0, Colony var1, ConstructionJob var2, List<ConstructionBlockPlan> var3, int var4) {
      if (!protectPen(var0, var1, var2)) {
         return 0;
      } else if (!var2.x2nfrt8qxdscso().isPresent() && !var2.x2s8emavervqrz() && !var2.x3vu8mnvg0blk4().isPresent() && WorldEditPolicy.x35ev8l9vc9uk7(var0)) {
         var2.x248ps383cwu1s(var3.size(), var0.method_75260(), 8);
         int var5 = 0;
         int var6 = 0;

         while (var2.xmu75rcopbjnh() < var3.size() && var6++ < 128 && var5 < var4 && var1.x11xljsmcmocmw() > 0) {
            ConstructionBlockPlan var7 = (ConstructionBlockPlan)var3.get(var2.xmu75rcopbjnh());
            class_2338 var8 = var7.x31k0w8t7ul189();
            if (!var0.method_14178().method_12123(var8.method_10263() >> 4, var8.method_10260() >> 4)) {
               ActionTrace.state(var0, var1, "construction_wait", "unloaded_chunk @ " + var8.method_23854());
               break;
            }

            class_2680 var9 = var0.method_8320(var8);
            if (!x1zol2fshkzbul(var9, var7.x2hmkda3gwcacx())
               && (!var7.x1jjn414zs82u2() || !var9.method_26206(var0, var8, class_2350.field_11036) || !var0.method_8316(var8).method_15769())
               && (!var7.x3ln30gt147gjn() || !var9.method_26215())) {
               if (x10z0hm6bbvhlr(var0, var8, var9)) {
                  ActionTrace.state(var0, var1, "construction_wait", "protected_or_block_entity @ " + var8.method_23854());
                  break;
               }

               if (var7.x3ln30gt147gjn()) {
                  if (!x3b6f14zbbbwjh(var9) || !WorldEditPolicy.x231if4rvsvptj(var0, var8, false, null)) {
                     ActionTrace.state(var0, var1, "construction_wait", "unsafe_clearance @ " + var8.method_23854() + "; block=" + var9);
                     break;
                  }
               } else {
                  boolean var10 = var9.method_27852(class_2246.field_10445)
                     && var8.method_10264() <= var2.x3ij6gescvakv2().method_10264()
                     && var8.method_10264() >= var2.x3ij6gescvakv2().method_10264() - 4
                     && var8.method_10263() >= var2.x24kbqv6g4kkrg().method_35415()
                     && var8.method_10263() <= var2.x24kbqv6g4kkrg().method_35418()
                     && var8.method_10260() >= var2.x24kbqv6g4kkrg().method_35417()
                     && var8.method_10260() <= var2.x24kbqv6g4kkrg().method_35420();
                  if (!var9.method_26215() && !x3b6f14zbbbwjh(var9) && !var7.x1jjn414zs82u2() && !var10) {
                     ActionTrace.state(var0, var1, "construction_wait", "non_replaceable @ " + var8.method_23854() + "; block=" + var9);
                     break;
                  }

                  Predicate<class_1799> var11 = materialPredicate(var7.x2hmkda3gwcacx(), materialItem(var7.x2hmkda3gwcacx()));
                  if (!ConstructionManager.xezn2zouajb9i(var0, var1, var11)) {
                     ActionTrace.state(var0, var1, "construction_wait", "missing_material @ " + var8.method_23854());
                     break;
                  }

                  if (!WorldEditPolicy.x1bq0f8n53gtob(var0, var8, var7.x2hmkda3gwcacx(), 3)) {
                     x1sqdj274gd8s2(var0, var2, var7);
                     break;
                  }

                  if (!ConstructionManager.x31mf0b9fst14k(var0, var1, var11)) {
                     WorldEditScope.x2aebumvi646n9(() -> var0.method_8652(var8, var9, 3));
                     break;
                  }
               }

               var1.x3g65nuli3tuzl();
               var2.xdhrxq42ir75w(var0.method_75260());
               var5++;
            } else {
               var2.xdhrxq42ir75w(var0.method_75260());
            }
         }

         if (var2.xmu75rcopbjnh() >= var3.size()) {
            var2.x3lcv8vebl7uhh();
            ActionTrace.event(var0, var1, null, var2.x3ij6gescvakv2(), "construction_complete", "blocks=" + var3.size());
         }

         ColonyManager.xchl1k1tnh3w8(var0).method_80();
         return var5;
      } else {
         return 0;
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
