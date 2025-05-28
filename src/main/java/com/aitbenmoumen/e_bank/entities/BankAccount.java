package com.aitbenmoumen.e_bank.entities;

import com.aitbenmoumen.e_bank.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data @NoArgsConstructor @AllArgsConstructor
public class BankAccount {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private Customer customer;  // to specify the cardinalities
    private List<AccountOperation> operations;

}
