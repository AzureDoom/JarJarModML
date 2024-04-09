package mod.azure.jarjarbinks.client.renders;

import mod.azure.azurelib.common.api.client.renderer.GeoEntityRenderer;
import mod.azure.jarjarbinks.client.models.DarthJarJarModel;
import mod.azure.jarjarbinks.entity.DarthJarJarEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class DarthJarJarRender extends GeoEntityRenderer<DarthJarJarEntity> {

	public DarthJarJarRender(EntityRendererProvider.Context renderManagerIn) {
		super(renderManagerIn, new DarthJarJarModel());
	}
}