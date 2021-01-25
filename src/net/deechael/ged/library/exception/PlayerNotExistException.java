package net.deechael.ged.library.exception;

public class PlayerNotExistException extends Exception {

    public PlayerNotExistException() {
        super("Player not exist");
    }

    public PlayerNotExistException(String exception) {
        super(exception);
    }

}
