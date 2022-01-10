package com.redlimerl.debugscale;

import net.fabricmc.loader.api.FabricLoader;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class OptionData {
    private static float DEBUG_SCALE = 1f;
    private static float PIE_TEXT_SCALE = 1f;

    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("debugscaling.txt");

    public static void setup() {
        File configFile = CONFIG_PATH.toFile();
        if (configFile.exists()) {
            try {
                String configData = FileUtils.readFileToString(configFile, StandardCharsets.UTF_8);
                DEBUG_SCALE = Float.parseFloat(configData.split(":")[0]);
                PIE_TEXT_SCALE = Float.parseFloat(configData.split(":")[1]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void save() {
        File configFile = CONFIG_PATH.toFile();
        try {
            FileUtils.writeStringToFile(configFile, DEBUG_SCALE + ":" + PIE_TEXT_SCALE, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static float getDebugScale() {
        return DEBUG_SCALE;
    }

    public static void setDebugScale(float debugScale) {
        DEBUG_SCALE = debugScale;
        save();
    }

    public static float getPieTextScale() {
        return PIE_TEXT_SCALE;
    }

    public static void setPieTextScale(float pieTextScale) {
        PIE_TEXT_SCALE = pieTextScale;
        save();
    }
}
