package net.smartaul.village;

import net.fabricmc.fabric.api.gametest.v1.GameTest;
import net.minecraft.*;

/**
 * B-002: an expedition leader must find its way around a concave wall facing the start. The
 * pocket is larger than one 3 ms search slice explores, so a greedy one-shot search walks into
 * it and stalls. Real world, real villager AI and the production navigation entry point.
 */
public final class ExpeditionRouteGameTests {
    static final int LENGTH = 120, WIDTH = 64, MAX_TICKS = 2800;

    static boolean wall(int x, int z) {
        boolean border = x == 0 || z == 0 || x == LENGTH - 1 || z == WIDTH - 1;
        boolean back = x == 70 && z >= 6 && z <= 58;
        boolean arm = (z == 6 || z == 58) && x >= 30 && x <= 70;
        return border || back || arm;
    }

    @GameTest(structure = "smart_aul_gametest:long_route", maxTicks = MAX_TICKS + 100)
    public void leaderRoutesAroundConcaveWall(class_4516 c) {
        var world = c.method_35943();
        for (int x = 0; x < LENGTH; x++) for (int z = 0; z < WIDTH; z++) {
            c.method_35946(x, 0, z, class_2246.field_10340);
            for (int y = 1; y <= 3; y++) c.method_35946(x, y, z, wall(x, z) ? class_2246.field_10340 : class_2246.field_10124);
        }
        var leader = c.method_35962(class_1299.field_6077, 4, 1, 32);
        var goal = c.method_36052(new class_2338(114, 1, 32));
        new Walk(c, world, leader, goal).tick();
    }

    private record Walk(class_4516 c, class_3218 world, class_1646 leader, class_2338 goal, int[] ticks) {
        Walk(class_4516 c, class_3218 world, class_1646 leader, class_2338 goal) { this(c, world, leader, goal, new int[1]); }

        void tick() {
            VillagerNavigation.x1wi30zgg7nf81(world, leader, goal, 0.9);
            if (leader.method_24515().method_10262(goal) <= 9.0) {
                net.smartaul.SmartAulMod.LOGGER.info("B-002 GameTest: leader reached the goal after {} ticks", ticks[0]);
                VillagerNavigation.x3oupbjeq14mj8(leader);
                c.method_36036();
                return;
            }
            var origin = c.method_36052(new class_2338(0, 0, 0));
            var p = leader.method_24515();
            c.method_75529(++ticks[0] < MAX_TICKS, "Leader stalled at relative " + (p.method_10263() - origin.method_10263()) + ","
                + (p.method_10260() - origin.method_10260()) + " after " + ticks[0] + " ticks; " + VillagerNavigation.xs0vwkswo7jny(leader));
            c.method_36003(1, this::tick);
        }
    }
}
