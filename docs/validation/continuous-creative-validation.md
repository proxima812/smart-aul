# Continuous creative settlement validation

The creative command now has no time deadline or global village/building quota.
One village fills its safe building sites, trying all home/service templates before
the next village is processed. New settlement sites follow a square spiral inside
the world border, with nearby alternatives for unsuitable terrain. Active work
loads one 96-block settlement area at a time and releases only its own tickets.
After all candidate cells have been visited, resource mode remains enabled.

Resource changes: communal-store extraction copies stacks without depletion;
village resource payments and supply crafting preserve materials. The director
continually replenishes communal supplies and supplies loaded older settlements.
Construction continues to use its creative material bypass.

Persistence keeps the unbounded membership list, long elapsed tick count, frontier,
current settlement, template-search progress, farms and chunk ownership. Existing
active forty-minute saves migrate; chunk windows are rebuilt after load.

Verification:

- `gradlew.bat build`: regression suite covers command registration, 10,000 unique
  adjacent spiral positions, finite and offset world-border traversal, NBT round
  trips beyond six villages and 32-bit elapsed time, stopped/completed states,
  legacy elapsed-time migration and window rebuilding.
- API reference checked for world-border bounds: Fabric Yarn 1.21.11 build 4,
  https://maven.fabricmc.net/docs/yarn-1.21.11+build.4/net/minecraft/world/border/WorldBorder.html
- No generated resources changed. No player world was opened.

Follow-up dedicated-server testing reproduced a hang in `RancherTask`: copying
unlimited supplies exposed a restock loop that kept dropping items when the
recipient inventory was full. Both rancher restock paths now check capacity,
require count progress and stop after at most 64 transfers. Regression checks cover
full/partial inventories, unavailable supply, exact targets and the hard budget.

Restart testing also found that `Colony` did not persist the building-search cursor.
It now saves `building_search_cursor`, with an idle default for older worlds.
The creative status reports seconds, chunk-loading progress, current construction
block counts and site-search progress; stopped mode reports ordinary resources.

See `creative-runtime-validation.md` and `creative-runtime-results.jsonl` for the
live-server evidence. No full-map fill or visual client validation was run.
The previous `creative-city-validation.md` describes the old six-village scenario
and its historical server smoke, not this revision.
