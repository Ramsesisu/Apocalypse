package org.apocalypse.core.weapon.projectile.type;

import org.apocalypse.api.weapon.projectile.type.BulletType;
import org.bukkit.entity.Arrow;

public class Shells extends BulletType {

    public Shells() {
        super("Shells", Arrow.class, 8, 2, 3.5F, 5L);
    }
}
