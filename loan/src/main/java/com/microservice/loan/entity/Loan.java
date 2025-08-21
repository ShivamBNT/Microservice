package com.microservice.loan.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    private String mobileNumber;

    private String loanType;

    private String loanNumber;

    private Integer totalLoanAmt;

    private Integer amountPaid;

    private Integer outstandingAmt;
}
