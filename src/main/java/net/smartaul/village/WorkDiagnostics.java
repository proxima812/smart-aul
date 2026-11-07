package net.smartaul.village;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.Map.Entry;
import net.minecraft.class_1646;
import net.minecraft.class_1799;
import net.minecraft.class_3218;
import net.minecraft.class_7893;
import net.minecraft.class_7923;

public final class WorkDiagnostics {
   private static final Map<class_1646, VillagerWorkStats> x3ciiv5lwkj918 = new WeakHashMap<>();

   private WorkDiagnostics() {
   }

   private static VillagerWorkStats xixsyawjjbup9(class_1646 var0) {
      return x3ciiv5lwkj918.computeIfAbsent(var0, var1 -> {
         VillagerWorkStats var2 = new VillagerWorkStats();
         var2.x36rgtts6rv2l8 = var0.method_73183().method_75260();
         return var2;
      });
   }

   public static void x2t98zlq4lu49t(class_1646 var0, String var1, int var2) {
      if (var2 > 0) {
         VillagerWorkStats var3 = xixsyawjjbup9(var0);
         var3.x36rgtts6rv2l8 = var0.method_73183().method_75260();
         var3.x15462tujos2pg = 0L;
         var3.x1h5tqum4uq2e2 += var2;
         var3.x1f9nzaiq3dwmi = var1;
         ActionTrace.worker(var0, "work_action", var1 + "; amount=" + var2, false);
      }
   }

   public static void x1qd0mutwsr3sc(class_1646 var0, String var1) {
      xixsyawjjbup9(var0).x1f9nzaiq3dwmi = var1;
      ActionTrace.worker(var0, "work_wait", var1, true);
   }

   public static boolean x25cotfj45r525(class_3218 var0, Colony var1, class_1646 var2, class_1799 var3) {
      int var4 = var3.method_7947();
      String var5 = class_7923.field_41178.method_10221(var3.method_7909()).method_12832();
      boolean var6 = ColonyManager.x2cl1crde2rnxy(var0, var1, var3);
      int var7 = var4 - var3.method_7947();
      if (var7 > 0) {
         ActionTrace.event(var0, var1, var2, null, "resource_deposit", var5 + "; amount=" + var7);
         x2t98zlq4lu49t(var2, "доставка на склад", var7);
         xixsyawjjbup9(var2).x2hbwpgkuqkeiy += var7;
         xixsyawjjbup9(var2).x2zbcr7dag6qvf.merge(var5, (long)var7, Long::sum);
      } else if (var4 > 0) {
         x1qd0mutwsr3sc(var2, "склад заполнен");
      }

      return var6;
   }

   public static void x1m5qncn5p4tgc(class_3218 var0, Colony var1, class_1646 var2) {
      if ((var0.method_75260() + var2.method_5628()) % 20L == 0L) {
         if (ActionTrace.enabled()) {
            String tasks = var2.method_18868().method_27074().stream()
               .map(t -> t.getClass().getSimpleName()).sorted().collect(java.util.stream.Collectors.joining(","));
            ActionTrace.worker(var2, "ai_state", "tasks=" + tasks + "; " + xnmb1jjez0ytg(var2) + "; nav=" + VillagerNavigation.xs0vwkswo7jny(var2), true);
         }
         VillagerWorkStats var3 = xixsyawjjbup9(var2);
         long var4 = Math.floorMod(var0.method_8532(), 24000L);
         if (!var2.method_6113() && var4 >= 1000L && var4 < 9000L && var1.roleOf(var2.method_5667()) != ColonyRole.GUARD) {
            var var6 = var2.method_18868().method_27074().stream().filter(var0x -> var0x.getClass().getName().startsWith("net.smartaul.")).toList();
            if (var6.isEmpty()) {
               var3.x15462tujos2pg = 0L;
            } else {
               var3.x15462tujos2pg += 20L;
               if (var3.x15462tujos2pg >= 1200L) {
                  if (!var6.stream().anyMatch(var0x -> var0x instanceof MinerTask || var0x instanceof HouseBuilderTask)) {
                     VillagerShelter.xi17ogl3h66v0(var2, var0.method_75260());

                     for (class_7893 var8 : var6) {
                        var8.method_18925(var0, var2, var0.method_75260());
                     }

                     var3.x15462tujos2pg = 0L;
                     var3.xq8h0uz9ud1id++;
                     var3.x1f9nzaiq3dwmi = "нет результата 60 с: повторный выбор работы";
                     VillagerStuckRecovery.x1feket2ghytw5(var2, var0.method_75260() + 100L);
                  }
               }
            }
         } else {
            var3.x15462tujos2pg = 0L;
         }
      }
   }

   public static String xnmb1jjez0ytg(class_1646 var0) {
      VillagerWorkStats var1 = xixsyawjjbup9(var0);
      return var1.x1f9nzaiq3dwmi
         + ", actions="
         + var1.x1h5tqum4uq2e2
         + ", delivered="
         + var1.x2hbwpgkuqkeiy
         + ", cargo="
         + var1.x2zbcr7dag6qvf
         + ", idle="
         + Math.max(0L, var0.method_73183().method_75260() - var1.x36rgtts6rv2l8) / 20L
         + "s, retries="
         + var1.xq8h0uz9ud1id;
   }

   public static long x2m7mdj9eld89j(class_1646 var0) {
      return xixsyawjjbup9(var0)
         .x2zbcr7dag6qvf
         .entrySet()
         .stream()
         .filter(var0x -> var0x.getKey().startsWith("raw_") || Set.of("coal", "diamond", "emerald", "redstone", "lapis_lazuli").contains(var0x.getKey()))
         .mapToLong(Entry::getValue)
         .sum();
   }

   public static boolean x1z41ec22rzeo2(class_1646 var0, long var1) {
      return var0.method_73183().method_75260() - xixsyawjjbup9(var0).x36rgtts6rv2l8 >= var1;
   }
}
