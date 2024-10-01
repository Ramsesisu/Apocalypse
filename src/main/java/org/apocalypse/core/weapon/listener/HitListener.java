package org.apocalypse.core.weapon.listener;

import org.apocalypse.api.lobby.Lobby;
import org.apocalypse.api.monster.Monster;
import org.apocalypse.api.player.Survivor;
import org.apocalypse.api.service.container.Container;
import org.apocalypse.api.weapon.Weapon;
import org.apocalypse.core.player.PlayerService;
import org.apocalypse.core.weapon.WeaponService;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

public class HitListener implements Listener {

    @EventHandler
    public void onBullet(ProjectileHitEvent event) {
        event.getEntity().remove();
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Projectile projectile) {
            Weapon weapon = Container.get(WeaponService.class).get(projectile);
            event.setDamage(weapon.getType().getDamage());
            Survivor survivor = weapon.getPlayer();
            if (survivor == null) return;
            survivor.addMoney((int) Math.round(weapon.getType().getDamage() * 2));
        } else if (event.getDamager() instanceof Player player) {
            ItemStack item = player.getInventory().getItemInMainHand();
            Weapon weapon = Container.get(WeaponService.class).get(item);
            if (weapon.getType().getType() == Weapon.Type.MELEE) {
                if (weapon.isCooldown()) {
                    event.setDamage(0);
                    event.setCancelled(true);
                } else {
                    event.setDamage(weapon.getType().getDamage());
                    weapon.setCooldown(System.currentTimeMillis() + Math.round(weapon.getType().getSpeed() * 1000));
                    Survivor survivor = Container.get(PlayerService.class).get(player);
                    survivor.addMoney((int) Math.round(weapon.getType().getDamage() * 2));
                }
            } else event.setDamage(1);
        }
    }

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        event.setDroppedExp(0);
        event.getDrops().clear();
        if (!(event.getEntity() instanceof Creature entity)) return;
        if (entity.getKiller() == null) return;
        Survivor killer = Container.get(PlayerService.class).get(entity.getKiller());
        killer.addKill();
        Lobby lobby = killer.getLobby();
        for (Monster monster : lobby.getMonster()) {
            if (monster.getEntity().equals(entity)) {
                lobby.getMonster().remove(monster);
                break;
            }
        }
        lobby.getSurvivors().forEach(Survivor::updateScoreboard);
        if (lobby.getMonster().isEmpty()) lobby.nextWave();
    }

    @EventHandler
    public void onCombust(EntityCombustEvent event) {
        if (!(event.getEntity() instanceof Player))
            event.setCancelled(true);
    }
}
