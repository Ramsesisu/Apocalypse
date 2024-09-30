package org.apocalypse.core.weapon.projectile.type;

import org.apocalypse.api.weapon.projectile.type.BulletType;
import org.bukkit.entity.Arrow;

public class Bullets extends BulletType {

    public Bullets() {
        super("Bullets", Arrow.class, 15, 7.5F);
    }
}
