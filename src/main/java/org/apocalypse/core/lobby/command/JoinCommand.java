package org.apocalypse.core.lobby.command;

import org.apocalypse.api.command.Command;
import org.apocalypse.api.command.CommandExecutor;
import org.apocalypse.api.command.Prefix;
import org.apocalypse.api.lobby.Lobby;
import org.apocalypse.api.player.Survivor;
import org.apocalypse.api.service.container.Container;
import org.apocalypse.core.lobby.LobbyService;
import org.apocalypse.core.lobby.maps.DeadEnd;

@Command(name = "join", usage = "/join", prefix = "Join", description = "Join a game.", prefixColor = Prefix.Color.YELLOW, prefixGradient = Prefix.Color.LIME)
public class JoinCommand extends CommandExecutor {

    @Override
    public void execute(Survivor survivor, String[] args, String name, Command command) {
        Lobby lobby = Container.get(LobbyService.class).find(DeadEnd.class);
        lobby.add(survivor);
        survivor.joinLobby(lobby);
    }
}
