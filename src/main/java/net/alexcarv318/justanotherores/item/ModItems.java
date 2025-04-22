package net.alexcarv318.justanotherores.item;

import net.alexcarv318.justanotherores.JustAnotherOres;
import net.alexcarv318.justanotherores.item.custom.OresDetectorItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, JustAnotherOres.MODID);

    private static final ResourceKey<Item> SAPPHIRE_KEY = getResourceKey("justanotherores:sapphire");
    private static final ResourceKey<Item> RUBY_KEY = getResourceKey("justanotherores:ruby");
    private static final ResourceKey<Item> ORE_DETECTOR_KEY = getResourceKey("justanotherores:ore_detector");


    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register(
            "sapphire", () -> new Item(new Item.Properties().setId(SAPPHIRE_KEY))
    );
    public static final RegistryObject<Item> RUBY = ITEMS.register(
            "ruby", () -> new Item(new Item.Properties().setId(RUBY_KEY))
    );


    public static final RegistryObject<Item> ORE_DETECTOR = ITEMS.register(
            "ore_detector", () -> new OresDetectorItem(new Item.Properties()
                    .durability(100)
                    .setId(ORE_DETECTOR_KEY)
            )
    );


    protected static ResourceKey<Item> getResourceKey(String key) {
        return ResourceKey.create(Registries.ITEM, ResourceLocation.parse(key));
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
