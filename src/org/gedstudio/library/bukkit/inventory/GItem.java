package org.gedstudio.library.bukkit.inventory;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.gedstudio.library.bukkit.attribute.GAttributeModifier;
import org.gedstudio.library.bukkit.enchant.GEnchantment;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface GItem extends Serializable, Cloneable, ConfigurationSerializable {

    /**
     * To check if item has data
     * @return boolean
     */
    boolean hasItemData();

    /**
     * To get the data of the item
     * @return item data
     */
    GItemData getItemData();

    /**
     * To get or create the data of the item
     * <p>If item has data will return the data of the item else will create a new item data then return the new item data</p>
     * @return item data
     */
    GItemData getOrCreateItemData();

    /**
     * To set the data of the item
     * @param itemData item data
     */
    void setItemData(GItemData itemData);

    /**
     * To check if item is a food
     * @return item is food
     */
    boolean isFood();

    /**
     * To get the type of the item
     * @return type
     */
    Material getType();

    /**
     * To set the type of item
     * @param type type
     */
    void setType(Material type);

    /**
     * To get the amount of the item
     * @return amount
     */
    int getAmount();

    /**
     * To set the amount of the item
     * @param amount amount
     */
    void setAmount(int amount);

    /**
     * To check if item has display name
     * @return boolean
     */
    boolean hasDisplayName();

    /**
     * To get the display name of the item
     * @return display name
     */
    String getDisplayName();

    /**
     * To set the display name of the item
     * @param displayName display name
     */
    void setDisplayName(String displayName);

    /**
     * To check if item has localized name
     * @return boolean
     */
    boolean hasLocalizedName();

    /**
     * To get the localized name of the item
     * @return localized name
     */
    String getLocalizedName();

    /**
     * To set the localized name of the item
     * @param name localized name
     */
    void setLocalizedName(String name);

    /**
     * To check if item has lore
     * @return boolean
     */
    boolean hasLore();

    /**
     * To get the lore of the item
     * @return lore
     */
    List<String> getLore();

    /**
     * To add a new line to the lore
     * @param line new line
     */
    void addLore(String line);

    /**
     * To add new lines to the lore
     * @param lines lines
     */
    void addMultiLore(String... lines);

    /**
     * To add new lines to the lore
     * @param lines lines
     */
    void addMultiLore(List<String> lines);

    /**
     * To set the lore of the item
     * @param lore lore
     */
    void setLore(List<String> lore);

    /**
     * To check if item has enchants
     * @return boolean
     */
    boolean hasEnchants();

    /**
     * To check if item has enchantment
     * @param enchantment enchantment
     * @return boolean
     */
    boolean hasEnchant(GEnchantment enchantment);

    /**
     * To add enchantment to the item
     * @param enchantment enchantment
     * @param level level
     */
    void addEnchant(GEnchantment enchantment, int level);

    /**
     * To check if item has attributeModifier
     * @return boolean
     */
    boolean hasAttributeModifiers();

    /**
     * To check if item has attributeModifier
     * @param attributeModifier attributeModifier
     * @return boolean
     */
    boolean hasAttributeModifier(GAttributeModifier attributeModifier);

    /**
     * To check if item has attributeModifier that attribute is attribute
     * @param attribute attribute
     * @return boolean
     */
    boolean hasAttributeModifier(Attribute attribute);

    /**
     * To get the attributeModifiers that attribute is attribute
     * @param attribute attribute
     * @return attributeModifiers
     */
    Set<GAttributeModifier> getAttributeModifiers(Attribute attribute);

    /**
     * To get the attributeModifiers that slot is slot
     * @param slot slot
     * @return attributeModifiers
     */
    Set<GAttributeModifier> getAttributeModifiers(EquipmentSlot slot);

    /**
     * To get all attributeModifiers
     * @return attributeModifiers
     */
    List<GAttributeModifier> getAttributeModifiers();

    /**
     * To add new attributeModifier
     * @param attributeModifier attributeModifier
     */
    void addAttributeModifier(GAttributeModifier attributeModifier);

    /**
     * To cast the GItem to Bukkit ItemStack
     * @return Bukkit ItemStack
     */
    ItemStack getHandle();

    /**
     * To clone a GItem
     * @return GItem
     */
    org.gedstudio.library.bukkit.inventory.GItem clone();

    /**
     * To serialize the GItem to Map
     * @return serialize Map
     */
    Map<String, Object> serialize();

}
