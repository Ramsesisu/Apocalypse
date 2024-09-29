package org.apocalypse.core.weapon.types;

import org.apocalypse.api.weapon.types.WeaponType;
import org.apocalypse.core.weapon.projectiles.Rounds;
import org.bukkit.Material;

public class Pistol extends WeaponType {

    public Pistol() {
        super("Pistol", Material.WOODEN_HOE, Rounds.class, 0.5F, 8, 300);
    }
}
