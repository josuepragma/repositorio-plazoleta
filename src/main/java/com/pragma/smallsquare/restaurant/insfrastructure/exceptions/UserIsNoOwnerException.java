package com.pragma.smallsquare.restaurant.insfrastructure.exceptions;

public class UserIsNoOwnerException extends RuntimeException{
    public UserIsNoOwnerException(String message) {
        super(message);
    }
}
