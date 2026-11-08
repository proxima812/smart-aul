package net.smartaul.village;

import java.util.*;
import net.minecraft.*;
import net.smartaul.config.SmartAulConfig;

/** The construction job is the persistent identity of a settlement's town hall. */
public final class TownHall {
   public static final class_2960 TEMPLATE = class_2960.method_60655("smart_colonies", "town_hall");
   private TownHall() {}

   static boolean exists(Colony colony) {
      return colony.x1ja61emeln2hc().stream().anyMatch(TownHall::isHall);
   }
   static boolean isHall(ConstructionJob job) { return TEMPLATE.equals(job.x3kqe1qjsw9oty()); }
   static class_2338 board(ConstructionJob job, int index) {
      return job.x3ij6gescvakv2().method_10069(2 + index * 2, 2, 1);
   }

   static void tick(class_3218 world, Colony colony) {
      if (Math.floorMod(world.method_75260() + colony.core().method_10063(), 100) != 0) return;
      var hall = colony.x1ja61emeln2hc().stream().filter(TownHall::isHall).findFirst().orElse(null);
      if (hall == null) {
         var config = SmartAulConfig.x2by5f9ehxvwk9();
         if (!config.roles.enable_auto_expansion || !WorldEditPolicy.x35ev8l9vc9uk7(world)
            || colony.population() < 2 || colony.x2ad3s8aentmo4().isEmpty()
            || colony.x3e4ycl8cmmmgc() || !colony.x1n1a2vp01kxzf().isEmpty()) return;
         if (!colony.x2zi111bgbtgzq()) colony.x31uoom8ikakob();
         var origin = BuildingSiteSearch.xzvjreewiw7bn(world, colony, 9, 9, 6, CreativeCityMode.contains(world, colony));
         origin.ifPresent(pos -> {
            var job = new ConstructionJob(UUID.randomUUID(), TEMPLATE, pos, 9, 6, 9);
            job.xkv5b17xl7vd6(CreativeCityMode.contains(world, colony));
            colony.x31ve0dsfeaxo4(job);
            ColonyWorldData.get(world).method_80();
         });
         return;
      }
      if (hall.x32oz43hmeynhc() != ConstructionStatus.COMPLETE) return;
      var summaries = summaries(colony);
      for (int i = 0; i < 3; i++) {
         var pos = board(hall, i);
         if (!VillageSafety.loaded(world, pos) || !WorldEditPolicy.x35ev8l9vc9uk7(world)
            || ColonyWorldData.get(world).isPlayerBlock(pos)
            || ColonyWorldData.get(world).land().stream().anyMatch(land -> land.contains(pos))) continue;
         if (world.method_8321(pos) instanceof class_2625 sign) {
            var text = sign.method_49853();
            boolean changed = false;
            for (int line = 0; line < 4; line++) {
               String value = summaries.get(i).get(line);
               if (!text.method_49859(line, false).getString().equals(value)) {
                  text = text.method_49857(line, class_2561.method_43470(value)); changed = true;
               }
            }
            if (changed) sign.method_49840(text, true);
         }
      }
   }

   static List<List<String>> summaries(Colony colony) {
      long done = colony.x1ja61emeln2hc().stream().filter(j -> j.x32oz43hmeynhc() == ConstructionStatus.COMPLETE).count();
      long todo = colony.x1ja61emeln2hc().size() - done;
      return List.of(
         List.of("РАТУША", "Жителей: " + colony.population(), "Готово: " + done, "ПКМ: все планы"),
         List.of("ПЛАН РАБОТ", "Строек: " + todo, "Дорог: " + colony.x1n1a2vp01kxzf().size(), "Провалов: " + colony.safetyCovers.size()),
         List.of("ЗАПАСЫ", "Еда: " + colony.cachedFood(), "Дерево: " + colony.cachedLogs(), "Камень: " + colony.cachedStone()));
   }

   public static boolean interact(class_3218 world, class_1657 player, class_2338 pos) {
      for (var colony : ColonyWorldData.get(world).colonies()) {
         for (var hall : colony.x1ja61emeln2hc()) {
            if (!isHall(hall) || hall.x32oz43hmeynhc() != ConstructionStatus.COMPLETE) continue;
            for (int i = 0; i < 3; i++) if (board(hall, i).equals(pos) && world.method_8321(pos) instanceof class_2625) {
               for (var line : details(world, colony)) player.method_7353(class_2561.method_43470(line), false);
               return true;
            }
         }
      }
      return false;
   }

   public static List<String> details(class_3218 world, Colony colony) {
      var lines = new ArrayList<String>();
      lines.add("Ратуша — планы поселения");
      lines.addAll(colony.materialRequests.describe());
      lines.add(SettlementSupport.progress(colony));
      lines.add("Запасы: еда " + colony.cachedFood() + ", дерево " + colony.cachedLogs() + ", камень " + colony.cachedStone());
      lines.add("Безопасность: перекрыто " + colony.completedSafetyCovers + ", ожидает " + colony.safetyCovers.size());
      lines.add("Дороги: осталось блоков " + colony.x1n1a2vp01kxzf().size() + "; ремонт: " + (colony.x2xqc3q79vfkw3().size() - colony.safetyCovers.size()));
      // Summarise all completed work; bound individual pending entries for large settlements.
      var counts = new EnumMap<ConstructionStatus, Long>(ConstructionStatus.class);
      for (var job : colony.x1ja61emeln2hc()) counts.merge(job.x32oz43hmeynhc(), 1L, Long::sum);
      counts.forEach((status, count) -> lines.add(statusName(status) + ": " + count));
      colony.x1ja61emeln2hc().stream().filter(j -> j.x32oz43hmeynhc() != ConstructionStatus.COMPLETE).limit(16)
         .forEach(job -> lines.addAll(BuildingPlanSummary.describe(world, colony, job)));
      if (colony.x1ja61emeln2hc().stream().filter(j -> j.x32oz43hmeynhc() != ConstructionStatus.COMPLETE).count() > 16)
         lines.add("Показаны первые 16 строек.");
      lines.add("Далее: " + ColonyManager.x2y69zyuty3rxs(world, colony));
      String readiness = SettlementSupport.readiness(world, colony);
      lines.add("Переселение: " + (readiness.isEmpty() ? "запасы готовы, ожидается группа переселенцев" : readiness));
      return lines;
   }

   static String statusName(ConstructionStatus status) {
      return switch (status) {
         case COMPLETE -> "Выполнено";
         case QUEUED -> "Запланировано";
         case BUILDING -> "В работе";
         case BLOCKED -> "Приостановлено";
      };
   }
}
