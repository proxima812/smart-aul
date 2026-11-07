package net.smartaul.village;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.class_2338;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2512;
import net.minecraft.class_2520;
import net.minecraft.class_2680;
import net.minecraft.class_7923;

public final class VillageDefenseState {
   public int x14zjd0to6v6yv;
   public long x37dzr2zekycme;
   public int x1fvrorwquv0wt;
   public final Map<class_2338, class_2680> x16wpjsjmva3od = new LinkedHashMap<>();
   public final List<WallBlockPlan> x1q0gb1h69ywu7 = new ArrayList<>();
   public final Set<Long> x3pic36dm8dawn = new HashSet<>();
   public final Map<class_2338, Long> x1b5j5bmruqafh = new HashMap<>();
   public final Map<class_2338, Integer> xb3n8htn3gf9q = new HashMap<>();
   public final Set<class_2338> x2fh8ft5lrq7el = new HashSet<>();

   public class_2487 x11ueu6ur29o1u() {
      class_2487 var1 = new class_2487();
      var1.method_10569("radius", this.x14zjd0to6v6yv);
      var1.method_10569("skipped", this.x1fvrorwquv0wt);
      var1.method_10544("next_plan", this.x37dzr2zekycme);
      class_2499 var2 = new class_2499();
      class_2499 var3 = new class_2499();
      this.x16wpjsjmva3od.forEach((var1x, var2x) -> var2.add(x153vc7q6umx05(var1x, var2x, false)));
      this.x1q0gb1h69ywu7.forEach(var1x -> var3.add(x153vc7q6umx05(var1x.x1oppt2nr5wv2g(), var1x.x1bbgyigo2fq58(), var1x.xttubpoe0ch3r())));
      var1.method_10564("surveyed", this.x3pic36dm8dawn.stream().mapToLong(Long::longValue).toArray());
      var1.method_10564("abandoned", this.x2fh8ft5lrq7el.stream().mapToLong(class_2338::method_10063).toArray());
      var1.method_10566("managed", var2);
      var1.method_10566("pending", var3);
      return var1;
   }

   public void x3e1m4qdtyt44s(class_2487 var1) {
      this.x14zjd0to6v6yv = Math.clamp((long)var1.method_68083("radius", 0), 0, 512);
      this.x1fvrorwquv0wt = Math.max(0, var1.method_68083("skipped", 0));
      this.x37dzr2zekycme = var1.method_68080("next_plan", 0L);

      for (long var5 : var1.method_10565("surveyed").orElse(new long[0])) {
         this.x3pic36dm8dawn.add(var5);
      }

      for (long var16 : var1.method_10565("abandoned").orElse(new long[0])) {
         if (this.x2fh8ft5lrq7el.size() < 32768) {
            this.x2fh8ft5lrq7el.add(class_2338.method_10092(var16));
         }
      }

      for (class_2520 var11 : var1.method_68569("managed")) {
         if (var11 instanceof class_2487 var14 && this.x16wpjsjmva3od.size() < 16384) {
            WallBlockPlan var17 = x127ho0uxrxw7f(var14);
            this.x16wpjsjmva3od.put(var17.x1oppt2nr5wv2g(), var17.x1bbgyigo2fq58());
         }
      }

      for (class_2520 var12 : var1.method_68569("pending")) {
         if (var12 instanceof class_2487 var15 && this.x1q0gb1h69ywu7.size() < 32768) {
            this.x1q0gb1h69ywu7.add(x127ho0uxrxw7f(var15));
         }
      }
   }

   private static class_2487 x153vc7q6umx05(class_2338 var0, class_2680 var1, boolean var2) {
      class_2487 var3 = new class_2487();
      var3.method_10544("pos", var0.method_10063());
      var3.method_10566("state", class_2512.method_10686(var1));
      var3.method_10556("remove", var2);
      return var3;
   }

   private static WallBlockPlan x127ho0uxrxw7f(class_2487 var0) {
      return new WallBlockPlan(
         class_2338.method_10092(var0.method_68080("pos", 0L)),
         class_2512.method_10681(class_7923.field_41175, var0.method_68568("state")),
         var0.method_68566("remove", false)
      );
   }
}
