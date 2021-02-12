package org.gedstudio.library.bukkit.nms.v1_13_R2.bossbar;

import net.minecraft.server.v1_13_R2.*;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BossBar;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.gedstudio.library.bukkit.GedLibrary;
import org.gedstudio.library.bukkit.bossbar.GBarColor;
import org.gedstudio.library.bukkit.bossbar.GBarStyle;
import org.gedstudio.library.bukkit.entity.GPlayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GBossBar implements org.gedstudio.library.bukkit.bossbar.GBossBar, Serializable {

    private String title;
    private int pct;
    private GBarColor color;
    private GBarStyle style;

    private boolean isFog = false;
    private boolean isPlayMusic = false;
    private boolean isDarkenSky = false;

    private transient List<GPlayer> players;
    private transient BossBattle bossBattle;

    public GBossBar(String title, GBarColor color, GBarStyle style) {
        this.title = title;
        this.color = color;
        this.style = style;
        this.pct = 100;
        this.players = new ArrayList<>();
        this.bossBattle = new BossBattleCustom(new MinecraftKey("gedlibrary", "gedbar"), new ChatMessage(title));
        this.bossBattle.color = BossBattle.BarColor.a(color.getName());
        this.bossBattle.style = BossBattle.BarStyle.valueOf(style.name());
        this.bossBattle.a((float) pct / 100);
        this.bossBattle.a(isDarkenSky).b(isPlayMusic).c(isFog);
    }

    public boolean isDarkenSky() {
        return isDarkenSky;
    }

    public boolean isFog() {
        return isFog;
    }

    public boolean isPlayMusic() {
        return isPlayMusic;
    }

    public void setDarkenSky(boolean darkenSky) {
        isDarkenSky = darkenSky;
        this.bossBattle = new BossBattleCustom(new MinecraftKey("gedlibrary", "gedbar"), new ChatMessage(title));
        this.bossBattle.color = BossBattle.BarColor.a(this.color.getName());
        this.bossBattle.style = BossBattle.BarStyle.valueOf(this.style.name());
        this.bossBattle.a((float) pct / 100);
        this.bossBattle.a(isDarkenSky).b(isPlayMusic).c(isFog);
        PacketPlayOutBoss packetPlayOutBoss = new PacketPlayOutBoss(PacketPlayOutBoss.Action.UPDATE_PROPERTIES, this.bossBattle);
        if (!(this.players.size() == 0)) {
            for (GPlayer player : this.players) {
                ((CraftPlayer) player.getHandle()).getHandle().playerConnection.sendPacket(packetPlayOutBoss);
            }
        }
    }

    public void setFog(boolean fog) {
        isFog = fog;
        this.bossBattle = new BossBattleCustom(new MinecraftKey("gedlibrary", "gedbar"), new ChatMessage(title));
        this.bossBattle.color = BossBattle.BarColor.a(this.color.getName());
        this.bossBattle.style = BossBattle.BarStyle.valueOf(this.style.name());
        this.bossBattle.a((float) pct / 100);
        this.bossBattle.a(isDarkenSky).b(isPlayMusic).c(isFog);
        PacketPlayOutBoss packetPlayOutBoss = new PacketPlayOutBoss(PacketPlayOutBoss.Action.UPDATE_PROPERTIES, this.bossBattle);
        if (!(this.players.size() == 0)) {
            for (GPlayer player : this.players) {
                ((CraftPlayer) player.getHandle()).getHandle().playerConnection.sendPacket(packetPlayOutBoss);
            }
        }
    }

    public void setPlayMusic(boolean playMusic) {
        isPlayMusic = playMusic;
        this.bossBattle = new BossBattleCustom(new MinecraftKey("gedlibrary", "gedbar"), new ChatMessage(title));
        this.bossBattle.color = BossBattle.BarColor.a(this.color.getName());
        this.bossBattle.style = BossBattle.BarStyle.valueOf(this.style.name());
        this.bossBattle.a((float) pct / 100);
        this.bossBattle.a(isDarkenSky).b(isPlayMusic).c(isFog);
        PacketPlayOutBoss packetPlayOutBoss = new PacketPlayOutBoss(PacketPlayOutBoss.Action.UPDATE_PROPERTIES, this.bossBattle);
        if (!(this.players.size() == 0)) {
            for (GPlayer player : this.players) {
                ((CraftPlayer) player.getHandle()).getHandle().playerConnection.sendPacket(packetPlayOutBoss);
            }
        }
    }

    public GBarColor getColor() {
        return color;
    }

    public void setColor(GBarColor color) {
        this.color = color;
        this.bossBattle = new BossBattleCustom(new MinecraftKey("gedlibrary", "gedbar"), new ChatMessage(title));
        this.bossBattle.color = BossBattle.BarColor.a(this.color.getName());
        this.bossBattle.style = BossBattle.BarStyle.valueOf(this.style.name());
        this.bossBattle.a((float) pct / 100);
        this.bossBattle.a(isDarkenSky).b(isPlayMusic).c(isFog);
        PacketPlayOutBoss packetPlayOutBoss = new PacketPlayOutBoss(PacketPlayOutBoss.Action.UPDATE_PROPERTIES, this.bossBattle);
        if (!(this.players.size() == 0)) {
            for (GPlayer player : this.players) {
                ((CraftPlayer) player.getHandle()).getHandle().playerConnection.sendPacket(packetPlayOutBoss);
            }
        }
    }

    public GBarStyle getStyle() {
        return this.style;
    }

    public void setStyle(GBarStyle style) {
        this.style = style;
        this.bossBattle = new BossBattleCustom(new MinecraftKey("gedlibrary", "gedbar"), new ChatMessage(title));
        this.bossBattle.color = BossBattle.BarColor.a(this.color.getName());
        this.bossBattle.style = BossBattle.BarStyle.valueOf(this.style.name());
        this.bossBattle.a((float) pct / 100);
        this.bossBattle.a(isDarkenSky).b(isPlayMusic).c(isFog);
        PacketPlayOutBoss packetPlayOutBoss = new PacketPlayOutBoss(PacketPlayOutBoss.Action.UPDATE_STYLE, this.bossBattle);
        if (!(this.players.size() == 0)) {
            for (GPlayer player : this.players) {
                ((CraftPlayer) player.getHandle()).getHandle().playerConnection.sendPacket(packetPlayOutBoss);
            }
        }
    }

    public int getPercent() {
        return pct;
    }

    public void setPercent(int pct) {
        if (pct > 100) pct = 100;
        if (pct < 0) pct = 0;
        this.pct = pct;
        this.bossBattle = new BossBattleCustom(new MinecraftKey("gedlibrary", "gedbar"), new ChatMessage(title));
        this.bossBattle.color = BossBattle.BarColor.a(this.color.getName());
        this.bossBattle.style = BossBattle.BarStyle.valueOf(this.style.name());
        this.bossBattle.a((float) pct / 100);
        this.bossBattle.a(isDarkenSky).b(isPlayMusic).c(isFog);
        PacketPlayOutBoss packetPlayOutBoss = new PacketPlayOutBoss(PacketPlayOutBoss.Action.UPDATE_PCT, this.bossBattle);
        if (!(this.players.size() == 0)) {
            for (GPlayer player : this.players) {
                ((CraftPlayer) player.getHandle()).getHandle().playerConnection.sendPacket(packetPlayOutBoss);
            }
        }
    }

    public List<GPlayer> getPlayers() {
        return players;
    }

    public void addPlayer(GPlayer player) {
        this.bossBattle = new BossBattleCustom(new MinecraftKey("gedlibrary", "gedbar"), new ChatMessage(title));
        this.bossBattle.color = BossBattle.BarColor.a(this.color.getName());
        this.bossBattle.style = BossBattle.BarStyle.valueOf(this.style.name());
        this.bossBattle.a((float) pct / 100);
        this.bossBattle.a(isDarkenSky).b(isPlayMusic).c(isFog);

        if (!this.players.contains(player)) {
            this.players.add(player);
            PacketPlayOutBoss packetPlayOutBoss = new PacketPlayOutBoss(PacketPlayOutBoss.Action.ADD, this.bossBattle);
            ((CraftPlayer) player.getHandle()).getHandle().playerConnection.sendPacket(packetPlayOutBoss);
        }
    }

    public void removePlayer(GPlayer player) {
        this.bossBattle = new BossBattleCustom(new MinecraftKey("gedlibrary", "gedbar"), new ChatMessage(title));
        this.bossBattle.color = BossBattle.BarColor.a(this.color.getName());
        this.bossBattle.style = BossBattle.BarStyle.valueOf(this.style.name());
        this.bossBattle.a((float) pct / 100);
        this.bossBattle.a(isDarkenSky).b(isPlayMusic).c(isFog);

        if (this.players.contains(player)) {
            this.players.remove(player);
            PacketPlayOutBoss packetPlayOutBoss = new PacketPlayOutBoss(PacketPlayOutBoss.Action.REMOVE, this.bossBattle);
            ((CraftPlayer) player.getHandle()).getHandle().playerConnection.sendPacket(packetPlayOutBoss);
        }
    }

    public void removeAll() {
        for (GPlayer player : players) {
            this.removePlayer(player);
        }
    }

    public void addAllOnlinePlayer() {
        for (GPlayer player : GedLibrary.getInstance().getOnlinePlayers()) {
            this.addPlayer(player);
        }
    }

    public BossBar getHandle() {
        BossBar bossBar = Bukkit.createBossBar(this.title, BarColor.valueOf(this.color.name()), this.style.getBukkitStyle());
        if (this.isDarkenSky) bossBar.addFlag(BarFlag.DARKEN_SKY);
        if (this.isFog) bossBar.addFlag(BarFlag.CREATE_FOG);
        if (this.isPlayMusic) bossBar.addFlag(BarFlag.PLAY_BOSS_MUSIC);
        return bossBar;
    }

}
