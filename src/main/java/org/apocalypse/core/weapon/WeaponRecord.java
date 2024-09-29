package org.apocalypse.core.weapon;

import lombok.SneakyThrows;
import org.apocalypse.Apocalypse;
import org.apocalypse.api.service.Record;
import org.apocalypse.api.weapon.Weapon;
import org.apocalypse.api.weapon.types.WeaponType;
import org.bukkit.inventory.ItemStack;

public class WeaponRecord extends Record<WeaponType> {

    @SneakyThrows
    @Override
    public void load() {
        for (Class<? extends WeaponType> clazz : Apocalypse.findClasses(WeaponType.class)) {
            WeaponType weapon = clazz.getConstructor().newInstance();
            list.put(clazz, weapon);
        }
    }

    public Weapon get(ItemStack item) {
        for (WeaponType weapon : list.values()) {
            if (weapon.getItem() == item.getType())
                return weapon.getWeapon();
        } return null;
    }

    public WeaponType getWeapon(ItemStack item) {
        for (WeaponType weapon : list.values()) {
            if (weapon.getItem() == item.getType())
                return weapon;
        } return null;
    }

    public boolean isWeapon(ItemStack item) {
        return getWeapon(item) != null;
    }

    public boolean isGun(ItemStack item) {
        if (!isWeapon(item))
            return false;
        return getWeapon(item).getType() == Weapon.Type.GUN;
    }
}
