#!/usr/bin/env python3
"""Load test of the packaged Smart Aul JAR (R-001, T-016): 5 generated villages, 100+ villagers.

Runs a clean Fabric dedicated server twice on the same seed: with Smart Aul and, as a baseline,
without it (Fabric API only). Villages are found with /locate, force-loaded (Smart Aul registers
them as colonies on generation), topped up with villagers, warmed up and sampled with /tick query.

Usage (after `gradlew build` and a downloaded jar_smoke cache):
    python tools/dev/load_test.py --accept-eula [--java PATH] [--measure 180]

Acceptance (D-008): with the mod, mean MSPT <= 20 ms and worst sampled P95 <= 35 ms, and the
mod's mean overhead over the baseline <= 10 ms. Exit code 0 means PASS.
"""
import argparse
import json
import re
import shutil
import statistics
import sys
import time
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))
import jar_smoke as smoke  # noqa: E402

LOCATE = re.compile(r"is at \[(-?\d+), [^,]+, (-?\d+)\]")
TICK = re.compile(r"Average time per tick: ([\d.]+) ?ms")
PERCENTILES = re.compile(r"P50: ([\d.]+) ?ms.*?P95: ([\d.]+) ?ms.*?P99: ([\d.]+) ?ms", re.S)
COUNT = re.compile(r"Count: (\d+)", re.IGNORECASE)
MEAN_LIMIT, P95_LIMIT, OVERHEAD_LIMIT = 20.0, 35.0, 10.0


def prepare(base, with_mod, mod_jar, launcher_source, api_jar, seed):
    server = base / ("mod" if with_mod else "vanilla")
    if server.exists():
        shutil.rmtree(server)
    server.mkdir(parents=True)
    # Reuse the launcher and its downloaded libraries/vanilla JAR from the smoke directory.
    for item in launcher_source.parent.iterdir():
        if item.name in ("libraries", "versions", ".fabric") and item.is_dir():
            shutil.copytree(item, server / item.name)
    launcher = server / launcher_source.name
    shutil.copy2(launcher_source, launcher)
    mods = server / "mods"
    mods.mkdir()
    shutil.copy2(api_jar, mods / api_jar.name)
    if with_mod:
        shutil.copy2(mod_jar, mods / mod_jar.name)
    (server / "eula.txt").write_text("eula=true\n", encoding="utf-8")
    (server / "server.properties").write_text("\n".join([
        f"level-seed={seed}", "online-mode=false", f"server-port={smoke.free_port()}",
        "pause-when-empty-seconds=0", "view-distance=4", "simulation-distance=4",
        "spawn-protection=0", "difficulty=peaceful", "motd=Smart Aul load test", ""]), encoding="utf-8")
    return server, launcher


def collect(server_proc, pattern, seconds):
    """Collects lines matching pattern that arrive within the given time."""
    found, deadline = [], time.time() + seconds
    regex = re.compile(pattern)
    while time.time() < deadline:
        try:
            line = server_proc.events.get(timeout=max(0.1, deadline - time.time()))
        except Exception:
            break
        if line is None:
            break
        if regex.search(line):
            found.append(line)
    return found


