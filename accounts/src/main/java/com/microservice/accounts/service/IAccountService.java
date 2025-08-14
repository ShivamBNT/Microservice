package com.microservice.accounts.service;

import com.microservice.accounts.dto.CustomerDto;

public interface IAccountService {

    /**
     *
     * @param customerDto
     * @return
     */
    void createAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber
     * @return
     */
    CustomerDto fetchAccount(String mobileNumber);

    /**
     *
     * @param customerDto
     * @return
     */
    boolean updateAcount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);
}
