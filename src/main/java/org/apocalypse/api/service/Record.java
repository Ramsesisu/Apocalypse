package org.apocalypse.api.service;

import java.util.HashMap;
import java.util.Map;

public abstract class Record<T> {

    protected final Map<Class<? extends T>, T> list = new HashMap<>();

    public abstract void load();

    public T get(Class<? extends T> clazz) {
        return list.get(clazz);
    }
}
