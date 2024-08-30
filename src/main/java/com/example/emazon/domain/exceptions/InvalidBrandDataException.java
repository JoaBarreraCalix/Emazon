package com.example.emazon.domain.exceptions;

public class InvalidBrandDataException extends RuntimeException {
  public InvalidBrandDataException() {
    super("Brand data does not match with the existing brand in the database");
  }
}
