package org.apocalypse.core.weapon.type;

import org.apocalypse.api.weapon.type.WeaponType;
import org.apocalypse.core.weapon.projectile.Bullet;
import org.bukkit.Material;

public class AK47 extends WeaponType {

    public AK47() {
        super("AK47", Material.STONE_HOE, Bullet.class, 2.5, 0.15F, 45, 320);
    }
}
