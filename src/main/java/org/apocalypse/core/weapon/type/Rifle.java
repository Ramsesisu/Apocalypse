package org.apocalypse.core.weapon.type;

import org.apocalypse.api.weapon.type.WeaponType;
import org.apocalypse.core.weapon.projectile.type.Ammo;
import org.bukkit.Material;

public class Rifle extends WeaponType {

    public Rifle() {
        super("Rifle", Material.IRON_HOE, false, Ammo.class, 0.2F, 30, 200);
    }
}
