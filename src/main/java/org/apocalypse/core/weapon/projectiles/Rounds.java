package org.apocalypse.core.weapon.projectiles;

import org.apocalypse.api.weapon.projectile.types.BulletType;
import org.bukkit.entity.Arrow;

public class Rounds extends BulletType {

    public Rounds() {
        super("Rounds", Arrow.class, 5, 1);
    }
}
