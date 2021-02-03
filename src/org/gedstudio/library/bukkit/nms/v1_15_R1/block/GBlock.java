package org.gedstudio.library.bukkit.nms.v1_15_R1.block;

import net.minecraft.server.v1_15_R1.BlockPosition;
import net.minecraft.server.v1_15_R1.DamageSource;
import net.minecraft.server.v1_15_R1.PacketPlayOutBlockBreakAnimation;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftMob;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.gedstudio.library.bukkit.GLocation;
import org.gedstudio.library.bukkit.GedLibrary;
import org.gedstudio.library.bukkit.entity.GPlayer;

public class GBlock implements org.gedstudio.library.bukkit.block.GBlock {

    private transient Block block;
    private transient World world;
    private GLocation location;
    private Material type;
    private boolean isGlow;

    public GBlock(GLocation location) {
        this.location = location;
        this.block = location.getHandle().getBlock();
        this.type = block.getType();
    }

    public Material getType() {
        return this.type;
    }

    public void sendBreakAnimation(GPlayer player, int level) {
        if (level > 9) {
            level = 9;
        }
        if (level < 0) {
            level = 0;
        }
        PacketPlayOutBlockBreakAnimation packetPlayOutBlockBreakAnimation = new PacketPlayOutBlockBreakAnimation(0, new BlockPosition(this.location.getX(), this.location.getY(), this.location.getZ()), level);
        ((CraftPlayer) player.getHandle()).getHandle().playerConnection.sendPacket(packetPlayOutBlockBreakAnimation);
    }

    public void setGlow(boolean glow) {
        if (!this.isGlow) {
            if (glow) {
                Mob shulker = (Mob) location.getHandle().getWorld().spawnEntity(location.getHandle(), EntityType.SHULKER);
                shulker.setSilent(true);
                shulker.setAI(false);
                shulker.setInvulnerable(true);
                shulker.setPersistent(true);
                shulker.setGravity(false);
                shulker.setGlowing(true);
                GedLibrary.getInstance().getGlowingBlocks().put(location, shulker);
            }
        } else {
            if (!glow) {
                if (GedLibrary.getInstance().getGlowingBlocks().containsKey(this.location)) {
                    Mob shulker = GedLibrary.getInstance().getGlowingBlocks().get(this.location);
                    CraftMob craftMob = (CraftMob) shulker;
                    craftMob.getHandle().die(DamageSource.OUT_OF_WORLD);
                    GedLibrary.getInstance().getGlowingBlocks().remove(this.location);
                }
            }
        }
    }

}
