package net.smartaul.api;

import java.util.UUID;
import net.minecraft.class_2338;
import net.smartaul.village.ColonyRole;

public interface VillageCapability {
   UUID id();

   class_2338 core();

   int radius();

   int population();

   int beds();

   int cachedFood();

   int cachedLogs();

   int cachedStone();

   ColonyRole roleOf(UUID var1);
}
