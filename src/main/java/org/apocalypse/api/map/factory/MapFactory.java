package org.apocalypse.api.map.factory;

import lombok.SneakyThrows;
import org.apocalypse.Apocalypse;
import org.apocalypse.api.config.MapConfig;
import org.apocalypse.api.config.loader.ConfigLoader;
import org.apocalypse.api.location.Location;
import org.apocalypse.api.map.Map;
import org.apocalypse.api.map.area.Area;
import org.apocalypse.api.map.area.door.Door;
import org.apocalypse.api.map.area.loot.Loot;
import org.apocalypse.api.map.area.spawn.Spawn;
import org.apocalypse.api.map.area.spawn.barrier.Barrier;
import org.apocalypse.api.map.area.storage.Storage;
import org.apocalypse.api.map.area.storage.type.ArmorStorage;
import org.apocalypse.api.map.area.storage.type.WeaponStorage;
import org.apocalypse.api.map.hologram.Hologram;
import org.apocalypse.api.map.wrapper.MapsWrapper;
import org.apocalypse.api.monster.type.MonsterType;
import org.apocalypse.api.weapon.type.WeaponType;
import org.bukkit.Material;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapFactory {

    public static MapsWrapper mapsWrapper;

    public static Map createMap(String name) {
        mapsWrapper = ConfigLoader.loadMapsConfig("maps.yml");
        MapConfig config = mapsWrapper.getMaps().stream()
                .filter(map -> map.getName().replace(" ", "").equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Map " + name + " not found"));
        return MapFactory.createMap(config);
    }

    public static Map createMap(MapConfig config) {
        Location spawn = new Location(config.getSpawn().getX(), config.getSpawn().getY(), config.getSpawn().getZ(), config.getSpawn().getYaw());
        List<Class<? extends MonsterType>> monsters = config.getMonsters().stream()
                .map(MapFactory::getMonsterClass)
                .collect(Collectors.toList());
        List<Area> areas = config.getAreas().stream()
                .map(MapFactory::createArea)
                .collect(Collectors.toList());
        List<Class<? extends WeaponType>> loot = config.getLoot().stream()
                .map(MapFactory::getWeaponClass)
                .collect(Collectors.toList());
        return new Map(config.getName(), spawn, monsters, areas, loot) {};
    }

    @SneakyThrows
    private static Class<? extends MonsterType> getMonsterClass(String name) {
        Set<Class<? extends MonsterType>> types = Apocalypse.findClasses(MonsterType.class);
        return types.stream()
                .filter(type -> type.getSimpleName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    @SneakyThrows
    private static Class<? extends WeaponType> getWeaponClass(String name) {
        Set<Class<? extends WeaponType>> types = Apocalypse.findClasses(WeaponType.class);
        return types.stream()
                .filter(type -> type.getSimpleName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    private static Area createArea(MapConfig.AreaConfig areaConfig) {
        Location location = new Location(areaConfig.getLocation().getX(), areaConfig.getLocation().getY(), areaConfig.getLocation().getZ());
        List<Spawn> spawns = areaConfig.getSpawns() != null ? areaConfig.getSpawns().stream()
                .map(MapFactory::createSpawn)
                .collect(Collectors.toList()) : Collections.emptyList();
        Loot loot = areaConfig.getLoot() != null ? createLoot(areaConfig.getLoot()) : null;
        List<Door> doors = areaConfig.getDoors() != null ? createDoors(areaConfig.getDoors()) : Collections.emptyList();
        List<ArmorStorage> armorStorages = areaConfig.getArmorStorages() != null ? createArmorStorages(areaConfig.getArmorStorages()) : Collections.emptyList();
        List<WeaponStorage> weaponStorages = areaConfig.getWeaponStorages() != null ? createWeaponStorages(areaConfig.getWeaponStorages()) : Collections.emptyList();
        List<Storage> storages = Stream.concat(armorStorages.stream(), weaponStorages.stream()).toList();
        return new Area(areaConfig.getName(), location, doors, storages, spawns, loot);
    }

    private static Spawn createSpawn(MapConfig.AreaConfig.SpawnConfig spawnConfig) {
        Location location = new Location(spawnConfig.getLocation().getX(), spawnConfig.getLocation().getY(), spawnConfig.getLocation().getZ());
        Barrier barrier = createBarrier(spawnConfig.getBarrier());
        return new Spawn(location, barrier);
    }

    private static Barrier createBarrier(MapConfig.AreaConfig.SpawnConfig.BarrierConfig barrierConfig) {
        if (barrierConfig == null) return null;
        Location start = new Location(barrierConfig.getStart().getX(), barrierConfig.getStart().getY(), barrierConfig.getStart().getZ());
        Location end = new Location(barrierConfig.getEnd().getX(), barrierConfig.getEnd().getY(), barrierConfig.getEnd().getZ());
        return new Barrier(start, end);
    }

    private static Loot createLoot(MapConfig.AreaConfig.LootConfig lootConfig) {
        if (lootConfig == null) return null;
        Location location = new Location(lootConfig.getLocation().getX(), lootConfig.getLocation().getY(), lootConfig.getLocation().getZ());
        Location hologram = new Location(lootConfig.getLocation().getX(), lootConfig.getLocation().getY(), lootConfig.getLocation().getZ());
        return new Loot(location, new Hologram(" ", hologram));
    }

    private static List<Door> createDoors(List<MapConfig.AreaConfig.DoorConfig> doorConfigs) {
        return doorConfigs != null ? doorConfigs.stream()
                .map(doorConfig -> new Door(
                        doorConfig.getName(),
                        new Location(doorConfig.getFirst().getX(), doorConfig.getFirst().getY(), doorConfig.getFirst().getZ()),
                        new Location(doorConfig.getSecond().getX(), doorConfig.getSecond().getY(), doorConfig.getSecond().getZ()),
                        new Location(doorConfig.getHologram().getX(), doorConfig.getHologram().getY(), doorConfig.getHologram().getZ()),
                        doorConfig.getCost()))
                .collect(Collectors.toList()) : Collections.emptyList();
    }

    private static List<ArmorStorage> createArmorStorages(List<MapConfig.AreaConfig.ArmorStorageConfig> armorStorageConfigs) {
        return armorStorageConfigs.stream()
                .map(armorStorageConfig -> new ArmorStorage(
                        armorStorageConfig.getName(),
                        new Location(armorStorageConfig.getLocation().getX(), armorStorageConfig.getLocation().getY() + 0.25F, armorStorageConfig.getLocation().getZ(), armorStorageConfig.getLocation().getYaw()),
                        armorStorageConfig.getArmor().stream().map(Material::valueOf).collect(Collectors.toList()),
                        armorStorageConfig.getCost()))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    private static List<WeaponStorage> createWeaponStorages(List<MapConfig.AreaConfig.WeaponStorageConfig> weaponStorageConfigs) {
        return weaponStorageConfigs.stream()
                .map(weaponStorageConfig -> {
                    try {
                        return new WeaponStorage(
                                weaponStorageConfig.getName(),
                                new Location(weaponStorageConfig.getLocation().getX(), weaponStorageConfig.getLocation().getY(), weaponStorageConfig.getLocation().getZ(), weaponStorageConfig.getLocation().getYaw()),
                                Apocalypse.findClasses(WeaponType.class).stream()
                                        .filter(type -> type.getSimpleName().equalsIgnoreCase(weaponStorageConfig.getWeapon()))
                                        .findFirst()
                                        .orElseThrow(() -> new IllegalArgumentException("Weapon type not found")),
                                weaponStorageConfig.getCost());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }).collect(Collectors.toList());
    }
}