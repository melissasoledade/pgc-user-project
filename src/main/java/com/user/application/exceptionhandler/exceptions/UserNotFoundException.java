package com.user.application.exceptionhandler.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found.");
    }
}
