package net.smartcolonies.r;

import com.mojang.serialization.Codec;
import java.util.List;
import java.util.Optional;
import net.minecraft.class_1309;
import net.minecraft.class_2338;
import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_3341;
import net.minecraft.class_4140;
import net.minecraft.class_7923;

public final class xxao083dbo7ag {
   private static final Codec<x1rxiy9mtu5b3h> x3jkqdj6uxtk52 = Codec.STRING.xmap(x1rxiy9mtu5b3h::byName, x1rxiy9mtu5b3h::serializedName);
   public static final class_4140<x1rxiy9mtu5b3h> x3ecoc0soedv0m = x23e6ofgmfaayg("colony_role", x3jkqdj6uxtk52);
   public static final class_4140<class_2338> x1r15huley8x9h = x23e6ofgmfaayg("build_target_pos", class_2338.field_25064);
   public static final class_4140<class_2338> x1h4kjv4hra8oe = x23e6ofgmfaayg("tree_target_pos", class_2338.field_25064);
   public static final class_4140<class_2338> x2bwom1pasl6g5 = x23e6ofgmfaayg("farm_target_pos", class_2338.field_25064);
   public static final class_4140<class_2338> x15kki2mjvh7tw = x23e6ofgmfaayg("mine_target_pos", class_2338.field_25064);
   public static final class_4140<class_2338> x263ucjykllb1b = x23e6ofgmfaayg("fish_target_pos", class_2338.field_25064);
   public static final class_4140<class_2338> x2ppem7wipzctm = x23e6ofgmfaayg("animal_target_pos", class_2338.field_25064);
   public static final class_4140<class_3341> x2ooa9y9romfh = x23e6ofgmfaayg("current_structure_bounds", class_3341.field_29325);
   public static final class_4140<class_2338> x3o547clcv9kaw = x23e6ofgmfaayg("storage_chest_pos", class_2338.field_25064);
   public static final class_4140<class_1309> x14b5znl8ndblc = x1xgu987j7gij9("threat_target");

   private xxao083dbo7ag() {
   }

   public static void x2ve9opbgxakpk() {
   }

   public static List<class_4140<?>> x1eiihmuh1mj7p() {
      return List.of(
         x3ecoc0soedv0m,
         x1r15huley8x9h,
         x1h4kjv4hra8oe,
         x2bwom1pasl6g5,
         x15kki2mjvh7tw,
         x263ucjykllb1b,
         x2ppem7wipzctm,
         x2ooa9y9romfh,
         x3o547clcv9kaw,
         x14b5znl8ndblc
      );
   }

   private static <T> class_4140<T> x23e6ofgmfaayg(String var0, Codec<T> var1) {
      return (class_4140<T>)class_2378.method_10230(class_7923.field_41129, class_2960.method_60655("smart_colonies", var0), new class_4140(Optional.of(var1)));
   }

   private static <T> class_4140<T> x1xgu987j7gij9(String var0) {
      return (class_4140<T>)class_2378.method_10230(class_7923.field_41129, class_2960.method_60655("smart_colonies", var0), new class_4140(Optional.empty()));
   }
}
