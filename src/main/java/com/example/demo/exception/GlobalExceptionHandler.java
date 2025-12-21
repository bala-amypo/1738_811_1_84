package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.demo.exception.VehicleNotFoundException;
import com.example.demo.exception.DuplicateVinException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;

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
     @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntime(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleNotFound(EntityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

   
}



