package org.apocalypse.api.scoreboard.team;

import org.apocalypse.api.scoreboard.Scoreboard;

import java.util.List;

public class TeamScoreBoard extends Scoreboard {

    @Override
    public void update(List<String> lines) {
        this.set(lines);
    }
}
