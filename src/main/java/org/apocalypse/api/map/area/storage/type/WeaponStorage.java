package org.apocalypse.api.map.area.storage.type;

import lombok.Getter;
import lombok.SneakyThrows;
import org.apocalypse.api.location.Location;
import org.apocalypse.api.map.area.storage.Storage;
import org.apocalypse.api.player.Survivor;
import org.apocalypse.api.service.container.Container;
import org.apocalypse.api.weapon.type.WeaponType;
import org.apocalypse.core.weapon.WeaponService;
import org.bukkit.inventory.ItemStack;

@Getter
public class WeaponStorage extends Storage {

    private final Class<? extends WeaponType> weapon;

    @SneakyThrows
    public WeaponStorage(String name, Location location, Class<? extends WeaponType> weapon, int cost) {
        super(name, location, cost);
        this.weapon = weapon;
        this.display.getEquipment().setHelmet(new ItemStack(weapon.getConstructor().newInstance().getItem()));
    }

    @SneakyThrows
    @Override
    public void buy(Survivor survivor) {
        if (survivor.removeMoney(this.getCost()))
            Container.get(WeaponService.class).give(weapon, survivor);
    }
}
