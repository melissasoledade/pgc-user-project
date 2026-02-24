package com.user.domain.exceptions;

public class InvalidBirthDateException extends RuntimeException {
    public InvalidBirthDateException() {
        super("Invalid birth date. User should be over 18 years old.");
    }
}
