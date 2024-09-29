package org.apocalypse.api.service;

import lombok.SneakyThrows;
import org.apocalypse.Apocalypse;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public abstract class Record<T> {

    public final Map<Class<? extends T>, T> list = new HashMap<>();

    @SneakyThrows
    @SuppressWarnings("unchecked")
    public void load() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType parameterizedType) {
            Type type = parameterizedType.getActualTypeArguments()[0];

            if (type instanceof Class) {
                Class<T> clazz = (Class<T>) type;

                for (Class<? extends T> foundClass : Apocalypse.findClasses(clazz)) {
                    T instance = foundClass.getConstructor().newInstance();
                    list.put(foundClass, instance);
                }
            }
        }
    }

    public T get(Class<? extends T> clazz) {
        return list.get(clazz);
    }
}
