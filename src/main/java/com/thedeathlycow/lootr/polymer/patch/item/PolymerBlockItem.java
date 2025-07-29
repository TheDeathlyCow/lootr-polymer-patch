package com.thedeathlycow.lootr.polymer.patch.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import xyz.nucleoid.packettweaker.PacketContext;

public record PolymerBlockItem(Item baseItem) implements PolymerItem {
    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext context) {
        return baseItem;
    }
}