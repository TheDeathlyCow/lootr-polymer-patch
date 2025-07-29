package com.thedeathlycow.lootr.polymer.patch;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LootrPolymerPatch implements ModInitializer {
    public static final String MOD_ID = "lootr-polymer-patch";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Patching Lootr to work serverside");
    }
}