package org.apocalypse.core.lobby.maps;

import org.apocalypse.api.map.Map;
import org.apocalypse.api.utils.LocationUtils;
import org.apocalypse.core.monster.type.Zombie;

import java.util.List;

public class DeadEnd extends Map {

    public DeadEnd() {
        super("Dead End", LocationUtils.get(0, 100, 0),
                List.of(Zombie.class),
                List.of(LocationUtils.get(0, 0, 0)));
    }
}
