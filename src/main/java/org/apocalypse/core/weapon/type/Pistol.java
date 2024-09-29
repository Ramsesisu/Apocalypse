package org.apocalypse.core.weapon.type;

import org.apocalypse.api.weapon.type.WeaponType;
import org.apocalypse.core.weapon.projectile.Rounds;
import org.bukkit.Material;

public class Pistol extends WeaponType {

    public Pistol() {
        super("Pistol", Material.WOODEN_HOE, Rounds.class, 0.5F, 8, 300);
    }
}
