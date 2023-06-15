package com.pragma.smallsquare.insfrastructure.exceptions;

public class DishNotFoundException extends RuntimeException{
    public DishNotFoundException(String message) {
        super(message);
    }
}
