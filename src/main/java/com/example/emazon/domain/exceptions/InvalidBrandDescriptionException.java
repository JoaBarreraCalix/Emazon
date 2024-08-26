// domain.exceptions.InvalidBrandDescriptionException
package com.example.emazon.domain.exceptions;

public class InvalidBrandDescriptionException extends RuntimeException {
    public InvalidBrandDescriptionException() {
        super("Brand description is invalid or empty");
    }
}