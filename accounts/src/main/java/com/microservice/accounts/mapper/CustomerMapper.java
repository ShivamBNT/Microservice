package com.microservice.accounts.mapper;

import com.microservice.accounts.dto.CustomerDto;
import com.microservice.accounts.entity.Customer;

public class CustomerMapper {

    public static Customer mapToCustomerEntity(CustomerDto customerDto,Customer customer){
        customer.setMobileNumber(customerDto.getMobileNumber());
        customer.setEmail(customerDto.getEmail());
        customer.setName(customerDto.getName());
         return customer;
    }

    public static CustomerDto mapToCustomerDto(Customer customer,CustomerDto customerDto){
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }




}
