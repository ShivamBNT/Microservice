package com.microservice.accounts.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound(String resourceName,String field, String fieldValue) {
        super(String.format("%s bot found with given data %s : %s",resourceName,field,fieldValue));
    }
}
