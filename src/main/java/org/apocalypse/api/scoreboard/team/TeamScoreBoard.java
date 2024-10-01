package org.apocalypse.api.scoreboard.team;

import org.apocalypse.api.lobby.Lobby;
import org.apocalypse.api.map.area.Area;
import org.apocalypse.api.player.Survivor;
import org.apocalypse.api.scoreboard.Scoreboard;
import org.apocalypse.api.utils.TimeUtils;
import org.bukkit.GameMode;

import java.util.ArrayList;
import java.util.List;

public class TeamScoreBoard extends Scoreboard {

    public TeamScoreBoard(Survivor survivor) {
        super("Test", survivor);
    }

    @Override
    public void update(Survivor survivor) {
        Lobby lobby = survivor.getLobby();
        if (lobby == null) return;
        List<String> board = new ArrayList<>();
        board.add("§8§o" + TimeUtils.getFormattedCurrentDate());
        board.add("");
        board.add(lobby.getRound() > 0 ? "§cWave §c§l" + lobby.getRound() : "§cLobby");
        if (lobby.getRound() > 0)
            board.add("§7Monster§8: §a" + lobby.getMonster().size());
        else {
            long elapsedTime = (System.currentTimeMillis() - lobby.getCreated()) / 1000;
            long remainingTime = (lobby.size() < 4 ? lobby.size() < 3 ? 60 : 30 : 10) - elapsedTime;
            if (remainingTime <= 0) {
                lobby.nextWave();
                return;
            }
            board.add("§7Time§8: §a" + remainingTime + "s");
        }
        board.add("");
        for (Survivor player : lobby.getSurvivors())
            board.add((player.getName().equals(survivor.getName()) ? "§a" : "§7") + player.getName() + "§8: " +
                    (player.isInLobby() ? !player.isDead() ? !player.online().getGameMode().equals(GameMode.SPECTATOR) ? "§6" + player.getMoney() + "$" :
                            "§cDEAD" : "§eREVIVE §8(§e" + (TimeUtils.getSecondsUntil(player.getCorpse().getTime())) + "§8)" :  "§cQUIT"));
        board.add("");
        if (lobby.getRound() > 0)
            board.add("§7Kills§8: §a" + survivor.getKills());
        Area area = lobby.getArea(survivor.getLocation());
        board.add("§7Area§8: §a" + (area == null ? "None" : area.getName()));
        board.add("");
        this.set(board);
    }
}
