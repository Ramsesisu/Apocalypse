package org.apocalypse.core.monster.type;

import org.apocalypse.api.monster.type.MonsterType;

public class Zombie extends MonsterType {

    public Zombie() {
        super("Zombie", org.bukkit.entity.Zombie.class, 1, 15, 20, 5);
    }
}
