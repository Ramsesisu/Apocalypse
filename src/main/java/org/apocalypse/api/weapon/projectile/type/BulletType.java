package org.apocalypse.api.weapon.projectile.type;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;

import java.util.List;

@Getter
public abstract class BulletType {

    private final String name;
    protected final Class<? extends Projectile> projectile;
    protected final float speed;
    protected final long range;
    private final boolean explosive;
    protected final int amount;

    public BulletType(String name, Class<? extends Projectile> projectile, float speed) {
        this.name = name;
        this.projectile = projectile;
        this.speed = speed;
        this.range = 0;
        this.explosive = false;
        this.amount = 1;
    }

    public BulletType(String name, Class<? extends Projectile> projectile, float speed, long range) {
        this.name = name;
        this.projectile = projectile;
        this.speed = speed;
        this.range = range;
        this.explosive = false;
        this.amount = 1;
    }

    public BulletType(String name, Class<? extends Projectile> projectile, int amount, float speed, long range) {
        this.name = name;
        this.projectile = projectile;
        this.speed = speed;
        this.range = range;
        this.explosive = false;
        this.amount = amount;
    }

    public BulletType(String name, Class<? extends Projectile> projectile, float speed, long range, boolean explosive) {
        this.name = name;
        this.projectile = projectile;
        this.speed = speed;
        this.range = range;
        this.explosive = explosive;
        this.amount = 1;
    }

    public abstract List<Projectile> shoot(Player shooter);
}
