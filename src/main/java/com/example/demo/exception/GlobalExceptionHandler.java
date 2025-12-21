package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.demo.exception.VehicleNotFoundException;
import com.example.demo.exception.DuplicateVinException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VehicleNotFoundException.class)
    public String handleVehicleNotFound(VehicleNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(DuplicateVinException.class)
    public String handleDuplicateVin(DuplicateVinException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                 .getFieldError()
                 .getDefaultMessage();
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception ex) {
        return ex.getMessage();
    }
}
