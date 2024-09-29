package org.apocalypse.core.weapon.type;

import org.apocalypse.api.weapon.type.WeaponType;
import org.apocalypse.core.weapon.projectile.Ammo;
import org.bukkit.Material;

public class Rifle extends WeaponType {

    public Rifle() {
        super("Rifle", Material.STONE_HOE, Ammo.class, 0.2F, 30, 200);
    }
}
