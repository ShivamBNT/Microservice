package com.microservice.accounts.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CustomerDto {

    @NotEmpty(message = "Name can not be null or empty")
    @Size(min = 4 , max = 10 , message = "The length of name should be 4 to 10")
    private String name;

    @NotEmpty(message = "Email address can not be a null or empty")
    @Email(message = "Enter a valid email address")
    private String email;

    @NotEmpty(message = "Mobile Number can not be null or empty")
    @Pattern(regexp = "\\d{10}" , message = "Mobile number should be 10 digits only.")
    private String mobileNumber;

    private AccountsDto accountsDto;

}
