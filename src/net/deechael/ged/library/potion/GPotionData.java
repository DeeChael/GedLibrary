package net.deechael.ged.library.potion;

import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.io.Serializable;

public class GPotionData implements Serializable {

    private final PotionType type;
    private final boolean extended;
    private final boolean upgraded;

    public GPotionData(PotionType type, boolean extended, boolean upgraded) {
        this.type = type;
        this.extended = extended;
        this.upgraded = upgraded;
    }

    public PotionData getHandle() {
        return new PotionData(type, extended, upgraded);
    }

    public static GPotionData fromPotionData(PotionData potionData) {
        return new GPotionData(potionData.getType(), potionData.isExtended(), potionData.isUpgraded());
    }

}
