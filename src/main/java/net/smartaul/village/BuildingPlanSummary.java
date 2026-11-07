package net.smartaul.village;

import java.util.*;
import net.minecraft.*;

/** Shared by the town hall and /smart_aul plan. Stock estimates do not reserve materials. */
final class BuildingPlanSummary {
   static List<String> describe(class_3218 world, Colony colony, ConstructionJob job) {
      var plan = HouseBuilderTask.loadPlan(world, job).orElse(List.of());
      var stock = new ArrayList<class_1799>();
      colony.x1i0d1gs2hqpsr().forEach((id, count) -> {
         if (count > 0) stock.add(new class_1799(class_7923.field_41178.method_63535(id), count));
      });
      for (var pos : colony.x2ad3s8aentmo4()) if (VillageSafety.loaded(world, pos) && world.method_8321(pos) instanceof class_1263 inventory)
         for (int i = 0; i < inventory.method_5439(); i++) stock.add(inventory.method_5438(i).method_7972());
      int total = 0, done = 0, unknown = 0;
      var missing = new TreeMap<String, Integer>();
      for (var b : plan) {
         if (b.x3ln30gt147gjn() || b.x2hmkda3gwcacx().method_26215()) continue;
         total++;
         if (!VillageSafety.loaded(world, b.x31k0w8t7ul189())) { unknown++; continue; }
         if (HouseBuilderTask.x1zol2fshkzbul(world.method_8320(b.x31k0w8t7ul189()), b.x2hmkda3gwcacx())) { done++; continue; }
         if (CreativeCityMode.contains(world, colony)) continue;
         var item = HouseBuilderTask.materialItem(b.x2hmkda3gwcacx());
         if (item == class_1802.field_8162) continue;
         var predicate = HouseBuilderTask.materialPredicate(b.x2hmkda3gwcacx(), item);
         var available = stock.stream().filter(s -> !s.method_7960() && predicate.test(s)).findFirst();
         if (available.isPresent()) available.get().method_7934(1);
         else missing.merge(new class_1799(item).method_7964().getString(), 1, Integer::sum);
      }
      String purpose = TownHall.isHall(job) ? "ратуша" : colony.x2h8eqlzb5mm0w(job.x3ij6gescvakv2()).displayName();
      String progress = total == 0 ? "план недоступен" : (done * 100 / total) + "%" + (unknown > 0 ? " (часть чанков не загружена)" : "");
      return List.of(job.x3kqe1qjsw9oty().method_12832() + " [" + purpose + "] @ " + job.x3ij6gescvakv2().method_23854()
         + ": " + TownHall.statusName(job.x32oz43hmeynhc()) + " " + progress,
         "Нехватка в загруженных общих запасах (без резервирования): " + (missing.isEmpty() ? "нет" : missing));
   }
}
