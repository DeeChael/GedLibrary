package org.gedstudio.library.bukkit.bossbar;

import org.bukkit.boss.BarStyle;

public enum GBarStyle {

    PROGRESS(BarStyle.SOLID),
    NOTCHED_6(BarStyle.SEGMENTED_6),
    NOTCHED_10(BarStyle.SEGMENTED_10),
    NOTCHED_12(BarStyle.SEGMENTED_12),
    NOTCHED_20(BarStyle.SEGMENTED_20);

    private BarStyle bukkitStyle;

    GBarStyle(BarStyle bukkitStyle) {
        this.bukkitStyle = bukkitStyle;
    }

    /**
     * To get bukkit style
     * @return Bukkit boss bar style
     */
    public BarStyle getBukkitStyle() {
        return bukkitStyle;
    }

}
