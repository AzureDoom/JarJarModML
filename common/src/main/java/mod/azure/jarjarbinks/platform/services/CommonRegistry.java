package mod.azure.jarjarbinks.platform.services;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

import java.util.function.Supplier;

public interface CommonRegistry {

    <T extends Entity> Supplier<EntityType<T>> registerEntity(
        String modID,
        String entityName,
        Supplier<EntityType<T>> entity
    );

    <T extends Item> Supplier<T> registerItem(String modID, String itemName, Supplier<T> item);

    <T extends SoundEvent> Supplier<T> registerSound(String modID, String soundName, Supplier<T> sound);

    <E extends Mob> Supplier<SpawnEggItem> makeSpawnEggFor(
        Supplier<EntityType<E>> entityType,
        int primaryEggColour,
        int secondaryEggColour,
        Item.Properties itemProperties
    );

}
