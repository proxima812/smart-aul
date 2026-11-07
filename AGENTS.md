# Инструкции агентам Smart Aul

Этот файл — общий вход для агентов, работающих в репозитории. Инструкции относятся
ко всему проекту. Перед изменением кода прочитай подходящий `SKILL.md` из таблицы;
для задачи на стыке областей используй несколько скиллов. Пути указаны от корня
репозитория. Скиллы хранятся вместе с проектом в `.agents/skills`, поэтому доступны
также агентам без встроенной поддержки Skills: открой нужный файл как Markdown.

## Выбор скилла

| Задача | Скилл |
|---|---|
| ИИ, профессии, экономика, навигация, защита, серверные тики | [.agents/skills/smart-aul-villager-ai/SKILL.md](.agents/skills/smart-aul-villager-ai/SKILL.md) |
| Строительство, ремонт, дороги, стены, выбор площадок и защита земли | [.agents/skills/smart-aul-construction/SKILL.md](.agents/skills/smart-aul-construction/SKILL.md) |
| Экспедиции, новые поселения, офлайн-развитие, креативная застройка | [.agents/skills/smart-aul-settlements/SKILL.md](.agents/skills/smart-aul-settlements/SKILL.md) |
| Конфиги, NBT, миграции, совместимость миров | [.agents/skills/smart-aul-persistence/SKILL.md](.agents/skills/smart-aul-persistence/SKILL.md) |
| Mixin, Fabric, mappings, рендер, игровые ресурсы и метаданные | [.agents/skills/smart-aul-fabric-integration/SKILL.md](.agents/skills/smart-aul-fabric-integration/SKILL.md) |
| Gradle, регрессии, запуск тестового сервера, игровая проверка и JAR | [.agents/skills/smart-aul-validation/SKILL.md](.agents/skills/smart-aul-validation/SKILL.md) |
| Ревью изменений без запроса на реализацию | [.agents/skills/smart-aul-review/SKILL.md](.agents/skills/smart-aul-review/SKILL.md) |

## Контекст и общие ограничения

- Актуальные ориентиры: `README.md`, `TESTING_RU.md`, `docs/PROJECT_STRUCTURE_RU.md`.
  `docs/history` и прежние отчёты — история, а не подтверждение текущего поведения.
- Minecraft 1.21.11, Fabric, Java target 21; версии зависимостей и задачи проверяй
  в `build.gradle`, версию wrapper — в `gradle/wrapper/gradle-wrapper.properties`.
- Java-код находится в `src/main/java/net/smartaul`; публичный ID — `smart_aul`.
  Сохранённые ID ресурсов, памяти ИИ и данных `smart_colonies` нужны старым мирам.
  Не заменяй их массово вслед за Java-пакетом.
- Проект использует локальные intermediary mappings из
  `gradle/mappings/intermediary-dev-mappings.jar`. Имена Minecraft `class_*`,
  `method_*`, `field_*` нельзя исправлять как обычную обфускацию проекта.
- Перед работой проверь `git status --short`: не отменяй чужие изменения и не
  восстанавливай удалённые старые пакеты `net.smartcolonies` из Git автоматически.
- `run/` содержит миры, а не только временные файлы. Для игровых тестов используй
  отдельный тестовый мир или копию. Не очищай `run/saves` и серверные миры как кеш.
- Сохраняй UTF-8. В Windows PowerShell читай русские файлы с `-Encoding UTF8`.
- Для проверки изменений Java используй Gradle wrapper; `build` уже включает
  `developmentRegression` через `check`. Для изменений только инструкций достаточно
  проверки ссылок, формата скиллов и `git diff --check`.
- В отчёте укажи результат, выполненные проверки и оставшиеся ограничения.
  Успешная сборка не подтверждает визуальное поведение жителей в игре.

Наличие скиллов не означает необходимость запускать несколько агентов. Если
делегирование отдельно разрешено, передай исполнителю этот файл, область задачи
и соответствующие скиллы; не допускай несогласованных правок общих файлов.
