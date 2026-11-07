package net.smartaul.village;

import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.class_10730;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1428;
import net.minecraft.class_1429;
import net.minecraft.class_1452;
import net.minecraft.class_1463;
import net.minecraft.class_1472;
import net.minecraft.class_1542;
import net.minecraft.class_1646;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2349;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_2398;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3341;
import net.minecraft.class_3481;
import net.minecraft.class_3489;
import net.minecraft.class_4097;
import net.minecraft.class_4140;
import net.minecraft.class_4141;
import net.minecraft.class_6053;
import net.minecraft.class_7923;
import net.minecraft.class_2902.class_2903;

public final class RancherTask extends class_4097<class_1646> {
   public static final int x2p4azrgz36t1c = 5;
   private static final int x34264qhqiplto = 10;
   public static final int x361h5d7gkiq12 = 32;
   private final Map<UUID, Long> x2nroh3xvht8t9 = new HashMap<>();
   private final Map<UUID, Long> x1tnak5qfia21t = new HashMap<>();
   private Colony x9b03bdckmlwm;
   private RancherPhase x3m757n03ci4pu;
   private class_2338 x1bm3tnyu1jatt;
   private class_1429 x3f72o6zqrgoi5;
   private class_1792 xmj689j1i2yoe;
   private long x2ch2ssr886gm8;
   private long x2numq6ar051rx;
   private long x1bkk3ozc9rnb9;
   private long x2ptpwjdc895we;
   private double x10nnii6m4pbue;
   private int x2ueth4fvnxw81;
   private int xhdr6wfedevn7;
   private RancherPhase x2owmu2l8oehuk;
   private boolean x17tcufg7yn81;
   private boolean x3213rjbn8ppz4;

   public RancherTask() {
      super(ImmutableMap.of(class_4140.field_18445, class_4141.field_18458), 12000);
   }

   private static long xd01akzx86p4c(class_3218 var0) {
      return var0.method_75260();
   }

   protected boolean shouldRun(class_3218 var1, class_1646 var2) {
      if (!var2.method_6109() && !var2.method_6113() && !var2.method_18009() && xd01akzx86p4c(var1) >= this.x2numq6ar051rx) {
         this.x9b03bdckmlwm = ColonyManager.x3psqezd5jem54(var1, var2.method_24515())
            .filter(var1x -> var1x.roleOf(var2.method_5667()) == ColonyRole.RANCHER && !var1x.x2ad3s8aentmo4().isEmpty())
            .orElse(null);
         return this.x9b03bdckmlwm != null;
      } else {
         return false;
      }
   }

   protected void run(class_3218 var1, class_1646 var2, long var3) {
      ActionTrace.worker(var2, "task_start", "RancherTask", false);
      long var5 = xd01akzx86p4c(var1);
      this.x3m757n03ci4pu = RancherPhase.SUPPLY;
      this.x1bm3tnyu1jatt = this.x9b03bdckmlwm.x329rgb9lpsari().orElse(null);
      this.x3f72o6zqrgoi5 = null;
      this.xmj689j1i2yoe = null;
      this.x2ueth4fvnxw81 = 0;
      this.xhdr6wfedevn7 = 0;
      this.x2owmu2l8oehuk = null;
      this.x3213rjbn8ppz4 = false;
      this.x2ch2ssr886gm8 = this.x1bkk3ozc9rnb9 = var5;
      this.x1tnak5qfia21t.entrySet().removeIf(var2x -> var2x.getValue() <= var5);
   }

   protected boolean shouldKeepRunning(class_3218 var1, class_1646 var2, long var3) {
      return !var2.method_6113()
         && !var2.method_18009()
         && this.x9b03bdckmlwm != null
         && this.x9b03bdckmlwm.roleOf(var2.method_5667()) == ColonyRole.RANCHER
         && !this.x3213rjbn8ppz4;
   }

   protected void keepRunning(class_3218 var1, class_1646 var2, long var3) {
      var3 = xd01akzx86p4c(var1);
      if (this.x3m757n03ci4pu != this.x2owmu2l8oehuk) {
         this.x2owmu2l8oehuk = this.x3m757n03ci4pu;
         this.xhdr6wfedevn7 = 0;
      } else {
         this.xhdr6wfedevn7++;
      }

      if (var3 >= this.x2ch2ssr886gm8) {
         this.x2ch2ssr886gm8 = var3 + 5L;
         if (this.x3m757n03ci4pu == RancherPhase.SUPPLY || this.x3m757n03ci4pu == RancherPhase.RETURN) {
            this.x3vp1wn6x4mhdg(var1, var2, var3);
         } else if (this.x3m757n03ci4pu == RancherPhase.PEN) {
            this.x1hxapso7q6mpf(var1, var2, var3);
         } else if (this.x2ueth4fvnxw81 < 12 && x26v4oc4f99cpr(var2) < 16) {
            this.x1i4hyghtu35ce(var1, var2);
            if (this.x3m757n03ci4pu == RancherPhase.SEARCH) {
               this.x1jph62phzfvq9(var1, var2, var3);
            } else if (this.x3f72o6zqrgoi5 != null && this.x3f72o6zqrgoi5.method_5805() && x2rzbu6s47zw2x(this.x3f72o6zqrgoi5)) {
               var2.method_18868().method_18878(ColonyMemories.x2ppem7wipzctm, this.x3f72o6zqrgoi5.method_24515());
               if (this.x3m757n03ci4pu == RancherPhase.HERD) {
                  this.x2w3c3qpcb0ux6(var1, var2, var3);
               } else if (this.x2745j5ue4rw4q(this.x3f72o6zqrgoi5) && !this.x2745j5ue4rw4q(var2)) {
                  class_2338 var5 = this.x3u1km6otsxwtp();
                  if (this.xhdr6wfedevn7 > 400) {
                     this.x3f72o6zqrgoi5 = null;
                     this.x3m757n03ci4pu = RancherPhase.RETURN;
                     this.x1bkk3ozc9rnb9 = var3;
                  } else {
                     if (var2.method_24515().method_10262(var5) > 9.0) {
                        this.x3v731fax5g1nx(var1);
                        this.x1yqub9v49uk07(var1, var2, var5.method_10076(2), 0.72);
                     } else {
                        this.x1f00g882945e(var1, this.x3f72o6zqrgoi5);
                        this.x3p0xf9wskmc24(var1);
                        this.x1yqub9v49uk07(var1, var2, this.x1bm3tnyu1jatt, 0.72);
                     }
                  }
               } else if (var2.method_5858(this.x3f72o6zqrgoi5) > 9.0) {
                  this.x1yqub9v49uk07(var1, var2, this.x3f72o6zqrgoi5.method_24515(), 0.85);
               } else {
                  this.xv7bmx8jq0onf(var1, var2, this.x3f72o6zqrgoi5, var3);
                  this.x3v731fax5g1nx(var1);
                  this.x3f72o6zqrgoi5 = null;
                  this.x3m757n03ci4pu = RancherPhase.SEARCH;
                  this.x1bkk3ozc9rnb9 = var3;
               }
            } else {
               this.x3m757n03ci4pu = RancherPhase.SEARCH;
            }
         } else {
            this.x3m757n03ci4pu = RancherPhase.RETURN;
            this.x1bkk3ozc9rnb9 = var3;
            this.x3f72o6zqrgoi5 = null;
         }
      }
   }

