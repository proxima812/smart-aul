# Deterministic fuzz regression (2026-11-08)

Entry point: `FuzzRegression.run()`, one added call beside
`NavigationSafetyRegression.run()` in `DevelopmentRegression.main`.
Run with `./gradlew.bat build` or `./gradlew.bat developmentRegression`.
No JUnit, new dependencies, world access, or live server is required.

## Coverage

- 3,000 maps, 9,750 production searches, 1,454 reachable full searches.
  Flat mazes, terraces, random height changes/cliffs, blocked/unsupported cells,
  weighted water, and water-to-bank transition restrictions.
- Independent Dijkstra enumerates pairs of supported cells without using the A*
  heuristic or neighbour generator. Compare reachability and optimal weighted
  path cost (cardinal 10, diagonal 14, vertical surcharge 4, terrain penalties).
  Target tolerance is the production contract: squared distance <= 2.25.
- Validate every full, node-limited and timed path: adjacency, support, transition
  permission, no repeated vertex, no drop over one block, no diagonal corner cut,
  and actual endpoint on success. Node limits are 1..8; 750 additional corridor
  searches prove nonempty partial progress after exactly four expansions.
  A 1ns time budget deterministically stops after the mandatory first expansion;
  a 1s per-map watchdog allows scheduler pauses. This is not a production 3ms
  performance benchmark or a live-world path execution check.
- 1,000 persistence cases: random valid colony/job/creative data must round-trip
  to equal NBT. Mutate every serialized key probabilistically, recursively through
  compound/list entries: deletion, wrong types, invalid UUID/resource IDs,
  int/long extremes, extra keys. Also read empty compounds, oversized safety and
  chunk queues, and legacy int-array optional UUIDs. Verify colony counters,
  job progress/property/stall bounds, creative cursor bounds and root consistency,
  safety queue cap/support and completed counter, owned-chunk cap.
- 64 file-based JSON/migration cases use the real config loader, temporary paths,
  malformed JSON, null sections, wrong types, missing fields and numeric extremes.
  Some cases retain valid types to exercise clamping rather than defaults.
  Check every DevelopmentPolicy range, population/house caps, idempotent validation,
  stable validated JSON roundtrip, copy-once migration and preservation of legacy
  bytes. Restore prior in-memory config and remove all temporary files/backups.

## Reproduction

Base seed: `6002525700211430732` (`0x534D41525441554C`).
Navigation case i: `BASE + i`; NBT: `BASE ^ (0x10000L + i)`;
JSON: `BASE ^ (0x20000L + i)`. Each case has its own Random and sorted mutation
keys. Assertion failures print phase and case seed. Rerun the same Gradle task;
there is no wall-clock-dependent expected route.

## Fixes exposed while adding the properties

- Missing `reported_thief` generated a new UUID on load. Read it with a nullable
  fallback while retaining old int-array UUID support.
- A newly constructed colony saved an empty labor census, but loading inserted
  zero entries for every role. Write a canonical full census from the first save.
- Creative plan parsing threw on missing/malformed root, member or farm UUIDs.
  Skip malformed members/farms, restore root ordering, and disable plans with
  damaged ownership. Missing root uses an inactive zero-UUID placeholder;
  valid plan IDs and progress remain unchanged. Initial failing NBT seed:
  `6002525700211365196`.
- The explicit-path config loader could not initialize outside Fabric because a
  static field fetched Fabric's config directory. Resolve that directory only in
  the normal Fabric startup entry point; tests use the actual explicit-path loader.

## Validation

`./gradlew.bat build`: PASS, including existing 96,994 development checks and the
fuzz properties. `git diff --check`: PASS. Final fuzz duration: **10.725s**
(addition budget <30s); full build: **1m 27s**. An earlier warm build measured
1.914s for the fuzz portion; timings vary with concurrent machine load.

Limits: finite fixed-seed generated data, not exhaustive adversarial NBT sizes;
no live Minecraft world/client, chunk unloading or villager movement validation.
Minecraft's codec and the config loader intentionally log diagnostics for corrupt
inputs; these ERROR messages are expected, with assertions determining the result.
