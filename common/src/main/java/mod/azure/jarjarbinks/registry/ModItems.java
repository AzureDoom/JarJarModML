package mod.azure.jarjarbinks.registry;

import mod.azure.azurelib.common.api.common.items.AzureSpawnEgg;

public record ModItems() {
    public static final AzureSpawnEgg JARJAR_SPAWN_EGG = new AzureSpawnEgg(ModEntities.JARJAR, 0x8f3427, 0xe6b975);
    public static final AzureSpawnEgg DARTHJARJAR_SPAWN_EGG = new AzureSpawnEgg(ModEntities.DARTHJARJAR, 0x8d3323,
            0xe59b20);

}
