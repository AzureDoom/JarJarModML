package mod.azure.jarjarbinks.client.renders;

import com.mojang.math.Axis;
import mod.azure.azurelib.common.model.AzBone;
import mod.azure.azurelib.common.render.AzRendererPipelineContext;
import mod.azure.azurelib.common.render.entity.AzEntityRenderer;
import mod.azure.azurelib.common.render.entity.AzEntityRendererConfig;
import mod.azure.azurelib.common.render.layer.AzBlockAndItemLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

import mod.azure.jarjarbinks.CommonMod;
import mod.azure.jarjarbinks.entity.JarJarBinksEntity;
import mod.azure.jarjarbinks.entity.animations.JarJarAmimator;

public class JarJarRender extends AzEntityRenderer<JarJarBinksEntity> {

    private static final ResourceLocation MODEL = CommonMod.modResource("geo/jarjarbinks.geo.json");

    private static final ResourceLocation TEX = CommonMod.modResource("textures/entity/jarjarbinks.png");

    public JarJarRender(EntityRendererProvider.Context context) {
        super(
            AzEntityRendererConfig.<JarJarBinksEntity>builder(
                MODEL,
                TEX
            )
                .setAnimatorProvider(JarJarAmimator::new)
                .addRenderLayer(new AzBlockAndItemLayer<>() {

                    @Override
                    public ItemStack itemStackForBone(AzBone bone, JarJarBinksEntity animatable) {
                        return switch (bone.getName()) {
                            case "rightHand_Item" -> animatable.getItemBySlot(EquipmentSlot.MAINHAND);
                            case "leftHand_Item" -> animatable.getItemBySlot(EquipmentSlot.OFFHAND);
                            default -> null;
                        };
                    }

                    @Override
                    protected ItemDisplayContext getTransformTypeForStack(
                        AzBone bone,
                        ItemStack stack,
                        JarJarBinksEntity animatable
                    ) {
                        return switch (bone.getName()) {
                            case "rightHand_Item" -> ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
                            case "leftHand_Item" -> ItemDisplayContext.THIRD_PERSON_LEFT_HAND;
                            default -> ItemDisplayContext.NONE;
                        };
                    }

                    @Override
                    protected void renderItemForBone(
                        AzRendererPipelineContext<UUID, JarJarBinksEntity> context,
                        AzBone bone,
                        ItemStack itemStack,
                        JarJarBinksEntity animatable
                    ) {
                        context.poseStack().mulPose(Axis.XP.rotationDegrees(270));
                        context.poseStack().mulPose(Axis.YP.rotationDegrees(0));
                        context.poseStack().mulPose(Axis.ZP.rotationDegrees(0f));
                        context.poseStack().translate(0.0D, 0.1D, 0.1D);
                        super.renderItemForBone(context, bone, itemStack, animatable);
                    }
                })
                .build(),
            context
        );
    }

}
