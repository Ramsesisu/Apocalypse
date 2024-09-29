package org.apocalypse.api.weapon.type;

import lombok.Getter;
import lombok.SneakyThrows;
import org.apocalypse.api.weapon.Weapon;
import org.apocalypse.api.weapon.projectile.type.BulletType;
import org.bukkit.Material;

@Getter
public class WeaponType {

    private final String name;
    private final Material item;
    private final Weapon.Type type;
    private final BulletType bullet;
    private final double damage;
    private final float speed;
    private final int ammo;
    private final int magazine;

    @SneakyThrows
    public WeaponType(String name, Material item, Class<? extends BulletType> bullet, float speed, int ammo, int magazine) {
        this.item = item;
        this.type = Weapon.Type.GUN;
        this.name = name;
        this.ammo = ammo;
        this.magazine = magazine;
        this.bullet = bullet.getConstructor().newInstance();
        this.damage = this.bullet.getDamage();
        this.speed = speed;
    }

    public WeaponType(String name, Material item, int damage, float speed) {
        this.item = item;
        this.type = Weapon.Type.MELEE;
        this.name = name;
        this.magazine = 0;
        this.ammo = 0;
        this.bullet = null;
        this.damage = damage;
        this.speed = speed;
    }
}
