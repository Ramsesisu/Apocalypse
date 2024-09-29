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
        Lobby lobby = null;
        for (Lobby l : this.getLobbies()) {
            if (l.getMap().getClass().equals(map))
                if (l.size() < 4)
                    lobby = l;
        }
        if (lobby == null) {
            lobby = new Lobby(map);
            this.add(lobby.hashCode(), lobby);
        }
        lobby.add(survivor);
        survivor.lobby(lobby);
        return lobby;
    }
}
