package org.gedstudio.library.bukkit.nms.v1_15_R1.inventory;

import net.minecraft.server.v1_15_R1.*;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.gedstudio.library.bukkit.entity.GPlayer;
import org.gedstudio.library.bukkit.inventory.GItem;
import org.gedstudio.library.bukkit.nms.all.GInventory;

public class GAnvil extends GInventory implements org.gedstudio.library.bukkit.inventory.GAnvil {

    private String title;
    private final Player player;
    private FakeAnvil fakeAnvil;

    public GAnvil(String title, GPlayer player) {
        super(title, 2);
        this.title = title;
        this.player = player.getHandle();
        this.fakeAnvil = new FakeAnvil(title, player.getHandle());
    }

    public GAnvil(GPlayer player) {
        super(null, 2);
        this.title = null;
        this.player = player.getHandle();
        this.fakeAnvil = new FakeAnvil(player.getHandle());
    }

    @Override
    public void setItem(int slot, GItem item) {
        //fakeAnvil.setItem(slot, CraftItemStack.asNMSCopy(itemStack));
        super.setItem(slot, item);
        fakeAnvil.castToBukkit().setItem(slot, item.getHandle());
    }

    @Override
    public GItem getItem(int slot) {
        return new GItem(fakeAnvil.castToBukkit().getItem(slot));
    }

    @Override
    public void openInventory() {
        //((CraftPlayer) player).getHandle().activeContainer = ((CraftPlayer) player).getHandle().defaultContainer;
        PacketPlayOutOpenWindow packetPlayOutOpenWindow = new PacketPlayOutOpenWindow(this.fakeAnvil.getContainerId(), Containers.ANVIL, new ChatMessage(this.title));
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutOpenWindow);
        ((CraftPlayer) player).getHandle().activeContainer = this.fakeAnvil;
        this.fakeAnvil.addSlotListener(((CraftPlayer) player).getHandle());
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Inventory getHandle() {
        return this.fakeAnvil.castToBukkit();
    }

    private class FakeAnvil extends ContainerAnvil {

        public FakeAnvil(String title, Player player) {
            super(((CraftPlayer) player).getHandle().nextContainerCounter(), ((CraftPlayer) player).getHandle().inventory, ContainerAccess.at(((CraftWorld) player.getWorld()).getHandle(), new BlockPosition(0, 0, 0)));
            this.checkReachable = false;
            if (title != null) {
                this.setTitle(new ChatMessage(title));
            }
        }

        public FakeAnvil (Player player) {
            super(((CraftPlayer) player).getHandle().nextContainerCounter(), ((CraftPlayer) player).getHandle().inventory, ContainerAccess.at(((CraftWorld) player.getWorld()).getHandle(), new BlockPosition(0, 0, 0)));
            this.checkReachable = false;
        }

        @Override
        public void e() {
            super.e();
            this.levelCost.set(0);
        }

        @Override
        public void b(EntityHuman entityhuman) {
        }

        @Override
        protected void a(EntityHuman entityhuman, World world, IInventory iinventory) {
        }

        public int getContainerId() {
            return windowId;
        }

        public Inventory castToBukkit() {
            return this.getBukkitView().getTopInventory();
        }

    }

}
