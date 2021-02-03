package org.gedstudio.library.bukkit.bossbar;

import org.bukkit.boss.BossBar;
import org.gedstudio.library.bukkit.entity.GPlayer;

import java.util.List;

public interface GBossBar {

    boolean isDarkenSky();

    boolean isFog();

    boolean isPlayMusic();

    void setDarkenSky(boolean darkenSky);

    void setFog(boolean fog);

    void setPlayMusic(boolean playMusic);

    GBarColor getColor();

    void setColor(GBarColor color);

    GBarStyle getStyle();

    void setStyle(GBarStyle style);

    int getPercent();

    void setPercent(int pct);

    List<GPlayer> getPlayers();

    void addPlayer(GPlayer player);

    void removePlayer(GPlayer player);

    void removeAll();

    void addAllOnlinePlayer();

    BossBar getHandle();

}
