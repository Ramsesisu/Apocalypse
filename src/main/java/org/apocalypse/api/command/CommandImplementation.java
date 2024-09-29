package org.apocalypse.api.command;

import org.apocalypse.api.player.Survivor;
import org.apocalypse.api.service.container.Container;
import org.apocalypse.core.player.PlayerService;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CommandImplementation extends org.bukkit.command.Command {

    private final CommandExecutor commandExecutor;
    private final Command command;

    public CommandImplementation(@NotNull String name, final CommandExecutor commandExecutor, final Command command) {
        super(name);
        this.commandExecutor = commandExecutor;
        this.command = command;

        if (command.prefix().isEmpty()) {
            this.commandExecutor.prefix = new Prefix();
        } else {
            if (command.prefixGradient() == Prefix.Color.WHITE) {
                this.commandExecutor.prefix = new Prefix(command.prefix(), command.prefixColor());
            } else {
                this.commandExecutor.prefix = new Prefix(command.prefix(), command.prefixColor(), command.prefixGradient());
            }
        }
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] strings) {
        if(!(sender instanceof Player)) {
            Bukkit.getLogger().warning("This command is only useable as a offer");
            return false;
        }

        final PlayerService playerService = Container.get(PlayerService.class);
        final Survivor survivor = playerService.get(((Player) sender));

        this.commandExecutor.execute(survivor, strings, s, command);
        return false;
    }

    @NotNull
    @Override
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        final List<String> list = commandExecutor.tabComplete(sender, alias, args);
        if(list == null ) {
            return super.tabComplete(sender, alias, args);
        }

        return list;
    }
}
