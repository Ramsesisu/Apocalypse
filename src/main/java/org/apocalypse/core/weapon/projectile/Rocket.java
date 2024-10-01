package org.apocalypse.core.weapon.projectile;

import org.apocalypse.api.weapon.projectile.type.BulletType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;

import java.util.List;

public class Rocket extends BulletType {

    public Rocket() {
        super("Rocket", Fireball.class, 4.0F, 12L, true);
    }

    @Override
    public List<Projectile> shoot(Player shooter) {
        return List.of();
    }
}
