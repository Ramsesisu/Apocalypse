package org.apocalypse.core.weapon;

import lombok.SneakyThrows;
import org.apocalypse.Apocalypse;
import org.apocalypse.api.service.Service;
import org.apocalypse.api.weapon.types.WeaponType;

public class WeaponService extends Service<WeaponType> {

    @SneakyThrows
    @Override
    public void load() {
        for (Class<? extends WeaponType> clazz : Apocalypse.findClasses(WeaponType.class)) {
            WeaponType weapon = clazz.getConstructor().newInstance();
            list.put(clazz, weapon);
        }
    }
}
