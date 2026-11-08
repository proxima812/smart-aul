package net.smartaul.village;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import net.fabricmc.fabric.api.gametest.v1.GameTest;
import net.minecraft.*;
import net.smartaul.config.SmartAulConfig;

public final class OfflineBudgetGameTests {
    @GameTest
    public void offlineOneAndThirtyDaysRespectCapsAndResources(class_4516 c) {
        var world=c.method_35943();
        var config=SmartAulConfig.x2by5f9ehxvwk9();
        boolean enabled=config.village.offline_development, terrain=config.roles.allow_terrain_changes;
        int days=config.village.offline_max_days, speed=config.village.development_speed;
        boolean rule=world.method_64395().method_76185(class_1928.field_19388);
        try {
            config.village.offline_development=true; config.village.offline_max_days=6;
            config.village.development_speed=3; config.roles.allow_terrain_changes=true;
            world.method_64395().method_76186(class_1928.field_19388,true,world.method_8503());
            java.util.Map<class_2960,Integer> cappedProduction=null;
            for(int elapsed : new int[]{1,6,30}) for(boolean staffed : new boolean[]{false,true}) {
                var colony=new Colony(UUID.randomUUID(),c.method_36052(new class_2338(3,2,3)),4,world.method_75260());
                colony.x1c0m5jj5x6wpm(8,12,world.method_75260());
                var nbt=colony.xufzblerzrsnk();
                nbt.method_10544("simulated_until",world.method_75260()-elapsed*24000L);
                nbt.method_10556("initial_development",false);
                var census=new class_2487();
                if(staffed) for(var role : new ColonyRole[]{ColonyRole.FARMER,ColonyRole.MINER,ColonyRole.LUMBERJACK,ColonyRole.BUILDER}) census.method_10569(role.serializedName(),1);
                nbt.method_10566("labor_census",census);
                colony=Colony.x3bvvkrzdde479(nbt);
                // Real offline entry point; elapsed time is saved state, not millions of simulated ticks.
                ConstructionManager.x3ij59k18pnfmd(world,colony);
                c.method_75529(colony.x2ya2lfuvm01ea()==Math.min(elapsed,6)*24000L,"Offline elapsed time must be capped to six days");
                c.method_75529(colony.population()==8 && colony.x1ja61emeln2hc().isEmpty(),"Offline credits cannot instantly create villagers or free houses");
                if(!staffed) {
                    c.method_75529(colony.x1i0d1gs2hqpsr().isEmpty(),"No workers/food: no free production");
                    c.method_75529(colony.x11xljsmcmocmw()==0 && colony.x3h8wqo6wk0f7p()==0 && colony.x2p9t1nvxo82zp()==0,"Starved colony cannot earn growth");
                } else {
                    if(elapsed==6) cappedProduction=java.util.Map.copyOf(colony.x1i0d1gs2hqpsr());
                    if(elapsed==30) c.method_75529(colony.x1i0d1gs2hqpsr().equals(cappedProduction),"Thirty days must produce exactly the same resources as the six-day cap");
                    c.method_75529(!colony.x1i0d1gs2hqpsr().isEmpty(),"Positive control: staffed colony earns bounded production");
                    c.method_75529(colony.x1i0d1gs2hqpsr().values().stream().allMatch(v->v>0 && v<=4096),"Per-resource production cap");
                    c.method_75529(colony.x11xljsmcmocmw()==Math.min(12000,Math.min(elapsed,6)*3000),"Labor respects elapsed days and cap");
                    c.method_75529(colony.x3h8wqo6wk0f7p()==Math.min(8,Math.min(elapsed,6)*3),"Birth credit cap");
                    c.method_75529(colony.x2p9t1nvxo82zp()==Math.min(4,Math.min(elapsed,6)*3),"House credit cap");
                }
                var before=colony.xufzblerzrsnk();
                ConstructionManager.x3ij59k18pnfmd(world,colony);
                c.method_75529(before.equals(colony.xufzblerzrsnk()),"Repeated processing in same tick must not mint resources/credits");
                c.method_75529(OfflineDevelopment.x2m8lyhoez9v7c(world,colony)==0,"Empty stores cannot convert free resources");
            }
        } finally {
            config.village.offline_development=enabled; config.village.offline_max_days=days;
            config.village.development_speed=speed; config.roles.allow_terrain_changes=terrain;
            world.method_64395().method_76186(class_1928.field_19388,rule,world.method_8503());
        }
        c.method_36036();
    }

