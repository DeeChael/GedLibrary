package org.gedstudio.library.bukkit.command.defaults;

import org.bukkit.permissions.PermissionDefault;
import org.gedstudio.library.bukkit.chat.GText;
import org.gedstudio.library.bukkit.command.GCommand;
import org.gedstudio.library.bukkit.command.GSender;

import java.util.ArrayList;
import java.util.List;

public class GedLibraryCommand extends GCommand {


    public GedLibraryCommand() {
        super("gedlibrary", "gedlibrary.command.gedlibrary", PermissionDefault.OP, "gedlib");
    }

    @Override
    public List<String> tab(GSender s, String[] args) {
        List<String> list = new ArrayList<>();

        return list;
    }

    @Override
    public void command(GSender s, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                GText message = new GText("&#7fffaaReloaded GedLibrary Successfully");
                message.translate("&");
                s.sendMessage(message);
            } else if (args[0].equalsIgnoreCase("info")) {
                GText spec = new GText("&#6495ed====================================");
                GText line1 = new GText("&#7fffaaGedLibrary - GeD Minecraft Spigot Development Library");
                GText line2 = new GText("&#7fffaaAuthors: GeD-Studio, DeeChael");
                spec.translate("&");
                line1.translate("&");
                line2.translate("&");
                s.sendMessage(spec, line1, line2, spec);
            } else {
                GText message = new GText("&#ffc0cbUnknown usage");
                message.translate("&");
                s.sendMessage(message);
            }
        } else {
            GText message = new GText("&#ffc0cbUnknown usage");
            message.translate("&");
            s.sendMessage(message);
        }
    }

}
