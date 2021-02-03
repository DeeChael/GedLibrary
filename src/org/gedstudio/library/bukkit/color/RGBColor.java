package org.gedstudio.library.bukkit.color;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Color;

import java.io.Serializable;

public class RGBColor implements Serializable {

    private final int color;

    private final int r;
    private final int g;
    private final int b;

    /**
     * To create new RGB Color
     * @param color rgb color number
     */
    public RGBColor(int color) {
        this.color = color;
        this.r = (color & 0xff0000) >> 16;
        this.g = (color & 0x00ff00) >> 8;
        this.b = (color & 0x0000ff);
    }

    /**
     * To create new RGB Color
     * @param r red color number
     * @param g green color number
     * @param b blue color number
     */
    public RGBColor(int r, int g, int b) {
        this.color = (r * 65536) + (g * 256) + b;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * To get rgb color number
     * @return rgb color number
     */
    public int getColor() {
        return color;
    }

    /**
     * To get blue color number
     * @return blue color number
     */
    public int getB() {
        return b;
    }

    /**
     * To get green color number
     * @return green color number
     */
    public int getG() {
        return g;
    }

    /**
     * To get red color number
     * @return red color number
     */
    public int getR() {
        return r;
    }

    /**
     * To cast to hex color
     * @return hex color
     */
    public HexColor toHex() {
        return new HexColor(Integer.toHexString(color).toLowerCase());
    }

    /**
     * To cast to Bukkit Color field
     * @return Bukkit Color field
     */
    public Color toBukkit() {
        return Color.fromRGB(color);
    }

    /**
     * To cast to Bungee Chat Color field
     * @return Bungee Chat Color field
     */
    public ChatColor toBungee() {
        return this.toHex().toBungee();
    }

}
