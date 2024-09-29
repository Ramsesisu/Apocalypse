package org.apocalypse.api.player;

import lombok.Getter;
import org.bukkit.OfflinePlayer;

@Getter
public class Survivor {

    private final OfflinePlayer player;

    public Survivor(OfflinePlayer player) {
        this.player = player;
    }
}
