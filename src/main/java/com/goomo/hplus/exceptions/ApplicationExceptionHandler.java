package com.goomo.hplus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

@ControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler({ApplicationException.class, AsyncRequestTimeoutException.class})
    public String handleException()
    {
        System.out.println("In Global Exception Handler");
        return "error";
    }

    @ExceptionHandler(LoginfailureException.class)
    public ResponseEntity hsndleLoginFailure(LoginfailureException loginfailureException)
    {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(loginfailureException.getMessage());
    }
}
