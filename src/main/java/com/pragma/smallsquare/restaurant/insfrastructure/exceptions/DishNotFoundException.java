package com.pragma.smallsquare.restaurant.insfrastructure.exceptions;

public class DishNotFoundException extends RuntimeException{
    public DishNotFoundException(String message) {
        super(message);
    }
}
