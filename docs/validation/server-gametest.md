# Серверные GameTest — 2026-11-08

Окружение: Minecraft 1.21.11, Loader 0.18.4, Fabric API 0.141.4+1.21.11,
Loom 1.15.5, Java 21. Ветка `t3/808ed80a`.

## Реализация

`fabricApi.configureTests` создаёт отдельный source set `gametest` и задачу
`runGameTest`. Тестовый mod ID — `smart_aul_gametest`; entrypoint и Mixin
хранятся только в `src/gametest/resources`. Рабочий каталог — `build/gametest`,
XML-отчёт — `build/reports/gametest/results.xml`. Фильтр исключает встроенный
`minecraft:always_pass` и тесты других модов.

Четыре обязательных теста используют реальный `ServerWorld`:

- Провал с тремя воздушными блоками под ногами рядом с неподвижным жителем
  создаёт ровно одно перекрытие и запись плана ремонта; повторное наблюдение
  не дублирует работу и не ставит блок бесплатно.
- Одноблочная яма не создаёт перекрытие и запись ремонта.
- `mobGriefing=false` блокирует установку и разрушение с проверкой состояния
  мира; успешные контрольные операции подтверждают работоспособность пути.
- `LandClaim` в `ColonyWorldData` защищает существующий блок и воздух над ним;
  вне заявки установка разрешена.

Настройки и gamerule восстанавливаются в `finally`; колонии не регистрируются
в автоматическом менеджере, радиус 4 изолирует их от соседних тестовых жителей.
Заявка остаётся только в одноразовом тестовом мире.

## Проверки

Команда: `./gradlew.bat build runGameTest --console=plain`.

```text
Navigation/safety/town hall regressions: PASS
Development regression: PASS (96994 checks)
Will run tests matching smart_aul_gametest:* (4 tests)
All 4 required tests passed :)
BUILD SUCCESSFUL
```

Также временно добавлена заведомо ложная проверка в первый тест. Запуск
`runGameTest` завершился `BUILD FAILED`, JVM exit value 1, Gradle exit code 1.
Затем ложная проверка удалена и выполнен успешный итоговый запуск.

Проверен ZIP-каталог `build/libs/smart-aul-1.1.0-dev.jar`: нет `gametest`,
`VillageGameTests`, тестового Mixin и entrypoint `fabric-gametest`.
`git diff --check` проходит. Исходники `src/main` не изменены.

## Совместимость и границы

Первый запуск обнаружил `AnnotationFormatError` у `GameTest.rotation`: локальные
identity mappings сохраняют строку enum `NONE`, но Loom переносит поле аннотации
в `field_11467`. Тестовый `BlockRotationNamesMixin` согласует четыре строковых
имени enum `class_2470` с intermediary-полями только в GameTest JVM. Сериализуемые
ID, ordinals, основной мод и mappings не меняются. Эта совместимость привязана
к текущим версиям; после обновления следует перепроверить необходимость адаптера.

Выбраны узкие синхронные сценарии очереди и защиты: они проверяют мир без долгой
симуляции назначения профессии, маршрута и стройки. Это не проверка полного
строительного цикла, расхода склада или загрузки NBT с диска. Существующие
NBT-регрессии сохранены; клиентское отображение и визуальное движение не проверялись.

Настройка сверена с официальной документацией:
https://docs.fabricmc.net/1.21.11/develop/automatic-testing
и исходниками установленного `fabric-gametest-api-v1` 3.1.27+4fc5413f3e.
