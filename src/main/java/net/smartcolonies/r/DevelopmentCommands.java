package net.smartcolonies.r;

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

   private static x15kgoaq6i3m6i nearby(class_2168 source) {
      return x2n55y2tom37h2.x3psqezd5jem54(source.method_9225(), class_2338.method_49638(source.method_9222())).orElse(null);
   }

   private static int reply(class_2168 source, String message, int result) {
      source.method_9226(() -> class_2561.method_43470(message), false);
      return result;
   }

   private static int settle(class_2168 source, class_2338 target) {
      var colony = nearby(source);
      if (colony == null) return reply(source, "Подойдите к поселению, из которого отправятся жители.", 0);
      var result = x313o9woxa22fw.requestSettlement(source.method_9225(), colony, target);
      return reply(source, result.x2fa4oag1sp0w9(), result.x3cg1vvxnlossv());
   }

   private static int recall(class_2168 source) {
      var colony = nearby(source);
      if (colony == null || colony.x2a380ray4md5t().isEmpty()) return reply(source, "У ближайшего поселения нет экспедиции.", 0);
      colony.x2qmn6nt922pp6();
      x3d5vk1zj3fvkj.get(source.method_9225()).method_80();
      return reply(source, "Экспедиция возвращается. Жители принесут оставшийся груз; маршрут должен быть загружен.", 1);
   }

   private static int progress(class_2168 source) {
      var colony = nearby(source);
      if (colony == null) return reply(source, "Рядом нет поселения.", 0);
      reply(source, SettlementSupport.progress(colony), 1);
      reply(source, "Строительство: " + x2n55y2tom37h2.x2y69zyuty3rxs(source.method_9225(), colony), 1);
      String readiness = SettlementSupport.readiness(source.method_9225(), colony);
      reply(source, "Переселение: " + (readiness.isEmpty() ? "запасы готовы; нужны пять свободных взрослых и подходящий загруженный участок." : readiness), 1);
      for (var job : colony.x1ja61emeln2hc()) {
         if (job.x32oz43hmeynhc() != x20r5v7z43p3d5.COMPLETE)
            reply(source, "Дом " + job.x3ij6gescvakv2().method_23854() + ": " + job.x32oz43hmeynhc()
               + ", блоки " + job.xmu75rcopbjnh() + "/" + job.xxjwqetbgzzle() + ", простой снабжения " + job.xbnccd29nwem4() / 20 + " с.", 1);
      }
      return 1;
   }

   private static int settlements(class_2168 source) {
      var colonies = x3d5vk1zj3fvkj.get(source.method_9225()).colonies();
      for (var colony : colonies)
         reply(source, colony.id() + " @ " + colony.core().method_23854() + ": " + SettlementSupport.progress(colony)
            + " Родитель: " + colony.parentColonyId().map(Object::toString).orElse("исходная деревня")
            + colony.x1x0agg3sdxjk8().map(p -> "; цель экспедиции " + p.method_23854()).orElse(""), 1);
      return colonies.size();
   }
}
