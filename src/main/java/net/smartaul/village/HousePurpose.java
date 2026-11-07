package net.smartaul.village;

import java.util.Locale;

public enum HousePurpose {
   GENERAL("общий"),
   FOOD("еда и урожай"),
   WOOD("древесина"),
   MINING("камень и руда"),
   ANIMAL("животноводство");

   // Preserve the original obfuscated binary field names.
   public static final HousePurpose x2y2vmpsepzv3 = GENERAL;
   public static final HousePurpose xnpajviatkbuo = FOOD;
   public static final HousePurpose x24dt8qhwemmti = WOOD;
   public static final HousePurpose x3leq9hn5gf43c = MINING;
   public static final HousePurpose x2uujme1um3d1w = ANIMAL;


   private final String x70udpkzuguui;

   private HousePurpose(String var3) {
      this.x70udpkzuguui = var3;
   }

   public String displayName() {
      return this.x70udpkzuguui;
   }

   public static HousePurpose byName(String var0) {
      try {
         return valueOf(var0.toUpperCase(Locale.ROOT));
      } catch (IllegalArgumentException var2) {
         return GENERAL;
      }
   }

   public static HousePurpose forHouse(long var0) {
      HousePurpose[] var2 = new HousePurpose[]{FOOD, WOOD, MINING, ANIMAL, GENERAL};
      return var2[Math.floorMod((int)var0, var2.length)];
   }
}
