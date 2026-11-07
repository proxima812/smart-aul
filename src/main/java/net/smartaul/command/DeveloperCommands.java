package net.smartaul.command;

import net.smartaul.config.SmartAulConfig;
import net.smartaul.village.Colony;
import net.smartaul.village.ActionTrace;
import net.smartaul.village.ColonyManager;
import net.smartaul.village.ColonyRole;
import net.smartaul.village.ColonyWorldData;
import net.smartaul.village.CommandResult;
import net.smartaul.village.CreativeCityMode;
import net.smartaul.village.DeveloperMode;
import net.smartaul.village.SettlementManager;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.util.Optional;
import java.util.function.BiFunction;
import net.minecraft.class_2168;
import net.minecraft.class_2170;
import net.minecraft.class_2262;
import net.minecraft.class_2338;
import net.minecraft.class_2561;

public final class DeveloperCommands {
   private DeveloperCommands() {
   }

   public static LiteralArgumentBuilder<class_2168> x2bhz7qcxu7mfc() {
      return create("film");
   }

   public static LiteralArgumentBuilder<class_2168> create(String root) {
      LiteralArgumentBuilder var0 = (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)class_2170.method_9247(
                                                      root
                                                   )
                                                   .requires(class_2170.method_71774(class_2170.field_31839)))
                                                .executes(var0x -> x2ed6fw6gftd0v((class_2168)var0x.getSource())))
                                             .then(class_2170.method_9247("help").executes(var0x -> x2ed6fw6gftd0v((class_2168)var0x.getSource()))))
                                          .then(
                                             class_2170.method_9247("on")
                                                .executes(
                                                   var0x -> {
                                                      DeveloperMode.xevco0r0wp03v(((class_2168)var0x.getSource()).method_9225());
                                                      return x3kspok0jyxjks(
                                                         (class_2168)var0x.getSource(),
                                                         "Режим разработчика включён в этом измерении до выхода из мира. Используйте КОПИЮ сохранения: команды меняют мир, off не откатывает изменения. Автоматические новые экспедиции приостановлены; уже запущенные продолжают путь.",
                                                         1
                                                      );
                                                   }
                                                )
                                          ))
                                       .then(
                                          class_2170.method_9247("off")
                                             .executes(
                                                var0x -> {
                                                      CreativeCityMode.stop(((class_2168)var0x.getSource()).method_9225());
                                                   DeveloperMode.x3evocl9yxshic(((class_2168)var0x.getSource()).method_9225());
                                                   return x3kspok0jyxjks(
                                                      (class_2168)var0x.getSource(),
                                                      "Режим разработчика выключен, ускорение max остановлено. Изменения мира сохранены. Обычные экспедиции снова разрешены.",
                                                      1
                                                   );
                                                }
                                             )
                                       ))
                                    .then(
                                       class_2170.method_9247("status")
                                          .executes(
                                             var0x -> x30301j211m4ot(
                                                (class_2168)var0x.getSource(),
                                                (var0xx, var1x) -> new CommandResult(1, DeveloperMode.x24jh62s2oq06y(var0xx.method_9225(), var1x)),
                                                false
                                             )
                                          )
                                    ))
                                 .then(
                                    class_2170.method_9247("supply")
                                       .executes(
                                          var0x -> x30301j211m4ot(
                                             (class_2168)var0x.getSource(), (var0xx, var1x) -> DeveloperMode.x2b94htgx8j8wb(var0xx.method_9225(), var1x), true
                                          )
                                       )
                                 ))
                              .then(
                                 class_2170.method_9247("population")
                                    .then(
                                       class_2170.method_9244("count", IntegerArgumentType.integer(2, 64))
                                          .executes(
                                             var0x -> x30301j211m4ot(
                                                (class_2168)var0x.getSource(),
                                                (var1x, var2) -> DeveloperMode.xcqb3adsjgu8q(
                                                   var1x.method_9225(), var2, IntegerArgumentType.getInteger(var0x, "count")
                                                ),
                                                true
                                             )
                                          )
                                    )
                              ))
                           .then(
                              class_2170.method_9247("max")
                                 .executes(
                                    var0x -> x30301j211m4ot(
                                       (class_2168)var0x.getSource(), (var0xx, var1x) -> DeveloperMode.x3hszkva73cyco(var0xx.method_9225(), var1x), true
                                    )
                                 )
                           ))
                        .then(
                           class_2170.method_9247("stop")
                              .executes(
                                 var0x -> x30301j211m4ot(
                                    (class_2168)var0x.getSource(), (var0xx, var1x) -> DeveloperMode.x2dwx84ddtzhb0(var0xx.method_9225(), var1x), true
                                 )
                              )
                        ))
                     .then(
                        class_2170.method_9247("rebalance")
                           .executes(
                              var0x -> x30301j211m4ot(
                                 (class_2168)var0x.getSource(), (var0xx, var1x) -> DeveloperMode.x32g8vo9hf8rm3(var0xx.method_9225(), var1x), true
                              )
                           )
                     ))
                  .then(
                     ((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)class_2170.method_9247("develop")
                                 .executes(
                                    var0x -> x30301j211m4ot(
                                       (class_2168)var0x.getSource(), (var0xx, var1x) -> DeveloperMode.x1uwgtgswk0hva(var0xx.method_9225(), var1x, 3), true
                                    )
                                 ))
                              .then(
                                 class_2170.method_9247("prepare")
                                    .executes(
                                       var0x -> x30301j211m4ot(
                                          (class_2168)var0x.getSource(),
                                          (var0xx, var1x) -> DeveloperMode.x241eq3b06s3l0(
                                             var0xx.method_9225(), var1x, class_2338.method_49638(var0xx.method_9222())
                                          ),
                                          true
                                       )
                                    )
                              ))
                           .then(
                              class_2170.method_9247("status")
                                 .executes(
                                    var0x -> x30301j211m4ot(
                                       (class_2168)var0x.getSource(),
                                       (var0xx, var1x) -> new CommandResult(1, DeveloperMode.x24jh62s2oq06y(var0xx.method_9225(), var1x)),
                                       false
                                    )
                                 )
                           ))
                        .then(
                           class_2170.method_9244("days", IntegerArgumentType.integer(1, 30))
                              .executes(
                                 var0x -> x30301j211m4ot(
                                    (class_2168)var0x.getSource(),
                                    (var1x, var2) -> DeveloperMode.x1uwgtgswk0hva(var1x.method_9225(), var2, IntegerArgumentType.getInteger(var0x, "days")),
                                    true
                                 )
                              )
                        )
                  ))
               .then(
                  ((LiteralArgumentBuilder)class_2170.method_9247("expedition")
                        .executes(
                           var0x -> x30301j211m4ot(
                              (class_2168)var0x.getSource(), (var0xx, var1x) -> SettlementManager.x3seqvpn908svy(var0xx.method_9225(), var1x, null), true
                           )
                        ))
                     .then(class_2170.method_9244("destination", class_2262.method_9698()).executes(var0x -> {
                        class_2338 var1x = class_2262.method_9696(var0x, "destination");
                        return x30301j211m4ot(
                           (class_2168)var0x.getSource(), (var1xx, var2) -> SettlementManager.x3seqvpn908svy(var1xx.method_9225(), var2, var1x), true
                        );
                     }))
               ))
            .then(
               class_2170.method_9247("build")
                  .executes(
                     var0x -> x30301j211m4ot(
                        (class_2168)var0x.getSource(),
                        (var0xx, var1x) -> {
                           var1x.xs28n9ortj0j6(0L);
                           ColonyWorldData.get(var0xx.method_9225()).method_80();
                           return new CommandResult(
                              1,
                              "Поиск участка разрешён без ожидания повтора. Строитель работает с обычной скоростью; активный дом не отменён. Состояние: "
                                 + ColonyManager.x2y69zyuty3rxs(var0xx.method_9225(), var1x)
                           );
                        },
                        true
                     )
                  )
            ))
         .then(class_2170.method_9247("walls").executes(var0x -> x30301j211m4ot((class_2168)var0x.getSource(), (var0xx, var1x) -> {
            if (!SmartAulConfig.x2by5f9ehxvwk9().roles.enable_village_walls) {
               return new CommandResult(0, "Стены выключены в конфиге.");
            } else {
               var1x.x2f7z1gnnht5ab().x37dzr2zekycme = 0L;
               ColonyWorldData.get(var0xx.method_9225()).method_80();
               return new CommandResult(1, "Запрошена обычная проверка периметра. Стены ставят строители, нужны материалы, дневное время и загруженные чанки.");
            }
         }, true)));
      LiteralArgumentBuilder var1 = class_2170.method_9247("shortage");

      for (String var5 : new String[]{"food", "wood"}) {
         var1.then(
            class_2170.method_9247(var5)
               .executes(
                  var1x -> x30301j211m4ot(
                     (class_2168)var1x.getSource(), (var1xx, var2) -> DeveloperMode.xl7doh5h33e42(var1xx.method_9225(), var2, var5.equals("food")), true
                  )
               )
         );
      }

      var0.then(var1);
      LiteralArgumentBuilder var8 = class_2170.method_9247("role");
      LiteralArgumentBuilder var9 = class_2170.method_9247("change");

      for (ColonyRole var7 : ColonyRole.values()) {
         if (var7 != ColonyRole.ELDER && var7 != ColonyRole.FOUNDER) {
            var9.then(
               class_2170.method_9247(var7.serializedName())
                  .executes(
                     var1x -> x30301j211m4ot(
                        (class_2168)var1x.getSource(),
                        (var1xx, var2) -> DeveloperMode.x2sc3yk5od1kev(var1xx.method_9225(), var2, var7, class_2338.method_49638(var1xx.method_9222())),
                        true
                     )
                  )
            );
            var8.then(
               ((LiteralArgumentBuilder)class_2170.method_9247(var7.serializedName()).executes(var1x -> x1myq7of069kaw((class_2168)var1x.getSource(), var7, 1)))
                  .then(
                     class_2170.method_9244("count", IntegerArgumentType.integer(1, 16))
                        .executes(var1x -> x1myq7of069kaw((class_2168)var1x.getSource(), var7, IntegerArgumentType.getInteger(var1x, "count")))
                  )
            );
         }
      }

      var0.then(class_2170.method_9247("trace")
         .executes(ctx -> x3kspok0jyxjks(ctx.getSource(), ActionTrace.status(), 1))
         .then(class_2170.method_9247("on").executes(ctx -> {
            SmartAulConfig.x2by5f9ehxvwk9().debug.log_ai_actions = true;
            return x3kspok0jyxjks(ctx.getSource(), ActionTrace.status(), 1);
         }))
         .then(class_2170.method_9247("off").executes(ctx -> {
            SmartAulConfig.x2by5f9ehxvwk9().debug.log_ai_actions = false;
            ActionTrace.close();
            return x3kspok0jyxjks(ctx.getSource(), ActionTrace.status(), 1);
         })));
      var0.then(class_2170.method_9247("creative")
         .executes(ctx -> x30301j211m4ot(ctx.getSource(), (source, colony) -> CreativeCityMode.start(source.method_9225(), colony), false))
         .then(class_2170.method_9247("status").executes(ctx -> x3kspok0jyxjks(ctx.getSource(), CreativeCityMode.status(ctx.getSource().method_9225()), 1)))
         .then(class_2170.method_9247("stop").executes(ctx -> {
            var result = CreativeCityMode.stop(ctx.getSource().method_9225());
            return x3kspok0jyxjks(ctx.getSource(), result.x2fa4oag1sp0w9(), result.x3cg1vvxnlossv());
         })));
      return (LiteralArgumentBuilder<class_2168>)((LiteralArgumentBuilder)var0.then(var8)).then(var9);
   }

   private static int x1myq7of069kaw(class_2168 var0, ColonyRole var1, int var2) {
      return x30301j211m4ot(
         var0, (var2x, var3) -> DeveloperMode.x2zuchidlrmdh(var2x.method_9225(), var3, var1, var2, class_2338.method_49638(var2x.method_9222())), true
      );
   }

   private static int x30301j211m4ot(class_2168 var0, BiFunction<class_2168, Colony, CommandResult> var1, boolean var2) {
      if (var2 && !DeveloperMode.x2d8qtehqomuj6(var0.method_9225())) {
         return x3kspok0jyxjks(var0, "Сначала /smart_aul dev on. Не используйте команды на единственной копии мира.", 0);
      } else {
         var var3 = ColonyManager.x3psqezd5jem54(var0.method_9225(), class_2338.method_49638(var0.method_9222()));
         if (var3.isEmpty()) {
            return x3kspok0jyxjks(var0, "Рядом нет зарегистрированной деревни. Подойдите к колоколу сгенерированной деревни.", 0);
         } else {
            CommandResult var4 = (CommandResult)var1.apply(var0, (Colony)var3.get());
            return x3kspok0jyxjks(var0, var4.x2fa4oag1sp0w9(), var4.x3cg1vvxnlossv());
         }
      }
   }

   private static int x3kspok0jyxjks(class_2168 var0, String var1, int var2) {
      var0.method_9226(() -> class_2561.method_43470(var1), false);
      return var2;
   }

   private static int x2ed6fw6gftd0v(class_2168 var0) {
      x3kspok0jyxjks(var0, "Креативная деревня: /smart_aul dev creative — бесконечные ресурсы и непрерывная застройка карты. creative status | stop", 1);
      x3kspok0jyxjks(var0, "Тестирование на копии мира: /smart_aul dev on | off | status | stop. Все сценарии: /smart_aul dev help", 1);
      x3kspok0jyxjks(var0, "Подготовка: dev supply; dev population 24; dev max (до 12 новых домов, до 48 жителей, ускоренная подготовка).", 1);
      x3kspok0jyxjks(
         var0, "Сцены: dev expedition [X Y Z]; dev develop [1..30 дней]; dev build; dev walls; dev shortage food|wood; dev role <профессия> [1..16].", 1
      );
      x3kspok0jyxjks(
         var0,
         "Ближайший житель: dev change <профессия>. Реальный спрос без обхода кулдауна: dev rebalance. Офлайн-сцена: dev develop prepare → dev develop 3 → dev develop status.",
         1
      );
      x3kspok0jyxjks(
         var0,
         "on не отключает обычный ИИ. max, supply, role, population — постановочные команды; shortage и develop необратимы без копии мира. Экспедиция — основатель-житель, не ванильный странствующий торговец.",
         1
      );
      return 1;
   }
}
