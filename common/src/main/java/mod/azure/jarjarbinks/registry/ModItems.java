package mod.azure.jarjarbinks.registry;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

import java.util.function.Supplier;

import mod.azure.jarjarbinks.CommonMod;
import mod.azure.jarjarbinks.platform.Services;

public record ModItems() implements CommonItemRegistryInterface {

    public static final Supplier<SpawnEggItem> JARJAR_SPAWN_EGG = CommonItemRegistryInterface.registerItem(
        CommonMod.MOD_ID,
        "jarjar_spawn_egg",
        Services.COMMON_REGISTRY.makeSpawnEggFor(
            ModEntities.JARJAR,
            0x8f3427,
            0xe6b975,
            new Item.Properties()
        )
    );

    public static final Supplier<SpawnEggItem> DARTHJARJAR_SPAWN_EGG = CommonItemRegistryInterface.registerItem(
        CommonMod.MOD_ID,
        "darthjarjar_spawn_egg",
        Services.COMMON_REGISTRY.makeSpawnEggFor(
            ModEntities.DARTHJARJAR,
            0x8d3323,
            0xe59b20,
            new Item.Properties()
        )
    );

    public static void init() {}

}
