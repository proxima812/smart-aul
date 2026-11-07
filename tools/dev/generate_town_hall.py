"""Reproduce the town hall structure, without third-party NBT dependencies."""
import gzip
import struct
from pathlib import Path


def string(value):
    raw = value.encode("utf-8")
    return struct.pack(">H", len(raw)) + raw


def payload(kind, value):
    if kind == 3:
        return struct.pack(">i", value)
    if kind == 8:
        return string(value)
    if kind == 9:
        child, values = value
        return bytes([child]) + struct.pack(">i", len(values)) + b"".join(payload(child, v) for v in values)
    if kind == 10:
        return b"".join(bytes([k]) + string(n) + payload(k, v) for n, (k, v) in value.items()) + b"\0"
    raise ValueError(kind)


def structure():
    palette = []
    blocks = {}

    def put(x, y, z, name, **properties):
        entry = {"Name": (8, "minecraft:" + name)}
        if properties:
            entry["Properties"] = (10, {k: (8, v) for k, v in properties.items()})
        if entry not in palette:
            palette.append(entry)
        blocks[x, y, z] = palette.index(entry)

    for x in range(9):
        for z in range(9):
            put(x, 0, z, "cobblestone")
            put(x, 5, z, "oak_slab", type="bottom", waterlogged="false")
            for y in range(1, 5):
                if x in (0, 8) or z in (0, 8):
                    name = "oak_log" if x in (0, 8) and z in (0, 8) else "oak_planks"
                    if y in (2, 3) and (x in (0, 8) and z in (2, 3, 5, 6) or z == 8 and x in (2, 6)):
                        name = "glass_pane"
                    put(x, y, z, name)
    # Two-block-wide doorless entrance: accessible to workers during construction.
    for x in (4, 5):
        for y in (1, 2, 3):
            blocks.pop((x, y, 8), None)
    for x in (2, 4, 6):
        put(x, 2, 1, "oak_wall_sign", facing="south", waterlogged="false")
    for x in (1, 7):
        put(x, 1, 4, "oak_stairs", facing="east" if x == 1 else "west", half="bottom", shape="straight", waterlogged="false")
        put(x, 1, 6, "oak_stairs", facing="east" if x == 1 else "west", half="bottom", shape="straight", waterlogged="false")
        put(x, 3, 1, "wall_torch", facing="south")
    return {
        "DataVersion": (3, 4671),
        "size": (9, (3, [9, 6, 9])),
        "palette": (9, (10, palette)),
        "blocks": (9, (10, [
            {"pos": (9, (3, list(pos))), "state": (3, state)}
            for pos, state in sorted(blocks.items(), key=lambda pair: (pair[0][1], pair[0][2], pair[0][0]))
        ])),
        "entities": (9, (10, [])),
    }


if __name__ == "__main__":
    target = Path(__file__).resolve().parents[2] / "src/main/resources/data/smart_colonies/structure/town_hall.nbt"
    target.parent.mkdir(parents=True, exist_ok=True)
    target.write_bytes(gzip.compress(b"\x0a\x00\x00" + payload(10, structure()), mtime=0))
    print(target)
