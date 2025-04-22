package net.alexcarv318.justanotherores.item.custom;

import net.alexcarv318.justanotherores.block.ModBlocks;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Arrays;

public class OresDetectorItem extends Item {
    private final Block[] TARGET_BLOCKS = {
            Blocks.IRON_ORE,
            Blocks.DIAMOND_ORE,
            Blocks.GOLD_ORE,
            Blocks.EMERALD_ORE,
            ModBlocks.RUBY_ORE.get(),
            ModBlocks.SAPPHIRE_ORE.get(),
    };

    public OresDetectorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();

        if (!level.isClientSide) {
            BlockPos clickedPosition = pContext.getClickedPos();
            ServerPlayer player = (ServerPlayer) pContext.getPlayer();
            assert player != null;
            boolean foundBlock = false;

            for (int i = 0; i <= clickedPosition.getY() + 64; i++) {
                BlockState state = pContext.getLevel().getBlockState(clickedPosition.below(i));

                if (isValuableBlock(state)) {
                    outputValuableCoordinates(clickedPosition.below(i), player, state.getBlock());
                    foundBlock = true;
                    break;
                }
            }

            if (!foundBlock) {
                Component msg = Component.literal("No ores found!");
                player.sendSystemMessage(msg);
            }

            hurtAndBreak(pContext.getItemInHand(), (ServerLevel) level, player);
        }

        return InteractionResult.SUCCESS;
    }

    private void outputValuableCoordinates(BlockPos blockPos, ServerPlayer player, Block block) {
        Component msg = Component.literal(
            "Found " + I18n.get(block.getDescriptionId()) + " at " + "(" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"
        );
        player.sendSystemMessage(msg);
    }

    private boolean isValuableBlock(BlockState state) {
        Block currentBlock = state.getBlock();
        return Arrays.asList(TARGET_BLOCKS).contains(currentBlock);
    }

    private void hurtAndBreak(ItemStack itemStack, ServerLevel level, ServerPlayer player) {
        itemStack.hurtAndBreak(1, level, player,
                item -> player.onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
    }
}
