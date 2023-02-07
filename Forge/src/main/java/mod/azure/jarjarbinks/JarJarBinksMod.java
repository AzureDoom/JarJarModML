package mod.azure.jarjarbinks;

import mod.azure.azurelib.items.AzureSpawnEgg;
import mod.azure.jarjarbinks.client.renders.DarthJarJarRender;
import mod.azure.jarjarbinks.client.renders.JarJarRender;
import mod.azure.jarjarbinks.entity.DarthJarJarEntity;
import mod.azure.jarjarbinks.entity.JarJarBinksEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.CreativeModeTabEvent;
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
    public static final RegistryObject<EntityType<JarJarBinksEntity>> JARJAR = ENTITY_TYPE_DEFERRED_REGISTER.register("jarjar",
            () -> EntityType.Builder.<JarJarBinksEntity>of(JarJarBinksEntity::new, MobCategory.MONSTER).sized(0.6f, 1.95F).build("jarjar"));
    public static final RegistryObject<EntityType<DarthJarJarEntity>> DARTHJARJAR = ENTITY_TYPE_DEFERRED_REGISTER.register("darthbinks",
            () -> EntityType.Builder.<DarthJarJarEntity>of(DarthJarJarEntity::new, MobCategory.MONSTER).sized(0.6f, 1.95F).build("darthbinks"));

    public static final DeferredRegister<Item> ITEMS_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);
    public static final RegistryObject<Item> JARJAR_SPAWN_EGG = ITEMS_DEFERRED_REGISTER.register("jarjar_spawn_egg",
            () -> new AzureSpawnEgg(JarJarBinksMod.JARJAR, 11022961, 11035249));
    public static final RegistryObject<Item> DARTHJARJAR_SPAWN_EGG = ITEMS_DEFERRED_REGISTER.register("jarjar_spawn_egg",
            () -> new AzureSpawnEgg(JarJarBinksMod.DARTHJARJAR, 11022961, 11035249));

    public void createTestEntityAttributes(final EntityAttributeCreationEvent event){
        event.put(JARJAR.get(), JarJarBinksEntity.createMobAttributes().build());
        event.put(DARTHJARJAR.get(), DarthJarJarEntity.createMobAttributes().build());
    }

    public void setupCommon(final FMLCommonSetupEvent event){
    }

    public void addCreativeTabs(final CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(JARJAR_SPAWN_EGG.get());
            event.accept(DARTHJARJAR_SPAWN_EGG.get());
        }
    }
    public JarJarBinksMod() {
        CommonClass.init();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ENTITY_TYPE_DEFERRED_REGISTER.register(bus);
        bus.addListener(this::setupCommon);
        bus.addListener(this::addCreativeTabs);
        bus.addListener(this::createTestEntityAttributes);
    }

    @Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    private static class ClientModListener {

        @SubscribeEvent
        public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event){
            event.registerEntityRenderer(JARJAR.get(), JarJarRender::new);
            event.registerEntityRenderer(DARTHJARJAR.get(), DarthJarJarRender::new);
        }
    }

}