package com.microservice.loan.mapper;

import com.microservice.loan.dto.LoansDto;
import com.microservice.loan.entity.Loan;

public class LoanMapper {
    public static LoansDto mapToLoanDto(Loan loan, LoansDto loansDto) {
        loansDto.setLoanNumber(loan.getLoanNumber());
        loansDto.setLoanAmt(loan.getTotalLoanAmt());
        loansDto.setAmountPaid(loan.getAmountPaid());
        loansDto.setMobileNumber(loan.getMobileNumber());
        loansDto.setOutstandingAmt(loan.getOutstandingAmt());
        return loansDto;
    }

    public static Loan mapToLoan(LoansDto loansDto,Loan loan) {
        loan.setLoanNumber(loansDto.getLoanNumber());
        loan.setTotalLoanAmt(loansDto.getLoanAmt());
        loan.setAmountPaid(loansDto.getAmountPaid());
        loan.setMobileNumber(loansDto.getMobileNumber());
        loan.setOutstandingAmt(loansDto.getOutstandingAmt());
        return loan;
    }
}
