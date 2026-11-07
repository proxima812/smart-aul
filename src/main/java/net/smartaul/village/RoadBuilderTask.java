package net.smartaul.village;

import net.smartaul.config.SmartAulConfig;

import com.google.common.collect.ImmutableMap;
import net.minecraft.class_1268;
import net.minecraft.class_1646;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_3218;
import net.minecraft.class_4097;
import net.minecraft.class_4140;
import net.minecraft.class_4141;

public final class RoadBuilderTask extends class_4097<class_1646> {
   private Colony x24sw4yjvvv3sl;
   private long x3c1e4wbf3gizu;
   private final BlockWorkProgress x3nsayy5nxyiob = new BlockWorkProgress();

   public RoadBuilderTask() {
      super(ImmutableMap.of(class_4140.field_18445, class_4141.field_18458), 12000);
   }

   protected boolean shouldRun(class_3218 var1, class_1646 var2) {
      this.x24sw4yjvvv3sl = ColonyManager.x3psqezd5jem54(var1, var2.method_24515())
         .filter(
            var2x -> !var2.method_6109()
               && !var2.method_6113()
               && !var2.method_18009()
               && var2x.roleOf(var2.method_5667()) == ColonyRole.BUILDER
               && !var2x.x2s37os1kht7vr()
               && !var2x.x2ad3s8aentmo4().isEmpty()
               && !var2x.x1n1a2vp01kxzf().isEmpty()
               && !VillageDefense.xuj80syz8c60l(var1, var2x, var2)
               && !VillageRepair.x81tynoeus5lh(var1, var2x)
         )
         .orElse(null);
      return this.x24sw4yjvvv3sl != null;
   }

   protected void run(class_3218 var1, class_1646 var2, long var3) {
      ActionTrace.worker(var2, "task_start", "RoadBuilderTask", false);
      this.x3c1e4wbf3gizu = var3;
   }

   protected boolean shouldKeepRunning(class_3218 var1, class_1646 var2, long var3) {
      return !var2.method_6113()
         && !var2.method_18009()
         && this.x24sw4yjvvv3sl != null
         && this.x24sw4yjvvv3sl.roleOf(var2.method_5667()) == ColonyRole.BUILDER
         && !this.x24sw4yjvvv3sl.x1n1a2vp01kxzf().isEmpty()
         && !VillageDefense.xuj80syz8c60l(var1, this.x24sw4yjvvv3sl, var2)
         && !VillageRepair.x81tynoeus5lh(var1, this.x24sw4yjvvv3sl);
   }

   protected void keepRunning(class_3218 var1, class_1646 var2, long var3) {
      if (var3 >= this.x3c1e4wbf3gizu) {
         if (!WorldEditPolicy.x35ev8l9vc9uk7(var1)) {
            WorkDiagnostics.x1qd0mutwsr3sc(var2, "изменение мира выключено");
         } else {
            this.x3c1e4wbf3gizu = var3 + 10L;
            class_2338 var5 = this.x24sw4yjvvv3sl.x1n1a2vp01kxzf().getFirst();
            if (TerrainSupport.x3hqfrterusj2u(var1, var5)) {
               this.x24sw4yjvvv3sl.x1tvwg66ci1f7k(false);
               ColonyManager.xchl1k1tnh3w8(var1).method_80();
            } else if (!TerrainSupport.xxon284tpck85(var1, this.x24sw4yjvvv3sl, var5)
               || VillagerShelter.xps35ehel6nkk(var2, var5.method_10084(), var3)
               || this.x24sw4yjvvv3sl.x3nmwt0p1j1m6g() >= 600) {
               this.x24sw4yjvvv3sl.x1tvwg66ci1f7k(true);
               WorkDiagnostics.x1qd0mutwsr3sc(var2, "непроходимый сегмент дороги пропущен: " + var5.method_23854());
               ColonyManager.xchl1k1tnh3w8(var1).method_80();
            } else if (var2.method_24515().method_10262(var5.method_10084()) > 9.0) {
               this.x24sw4yjvvv3sl.x1nocpys4yjo2k(var3);
               VillagerShelter.x2wzylgizxltef(
                  var1, var2, class_2338.method_49637(var5.method_10263() + 0.5, var5.method_10264() + 1, var5.method_10260() + 0.5), 0.65
               );
            } else if (!var1.method_8320(var5.method_10084()).method_26215() && !WorldEditPolicy.x231if4rvsvptj(var1, var5.method_10084(), true, var2)) {
               this.x24sw4yjvvv3sl.x1nocpys4yjo2k(var3);
            } else if (this.x3nsayy5nxyiob.xpohy1q30uxlk(var1, var2, var5, SmartAulConfig.x2by5f9ehxvwk9().work.placement_ticks, false)) {
               if (WorldEditPolicy.x1bq0f8n53gtob(var1, var5, class_2246.field_10194.method_9564(), 3)) {
                  this.x24sw4yjvvv3sl.x1tvwg66ci1f7k(false);
                  WorkDiagnostics.x2t98zlq4lu49t(var2, "дорога", 1);
                  var2.method_6104(class_1268.field_5808);
                  ColonyManager.xchl1k1tnh3w8(var1).method_80();
               } else {
                  this.x24sw4yjvvv3sl.x1nocpys4yjo2k(var3);
               }
            }
         }
      }
   }

   protected void finishRunning(class_3218 var1, class_1646 var2, long var3) {
      ActionTrace.worker(var2, "task_stop", "RoadBuilderTask", false);
      this.x3nsayy5nxyiob.xupxzzyodxtwj(var1, var2);
      var2.method_5942().method_6340();
      this.x24sw4yjvvv3sl = null;
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
