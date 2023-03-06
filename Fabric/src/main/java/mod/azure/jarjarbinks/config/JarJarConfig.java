package mod.azure.jarjarbinks.config;

import eu.midnightdust.lib.config.MidnightConfig;

import java.util.Arrays;
import java.util.List;

public class JarJarConfig extends MidnightConfig {

    @Entry
    public static List<String> jarjar_biomes = Arrays.asList("minecraft:mangrove_swamp", "minecraft:swamp");

    @Entry
    public static List<String> darthjarjar_biomes = Arrays.asList("minecraft:basalt_deltas", "minecraft:crimson_forest",
            "minecraft:nether_wastes", "minecraft:soul_sand_valley", "minecraft:warped_forest");
}
