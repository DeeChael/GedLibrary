package org.gedstudio.library.bukkit.block.banner;

import org.bukkit.DyeColor;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;

import java.io.Serializable;

public class GPattern implements Serializable {

    private DyeColor color;
    private PatternType type;

    /**
     * For GItem can be serializable
     * @param color the color of the pattern
     * @param type the type of the pattern
     */
    public GPattern(DyeColor color, PatternType type) {
        this.color = color;
        this.type = type;
    }

    /**
     * To get the color of the pattern
     * @return the color of the pattern
     */
    public DyeColor getColor() {
        return color;
    }

    /**
     * To get the type of the pattern
     * @return the type of the pattern
     */
    public PatternType getType() {
        return type;
    }

    /**
     * To cast to Bukkit Pattern field
     * @return Bukkit Pattern field
     */
    public Pattern toPattern() {
        return new Pattern(color, type);
    }

    /**
     * To get GPattern from Bukkit Pattern field
     * @param pattern Bukkit Pattern field
     * @return GPattern field
     */
    public static GPattern fromPattern(Pattern pattern) {
        return new GPattern(pattern.getColor(), pattern.getPattern());
    }

}
