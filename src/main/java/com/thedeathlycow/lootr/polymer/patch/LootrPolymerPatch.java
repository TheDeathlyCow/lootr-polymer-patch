package com.thedeathlycow.lootr.polymer.patch;

import eu.pb4.polymer.core.api.block.PolymerBlockUtils;
import net.fabricmc.api.ModInitializer;
import noobanidus.mods.lootr.fabric.init.ModBlockEntities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LootrPolymerPatch implements ModInitializer {
    public static final String MOD_ID = "lootr-polymer-patch";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Patching Lootr to work serverside");
        PolymerBlockUtils.registerBlockEntity(
                ModBlockEntities.LOOTR_CHEST,
                ModBlockEntities.LOOTR_BARREL,
                ModBlockEntities.LOOTR_TRAPPED_CHEST,
                ModBlockEntities.LOOTR_SHULKER,
                ModBlockEntities.LOOTR_INVENTORY
        );
    }
}