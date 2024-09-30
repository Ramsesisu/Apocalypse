package org.apocalypse.api.location.iterator;

import org.apocalypse.api.location.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BlockIterator implements Iterator<Block> {
    private final World world;
    private final int startX, startY, startZ, endX, endY, endZ;
    private int currentX, currentY, currentZ;

    public BlockIterator(World world, Location first, Location second) {
        this.world = world;
        this.startX = Math.min(first.getX(), second.getX());
        this.endX = Math.max(first.getX(), second.getX());
        this.startY = Math.max(first.getY(), second.getY());
        this.endY = Math.min(first.getY(), second.getY());
        this.startZ = Math.min(first.getZ(), second.getZ());
        this.endZ = Math.max(first.getZ(), second.getZ());
        this.currentX = startX;
        this.currentY = startY;
        this.currentZ = startZ;
    }

    @Override
    public boolean hasNext() {
        return currentX <= endX && currentY >= endY && currentZ <= endZ;
    }

    @Override
    public Block next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Block block = world.getBlockAt(currentX, currentY, currentZ);
        if (currentZ < endZ) {
            currentZ++;
        } else if (currentY > endY) {
            currentZ = startZ;
            currentY--;
        } else if (currentX < endX) {
            currentZ = startZ;
            currentY = startY;
            currentX++;
        } else {
            currentX++;
        }
        return block;
    }
}