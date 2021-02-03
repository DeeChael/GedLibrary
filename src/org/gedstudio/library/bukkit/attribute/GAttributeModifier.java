package org.gedstudio.library.bukkit.attribute;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;

import java.io.Serializable;
import java.util.UUID;

public class GAttributeModifier implements Serializable {

    private final Attribute attribute;
    private final UUID uuid;
    private final String name;
    private final double amount;
    private final AttributeModifier.Operation operation;
    private final EquipmentSlot slot;

    /**
     * For GItem can be serializable
     * @param attribute attribute
     * @param uuid uuid
     * @param name name
     * @param amount amount
     * @param operation operation
     * @param slot slot
     */
    public GAttributeModifier(Attribute attribute, UUID uuid, String name, double amount, AttributeModifier.Operation operation, EquipmentSlot slot) {
        this.attribute = attribute;
        this.uuid = uuid;
        this.name = name;
        this.amount = amount;
        this.operation = operation;
        this.slot = slot;
    }

    /**
     * To get the uuid of the attributeModifier
     * @return the uuid of the attributeModifier
     */
    public UUID getUniqueId() {
        return this.uuid;
    }

    /**
     * To get the attribute
     * @return attribute
     */
    public Attribute getAttribute() {
        return attribute;
    }

    /**
     * To get the amount of the attributeModifier
     * @return the amount of the attributeModifier
     */
    public double getAmount() {
        return amount;
    }

    /**
     * To get the slot of the attributeModifier
     * @return the slot of the attributeModifier
     */
    public EquipmentSlot getSlot() {
        return slot;
    }

    /**
     * To get the operation of the attributeModifier
     * @return the operation of the attributeModifier
     */
    public AttributeModifier.Operation getOperation() {
        return operation;
    }

    /**
     * To get the name of the attributeModifier
     * @return the name of the attributeModifier
     */
    public String getName() {
        return name;
    }

    /**
     * To cast to Bukkit AttributeModifier field
     * @return Bukkit AttributeModifier field
     */
    public AttributeModifier toAttributeModifier() {
        return new AttributeModifier(uuid, name, amount, operation, slot);
    }

    /**
     * To get GAttributeModifier from Bukkit AttributeModifier
     * @param attribute attribute
     * @param attributeModifier Bukkit AttributeModifier field
     * @return GAttributeModifier
     */
    public static GAttributeModifier fromAttributeModifier(Attribute attribute, AttributeModifier attributeModifier) {
        return new GAttributeModifier(attribute, attributeModifier.getUniqueId(), attributeModifier.getName(), attributeModifier.getAmount(), attributeModifier.getOperation(), attributeModifier.getSlot());
    }

}
