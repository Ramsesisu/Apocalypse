package org.apocalypse.api.map.wrapper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apocalypse.api.config.MapConfig;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MapsWrapper {
    private List<MapConfig> maps;

    public MapConfig getMap(String name) {
        for (MapConfig map : this.maps) {
            if (map.getName().replace(" ", "").equalsIgnoreCase(name))
                return map;
        }
        return null;
    }
}