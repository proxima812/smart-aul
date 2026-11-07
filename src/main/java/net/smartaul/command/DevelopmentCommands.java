package net.smartaul.command;

import net.smartaul.config.SmartAulConfig;
import net.smartaul.village.Colony;
import net.smartaul.village.ColonyManager;
import net.smartaul.village.ColonyWorldData;
import net.smartaul.village.ConstructionStatus;
import net.smartaul.village.SettlementManager;
import net.smartaul.village.SettlementSupport;
import net.smartaul.village.VillagerNavigation;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.class_2168;
import net.minecraft.class_2170;
import net.minecraft.class_2262;
import net.minecraft.class_2338;
import net.minecraft.class_2561;

public final class DevelopmentCommands {
   private DevelopmentCommands() {}

   public static void register(CommandDispatcher<class_2168> dispatcher) {
      dispatcher.register(class_2170.method_9247("smart_aul")
         .then(DeveloperCommands.create("dev"))
         .then(class_2170.method_9247("progress").executes(ctx -> progress(ctx.getSource())))
         .then(class_2170.method_9247("settlements").executes(ctx -> settlements(ctx.getSource())))
         .then(class_2170.method_9247("settle")
            .requires(class_2170.method_71774(class_2170.field_31839))
            .executes(ctx -> settle(ctx.getSource(), null))
            .then(class_2170.method_9244("target", class_2262.method_9698())
               .executes(ctx -> settle(ctx.getSource(), class_2262.method_9696(ctx, "target")))))
         .then(class_2170.method_9247("recall")
            .requires(class_2170.method_71774(class_2170.field_31839))
            .executes(ctx -> recall(ctx.getSource()))));
   }

   public static void registerLegacyAlias(CommandDispatcher<class_2168> dispatcher) {
      dispatcher.register(class_2170.method_9247("smartcolonies").redirect(dispatcher.getRoot().getChild("smart_aul")));
   }

   private static Colony nearby(class_2168 source) {
      return ColonyManager.x3psqezd5jem54(source.method_9225(), class_2338.method_49638(source.method_9222())).orElse(null);
   }

   private static int reply(class_2168 source, String message, int result) {
      source.method_9226(() -> class_2561.method_43470(message), false);
      return result;
   }

   private static int settle(class_2168 source, class_2338 target) {
      var colony = nearby(source);
      if (colony == null) return reply(source, "Подойдите к поселению, из которого отправятся жители.", 0);
      var result = SettlementManager.requestSettlement(source.method_9225(), colony, target);
      return reply(source, result.x2fa4oag1sp0w9(), result.x3cg1vvxnlossv());
   }

   private static int recall(class_2168 source) {
      var colony = nearby(source);
      if (colony == null || colony.x2a380ray4md5t().isEmpty()) return reply(source, "У ближайшего поселения нет экспедиции.", 0);
      colony.x2qmn6nt922pp6();
      ColonyWorldData.get(source.method_9225()).method_80();
      return reply(source, "Экспедиция возвращается. Жители принесут оставшийся груз; маршрут должен быть загружен.", 1);
   }

   private static int progress(class_2168 source) {
      var colony = nearby(source);
      if (colony == null) return reply(source, "Рядом нет поселения.", 0);
      reply(source, SettlementSupport.progress(colony), 1);
      reply(source, "Строительство: " + ColonyManager.x2y69zyuty3rxs(source.method_9225(), colony), 1);
      String readiness = SettlementSupport.readiness(source.method_9225(), colony);
      reply(source, "Переселение: " + (readiness.isEmpty() ? "запасы готовы; нужны пять свободных взрослых и подходящий загруженный участок." : readiness), 1);
      if (colony.x2a380ray4md5t().isPresent()) {
         long elapsed = source.method_9225().method_75260() - colony.x1spmho2xy9muc();
         reply(source, "??????????: " + (colony.xfx95ux1xn73w() ? "???????" : elapsed < 0 ? "????" : "?????")
            + "; ??????? " + colony.x13ua0ftegttnr() + "/24; ?? ?????? ?? ??????? "
            + Math.max(0, SmartAulConfig.x2by5f9ehxvwk9().village.settlement_timeout_ticks - elapsed) / 20 + " ?.", 1);
         var people = new java.util.LinkedHashSet<>(colony.x2zftltl6vhwwx());
         people.add(colony.x2a380ray4md5t().orElseThrow());
         for (var id : people) {
            var entity = source.method_9225().method_66347(id);
            reply(source, id + ": " + (entity instanceof net.minecraft.class_1646 villager
               ? villager.method_24515().method_23854() + "; " + VillagerNavigation.xs0vwkswo7jny(villager)
               : "?? ???????? ??? ???????????; ?????? ?? ????????????"), 1);
         }
      }
      if (SmartAulConfig.x2by5f9ehxvwk9().village.automatic_settlement_requires_city && !SettlementSupport.isCity(colony))
         reply(source, "?????????????? ??????????? ??????? ??????? ??????.", 1);
      for (var job : colony.x1ja61emeln2hc()) {
         if (job.x32oz43hmeynhc() != ConstructionStatus.COMPLETE)
            reply(source, "Дом " + job.x3ij6gescvakv2().method_23854() + ": " + job.x32oz43hmeynhc()
               + ", блоки " + job.xmu75rcopbjnh() + "/" + job.xxjwqetbgzzle() + ", простой снабжения " + job.xbnccd29nwem4() / 20 + " с.", 1);
      }
      return 1;
   }

   private static int settlements(class_2168 source) {
      var colonies = ColonyWorldData.get(source.method_9225()).colonies();
      for (var colony : colonies)
         reply(source, colony.id() + " @ " + colony.core().method_23854() + ": " + SettlementSupport.progress(colony)
            + " Родитель: " + colony.parentColonyId().map(Object::toString).orElse("исходная деревня")
            + colony.x1x0agg3sdxjk8().map(p -> "; цель экспедиции " + p.method_23854()).orElse(""), 1);
      return colonies.size();
   }
}
