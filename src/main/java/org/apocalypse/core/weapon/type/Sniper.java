package org.apocalypse.core.weapon.type;

import org.apocalypse.api.weapon.type.WeaponType;
import org.apocalypse.core.weapon.projectile.type.Bullets;
import org.bukkit.Material;

public class Sniper extends WeaponType {

    public Sniper() {
        super("Sniper", Material.GOLDEN_SHOVEL, false, Bullets.class, 1.5F, 4, 60);
    }
}
