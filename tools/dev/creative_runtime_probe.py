"""Record real server commands and persisted creative progress on the isolated dev server."""
import json
import sys
import time
from pathlib import Path

from development_rcon import command
from read_test_nbt import load


def snapshot(label):
    replies = {text: command(text) for text in (
        "smart_aul dev creative status", "tick query", "save-all flush"
    )}
    data = load("run/servers/development/world/data/smart_colonies.dat")[""]["data"]
    plan = dict(data["creative_city"])
    plan["owned_chunk_count"] = len(plan.pop("owned_chunks", []))
    record = {
        "label": label, "time": time.time(), "replies": replies, "plan": plan,
        "colonies": [{
            "id": c["id"], "core": c["core"], "population": c["last_population"],
            "building_search_cursor": c.get("building_search_cursor", -1),
            "storages": c.get("managed_storages", []),
            "jobs": [{k: j.get(k) for k in (
                "id", "status", "template", "next_block", "total_blocks", "catchup"
            )} for j in c.get("construction_jobs", [])]
        } for c in data["colonies"]]
    }
    path = Path("docs/validation/creative-runtime-results.jsonl")
    with path.open("a", encoding="utf-8") as stream:
        stream.write(json.dumps(record, ensure_ascii=False) + "\n")
    print(replies["smart_aul dev creative status"])
    print(json.dumps({k: plan.get(k) for k in (
        "elapsed_ticks", "active", "active_colony", "chunks", "frontier", "owned_chunk_count"
    )}))
    return record


if __name__ == "__main__":
    snapshot(sys.argv[1])
