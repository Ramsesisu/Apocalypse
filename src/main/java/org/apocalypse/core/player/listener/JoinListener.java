package org.apocalypse.core.player.listener;

import net.kyori.adventure.text.Component;
import org.apocalypse.api.command.Prefix;
import org.apocalypse.api.service.container.Container;
import org.apocalypse.core.player.PlayerService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Prefix prefix = new Prefix("Join", Prefix.Color.YELLOW, Prefix.Color.LIME);
        event.joinMessage(prefix.getPrefix().append(Component.text( "§e§l" + event.getPlayer().getName() + "§e joined!")));

        Container.get(PlayerService.class).register(event.getPlayer());
    }
}
