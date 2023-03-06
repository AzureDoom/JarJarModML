package mod.azure.jarjarbinks.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import eu.midnightdust.lib.config.MidnightConfig;
import mod.azure.jarjarbinks.Constants;
import org.quiltmc.loader.api.minecraft.ClientOnly;

@ClientOnly
public class ModMenuJarJar implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> MidnightConfig.getScreen(parent, Constants.MOD_ID);
    }
}