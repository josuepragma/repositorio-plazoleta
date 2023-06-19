package com.pragma.smallsquare.application.exceptions;

public class OrderStatusInProgressException extends RuntimeException{
    public OrderStatusInProgressException(String message) {
        super(message);
    }
}
