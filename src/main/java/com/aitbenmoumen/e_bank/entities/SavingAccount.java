package com.aitbenmoumen.e_bank.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
//@DiscriminatorValue("SA") // Discriminator value for the SavingsAccount entity
public class SavingAccount extends BankAccount {
    private double interestRate; // The interest rate for the savings account
}
