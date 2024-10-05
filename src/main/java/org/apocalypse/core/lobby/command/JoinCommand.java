package org.apocalypse.core.lobby.command;

import lombok.SneakyThrows;
import net.kyori.adventure.text.Component;
import org.apocalypse.api.builder.ItemBuilder;
import org.apocalypse.api.command.Command;
import org.apocalypse.api.command.CommandExecutor;
import org.apocalypse.api.command.Prefix;
import org.apocalypse.api.lobby.Lobby;
import org.apocalypse.api.player.Survivor;
import org.apocalypse.api.service.container.Container;
import org.apocalypse.core.lobby.LobbyService;
import org.apocalypse.core.weapon.WeaponService;
import org.apocalypse.core.weapon.type.AA12;
import org.apocalypse.core.weapon.type.Knife;
import org.bukkit.Material;
import org.bukkit.inventory.MainHand;

@Command(name = "join", usage = "/join", prefix = "Join", description = "Join a game.", prefixColor = Prefix.Color.YELLOW, prefixGradient = Prefix.Color.LIME)
public class JoinCommand extends CommandExecutor {

    @SneakyThrows
    @Override
    public void execute(Survivor survivor, String[] args, String name, Command command) {
        if (survivor.online().getMainHand() == MainHand.LEFT) {
            survivor.online().kick(Component.text("§cYou are not allowed to join having the main hand set to left."));
            return;
        }
        Lobby lobby = Container.get(LobbyService.class).find("deadend");
        lobby.add(survivor);
        survivor.joinLobby(lobby);
        Container.get(WeaponService.class).give(Knife.class, survivor, 0);
        Container.get(WeaponService.class).give(AA12.class, survivor, 1);
        survivor.give(2, ItemBuilder.create(Material.FIREWORK_STAR).setName("§6Weapon #2").build());
        for (int i = 6; i < 9; i++)
            survivor.give(i, ItemBuilder.create(Material.GRAY_DYE).setName("§ePerk #" + (i - 5)).build());
    }
}