   private void x3vp1wn6x4mhdg(class_3218 var1, class_1646 var2, long var3) {
      if (this.x1bm3tnyu1jatt != null && this.x2745j5ue4rw4q(var2)) {
         this.x1f00g882945e(var1, null);
         this.x3p0xf9wskmc24(var1);
         class_2338 var12 = this.x3u1km6otsxwtp().method_10095();
         this.x1yqub9v49uk07(var1, var2, var12.method_10095(), 0.7);
         if (var3 - this.x1bkk3ozc9rnb9 > 400L) {
            this.x3213rjbn8ppz4 = true;
            this.x2numq6ar051rx = var3 + 100L;
         }
      } else {
         this.x3v731fax5g1nx(var1);
         var var5 = ColonyManager.x3jrfw2d3yykeu(var1, this.x9b03bdckmlwm, var2, HousePurpose.ANIMAL);
         if (var5.isEmpty()) {
            this.x3213rjbn8ppz4 = true;
         } else {
            var2.method_18868().method_18878(ColonyMemories.x3o547clcv9kaw, (class_2338)var5.get());
            if (var2.method_24515().method_10262((class_2382)var5.get()) > 9.0) {
               this.x1yqub9v49uk07(var1, var2, (class_2338)var5.get(), 0.85);
            } else {
               ColonyManager.x1asz375yez9sw(var1, this.x9b03bdckmlwm, var0 -> var0.method_31574(class_1802.field_8803), 32);
               this.x2kdf6zxkbv4uj(var1, var2);
               OfflineDevelopment.x2m8lyhoez9v7c(var1, this.x9b03bdckmlwm);
               OfflineDevelopment.x3tihjtlnbfyrs(var2.method_35199(), Math.max(0, 4 - OfflineDevelopment.x31lz6msk67uxc(var1, this.x9b03bdckmlwm)));

               for (int var6 = 0; var6 < var2.method_35199().method_5439(); var6++) {
                  if (x3e0xb3lfomiyr(var2.method_35199().method_5438(var6))) {
                     WorkDiagnostics.x25cotfj45r525(var1, this.x9b03bdckmlwm, var2, var2.method_35199().method_5438(var6));
                  }
               }

               int var13 = 2;

               for (int var7 = 0; var7 < var2.method_35199().method_5439(); var7++) {
                  class_1799 var8 = var2.method_35199().method_5438(var7);
                  if (var8.method_31574(class_1802.field_8550)) {
                     int var9 = Math.min(var13, var8.method_7947());
                     var13 -= var9;
                     int var10 = var8.method_7947() - var9;
                     if (var10 != 0) {
                        class_1799 var11 = var8.method_46651(var10);
                        WorkDiagnostics.x25cotfj45r525(var1, this.x9b03bdckmlwm, var2, var11);
                        var8.method_7934(var10 - var11.method_7947());
                     }
                  }
               }

               this.x27sd5416kpqqj(var1, var2);
               this.x33mnq7ijq2l5z(var1, var2);
               int var14 = Math.max(0, 4 - OfflineDevelopment.x2m95xmyunf3vb(var1, this.x9b03bdckmlwm));
               if (var14 > 0) {
                  this.x31djqoo9oci6(var1, var2, Math.min(2, var14));
               }

               this.x12fl4tcpl6f1x(var1, var2, class_1802.field_8861, 8);
               this.x12fl4tcpl6f1x(var1, var2, class_1802.field_8317, 8);
               this.x12fl4tcpl6f1x(var1, var2, class_1802.field_8179, 8);
               if (this.x3m757n03ci4pu == RancherPhase.RETURN) {
                  this.x3213rjbn8ppz4 = true;
                  this.x2numq6ar051rx = var3 + 40L;
               } else {
                  this.x3m757n03ci4pu = RancherPhase.PEN;
                  this.x1bkk3ozc9rnb9 = var3;
               }
            }
         }
      }
   }

