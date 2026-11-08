#!/usr/bin/env python3
"""Smoke test of the packaged Smart Aul JAR on a clean Fabric dedicated server (R-002, T-015).

Unlike Loom runs, this uses the release JAR from build/libs with only Fabric Loader and
Fabric API, in an isolated directory (default build/jar-smoke). It never touches run/.

Usage (after `gradlew build`):
    python tools/dev/jar_smoke.py --accept-eula [--java PATH] [--dir build/jar-smoke]

--accept-eula writes eula=true into the isolated server directory only; pass it only if the
Minecraft EULA (https://aka.ms/MinecraftEULA) has been accepted. Exit code 0 means PASS.
"""
import argparse
import json
import os
import queue
import re
import shutil
import socket
import subprocess
import sys
import threading
import time
import urllib.request
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
MINECRAFT = "1.21.11"
LOADER = "0.18.4"
FABRIC_API = "0.141.4+1.21.11"
USER_AGENT = "smart-aul-jar-smoke/1.0"

# Problems that mean the packaged mod does not work in production.
FATAL = re.compile(
    r"Mixin apply|InvalidMixin|MixinTransformerError|NoSuchMethodError|NoSuchFieldError|"
    r"NoClassDefFoundError|ClassNotFoundException|IncompatibleClassChangeError|AbstractMethodError|"
    r"Exception in server tick loop|Encountered an unexpected exception|Failed to load|crash report",
    re.IGNORECASE)
MOD_MENTION = re.compile(r"smart[_ ]?aul|smartaul|smart_colonies", re.IGNORECASE)


def versions_from_build():
    text = (ROOT / "build.gradle").read_text(encoding="utf-8")
    found = {
        "minecraft": re.search(r"com\.mojang:minecraft:([^'\"]+)", text),
        "loader": re.search(r"net\.fabricmc:fabric-loader:([^'\"]+)", text),
        "api": re.search(r"fabric-api:fabric-api:([^'\"]+)", text),
        "version": re.search(r"^version\s*=\s*'([^']+)'", text, re.M),
    }
    return {k: (m.group(1) if m else None) for k, m in found.items()}


def download(url, target):
    if target.exists() and target.stat().st_size > 0:
        return
    target.parent.mkdir(parents=True, exist_ok=True)
    tmp = target.with_suffix(target.suffix + ".part")
    req = urllib.request.Request(url, headers={"User-Agent": USER_AGENT})
    with urllib.request.urlopen(req, timeout=120) as response, open(tmp, "wb") as out:
        shutil.copyfileobj(response, out)
    tmp.replace(target)


def free_port():
    with socket.socket() as s:
        s.bind(("127.0.0.1", 0))
        return s.getsockname()[1]


class Server:
    def __init__(self, java, directory, launcher, log):
        self.lines = []
        self.events = queue.Queue()
        self.log = log
        self.process = subprocess.Popen(
            [java, "-Xmx2G", "-jar", launcher.name, "nogui"], cwd=directory,
            stdin=subprocess.PIPE, stdout=subprocess.PIPE, stderr=subprocess.STDOUT,
            text=True, encoding="utf-8", errors="replace", bufsize=1)
        threading.Thread(target=self._read, daemon=True).start()

    def _read(self):
        for line in self.process.stdout:
            line = line.rstrip("\n")
            self.lines.append(line)
            self.log.write(line + "\n")
            self.log.flush()
            self.events.put(line)
        self.events.put(None)

    def wait_for(self, pattern, timeout):
        regex = re.compile(pattern)
        deadline = time.time() + timeout
        while time.time() < deadline:
            try:
                line = self.events.get(timeout=max(0.1, deadline - time.time()))
            except queue.Empty:
                break
            if line is None:
                raise RuntimeError(f"server exited while waiting for {pattern!r}")
            if regex.search(line):
                return line
        raise RuntimeError(f"timeout {timeout}s waiting for {pattern!r}")

    def command(self, text):
        self.log.write(f"> {text}\n")
        self.process.stdin.write(text + "\n")
        self.process.stdin.flush()

    def stop(self, timeout=180):
        if self.process.poll() is None:
            self.command("stop")
        try:
            return self.process.wait(timeout=timeout)
        except subprocess.TimeoutExpired:
            self.process.kill()
            return -9


