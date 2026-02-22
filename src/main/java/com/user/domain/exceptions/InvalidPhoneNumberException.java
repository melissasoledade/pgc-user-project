package com.user.domain.exceptions;

public class InvalidPhoneNumberException extends RuntimeException {
    public InvalidPhoneNumberException() {
        super("Invalid phone number.");
    }
}
