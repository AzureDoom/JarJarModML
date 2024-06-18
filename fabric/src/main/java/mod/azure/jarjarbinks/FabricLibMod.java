package mod.azure.jarjarbinks;

import mod.azure.jarjarbinks.entity.DarthJarJarEntity;
import mod.azure.jarjarbinks.entity.JarJarBinksEntity;
import mod.azure.jarjarbinks.registry.ModEntities;
import mod.azure.jarjarbinks.registry.ModItems;
import mod.azure.jarjarbinks.registry.ModSounds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.levelgen.Heightmap;

public final class FabricLibMod implements ModInitializer {

    @Override
    public void onInitialize() {
        Registry.register(BuiltInRegistries.ITEM, CommonMod.modResource("jarjar_spawn_egg"), ModItems.JARJAR_SPAWN_EGG);
        Registry.register(BuiltInRegistries.ITEM, CommonMod.modResource("darthjarjar_spawn_egg"),
                ModItems.DARTHJARJAR_SPAWN_EGG);
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS).register(entries -> {
            entries.accept(ModItems.JARJAR_SPAWN_EGG);
            entries.accept(ModItems.DARTHJARJAR_SPAWN_EGG);
        });
        Registry.register(BuiltInRegistries.SOUND_EVENT, CommonMod.modResource("jarjar.howwude"),
                ModSounds.JARDEATH.get());
        Registry.register(BuiltInRegistries.SOUND_EVENT, CommonMod.modResource("jarjar.jarjarluvsyou"),
                ModSounds.JARNORMAL.get());
        Registry.register(BuiltInRegistries.SOUND_EVENT, CommonMod.modResource("jarjar.meesadoanuthin"),
                ModSounds.JARHURT.get());
        Registry.register(BuiltInRegistries.SOUND_EVENT, CommonMod.modResource("jarjar.yousamaystrike"),
                ModSounds.DARTHDEATH.get());
        Registry.register(BuiltInRegistries.SOUND_EVENT, CommonMod.modResource("jarjar.yousagonnadie"),
                ModSounds.DARTHNORMAL.get());
        Registry.register(BuiltInRegistries.SOUND_EVENT, CommonMod.modResource("jarjar.uhohyousadidafuckywucky"),
                ModSounds.DARTHHURT.get());
        Registry.register(BuiltInRegistries.ENTITY_TYPE, CommonMod.modResource("jarjar"), ModEntities.JARJAR);
        Registry.register(BuiltInRegistries.ENTITY_TYPE, CommonMod.modResource("darthbinks"), ModEntities.DARTHJARJAR);
        FabricDefaultAttributeRegistry.register(ModEntities.JARJAR, JarJarBinksEntity.createMobAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.DARTHJARJAR, DarthJarJarEntity.createMobAttributes());
        addSpawnEntries();
    }

    public static void addSpawnEntries() {
        BiomeModifications.addSpawn(BiomeSelectors.tag(CommonMod.JARJAR_BIOMES), MobCategory.MONSTER,
                ModEntities.JARJAR, 50, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.tag(CommonMod.DARTH_BIOMES), MobCategory.MONSTER,
                ModEntities.DARTHJARJAR, 50, 1, 1);
        SpawnPlacements.register(ModEntities.JARJAR, SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, JarJarBinksEntity::canSpawn);
        SpawnPlacements.register(ModEntities.DARTHJARJAR, SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, JarJarBinksEntity::canSpawn);
    }
}
