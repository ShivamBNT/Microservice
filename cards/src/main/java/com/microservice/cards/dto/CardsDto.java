package com.microservice.cards.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CardsDto {

    private Long  cardNumber;
    private String mobileNumber;
    private String cardType;
    private Integer totalLimit;
    private Integer amountUsed;
    private Integer availableAmount;

}
