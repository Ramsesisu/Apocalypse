package org.apocalypse.api.weapon;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apocalypse.api.builder.ItemBuilder;
import org.apocalypse.api.player.Survivor;
import org.apocalypse.api.weapon.type.WeaponType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Getter
@Setter
public class Weapon {

    public enum Type {
        MELEE,
        GUN
    }

    private final UUID key;
    private final ItemStack item;
    private final WeaponType type;
    @Setter(AccessLevel.NONE)
    private int ammo;
    private int magazine;
    private boolean enchanted = false;
    private long cooldown = 0;
    private Survivor player = null;
    private boolean reloading = false;

    public Weapon(Class<? extends WeaponType> type) throws Exception {
        this(type.getConstructor().newInstance());
    }

    public Weapon(@NotNull WeaponType type) {
        this.key = UUID.randomUUID();
        this.type = type;
        this.ammo = type.getAmmo();
        this.magazine = type.getMagazine();
        if (type.getType() == Type.GUN)
            this.item = ItemBuilder.create(type.getItem()).setName("§6" + type.getName()).setLore("§6" + ammo + "§8/§6" + magazine).saveData("uuid", key.toString()).build();
        else
            this.item = ItemBuilder.create(type.getItem()).setName("§6" + type.getName()).saveData("uuid", key.toString()).build();
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
