package com.softwareengineering.planai.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("com.softwareengineering.planai.web")
@RestController
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult IllegalArgExceptionHandler(IllegalArgumentException error) {
        return new ErrorResult("BAD_REQUEST", error.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IllegalStateException.class)
    public ErrorResult IllegalArgExceptionHandler(IllegalStateException error) {
        return new ErrorResult("INTERNAL_SERVER_ERROR", error.getMessage());
    }
}
