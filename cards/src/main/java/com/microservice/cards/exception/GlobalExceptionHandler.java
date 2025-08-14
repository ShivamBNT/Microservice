package com.microservice.cards.exception;

import com.microservice.cards.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CardDetailsAlreadyPresent.class)
    public ResponseEntity<ErrorResponse> handleCardDetailsAlreadyPresentException(WebRequest request, CardDetailsAlreadyPresent cardDetailsNotFound){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                request.getDescription(false),
                HttpStatus.BAD_REQUEST,
                cardDetailsNotFound.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity(errorResponseDto,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(WebRequest request, ResourceNotFound resourceNotFound){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                request.getDescription(false),
                HttpStatus.NOT_FOUND,
                resourceNotFound.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity(errorResponseDto,HttpStatus.NOT_FOUND);
    }
}
