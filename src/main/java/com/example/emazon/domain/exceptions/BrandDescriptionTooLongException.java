// domain.exceptions.BrandDescriptionTooLongException
package com.example.emazon.domain.exceptions;

public class BrandDescriptionTooLongException extends RuntimeException {
    public BrandDescriptionTooLongException() {
        super("Brand description must not exceed 120 characters");
    }
}