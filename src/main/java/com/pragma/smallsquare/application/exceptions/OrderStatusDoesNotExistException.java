package com.pragma.smallsquare.application.exceptions;

public class OrderStatusDoesNotExistException extends RuntimeException{
    public OrderStatusDoesNotExistException(String message) {
        super(message);
    }
}
