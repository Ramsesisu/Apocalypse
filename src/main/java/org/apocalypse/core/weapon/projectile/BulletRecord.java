package org.apocalypse.core.weapon.projectile;

import org.apocalypse.api.service.Record;
import org.apocalypse.api.weapon.projectile.type.BulletType;
import org.bukkit.entity.Projectile;

import java.util.Objects;

public class BulletRecord extends Record<BulletType> {

    public BulletType get(Projectile projectile) {
        if (!Objects.requireNonNull(projectile.getCustomName()).isEmpty()) {
            for (BulletType bullet : this.list.values()) {
                if (bullet.getName().equalsIgnoreCase(projectile.getCustomName())) {
                    return bullet;
                }
            }
        } return null;
    }
}
