package com.thedeathlycow.lootr.polymer.patch.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.thedeathlycow.lootr.polymer.patch.item.PolymerBlockItemOverlay;
import eu.pb4.polymer.core.api.item.PolymerItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import noobanidus.mods.lootr.fabric.init.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ModItems.class)
public class ModItemsMixin {
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
        PolymerItem.registerOverlay(result, PolymerBlockItemOverlay.of(result));
        return result;
    }
}