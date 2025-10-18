package mod.azure.jarjarbinks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

import mod.azure.jarjarbinks.client.renders.DarthJarJarRender;
import mod.azure.jarjarbinks.client.renders.JarJarRender;
import mod.azure.jarjarbinks.registry.ModEntities;

public class FabricClientLibMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.JARJAR.get(), JarJarRender::new);
        EntityRendererRegistry.register(ModEntities.DARTHJARJAR.get(), DarthJarJarRender::new);
    }
}
