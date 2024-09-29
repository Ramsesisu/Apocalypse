package org.apocalypse.api.service.container;

import lombok.NonNull;
import lombok.SneakyThrows;
import org.apocalypse.Apocalypse;
import org.apocalypse.api.service.Record;
import org.apocalypse.api.service.Service;

import java.util.HashMap;
import java.util.Map;

public class Container {

    private static Container INSTANCE;
    private final Map<Class<? extends Record<?>>, Record<?>> records = new HashMap<>();
    private final Map<Class<? extends Service<?, ?>>, Service<?, ?>> services = new HashMap<>();

    @SneakyThrows
    public static void register() {
        INSTANCE = new Container();
        for (Class<? extends Record<?>> clazz : Apocalypse.findClasses(Record.class)) {
            Record<?> record = clazz.getConstructor().newInstance();
            INSTANCE.records.put(clazz, record);
            record.load();
        }
        for (Class<? extends Service<?, ?>> clazz : Apocalypse.findClasses(Service.class)) {
            Service<?, ?> service = clazz.getConstructor().newInstance();
            INSTANCE.services.put(clazz, service);
        }
    }

    @NonNull
    public static <T> T get(Class<T> service) {
        if (INSTANCE.services.containsKey(service))
            return service.cast(INSTANCE.services.get(service));
        else return service.cast(INSTANCE.records.get(service));
    }
}
