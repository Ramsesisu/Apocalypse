package org.apocalypse.core.weapon;

import lombok.SneakyThrows;
import org.apocalypse.api.builder.ItemBuilder;
import org.apocalypse.api.player.Survivor;
import org.apocalypse.api.service.Service;
import org.apocalypse.api.service.container.Container;
import org.apocalypse.api.weapon.Weapon;
import org.apocalypse.api.weapon.type.WeaponType;
import org.bukkit.Material;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.UUID;

public class WeaponService extends Service<UUID, Weapon> {

    @SneakyThrows
    public boolean give(Class<? extends WeaponType> weapon, Survivor survivor) {
        return this.give(new Weapon(weapon), survivor);
    }

    @SneakyThrows
    public boolean give(Class<? extends WeaponType> weapon, Survivor survivor, int slot) {
        return this.give(new Weapon(weapon), survivor, slot);
    }

    public boolean give(Weapon weapon, Survivor survivor) {
        int slot = survivor.online().getInventory().getHeldItemSlot();
        if (survivor.online().getInventory().getItemInMainHand().getType() != Material.FIREWORK_STAR) {
            if (!Container.get(WeaponRecord.class).isGun(survivor.online().getInventory().getItemInMainHand())) {
                survivor.sendMessage("§cYou can hold Weapons in your Weapon-Slots only.");
                return false;
            }
        }
        if (weapon.getType().getType() == Weapon.Type.MELEE && slot != 0) {
            survivor.sendMessage("§cYou can hold Melee Weapons in your Melee-Slot only.");
            return false;
        }
        return this.give(weapon, survivor, slot);
    }

    public boolean give(Weapon weapon, Survivor survivor, int slot) {
        survivor.give(slot, weapon.getItem());
        this.add(weapon.getKey(), weapon);
        return true;
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
