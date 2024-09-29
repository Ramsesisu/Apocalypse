package org.apocalypse.core.weapon.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class HitListener implements Listener {

    @EventHandler
    public void onBullet(ProjectileHitEvent event) {
        event.getEntity().remove();
    }

    @EventHandler
    public void onHit(ProjectileHitEvent event) {
        if (event.getHitEntity() == null) return;
    }
}
