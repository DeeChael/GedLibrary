package org.gedstudio.library.bukkit.nms.all;

import org.gedstudio.library.bukkit.GedLibrary;
import org.gedstudio.library.bukkit.inventory.GItem;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.Serializable;

import java.util.HashMap;

public class GInventory implements org.gedstudio.library.bukkit.inventory.GInventory, Serializable {

    private transient Inventory inventory;
    private String title = null;
    private int size = 9;
    private int realSize = 0;
    private HashMap<Integer, GItem> items;

    public GInventory(Inventory inventory, String title) {
        this.inventory = inventory;
        this.title = title;
        this.size = (inventory.getSize() % 9) == 0 ? inventory.getSize() : (inventory.getSize() / 9) * 9;
        this.realSize = inventory.getSize();
        this.items = new HashMap<>();
        if (!inventory.isEmpty()) {
            for (int i = 0; i < this.size; i++) {
                if (this.inventory.getItem(i) != null) {
                    this.items.put(i, new GItem(this.inventory.getItem(i)));
                }
            }
        }
    }

    public GInventory(String title, int size) {
        this.inventory = Bukkit.createInventory(null, size, title);
        this.title = title;
        this.size = (size % 9) == 0 ? size : (size / 9) * 9;
        this.realSize = size;
        this.items = new HashMap<>();
    }

    public void setItem(int slot, GItem item) {
        if (slot >= realSize) return;
        if (!item.getType().equals(Material.AIR)) {
            this.items.put(slot, item);
        }
    }

    public void addItem(GItem... items) {
        for (GItem item : items) {
            if (!item.getType().equals(Material.AIR)) {
                for (int i = 0; i < realSize; i++) {
                    if (!this.items.containsKey(i)) {
                        this.setItem(i, item);
                        break;
                    } else {
                        if ((size - 1) == i) {
                            GedLibrary.getInstance().getLogger().warning("Inventory is full but a plugin still trying to add item to inventory");
                        }
                    }
                }
            }
        }
    }

    public GItem getItem(int slot) {
        if (this.items.containsKey(slot)) {
            return this.items.get(slot);
        } else {
            return new GItem(new ItemStack(Material.AIR));
        }
    }

    public boolean hasItem() {
        return this.items.size() != 0;
    }

    public boolean hasItem(int slot) {
        return this.items.containsKey(slot);
    }

    public Inventory getHandle() {
        Inventory inventory = null;
        if (this.title != null) {
            Bukkit.createInventory(null, size, title);
        } else {
            Bukkit.createInventory(null, size);
        }
        for (int i : items.keySet()) {
            inventory.setItem(i, items.get(i).getHandle());
        }
        this.inventory = inventory;
        return inventory;
    }

}
