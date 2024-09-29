package org.apocalypse.api.lobby;

import lombok.Getter;
import lombok.SneakyThrows;
import org.apocalypse.api.map.Map;
import org.apocalypse.api.player.Survivor;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Lobby {

    private final List<Survivor> survivors;
    private final Map map;
    private final World world;
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
    }

    public void add(Survivor survivor) {
        this.survivors.add(survivor);
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
}
