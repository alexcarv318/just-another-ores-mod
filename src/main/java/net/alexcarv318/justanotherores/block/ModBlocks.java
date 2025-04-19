package net.alexcarv318.justanotherores.block;

import net.alexcarv318.justanotherores.JustAnotherOres;
import net.alexcarv318.justanotherores.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, JustAnotherOres.MODID);

    private static final ResourceKey<Block> SAPPHIRE_BLOCK_KEY = getResourceKey("justanotherores:sapphire_block");
    private static final ResourceKey<Block> RAW_SAPPHIRE_BLOCK_KEY = getResourceKey("justanotherores:raw_sapphire_block");
    private static final ResourceKey<Block> RUBY_BLOCK_KEY = getResourceKey("justanotherores:ruby_block");
    private static final ResourceKey<Block> RAW_RUBY_BLOCK_KEY = getResourceKey("justanotherores:raw_ruby_block");


    public static final RegistryObject<Block> SAPPHIRE_BLOCK = registerBlock(
    "sapphire_block", () -> new Block(
                BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).setId(SAPPHIRE_BLOCK_KEY).sound(SoundType.AMETHYST)
        )
    );

    public static final RegistryObject<Block> RAW_SAPPHIRE_BLOCK = registerBlock(
        "raw_sapphire_block", () -> new Block(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).setId(RAW_SAPPHIRE_BLOCK_KEY)
            )
    );

    public static final RegistryObject<Block> RUBY_BLOCK = registerBlock(
            "ruby_block", () -> new Block(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).setId(RUBY_BLOCK_KEY).sound(SoundType.NETHERITE_BLOCK)
            )
    );

    public static final RegistryObject<Block> RAW_RUBY_BLOCK = registerBlock(
            "raw_ruby_block", () -> new Block(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK).setId(RAW_RUBY_BLOCK_KEY).sound(SoundType.AMETHYST)
            )
    );


    protected static ResourceKey<Block> getResourceKey(String key) {
        return ResourceKey.create(Registries.BLOCK, ResourceLocation.parse(key));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> registeredBlock = BLOCKS.register(name, block);
        registerBlockItem(name, registeredBlock);
        return registeredBlock;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        ResourceKey<Item> blockKey = ResourceKey.create(
                Registries.ITEM, ResourceLocation.parse("justanotherores:%s".formatted(name))
        );
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().setId(blockKey)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
