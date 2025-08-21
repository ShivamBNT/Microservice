package com.microservice.loan.exception;

import com.microservice.loan.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoanAlreadyPresent.class)
    public ResponseEntity<ErrorResponse> handleCardDetailsAlreadyPresentException(WebRequest request, LoanAlreadyPresent loanAlreadyPresent){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                request.getDescription(false),
                loanAlreadyPresent.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now());
        return new ResponseEntity(errorResponseDto,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(WebRequest request, ResourceNotFoundException resourceNotFound){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                request.getDescription(false),
                resourceNotFound.getMessage(),
                HttpStatus.NOT_FOUND,
                LocalDateTime.now());
        return new ResponseEntity(errorResponseDto,HttpStatus.NOT_FOUND);
    }
}
