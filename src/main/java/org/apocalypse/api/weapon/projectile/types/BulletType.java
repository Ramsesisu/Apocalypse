package org.apocalypse.api.weapon.projectile.types;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Projectile;

@Getter
@RequiredArgsConstructor
public class BulletType {

    private final String name;
    private final Class<? extends Projectile> projectile;
    private final double damage;
    private final float speed;
    private final float range;
    private final boolean explosive;
}
