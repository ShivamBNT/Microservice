package com.microservice.cards.repository;

import com.microservice.cards.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CardRepository extends JpaRepository<Cards,Long> {

    Optional<Cards> findByCardNumber(String cardNumber);

    Optional<Cards> findByMobileNumber(String mobileNumber);
}
