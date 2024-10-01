package org.apocalypse.core.monster.type;

import org.apocalypse.api.monster.type.MonsterType;
import org.bukkit.Material;
import org.bukkit.entity.Zombie;

public class Baby extends MonsterType {

    public Baby() {
        super("Baby", Zombie.class, true, 5, 17, 10, 3, Material.TNT, Material.AIR, Material.AIR, Material.AIR);
    }
}
