package net.smartaul.village;

import net.smartaul.config.DevelopmentPolicy;
import net.smartaul.config.SmartAulConfig;

import java.util.UUID;
import net.minecraft.class_2338;
import net.minecraft.class_3218;

public final class SettlementSupport {
   private SettlementSupport() {}
   private record RepairAssignment(long tick, boolean urgent, java.util.Optional<UUID> worker) {}
   private static final java.util.Map<Colony, RepairAssignment> repairs = new java.util.WeakHashMap<>();

   public static boolean shouldRepair(class_3218 world, Colony colony, net.minecraft.class_1646 worker) {
      if (!VillageRepair.x81tynoeus5lh(world, colony)) return false;
      boolean urgent = colony.safetyCovers.stream().anyMatch(pos -> VillageRepair.x2b8s98wj2zcbw(world, colony, pos));
      long now = world.method_75260();
      var assignment = repairs.get(colony);
      if (assignment == null || urgent != assignment.urgent() || now < assignment.tick() || now - assignment.tick() >= 20L) {
         var builders = world.method_18023(net.minecraft.class_1299.field_6077,
            ColonyManager.x150ccxomrlozr(world, colony),
            v -> v.method_5805() && !v.method_6109() && !v.method_6113() && !v.method_18009()
               && colony.roleOf(v.method_5667()) == ColonyRole.BUILDER)
            .stream().map(net.minecraft.class_1646::method_5667).toList();
         assignment = new RepairAssignment(now, urgent, DevelopmentPolicy.repairWorker(builders, colony.x3e4ycl8cmmmgc() && !urgent));
         repairs.put(colony, assignment);
      }
      return assignment.worker().filter(worker.method_5667()::equals).isPresent();
   }

   static double distanceSquared(class_2338 a, class_2338 b) {
      double dx = (double)a.method_10263() - b.method_10263();
      double dz = (double)a.method_10260() - b.method_10260();
      return dx * dx + dz * dz;
   }

   /** Checks both existing territories and reserved expedition destinations. */
   public static boolean available(class_3218 world, class_2338 target, UUID ownExpedition) {
      int gap = SmartAulConfig.x2by5f9ehxvwk9().village.settlement_border_gap;
      for (var colony : ColonyWorldData.get(world).colonies()) {
         if (!DevelopmentPolicy.separated(distanceSquared(target, colony.core()), colony.radius(), 96, gap)) return false;
         if (!colony.id().equals(ownExpedition) && colony.x1x0agg3sdxjk8().isPresent()
            && !DevelopmentPolicy.separated(distanceSquared(target, colony.x1x0agg3sdxjk8().get()), 96, 96, gap)) return false;
      }
      return true;
   }

   public static void expandRadius(class_3218 world, Colony colony, int requested) {
      int radius = Math.min(requested, SmartAulConfig.x2by5f9ehxvwk9().village.expansion_radius_max);
      int gap = SmartAulConfig.x2by5f9ehxvwk9().village.settlement_border_gap;
      for (var other : ColonyWorldData.get(world).colonies()) {
         if (!other.id().equals(colony.id()))
            radius = Math.min(radius, (int)Math.floor(Math.sqrt(distanceSquared(colony.core(), other.core()))) - other.radius() - gap);
         if (other.x1x0agg3sdxjk8().isPresent())
            radius = Math.min(radius, (int)Math.floor(Math.sqrt(distanceSquared(colony.core(), other.x1x0agg3sdxjk8().get()))) - 96 - gap);
      }
      // Never shrink existing territory or invalidate existing buildings.
      colony.x1ne2dy4ss3d7w(Math.max(colony.radius(), radius));
   }

   public static String readiness(class_3218 world, Colony colony) {
      var config = SmartAulConfig.x2by5f9ehxvwk9();
      if (!config.roles.enable_auto_expansion || !WorldEditPolicy.x35ev8l9vc9uk7(world)) return "Переселение или изменения ландшафта выключены.";
      if (colony.x2a380ray4md5t().isPresent()) return colony.xfx95ux1xn73w() ? "Экспедиция возвращается с грузом." : "Экспедиция уже в пути.";
      if (DeveloperMode.x2r8c2c8wlnbsc(world, colony)) return "Выполняется постановочная подготовка max.";
      if (RaidShelterTask.active(world, colony)) return "Поселение под атакой.";
      long wait = colony.x15y708t7gvjof() - world.method_75260();
      if (wait > 0) return "Следующая попытка переселения через " + (wait + 19) / 20 + " с.";
      if (colony.population() < config.village.settlement_min_population) return "Население " + colony.population() + "/" + config.village.settlement_min_population + ".";
      int food = Math.max(128, colony.population() * 6 + 32);
      if (colony.cachedFood() < food || colony.cachedLogs() < 64 || colony.cachedStone() < 128)
         return "Подготовка склада: еда " + colony.cachedFood() + "/" + food + ", брёвна " + colony.cachedLogs() + "/64, камень " + colony.cachedStone() + "/128.";
      return "";
   }

   public static long completedHouses(Colony colony) {
      return colony.x1ja61emeln2hc().stream()
         .filter(j -> j.x2nfrt8qxdscso().isEmpty() && j.x32oz43hmeynhc() == ConstructionStatus.COMPLETE).count();
   }

   public static boolean isCity(Colony colony) {
      var c = SmartAulConfig.x2by5f9ehxvwk9().village;
      return colony.population() >= c.city_population && completedHouses(colony) >= c.city_houses;
   }

   public static String progress(Colony colony) {
      var config = SmartAulConfig.x2by5f9ehxvwk9().village;
      long houses = completedHouses(colony);
      return "Уровень: " + DevelopmentPolicy.stage(colony.population(), houses, config)
         + ". До города: население " + colony.population() + "/" + config.city_population
         + ", общественные дома " + houses + "/" + config.city_houses + ". Темп: x" + config.development_speed + ".";
   }
}
