package net.smartaul.village;

import java.io.*;
import java.lang.reflect.Method;
import java.util.*;
import net.fabricmc.fabric.api.gametest.v1.GameTest;
import net.minecraft.*;
import net.smartaul.config.SmartAulConfig;

/** Exercises production task lifecycle in a real world; no placement/progress test hooks. */
public final class ConstructionGameTests {
    private static class_2248 block(String name) {
        return class_7923.field_41175.method_63535(class_2960.method_60655("minecraft", name));
    }

    @GameTest(maxTicks = 400)
    public void builderConsumesEachBlockOnce(class_4516 c) { new Build(c, false, false).start(); }

    @GameTest(maxTicks = 400)
    public void builderWaitsThenUsesDeliveredMaterials(class_4516 c) { new Build(c, true, false).start(); }

    @GameTest(maxTicks = 400)
    public void unfinishedWorldDataResumesWithoutDuplicateJob(class_4516 c) { new Build(c, false, true).start(); }

    private static final class Build {
        final class_4516 c;
        final class_3218 world;
        final boolean wait, reload;
        Colony colony;
        ConstructionJob job;
        HouseBuilderTask task = new HouseBuilderTask();
        class_1646 worker;
        class_1263 chest;
        class_2338 origin;
        class_2960 template;
        int ticks, lastPlaced;
        boolean restored, started;
        Build(class_4516 c, boolean wait, boolean reload) {
            this.c = c; world = c.method_35943(); this.wait = wait; this.reload = reload;
        }
        void start() {
            for (int x=1;x<=6;x++) for(int z=1;z<=6;z++) {
                for(int y=-3;y<=1;y++) c.method_35946(x,y,z, block("stone"));
            }
            origin = c.method_36052(new class_2338(2,1,4));
            for(int x=0;x<3;x++) world.method_8652(origin.method_10069(x,1,0), block("glass").method_9564(),3);
            template = class_2960.method_60655("smart_aul_gametest", "tiny_" + UUID.randomUUID());
            world.method_8503().method_27727().method_15091(template)
                .method_15174(world, origin, new class_2382(3,2,1), false, List.of());
            for(int x=0;x<3;x++) world.method_8652(origin.method_10069(x,1,0), block("air").method_9564(),3);
            c.method_35946(2,2,2,block("chest"));
            var store = c.method_36052(new class_2338(2,2,2));
            chest = (class_1263)world.method_8321(store);
            worker = c.method_35962(class_1299.field_6077,3,2,2);
            worker.method_5977(true); // Test drives the public task lifecycle, vanilla brain cannot race it.
            colony = new Colony(UUID.randomUUID(), worker.method_24515(), 5, world.method_75260());
            colony.x1v7s85y1rhqmd(store, HousePurpose.GENERAL);
            colony.x1z3izo4s1trbu(); // Existing communal warehouse enables construction.
            colony.x36xpmo21hmp9h(worker.method_5667(), ColonyRole.BUILDER);
            job = new ConstructionJob(UUID.randomUUID(),template,origin,3,2,1);
            colony.x31ve0dsfeaxo4(job);
            ColonyWorldData.get(world).add(colony);
            if (!wait) deliver();
            tick();
        }
        void deliver() { chest.method_5447(0,new class_1799(block("glass"),3)); }
        int count(class_1263 inv) {
            int n=0;
            for(int i=0;i<inv.method_5439();i++) if(inv.method_5438(i).method_31574(block("glass").method_8389())) n+=inv.method_5438(i).method_7947();
            return n;
        }
        void cleanup() {
            if(started) task.finishRunning(world,worker,world.method_75260());
            ColonyWorldData.get(world).colonies().remove(colony);
            world.method_8503().method_27727().method_15087(template);
            worker.method_31472();
        }
        void tick() {
            var config=SmartAulConfig.x2by5f9ehxvwk9();
            int speed=config.village.development_speed, batch=config.village.builder_blocks_per_action;
            boolean terrain=config.roles.allow_terrain_changes;
            boolean rule=world.method_64395().method_76185(class_1928.field_19388);
            try {
                config.village.development_speed=100; config.village.builder_blocks_per_action=1;
                config.roles.allow_terrain_changes=true;
                world.method_64395().method_76186(class_1928.field_19388,true,world.method_8503());
                if(!started) {
                    c.method_75529(task.shouldRun(world,worker),"Builder must accept the real template job");
                    task.run(world,worker,world.method_75260()); started=true;
                }
                c.method_75529(task.shouldKeepRunning(world,worker,world.method_75260()),"Task must remain runnable until completion");
                task.keepRunning(world,worker,world.method_75260());
                int placed=0;
                for(int x=0;x<3;x++) if(world.method_8320(origin.method_10069(x,1,0)).method_27852(block("glass"))) placed++;
                int held=count(worker.method_35199()) + (worker.method_6047().method_31574(block("glass").method_8389()) ? worker.method_6047().method_7947() : 0);
                c.method_75529(count(chest)+held+placed == (wait && ticks<25 ? 0 : 3),"Exact conservation after every task action, tick="+ticks);
                c.method_75529(placed>=lastPlaced,"Installed blocks must remain"); lastPlaced=placed;
                if(wait && ticks<25) c.method_75529(placed==0 && job.x32oz43hmeynhc()!=ConstructionStatus.COMPLETE,"No materials: wait without free blocks");
                if(reload && !restored && placed==1) {
                    int cursor=job.xmu75rcopbjnh();
                    UUID id=job.id();

                    task.finishRunning(world,worker,world.method_75260()); started=false;
                    var data=roundTrip(ColonyWorldData.get(world));
                    ColonyWorldData.get(world).colonies().remove(colony);
                    colony=data.byId(colony.id()).orElseThrow();
                    ColonyWorldData.get(world).add(colony);
                    c.method_75529(colony.x1ja61emeln2hc().size()==1,"NBT must retain exactly one job");
                    job=colony.x1ja61emeln2hc().getFirst();
                    c.method_75529(job.id().equals(id) && job.xmu75rcopbjnh()==cursor,"NBT must retain job identity and exact cursor");
                    var second=roundTrip(data).byId(colony.id()).orElseThrow();
                    c.method_75529(second.x1ja61emeln2hc().size()==1 && second.x1ja61emeln2hc().getFirst().id().equals(id),"Second NBT load must also retain one job");
                    task=new HouseBuilderTask(); restored=true;
                }
                if(job.x32oz43hmeynhc()==ConstructionStatus.COMPLETE) {
                    c.method_75529(placed==3 && count(chest)==0 && held==0,"COMPLETE requires all three paid blocks");
                    c.method_75529(!reload || restored,"Must reload while unfinished");
                    c.method_75529(colony.x1ja61emeln2hc().size()==1,"Completion must not duplicate job");
                    cleanup(); c.method_36036(); return;
                }
                if(wait && ticks==24) deliver();
                c.method_75529(++ticks<350,"Construction stalled: cursor="+job.xmu75rcopbjnh()+" placed="+placed);
                c.method_36003(1,this::tick);
            } catch(Throwable e) { cleanup(); throw e; }
            finally {
                config.village.development_speed=speed; config.village.builder_blocks_per_action=batch;
                config.roles.allow_terrain_changes=terrain;
                world.method_64395().method_76186(class_1928.field_19388,rule,world.method_8503());
            }
        }
    }
    private static ColonyWorldData roundTrip(ColonyWorldData data) {
        try {
            Method write=ColonyWorldData.class.getDeclaredMethod("x17s9f8fvs5qrt"); write.setAccessible(true);
            Method read=ColonyWorldData.class.getDeclaredMethod("x1vfd7ma58aaai",class_2487.class); read.setAccessible(true);
            var bytes=new ByteArrayOutputStream();
            class_2507.method_10628((class_2487)write.invoke(data),new DataOutputStream(bytes));
            return (ColonyWorldData)read.invoke(null,class_2507.method_10627(new DataInputStream(new ByteArrayInputStream(bytes.toByteArray()))));
        } catch(ReflectiveOperationException | IOException e) { throw new AssertionError("Real world-data NBT round trip",e); }
    }
}
