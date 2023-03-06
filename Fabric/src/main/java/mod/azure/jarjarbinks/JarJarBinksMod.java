package mod.azure.jarjarbinks;

import eu.midnightdust.lib.config.MidnightConfig;
import mod.azure.azurelib.items.AzureSpawnEgg;
import mod.azure.jarjarbinks.config.JarJarConfig;
import mod.azure.jarjarbinks.entity.DarthJarJarEntity;
import mod.azure.jarjarbinks.entity.JarJarBinksEntity;
import mod.azure.jarjarbinks.registry.ModEntitySpawning;
import mod.azure.jarjarbinks.registry.ModSoundEvents;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTabs;

public class JarJarBinksMod implements ModInitializer {

    public static EntityType<JarJarBinksEntity> JARJAR = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Constants.rl("jarjar"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, JarJarBinksEntity::new).dimensions(EntityDimensions.scalable(0.6f, 1.95F)).build());
    public static final AzureSpawnEgg JARJAR_SPAWN_EGG = new AzureSpawnEgg(JarJarBinksMod.JARJAR, 0x8f3427, 0xe6b975);
    public static EntityType<DarthJarJarEntity> DARTHJARJAR = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Constants.rl("darthbinks"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, DarthJarJarEntity::new).dimensions(EntityDimensions.scalable(0.6f, 1.95F)).build());
    public static final AzureSpawnEgg DARTHJARJAR_SPAWN_EGG = new AzureSpawnEgg(JarJarBinksMod.DARTHJARJAR, 0x8d3323, 0xe59b20);

    static SoundEvent of(String id) {
        SoundEvent sound = SoundEvent.createVariableRangeEvent(new ResourceLocation(Constants.MOD_ID, id));
        Registry.register(BuiltInRegistries.SOUND_EVENT, new ResourceLocation(Constants.MOD_ID, id), sound);
        return sound;
    }

    @Override
    public void onInitialize() {
        CommonClass.init();
        MidnightConfig.init(Constants.MOD_ID, JarJarConfig.class);
        ModEntitySpawning.addSpawnEntries();
        Registry.register(BuiltInRegistries.ITEM, Constants.rl("jarjar_spawn_egg"),
                JARJAR_SPAWN_EGG);
        Registry.register(BuiltInRegistries.ITEM, Constants.rl("darthjarjar_spawn_egg"),
                DARTHJARJAR_SPAWN_EGG);
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS)
                .register(entries -> entries.accept(JARJAR_SPAWN_EGG));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS)
                .register(entries -> entries.accept(DARTHJARJAR_SPAWN_EGG));
        ModSoundEvents.JARDEATH = of("jarjar.howwude");
        ModSoundEvents.JARNORMAL = of("jarjar.jarjarluvsyou");
        ModSoundEvents.JARHURT = of("jarjar.meesadoanuthin");
        ModSoundEvents.DARTHDEATH = of("jarjar.yousamaystrike");
        ModSoundEvents.DARTHNORMAL = of("jarjar.yousagonnadie");
        ModSoundEvents.DARTHHURT = of("jarjar.uhohyousadidafuckywucky");
        FabricDefaultAttributeRegistry.register(JARJAR, JarJarBinksEntity.createMobAttributes());
        FabricDefaultAttributeRegistry.register(DARTHJARJAR, DarthJarJarEntity.createMobAttributes());
    }
}
