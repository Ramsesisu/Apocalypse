package org.apocalypse.core.map.listener;

import org.apocalypse.api.location.Location;
import org.apocalypse.api.map.area.door.Door;
import org.apocalypse.api.player.Survivor;
import org.apocalypse.api.service.container.Container;
import org.apocalypse.core.player.PlayerService;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class DoorListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onBuy(PlayerInteractAtEntityEvent event) {
        if (!(event.getRightClicked() instanceof ArmorStand entity)) return;

        Survivor survivor = Container.get(PlayerService.class).get(event.getPlayer());
        Door door = survivor.getLobby().getDoor(new Location(entity.getLocation()));
        if (door == null) return;
        if (door.isOpen()) return;

        if (survivor.removeMoney(door.getCost())) {
            door.open(survivor.getWorld());
            survivor.getLobby().getSurvivors().forEach(s -> s.sendTitle("§6" + survivor.getName() + " §7 opened §e" + door.getName() + "§7!"));
        }
    }
}
