package com.pragma.smallsquare.restaurant.insfrastructure.exceptions;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
