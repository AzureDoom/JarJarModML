package mod.azure.jarjarbinks;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mod.azure.azurelib.neoforge.items.NeoForgeAzureSpawnEgg;
import mod.azure.jarjarbinks.entity.DarthJarJarEntity;
import mod.azure.jarjarbinks.entity.JarJarBinksEntity;
import mod.azure.jarjarbinks.registry.ModEntities;
import mod.azure.jarjarbinks.registry.ModSounds;
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
import net.neoforged.neoforge.event.entity.SpawnPlacementRegisterEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

@Mod(CommonMod.MOD_ID)
public final class NeoForgeMod {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE_DEFERRED_REGISTER = DeferredRegister.create(
            Registries.ENTITY_TYPE, CommonMod.MOD_ID);
    public static final Supplier<EntityType<JarJarBinksEntity>> JARJAR = ENTITY_TYPE_DEFERRED_REGISTER.register(
            "jarjar", () -> ModEntities.JARJAR);
    public static final Supplier<EntityType<DarthJarJarEntity>> DARTHJARJAR = ENTITY_TYPE_DEFERRED_REGISTER.register(
            "darthbinks", () -> ModEntities.DARTHJARJAR);
    public static final DeferredRegister<Item> ITEMS_DEFERRED_REGISTER = DeferredRegister.create(Registries.ITEM,
            CommonMod.MOD_ID);
    public static final Supplier<Item> JARJAR_SPAWN_EGG = ITEMS_DEFERRED_REGISTER.register("jarjar_spawn_egg",
            () -> new NeoForgeAzureSpawnEgg(JARJAR, 0x8f3427, 0xe6b975));
    public static final Supplier<Item> DARTHJARJAR_SPAWN_EGG = ITEMS_DEFERRED_REGISTER.register("darthjarjar_spawn_egg",
            () -> new NeoForgeAzureSpawnEgg(DARTHJARJAR, 0x8d3323, 0xe59b20));
    public static final DeferredRegister<SoundEvent> SOUNDS_DEFERRED_REGISTER = DeferredRegister.create(
            Registries.SOUND_EVENT, CommonMod.MOD_ID);
    public static final Supplier<SoundEvent> JARDEATH = SOUNDS_DEFERRED_REGISTER.register("jarjar.howwude",
            ModSounds.JARDEATH);
    public static final Supplier<SoundEvent> JARNORMAL = SOUNDS_DEFERRED_REGISTER.register("jarjar.jarjarluvsyou",
            ModSounds.JARNORMAL);
    public static final Supplier<SoundEvent> JARHURT = SOUNDS_DEFERRED_REGISTER.register("jarjar.meesadoanuthin",
            ModSounds.JARHURT);
    public static final Supplier<SoundEvent> DARTHDEATH = SOUNDS_DEFERRED_REGISTER.register("jarjar.yousamaystrike",
            ModSounds.DARTHDEATH);
    public static final Supplier<SoundEvent> DARTHNORMAL = SOUNDS_DEFERRED_REGISTER.register("jarjar.yousagonnadie",
            ModSounds.DARTHNORMAL);
    public static final Supplier<SoundEvent> DARTHHURT = SOUNDS_DEFERRED_REGISTER.register(
            "jarjar.uhohyousadidafuckywucky", ModSounds.DARTHHURT);

    public NeoForgeMod(IEventBus modEventBus) {
        ENTITY_TYPE_DEFERRED_REGISTER.register(modEventBus);
        ITEMS_DEFERRED_REGISTER.register(modEventBus);
        SOUNDS_DEFERRED_REGISTER.register(modEventBus);
        ModEntitySpawn.SERIALIZER.register(modEventBus);
        modEventBus.addListener(this::addCreativeTabs);
        modEventBus.addListener(this::createEntityAttributes);
        modEventBus.addListener(this::createSpawnPlacements);
    }

    public void createSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(JARJAR.get(), SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, JarJarBinksEntity::canSpawn,
                SpawnPlacementRegisterEvent.Operation.AND);
        event.register(DARTHJARJAR.get(), SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, JarJarBinksEntity::canSpawn,
                SpawnPlacementRegisterEvent.Operation.AND);
    }

    public void createEntityAttributes(final EntityAttributeCreationEvent event) {
        event.put(JARJAR.get(), JarJarBinksEntity.createMobAttributes().build());
        event.put(DARTHJARJAR.get(), DarthJarJarEntity.createMobAttributes().build());
    }

    public void addCreativeTabs(final BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(JARJAR_SPAWN_EGG.get());
            event.accept(DARTHJARJAR_SPAWN_EGG.get());
        }
    }

    record ModEntitySpawn(HolderSet<Biome> biomes, MobSpawnSettings.SpawnerData spawn) implements BiomeModifier {

        public static DeferredRegister<MapCodec<? extends BiomeModifier>> SERIALIZER = DeferredRegister.create(
                NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, CommonMod.MOD_ID);

        static Supplier<MapCodec<ModEntitySpawn>> JARJAR_SPAWN_CODEC = SERIALIZER.register("mobspawns",
                () -> RecordCodecBuilder.mapCodec(
                        builder -> builder.group(Biome.LIST_CODEC.fieldOf("biomes").forGetter(ModEntitySpawn::biomes),
                                MobSpawnSettings.SpawnerData.CODEC.fieldOf("spawn").forGetter(
                                        ModEntitySpawn::spawn)).apply(builder, ModEntitySpawn::new)));


        @Override
        public void modify(@NotNull Holder<Biome> biome, @NotNull Phase phase, ModifiableBiomeInfo.BiomeInfo.@NotNull Builder builder) {
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
