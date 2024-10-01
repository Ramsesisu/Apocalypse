package org.apocalypse.core.weapon.type;

import org.apocalypse.api.weapon.type.WeaponType;
import org.apocalypse.core.weapon.projectile.Bullet;
import org.bukkit.Material;

public class M4A1 extends WeaponType {

    public M4A1() {
        super("M4A1", Material.DIAMOND_HOE, Bullet.class, 3, 0.2F, 35, 250);
    }
}
