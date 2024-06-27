package mod.azure.jarjarbinks.registry;

import mod.azure.azurelib.common.api.common.registry.CommonEntityRegistryInterface;
import mod.azure.azurelib.common.api.common.registry.CommonSoundRegistryInterface;
import mod.azure.jarjarbinks.CommonMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public record ModSounds() implements CommonSoundRegistryInterface {

     public static Supplier<SoundEvent> JARDEATH = CommonSoundRegistryInterface.registerSound(CommonMod.MOD_ID,
             "jarjar.howwude",
             () -> SoundEvent.createVariableRangeEvent(
                     ResourceLocation.fromNamespaceAndPath(CommonMod.MOD_ID, "jarjar.howwude")));

    public static Supplier<SoundEvent> JARNORMAL = CommonSoundRegistryInterface.registerSound(CommonMod.MOD_ID,
            "jarjar.jarjarluvsyou",
            () -> SoundEvent.createVariableRangeEvent(
                    ResourceLocation.fromNamespaceAndPath(CommonMod.MOD_ID, "jarjar.jarjarluvsyou")));

    public static Supplier<SoundEvent> JARHURT = CommonSoundRegistryInterface.registerSound(CommonMod.MOD_ID,
            "jarjar.meesadoanuthin",
            () -> SoundEvent.createVariableRangeEvent(
                    ResourceLocation.fromNamespaceAndPath(CommonMod.MOD_ID, "jarjar.meesadoanuthin")));


    public static Supplier<SoundEvent> DARTHDEATH = CommonSoundRegistryInterface.registerSound(CommonMod.MOD_ID,
            "jarjar.yousamaystrike",
            () -> SoundEvent.createVariableRangeEvent(
                    ResourceLocation.fromNamespaceAndPath(CommonMod.MOD_ID, "jarjar.yousamaystrike")));

    public static Supplier<SoundEvent> DARTHNORMAL = CommonSoundRegistryInterface.registerSound(CommonMod.MOD_ID,
            "jarjar.yousagonnadie",
            () -> SoundEvent.createVariableRangeEvent(
                    ResourceLocation.fromNamespaceAndPath(CommonMod.MOD_ID, "jarjar.yousagonnadie")));

    public static Supplier<SoundEvent> DARTHHURT = CommonSoundRegistryInterface.registerSound(CommonMod.MOD_ID,
            "jarjar.uhohyousadidafuckywucky",
            () -> SoundEvent.createVariableRangeEvent(
                    ResourceLocation.fromNamespaceAndPath(CommonMod.MOD_ID, "jarjar.uhohyousadidafuckywucky")));

    public static void init() {
    }

}
