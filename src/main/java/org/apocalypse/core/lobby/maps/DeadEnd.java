package org.apocalypse.core.lobby.maps;

import org.apocalypse.api.location.Location;
import org.apocalypse.api.map.Map;
import org.apocalypse.api.map.area.Area;
import org.apocalypse.api.map.area.loot.Loot;
import org.apocalypse.api.map.area.spawn.Spawn;
import org.apocalypse.api.map.area.spawn.barrier.Barrier;
import org.apocalypse.core.monster.type.Zombie;

import java.util.List;

public class DeadEnd extends Map {

    public DeadEnd() {
        super("Dead End", new Location(17, 67, 23, -30),
                List.of(Zombie.class),
                List.of(new Area("Street",
                            new Location(0, 0, 0),
                            List.of(new Spawn(new Location(6, 69, 31), new Barrier(new Location(10, 71, 28), new Location(10, 70, 26))),
                                    new Spawn(new Location(4, 69, 43), new Barrier(new Location(10, 71, 40), new Location(10, 70, 38)))),
                            new Loot(new Location(16, 68, 19)))));
    }
}
