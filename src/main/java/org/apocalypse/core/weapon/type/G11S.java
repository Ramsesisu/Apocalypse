package org.apocalypse.core.weapon.type;

import org.apocalypse.api.weapon.type.WeaponType;
import org.apocalypse.core.weapon.projectile.Bullet;
import org.bukkit.Material;

public class G11S extends WeaponType {

    public G11S() {
        super("G11S", Material.IRON_SHOVEL, Bullet.class, 4.5, 0.35F, 30, 220);
    }
}
