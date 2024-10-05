package org.apocalypse.core.map.listener;

import org.apocalypse.api.location.Location;
import org.apocalypse.api.map.area.storage.Storage;
import org.apocalypse.api.player.Survivor;
import org.apocalypse.api.service.container.Container;
import org.apocalypse.core.player.PlayerService;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class StorageListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onBuy(PlayerInteractAtEntityEvent event) {
        if (!(event.getRightClicked() instanceof ArmorStand entity)) return;

        Survivor survivor = Container.get(PlayerService.class).get(event.getPlayer());
        Storage storage = survivor.getLobby().getStorage(new Location(entity.getLocation()));
        if (storage == null) return;
        storage.buy(survivor);
    }
}
