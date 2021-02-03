package org.gedstudio.library.bukkit.exception;

public class CooldownHasStartedError extends Error {

    public CooldownHasStartedError() {
        super("Cooldown has been started");
    }

    public CooldownHasStartedError(String message) {
        super(message);
    }

}
