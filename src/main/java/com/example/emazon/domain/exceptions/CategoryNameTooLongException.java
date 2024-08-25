package com.example.emazon.domain.exceptions;

public class CategoryNameTooLongException extends RuntimeException {
    public CategoryNameTooLongException() {
        super("Category name must not exceed 50 characters.");
    }
}
