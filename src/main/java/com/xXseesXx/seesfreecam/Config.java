package com.xXseesXx.seesfreecam;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static float freecamSpeed = 1.0f;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        freecamSpeed = configuration.getFloat(
            "freecamSpeed",
            Configuration.CATEGORY_GENERAL,
            freecamSpeed,
            0.2f,
            10.0f,
            "Default freecam movement speed (0.2 - 10.0)");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
