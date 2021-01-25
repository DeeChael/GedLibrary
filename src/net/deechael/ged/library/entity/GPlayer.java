package net.deechael.ged.library.entity;

import net.deechael.ged.library.GLocation;
import net.deechael.ged.library.exception.PlayerNotExistException;
import net.deechael.ged.library.inventory.GItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GPlayer extends GLivingEntity {

    private final Player player;

    public GPlayer(Player player) throws PlayerNotExistException {
        super(player);
        if (player != null) {
            this.player = player;
        } else {
            throw new PlayerNotExistException();
        }
    }

    public GPlayer(String name) throws PlayerNotExistException {
        super(Bukkit.getPlayer(name));
        this.player = Bukkit.getPlayer(name);
        if (player == null) {
            throw new PlayerNotExistException();
        }
    }

    public GPlayer(UUID uuid) throws PlayerNotExistException {
        super(Bukkit.getPlayer(uuid));
        this.player = Bukkit.getPlayer(uuid);
        if (player == null) {
            throw new PlayerNotExistException();
        }
    }

    public void setDisplayName(String name) {
        player.setDisplayName(name);
    }

    public String getDisplayName() {
        return this.player.getDisplayName();
    }

    public void setPlayerListName(String name) {
        player.setPlayerListName(name);
    }

    public String getPlayerListName() {
        return this.player.getPlayerListName();
    }

    @Override
    public GItem getItemInMainHand() {
        return new GItem(this.player.getInventory().getItemInMainHand());
    }

    @Override
    public GItem getItemInOffHand() {
        return new GItem(this.player.getInventory().getItemInOffHand());
    }

    @Override
    public GItem getHelmet() {
        return new GItem(this.player.getInventory().getHelmet());
    }

    @Override
    public GItem getChestplate() {
        return new GItem(this.player.getInventory().getChestplate());
    }

    @Override
    public GItem getLeggings() {
        return new GItem(this.player.getInventory().getLeggings());
    }

    @Override
    public GItem getBoots() {
        return new GItem(this.player.getInventory().getBoots());
    }

    @Override
    public void setItemInMainHand(GItem item) {
        this.player.getInventory().setItemInMainHand(item.getHandle());
    }

    @Override
    public void setItemInOffHand(GItem item) {
        this.player.getInventory().setItemInOffHand(item.getHandle());
    }

    @Override
    public void setHelmet(GItem item) {
        this.player.getInventory().setHelmet(item.getHandle());
    }

    @Override
    public void setChestplate(GItem item) {
        this.player.getInventory().setChestplate(item.getHandle());
    }

    @Override
    public void setLeggings(GItem item) {
        this.player.getInventory().setLeggings(item.getHandle());
    }

    @Override
    public void setBoots(GItem item) {
        this.player.getInventory().setBoots(item.getHandle());
    }

    public void addItem(GItem... items) {
        List<ItemStack> itemStackList = new ArrayList<>();
        for (GItem item : items) {
            itemStackList.add(item.getHandle());
        }
        this.player.getInventory().addItem(itemStackList.toArray(new ItemStack[itemStackList.size()]));
    }

    public void setItem(int slot, GItem item) {
        this.player.getInventory().setItem(slot, item.getHandle());
    }

    @Override
    public void teleport(GLocation location) {
        this.player.teleport(location.getHandle());
    }

    @Override
    public void teleport(GEntity entity) {
        this.player.teleport(entity.getHandle());
    }

    @Override
    public GLocation getLocation() {
        return new GLocation(this.player.getLocation());
    }

    @Override
    public UUID getUniqueId() {
        return this.player.getUniqueId();
    }

    public void setSkin(String name) {

    }

    @Override
    public Player getHandle() {
        return this.player;
    }

}
