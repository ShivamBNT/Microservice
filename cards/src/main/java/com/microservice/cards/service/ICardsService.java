package com.microservice.cards.service;

import com.microservice.cards.dto.CardsDto;

public interface ICardsService {


    void createCard(String mobileNumber);

    CardsDto fetchCardDetails(String mobileNumber);

    boolean updateCardDetails(CardsDto cardsDto);

    boolean deleteCardDetails(String mobileNumber);
}
