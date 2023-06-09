package com.example.Library.exception;

public class BookHasRentalsException extends RuntimeException {
    public BookHasRentalsException(String message) {
        super(message);
    }
}

