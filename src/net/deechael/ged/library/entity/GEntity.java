package net.deechael.ged.library.entity;

import net.deechael.ged.library.GLocation;
import org.bukkit.entity.Entity;

import java.util.UUID;

public class GEntity {

    private final Entity entity;

    public GEntity(Entity entity) {
        this.entity = entity;
    }

    public UUID getUniqueId() {
        return this.entity.getUniqueId();
    }

    public Entity getHandle() {
        return this.entity;
    }

    public GLocation getLocation() {
        return new GLocation(this.entity.getLocation());
    }

    public void teleport(GLocation location) {
        this.entity.teleport(location.getHandle());
    }

    public void teleport(GEntity entity) {
        this.entity.teleport(entity.getHandle());
    }

}
