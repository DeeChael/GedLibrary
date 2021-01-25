package net.deechael.ged.library.attribute;

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

    public GAttributeModifier(Attribute attribute, UUID uuid, String name, double amount, AttributeModifier.Operation operation, EquipmentSlot slot) {
        this.attribute = attribute;
        this.uuid = uuid;
        this.name = name;
        this.amount = amount;
        this.operation = operation;
        this.slot = slot;
    }

    public UUID getUniqueId() {
        return this.uuid;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public double getAmount() {
        return amount;
    }

    public EquipmentSlot getSlot() {
        return slot;
    }

    public AttributeModifier.Operation getOperation() {
        return operation;
    }

    public String getName() {
        return name;
    }

    public AttributeModifier toAttributeModifier() {
        return new AttributeModifier(uuid, name, amount, operation, slot);
    }

    public static GAttributeModifier fromAttributeModifier(Attribute attribute, AttributeModifier attributeModifier) {
        return new GAttributeModifier(attribute, attributeModifier.getUniqueId(), attributeModifier.getName(), attributeModifier.getAmount(), attributeModifier.getOperation(), attributeModifier.getSlot());
    }

}
