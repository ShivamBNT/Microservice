package com.microservice.cards.mapper;

import com.microservice.cards.dto.CardsDto;
import com.microservice.cards.entity.Cards;

public class CardsMapper {

    public static CardsDto mapToCardsDto(Cards cards,CardsDto cardsDto){
        cardsDto.setCardNumber(cards.getCardNumber());
        cardsDto.setCardType(cards.getCardType());
        cardsDto.setTotalLimit(cards.getTotalLimit());
        cardsDto.setAmountUsed(cards.getAmountUsed());
        cardsDto.setAvailableAmount(cards.getAvailableAmount());
        cardsDto.setMobileNumber(cards.getMobileNumber());
        return cardsDto;
    }

    public static Cards mapToCards(CardsDto cardsDto,Cards cards){
        cards.setCardNumber(cardsDto.getCardNumber());
        cards.setCardType(cardsDto.getCardType());
        cards.setTotalLimit(cardsDto.getTotalLimit());
        cards.setAmountUsed(cardsDto.getAmountUsed());
        cards.setAvailableAmount(cardsDto.getAvailableAmount());
        cards.setMobileNumber(cardsDto.getMobileNumber());
        return cards;
    }
}
