package org.apocalypse.api.service.container;

import lombok.SneakyThrows;
import org.apocalypse.Apocalypse;
import org.apocalypse.api.service.Service;

import java.util.HashMap;
import java.util.Map;

public class ServiceContainer {

    private static ServiceContainer INSTANCE;
    private final Map<Class<? extends Service>, Service> services = new HashMap<>();

    @SneakyThrows
    public static void register() {
        INSTANCE = new ServiceContainer();

        for (Class<?> clazz : Apocalypse.findClasses(Service.class)) {
            Service service = (Service) clazz.getConstructor().newInstance();
            INSTANCE.services.put(clazz.asSubclass(Service.class), service);
            service.load();
        }
    }

    public static <T> T get(Class<T> service) {
        return service.cast(INSTANCE.services.get(service));
    }
}
