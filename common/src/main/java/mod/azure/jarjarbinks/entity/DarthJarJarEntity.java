package mod.azure.jarjarbinks.entity;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.NotNull;

import mod.azure.jarjarbinks.entity.animations.AnimationDispatcher;
import mod.azure.jarjarbinks.registry.ModSounds;

public class DarthJarJarEntity extends JarJarBinksEntity {

    public DarthJarJarEntity(EntityType<? extends JarJarBinksEntity> entityType, Level worldIn) {
        super(entityType, worldIn);
        dispatcher = new AnimationDispatcher(this);
    }

    public static AttributeSupplier.@NotNull Builder createMobAttributes() {
        return LivingEntity.createLivingAttributes()
            .add(Attributes.FOLLOW_RANGE, 50.0D)
            .add(Attributes.MAX_HEALTH, 40.0D)
            .add(Attributes.ATTACK_KNOCKBACK, 0.5D)
            .add(Attributes.KNOCKBACK_RESISTANCE, 0.5D)
            .add(Attributes.MOVEMENT_SPEED, 0.35D)
            .add(Attributes.ATTACK_DAMAGE, 1.5D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.DARTHNORMAL.get();
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource damageSourceIn) {
        return ModSounds.DARTHHURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.DARTHDEATH.get();
    }

    @Override
    public boolean canStandOnFluid(FluidState state) {
        return state.is(FluidTags.LAVA);
    }

}
