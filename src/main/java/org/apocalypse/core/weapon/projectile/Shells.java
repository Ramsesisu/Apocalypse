package org.apocalypse.core.weapon.projectile;

import org.apocalypse.api.weapon.projectile.type.BulletType;
import org.bukkit.entity.Arrow;

public class Shells extends BulletType {

    public Shells() {
        super("Shells", Arrow.class, 5, 2, 3.5F, 5L);
    }
}
