package org.apocalypse.api.monster;

import lombok.Getter;
import lombok.SneakyThrows;
import org.apocalypse.Apocalypse;
import org.apocalypse.api.lobby.Lobby;
import org.apocalypse.api.location.Location;
import org.apocalypse.api.map.area.spawn.Spawn;
import org.apocalypse.api.monster.type.MonsterType;
import org.apocalypse.api.player.Survivor;
import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Creature;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

@Getter
public class Monster {

    private final Creature entity;
    private final MonsterType type;
    private final Spawn spawn;

    @SneakyThrows
    public Monster(Lobby lobby, Spawn spawn, MonsterType type) {
        this.type = type;
        this.spawn = spawn;
        this.entity = lobby.getWorld().createEntity(spawn.getLocation().get(), type.getType());
        this.entity.setAggressive(true);
        if (this.entity instanceof Ageable ageable)
            if (type.isBaby()) ageable.setBaby();
        EntityEquipment equipment = this.entity.getEquipment();
        equipment.setHelmet(ItemStack.of(type.getHelmet()));
        equipment.setChestplate(ItemStack.of(type.getChestplate()));
        equipment.setLeggings(ItemStack.of(type.getLeggings()));
        equipment.setBoots(ItemStack.of(type.getBoots()));
        Objects.requireNonNull(this.entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(type.getHealth());
    }

    public void spawn(Lobby lobby) {
        this.entity.spawnAt(spawn.getLocation().get(lobby.getWorld()));
        new BukkitRunnable() {
            @Override
            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                Survivor closest = getClosest(lobby);
                if (closest == null) {
                    lobby.lose();
                    this.cancel();
                    return;
                }
                entity.setTarget(closest.online());
                if (new Location(entity.getLocation()).distance(spawn.getBarrier().getCenter(lobby.getWorld())) < 2.5)
                    spawn.getBarrier().destroy(lobby.getWorld());
            }
        }.runTaskTimer(Apocalypse.getInstance(), 0L, 40L);
    }

    public Survivor getClosest(Lobby lobby) {
        Survivor closest = null;
        double distance = Double.MAX_VALUE;
        for (Survivor player : lobby.getSurvivors()) {
            if (!player.isOnline()) continue;
            if (player.online().getGameMode() == GameMode.SPECTATOR) continue;
            double d = player.getLocation().get().distance(this.entity.getLocation());
            if (d < distance) {
                distance = d;
                closest = player;
            }
        }
        return closest;
    }

    public boolean isSpawned() {
        return this.entity.isValid();
    }
}
