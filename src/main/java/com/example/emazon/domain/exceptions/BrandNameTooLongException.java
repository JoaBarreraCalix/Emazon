// domain.exceptions.BrandNameTooLongException
package com.example.emazon.domain.exceptions;

public class BrandNameTooLongException extends RuntimeException {
    public BrandNameTooLongException() {
        super("Brand name must not exceed 50 characters");
    }
}