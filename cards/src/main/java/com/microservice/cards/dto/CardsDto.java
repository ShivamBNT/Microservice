package com.microservice.cards.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class CardsDto {

    @NotEmpty(message = "Card number Should not be empty")
    @Pattern(regexp="(^$|[0-9]{12})",message = "CardNumber must be 12 digits")
    private Long  cardNumber;

    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
    @NotEmpty(message = "Mobile number should not be empty")
    private String mobileNumber;

    @NotEmpty(message = "Card type can not be null or empty.")
    private String cardType;

    @Positive(message = "Total limit must be grater than 0")
    private Integer totalLimit;

    @PositiveOrZero(message = "amount used must be 0 or more than 0")
    private Integer amountUsed;

    @PositiveOrZero(message = "available amount must be 0 or more than 0")
    private Integer availableAmount;

}
