package org.apocalypse.api.map.area;

import lombok.Getter;
import org.apocalypse.api.location.Location;
import org.apocalypse.api.map.area.door.Door;
import org.apocalypse.api.map.area.loot.Loot;
import org.apocalypse.api.map.area.spawn.Spawn;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Area {

    private final String name;
    private final Location location;
    private final List<Door> doors;
    private final List<Spawn> spawns;
    private final Loot loot;

    public Area(String name, Location location, Door door, List<Spawn> spawns, Loot loot) {
        this.name = name;
        this.location = location;
        this.doors = List.of(door);
        this.spawns = spawns;
        this.loot = loot;
    }

    public Area(String name, Location location, List<Door> doors, List<Spawn> spawns, Loot loot) {
        this.name = name;
        this.location = location;
        this.doors = doors;
        this.spawns = spawns;
        this.loot = loot;
    }

    public Area(String name, Location location, List<Spawn> spawns, Loot loot) {
        this.name = name;
        this.location = location;
        this.doors = new ArrayList<>();
        this.spawns = spawns;
        this.loot = loot;
    }

    public boolean isOpen() {
        if (this.doors.isEmpty()) return true;
        return this.doors.stream().anyMatch(Door::isOpen);
    }
}
