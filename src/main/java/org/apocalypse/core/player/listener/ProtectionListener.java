package org.apocalypse.core.player.listener;


import org.apocalypse.api.player.Survivor;
import org.apocalypse.api.service.container.Container;
import org.apocalypse.core.player.PlayerService;
import org.apocalypse.core.weapon.WeaponRecord;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.meta.Damageable;

public class ProtectionListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (event.getPlayer().getGameMode() != GameMode.CREATIVE)
            event.setCancelled(true);
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPickup(PlayerAttemptPickupItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onDamage(PlayerItemDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSwap(PlayerSwapHandItemsEvent event) {
        if (!Container.get(WeaponRecord.class).isGun(event.getMainHandItem()) && !Container.get(WeaponRecord.class).isGun(event.getOffHandItem()))
            event.setCancelled(true);
        if (event.getMainHandItem().getItemMeta() instanceof Damageable damageable)
            if (damageable.getDamage() > 0) event.setCancelled(true);
        if (event.getOffHandItem().getItemMeta() instanceof Damageable damageable)
            if (damageable.getDamage() > 0) event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onItem(InventoryClickEvent event) {
        if (event.getInventory().getHolder() instanceof Player player)
                if (player.getGameMode() == GameMode.CREATIVE) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHeld(PlayerItemHeldEvent event) {
        if (Container.get(WeaponRecord.class).isGun(event.getPlayer().getInventory().getItemInOffHand()))
            event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDrop(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        Survivor survivor = Container.get(PlayerService.class).get(player);
        if (!survivor.isInLobby()) event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onArmor(PlayerArmorStandManipulateEvent event) {
        event.setCancelled(true);
    }
}
