package mod.azure.jarjarbinks.registry;

import mod.azure.jarjarbinks.CommonClass;
import mod.azure.jarjarbinks.JarJarBinksMod;
import mod.azure.jarjarbinks.entity.JarJarBinksEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.List;

public class ModEntitySpawning {

    public static void addSpawnEntries() {
        BiomeModifications.addSpawn(BiomeSelectors.tag(CommonClass.JARJAR_BIOMES), MobCategory.MONSTER,
                JarJarBinksMod.JARJAR, 4, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.tag(CommonClass.DARTH_BIOMES), MobCategory.MONSTER,
                JarJarBinksMod.DARTHJARJAR, 4, 1, 1);
        SpawnPlacements.register(JarJarBinksMod.JARJAR, SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, JarJarBinksEntity::canSpawn);
        SpawnPlacements.register(JarJarBinksMod.DARTHJARJAR, SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, JarJarBinksEntity::canSpawn);
    }
}
