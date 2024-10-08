package org.apocalypse.api.lobby;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apocalypse.Apocalypse;
import org.apocalypse.api.config.MapConfig;
import org.apocalypse.api.lobby.wave.Wave;
import org.apocalypse.api.location.Location;
import org.apocalypse.api.map.Map;
import org.apocalypse.api.map.area.Area;
import org.apocalypse.api.map.area.door.Door;
import org.apocalypse.api.map.area.loot.Loot;
import org.apocalypse.api.map.area.spawn.Spawn;
import org.apocalypse.api.map.area.spawn.barrier.Barrier;
import org.apocalypse.api.map.area.storage.Storage;
import org.apocalypse.api.map.factory.MapFactory;
import org.apocalypse.api.monster.Monster;
import org.apocalypse.api.monster.type.MonsterType;
import org.apocalypse.api.player.Survivor;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@Getter
@Setter
public class Lobby {

    private final List<Survivor> survivors;
    private final List<Monster> monster = new ArrayList<>();
    private final List<? extends MonsterType> types;
    private final Map map;
    private final World world;
    private final long created;
    private long started = 0;
    private Loot loot = null;
    private Wave wave = null;
    private int round = 0;

    @SneakyThrows
    public Lobby(MapConfig map) {
        this.survivors = new ArrayList<>();
        this.created = System.currentTimeMillis();
        this.world = this.createNewLobby(map);
        if (this.world == null)
            throw new IllegalStateException("Failed to create new " + map.getName() + " lobby.");
        this.map = MapFactory.createMap(map);
        this.world.setAutoSave(false);
        this.world.setPVP(false);
        this.world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        this.world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        this.world.setGameRule(GameRule.DO_MOB_LOOT, false);
        this.world.setGameRule(GameRule.MOB_GRIEFING, false);
        this.world.getEntities().forEach(Entity::remove);
        this.types = this.map.getMonster().stream().map(monster -> {
            try {
                return monster.getConstructor().newInstance();
            } catch (Exception e) {
                return null;
            }
        }).toList();
        this.resetLoot();
        this.map.getLootChests().forEach(loot -> loot.getHologram().spawn(this.world));
        this.map.getAreas().forEach(area -> area.getDoors().forEach(door -> door.getHologram().spawn(this.world)));
        this.map.getAreas().forEach(area -> area.getStorages().forEach(storage -> storage.spawn(this.world)));
        Bukkit.getScheduler().runTaskTimer(Apocalypse.getInstance(),
                () -> this.survivors.forEach(Survivor::updateScoreboard), 0L, 20L);
    }

    public World createNewLobby(MapConfig map) {
        String name = map.getName().replace(" ", "").toLowerCase();
        File srcDir = new File("worlds/" + name);
        String file = "worlds/" + name + this.hashCode();
        File destDir = new File(file);

        try {
            copyWorld(srcDir, destDir);
            File uidFile = new File(destDir, "uid.dat");
            if (uidFile.exists()) uidFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        WorldCreator wc = new WorldCreator(file);
        wc.environment(World.Environment.NORMAL);
        wc.type(WorldType.NORMAL);
        World world = wc.createWorld();

        if (world == null)
            System.err.println("Failed to create world: " + file);

        return world;
    }

    public void removeLobby() {
        String name = this.map.getName().replace(" ", "").toLowerCase();
        String file = "worlds/" + name + this.hashCode();
        World world = Bukkit.getWorld(file);
        if (world != null)
            Bukkit.unloadWorld(world, false);

        File lobbyFolder = new File(file);
        deleteWorld(lobbyFolder);
    }

    private void copyWorld(File source, File target) throws Exception {
        if (!source.isDirectory()) return;

        if (!target.exists()) {
            target.mkdirs();
        }

        for (String file : source.list()) {
            File srcFile = new File(source, file);
            File destFile = new File(target, file);

            if (srcFile.isDirectory()) {
                copyWorld(srcFile, destFile);
            } else {
                if (srcFile.exists()) {
                    Files.copy(srcFile.toPath(), destFile.toPath());
                } else {
                    System.err.println("File not found: " + srcFile.getPath());
                }
            }
        }
    }

    private void deleteWorld(File folder) {
        if (folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                this.deleteWorld(file);
            }
        }
        folder.delete();
    }

