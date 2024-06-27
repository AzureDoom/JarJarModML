package mod.azure.jarjarbinks;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mod.azure.azurelib.common.internal.common.AzureLib;
import mod.azure.jarjarbinks.entity.DarthJarJarEntity;
import mod.azure.jarjarbinks.entity.JarJarBinksEntity;
import mod.azure.jarjarbinks.registry.ModEntities;
import mod.azure.jarjarbinks.registry.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.StructureType;
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
    public static DeferredRegister<BlockEntityType<?>> blockEntityTypeDeferredRegister = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, CommonMod.MOD_ID);
    public static DeferredRegister<Block> blockDeferredRegister = DeferredRegister.create(Registries.BLOCK, CommonMod.MOD_ID);
    public static DeferredRegister<EntityType<?>> entityTypeDeferredRegister = DeferredRegister.create(Registries.ENTITY_TYPE, CommonMod.MOD_ID);
    public static DeferredRegister<ArmorMaterial> armorMaterialDeferredRegister = DeferredRegister.create(Registries.ARMOR_MATERIAL, CommonMod.MOD_ID);
    public static DeferredRegister<Item> itemDeferredRegister = DeferredRegister.create(Registries.ITEM, CommonMod.MOD_ID);
    public static DeferredRegister<SoundEvent> soundEventDeferredRegister= DeferredRegister.create(Registries.SOUND_EVENT, CommonMod.MOD_ID);
    public static DeferredRegister<MenuType<?>> menuTypeDeferredRegister= DeferredRegister.create(Registries.MENU, CommonMod.MOD_ID);
    public static DeferredRegister<StructureType<?>> structureTypeDeferredRegister = DeferredRegister.create(Registries.STRUCTURE_TYPE, CommonMod.MOD_ID);
    public static DeferredRegister<ParticleType<?>> particleTypeDeferredRegister = DeferredRegister.create(Registries.PARTICLE_TYPE, CommonMod.MOD_ID);
    public static DeferredRegister<CreativeModeTab> creativeModeTabDeferredRegister = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CommonMod.MOD_ID);

    public NeoForgeMod(IEventBus modEventBus) {
        AzureLib.initialize();
        CommonMod.initRegistries();
        ModEntitySpawn.SERIALIZER.register(modEventBus);
        if (NeoForgeMod.blockEntityTypeDeferredRegister != null)
            NeoForgeMod.blockEntityTypeDeferredRegister.register(modEventBus);
        if (NeoForgeMod.blockDeferredRegister != null)
            NeoForgeMod.blockDeferredRegister.register(modEventBus);
        if (NeoForgeMod.entityTypeDeferredRegister != null)
            NeoForgeMod.entityTypeDeferredRegister.register(modEventBus);
        if (NeoForgeMod.armorMaterialDeferredRegister != null)
            NeoForgeMod.armorMaterialDeferredRegister.register(modEventBus);
        if (NeoForgeMod.itemDeferredRegister != null)
            NeoForgeMod.itemDeferredRegister.register(modEventBus);
        if (NeoForgeMod.soundEventDeferredRegister != null)
            NeoForgeMod.soundEventDeferredRegister.register(modEventBus);
        if (NeoForgeMod.menuTypeDeferredRegister != null)
            NeoForgeMod.menuTypeDeferredRegister.register(modEventBus);
        if (NeoForgeMod.structureTypeDeferredRegister != null)
            NeoForgeMod.structureTypeDeferredRegister.register(modEventBus);
        if (NeoForgeMod.particleTypeDeferredRegister != null)
            NeoForgeMod.particleTypeDeferredRegister.register(modEventBus);
        if (NeoForgeMod.creativeModeTabDeferredRegister != null)
            NeoForgeMod.creativeModeTabDeferredRegister.register(modEventBus);
        modEventBus.addListener(this::addCreativeTabs);
        modEventBus.addListener(this::createEntityAttributes);
        modEventBus.addListener(this::createSpawnPlacements);
    }

    public void createSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(ModEntities.JARJAR.get(), SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, JarJarBinksEntity::canSpawn,
                SpawnPlacementRegisterEvent.Operation.AND);
        event.register(ModEntities.DARTHJARJAR.get(), SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, JarJarBinksEntity::canSpawn,
                SpawnPlacementRegisterEvent.Operation.AND);
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
