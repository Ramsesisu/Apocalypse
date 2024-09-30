package org.apocalypse.core.weapon.type;

import org.apocalypse.api.weapon.type.WeaponType;
import org.apocalypse.core.weapon.projectile.Cartridges;
import org.bukkit.Material;

public class Uzi extends WeaponType {

    public Uzi() {
        super("Uzi", Material.GOLDEN_HOE, false, Cartridges.class, 0.15F, 45, 250);
    }
}
