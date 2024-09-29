package org.apocalypse.core.weapon;

import org.apocalypse.api.service.Record;
import org.apocalypse.api.weapon.Weapon;
import org.apocalypse.api.weapon.types.WeaponType;
import org.bukkit.inventory.ItemStack;

public class WeaponRecord extends Record<WeaponType> {

    public WeaponType get(ItemStack item) {
        for (WeaponType weapon : list.values()) {
            if (weapon.getItem() == item.getType())
                return weapon;
        } return null;
    }

    public boolean isWeapon(ItemStack item) {
        return get(item) != null;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean isGun(ItemStack item) {
        if (!isWeapon(item))
            return false;
        return get(item).getType() == Weapon.Type.GUN;
    }
}
