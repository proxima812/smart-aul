package net.smartaul.village;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import net.minecraft.class_2338;
import net.minecraft.class_2487;
import net.minecraft.class_2499;

/** Persistent frontier for continuous creative settlement. */
public final class CreativeCityPlan {
   public static final int CHUNK_LIMIT = 768;
   public final UUID root;
   public final class_2338 origin;
   public final List<UUID> colonies = new ArrayList<>();
   public final Set<Long> ownedChunks = new LinkedHashSet<>();
   public final java.util.Map<UUID, class_2338> farms = new java.util.LinkedHashMap<>();
   public long elapsed;
   public long frontier = 1;
   public int activeColony;
   public int failedTemplates;
   public int chunkCursor;
   public int roadCursor;
   public int farmCursor;
   public int blockedRoads;
   public int blockedFarms;
   public int siteAttempt;
   public boolean active = true;
   public boolean rebuildWindow;
   public int searchOrderVersion = 1;
   public boolean mapComplete;
   public String message = "Непрерывная застройка карты";

   public CreativeCityPlan(UUID root, class_2338 origin) {
      this.root = root;
      this.origin = origin.method_10062();
      colonies.add(root);
   }

   public static int populationTarget(long houses) {
      return Math.clamp(6L + Math.max(0, houses) * 2, 6, 32);
   }

   /** Square spiral; every grid cell is visited once, including distant rings. */
   public static long[] cell(long index) {
      if (index <= 0) return new long[]{0, 0};
      long ring = (long)Math.ceil((Math.sqrt(index + 1.0) - 1) / 2);
      long side = 2 * ring, delta = (2 * ring + 1) * (2 * ring + 1) - 1 - index;
      if (delta < side) return new long[]{ring - delta, -ring};
      if (delta < side * 2) return new long[]{-ring, -ring + delta - side};
      if (delta < side * 3) return new long[]{-ring + delta - side * 2, ring};
      return new long[]{ring, ring - (delta - side * 3)};
   }

   public static boolean exhausted(long frontier, int spacing, int x, int z, double west, double east, double north, double south) {
      double reach = Math.max(Math.max(Math.abs(west - x), Math.abs(east - x)),
         Math.max(Math.abs(north - z), Math.abs(south - z)));
      long rings = (long)Math.ceil(reach / spacing);
      long side = rings * 2 + 1;
      return frontier >= side * side;
   }

   public class_2487 toNbt() {
      var n = new class_2487();
      n.method_10582("root", root.toString());
      n.method_10544("origin", origin.method_10063());
      n.method_10544("elapsed_ticks", elapsed);
      n.method_10544("frontier", frontier);
      n.method_10569("active_colony", activeColony);
      n.method_10569("failed_templates", failedTemplates);
      n.method_10569("chunks", chunkCursor);
      n.method_10569("roads", roadCursor);
      n.method_10569("farms", farmCursor);
      n.method_10569("blocked_roads", blockedRoads);
      n.method_10569("blocked_farms", blockedFarms);
      n.method_10569("site_attempt", siteAttempt);
      n.method_10556("active", active);
      n.method_10569("search_order_version", searchOrderVersion);
      n.method_10556("map_complete", mapComplete);
      n.method_10582("message", message);
      n.method_10564("owned_chunks", ownedChunks.stream().mapToLong(Long::longValue).toArray());
      var list = new class_2499();
      for (var id : colonies) {
         var entry = new class_2487();
         entry.method_10582("id", id.toString());
         list.add(entry);
      }
      n.method_10566("colonies", list);
      var farmList = new class_2499();
      farms.forEach((id, pos) -> {
         var entry = new class_2487();
         entry.method_10582("id", id.toString());
         entry.method_10544("pos", pos.method_10063());
         farmList.add(entry);
      });
      n.method_10566("farm_origins", farmList);
      return n;
   }

   private static UUID readId(class_2487 n, String key) {
      try { return UUID.fromString(n.method_68564(key, "")); }
      catch (IllegalArgumentException error) { return null; }
   }

   public static CreativeCityPlan fromNbt(class_2487 n) {
      UUID root = readId(n, "root");
      boolean damagedIdentity = root == null;
      var p = new CreativeCityPlan(root == null ? new UUID(0, 0) : root,
         class_2338.method_10092(n.method_68080("origin", 0L)));
      p.elapsed = Math.max(0L, n.method_68080("elapsed_ticks", n.method_68083("elapsed", 0)));
      p.frontier = Math.max(1L, n.method_68080("frontier", 1L));
      p.failedTemplates = Math.max(0, n.method_68083("failed_templates", 0));
      p.chunkCursor = Math.clamp(n.method_68083("chunks", 0), 0, CHUNK_LIMIT);
      p.roadCursor = Math.clamp(n.method_68083("roads", 0), 0, 5 * 513 * 3);
      p.farmCursor = Math.clamp(n.method_68083("farms", 0), 0, 6 * 58);
      p.blockedRoads = Math.max(0, n.method_68083("blocked_roads", 0));
      p.blockedFarms = Math.max(0, n.method_68083("blocked_farms", 0));
      p.siteAttempt = Math.clamp(n.method_68083("site_attempt", 0), 0, 289);
      p.active = n.method_68566("active", false);
      p.searchOrderVersion = n.method_68083("search_order_version", 0);
      p.mapComplete = n.method_68566("map_complete", false);
      p.message = n.method_68564("message", "");
      p.colonies.clear();
      var list = n.method_68569("colonies");
      for (int i = 0; i < list.size(); i++) {
         UUID id = readId(list.method_68582(i), "id");
         if (id != null) p.colonies.add(id);
         else damagedIdentity = true;
      }
      if (p.colonies.isEmpty() || !p.colonies.getFirst().equals(p.root)) {
         damagedIdentity = true;
         p.colonies.remove(p.root);
         p.colonies.addFirst(p.root);
      }
      // Preserve recoverable progress, but never resume a plan with damaged ownership.
      if (damagedIdentity) p.active = false;
      var farmList = n.method_68569("farm_origins");
      for (int i = 0; i < farmList.size(); i++) {
         var entry = farmList.method_68582(i);
         var id = readId(entry, "id");
         if (p.colonies.contains(id)) p.farms.put(id, class_2338.method_10092(entry.method_68080("pos", 0L)));
      }
      for (long c : n.method_10565("owned_chunks").orElse(new long[0])) {
         if (p.ownedChunks.size() >= CHUNK_LIMIT) break;
         p.ownedChunks.add(c);
      }
      p.rebuildWindow = true;
      p.activeColony = Math.clamp(n.method_68083("active_colony", 0), 0, p.colonies.size());
      return p;
   }
}
