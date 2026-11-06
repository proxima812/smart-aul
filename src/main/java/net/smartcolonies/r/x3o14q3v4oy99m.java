package net.smartcolonies.r;

import java.util.UUID;
import net.minecraft.class_2338;
import net.minecraft.class_2487;
import net.minecraft.class_3341;

public record x3o14q3v4oy99m(UUID xetxwd9l2gcx6, UUID x3mnabq6qj85f8, class_2338 x2kystgxl68xrn, int x3rgd0i6cz19eu) {
   public x3o14q3v4oy99m(UUID xetxwd9l2gcx6, UUID x3mnabq6qj85f8, class_2338 x2kystgxl68xrn, int x3rgd0i6cz19eu) {
      x2kystgxl68xrn = x2kystgxl68xrn.method_10062();
      x3rgd0i6cz19eu = Math.clamp((long)x3rgd0i6cz19eu, 1, 32);
      this.xetxwd9l2gcx6 = xetxwd9l2gcx6;
      this.x3mnabq6qj85f8 = x3mnabq6qj85f8;
      this.x2kystgxl68xrn = x2kystgxl68xrn;
      this.x3rgd0i6cz19eu = x3rgd0i6cz19eu;
   }

   public boolean contains(class_2338 var1) {
      return Math.abs((long)var1.method_10263() - this.x2kystgxl68xrn.method_10263()) <= this.x3rgd0i6cz19eu
         && Math.abs((long)var1.method_10260() - this.x2kystgxl68xrn.method_10260()) <= this.x3rgd0i6cz19eu;
   }

   public boolean intersects(class_3341 var1) {
      return var1.method_35418() >= this.x2kystgxl68xrn.method_10263() - this.x3rgd0i6cz19eu
         && var1.method_35415() <= this.x2kystgxl68xrn.method_10263() + this.x3rgd0i6cz19eu
         && var1.method_35420() >= this.x2kystgxl68xrn.method_10260() - this.x3rgd0i6cz19eu
         && var1.method_35417() <= this.x2kystgxl68xrn.method_10260() + this.x3rgd0i6cz19eu;
   }

   public class_2487 toNbt() {
      class_2487 var1 = new class_2487();
      var1.method_10582("owner", this.xetxwd9l2gcx6.toString());
      var1.method_10582("colony", this.x3mnabq6qj85f8.toString());
      var1.method_10544("center", this.x2kystgxl68xrn.method_10063());
      var1.method_10569("radius", this.x3rgd0i6cz19eu);
      return var1;
   }

   public static x3o14q3v4oy99m fromNbt(class_2487 var0) {
      return new x3o14q3v4oy99m(
         UUID.fromString(var0.method_68564("owner", "")),
         UUID.fromString(var0.method_68564("colony", "")),
         class_2338.method_10092(var0.method_68080("center", 0L)),
         var0.method_68083("radius", 8)
      );
   }
}
