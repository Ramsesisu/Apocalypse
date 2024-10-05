package org.apocalypse.core.weapon.listener;

import net.kyori.adventure.text.Component;
import org.apocalypse.Apocalypse;
import org.apocalypse.api.builder.ItemBuilder;
import org.apocalypse.api.player.Survivor;
import org.apocalypse.api.service.container.Container;
import org.apocalypse.api.weapon.Weapon;
import org.apocalypse.api.weapon.projectile.Bullet;
import org.apocalypse.core.player.PlayerService;
import org.apocalypse.core.weapon.WeaponRecord;
import org.apocalypse.core.weapon.WeaponService;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.scheduler.BukkitRunnable;

public class WeaponListener implements Listener {

    @EventHandler
    public void onShoot(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        ItemStack item = event.getItem();
        if (item == null) return;
        if (!Container.get(WeaponRecord.class).isGun(item)) return;

        Player player = event.getPlayer();
        Survivor survivor = Container.get(PlayerService.class).get(player);
        if (survivor.isDead()) return;
        Weapon weapon = Container.get(WeaponService.class).get(item);
        if (weapon == null) return;
        event.setCancelled(true);
        if (weapon.isCooldown()) return;
        if (!weapon.removeAmmo()) return;

        Bullet bullet = new Bullet(weapon.getType().getBullet());
        bullet.shoot(player).forEach(projectile -> projectile.setCustomName(weapon.getKey().toString()));
        weapon.setPlayer(Container.get(PlayerService.class).get(player));
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 0.2F, 1.2F);
        weapon.setCooldown(System.currentTimeMillis() + Math.round(weapon.getType().getSpeed() * 1000));
        weapon.updateLore();
        player.sendActionBar(Component.text(ItemBuilder.get(weapon.getItem()).getLore()));
        item.setItemMeta(weapon.getItem().getItemMeta());
    }

    @EventHandler
    public void onReload(PlayerDropItemEvent event) {
        ItemStack item = event.getItemDrop().getItemStack();
        if (!Container.get(WeaponRecord.class).isGun(item)) return;
        Weapon weapon = Container.get(WeaponService.class).get(item);
        if (weapon == null) return;
        if (weapon.isCooldown()) return;
        if (weapon.isFull()) return;

        Player player = event.getPlayer();
        Survivor survivor = Container.get(PlayerService.class).get(player);
        if (survivor.isDead()) return;
        int slot = player.getInventory().getHeldItemSlot();
        if (item.getItemMeta() instanceof Damageable damageable) {
            int step = item.getType().getMaxDurability() / 10;
            damageable.setDamage(item.getType().getMaxDurability());
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (damageable.getDamage() <= 0) {
                        item.setItemMeta(weapon.getItem().getItemMeta());
                        weapon.setReloading(false);
                        this.cancel();
                        return;
                    }
                    weapon.setReloading(true);
                    int damage = damageable.getDamage() - step;
                    if (damage < 0) damage = 0;
                    damageable.setDamage(damage);
                    item.setItemMeta(damageable);
                    if (player.getInventory().getItem(slot) != null)
                        if (player.getInventory().getItem(slot).getType() != Material.AIR)
                            player.getInventory().setItem(slot, item);
                }
            }.runTaskTimer(Apocalypse.getInstance(), 0L, Math.round(weapon.getType().getSpeed()) * 6L);
        }

        weapon.fillAmmo();
        player.getWorld().playSound(player.getLocation(), Sound.ITEM_FLINTANDSTEEL_USE, 0.2F, 1.2F);
        weapon.setCooldown(System.currentTimeMillis() + Math.round(weapon.getType().getSpeed() * 3000));
        player.sendActionBar(Component.text(ItemBuilder.get(weapon.getItem()).getLore()));
        weapon.updateLore();
        item.setItemMeta(weapon.getItem().getItemMeta());
    }
}
