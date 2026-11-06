package net.smartcolonies;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.EndWorldTick;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.class_1269;
import net.minecraft.class_2246;
import net.minecraft.class_3218;
import net.smartcolonies.r.x154n3jme5c00e;
import net.smartcolonies.r.x1ln7i1g8dapfg;
import net.smartcolonies.r.x1zf3mxb6erjkn;
import net.smartcolonies.r.x2n55y2tom37h2;
import net.smartcolonies.r.x2s667aisw18ww;
import net.smartcolonies.r.x3sqkg3n6v972r;
import net.smartcolonies.r.x6amcj7vm0jm5;
import net.smartcolonies.r.xnssk2qgda4r6;
import net.smartcolonies.r.xquptt42fexz2;
import net.smartcolonies.r.xxao083dbo7ag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SmartColoniesMod implements ModInitializer {
   public static final String MOD_ID = "smart_aul";
   public static final Logger LOGGER = LoggerFactory.getLogger("smart_aul");

   public void onInitialize() {
      x3sqkg3n6v972r.xhwm4ut4yzbjy();
      xnssk2qgda4r6.x1bu824b0po0bm();
      xxao083dbo7ag.x2ve9opbgxakpk();
      x1ln7i1g8dapfg.xyjj6h9u3nkbj();
      x154n3jme5c00e.x32f59jmix8kb9();
      ServerTickEvents.END_WORLD_TICK.register((EndWorldTick)var0 -> {
         x1zf3mxb6erjkn.x1f4i4km49zkmu(var0);
         x2n55y2tom37h2.xhvlm4bwxgmtf(var0);
      });
      ServerTickEvents.END_SERVER_TICK.register(xquptt42fexz2::x2pghlycghjdni);
      ServerChunkEvents.CHUNK_LOAD.register(x2s667aisw18ww::xuo3itsmzrpvl);
      ServerChunkEvents.CHUNK_GENERATE.register(x1zf3mxb6erjkn::x2yhjdk5vgl5bq);
      UseBlockCallback.EVENT.register((UseBlockCallback)(var0, var1, var2, var3) -> {
         if (!var1.method_8608() && x6amcj7vm0jm5.x3ew3lrc2q3oxu(var0.method_5998(var2))) {
            x6amcj7vm0jm5.x1mzhkk1en9rl((class_3218)var1, var0, var2, var3.method_17777());
            return class_1269.field_5812;
         } else {
            if (!var1.method_8608() && var1.method_8320(var3.method_17777()).method_27852(class_2246.field_16332)) {
               x2n55y2tom37h2.x30py6w81fega5((class_3218)var1, var3.method_17777());
            }

            return class_1269.field_5811;
         }
      });
      LOGGER.info("Smart Aul initialized for generated vanilla villages");
   }
}
