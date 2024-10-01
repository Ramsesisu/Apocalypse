package org.apocalypse.core.weapon.projectile;

import org.apocalypse.api.weapon.projectile.type.BulletType;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Shells extends BulletType {

    public Shells() {
        super("Shells", Arrow.class, 8, 3.5F, 5L);
    }

    @Override
    public List<Projectile> shoot(Player shooter) {
        List<Projectile> projectiles = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < this.amount; i++) {
            Vector direction = shooter.getLocation().add(0, 1.5, 0).getDirection();
            double offset = (double) this.amount / 25;
            direction.add(new Vector(
                    (random.nextDouble() - 0.5) * offset,
                    (random.nextDouble() - 0.5) * offset,
                    (random.nextDouble() - 0.5) * offset
            ));
            Projectile projectile = shooter.launchProjectile(this.projectile, direction);
            projectile.setShooter(shooter);
            projectile.setVelocity(direction.multiply(this.speed));
            projectiles.add(projectile);
        }
        return projectiles;
    }
}
