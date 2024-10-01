package org.apocalypse.core.player.listener;

import net.kyori.adventure.text.Component;
import org.apocalypse.api.command.Prefix;
import org.apocalypse.api.lobby.Lobby;
import org.apocalypse.api.location.Location;
import org.apocalypse.api.player.Survivor;
import org.apocalypse.api.service.container.Container;
import org.apocalypse.api.utils.LocationUtils;
import org.apocalypse.core.lobby.LobbyService;
import org.apocalypse.core.player.PlayerService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.MainHand;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Prefix prefix = new Prefix("Join", Prefix.Color.YELLOW, Prefix.Color.LIME);

        Player player = event.getPlayer();
        if (player.getMainHand() == MainHand.LEFT)
            player.kick(Component.text("§cYou are not allowed to join having the main hand set to left."));
        event.joinMessage(prefix.getPrefix().append(Component.text( "§e§l" + player.getName() + "§e joined!")));

        PlayerService playerService = Container.get(PlayerService.class);
        Survivor survivor = playerService.register(player);
        survivor.getInventory().clear();
        survivor.teleport(LocationUtils.WORLD, new Location(LocationUtils.WORLD.getSpawnLocation()));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Survivor survivor = Container.get(PlayerService.class).get(player.getUniqueId());
        Lobby lobby = Container.get(LobbyService.class).find(survivor);
        if (lobby != null) if (lobby.getRound() == 0) lobby.remove(survivor);
    }
}
