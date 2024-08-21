// domain.exceptions.NoDataFoundException
package com.example.emazon.domain.exceptions;

public class NoDataFoundException extends RuntimeException {

    public NoDataFoundException() {
        super("No data found");
    }

    public NoDataFoundException(String message) {
        super(message);
    }
}