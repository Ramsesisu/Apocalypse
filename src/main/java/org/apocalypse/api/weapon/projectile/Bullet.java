package org.apocalypse.api.weapon.projectile;

import lombok.Getter;
import org.apocalypse.api.weapon.projectile.type.BulletType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.util.Vector;

@Getter
public class Bullet {

    private Projectile projectile = null;
    private final BulletType type;
    private final float range;

    public Bullet(BulletType type) {
        this.type = type;
        this.range = 0;
    }

    public Bullet(BulletType type, float range) {
        this.type = type;
        this.range = range;
    }

    public void shoot(Player shooter, BulletType type) {
        Vector direction = shooter.getLocation().add(0, 1.5, 0).getDirection();
        this.projectile = shooter.launchProjectile(type.getProjectile(), direction);
        this.projectile.setShooter(shooter);
        this.projectile.setVelocity(direction.multiply(type.getSpeed()));
        this.projectile.setGravity(false);
    }
}
