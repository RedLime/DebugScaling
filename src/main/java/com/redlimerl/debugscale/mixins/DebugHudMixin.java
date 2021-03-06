package com.redlimerl.debugscale.mixins;

import com.redlimerl.debugscale.DebugScale;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DebugHud.class)
public class DebugHudMixin {

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;pushMatrix()V", shift = At.Shift.AFTER))
    public void startDrawDebug(MatrixStack matrixStack, CallbackInfo ci) {
        DebugScale.IS_RENDERING = true;
        DebugScale.scaledDebugMatrix(matrixStack);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;popMatrix()V", shift = At.Shift.BEFORE))
    public void endDrawDebug(MatrixStack matrixStack, CallbackInfo ci) {
        DebugScale.IS_RENDERING = false;
        DebugScale.unscaledDebugMatrix(matrixStack);
    }
}
