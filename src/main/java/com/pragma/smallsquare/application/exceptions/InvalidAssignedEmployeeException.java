package com.pragma.smallsquare.application.exceptions;

public class InvalidAssignedEmployeeException extends RuntimeException{
    public InvalidAssignedEmployeeException(String message) {
        super(message);
    }
}
