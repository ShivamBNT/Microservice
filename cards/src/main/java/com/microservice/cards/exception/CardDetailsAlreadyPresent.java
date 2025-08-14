package com.microservice.cards.exception;

public class CardDetailsAlreadyPresent extends RuntimeException{

    public CardDetailsAlreadyPresent(String message) {
        super(message);
    }
}
