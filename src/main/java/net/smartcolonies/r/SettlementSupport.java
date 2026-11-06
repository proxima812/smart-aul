package net.smartcolonies.r;

import java.util.UUID;
import net.minecraft.class_2338;
import net.minecraft.class_3218;

public final class SettlementSupport {
   private SettlementSupport() {}
   private record RepairAssignment(long tick, java.util.Optional<UUID> worker) {}
   private static final java.util.Map<x15kgoaq6i3m6i, RepairAssignment> repairs = new java.util.WeakHashMap<>();

   public static boolean shouldRepair(class_3218 world, x15kgoaq6i3m6i colony, net.minecraft.class_1646 worker) {
      if (!x20rghnsl2yowl.x81tynoeus5lh(world, colony)) return false;
      long now = world.method_75260();
      var assignment = repairs.get(colony);
      if (assignment == null || now < assignment.tick() || now - assignment.tick() >= 20L) {
         var builders = world.method_18023(net.minecraft.class_1299.field_6077,
            x2n55y2tom37h2.x150ccxomrlozr(world, colony),
            v -> v.method_5805() && !v.method_6109() && !v.method_6113() && !v.method_18009()
               && colony.roleOf(v.method_5667()) == x1rxiy9mtu5b3h.BUILDER)
            .stream().map(net.minecraft.class_1646::method_5667).toList();
         assignment = new RepairAssignment(now, DevelopmentPolicy.repairWorker(builders, colony.x3e4ycl8cmmmgc()));
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
      int gap = x3sqkg3n6v972r.x2by5f9ehxvwk9().village.settlement_border_gap;
      for (var colony : x3d5vk1zj3fvkj.get(world).colonies()) {
         if (!DevelopmentPolicy.separated(distanceSquared(target, colony.core()), colony.radius(), 96, gap)) return false;
         if (!colony.id().equals(ownExpedition) && colony.x1x0agg3sdxjk8().isPresent()
            && !DevelopmentPolicy.separated(distanceSquared(target, colony.x1x0agg3sdxjk8().get()), 96, 96, gap)) return false;
      }
      return true;
   }

   public static void expandRadius(class_3218 world, x15kgoaq6i3m6i colony, int requested) {
      int radius = Math.min(requested, x3sqkg3n6v972r.x2by5f9ehxvwk9().village.expansion_radius_max);
      int gap = x3sqkg3n6v972r.x2by5f9ehxvwk9().village.settlement_border_gap;
      for (var other : x3d5vk1zj3fvkj.get(world).colonies()) {
         if (!other.id().equals(colony.id()))
            radius = Math.min(radius, (int)Math.floor(Math.sqrt(distanceSquared(colony.core(), other.core()))) - other.radius() - gap);
         if (other.x1x0agg3sdxjk8().isPresent())
            radius = Math.min(radius, (int)Math.floor(Math.sqrt(distanceSquared(colony.core(), other.x1x0agg3sdxjk8().get()))) - 96 - gap);
      }
      // Never shrink existing territory or invalidate existing buildings.
      colony.x1ne2dy4ss3d7w(Math.max(colony.radius(), radius));
   }

   public static String readiness(class_3218 world, x15kgoaq6i3m6i colony) {
      var config = x3sqkg3n6v972r.x2by5f9ehxvwk9();
      if (!config.roles.enable_auto_expansion || !x1dxdlnevtfbhm.x35ev8l9vc9uk7(world)) return "Переселение или изменения ландшафта выключены.";
      if (colony.x2a380ray4md5t().isPresent()) return colony.xfx95ux1xn73w() ? "Экспедиция возвращается с грузом." : "Экспедиция уже в пути.";
      if (x8veg662b2f5p.x2r8c2c8wlnbsc(world, colony)) return "Выполняется постановочная подготовка max.";
      if (x36u1fhs2z6uoi.active(world, colony)) return "Поселение под атакой.";
      long wait = colony.x15y708t7gvjof() - world.method_75260();
      if (wait > 0) return "Следующая попытка переселения через " + (wait + 19) / 20 + " с.";
      if (colony.population() < config.village.settlement_min_population) return "Население " + colony.population() + "/" + config.village.settlement_min_population + ".";
      int food = Math.max(128, colony.population() * 6 + 32);
      if (colony.cachedFood() < food || colony.cachedLogs() < 64 || colony.cachedStone() < 128)
         return "Подготовка склада: еда " + colony.cachedFood() + "/" + food + ", брёвна " + colony.cachedLogs() + "/64, камень " + colony.cachedStone() + "/128.";
      return "";
   }

   public static long completedHouses(x15kgoaq6i3m6i colony) {
      return colony.x1ja61emeln2hc().stream()
         .filter(j -> j.x2nfrt8qxdscso().isEmpty() && j.x32oz43hmeynhc() == x20r5v7z43p3d5.COMPLETE).count();
   }

   public static boolean isCity(x15kgoaq6i3m6i colony) {
      var c = x3sqkg3n6v972r.x2by5f9ehxvwk9().village;
      return colony.population() >= c.city_population && completedHouses(colony) >= c.city_houses;
   }

   public static String progress(x15kgoaq6i3m6i colony) {
      var config = x3sqkg3n6v972r.x2by5f9ehxvwk9().village;
      long houses = completedHouses(colony);
      return "Уровень: " + DevelopmentPolicy.stage(colony.population(), houses, config)
         + ". До города: население " + colony.population() + "/" + config.city_population
         + ", общественные дома " + houses + "/" + config.city_houses + ". Темп: x" + config.development_speed + ".";
   }
}
