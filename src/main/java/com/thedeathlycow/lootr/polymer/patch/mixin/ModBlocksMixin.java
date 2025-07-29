package com.thedeathlycow.lootr.polymer.patch.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.thedeathlycow.lootr.polymer.patch.block.PolymerBlockOverlay;
import eu.pb4.polymer.core.api.block.PolymerBlockUtils;
import net.minecraft.block.Block;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import noobanidus.mods.lootr.fabric.init.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ModBlocks.class)
public class ModBlocksMixin {
    @WrapOperation(
            method = "registerBlocks",
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
        Block result = (Block) original.call(registry, id, entry);
        PolymerBlockUtils.registerOverlay(result, PolymerBlockOverlay.of(result));
        return result;
    }
}