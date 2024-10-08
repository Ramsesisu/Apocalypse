package org.apocalypse.api.map.area.spawn.barrier;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apocalypse.api.location.Location;
import org.apocalypse.api.location.iterator.BlockIterator;
import org.apocalypse.api.utils.LocationUtils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;

@Getter
@Setter
@RequiredArgsConstructor
public class Barrier {

    private final Location first;
    private final Location second;
    private boolean repairing = false;

    public boolean repair(World world) {
        BlockIterator iterator = new BlockIterator(world, first, second);
        while (iterator.hasNext()) {
            Block block = iterator.next();
            if (!block.getType().isSolid()) {
                block.setType(Material.OAK_SLAB);
                block.getLocation().getWorld().playSound(block.getLocation(), Sound.BLOCK_WOODEN_TRAPDOOR_CLOSE, 0.4F, 1.0F);
                return true;
            }
        }
        return false;
    }

    public void destroy(World world) {
        BlockIterator iterator = new BlockIterator(world, first, second, true);
        while (iterator.hasNext()) {
            Block block = iterator.next();
            if (block.getType().isSolid()) {
                block.setType(Material.AIR);
                block.getLocation().getWorld().playSound(block.getLocation(), Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 0.1F, 1.0F);
                return;
            }
        }
    }

    public boolean isSafe(World world) {
        for (LivingEntity entity : world.getNearbyLivingEntities(this.getCenter(world).get(), 2)) {
            if (entity instanceof Monster) return false;
        } return true;
    }

    public Location getCenter(World world) {
        return new Location(LocationUtils.getMiddle(first.get(world), second.get(world)));
    }
}