package org.apocalypse.api.scoreboard;

import net.kyori.adventure.text.Component;
import org.apocalypse.api.player.Survivor;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.*;

import java.util.List;

public abstract class Scoreboard {

    private final String name;
    private final org.bukkit.scoreboard.Scoreboard scoreboard;

    public Scoreboard(String name, Survivor survivor) {
        this.name = name;
        ScoreboardManager manager = Bukkit.getServer().getScoreboardManager();
        this.scoreboard = manager.getNewScoreboard();
        Objective objective = this.scoreboard.registerNewObjective(name, Criteria.DUMMY, Component.text("§e§lAPOCALYPSE"));
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        survivor.online().setScoreboard(this.scoreboard);
        this.update(survivor);
    }

    public org.bukkit.scoreboard.Scoreboard get() {
        return this.scoreboard;
    }

    public abstract void update(Survivor survivor);

    public void set(List<String> lines) {
        Objective objective = this.scoreboard.getObjective(this.name);
        for (String entry : this.scoreboard.getEntries())
            this.scoreboard.resetScores(entry);
        int b = 0;
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).isEmpty()) {
                b++;
                lines.set(i, new StringBuilder().repeat(" ", b).toString());
            }
            assert objective != null;
            objective.getScore(lines.get(i)).setScore(lines.size() - i);
        }
    }
}
