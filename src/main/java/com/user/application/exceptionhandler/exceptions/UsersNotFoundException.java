package com.user.application.exceptionhandler.exceptions;

public class UsersNotFoundException extends RuntimeException {
    public UsersNotFoundException() {
        super("Users not found.");
    }
}
