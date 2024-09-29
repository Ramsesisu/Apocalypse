package org.apocalypse.api.weapon.types;

import lombok.Getter;
import org.apocalypse.api.weapon.Weapon;
import org.bukkit.Material;

@Getter
public class WeaponType {

    private final String name;
    private final Material item;
    private final Weapon.Type type;
    private final int ammo;
    private final int magazine;
    private final int damage;

    public WeaponType(String name, Material item, int damage, int ammo, int magazine) {
        this.item = item;
        this.type = Weapon.Type.GUN;
        this.name = name;
        this.ammo = ammo;
        this.magazine = magazine;
        this.damage = damage;
    }

    public WeaponType(String name, Material item, int damage) {
        this.item = item;
        this.type = Weapon.Type.MELEE;
        this.name = name;
        this.damage = damage;
        this.magazine = 0;
        this.ammo = 0;
    }
}
