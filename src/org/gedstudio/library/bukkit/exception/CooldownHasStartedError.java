package org.gedstudio.library.bukkit.exception;

public class CooldownHasStartedError extends Error {

    /**
     * This error maybe will be threw when some plugin try to start a started cool down
     */
    public CooldownHasStartedError() {
        super("Cooldown has been started");
    }

    /**
     * This error maybe will be threw when some plugin try to start a started cool down
     * @param message error message
     */
    public CooldownHasStartedError(String message) {
        super(message);
    }

}
