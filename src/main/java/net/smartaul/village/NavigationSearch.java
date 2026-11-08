package net.smartaul.village;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.ToIntFunction;
import net.minecraft.class_2338;

/** Bounded A*: costs describe the worker, never client player inputs. */
final class NavigationSearch {
   record Node(class_2338 pos, int cost, int estimate, long order) {}
   record Result(List<class_2338> points, int expanded, boolean reached) {}

   static int estimate(class_2338 a, class_2338 b) {
      int dx = Math.abs(a.method_10263() - b.method_10263());
      int dz = Math.abs(a.method_10260() - b.method_10260());
      // A step can move both horizontally and vertically. Do not count both twice.
      return Math.max(0, Math.max(10 * Math.max(dx, dz) + 4 * Math.min(dx, dz),
         14 * Math.abs(a.method_10264() - b.method_10264())) - 21);
   }

   /** One-shot search: stops at the node or time limit and returns the best partial path. */
   static Result find(class_2338 start, class_2338 goal, ToIntFunction<class_2338> terrain,
                      BiPredicate<class_2338, class_2338> transition,
                      int limit, long nanos) {
      var session = new Session(start, goal, terrain, transition);
      session.advance(limit, Integer.MAX_VALUE, nanos);
      return session.result(limit);
   }

   /**
    * Resumable A*. Long routes cannot be planned inside one tick's time slice; a session keeps
    * its frontier so later slices continue the same search instead of restarting greedily from
    * the best partial node (which is a dead end in front of concave cliffs and walls).
    * Slicing never changes the result: the same total node limit gives the same path.
    */
   static final class Session {
      private final class_2338 start, goal;
      private final ToIntFunction<class_2338> terrain;
      private final BiPredicate<class_2338, class_2338> transition;
      private final PriorityQueue<Node> open = new PriorityQueue<>(Comparator.comparingInt(Node::estimate).thenComparingLong(Node::order));
      private final HashMap<class_2338, Integer> costs = new HashMap<>();
      private final HashMap<class_2338, class_2338> parents = new HashMap<>();
      private final HashMap<class_2338, Integer> cached = new HashMap<>();
      private long order;
      private class_2338 best;
      private int bestEstimate, expanded;
      private boolean reached;
      /** Heuristic weight in percent: 100 is optimal A*, larger trades route length for fewer nodes. */
      private final int weight;

      Session(class_2338 start, class_2338 goal, ToIntFunction<class_2338> terrain, BiPredicate<class_2338, class_2338> transition) {
         this(start, goal, terrain, transition, 100);
      }

      Session(class_2338 start, class_2338 goal, ToIntFunction<class_2338> terrain, BiPredicate<class_2338, class_2338> transition, int weight) {
         this.start = start; this.goal = goal; this.terrain = terrain; this.transition = transition;
         this.weight = Math.max(100, weight);
         costs.put(start, 0);
         open.add(new Node(start, 0, estimate(start, goal), order++));
         best = start;
         bestEstimate = estimate(start, goal);
      }

      class_2338 start() { return start; }
      class_2338 goal() { return goal; }
      int expanded() { return expanded; }
      /** Long routes are searched with a weighted heuristic and resumed across ticks. */
      boolean longRoute() { return weight > 100; }

      /** True when no further slice can change the result for this total node limit. */
      boolean finished(int limit) { return reached || open.isEmpty() || expanded >= limit; }

      /**
       * Expands up to {@code sliceNodes} nodes, stopping early at the total {@code limit}, the
       * goal or after {@code nanos} (at least one expansion per call guarantees progress).
       */
      void advance(int limit, int sliceNodes, long nanos) {
         long began = System.nanoTime();
         int slice = 0;
         while (!open.isEmpty() && expanded < limit && !reached && slice < sliceNodes) {
            if (slice > 0 && nanos > 0 && System.nanoTime() - began >= nanos) break;
            Node node = open.poll();
            if (node.cost != costs.getOrDefault(node.pos, Integer.MAX_VALUE)) continue;
            expanded++; slice++;
            int remaining = estimate(node.pos, goal);
            if (remaining < bestEstimate) { best = node.pos; bestEstimate = remaining; }
            if (node.pos.method_10262(goal) <= 2.25) { best = node.pos; reached = true; break; }
            for (int dx = -1; dx <= 1; dx++) for (int dz = -1; dz <= 1; dz++) {
               if (dx == 0 && dz == 0) continue;
               for (int dy : new int[]{0, 1, -1}) {
                  var next = node.pos.method_10069(dx, dy, dz);
                  if (Math.abs(next.method_10263() - start.method_10263()) > 192
                     || Math.abs(next.method_10260() - start.method_10260()) > 192) continue;
                  int extra = cached.computeIfAbsent(next, terrain::applyAsInt);
                  if (extra < 0 || !transition.test(node.pos, next)) continue;
                  // Diagonal movement must not cut a corner or cross an unsupported side.
                  if (dx != 0 && dz != 0 && (dy != 0
                     || cached.computeIfAbsent(node.pos.method_10069(dx, 0, 0), terrain::applyAsInt) < 0
                     || cached.computeIfAbsent(node.pos.method_10069(0, 0, dz), terrain::applyAsInt) < 0)) continue;
                  int cost = node.cost + (dx != 0 && dz != 0 ? 14 : 10) + Math.abs(dy) * 4 + extra;
                  if (cost >= costs.getOrDefault(next, Integer.MAX_VALUE)) continue;
                  costs.put(next, cost); parents.put(next, node.pos);
                  open.add(new Node(next, cost, cost + (int)((long)estimate(next, goal) * weight / 100), order++));
               }
            }
         }
      }

      /** Path to the goal, or to the node with the best estimate found so far. */
      Result result(int limit) {
         return pathTo(best, limit);
      }

      /**
       * For a truncated long route: path to the open node with the lowest f = g + h, which
       * already accounts for detours found so far. The closest node by estimate can still be a
       * dead end in front of a wall the search has found its way around. Consumes the session.
       */
      Result frontierResult(int limit) {
         if (reached) return result(limit);
         while (!open.isEmpty()) {
            Node node = open.poll();
            if (node.cost == costs.getOrDefault(node.pos, Integer.MAX_VALUE) && !node.pos.equals(start)) return pathTo(node.pos, limit);
         }
         return result(limit);
      }

      private Result pathTo(class_2338 target, int limit) {
         var points = new ArrayList<class_2338>();
         for (var pos = target; !pos.equals(start); pos = parents.get(pos)) {
            if (pos == null || points.size() >= limit) return new Result(List.of(), expanded, false);
            points.add(pos);
         }
         Collections.reverse(points);
         return new Result(List.copyOf(points), expanded, reached);
      }
   }
}
