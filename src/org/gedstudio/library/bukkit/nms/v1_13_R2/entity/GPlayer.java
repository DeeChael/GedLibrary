package org.gedstudio.library.bukkit.nms.v1_13_R2.entity;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import net.minecraft.server.v1_13_R2.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_13_R2.util.CraftMagicNumbers;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.gedstudio.library.bukkit.EnumHand;
import org.gedstudio.library.bukkit.GLocation;
import org.gedstudio.library.bukkit.GedLibrary;
import org.gedstudio.library.bukkit.chat.GText;
import org.gedstudio.library.bukkit.exception.PlayerNotExistException;
import org.gedstudio.library.bukkit.inventory.GInventory;
import org.gedstudio.library.bukkit.inventory.GItem;
import org.gedstudio.library.bukkit.scoreboard.GScoreBoard;
import org.gedstudio.library.bukkit.skin.Skin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GPlayer implements org.gedstudio.library.bukkit.entity.GPlayer {

    private final Player player;

    private GScoreBoard scoreBoard;

    public GPlayer(Player player) throws PlayerNotExistException {
        if (player != null) {
            this.player = player;
        } else {
            throw new PlayerNotExistException();
        }
    }

    public GPlayer(String name) throws PlayerNotExistException {
        this.player = Bukkit.getPlayer(name);
        if (player == null) {
            throw new PlayerNotExistException();
        }
    }

    public GPlayer(UUID uuid) throws PlayerNotExistException {
        this.player = Bukkit.getPlayer(uuid);
        if (player == null) {
            throw new PlayerNotExistException();
        }
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    @Override
    public String getName() {
        return this.player.getName();
    }

    @Override
    public void sendMessage(GText gText) {
        this.player.sendMessage(gText.getText());
    }

    @Override
    public void sendMessage(GText... gTexts) {
        for (GText gText : gTexts) {
            this.player.sendMessage(gText.getText());
        }
    }

    @Override
    public void sendMessage(List<GText> gTexts) {
        for (GText gText : gTexts) {
            this.player.sendMessage(gText.getText());
        }
    }

    public void setItemCoolDown(Material itemType, int cd) {
        PacketPlayOutSetCooldown packetPlayOutSetCooldown = new PacketPlayOutSetCooldown(CraftMagicNumbers.getItem(itemType), cd);
        ((CraftPlayer) this.player).getHandle().playerConnection.sendPacket(packetPlayOutSetCooldown);
    }

    public void swingArm(EnumHand enumHand) {
        if (enumHand.equals(EnumHand.OFF_HAND)) {
            PacketPlayOutAnimation packetPlayOutAnimation = new PacketPlayOutAnimation(((CraftPlayer) this.player).getHandle(), 3);
            for (org.gedstudio.library.bukkit.entity.GPlayer player : GedLibrary.getInstance().getOnlinePlayers()) {
                ((CraftPlayer) player.getHandle()).getHandle().playerConnection.sendPacket(packetPlayOutAnimation);
            }
        } else {
            PacketPlayOutAnimation packetPlayOutAnimation = new PacketPlayOutAnimation(((CraftPlayer) this.player).getHandle(), 0);
            for (org.gedstudio.library.bukkit.entity.GPlayer player : GedLibrary.getInstance().getOnlinePlayers()) {
                ((CraftPlayer) player.getHandle()).getHandle().playerConnection.sendPacket(packetPlayOutAnimation);
            }
        }
    }

    public int ping() {
        return ((CraftPlayer) this.player).getHandle().ping;
    }

    @Override
    public void sendActionbar(GText gText) {
        this.sendActionbar(gText, 10, 70, 20);
    }

    @Override
    public void sendActionbar(GText gText, int fadeIn, int stay, int fadeOut) {
        PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.ACTIONBAR, new ChatMessage(gText.getText()), fadeIn, stay, fadeOut);
        ((CraftPlayer) this.player).getHandle().playerConnection.sendPacket(packetPlayOutTitle);
    }

    @Override
    public void sendTitle(GText gText) {
        this.sendTitle(gText, 10, 70, 20);
    }

    @Override
    public void sendTitle(GText gText, int fadeIn, int stay, int fadeOut) {
        PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, new ChatMessage(gText.getText()), fadeIn, stay, fadeOut);
        ((CraftPlayer) this.player).getHandle().playerConnection.sendPacket(packetPlayOutTitle);
    }

    @Override
    public void sendSubTitle(GText gText) {
        this.sendSubTitle(gText, 10, 70, 20);
    }

    @Override
    public void sendSubTitle(GText gText, int fadeIn, int stay, int fadeOut) {
        PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, new ChatMessage(gText.getText()), fadeIn, stay, fadeOut);
        ((CraftPlayer) this.player).getHandle().playerConnection.sendPacket(packetPlayOutTitle);
    }

    @Override
    public void sendTitleWithSubTitle(GText title, GText subTitle) {
        this.sendTitleWithSubTitle(title, subTitle, 10, 70, 20);
    }

    @Override
    public void sendTitleWithSubTitle(GText title, GText subTitle, int fadeIn, int stay, int fadeOut) {
        this.sendTitleWithSubTitle(title, fadeIn, stay, fadeOut, subTitle, fadeIn, stay, fadeOut);
    }

    @Override
    public void sendTitleWithSubTitle(GText title, int titleFadeIn, int titleStay, int titleFadeOut, GText subTitle, int subTitleFadeIn, int subTitleStay, int subTitleFadeOut) {
        PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, new ChatMessage(title.getText()), titleFadeIn, titleStay, titleFadeOut);
        PacketPlayOutTitle subTitlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, new ChatMessage(subTitle.getText()), subTitleFadeIn, subTitleStay, subTitleFadeOut);
        ((CraftPlayer) this.player).getHandle().playerConnection.sendPacket(titlePacket);
        ((CraftPlayer) this.player).getHandle().playerConnection.sendPacket(subTitlePacket);
    }

    @Override
    public void clearScreen() {
        PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.CLEAR, new ChatMessage(""));
        ((CraftPlayer) this.player).getHandle().playerConnection.sendPacket(packetPlayOutTitle);
    }

    @Override
    public void resetScreen() {
        PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.RESET, new ChatMessage(""));
        ((CraftPlayer) this.player).getHandle().playerConnection.sendPacket(packetPlayOutTitle);
    }

    @Override
    public void setScoreboard(GScoreBoard scoreboard) {
        if (this.scoreBoard != null) {
            this.scoreBoard.removePlayer(this);
            this.scoreBoard = null;
        }
        this.scoreBoard = scoreboard;
        scoreboard.addPlayer(this);
    }

    @Override
    public void resetScoreboard() {
        if (this.scoreBoard != null) {
            this.scoreBoard.removePlayer(this);
            this.scoreBoard = null;
        }
    }

    @Override
    public boolean hasScoreboard() {
        return this.scoreBoard != null;
    }

    @Override
    public GScoreBoard getScoreboard() {
        if (this.scoreBoard != null) {
            return this.scoreBoard;
        } else {
            return null;
        }
    }

    @Override
    public CommandSender getSender() {
        return this.player;
    }

    public void hidePlayer(org.gedstudio.library.bukkit.entity.GPlayer player) {
        this.player.hidePlayer(GedLibrary.getInstance(), player.getHandle());
    }

    public void showPlayer(org.gedstudio.library.bukkit.entity.GPlayer player) {
        this.player.showPlayer(GedLibrary.getInstance(), player.getHandle());
    }

    public void die() {
        ((CraftPlayer) this.player).getHandle().die(DamageSource.OUT_OF_WORLD);
    }

    @Override
    public void setName(String name) {
        GameProfile gameProfile = ((CraftPlayer) this.player).getHandle().getProfile();
        try {
            Field field = gameProfile.getClass().getDeclaredField("name");
            field.setAccessible(true);
            field.set(gameProfile, name);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setDisplayName(String name) {
        player.setDisplayName(name);
    }

    @Override
    public String getDisplayName() {
        return this.player.getDisplayName();
    }

    @Override
    public void setPlayerListName(String name) {
        player.setPlayerListName(name);
    }

    @Override
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

    @Override
    public void addItem(GItem... items) {
        List<ItemStack> itemStackList = new ArrayList<>();
        for (GItem item : items) {
            itemStackList.add(item.getHandle());
        }
        this.player.getInventory().addItem(itemStackList.toArray(new ItemStack[itemStackList.size()]));
    }

    @Override
    public void setItem(int slot, GItem item) {
        this.player.getInventory().setItem(slot, item.getHandle());
    }

    @Override
    public GItem getItem(int slot) {
        return new GItem(this.player.getInventory().getItem(slot));
    }

    @Override
    public void openInventory(GInventory inventory) {
        this.player.openInventory(inventory.getHandle());
    }

    @Override
    public void closeInventory() {
        this.player.closeInventory();
    }

    @Override
    public GInventory getOpenInventory() {
        if (!this.player.getOpenInventory().getType().equals(InventoryType.PLAYER)) {
            return new org.gedstudio.library.bukkit.nms.all.GInventory(this.player.getOpenInventory().getTopInventory(), this.player.getOpenInventory().getTitle());
        } else {
            return new org.gedstudio.library.bukkit.nms.all.GInventory(this.player.getInventory(), null);
        }
    }

    @Override
    public void teleport(GLocation location) {
        this.player.teleport(location.getHandle());
    }

    @Override
    public void teleport(org.gedstudio.library.bukkit.entity.GPlayer player) {
        this.player.teleport(player.getHandle());
    }

    @Override
    public GLocation getLocation() {
        return new GLocation(this.player.getLocation());
    }

    @Override
    public UUID getUniqueId() {
        return this.player.getUniqueId();
    }

    @Override
    public void setSkin(Skin skin) {
        EntityPlayer entityPlayer = ((CraftPlayer) this.getHandle()).getHandle();
        GameProfile gameProfile = entityPlayer.getProfile();
        PropertyMap propertyMap = gameProfile.getProperties();
        propertyMap.put("textures", new Property("textures", skin.getValue(), skin.getSignature()));
        final PacketPlayOutPlayerInfo removeInfo = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, entityPlayer);
        final PacketPlayOutPlayerInfo addInfo = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, entityPlayer);
        final PacketPlayOutRespawn respawn = new PacketPlayOutRespawn(entityPlayer.getWorld().worldProvider.getDimensionManager(), entityPlayer.getWorld().getDifficulty(), entityPlayer.getWorld().S(), entityPlayer.playerInteractManager.getGameMode());
        for (org.gedstudio.library.bukkit.entity.GPlayer player : GedLibrary.getInstance().getOnlinePlayers()) {
            ((CraftPlayer) player.getHandle()).getHandle().playerConnection.sendPacket(removeInfo);
            ((CraftPlayer) player.getHandle()).getHandle().playerConnection.sendPacket(addInfo);
        }
        entityPlayer.playerConnection.sendPacket(respawn);
        new BukkitRunnable() {
            @Override
            public void run() {
                getHandle().updateInventory();
            }
        }.runTaskLater(GedLibrary.getInstance(), 2L);
    }

    @Override
    public Player getHandle() {
        return this.player;
    }

}
