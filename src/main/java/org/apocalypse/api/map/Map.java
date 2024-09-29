package org.apocalypse.api.map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apocalypse.api.monster.type.MonsterType;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Map {

    private final String name;
    private final List<MonsterType> monsterTypes;
}
