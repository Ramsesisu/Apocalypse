package org.apocalypse.core.weapon.projectile;

import org.apocalypse.api.weapon.projectile.type.BulletType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.WindCharge;

import java.util.List;

public class Plasma extends BulletType {

    public Plasma() {
        super("Plasma", WindCharge.class, 4.5F, 10L);
    }

    @Override
    public List<Projectile> shoot(Player shooter) {
        return List.of();
    }
}