   private void x1hxapso7q6mpf(class_3218 var1, class_1646 var2, long var3) {
      if (this.x1bm3tnyu1jatt == null) {
         this.x1bm3tnyu1jatt = this.x2hjrd57df3dbn(var1).orElse(null);
         if (this.x1bm3tnyu1jatt == null) {
            this.x3213rjbn8ppz4 = true;
            this.x2numq6ar051rx = var3 + 400L;
            return;
         }

         this.x9b03bdckmlwm.x146t7j3h7t042(this.x1bm3tnyu1jatt);
         ColonyManager.xchl1k1tnh3w8(var1).method_80();
      }

      var var5 = this.x2upxz7dqb7edt(var1, this.x1bm3tnyu1jatt);
      if (!var5.isEmpty()) {
         var5.removeIf(var1x -> !var1.method_8320(var1x).method_45474() || !WorldEditPolicy.x13f7be5vlapn7(var1, var1x));
         if (var5.isEmpty()) {
            WorkDiagnostics.x1qd0mutwsr3sc(var2, "периметр загона заблокирован");
            this.x3213rjbn8ppz4 = true;
            this.x2numq6ar051rx = var3 + 600L;
         } else if (!ColonyManager.x22hjebj4vmztt(var1, this.x9b03bdckmlwm, var0 -> var0.method_31573(class_3489.field_15539), 1)) {
            this.x3213rjbn8ppz4 = true;
            this.x2numq6ar051rx = var3 + 200L;
         } else {
            int var10 = 0;

            for (int var11 = 0; var11 < Math.min(3, var5.size()); var11++) {
               class_2338 var8 = (class_2338)var5.get(var11);
               class_2680 var9 = var8.equals(this.x3u1km6otsxwtp())
                  ? (class_2680)class_2246.field_10188.method_9564().method_11657(class_2349.field_11177, class_2350.field_11035)
                  : class_2246.field_10620.method_9564();
               if (var1.method_8320(var8).method_45474() && WorldEditPolicy.x1bq0f8n53gtob(var1, var8, var9, 3)) {
                  var10++;
               }
            }

            if (var10 == 0) {
               VillagerInventory.xqe8twcjwxn55(var1, this.x9b03bdckmlwm, var2, new class_1799(class_1802.field_8583));
            } else {
               WorkDiagnostics.x2t98zlq4lu49t(var2, "загон", var10);
            }

            var2.method_6104(class_1268.field_5808);
            this.x2ueth4fvnxw81++;
            this.x2ch2ssr886gm8 = var3 + 8L;
         }
      } else {
         var var6 = this.x2ujvd9g07uaij(var1);
         if (var6.isEmpty()) {
            this.x3v731fax5g1nx(var1);
            this.x3m757n03ci4pu = RancherPhase.SEARCH;
            this.x1bkk3ozc9rnb9 = var3;
            this.x2ueth4fvnxw81 = 0;
         } else {
            for (int var7 = 0; var7 < Math.min(3, var6.size()); var7++) {
               WorldEditPolicy.x231if4rvsvptj(var1, (class_2338)var6.get(var7), false, var2);
            }

            var2.method_6104(class_1268.field_5808);
            this.x2ch2ssr886gm8 = var3 + 8L;
         }
      }
   }

   private void x1jph62phzfvq9(class_3218 var1, class_1646 var2, long var3) {
      var var5 = this.x3q7ywqh31h55d(var1);
      var var6 = var5.stream()
         .filter(var1x -> !this.x2745j5ue4rw4q(var1x))
         .filter(var3x -> this.x1tnak5qfia21t.getOrDefault(var3x.method_5667(), 0L) <= var3)
         .filter(var2x -> this.xpn2n0o78r82j(var5, var2x) < 10)
         .filter(var1x -> x1zya4i1rpwuhr(var1x) != null && xq6j4f862i6km(var2, x1zya4i1rpwuhr(var1x)))
         .min(Comparator.<class_1429>comparingInt(var0 -> var0 instanceof class_1472 ? 0 : 1).thenComparingDouble(var2::method_5858));
      if (var6.isPresent()) {
         this.x2fj0r64lb2ked(var1, var2, (class_1429)var6.get(), var3);
      } else {
         var var7 = var5.stream()
            .filter(var5x -> this.x2745j5ue4rw4q(var5x) && this.xgwotc5f7rrat(var1, var2, var5x, var3))
            .min(Comparator.comparingDouble(var2::method_5858));
         if (var7.isPresent()) {
            this.x3f72o6zqrgoi5 = (class_1429)var7.get();
            this.x3m757n03ci4pu = RancherPhase.CARE;
            this.x1bkk3ozc9rnb9 = var3;
         } else if (this.x1gx3m3f1j5h9e(var1, var2, var5, var3)) {
            this.x2ueth4fvnxw81++;
            WorkDiagnostics.x2t98zlq4lu49t(var2, "разведение животных", 1);
            this.x2ch2ssr886gm8 = var3 + 40L;
         } else if (x26v4oc4f99cpr(var2) > 0) {
            this.x3m757n03ci4pu = RancherPhase.RETURN;
            this.x1bkk3ozc9rnb9 = var3;
         } else {
            this.x3m757n03ci4pu = RancherPhase.RETURN;
            this.x1bkk3ozc9rnb9 = var3;
         }
      }
   }

