package mod.azure.jarjarbinks.client.renders;

import com.mojang.math.Axis;
import mod.azure.azurelib.common.internal.client.util.RenderUtils;
import mod.azure.azurelib.rewrite.model.AzBone;
import mod.azure.azurelib.rewrite.render.AzRendererPipelineContext;
import mod.azure.azurelib.rewrite.render.entity.AzEntityRenderer;
import mod.azure.azurelib.rewrite.render.entity.AzEntityRendererConfig;
import mod.azure.azurelib.rewrite.render.layer.AzBlockAndItemLayer;
import mod.azure.jarjarbinks.CommonMod;
import mod.azure.jarjarbinks.entity.JarJarBinksEntity;
import mod.azure.jarjarbinks.entity.animations.JarJarAmimator;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

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
						.addRenderLayer(new AzBlockAndItemLayer<JarJarBinksEntity>() {
							public ItemStack itemStackForBoneWithEntity(AzBone bone, JarJarBinksEntity animatable) {
								return switch (bone.getName()) {
									case "rightHand_Item" -> animatable.getItemBySlot(EquipmentSlot.MAINHAND);
									case "leftHand_Item" -> animatable.getItemBySlot(EquipmentSlot.OFFHAND);
									default -> null;
								};
							}

							@Override
							public void renderForBone(AzRendererPipelineContext<JarJarBinksEntity> context, AzBone bone) {
								var stack = itemStackForBoneWithEntity(bone, context.animatable());

								if (stack == null)
									return;

								context.poseStack().pushPose();
								RenderUtils.translateAndRotateMatrixForBone(context.poseStack(), bone);

								renderItemForBone(context, bone, stack);

								context.poseStack().popPose();
							}

							@Override
							protected ItemDisplayContext getTransformTypeForStack(AzBone bone, ItemStack stack) {
								return ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
							}

							@Override
							protected void renderItemForBone(AzRendererPipelineContext<JarJarBinksEntity> context, AzBone bone, ItemStack itemStack) {
								context.poseStack().mulPose(Axis.XP.rotationDegrees(270));
								context.poseStack().mulPose(Axis.YP.rotationDegrees(0));
								context.poseStack().mulPose(Axis.ZP.rotationDegrees(0f));
								context.poseStack().translate(0.0D, 0.1D, 0.1D);
								super.renderItemForBone(context, bone, itemStack);
							}
						})
						.build(),
				context
		);
	}

}