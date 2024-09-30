package org.apocalypse.api.lobby.wave;

import lombok.Getter;
import org.apocalypse.Apocalypse;
import org.apocalypse.api.lobby.Lobby;
import org.apocalypse.api.map.area.spawn.Spawn;
import org.apocalypse.api.monster.Monster;
import org.apocalypse.api.monster.type.MonsterType;
import org.apocalypse.api.player.Survivor;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;

@Getter
public class Wave extends BukkitRunnable {

    private final Lobby lobby;
    private final List<MonsterType> monster;
    private final int amount;
    private int spawned = 0;
    private int index = 0;

    public Wave(Lobby lobby, List<MonsterType> monster, int amount) {
        this.lobby = lobby;
        this.monster = monster;
        this.amount = amount;
    }

    public void start() {
        List<Spawn> spawns = this.lobby.getMap().getSpawns();
        for (int i = 0; i < this.amount; i++) {
            if (this.index >= spawns.size()) this.index = 0;
            Spawn spawn = spawns.get(this.index);
            MonsterType type = this.monster.get(new Random().nextInt(this.monster.size()));
            Monster monster = new Monster(this.lobby, spawn, type);
            this.lobby.getMonster().add(monster);
            this.index++;
        }
        lobby.getSurvivors().forEach(Survivor::updateScoreboard);
        this.runTaskTimer(Apocalypse.getInstance(), 0L, Math.round(200 * Math.pow(this.amount, -0.5)));
    }

    @Override
    public void run() {
        if (this.spawned > this.amount)
            this.cancel();
        this.lobby.getMonster().get(this.spawned).spawn(this.lobby);
        this.spawned++;
    }
}
