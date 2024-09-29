package org.apocalypse.core.lobby;

import org.apocalypse.api.lobby.Lobby;
import org.apocalypse.api.map.Map;
import org.apocalypse.api.player.Survivor;
import org.apocalypse.api.service.Service;

import java.util.Collection;

public class LobbyService extends Service<Integer, Lobby> {

    public Collection<Lobby> getLobbies() {
        return this.list.values();
    }

    public Lobby find(Survivor survivor, Class<? extends Map> map) {
        for (Lobby lobby : this.getLobbies()) {
            if (lobby.getMap().getClass().equals(map))
                if (lobby.size() < 4)
                    return lobby;
        }
        Lobby lobby = new Lobby(map);
        this.add(lobby.hashCode(), lobby);
        return lobby;
    }
}