def main():
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--accept-eula", action="store_true", help="write eula=true into the isolated directory")
    parser.add_argument("--java", default=os.environ.get("SMOKE_JAVA") or shutil.which("java") or "java")
    parser.add_argument("--dir", default=str(ROOT / "build" / "jar-smoke"))
    parser.add_argument("--jar", help="mod JAR (default: build/libs/smart-aul-<version>.jar)")
    parser.add_argument("--report", help="write a JSON summary here")
    args = parser.parse_args()

    found = versions_from_build()
    for key, expected in (("minecraft", MINECRAFT), ("loader", LOADER), ("api", FABRIC_API)):
        if found[key] != expected:
            print(f"build.gradle {key}={found[key]} differs from script {expected}; update jar_smoke.py", file=sys.stderr)
            return 2
    mod_jar = Path(args.jar) if args.jar else ROOT / "build" / "libs" / f"smart-aul-{found['version']}.jar"
    if not mod_jar.is_file():
        print(f"missing {mod_jar}; run gradlew build first", file=sys.stderr)
        return 2
    if not args.accept_eula:
        print("refusing to start: pass --accept-eula only if the Minecraft EULA is accepted", file=sys.stderr)
        return 2

    base = Path(args.dir).resolve()
    if ROOT / "run" in base.parents or base == ROOT / "run":
        print("the smoke directory must not be inside run/", file=sys.stderr)
        return 2
    cache = base / "cache"
    server = base / "server"
    # Only server state is reset; downloaded launcher, libraries and vanilla JAR are kept.
    for name in ("world", "mods", "config", "logs", "crash-reports", "server.properties", "eula.txt"):
        path = server / name
        if path.is_dir():
            shutil.rmtree(path)
        elif path.exists():
            path.unlink()
    server.mkdir(parents=True, exist_ok=True)

    installers = json.load(urllib.request.urlopen(urllib.request.Request(
        "https://meta.fabricmc.net/v2/versions/installer", headers={"User-Agent": USER_AGENT}), timeout=60))
    installer = next(v["version"] for v in installers if v["stable"])
    launcher = server / f"fabric-server-mc.{MINECRAFT}-loader.{LOADER}-launcher.{installer}.jar"
    download(f"https://meta.fabricmc.net/v2/versions/loader/{MINECRAFT}/{LOADER}/{installer}/server/jar", launcher)
    api_jar = cache / f"fabric-api-{FABRIC_API}.jar"
    download(f"https://maven.fabricmc.net/net/fabricmc/fabric-api/fabric-api/{FABRIC_API}/fabric-api-{FABRIC_API}.jar", api_jar)

    mods = server / "mods"
    mods.mkdir()
    shutil.copy2(api_jar, mods / api_jar.name)
    shutil.copy2(mod_jar, mods / mod_jar.name)
    (server / "eula.txt").write_text("eula=true\n", encoding="utf-8")
    (server / "server.properties").write_text("\n".join([
        "level-type=minecraft\\:flat",
        # Without explicit layers the flat preset decodes as empty ("No key layers") and is void.
        'generator-settings={"layers":[{"block":"minecraft:bedrock","height":1},'
        '{"block":"minecraft:dirt","height":2},{"block":"minecraft:grass_block","height":1}],'
        '"biome":"minecraft:plains"}',
        "online-mode=false",
        # Keep ticking without players; the default pauses an empty server after 60 s.
        "pause-when-empty-seconds=0", f"server-port={free_port()}",
        "view-distance=4", "simulation-distance=4", "spawn-protection=0", "enable-rcon=false",
        "motd=Smart Aul JAR smoke", "level-seed=7", ""]), encoding="utf-8")

    results = {"jar": str(mod_jar), "installer": installer, "runs": []}
    failures = []
    with open(base / "smoke.log", "w", encoding="utf-8") as log:
        for run in (1, 2):
            log.write(f"===== run {run} =====\n")
            began = time.time()
            s = Server(args.java, server, launcher, log)
            try:
                done = s.wait_for(r"Done \(", 600)
                # 1.21.11 keeps no spawn chunks loaded: force-load the test area so the
                # villagers stay loaded and entity-ticking without a player.
                s.command("forceload add -16 -16 15 15")
                time.sleep(3)  # entities of a freshly loaded chunk load asynchronously
                if run == 1:
                    for i in range(4):
                        s.command(f"summon minecraft:villager {i * 2} -60 3")
                    s.command("time set day")
                    time.sleep(15)  # about 300 server ticks of villager AI with the mod's mixins
                s.command("execute if entity @e[type=minecraft:villager]")
                count = s.wait_for(r"Test (passed|failed)", 30)
                s.command("smart_aul settlements")
                s.command("smart_aul progress")
                time.sleep(2)
                s.command("save-all flush")
                s.wait_for(r"Saved the game", 120)
            except RuntimeError as error:
                failures.append(f"run {run}: {error}")
                done = count = None
            code = s.stop()
            if code != 0:
                failures.append(f"run {run}: exit code {code}")
            run_lines = s.lines
            results["runs"].append({
                "run": run, "exit": code, "seconds": round(time.time() - began, 1),
                "done": done, "villagers": count,
                "mod_lines": [l for l in run_lines if MOD_MENTION.search(l)][:80],
                "fatal": [l for l in run_lines if FATAL.search(l)][:40],
                "errors": [l for l in run_lines if "/ERROR]" in l or " ERROR" in l][:40],
            })
            joined = "\n".join(run_lines)
            if "Smart Aul initialized" not in joined:
                failures.append(f"run {run}: mod initializer did not run")
            if not re.search(r"smart_aul\s+" + re.escape(found["version"]), joined):
                failures.append(f"run {run}: smart_aul {found['version']} missing from the loaded mod list")
            if results["runs"][-1]["fatal"]:
                failures.append(f"run {run}: fatal log lines")
            if count is None or not re.search(r"count: 4\b", count, re.IGNORECASE):
                failures.append(f"run {run}: expected 4 villagers, got {count}")
            # The mod's command tree is registered in production and answers from the console.
            if "Рядом нет поселения" not in joined:
                failures.append(f"run {run}: no reply to /smart_aul progress")
            if not (server / "world" / "data" / "smart_colonies.dat").is_file():
                failures.append(f"run {run}: world/data/smart_colonies.dat was not saved")
    results["world_data"] = sorted(p.name for p in (server / "world" / "data").glob("*")) if (server / "world" / "data").is_dir() else []
    results["failures"] = failures
    if args.report:
        Path(args.report).write_text(json.dumps(results, ensure_ascii=False, indent=2), encoding="utf-8")
    print(json.dumps({k: results[k] for k in ("installer", "world_data", "failures")}, ensure_ascii=False, indent=2))
    for r in results["runs"]:
        print(f"run {r['run']}: exit={r['exit']} {r['seconds']}s villagers={r['villagers']!r} fatal={len(r['fatal'])} errors={len(r['errors'])}")
    print("JAR smoke: " + ("PASS" if not failures else "FAIL"))
    return 0 if not failures else 1


if __name__ == "__main__":
    sys.exit(main())
