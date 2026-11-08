package net.smartaul.village;

import java.nio.file.*;
import java.util.*;
import net.fabricmc.fabric.api.client.gametest.v1.FabricClientGameTest;
import net.fabricmc.fabric.api.client.gametest.v1.context.*;
import net.fabricmc.fabric.api.client.gametest.v1.screenshot.TestScreenshotOptions;
import net.minecraft.*;
import net.smartaul.client.BlueprintClient;

/** Disposable world; exercises the production plan, periodic S2C and real villager brain. */
public final class VisualGameTests implements FabricClientGameTest {
    private ConstructionJob job;
    private Colony colony;
    private class_3765 raid;
    private List<ConstructionBlockPlan> plan;
    private final List<class_1646> visitors = new ArrayList<>();
    private final Path output = Path.of(System.getProperty("smart_aul.visual-output", "screenshots"));

    @Override public void runTest(ClientGameTestContext context) {
        try (var game = context.worldBuilder().create()) {
            var server = game.getServer();
            context.runOnClient(client -> client.field_1690.field_1842 = true);
            server.runCommand("gamemode spectator @a");
            server.runCommand("gamerule minecraft:advance_time false");
            server.runCommand("gamerule minecraft:mob_griefing true");
            server.runCommand("time set 10000");
            server.runCommand("tp @a 17 -51 25 140 25");
            game.getClientWorld().waitForChunksRender();
            server.runOnServer(s -> {
                var world = s.method_30002();
                var origin = new class_2338(0, -60, 0);
                colony = new Colony(UUID.randomUUID(), origin.method_10069(-4, 0, 6), 48, world.method_75260());
                world.method_8652(colony.core(), class_2246.field_16332.method_9564(), 3);
                ColonyWorldData.get(world).add(colony);
                job = new ConstructionJob(UUID.randomUUID(), VillageLeisure.TEMPLATE, origin, 9, 6, 13);
                colony.x31ve0dsfeaxo4(job);
                plan = HouseBuilderTask.loadPlan(world, job).orElseThrow();
                require(!plan.isEmpty(), "Tavern plan must load");
                for (var b : plan) if (!b.x3ln30gt147gjn() && b.x31k0w8t7ul189().method_10264() == -60)
                    world.method_8652(b.x31k0w8t7ul189(), b.x2hmkda3gwcacx(), 3);
                var snapshot = BlueprintPlans.snapshot(world, origin);
                require(!snapshot.blocks().isEmpty(), "Production snapshot must contain missing blocks");
                require(snapshot.blocks().stream().noneMatch(b -> class_2338.method_10092(b.pos()).method_10264() == -60),
                    "Already placed floor must not be included in payload");
                System.out.println("VISUAL blueprint missing=" + snapshot.blocks().size() + "; placed floor excluded");
            });
            server.runCommand("execute as @a run smart_aul blueprint on");
            context.waitFor(client -> meshVertices() > 0, 200);
            context.waitFor(client -> !client.field_1687.method_8320(new class_2338(0, -60, 0)).method_26215(), 200);
            game.getClientWorld().waitForChunksRender();
            screenshot(context, "blueprint-front");
            context.waitTicks(20);
            screenshot(context, "blueprint-front-later");
            server.runCommand("tp @a -10 -53 -9 -40 20");
            context.waitTicks(10);
            game.getClientWorld().waitForChunksRender();
            screenshot(context, "blueprint-back");
            server.runCommand("execute as @a run smart_aul blueprint off");
            context.waitFor(client -> meshVertices() == 0, 100);
            screenshot(context, "blueprint-off");
            for (int i=0; i<20; i++) {
                int x = 12 + (i%10)*2;
                int z = 6 + (i/10)*4;
                server.runCommand("setblock " + x + " -60 " + z + " minecraft:red_bed[facing=south,part=foot]");
                server.runCommand("setblock " + x + " -60 " + (z+1) + " minecraft:red_bed[facing=south,part=head]");
            }
            server.runOnServer(s -> {
                var world = s.method_30002();
                for (var b : plan) if (!b.x3ln30gt147gjn())
                    world.method_8652(b.x31k0w8t7ul189(), b.x2hmkda3gwcacx(), 3);
                job.x3lcv8vebl7uhh();
                for (int i = 0; i < 4; i++) {
                    var v = class_1299.field_6077.method_5883(world, class_3730.field_16462);
                    require(v != null, "Villager creation");
                    v.method_5808(4.5 + i, -59, 15.5, 180, 0);
                    v.method_35199().method_5447(0, bread());
                    v.method_18868().method_18878(class_4140.field_18438,
                        class_4208.method_19443(world.method_27983(), new class_2338(12+i*2, -60, 7)));
                    world.method_8649(v);
                    visitors.add(v);
                }
            });
            server.runCommand("tp @a 17 -51 25 140 25");
            // Network synchronization is disabled: wait for the actual placed bar before rendering.
            context.waitFor(client -> !client.field_1687.method_8320(new class_2338(4, -59, 2)).method_26215(), 200);
            game.getClientWorld().waitForChunksRender();
            screenshot(context, "tavern-exterior");
            server.runCommand("tp @a 4.5 -57 8 180 38");
            boolean arrived = false;
            for (int tick = 0; tick < 500; tick++) {
                context.waitTick();
                if (tick % 20 == 0) server.runOnServer(s -> visitors.forEach(v -> System.out.println("VISUAL tick=" + s.method_30002().method_75260() + " pos=" + v.method_24515() + " goal=" + VillageLeisure.goal(v) + " active=" + VillageLeisure.active(v, s.method_30002().method_75260()) + " brain=" + v.method_18868().method_24538() + " role=" + colony.roleOf(v.method_5667()) + " task=" + v.method_18868().method_27074())));
                if (server.computeOnServer(s -> visitors.stream().filter(v -> {
                    var goal = VillageLeisure.goal(v);
                    return goal != null && v.method_5649(goal.method_10263()+.5, goal.method_10264(), goal.method_10260()+.5) <= 2.25;
                }).count() >= 2)) { arrived = true; break; }
            }
            server.runOnServer(s -> visitors.removeIf(v -> VillageLeisure.goal(v) == null
                || v.method_5649(VillageLeisure.goal(v).method_10263()+.5, VillageLeisure.goal(v).method_10264(), VillageLeisure.goal(v).method_10260()+.5) > 2.25));
            screenshot(context, "tavern-evening");
            server.runOnServer(s -> visitors.forEach(v -> System.out.println("VISUAL visitor=" + v.method_24515()
                + " goal=" + VillageLeisure.goal(v) + " brain=" + v.method_18868().method_24538())));
            require(arrived, "Both villagers must walk to leisure points within 500 ticks");
            var evening = server.computeOnServer(s -> visitors.stream().map(class_1646::method_24515).toList());
            server.runCommand("difficulty normal");
            server.runCommand("gamemode creative @a");
            server.runOnServer(s -> {
                var world = s.method_30002();
                world.method_64395().method_76186(class_1928.field_19422, true, s);
                raid = world.method_19495().method_16540(world.method_18456().getFirst(), colony.core());
                require(raid != null && RaidShelterTask.active(world, colony), "Real raid must be active at colony");
            });
            server.runCommand("gamemode spectator @a");
            server.runCommand("tp @a 4.5 -57 8 180 38");
            context.waitTicks(40);
            server.runOnServer(s -> visitors.forEach(v -> {
                require(!VillageLeisure.active(v, s.method_30002().method_75260()), "Raid must release leisure visit");
                require(v.method_18868().method_24538().filter(ColonyActivities.LEISURE::equals).isEmpty(), "Raid must interrupt leisure activity");
            }));
            screenshot(context, "tavern-raid");
            server.runOnServer(s -> raid.method_16506());
            server.runCommand("gamemode spectator @a");
            server.runCommand("time set 13000");
            context.waitTicks(600);
            server.runOnServer(s -> visitors.forEach(v -> System.out.println("VISUAL night pos=" + v.method_24515() + " sleeping=" + v.method_6113() + " home=" + v.method_18868().method_18904(class_4140.field_18438))));
            server.runOnServer(s -> {
                for (int i=0; i<visitors.size(); i++) {
                    var v = visitors.get(i);
                    require(!VillageLeisure.active(v, s.method_30002().method_75260()), "Sleep time must end leisure");
                    require(!v.method_24515().equals(evening.get(i)), "Villagers must leave tables at night");

                }
                require(visitors.stream().anyMatch(class_1646::method_6113), "At least one visitor must reach sleep");
            });
            screenshot(context, "tavern-night");
            server.runCommand("tp @a 15 -55 13 180 35");
            context.waitTicks(10);
            screenshot(context, "tavern-sleep");
            System.out.println("VISUAL PASS: blueprint packet/render, placed-floor exclusion, evening arrival, raid interruption and night sleep");
        }
    }

    private static class_1799 bread() {
        for (var item : class_7923.field_41178) if (class_7923.field_41178.method_10221(item).toString().equals("minecraft:bread")) {
            var stack = new class_1799(item, 64);
            require(ColonyManager.xhahxgpa08yc9(stack) > 0, "Fixture food must be edible");
            return stack;
        }
        throw new AssertionError("Bread item missing");
    }
    private static int meshVertices() {
        try {
            var field = BlueprintClient.class.getDeclaredField("mesh"); field.setAccessible(true);
            var mesh = field.get(null);
            var vertices = mesh.getClass().getDeclaredMethod("vertices"); vertices.setAccessible(true);
            return ((float[]) vertices.invoke(mesh)).length;
        } catch (ReflectiveOperationException e) { throw new AssertionError(e); }
    }
    private void screenshot(ClientGameTestContext context, String name) {
        context.takeScreenshot(TestScreenshotOptions.of(name).withDestinationDir(output).disableCounterPrefix());
    }
    private static void require(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
}
