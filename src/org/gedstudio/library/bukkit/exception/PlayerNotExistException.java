package org.gedstudio.library.bukkit.exception;

public class PlayerNotExistException extends Exception {

    /**
     * This exception will be threw when cannot find a player
     */
    public PlayerNotExistException() {
        super("Player not exist");
    }

    /**
     * This exception will be threw when cannot find a player
     * @param exception the exception message
     */
    public PlayerNotExistException(String exception) {
        super(exception);
    }

}
