package mod.azure.jarjarbinks.registry;

import mod.azure.jarjarbinks.Constants;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class ModSoundEvents {

	public static SoundEvent JARDEATH = of("jarjar.howwude");
	public static SoundEvent JARNORMAL = of("jarjar.jarjarluvsyou");
	public static SoundEvent JARHURT = of("jarjar.meesadoanuthin");

	public static SoundEvent DARTHDEATH = of("jarjar.yousamaystrike");
	public static SoundEvent DARTHNORMAL = of("jarjar.yousagonnadie");
	public static SoundEvent DARTHHURT = of("jarjar.uhohyousadidafuckywucky");

	static SoundEvent of(String id) {
		SoundEvent sound = SoundEvent.createVariableRangeEvent(new ResourceLocation(Constants.MOD_ID, id));
		Registry.register(BuiltInRegistries.SOUND_EVENT, new ResourceLocation(Constants.MOD_ID, id), sound);
		return sound;
	}

}
