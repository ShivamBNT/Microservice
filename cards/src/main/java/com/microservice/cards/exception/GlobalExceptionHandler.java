package com.microservice.cards.exception;

import com.microservice.cards.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {        Map<String,String> validationError = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            validationError.put(fieldName, validationMsg);
        });
        return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);    }


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
