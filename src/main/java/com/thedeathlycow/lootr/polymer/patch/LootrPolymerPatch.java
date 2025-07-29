package com.thedeathlycow.lootr.polymer.patch;

import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.api.ModInitializer;
import noobanidus.mods.lootr.common.api.LootrAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LootrPolymerPatch implements ModInitializer {
    public static final String MOD_ID = "lootr-polymer-patch";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Patching Lootr to work serverside");
        PolymerResourcePackUtils.addModAssets(LootrAPI.MODID);
        PolymerResourcePackUtils.addModAssets(MOD_ID);
    }
}