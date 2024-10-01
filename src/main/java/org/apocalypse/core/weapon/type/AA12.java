package org.apocalypse.core.weapon.type;

import org.apocalypse.api.weapon.type.WeaponType;
import org.apocalypse.core.weapon.projectile.Bullet;
import org.bukkit.Material;

public class AA12 extends WeaponType {

    public AA12() {
        super("AA12", Material.WOODEN_HOE, Bullet.class, 6.0F, 0.65F, 10, 300);
    }
}
