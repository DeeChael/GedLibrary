package org.gedstudio.library.bukkit.nms.v1_16_R2.inventory;

import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class GItem extends org.gedstudio.library.bukkit.nms.all.inventory.GItem {

    public GItem(ItemStack itemStack) {
        super(itemStack);
    }

    public org.gedstudio.library.bukkit.inventory.GItemData getOrCreateItemData() {
        org.gedstudio.library.bukkit.inventory.GItemData itemData = new GItemData();
        return this.hasItemData() ? this.getItemData() : itemData;
    }

    public boolean isFood() {
        return CraftItemStack.asNMSCopy(this.getHandle()).getItem().isFood();
    }

}
