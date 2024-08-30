package com.example.emazon.domain.exceptions;

public class BrandNotFoundException extends RuntimeException {
    public BrandNotFoundException() {
    super("Brand not found in the database");
  }
}
