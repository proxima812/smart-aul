package net.smartaul.village;

import java.util.function.Predicate;
import net.minecraft.*;

/** Server-thread transfers of ordinary construction items; no remote withdrawal. */
public final class CourierSupply {
   private CourierSupply() {}
   static boolean plain(class_1799 stack) {
      return !stack.method_7960() && class_1799.method_31577(stack, new class_1799(stack.method_7909()));
   }
   static boolean request(class_3218 world, Colony colony, class_1646 worker, class_2338 target,
                          class_1792 preferred, Predicate<class_1799> allowed, int count, int priority) {
      boolean available = world.method_18023(class_1299.field_6077, ColonyManager.x150ccxomrlozr(world,colony),
         v -> v.method_5805() && colony.roleOf(v.method_5667())==ColonyRole.COURIER).size()>0;
      if (!available) return false;
      for (var pending : colony.materialRequests.entries()) {
         if(pending.active() && pending.worker.equals(worker.method_5667()) && world.method_75260()<pending.deadline
            && allowed.test(new class_1799(class_7923.field_41178.method_63535(pending.item)))) {
            worker.method_5942().method_6340();
            return true;
         }
      }
      class_1792 item=preferred;
      // Use the same material predicate as the worker, including its strategic stock reserve.
      outer: for (var pos : colony.x2ad3s8aentmo4()) {
         if (!VillageSafety.loaded(world,pos) || ProtectedLand.xic1z39wiy3pn(world,pos)) continue;
         if (world.method_8321(pos) instanceof class_1263 inv) for(int slot=0;slot<inv.method_5439();slot++) {
            var stack=inv.method_5438(slot);
            if (plain(stack) && allowed.test(stack)) { item=stack.method_7909(); break outer; }
         }
      }
      // When stock is absent, keep a concrete request for the preferred material.
      if (item==class_1802.field_8162) return false;
      var r=colony.materialRequests.submit(worker.method_5667(),class_7923.field_41178.method_10221(item),count,target,priority,world.method_75260());
      if (r==null) return false;
      ColonyWorldData.get(world).method_80();
      worker.method_5942().method_6340();
      WorkDiagnostics.x1qd0mutwsr3sc(worker,"ожидание курьера");
      return true;
   }
   static void step(class_3218 world, Colony colony, class_1646 courier, long now) {
      var queue=colony.materialRequests;
      var r=queue.claim(courier.method_5667(),now);
      if(r==null) return;
      ColonyWorldData.get(world).method_80();
      var item=class_7923.field_41178.method_63535(r.item);
      var entity=world.method_73284(r.worker);
      if (entity instanceof class_1646 worker && (!worker.method_5805() || colony.roleOf(r.worker)!=ColonyRole.BUILDER)) {
         r.state=MaterialRequests.State.CANCELLED;
      }
      if (!r.active()) {
         if(r.cargo==0) { r.reservation=null; return; }
         var storage=ColonyManager.x1falh21nhy4yk(world,colony,courier.method_24515()).orElse(null);
         if(storage==null || !near(world,courier,storage)) return;
         var stack=new class_1799(item,r.cargo);
         // The existing deposit method mutates the remainder even on partial failure.
         if(world.method_8321(storage) instanceof class_1263 inv) deposit(inv,stack);
         r.cargo=stack.method_7947();
         if(r.cargo==0) r.reservation=null;
      } else if(r.cargo==0) {
         var storage=ColonyManager.x1dhbpietunk9i(world,colony,courier,s -> s.method_31574(item) && plain(s)).orElse(null);
         if(storage==null || !near(world,courier,storage)) return;
         // One bounded stack per trip. Never store both a copy in a villager and a queue count.
         var probe=new class_1799(item);
         int reserve=probe.method_31573(class_3489.field_15539) || probe.method_31573(class_3489.field_25808) ? 8 : 0;
         int stock=ColonyManager.x1mlne8tl8k5ag(world,colony,s -> s.method_31574(item) && plain(s));
         var stack=ColonyManager.x5jd7014csotp(world,storage,s -> s.method_31574(item) && plain(s),Math.min(r.count-r.delivered,Math.max(0,stock-reserve))).orElse(class_1799.field_8037);
         r.cargo=stack.method_7947();
      } else if(entity instanceof class_1646 worker && worker.method_5805()) {
         if (worker.method_24515().method_10262(r.target)>256) { r.state=MaterialRequests.State.CANCELLED; }
         else if(near(world,courier,worker.method_24515())) {
            int before=r.cargo;
            var remainder=worker.method_35199().method_5491(new class_1799(item,r.cargo));
            r.cargo=remainder.method_7947();
            r.delivered+=before-r.cargo;
            if(r.delivered==r.count) { r.state=MaterialRequests.State.DELIVERED; r.reservation=null; }
         }
      }
      ColonyWorldData.get(world).method_80();
   }
   static void deposit(class_1263 inv,class_1799 stack) {
      for(int i=0;i<inv.method_5439() && !stack.method_7960();i++) {
         var slot=inv.method_5438(i); int cap=inv.method_58350(stack);
         if(slot.method_7960()) {
            int n=Math.min(cap,stack.method_7947()); inv.method_5447(i,stack.method_46651(n)); stack.method_7934(n);
         } else if(class_1799.method_31577(slot,stack)) {
            int n=Math.min(Math.max(0,cap-slot.method_7947()),stack.method_7947()); slot.method_7933(n); stack.method_7934(n);
         }
      }
      inv.method_5431();
   }
   private static boolean near(class_3218 world,class_1646 courier,class_2338 pos) {
      if(!VillageSafety.loaded(world,pos) || courier.method_24515().method_10262(pos)>96*96) return false;
      if(courier.method_24515().method_10262(pos)<=9) { courier.method_5942().method_6340(); return true; }
      VillagerShelter.x2wzylgizxltef(world,courier,pos,0.65);
      return false;
   }
}
