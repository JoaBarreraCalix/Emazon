package com.example.emazon.domain.exceptions;

public class InvalidBrandDataException extends RuntimeException {
  public InvalidBrandDataException(String message) {
    super(message);
  }
}
