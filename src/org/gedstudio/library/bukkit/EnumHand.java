package org.gedstudio.library.bukkit;

import org.bukkit.inventory.EquipmentSlot;

public enum EnumHand {

    MAIN_HAND(0),
    OFF_HAND(1);

    private int nmsSlot;

    EnumHand(int i) {
        this.nmsSlot = i;
    }

    /**
     * To get Bukkit hand slot
     * @return Bukkit hand slot
     */
    public EquipmentSlot toBukkit() {
        if (this.equals(OFF_HAND)) {
            return EquipmentSlot.OFF_HAND;
        }
        return EquipmentSlot.HAND;
    }

    /**
     * To get nms hand number
     * @return nms hand number
     */
    public int toNms() {
        return this.nmsSlot;
    }

}
