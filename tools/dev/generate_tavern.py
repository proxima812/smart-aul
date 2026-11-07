"""Original Smart Aul tavern and courtyard; no external assets or NBT dependencies."""
import gzip
from pathlib import Path
from generate_town_hall import payload


def structure():
    palette, blocks = [], {}
    def put(x, y, z, name, **props):
        state = {"Name": (8, "minecraft:" + name)}
        if props:
            state["Properties"] = (10, {k: (8, v) for k, v in props.items()})
        if state not in palette:
            palette.append(state)
        blocks[x, y, z] = palette.index(state)
    # Enclosed room 9x9, open southern courtyard 9x4 with benches and a well.
    for x in range(9):
        for z in range(13):
            put(x, 0, z, "cobblestone")
            for y in range(1, 6):
                put(x, y, z, "air")
            if z < 9:
                put(x, 5, z, "oak_slab", type="bottom", waterlogged="false")
                for y in range(1, 5):
                    if x in (0, 8) or z in (0, 8):
                        name = "oak_log" if x in (0, 8) and z in (0, 8) else "oak_planks"
                        if y in (2, 3) and (x in (0, 8) and z in (3, 5) or z == 0 and x in (3, 5)):
                            name = "glass_pane"
                        put(x, y, z, name)
    for x in (4, 5):
        for y in (1, 2, 3):
            put(x, y, 8, "air")
    for x in range(2, 7):
        put(x, 1, 2, "oak_planks")  # bar counter
    for x in (2, 6):
        put(x, 1, 5, "oak_fence", waterlogged="false")
        put(x, 2, 5, "oak_pressure_plate", powered="false")
        for z in (4, 6):
            put(x, 1, z, "oak_stairs", facing="south" if z == 4 else "north", half="bottom", shape="straight", waterlogged="false")
        put(x, 3, 0, "wall_torch", facing="south")
    for x in (1, 7):
        for z in (10, 11):
            put(x, 1, z, "oak_stairs", facing="east" if x == 1 else "west", half="bottom", shape="straight", waterlogged="false")
    # Dry ornamental well avoids introducing flowing water during staged building.
    for x, z in ((3,10), (4,10), (5,10), (3,11), (5,11), (3,12), (4,12), (5,12)):
        put(x, 1, z, "cobblestone")
    for x in (3, 5):
        put(x, 2, 11, "oak_fence", waterlogged="false")
        put(x, 3, 11, "oak_slab", type="bottom", waterlogged="false")
    put(4, 3, 11, "oak_slab", type="bottom", waterlogged="false")
    return {"DataVersion": (3, 4671), "size": (9, (3, [9,6,13])),
            "palette": (9, (10, palette)), "blocks": (9, (10, [
                {"pos": (9, (3, list(pos))), "state": (3, state)}
                for pos, state in sorted(blocks.items(), key=lambda p: (p[0][1], p[0][2], p[0][0]))])),
            "entities": (9, (10, []))}


if __name__ == "__main__":
    target = Path(__file__).resolve().parents[2] / "src/main/resources/data/smart_colonies/structure/tavern.nbt"
    target.write_bytes(gzip.compress(b"\x0a\x00\x00" + payload(10, structure()), mtime=0))
    print(target)
