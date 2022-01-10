package com.redlimerl.debugscale.mixins;

import com.redlimerl.debugscale.DebugScale;
import com.redlimerl.debugscale.OptionData;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Window.class)
public class WindowMixin {

    @Inject(method = "getScaledWidth", at = @At("RETURN"), cancellable = true)
    private void injected(CallbackInfoReturnable<Integer> cir) {
        if (DebugScale.IS_RENDERING) cir.setReturnValue(Math.round(cir.getReturnValue() / OptionData.getDebugScale()));
    }
}
