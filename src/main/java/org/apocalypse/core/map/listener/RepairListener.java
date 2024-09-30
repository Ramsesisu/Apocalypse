package org.apocalypse.core.map.listener;

import org.apocalypse.Apocalypse;
import org.apocalypse.api.lobby.Lobby;
import org.apocalypse.api.map.area.spawn.barrier.Barrier;
import org.apocalypse.api.player.Survivor;
import org.apocalypse.api.service.container.Container;
import org.apocalypse.core.lobby.LobbyService;
import org.apocalypse.core.player.PlayerService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class RepairListener implements Listener {

    @EventHandler
    public void onRepair(PlayerToggleSneakEvent event) {
        final Survivor survivor = Container.get(PlayerService.class).get(event.getPlayer());
        final Lobby lobby = Container.get(LobbyService.class).find(survivor);
        if (lobby == null) return;
        final Barrier barrier = lobby.getBarrier(survivor.getLocation());
        if (barrier == null) return;
        if (barrier.isRepairing()) return;
        barrier.setRepairing(true);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!event.getPlayer().isSneaking()
                        || survivor.getLocation().distance(barrier.getCenter(survivor.getWorld())) > 5) {
                    barrier.setRepairing(false);
                    this.cancel();
                } else barrier.repair(lobby.getWorld());
            }
        }.runTaskTimer(Apocalypse.getInstance(), 20L, 20L);
    }
}
