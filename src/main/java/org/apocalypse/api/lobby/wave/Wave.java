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

    public Wave(Lobby lobby, List<MonsterType> monster, int amount) {
        this.lobby = lobby;
        this.monster = monster;
        this.amount = amount;
    }

    public void start() {
        List<Spawn> spawns = this.lobby.getMap().getSpawns();
        int index = 0;
        for (int i = 0; i < this.amount; i++) {
            if (index >= spawns.size()) index = 0;
            Spawn spawn = spawns.get(index);
            MonsterType type = this.monster.get(new Random().nextInt(this.monster.size()));
            Monster monster = new Monster(this.lobby, spawn, type);
            this.lobby.getMonster().add(monster);
            index++;
        }
        lobby.getSurvivors().forEach(Survivor::updateScoreboard);
        this.runTaskTimer(Apocalypse.getInstance(), 20L, Math.round(200 * Math.pow(this.amount, -0.5)));
    }

    @Override
    public void run() {
        for (Monster monster : this.lobby.getMonster()) {
            if (!monster.isSpawned()) {
                monster.spawn(this.lobby);
                return;
            }
        }
        this.cancel();
    }
}
