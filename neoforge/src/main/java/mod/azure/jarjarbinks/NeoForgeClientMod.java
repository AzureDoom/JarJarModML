package mod.azure.jarjarbinks;

import mod.azure.jarjarbinks.client.renders.DarthJarJarRender;
import mod.azure.jarjarbinks.client.renders.JarJarRender;
import mod.azure.jarjarbinks.registry.ModEntities;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = CommonMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class NeoForgeClientMod {

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.JARJAR.get(), JarJarRender::new);
        event.registerEntityRenderer(ModEntities.DARTHJARJAR.get(), DarthJarJarRender::new);
    }
}
