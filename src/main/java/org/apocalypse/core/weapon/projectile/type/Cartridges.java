package org.apocalypse.core.weapon.projectile.type;

import org.apocalypse.api.weapon.projectile.type.BulletType;
import org.bukkit.entity.Arrow;

public class Cartridges extends BulletType {

    public Cartridges() {
        super("Cartridges", Arrow.class, 4, 7.0F);
    }
}
