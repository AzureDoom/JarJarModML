package mod.azure.jarjarbinks.registry;

import mod.azure.jarjarbinks.entity.DarthJarJarEntity;
import mod.azure.jarjarbinks.entity.JarJarBinksEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public record ModEntities() {
    public static EntityType<JarJarBinksEntity> JARJAR = EntityType.Builder.of(JarJarBinksEntity::new,
            MobCategory.CREATURE).sized(0.6f, 1.95F).build("jarjar");
    public static EntityType<DarthJarJarEntity> DARTHJARJAR = EntityType.Builder.of(DarthJarJarEntity::new,
            MobCategory.MONSTER).sized(0.6f, 1.95F).build("darthbinks");
}
