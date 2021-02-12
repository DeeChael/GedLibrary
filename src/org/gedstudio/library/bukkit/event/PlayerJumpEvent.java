package org.gedstudio.library.bukkit.event;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerJumpEvent extends PlayerEvent implements Cancellable {

    private final static HandlerList handlers = new HandlerList();

    private Location from;
    private Location to;
    private double distance;

    private boolean isCancelled = false;

    public PlayerJumpEvent(Player player, Location from, Location to, double distance) {
        super(player);
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public Location getFrom() {
        return from;
    }

    public Location getTo() {
        return to;
    }

    public boolean isCancelled() {
        return this.isCancelled;
    }

    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}
