package com.thedeathlycow.lootr.polymer.patch.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.thedeathlycow.lootr.polymer.patch.LootrPolymerPatch;
import com.thedeathlycow.lootr.polymer.patch.item.PolymerBlockItem;
import eu.pb4.polymer.core.api.item.PolymerItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import noobanidus.mods.lootr.fabric.init.ModItems;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ModItems.class)
public class ModItemsMixin {
    @Shadow @Final public static BlockItem CHEST;

    @Shadow @Final public static BlockItem TRAPPED_CHEST;

    @Shadow @Final public static BlockItem BARREL;

    @Shadow @Final public static BlockItem SHULKER;

    @Shadow @Final public static BlockItem INVENTORY;

    @WrapOperation(
            method = "registerItems",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/registry/Registry;register(Lnet/minecraft/registry/Registry;Lnet/minecraft/util/Identifier;Ljava/lang/Object;)Ljava/lang/Object;"
            )
    )
    private static Object polymerizeLootrItems(
            Registry<Object> registry,
            Identifier id,
            Object entry,
            Operation<Object> original
    ) {
        Item result = (Item) original.call(registry, id, entry);

        Item clientItem;

        if (result == CHEST || result == INVENTORY) {
            clientItem = Items.CHEST;
        } else if (result == TRAPPED_CHEST) {
            clientItem = Items.TRAPPED_CHEST;
        } else if (result == BARREL) {
            clientItem = Items.BARREL;
        } else if (result == SHULKER) {
            clientItem = Items.SHULKER_BOX;
        } else {
            clientItem = Items.TRIAL_KEY;
        }

        PolymerItem.registerOverlay(result, new PolymerBlockItem(clientItem));

        return result;
    }
}