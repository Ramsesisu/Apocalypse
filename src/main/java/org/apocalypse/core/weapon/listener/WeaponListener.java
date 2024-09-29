package org.apocalypse.core.weapon.listener;

import net.kyori.adventure.text.Component;
import org.apocalypse.api.builder.ItemBuilder;
import org.apocalypse.api.service.container.Container;
import org.apocalypse.api.weapon.Weapon;
import org.apocalypse.api.weapon.projectile.Bullet;
import org.apocalypse.core.weapon.WeaponRecord;
import org.apocalypse.core.weapon.WeaponService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class WeaponListener implements Listener {

    @EventHandler
    public void onShoot(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        ItemStack item = event.getItem();
        if (item == null) return;
        if (!Container.get(WeaponRecord.class).isGun(item)) return;

        Weapon weapon = Container.get(WeaponService.class).get(item);
        if (weapon == null) return;
        event.setCancelled(true);
        if (weapon.isCooldown()) return;
        if (!weapon.removeAmmo()) return;

        Player player = event.getPlayer();
        Bullet bullet = new Bullet(weapon.getType().getBullet());
        bullet.shoot(player, weapon.getType().getBullet());
        weapon.setCooldown(System.currentTimeMillis());
        weapon.updateLore();
        player.sendActionBar(Component.text(ItemBuilder.get(weapon.getItem()).getLore()));
    }

    @EventHandler
    public void onReload(PlayerDropItemEvent event) {
        event.setCancelled(true);

        ItemStack item = event.getItemDrop().getItemStack();
        if (!Container.get(WeaponRecord.class).isGun(item)) return;

        Weapon weapon = Container.get(WeaponService.class).get(item);
        weapon.fillAmmo();
        weapon.updateLore();
    }
}
