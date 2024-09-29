package org.apocalypse.core.monster;

import lombok.SneakyThrows;
import org.apocalypse.Apocalypse;
import org.apocalypse.api.monster.type.MonsterType;
import org.apocalypse.api.service.Record;

public class MonsterRecord extends Record<MonsterType> {

    @SneakyThrows
    @Override
    public void load() {
        for (Class<? extends MonsterType> clazz : Apocalypse.findClasses(MonsterType.class)) {
            MonsterType monster = clazz.getConstructor().newInstance();
            list.put(clazz, monster);
        }
    }
}
