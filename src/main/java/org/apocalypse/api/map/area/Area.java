package org.apocalypse.api.map.area;

import lombok.Getter;
import org.apocalypse.api.location.Location;
import org.apocalypse.api.map.area.door.Door;
import org.apocalypse.api.map.area.loot.Loot;
import org.apocalypse.api.map.area.spawn.Spawn;
import org.apocalypse.api.map.area.storage.Storage;

import java.util.List;

@Getter
public class Area {

    private final String name;
    private final Location location;
    private final List<Door> doors;
    private final List<Storage> storages;
    private final List<Spawn> spawns;
    private final Loot loot;

    public Area(String name, Location location, List<Door> doors, List<Storage> storages, List<Spawn> spawns, Loot loot) {
        this.name = name;
        this.location = location;
        this.doors = doors;
        this.storages = storages;
        this.spawns = spawns;
        this.loot = loot;
    }

    public boolean isOpen() {
        if (this.doors.isEmpty()) return true;
        return this.doors.stream().anyMatch(Door::isOpen);
    }
}
