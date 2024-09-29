package org.apocalypse.api.weapon.projectile.type;

import lombok.Getter;
import org.bukkit.entity.Projectile;

@Getter
public class BulletType {

    private final String name;
    private final Class<? extends Projectile> projectile;
    private final double damage;
    private final float speed;
    private final float range;
    private final boolean explosive;

    public BulletType(String name, Class<? extends Projectile> projectile, double damage, float speed) {
        this.name = name;
        this.projectile = projectile;
        this.damage = damage;
        this.speed = speed;
        this.range = 0;
        this.explosive = false;
    }
    public BulletType(String name, Class<? extends Projectile> projectile, double damage, float speed, float range) {
        this.name = name;
        this.projectile = projectile;
        this.damage = damage;
        this.speed = speed;
        this.range = range;
        this.explosive = false;
    }

    public BulletType(String name, Class<? extends Projectile> projectile, double damage, float speed, float range, boolean explosive) {
        this.name = name;
        this.projectile = projectile;
        this.damage = damage;
        this.speed = speed;
        this.range = range;
        this.explosive = explosive;
    }
}
