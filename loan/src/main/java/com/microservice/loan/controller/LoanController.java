package com.microservice.loan.controller;

import com.microservice.loan.constants.LoansConstants;
import com.microservice.loan.dto.LoansDto;
import com.microservice.loan.dto.ResponseDto;
import com.microservice.loan.service.impl.ILoanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoanController {

    private ILoanService iLoanService;

    public LoanController(ILoanService iLoanService) {
        this.iLoanService = iLoanService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam("mobileNumber") String mobileNumber)
    {
        iLoanService.createLoan(mobileNumber);
        return ResponseEntity
                    .status(HttpStatus.OK)
                .body(new ResponseDto(LoansConstants.STATUS_201,LoansConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<LoansDto> getLoanDetails(@RequestParam("mobileNumber") String mobileNumber){
        LoansDto loansDto = iLoanService.fetchLoanDetails(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loansDto);
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseDto> updateLoanDetails(@Valid @RequestBody LoansDto loansDto){
        boolean isUpdated = iLoanService.updateLoanDetails(loansDto);
        if(isUpdated)
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_101,LoansConstants.MESSAGE_101));
        else
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(LoansConstants.STATUS_102,LoansConstants.MESSAGE_102));

    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteDetails(@RequestParam("mobileNumber") String mobileNumber){
        boolean isDeleted = iLoanService.deleteLoan(mobileNumber);
        if(isDeleted)
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_103,LoansConstants.MESSAGE_103));
        else
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(LoansConstants.STATUS_102,LoansConstants.MESSAGE_102));
    }





}
