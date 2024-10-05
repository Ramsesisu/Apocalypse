package org.apocalypse.core.map.listener;

import lombok.SneakyThrows;
import org.apocalypse.api.map.area.loot.Loot;
import org.apocalypse.api.player.Survivor;
import org.apocalypse.api.service.container.Container;
import org.apocalypse.core.player.PlayerService;
import org.apocalypse.core.weapon.WeaponService;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class LootListener implements Listener {

    @SneakyThrows
    @EventHandler
    public void onLoot(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        Block block = event.getClickedBlock();
        if (block == null) return;
        if (block.getType() != Material.CHEST) return;
        event.setCancelled(true);

        Player player = event.getPlayer();
        Survivor survivor = Container.get(PlayerService.class).get(player);
        Loot loot = survivor.getLobby().getLoot(survivor.getLocation());
        if (loot == null) return;
        if (!loot.isActive()) return;
        if (loot.isSpinning()) return;

        if (loot.getCurrent() == null)
            loot.open(survivor);
        else {
            if (Container.get(WeaponService.class).give(loot.getCurrent(), survivor)) {
                loot.setActive(true);
                loot.getHologram().getEntity().getEquipment().setHelmet(new ItemStack(Material.AIR));
                loot.setCurrent(null);
            }
        }
    }
}
