package com.coople.gamepartnerservice.exception;

public class InvalidResetTokenException extends RuntimeException {

    public InvalidResetTokenException(String message){
        super(message);
    }
}
