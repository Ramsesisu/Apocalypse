package org.apocalypse.api.weapon;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apocalypse.api.builder.ItemBuilder;
import org.apocalypse.api.service.container.Container;
import org.apocalypse.api.weapon.type.WeaponType;
import org.apocalypse.core.weapon.WeaponService;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class Weapon {

    public enum Type {
        MELEE,
        GUN
    }

    private final ItemStack item;
    private final WeaponType type;
    @Setter(AccessLevel.NONE)
    private int ammo;
    private int magazine;
    private boolean enchanted = false;
    private long cooldown = 0;

    public Weapon(@NotNull WeaponType type) {
        this.type = type;
        this.ammo = type.getAmmo();
        this.magazine = type.getMagazine();
        this.item = ItemBuilder.create(type.getItem()).setName("§7" + type.getName()).setLore("§6" + ammo + "§8/§6" + magazine).build();

        Container.get(WeaponService.class).add(this.item.getItemMeta(), this);
    }

    public boolean isCooldown() {
        return System.currentTimeMillis() < (cooldown + Math.round(type.getSpeed() * 1000));
    }

    public boolean removeAmmo() {
        if (this.ammo <= 0)
            return false;
        this.ammo--;
        return true;
    }

    public boolean addAmmo() {
        if (this.ammo >= type.getMagazine())
            return false;
        this.ammo++;
        return true;
    }

    public void fillAmmo() {
        int r = type.getMagazine() - this.ammo;
        magazine = magazine - r;
        for (int i = 0; i < r; i++) {
            if (!this.addAmmo()) {
                magazine = 0;
                break;
            }
        }
    }

    public void updateLore() {
        ItemBuilder.get(item).setLore("§6" + ammo + "§8/§6" + magazine);
    }
}
