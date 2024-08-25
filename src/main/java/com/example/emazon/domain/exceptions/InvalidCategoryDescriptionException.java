//domain.exceptions.InvalidCategoryDescriptionException
package com.example.emazon.domain.exceptions;

public class InvalidCategoryDescriptionException extends RuntimeException {
    public InvalidCategoryDescriptionException() {
        super("Category description cannot be null or empty.");
    }
}
