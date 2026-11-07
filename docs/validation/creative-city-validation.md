# Creative city scenario validation

Implementation: `/smart_aul dev creative`, with `status` and `stop` children.
Objective: six related colonies, twelve houses/services per colony, population 32,
warehouses, irrigated farms, lamps and five connecting roads. Simulation duration:
48,000 ticks (40 minutes at 20 TPS). Existing direct child settlements can join the city.

Survival config is unchanged. The scenario supplies creative construction materials
and applies a rotating shared catch-up budget of 256 blocks per tick. Protected land,
player construction and entity collision checks remain active. Construction sites
reserve planned roads and farm plots. Chunk generation is staged one chunk per tick;
only chunks added by this scenario are released by stop or deadline.

Checks:

- `gradlew.bat build`: passed, 327 regression checks, including schedule boundaries,
  command presence, district separation and actual NBT round trips of timer,
  membership, infrastructure cursors, farm reservations and chunk ownership.
- `git diff --check`: passed (line-ending warnings only).
- Dedicated server smoke: scenario started at the test colony (318,65,372),
  status was readable, buildings increased from 1 to 2 during the short test,
  stop succeeded, server saved and shut down normally.
- The smoke detected the existing child colony obstructing the original first
  district location; the implementation now adopts direct children before seeding
  additional districts and loads their actual positions.
- The test world's pre-existing forced chunks were retained after stopping.
- Smoke log: `docs/validation/creative-city-smoke.log`.

Limits: no full forty-minute end-to-end city completion or visual client validation
has been performed. The short smoke did not prove six districts or completed roads.
Obstructed sites/roads/farms and low TPS can prevent the objective completing on time.
At the deadline the scenario reports success only if all goals are met; otherwise it
reports incomplete and releases its chunk loads. No user client save was opened.
