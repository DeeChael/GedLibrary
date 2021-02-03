package org.gedstudio.library.bukkit;

import org.bukkit.inventory.EquipmentSlot;

public enum EnumHand {

    MAIN_HAND(0),
    OFF_HAND(1);

    private int nmsSlot;

    EnumHand(int i) {
        this.nmsSlot = i;
    }

    public EquipmentSlot toBukkit() {
        if (this.equals(OFF_HAND)) {
            return EquipmentSlot.OFF_HAND;
        }
        return EquipmentSlot.HAND;
    }

    public int toNms() {
        return this.nmsSlot;
    }

}
