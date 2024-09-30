package org.apocalypse.core.weapon.listener;

import org.apocalypse.api.lobby.Lobby;
import org.apocalypse.api.monster.Monster;
import org.apocalypse.api.player.Survivor;
import org.apocalypse.api.service.container.Container;
import org.apocalypse.api.weapon.projectile.type.BulletType;
import org.apocalypse.core.player.PlayerService;
import org.apocalypse.core.weapon.projectile.BulletRecord;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

public class HitListener implements Listener {

    @EventHandler
    public void onBullet(ProjectileHitEvent event) {
        event.getEntity().remove();
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Projectile projectile)) return;
        BulletType bullet = Container.get(BulletRecord.class).get(projectile);
        event.setDamage(bullet.getDamage());
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
    }
}
