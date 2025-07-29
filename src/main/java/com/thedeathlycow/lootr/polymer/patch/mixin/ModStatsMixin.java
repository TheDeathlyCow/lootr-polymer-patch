package com.thedeathlycow.lootr.polymer.patch.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.thedeathlycow.lootr.polymer.patch.server.PolymerCustomStat;
import net.minecraft.registry.Registry;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import noobanidus.mods.lootr.fabric.init.ModStats;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ModStats.class)
public class ModStatsMixin {
    @WrapOperation(
            method = "registerStats",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/registry/Registry;register(Lnet/minecraft/registry/Registry;Lnet/minecraft/util/Identifier;Ljava/lang/Object;)Ljava/lang/Object;"
            )
    )
    private static Object polymerizeLootrStats(
            Registry<Object> registry,
            Identifier id,
            Object entry,
            Operation<Object> original
    ) {
        Identifier result = (Identifier) original.call(registry, id, entry);
        PolymerCustomStat.registerOverlay(result, new PolymerCustomStat(Stats.OPEN_CHEST));
        return result;
    }
}