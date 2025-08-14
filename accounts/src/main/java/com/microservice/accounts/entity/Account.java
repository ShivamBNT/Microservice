package com.microservice.accounts.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
public class Account extends BaseEntity{

    @Id
    //custom logic to create account number
    private Long accountNumber;

    @Column
    private String accountType;

    @Column
    private String  branchAddress;

    @Column
    private Long customerId;
}
