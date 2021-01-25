package net.deechael.ged.library;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;

import java.io.Serializable;

public class GedKey implements Serializable {

    private String key;

    public GedKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public NamespacedKey getHandle(Plugin plugin) {
        return new NamespacedKey(plugin, key);
    }

}
