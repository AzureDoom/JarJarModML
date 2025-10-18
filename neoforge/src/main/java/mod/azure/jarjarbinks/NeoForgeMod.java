package mod.azure.jarjarbinks;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mod.azure.azurelib.AzureLib;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

import mod.azure.jarjarbinks.entity.DarthJarJarEntity;
import mod.azure.jarjarbinks.entity.JarJarBinksEntity;
import mod.azure.jarjarbinks.registry.ModEntities;
import mod.azure.jarjarbinks.registry.ModItems;

@Mod(CommonMod.MOD_ID)
public final class NeoForgeMod {

    public static DeferredRegister<EntityType<?>> entityTypeDeferredRegister = DeferredRegister.create(
        Registries.ENTITY_TYPE,
        CommonMod.MOD_ID
    );

    public static DeferredRegister<Item> itemDeferredRegister = DeferredRegister.create(
        Registries.ITEM,
        CommonMod.MOD_ID
    );

    public static DeferredRegister<SoundEvent> soundEventDeferredRegister = DeferredRegister.create(
        Registries.SOUND_EVENT,
        CommonMod.MOD_ID
    );

    public NeoForgeMod(IEventBus modEventBus) {
        AzureLib.initialize();
        CommonMod.initRegistries();
        ModEntitySpawn.SERIALIZER.register(modEventBus);
        if (NeoForgeMod.entityTypeDeferredRegister != null)
            NeoForgeMod.entityTypeDeferredRegister.register(modEventBus);
        if (NeoForgeMod.itemDeferredRegister != null)
            NeoForgeMod.itemDeferredRegister.register(modEventBus);
        if (NeoForgeMod.soundEventDeferredRegister != null)
            NeoForgeMod.soundEventDeferredRegister.register(modEventBus);
        modEventBus.addListener(this::addCreativeTabs);
        modEventBus.addListener(this::createEntityAttributes);
        modEventBus.addListener(this::createSpawnPlacements);
    }

    public void createSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(
            ModEntities.JARJAR.get(),
            SpawnPlacementTypes.ON_GROUND,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            JarJarBinksEntity::canSpawn,
            RegisterSpawnPlacementsEvent.Operation.AND
        );
        event.register(
            ModEntities.DARTHJARJAR.get(),
            SpawnPlacementTypes.ON_GROUND,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            JarJarBinksEntity::canSpawn,
            RegisterSpawnPlacementsEvent.Operation.AND
        );
    }

    public void createEntityAttributes(final EntityAttributeCreationEvent event) {
        event.put(ModEntities.JARJAR.get(), JarJarBinksEntity.createMobAttributes().build());
        event.put(ModEntities.DARTHJARJAR.get(), DarthJarJarEntity.createMobAttributes().build());
    }

    public void addCreativeTabs(final BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(ModItems.JARJAR_SPAWN_EGG.get());
            event.accept(ModItems.DARTHJARJAR_SPAWN_EGG.get());
        }
    }

    record ModEntitySpawn(
        HolderSet<Biome> biomes,
        MobSpawnSettings.SpawnerData spawn
    ) implements BiomeModifier {

        public static DeferredRegister<MapCodec<? extends BiomeModifier>> SERIALIZER = DeferredRegister.create(
            NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS,
            CommonMod.MOD_ID
        );

        static Supplier<MapCodec<ModEntitySpawn>> JARJAR_SPAWN_CODEC = SERIALIZER.register(
            "mobspawns",
            () -> RecordCodecBuilder.mapCodec(
                builder -> builder.group(
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(ModEntitySpawn::biomes),
                    MobSpawnSettings.SpawnerData.CODEC.fieldOf("spawn")
                        .forGetter(
                            ModEntitySpawn::spawn
                        )
                ).apply(builder, ModEntitySpawn::new)
            )
        );

        @Override
        public void modify(
            @NotNull Holder<Biome> biome,
            @NotNull Phase phase,
            ModifiableBiomeInfo.BiomeInfo.@NotNull Builder builder
        ) {
            if (phase == Phase.ADD && this.biomes.contains(biome)) {
                builder.getMobSpawnSettings().addSpawn(MobCategory.MONSTER, this.spawn);
            }
        }

        @Override
        public @NotNull MapCodec<? extends BiomeModifier> codec() {
            return JARJAR_SPAWN_CODEC.get();
        }
    }
}
