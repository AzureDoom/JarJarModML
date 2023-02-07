package mod.azure.jarjarbinks.client;

import mod.azure.jarjarbinks.JarJarBinksMod;
import mod.azure.jarjarbinks.client.renders.DarthJarJarRender;
import mod.azure.jarjarbinks.client.renders.JarJarRender;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class JarJarBinksQuiltClient implements ClientModInitializer {
    @Override
    public void onInitializeClient(ModContainer container) {
        EntityRendererRegistry.register(JarJarBinksMod.JARJAR, JarJarRender::new);
        EntityRendererRegistry.register(JarJarBinksMod.DARTHJARJAR, DarthJarJarRender::new);
    }
}
