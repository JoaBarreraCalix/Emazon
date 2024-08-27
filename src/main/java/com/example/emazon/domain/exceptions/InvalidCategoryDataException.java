package com.example.emazon.domain.exceptions;

public class InvalidCategoryDataException extends RuntimeException {
    public InvalidCategoryDataException(String message) {
        super(message);
    }
}
