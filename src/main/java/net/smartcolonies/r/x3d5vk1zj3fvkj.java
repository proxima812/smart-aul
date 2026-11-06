package net.smartcolonies.r;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import net.minecraft.class_10741;
import net.minecraft.class_18;
import net.minecraft.class_1923;
import net.minecraft.class_2338;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_3218;

public final class x3d5vk1zj3fvkj extends class_18 {
   private static final String x41xas6lstatb = "smart_colonies";
   private static final class_10741<x3d5vk1zj3fvkj> xlj3y4jgsvf46 = new class_10741(
      "smart_colonies", x3d5vk1zj3fvkj::new, class_2487.field_25128.xmap(x3d5vk1zj3fvkj::x1vfd7ma58aaai, x3d5vk1zj3fvkj::x17s9f8fvs5qrt), null
   );
   private final Map<UUID, x15kgoaq6i3m6i> x1g5jtc8hqnb9v = new LinkedHashMap<>();
   private final Set<Long> x2zsg3w2bwvt8r = new HashSet<>();
   private final Map<Long, Set<Long>> x1j9cli2zfshem = new HashMap<>();
   private final Map<Long, UUID> x2fxchwsury85l = new HashMap<>();
   private final List<x3o14q3v4oy99m> x33hs2tmxu4ojw = new ArrayList<>();
   private final Map<Long, x2puvzcw5rs039> x38t3bkbt21nlp = new HashMap<>();

   private void x1iwjnc1stouur(long var1) {
      class_2338 var3 = class_2338.method_10092(var1);
      this.x1j9cli2zfshem.computeIfAbsent(class_1923.method_8331(var3.method_10263() >> 4, var3.method_10260() >> 4), var0 -> new HashSet<>()).add(var1);
   }

