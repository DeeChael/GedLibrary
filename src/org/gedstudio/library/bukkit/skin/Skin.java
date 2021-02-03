package org.gedstudio.library.bukkit.skin;

import org.bukkit.scheduler.BukkitRunnable;
import org.gedstudio.library.bukkit.GedLibrary;
import org.gedstudio.library.bukkit.configuration.JsonConfiguration;

import java.io.Serializable;

public class Skin implements Serializable {

    private String name;
    private String uuid;
    private String value;
    private String signature;

    /**
     * To get a new skin
     * @param playerName the name of the skin's owner
     */
    public Skin(String playerName) {
        this.name = playerName;
        if (GedLibrary.getInstance().getSkinCache().containsKey(playerName)) {
            Skin cache = GedLibrary.getInstance().getSkinCache().get(playerName);
            uuid = cache.getUuid();
            value = cache.getValue();
            signature = cache.getSignature();

        } else {
            new BukkitRunnable() {
                @Override
                public void run() {
                    JsonConfiguration api1 = new JsonConfiguration("https://api.mojang.com/users/profiles/minecraft/" + playerName);
                    uuid = api1.getString("id");
                    JsonConfiguration api2 = new JsonConfiguration("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false");
                    value = api2.getString("value");
                    signature = api2.getString("signature");
                    GedLibrary.getInstance().getSkinCache().put(playerName, getHandle());
                }
            }.runTaskAsynchronously(GedLibrary.getInstance());
        }
    }

    /**
     * To get the owner of the skin
     * @return the owner of the skin
     */
    public String getName() {
        return name;
    }

    /**
     * To get the uuid of the owner
     * @return the uuid of the owner
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * To get the texture value
     * @return texture value
     */
    public String getValue() {
        return value;
    }

    /**
     * To get the texture signature
     * @return texture signature
     */
    public String getSignature() {
        return signature;
    }

    /**
     * To get self
     * @return self
     */
    public Skin getHandle() {
        return this;
    }

}
