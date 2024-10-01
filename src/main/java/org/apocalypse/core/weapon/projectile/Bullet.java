package org.apocalypse.core.weapon.projectile;

import org.apocalypse.api.weapon.projectile.type.BulletType;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.util.Vector;

import java.util.List;

public class Bullet extends BulletType {

    public Bullet() {
        super("Ammo", Arrow.class, 6.0F);
    }

    @Override
    public List<Projectile> shoot(Player shooter) {
        Vector direction = shooter.getLocation().add(0, 1.5, 0).getDirection();
        Projectile projectile = shooter.launchProjectile(this.projectile, direction);
        projectile.setShooter(shooter);
        projectile.setVelocity(direction.multiply(this.speed));
        return List.of(projectile);
    }
}
