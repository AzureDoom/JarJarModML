package mod.azure.jarjarbinks.entity.animations;

import mod.azure.azurelib.rewrite.animation.dispatch.command.AzCommand;
import mod.azure.azurelib.rewrite.animation.play_behavior.AzPlayBehaviors;
import net.minecraft.world.entity.Entity;

public class AnimationDispatcher {

    private final AzCommand IDLE_COMMAND = AzCommand.create("base_controller", "idle", AzPlayBehaviors.LOOP);

    private final AzCommand IDLE_WATER_COMMAND = AzCommand.create("base_controller", "idle_water", AzPlayBehaviors.LOOP);

    private final AzCommand ATTACK_COMMAND = AzCommand.create("attack_controller", "attack", AzPlayBehaviors.LOOP);

    private final Entity animatedEntity;

    public AnimationDispatcher(Entity animatedEntity) {
        this.animatedEntity = animatedEntity;
    }

    public void sendIdleAnimation() {
        IDLE_COMMAND.sendForEntity( animatedEntity);
    }

    public void sendIdleWaterAnimation() {
        IDLE_WATER_COMMAND.sendForEntity( animatedEntity);
    }

    public void sendAttackAnimation() {
        ATTACK_COMMAND.sendForEntity( animatedEntity);
    }
}
