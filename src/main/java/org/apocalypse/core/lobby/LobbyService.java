package org.apocalypse.core.lobby;

import org.apocalypse.api.config.MapConfig;
import org.apocalypse.api.lobby.Lobby;
import org.apocalypse.api.map.factory.MapFactory;
import org.apocalypse.api.player.Survivor;
import org.apocalypse.api.service.Service;

import java.util.Collection;

public class LobbyService extends Service<Integer, Lobby> {

    public Collection<Lobby> getLobbies() {
        return this.list.values();
    }

    public Lobby find(String map) {
        return this.find(MapFactory.mapsWrapper.getMap(map));
    }

    public Lobby find(MapConfig map) {
        for (Lobby lobby : this.getLobbies()) {
            if (lobby.getMap().getName().equalsIgnoreCase(map.getName()))
                if (lobby.size() < 4 && !lobby.isStarted())
                    return lobby;
        }
        Lobby lobby = new Lobby(map);
        this.add(lobby.hashCode(), lobby);
        return lobby;
    }

    public Lobby find(Survivor survivor) {
        if (survivor.getLobby() == null) return null;
        for (Lobby lobby : this.getLobbies()) {
            if (survivor.getLobby() == lobby)
                return lobby;
        }
        return null;
    }
}
