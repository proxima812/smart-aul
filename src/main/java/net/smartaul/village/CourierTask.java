package net.smartaul.village;

import com.google.common.collect.ImmutableMap;
import net.minecraft.*;

public final class CourierTask extends class_4097<class_1646> {
   private Colony colony;
   private long next;
   public CourierTask() { super(ImmutableMap.of(class_4140.field_18445,class_4141.field_18458),2400); }
   @Override protected boolean method_18919(class_3218 world,class_1646 villager) {
      colony=ColonyManager.x3psqezd5jem54(world,villager.method_24515()).orElse(null);
      return eligible(world,villager) && colony.materialRequests.entries().stream().anyMatch(r -> r.active() || r.cargo>0);
   }
   private boolean eligible(class_3218 world,class_1646 v) {
      return colony!=null && colony.roleOf(v.method_5667())==ColonyRole.COURIER && !v.method_6109()
         && !v.method_6113() && !v.method_18009() && !VillageDefense.xuj80syz8c60l(world,colony,v);
   }
   @Override protected boolean method_18927(class_3218 world,class_1646 v,long now) { return eligible(world,v); }
   @Override protected void method_18920(class_3218 world,class_1646 v,long now) { next=now; }
   @Override protected void method_18924(class_3218 world,class_1646 v,long now) {
      if(now>=next) { next=now+20; CourierSupply.step(world,colony,v,now); }
   }
   @Override protected void method_18926(class_3218 world,class_1646 v,long now) {
      v.method_5942().method_6340(); colony=null;
   }
}
