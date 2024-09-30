package org.apocalypse.core.debug;

import org.apocalypse.api.command.Command;
import org.apocalypse.api.command.CommandExecutor;
import org.apocalypse.api.command.Prefix;
import org.apocalypse.api.lobby.Lobby;
import org.apocalypse.api.player.Survivor;
import org.apocalypse.api.service.container.Container;
import org.apocalypse.core.lobby.LobbyService;

@Command(name = "next", usage = "/next", op = true, description = "Moves to the next wave.", prefix = "Debug", prefixColor = Prefix.Color.LIME, prefixGradient = Prefix.Color.PURPLE)
public class NextCommand extends CommandExecutor {

    @Override
    public void execute(Survivor survivor, String[] args, String name, Command command) {
        if (!checkPermissions(survivor, command)) return;

        Lobby lobby = Container.get(LobbyService.class).find(survivor);
        if (lobby == null) {
            survivor.sendMessage(prefix, "§cYou are not in a lobby.");
            return;
        }

        lobby.nextWave();
        survivor.sendMessage(prefix, "§cMoving to the next wave.");
    }
}
