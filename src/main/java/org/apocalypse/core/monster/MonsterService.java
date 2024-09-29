package org.apocalypse.core.monster;

import lombok.SneakyThrows;
import org.apocalypse.Apocalypse;
import org.apocalypse.api.monster.type.MonsterType;
import org.apocalypse.api.service.Service;

import java.util.HashMap;
import java.util.Map;

public class MonsterService extends Service {

    private final Map<Class<?>, MonsterType> monsters = new HashMap<>();

    @SneakyThrows
    @Override
    public void load() {
        for (Class<?> clazz : Apocalypse.findClasses(MonsterType.class)) {
            try {
                MonsterType monster = (MonsterType) clazz.getConstructor().newInstance();
                monsters.put(clazz, monster);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public MonsterType get(Class<? extends MonsterType> monster) {
        return monsters.get(monster);
    }
}
