package com.pragma.smallsquare.application.exceptions;

public class UserIsNoOwnerException extends RuntimeException{
    public UserIsNoOwnerException(String message) {
        super(message);
    }
}
