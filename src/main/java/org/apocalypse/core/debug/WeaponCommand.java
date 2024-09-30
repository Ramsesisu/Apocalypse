package org.apocalypse.core.debug;

import org.apocalypse.api.command.Command;
import org.apocalypse.api.command.CommandExecutor;
import org.apocalypse.api.command.Prefix;
import org.apocalypse.api.player.Survivor;
import org.apocalypse.api.service.container.Container;
import org.apocalypse.api.weapon.Weapon;
import org.apocalypse.api.weapon.type.WeaponType;
import org.apocalypse.core.weapon.WeaponRecord;
import org.apocalypse.core.weapon.WeaponService;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Command(name = "weapon", usage = "/weapon [Weapon]", op = true, prefix = "Weapon", description = "Gives a weapon to the player.", prefixColor = Prefix.Color.MAROON, prefixGradient = Prefix.Color.PURPLE)
public class WeaponCommand extends CommandExecutor {

    @Override
    public void execute(Survivor survivor, String[] args, String name, Command command) {
        if (!checkPermissions(survivor, command)) return;

        if (args.length == 0) {
            survivor.sendMessage(prefix + command.usage());
            return;
        }

        WeaponRecord weaponRecord = Container.get(WeaponRecord.class);
        WeaponType type = weaponRecord.get(args[0]);
        Weapon weapon = new Weapon(type);
        ItemStack item = weapon.getItem();
        survivor.give(item);
        WeaponService weaponService = Container.get(WeaponService.class);
        weaponService.add(weapon.getKey(), weapon);

        survivor.sendMessage(prefix, "§cReceived the weapon " + type.getName() + ".");
    }

    @Override
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args)  {
        if (args.length == 1) {
            WeaponRecord weaponRecord = Container.get(WeaponRecord.class);
            List<String> records = new ArrayList<>();
            for (WeaponType weapon : weaponRecord.list.values())
                records.add(weapon.getName());
            return this.getListCompleter(records, args[0]);
        }
        return null;
    }
}
