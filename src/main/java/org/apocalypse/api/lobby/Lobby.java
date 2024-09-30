package org.apocalypse.api.lobby;

import lombok.Getter;
import lombok.SneakyThrows;
import org.apocalypse.api.location.Location;
import org.apocalypse.api.map.Map;
import org.apocalypse.api.map.area.Area;
import org.apocalypse.api.map.area.door.Door;
import org.apocalypse.api.map.area.loot.Loot;
import org.apocalypse.api.map.area.spawn.Spawn;
import org.apocalypse.api.map.area.spawn.barrier.Barrier;
import org.apocalypse.api.player.Survivor;
import org.apocalypse.api.scoreboard.team.TeamScoreBoard;
import org.apocalypse.api.utils.LocationUtils;
import org.bukkit.*;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Lobby {

    private final List<Survivor> survivors;
    private final Map map;
    private final World world;
    private final TeamScoreBoard scoreboard;
    private int round = 1;

    @SneakyThrows
    public Lobby(Class<? extends Map> map) {
        this.survivors = new ArrayList<>();
        this.map = map.getConstructor().newInstance();
        this.world = Bukkit.getServer().createWorld(new WorldCreator("map_" + map.getSimpleName().toLowerCase()));
        assert this.world != null;
        this.world.setAutoSave(false);
        this.world.setPVP(false);
        this.world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        this.world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        this.scoreboard = new TeamScoreBoard();
        this.scoreboard.update(List.of("Round: " + this.round));
    }

    public void add(Survivor survivor) {
        this.survivors.add(survivor);
        this.scoreboard.add(survivor);
    }

    public void remove(Survivor survivor) {
        this.survivors.remove(survivor);
        this.scoreboard.remove(survivor);
    }

    public int size() {
        return this.survivors.size();
    }

    public void next() {
        this.round++;
    }

    public void win() {

    }

    public void lose() {

    }

    public Door getDoor(Location location) {
        for (Area area : this.map.getAreas()) {
            for (Door door : area.getDoors()) {
                if (LocationUtils.getMiddle(door.getFirst().get(location.getWorld()), door.getSecond().get(location.getWorld()))
                        .distance(location.get()) < 2)
                    return door;
            }
        } return null;
    }

    public Barrier getBarrier(Location location) {
        for (Area area : this.map.getAreas()) {
            for (Spawn spawn : area.getSpawns()) {
                if (spawn.getBarrier().getCenter(location.getWorld()).distance(location) < 5)
                    return spawn.getBarrier();
            }
        } return null;
    }

    public Loot getLoot(Location location) {
        for (Area area : this.map.getAreas()) {
            if (area.getLoot() == null) continue;
            if (area.getLoot().getLocation().get(location.getWorld()).distance(location.get()) < 2)
                return area.getLoot();
        } return null;
    }
}
