package net.deechael.ged.library.block.banner;

import org.bukkit.DyeColor;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;

import java.io.Serializable;

public class GPattern implements Serializable {

    private DyeColor color;
    private PatternType type;

    public GPattern(DyeColor color, PatternType type) {
        this.color = color;
        this.type = type;
    }

    public DyeColor getColor() {
        return color;
    }

    public PatternType getType() {
        return type;
    }

    public Pattern toPattern() {
        return new Pattern(color, type);
    }

    public static GPattern fromPattern(Pattern pattern) {
        return new GPattern(pattern.getColor(), pattern.getPattern());
    }

}
