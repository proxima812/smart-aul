package net.smartcolonies.r;

import java.util.Locale;

public enum x2jj5edkiu5orf {
   GENERAL("общий"),
   FOOD("еда и урожай"),
   WOOD("древесина"),
   MINING("камень и руда"),
   ANIMAL("животноводство");

   // Preserve the original obfuscated binary field names.
   public static final x2jj5edkiu5orf x2y2vmpsepzv3 = GENERAL;
   public static final x2jj5edkiu5orf xnpajviatkbuo = FOOD;
   public static final x2jj5edkiu5orf x24dt8qhwemmti = WOOD;
   public static final x2jj5edkiu5orf x3leq9hn5gf43c = MINING;
   public static final x2jj5edkiu5orf x2uujme1um3d1w = ANIMAL;


   private final String x70udpkzuguui;

   private x2jj5edkiu5orf(String var3) {
      this.x70udpkzuguui = var3;
   }

   public String displayName() {
      return this.x70udpkzuguui;
   }

   public static x2jj5edkiu5orf byName(String var0) {
      try {
         return valueOf(var0.toUpperCase(Locale.ROOT));
      } catch (IllegalArgumentException var2) {
         return GENERAL;
      }
   }

   public static x2jj5edkiu5orf forHouse(long var0) {
      x2jj5edkiu5orf[] var2 = new x2jj5edkiu5orf[]{FOOD, WOOD, MINING, ANIMAL, GENERAL};
      return var2[Math.floorMod((int)var0, var2.length)];
   }
}
