package com.aitbenmoumen.e_bank.entities;

import com.aitbenmoumen.e_bank.enums.AccountStatus;

import java.util.Date;

public class BankAccount {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private Customer customer;  // to specify the cardinalities
}
