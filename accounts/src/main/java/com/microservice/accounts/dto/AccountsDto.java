package com.microservice.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

    @NotEmpty(message = "Account number should not be empty.")
    @Pattern(regexp = "\\d{12}" , message = "Account number should be 12 digits.")
    private Long accountNumber;

    @NotEmpty(message = "Please Enter Account Type.")
    private String accountType;

    @NotEmpty(message = "BranchAddress can not be a null or empty")
    private String branchName;

}
