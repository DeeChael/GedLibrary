package org.gedstudio.library.bukkit.bossbar;

import org.bukkit.boss.BossBar;
import org.gedstudio.library.bukkit.entity.GPlayer;

import java.util.List;

public interface GBossBar {

    /**
     * To check if boss bar will make sky dark
     * @return is darken sky
     */
    boolean isDarkenSky();

    /**
     * To check if boss bar will create fog
     * @return is create fog
     */
    boolean isFog();

    /**
     * To check if boss bar will play boss music
     * @return is play boss music
     */
    boolean isPlayMusic();

    /**
     * To set the boss bar will make sky dark
     * @param darkenSky is darken sky
     */
    void setDarkenSky(boolean darkenSky);

    /**
     * To set the boss bar will create fog
     * @param fog is create fog
     */
    void setFog(boolean fog);

    /**
     * To set the boss bar will play boss music
     * @param playMusic is play boss music
     */
    void setPlayMusic(boolean playMusic);

    /**
     * To get the color of the boss bar
     * @return the color of the boss bar
     */
    GBarColor getColor();

    /**
     * To set the color of the boss bar
     * @param color the color to set
     */
    void setColor(GBarColor color);

    /**
     * To get the style of the boss bar
     * @return the style of the boss bar
     */
    GBarStyle getStyle();

    /**
     * To set the style of the boss bar
     * @param style the style to set
     */
    void setStyle(GBarStyle style);

    /**
     * To get the percent of the boss bar
     * @return the percent of the boss bar
     */
    int getPercent();

    /**
     * To set the percent of the boss bar
     * @param pct the percent to set
     */
    void setPercent(int pct);

    /**
     * To get the players can see the boss bar
     * @return players can see the boss bar
     */
    List<GPlayer> getPlayers();

    /**
     * To add a player can see the boss bar
     * @param player the player to add
     */
    void addPlayer(GPlayer player);

    /**
     * To remove a player can see the boss bar
     * @param player the player to remove
     */
    void removePlayer(GPlayer player);

    /**
     * To remove all players can see the boss bar
     */
    void removeAll();

    /**
     * To make all online players can see the boss bar
     */
    void addAllOnlinePlayer();

    /**
     * To get the Bukkit BossBar field
     * @return Bukkit BossBar field
     */
    BossBar getHandle();

}
