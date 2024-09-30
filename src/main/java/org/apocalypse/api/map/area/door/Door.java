package org.apocalypse.api.map.area.door;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apocalypse.api.location.Location;

@Getter
@Setter
@RequiredArgsConstructor
public class Door {

    private final Location first;
    private final Location second;
    private final int cost;
    private boolean open;
}
