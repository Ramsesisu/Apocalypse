package org.apocalypse.core.lobby;

import org.apocalypse.api.lobby.Lobby;
import org.apocalypse.api.map.Map;
import org.apocalypse.api.player.Survivor;
import org.apocalypse.api.service.Service;
import org.apocalypse.api.service.container.Container;
import org.apocalypse.core.map.MapRecord;

import java.util.Collection;

public class LobbyService extends Service<Integer, Lobby> {

    public Collection<Lobby> getLobbies() {
        return this.list.values();
    }

    public Lobby find(Class<? extends Map> map) {
        return this.find(Container.get(MapRecord.class).get(map));
    }

    public Lobby find(Map map) {
        for (int i : this.list.keySet()) {
            Lobby lobby = this.get(i);
            if (lobby.size() == 0 && lobby.isStarted()) {
                lobby.removeLobby();
                this.remove(i);
            }
        }
        for (Lobby lobby : this.getLobbies()) {
            if (lobby.getMap().equals(map))
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
