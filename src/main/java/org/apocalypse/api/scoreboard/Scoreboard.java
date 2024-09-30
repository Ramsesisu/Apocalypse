package org.apocalypse.api.scoreboard;

import net.kyori.adventure.text.Component;
import org.apocalypse.api.player.Survivor;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.List;

public abstract class Scoreboard {

    private final org.bukkit.scoreboard.Scoreboard scoreboard;

    public Scoreboard() {
        ScoreboardManager manager = Bukkit.getServer().getScoreboardManager();
        this.scoreboard = manager.getNewScoreboard();
        Objective objective = this.scoreboard.registerNewObjective("test", Criteria.DUMMY, Component.text("§e§lAPOCALYPSE"));
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    public org.bukkit.scoreboard.Scoreboard get() {
        return this.scoreboard;
    }

    public abstract void update(List<String> lines);

    public void set(List<String> lines) {
        Objective objective = this.scoreboard.getObjective("test");
        for (String entry : this.scoreboard.getEntries())
            this.scoreboard.resetScores(entry);
        for (int i = 0; i < lines.size(); i++)
            objective.getScore(lines.get(i)).setScore(lines.size() - i);
    }

    public void add(Survivor survivor) {
        survivor.online().setScoreboard(this.scoreboard);
    }

    public void remove(Survivor survivor) {
        survivor.online().setScoreboard(Bukkit.getServer().getScoreboardManager().getMainScoreboard());
    }
}
