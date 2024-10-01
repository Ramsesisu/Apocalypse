package org.apocalypse.api.map.area.door;

import lombok.Getter;
import org.apocalypse.api.location.Location;
import org.apocalypse.api.location.iterator.BlockIterator;
import org.apocalypse.api.map.hologram.Hologram;
import org.bukkit.Material;
import org.bukkit.World;

@Getter
public class Door {

    private final String name;
    private final Location first;
    private final Location second;
    private final Hologram hologram;
    private final int cost;
    private boolean open;

    public Door(String name, Location first, Location second, Location hologram, int cost) {
        this.name = name;
        this.first = first;
        this.second = second;
        this.hologram = new Hologram("§e§l" + name + "§8: §6" + cost + "$", hologram);
        this.cost = cost;
    }

    public void open(World world) {
        new BlockIterator(world, first, second).forEachRemaining(block -> block.setType(Material.AIR));
        this.open = true;
        this.hologram.remove();
    }
}
