// domain.exceptions.InvalidBrandNameException
package com.example.emazon.domain.exceptions;

public class InvalidBrandNameException extends RuntimeException {
    public InvalidBrandNameException() {
        super("Brand name is invalid or empty");
    }
}