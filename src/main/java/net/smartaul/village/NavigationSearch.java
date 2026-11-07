package net.smartaul.village;

import java.util.*;
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

   static Result find(class_2338 start, class_2338 goal, ToIntFunction<class_2338> terrain,
                      java.util.function.BiPredicate<class_2338, class_2338> transition,
                      int limit, long nanos) {
      var open = new PriorityQueue<Node>(Comparator.comparingInt(Node::estimate).thenComparingLong(Node::order));
      var costs = new HashMap<class_2338, Integer>();
      var parents = new HashMap<class_2338, class_2338>();
      var cached = new HashMap<class_2338, Integer>();
      long began = System.nanoTime(), order = 0;
      costs.put(start, 0);
      open.add(new Node(start, 0, estimate(start, goal), order++));
      class_2338 best = start;
      int bestEstimate = estimate(start, goal), expanded = 0;
      boolean reached = false;
      while (!open.isEmpty() && expanded < limit) {
         if (expanded > 0 && nanos > 0 && System.nanoTime() - began >= nanos) break;
         Node node = open.poll();
         if (node.cost != costs.getOrDefault(node.pos, Integer.MAX_VALUE)) continue;
         expanded++;
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
               open.add(new Node(next, cost, cost + estimate(next, goal), order++));
            }
         }
      }
      var points = new ArrayList<class_2338>();
      for (var pos = best; !pos.equals(start); pos = parents.get(pos)) {
         if (pos == null || points.size() >= limit) return new Result(List.of(), expanded, false);
         points.add(pos);
      }
      Collections.reverse(points);
      return new Result(List.copyOf(points), expanded, reached);
   }
}
