package org.apocalypse.core.weapon;

import org.apocalypse.api.builder.ItemBuilder;
import org.apocalypse.api.service.Service;
import org.apocalypse.api.weapon.Weapon;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class WeaponService extends Service<UUID, Weapon> {

    public Weapon get(final String key) {
        return this.get(UUID.fromString(key));
    }

    public Weapon get(final ItemStack item) {
        String uuid = ItemBuilder.get(item).loadData("uuid");
        return this.get(uuid);
    }
}
