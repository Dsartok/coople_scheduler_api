package com.coople.gamepartnerservice.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * GlobalExceptionHandler is a controller advice class that provides centralized exception handling
 * for specific exception types.
 *
 * This class includes an {@code @ExceptionHandler} method to handle exceptions of types
 * {@link NotFoundException} and {@link EntityNotFoundException}. It returns a consistent
 * {@link ResponseEntity} with a 404 (Not Found) status and the error message from the exception.
 * {@link DuplicateEntityException} with a 409 (Conflict) status and the error message from the exception.
 *
 * @see ExceptionHandler
 * @see ResponseEntity
 * @see NotFoundException
 * @see EntityNotFoundException
 * @see DuplicateEntityException
 *
 * @version 1.0
 * @since 14-12-2023
 * @author maupa13
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles exceptions of types {@link NotFoundException} and {@link EntityNotFoundException}.
     * Returns a {@link ResponseEntity} with a 404 (Not Found) status and the error message from the exception.
     *
     * @param ex The exception to handle.
     * @return A {@link ResponseEntity} with a 404 status and the error message.
     * @see NotFoundException
     * @see EntityNotFoundException
     */
    @ExceptionHandler({NotFoundException.class,
            EntityNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles exceptions of type {@link DuplicateEntityException},
     * {@link IllegalArgumentException} and {@link InvalidResetTokenException}.
     * Returns a {@link ResponseEntity} with a 409 (Conflict) status and the error message from the exception.
     *
     * @param ex The exception to handle.
     * @return A {@link ResponseEntity} with a 409 status and the error message.
     * @see DuplicateEntityException
     * @see IllegalArgumentException
     * @see InvalidResetTokenException
     */
    @ExceptionHandler({DuplicateEntityException.class,
            IllegalArgumentException.class,
            InvalidResetTokenException.class})
    public ResponseEntity<String> handleDuplicateEntityException(DuplicateEntityException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}