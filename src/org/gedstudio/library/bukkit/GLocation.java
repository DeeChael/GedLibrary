package org.gedstudio.library.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.Serializable;

public class GLocation implements Serializable {

    private String world;
    private double x;
    private double y;
    private double z;

    /**
     * To create new GLocation from Bukkit Location
     * @param location Bukkit Location
     */
    public GLocation(Location location) {
        this.world = location.getWorld().getName();
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
    }

    public String getWorldAsString() {
        return world;
    }

    public World getWorld() {
        return Bukkit.getWorld(this.world);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    /**
     * To cast GLocation to Bukkit Location
     * @return Bukkit Location
     */
    public Location getHandle() {
        return new Location(Bukkit.getWorld(world), x, y, z);
    }

}
