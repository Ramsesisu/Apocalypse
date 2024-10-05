package org.apocalypse.api.location;

import org.apocalypse.api.utils.LocationUtils;
import org.bukkit.World;

public class Location {

    private final org.bukkit.Location location;

    public Location(org.bukkit.Location location) {
        this.location = location;
    }

    public Location(World world, org.bukkit.Location location) {
        this.location = location;
        this.location.setWorld(world);
    }

    public Location(int x, int z) {
        this(x, 0, z, 0, 0);
    }

    public Location(int x, int y, int z) {
        this(x, y, z, 0, 0);
    }

    public Location(int x, float y, int z) {
        this(x, y, z, 0, 0);
    }

    public Location(int x, int y, int z, int yaw) {
        this(x, y, z, yaw, 0);
    }

    public Location(int x, float y, int z, int yaw) {
        this(x, y, z, yaw, 0);
    }

    public Location(int x, int y, int z, int yaw, int pitch) {
        this.location = new org.bukkit.Location(LocationUtils.WORLD, x, y, z, yaw, pitch);
    }

    public Location(int x, float y, int z, int yaw, int pitch) {
        this.location = new org.bukkit.Location(LocationUtils.WORLD, x, y, z, yaw, pitch);
    }

    public Location(String x, String y, String z) {
        this.location = new org.bukkit.Location(LocationUtils.WORLD, parse(x), parse(y), parse(z));
    }

    public Location(String x, String y, String z, String yaw) {
        this.location = new org.bukkit.Location(LocationUtils.WORLD, parse(x), parse(y), parse(z), parse(yaw), 0);
    }

    public void add(float x, float y, float z) {
        this.location.setX(this.location.getX() + x);
        this.location.setY(this.location.getY() + y);
        this.location.setZ(this.location.getZ() + z);
    }

    public org.bukkit.Location get() {
        return LocationUtils.toAverage(this.location);
    }

    public org.bukkit.Location get(World world) {
        org.bukkit.Location loc = this.location.clone();
        loc.setWorld(world);
        return LocationUtils.toAverage(loc);
    }

    public void setWorld(World world) {
        this.location.setWorld(world);
    }

    public World getWorld() {
        return this.location.getWorld();
    }

    private int parse(String string) {
        return Integer.parseInt(string);
    }

    public int getX() {
        return this.location.getBlockX();
    }

    public int getY() {
        return this.location.getBlockY();
    }

    public int getZ() {
        return this.location.getBlockZ();
    }

    public int getYaw() {
        return (int) this.location.getYaw();
    }

    public double distance(Location location) {
        return this.get(location.getWorld()).distance(location.get());
    }
}
