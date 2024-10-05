package org.apocalypse.api.map.area.storage.type;

import lombok.Getter;
import org.apocalypse.api.location.Location;
import org.apocalypse.api.map.area.storage.Storage;
import org.apocalypse.api.player.Survivor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Getter
public class ArmorStorage extends Storage {

    private final List<Material> armor;

    public ArmorStorage(String name, Location location, List<Material> armor, int cost) {
        super(name, location, cost);
        this.armor = armor;
        for (Material material : armor) {
            ItemStack item = new ItemStack(material);
            String named = material.name();
            if (named.contains("_HELMET")) {
                this.display.getEquipment().setHelmet(item);
            } else if (named.contains("_CHESTPLATE")) {
                this.display.getEquipment().setChestplate(item);
            } else if (named.contains("_LEGGINGS")) {
                this.display.getEquipment().setLeggings(item);
            } else if (named.contains("_BOOTS")) {
                this.display.getEquipment().setBoots(item);
            }
        }
    }

    @Override
    public void buy(Survivor survivor) {
        if (survivor.removeMoney(this.getCost())) {
            for (Material material : this.armor) {
                ItemStack item = new ItemStack(material);
                String named = material.name();
                if (named.contains("_HELMET")) {
                    survivor.online().getEquipment().setHelmet(item);
                } else if (named.contains("_CHESTPLATE")) {
                    survivor.online().getEquipment().setChestplate(item);
                } else if (named.contains("_LEGGINGS")) {
                    survivor.online().getEquipment().setLeggings(item);
                } else if (named.contains("_BOOTS")) {
                    survivor.online().getEquipment().setBoots(item);
                }
            }
        }
    }
}
