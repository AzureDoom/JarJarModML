package mod.azure.jarjarbinks.entity;

import mod.azure.azurelib.animatable.GeoEntity;
import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animation.AnimatableManager.ControllerRegistrar;
import mod.azure.azurelib.core.animation.Animation.LoopType;
import mod.azure.azurelib.core.animation.AnimationController;
import mod.azure.azurelib.core.animation.RawAnimation;
import mod.azure.azurelib.core.object.PlayState;
import mod.azure.azurelib.util.AzureLibUtil;
import mod.azure.jarjarbinks.registry.ModSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class JarJarBinksEntity extends PathfinderMob implements GeoEntity {

    protected final GroundPathNavigation landNavigation = new GroundPathNavigation(this, this.getCommandSenderWorld());
    protected final AmphibiousNavigation swimNavigation = new AmphibiousNavigation(this, this.getCommandSenderWorld());
    protected final MoveControl landMoveControl = new MoveControl(this);
    protected final LookControl landLookControl = new LookControl(this);
    protected final SmoothSwimmingMoveControl swimMoveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.5f, 1.0f,
            false);
    protected final SmoothSwimmingLookControl swimLookControl = new SmoothSwimmingLookControl(this, 10);
    private final AnimatableInstanceCache cache = AzureLibUtil.createInstanceCache(this);

    public JarJarBinksEntity(EntityType<? extends JarJarBinksEntity> entityType, Level worldIn) {
        super(entityType, worldIn);
        navigation = landNavigation;
        moveControl = landMoveControl;
        lookControl = landLookControl;
    }

    public static AttributeSupplier.@NotNull Builder createMobAttributes() {
        return Mob.createLivingAttributes().add(Attributes.FOLLOW_RANGE, 50.0D).add(Attributes.MAX_HEALTH, 15.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.1D).add(Attributes.KNOCKBACK_RESISTANCE, 0.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.ATTACK_DAMAGE, 1.0D);
    }

    public static boolean canSpawn(EntityType<? extends JarJarBinksEntity> type, LevelAccessor world,
                                   MobSpawnType reason, BlockPos pos, RandomSource random) {
        return world.getDifficulty() != Difficulty.PEACEFUL;
    }

    @Override
    public void travel(@NotNull Vec3 movementInput) {
        this.navigation = (this.isInWater() || this.isInWater()) ? swimNavigation : landNavigation;
        this.moveControl = (this.wasEyeInWater || this.isInWater()) ? swimMoveControl : landMoveControl;
        this.lookControl = (this.wasEyeInWater || this.isInWater()) ? swimLookControl : landLookControl;

        if (this.tickCount % 10 == 0) {
            this.refreshDimensions();
        }
        if (isEffectiveAi() && this.isInWater()) {
            moveRelative(getSpeed(), movementInput);
            move(MoverType.SELF, getDeltaMovement());
            setDeltaMovement(getDeltaMovement().scale(0.35));
            if (getTarget() == null) {
                setDeltaMovement(getDeltaMovement().add(0.0, -0.005, 0.0));
            }
        } else {
            super.travel(movementInput);
        }
    }

    @Override
    public @NotNull PathNavigation createNavigation(@NotNull Level world) {
        return swimNavigation;
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    protected void jumpInLiquid(@NotNull TagKey<Fluid> fluid) {
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.3D, false));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(2, new RandomSwimmingGoal(this, 1.0D, 10));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Cod.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Pufferfish.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Salmon.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, TropicalFish.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Squid.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Turtle.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Dolphin.class, true));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public void registerControllers(ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "idle_controller", 0, event -> {
            if (this.wasEyeInWater)
                return event.setAndContinue(RawAnimation.begin().thenLoop("idle_water"));
            return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
        })).add(new AnimationController<>(this, "attack_controller", 0, event -> {
            if (this.swinging)
                return event.setAndContinue(RawAnimation.begin().then("attack", LoopType.PLAY_ONCE));

            return PlayState.STOP;
        }));
    }

    @Override
    public float getEyeHeight(@NotNull Pose pose) {
        return 1.74F;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSoundEvents.JARNORMAL;
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource damageSourceIn) {
        return ModSoundEvents.JARHURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSoundEvents.JARDEATH;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public @NotNull EntityDimensions getDimensions(@NotNull Pose pose) {
        return this.wasEyeInWater ? EntityDimensions.scalable(1.5f, 0.8f) : super.getDimensions(pose);
    }

    @Override
    public void refreshDimensions() {
        super.refreshDimensions();
    }

}