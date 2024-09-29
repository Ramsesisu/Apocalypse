package org.apocalypse.core.weapon.handler;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class WeaponHandler implements Listener {

    @EventHandler
    public void onBullet(ProjectileHitEvent event) {
        event.getEntity().remove();
    }

    @EventHandler
    public void onHit(ProjectileHitEvent event) {
        if (event.getHitEntity() == null) return;
    }
}
