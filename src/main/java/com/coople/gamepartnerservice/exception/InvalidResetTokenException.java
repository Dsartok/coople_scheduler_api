package com.coople.gamepartnerservice.exception;

/**
 * Exception thrown when a reset token is considered invalid.
 * This exception is typically used when attempting to process a reset token
 * that doesn't meet the expected criteria or is no longer valid.
 *
 * @version 1.0
 * @since 14-12-2023
 * @author maupa13
 */
public class InvalidResetTokenException extends RuntimeException {

    /**
     * Constructs a new InvalidResetTokenException with the specified detail message.
     *
     * @param message the detail message.
     */
    public InvalidResetTokenException(String message){
        super(message);
    }
}
