package mod.azure.jarjarbinks.registry;

import mod.azure.jarjarbinks.CommonMod;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public record ModSounds() {

    public static final Supplier<SoundEvent> JARDEATH = soundEventSupplier("jarjar.howwude");
    public static final Supplier<SoundEvent> JARNORMAL = soundEventSupplier("jarjar.jarjarluvsyou");
    public static final Supplier<SoundEvent> JARHURT = soundEventSupplier("jarjar.meesadoanuthin");

    public static final Supplier<SoundEvent> DARTHDEATH = soundEventSupplier("jarjar.yousamaystrike");
    public static final Supplier<SoundEvent> DARTHNORMAL = soundEventSupplier("jarjar.yousagonnadie");
    public static final Supplier<SoundEvent> DARTHHURT = soundEventSupplier("jarjar.uhohyousadidafuckywucky");

    static Supplier<SoundEvent> soundEventSupplier(String id) {
        return () -> SoundEvent.createVariableRangeEvent(CommonMod.modResource(id));
    }

}
