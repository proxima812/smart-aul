package net.smartaul.village;

import java.util.*;
import net.minecraft.*;
import net.smartaul.config.*;

final class CourierRegression {
   static void check(boolean b,String message) { if(!b) throw new AssertionError(message); }
   static void run() {
      var q=new MaterialRequests(); var worker=UUID.randomUUID(); var courier=UUID.randomUUID();
      var pos=new class_2338(2,64,3); var item=class_7923.field_41178.method_10221(class_1802.field_8317);
      var first=q.submit(worker,item,32,pos,50,10);
      check(q.submit(worker,item,16,pos,80,20)==first,"dedup preserves cargo and deadline");
      var urgent=q.submit(UUID.randomUUID(),item,16,pos,80,20);
      check(q.claim(courier,30)==urgent,"higher priority first");
      check(q.claim(UUID.randomUUID(),31)==first,"lease prevents double claim");
      urgent.cargo=7;
      q.expire(230);
      check(urgent.cargo==7 && urgent.state==MaterialRequests.State.OPEN,"expired lease retains sole cargo ownership");
      check(q.claim(courier,231)==urgent,"recover cargo before new withdrawal");
      var restored=new MaterialRequests(); restored.read(q.write());
      var r=restored.entries().stream().filter(v -> v.id.equals(urgent.id)).findFirst().orElseThrow();
      check(r.cargo==7 && r.reservation==null && r.state==MaterialRequests.State.OPEN,"NBT clears stale entity leases but retains cargo");
      restored.expire(2420);
      check(r.state==MaterialRequests.State.CANCELLED && r.cargo==7,"deadline cancels without discarding cargo");
      restored.cancel(worker);
      check(restored.entries().stream().noneMatch(MaterialRequests.Request::active),"explicit cancellation");
      var c=new Colony(UUID.randomUUID(),pos,96,0); c.materialRequests.read(q.write());
      var reload=Colony.x3bvvkrzdde479(c.xufzblerzrsnk());
      check(reload.materialRequests.entries().size()==2,"colony NBT embeds queue");
      check(Colony.x3bvvkrzdde479(new Colony(UUID.randomUUID(),pos,96,0).xufzblerzrsnk()).materialRequests.entries().isEmpty(),"old empty queue");
      var bad=new class_2499(); bad.add(new class_2487());
      var valid=q.write().method_68582(0); valid.method_10569("cargo",Integer.MAX_VALUE); bad.add(valid);
      restored.read(bad); check(restored.entries().isEmpty(),"corrupt UUID and impossible cargo rejected");
      var records=q.write(); records.add(records.method_68582(0).method_10553());
      restored.read(records); check(restored.entries().size()==2,"duplicate saved record is not copied");
      var changed=new MaterialRequests();
      var old=changed.submit(worker,item,32,pos,50,0); old.cargo=8;
      changed.submit(worker,item,16,pos.method_10084(),80,1);
      check(old.state==MaterialRequests.State.CANCELLED && old.cargo==8
         && changed.entries().stream().filter(MaterialRequests.Request::active).count()==1,"changed destination cancels old work but retains cargo for return");
      var legacy=new Colony(UUID.randomUUID(),pos,96,0).xufzblerzrsnk(); legacy.method_10551("material_requests");
      check(Colony.x3bvvkrzdde479(legacy).materialRequests.entries().isEmpty(),"old NBT with absent field");
      var full=new MaterialRequests();
      for(int i=0;i<200;i++) full.submit(UUID.randomUUID(),item,1,pos,0,0);
      check(full.entries().size()==128,"bounded queue");
      full.expire(2400); check(full.submit(worker,item,1,pos,0,2401)!=null,"completed history can be pruned");
      check(ColonyRole.CITIZEN.ordinal()==9 && ColonyRole.COURIER.ordinal()==10 && ColonyRole.byName("courier")==ColonyRole.COURIER,"old role ordinals and new name");
      var cfg=new com.google.gson.Gson().fromJson("{}",VillageConfig.class);
      check(cfg.courier_min_population==10,"old JSON defaults"); cfg.courier_min_population=-1; DevelopmentPolicy.validate(cfg);
      check(cfg.courier_min_population==8,"safe minimum");
      c.x1c0m5jj5x6wpm(10,24,0);
      check(ColonyManager.x17qz5r6rg31u8(c,0,9).get(ColonyRole.COURIER)==0,"no warehouse no courier");
      c.x2ad3s8aentmo4().add(pos);
      var roles=ColonyManager.x17qz5r6rg31u8(c,0,9);
      check(roles.get(ColonyRole.COURIER)==1 && roles.values().stream().mapToInt(Integer::intValue).sum()==9,"exactly one courier in total worker budget");
      for(var role:List.of(ColonyRole.FARMER,ColonyRole.BUILDER,ColonyRole.LUMBERJACK,ColonyRole.MINER)) check(roles.get(role)>0,"essential role retained: "+role);
      System.out.println("Courier request regressions: PASS");
   }
}
