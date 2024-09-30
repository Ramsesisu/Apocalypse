package org.apocalypse.api.map.area.spawn;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apocalypse.api.location.Location;
import org.apocalypse.api.map.area.spawn.barrier.Barrier;

@Getter
@RequiredArgsConstructor
public class Spawn {

    private final Location location;
    private final Barrier barrier;
}
