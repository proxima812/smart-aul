package net.smartcolonies.r;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.class_1258;
import net.minecraft.class_1263;
import net.minecraft.class_1799;
import net.minecraft.class_2338;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_7923;

public final class x1pk6h11cwmsba {
   public static Map<x2x8dxxurg9b7p, Integer> x2l7fon36lgwu2(class_1263 var0) {
      var var1 = new HashMap<x2x8dxxurg9b7p,Integer>();

      for (int var2 = 0; var2 < var0.method_5439(); var2++) {
         class_1799 var3 = var0.method_5438(var2);
         if (!var3.method_7960()) {
            var1.merge(new x2x8dxxurg9b7p(var3), var3.method_7947(), Integer::sum);
         }
      }

      return var1;
   }

   public static int x2qxayij05nw0s(Map<x2x8dxxurg9b7p, Integer> var0, Map<x2x8dxxurg9b7p, Integer> var1) {
      int var2 = 0;

      for (Entry var4 : var0.entrySet()) {
         var2 += Math.max(0, (Integer)var4.getValue() - var1.getOrDefault(var4.getKey(), 0));
      }

      return var2;
   }

   private x1pk6h11cwmsba() {
   }

   public static boolean x33zofxkqevoo8(class_3218 var0, class_2338 var1) {
      return x2uh56vnzc4u51.xic1z39wiy3pn(var0, var1)
         ? false
         : x2n55y2tom37h2.xchl1k1tnh3w8(var0).colonies().stream().anyMatch(var1x -> var1x.x29j0o9zdxri2().containsKey(var1));
   }

   public static boolean xu27zvtjfruut(class_3218 var0, x15kgoaq6i3m6i var1, class_1263 var2) {
      for (class_2338 var4 : var1.x29j0o9zdxri2().keySet()) {
         if (!x2uh56vnzc4u51.xic1z39wiy3pn(var0, var4)
            && var0.method_8321(var4) instanceof class_1263 var5
            && (var2 == var5 || var2 instanceof class_1258 var7 && var7.method_5405(var5))) {
            return true;
         }
      }

      return false;
   }

   public static Map<class_2960, Integer> x2yl261ceqau5h(class_3218 var0, x15kgoaq6i3m6i var1) {
      var var2 = new HashMap<class_2960,Integer>();

      for (class_2338 var4 : var1.x29j0o9zdxri2().keySet()) {
         if (!x2uh56vnzc4u51.xic1z39wiy3pn(var0, var4) && var0.method_8321(var4) instanceof class_1263 var5) {
            for (int var8 = 0; var8 < var5.method_5439(); var8++) {
               class_1799 var7 = var5.method_5438(var8);
               if (!var7.method_7960()) {
                  var2.merge(class_7923.field_41178.method_10221(var7.method_7909()), var7.method_7947(), Integer::sum);
               }
            }
         }
      }

      return var2;
   }

   public static int xhz2g56hwio6r(Map<class_2960, Integer> var0, Map<class_2960, Integer> var1) {
      int var2 = 0;

      for (Entry var4 : var0.entrySet()) {
         var2 += Math.max(0, (Integer)var4.getValue() - var1.getOrDefault(var4.getKey(), 0));
      }

      return var2;
   }
}
