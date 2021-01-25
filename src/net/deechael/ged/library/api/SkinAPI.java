package net.deechael.ged.library.api;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.deechael.ged.library.configuration.JsonConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SkinAPI {

    public static void setPlayerSkin(Player player, String name) {
        try {
            Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
            String skinUUID = new JsonConfiguration("https://api.mojang.com/users/profiles/minecraft/" + name).getString("id");
            JsonConfiguration json = new JsonConfiguration("https://sessionserver.mojang.com/session/minecraft/profile/" + skinUUID + "?unsigned=false").subJson("properties", 0);
            String value = json.getString("value");
            String signature = json.getString("signature");
            GameProfile gameProfile = (GameProfile) entityPlayer.getClass().getMethod("getProfile").invoke(entityPlayer);
            gameProfile.getProperties().put("textures", new Property("textures", value, signature));
            Class<?> packetPlayOutPlayerInfo = Class.forName("net.minecraft.server." + nms() + ".PacketPlayOutPlayerInfo");
            for (Player p : Bukkit.getOnlinePlayers()) {
                Object ep = p.getClass().getMethod("getHandle").invoke(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static String nms() {
        return Bukkit.getServer().getClass().getPackage().getName().substring(Bukkit.getServer().getClass().getPackage().getName().lastIndexOf('.') + 1);
    }

}
