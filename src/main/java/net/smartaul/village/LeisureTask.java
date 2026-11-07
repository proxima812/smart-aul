package net.smartaul.village;

import com.google.common.collect.ImmutableMap;
import net.minecraft.*;

/** Standing at tables is deliberate: no invisible mounts or entity pose hacks. */
public final class LeisureTask extends class_4097<class_1646> {
   private long nextMove;
   public LeisureTask() { super(ImmutableMap.of(), 600); }
   @Override
   protected boolean method_18919(class_3218 world, class_1646 v) { return valid(world, v); }
   @Override
   protected boolean method_18927(class_3218 world, class_1646 v, long time) { return valid(world, v); }
   private boolean valid(class_3218 world, class_1646 v) {
      return VillageLeisure.restTime(world.method_8532()) && !v.method_6113() && !v.method_18009()
         && VillageLeisure.active(v, world.method_75260())
         && ColonyManager.x3psqezd5jem54(world, v.method_24515())
            .filter(c -> !RaidShelterTask.active(world, c) && VillageLeisure.completed(c).isPresent()).isPresent();
   }
   @Override
   protected void method_18920(class_3218 world, class_1646 v, long time) { nextMove = time; }
   @Override
   protected void method_18924(class_3218 world, class_1646 v, long time) {
      if (time < nextMove) return;
      nextMove = time + 20;
      var goal = VillageLeisure.goal(v);
      if (!VillageSafety.loaded(world, goal) || !VillagerShelter.x9bnd6e2jczhd(world, goal)) return;
      WorkDiagnostics.x1qd0mutwsr3sc(v, "отдыхает в таверне");
      if (v.method_5649(goal.method_10263() + 0.5, goal.method_10264(), goal.method_10260() + 0.5) > 2.25) {
         VillagerNavigation.x1wi30zgg7nf81(world, v, goal, 0.65);
      } else {
         v.method_18868().method_18875(class_4140.field_18445);
         v.method_5942().method_6340();
         VillageLeisure.companion(v).ifPresent(other -> v.method_5988().method_6226(other, 30.0F, 30.0F));
      }
   }
   @Override
   protected void method_18926(class_3218 world, class_1646 v, long time) {
      VillageLeisure.release(v);
      VillagerNavigation.x3oupbjeq14mj8(v);
   }
}
