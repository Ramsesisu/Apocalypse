package org.apocalypse.core.weapon.type;

import org.apocalypse.api.weapon.type.WeaponType;
import org.apocalypse.core.weapon.projectile.Rounds;
import org.bukkit.Material;

public class Pistol extends WeaponType {

    public Pistol() {
        super("Pistol", Material.WOODEN_HOE, false, Rounds.class, 1.0F, 8, 300);
    }
}
