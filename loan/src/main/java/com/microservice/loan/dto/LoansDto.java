package com.microservice.loan.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class LoansDto {

    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
    @NotEmpty(message = "Mobile number should not be empty")
    private String mobileNumber;

    @NotEmpty(message = "LoanType should not be null or empty")
    private String loanType;

    @NotEmpty(message = "LoanType should not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Loan Number must be 10 digits")
    private String loanNumber;

    @Positive(message = "Loan amount must be more than 0")
    private Integer loanAmt;

    @Positive(message = "Paid Loan Amount  must be more than 0")
    private Integer amountPaid;

    @Positive(message = "Outstanding amount must be more than 0")
    private Integer outstandingAmt;
}
