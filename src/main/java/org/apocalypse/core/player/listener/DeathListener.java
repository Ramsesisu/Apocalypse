package org.apocalypse.core.player.listener;

import org.apocalypse.Apocalypse;
import org.apocalypse.api.player.Survivor;
import org.apocalypse.api.player.corpse.Corpse;
import org.apocalypse.api.service.container.Container;
import org.apocalypse.core.player.PlayerService;
import org.bukkit.GameMode;
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
        survivor.getLobby().getSurvivors().forEach(s -> s.sendTitle("§4" + survivor.getName() + " §7 died in §c" + survivor.getLobby().getArea(survivor.getLocation()).getName() + "§7!"));
    }

    @EventHandler
    public void onRevive(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.SPECTATOR) return;
        Survivor survivor = Container.get(PlayerService.class).get(player);
        if (survivor.isReviving() || !survivor.isInLobby()) return;

        player.getWorld().getEntities().stream()
                .filter(entity -> entity instanceof Item)
                .map(entity -> (Item) entity)
                .forEach(item -> survivor.getLobby().getSurvivors().stream()
                        .filter(Survivor::isDead)
                        .filter(dead -> dead.getCorpse().getItem().equals(item))
                        .findFirst()
                        .ifPresent(dead -> startReviveProcess(player, survivor, dead)));
    }

    private void startReviveProcess(Player player, Survivor survivor, Survivor dead) {
        survivor.setReviving(true);
        new BukkitRunnable() {
            private int i = 0;

            @Override
            public void run() {
                if (i++ > 12) {
                    completeRevival(dead);
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

    private void completeRevival(Survivor dead) {
        dead.teleport(dead.getCorpse().getLocation());
        dead.getCorpse().cancel();
        dead.getCorpse().getItem().remove();
        dead.setCorpse(null);
        dead.online().setGameMode(GameMode.SURVIVAL);
    }
}
