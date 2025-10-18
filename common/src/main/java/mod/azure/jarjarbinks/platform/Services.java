package mod.azure.jarjarbinks.platform;

import java.util.ServiceLoader;

import mod.azure.jarjarbinks.platform.services.CommonRegistry;

public final class Services {

    public static final CommonRegistry COMMON_REGISTRY = load(CommonRegistry.class);

    private Services() {
        throw new UnsupportedOperationException();
    }

    public static <T> T load(Class<T> clazz) {
        return ServiceLoader.load(clazz)
            .findFirst()
            .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
    }
}
