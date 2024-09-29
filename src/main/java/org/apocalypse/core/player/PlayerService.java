package org.apocalypse.core.player;

import org.apocalypse.api.player.Survivor;
import org.apocalypse.api.service.Service;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class PlayerService extends Service<UUID, Survivor> {

    public @NotNull Survivor get(OfflinePlayer player) {
        return super.get(player.getUniqueId());
    }
}
