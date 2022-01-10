package com.redlimerl.debugscale.options;

import com.google.common.collect.ImmutableList;
import com.redlimerl.debugscale.OptionData;
import me.jellysquid.mods.sodium.client.gui.options.*;
import me.jellysquid.mods.sodium.client.gui.options.control.ControlValueFormatter;
import me.jellysquid.mods.sodium.client.gui.options.control.SliderControl;
import me.jellysquid.mods.sodium.client.gui.options.storage.SodiumOptionsStorage;

import java.util.ArrayList;
import java.util.List;

public class SodiumAdditionalOption {
    private static final SodiumOptionsStorage sodiumOpts = new SodiumOptionsStorage();

    public static OptionPage debugScale() {
        List<OptionGroup> groups = new ArrayList<>();

        groups.add(OptionGroup.createBuilder()
                .add(OptionImpl.createBuilder(int.class, sodiumOpts)
                        .setName("Debug Screen Text")
                        .setTooltip("Resizing debug screen texts scale.")
                        .setControl(option -> new SliderControl(option, 50, 250, 1, ControlValueFormatter.percentage()))
                        .setBinding((opts, value) -> OptionData.setDebugScale(value / 100f), opts -> (int) (OptionData.getDebugScale()*100f))
                        .setImpact(OptionImpact.LOW)
                        .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                        .build()
                )
                .build());

        groups.add(OptionGroup.createBuilder()
                .add(OptionImpl.createBuilder(int.class, sodiumOpts)
                        .setName("Pie Chart Text")
                        .setTooltip("Resizing pie chart texts scale.")
                        .setControl(option -> new SliderControl(option, 50, 250, 1, ControlValueFormatter.percentage()))
                        .setBinding((opts, value) -> OptionData.setPieTextScale(value / 100f), opts -> (int) (OptionData.getPieTextScale()*100f))
                        .setImpact(OptionImpact.LOW)
                        .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                        .build()
                )
                .build());

        return new OptionPage("Debug Text", ImmutableList.copyOf(groups));
    }
}
