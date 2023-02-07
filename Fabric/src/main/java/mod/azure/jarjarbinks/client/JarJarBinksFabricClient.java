package mod.azure.jarjarbinks.client;

import mod.azure.jarjarbinks.JarJarBinksMod;
import mod.azure.jarjarbinks.client.renders.DarthJarJarRender;
import mod.azure.jarjarbinks.client.renders.JarJarRender;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class JarJarBinksFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(JarJarBinksMod.JARJAR, JarJarRender::new);
        EntityRendererRegistry.register(JarJarBinksMod.DARTHJARJAR, DarthJarJarRender::new);
    }
}
