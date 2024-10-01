package org.apocalypse.core.monster.type;

import org.apocalypse.api.monster.type.MonsterType;

public class Wolf extends MonsterType {

    public Wolf() {
        super("Wolf", org.bukkit.entity.Wolf.class, 3, 20, 12, 3);
    }
}
