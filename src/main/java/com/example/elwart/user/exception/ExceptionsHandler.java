package com.example.elwart.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String getUserNotFoundException(UserNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String getRoleNotFoundException(RoleNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(DelegationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String getDelegationNotFoundException(DelegationNotFoundException ex){ return ex.getMessage();}

    @ExceptionHandler(NotTicketPriceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String getNotTicketPriceException(NotTicketPriceException ex) { return  ex.getMessage();}

    @ExceptionHandler(NotKmException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String getNotKmException(NotKmException ex) { return  ex.getMessage();}

    @ExceptionHandler(BadAutoCapacityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String getBadAutoCapacityException(BadAutoCapacityException ex) { return  ex.getMessage();}


}
