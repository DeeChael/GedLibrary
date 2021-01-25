package net.deechael.ged.library;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.Serializable;

public class GLocation implements Serializable {

    private String world;
    private double x;
    private double y;
    private double z;

    public GLocation(Location location) {
        this.world = location.getWorld().getName();
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
    }

    public Location getHandle() {
        return new Location(Bukkit.getWorld(world), x, y, z);
    }

}
