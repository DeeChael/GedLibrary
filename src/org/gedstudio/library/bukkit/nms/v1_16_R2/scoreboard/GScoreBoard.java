package org.gedstudio.library.bukkit.nms.v1_16_R2.scoreboard;

import net.minecraft.server.v1_16_R2.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.gedstudio.library.bukkit.entity.GPlayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GScoreBoard implements org.gedstudio.library.bukkit.scoreboard.GScoreBoard, Serializable {

    private String title;
    private List<String> context;

    private transient List<GPlayer> players;

    private transient Scoreboard scoreboard;
    private transient ScoreboardObjective objective;
    private transient ScoreboardTeam team;

    public GScoreBoard(String title, String... context) {
        this.title = title;
        this.context = new ArrayList<>();
        for (String string : context) {
            this.context.add(string);
        }
        this.players = new ArrayList<>();
        this.scoreboard = new Scoreboard();
        this.objective = scoreboard.registerObjective("gedlibrary_scoreboard", IScoreboardCriteria.DUMMY, new ChatComponentText(title), IScoreboardCriteria.EnumScoreboardHealthDisplay.INTEGER);
        this.team = new ScoreboardTeam(scoreboard, "gedlibrary_scoreboard_team");
    }

    public String getTitle() {
        return title;
    }

    public List<GPlayer> getPlayers() {
        return players;
    }

    public List<String> getContext() {
        return context;
    }

    public void addPlayer(GPlayer player) {
        this.players.add(player);
        this.scoreboard = new Scoreboard();
        this.objective = scoreboard.registerObjective("gedlibrary_scoreboard", IScoreboardCriteria.DUMMY, new ChatComponentText(title), IScoreboardCriteria.EnumScoreboardHealthDisplay.INTEGER);
        this.team = new ScoreboardTeam(scoreboard, "gedlibrary_scoreboard_team");
        PlayerConnection connection = ((CraftPlayer) player.getHandle()).getHandle().playerConnection;
        connection.sendPacket(new PacketPlayOutScoreboardObjective(objective, 0));
        for (int i = 0 ; i < context.size() ; i ++) {
            String text = context.get(i);
            connection.sendPacket(new PacketPlayOutScoreboardScore(ScoreboardServer.Action.CHANGE, "gedlibrary_scoreboard", text, (15 - i)));
        }
        connection.sendPacket(new PacketPlayOutScoreboardDisplayObjective(1, objective));
        connection.sendPacket(new PacketPlayOutScoreboardTeam(team, 0));
    }

    public void removePlayer(GPlayer player) {
        if (this.players.contains(player)) {
            this.players.remove(player);
            ((CraftPlayer) player.getHandle()).getHandle().playerConnection.sendPacket(new PacketPlayOutScoreboardObjective(objective, 1));
        }
    }

    public org.bukkit.scoreboard.Scoreboard getHandle() {
        org.bukkit.scoreboard.Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("gedlibrary_scoreboard", "dummy", this.title);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        for (int i = 0 ; i < context.size() ; i ++) {
            String text = context.get(i);
            Score score = objective.getScore(text);
            score.setScore(i);
        }
        return scoreboard;
    }

}
