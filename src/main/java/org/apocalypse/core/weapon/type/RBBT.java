package org.apocalypse.core.weapon.type;

import org.apocalypse.api.weapon.type.WeaponType;
import org.apocalypse.core.weapon.projectile.Plasma;
import org.bukkit.Material;

public class RBBT extends WeaponType {

    public RBBT() {
        super("RBBT", Material.DIAMOND_PICKAXE, Plasma.class, 10, 0.6F, 12, 120);
    }
}
