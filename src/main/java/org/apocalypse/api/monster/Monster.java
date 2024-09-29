package org.apocalypse.api.monster;

import lombok.Getter;
import lombok.SneakyThrows;
import org.apocalypse.api.monster.type.MonsterType;
import org.bukkit.entity.Creature;

@Getter
public class Monster {

    private final Creature entity;
    private final MonsterType type;

    @SneakyThrows
    public Monster(Creature entity, Class<? extends MonsterType> type) {
        this.entity = entity;
        this.type = type.getConstructor().newInstance();
    }
}
