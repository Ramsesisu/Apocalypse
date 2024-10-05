package org.apocalypse.core.monster;

import org.apocalypse.api.monster.type.MonsterType;
import org.apocalypse.api.service.Record;

public class MonsterRecord extends Record<MonsterType> {

    public Class<? extends MonsterType> get(String monster) {
        return this.list.values().stream()
                .filter(monsterType -> monsterType.getName().equalsIgnoreCase(monster))
                .map(MonsterType::getClass)
                .findFirst()
                .orElse(null);
    }
}
