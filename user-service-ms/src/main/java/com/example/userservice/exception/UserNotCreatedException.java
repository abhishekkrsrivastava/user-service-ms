package com.example.userservice.exception;

public class UserNotCreatedException extends RuntimeException {

    public UserNotCreatedException(String message) {
        super(message);
    }
}
