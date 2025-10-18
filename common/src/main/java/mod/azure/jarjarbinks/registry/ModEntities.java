package mod.azure.jarjarbinks.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.function.Supplier;

import mod.azure.jarjarbinks.CommonMod;
import mod.azure.jarjarbinks.entity.DarthJarJarEntity;
import mod.azure.jarjarbinks.entity.JarJarBinksEntity;

public record ModEntities() implements CommonEntityRegistryInterface {

    public static final Supplier<EntityType<JarJarBinksEntity>> JARJAR = CommonEntityRegistryInterface.registerEntity(
        CommonMod.MOD_ID,
        "jarjar",
        JarJarBinksEntity::new,
        MobCategory.CREATURE,
        0.7f,
        1.95F
    );

    public static final Supplier<EntityType<DarthJarJarEntity>> DARTHJARJAR = CommonEntityRegistryInterface
        .registerEntity(
            CommonMod.MOD_ID,
            "darthbinks",
            DarthJarJarEntity::new,
            MobCategory.MONSTER,
            0.7f,
            1.95F
        );

    public static void init() {}
}
