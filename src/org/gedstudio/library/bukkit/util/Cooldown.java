package org.gedstudio.library.bukkit.util;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.gedstudio.library.bukkit.GedLibrary;
import org.gedstudio.library.bukkit.exception.CooldownHasStartedError;

public class Cooldown {

    private int startTime;
    private int time;
    private boolean isStart = false;
    private BukkitTask bukkitTask;

    /**
     * To create new cool down
     * <p>Use start() method to start cool down</p>
     * <p>Time unit is second</p>
     * @param startTime start time
     */
    public Cooldown(int startTime) {
        this.startTime = startTime;
        this.time = startTime;
    }

    /**
     * To start the cool down
     * <p>You can use method isEnd() to check if cool down is end</p>
     */
    public void start() {
        if (this.isStart) {
            throw new CooldownHasStartedError();
        } else {
            this.isStart = true;
            this.bukkitTask = new BukkitRunnable() {
                @Override
                public void run() {
                    if (!(Cooldown.this.time <= 0)) {
                        Cooldown.this.time -= 1;
                    } else {
                        Cooldown.this.isStart = false;
                        this.cancel();
                    }
                }
            }.runTaskTimerAsynchronously(GedLibrary.getInstance(), 20L, 20L);
        }
    }

    /**
     * To check if cool down is started
     * @return cool down is started
     */
    public boolean isStart() {
        return isStart;
    }

    /**
     * To check if cool down is stopped
     * @return cool down is stopped
     */
    public boolean isEnd() {
        return this.time == 0 && this.bukkitTask.isCancelled();
    }

    /**
     * To stop the cool down
     * <p>It will work when cool down is start</p>
     */
    public void stop() {
        if (isStart && !this.bukkitTask.isCancelled() && this.bukkitTask != null) {
            this.isStart = false;
            this.bukkitTask.cancel();
        }
    }

    /**
     * To reset the cool down
     */
    public void reset() {
        if (bukkitTask != null && !bukkitTask.isCancelled()) {
            bukkitTask.cancel();
        }
        this.time = startTime;
        this.bukkitTask = null;
        this.isStart = false;
    }

    /**
     * To get the rest of the time
     * @return the rest of the time
     */
    public int getTime() {
        return this.time;
    }

    /**
     * To get the start time
     * <p>It won't change after init</p>
     * @return start time
     */
    public int getStartTime() {
        return this.startTime;
    }

}
