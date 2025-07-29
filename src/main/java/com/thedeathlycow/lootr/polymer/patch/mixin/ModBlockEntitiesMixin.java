package com.thedeathlycow.lootr.polymer.patch.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import eu.pb4.polymer.core.api.block.PolymerBlockUtils;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import noobanidus.mods.lootr.fabric.init.ModBlockEntities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ModBlockEntities.class)
public class ModBlockEntitiesMixin {
    @WrapOperation(
            method = "registerBlockEntities",
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
        BlockEntityType<?> result = (BlockEntityType<?>) original.call(registry, id, entry);
        PolymerBlockUtils.registerBlockEntity(result);
        return result;
    }
}