package org.apocalypse.core.weapon.type;

import org.apocalypse.api.weapon.type.WeaponType;
import org.apocalypse.core.weapon.projectile.type.Magnums;
import org.bukkit.Material;

public class Revolver extends WeaponType {

    public Revolver() {
        super("Revolver", Material.STONE_HOE, false, Magnums.class, 1.0F, 6, 150);
    }
}
