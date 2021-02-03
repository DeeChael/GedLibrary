package org.gedstudio.library.bukkit.command;

import org.bukkit.command.CommandSender;
import org.gedstudio.library.bukkit.chat.GText;

import java.util.List;

public interface GSender {

    /**
     * To check if the command sender is player
     * @return if command is player will be true else will be false
     */
    boolean isPlayer();

    /**
     * To get the name of the command sender
     * @return the name of the command sender
     */
    String getName();

    /**
     * To send message to command sender
     * @param gText message
     */
    void sendMessage(GText gText);

    /**
     * To send messages to command sender
     * @param gTexts messages
     */
    void sendMessage(GText... gTexts);

    /**
     * To send messages to command sender
     * @param gTexts messages
     */
    void sendMessage(List<GText> gTexts);

    /**
     * To get Bukkit CommandSender
     * @return bukkit command sender
     */
    CommandSender getHandle();

}
