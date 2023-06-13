package com.example.library.exception;

public class BookHasRentalsException extends RuntimeException {
    public BookHasRentalsException(String message) {
        super(message);
    }
}

