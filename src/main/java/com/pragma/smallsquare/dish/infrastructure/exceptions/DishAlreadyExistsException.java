package com.pragma.smallsquare.dish.infrastructure.exceptions;

public class DishAlreadyExistsException extends RuntimeException {
    public DishAlreadyExistsException(String message) {
        super(message);
    }
}
