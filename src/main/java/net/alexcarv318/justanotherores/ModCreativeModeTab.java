package net.alexcarv318.justanotherores;

import net.alexcarv318.justanotherores.block.ModBlocks;
import net.alexcarv318.justanotherores.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, JustAnotherOres.MODID);

    public static final RegistryObject<CreativeModeTab> JUST_ANOTHER_ORES_TAB = CREATIVE_MODE_TABS.register("justanotherorestab", () -> CreativeModeTab.builder()
            .title(Component.translatable("creativetab.justanotherorestab"))
            .icon(() -> ModItems.SAPPHIRE.get().getDefaultInstance())
            .displayItems((featureFlagSet, output) -> {
                output.accept(ModItems.SAPPHIRE.get());
                output.accept(ModBlocks.SAPPHIRE_BLOCK.get());
                output.accept(ModBlocks.SAPPHIRE_ORE.get());

                output.accept(ModItems.RUBY.get());
                output.accept(ModBlocks.RUBY_BLOCK.get());
                output.accept(ModBlocks.RUBY_ORE.get());

                output.accept(ModItems.ORE_DETECTOR.get());
            })
            .build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
