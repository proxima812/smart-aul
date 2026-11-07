package net.smartaul.village;

import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import net.minecraft.class_1646;
import net.minecraft.class_2338;
import net.minecraft.class_3218;
import net.smartaul.SmartAulMod;
import net.smartaul.config.SmartAulConfig;

/** Server-side diagnostics. Only immutable JSON strings cross to the disk thread. */
public final class ActionTrace {
   private static final Gson JSON = new Gson();
   private static final Map<Object, Map<String, Repeated>> waits = new WeakHashMap<>();
   private static Journal journal;
   private static final class Repeated {
      String detail;
      long tick;
      int repeats;
   }
   private ActionTrace() {}

   public static boolean enabled() { return SmartAulConfig.x2by5f9ehxvwk9().debug.log_ai_actions; }

   public static void event(class_3218 world, Colony colony, class_1646 worker, class_2338 target, String type, String detail) {
      if (!enabled()) return;
      if (colony == null && (worker != null || target != null))
         colony = ColonyManager.x3psqezd5jem54(world, worker == null ? target : worker.method_24515()).orElse(null);
      var row = new LinkedHashMap<String, Object>();
      row.put("time_ms", System.currentTimeMillis());
      row.put("tick", world.method_75260());
      row.put("world", world.method_27983().method_29177().toString());
      row.put("event", type);
      row.put("actor", worker == null ? "village_system" : "villager");
      if (colony != null) row.put("colony", colony.id().toString());
      if (worker != null) {
         row.put("villager", worker.method_5667().toString());
         row.put("pos", worker.method_24515().method_23854());
         row.put("role", colony == null ? "unknown" : colony.roleOf(worker.method_5667()).serializedName());
      }
      if (target != null) row.put("target", target.method_23854());
      row.put("detail", detail);
      if (journal == null) journal = new Journal(Path.of("logs", "smart-aul-actions.jsonl"), 8 * 1024 * 1024, 3, 8192);
      journal.offer(JSON.toJson(row));
   }

   public static void worker(class_1646 worker, String type, String detail, boolean repeat) {
      if (!enabled()) return;
      if (!(worker.method_73183() instanceof class_3218 world)) return;
      if (repeat && !changed(worker, type, detail, world.method_75260())) return;
      event(world, ColonyManager.x3psqezd5jem54(world, worker.method_24515()).orElse(null), worker, null, type, detail);
   }

   public static void state(class_3218 world, Colony colony, String type, String detail) {
      if (enabled() && changed(colony == null ? world : colony, type, detail, world.method_75260()))
         event(world, colony, null, colony == null ? null : colony.core(), type, detail);
   }

   static boolean changed(Object subject, String type, String detail, long tick) {
      var states = waits.computeIfAbsent(subject, ignored -> new HashMap<>());
      var previous = states.computeIfAbsent(type, ignored -> new Repeated());
      if (detail.equals(previous.detail) && tick >= previous.tick && tick - previous.tick < 100) {
         previous.repeats++;
         return false;
      }
      previous.detail = detail;
      previous.tick = tick;
      previous.repeats = 0;
      return true;
   }

   public static String status() {
      return "AI logging=" + enabled() + "; logs/smart-aul-actions.jsonl; "
         + (journal == null ? "not opened" : "queued=" + journal.queue.size() + "; dropped=" + journal.totalDropped.get()
            + "; error=" + journal.failure);
   }

   public static void close() {
      if (journal != null) { journal.close(); journal = null; }
      waits.clear();
   }

   /** Bounded queue and rotating files keep slow disks off the simulation thread. */
   static final class Journal implements AutoCloseable {
      private static final int NEWLINE_BYTES = System.lineSeparator().getBytes(StandardCharsets.UTF_8).length;
      private final Path path;
      private final long limit;
      private final int backups;
      private final ArrayBlockingQueue<String> queue;
      private final AtomicLong totalDropped = new AtomicLong();
      private final AtomicLong pendingDropped = new AtomicLong();
      private final Thread thread;
      private volatile boolean running = true;
      private volatile String failure = "none";

      Journal(Path path, long limit, int backups, int capacity) {
         this.path = path; this.limit = limit; this.backups = backups;
         queue = new ArrayBlockingQueue<>(capacity);
         thread = new Thread(this::write, "Smart-Aul-action-log");
         thread.setDaemon(true);
         thread.start();
      }

      void offer(String line) {
         if (!running || !queue.offer(line)) { totalDropped.incrementAndGet(); pendingDropped.incrementAndGet(); }
      }

      private BufferedWriter open() throws IOException {
         Files.createDirectories(path.toAbsolutePath().getParent());
         return Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
      }

      private void rotate() throws IOException {
         for (int i = backups; i >= 1; i--) {
            Path from = i == 1 ? path : path.resolveSibling(path.getFileName() + "." + (i - 1));
            if (Files.exists(from)) Files.move(from, path.resolveSibling(path.getFileName() + "." + i), StandardCopyOption.REPLACE_EXISTING);
         }
      }

      private void write() {
         BufferedWriter writer = null;
         try {
            writer = open();
            long bytes = Files.size(path), flushed = System.nanoTime();
            while (running || !queue.isEmpty()) {
               String line = queue.poll(200, TimeUnit.MILLISECONDS);
               if (line != null) {
                  int size = line.getBytes(StandardCharsets.UTF_8).length + NEWLINE_BYTES;
                  if (bytes + size > limit) { writer.close(); rotate(); writer = open(); bytes = 0; }
                  writer.write(line); writer.newLine(); bytes += size;
               }
               long dropped = pendingDropped.getAndSet(0);
               if (dropped > 0) {
                  String notice = "{\"event\":\"trace_overflow\",\"dropped\":" + dropped + "}";
                  writer.write(notice); writer.newLine(); bytes += notice.length() + NEWLINE_BYTES;
               }
               if (System.nanoTime() - flushed > 1_000_000_000L || !running) {
                  writer.flush(); flushed = System.nanoTime();
               }
            }
         } catch (IOException | InterruptedException error) {
            failure = error.toString();
            SmartAulMod.LOGGER.warn("AI action log stopped: {}", failure);
         } finally {
            running = false;
            if (writer != null) try { writer.close(); } catch (IOException ignored) {}
         }
      }

      public void close() {
         running = false;
         try { thread.join(2000); } catch (InterruptedException error) { Thread.currentThread().interrupt(); }
      }
   }
}
