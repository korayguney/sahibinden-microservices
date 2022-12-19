package com.sahibinden.customerservice.exception;

public class CustomerDoesNotExistException extends RuntimeException {
    public CustomerDoesNotExistException() {
    }

    public CustomerDoesNotExistException(String message) {
        super(message);
    }
}
