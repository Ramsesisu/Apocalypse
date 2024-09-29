package org.apocalypse.api.builder;

import lombok.SneakyThrows;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ItemBuilder {

    private final ItemStack item;

    private ItemBuilder(Material material) {
        this(material, 1);
    }

    private ItemBuilder(ItemStack item) {
        this.item = Objects.requireNonNullElseGet(item, () -> new ItemStack(Material.PLAYER_HEAD, 1));
    }

    private ItemBuilder(Material material, int amount) {
        item = new ItemStack(material, amount);
    }

    public static ItemBuilder create(Material material) {
        return new ItemBuilder(material).clone();
    }

    public static ItemBuilder create(ItemStack item) {
        return new ItemBuilder(item).clone();
    }

    public static ItemBuilder get(ItemStack item) {
        return new ItemBuilder(item);
    }

    public ItemBuilder setDurability(short durability) {
        item.setDurability(durability);
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setUnbreakable(unbreakable);
            item.setItemMeta(meta);
        }
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public ItemBuilder setName(String name) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            item.setItemMeta(meta);
        }
        return this;
    }

    public ItemBuilder setName(Component name) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.displayName(name);
            item.setItemMeta(meta);
        }
        return this;
    }

    public ItemBuilder emptyName() {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(" ");
            item.setItemMeta(meta);
        }
        return this;
    }

    public String getName() {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            return meta.getDisplayName();
        }
        return null;
    }

    public ItemBuilder setLore(String lore) {
        return this.setLore(List.of(lore));
    }

    public ItemBuilder setLore(List<String> lore) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
        return this;
    }

    public String getLore() {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            return meta.getLore().get(0);
        }
        return null;
    }

    public ItemBuilder addLore(String lore) {
        return this.addLore(List.of(lore));
    }

    public ItemBuilder addLore(List<String> lore) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            List<String> currentLore = meta.getLore();
            if (currentLore == null) currentLore = lore;
            else currentLore.addAll(lore);
            meta.setLore(currentLore);
            item.setItemMeta(meta);
        }
        return this;
    }

    public ItemBuilder emptyLore() {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setLore(Collections.emptyList());
            item.setItemMeta(meta);
        }
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        item.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ItemBuilder removeEnchantment(Enchantment enchantment) {
        item.removeEnchantment(enchantment);
        return this;
    }

    public ItemBuilder setHead(OfflinePlayer player) {
        if (this.item.getType() != Material.PLAYER_HEAD)
            return this;
        ItemMeta meta = this.item.getItemMeta();
        if (meta != null) {
            ((SkullMeta) meta).setOwningPlayer(player);
            this.item.setItemMeta(meta);
        }
        return this;
    }

    public ItemBuilder copy() {
        return new ItemBuilder(this.item);
    }

    @SneakyThrows
    public ItemBuilder clone() {
        return new ItemBuilder(this.item.clone());
    }

    public ItemStack build() {
        return this.item;
    }
}