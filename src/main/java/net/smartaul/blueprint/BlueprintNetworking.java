package net.smartaul.blueprint;

import java.util.*;
import net.fabricmc.fabric.api.networking.v1.*;
import net.fabricmc.fabric.api.event.lifecycle.v1.*;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.*;
import net.smartaul.village.*;

/** Session preferences belong to the connection, not NBT or persistent player data. */
public final class BlueprintNetworking {
   private static final Set<UUID> disabled = new HashSet<>();
   private BlueprintNetworking() {}
   public static void register() {
      PayloadTypeRegistry.playS2C().register(BlueprintPayload.ID, BlueprintPayload.CODEC);
      ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> disabled.remove(handler.method_32311().method_5667()));
      ServerLifecycleEvents.SERVER_STOPPED.register(server -> disabled.clear());
      ServerTickEvents.END_WORLD_TICK.register(world -> {
         if (world.method_75260() % 60 != 0) return;
         for (var player : world.method_18456())
            if (!disabled.contains(player.method_5667()) && ServerPlayNetworking.canSend(player, BlueprintPayload.ID))
               ServerPlayNetworking.send(player, BlueprintPlans.snapshot(world, player.method_24515()));
      });
      CommandRegistrationCallback.EVENT.register((dispatcher, registry, environment) -> {
         var command = class_2170.method_9247("blueprint");
         for (var mode : List.of("on", "off", "status")) command.then(class_2170.method_9247(mode).executes(ctx -> {
            var player = ctx.getSource().method_9207();
            if (mode.equals("off")) disabled.add(player.method_5667());
            if (mode.equals("on")) disabled.remove(player.method_5667());
            boolean supported = ServerPlayNetworking.canSend(player, BlueprintPayload.ID);
            if (!mode.equals("status") && supported) ServerPlayNetworking.send(player, mode.equals("off")
               ? new BlueprintPayload(List.of(), List.of()) : BlueprintPlans.snapshot(ctx.getSource().method_9225(), player.method_24515()));
            ctx.getSource().method_9226(() -> class_2561.method_43470("Чертежи: " + (disabled.contains(player.method_5667()) ? "выключены" : "включены")
               + (supported ? "" : " (нужен клиент Smart Aul)")), false);
            return 1;
         }));
         dispatcher.register(class_2170.method_9247("smart_aul").then(command).then(class_2170.method_9247("plan").executes(ctx -> {
            var source = ctx.getSource();
            var colony = ColonyManager.x3psqezd5jem54(source.method_9225(), class_2338.method_49638(source.method_9222()));
            if (colony.isEmpty()) { source.method_9226(() -> class_2561.method_43470("Рядом нет колонии."), false); return 0; }
            for (var line : TownHall.details(source.method_9225(), colony.get())) source.method_9226(() -> class_2561.method_43470(line), false);
            return 1;
         })));
      });
   }
}
