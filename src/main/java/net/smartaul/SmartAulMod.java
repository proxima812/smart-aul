package net.smartaul;

import net.smartaul.command.ColonyCommands;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.EndWorldTick;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.class_1269;
import net.minecraft.class_2246;
import net.minecraft.class_3218;
import net.smartaul.village.ColonySensors;
import net.smartaul.village.PlayerBuildTracker;
import net.smartaul.village.ColonyManager;
import net.smartaul.village.VillageStructureDiscovery;
import net.smartaul.config.SmartAulConfig;
import net.smartaul.village.LandClaimManager;
import net.smartaul.village.ColonyActivities;
import net.smartaul.village.PropertyDeeds;
import net.smartaul.village.ColonyMemories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SmartAulMod implements ModInitializer {
   public static final String MOD_ID = "smart_aul";
   public static final Logger LOGGER = LoggerFactory.getLogger("smart_aul");

   public void onInitialize() {
      SmartAulConfig.xhwm4ut4yzbjy();
      ColonyActivities.x1bu824b0po0bm();
      ColonyMemories.x2ve9opbgxakpk();
      ColonySensors.xyjj6h9u3nkbj();
      ColonyCommands.x32f59jmix8kb9();
      ServerTickEvents.END_WORLD_TICK.register((EndWorldTick)var0 -> {
         PlayerBuildTracker.x1f4i4km49zkmu(var0);
         long started = System.nanoTime();
         ColonyManager.xhvlm4bwxgmtf(var0);
         if (var0.method_75260() % 20 == 0 && net.smartaul.village.ActionTrace.enabled())
            net.smartaul.village.ActionTrace.event(var0, null, null, null, "manager_tick", "us=" + (System.nanoTime() - started) / 1000);
      });
      ServerTickEvents.END_SERVER_TICK.register(PropertyDeeds::x2pghlycghjdni);
      ServerChunkEvents.CHUNK_LOAD.register(VillageStructureDiscovery::xuo3itsmzrpvl);
      ServerChunkEvents.CHUNK_GENERATE.register(PlayerBuildTracker::x2yhjdk5vgl5bq);
      UseBlockCallback.EVENT.register((UseBlockCallback)(var0, var1, var2, var3) -> {
         if (!var1.method_8608() && !LandClaimManager.x3ew3lrc2q3oxu(var0.method_5998(var2))
            && net.smartaul.village.TownHall.interact((class_3218)var1, var0, var3.method_17777())) return class_1269.field_5812;
         if (!var1.method_8608() && LandClaimManager.x3ew3lrc2q3oxu(var0.method_5998(var2))) {
            LandClaimManager.x1mzhkk1en9rl((class_3218)var1, var0, var2, var3.method_17777());
            return class_1269.field_5812;
         } else {
            if (!var1.method_8608() && var1.method_8320(var3.method_17777()).method_27852(class_2246.field_16332)) {
               ColonyManager.x30py6w81fega5((class_3218)var1, var3.method_17777());
            }

            return class_1269.field_5811;
         }
      });
      LOGGER.info("Smart Aul initialized for generated vanilla villages");
   }
}
