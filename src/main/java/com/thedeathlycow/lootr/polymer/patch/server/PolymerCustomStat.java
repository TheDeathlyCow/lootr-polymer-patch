package com.thedeathlycow.lootr.polymer.patch.server;

import eu.pb4.polymer.core.api.utils.PolymerSyncedObject;
import eu.pb4.polymer.rsm.api.RegistrySyncUtils;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import xyz.nucleoid.packettweaker.PacketContext;

public record PolymerCustomStat(Identifier id) implements PolymerSyncedObject<Identifier> {
    @Override
    public Identifier getPolymerReplacement(Identifier object, PacketContext context) {
        return id;
    }

    public static void registerOverlay(Identifier serverStat, PolymerCustomStat stat) {
        PolymerSyncedObject.setSyncedObject(Registries.CUSTOM_STAT, serverStat, stat);

        Registry<Object> registry = (Registry<Object>) (Object) Registries.CUSTOM_STAT;
        Object value = serverStat;
        RegistrySyncUtils.setServerEntry(registry, value);
    }
}