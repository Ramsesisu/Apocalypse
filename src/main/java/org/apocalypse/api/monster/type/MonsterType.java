package org.apocalypse.api.monster.type;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

@Getter
public abstract class MonsterType {

    private final String name;
    private final EntityType type;
    private final int first;
    private final int last;
    private final double health;
    private final double damage;
    private final Material weapon;
    private final Material helmet;
    private final Material chestplate;
    private final Material leggings;
    private final Material boots;

    public MonsterType(String name, EntityType type, int first, int last, double health, double damage) {
        this.name = name;
        this.type = type;
        this.first = first;
        this.last = last;
        this.health = health;
        this.damage = damage;
        this.weapon = Material.AIR;
        this.helmet = Material.AIR;
        this.chestplate = Material.AIR;
        this.leggings = Material.AIR;
        this.boots = Material.AIR;
    }

    public MonsterType(String name, EntityType type, int first, int last, double health, double damage, Material weapon) {
        this.name = name;
        this.type = type;
        this.first = first;
        this.last = last;
        this.health = health;
        this.damage = damage;
        this.weapon = weapon;
        this.helmet = Material.AIR;
        this.chestplate = Material.AIR;
        this.leggings = Material.AIR;
        this.boots = Material.AIR;
    }

    public MonsterType(String name, EntityType type, int first, int last, double health, double damage, Material weapon, Material helmet, Material chestplate, Material leggings, Material boots) {
        this.name = name;
        this.type = type;
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
