package net.smartcolonies.mixin;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1646;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1914;
import net.minecraft.class_2244;
import net.minecraft.class_2338;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3481;
import net.minecraft.class_3852;
import net.minecraft.class_4095;
import net.minecraft.class_4140;
import net.minecraft.class_4148;
import net.minecraft.class_4149;
import net.minecraft.class_4168;
import net.minecraft.class_4174;
import net.minecraft.class_4208;
import net.minecraft.class_4217;
import net.minecraft.class_7477;
import net.minecraft.class_7893;
import net.minecraft.class_9334;
import net.minecraft.class_4095.class_5303;
import net.minecraft.class_4153.class_4155;
import net.smartcolonies.r.x15kgoaq6i3m6i;
import net.smartcolonies.r.x19n3hxzuoqopl;
import net.smartcolonies.r.x1c1r5c6a13gtl;
import net.smartcolonies.r.x1faxfib09uh4;
import net.smartcolonies.r.x1intnp6ax9q2k;
import net.smartcolonies.r.x1ln7i1g8dapfg;
import net.smartcolonies.r.x1maxmocdanph0;
import net.smartcolonies.r.x1rxiy9mtu5b3h;
import net.smartcolonies.r.x22x83apw5u72z;
import net.smartcolonies.r.x2arpn38dclo1g;
import net.smartcolonies.r.x2cvbyh5y5day0;
import net.smartcolonies.r.x2ff3vfeunp7fx;
import net.smartcolonies.r.x2n55y2tom37h2;
import net.smartcolonies.r.x2vaj7rsm57i4n;
import net.smartcolonies.r.x2vu3n4ffebkej;
import net.smartcolonies.r.x35kkrl6uz1e8h;
import net.smartcolonies.r.x36u1fhs2z6uoi;
import net.smartcolonies.r.x37idyr79ed75m;
import net.smartcolonies.r.x3gesg5rjwqz5j;
import net.smartcolonies.r.x3iscxmqtuzq8n;
import net.smartcolonies.r.x3lus134ezrtp5;
import net.smartcolonies.r.x3m66wv1c6161h;
import net.smartcolonies.r.x6amcj7vm0jm5;
import net.smartcolonies.r.x6g7jwukdjgn4;
import net.smartcolonies.r.x9ubd81l6sw6p;
import net.smartcolonies.r.xctqfh4vgmwfd;
import net.smartcolonies.r.xf6ny39u2klct;
import net.smartcolonies.r.xlo4chqtqjj9e;
import net.smartcolonies.r.xnssk2qgda4r6;
import net.smartcolonies.r.xpmscpidlka0a;
import net.smartcolonies.r.xquptt42fexz2;
import net.smartcolonies.r.xxao083dbo7ag;
import net.smartcolonies.r.xzszqoikocxnx;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1646.class)
public abstract class x12eywdeqyc6hd {
   @Shadow
   @Final
   private static ImmutableList<class_4140<?>> field_18538;
   @Shadow
   @Final
   private static ImmutableList<class_4149<? extends class_4148<? super class_1646>>> field_18539;

