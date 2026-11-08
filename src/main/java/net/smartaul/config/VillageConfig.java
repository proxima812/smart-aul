package net.smartaul.config;

public final class VillageConfig {
   public int courier_min_population = 10;
   // New fields also receive these defaults when loading an older JSON config.
   public int development_speed = 3;
   public int builder_blocks_per_action = 3;
   public int city_population = 32;
   public int tavern_min_population = 10;
   public int city_houses = 12;
   public boolean automatic_settlement_requires_city = true;
   public int settlement_min_population = 24;
   public int settlement_min_distance = 256;
   public int settlement_max_distance = 512;
   public int settlement_border_gap = 32;
   public int settlement_retry_ticks = 600;
   public int settlement_cooldown_ticks = 12000;
   public int settlement_capital_interval_ticks = 400;
   public int settlement_timeout_ticks = 18000;
   public boolean offline_development = true;
   public int offline_max_days = 6;
   public int offline_blocks_per_tick = 8;
   public int offline_arrival_blocks_per_tick = 512;
   public int max_population_per_bell = 64;
   public int expansion_radius_max = 256;
   public int build_delay_ticks = 10;
   public int max_constructed_houses = 48;
}
