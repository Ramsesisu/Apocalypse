package org.apocalypse.api.service;

import java.util.HashMap;
import java.util.Map;

public abstract class Service<T> {

    protected final Map<Class<? extends T>, Object> list = new HashMap<>();

    public abstract void load();

    public void get(Class<? extends T> clazz) {
        list.get(clazz);
    }
}