   private void x2w3c3qpcb0ux6(class_3218 var1, class_1646 var2, long var3) {
      if (this.x2745j5ue4rw4q(this.x3f72o6zqrgoi5) && !this.x3f72o6zqrgoi5.method_5829().method_994(new class_238(this.x3u1km6otsxwtp()).method_1014(0.5))) {
         this.x3dxvfalesg3gp(var1, var2, true);
      } else if (!this.xmdm1vdfbqp32(var1, var2)) {
         this.x1tnak5qfia21t.put(this.x3f72o6zqrgoi5.method_5667(), var3 + 400L);
         this.x3dxvfalesg3gp(var1, var2, false);
      } else {
         FollowRancherGoal.follow(this.x3f72o6zqrgoi5, var2, this.xmj689j1i2yoe);
         if (!this.x17tcufg7yn81 && this.x2745j5ue4rw4q(var2)) {
            this.x1f00g882945e(var1, this.x3f72o6zqrgoi5);
            this.x3p0xf9wskmc24(var1);
            this.x1yqub9v49uk07(var1, var2, this.x3u1km6otsxwtp().method_10076(2), 0.65);
         } else {
            if (!this.x17tcufg7yn81) {
               this.x17tcufg7yn81 = true;
               this.x3v731fax5g1nx(var1);
            }

            double var5 = this.x3f72o6zqrgoi5.method_24515().method_10262(this.x1bm3tnyu1jatt);
            if (var5 + 1.0 < this.x10nnii6m4pbue) {
               this.x10nnii6m4pbue = var5;
               this.x2ptpwjdc895we = var3;
            }

            if (this.xhdr6wfedevn7 <= 2000 && var3 - this.x2ptpwjdc895we <= 600L) {
               var2.method_5988().method_6226(this.x3f72o6zqrgoi5, 30.0F, 30.0F);
               this.x3f72o6zqrgoi5.method_5988().method_6226(var2, 30.0F, 30.0F);
               if (var2.method_5858(this.x3f72o6zqrgoi5) > 36.0) {
                  this.x3v731fax5g1nx(var1);
                  this.x1yqub9v49uk07(var1, var2, this.x3f72o6zqrgoi5.method_24515(), 0.7);
               } else if (var2.method_5858(this.x3f72o6zqrgoi5) > 16.0) {
                  var2.method_5942().method_6340();
               } else {
                  class_2338 var7 = this.x3u1km6otsxwtp();
                  if (var2.method_24515().method_10262(var7) > 9.0) {
                     this.x3v731fax5g1nx(var1);
                     this.x1yqub9v49uk07(var1, var2, var7.method_10076(2), 0.62);
                  } else {
                     this.x1f00g882945e(var1, this.x3f72o6zqrgoi5);
                     this.x3p0xf9wskmc24(var1);
                     this.x1yqub9v49uk07(var1, var2, this.x1bm3tnyu1jatt.method_10077(2), 0.62);
                  }
               }
            } else {
               WorkDiagnostics.x1qd0mutwsr3sc(var2, "животному недоступен путь в загон: выбран другой маршрут/животное");
               this.x1tnak5qfia21t.put(this.x3f72o6zqrgoi5.method_5667(), var3 + 2400L);
               this.x3dxvfalesg3gp(var1, var2, false);
            }
         }
      }
   }

   private void x2fj0r64lb2ked(class_3218 var1, class_1646 var2, class_1429 var3, long var4) {
      class_1792 var6 = x1zya4i1rpwuhr(var3);
      this.x2vvpgkqtpg958(var1, var2);
      this.xmj689j1i2yoe = var6;
      if (!this.xmdm1vdfbqp32(var1, var2)) {
         this.xmj689j1i2yoe = null;
         this.x3m757n03ci4pu = RancherPhase.SEARCH;
      } else {
         this.x3f72o6zqrgoi5 = var3;
         this.x3m757n03ci4pu = RancherPhase.HERD;
         this.x1bkk3ozc9rnb9 = this.x2ptpwjdc895we = var4;
         this.x17tcufg7yn81 = !this.x2745j5ue4rw4q(var2);
         this.x10nnii6m4pbue = var3.method_24515().method_10262(this.x1bm3tnyu1jatt);
      }
   }

   private void x3dxvfalesg3gp(class_3218 var1, class_1646 var2, boolean var3) {
      if (this.x3f72o6zqrgoi5 != null) {
         FollowRancherGoal.release(this.x3f72o6zqrgoi5);
         this.x3f72o6zqrgoi5.method_5942().method_6340();
      }

      if (var3) {
         this.x1f00g882945e(var1, null);
      }

      this.x2vvpgkqtpg958(var1, var2);
      this.x3v731fax5g1nx(var1);
      this.x3f72o6zqrgoi5 = null;
      if (var3) {
         this.x2ueth4fvnxw81++;
         WorkDiagnostics.x2t98zlq4lu49t(var2, "животное приведено в загон", 1);
         this.x3m757n03ci4pu = RancherPhase.SEARCH;
      } else {
         this.x3m757n03ci4pu = RancherPhase.RETURN;
         this.x1bkk3ozc9rnb9 = var1.method_75260();
      }
   }

   private void xv7bmx8jq0onf(class_3218 var1, class_1646 var2, class_1429 var3, long var4) {
      if (var3 instanceof class_1472 var6 && var6.method_27072() && xq6j4f862i6km(var2, class_1802.field_8868)) {
         var6.method_6635(true);
         this.x2xg4t4fgfn92z(var1, var2, new class_1799(x3k9mdozxqhn1u(var6), 1 + var1.field_9229.method_43048(3)));
         this.x2nroh3xvht8t9.put(var3.method_5667(), var4 + 1200L);
         this.x2ueth4fvnxw81++;
         WorkDiagnostics.x2t98zlq4lu49t(var2, "стрижка", 1);
         var2.method_6104(class_1268.field_5808);
      } else if (var3 instanceof class_1428) {
         this.x2xg4t4fgfn92z(var1, var2, new class_1799(class_1802.field_8803));
         this.x2nroh3xvht8t9.put(var3.method_5667(), var4 + 1200L);
         this.x2ueth4fvnxw81++;
         WorkDiagnostics.x2t98zlq4lu49t(var2, "сбор яиц", 1);
         var2.method_6104(class_1268.field_5808);
      } else {
         if ((var3 instanceof class_10730 || var3 instanceof class_6053)
            && OfflineDevelopment.x2m95xmyunf3vb(var1, this.x9b03bdckmlwm) < 4
            && x165qssxeku6kz(var2, class_1802.field_8550)) {
            this.x2xg4t4fgfn92z(var1, var2, new class_1799(class_1802.field_8103));
            this.x2nroh3xvht8t9.put(var3.method_5667(), var4 + 2400L);
            this.x2ueth4fvnxw81++;
            WorkDiagnostics.x2t98zlq4lu49t(var2, "дойка", 1);
            var2.method_6104(class_1268.field_5808);
         }
      }
   }

