package com.pragma.smallsquare.dish.infrastructure.exceptions;

public class DishNotFoundException extends RuntimeException{
    public DishNotFoundException(String message) {
        super(message);
    }
}
