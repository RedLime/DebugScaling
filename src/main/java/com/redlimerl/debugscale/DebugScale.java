package com.redlimerl.debugscale;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

public class DebugScale implements ClientModInitializer {

    private static final String MOD_ID = "sizing_debug";
    public static boolean IS_RENDERING = false;

    @Override
    public void onInitializeClient() {
        /*
        SpeedRunOptions.addOptionButton(screen -> new SliderWidget(0, 0, 150, 20, new LiteralText("Pie Chart Scale : " + (int) (OptionData.getPieTextScale()*100) + "%"), (OptionData.getPieTextScale() - 0.5f) / 2) {
            @Override
            protected void updateMessage() {
                this.setMessage(new LiteralText("Pie Chart Scale : " + (int) (OptionData.getPieTextScale()*100) + "%"));
            }

            @Override
            protected void applyValue() {
                SpeedRunOptions.setOption(PIE_CHART_SCALE, Math.round((0.5f + (float) this.value * 2)*100)/100f);
            }
        });
        SpeedRunOptions.addOptionButton(screen -> new SliderWidget(0, 0, 150, 20, new LiteralText("Debug Text Scale : " + (int) (OptionData.getDebugScale()*100) + "%"), (OptionData.getDebugScale() - 0.5f) / 2) {
            @Override
            protected void updateMessage() {
                this.setMessage(new LiteralText("Debug Text Scale : " + (int) (OptionData.getDebugScale()*100) + "%"));
            }

            @Override
            protected void applyValue() {
                SpeedRunOptions.setOption(DEBUG_SCALE, Math.round((0.5f + (float) this.value * 2)*100)/100f);
            }
        });*/
        
        OptionData.setup();
    }

    public static void scaledPieMatrix(MatrixStack matrixStack) {
        matrixStack.push();
        matrixStack.scale(OptionData.getPieTextScale(), OptionData.getPieTextScale(), 1f);
    }

    public static void unscaledPieMatrix(MatrixStack matrixStack) {
        matrixStack.pop();
    }

    public static void scaledDebugMatrix(MatrixStack matrixStack) {
        matrixStack.push();
        matrixStack.scale(OptionData.getDebugScale(), OptionData.getDebugScale(), 1f);
    }
    public static void unscaledDebugMatrix(MatrixStack matrixStack) {
        matrixStack.pop();
    }

    public static void drawTranslatedPieChart(MinecraftClient client, Args args) {
        int j = client.getWindow().getFramebufferWidth() - 160 - 10;
        float k = client.getWindow().getFramebufferHeight() - 320;

        int textWidth = client.textRenderer.getWidth((String) args.get(1));
        if (j == (float) args.get(2) - 110 + textWidth) {
            // XXXXX   OO%  XX%
            args.set(2, (((float) args.get(2) + 50 + textWidth) / OptionData.getPieTextScale()) - (OptionData.getPieTextScale() > 1 ? client.textRenderer.getWidth("00.00%") + 5 : 50) - textWidth);
        } else if (j == (float) args.get(2) - 160 + textWidth) {
            // XXXXX   XX%  OO%
            args.set(2, (((float) args.get(2) + textWidth) / OptionData.getPieTextScale()) - textWidth);
        } else {
            // OOOOO   XX%  XX%
            args.set(2, (float) args.get(2) / OptionData.getPieTextScale());
        }
        if (k == (float) args.get(3) + 96) {
            // top root name
            args.set(3, (float) args.get(3) / OptionData.getPieTextScale());
        } else {
            // profiles
            float y = ((float) args.get(3) - k - 100);
            args.set(3, (((float) args.get(3) - y) / OptionData.getPieTextScale()) + y);
        }
    }

    public static void drawTranslatedDebug(Args args, boolean isRight) {
        args.set(2, ((float) args.get(2)) / OptionData.getDebugScale());
        args.set(3, ((float) args.get(3)) / OptionData.getDebugScale());
    }

    public static void drawFillTranslatedDebug(Args args, boolean isRight) {
        for (int i = 1; i <= 4; i++) {
            args.set(i, Math.round(((int) args.get(i)) / OptionData.getDebugScale()));
        }
    }
}
