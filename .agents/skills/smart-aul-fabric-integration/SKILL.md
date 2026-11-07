---
name: smart-aul-fabric-integration
description: Интеграция Smart Aul с Fabric и Minecraft — intermediary mappings, Mixin, клиентский рендер, регистрации и ресурсы. Используй при изменении точек внедрения, метаданных или игровых assets/data.
---

# Fabric, Mixin и ресурсы Smart Aul

Пути указаны от корня. Версии и схема сборки определяются `build.gradle`,
`settings.gradle`, `src/main/resources/fabric.mod.json` и
`src/main/resources/smart_aul.mixins.json`.

## Особенности этого проекта

- Сборка использует локальный `gradle/mappings/intermediary-dev-mappings.jar`.
  Minecraft-имена `class_*`, `method_*`, `field_*` и Mixin selectors осмысленны
  именно в этом namespace. Не переключай проект на Yarn/Mojmap ради отдельного
  примера и не заменяй имена поиском по всему дереву.
- Java-пакет — `net.smartaul`, точка входа — `SmartAulMod`, mod ID — `smart_aul`.
  Старые ресурсы `assets/smart_colonies`, `data/smart_colonies` и связанные
  регистрации нужны сохранениям. Их переименование требует отдельной миграции.
- Читаемые имена восстановленных классов описаны в
  `docs/history/class-renames.json`. Исторические инструкции по декомпиляции не
  нужны для обычной сборки.

## При изменении интеграции

1. Проверь реальную сигнатуру и target в используемой версии Minecraft/mappings.
   Если нужна внешняя документация, используй официальные Fabric Wiki/API и
   исходники нужной версии; сопоставь именованный пример с локальными mappings.
   Навык исследования Fabric, если доступен агенту, применяй по его инструкции.
2. Сверь `@Mixin`, `@Inject`/`@Redirect`, accessor/invoker и конфигурацию
   `smart_aul.mixins.json`. В ней `defaultRequire: 1`; не скрывай несовпадение
   target понижением требования, если это просто маскирует сломанную интеграцию.
3. Клиентские классы `client/render` и клиентские mixins оставляй на стороне
   клиента. Общая серверная инициализация не должна загружать renderer/model.
4. Для ресурса проверь namespace, путь, ссылки из регистраций и наличие в JAR.
   При изменении структур учитывай их выбор через `HouseTemplateRegistry`,
   материалы и правила размещения; NBT-структуры не являются исходниками Java.
5. Собери мод wrapper-командой из `smart-aul-validation`. Для Mixin существенен
   запуск соответствующей стороны: сервер проверяет common hooks, клиент —
   renderer/model. Компиляция сама по себе не доказывает успешную инъекцию.

При изменении версий согласуй зависимости и `fabric.mod.json`. Не проводи
обновление Minecraft, Loom или mappings попутно с исправлением одной функции.
