package org.apocalypse.api.player.corpse;

import lombok.Getter;
import org.apocalypse.Apocalypse;
import org.apocalypse.api.builder.ItemBuilder;
import org.apocalypse.api.location.Location;
import org.apocalypse.api.player.Survivor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

@Getter
public class Corpse extends BukkitRunnable {

    private final Survivor survivor;
    private final Item item;
    private final Location location;
    private final long time;

    public Corpse(Survivor survivor) {
        ItemStack item = ItemBuilder.create(Material.PLAYER_HEAD).setHead(survivor.getPlayer()).build();
        this.item = survivor.getWorld().dropItem(survivor.getLocation().get(), item);
        this.item.setCanMobPickup(false);
        this.item.setCustomName("§7§l✟§f " + survivor.getName());
        this.item.setCustomNameVisible(false);
        this.location = survivor.getLocation();
        this.time = System.currentTimeMillis() + 30000;
        this.survivor = survivor;
        this.runTaskTimer(Apocalypse.getInstance(), 0L, 20L);
    }

    @Override
    public void run() {
        if (this.survivor == null
                || !this.survivor.isOnline()
                || !this.survivor.isInLobby()
                || System.currentTimeMillis() > this.time
                || !this.item.isValid()) {
            if (this.survivor != null) {
                this.survivor.setCorpse(null);
            }
            if (this.item.isValid())
                this.item.remove();
            this.cancel();
        }
    }
}
