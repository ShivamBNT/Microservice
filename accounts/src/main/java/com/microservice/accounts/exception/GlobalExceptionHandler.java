package com.microservice.accounts.exception;

import com.microservice.accounts.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerAlreadyPresentException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyPresentException(CustomerAlreadyPresentException customerAlreadyPresentException
                                                , WebRequest request){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                request.getDescription(false),
                HttpStatus.BAD_REQUEST,
                customerAlreadyPresentException.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception exception
            , WebRequest request){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                request.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDto,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFound resourceNotFound
            , WebRequest request){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                request.getDescription(false),
                HttpStatus.NOT_FOUND,
                resourceNotFound.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDto,HttpStatus.NOT_FOUND);
    }

}
