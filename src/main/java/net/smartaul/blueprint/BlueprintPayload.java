package net.smartaul.blueprint;

import java.util.*;
import io.netty.buffer.ByteBuf;
import net.minecraft.*;

/** A bounded replacement snapshot, never a stream of unbounded additions. */
public record BlueprintPayload(List<Block> blocks, List<Bounds> bounds) implements class_8710 {
   public static final int MAX_BLOCKS = 4096, MAX_BUILDINGS = 16;
   public static final class_8710.class_9154<BlueprintPayload> ID = new class_8710.class_9154<>(class_2960.method_60655("smart_aul", "blueprint"));
   public static final class_9139<ByteBuf, BlueprintPayload> CODEC = class_9139.method_56437(BlueprintPayload::encode, BlueprintPayload::decode);
   public record Block(long pos, int state) {}
   public record Bounds(long min, long max) {}
   public BlueprintPayload {
      if (blocks.size() > MAX_BLOCKS || bounds.size() > MAX_BUILDINGS) throw new IllegalArgumentException("Blueprint limit");
      blocks = List.copyOf(blocks); bounds = List.copyOf(bounds);
   }
   public class_8710.class_9154<BlueprintPayload> method_56479() { return ID; }
   private static void encode(ByteBuf buf, BlueprintPayload value) {
      buf.writeInt(value.blocks.size());
      for (var b : value.blocks) { buf.writeLong(b.pos); buf.writeInt(b.state); }
      buf.writeInt(value.bounds.size());
      for (var b : value.bounds) { buf.writeLong(b.min); buf.writeLong(b.max); }
   }
   private static int count(ByteBuf buf, int max) {
      int n = buf.readInt();
      if (n < 0 || n > max) throw new IllegalArgumentException("Blueprint count");
      return n;
   }
   private static BlueprintPayload decode(ByteBuf buf) {
      int n = count(buf, MAX_BLOCKS);
      var blocks = new ArrayList<Block>(n);
      for (int i = 0; i < n; i++) blocks.add(new Block(buf.readLong(), buf.readInt()));
      n = count(buf, MAX_BUILDINGS);
      var bounds = new ArrayList<Bounds>(n);
      for (int i = 0; i < n; i++) bounds.add(new Bounds(buf.readLong(), buf.readLong()));
      return new BlueprintPayload(blocks, bounds);
   }
}
