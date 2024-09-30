package org.apocalypse.api.player;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.apocalypse.api.command.Prefix;
import org.apocalypse.api.lobby.Lobby;
import org.apocalypse.api.location.Location;
import org.apocalypse.api.scoreboard.Scoreboard;
import org.apocalypse.api.scoreboard.main.MainScoreboard;
import org.apocalypse.api.scoreboard.team.TeamScoreBoard;
import org.apocalypse.api.utils.LocationUtils;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@Getter
@Setter
public class Survivor {

    @Setter(AccessLevel.NONE)
    private final OfflinePlayer player;
    private Scoreboard scoreboard;
    private Lobby lobby = null;
    private int money = 0;
    private int kills;


    public Survivor(OfflinePlayer player) {
        this.player = player;
        this.scoreboard = new MainScoreboard(this);
    }

    public Player online() {
        return player.getPlayer();
    }

    public boolean isOnline() {
        return player.isOnline();
    }

    public String getName() {
        return player.getName();
    }

    public boolean hasPermission(String permission) {
        if (this.player.isOp()) return true;
        if (this.isOnline())
            return this.online().hasPermission(permission);
        return false;
    }

    public void sendMessage(Component text, String... args) {
        if (!this.isOnline()) return;
        for (int i = 0; i < args.length; i++)
            text = text.replaceText(TextReplacementConfig.builder().match("{" + i + "}").replacement(args[i]).build());
        this.online().sendMessage(text);
    }

    public void sendMessage(String msg, String... args) {
        this.sendMessage(Component.text(msg), args);
    }

    public void sendMessage(Prefix prefix, Component text, String... args) {
        this.sendMessage(prefix.getPrefix().append(text), args);
    }

    public void sendMessage(Prefix prefix, String msg, String... args) {
        this.sendMessage(prefix.getPrefix().append(Component.text(msg)), args);
    }

    public void sendRawMessage(Object object) {
        this.sendMessage(String.valueOf(object));
    }

    public void joinLobby(Lobby lobby) {
        if (this.isOnline()) {
            this.teleport(lobby.getWorld(), lobby.getMap().getSpawn());
            if (!lobby.getSurvivors().contains(this))
                lobby.add(this);
            this.setLobby(lobby);
            this.setScoreboard(new TeamScoreBoard(this));
        }
    }

    public void leaveLobby() {
        if (this.isOnline()) {
            this.teleport(LocationUtils.WORLD, new Location(LocationUtils.WORLD.getSpawnLocation()));
            if (this.getLobby() != null)
                this.getLobby().remove(this);
            this.setLobby(null);
            this.setScoreboard(new MainScoreboard(this));
        }
    }

    public void teleport(World world, Location target) {
        if (this.isOnline()) {
            this.online().teleport(target.get(world));
        }
    }

    public void teleport(Location target) {
        if (this.isOnline()) {
            this.teleport(this.getLocation().get().getWorld(), target);
        }
    }

    public Location getLocation() {
        return this.isOnline() ? new Location(this.online().getLocation()) : null;
    }

    public World getWorld() {
        return this.isOnline() ? this.online().getWorld() : null;
    }

    public Inventory getInventory() {
        return this.isOnline() ? this.online().getInventory() : null;
    }

    public void give(ItemStack item) {
        if (this.isOnline())
            this.online().getInventory().addItem(item);
    }

    public void setScoreboard(Scoreboard scoreboard) {
        if (this.isOnline()) {
            this.scoreboard = scoreboard;
            this.online().setScoreboard(this.scoreboard.get());
        }
    }

    public void updateScoreboard() {
        if (this.isOnline()) {
            this.scoreboard.update(this);
        }
    }

    public boolean addMoney(int amount) {
        this.money += amount;
        return true;
    }

    public boolean hasMoney(int amount) {
        return this.money >= amount;
    }

    public boolean removeMoney(int amount) {
        if (!this.hasMoney(amount))
            return false;
        this.money -= amount;
        return true;
    }

    public void addKill() {
        this.kills++;
    }

    @SuppressWarnings("deprecation")
    public void sendTitle(String title) {
        if (this.isOnline())
            this.online().sendTitle(title, "", 10, 70, 20);
    }

    @SuppressWarnings("deprecation")
    public void sendTitle(String title, String subtitle) {
        if (this.isOnline())
            this.online().sendTitle(title, subtitle, 10, 70, 20);
    }
}
