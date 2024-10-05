package org.apocalypse.api.map.hologram;

import lombok.Getter;
import org.apocalypse.api.location.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;

@SuppressWarnings("deprecation")
@Getter
public class Hologram {

    private final Location location;
    private final ArmorStand entity;

    public Hologram(String text, Location location) {
        this.location = location;
        this.entity = location.getWorld().createEntity(location.get(), ArmorStand.class);
        this.entity.setInvisible(true);
        this.entity.setInvulnerable(true);
        this.entity.setGravity(false);
        this.entity.setCustomName(text);
        this.entity.setCustomNameVisible(true);
    }

    public void setText(String text) {
        this.entity.setCustomName(text);
    }

    public String getText() {
        return this.entity.getCustomName();
    }

    public void spawn(World world) {
        this.entity.spawnAt(location.get(world));
    }

    public void remove() {
        this.entity.remove();
    }
}
