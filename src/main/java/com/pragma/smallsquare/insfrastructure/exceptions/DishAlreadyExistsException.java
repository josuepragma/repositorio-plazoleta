package com.pragma.smallsquare.insfrastructure.exceptions;

public class DishAlreadyExistsException extends RuntimeException {
    public DishAlreadyExistsException(String message) {
        super(message);
    }
}
