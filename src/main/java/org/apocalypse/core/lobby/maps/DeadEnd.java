package org.apocalypse.core.lobby.maps;

import org.apocalypse.api.location.Location;
import org.apocalypse.api.map.Map;
import org.apocalypse.core.monster.type.Zombie;

import java.util.List;

public class DeadEnd extends Map {

    public DeadEnd() {
        super("Dead End", new Location(17, 67, 23, -30),
                List.of(Zombie.class),
                List.of(new Location(0, 0, 0)));
    }
}
