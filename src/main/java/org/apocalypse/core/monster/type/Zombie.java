package org.apocalypse.core.monster.type;

import org.apocalypse.api.monster.type.MonsterType;
import org.bukkit.entity.EntityType;

public class Zombie extends MonsterType {

    public Zombie() {
        super("Zombie", EntityType.ZOMBIE, 1, 15, 20, 5);
    }
}
