package org.apocalypse.api.weapon.projectile;

import lombok.Getter;
import org.apocalypse.api.weapon.projectile.types.BulletType;
import org.bukkit.entity.Projectile;

@Getter
public class Bullet {

    private final Projectile entity;
    private final BulletType type;
    private final int damage;
    private boolean explosive = false;
    private final float range;

    public Bullet(Projectile entity, int damage, float range) {
        this.entity = entity;
        this.damage = damage;
        this.range = range;
    }

    public Bullet(Projectile entity, int damage, float range, boolean explosive) {
        this.entity = entity;
        this.damage = damage;
        this.range = range;
        this.explosive = explosive;
    }
}
