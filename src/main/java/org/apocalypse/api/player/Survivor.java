package org.apocalypse.api.player;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.apocalypse.api.command.Prefix;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

@Getter
public class Survivor {

    private final OfflinePlayer player;

    public Survivor(OfflinePlayer player) {
        this.player = player;
    }

    public Player online() {
        return player.getPlayer();
    }

    public boolean isOnline() {
        return player.isOnline();
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
        this.sendMessage(prefix.getPrefix() + msg, args);
    }

    public void sendRawMessage(Object object) {
        this.sendMessage(String.valueOf(object));
    }
}
