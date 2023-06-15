package com.pragma.smallsquare.insfrastructure.exceptions;

public class RestaurantAlreadyExistsException extends RuntimeException {

    public RestaurantAlreadyExistsException(String message) {
        super(message);
    }
}
