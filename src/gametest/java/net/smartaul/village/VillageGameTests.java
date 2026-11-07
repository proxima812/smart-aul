package net.smartaul.village;

import java.util.UUID;
import net.fabricmc.fabric.api.gametest.v1.GameTest;
import net.minecraft.*;
import net.smartaul.config.SmartAulConfig;

/** Real ServerWorld tests; package access avoids adding test hooks to the release mod. */
public final class VillageGameTests {
    @GameTest
    public void deepHoleQueuesExactlyOneCover(class_4516 context) {
        checkHole(context, true);
    }

    @GameTest
    public void shallowHoleDoesNotQueueCover(class_4516 context) {
        checkHole(context, false);
    }

    private void checkHole(class_4516 context, boolean deep) {
        var world = context.method_35943();
        boolean oldTerrain = SmartAulConfig.x2by5f9ehxvwk9().roles.allow_terrain_changes;
        boolean oldRule = world.method_64395().method_76185(class_1928.field_19388);
        try {
            SmartAulConfig.x2by5f9ehxvwk9().roles.allow_terrain_changes = true;
            world.method_64395().method_76186(class_1928.field_19388, true, world.method_8503());
            // A three-air-block drop beside a solid rim, fully inside the empty 8x8 fixture.
            for (int x = 1; x <= 5; x++) for (int z = 1; z <= 5; z++)
                context.method_35946(x, 0, z, class_2246.field_10340);
            for (int x = 1; x <= 5; x++) for (int z = 1; z <= 5; z++)
                if (x != 3 || z != 2) context.method_35946(x, 3, z, class_2246.field_10340);
            if (!deep) context.method_35946(3, 2, 2, class_2246.field_10340);
            var feet = context.method_36052(new class_2338(2, 4, 2));
            var hole = context.method_36052(new class_2338(3, 3, 2));
            var worker = context.method_35962(class_1299.field_6077, 2, 4, 2);
            worker.method_5977(true); // Keep the worker on the rim while observing synchronously.
            var colony = new Colony(UUID.randomUUID(), feet, 4, world.method_75260());
            context.method_75529(VillageSafety.loaded(world, hole), "Fixture chunk must be loaded");
            context.method_75529(VillageSafety.deepDrop(world, hole.method_10084()) == deep,
                "Real block column must have the expected depth classification");
            VillageSafety.observe(world, colony);
            context.method_75529(colony.safetyCovers.contains(hole) == deep,
                "Only the deep hole must enter the safety queue");
            int queued = colony.safetyCovers.size();
            context.method_75529(queued == (deep ? 1 : 0), "Only the fixture hole may be queued");
            VillageSafety.observe(world, colony);
            context.method_75529(colony.safetyCovers.size() == queued,
                "Repeated observation must not duplicate work");
            context.method_75529(colony.x2xqc3q79vfkw3().containsKey(hole) == deep,
                "Repair plan must agree with the safety queue");
            context.method_75529(world.method_8320(hole).method_26215(),
                "Observation must not place free blocks");
        } finally {
            SmartAulConfig.x2by5f9ehxvwk9().roles.allow_terrain_changes = oldTerrain;
            world.method_64395().method_76186(class_1928.field_19388, oldRule, world.method_8503());
        }
        context.method_36036();
    }

    @GameTest
    public void mobGriefingBlocksPlacementAndBreaking(class_4516 context) {
        var world = context.method_35943();
        var pos = context.method_36052(new class_2338(3, 3, 3));
        boolean oldTerrain = SmartAulConfig.x2by5f9ehxvwk9().roles.allow_terrain_changes;
        boolean oldRule = world.method_64395().method_76185(class_1928.field_19388);
        try {
            SmartAulConfig.x2by5f9ehxvwk9().roles.allow_terrain_changes = true;
            world.method_64395().method_76186(class_1928.field_19388, true, world.method_8503());
            context.method_75529(place(world, pos), "Positive control: unprotected placement must succeed");
            world.method_64395().method_76186(class_1928.field_19388, false, world.method_8503());
            context.method_75529(!WorldEditPolicy.x231if4rvsvptj(world, pos, false, null),
                "mobGriefing=false must prevent breaking");
            context.method_75529(world.method_8320(pos).method_27852(class_2246.field_10340),
                "Denied breaking must leave the block intact");
            var empty = pos.method_10084();
            context.method_75529(!place(world, empty), "mobGriefing=false must prevent placement");
            context.method_75529(world.method_8320(empty).method_26215(), "Denied placement must leave air");
            world.method_64395().method_76186(class_1928.field_19388, true, world.method_8503());
            context.method_75529(WorldEditPolicy.x231if4rvsvptj(world, pos, false, null),
                "Positive control: breaking must resume when the rule is restored");
        } finally {
            SmartAulConfig.x2by5f9ehxvwk9().roles.allow_terrain_changes = oldTerrain;
            world.method_64395().method_76186(class_1928.field_19388, oldRule, world.method_8503());
        }
        context.method_36036();
    }

    @GameTest
    public void landClaimBlocksPlacementAndBreaking(class_4516 context) {
        var world = context.method_35943();
        var pos = context.method_36052(new class_2338(3, 3, 3));
        var claim = new LandClaim(UUID.randomUUID(), UUID.randomUUID(), pos, 1);
        var data = ColonyWorldData.get(world);
        boolean oldTerrain = SmartAulConfig.x2by5f9ehxvwk9().roles.allow_terrain_changes;
        boolean oldRule = world.method_64395().method_76185(class_1928.field_19388);
        try {
            SmartAulConfig.x2by5f9ehxvwk9().roles.allow_terrain_changes = true;
            world.method_64395().method_76186(class_1928.field_19388, true, world.method_8503());
            context.method_75529(place(world, pos), "Positive control: placement before claim must succeed");
            data.addLand(claim);
            context.method_75529(ProtectedLand.x3gqyq7yie9jxr(world, pos), "Claim must protect the real world position");
            context.method_75529(!WorldEditPolicy.x231if4rvsvptj(world, pos, false, null), "Claim must prevent breaking");
            context.method_75529(world.method_8320(pos).method_27852(class_2246.field_10340), "Claimed block must remain");
            context.method_75529(!place(world, pos.method_10084()), "Claim must prevent placement");
            context.method_75529(world.method_8320(pos.method_10084()).method_26215(), "Claimed air must remain");
            context.method_75529(place(world, pos.method_10069(3, 0, 0)),
                "Positive control: placement outside the claim must succeed");
        } finally {
            SmartAulConfig.x2by5f9ehxvwk9().roles.allow_terrain_changes = oldTerrain;
            world.method_64395().method_76186(class_1928.field_19388, oldRule, world.method_8503());
        }
        context.method_36036();
    }

    private boolean place(class_3218 world, class_2338 pos) {
        return WorldEditPolicy.x1bq0f8n53gtob(world, pos, class_2246.field_10340.method_9564(), 3);
    }
}
