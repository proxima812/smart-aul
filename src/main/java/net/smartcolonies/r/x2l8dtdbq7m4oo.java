package net.smartcolonies.r;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import net.minecraft.class_1309;
import net.minecraft.class_2168;
import net.minecraft.class_2170;
import net.minecraft.class_2246;
import net.minecraft.class_2262;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_2561;
import net.minecraft.class_2680;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3341;
import net.minecraft.class_3481;
import net.minecraft.class_3492;
import net.minecraft.class_3499;

public final class x2l8dtdbq7m4oo {
   public static final class_2960 x2l0wl5wa7yi4i = class_2960.method_60655("smart_colonies", "village/savanna/houses/savanna_veranda_1");
   public static final int xbuv0x96xapb5 = 2;

   private x2l8dtdbq7m4oo() {
   }

   public static LiteralArgumentBuilder<class_2168> x2aso8h2ks6ywe() {
      LiteralArgumentBuilder var0 = (LiteralArgumentBuilder)((LiteralArgumentBuilder)class_2170.method_9247("savanna")
            .executes(
               var0x -> x3vftv9p8ig9cy(
                  (class_2168)var0x.getSource(), class_2338.method_49638(((class_2168)var0x.getSource()).method_9222()).method_10069(-6, -1, 6)
               )
            ))
         .then(
            class_2170.method_9244("floor", class_2262.method_9698())
               .executes(var0x -> x3vftv9p8ig9cy((class_2168)var0x.getSource(), class_2262.method_9696(var0x, "floor")))
         );

      for (String var2 : x3eemzey2obnvq.x11oxp2ntj65hs) {
         class_2960 var3 = x3eemzey2obnvq.xhp130tff2ww4(var2);
         var0.then(
            ((LiteralArgumentBuilder)class_2170.method_9247(var2)
                  .executes(
                     var1 -> x1f5jbrpm0wxwk(
                        (class_2168)var1.getSource(), class_2338.method_49638(((class_2168)var1.getSource()).method_9222()).method_10069(-6, -1, 6), var3, 0
                     )
                  ))
               .then(
                  class_2170.method_9244("floor", class_2262.method_9698())
                     .executes(var1 -> x1f5jbrpm0wxwk((class_2168)var1.getSource(), class_2262.method_9696(var1, "floor"), var3, 0))
               )
         );
      }

      LiteralArgumentBuilder var9 = class_2170.method_9247("taiga");

      for (String var12 : x2fhwte3fyje1l.x1krr6wlni86ip) {
         class_2960 var4 = x2fhwte3fyje1l.x1l4umcgiak8iw(var12);
         var9.then(
            ((LiteralArgumentBuilder)class_2170.method_9247(var12)
                  .executes(
                     var1 -> x1f5jbrpm0wxwk(
                        (class_2168)var1.getSource(), class_2338.method_49638(((class_2168)var1.getSource()).method_9222()).method_10069(-6, -1, 6), var4, 0
                     )
                  ))
               .then(
                  class_2170.method_9244("floor", class_2262.method_9698())
                     .executes(var1 -> x1f5jbrpm0wxwk((class_2168)var1.getSource(), class_2262.method_9696(var1, "floor"), var4, 0))
               )
         );
      }

      LiteralArgumentBuilder var11 = (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)class_2170.method_9247("house")
               .requires(class_2170.method_71774(class_2170.field_31839)))
            .then(var0))
         .then(var9);

      for (String var14 : List.of("plains", "desert", "snowy")) {
         LiteralArgumentBuilder var5 = class_2170.method_9247(var14);

         for (String var7 : x53nro1q5nc9v.x3tmz8uu7gcem2(var14)) {
            class_2960 var8 = x53nro1q5nc9v.x4jnab85o8lop(var14, var7);
            var5.then(
               ((LiteralArgumentBuilder)class_2170.method_9247(var7)
                     .executes(
                        var1 -> x1f5jbrpm0wxwk(
                           (class_2168)var1.getSource(), class_2338.method_49638(((class_2168)var1.getSource()).method_9222()).method_10069(-6, -1, 6), var8, 0
                        )
                     ))
                  .then(
                     class_2170.method_9244("floor", class_2262.method_9698())
                        .executes(var1 -> x1f5jbrpm0wxwk((class_2168)var1.getSource(), class_2262.method_9696(var1, "floor"), var8, 0))
                  )
            );
         }

         var11.then(var5);
      }

      return var11;
   }

   public static int x3vftv9p8ig9cy(class_2168 var0, class_2338 var1) {
      return x1f5jbrpm0wxwk(var0, var1, x2l0wl5wa7yi4i, 2);
   }

   public static int x1f5jbrpm0wxwk(class_2168 var0, class_2338 var1, class_2960 var2, int var3) {
      class_3218 var4 = var0.method_9225();
      var var5 = var4.method_14183().method_15094(var2);
      if (var5.isEmpty()) {
         return x3nxixnbj7g1le(var0, "Шаблон тестового дома не найден. Проверьте версию мода.");
      } else {
         class_3499 var6 = (class_3499)var5.get();
         class_2382 var7 = var6.method_15160();
         class_2338 var8 = var1.method_10087(var3);
         class_2338 var9 = var8.method_10069(var7.method_10263() - 1, var7.method_10264() - 1, var7.method_10260() - 1);
         if (!var4.method_31606(var8) && !var4.method_31606(var9) && var4.method_8621().method_11952(var8) && var4.method_8621().method_11952(var9)) {
            for (int var10 = var8.method_10263() >> 4; var10 <= var9.method_10263() >> 4; var10++) {
               for (int var11 = var8.method_10260() >> 4; var11 <= var9.method_10260() >> 4; var11++) {
                  if (!var4.method_14178().method_12123(var10, var11)) {
                     return x3nxixnbj7g1le(var0, "Вся площадка должна быть загружена. Подойдите ближе.");
                  }
               }
            }

            class_238 var19 = class_238.method_54784(var8, var9);
            if (!var4.method_8333(null, var19, var0x -> var0x.method_5805() && var0x instanceof class_1309 && !var0x.method_7325()).isEmpty()) {
               return x3nxixnbj7g1le(var0, "Внутри площадки есть игрок или животное. Освободите её и повторите команду.");
            } else {
               x3d5vk1zj3fvkj var20 = x3d5vk1zj3fvkj.get(var4);
               class_3341 var12 = new class_3341(
                  var8.method_10263(), var8.method_10264(), var8.method_10260(), var9.method_10263(), var9.method_10264(), var9.method_10260()
               );
               if (var20.colonies().stream().flatMap(var0x -> var0x.x1ja61emeln2hc().stream()).anyMatch(var1x -> var1x.x24kbqv6g4kkrg().method_14657(var12))) {
                  return x3nxixnbj7g1le(var0, "Площадка пересекает существующий дом или заказ строителя.");
               } else {
                  var var13 = new LinkedHashMap<class_2338,class_2680>();

                  for (class_2338 var15 : class_2338.method_10097(var8, var9)) {
                     class_2338 var16 = var15.method_10062();
                     class_2680 var17 = var4.method_8320(var16);
                     if (var20.isPlayerBlock(var16) && !var17.method_26215()
                        || var4.method_8321(var16) != null
                        || !var17.method_26227().method_15769()
                        || !var17.method_26215() && !var17.method_45474() && (var16.method_10264() > var1.method_10264() || !x6rhl7fd6f8wq(var17))) {
                        return x3nxixnbj7g1le(
                           var0,
                           "Площадка занята в "
                              + var16.method_23854()
                              + ". Нужен свободный участок "
                              + var7.method_10263()
                              + "×"
                              + var7.method_10260()
                              + "; постройки не перезаписываются."
                        );
                     }

                     var13.put(var16, var17);
                  }

                  try {
                     if (!var6.method_15172(var4, var8, var8, new class_3492().method_15133(true), var4.field_9229, 3)) {
                        x14l2hggbuya9a(var4, var13);
                        return x3nxixnbj7g1le(var0, "Не удалось поставить дом; прежние блоки возвращены.");
                     }
                  } catch (RuntimeException var18) {
                     x14l2hggbuya9a(var4, var13);
                     throw var18;
                  }

                  var0.method_9226(
                     () -> class_2561.method_43470(
                        "Здание готово: "
                           + var2.method_12832().substring(var2.method_12832().lastIndexOf(47) + 1)
                           + ". Участок: "
                           + var1.method_23854()
                           + "; размер "
                           + var7.method_10263()
                           + "×"
                           + var7.method_10260()
                           + "; фасад смотрит на север."
                     ),
                     false
                  );
                  return 1;
               }
            }
         } else {
            return x3nxixnbj7g1le(var0, "Дом выходит за высоту мира или его границу.");
         }
      }
   }

   private static boolean x6rhl7fd6f8wq(class_2680 var0) {
      return var0.method_26164(class_3481.field_29822)
         || var0.method_26164(class_3481.field_25806)
         || var0.method_26164(class_3481.field_15466)
         || var0.method_27852(class_2246.field_10255)
         || var0.method_27852(class_2246.field_10460)
         || var0.method_27852(class_2246.field_10491);
   }

   private static void x14l2hggbuya9a(class_3218 var0, Map<class_2338, class_2680> var1) {
      var1.forEach((var1x, var2) -> var0.method_8652(var1x, var2, 18));
   }

   private static int x3nxixnbj7g1le(class_2168 var0, String var1) {
      var0.method_9213(class_2561.method_43470(var1));
      return 0;
   }
}
