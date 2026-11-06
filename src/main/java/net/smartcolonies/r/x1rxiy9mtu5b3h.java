package net.smartcolonies.r;

import java.util.Locale;

public enum x1rxiy9mtu5b3h {
   BUILDER,
   GUARD,
   LUMBERJACK,
   MINER,
   FARMER,
   FISHERMAN,
   RANCHER,
   ELDER,
   FOUNDER,
   CITIZEN;

   // Preserve the original obfuscated binary field names.
   public static final x1rxiy9mtu5b3h x35csp4lojh59r = BUILDER;
   public static final x1rxiy9mtu5b3h x3r8p2tvihwpph = GUARD;
   public static final x1rxiy9mtu5b3h x2hwljbtybtlwc = LUMBERJACK;
   public static final x1rxiy9mtu5b3h x18g4mqx9kxs89 = MINER;
   public static final x1rxiy9mtu5b3h x8jydaiei7iry = FARMER;
   public static final x1rxiy9mtu5b3h xhds9crfjdp9i = FISHERMAN;
   public static final x1rxiy9mtu5b3h xeoehg87qwfrn = RANCHER;
   public static final x1rxiy9mtu5b3h x1saccptp3i00w = ELDER;
   public static final x1rxiy9mtu5b3h x2xja04fpuesoh = FOUNDER;
   public static final x1rxiy9mtu5b3h x2j116s1wug0g2 = CITIZEN;


   public String serializedName() {
      return this.name().toLowerCase(Locale.ROOT);
   }

   public static x1rxiy9mtu5b3h byName(String var0) {
      for (x1rxiy9mtu5b3h var4 : values()) {
         if (var4.serializedName().equals(var0)) {
            return var4;
         }
      }

      return CITIZEN;
   }
}
