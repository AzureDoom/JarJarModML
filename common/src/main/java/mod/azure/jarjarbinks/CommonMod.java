package mod.azure.jarjarbinks;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class CommonMod {
    public static final String MOD_ID = "jarjarbinks";
    public static final TagKey<Biome> JARJAR_BIOMES = TagKey.create(Registries.BIOME,
            CommonMod.modResource("jarjarbiomes"));
    public static final TagKey<Biome> DARTH_BIOMES = TagKey.create(Registries.BIOME,
            CommonMod.modResource("darthbiomes"));

    public static ResourceLocation modResource(String name) {
        return new ResourceLocation(MOD_ID, name);
    }
}
