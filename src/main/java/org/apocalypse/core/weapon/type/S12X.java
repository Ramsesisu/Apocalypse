package org.apocalypse.core.weapon.type;

import org.apocalypse.api.weapon.type.WeaponType;
import org.apocalypse.core.weapon.projectile.Shells;
import org.bukkit.Material;

public class S12X extends WeaponType {

    public S12X() {
        super("S12X", Material.IRON_HOE, Shells.class, 5, 1.0F, 25, 200);
    }
}
