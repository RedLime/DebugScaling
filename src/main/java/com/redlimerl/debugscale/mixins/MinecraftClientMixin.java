package com.redlimerl.debugscale.mixins;

import com.redlimerl.debugscale.DebugScale;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.profiler.ProfileResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {

    @Inject(method = "drawProfilerResults", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiler/ProfileResult;getHumanReadableName(Ljava/lang/String;)Ljava/lang/String;", shift = At.Shift.AFTER))
    public void startDrawProfiler(MatrixStack matrixStack, ProfileResult profileResult, CallbackInfo ci) {
        DebugScale.scaledPieMatrix(matrixStack);
    }

    @Inject(method = "drawProfilerResults", at = @At("TAIL"))
    public void endDrawProfiler(MatrixStack matrixStack, ProfileResult profileResult, CallbackInfo ci) {
        DebugScale.unscaledPieMatrix(matrixStack);
    }

    @ModifyArgs(method = "drawProfilerResults",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/font/TextRenderer;drawWithShadow(Lnet/minecraft/client/util/math/MatrixStack;Ljava/lang/String;FFI)I"
            ),
            slice = @Slice(
                    from = @At(
                            value = "INVOKE",
                            target = "Lnet/minecraft/util/profiler/ProfileResult;getHumanReadableName(Ljava/lang/String;)Ljava/lang/String;"
                    ),
                    to = @At("TAIL")
            )
    )
    private void drawTranslate(Args args) {
        DebugScale.drawTranslatedPieChart((MinecraftClient) ((Object) this), args);
    }
}
