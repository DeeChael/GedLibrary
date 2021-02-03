package org.gedstudio.library.bukkit.command;

import org.bukkit.entity.Player;
import org.gedstudio.library.bukkit.GedLibrary;
import org.gedstudio.library.bukkit.chat.GText;
import org.bukkit.command.CommandSender;

import java.util.List;

public class GConsoleSender implements GSender {

    private CommandSender commandSender;

    /**
     * To create new console sender
     * @param commandSender command sender
     */
    public GConsoleSender(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    /**
     * To check if the command sender is player
     * @return if command is player will be true else will be false
     */
    @Override
    public boolean isPlayer() {
        return this.commandSender instanceof Player;
    }

    /**
     * To get the name of the command sender
     * @return the name of the command sender
     */
    @Override
    public String getName() {
        return this.commandSender.getName();
    }

    /**
     * To print finest message
     * @param gText message
     */
    public void finest(GText gText) {
        GedLibrary.getInstance().getLogger().finest(gText.getText());
    }

    /**
     * To print finer message
     * @param gText message
     */
    public void finer(GText gText) {
        GedLibrary.getInstance().getLogger().finer(gText.getText());
    }

    /**
     * To print fine message
     * @param gText message
     */
    public void fine(GText gText) {
        GedLibrary.getInstance().getLogger().fine(gText.getText());
    }

    /**
     * To print config message
     * @param gText message
     */
    public void config(GText gText) {
        GedLibrary.getInstance().getLogger().config(gText.getText());
    }

    /**
     * To print info message
     * @param gText message
     */
    public void info(GText gText) {
        GedLibrary.getInstance().getLogger().info(gText.getText());
    }

    /**
     * To print warn message
     * @param gText message
     */
    public void warn(GText gText) {
        GedLibrary.getInstance().getLogger().warning(gText.getText());
    }

    /**
     * To print severe message
     * @param gText message
     */
    public void severe(GText gText) {
        GedLibrary.getInstance().getLogger().severe(gText.getText());
    }

    /**
     * To print message without [GedLibrary] prefix
     * @param gText message
     */
    public void msg(GText gText) {
        commandSender.sendMessage(gText.getText());
    }

    /**
     * To print the logo of GeD
     */
    public void printGedLogo() {
        commandSender.sendMessage("§6          _____                    _____                    _____          ");
        commandSender.sendMessage("§6         /\\    \\                  /\\    \\                  /\\    \\         ");
        commandSender.sendMessage("§6        /::\\    \\                /::\\    \\                /::\\    \\        ");
        commandSender.sendMessage("§6       /::::\\    \\              /::::\\    \\              /::::\\    \\       ");
        commandSender.sendMessage("§6      /::::::\\    \\            /::::::\\    \\            /::::::\\    \\      ");
        commandSender.sendMessage("§6     /:::/\\:::\\    \\          /:::/\\:::\\    \\          /:::/\\:::\\    \\     ");
        commandSender.sendMessage("§6    /:::/  \\:::\\    \\        /:::/__\\:::\\    \\        /:::/  \\:::\\    \\    ");
        commandSender.sendMessage("§6   /:::/    \\:::\\    \\      /::::\\   \\:::\\    \\      /:::/    \\:::\\    \\   ");
        commandSender.sendMessage("§6  /:::/    / \\:::\\    \\    /::::::\\   \\:::\\    \\    /:::/    / \\:::\\    \\  ");
        commandSender.sendMessage("§6 /:::/    /   \\:::\\ ___\\  /:::/\\:::\\   \\:::\\    \\  /:::/    /   \\:::\\ ___\\ ");
        commandSender.sendMessage("§6/:::/____/  ___\\:::|    |/:::/__\\:::\\   \\:::\\____\\/:::/____/     \\:::|    |");
        commandSender.sendMessage("§6\\:::\\    \\ /\\  /:::|____|\\:::\\   \\:::\\   \\::/    /\\:::\\    \\     /:::|____|");
        commandSender.sendMessage("§6 \\:::\\    /::\\ \\::/    /  \\:::\\   \\:::\\   \\/____/  \\:::\\    \\   /:::/    / ");
        commandSender.sendMessage("§6  \\:::\\   \\:::\\ \\/____/    \\:::\\   \\:::\\    \\       \\:::\\    \\ /:::/    /  ");
        commandSender.sendMessage("§6   \\:::\\   \\:::\\____\\       \\:::\\   \\:::\\____\\       \\:::\\    /:::/    /   ");
        commandSender.sendMessage("§6    \\:::\\  /:::/    /        \\:::\\   \\::/    /        \\:::\\  /:::/    /    ");
        commandSender.sendMessage("§6     \\:::\\/:::/    /          \\:::\\   \\/____/          \\:::\\/:::/    /     ");
        commandSender.sendMessage("§6      \\::::::/    /            \\:::\\    \\               \\::::::/    /      ");
        commandSender.sendMessage("§6       \\::::/    /              \\:::\\____\\               \\::::/    /       ");
        commandSender.sendMessage("§6        \\__/____/                \\::/    /                \\__/____/        ");
        commandSender.sendMessage("§6                                  \\/____/                                ");
        commandSender.sendMessage("§6                                                                           ");
    }

    /**
     * To send message to command sender
     * @param gText message
     */
    @Override
    public void sendMessage(GText gText) {
        this.msg(gText);
    }

    /**
     * To send messages to command sender
     * @param gTexts messages
     */
    @Override
    public void sendMessage(GText... gTexts) {
        for (GText gText : gTexts) {
            this.sendMessage(gText);
        }
    }

    /**
     * To send messages to command sender
     * @param gTexts messages
     */
    @Override
    public void sendMessage(List<GText> gTexts) {
        for (GText gText : gTexts) {
            this.sendMessage(gText);
        }
    }

    /**
     * To get Bukkit CommandSender
     * @return bukkit command sender
     */
    public CommandSender getHandle() {
        return commandSender;
    }

}
