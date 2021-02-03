package org.gedstudio.library.bukkit.inventory;

import org.bukkit.inventory.Inventory;

public interface GInventory {

    /**
     * To cast to bukkit inventory
     * @return bukkit inventory
     */
    Inventory getHandle();

    /**
     * To set the item in slot
     * @param slot slot
     * @param item item
     */
    void setItem(int slot, GItem item);

    /**
     * To add items into the inventory
     * @param items items
     */
    void addItem(GItem... items);

    /**
     * To get the item in slot
     * @param slot slot
     * @return item
     */
    GItem getItem(int slot);

    /**
     * To check if there are any items in this inventory
     * @return boolean
     */
    boolean hasItem();

    /**
     * To check if there is a item in the slot
     * @param slot slot
     * @return boolean
     */
    boolean hasItem(int slot);

}
