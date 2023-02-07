package mod.azure.jarjarbinks.entity;

import mod.azure.jarjarbinks.registry.ModSoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import mod.azure.azurelib.animatable.GeoEntity;
import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animation.AnimatableManager.ControllerRegistrar;
import mod.azure.azurelib.core.animation.Animation.LoopType;
import mod.azure.azurelib.core.animation.AnimationController;
import mod.azure.azurelib.core.animation.RawAnimation;
import mod.azure.azurelib.core.object.PlayState;
import mod.azure.azurelib.util.AzureLibUtil;

public class DarthJarJarEntity extends JarJarBinksEntity implements GeoEntity {

	private final AnimatableInstanceCache cache = AzureLibUtil.createInstanceCache(this);

	public DarthJarJarEntity(EntityType<? extends JarJarBinksEntity> entityType, Level worldIn) {
		super(entityType, worldIn);
	}

	public static AttributeSupplier.Builder createMobAttributes() {
		return Mob.createLivingAttributes().add(Attributes.FOLLOW_RANGE, 50.0D).add(Attributes.MAX_HEALTH, 40.0D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.5D).add(Attributes.KNOCKBACK_RESISTANCE, 0.5D)
				.add(Attributes.MOVEMENT_SPEED, 0.35D).add(Attributes.ATTACK_DAMAGE, 1.5D);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}

	@Override
	public void registerControllers(ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "idle_controller", 0, event -> {
			return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
		})).add(new AnimationController<>(this, "attack_controller", 0, event -> {
			if (this.swinging)
				return event.setAndContinue(RawAnimation.begin().then("attack", LoopType.PLAY_ONCE));

			return PlayState.STOP;
		}));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSoundEvents.DARTHNORMAL;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return ModSoundEvents.DARTHHURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSoundEvents.DARTHDEATH;
	}

	@Override
	public boolean canStandOnFluid(FluidState state) {
		return state.is(FluidTags.LAVA);
	}

}