   private boolean x1gx3m3f1j5h9e(class_3218 var1, class_1646 var2, List<class_1429> var3, long var4) {
      if (this.x1yv8cepr980a2(var3) >= 40) {
         return false;
      } else {
         for (int var6 = 0; var6 < var3.size(); var6++) {
            class_1429 var7 = (class_1429)var3.get(var6);
            if (this.x2745j5ue4rw4q(var7) && !var7.method_6109() && var7.method_5618() == 0) {
               class_1792 var8 = x1zya4i1rpwuhr(var7);
               if (var8 != null) {
                  for (int var9 = var6 + 1; var9 < var3.size(); var9++) {
                     class_1429 var10 = (class_1429)var3.get(var9);
                     if (this.x2745j5ue4rw4q(var10)
                        && !var10.method_6109()
                        && var10.method_5618() == 0
                        && var7.method_5864() == var10.method_5864()
                        && this.x2idhnuuiroo7r(var1, var2, var8, 2)) {
                        var7.method_6476(600);
                        var10.method_6476(600);
                        var7.method_24650(var1, var10);
                        var1.method_65096(
                           class_2398.field_11201,
                           this.x1bm3tnyu1jatt.method_10263() + 0.5,
                           this.x1bm3tnyu1jatt.method_10264() + 1,
                           this.x1bm3tnyu1jatt.method_10260() + 0.5,
                           8,
                           2.0,
                           0.5,
                           2.0,
                           0.0
                        );
                        return true;
                     }
                  }
               }
            }
         }

         return false;
      }
   }

   private boolean xgwotc5f7rrat(class_3218 var1, class_1646 var2, class_1429 var3, long var4) {
      if (var3.method_6109() || this.x2nroh3xvht8t9.getOrDefault(var3.method_5667(), 0L) > var4) {
         return false;
      } else if (var3 instanceof class_1472 var6) {
         return var6.method_27072() && xq6j4f862i6km(var2, class_1802.field_8868);
      } else {
         return var3 instanceof class_1428
            ? this.x8bmwjivt7kr4(var1, var2) < 32
            : (var3 instanceof class_10730 || var3 instanceof class_6053)
               && xq6j4f862i6km(var2, class_1802.field_8550)
               && OfflineDevelopment.x2m95xmyunf3vb(var1, this.x9b03bdckmlwm) < 4;
      }
   }

   private void x1i4hyghtu35ce(class_3218 var1, class_1646 var2) {
      if (this.x1bm3tnyu1jatt != null) {
         for (class_1542 var4 : var1.method_8390(class_1542.class, new class_238(this.x1bm3tnyu1jatt).method_1009(6.0, 2.0, 6.0), class_1297::method_5805)) {
            if (x3e0xb3lfomiyr(var4.method_6983()) && VillagerEquipment.x2t0sm1vfzey0p(var1, this.x9b03bdckmlwm, var2, var4, ColonyRole.RANCHER)) {
               int var5 = var4.method_6983().method_31574(class_1802.field_8803) ? Math.max(0, 32 - this.x8bmwjivt7kr4(var1, var2)) : Integer.MAX_VALUE;
               VillagerEquipment.x105bgml33wsy(var1, this.x9b03bdckmlwm, var2, var4, ColonyRole.RANCHER, var5);
            }
         }
      }
   }

   private Optional<class_2338> x2hjrd57df3dbn(class_3218 var1) {
      for (int var2 = 16; var2 <= 40; var2++) {
         for (int var3 = 0; var3 < var2 * 8; var3++) {
            int var4 = var3 % (var2 * 8);
            int var5;
            int var6;
            if (var4 < var2 * 2) {
               var5 = -var2 + var4;
               var6 = -var2;
            } else if (var4 < var2 * 4) {
               var5 = var2;
               var6 = -var2 + (var4 - var2 * 2);
            } else if (var4 < var2 * 6) {
               var5 = var2 - (var4 - var2 * 4);
               var6 = var2;
            } else {
               var5 = -var2;
               var6 = var2 - (var4 - var2 * 6);
            }

            int var7 = this.x9b03bdckmlwm.core().method_10263() + var5;
            int var8 = this.x9b03bdckmlwm.core().method_10260() + var6;
            if (var1.method_14178().method_12123(var7 >> 4, var8 >> 4)) {
               class_2338 var9 = new class_2338(var7, var1.method_8624(class_2903.field_13203, var7, var8), var8);
               if (this.x29zcp8wrpahei(var1, var9)) {
                  return Optional.of(var9);
               }
            }
         }
      }

      return Optional.empty();
   }

   private boolean x29zcp8wrpahei(class_3218 var1, class_2338 var2) {
      for (class_2338 var4 : class_2338.method_10097(var2.method_10069(-5, 0, -5), var2.method_10069(5, 1, 5))) {
         if (var1.method_14178().method_12123(var4.method_10263() >> 4, var4.method_10260() >> 4)
            && !ProtectedLand.x3gqyq7yie9jxr(var1, var4)
            && !this.x9b03bdckmlwm.xhu9xvlie05r6(var4, 2)) {
            if ((var1.method_8320(var4).method_26215() || var1.method_8320(var4).method_45474()) && var1.method_8321(var4) == null) {
               class_2680 var5 = var1.method_8320(new class_2338(var4.method_10263(), var2.method_10264() - 1, var4.method_10260()));
               if (var5.method_26206(var1, new class_2338(var4.method_10263(), var2.method_10264() - 1, var4.method_10260()), class_2350.field_11036)
                  && !var5.method_27852(class_2246.field_10194)
                  && !var5.method_27852(class_2246.field_10362)
                  && !var5.method_26164(class_3481.field_15471)) {
                  continue;
               }

               return false;
            }

            return false;
         }

         return false;
      }

      for (ConstructionJob var7 : this.x9b03bdckmlwm.x1ja61emeln2hc()) {
         if (var7.x24kbqv6g4kkrg()
            .method_14657(
               new class_3341(
                  var2.method_10263() - 5 - 2,
                  var2.method_10264() - 2,
                  var2.method_10260() - 5 - 2,
                  var2.method_10263() + 5 + 2,
                  var2.method_10264() + 3,
                  var2.method_10260() + 5 + 2
               )
            )) {
            return false;
         }
      }

      return true;
   }

