package com.pragma.smallsquare.restaurant.insfrastructure.exceptions;

public class DishAlreadyExistsException extends RuntimeException {
    public DishAlreadyExistsException(String message) {
        super(message);
    }
}
