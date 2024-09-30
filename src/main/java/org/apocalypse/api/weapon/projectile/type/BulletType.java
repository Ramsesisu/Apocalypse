package org.apocalypse.api.weapon.projectile.type;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
public abstract class BulletType {

    private final String name;
    private final Class<? extends Projectile> projectile;
    private final double damage;
    private final float speed;
    private final long range;
    private final boolean explosive;
    private final int amount;

    public BulletType(String name, Class<? extends Projectile> projectile, double damage, float speed) {
        this.name = name;
        this.projectile = projectile;
        this.damage = damage;
        this.speed = speed;
        this.range = 0;
        this.explosive = false;
        this.amount = 1;
    }

    public BulletType(String name, Class<? extends Projectile> projectile, double damage, float speed, long range) {
        this.name = name;
        this.projectile = projectile;
        this.damage = damage;
        this.speed = speed;
        this.range = range;
        this.explosive = false;
        this.amount = 1;
    }

    public BulletType(String name, Class<? extends Projectile> projectile, int amount, double damage, float speed, long range) {
        this.name = name;
        this.projectile = projectile;
        this.damage = damage;
        this.speed = speed;
        this.range = range;
        this.explosive = false;
        this.amount = amount;
    }

    public BulletType(String name, Class<? extends Projectile> projectile, double damage, float speed, long range, boolean explosive) {
        this.name = name;
        this.projectile = projectile;
        this.damage = damage;
        this.speed = speed;
        this.range = range;
        this.explosive = explosive;
        this.amount = 1;
    }

    public List<Projectile> shoot(Player shooter) {
        if (this.amount <= 1) {
            Vector direction = shooter.getLocation().add(0, 1.5, 0).getDirection();
            Projectile projectile = shooter.launchProjectile(this.projectile, direction);
            projectile.setShooter(shooter);
            projectile.setVelocity(direction.multiply(this.speed));
            projectile.setCustomName(this.name);
            return List.of(projectile);
        } else {
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
                projectile.setCustomName(this.name);
                projectiles.add(projectile);
            }
            return projectiles;
        }
    }
}
