package com.microservice.loan.service;

import com.microservice.loan.constants.LoansConstants;
import com.microservice.loan.dto.LoansDto;
import com.microservice.loan.entity.Loan;
import com.microservice.loan.exception.LoanAlreadyPresent;
import com.microservice.loan.exception.ResourceNotFoundException;
import com.microservice.loan.mapper.LoanMapper;
import com.microservice.loan.repository.LoansRepository;
import com.microservice.loan.service.impl.ILoanService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class LoanServiceImpl implements ILoanService {

    private LoansRepository loansRepository;

    public LoanServiceImpl(LoansRepository loansRepository) {
        this.loansRepository = loansRepository;
    }

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loan> optionalLoans= loansRepository.findByMobileNumber(mobileNumber);
        if(optionalLoans.isPresent())
        {
            throw new LoanAlreadyPresent("Loan Details are already present in system.");
        }
        loansRepository.save(createNewLoan(mobileNumber));
    }

    @Override
    public LoansDto fetchLoanDetails(String mobileNumber) {
        Loan loan = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        return LoanMapper.mapToLoanDto(loan, new LoansDto());
    }

    @Override
    public boolean updateLoanDetails(LoansDto loansDto) {
        Loan loans =  loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "LoanNumber", loansDto.getLoanNumber()));
        LoanMapper.mapToLoan(loansDto, loans);
        loansRepository.save(loans);
        return  true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loan loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        loansRepository.deleteById(loans.getLoanId());
        return true;
    }


    private Loan createNewLoan(String mobileNumber) {
        Loan newLoan = new Loan();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoanAmt(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmt(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }
}
