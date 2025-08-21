package com.microservice.cards.controller;

import com.microservice.cards.constants.Constants;
import com.microservice.cards.dto.CardsDto;
import com.microservice.cards.dto.ResponseDto;
import com.microservice.cards.service.ICardsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cards")
public class CardsController {

    ICardsService iCardsService;

    public CardsController(ICardsService iCardsService) {
        this.iCardsService = iCardsService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(@RequestParam(name = "mobileNumber") String mobileNumber){
        iCardsService.createCard(mobileNumber);
        return  ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ResponseDto(Constants.STATUS_201, Constants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestParam(name = "mobileNumber") String mobileNumber){
        CardsDto cardsDto = iCardsService.fetchCardDetails(mobileNumber);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(cardsDto);
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseDto> updateCardsDetail(@Valid @RequestBody CardsDto cardsDto){
        boolean isUpdated = iCardsService.updateCardDetails(cardsDto);
        if(isUpdated){
            return  ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(Constants.STATUS_200,Constants.MESSAGE_200));
        }
        else
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(Constants.STATUS_113,Constants.MESSAGE_113_UPDATE));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCardDetails(@RequestParam(name = "mobileNumber") String mobileNumber){
            boolean isDeleted = iCardsService.deleteCardDetails(mobileNumber);
        if(isDeleted){
            return  ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(Constants.STATUS_200,Constants.MESSAGE_200));
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(Constants.STATUS_114, Constants.MESSAGE_114_DELETE));
        }

    }
}
