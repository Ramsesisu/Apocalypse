package org.apocalypse.api.monster.type;

import org.bukkit.entity.EntityType;

public abstract class BossType extends MonsterType {

    public BossType(String name, EntityType type, int round, double health, double damage) {
        super(name, type, round, round, health, damage);
    }
}
