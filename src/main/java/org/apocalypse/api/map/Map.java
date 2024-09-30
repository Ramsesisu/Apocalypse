package org.apocalypse.api.map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apocalypse.api.location.Location;
import org.apocalypse.api.map.area.Area;
import org.apocalypse.api.monster.type.MonsterType;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Map {

    private final String name;
    private final Location spawn;
    private final List<Class<? extends MonsterType>> monster;
    private final List<Area> areas;
}
