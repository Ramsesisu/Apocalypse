package org.apocalypse.core.player.listener;

import net.kyori.adventure.text.Component;
import org.apocalypse.api.command.Prefix;
import org.apocalypse.api.lobby.Lobby;
import org.apocalypse.api.location.Location;
import org.apocalypse.api.player.Survivor;
import org.apocalypse.api.service.container.Container;
import org.apocalypse.core.lobby.LobbyService;
import org.apocalypse.core.player.PlayerService;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Prefix prefix = new Prefix("Join", Prefix.Color.YELLOW, Prefix.Color.LIME);

        Player player = event.getPlayer();
        event.joinMessage(prefix.getPrefix().append(Component.text( "§e§l" + player.getName() + "§e joined!")));

        PlayerService playerService = Container.get(PlayerService.class);
        Survivor survivor = playerService.register(player);
        survivor.getInventory().clear();
        World world = Bukkit.getServer().getWorld("lobby");
        if (world != null) survivor.teleport(world, new Location(world.getSpawnLocation()));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Survivor survivor = Container.get(PlayerService.class).get(player.getUniqueId());
        Lobby lobby = Container.get(LobbyService.class).find(survivor);
        if (lobby != null) if (lobby.getRound() == 0) lobby.remove(survivor);
    }
}
