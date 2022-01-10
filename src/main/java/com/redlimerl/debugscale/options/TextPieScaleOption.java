package com.redlimerl.debugscale.options;

import com.redlimerl.debugscale.OptionData;
import net.minecraft.client.options.DoubleOption;
import net.minecraft.text.LiteralText;

public class TextPieScaleOption extends DoubleOption {

    public TextPieScaleOption() {
        super("options.debugscale.pie", 0.5f, 2.5f, 0.01f,
                gameOptions -> (double) OptionData.getPieTextScale(),
                (gameOptions, db) -> OptionData.setPieTextScale(db.floatValue()),
                (gameOptions, doubleOption) -> new LiteralText("Pie Chart Text : " + (int) (OptionData.getPieTextScale() * 100) + "%"));
    }
}
