package com.thedeathlycow.lootr.polymer.patch.block;

import com.thedeathlycow.lootr.polymer.patch.LootrPolymerPatch;
import eu.pb4.polymer.core.api.block.PolymerBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import noobanidus.mods.lootr.common.block.LootrChestBlock;
import noobanidus.mods.lootr.common.block.LootrInventoryBlock;
import noobanidus.mods.lootr.common.block.LootrShulkerBlock;
import noobanidus.mods.lootr.common.block.LootrTrappedChestBlock;
import noobanidus.mods.lootr.fabric.block.LootrFabricBarrelBlock;
import xyz.nucleoid.packettweaker.PacketContext;

public record PolymerBlockOverlay(Block clientBlock) implements PolymerBlock {

    public static PolymerBlockOverlay of(Block serverBlock) {
        Block clientBlock = switch (serverBlock) {
            case LootrChestBlock ignored -> Blocks.CHEST;
            case LootrFabricBarrelBlock ignored -> Blocks.BARREL;
            case LootrTrappedChestBlock ignored -> Blocks.TRAPPED_CHEST;
            case LootrInventoryBlock ignored -> Blocks.CHEST;
            case LootrShulkerBlock ignored -> Blocks.SHULKER_BOX;
            default -> {
                LootrPolymerPatch.LOGGER.error("Unknown lootr block, defaulting to stone: {}", serverBlock);
                yield Blocks.STONE;
            }
        };

        return new PolymerBlockOverlay(clientBlock);
    }


    @Override
    public BlockState getPolymerBlockState(BlockState state, PacketContext context) {
        return clientBlock.getStateWithProperties(state);
    }
}