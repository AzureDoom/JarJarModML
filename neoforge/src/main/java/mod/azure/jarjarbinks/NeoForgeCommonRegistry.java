package mod.azure.jarjarbinks;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;

import java.util.function.Supplier;

import mod.azure.jarjarbinks.platform.services.CommonRegistry;

public class NeoForgeCommonRegistry implements CommonRegistry {

    @Override
    public <T extends Entity> Supplier<EntityType<T>> registerEntity(
        String modID,
        String entityName,
        Supplier<EntityType<T>> entity
    ) {
        return NeoForgeMod.entityTypeDeferredRegister.register(entityName, entity);
    }

    @Override
    public <T extends Item> Supplier<T> registerItem(String modID, String itemName, Supplier<T> item) {
        return NeoForgeMod.itemDeferredRegister.register(itemName, item);
    }

    @Override
    public <T extends SoundEvent> Supplier<T> registerSound(String modID, String soundName, Supplier<T> sound) {
        return NeoForgeMod.soundEventDeferredRegister.register(soundName, sound);
    }

    @Override
    public <E extends Mob> Supplier<SpawnEggItem> makeSpawnEggFor(
        Supplier<EntityType<E>> entityType,
        int primaryEggColour,
        int secondaryEggColour,
        Item.Properties itemProperties
    ) {
        return () -> new DeferredSpawnEggItem(entityType, primaryEggColour, secondaryEggColour, itemProperties);
    }
}
