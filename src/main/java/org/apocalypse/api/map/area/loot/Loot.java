package org.apocalypse.api.map.area.loot;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apocalypse.Apocalypse;
import org.apocalypse.api.location.Location;
import org.apocalypse.api.map.hologram.Hologram;
import org.apocalypse.api.player.Survivor;
import org.apocalypse.api.weapon.type.WeaponType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

@Getter
@Setter
@RequiredArgsConstructor
public class Loot {

    private final Location location;
    private final Hologram hologram;
    private boolean active = false;
    private int tries = 0;
    private Class<? extends WeaponType> current = null;
    private boolean spinning = false;

    public void open(Survivor survivor) {
        if (!survivor.removeMoney(1000)) return;
        this.spinning = true;
        final int[] count = {0};
        new BukkitRunnable() {
            @Override
            public void run() {
                current = survivor.getLobby().getMap().getLoot()
                        .get(new Random().nextInt(survivor.getLobby().getMap().getLoot().size()));
                WeaponType type = null;
                try {
                    type = current.getConstructor().newInstance();
                    hologram.getEntity().getEquipment().setHelmet(new ItemStack(type.getItem()));
                    hologram.setText("§6" + type.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                survivor.getWorld().playSound(location.get(survivor.getWorld()), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 1.0F, 1.2F);
                if (count[0]++ == 10) {
                    spinning = false;
                    WeaponType finalType = type;
                    survivor.getLobby().getSurvivors().forEach(s -> s.sendMessage("§6" + survivor.getName() + " §5drew an §e" + finalType.getName() + "§5!"));
                    Bukkit.getScheduler().runTaskLater(Apocalypse.getInstance(), () -> {
                        setActive(true);
                        getHologram().getEntity().getEquipment().setHelmet(new ItemStack(Material.AIR));
                        setCurrent(null);
                        if (tries == 3) {
                            survivor.getLobby().resetLoot();
                            tries = 0;
                        } else tries++;
                    }, 120L);
                    this.cancel();
                }
            }
        }.runTaskTimer(Apocalypse.getInstance(), 0, 10L);
    }

    public void setActive(boolean active) {
        this.active = active;
        if (active) {
            hologram.setText("§e§lLoot§8: §61000$");
        } else {
            hologram.setText(" ");
        }
    }
}
