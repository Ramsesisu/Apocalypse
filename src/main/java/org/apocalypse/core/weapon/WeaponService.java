package org.apocalypse.core.weapon;

import lombok.SneakyThrows;
import org.apocalypse.api.builder.ItemBuilder;
import org.apocalypse.api.player.Survivor;
import org.apocalypse.api.service.Service;
import org.apocalypse.api.weapon.Weapon;
import org.apocalypse.api.weapon.type.WeaponType;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.UUID;

public class WeaponService extends Service<UUID, Weapon> {

    @SneakyThrows
    public void give(Class<? extends WeaponType> weapon, Survivor survivor) {
        this.give(new Weapon(weapon), survivor);
    }

    public void give(Weapon weapon, Survivor survivor) {
        survivor.give(weapon.getItem());
        this.add(weapon.getKey(), weapon);
    }

    public Weapon get(final String key) {
        return this.get(UUID.fromString(key));
    }

    public Weapon get(final ItemStack item) {
        String uuid = ItemBuilder.get(item).loadData("uuid");
        return this.get(uuid);
    }

    @SuppressWarnings("deprecation")
    public Weapon get(Projectile projectile) {
        if (!Objects.requireNonNull(projectile.getCustomName()).isEmpty()) {
            for (Weapon weapon : this.list.values()) {
                if (weapon.getKey().toString().equalsIgnoreCase(projectile.getCustomName()))
                    return weapon;
            }
        } return null;
    }
}
