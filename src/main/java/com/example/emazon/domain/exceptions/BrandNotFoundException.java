package com.example.emazon.domain.exceptions;

public class BrandNotFoundException extends RuntimeException {
    public BrandNotFoundException(String message) {
    super(message);
  }
}
