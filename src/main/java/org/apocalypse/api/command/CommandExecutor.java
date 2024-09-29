package org.apocalypse.api.command;

import lombok.Getter;
import lombok.Setter;
import org.apocalypse.Apocalypse;
import org.apocalypse.api.player.Survivor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public abstract class CommandExecutor {

    protected Apocalypse apocalypse;
    @Getter
    @Setter
    protected Prefix prefix;

    public abstract void execute(final Survivor survivor, final String[] args, final String name, final Command command);

    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args)  {
        return null;
    }

    public List<String> getListCompleter(final List<String> list, final String arg) {
        final List<String> completions = new ArrayList<>();
        for(String string : list) {
            if(string.toLowerCase().startsWith(arg.toLowerCase())) {
                completions.add(string);
            }
        }
        return completions;
    }

    public Apocalypse getMcRoleplayMain() {
        return apocalypse;
    }

    protected int getInteger(final String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            Bukkit.getLogger().log(Level.WARNING, e, () -> "Couldn't parse " + string + " to an integer.");
        }
        return 0;
    }

    protected boolean checkPermissions(final Survivor survivor, final Command command) {
        String permission = command.permissions();
        if (permission.isEmpty()) {
            permission = "mcrp.command." + command.name();
        }

        if (!survivor.hasPermission(permission)) {
            this.sendNoPermissionMessage(survivor);
            return false;
        }
        return true;
    }

    protected boolean isInteger(final String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String[] parseSubCommandArgs(final String[] args) {
        return Arrays.copyOfRange(args, 1, args.length);
    }

    protected String mergeArgs(final String[] args, final int statIndex) {
        return String.join(" ", Arrays.copyOfRange(args, statIndex, args.length));
    }

    protected void sendNoPermissionMessage(final Survivor survivor) {
        survivor.sendMessage("mcrp.message.no-permission");
    }

    public void setMcRoleplayMain(Apocalypse apocalypse) {
        this.apocalypse = apocalypse;
    }
}