    public void add(Survivor survivor) {
        this.survivors.add(survivor);
        this.survivors.forEach(Survivor::updateScoreboard);
    }

    public void remove(Survivor survivor) {
        this.survivors.remove(survivor);
        this.survivors.forEach(Survivor::updateScoreboard);
    }

    public int size() {
        return this.survivors.size();
    }

    public List<Survivor> getSurvivors() {
        return this.survivors.stream().filter(survivor -> survivor.getLobby() == this).collect(Collectors.toList());
    }

    public boolean isStarted() {
        return this.wave != null;
    }

    public void nextWave() {
        this.round++;
        List<MonsterType> monster = this.types.stream()
                .filter(type -> type.getFirst() <= this.round && type.getLast() >= this.round)
                .collect(Collectors.toList());

        this.wave = new Wave(this, monster, round * (survivors.size() > 1 ? survivors.size() > 2 ? 5 : 4 : 3));
        this.wave.start();

        this.survivors.forEach(survivor -> {
            if (survivor.isDead()) {
                survivor.getCorpse().cancel();
                survivor.setCorpse(null);
                survivor.teleport(this.map.getSpawn());
                survivor.online().setGameMode(GameMode.ADVENTURE);
            }
            if (survivor.online().getGameMode() == GameMode.SPECTATOR) {
                survivor.teleport(this.map.getSpawn());
                survivor.online().setGameMode(GameMode.ADVENTURE);
            }
        });
        this.survivors.forEach(survivor -> survivor.online().setHealth(Objects.requireNonNull(survivor.online().getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue()));
        this.survivors.forEach(Survivor::updateScoreboard);
        this.survivors.forEach(survivor -> survivor.sendTitle("§cWave §l" + this.round, "§7Prepare yourself!"));
    }

    public void win() {

    }

    public void lose() {

    }

    public Area getArea(Location location) {
        Area closest = null;
        double distance = Double.MAX_VALUE;
        for (Area area : this.map.getAreas()) {
            double current = area.getLocation().distance(location);
            if (current < distance) {
                closest = area;
                distance = current;
            }
        } return closest;
    }

    public Door getDoor(Location location) {
        for (Area area : this.map.getAreas()) {
            for (Door door : area.getDoors()) {
                if (door == null) continue;
                if (door.getHologram().getLocation().distance(location) < 4)
                    return door;
            }
        } return null;
    }

    public Storage getStorage(Location location) {
        for (Area area : this.map.getAreas()) {
            for (Storage storage : area.getStorages()) {
                if (storage == null) continue;
                if (storage.getLocation().get(location.getWorld()).distance(location.get()) < 3)
                    return storage;
            }
        } return null;
    }

    public Barrier getBarrier(Location location) {
        for (Area area : this.map.getAreas()) {
            for (Spawn spawn : area.getSpawns()) {
                if (spawn.getBarrier() == null) continue;
                if (spawn.getBarrier().getCenter(location.getWorld()).distance(location) < 4)
                    return spawn.getBarrier();
            }
        } return null;
    }

    public Loot getLoot(Location location) {
        for (Loot loot : this.map.getLootChests()) {
            if (loot.getLocation().get(location.getWorld()).distance(location.get()) < 3)
                return loot;
        } return null;
    }

    public void resetLoot() {
        Loot loot = this.map.getLootChests().get(new Random().nextInt(this.map.getLootChests().size()));
        if (this.loot != null) {
            if (this.loot == loot) {
                this.resetLoot();
                return;
            }
        }
        loot.setTries(0);
        loot.setActive(true);
        this.loot = loot;
    }
}
