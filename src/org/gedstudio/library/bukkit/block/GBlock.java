package org.gedstudio.library.bukkit.block;

import org.bukkit.Material;
import org.gedstudio.library.bukkit.entity.GPlayer;

public interface GBlock {

    Material getType();

    void sendBreakAnimation(GPlayer player, int level);

    void setGlow(boolean glow);

}
