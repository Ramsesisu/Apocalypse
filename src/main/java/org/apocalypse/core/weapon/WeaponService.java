package org.apocalypse.core.weapon;

import org.apocalypse.api.service.Service;
import org.apocalypse.api.weapon.Weapon;

public class WeaponService extends Service<Integer, Weapon> {

    @Override
    public Weapon get(Integer key) {
        for (Weapon weapon : this.list.values()) {
            if (weapon.getKey() == key)
                return weapon;
        } return null;
    }
}
