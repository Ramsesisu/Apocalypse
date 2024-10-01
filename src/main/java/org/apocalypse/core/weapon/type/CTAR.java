package org.apocalypse.core.weapon.type;

import org.apocalypse.api.weapon.type.WeaponType;
import org.apocalypse.core.weapon.projectile.Bullet;
import org.bukkit.Material;

public class CTAR extends WeaponType {

    public CTAR() {
        super("CTAR", Material.GOLDEN_PICKAXE, Bullet.class, 4, 0.2F, 25, 200);
    }
}
