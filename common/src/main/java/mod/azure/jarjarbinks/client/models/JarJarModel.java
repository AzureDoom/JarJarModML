package mod.azure.jarjarbinks.client.models;

import mod.azure.azurelib.common.api.client.model.GeoModel;
import mod.azure.azurelib.common.internal.common.constant.DataTickets;
import mod.azure.azurelib.core.animation.AnimationState;
import mod.azure.jarjarbinks.CommonMod;
import mod.azure.jarjarbinks.entity.JarJarBinksEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class JarJarModel extends GeoModel<JarJarBinksEntity> {

    @Override
    public ResourceLocation getModelResource(JarJarBinksEntity object) {
        return CommonMod.modResource("geo/jarjarbinks.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(JarJarBinksEntity object) {
        return CommonMod.modResource("textures/entity/jarjarbinks.png");
    }

    @Override
    public ResourceLocation getAnimationResource(JarJarBinksEntity object) {
        return CommonMod.modResource("animations/jarjarbinks.animation.json");
    }

    @Override
    public void setCustomAnimations(JarJarBinksEntity animatable, long instanceId, AnimationState<JarJarBinksEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
        var neck = getAnimationProcessor().getBone("head");
        var leftarm = getAnimationProcessor().getBone("leftarm");
        var rightarm = getAnimationProcessor().getBone("rightarm");
        var leftleg = getAnimationProcessor().getBone("leftleg");
        var rightleg = getAnimationProcessor().getBone("rightleg");
        var entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        if (neck != null) {
            neck.setRotX((entityData.headPitch() * (((float) Math.PI) / 180F)));
            neck.setRotY((entityData.netHeadYaw() * (((float) Math.PI) / 180F)));
        }
        if (leftarm != null && !animatable.isAggressive()) {
            leftarm.setRotX(Mth.cos(animatable.walkAnimation.position(
                    animationState.getPartialTick()) * 0.6662F) * 2.0F * animatable.walkAnimation.speed() * 0.5F);
        }
        if (rightarm != null && !animatable.isAggressive()) {
            rightarm.setRotX(Mth.cos(animatable.walkAnimation.position(
                    animationState.getPartialTick()) * 0.6662F + 3.1415927F) * 2.0F * animatable.walkAnimation.speed() * 0.5F);
        }
        if (leftleg != null) {
            leftleg.setRotX(Mth.cos(animatable.walkAnimation.position(
                    animationState.getPartialTick()) * 0.6662F + 3.1415927F) * 1.4F * animatable.walkAnimation.speed() * 0.5F);
        }
        if (rightleg != null) {
            rightleg.setRotX(Mth.cos(animatable.walkAnimation.position(
                    animationState.getPartialTick()) * 0.6662F) * 1.4F * animatable.walkAnimation.speed() * 0.5F);
        }
    }

    @Override
    public RenderType getRenderType(JarJarBinksEntity animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureResource(animatable));
    }
}