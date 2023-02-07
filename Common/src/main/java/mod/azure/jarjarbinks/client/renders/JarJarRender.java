package mod.azure.jarjarbinks.client.renders;

import mod.azure.jarjarbinks.client.models.JarJarModel;
import mod.azure.jarjarbinks.entity.JarJarBinksEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import mod.azure.azurelib.renderer.GeoEntityRenderer;

public class JarJarRender extends GeoEntityRenderer<JarJarBinksEntity> {

	public JarJarRender(EntityRendererProvider.Context renderManagerIn) {
		super(renderManagerIn, new JarJarModel());
	}

}