package org.gedstudio.library.bukkit.nms.v1_13_R2.inventory;

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
        switch (this.getType()) {
            case BREAD:
            case POTATO:
            case CARROT:
            case BEETROOT:
            case BEEF:
            case PORKCHOP:
            case CHICKEN:
            case COD:
            case SALMON:
            case MUTTON:
            case RABBIT:
            case TROPICAL_FISH:
            case PUFFERFISH:
            case MUSHROOM_STEW:
            case RABBIT_STEW:
            case BEETROOT_SOUP:
            case COOKED_BEEF:
            case COOKED_PORKCHOP:
            case COOKED_CHICKEN:
            case COOKED_SALMON:
            case COOKED_MUTTON:
            case COOKED_COD:
            case WHEAT:
            case MELON:
            case PUMPKIN:
            case BROWN_MUSHROOM:
            case RED_MUSHROOM:
            case NETHER_WART:
            case MELON_SLICE:
            case CAKE:
            case PUMPKIN_PIE:
            case SUGAR:
            case EGG:
            case SUGAR_CANE:
            case APPLE:
            case COOKIE:
            case POISONOUS_POTATO:
            case CHORUS_FRUIT:
            case DRIED_KELP:
            case BAKED_POTATO:
                return true;
        }
        return false;
    }

}
