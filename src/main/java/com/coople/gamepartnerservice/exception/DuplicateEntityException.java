package com.coople.gamepartnerservice.exception;

/**
 * Exception thrown when there is duplicate entity.
 * This exception is typically used to indicate that an expected resource,
 * such as a database record could not be located.
 *
 * @version 1.0
 * @since 14-12-2023
 * @author maupa13
 */
public class DuplicateEntityException extends RuntimeException {

    /**
     * Constructs a new DuplicateEntityException with the specified detail message.
     *
     * @param message the detail message.
     */
    public DuplicateEntityException(String message){
        super(message);
    }
}
