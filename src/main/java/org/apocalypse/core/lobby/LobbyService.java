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

    public Lobby find(Class<? extends Map> map) {
        for (int i : this.list.keySet()) {
            Lobby lobby = this.get(i);
            if (lobby.size() == 0 && lobby.isStarted())
                this.remove(i);
        }
        for (Lobby lobby : this.getLobbies()) {
            if (lobby.getMap().getClass().equals(map))
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
