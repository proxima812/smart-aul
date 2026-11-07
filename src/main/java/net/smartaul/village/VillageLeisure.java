package net.smartaul.village;

import java.util.*;
import net.minecraft.*;
import net.smartaul.config.VillageConfig;

/** Original social spaces. Identity is the existing persistent construction job. */
public final class VillageLeisure {
   public static final class_2960 TEMPLATE = class_2960.method_60655("smart_colonies", "tavern");
   private record Visit(UUID colony, int place, class_2338 goal, long until, long heartbeat) {}
   private static final Map<class_1646, Visit> VISITS = new WeakHashMap<>();
   private static final Map<class_1646, Long> RETRY = new WeakHashMap<>();
   private VillageLeisure() {}
   static void clearRuntimeCaches() { VISITS.clear(); RETRY.clear(); }

   static boolean isTavern(ConstructionJob job) { return TEMPLATE.equals(job.x3kqe1qjsw9oty()); }
   static boolean needsTavern(Colony c, VillageConfig config) {
      return c.population() >= config.tavern_min_population && c.x1ja61emeln2hc().stream().noneMatch(VillageLeisure::isTavern);
   }
   public static boolean restTime(long time) {
      long day = Math.floorMod(time, 24000L);
      return day >= 9000 && day < 12000;
   }
   static Optional<ConstructionJob> completed(Colony c) {
      return c.x1ja61emeln2hc().stream().filter(VillageLeisure::isTavern)
         .filter(j -> j.x32oz43hmeynhc() == ConstructionStatus.COMPLETE).findFirst();
   }
   static String summary(Colony c) {
      var job = c.x1ja61emeln2hc().stream().filter(VillageLeisure::isTavern).findFirst();
      return "Таверна: " + job.map(j -> j.x32oz43hmeynhc() == ConstructionStatus.COMPLETE ? "готова"
         : "строится " + j.xmu75rcopbjnh() + "/" + j.xxjwqetbgzzle()).orElse("не запланирована");
   }

   /** Called only after work, sleep, food, recovery, claims and defence have priority. */
   public static boolean visit(class_3218 world, Colony c, class_1646 villager) {
      long now = world.method_75260();
      if (!restTime(world.method_8532()) || villager.method_6113() || villager.method_6109()
         || villager.method_18009() || c.roleOf(villager.method_5667()) == ColonyRole.GUARD
         || c.roleOf(villager.method_5667()) == ColonyRole.FOUNDER) return false;
      var current = VISITS.get(villager);
      if (current != null && current.colony.equals(c.id()) && now < current.until && now <= current.heartbeat) {
         VISITS.put(villager, new Visit(c.id(), current.place, current.goal, current.until, now + 40));
         return true;
      }
      VISITS.remove(villager);
      if (now < RETRY.getOrDefault(villager, Long.MIN_VALUE)) return false;
      // Stagger expensive checks and allow at most one short visit per cooldown.
      if (Math.floorMod(now + villager.method_5628(), 100) != 0) return false;
      RETRY.put(villager, now + 1200);
      var tavern = completed(c);
      if (tavern.isEmpty()) return false;
      VISITS.entrySet().removeIf(e -> !e.getKey().method_5805() || now > e.getValue().heartbeat
         || now >= e.getValue().until);
      var used = new HashSet<Integer>();
      VISITS.forEach((v, visit) -> {
         if (v.method_73183() == world && visit.colony.equals(c.id())) used.add(visit.place);
      });
      for (int slot = 0; slot < 4; slot++) {
         if (used.contains(slot)) continue;
         var goal = meetingPoint(tavern.get(), slot);
         if (!VillageSafety.loaded(world, goal) || !VillagerShelter.x9bnd6e2jczhd(world, goal)
            || ProtectedLand.x3gqyq7yie9jxr(world, goal)) continue;
         VISITS.put(villager, new Visit(c.id(), slot, goal, now + 600, now + 40));
         return true;
      }
      return false;
   }

   static class_2338 meetingPoint(ConstructionJob job, int slot) {
      return job.x3ij6gescvakv2().method_10069(slot % 2 == 0 ? 3 : 5, 1, slot < 2 ? 5 : 7);
   }
   static class_2338 goal(class_1646 v) {
      var visit = VISITS.get(v);
      return visit == null ? null : visit.goal;
   }
   static boolean active(class_1646 v, long now) {
      var visit = VISITS.get(v);
      return visit != null && now < visit.until && now <= visit.heartbeat;
   }
   static void release(class_1646 v) { VISITS.remove(v); }
   static Optional<class_1646> companion(class_1646 v) {
      return VISITS.keySet().stream().filter(other -> other != v && other.method_5805()
         && other.method_73183() == v.method_73183()
         && other.method_24515().method_10262(v.method_24515()) <= 16).findFirst();
   }
}