   private List<class_2338> x2upxz7dqb7edt(class_3218 var1, class_2338 var2) {
      var var3 = new ArrayList<class_2338>();

      for (int var4 = -5; var4 <= 5; var4++) {
         for (int var5 = -5; var5 <= 5; var5++) {
            if (Math.abs(var4) == 5 || Math.abs(var5) == 5) {
               class_2338 var6 = var2.method_10069(var4, 0, var5);
               class_2680 var7 = var1.method_8320(var6);
               if (var6.equals(this.x3u1km6otsxwtp()) ? !var7.method_27852(class_2246.field_10188) : !var7.method_27852(class_2246.field_10620)) {
                  var3.add(var6);
               }
            }
         }
      }

      return var3;
   }

   private List<class_2338> x2ujvd9g07uaij(class_3218 var1) {
      var var2 = new ArrayList<class_2338>();

      for (int var3 = -3; var3 <= 3; var3++) {
         for (int var4 = -3; var4 <= 3; var4++) {
            if (Math.abs(var3) == 3 || Math.abs(var4) == 3) {
               class_2338 var5 = this.x1bm3tnyu1jatt.method_10069(var3, 0, var4);
               class_2680 var6 = var1.method_8320(var5);
               if (var6.method_27852(class_2246.field_10620) || var6.method_27852(class_2246.field_10188)) {
                  var2.add(var5);
               }
            }
         }
      }

      return var2;
   }

   private class_2338 x3u1km6otsxwtp() {
      return this.x1bm3tnyu1jatt.method_10076(5);
   }

   private void x3v731fax5g1nx(class_3218 var1) {
      if (this.x1bm3tnyu1jatt != null) {
         class_2338 var2 = this.x3u1km6otsxwtp();
         if (var1.method_14178().method_12123(var2.method_10263() >> 4, var2.method_10260() >> 4)) {
            if (var1.method_8333(null, new class_238(var2).method_1009(0.15, 0.2, 0.15), var0 -> var0.method_5805() && !var0.method_7325()).isEmpty()) {
               class_2680 var3 = var1.method_8320(var2);
               if (var3.method_27852(class_2246.field_10188) && (Boolean)var3.method_11654(class_2349.field_11026)) {
                  WorldEditPolicy.x1bq0f8n53gtob(var1, var2, (class_2680)var3.method_11657(class_2349.field_11026, false), 3);
               }
            }
         }
      }
   }

   private void x3p0xf9wskmc24(class_3218 var1) {
      if (this.x1bm3tnyu1jatt != null) {
         class_2338 var2 = this.x3u1km6otsxwtp();
         if (var1.method_14178().method_12123(var2.method_10263() >> 4, var2.method_10260() >> 4)) {
            class_2680 var3 = var1.method_8320(var2);
            if (var3.method_27852(class_2246.field_10188) && !(Boolean)var3.method_11654(class_2349.field_11026)) {
               WorldEditPolicy.x1bq0f8n53gtob(var1, var2, (class_2680)var3.method_11657(class_2349.field_11026, true), 3);
            }
         }
      }
   }

   private void x1f00g882945e(class_3218 var1, class_1429 var2) {
      for (class_1429 var4 : this.x3q7ywqh31h55d(var1)) {
         if (var4 != var2 && this.x2745j5ue4rw4q(var4)) {
            var4.method_5942()
               .method_6337(this.x1bm3tnyu1jatt.method_10263() + 0.5, this.x1bm3tnyu1jatt.method_10264(), this.x1bm3tnyu1jatt.method_10260() + 0.5, 0.8);
         }
      }
   }

   private List<class_1429> x3q7ywqh31h55d(class_3218 var1) {
      return var1.method_8390(
         class_1429.class,
         new class_238(this.x9b03bdckmlwm.core()).method_1009(Math.min(56, this.x9b03bdckmlwm.radius()), 32.0, Math.min(56, this.x9b03bdckmlwm.radius())),
         var0 -> var0.method_5805() && x2rzbu6s47zw2x(var0)
      );
   }

   private boolean x2745j5ue4rw4q(class_1297 var1) {
      return this.x1bm3tnyu1jatt != null
         && Math.abs(var1.method_23317() - (this.x1bm3tnyu1jatt.method_10263() + 0.5)) < 5.0
         && Math.abs(var1.method_23321() - (this.x1bm3tnyu1jatt.method_10260() + 0.5)) < 5.0
         && Math.abs(var1.method_23318() - this.x1bm3tnyu1jatt.method_10264()) < 3.0;
   }

   private int xpn2n0o78r82j(List<class_1429> var1, class_1429 var2) {
      return (int)var1.stream().filter(this::x2745j5ue4rw4q).filter(var1x -> var1x.method_5864() == var2.method_5864()).count();
   }

   private int x1yv8cepr980a2(List<class_1429> var1) {
      return (int)var1.stream().filter(this::x2745j5ue4rw4q).count();
   }

   private static boolean x2rzbu6s47zw2x(class_1429 var0) {
      return var0 instanceof class_10730
         || var0 instanceof class_1472
         || var0 instanceof class_1428
         || var0 instanceof class_1452
         || var0 instanceof class_1463
         || var0 instanceof class_6053;
   }

   private static class_1792 x1zya4i1rpwuhr(class_1429 var0) {
      if (var0 instanceof class_1428) {
         return class_1802.field_8317;
      } else if (var0 instanceof class_1452 || var0 instanceof class_1463) {
         return class_1802.field_8179;
      } else {
         return !(var0 instanceof class_10730) && !(var0 instanceof class_1472) && !(var0 instanceof class_6053) ? null : class_1802.field_8861;
      }
   }

   private static class_1792 x3k9mdozxqhn1u(class_1472 var0) {
      return switch (var0.method_6633()) {
         case field_7946 -> class_1802.field_19045;
         case field_7958 -> class_1802.field_19046;
         case field_7951 -> class_1802.field_19047;
         case field_7947 -> class_1802.field_19048;
         case field_7961 -> class_1802.field_19049;
         case field_7954 -> class_1802.field_19050;
         case field_7944 -> class_1802.field_19051;
         case field_7967 -> class_1802.field_19052;
         case field_7955 -> class_1802.field_19053;
         case field_7945 -> class_1802.field_19054;
         case field_7966 -> class_1802.field_19055;
         case field_7957 -> class_1802.field_19056;
         case field_7942 -> class_1802.field_19057;
         case field_7964 -> class_1802.field_19058;
         case field_7963 -> class_1802.field_19059;
         default -> class_1802.field_19044;
      };
   }

