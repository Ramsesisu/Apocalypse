package org.apocalypse.api.map.area.spawn.barrier;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apocalypse.api.location.Location;
import org.apocalypse.api.location.iterator.BlockIterator;
import org.apocalypse.api.utils.LocationUtils;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

@Getter
@Setter
@RequiredArgsConstructor
public class Barrier {

    private final Location first;
    private final Location second;
    private boolean repairing = false;

    public void repair(World world) {
        BlockIterator iterator = new BlockIterator(world, first, second);
        while (iterator.hasNext()) {
            Block block = iterator.next();
            if (!block.getType().isSolid()) {
                block.setType(Material.OAK_SLAB);
                return;
            }
        }
    }

    public Location getCenter(World world) {
        return new Location(LocationUtils.getMiddle(first.get(world), second.get(world)));
    }
}