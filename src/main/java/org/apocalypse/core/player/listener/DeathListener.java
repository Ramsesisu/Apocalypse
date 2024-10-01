package org.apocalypse.core.player.listener;

import org.apocalypse.Apocalypse;
import org.apocalypse.api.player.Survivor;
import org.apocalypse.api.player.corpse.Corpse;
import org.apocalypse.api.service.container.Container;
import org.apocalypse.core.player.PlayerService;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class DeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        event.setCancelled(true);

        Player player = event.getEntity();
        Survivor survivor = Container.get(PlayerService.class).get(player);
        if (!survivor.isInLobby()) return;
        Corpse corpse = new Corpse(survivor);
        survivor.setCorpse(corpse);
        survivor.online().setGameMode(GameMode.SPECTATOR);
    }

    @EventHandler
    public void onRevive(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        Survivor survivor = Container.get(PlayerService.class).get(player);
        if (survivor.isReviving()) return;
        if (!survivor.isInLobby()) return;
        for (Entity entity : player.getWorld().getEntities()) {
            if (entity instanceof Item item) {
                for (Survivor dead : survivor.getLobby().getSurvivors()) {
                    if (dead.isDead() && dead.getCorpse().getItem().equals(item)) {
                        survivor.setReviving(true);
                        final int[] i = {0};
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (i[0]++ > 12) {
                                    dead.teleport(dead.getCorpse().getLocation());
                                    dead.setCorpse(null);
                                    dead.getCorpse().cancel();
                                    dead.getCorpse().getItem().remove();
                                    dead.online().setGameMode(GameMode.SURVIVAL);
                                    survivor.setReviving(false);
                                    this.cancel();
                                }
                                if (!player.isSneaking()) {
                                    survivor.setReviving(false);
                                    this.cancel();
                                }
                            }
                        }.runTaskTimer(Apocalypse.getInstance(), 0L, 5L);
                    }
                }
            }
        }
    }
}
