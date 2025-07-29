package com.thedeathlycow.lootr.polymer.patch.mixin;

import eu.pb4.polymer.core.api.block.PolymerBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import noobanidus.mods.lootr.common.block.LootrChestBlock;
import noobanidus.mods.lootr.common.block.LootrInventoryBlock;
import noobanidus.mods.lootr.common.block.LootrShulkerBlock;
import noobanidus.mods.lootr.common.block.LootrTrappedChestBlock;
import noobanidus.mods.lootr.fabric.block.LootrFabricBarrelBlock;
import org.spongepowered.asm.mixin.Mixin;
import xyz.nucleoid.packettweaker.PacketContext;

@Mixin({LootrChestBlock.class, LootrFabricBarrelBlock.class, LootrTrappedChestBlock.class, LootrInventoryBlock.class, LootrShulkerBlock.class})
public class LootrContainerBlockMixin implements PolymerBlock {
    @Override
    public BlockState getPolymerBlockState(BlockState state, PacketContext packetContext) {
        Object self = this;

        Block clientBlock = switch (self) {
            case LootrChestBlock ignored -> Blocks.CHEST;
            case LootrFabricBarrelBlock ignored -> Blocks.BARREL;
            case LootrTrappedChestBlock ignored -> Blocks.TRAPPED_CHEST;
            case LootrInventoryBlock ignored -> Blocks.CHEST;
            case LootrShulkerBlock ignored -> Blocks.SHULKER_BOX;
            default -> throw new IllegalStateException("Unpatched Lootr container type: " + this);
        };

        return clientBlock.getStateWithProperties(state);
    }
}