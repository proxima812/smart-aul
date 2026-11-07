# Developer mode and readable class names

- Renamed all 145 obfuscated project Java types, including 32 mixins.
- Updated Java references, regression code and `smart_aul.mixins.json`.
- Cross-checked mixin target names against cached Yarn 1.21.11 build 5 mappings.
- Kept Minecraft intermediary selectors and persisted resource/NBT IDs intact.
- Added `/smart_aul dev` using the existing scenario implementation; `film` remains available.
- Added Russian manual scenarios in `TESTING_RU.md`.
- `gradlew.bat clean build`: PASS, 60 regression checks.
- Final JAR inspection: all 32 configured mixins have class files; no obfuscated top-level class names.
- `git diff --check`: PASS (line-ending normalization warnings only).
- Dedicated development server: reached Done, accepted dev help/on/status/off and film help,
  then stopped and saved successfully. Status without a nearby registered colony reports that condition.
- Server smoke log: `run/servers/development/logs/latest.log`.
- Client-only rendering mixins and complete in-game village scenarios have not been visually tested.
- Existing uncommitted changes were retained during renaming.
