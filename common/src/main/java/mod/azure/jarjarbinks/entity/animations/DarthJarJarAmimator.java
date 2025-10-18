package mod.azure.jarjarbinks.entity.animations;

import mod.azure.azurelib.common.animation.controller.AzAnimationController;
import mod.azure.azurelib.common.animation.controller.AzAnimationControllerContainer;
import mod.azure.azurelib.common.animation.impl.AzEntityAnimator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import mod.azure.jarjarbinks.CommonMod;
import mod.azure.jarjarbinks.entity.DarthJarJarEntity;

public class DarthJarJarAmimator extends AzEntityAnimator<DarthJarJarEntity> {

    private final ResourceLocation animationLocation = CommonMod.modResource("animations/jarjarbinks.animation.json");

    @Override
    public void registerControllers(AzAnimationControllerContainer<DarthJarJarEntity> animationControllerContainer) {
        animationControllerContainer.add(
            AzAnimationController.builder(this, "base_controller")
                .setTransitionLength(5)
                .build()
        );
        animationControllerContainer.add(
            AzAnimationController.builder(this, "attack_controller")
                .setTransitionLength(5)
                .build()
        );
    }

    @Override
    public @NotNull ResourceLocation getAnimationLocation(DarthJarJarEntity animatable) {
        return animationLocation;
    }

    @Override
    public void setCustomAnimations(DarthJarJarEntity animatable, float partialTicks) {
        super.setCustomAnimations(animatable, partialTicks);
        var boneCache = this.context().boneCache();
        var leftarm = boneCache.getBakedModel().getBone("leftarm");
        var rightarm = boneCache.getBakedModel().getBone("rightarm");
        var leftleg = boneCache.getBakedModel().getBone("leftleg");
        var rightleg = boneCache.getBakedModel().getBone("rightleg");

        if (leftarm.isPresent() && !animatable.isAggressive()) {
            leftarm.get()
                .setRotX(
                    Mth.cos(
                        animatable.walkAnimation.position(
                            partialTicks
                        ) * 0.6662F
                    ) * 2.0F * animatable.walkAnimation.speed() * 0.5F
                );
        }
        if (rightarm.isPresent() && !animatable.isAggressive()) {
            rightarm.get()
                .setRotX(
                    Mth.cos(
                        animatable.walkAnimation.position(
                            partialTicks
                        ) * 0.6662F + 3.1415927F
                    ) * 2.0F * animatable.walkAnimation.speed() * 0.5F
                );
        }
        if (leftleg.isPresent()) {
            leftleg.get()
                .setRotX(
                    Mth.cos(
                        animatable.walkAnimation.position(
                            partialTicks
                        ) * 0.6662F + 3.1415927F
                    ) * 1.4F * animatable.walkAnimation.speed() * 0.5F
                );
        }
        if (rightleg.isPresent()) {
            rightleg.get()
                .setRotX(
                    Mth.cos(
                        animatable.walkAnimation.position(
                            partialTicks
                        ) * 0.6662F
                    ) * 1.4F * animatable.walkAnimation.speed() * 0.5F
                );
        }
    }
}