   @Inject(method = "method_5992", at = @At("HEAD"), cancellable = true)
   private void xs8pnsbhdld2q(class_1657 var1, class_1268 var2, CallbackInfoReturnable<class_1269> var3) {
      class_1646 var4 = (class_1646)(Object)this;
      if (var2 == class_1268.field_5808 && !var4.method_6109() && var4.method_73183() instanceof class_3218 var5) {
         x2n55y2tom37h2.x3psqezd5jem54(var5, var4.method_24515())
            .ifPresent(
               var5x -> {
                  x1rxiy9mtu5b3h var6 = var5x.roleOf(var4.method_5667());
                  int var7 = xquptt42fexz2.xau920lx57wg3(var1.method_5998(var2));
                  if (var6 == x1rxiy9mtu5b3h.BUILDER && var7 > 0) {
                     if (xquptt42fexz2.x11oga8h5115u7(var1.method_5998(var2))) {
                        xquptt42fexz2.x3000fumbeekrm(var5.method_8503(), var1.method_5998(var2));
                        var1.method_7353(class_2561.method_43470("Этот листок уже использован. Повторный заказ невозможен."), true);
                     } else {
                        var var8 = xquptt42fexz2.x2l483kg9fflzs(var5, var5x, var1, var2);
                        var1.method_7353(
                           class_2561.method_43470(
                              var8.isPresent()
                                 ? "Строитель принял заказ. Дом: " + ((x2arpn38dclo1g)var8.get()).x3ij6gescvakv2().method_23854()
                                 : "Заказ пока не принят: нужно завершить дорогу/предыдущий заказ или найти свободный участок. Листок не использован."
                           ),
                           true
                        );
                     }

                     var3.setReturnValue(class_1269.field_5812);
                  } else if (var6 != x1rxiy9mtu5b3h.CITIZEN && !var4.method_6113() && !var4.method_18009()) {
                     x2n55y2tom37h2.x21g1rujs9r1h6(var5, var4, var6);
                     x1faxfib09uh4.x88t1bn7r4bt7(var5, var5x, var4, var6);
                     x6g7jwukdjgn4.x3qgnd0wqcbp6n(var4);
                     ((x3jzeu04kdkziv)var4).xj1jj9iwsa6et(var1);
                     var3.setReturnValue(class_1269.field_5812);
                  }
               }
            );
      }
   }

   @Inject(method = "method_18008", at = @At("TAIL"))
   private void x2mxal3pq2t9ku(class_1914 var1, CallbackInfo var2) {
      class_1646 var3 = (class_1646)(Object)this;
      if (var3.method_73183() instanceof class_3218 var4) {
         x1faxfib09uh4.x1qctxo20x2yyf(var4, var3, var1);
      }
   }

   @Inject(method = "method_28306", at = @At("HEAD"), cancellable = true)
   private void x3aj3xzpyni326(CallbackInfoReturnable<class_5303<class_1646>> var1) {
      ImmutableList var2 = ImmutableList.builder().addAll(field_18538).addAll(xxao083dbo7ag.x1eiihmuh1mj7p()).build();
      ImmutableList var3 = ImmutableList.builder().addAll(field_18539).add(x1ln7i1g8dapfg.x38iq4tihdy4v8).build();
      var1.setReturnValue(class_4095.method_28311(var2, var3));
   }

   @Inject(method = "method_5958", at = @At("HEAD"))
   private void xd52oyun3ud5a(class_3218 var1, CallbackInfo var2) {
      class_1646 var3 = (class_1646)(Object)this;
      if (!var3.method_6109() && var3.method_7231().comp_3521().method_40225(class_3852.field_17051)) {
         x2n55y2tom37h2.x3psqezd5jem54(var1, var3.method_24515()).ifPresent(var2x -> {
            x1rxiy9mtu5b3h var3x = var2x.roleOf(var3.method_5667());
            if (var3x != x1rxiy9mtu5b3h.CITIZEN) {
               x2n55y2tom37h2.x21g1rujs9r1h6(var1, var3, var3x);
            }
         });
      }
   }

