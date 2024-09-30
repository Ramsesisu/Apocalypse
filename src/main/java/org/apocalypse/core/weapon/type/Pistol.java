package org.apocalypse.core.weapon.type;

import org.apocalypse.api.weapon.type.WeaponType;
import org.apocalypse.core.weapon.projectile.type.Rounds;
import org.bukkit.Material;

public class Pistol extends WeaponType {

    public Pistol() {
        super("Pistol", Material.WOODEN_HOE, false, Rounds.class, 0.8F, 8, 300);
    }
}
