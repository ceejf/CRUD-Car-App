package com.example.CRUDCarApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST) // handles 400 bad requests
    @ExceptionHandler(MethodArgumentNotValidException.class) // handles validation errors thrown when @Valid fails during the request
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>(); // create container to store key-value pairs

        ex.getBindingResult().getAllErrors().forEach((error) -> { // gets all the errors
            String pptName = ((FieldError) error).getField(); // get the field error for each
            String errorMessage = error.getDefaultMessage(); // get the error message for each
            errors.put(pptName, errorMessage); // put the field error and error message in the map for each
        });

        return errors; // returns error messages in JSON format
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // handles 500 for unexpected exceptions
    @ExceptionHandler()
    public Map<String, String> handleAllExceptions(Exception ex) {

        Map<String, String> error = new HashMap<>();

        error.put("error","Internal server error");
        error.put("details", ex.getMessage());

        return error;
    }
}
