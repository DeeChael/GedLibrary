package org.gedstudio.library.bukkit.color;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Color;

import java.io.Serializable;

public class HexColor implements Serializable {

    private final String hex;

    /**
     * To create new HexColor
     * @param hex the hex string
     */
    public HexColor(String hex) {
        if (hex.length() >= 6) {
            this.hex = hex.substring(0, 6);
        } else {
            for (int i = 0; i < (6 - hex.length()); i++) {
                hex += "0";
            }
            this.hex = hex;
        }
    }

    /**
     * To get hex color string
     * @return hex string
     */
    public String getColor() {
        return this.hex;
    }

    /**
     * To cast to RGB Color
     * @return rgb color
     */
    public RGBColor toRGB() {
        return new RGBColor(Integer.valueOf(hex, 16));
    }

    /**
     * To cast to Bukkit Color field
     * @return Bukkit Color field
     */
    public Color toBukkit() {
        return Color.fromRGB(this.toRGB().getColor());
    }

    /**
     * To cast to Bungee Chat Color field
     * @return Bungee Chat Color field
     */
    public ChatColor toBungee() {
        return ChatColor.of("#" + hex);
    }

}
