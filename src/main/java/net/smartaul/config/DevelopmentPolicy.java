package net.smartaul.config;

import net.smartaul.village.ColonyRole;

/** Bounded development rules shared by normal gameplay, diagnostics and tests. */
public final class DevelopmentPolicy {
   private DevelopmentPolicy() {}

   public static void validate(VillageConfig c) {
      c.courier_min_population = Math.clamp(c.courier_min_population, 8, Math.max(8, c.max_population_per_bell));
      c.tavern_min_population = Math.clamp(c.tavern_min_population, 2, Math.max(2, c.max_population_per_bell));
      c.development_speed = Math.clamp(c.development_speed, 1, 8);
      c.builder_blocks_per_action = Math.clamp(c.builder_blocks_per_action, 1, 8);
      c.city_population = Math.clamp(c.city_population, Math.min(8, c.max_population_per_bell), c.max_population_per_bell);
      c.city_houses = Math.clamp(c.city_houses, 1, c.max_constructed_houses);
      c.settlement_min_population = Math.clamp(c.settlement_min_population, Math.min(12, c.max_population_per_bell), c.max_population_per_bell);
      c.settlement_min_distance = Math.clamp(c.settlement_min_distance, 192, 1024);
      c.settlement_max_distance = Math.clamp(c.settlement_max_distance, c.settlement_min_distance, 1536);
      c.settlement_border_gap = Math.clamp(c.settlement_border_gap, 16, 128);
      c.settlement_retry_ticks = Math.clamp(c.settlement_retry_ticks, 200, 12000);
      c.settlement_cooldown_ticks = Math.clamp(c.settlement_cooldown_ticks, 1200, 72000);
      c.settlement_capital_interval_ticks = Math.clamp(c.settlement_capital_interval_ticks, 100, 2400);
      c.settlement_timeout_ticks = Math.clamp(c.settlement_timeout_ticks, 2400, 72000);
   }

   public static int delay(int ticks, int speed) {
      return Math.max(1, (ticks + Math.clamp(speed, 1, 8) - 1) / Math.clamp(speed, 1, 8));
   }

   public static boolean separated(double distanceSquared, int radius, int otherRadius, int gap) {
      long distance = (long)radius + otherRadius + gap;
      return distanceSquared >= distance * distance;
   }

   public static java.util.List<ColonyRole> minimumRoles(int workers, java.util.List<ColonyRole> active) {
      if (workers >= active.size()) return active;
      // A five-person settlement has an elder and four workers: preserve the entire supply loop.
      return java.util.List.of(ColonyRole.FARMER, ColonyRole.BUILDER,
         ColonyRole.LUMBERJACK, ColonyRole.MINER).stream()
         .filter(active::contains).limit(Math.max(0, workers)).toList();
   }

   public static java.util.Optional<java.util.UUID> repairWorker(java.util.List<java.util.UUID> builders, boolean houseActive) {
      if (houseActive && builders.size() <= 1) return java.util.Optional.empty();
      return builders.stream().min(java.util.UUID::compareTo);
   }

   public static String stage(int population, long houses, VillageConfig config) {
      if (population >= config.city_population && houses >= config.city_houses) return "город";
      if (population >= 16 && houses >= 4) return "посёлок";
      return "деревня";
   }
}
