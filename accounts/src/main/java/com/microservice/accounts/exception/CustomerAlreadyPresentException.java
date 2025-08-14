package com.microservice.accounts.exception;

public class CustomerAlreadyPresentException extends RuntimeException{


    public CustomerAlreadyPresentException(String msg) {
        super(msg);
    }
}
