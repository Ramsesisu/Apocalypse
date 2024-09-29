package org.apocalypse.api.weapon;

import lombok.Getter;
import lombok.Setter;
import org.apocalypse.api.builder.ItemBuilder;
import org.apocalypse.api.weapon.types.WeaponType;
import org.bukkit.inventory.ItemStack;

@Getter
public class Weapon {

    public enum Type {
        MELEE,
        GUN
    }

    private final ItemStack item;
    private final WeaponType type;
    @Setter
    private int ammo;

    public Weapon(WeaponType type) {
        this.item = ItemBuilder.create(type.getItem()).setName(type.getName()).build();
        this.type = type;
    }
}
