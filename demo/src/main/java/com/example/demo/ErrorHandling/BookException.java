package com.example.demo.ErrorHandling;

public class BookException extends  RuntimeException {
    public BookException(String message){
        super(message);
    }
}
