package org.apocalypse.core.weapon.type;

import org.apocalypse.api.weapon.type.WeaponType;
import org.apocalypse.core.weapon.projectile.Bullet;
import org.bukkit.Material;

public class SR7V extends WeaponType {

    public SR7V() {
        super("SR7V", Material.WOODEN_SHOVEL, Bullet.class, 15.0F, 1.5F, 5, 80);
    }
}
