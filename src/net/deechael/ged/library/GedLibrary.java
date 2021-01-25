package net.deechael.ged.library;

import net.deechael.ged.library.entity.GPlayer;
import net.deechael.ged.library.exception.PlayerNotExistException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.HashSet;

public final class GedLibrary extends JavaPlugin {

    private static GedLibrary instance;

    @Override
    public void onEnable() {
        instance = this;
        this.getServer().getConsoleSender().sendMessage("§aJust tell you I am enabled \n");
    }

    public static GedLibrary getInstance() {
        return instance;
    }

    public Collection<GPlayer> getOnlinePlayers() {
        Collection<GPlayer> players = new HashSet<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            try {
                players.add(new GPlayer(player));
            } catch (PlayerNotExistException e) {
                e.printStackTrace();
            }
        }
        return players;
    }

}
