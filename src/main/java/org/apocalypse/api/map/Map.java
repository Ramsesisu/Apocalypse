package org.apocalypse.api.map;

import lombok.Getter;
import org.apocalypse.api.location.Location;
import org.apocalypse.api.map.area.Area;
import org.apocalypse.api.map.area.loot.Loot;
import org.apocalypse.api.map.area.spawn.Spawn;
import org.apocalypse.api.monster.type.MonsterType;
import org.apocalypse.api.weapon.type.WeaponType;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public abstract class Map {

    private final String name;
    private final Location spawn;
    private final List<Class<? extends MonsterType>> monster;
    private final List<Area> areas;
    private final List<Class<? extends WeaponType>> loot;

    protected Map(String name, Location spawn, List<Class<? extends MonsterType>> monster, List<Area> areas, List<Class<? extends WeaponType>> loot) {
        this.name = name;
        this.spawn = spawn;
        this.monster = monster;
        this.areas = areas;
        this.loot = loot;
    }

    public List<Spawn> getSpawns() {
        return areas.stream()
                .filter(Area::isOpen)
                .flatMap(area -> area.getSpawns().stream())
                .collect(Collectors.toList());
    }

    public List<Loot> getLootChests() {
        return areas.stream()
                .map(Area::getLoot)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
