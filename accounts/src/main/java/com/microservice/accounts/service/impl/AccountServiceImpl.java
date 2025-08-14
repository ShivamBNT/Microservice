package com.microservice.accounts.service.impl;

import com.microservice.accounts.constant.AccountConstants;
import com.microservice.accounts.dto.AccountsDto;
import com.microservice.accounts.dto.CustomerDto;
import com.microservice.accounts.entity.Account;
import com.microservice.accounts.entity.Customer;
import com.microservice.accounts.exception.ResourceNotFound;
import com.microservice.accounts.mapper.AccountMapper;
import com.microservice.accounts.mapper.CustomerMapper;
import com.microservice.accounts.repository.AccountRepository;
import com.microservice.accounts.repository.CustomerRepository;
import com.microservice.accounts.service.IAccountService;
import com.microservice.accounts.exception.CustomerAlreadyPresentException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountServiceImpl implements IAccountService {

    private AccountRepository accountRepository;

    private CustomerRepository customerRepository;

    public AccountServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void createAccount(CustomerDto customerDto) {

        Optional<Customer> byMobileNumber = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(byMobileNumber.isPresent()){
            throw new CustomerAlreadyPresentException("Customer Already Present" + customerDto.getMobileNumber());
        }
        Customer customer = CustomerMapper.mapToCustomerEntity(customerDto,new Customer());
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("1");
        Customer savedCustomer = customerRepository.save(customer);

        accountRepository.save(createNewAccount(savedCustomer));


    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFound("Customer", "mobileNumber", mobileNumber)
        );
        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFound("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer,new CustomerDto());
        customerDto.setAccountsDto(AccountMapper.mapToAccountDto(account,new AccountsDto()));
        return customerDto;
    }

    /**
     * @param customerDto
     * @return
     */
    @Override
    public boolean updateAcount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto != null) {
            Account account = accountRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFound("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountMapper.mapToAccountEntity(accountsDto,account);
            account = accountRepository.save(account);

            Long customerId = account.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFound("Customer","CustomerId",customerId.toString())
            );
            CustomerMapper.mapToCustomerEntity(customerDto,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }

        return isUpdated;
    }

    /**Delete Account Information(soft delete)
     * @param mobileNumber
     * @return
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFound("customer", "mobileNumber",mobileNumber)
        );
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        accountRepository.deleteById(customer.getCustomerId());
        return true;
    }

    private Account createNewAccount(Customer customer){
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 100000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("1");
        return  newAccount;
    }
}
