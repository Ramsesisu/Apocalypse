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

    private int key;
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

        this.key = this.getItem().hashCode();
        Container.get(WeaponService.class).add(this.key, this);
    }

    public boolean isCooldown() {
        return System.currentTimeMillis() < cooldown;
    }

    public boolean removeAmmo() {
        if (this.ammo <= 0)
            return false;
        this.ammo--;
        return true;
    }

    public boolean addAmmo() {
        if (this.ammo >= type.getAmmo())
            return false;
        this.ammo++;
        this.magazine--;
        return true;
    }

    public boolean isFull() {
        return this.ammo >= this.type.getAmmo();
    }

    public void fillAmmo() {
        int r = this.type.getAmmo() - this.ammo;
        for (int i = 0; i < r; i++) {
            if (this.magazine <= 0) break;
            if (!this.addAmmo()) break;
        }
    }

    public void updateLore() {
        ItemBuilder.get(this.item).setLore("§6" + this.ammo + "§8/§6" + this.magazine).build();
    }
}
