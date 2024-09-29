package org.apocalypse.api.command;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class Prefix {

    @Getter
    public enum Color {
        BLACK(org.bukkit.Color.BLACK),
        NAVY(org.bukkit.Color.NAVY),
        BLUE(org.bukkit.Color.BLUE),
        GREEN(org.bukkit.Color.GREEN),
        TEAL(org.bukkit.Color.TEAL),
        RED(org.bukkit.Color.RED),
        PURPLE(org.bukkit.Color.PURPLE),
        ORANGE(org.bukkit.Color.ORANGE),
        GRAY(org.bukkit.Color.GRAY),
        FUCHSIA(org.bukkit.Color.FUCHSIA),
        LIME(org.bukkit.Color.LIME),
        YELLOW(org.bukkit.Color.YELLOW),
        AQUA(org.bukkit.Color.AQUA),
        WHITE(org.bukkit.Color.WHITE),
        SILVER(org.bukkit.Color.SILVER),
        MAROON(org.bukkit.Color.MAROON),
        OLIVE(org.bukkit.Color.OLIVE);


        private final org.bukkit.Color color;

        Color(org.bukkit.Color color) {
            this.color = color;
        }

        public int getRed() {
            return color.getRed();
        }

        public int getGreen() {
            return color.getGreen();
        }

        public int getBlue() {
            return color.getBlue();
        }

        public static org.bukkit.Color fromRBG(int red, int green, int blue) {
            return org.bukkit.Color.fromRGB(red, green, blue);
        }
    }

    String prefix;
    Color color;
    Color gradient;

    public Prefix(String prefix, Color color) {
        this.prefix = prefix;
        this.color = color;
        this.gradient = color;
    }

    public Prefix(String prefix, Color color1, Color color2) {
        this.prefix = prefix;
        this.color = color1;
        this.gradient = color2;
    }

    public Prefix() {
        this.prefix = "§eMC§6RP";
        this.color = Color.ORANGE;
        this.gradient = Color.ORANGE;
    }

    public String getName() {
        return prefix;
    }

    public void setName(String prefix) {
        this.prefix = prefix;
    }

    public Component getPrefix() {
        Component component = Component.text("§8[§");
        if (gradient != null) {
            for (int i = 0; i < prefix.length(); i++) {
                float ratio = (float) i / (prefix.length() - 1);
                int red = (int) (color.getRed() + ratio * (gradient.getRed() - color.getRed()));
                int green = (int) (color.getGreen() + ratio * (gradient.getGreen() - color.getGreen()));
                int blue = (int) (color.getBlue() + ratio * (gradient.getBlue() - color.getBlue()));
                org.bukkit.Color interpolatedColor = Color.fromRBG(red, green, blue);
                component = component.append(Component.text(String.valueOf(prefix.charAt(i)))
                        .color(TextColor.color(interpolatedColor.getRed(), interpolatedColor.getGreen(), interpolatedColor.getBlue())));
            }
        } else {
            component = component.append(Component.text(prefix)
                    .color(TextColor.color(color.getRed(), color.getGreen(), color.getBlue())));
        }
        return component.append(Component.text("§8] §7"));
    }
}