package com.microservice.accounts.mapper;

import com.microservice.accounts.dto.AccountsDto;
import com.microservice.accounts.entity.Account;

public class AccountMapper {

    public static Account mapToAccountEntity(AccountsDto accountsDto, Account account){
         account.setAccountNumber(accountsDto.getAccountNumber());
         account.setAccountType(accountsDto.getAccountType());
         account.setBranchAddress(accountsDto.getBranchName());
         return account;
    }

    public static AccountsDto mapToAccountDto(Account account,AccountsDto accountsDto){
        accountsDto.setAccountNumber(account.getAccountNumber());
        accountsDto.setAccountType(account.getAccountType());
        accountsDto.setBranchName(account.getBranchAddress());
        return accountsDto;
    }




}
