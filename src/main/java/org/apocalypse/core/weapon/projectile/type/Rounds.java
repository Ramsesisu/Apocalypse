package org.apocalypse.core.weapon.projectile.type;

import org.apocalypse.api.weapon.projectile.type.BulletType;
import org.bukkit.entity.Arrow;

public class Rounds extends BulletType {

    public Rounds() {
        super("Rounds", Arrow.class, 8, 5.0F);
    }
}
