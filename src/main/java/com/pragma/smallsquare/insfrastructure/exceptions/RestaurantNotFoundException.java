package com.pragma.smallsquare.insfrastructure.exceptions;

public class RestaurantNotFoundException extends RuntimeException{
    public RestaurantNotFoundException(String message) {
        super(message);
    }
}
