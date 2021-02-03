package org.gedstudio.library.bukkit.exception;

public class ValueNotEqualsError extends Error {

    /**
     * This error maybe will be threw when other plugins changing item nbt data
     */
    public ValueNotEqualsError() {
        super("Value type is not equals the exist value type");
    }

    /**
     * This error maybe will be threw when other plugins changing item nbt data
     * @param errorMessage error message
     */
    public ValueNotEqualsError(String errorMessage) {
        super(errorMessage);
    }

}
