package org.gedstudio.library.bukkit.potion;

import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.io.Serializable;

public class GPotionData implements Serializable {

    private final PotionType type;
    private final boolean extended;
    private final boolean upgraded;

    /**
     * For GItem can be serializable
     * @param type type
     * @param extended extended
     * @param upgraded upgraded
     */
    public GPotionData(PotionType type, boolean extended, boolean upgraded) {
        this.type = type;
        this.extended = extended;
        this.upgraded = upgraded;
    }

    /**
     * To cast to Bukkit PotionData
     * @return Bukkit PotionData
     */
    public PotionData getHandle() {
        return new PotionData(type, extended, upgraded);
    }

    /**
     * To get GPotionData From Bukkit PotionData
     * @param potionData Bukkit PotionData
     * @return GPotionData
     */
    public static GPotionData fromPotionData(PotionData potionData) {
        return new GPotionData(potionData.getType(), potionData.isExtended(), potionData.isUpgraded());
    }

}
