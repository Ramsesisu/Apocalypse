package org.apocalypse.core.weapon.type;

import org.apocalypse.api.weapon.type.WeaponType;
import org.apocalypse.core.weapon.projectile.Rocket;
import org.bukkit.Material;

public class HR13 extends WeaponType {

    public HR13() {
        super("HR13", Material.STONE_SHOVEL, Rocket.class, 20, 2.5F, 2, 30);
    }
}
