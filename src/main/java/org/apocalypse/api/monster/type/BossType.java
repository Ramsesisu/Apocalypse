package org.apocalypse.api.monster.type;

import org.bukkit.entity.Creature;

public abstract class BossType extends MonsterType {

    public BossType(String name, Class<? extends Creature> type, int round, double health, double damage) {
        super(name, type, round, round, health, damage);
    }
}
