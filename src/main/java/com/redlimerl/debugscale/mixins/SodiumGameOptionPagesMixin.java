package com.redlimerl.debugscale.mixins;

import com.redlimerl.debugscale.options.SodiumAdditionalOption;
import me.jellysquid.mods.sodium.client.gui.options.OptionPage;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(targets = "me.jellysquid.mods.sodium.client.gui.SodiumOptionsGUI")
public class SodiumGameOptionPagesMixin {

    @Shadow @Final private List<OptionPage> pages;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void injectOption(Screen prevScreen, CallbackInfo ci) {
        this.pages.add(SodiumAdditionalOption.debugScale());
    }
}
