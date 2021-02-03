package org.gedstudio.library.bukkit;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;

import java.io.Serializable;

public class GedKey implements Serializable {

    private String key;

    /**
     * To create a new Key
     * @param key key
     */
    public GedKey(String key) {
        this.key = key;
    }

    /**
     * To get key text
     * @return key text
     */
    public String getKey() {
        return key;
    }

    /**
     * To cast to Bukkit NamespacedKey
     * @param plugin plugin
     * @return Bukkit NamespacedKey
     */
    public NamespacedKey getHandle(Plugin plugin) {
        return new NamespacedKey(plugin, key);
    }

}
