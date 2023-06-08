package mod.azure.jarjarbinks;

import mod.azure.azurelib.items.AzureSpawnEgg;
import mod.azure.jarjarbinks.client.renders.DarthJarJarRender;
import mod.azure.jarjarbinks.client.renders.JarJarRender;
import mod.azure.jarjarbinks.entity.DarthJarJarEntity;
import mod.azure.jarjarbinks.entity.JarJarBinksEntity;
import mod.azure.jarjarbinks.registry.ModEntitySpawn;
import mod.azure.jarjarbinks.registry.ModSoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(Constants.MOD_ID)
public class JarJarBinksMod {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Constants.MOD_ID);
    public static final DeferredRegister<Item> ITEMS_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUNDS_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Constants.MOD_ID);
    public static final RegistryObject<EntityType<JarJarBinksEntity>> JARJAR = ENTITY_TYPE_DEFERRED_REGISTER.register("jarjar",
            () -> EntityType.Builder.of(JarJarBinksEntity::new, MobCategory.MONSTER).sized(0.6f, 1.95F).build("jarjar"));
    public static final RegistryObject<Item> JARJAR_SPAWN_EGG = ITEMS_DEFERRED_REGISTER.register("jarjar_spawn_egg",
            () -> new AzureSpawnEgg(JarJarBinksMod.JARJAR, 0x8f3427, 0xe6b975));
    public static final RegistryObject<EntityType<DarthJarJarEntity>> DARTHJARJAR = ENTITY_TYPE_DEFERRED_REGISTER.register("darthbinks",
            () -> EntityType.Builder.of(DarthJarJarEntity::new, MobCategory.MONSTER).sized(0.6f, 1.95F).build("darthbinks"));
    public static final RegistryObject<Item> DARTHJARJAR_SPAWN_EGG = ITEMS_DEFERRED_REGISTER.register("darthjarjar_spawn_egg",
            () -> new AzureSpawnEgg(JarJarBinksMod.DARTHJARJAR, 0x8d3323, 0xe59b20));
    public static final RegistryObject<SoundEvent> JARDEATH = SOUNDS_DEFERRED_REGISTER.register("jarjar.howwude",
            () -> ModSoundEvents.JARDEATH.createVariableRangeEvent(Constants.rl("jarjar.howwude")));
    public static final RegistryObject<SoundEvent> JARNORMAL = SOUNDS_DEFERRED_REGISTER.register("jarjar.jarjarluvsyou",
            () -> ModSoundEvents.JARNORMAL.createVariableRangeEvent(Constants.rl("jarjar.jarjarluvsyou")));
    public static final RegistryObject<SoundEvent> JARHURT = SOUNDS_DEFERRED_REGISTER.register("jarjar.meesadoanuthin",
            () -> ModSoundEvents.JARHURT.createVariableRangeEvent(Constants.rl("jarjar.meesadoanuthin")));
    public static final RegistryObject<SoundEvent> DARTHDEATH = SOUNDS_DEFERRED_REGISTER.register("jarjar.yousamaystrike",
            () -> ModSoundEvents.DARTHDEATH.createVariableRangeEvent(Constants.rl("jarjar.yousamaystrike")));
    public static final RegistryObject<SoundEvent> DARTHNORMAL = SOUNDS_DEFERRED_REGISTER.register("jarjar.yousagonnadie",
            () -> ModSoundEvents.DARTHNORMAL.createVariableRangeEvent(Constants.rl("jarjar.yousagonnadie")));
    public static final RegistryObject<SoundEvent> DARTHHURT = SOUNDS_DEFERRED_REGISTER.register("jarjar.uhohyousadidafuckywucky",
            () -> ModSoundEvents.DARTHHURT.createVariableRangeEvent(Constants.rl("jarjar.uhohyousadidafuckywucky")));

    public JarJarBinksMod() {
        CommonClass.init();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ENTITY_TYPE_DEFERRED_REGISTER.register(bus);
        ITEMS_DEFERRED_REGISTER.register(bus);
        SOUNDS_DEFERRED_REGISTER.register(bus);
        bus.addListener(this::setupCommon);
        bus.addListener(this::addCreativeTabs);
        bus.addListener(this::createTestEntityAttributes);
        ModEntitySpawn.SERIALIZER.register(bus);
    }

    public void createTestEntityAttributes(final EntityAttributeCreationEvent event) {
        event.put(JARJAR.get(), JarJarBinksEntity.createMobAttributes().build());
        event.put(DARTHJARJAR.get(), DarthJarJarEntity.createMobAttributes().build());
    }

    public void setupCommon(final FMLCommonSetupEvent event) {
        ModSoundEvents.JARDEATH = JARDEATH.get();
        ModSoundEvents.JARNORMAL = JARNORMAL.get();
        ModSoundEvents.JARHURT = JARHURT.get();
        ModSoundEvents.DARTHDEATH = DARTHDEATH.get();
        ModSoundEvents.DARTHNORMAL = DARTHNORMAL.get();
        ModSoundEvents.DARTHHURT = DARTHHURT.get();
    }

    public void addCreativeTabs(final BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(JARJAR_SPAWN_EGG.get());
            event.accept(DARTHJARJAR_SPAWN_EGG.get());
        }
    }

    @Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    private static class ClientModListener {
        @SubscribeEvent
        public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(JARJAR.get(), JarJarRender::new);
            event.registerEntityRenderer(DARTHJARJAR.get(), DarthJarJarRender::new);
        }
    }

}