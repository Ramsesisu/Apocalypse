package org.apocalypse.core.weapon.type;

import org.apocalypse.api.weapon.type.WeaponType;
import org.apocalypse.core.weapon.projectile.Shells;
import org.bukkit.Material;

public class Shotgun extends WeaponType {

    public Shotgun() {
        super("Shotgun", Material.GOLDEN_AXE, false, Shells.class, 1.4F, 5, 50);
    }
}
