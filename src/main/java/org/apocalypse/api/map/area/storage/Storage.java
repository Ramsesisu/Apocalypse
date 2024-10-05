package org.apocalypse.api.map.area.storage;

import lombok.Getter;
import org.apocalypse.api.location.Location;
import org.apocalypse.api.player.Survivor;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;

@Getter
public abstract class Storage {

    private final String name;
    private final Location location;
    protected final ArmorStand display;
    private final int cost;

    protected Storage(String name, Location location, int cost) {
        this.name = "§e§l" + name + "§8: §6" + cost + "$";
        this.location = location;
        this.cost = cost;
        this.display = location.getWorld().createEntity(location.get(), ArmorStand.class);
        this.display.setInvisible(true);
        this.display.setCustomName(this.name);
        this.display.setCustomNameVisible(true);
        this.display.setInvulnerable(true);
        this.display.setGravity(false);
    }

    public void spawn(World world) {
        this.display.spawnAt(this.location.get(world));
    }

    public abstract void buy(Survivor survivor);
}
