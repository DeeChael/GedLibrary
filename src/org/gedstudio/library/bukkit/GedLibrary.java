package org.gedstudio.library.bukkit;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

import org.gedstudio.library.bukkit.bossbar.GBarColor;
import org.gedstudio.library.bukkit.bossbar.GBarStyle;
import org.gedstudio.library.bukkit.bossbar.GBossBar;
import org.gedstudio.library.bukkit.chat.GText;
import org.gedstudio.library.bukkit.command.GCommand;
import org.gedstudio.library.bukkit.command.GConsoleSender;
import org.gedstudio.library.bukkit.command.defaults.GedLibraryCommand;
import org.gedstudio.library.bukkit.entity.GPlayer;
import org.gedstudio.library.bukkit.exception.PlayerNotExistException;
import org.gedstudio.library.bukkit.inventory.GAnvil;
import org.gedstudio.library.bukkit.inventory.GItem;
import org.gedstudio.library.bukkit.inventory.GItemData;
import org.gedstudio.library.bukkit.listener.defaults.PlayerListener;
import org.gedstudio.library.bukkit.scoreboard.GScoreBoard;
import org.gedstudio.library.bukkit.skin.Skin;
import org.gedstudio.library.bukkit.special.Author;
import org.gedstudio.library.bukkit.special.DeeChael;

import org.bukkit.entity.Mob;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * GedLibrary project started on Dec 21, 2020
 * @author DeeChael
 */
public final class GedLibrary extends JavaPlugin {

    private static GedLibrary instance;
    private CommandMap commandMap;
    private Map<String, Skin> skinCache;
    private Map<GLocation, Mob> glowingBlocks;
    private Author author;
    private List<GCommand> gedLibraryCommands;