    @GameTest
    public void offlineConversionConsumesRealStoreSourceExactlyOnce(class_4516 c) {
        var world=c.method_35943();
        c.method_35946(3,2,3,class_7923.field_41175.method_63535(class_2960.method_60655("minecraft","chest")));
        var pos=c.method_36052(new class_2338(3,2,3));
        var store=(class_1263)world.method_8321(pos);
        var colony=new Colony(UUID.randomUUID(),pos,4,world.method_75260());
        colony.x1v7s85y1rhqmd(pos,HousePurpose.GENERAL);
        for(int i=0;i<10;i++) store.method_5447(i,new class_1799(class_1802.field_8103));
        c.method_75529(OfflineDevelopment.x31lz6msk67uxc(world,colony)==10,"Real loaded storage has ten input items");
        c.method_75529(OfflineDevelopment.x2m8lyhoez9v7c(world,colony)==6,"Conversion consumes six inputs and reserves four");
        int input=0,output=0;
        for(int i=0;i<store.method_5439();i++) {
            var stack=store.method_5438(i);
            if(stack.method_31574(class_1802.field_8103)) input+=stack.method_7947();
            if(stack.method_31574(class_1802.field_8550)) output+=stack.method_7947();
        }
        c.method_75529(input==4 && output==6,"No free output: every converted item consumes one input");
        c.method_75529(OfflineDevelopment.x2m8lyhoez9v7c(world,colony)==0,"Repeated conversion cannot consume the reserve or duplicate output");
        c.method_36036();
    }
    @GameTest
    public void worldSearchBudgetExhaustsAndResetsNextTick(class_4516 c) {
        var world=c.method_35943();
        // Separate world-tick budget from NavigationSearch node/time budgets.
        int granted=0;
        while(ColonyManager.x5ll9elqpk1a4(world)) granted++;
        c.method_75529(granted==6,"Fresh world tick must grant six searches");
        for(int i=0;i<20;i++) c.method_75529(!ColonyManager.x5ll9elqpk1a4(world),"Exhaustion must remain stable within tick");
        c.method_36003(1,()-> {
            for(int i=0;i<6;i++) c.method_75529(ColonyManager.x5ll9elqpk1a4(world),"Next tick restores search quota");
            c.method_75529(!ColonyManager.x5ll9elqpk1a4(world),"Restored budget is still bounded");
            c.method_36036();
        });
    }

    @GameTest
    public void navigationNodeAndTimeExhaustionReturnsSafePartialPath(class_4516 c) {
        var start=new class_2338(0,0,0); var goal=new class_2338(100,0,0);
        var nodes=NavigationSearch.find(start,goal,p->p.method_10264()==0?0:-1,(a,b)->true,4,0);
        c.method_75529(nodes.expanded()==4 && !nodes.reached() && !nodes.points().isEmpty(),"Node exhaustion must return partial progress, never success");
        var calls=new AtomicInteger();
        var timed=NavigationSearch.find(start,goal,p->{
            // Ensure the elapsed-time condition fires without relying on machine speed or sleeps.
            if(calls.getAndIncrement()==0) { long until=System.nanoTime()+2_000_000; while(System.nanoTime()<until) Thread.onSpinWait(); }
            return p.method_10264()==0?0:-1;
        },(a,b)->true,1000,1_000_000);
        c.method_75529(timed.expanded()==1 && !timed.reached() && timed.points().isEmpty(),"Time exhaustion before second expansion must return no path and no success");
        var previous=start;
        for(var p:nodes.points()) {
            c.method_75529(p.method_10264()==0 && previous.method_10262(p)<=2,"Partial path must remain adjacent and walkable"); previous=p;
        }
        var complete=NavigationSearch.find(start,new class_2338(5,0,0),p->p.method_10264()==0?0:-1,(a,b)->true,1000,0);
        c.method_75529(complete.reached(),"Positive control: sufficient budget reaches goal");
        c.method_36036();
    }
}
