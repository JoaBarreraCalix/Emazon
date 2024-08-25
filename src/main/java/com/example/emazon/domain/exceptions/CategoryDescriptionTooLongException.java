package com.example.emazon.domain.exceptions;

public class CategoryDescriptionTooLongException extends RuntimeException {
    public CategoryDescriptionTooLongException() {
        super("Category description must not exceed 90 characters.");
    }
}
