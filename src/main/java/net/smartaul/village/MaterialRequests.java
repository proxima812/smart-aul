package net.smartaul.village;

import java.util.*;
import net.minecraft.*;

/** Bounded persistent queue. Cargo belongs exclusively to the colony, never to an entity. */
public final class MaterialRequests {
   public enum State { OPEN, TAKEN, DELIVERED, CANCELLED }
   static final int LIMIT = 128;
   public static final class Request {
      final UUID id, worker;
      final class_2960 item;
      final class_2338 target;
      final int count, priority;
      final long deadline;
      State state = State.OPEN;
      TimedReservation reservation;
      int cargo;
      int delivered;
      Request(UUID id, UUID worker, class_2960 item, int count, class_2338 target, int priority, long deadline) {
         this.id=id; this.worker=worker; this.item=item; this.count=count;
         this.target=target.method_10062(); this.priority=priority; this.deadline=deadline;
      }
      boolean active() { return state == State.OPEN || state == State.TAKEN; }
   }
   private final List<Request> entries = new ArrayList<>();
   public List<Request> entries() { return Collections.unmodifiableList(entries); }
   Request submit(UUID worker, class_2960 item, int count, class_2338 target, int priority, long now) {
      expire(now);
      for (var r : entries) if (r.active() && r.worker.equals(worker) && r.item.equals(item) && r.target.equals(target)) return r;
      cancel(worker);
      if (entries.size() >= LIMIT) entries.removeIf(r -> !r.active() && r.cargo == 0);
      if (count <= 0 || entries.size() >= LIMIT) return null;
      var r = new Request(UUID.randomUUID(), worker, item, Math.min(64,count), target, Math.clamp(priority,0,100), now+2400);
      entries.add(r); return r;
   }
   void cancel(UUID worker) {
      for (var r : entries) if(r.active() && r.worker.equals(worker)) { r.state=State.CANCELLED; r.reservation=null; }
   }
   void expire(long now) {
      for (var r : entries) {
         if (r.active() && now >= r.deadline) { r.state=State.CANCELLED; r.reservation=null; }
         if (r.reservation != null && now >= r.reservation.xv5ezs7er1s4p()) {
            r.reservation=null;
            if (r.state == State.TAKEN) r.state=State.OPEN;
         }
      }
   }
   Request claim(UUID courier, long now) {
      expire(now);
      var owned = entries.stream().filter(r -> r.reservation != null && r.reservation.x1v3u0njrh3j7a().equals(courier)).findFirst();
      if (owned.isPresent()) return owned.get();
      var r = entries.stream().filter(v -> (v.active() || v.cargo>0) && v.reservation == null)
         .max(Comparator.<Request>comparingInt(v -> v.cargo>0 ? 101 : v.priority).thenComparingLong(v -> -v.deadline)).orElse(null);
      if (r != null) {
         r.reservation=new TimedReservation(courier, now+200);
         if (r.active()) r.state=State.TAKEN;
      }
      return r;
   }
   public List<String> describe() {
      var lines = new ArrayList<String>();
      lines.add("Заявки материалов: " + entries.stream().filter(Request::active).count());
      entries.stream().filter(r -> r.active() || r.cargo>0).limit(16).forEach(r -> lines.add(
         r.item+" ×"+r.count+" → "+r.target.method_23854()+"; "+switch(r.state) {
            case OPEN -> "открыта"; case TAKEN -> "взята"; case DELIVERED -> "доставлена"; case CANCELLED -> "отменена";
         }+"; приоритет "+r.priority+"; срок "+r.deadline+"; груз "+r.cargo));
      return lines;
   }
   class_2499 write() {
      var list = new class_2499();
      for (var r : entries) {
         var n=new class_2487();
         n.method_10582("id",r.id.toString()); n.method_10582("worker",r.worker.toString());
         n.method_10582("item",r.item.toString()); n.method_10544("target",r.target.method_10063());
         n.method_10569("count",r.count); n.method_10569("priority",r.priority);
         n.method_10544("deadline",r.deadline); n.method_10582("state",r.state.name()); n.method_10569("cargo",r.cargo);
         n.method_10569("delivered",r.delivered);
         // Leases are runtime only: the old courier must compete again after reload.
         list.add(n);
      }
      return list;
   }
   void read(class_2499 list) {
      entries.clear(); var ids=new HashSet<UUID>(); var keys=new HashSet<String>();
      for(int i=0;i<Math.min(LIMIT,list.size());i++) try {
         var n=list.method_68582(i);
         var id=UUID.fromString(n.method_68564("id","")); var worker=UUID.fromString(n.method_68564("worker",""));
         var item=class_2960.method_60654(n.method_68564("item",""));
         int count=n.method_68083("count",0), cargo=n.method_68083("cargo",0);
         if (item==null || count<1 || count>64 || cargo<0 || cargo>count || !class_7923.field_41178.method_10250(item)
            || class_7923.field_41178.method_63535(item)==class_1802.field_8162) continue;
         var r=new Request(id,worker,item,count,class_2338.method_10092(n.method_68080("target",0)),
            Math.clamp(n.method_68083("priority",0),0,100),n.method_68080("deadline",0));
         r.state=State.valueOf(n.method_68564("state","OPEN")); r.cargo=cargo;
         r.delivered=Math.clamp(n.method_68083("delivered",0),0,count-cargo);
         if(r.state==State.TAKEN) r.state=State.OPEN;
         String key=worker+":"+item+":"+r.target.method_10063();
         if(ids.add(id) && (!r.active() || keys.add(key))) entries.add(r);
      } catch(IllegalArgumentException ignored) { /* Skip corrupt records; never manufacture cargo. */ }
   }
}
