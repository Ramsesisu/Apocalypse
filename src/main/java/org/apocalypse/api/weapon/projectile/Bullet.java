package org.apocalypse.api.weapon.projectile;

import lombok.Getter;
import org.apocalypse.Apocalypse;
import org.apocalypse.api.weapon.projectile.type.BulletType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Bullet {

    private List<Projectile> projectiles = new ArrayList<>();
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

    public Projectile getProjectile() {
        if (this.projectiles.isEmpty()) return null;
        return this.projectiles.getFirst();
    }

    public boolean isMultishot() {
        return this.projectiles.size() > 1;
    }

    public void shoot(Player shooter) {
        this.projectiles = this.type.shoot(shooter);
        if (this.type.getRange() > 0)
            Bukkit.getScheduler().runTaskLater(Apocalypse.getInstance(),
                    () -> this.projectiles.forEach(Entity::remove), this.type.getRange());
    }
}
