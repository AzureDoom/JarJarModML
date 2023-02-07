package mod.azure.jarjarbinks.client.renders;

import mod.azure.jarjarbinks.client.models.DarthJarJarModel;
import mod.azure.jarjarbinks.entity.DarthJarJarEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import mod.azure.azurelib.renderer.GeoEntityRenderer;

public class DarthJarJarRender extends GeoEntityRenderer<DarthJarJarEntity> {

	public DarthJarJarRender(EntityRendererProvider.Context renderManagerIn) {
		super(renderManagerIn, new DarthJarJarModel());
	}
}