   @Inject(method = "method_5958", at = @At("TAIL"))
   private void x26yz6oi4dtq7f(class_3218 var1, CallbackInfo var2) {
      class_1646 var3 = (class_1646)(Object)this;
      var var4 = x2n55y2tom37h2.x2yj2v4iudqlvb(var1, var3.method_5667()).or(() -> x2n55y2tom37h2.x3psqezd5jem54(var1, var3.method_24515()));
      x3m66wv1c6161h.x2zyakuv0vnixz(var1, var3);
      if (var4.isEmpty()) {
         var3.method_18868().method_18875(xxao083dbo7ag.x3ecoc0soedv0m);
         if (var3.method_18868()
            .method_24538()
            .filter(
               var0 -> var0.equals(xnssk2qgda4r6.x12qpuae7zsr5m)
                  || var0.equals(xnssk2qgda4r6.x2t145qayl7tz3)
                  || var0.equals(xnssk2qgda4r6.x38u24k78sk73y)
                  || var0.equals(class_4168.field_22396)
            )
            .isPresent()) {
            var3.method_18868().method_18871(var1.method_75728(), var1.method_75260(), var3.method_73189());
         }
      } else {
         x15kgoaq6i3m6i var5 = (x15kgoaq6i3m6i)var4.get();
         if (x2n55y2tom37h2.x3psqezd5jem54(var1, var3.method_24515()).map(var1x -> var1x.id().equals(var5.id())).orElse(false)) {
            var5.x1j7a15w06f1fz(var1.method_75260());
            x1intnp6ax9q2k.x3ij59k18pnfmd(var1, var5);
         }

         xf6ny39u2klct.x3nc609h8fgb5y(var1, var5, var3);
         x1rxiy9mtu5b3h var6 = var5.roleOf(var3.method_5667());
         if (x36u1fhs2z6uoi.active(var1, var5) && x36u1fhs2z6uoi.civilian(var5, var3)) {
            if (var3.method_18009()) {
               var3.method_8259(null);
            }

            if (var3.method_6113()) {
               var3.method_18400();
            }

            if (var3.method_18868().method_24538().filter(xnssk2qgda4r6.x38u24k78sk73y::equals).isEmpty()) {
               for (class_7893 var19 : List.copyOf(var3.method_18868().method_27074())) {
                  if (!(var19 instanceof x36u1fhs2z6uoi)) {
                     var19.method_18925(var1, var3, var1.method_75260());
                  }
               }

               x1c1r5c6a13gtl.x2fxoqmbs0henb(var3);
            }

            var3.method_18868().method_18875(class_4140.field_18445);
            var3.method_18868().method_24526(xnssk2qgda4r6.x38u24k78sk73y);
         } else {
            if (var3.method_18868().method_24538().filter(xnssk2qgda4r6.x38u24k78sk73y::equals).isPresent()) {
               var3.method_18868().method_18871(var1.method_75728(), var1.method_75260(), var3.method_73189());
            }

            if (!xzszqoikocxnx.x12ghbsk7tys8r(var1, var3, var5)) {
               if (var6 == x1rxiy9mtu5b3h.FOUNDER) {
                  var3.method_18868().method_18878(xxao083dbo7ag.x3ecoc0soedv0m, var6);
                  if (var3.method_18868().method_24538().filter(xnssk2qgda4r6.x2t145qayl7tz3::equals).isEmpty()) {
                     for (class_7893 var18 : List.copyOf(var3.method_18868().method_27074())) {
                        var18.method_18925(var1, var3, var1.method_75260());
                     }

                     x1c1r5c6a13gtl.x2fxoqmbs0henb(var3);
                  }

                  var3.method_18868().method_18875(class_4140.field_18445);
                  var3.method_18868().method_24526(xnssk2qgda4r6.x2t145qayl7tz3);
               } else if (var3.method_18009()) {
                  x6g7jwukdjgn4.x3qgnd0wqcbp6n(var3);
               } else {
                  if ((var1.method_75260() + var3.method_5628()) % 100L == 0L) {
                     var3.method_18868().method_18878(xxao083dbo7ag.x3ecoc0soedv0m, var6);
                  }

                  boolean var7 = var5.x3klfyffbbfab2() != Long.MIN_VALUE && var1.method_75260() - var5.x3klfyffbbfab2() <= 200L
                     || x36u1fhs2z6uoi.active(var1, var5)
                     || var6 == x1rxiy9mtu5b3h.GUARD && x37idyr79ed75m.x3kaxmxx3tadjj(var1, var5).isPresent();
                  boolean var8 = Math.floorMod(var1.method_8532(), 24000L) >= 1000L && Math.floorMod(var1.method_8532(), 24000L) < 9000L;
                  boolean var9 = Math.floorMod(var1.method_8532(), 24000L) >= 12000L;
                  boolean var10 = Math.floorMod(var1.method_8532(), 24000L) >= 9000L && Math.floorMod(var1.method_8532(), 24000L) < 12000L;
                  this.xwfvqwvn9pi6v(var1, var3, var5, var9, var7);
                  if (var3.method_6113()) {
                     var3.method_5942().method_6340();
                     var3.method_18868().method_18875(class_4140.field_18445);
                     var3.method_18799(class_243.field_1353);
                     var3.method_18868().method_24526(class_4168.field_18597);
                  } else {
                     boolean var11 = this.x19aa321fd7oor(var1, var3, var5, var10);
                     if (!x6amcj7vm0jm5.x20vp2osbvc2gk(var1, var3, var5)) {
                        boolean var12;
                        boolean var13;
                        boolean var10000;
                        label280: {
                           x19n3hxzuoqopl.xdfuvky2q520(var1, var5, var3);
                           x2vaj7rsm57i4n.x1m5qncn5p4tgc(var1, var5, var3);
                           x35kkrl6uz1e8h.x8pl4kh8avnpr(var1, var5, var3);
                           var12 = x35kkrl6uz1e8h.x3u53699ao0yas(var3, var1.method_75260());
                           var13 = var6 == x1rxiy9mtu5b3h.GUARD
                              && (
                                 var3.method_18868()
                                       .method_18904(xxao083dbo7ag.x14b5znl8ndblc)
                                       .filter(var1x -> x37idyr79ed75m.x1bj0phudo3a5m(var1x) && x37idyr79ed75m.xs8awvrrlhug9(var5, var1x))
                                       .isPresent()
                                    || x37idyr79ed75m.x3kaxmxx3tadjj(var1, var5).isPresent()
                              );
                           label205:
                           if (var6 != x1rxiy9mtu5b3h.GUARD || !var7 && !var13) {
                              if (var8) {
                                 switch (var6) {
                                    case BUILDER:
                                       if (var5.x2s37os1kht7vr()
                                          || var5.x2ad3s8aentmo4().isEmpty()
                                          || var5.x3e4ycl8cmmmgc()
                                          || !var5.x1n1a2vp01kxzf().isEmpty()
                                          || !var5.x2xqc3q79vfkw3().isEmpty()
                                          || !var5.x2f7z1gnnht5ab().x1q0gb1h69ywu7.isEmpty()) {
                                          break label205;
                                       }
                                       break;
                                    case FARMER:
                                       if (!var5.x2ad3s8aentmo4().isEmpty()) {
                                          break label205;
                                       }
                                       break;
                                    case LUMBERJACK:
                                       break label205;
                                    case MINER:
                                       if (!var5.x2ad3s8aentmo4().isEmpty()) {
                                          break label205;
                                       }
                                       break;
                                    case FISHERMAN:
                                       if (!var5.x2ad3s8aentmo4().isEmpty()) {
                                          break label205;
                                       }
                                       break;
                                    case RANCHER:
                                       if (!var5.x2ad3s8aentmo4().isEmpty()) {
                                          break label205;
                                       }
                                       break;
                                    case GUARD:
                                       if (!var5.x2ad3s8aentmo4().isEmpty()) {
                                          break label205;
                                       }
                                 }
                              }

                              var10000 = false;
                              break label280;
                           }

                           var10000 = true;
                        }

                        boolean var14 = var10000;
                        class_4168 var15 = var12 || var11
                           ? class_4168.field_18595
                           : (
                              var9 && !var7
                                 ? class_4168.field_18597
                                 : (
                                    var6 != x1rxiy9mtu5b3h.GUARD || !var13 && !var7 && !(var3.method_6032() < var3.method_6063() * 0.3F)
                                       ? (var14 ? xnssk2qgda4r6.x12qpuae7zsr5m : null)
                                       : class_4168.field_22396
                                 )
                           );
                        if (var15 != null) {
                           var3.method_18868().method_24526(var15);
                        } else if (var3.method_18868()
                           .method_24538()
                           .filter(
                              var0 -> var0.equals(class_4168.field_22396)
                                 || var0.equals(xnssk2qgda4r6.x12qpuae7zsr5m)
                                 || var0.equals(xnssk2qgda4r6.x2t145qayl7tz3)
                           )
                           .isPresent()) {
                           var3.method_18868().method_18871(var1.method_75728(), var1.method_75260(), var3.method_73189());
                        }

                        if (var6 == x1rxiy9mtu5b3h.GUARD
                           && var8
                           && !var7
                           && (var1.method_75260() + var3.method_5628()) % 40L == 0L
                           && var3.method_18868().method_27074().stream().noneMatch(var0 -> var0 instanceof x9ubd81l6sw6p)) {
                           x2ff3vfeunp7fx.patrol(var1, var5, var3);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private boolean x19aa321fd7oor(class_3218 var1, class_1646 var2, x15kgoaq6i3m6i var3, boolean var4) {
      int var5 = x1ukapw8ymk7ox(var2);
      boolean var6 = !var2.method_6109() && var2.method_5618() == 0;
      boolean var7 = x1pbipaexrd92u(var2);
      boolean var8 = var4 && (!var7 || var6 && var5 < 12);
      if ((var1.method_75260() + var2.method_5628()) % 20L != 0L) {
         return var8;
      } else {
         if (var2.method_6032() < var2.method_6063() && (var1.method_75260() + var2.method_5628()) % 40L == 0L) {
            for (int var9 = 0; var9 < var2.method_35199().method_5439(); var9++) {
               class_1799 var10 = var2.method_35199().method_5438(var9);
               class_4174 var11 = (class_4174)var10.method_58694(class_9334.field_50075);
               if (var11 != null) {
                  var10.method_7934(1);
                  var2.method_6025(Math.max(2.0F, var11.comp_2491() * 1.5F));
                  var2.method_6104(class_1268.field_5808);
                  return var8;
               }
            }

            x2n55y2tom37h2.x1falh21nhy4yk(var1, var3, var2.method_24515()).ifPresent(var3x -> {
               if (var2.method_24515().method_10262(var3x) <= 9.0 && var3.cachedFood() > var3.population() * 12) {
                  x2n55y2tom37h2.x3lxwdirnn77qc(var1, var3, var0x -> var0x.method_58694(class_9334.field_50075) != null).ifPresent(var1xx -> {
                     class_4174 var2xx = (class_4174)var1xx.method_58694(class_9334.field_50075);
                     var2.method_6025(var2xx == null ? 2.0F : Math.max(2.0F, var2xx.comp_2491() * 1.5F));
                     var2.method_6104(class_1268.field_5808);
                  });
               }
            });
         }

         if (!var8) {
            return false;
         } else {
            var var14 = x2n55y2tom37h2.x1falh21nhy4yk(var1, var3, var2.method_24515());
            if (var14.isEmpty()) {
               return true;
            } else if (var2.method_24515().method_10262((class_2382)var14.get()) > 9.0) {
               var2.method_18868().method_18878(xxao083dbo7ag.x3o547clcv9kaw, (class_2338)var14.get());
               x1c1r5c6a13gtl.x2wzylgizxltef(var1, var2, (class_2338)var14.get(), 0.85);
               return true;
            } else {
               int var15 = var6 ? 12 : (var7 ? 0 : 1);

               for (int var16 = 0; var16 < 12 && var5 < var15; var16++) {
                  var var12 = x2n55y2tom37h2.x3lxwdirnn77qc(var1, var3, var0 -> var0.method_58694(class_9334.field_50075) != null);
                  if (var12.isEmpty()) {
                     break;
                  }

                  var5 += x2n55y2tom37h2.xhahxgpa08yc9((class_1799)var12.get());
                  class_1799 var13 = var2.method_35199().method_5491((class_1799)var12.get());
                  if (!var13.method_7960()) {
                     x2n55y2tom37h2.x2cl1crde2rnxy(var1, var3, var13);
                  }
               }

               if (!x1pbipaexrd92u(var2)) {
                  x2n55y2tom37h2.x3lxwdirnn77qc(var1, var3, var0 -> var0.method_58694(class_9334.field_50075) != null).ifPresent(var3x -> {
                     class_1799 var4x = var2.method_35199().method_5491(var3x);
                     if (!var4x.method_7960()) {
                        x2n55y2tom37h2.x2cl1crde2rnxy(var1, var3, var4x);
                     }
                  });
               }

               var2.method_18868().method_18875(xxao083dbo7ag.x3o547clcv9kaw);
               return !x1pbipaexrd92u(var2) || var6 && x1ukapw8ymk7ox(var2) < 12;
            }
         }
      }
   }

   private static boolean x1pbipaexrd92u(class_1646 var0) {
      for (int var1 = 0; var1 < var0.method_35199().method_5439(); var1++) {
         if (var0.method_35199().method_5438(var1).method_58694(class_9334.field_50075) != null) {
            return true;
         }
      }

      return false;
   }

   private static int x1ukapw8ymk7ox(class_1646 var0) {
      int var1 = 0;

      for (int var2 = 0; var2 < var0.method_35199().method_5439(); var2++) {
         class_1799 var3 = var0.method_35199().method_5438(var2);
         var1 += x2n55y2tom37h2.xhahxgpa08yc9(var3) * var3.method_7947();
      }

      return var1;
   }

   @Inject(method = "method_19174", at = @At("TAIL"))
   private void x2hkdgr70am6hi(class_4095<class_1646> var1, CallbackInfo var2) {
      var1.method_18881(xnssk2qgda4r6.x2t145qayl7tz3, ImmutableList.of());
      var1.method_18881(xnssk2qgda4r6.x38u24k78sk73y, ImmutableList.of(Pair.of(0, new x36u1fhs2z6uoi())));
      var1.method_18881(class_4168.field_22396, ImmutableList.of(Pair.of(0, new x2ff3vfeunp7fx())));
      var1.method_18881(
         xnssk2qgda4r6.x12qpuae7zsr5m,
         ImmutableList.of(
            Pair.of(0, new xlo4chqtqjj9e()),
            Pair.of(0, new x2vu3n4ffebkej()),
            Pair.of(0, new x3iscxmqtuzq8n()),
            Pair.of(1, new x2cvbyh5y5day0()),
            Pair.of(1, new x22x83apw5u72z()),
            Pair.of(1, new x9ubd81l6sw6p()),
            Pair.of(2, new xpmscpidlka0a()),
            Pair.of(3, new x3lus134ezrtp5()),
            Pair.of(4, new x3gesg5rjwqz5j()),
            Pair.of(5, new xctqfh4vgmwfd()),
            Pair.of(6, new x1maxmocdanph0())
         )
      );
   }

   private void xwfvqwvn9pi6v(class_3218 var1, class_1646 var2, x15kgoaq6i3m6i var3, boolean var4, boolean var5) {
      if (var4 && !var5) {
         for (class_7893 var7 : List.copyOf(var2.method_18868().method_27074())) {
            if (var7.getClass().getName().startsWith("net.smartcolonies.") || var7 instanceof class_4217) {
               var7.method_18925(var1, var2, var1.method_75260());
            }
         }

         if (var2.method_6113()) {
            var2.method_5942().method_6340();
            var2.method_18868().method_18875(class_4140.field_18445);
         } else if ((var1.method_75260() + var2.method_5628()) % 20L == 0L) {
            var var9 = var2.method_18868()
               .method_18904(class_4140.field_18438)
               .filter(var1x -> var1x.comp_2207().equals(var1.method_27983()))
               .<class_2338>map(class_4208::comp_2208)
               .filter(var2x -> xi6qq1j9vjyfk(var1, var2x, var2));
            var var10 = var9.isPresent()
               ? var9
               : var1.method_19494()
                  .method_21647(
                     var0 -> var0.method_40225(class_7477.field_39291),
                     var2x -> xi6qq1j9vjyfk(var1, var2x, var2),
                     var3.core(),
                     var3.radius(),
                     class_4155.field_18489
                  )
                  .min(Comparator.comparingDouble(var1x -> var1x.method_10262(var2.method_24515())));
            if (!var10.isEmpty()) {
               class_2338 var8 = (class_2338)var10.get();
               var2.method_18868().method_18878(class_4140.field_18438, class_4208.method_19443(var1.method_27983(), var8));
               if (var2.method_24515().method_10262(var8) <= 4.0) {
                  var2.method_5942().method_6340();
                  var2.method_18403(var8);
               } else {
                  x1c1r5c6a13gtl.x1lrdp6728c3ml(var1, var2, var8, 0.85);
               }
            }
         }
      } else {
         if (var2.method_6113()) {
            var2.method_18400();
         }
      }
   }

   private static boolean xi6qq1j9vjyfk(class_3218 var0, class_2338 var1, class_1646 var2) {
      if (!var0.method_14178().method_12123(var1.method_10263() >> 4, var1.method_10260() >> 4)) {
         return false;
      } else {
         class_2680 var3 = var0.method_8320(var1);
         if (!var3.method_26164(class_3481.field_16443)) {
            return false;
         } else {
            return var3.method_28498(class_2244.field_9968) && var3.method_11654(class_2244.field_9968)
               ? var2.method_18398().<Boolean>map(var1::equals).orElse(false)
               : true;
         }
      }
   }
}
