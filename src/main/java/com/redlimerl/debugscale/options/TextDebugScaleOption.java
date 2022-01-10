package com.redlimerl.debugscale.options;

import com.redlimerl.debugscale.OptionData;
import net.minecraft.client.options.DoubleOption;
import net.minecraft.text.LiteralText;

public class TextDebugScaleOption extends DoubleOption {

    public TextDebugScaleOption() {
        super("options.debugscale.debug", 0.5f, 2.5f, 0.01f,
                gameOptions -> (double) OptionData.getDebugScale(),
                (gameOptions, db) -> OptionData.setDebugScale(db.floatValue()),
                (gameOptions, doubleOption) -> new LiteralText("Debug Screen Text : " + (int) (OptionData.getDebugScale() * 100) + "%"));
    }
}
