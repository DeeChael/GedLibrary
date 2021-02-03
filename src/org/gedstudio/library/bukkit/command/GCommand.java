package org.gedstudio.library.bukkit.command;

import org.gedstudio.library.bukkit.GedLibrary;
import org.gedstudio.library.bukkit.entity.GPlayer;
import org.gedstudio.library.bukkit.exception.PlayerNotExistException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import java.util.Arrays;
import java.util.List;

public abstract class GCommand extends Command {

    /**
     * To create new command
     * @param name the name of the command
     * @param permission the permission of the command
     * @param permissionDefault the default permission owner of the permission of the command
     * @param aliases the aliases of the command
     */
    public GCommand(String name, String permission, PermissionDefault permissionDefault, String... aliases) {
        super(name, name + " command", "/" + name, Arrays.asList(aliases));
        new Permission(permission, permissionDefault);
    }

    @Override
    public List<String> tabComplete(CommandSender s, String Label, String[] args) {
        if (s instanceof Player) {
            return this.tab(GedLibrary.getInstance().getPlayer(s.getName()), args);
        } else {
            return this.tab(new GConsoleSender(s), args);
        }
    }

    /**
     * The tab completer
     * @param s the sender of the command
     * @param args the arguments of the command
     * @return tab complete texts list
     */
    public abstract List<String> tab(GSender s, String[] args);

    @Override
    public boolean execute(CommandSender s, String Label, String[] args) {
        if (s instanceof Player) {
            this.command(GedLibrary.getInstance().getPlayer(s.getName()), args);
        } else {
            this.command(new GConsoleSender(s), args);
        }
        return true;
    }

    /**
     * The command executor
     * @param s the sender of the command
     * @param args the arguments of the command
     */
    public abstract void command(GSender s, String[] args);

    /**
     * To register command
     * <p>prefix example: "/prefix:name [argument]"</p>
     * @param prefix the prefix of the command
     */
    public void register(String prefix) {
        GedLibrary.getInstance().getCommandMap().register(prefix, this);
    }

}
