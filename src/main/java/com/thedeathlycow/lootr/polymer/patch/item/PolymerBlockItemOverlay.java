package com.thedeathlycow.lootr.polymer.patch.item;

import com.thedeathlycow.lootr.polymer.patch.LootrPolymerPatch;
import eu.pb4.polymer.core.api.item.PolymerItem;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import noobanidus.mods.lootr.fabric.init.ModItems;
import org.jetbrains.annotations.Nullable;
import xyz.nucleoid.packettweaker.PacketContext;

public record PolymerBlockItemOverlay(Item baseItem) implements PolymerItem {
    public static PolymerBlockItemOverlay of(Item serverItem) {
        Item clientItem;

        if (serverItem == ModItems.CHEST || serverItem == ModItems.INVENTORY) {
            clientItem = Items.CHEST;
        } else if (serverItem == ModItems.TRAPPED_CHEST) {
            clientItem = Items.TRAPPED_CHEST;
        } else if (serverItem == ModItems.BARREL) {
            clientItem = Items.BARREL;
        } else if (serverItem == ModItems.SHULKER) {
            clientItem = Items.SHULKER_BOX;
        } else {
            LootrPolymerPatch.LOGGER.error("Unknown lootr item, defaulting to trial key: {}", serverItem);
            clientItem = Items.TRIAL_KEY;
        }

        return new PolymerBlockItemOverlay(clientItem);
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext context) {
        return baseItem;
    }

    @Override
    @Nullable
    public Identifier getPolymerItemModel(ItemStack stack, PacketContext context) {
        return !PolymerResourcePackUtils.hasMainPack(context)
                ? baseItem.getComponents().get(DataComponentTypes.ITEM_MODEL)
                : PolymerItem.super.getPolymerItemModel(stack, context);
    }
}