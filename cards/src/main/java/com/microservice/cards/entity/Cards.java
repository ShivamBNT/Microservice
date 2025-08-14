package com.microservice.cards.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter@Setter@ToString@AllArgsConstructor@NoArgsConstructor
@Entity
@Table(name = "cards")
public class Cards extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @Column
    private String mobileNumber;

    @Column
    private Long cardNumber;

    @Column
    private String cardType;

    @Column
    private Integer totalLimit;

    @Column
    private Integer amountUsed;

    @Column
    private Integer availableAmount;
}
