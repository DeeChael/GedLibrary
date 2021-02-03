package org.gedstudio.library.bukkit.scoreboard;

import org.bukkit.scoreboard.Scoreboard;
import org.gedstudio.library.bukkit.entity.GPlayer;

import java.util.List;

public interface GScoreBoard {

    /**
     * To get the title of the scoreboard
     * @return the title of the scoreboard
     */
    String getTitle();

    /**
     * To get the scoreboard was shown to
     * @return players
     */
    List<GPlayer> getPlayers();

    /**
     * To get the context of the scoreboard
     * @return the context of the scoreboard
     */
    List<String> getContext();

    /**
     * To set the scoreboard was shown to player to this
     * @param player player
     */
    void addPlayer(GPlayer player);

    /**
     * To remove player from shown to
     * @param player player to remove
     */
    void removePlayer(GPlayer player);

    /**
     * To cast GScoreboard to Bukkit Scoreboard
     * @return Bukkit Scoreboard
     */
    Scoreboard getHandle();

}
