package org.gedstudio.library.bukkit.bossbar;

public enum GBarColor {

    WHITE("white"),
    PINK("pink"),
    BLUE("blue"),
    RED("red"),
    GREEN("green"),
    PURPLE("purple"),
    YELLOW("yellow");

    private String name;

    GBarColor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
