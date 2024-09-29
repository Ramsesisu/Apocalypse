package org.apocalypse.api.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.logging.Level;

public class LocationUtils {

    public static final World WORLD = Bukkit.getWorlds().getFirst();

    public static Location get(World world, int x, int y, int z) {
        return toAverage(new Location(world, x, y, z, 0, 0));
    }

    public static Location get(int x, int y, int z) {
        return toAverage(new Location(WORLD, x, y, z, 0, 0));
    }

    public static Location get(World world, int x, int y, int z, int yaw) {
        return toAverage(new Location(world, x, y, z, yaw, 0));
    }

    public static Location get(int x, int y, int z, int yaw) {
        return toAverage(new Location(WORLD, x, y, z, yaw, 0));
    }

    public static Location get(World world, int x, int z) {
        return toAverage(new Location(world, x, 0, z, 0, 0));
    }

    public static Location get(int x, int z) {
        return toAverage(new Location(WORLD, x, 0, z, 0, 0));
    }

    public static Location get(World world, String x, String y, String z, String yaw) {
        return get(world, parse(x), parse(y), parse(z), parse(yaw));
    }

    public static Location get(String x, String y, String z, String yaw) {
        return get(parse(x), parse(y), parse(z), parse(yaw));
    }

    public static Location get(World world, String x, String y, String z) {
        return get(world, parse(x), parse(y), parse(z));
    }

    public static Location get(String x, String y, String z) {
        return get(parse(x), parse(y), parse(z));
    }

    public static int parse(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            Bukkit.getLogger().log(Level.WARNING, e, () -> "Couldn't parse " + string + " to an integer.");
        }
        return 0;
    }

    public static String parse(int i) {
        return String.valueOf(i);
    }

    public static String getShort(Location loc) {
        DecimalFormat df = new DecimalFormat("#.#", new DecimalFormatSymbols(Locale.ENGLISH));
        return df.format(loc.getX()) + "/" + df.format(loc.getY()) + "/" + df.format(loc.getZ());
    }

    public static String getFormatted(Location loc) {
        DecimalFormat df = new DecimalFormat("#.#", new DecimalFormatSymbols(Locale.ENGLISH));
        return "LocationUtils.get(world, " + df.format(loc.getX()) + ", " + df.format(loc.getY()) + ", " + df.format(loc.getZ()) + ", " + df.format(loc.getYaw()) + ")";
    }

    public static Location toAverage(Location loc) {
        int x = loc.getBlockX();
        int z = loc.getBlockZ();
        return new Location(loc.getWorld(), x + 0.5, loc.getBlockY(), z + 0.5);
    }

    public static boolean isInArea(Location loc, Location loc1, Location loc2) {
        return loc.getBlockX() >= Math.min(loc1.getBlockX(), loc2.getBlockX()) &&
                loc.getBlockX() <= Math.max(loc1.getBlockX(), loc2.getBlockX()) &&
                loc.getBlockZ() >= Math.min(loc1.getBlockZ(), loc2.getBlockZ()) &&
                loc.getBlockZ() <= Math.max(loc1.getBlockZ(), loc2.getBlockZ());
    }

    public static boolean isInCube(Location loc, Location loc1, Location loc2) {
        return loc.getBlockX() >= Math.min(loc1.getBlockX(), loc2.getBlockX()) &&
                loc.getBlockX() <= Math.max(loc1.getBlockX(), loc2.getBlockX()) &&
                loc.getBlockY() >= Math.min(loc1.getBlockY(), loc2.getBlockY()) &&
                loc.getBlockY() <= Math.max(loc1.getBlockY(), loc2.getBlockY()) &&
                loc.getBlockZ() >= Math.min(loc1.getBlockZ(), loc2.getBlockZ()) &&
                loc.getBlockZ() <= Math.max(loc1.getBlockZ(), loc2.getBlockZ());
    }

    public static Location getMiddle(Location loc1, Location loc2) {
        return new Location(loc1.getWorld(), (double) (loc1.getBlockX() + loc2.getBlockX()) / 2, (double) (loc1.getBlockY() + loc2.getBlockY()) / 2, (double) (loc1.getBlockZ() + loc2.getBlockZ()) / 2);
    }

    public static String toString(Location loc) {
        return loc.getBlockX() + "/" + loc.getBlockY() + "/" + loc.getBlockZ();
    }

    public static Location fromString(String string) {
        String[] split = string.split("/");
        try {
            return toAverage(new Location(WORLD, Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3])));
        } catch (NumberFormatException e) {
            Bukkit.getLogger().log(Level.WARNING, e, () -> "Couldn't parse " + string + " to an location.");
        }
        return null;
    }
}

