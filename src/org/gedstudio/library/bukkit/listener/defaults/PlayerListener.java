package org.gedstudio.library.bukkit.listener.defaults;

import org.gedstudio.library.bukkit.GedLibrary;
import org.gedstudio.library.bukkit.color.HexColor;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public final class PlayerListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        String message = e.getMessage();
        for (char c : ChatColor.ALL_CODES.toCharArray()) {
            message = message.replace("&" + c, "§" + c);
        }
        if (GedLibrary.getInstance().getNmsVersion().equalsIgnoreCase("v1_16_R1") || GedLibrary.getInstance().getNmsVersion().equalsIgnoreCase("v1_16_R2") || GedLibrary.getInstance().getNmsVersion().equalsIgnoreCase("v1_16_R3")) {
            if (message.contains("&#")) {
                String[] strings = message.split("&#");
                for (int i = 1; i < strings.length; i ++) {
                    String string = strings[i];
                    String msg = string;
                    if ((msg.length() >= 6)) {
                        message = message.replace("&#" + msg.substring(0, 6), new HexColor(msg.substring(0, 6)).toBungee().toString());
                    }
                }
            }
        }
        e.setMessage(message);
    }

}
