package org.apocalypse.core.weapon.type;

import org.apocalypse.api.weapon.type.WeaponType;
import org.bukkit.Material;

public class Knife extends WeaponType {

    public Knife() {
        super("Knife", Material.IRON_SWORD, 10.0F, 1.5F);
    }
}
