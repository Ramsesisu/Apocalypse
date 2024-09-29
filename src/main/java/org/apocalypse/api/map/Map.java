package org.apocalypse.api.map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apocalypse.api.monster.type.MonsterType;
import org.bukkit.Location;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Map {

    private final String name;
    private final Location origin;
    private final List<Class<? extends MonsterType>> monster;
    private final List<Location> spawns;
}
