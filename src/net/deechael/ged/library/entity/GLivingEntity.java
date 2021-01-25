package net.deechael.ged.library.entity;

import net.deechael.ged.library.GLocation;
import net.deechael.ged.library.inventory.GItem;
import org.bukkit.entity.LivingEntity;

import java.util.UUID;

public class GLivingEntity extends GEntity {

    private final LivingEntity livingEntity;

    public GLivingEntity(LivingEntity livingEntity) {
        super(livingEntity);
        this.livingEntity = livingEntity;
    }

    public void setItemInMainHand(GItem item) {
        this.livingEntity.getEquipment().setItemInMainHand(item.getHandle());
    }

    public void setItemInOffHand(GItem item) {
        this.livingEntity.getEquipment().setItemInOffHand(item.getHandle());
    }

    public void setHelmet(GItem item) {
        this.livingEntity.getEquipment().setHelmet(item.getHandle());
    }

    public void setChestplate(GItem item) {
        this.livingEntity.getEquipment().setChestplate(item.getHandle());
    }

    public void setLeggings(GItem item) {
        this.livingEntity.getEquipment().setLeggings(item.getHandle());
    }

    public void setBoots(GItem item) {
        this.livingEntity.getEquipment().setBoots(item.getHandle());
    }

    public GItem getItemInMainHand() {
        return new GItem(this.livingEntity.getEquipment().getItemInMainHand());
    }

    public GItem getItemInOffHand() {
        return new GItem(this.livingEntity.getEquipment().getItemInOffHand());
    }

    public GItem getHelmet() {
        return new GItem(this.livingEntity.getEquipment().getHelmet());
    }

    public GItem getChestplate() {
        return new GItem(this.livingEntity.getEquipment().getChestplate());
    }

    public GItem getLeggings() {
        return new GItem(this.livingEntity.getEquipment().getLeggings());
    }

    public GItem getBoots() {
        return new GItem(this.livingEntity.getEquipment().getBoots());
    }

    @Override
    public UUID getUniqueId() {
        return this.livingEntity.getUniqueId();
    }

    @Override
    public GLocation getLocation() {
        return new GLocation(this.livingEntity.getLocation());
    }

    @Override
    public void teleport(GLocation location) {
        this.livingEntity.teleport(location.getHandle());
    }

    @Override
    public void teleport(GEntity entity) {
        this.livingEntity.teleport(entity.getHandle());
    }

    @Override
    public LivingEntity getHandle() {
        return this.livingEntity;
    }

}
