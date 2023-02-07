package mod.azure.jarjarbinks;

import mod.azure.azurelib.items.AzureSpawnEgg;
import mod.azure.jarjarbinks.entity.DarthJarJarEntity;
import mod.azure.jarjarbinks.entity.JarJarBinksEntity;
import mod.azure.jarjarbinks.registry.ModEntitySpawning;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTabs;

public class JarJarBinksMod implements ModInitializer {

    public static EntityType<JarJarBinksEntity> JARJAR = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Constants.rl("jarjar"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, JarJarBinksEntity::new).dimensions(EntityDimensions.scalable(0.6f, 1.95F)).build());
    public static EntityType<DarthJarJarEntity> DARTHJARJAR = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Constants.rl("darthbinks"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, DarthJarJarEntity::new).dimensions(EntityDimensions.scalable(0.6f, 1.95F)).build());
    public static final AzureSpawnEgg JARJAR_SPAWN_EGG = new AzureSpawnEgg(JarJarBinksMod.JARJAR, 16753920, 16777215);
    public static final AzureSpawnEgg DARTHJARJAR_SPAWN_EGG = new AzureSpawnEgg(JarJarBinksMod.DARTHJARJAR, 16753920, 16777215);

    @Override
    public void onInitialize() {
        CommonClass.init();
        ModEntitySpawning.addSpawnEntries();
        Registry.register(BuiltInRegistries.ITEM, Constants.rl("jarjar_spawn_egg"),
                JARJAR_SPAWN_EGG);
        Registry.register(BuiltInRegistries.ITEM, Constants.rl("darthjarjar_spawn_egg"),
                DARTHJARJAR_SPAWN_EGG);
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS)
                .register(entries -> entries.accept(JARJAR_SPAWN_EGG));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS)
                .register(entries -> entries.accept(DARTHJARJAR_SPAWN_EGG));
        FabricDefaultAttributeRegistry.register(JARJAR, JarJarBinksEntity.createMobAttributes());
        FabricDefaultAttributeRegistry.register(DARTHJARJAR, DarthJarJarEntity.createMobAttributes());
    }
}
