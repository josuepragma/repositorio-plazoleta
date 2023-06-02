package com.pragma.smallsquare.restaurant.insfrastructure.exceptions;

public class NoDataFoundException extends RuntimeException{
    public NoDataFoundException(String message) {
        super(message);
    }
}
