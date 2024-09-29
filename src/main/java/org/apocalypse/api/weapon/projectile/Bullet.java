package org.apocalypse.api.weapon.projectile;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apocalypse.api.weapon.projectile.types.BulletType;
import org.bukkit.entity.Projectile;

@Getter
@RequiredArgsConstructor
public class Bullet {

    private final Projectile entity;
    private final BulletType type;
    private final int damage;
    private final float range;
}
