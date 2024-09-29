package org.apocalypse.api.weapon;

import lombok.Getter;
import org.apocalypse.api.builder.ItemBuilder;
import org.apocalypse.api.weapon.types.WeaponType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

@Getter
public class Weapon {

    public enum Type {
        MELEE,
        GUN
    }

    private final ItemStack item;
    private final WeaponType type;
    private int ammo = 0;
    private int magazine = 0;

    public Weapon(@NotNull WeaponType type) {
        this.item = ItemBuilder.create(type.getItem()).setName(type.getName()).build();
        this.type = type;
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
}
