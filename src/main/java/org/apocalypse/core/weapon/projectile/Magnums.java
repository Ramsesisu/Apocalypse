package org.apocalypse.core.weapon.projectile;

import org.apocalypse.api.weapon.projectile.type.BulletType;
import org.bukkit.entity.Arrow;

public class Magnums extends BulletType {

    public Magnums() {
        super("Magnums", Arrow.class, 10, 6.0F);
    }
}
