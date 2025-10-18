package mod.azure.jarjarbinks;

import mod.azure.azurelib.AzureLib;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.levelgen.Heightmap;

import mod.azure.jarjarbinks.entity.DarthJarJarEntity;
import mod.azure.jarjarbinks.entity.JarJarBinksEntity;
import mod.azure.jarjarbinks.registry.ModEntities;
import mod.azure.jarjarbinks.registry.ModItems;

public final class FabricLibMod implements ModInitializer {

    @Override
    public void onInitialize() {
        AzureLib.initialize();
        CommonMod.initRegistries();
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS).register(entries -> {
            entries.accept(ModItems.JARJAR_SPAWN_EGG.get());
            entries.accept(ModItems.DARTHJARJAR_SPAWN_EGG.get());
        });
        FabricDefaultAttributeRegistry.register(ModEntities.JARJAR.get(), JarJarBinksEntity.createMobAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.DARTHJARJAR.get(), DarthJarJarEntity.createMobAttributes());
        addSpawnEntries();
    }

    public static void addSpawnEntries() {
        BiomeModifications.addSpawn(
            BiomeSelectors.tag(CommonMod.JARJAR_BIOMES),
            MobCategory.MONSTER,
            ModEntities.JARJAR.get(),
            4,
            1,
            1
        );
        BiomeModifications.addSpawn(
            BiomeSelectors.tag(CommonMod.DARTH_BIOMES),
            MobCategory.MONSTER,
            ModEntities.DARTHJARJAR.get(),
            4,
            1,
            1
        );
        SpawnPlacements.register(
            ModEntities.JARJAR.get(),
            SpawnPlacementTypes.ON_GROUND,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            JarJarBinksEntity::canSpawn
        );
        SpawnPlacements.register(
            ModEntities.DARTHJARJAR.get(),
            SpawnPlacementTypes.ON_GROUND,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            JarJarBinksEntity::canSpawn
        );
    }
}
