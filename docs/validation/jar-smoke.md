# Smoke-тест упакованного JAR (R-002, T-015) — 2026-11-09

Ревизия: `main` @ `ef03fc2`; JAR `build/libs/smart-aul-1.1.0-dev.jar` из `gradlew.bat build`.
Команда: `python tools/dev/jar_smoke.py --accept-eula --java "C:\Program Files\Java\jdk-21in\java.exe"`.
Windows 11, JDK 21. EULA принята пользователем для одноразового каталога `build/jar-smoke`.

## Стенд

Чистый выделенный сервер, не Loom: Fabric server launcher (MC 1.21.11, loader 0.18.4,
installer 1.1.2), в `mods/` только Fabric API 0.141.4+1.21.11 с Maven и релизный JAR.
Плоский мир, свободный порт, `pause-when-empty-seconds=0`, игроков нет.
Запуск 1: `forceload` 4 чанков у спавна, 4 жителя, ~300 тиков ИИ, `smart_aul settlements`,
`smart_aul progress`, `save-all flush`, `stop`. Запуск 2 — тот же мир: загрузка,
подсчёт жителей, команды, сохранение, `stop`.

## Результат: PASS

| Запуск | Код выхода | Время | Жители | Строк ERROR | Фатальных |
|---|---|---|---|---|---|
| 1 | 0 | 34.6 с | 4 | 0 | 0 |
| 2 | 0 | 16.1 с | 4 | 0 | 0 |

В обоих запусках: `smart_aul 1.1.0-dev` в списке модов, `Smart Aul initialized for generated
vanilla villages`, ответ `Рядом нет поселения.` на `progress` из консоли, сохранён
`world/data/smart_colonies.dat`. Ошибок Mixin, `NoSuchMethodError`, `ClassNotFoundException`
и исключений тика нет; предупреждения — только об offline-режиме.

## Найденные ошибки стенда (исправлены в скрипте)

- Плоский мир без `generator-settings` декодируется пустым (`No key layers`) — жители падали в пустоту.
- В 1.21.11 нет постоянно загруженных spawn-чанков; без `forceload` сущности выгружаются,
  и `@e` их не видит.

## Ограничения

Без игрока: клиентская часть (голограмма, `BlueprintClient`), сетевые пакеты, вход клиента
без мода не проверены. Деревня и колония не создавались — проверена загрузка, Mixin-ы на
тике жителей, команды и сохранение пустых данных, а не механики. Только Windows; в CI не включено.