   public boolean playerBuildNear(class_2338 var1, int var2) {
      for (int var3 = var1.method_10263() - var2 >> 4; var3 <= var1.method_10263() + var2 >> 4; var3++) {
         for (int var4 = var1.method_10260() - var2 >> 4; var4 <= var1.method_10260() + var2 >> 4; var4++) {
            var var5 = this.x1j9cli2zfshem.get(class_1923.method_8331(var3, var4));
            if (var5 != null) {
               for (long var7 : var5) {
                  class_2338 var9 = class_2338.method_10092(var7);
                  long var10 = (long)var9.method_10263() - var1.method_10263();
                  long var12 = (long)var9.method_10260() - var1.method_10260();
                  if (var10 * var10 + var12 * var12 <= (long)var2 * var2) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   public List<x3o14q3v4oy99m> land() {
      return Collections.unmodifiableList(this.x33hs2tmxu4ojw);
   }

   public void addLand(x3o14q3v4oy99m var1) {
      this.x33hs2tmxu4ojw.add(var1);
      this.method_80();
   }

   public Optional<UUID> blockOwner(class_2338 var1) {
      return Optional.ofNullable(this.x2fxchwsury85l.get(var1.method_10063()));
   }

   public boolean foreignBlocks(x3o14q3v4oy99m var1) {
      return this.x2fxchwsury85l
         .entrySet()
         .stream()
         .anyMatch(var1x -> !var1x.getValue().equals(var1.xetxwd9l2gcx6()) && var1.contains(class_2338.method_10092(var1x.getKey())));
   }

   public boolean isPlayerBlock(class_2338 var1) {
      return this.x2zsg3w2bwvt8r.contains(var1.method_10063());
   }

   public void recordPlacement(class_2338 var1, UUID var2, long var3) {
      this.x2zsg3w2bwvt8r.add(var1.method_10063());
      this.x1iwjnc1stouur(var1.method_10063());
      this.x2fxchwsury85l.put(var1.method_10063(), var2);
      this.x38t3bkbt21nlp.entrySet().removeIf(var2x -> var3 - var2x.getValue().xv5ezs7er1s4p() > 1200L);
      this.x38t3bkbt21nlp.put(var1.method_10063(), new x2puvzcw5rs039(var2, var3));
      this.method_80();
   }

   public boolean recentAttackerBlock(class_2338 var1, UUID var2, long var3) {
      x2puvzcw5rs039 var5 = this.x38t3bkbt21nlp.get(var1.method_10063());
      return var5 != null && var5.x1v3u0njrh3j7a().equals(var2) && var3 - var5.xv5ezs7er1s4p() >= 0L && var3 - var5.xv5ezs7er1s4p() <= 1200L;
   }

   public void forgetPlacement(class_2338 var1) {
      if (this.x2zsg3w2bwvt8r.remove(var1.method_10063())) {
         this.method_80();
      }

      long var2 = class_1923.method_8331(var1.method_10263() >> 4, var1.method_10260() >> 4);
      var var4 = this.x1j9cli2zfshem.get(var2);
      if (var4 != null) {
         var4.remove(var1.method_10063());
         if (var4.isEmpty()) {
            this.x1j9cli2zfshem.remove(var2);
         }
      }

      this.x2fxchwsury85l.remove(var1.method_10063());
      this.x38t3bkbt21nlp.remove(var1.method_10063());
   }

   public static x3d5vk1zj3fvkj get(class_3218 var0) {
      return (x3d5vk1zj3fvkj)var0.method_17983().method_17924(xlj3y4jgsvf46);
   }

   public Collection<x15kgoaq6i3m6i> colonies() {
      return this.x1g5jtc8hqnb9v.values();
   }

   public Optional<x15kgoaq6i3m6i> byId(UUID var1) {
      return Optional.ofNullable(this.x1g5jtc8hqnb9v.get(var1));
   }

   public void add(x15kgoaq6i3m6i var1) {
      this.x1g5jtc8hqnb9v.put(var1.id(), var1);
      this.method_80();
   }

   private class_2487 x17s9f8fvs5qrt() {
      class_2487 var1 = new class_2487();
      class_2499 var2 = new class_2499();
      this.x1g5jtc8hqnb9v.values().forEach(var1x -> var2.add(var1x.xufzblerzrsnk()));
      var1.method_10566("colonies", var2);
      var1.method_10564("player_blocks", this.x2zsg3w2bwvt8r.stream().mapToLong(Long::longValue).toArray());
      class_2499 var3 = new class_2499();
      this.x2fxchwsury85l.forEach((var1x, var2x) -> {
         class_2487 var3x = new class_2487();
         var3x.method_10544("pos", var1x);
         var3x.method_10582("owner", var2x.toString());
         var3.add(var3x);
      });
      var1.method_10566("block_owners", var3);
      class_2499 var4 = new class_2499();
      this.x33hs2tmxu4ojw.forEach(var1x -> var4.add(var1x.toNbt()));
      var1.method_10566("land", var4);
      x6amcj7vm0jm5.x2xqw0zvyjpw3b(var1, this);
      return var1;
   }

   private static x3d5vk1zj3fvkj x1vfd7ma58aaai(class_2487 var0) {
      x3d5vk1zj3fvkj var1 = new x3d5vk1zj3fvkj();

      for (long var5 : var0.method_10565("player_blocks").orElse(new long[0])) {
         var1.x2zsg3w2bwvt8r.add(var5);
         var1.x1iwjnc1stouur(var5);
      }

      class_2499 var9 = var0.method_68569("block_owners");

      for (int var10 = 0; var10 < var9.size(); var10++) {
         class_2487 var12 = var9.method_68582(var10);

         try {
            var1.x2fxchwsury85l.put(var12.method_68080("pos", 0L), UUID.fromString(var12.method_68564("owner", "")));
         } catch (IllegalArgumentException var8) {
         }
      }

      class_2499 var11 = var0.method_68569("land");

      for (int var13 = 0; var13 < var11.size(); var13++) {
         try {
            var1.x33hs2tmxu4ojw.add(x3o14q3v4oy99m.fromNbt(var11.method_68582(var13)));
         } catch (IllegalArgumentException var7) {
         }
      }

      x6amcj7vm0jm5.x1vdpt6b5sksjj(var0, var1);
      class_2499 var14 = var0.method_68569("colonies");

      for (int var15 = 0; var15 < var14.size(); var15++) {
         x15kgoaq6i3m6i var6 = x15kgoaq6i3m6i.x3bvvkrzdde479(var14.method_68582(var15));
         var1.x1g5jtc8hqnb9v.put(var6.id(), var6);
      }

      return var1;
   }
}
