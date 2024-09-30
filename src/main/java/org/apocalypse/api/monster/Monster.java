package org.apocalypse.api.monster;

import lombok.Getter;
import lombok.SneakyThrows;
import org.apocalypse.api.map.area.spawn.Spawn;
import org.apocalypse.api.monster.type.MonsterType;
import org.bukkit.entity.Creature;

@Getter
public class Monster {

    private final Creature entity;
    private final MonsterType type;
    private final Spawn spawn;

    @SneakyThrows
    public Monster(Creature entity, Class<? extends MonsterType> type, Spawn spawn) {
        this.entity = entity;
        this.type = type.getConstructor().newInstance();
        this.spawn = spawn;
    }
}
