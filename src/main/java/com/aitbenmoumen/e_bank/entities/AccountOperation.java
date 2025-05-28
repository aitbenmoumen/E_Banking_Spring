package com.aitbenmoumen.e_bank.entities;

import com.aitbenmoumen.e_bank.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountOperation {
    private Long id;
    private Date date;
    private double amount;
    private OperationType type;
    private BankAccount account;
}