   private static boolean x3e0xb3lfomiyr(class_1799 var0) {
      if (var0.method_7960()) {
         return false;
      } else {
         String var1 = class_7923.field_41178.method_10221(var0.method_7909()).method_12832();
         return var0.method_31574(class_1802.field_8803)
            || var0.method_31574(class_1802.field_8103)
            || var0.method_31574(class_1802.field_8745)
            || var0.method_31574(class_1802.field_8153)
            || var0.method_31574(class_1802.field_8245)
            || var1.endsWith("_wool");
      }
   }

   private static int x26v4oc4f99cpr(class_1646 var0) {
      int var1 = 0;

      for (int var2 = 0; var2 < var0.method_35199().method_5439(); var2++) {
         if (x3e0xb3lfomiyr(var0.method_35199().method_5438(var2))) {
            var1 += var0.method_35199().method_5438(var2).method_7947();
         }
      }

      return var1;
   }

   private int x8bmwjivt7kr4(class_3218 var1, class_1646 var2) {
      return x18gt41621rh5l(var2, class_1802.field_8803)
         + ColonyManager.x1mlne8tl8k5ag(var1, this.x9b03bdckmlwm, var0 -> var0.method_31574(class_1802.field_8803));
   }

   private void x2kdf6zxkbv4uj(class_3218 var1, class_1646 var2) {
      int var3 = Math.max(0, 32 - ColonyManager.x1mlne8tl8k5ag(var1, this.x9b03bdckmlwm, var0 -> var0.method_31574(class_1802.field_8803)));

      for (int var4 = 0; var4 < var2.method_35199().method_5439(); var4++) {
         class_1799 var5 = var2.method_35199().method_5438(var4);
         if (var5.method_31574(class_1802.field_8803)) {
            int var6 = Math.min(var3, var5.method_7947());
            var3 -= var6;
            if (var6 < var5.method_7947()) {
               var5.method_7939(var6);
            }
         }
      }
   }

   private void x33mnq7ijq2l5z(class_3218 var1, class_1646 var2) {
      if (!xq6j4f862i6km(var2, class_1802.field_8868)) {
         var var3 = ColonyManager.x3lxwdirnn77qc(var1, this.x9b03bdckmlwm, var0 -> var0.method_31574(class_1802.field_8868));
         if (var3.isPresent()) {
            this.x2xg4t4fgfn92z(var1, var2, (class_1799)var3.get());
         } else {
            if (ColonyManager.x22hjebj4vmztt(var1, this.x9b03bdckmlwm, var0 -> var0.method_31574(class_1802.field_8620), 2)) {
               this.x2xg4t4fgfn92z(var1, var2, new class_1799(class_1802.field_8868));
            }
         }
      }
   }

   private void x31djqoo9oci6(class_3218 var1, class_1646 var2, int var3) {
      VillagerInventory.restock(() -> x18gt41621rh5l(var2, class_1802.field_8550), var3, () -> {
         if (!VillagerInventory.canAccept(var2.method_35199(), class_1802.field_8550)) return false;
         var var4 = ColonyManager.x3lxwdirnn77qc(var1, this.x9b03bdckmlwm, var0 -> var0.method_31574(class_1802.field_8550));
         if (var4.isPresent()) {
            this.x2xg4t4fgfn92z(var1, var2, (class_1799)var4.get());
         } else {
            if (!ColonyManager.x22hjebj4vmztt(var1, this.x9b03bdckmlwm, var0 -> var0.method_31574(class_1802.field_8620), 3)) {
               return false;
            }

            this.x2xg4t4fgfn92z(var1, var2, new class_1799(class_1802.field_8550));
         }
         return true;
      });
   }

   private void x27sd5416kpqqj(class_3218 var1, class_1646 var2) {
      for (int var3 = 0; var3 < var2.method_35199().method_5439(); var3++) {
         if (ColonyManager.xhahxgpa08yc9(var2.method_35199().method_5438(var3)) > 0) {
            return;
         }
      }

      ColonyManager.x3lxwdirnn77qc(var1, this.x9b03bdckmlwm, var0 -> ColonyManager.xhahxgpa08yc9(var0) > 0)
         .ifPresent(var3x -> this.x2xg4t4fgfn92z(var1, var2, var3x));
   }

   private void x12fl4tcpl6f1x(class_3218 var1, class_1646 var2, class_1792 var3, int var4) {
      VillagerInventory.restock(() -> x18gt41621rh5l(var2, var3)
         + (var2.method_6047().method_31574(var3) ? var2.method_6047().method_7947() : 0), var4, () -> {
         if (!VillagerInventory.canAccept(var2.method_35199(), var3)) return false;
         var var5 = ColonyManager.x3lxwdirnn77qc(var1, this.x9b03bdckmlwm, var1x -> var1x.method_31574(var3));
         if (var5.isEmpty()) {
            return false;
         }

         this.x2xg4t4fgfn92z(var1, var2, (class_1799)var5.get());
         return true;
      });
   }

   private boolean xmdm1vdfbqp32(class_3218 var1, class_1646 var2) {
      if (this.xmj689j1i2yoe == null) {
         return false;
      } else if (var2.method_6047().method_31574(this.xmj689j1i2yoe)) {
         return true;
      } else {
         if (!var2.method_6047().method_7960()) {
            class_1799 var3 = var2.method_6047().method_7972();
            var2.method_5673(class_1304.field_6173, class_1799.field_8037);
            this.x2xg4t4fgfn92z(var1, var2, var3);
         }

         class_1799 var4 = xt9wcfi8kl303(var2, this.xmj689j1i2yoe);
         if (var4.method_7960()) {
            var4 = ColonyManager.x3lxwdirnn77qc(var1, this.x9b03bdckmlwm, var1x -> var1x.method_31574(this.xmj689j1i2yoe)).orElse(class_1799.field_8037);
         }

         if (var4.method_7960()) {
            return false;
         } else {
            var2.method_5673(class_1304.field_6173, var4);
            return true;
         }
      }
   }

