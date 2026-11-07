"""Read action evidence and save a small reproducible dev-server checkpoint."""
import collections
import json
import sys
from pathlib import Path

from development_rcon import command
from read_test_nbt import load


def probe(label):
    replies = {c: command(c) for c in (
        "smart_aul dev creative status", "smart_aul dev trace", "tick query", "save-all flush"
    )}
    rows = []
    for path in Path("run/servers/development/logs").glob("smart-aul-actions.jsonl*"):
        for line in path.read_text(encoding="utf-8").splitlines():
            try:
                rows.append(json.loads(line))
            except ValueError:
                pass  # The writer can be appending the last line.
    data = load("run/servers/development/world/data/smart_colonies.dat")[""]["data"]
    summary = {
        "label": label, "replies": replies, "events": dict(collections.Counter(r["event"] for r in rows)),
        "task_starts": dict(collections.Counter(r["detail"] for r in rows if r["event"] == "task_start")),
        "waits": collections.Counter(r["detail"] for r in rows if r["event"] in ("work_wait", "construction_wait")).most_common(12),
        "site_rejections": dict(collections.Counter(r["detail"] for r in rows if r["event"] == "site_rejected")),
        "colonies": [{"id": c["id"], "population": c["last_population"],
            "search_cursor": c.get("building_search_cursor", -1),
            "jobs": [{k: j.get(k) for k in ("status", "next_block", "total_blocks", "template")} for j in c.get("construction_jobs", [])]
        } for c in data["colonies"]],
        "trace_overflows": sum(r.get("dropped", 0) for r in rows if r["event"] == "trace_overflow"),
    }
    with Path("docs/validation/ai-behavior-results.jsonl").open("a", encoding="utf-8") as stream:
        stream.write(json.dumps(summary, ensure_ascii=False) + "\n")
    print(replies["smart_aul dev creative status"])
    print(replies["smart_aul dev trace"])
    print("events", summary["events"])
    print("starts", summary["task_starts"])
    print("rejections", summary["site_rejections"])
    print("waits", summary["waits"])
    return summary


if __name__ == "__main__":
    probe(sys.argv[1])