    @Override
    public void onEnable() {
        instance = this;
        if (!(this.getNmsVersion().equalsIgnoreCase("v1_13_R1") ||
                this.getNmsVersion().equalsIgnoreCase("v1_13_R2") ||
                this.getNmsVersion().equalsIgnoreCase("v1_14_R1") ||
                this.getNmsVersion().equalsIgnoreCase("v1_15_R1") ||
                this.getNmsVersion().equalsIgnoreCase("v1_16_R1") ||
                this.getNmsVersion().equalsIgnoreCase("v1_16_R2") ||
                this.getNmsVersion().equalsIgnoreCase("v1_16_R3"))
        ) {
            this.getConsoleSender().severe(new GText("GedLibrary unsupport server version"));
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        skinCache = new HashMap<>();
        glowingBlocks = new HashMap<>();
        author = new DeeChael();
        gedLibraryCommands = new ArrayList<>();
        final Class<?> craftServer = Bukkit.getServer().getClass();
        for (final Method method : craftServer.getDeclaredMethods()) {
            if (method.getName().equals("getCommandMap")) {
                try {
                    commandMap = (CommandMap) method.invoke(Bukkit.getServer(), new Object[0]);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        GedLibraryCommand gedLibraryCommand = new GedLibraryCommand();
        gedLibraryCommand.register("gedlibrary");
        this.gedLibraryCommands.add(gedLibraryCommand);
    }

    /**
     * To get GedLibrary field
     * @return GedLibrary field
     */
    public static GedLibrary getInstance() {
        return instance;
    }

    /**
     * To get glowing blocks
     * <p>This is for GedLibrary not for developers</p>
     * @return glowing blocks location and shulkers
     */
    public Map<GLocation, Mob> getGlowingBlocks() {
        return glowingBlocks;
    }

    /**
     * To get GedLibrary's author
     * @return DeeChael
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * To get server default CommandMap
     * @return Server CommandMap
     */
    public CommandMap getCommandMap() {
        return commandMap;
    }

    /**
     * To get GedLibrary's skin cache
     * <p>Skin cache will be cleaned when server close</p>
     * @return skin cache
     */
    public Map<String, Skin> getSkinCache() {
        return this.skinCache;
    }

    /**
     * To get console sender
     * @return console sender
     */
    public GConsoleSender getConsoleSender() {
        return new GConsoleSender(Bukkit.getServer().getConsoleSender());
    }

    /**
     * To set Server Motd
     * @param motd Server motd
     */
    public void setMotd(String motd) {
        if (this.getNmsVersion().equalsIgnoreCase("v1_13_R1")) {
            net.minecraft.server.v1_13_R1.MinecraftServer.getServer().setMotd(motd);
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_13_R2")) {
            net.minecraft.server.v1_13_R2.MinecraftServer.getServer().setMotd(motd);
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_14_R1")) {
            net.minecraft.server.v1_14_R1.MinecraftServer.getServer().setMotd(motd);
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_15_R1")) {
            net.minecraft.server.v1_15_R1.MinecraftServer.getServer().setMotd(motd);
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_16_R1")) {
            net.minecraft.server.v1_16_R1.MinecraftServer.getServer().setMotd(motd);
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_16_R2")) {
            net.minecraft.server.v1_16_R2.MinecraftServer.getServer().setMotd(motd);
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_16_R3")) {
            net.minecraft.server.v1_16_R3.MinecraftServer.getServer().setMotd(motd);
        }
    }

    /**
     * To set Server Icon
     * @param file icon file
     */
    public void setIcon(File file) {
        if (!file.exists()) return;
        if (!file.isFile()) return;
        if (!file.getName().endsWith(".png")) return;
        ByteBuf bytebuf = Unpooled.buffer();
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            if (!(bufferedImage.getHeight() == 64 && bufferedImage.getWidth() == 64)) return;
            ImageIO.write(bufferedImage, "PNG", new ByteBufOutputStream(bytebuf));
            ByteBuffer bytebuffer = Base64.getEncoder().encode(bytebuf.nioBuffer());
            if (this.getNmsVersion().equalsIgnoreCase("v1_13_R1")) {
                net.minecraft.server.v1_13_R1.MinecraftServer.getServer().getServerPing().setFavicon("data:image/png;base64," + StandardCharsets.UTF_8.decode(bytebuffer));
            } else if (this.getNmsVersion().equalsIgnoreCase("v1_13_R2")) {
                net.minecraft.server.v1_13_R2.MinecraftServer.getServer().getServerPing().setFavicon("data:image/png;base64," + StandardCharsets.UTF_8.decode(bytebuffer));
            } else if (this.getNmsVersion().equalsIgnoreCase("v1_14_R1")) {
                net.minecraft.server.v1_14_R1.MinecraftServer.getServer().getServerPing().setFavicon("data:image/png;base64," + StandardCharsets.UTF_8.decode(bytebuffer));
            } else if (this.getNmsVersion().equalsIgnoreCase("v1_15_R1")) {
                net.minecraft.server.v1_15_R1.MinecraftServer.getServer().getServerPing().setFavicon("data:image/png;base64," + StandardCharsets.UTF_8.decode(bytebuffer));
            } else if (this.getNmsVersion().equalsIgnoreCase("v1_16_R1")) {
                net.minecraft.server.v1_16_R1.MinecraftServer.getServer().getServerPing().setFavicon("data:image/png;base64," + StandardCharsets.UTF_8.decode(bytebuffer));
            } else if (this.getNmsVersion().equalsIgnoreCase("v1_16_R2")) {
                net.minecraft.server.v1_16_R2.MinecraftServer.getServer().getServerPing().setFavicon("data:image/png;base64," + StandardCharsets.UTF_8.decode(bytebuffer));
            } else if (this.getNmsVersion().equalsIgnoreCase("v1_16_R3")) {
                net.minecraft.server.v1_16_R3.MinecraftServer.getServer().getServerPing().setFavicon("data:image/png;base64," + StandardCharsets.UTF_8.decode(bytebuffer));
            }
        } catch (IOException ignored) {
        } finally {
            bytebuf.release();
        }
    }

    /**
     * To get server tps
     * @return server tps
     */
    public double getTps(TpsTime time) {
        int type = 0;
        switch (time) {
            case ONE_MINUTE:
                type = 0;
            case FIVE_MINUTES:
                type = 1;
            case FIFTEEN_MINUTES:
                type = 2;
        }
        double tps = 23.3;
        if (this.getNmsVersion().equalsIgnoreCase("v1_13_R1")) {
            tps = BigDecimal.valueOf(net.minecraft.server.v1_13_R1.MinecraftServer.getServer().recentTps[type]).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_13_R2")) {
            tps = BigDecimal.valueOf(net.minecraft.server.v1_13_R2.MinecraftServer.getServer().recentTps[type]).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_14_R1")) {
            tps = BigDecimal.valueOf(net.minecraft.server.v1_14_R1.MinecraftServer.getServer().recentTps[type]).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_15_R1")) {
            tps = BigDecimal.valueOf(net.minecraft.server.v1_15_R1.MinecraftServer.getServer().recentTps[type]).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_16_R1")) {
            tps = BigDecimal.valueOf(net.minecraft.server.v1_16_R1.MinecraftServer.getServer().recentTps[type]).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_16_R2")) {
            tps = BigDecimal.valueOf(net.minecraft.server.v1_16_R2.MinecraftServer.getServer().recentTps[type]).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_16_R3")) {
            tps = BigDecimal.valueOf(net.minecraft.server.v1_16_R3.MinecraftServer.getServer().recentTps[type]).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        tps = Math.min(tps, 20.0);
        tps = Math.max(tps, 0.0);
        return tps;
    }

    /**
     * To get GItem from Bukkit ItemStack
     * @param itemStack Bukkit ItemStack
     * @return GItem
     */
    public GItem getItem(ItemStack itemStack) {
        if (this.getNmsVersion().equalsIgnoreCase("v1_13_R1")) {
            return new org.gedstudio.library.bukkit.nms.v1_13_R1.inventory.GItem(itemStack);
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_13_R2")) {
            return new org.gedstudio.library.bukkit.nms.v1_13_R2.inventory.GItem(itemStack);
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_14_R1")) {
            return new org.gedstudio.library.bukkit.nms.v1_14_R1.inventory.GItem(itemStack);
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_15_R1")) {
            return new org.gedstudio.library.bukkit.nms.v1_15_R1.inventory.GItem(itemStack);
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_16_R1")) {
            return new org.gedstudio.library.bukkit.nms.v1_16_R1.inventory.GItem(itemStack);
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_16_R2")) {
            return new org.gedstudio.library.bukkit.nms.v1_16_R2.inventory.GItem(itemStack);
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_16_R3")) {
            return new org.gedstudio.library.bukkit.nms.v1_16_R3.inventory.GItem(itemStack);
        }
        return new org.gedstudio.library.bukkit.nms.all.inventory.GItem(itemStack) {
            @Override
            public GItemData getOrCreateItemData() {
                return null;
            }

            @Override
            public boolean isFood() {
                return false;
            }
        };
    }

    /**
     * To create a new anvil container
     * @param title The title of the anvil container
     * @param player The player will be shown the anvil container
     * @return created anvil container
     */
    public GAnvil createNewAnvil(String title, GPlayer player) {
        if (this.getNmsVersion().equalsIgnoreCase("v1_13_R1")) {
            return new org.gedstudio.library.bukkit.nms.v1_13_R1.inventory.GAnvil(title, player);
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_13_R2")) {
            return new org.gedstudio.library.bukkit.nms.v1_13_R2.inventory.GAnvil(title, player);
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_14_R1")) {
            return new org.gedstudio.library.bukkit.nms.v1_14_R1.inventory.GAnvil(title, player);
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_15_R1")) {
            return new org.gedstudio.library.bukkit.nms.v1_15_R1.inventory.GAnvil(title, player);
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_16_R1")) {
            return new org.gedstudio.library.bukkit.nms.v1_16_R1.inventory.GAnvil(title, player);
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_16_R2")) {
            return new org.gedstudio.library.bukkit.nms.v1_16_R2.inventory.GAnvil(title, player);
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_16_R3")) {
            return new org.gedstudio.library.bukkit.nms.v1_16_R3.inventory.GAnvil(title, player);
        } else {
            return null;
        }
    }

    /**
     * To get a GPlayer from Player
     * @param player Bukkit Player field
     * @return GPlayer field
     */
    public GPlayer getPlayer(Player player) {
        try {
            if (this.getNmsVersion().equalsIgnoreCase("v1_13_R1")) {
                return new org.gedstudio.library.bukkit.nms.v1_13_R1.entity.GPlayer(player);
            } else if (this.getNmsVersion().equalsIgnoreCase("v1_13_R2")) {
                return new org.gedstudio.library.bukkit.nms.v1_13_R2.entity.GPlayer(player);
            } else if (this.getNmsVersion().equalsIgnoreCase("v1_14_R1")) {
                return new org.gedstudio.library.bukkit.nms.v1_14_R1.entity.GPlayer(player);
            } else if (this.getNmsVersion().equalsIgnoreCase("v1_15_R1")) {
                return new org.gedstudio.library.bukkit.nms.v1_15_R1.entity.GPlayer(player);
            } else if (this.getNmsVersion().equalsIgnoreCase("v1_16_R1")) {
                return new org.gedstudio.library.bukkit.nms.v1_16_R1.entity.GPlayer(player);
            } else if (this.getNmsVersion().equalsIgnoreCase("v1_16_R2")) {
                return new org.gedstudio.library.bukkit.nms.v1_16_R2.entity.GPlayer(player);
            } else if (this.getNmsVersion().equalsIgnoreCase("v1_16_R3")) {
                return new org.gedstudio.library.bukkit.nms.v1_16_R3.entity.GPlayer(player);
            } else {
                return null;
            }
        } catch (PlayerNotExistException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * To get a GPlayer from the name of the player
     * @param name the name of the player
     * @return GPlayer field
     */
    public GPlayer getPlayer(String name) {
        try {
            if (this.getNmsVersion().equalsIgnoreCase("v1_13_R1")) {
                return new org.gedstudio.library.bukkit.nms.v1_13_R1.entity.GPlayer(name);
            } else if (this.getNmsVersion().equalsIgnoreCase("v1_13_R2")) {
                return new org.gedstudio.library.bukkit.nms.v1_13_R2.entity.GPlayer(name);
            } else if (this.getNmsVersion().equalsIgnoreCase("v1_14_R1")) {
                return new org.gedstudio.library.bukkit.nms.v1_14_R1.entity.GPlayer(name);
            } else if (this.getNmsVersion().equalsIgnoreCase("v1_15_R1")) {
                return new org.gedstudio.library.bukkit.nms.v1_15_R1.entity.GPlayer(name);
            } else if (this.getNmsVersion().equalsIgnoreCase("v1_16_R1")) {
                return new org.gedstudio.library.bukkit.nms.v1_16_R1.entity.GPlayer(name);
            } else if (this.getNmsVersion().equalsIgnoreCase("v1_16_R2")) {
                return new org.gedstudio.library.bukkit.nms.v1_16_R2.entity.GPlayer(name);
            } else if (this.getNmsVersion().equalsIgnoreCase("v1_16_R3")) {
                return new org.gedstudio.library.bukkit.nms.v1_16_R3.entity.GPlayer(name);
            } else {
                return null;
            }
        } catch (PlayerNotExistException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * To create a new boss bar
     * @param title the title of the boss bar
     * @param color the color of the boss bar
     * @param style the style of the boss bar
     * @return boss bar
     */
    public GBossBar createNewBossBar(String title, GBarColor color, GBarStyle style) {
        if (this.getNmsVersion().equalsIgnoreCase("v1_13_R1")) {
            return new org.gedstudio.library.bukkit.nms.v1_13_R1.bossbar.GBossBar(title, color, style);
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_13_R2")) {
            return new org.gedstudio.library.bukkit.nms.v1_13_R2.bossbar.GBossBar(title, color, style);
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_14_R1")) {
            return new org.gedstudio.library.bukkit.nms.v1_14_R1.bossbar.GBossBar(title, color, style);
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_15_R1")) {
            return new org.gedstudio.library.bukkit.nms.v1_15_R1.bossbar.GBossBar(title, color, style);
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_16_R1")) {
            return new org.gedstudio.library.bukkit.nms.v1_16_R1.bossbar.GBossBar(title, color, style);
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_16_R2")) {
            return new org.gedstudio.library.bukkit.nms.v1_16_R2.bossbar.GBossBar(title, color, style);
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_16_R3")) {
            return new org.gedstudio.library.bukkit.nms.v1_16_R3.bossbar.GBossBar(title, color, style);
        } else {
            return null;
        }
    }

    /**
     * To create a new GScoreboard
     * @param title The title of the scoreboard
     * @param context The context of the scoreboard
     * @return a GScoreboard field
     */
    public GScoreBoard createNewScoreboard(String title, List<String> context) {
        String[] contextArray = context.toArray(new String[context.size()]);
        return this.createNewScoreboard(title, contextArray);
    }

    /**
     * To create a new GScoreboard
     * @param title The title of the scoreboard
     * @param context The context of the scoreboard
     * @return a GScoreboard field
     */
    public GScoreBoard createNewScoreboard(String title, String... context) {
        if (this.getNmsVersion().equalsIgnoreCase("v1_13_R1")) {
            return new org.gedstudio.library.bukkit.nms.v1_13_R1.scoreboard.GScoreBoard(title, context);
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_13_R2")) {
            return new org.gedstudio.library.bukkit.nms.v1_13_R2.scoreboard.GScoreBoard(title, context);
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_14_R1")) {
            return new org.gedstudio.library.bukkit.nms.v1_14_R1.scoreboard.GScoreBoard(title, context);
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_15_R1")) {
            return new org.gedstudio.library.bukkit.nms.v1_15_R1.scoreboard.GScoreBoard(title, context);
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_16_R1")) {
            return new org.gedstudio.library.bukkit.nms.v1_16_R1.scoreboard.GScoreBoard(title, context);
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_16_R2")) {
            return new org.gedstudio.library.bukkit.nms.v1_16_R2.scoreboard.GScoreBoard(title, context);
        } else if (this.getNmsVersion().equalsIgnoreCase("v1_16_R3")) {
            return new org.gedstudio.library.bukkit.nms.v1_16_R3.scoreboard.GScoreBoard(title, context);
        } else {
            return null;
        }
    }

    /**
     * To get online players
     * @return GPlayer fields collection
     */
    public Collection<GPlayer> getOnlinePlayers() {
        Collection<GPlayer> players = new HashSet<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            players.add(this.getPlayer(player.getName()));
        }
        return players;
    }

    /**
     * To get server nms version
     * <p>Nms version format: v1_xx_Rx</p>
     * @return Nms Version String
     */
    public String getNmsVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().substring(Bukkit.getServer().getClass().getPackage().getName().lastIndexOf('.') + 1);
    }

}
