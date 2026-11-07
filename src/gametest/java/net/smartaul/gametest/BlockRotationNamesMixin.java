package net.smartaul.gametest;

import net.minecraft.class_2470;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

/**
 * The local identity mappings retain Mojang enum name strings, while Loom remaps
 * Fabric's annotation default to intermediary. Java resolves annotation enum
 * values by Enum.name(), not by field access. Align only BlockRotation's names
 * in the test JVM; serialized rotation IDs and ordinals are unchanged.
 */
@Mixin(class_2470.class)
public abstract class BlockRotationNamesMixin {
    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/class_2470;<init>(Ljava/lang/String;IILjava/lang/String;Lnet/minecraft/class_4990;)V"), index = 0)
    private static String smartAul$annotationEnumName(String name) {
        return switch (name) {
            case "NONE" -> "field_11467";
            case "CLOCKWISE_90" -> "field_11463";
            case "CLOCKWISE_180" -> "field_11464";
            case "COUNTERCLOCKWISE_90" -> "field_11465";
            default -> name;
        };
    }
}
