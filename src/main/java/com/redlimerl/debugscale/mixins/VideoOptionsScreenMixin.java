package com.redlimerl.debugscale.mixins;

import com.redlimerl.debugscale.options.TextDebugScaleOption;
import com.redlimerl.debugscale.options.TextPieScaleOption;
import net.minecraft.client.gui.screen.VideoOptionsScreen;
import net.minecraft.client.gui.widget.ButtonListWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VideoOptionsScreen.class)
public class VideoOptionsScreenMixin {

    @Shadow private ButtonListWidget list;

    @Inject(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/ButtonListWidget;addAll([Lnet/minecraft/client/options/Option;)V", shift = At.Shift.AFTER))
    public void initButtons(CallbackInfo ci) {
        this.list.addOptionEntry(new TextDebugScaleOption(), new TextPieScaleOption());
    }

}
