package net.smartaul.client;

import java.util.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.*;
import net.fabricmc.fabric.api.client.rendering.v1.world.*;
import net.minecraft.*;
import net.smartaul.blueprint.BlueprintPayload;

/** Untextured baked model geometry: white blueprint silhouettes, including stairs/slabs. */
public final class BlueprintClient implements ClientModInitializer {
   private record Mesh(float[] vertices, List<BlueprintPayload.Bounds> bounds) {}
   private static final Mesh EMPTY = new Mesh(new float[0], List.of());
   private static Mesh mesh = EMPTY;
   private static class_2960 dimension;
   private static long received;
   public void onInitializeClient() {
      ClientPlayNetworking.registerGlobalReceiver(BlueprintPayload.ID, (payload, context) -> context.client().execute(() -> rebuild(payload)));
      ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> { mesh = EMPTY; dimension = null; });
      WorldRenderEvents.BEFORE_TRANSLUCENT.register(BlueprintClient::draw);
   }
   private static void rebuild(BlueprintPayload payload) {
      var client = class_310.method_1551();
      if (client.field_1687 == null) return;
      dimension = client.field_1687.method_27983().method_29177();
      received = System.nanoTime();
      var points = new ArrayList<Float>();
      for (var block : payload.blocks()) {
         var pos = class_2338.method_10092(block.pos());
         var state = class_2248.method_9531(block.state());
         var model = client.method_1541().method_3349(state);
         for (var part : model.method_68512(class_5819.method_43049(42))) {
            add(points, part.method_68509(null), pos);
            for (var face : class_2350.values()) add(points, part.method_68509(face), pos);
         }
      }
      var vertices = new float[points.size()];
      for (int i = 0; i < vertices.length; i++) vertices[i] = points.get(i);
      mesh = new Mesh(vertices, payload.bounds());
   }
   private static void add(List<Float> points, List<class_777> quads, class_2338 pos) {
      for (var quad : quads) for (int i = 0; i < 4; i++) {
         var v = quad.method_76648(i);
         // Slight inset avoids coplanar flicker where future blocks touch existing terrain.
         points.add(pos.method_10263() + .002f + v.x() * .996f);
         points.add(pos.method_10264() + .002f + v.y() * .996f);
         points.add(pos.method_10260() + .002f + v.z() * .996f);
      }
   }
   private static void draw(WorldRenderContext context) {
      var client = class_310.method_1551();
      if (client.field_1687 == null || !client.field_1687.method_27983().method_29177().equals(dimension)
            || System.nanoTime() - received > 10_000_000_000L) { mesh = EMPTY; return; }
      var current = mesh;
      if (current.vertices.length == 0) return;
      var camera = context.worldState().field_63082.field_63078;
      var matrices = context.matrices();
      matrices.method_22903();
      matrices.method_22904(-camera.field_1352, -camera.field_1351, -camera.field_1350);
      var entry = matrices.method_23760();
      var buffer = context.consumers().method_73477(class_12249.method_76023());
      for (int i = 0; i < current.vertices.length; i += 3)
         buffer.method_56824(entry, current.vertices[i], current.vertices[i+1], current.vertices[i+2]).method_1336(255, 255, 255, 102);
      var lines = context.consumers().method_73477(class_12249.method_76015());
      for (var b : current.bounds) {
         var min = class_2338.method_10092(b.min()); var max = class_2338.method_10092(b.max());
         float[] x = {min.method_10263() - .01f, max.method_10263() + .01f};
         float[] y = {min.method_10264() - .01f, max.method_10264() + .01f};
         float[] z = {min.method_10260() - .01f, max.method_10260() + .01f};
         for (int a = 0; a < 2; a++) for (int c = 0; c < 2; c++) {
            line(lines, entry, x[0],y[a],z[c],x[1],y[a],z[c],1,0,0);
            line(lines, entry, x[a],y[0],z[c],x[a],y[1],z[c],0,1,0);
            line(lines, entry, x[a],y[c],z[0],x[a],y[c],z[1],0,0,1);
         }
      }
      matrices.method_22909();
   }
   private static void line(class_4588 b, class_4587.class_4665 e, float x,float y,float z,float xx,float yy,float zz,float nx,float ny,float nz) {
      b.method_56824(e,x,y,z).method_1336(255,255,255,200).method_22914(nx,ny,nz).method_75298(1f);
      b.method_56824(e,xx,yy,zz).method_1336(255,255,255,200).method_22914(nx,ny,nz).method_75298(1f);
   }
}