   private static class_1799 xt9wcfi8kl303(class_1646 var0, class_1792 var1) {
      if (var1 == null) {
         return class_1799.field_8037;
      } else {
         for (int var2 = 0; var2 < var0.method_35199().method_5439(); var2++) {
            if (var0.method_35199().method_5438(var2).method_31574(var1)) {
               class_1799 var3 = var0.method_35199().method_5438(var2).method_46651(1);
               var0.method_35199().method_5438(var2).method_7934(1);
               return var3;
            }
         }

         return class_1799.field_8037;
      }
   }

   private boolean x2idhnuuiroo7r(class_3218 var1, class_1646 var2, class_1792 var3, int var4) {
      int var5 = x18gt41621rh5l(var2, var3)
         + (var2.method_6047().method_31574(var3) ? var2.method_6047().method_7947() : 0)
         + ColonyManager.x1mlne8tl8k5ag(var1, this.x9b03bdckmlwm, var1x -> var1x.method_31574(var3));
      if (var5 < var4) {
         return false;
      } else {
         int var6 = var4;

         for (int var7 = 0; var7 < var2.method_35199().method_5439() && var6 > 0; var7++) {
            if (var2.method_35199().method_5438(var7).method_31574(var3)) {
               int var8 = Math.min(var6, var2.method_35199().method_5438(var7).method_7947());
               var2.method_35199().method_5438(var7).method_7934(var8);
               var6 -= var8;
            }
         }

         if (var6 > 0 && var2.method_6047().method_31574(var3)) {
            int var9 = Math.min(var6, var2.method_6047().method_7947());
            var2.method_6047().method_7934(var9);
            var6 -= var9;
         }

         return var6 == 0 || ColonyManager.x22hjebj4vmztt(var1, this.x9b03bdckmlwm, var1x -> var1x.method_31574(var3), var6);
      }
   }

   private void x2vvpgkqtpg958(class_3218 var1, class_1646 var2) {
      if (this.xmj689j1i2yoe != null && var2.method_6047().method_31574(this.xmj689j1i2yoe)) {
         class_1799 var3 = var2.method_6047().method_7972();
         var2.method_5673(class_1304.field_6173, class_1799.field_8037);
         this.x2xg4t4fgfn92z(var1, var2, var3);
         this.xmj689j1i2yoe = null;
      }
   }

   private static boolean xq6j4f862i6km(class_1646 var0, class_1792 var1) {
      for (int var2 = 0; var2 < var0.method_35199().method_5439(); var2++) {
         if (var0.method_35199().method_5438(var2).method_31574(var1)) {
            return true;
         }
      }

      return false;
   }

   private static int x18gt41621rh5l(class_1646 var0, class_1792 var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var0.method_35199().method_5439(); var3++) {
         if (var0.method_35199().method_5438(var3).method_31574(var1)) {
            var2 += var0.method_35199().method_5438(var3).method_7947();
         }
      }

      return var2;
   }

   private static boolean x165qssxeku6kz(class_1646 var0, class_1792 var1) {
      for (int var2 = 0; var2 < var0.method_35199().method_5439(); var2++) {
         if (var0.method_35199().method_5438(var2).method_31574(var1)) {
            var0.method_35199().method_5438(var2).method_7934(1);
            return true;
         }
      }

      return false;
   }

   private void x2xg4t4fgfn92z(class_3218 var1, class_1646 var2, class_1799 var3) {
      class_1799 var4 = var2.method_35199().method_5491(var3);
      if (!var4.method_7960()) {
         var2.method_5775(var1, var4);
      }
   }

   private void x1yqub9v49uk07(class_3218 var1, class_1646 var2, class_2338 var3, double var4) {
      VillagerShelter.x2wzylgizxltef(var1, var2, class_2338.method_49637(var3.method_10263() + 0.5, var3.method_10264(), var3.method_10260() + 0.5), var4);
   }

   public String debugState() {
      return (this.x3m757n03ci4pu == null ? "ожидание" : this.x3m757n03ci4pu.name().toLowerCase(Locale.ROOT))
         + (this.xmj689j1i2yoe == null ? "" : ", приманка=" + class_7923.field_41178.method_10221(this.xmj689j1i2yoe).method_12832())
         + ", собрано="
         + this.x2ueth4fvnxw81;
   }

   protected void finishRunning(class_3218 var1, class_1646 var2, long var3) {
      ActionTrace.worker(var2, "task_stop", "RancherTask", false);
      if (this.x3f72o6zqrgoi5 != null) {
         FollowRancherGoal.release(this.x3f72o6zqrgoi5);
      }

      this.x2vvpgkqtpg958(var1, var2);
      this.x3v731fax5g1nx(var1);
      var2.method_5942().method_6340();
      var2.method_18868().method_18875(ColonyMemories.x2ppem7wipzctm);
      var2.method_18868().method_18875(ColonyMemories.x3o547clcv9kaw);
      this.x9b03bdckmlwm = null;
      this.x3f72o6zqrgoi5 = null;
   }

   @Override
   protected boolean method_18919(class_3218 var1, class_1646 var2) {
      return shouldRun(var1, var2);
   }

   @Override
   protected boolean method_18927(class_3218 var1, class_1646 var2, long var3) {
      return shouldKeepRunning(var1, var2, var3);
   }

   @Override
   protected void method_18926(class_3218 var1, class_1646 var2, long var3) {
      finishRunning(var1, var2, var3);
   }

   @Override
   protected void method_18924(class_3218 var1, class_1646 var2, long var3) {
      keepRunning(var1, var2, var3);
   }

   @Override
   protected void method_18920(class_3218 var1, class_1646 var2, long var3) {
      run(var1, var2, var3);
   }
}
