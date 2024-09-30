package org.apocalypse.api.scoreboard.team;

import org.apocalypse.api.lobby.Lobby;
import org.apocalypse.api.map.area.Area;
import org.apocalypse.api.player.Survivor;
import org.apocalypse.api.scoreboard.Scoreboard;
import org.apocalypse.api.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

public class TeamScoreBoard extends Scoreboard {

    public TeamScoreBoard(Survivor survivor) {
        super(survivor);
    }

    @Override
    public void update(Survivor survivor) {
        Lobby lobby = survivor.getLobby();
        if (lobby == null) return;
        List<String> list = new ArrayList<>();
        list.add("§8§o" + TimeUtils.getFormattedCurrentDate());
        list.add("");
        list.add("§cWave §c§l" + lobby.getWave());
        list.add("§7Monster§8: §a" + lobby.getMonsters().size());
        list.add("");
        for (Survivor player : lobby.getSurvivors())
            list.add((player.getName().equals(survivor.getName()) ? "§a" : "§7") + player.getName() + "§8: §6" + player.getMoney() + "$");
        list.add("");
        Area area = lobby.getArea(survivor.getLocation());
        list.add("§7Area§8: §a" + (area == null ? "None" : area.getName()));
        list.add("");
        this.set(list);
    }
}
