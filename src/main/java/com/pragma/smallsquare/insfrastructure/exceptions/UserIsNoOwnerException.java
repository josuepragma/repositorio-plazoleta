package com.pragma.smallsquare.insfrastructure.exceptions;

public class UserIsNoOwnerException extends RuntimeException{
    public UserIsNoOwnerException(String message) {
        super(message);
    }
}
