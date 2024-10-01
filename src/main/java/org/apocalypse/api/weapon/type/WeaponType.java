package org.apocalypse.api.weapon.type;

import lombok.Getter;
import lombok.SneakyThrows;
import org.apocalypse.api.weapon.Weapon;
import org.apocalypse.api.weapon.projectile.type.BulletType;
import org.bukkit.Material;

@Getter
public abstract class WeaponType {

    private final String name;
    private final Material item;
    private final Weapon.Type type;
    private final boolean exclusive;
    private final BulletType bullet;
    private final double damage;
    private final float speed;
    private final int ammo;
    private final int magazine;

    public WeaponType(String name, Material item, Class<? extends BulletType> bullet, double damage, float speed, int ammo, int magazine) {
        this(name, item, false, bullet, damage, speed, ammo, magazine);
    }

    @SneakyThrows
    public WeaponType(String name, Material item, boolean exclusive, Class<? extends BulletType> bullet, double damage, float speed, int ammo, int magazine) {
        this.item = item;
        this.exclusive = exclusive;
        this.type = Weapon.Type.GUN;
        this.name = name;
        this.ammo = ammo;
        this.magazine = magazine;
        this.bullet = bullet.getConstructor().newInstance();
        this.damage = damage;
        this.speed = speed;
    }

    public WeaponType(String name, Material item, double damage, float speed) {
        this(name, item, false, damage, speed);
    }

    public WeaponType(String name, Material item, boolean exclusive, double damage, float speed) {
        this.item = item;
        this.exclusive = exclusive;
        this.type = Weapon.Type.MELEE;
        this.name = name;
        this.magazine = 0;
        this.ammo = 0;
        this.bullet = null;
        this.damage = damage;
        this.speed = speed;
    }
}
