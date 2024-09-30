package org.apocalypse.api.map.area.loot;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apocalypse.api.location.Location;

@Getter
@Setter
@RequiredArgsConstructor
public class Loot {

    private final Location location;
    private int tries = 0;
}
