package com.microservice.cards.service.impl;

import com.microservice.cards.constants.Constants;
import com.microservice.cards.dto.CardsDto;
import com.microservice.cards.entity.Cards;
import com.microservice.cards.exception.CardDetailsAlreadyPresent;
import com.microservice.cards.exception.ResourceNotFound;
import com.microservice.cards.mapper.CardsMapper;
import com.microservice.cards.repository.CardRepository;
import com.microservice.cards.service.ICardsService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class CardsService implements ICardsService {

    CardRepository cardRepository;

    public CardsService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> byCardNumber = cardRepository.findByMobileNumber(mobileNumber);
        if(byCardNumber.isPresent()){
            throw new CardDetailsAlreadyPresent("Card Details are already registered with given mobile number" + mobileNumber);
        }
        cardRepository.save(createNewCard(mobileNumber));
    }

    @Override
    public CardsDto fetchCardDetails(String mobileNumber) {
        Cards cards = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFound("Card","mobileNumber",mobileNumber)
        );
        return CardsMapper.mapToCardsDto(cards,new CardsDto());
    }

    @Override
    public boolean updateCardDetails(CardsDto cardsDto) {
        Cards cards = cardRepository.findByMobileNumber(cardsDto.getMobileNumber()).orElseThrow(
                () -> new ResourceNotFound("Card", "CardNumber", cardsDto.getCardNumber().toString())
        );
        CardsMapper.mapToCards(cardsDto,cards);
        cardRepository.save(cards);
        return true;
    }

    @Override
    public boolean deleteCardDetails(String mobileNumber) {
        Cards cards = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFound("card", "mobileNumber", mobileNumber)
        );
        cardRepository.deleteById(cards.getCardId());
        return true;
    }

    private Cards createNewCard(String mobileNumber){
        Cards cards =new Cards();
        Long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        cards.setCardNumber(randomCardNumber);
        cards.setCardType(Constants.CARD_TYPE_CREDIT);
        cards.setAmountUsed(0);
        cards.setAvailableAmount(Constants.CARD_LIMIT);
        cards.setTotalLimit(Constants.CARD_LIMIT);
        cards.setCreatedBy("1");
        cards.setUpdatedBy("1");
        return cards;
    }
}
