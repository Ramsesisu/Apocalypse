package org.apocalypse.api.service;

import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

public abstract class Service<K, V> {

    protected final Map<K, V> list = new HashMap<>();

    public V get(K key) {
        return list.get(key);
    }

    public void add(K key, V value) {
        list.put(key, value);
    }

    public V remove(K key) {
        return list.remove(key);
    }

    public boolean remove(K key, V value) {
        return list.remove(key, value);
    }

    public void clear() {
        list.clear();
    }
}
