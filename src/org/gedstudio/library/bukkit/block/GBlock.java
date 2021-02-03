package org.gedstudio.library.bukkit.block;

import org.bukkit.Material;
import org.gedstudio.library.bukkit.entity.GPlayer;

public interface GBlock {

    /**
     * To get the type of the block
     * @return the type of the block
     */
    Material getType();

    /**
     * To send block break animation to a player
     * <p>Min block break level is 0 and max is 9</p>
     * @param player player to send block break animation
     * @param level block break level
     */
    void sendBreakAnimation(GPlayer player, int level);

    /**
     * To make the block glow like a entity
     * @param glow isGlow
     */
    void setGlow(boolean glow);

}
