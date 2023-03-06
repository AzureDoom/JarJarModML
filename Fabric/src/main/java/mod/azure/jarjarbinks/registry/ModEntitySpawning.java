package mod.azure.jarjarbinks.registry;

import mod.azure.jarjarbinks.JarJarBinksMod;
import mod.azure.jarjarbinks.config.JarJarConfig;
import mod.azure.jarjarbinks.entity.JarJarBinksEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.List;

public class ModEntitySpawning {

    public static void addSpawnEntries() {
        BiomeModifications.addSpawn(BiomeSelectors.all().and(context -> parseBiomes(JarJarConfig.jarjar_biomes, context)), MobCategory.MONSTER,
                JarJarBinksMod.JARJAR, 50, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.all().and(context -> parseBiomes(JarJarConfig.darthjarjar_biomes, context)), MobCategory.MONSTER,
                JarJarBinksMod.DARTHJARJAR, 50, 1, 1);
        SpawnPlacements.register(JarJarBinksMod.JARJAR, SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, JarJarBinksEntity::canSpawn);
        SpawnPlacements.register(JarJarBinksMod.DARTHJARJAR, SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, JarJarBinksEntity::canSpawn);
    }

    private static boolean parseBiomes(List<String> biomes, BiomeSelectionContext biomeContext) {
        return biomes.contains(biomeContext.getBiomeKey().location().toString());
    }
}
