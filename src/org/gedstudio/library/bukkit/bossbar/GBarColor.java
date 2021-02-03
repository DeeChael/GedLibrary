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

    /**
     * To get the nms bar color name
     * @return nms bar color name
     */
    public String getName() {
        return name;
    }

}
