// domain.exceptions.BrandAlreadyExistsException
package com.example.emazon.domain.exceptions;

public class BrandAlreadyExistsException extends RuntimeException {
    public BrandAlreadyExistsException() {
        super("Brand already exists");
    }
}
