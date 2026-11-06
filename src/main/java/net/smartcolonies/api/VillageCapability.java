package net.smartcolonies.api;

import java.util.UUID;
import net.minecraft.class_2338;
import net.smartcolonies.r.x1rxiy9mtu5b3h;

public interface VillageCapability {
   UUID id();

   class_2338 core();

   int radius();

   int population();

   int beds();

   int cachedFood();

   int cachedLogs();

   int cachedStone();

   x1rxiy9mtu5b3h roleOf(UUID var1);
}
