package com.example.emazon.domain.exceptions;

public class InvalidCategoryNameException extends RuntimeException {
    public InvalidCategoryNameException() {
        super("Category name cannot be null or empty.");
    }
}
