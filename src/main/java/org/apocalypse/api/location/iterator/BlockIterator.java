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
    private final boolean reverse;

    public BlockIterator(World world, Location first, Location second) {
        this(world, first, second, false);
    }

    public BlockIterator(World world, Location first, Location second, boolean reverse) {
        this.world = world;
        this.startX = Math.min(first.getX(), second.getX());
        this.endX = Math.max(first.getX(), second.getX());
        this.startY = Math.max(first.getY(), second.getY());
        this.endY = Math.min(first.getY(), second.getY());
        this.startZ = Math.min(first.getZ(), second.getZ());
        this.endZ = Math.max(first.getZ(), second.getZ());
        this.reverse = reverse;
        if (reverse) {
            this.currentX = endX;
            this.currentY = endY;
            this.currentZ = endZ;
        } else {
            this.currentX = startX;
            this.currentY = startY;
            this.currentZ = startZ;
        }
    }

    @Override
    public boolean hasNext() {
        if (reverse) {
            return currentX >= startX && currentY <= startY && currentZ >= startZ;
        } else {
            return currentX <= endX && currentY >= endY && currentZ <= endZ;
        }
    }

    @Override
    public Block next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Block block = world.getBlockAt(currentX, currentY, currentZ);
        if (reverse) {
            if (currentZ > startZ) {
                currentZ--;
            } else if (currentY < startY) {
                currentZ = endZ;
                currentY++;
            } else if (currentX > startX) {
                currentZ = endZ;
                currentY = endY;
                currentX--;
            } else {
                currentX--;
            }
        } else {
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
        }
        return block;
    }
}