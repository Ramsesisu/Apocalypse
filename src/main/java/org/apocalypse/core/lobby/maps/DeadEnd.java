package org.apocalypse.core.lobby.maps;

import org.apocalypse.api.location.Location;
import org.apocalypse.api.map.Map;
import org.apocalypse.api.map.area.Area;
import org.apocalypse.api.map.area.door.Door;
import org.apocalypse.api.map.area.loot.Loot;
import org.apocalypse.api.map.area.spawn.Spawn;
import org.apocalypse.api.map.area.spawn.barrier.Barrier;
import org.apocalypse.core.monster.type.Zombie;

import java.util.List;

public class DeadEnd extends Map {

    public DeadEnd() {
        super("Dead End", new Location(17, 67, 23, -30),
                List.of(Zombie.class),
                List.of(new Area("Street", new Location(22, 68, 26),
                            List.of(new Spawn(new Location(6, 69, 31), new Barrier(new Location(10, 70, 28), new Location(10, 71, 26))),
                                    new Spawn(new Location(4, 69, 43), new Barrier(new Location(10, 70, 40), new Location(10, 71, 38))),
                                    new Spawn(new Location(39, 70, 8), new Barrier(new Location(35, 71, 14), new Location(37, 72, 14))),
                                    new Spawn(new Location(42, 70, 29), new Barrier(new Location(37, 71, 24), new Location(37, 72, 26)))),
                            new Loot(new Location(16, 68, 19))),
                        new Area("Hotel", new Location(2, 69, 18), new Door(new Location(11, 68, 22), new Location(11, 71, 20), 750),
                                List.of(new Spawn(new Location(-10, 68, 14), new Barrier(new Location(-8, 69, 18), new Location(-6, 70, 18))),
                                        new Spawn(new Location(0, 68, 46), new Barrier(new Location(-3, 69, 37), new Location(-5, 70, 37))),
                                        new Spawn(new Location(24, 69, 3), new Barrier(new Location(25, 70, 9), new Location(25, 71, 11))),
                                        new Spawn(new Location(25, 69, -4), new Barrier(new Location(22, 70, -2), new Location(22, 71, 0)))),
                                new Loot(new Location(-9, 67, 24)))));
    }
}
