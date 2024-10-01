package org.apocalypse.core.weapon.type;

import org.apocalypse.api.weapon.type.WeaponType;
import org.apocalypse.core.weapon.projectile.Bullet;
import org.bukkit.Material;

public class VLR9 extends WeaponType {

    public VLR9() {
        super("VLR9", Material.GOLDEN_HOE, Bullet.class, 16.5F, 1.4F, 4, 65);
    }
}
