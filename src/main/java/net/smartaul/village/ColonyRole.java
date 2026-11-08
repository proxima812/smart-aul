package net.smartaul.village;

import java.util.Locale;

public enum ColonyRole {
   BUILDER,
   GUARD,
   LUMBERJACK,
   MINER,
   FARMER,
   FISHERMAN,
   RANCHER,
   ELDER,
   FOUNDER,
   CITIZEN,
   COURIER;

   // Preserve the original obfuscated binary field names.
   public static final ColonyRole x35csp4lojh59r = BUILDER;
   public static final ColonyRole x3r8p2tvihwpph = GUARD;
   public static final ColonyRole x2hwljbtybtlwc = LUMBERJACK;
   public static final ColonyRole x18g4mqx9kxs89 = MINER;
   public static final ColonyRole x8jydaiei7iry = FARMER;
   public static final ColonyRole xhds9crfjdp9i = FISHERMAN;
   public static final ColonyRole xeoehg87qwfrn = RANCHER;
   public static final ColonyRole x1saccptp3i00w = ELDER;
   public static final ColonyRole x2xja04fpuesoh = FOUNDER;
   public static final ColonyRole x2j116s1wug0g2 = CITIZEN;


   public String serializedName() {
      return this.name().toLowerCase(Locale.ROOT);
   }

   public static ColonyRole byName(String var0) {
      for (ColonyRole var4 : values()) {
         if (var4.serializedName().equals(var0)) {
            return var4;
         }
      }

      return CITIZEN;
   }
}
