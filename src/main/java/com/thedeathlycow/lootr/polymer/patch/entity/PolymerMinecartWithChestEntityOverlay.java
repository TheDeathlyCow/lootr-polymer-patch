package com.thedeathlycow.lootr.polymer.patch.entity;

import eu.pb4.polymer.core.api.entity.PolymerEntity;
import net.minecraft.entity.EntityType;
import xyz.nucleoid.packettweaker.PacketContext;

public class PolymerMinecartWithChestEntityOverlay implements PolymerEntity {
    @Override
    public EntityType<?> getPolymerEntityType(PacketContext context) {
        return EntityType.CHEST_MINECART;
    }
}