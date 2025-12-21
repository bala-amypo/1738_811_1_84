package com.example.demo.exception;

public class DuplicateVinException extends RuntimeException {

    public DuplicateVinException(String message) {
        super(message);
    }
}