def run(java, server, launcher, log, args, with_mod):
    s = smoke.Server(java, server, launcher, log, args.jvm_arg if with_mod else ())
    result = {"mod": with_mod, "villages": [], "samples": []}
    try:
        s.wait_for(r"Done \(", 900)
        s.command("gamerule doDaylightCycle true")
        s.command("time set 6000")
        villages = []
        for k in range(12):
            if len(villages) >= args.villages:
                break
            x, z = (k % 4) * 1200 - 1800, (k // 4) * 1200 - 1200
            s.command(f"execute positioned {x} 64 {z} run locate structure #minecraft:village")
            line = s.wait_for(r"nearest|Could not find", 120)
            m = LOCATE.search(line)
            if m:
                pos = (int(m.group(1)), int(m.group(2)))
                if all(max(abs(pos[0] - v[0]), abs(pos[1] - v[1])) > 256 for v in villages):
                    villages.append(pos)
        result["villages"] = villages
        if len(villages) < args.villages:
            raise RuntimeError(f"found only {len(villages)} villages")
        for x, z in villages:
            s.command(f"forceload add {x - 64} {z - 64} {x + 63} {z + 63}")
        # Generation of 5 x 64 chunks; Smart Aul logs each registered colony.
        if with_mod:
            registered = collect(s, r"Registered generated vanilla village", args.generate)
            result["registered"] = len(registered)
        else:
            time.sleep(args.generate)
        for x, z in villages:
            for i in range(args.per_village):
                dx, dz = (i % 5) * 3 - 6, (i // 5) * 3 - 6
                s.command(f"execute positioned {x + dx} 0 {z + dz} positioned over motion_blocking_no_leaves "
                          f"run summon minecraft:villager ~ ~ ~")
        time.sleep(5)
        s.command("execute if entity @e[type=minecraft:villager]")
        count = COUNT.search(s.wait_for(r"Test (passed|failed)", 60))
        result["villagers_start"] = int(count.group(1)) if count else 0
        time.sleep(args.warmup)
        end = time.time() + args.measure
        while time.time() < end:
            s.command("tick query")
            lines = collect(s, r".", 5)
            text = "\n".join(lines)
            mean, pct = TICK.search(text), PERCENTILES.search(text)
            if mean and pct:
                result["samples"].append({"mean": float(mean.group(1)), "p50": float(pct.group(1)),
                                          "p95": float(pct.group(2)), "p99": float(pct.group(3))})
        s.command("execute if entity @e[type=minecraft:villager]")
        count = COUNT.search(s.wait_for(r"Test (passed|failed)", 60))
        result["villagers_end"] = int(count.group(1)) if count else 0
        if with_mod:
            s.command("smart_aul settlements")
            time.sleep(2)
    except RuntimeError as error:
        result["error"] = str(error)
    result["exit"] = s.stop()
    result["fatal"] = [l for l in s.lines if smoke.FATAL.search(l)][:40]
    result["errors"] = [l for l in s.lines if "/ERROR]" in l][:40]
    samples = result["samples"]
    if samples:
        result["summary"] = {
            "samples": len(samples),
            "mean": round(statistics.fmean(x["mean"] for x in samples), 2),
            "p95_max": max(x["p95"] for x in samples),
            "p99_max": max(x["p99"] for x in samples),
        }
    return result


def main():
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--accept-eula", action="store_true")
    parser.add_argument("--java", default=shutil.which("java") or "java")
    parser.add_argument("--dir", default=str(smoke.ROOT / "build" / "load-test"))
    parser.add_argument("--smoke-dir", default=str(smoke.ROOT / "build" / "jar-smoke"))
    parser.add_argument("--seed", default="20261109")
    parser.add_argument("--villages", type=int, default=5)
    parser.add_argument("--per-village", type=int, default=20)
    parser.add_argument("--generate", type=int, default=90, help="seconds to wait for village generation")
    parser.add_argument("--warmup", type=int, default=60)
    parser.add_argument("--measure", type=int, default=180)
    parser.add_argument("--skip-baseline", action="store_true")
    parser.add_argument("--jvm-arg", action="append", default=[], help="extra JVM argument for the Smart Aul run, e.g. JFR")
    args = parser.parse_args()
    if not args.accept_eula:
        print("refusing to start: pass --accept-eula only if the Minecraft EULA is accepted", file=sys.stderr)
        return 2
    version = smoke.versions_from_build()["version"]
    mod_jar = smoke.ROOT / "build" / "libs" / f"smart-aul-{version}.jar"
    smoke_dir = Path(args.smoke_dir)
    launchers = sorted((smoke_dir / "server").glob("fabric-server-*.jar"))
    api_jar = smoke_dir / "cache" / f"fabric-api-{smoke.FABRIC_API}.jar"
    if not mod_jar.is_file() or not launchers or not api_jar.is_file():
        print("run gradlew build and tools/dev/jar_smoke.py first (JAR, launcher and Fabric API cache)", file=sys.stderr)
        return 2
    base = Path(args.dir).resolve()
    base.mkdir(parents=True, exist_ok=True)
    report = {"seed": args.seed, "per_village": args.per_village, "warmup": args.warmup, "measure": args.measure, "runs": []}
    with open(base / "load.log", "w", encoding="utf-8") as log:
        for with_mod in ([True] if args.skip_baseline else [True, False]):
            log.write(f"===== {'smart_aul' if with_mod else 'baseline'} =====\n")
            server, launcher = prepare(base, with_mod, mod_jar, launchers[-1], api_jar, args.seed)
            report["runs"].append(run(args.java, server, launcher, log, args, with_mod))
    failures = []
    mod = report["runs"][0]
    for r in report["runs"]:
        name = "mod" if r["mod"] else "baseline"
        if r.get("error"):
            failures.append(f"{name}: {r['error']}")
        if r["exit"] != 0:
            failures.append(f"{name}: exit code {r['exit']}")
        if r["fatal"]:
            failures.append(f"{name}: fatal log lines")
        if "summary" not in r or r["summary"]["samples"] < 10:
            failures.append(f"{name}: too few /tick query samples")
    if mod.get("villagers_start", 0) < 100:
        failures.append(f"mod: only {mod.get('villagers_start')} villagers")
    if mod.get("registered", 0) < args.villages:
        failures.append(f"mod: only {mod.get('registered')} colonies registered")
    if "summary" in mod:
        if mod["summary"]["mean"] > MEAN_LIMIT:
            failures.append(f"mod: mean MSPT {mod['summary']['mean']} > {MEAN_LIMIT}")
        if mod["summary"]["p95_max"] > P95_LIMIT:
            failures.append(f"mod: worst P95 {mod['summary']['p95_max']} > {P95_LIMIT}")
        base_run = next((r for r in report["runs"] if not r["mod"] and "summary" in r), None)
        if base_run:
            overhead = round(mod["summary"]["mean"] - base_run["summary"]["mean"], 2)
            report["overhead_mean"] = overhead
            if overhead > OVERHEAD_LIMIT:
                failures.append(f"mod overhead {overhead} ms > {OVERHEAD_LIMIT}")
    report["failures"] = failures
    (base / "report.json").write_text(json.dumps(report, ensure_ascii=False, indent=2), encoding="utf-8")
    for r in report["runs"]:
        print(("smart_aul" if r["mod"] else "baseline"), "villages", r["villages"], "registered", r.get("registered"),
              "villagers", r.get("villagers_start"), "->", r.get("villagers_end"), "summary", r.get("summary"),
              "errors", len(r["errors"]), "exit", r["exit"])
    print("overhead_mean", report.get("overhead_mean"), "failures", failures)
    print("Load test: " + ("PASS" if not failures else "FAIL"))
    return 0 if not failures else 1


if __name__ == "__main__":
    sys.exit(main())
