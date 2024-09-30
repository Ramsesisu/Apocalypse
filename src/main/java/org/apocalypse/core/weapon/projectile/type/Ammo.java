package org.apocalypse.core.weapon.projectile.type;

import org.apocalypse.api.weapon.projectile.type.BulletType;
import org.bukkit.entity.Arrow;

public class Ammo extends BulletType {

    public Ammo() {
        super("Ammo", Arrow.class, 5, 6.0F);
    }
}
