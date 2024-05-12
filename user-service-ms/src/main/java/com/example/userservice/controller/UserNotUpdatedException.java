package com.example.userservice.controller;

public class UserNotUpdatedException extends RuntimeException {
    public UserNotUpdatedException(String s) {
        super(s);
    }
}
