package com.aitbenmoumen.e_bank.entities;

import java.util.Date;

import com.aitbenmoumen.e_bank.enums.OperationType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class AccountOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType operationType; // Assuming OperationType is an enum you have defined
    @ManyToOne
    private BankAccount bankAccount;
}
