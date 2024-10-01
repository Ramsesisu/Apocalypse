package org.apocalypse.api.monster.type;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Creature;

@Getter
public abstract class MonsterType {

    private final String name;
    private final Class<? extends Creature> type;
    private final boolean baby;
    private final int first;
    private final int last;
    private final double health;
    private final double damage;
    private final Material weapon;
    private final Material helmet;
    private final Material chestplate;
    private final Material leggings;
    private final Material boots;

    public MonsterType(String name, Class<? extends Creature> type, int first, int last, double health, double damage) {
        this(name, type, false, first, last, health, damage);
    }

    public MonsterType(String name, Class<? extends Creature> type, boolean baby, int first, int last, double health, double damage) {
        this(name, type, baby, first, last, health, damage, Material.AIR);
    }

    public MonsterType(String name, Class<? extends Creature> type, int first, int last, double health, double damage, Material weapon) {
        this(name, type, false, first, last, health, damage, weapon);
    }

    public MonsterType(String name, Class<? extends Creature> type, boolean baby, int first, int last, double health, double damage, Material weapon) {
        this(name, type, baby, first, last, health, damage, weapon, Material.AIR, Material.AIR, Material.AIR, Material.AIR);
    }

    public MonsterType(String name, Class<? extends Creature> type, int first, int last, double health, double damage, Material helmet, Material chestplate, Material leggings, Material boots) {
        this(name, type, false, first, last, health, damage, helmet, chestplate, leggings, boots);
    }

    public MonsterType(String name, Class<? extends Creature> type, boolean baby, int first, int last, double health, double damage, Material helmet, Material chestplate, Material leggings, Material boots) {
        this(name, type, baby, first, last, health, damage, Material.AIR, helmet, chestplate, leggings, boots);
    }

    public MonsterType(String name, Class<? extends Creature> type, int first, int last, double health, double damage, Material weapon, Material helmet, Material chestplate, Material leggings, Material boots) {
        this(name, type, false, first, last, health, damage, weapon, helmet, chestplate, leggings, boots);
    }

    public MonsterType(String name, Class<? extends Creature> type, boolean baby, int first, int last, double health, double damage, Material weapon, Material helmet, Material chestplate, Material leggings, Material boots) {
        this.name = name;
        this.type = type;
        this.baby = baby;
        this.first = first;
        this.last = last;
        this.health = health;
        this.damage = damage;
        this.weapon = weapon;
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
    }
}
