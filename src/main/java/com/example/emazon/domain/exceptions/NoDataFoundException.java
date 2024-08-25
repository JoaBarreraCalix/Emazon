//domain.exceptions.NoDataFoundExceptionException
package com.example.emazon.domain.exceptions;

public class NoDataFoundException extends RuntimeException{

    public NoDataFoundException(){
        super("Not data found");
    }
}
